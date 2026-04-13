# Venta Óptica - Documentación Técnica

## Resumen
- Wizard de venta de lentes y monturas con proceso multi-etapa: búsqueda de cliente, selección de receta, catálogo de monturas, selección de lentes, configuración de pago, resumen de venta e historial de ventas.
- 7 etapas de wizard (no son sub-vistas con ToggleButton, sino etapas secuenciales de un flujo).
- Patrón arquitectónico: **Facade + Wizard Multi-Etapa + Panel resumen de orden**.

## Estructura de Clases

| Clase | Responsabilidad |
|-------|----------------|
| `VentaOpticaController.java` | Controlador del wizard de venta. Gestiona las 7 etapas del proceso, búsqueda de clientes, selección de receta, catálogo de monturas, selección de lentes, configuración de pago y confirmación. Delega toda lógica a `VentaOpticaFacade`. |
| `VentaOpticaFacade.java` | Capa de abstracción entre `DemoStore` y las etapas del wizard. Busca clientes, retorna recetas disponibles, catálogo de monturas con filtros, tipos de lentes, historial de ventas y construye el resumen de orden. |
| `VentaOpticaFilters.java` | Criterios de filtrado por etapa: búsqueda de cliente, marca de montura, material de montura, tipo de lente, índice de lente. |
| `VentaOpticaRowModel.java` | Contiene 4 records inner: `ClientSearchRow`, `RecipeRow`, `MonturaRow`, `HistoricRow`. Cada uno alimenta una etapa diferente del wizard. |
| `VentaOpticaSummaryModel.java` | Modelo resumen para el panel lateral derecho. Presenta el detalle completo de la orden en construcción: cliente, receta, montura, lente, valores económicos y estado. |

### Inner Records de VentaOpticaRowModel

| Record | Campos | Etapa del wizard que alimenta |
|--------|--------|------------------------------|
| `ClientSearchRow` | nombre, codigo, telefono, ultimaVisita, estado, sucursal | Etapa 1 — Búsqueda de cliente |
| `RecipeRow` | fecha, estado, profesional, odResumen, oiResumen, pd | Etapa 2 — Selección de receta |
| `MonturaRow` | referencia, nombre, marca, color, precio, stock, sucursal | Etapa 3 — Catálogo de monturas |
| `HistoricRow` | fecha, orden, cliente, monto, metodo, estado, comprobante | Etapa 7 — Historial de ventas |

### VentaOpticaSummaryModel

| Campo | Descripción |
|-------|-------------|
| cliente | Nombre del cliente |
| codigo | Código de la orden |
| receta | Fecha de receta seleccionada |
| montura | Descripción de la montura |
| lente | Tipo de lente |
| pd | Distancia pupilar |
| subtotal | Subtotal antes de descuentos |
| descuento | Monto de descuento aplicado |
| abono | Abono inicial |
| saldo | Saldo pendiente (calculado: subtotal - descuento - abono) |
| estado | Estado de la orden (BORRADOR, EN_PROCESO, etc.) |
| entregaEstimada | Fecha estimada de entrega |
| laboratorio | Laboratorio asignado |

## Sub-vistas (Etapas del Wizard)

| Etapa | ToggleButton/Control | Qué datos muestra | Qué RowModel usa |
|-------|---------------------|-------------------|------------------|
| Etapa 1: Búsqueda de Cliente | Navegación wizard | Tabla de clientes con búsqueda por nombre, código o teléfono | `ClientSearchRow` |
| Etapa 2: Selección de Receta | Navegación wizard | Recetas disponibles del cliente con graduación OD/OI | `RecipeRow` |
| Etapa 3: Catálogo de Monturas | Navegación wizard | Monturas filtrables por marca y material con precio y stock | `MonturaRow` |
| Etapa 4: Lentes Disponibles | Navegación wizard | Tipos de lente desde `SharedSeedSupport.LENTE_TYPES` | N/A (lista de strings) |
| Etapa 5: Configuración de Pago | Navegación wizard | Formulario de pago: subtotal, descuento, abono, método de pago | N/A (formulario) |
| Etapa 6: Resumen de Venta | Navegación wizard | Resumen consolidado de la orden con todos los parámetros | `VentaOpticaSummaryModel` |
| Etapa 7: Historial de Ventas | Navegación wizard | Ventas anteriores del cliente con montos y comprobantes | `HistoricRow` |

## Flujo de Datos

