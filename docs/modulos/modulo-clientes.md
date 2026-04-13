# Clientes - Documentación Técnica

## Resumen
- Gestión del catálogo de clientes/pacientes del sistema Óptica, con búsqueda, filtrado y panel de resumen integral.
- 1 sub-vista principal (Lista de Clientes) con paginación.
- Patrón arquitectónico: **Facade + Paginación + Panel resumen persistente**.

## Estructura de Clases

| Clase | Responsabilidad |
|-------|----------------|
| `ClientesController.java` | Controlador del módulo. Configura filtros, tabla paginada de clientes y panel resumen lateral. Delega toda lógica de negocio a `ClientesFacade`. |
| `ClientesFacade.java` | Capa de abstracción entre `DemoStore` y la vista. Filtra la colección `store.clientes`, mapea a `ClientesRowModel` y aplica paginación vía `PaginationHelper`. |
| `ClientesFilters.java` | Criterios de filtrado: texto de búsqueda, estado, última visita, estado de receta. |
| `ClientesRowModel.java` | Record que mapea la entidad `Cliente` a filas de tabla. Contiene método estático `from(Cliente)` para conversión. |
| `ClientesSummaryModel.java` | Modelo resumen para el panel lateral derecho. Presenta datos personales, estado comercial y métricas de actividad del cliente seleccionado. |

### ClientesRowModel

| Campo | Descripción | Columna en TableView |
|-------|-------------|---------------------|
| nombre | Nombre completo del cliente | "Nombre" |
| codigo | Código interno | "Código" |
| telefono | Teléfono de contacto | "Teléfono" |
| email | Correo electrónico | "Email" |
| ultimaVisita | Fecha de última visita | "Última Visita" |
| estado | Estado: ACTIVO, INACTIVO | "Estado" |
| receta | Estado de receta: Vigente, Vencida, Sin receta | "Receta" |
| sucursal | Sucursal habitual | "Sucursal" |

### ClientesSummaryModel

| Campo | Descripción |
|-------|-------------|
| nombre | Nombre completo del cliente |
| codigo | Código interno |
| estado | Estado (activo, inactivo, etc.) |
| sucursal | Sucursal habitual |
| telefono | Teléfono de contacto |
| email | Correo electrónico |
| ultimaCompra | Fecha/descripción de última compra |
| saldoPendiente | Saldo pendiente de pago |
| ordenesActivas | Cantidad de órdenes activas |
| entregasPendientes | Cantidad de entregas pendientes |

## Sub-vistas

| Sub-vista | ToggleButton | Qué datos muestra | Qué RowModel usa |
|-----------|-------------|-------------------|------------------|
| Lista de Clientes | N/A (vista única) | Tabla paginada con datos básicos de clientes: nombre, código, teléfono, email, última visita, estado, receta, sucursal | `ClientesRowModel` |

## Flujo de Datos

```
Controller.initialize()
    → Obtiene DemoStore desde App.getDemoStore()
    → Crea ClientesFacade(store)
    → Crea ClientesFilters() (valores por defecto)
    → setupFilterCombos() — estado, última visita, receta, búsqueda libre
    → setupTable() — configura columnas y cellValueFactory
    → loadClientes() — facade.findPage(filters, pageRequest) → PageResult<ClientesRowModel>
    → Actualiza TableView con página actual
    → Configura paginación (anterior/siguiente)

Al seleccionar un cliente:
    onClienteSelected(cliente)
        → facade.buildSummary(cliente) → ClientesSummaryModel
        → updateSummaryPanel(summary) — actualiza panel derecho

Al aplicar filtros:
    applyFilters()
        → Actualiza currentFilters desde combos
        → facade.findPage(currentFilters, pageRequest)
        → Actualiza TableView con resultados filtrados
```

## Filtros Disponibles

| Filtro | Tipo | Opciones | Cómo filtra el Facade |
|--------|------|----------|----------------------|
| Búsqueda | TextField | Texto libre | `FilterSupport.matchesText(searchText, nombreCompleto, codigoInterno, telefono)` — búsqueda parcial en 3 campos. |
| Estado | ComboBox | ACTIVO, INACTIVO (desde `store.clientes`) | `FilterSupport.matchesExact(estadoStr, filters.getEstado())` — filtro exacto sobre `Cliente.getEstado().name()`. |
| Última Visita | ComboBox | Fechas de `store.clientes` | `FilterSupport.matchesExact(ultimaVisita, filters.getUltimaVisita())` |
| Receta | ComboBox | Vigente, Vencida, Sin receta (desde `store.clientes`) | `FilterSupport.matchesExact(estadoReceta, filters.getReceta())` |

## Panel Resumen (Derecho)

El `ClientesSummaryModel` se actualiza al seleccionar un cliente en la tabla:

- `facade.buildSummary(cliente)` → `ClientesSummaryModel.from(cliente)` — mapea la entidad `Cliente` al modelo resumen.
- Se actualizan 10 campos: nombre, código, estado, sucursal, teléfono, email, última compra, saldo pendiente, órdenes activas, entregas pendientes.
- Los campos `ultimaCompra`, `saldoPendiente`, `ordenesActivas` y `entregasPendientes` son valores placeholder en la demo ("Sin compras recientes", "$0.00", "0", "0").

## Patrones de Diseño Aplicados

| Patrón | Descripción |
|--------|-------------|
| **Facade** | `ClientesFacade` abstrae el acceso a `store.clientes`, aplicando filtros y paginación. |
| **Paginación** | `PaginationHelper.page(filtered, pageRequest)` retorna `PageResult<T>` con datos de página. |
| **Mapper/Converter** | `ClientesRowModel.from(Cliente)` convierte entidad de dominio a modelo de presentación. |
| **Factory** | `ComboBoxFactory.createFilterCombo()` para combos filtrables. |
| **Observer** | Listeners en `searchField.textProperty` y combos disparan `applyFilters()`. |
| **Presentation Model** | `ClientesFilters` separa el estado de los filtros de la vista. |

## Integración con Otros Módulos

| Módulo | Dirección | Descripción |
|--------|-----------|-------------|
| `Agenda` | Clientes → Agenda | Los clientes de las citas referencian entidades `Cliente`. |
| `Recetas` | Clientes → Recetas | Las recetas están asociadas a un `clienteId`. El módulo Recetas busca clientes vía `RecetasFacade.findClients()`. |
| `VentaOptica` | Clientes → VentaOptica | El wizard de venta busca clientes en la etapa 1 (`VentaOpticaFacade.searchClientes()`). |
| `Caja` | Clientes → Caja | Los cobros referencian `clienteNombre` y `clienteId`. |
| `Entregas` | Clientes → Entregas | Las entregas referencian clientes asociados a ventas. |
| `Inicio` | Clientes → Inicio | El dashboard muestra datos consolidados de clientes en KPIs y alertas. |

## Archivos Relacionados

```
modules/clientes/
├── ClientesController.java
├── ClientesFacade.java
├── ClientesFilters.java
├── ClientesRowModel.java
├── ClientesSummaryModel.java
└── ClientesView.fxml

shared/query/
├── FilterSupport.java
├── PaginationHelper.java
├── PageRequest.java
└── PageResult.java

shared/domain/cliente/
└── Cliente.java
```