```
Controller.initialize()
    → Obtiene DemoStore desde App.getDemoStore()
    → Crea VentaOpticaFacade(store)
    → Crea VentaOpticaFilters()
    → Configura etapas del wizard (7 pasos)
    → Muestra Etapa 1 por defecto

Etapa 1: Búsqueda de Cliente
    → Usuario ingresa texto de búsqueda
    → facade.searchClientes(searchText) → List<ClientSearchRow>
    → Filtra store.clientes por nombre, código o teléfono
    → Usuario selecciona cliente → currentClienteId

Etapa 2: Selección de Receta
    → facade.getRecetasForCliente(clienteId) → List<RecipeRow>
    → Si cliente tiene SIN_RECETA, retorna lista vacía
    → Usuario selecciona receta

Etapa 3: Catálogo de Monturas
    → facade.getMonturas(filters) → List<MonturaRow>
    → Filtra por marca y material (heurística basada en nombre)
    → Usuario selecciona montura

Etapa 4: Lentes Disponibles
    → facade.getLentesDisponibles() → List<String> (desde SharedSeedSupport)
    → Usuario selecciona tipo de lente

Etapa 5: Configuración de Pago
    → Usuario ingresa subtotal, descuento, abono
    → facade.buildOrderSummary() → VentaOpticaSummaryModel
    → Calcula saldo = subtotal - descuento - abono

Etapa 6: Resumen de Venta
    → Muestra VentaOpticaSummaryModel completo
    → Usuario confirma venta

Etapa 7: Historial de Ventas (accesible desde cualquier etapa)
    → facade.getVentasHistoricas() → List<HistoricRow>
    → Mapea store.ventas a HistoricRow
```

## Filtros Disponibles

| Filtro | Tipo | Opciones | Cómo filtra el Facade |
|--------|------|----------|----------------------|
| Búsqueda de Cliente | TextField | Texto libre | `matchesText(searchText, nombre, codigo, telefono)` — búsqueda parcial en 3 campos de `Cliente`. |
| Marca de Montura | ComboBox | Ray-Ban, Oakley, Vogue, Arnette, Todas | `matchesText(filters.getMonturaMarca(), montura.marca())` — filtro por marca. |
| Material de Montura | ComboBox | Todos, Acetato, Metal, Policarbonato | `matchesMaterial(montura, material)` — heurística basada en nombre (contiene "acetato", "titanio", etc.). |
| Tipo de Lente | ComboBox | Todos + tipos de `SharedSeedSupport` | Aplicado en la UI; el facade retorna todos los tipos disponibles. |
| Índice de Lente | ComboBox | Todos | Filtro de UI para índice de refracción (1.50, 1.60, 1.67, etc.). |

## Panel Resumen (Derecho)

El `VentaOpticaSummaryModel` se construye y actualiza durante el wizard:

- `facade.buildOrderSummary(cliente, codigo, receta, montura, lente, pd, subtotal, descuento, abono, entregaEstimada, laboratorio)`
- Calcula automáticamente el saldo: `saldo = subtotal - descuento - abono`
- Formatea todos los valores monetarios con `String.format("$%.2f", value)`
- El panel se actualiza en cada etapa del wizard conforme el usuario selecciona opciones.

## Patrones de Diseño Aplicados

| Patrón | Descripción |
|--------|-------------|
| **Facade** | `VentaOpticaFacade` abstrae el acceso a `DemoStore`, `Cliente`, `VentaOptica` y `SharedSeedSupport`. |
| **Wizard/Stepper** | Flujo secuencial de 7 etapas con navegación entre pasos. |
| **Builder** | `buildOrderSummary()` construye el resumen de orden acumulando selecciones de cada etapa. |
| **Factory** | `ComboBoxFactory.createFilterCombo()` para combos filtrables. |
| **Strategy (heurística)** | `matchesMaterial()` usa heurística basada en nombre para determinar material de montura. |
| **MVVM-like** | Los `record` tienen `StringProperty` para binding con JavaFX `TableView`. |

## Integración con Otros Módulos

| Módulo | Dirección | Descripción |
|--------|-----------|-------------|
| `Clientes` | VentaOptica ← Clientes | La etapa 1 busca clientes en `store.clientes`. |
| `Recetas` | VentaOptica ← Recetas | La etapa 2 selecciona recetas del cliente. |
| `Inventario` | VentaOptica ← Inventario | La etapa 3 consulta monturas del inventario. |
| `Caja` | VentaOptica → Caja | Tras confirmar la venta, se registran cobros en el módulo Caja. |
| `OrdenesLaboratorio` | VentaOptica → OrdenesLab | La venta genera órdenes de laboratorio para fabricación de lentes. |
| `Entregas` | VentaOptica → Entregas | La venta crea trabajos pendientes de entrega. |
| `Inicio` | VentaOptica → Inicio | KPIs de ventas y alertas del dashboard reflejan datos de ventas. |

## Archivos Relacionados

```
modules/ventaoptica/
├── VentaOpticaController.java
├── VentaOpticaFacade.java
├── VentaOpticaFilters.java
├── VentaOpticaRowModel.java
├── VentaOpticaSummaryModel.java
└── VentaOpticaView.fxml

shared/query/
└── FilterSupport.java

shared/domain/
├── cliente/Cliente.java
├── venta/VentaOptica.java
└── common/SharedSeedSupport.java

shared/util/
└── MoneyUtils.java
```
