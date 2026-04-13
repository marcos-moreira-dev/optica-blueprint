# Recetas - Documentación Técnica

## Resumen
- Gestión de recetas oftalmológicas de los clientes: historial, medidas optométricas, recomendaciones y vinculación con órdenes ópticas.
- 5 sub-vistas: Receta Actual, Historial, Medidas y Parámetros, Recomendaciones, Vinculación con Órdenes.
- Patrón arquitectónico: **Facade + ToggleGroup de sub-vistas + Contexto de cliente persistente**.

## Estructura de Clases

| Clase | Responsabilidad |
|-------|----------------|
| `RecetasController.java` | Controlador del módulo (~881 líneas). Gestiona 5 sub-vistas con ToggleGroup, filtros, tablas de historial/medidas y panel de contexto del cliente. Delega toda lógica a `RecetasFacade`. |
| `RecetasFacade.java` | Capa de abstracción entre `DemoStore` y las sub-vistas. Genera datos seed para el cliente CLI-001 (Sofía Ramírez) con recetas, medidas, vinculaciones y recomendaciones. Para otros clientes retorna datos genéricos. |
| `RecetasFilters.java` | Criterios de filtrado: búsqueda por cliente, estado, profesional, rango de fechas (desde/hasta). |
| `RecetasRowModel.java` | Contiene 3 records inner: `HistorialRow`, `MedicionRow`, `VinculacionRow`. Cada uno alimenta una tabla diferente del módulo. |
| `RecetasSummaryModel.java` | Modelo resumen con datos optométricos completos de la receta seleccionada: valores OD/OI (Sph, Cyl, Axis), ADD, DP, tratamiento sugerido, uso principal. |

### Inner Records de RecetasRowModel

| Record | Campos | Tabla que alimenta |
|--------|--------|-------------------|
| `HistorialRow` | fecha, profesional, estado, odResumen, oiResumen, pd, observacionBreve | `historialTable` — historial cronológico de recetas |
| `MedicionRow` | parametro, valor, unidad, observacion | `medidasTable` — parámetros optométricos (DP, altura, etc.) |
| `VinculacionRow` | referenciaOrden, estadoOrden, fecha, saldo, entrega | Vinculación — órdenes asociadas a la receta |

### RecetasSummaryModel

| Campo | Descripción |
|-------|-------------|
| cliente | Nombre del cliente titular |
| codigo | Código de la receta |
| estado | Estado (vigente, vencida, etc.) |
| profesional | Profesional que emitió la receta |
| fechaReceta | Fecha de emisión |
| odSph, odCyl, odAxis | Esfera, cilindro, eje del ojo derecho |
| oiSph, oiCyl, oiAxis | Esfera, cilindro, eje del ojo izquierdo |
| add | Adición para lentes progresivos/bifocales |
| pd | Distancia pupilar (mm) |
| tratamientoSugerido | Tratamiento sugerido (antirreflejo, blue cut, etc.) |
| usoPrincipal | Uso indicado (diario, pantalla, lejos, cerca) |

### ClientContextModel (inner record de RecetasFacade)

Modelo de contexto del cliente para el panel derecho: nombre, código, teléfono, última visita, sucursal, estado receta, PD, profesional, órdenes activas, entregas pendientes, saldo pendiente.

## Sub-vistas

| Sub-vista | ToggleButton | Qué datos muestra | Qué RowModel usa |
|-----------|-------------|-------------------|------------------|
| Receta Actual | `subViewRecetaActualBtn` | Valores OD/OI completos (Sph, Cyl, Axis, Prism, Base, Add), PD, uso principal, vigencia, tratamiento sugerido, observaciones | `RecetasSummaryModel` (labels individuales) |
| Historial | `subViewHistorialBtn` | Tabla cronológica de recetas anteriores con fecha, profesional, estado, resumen OD/OI, PD y observación | `HistorialRow` |
| Medidas y Parámetros | `subViewMedidasBtn` | Tabla de parámetros optométricos: DP, altura de montaje, uso principal, actividad laboral, preferencias | `MedicionRow` |
| Recomendaciones | `subViewRecomendacionesBtn` | Labels con recomendación principal, observación técnica y notas internas | N/A (strings directos del facade) |
| Vinculación con Órdenes | `subViewVinculacionBtn` | Estado de vinculación, referencia de orden, estado de orden, saldo, fecha de entrega | `VinculacionRow` |

## Flujo de Datos

```
Controller.initialize()
    → Obtiene DemoStore desde App.getDemoStore()
    → Crea RecetasFacade(store)
    → Crea RecetasFilters()
    → currentClienteId = "CLI-001" (Sofía Ramírez por defecto)
    → setupFilterCombos() — estado, profesional, búsqueda libre
    → setupSubViewToggle() — ToggleGroup con 5 botones
    → setupHistorialTable() — configura columnas y cellValueFactory
    → setupMedidasTable() — configura columnas y cellValueFactory
    → loadCurrentRecipe() — facade.getCurrentRecipe(clienteId) → SummaryModel → labels
    → loadHistorial() — facade.getHistorial(clienteId) → TableView
    → loadMedidas() — facade.getMedidas(clienteId) → TableView
    → loadVinculacion() — facade.getVinculaciones(clienteId) → labels
    → loadRecomendaciones() — facade.getRecomendaciones(clienteId) → labels
    → showSubView("recetaActual") — muestra receta actual por defecto
    → updateClientContext() — facade.buildClientContext(cliente) → panel derecho

Al cambiar de cliente (búsqueda):
    onClienteSelected(cliente)
        → currentClienteId = cliente.getId()
        → Recarga todas las sub-vistas con datos del nuevo cliente
        → Actualiza panel de contexto
```

## Filtros Disponibles

| Filtro | Tipo | Opciones | Cómo filtra el Facade |
|--------|------|----------|----------------------|
| Búsqueda | TextField | Texto libre | `FilterSupport.matchesText(searchText, nombreCompleto, codigoInterno, telefono)` — búsqueda en datos del cliente. |
| Estado | ComboBox filtrable | Vigente, Vencida, Sin receta (desde `store.clientes`) | `FilterSupport.matchesExact(estadoReceta, filters.getEstado())` |
| Profesional | ComboBox filtrable | Dra. Salazar, Dr. Paredes | `FilterSupport.matchesExact(profesional, filters.getProfesional())` |
| Desde | ComboBox | Fechas | `FilterSupport.inRange(fecha, filters.getDesde(), filters.getHasta())` |
| Hasta | ComboBox | Fechas | Rango de fechas sobre la fecha de receta |

## Panel Resumen (Derecho)

El panel de contexto del cliente se actualiza con `facade.buildClientContext(cliente)`:

- Calcula órdenes activas contando ventas con estado `EN_PROCESO` o `CONFIRMADO` del cliente.
- Calcula entregas pendientes contando ventas con estado `EN_PROCESO`.
- Calcula saldo pendiente sumando `venta.getSaldo()` de todas las ventas del cliente.
- Muestra 11 campos: nombre, código, teléfono, última visita, sucursal, estado receta, PD, profesional, órdenes activas, entregas pendientes, saldo pendiente.

## Patrones de Diseño Aplicados

| Patrón | Descripción |
|--------|-------------|
| **Facade** | `RecetasFacade` abstrae el acceso a `DemoStore`, retornando datos seed para el cliente principal y datos genéricos para el resto. |
| **ToggleGroup** | 5 `ToggleButton` mutuamente excluyentes controlan qué sub-vista es visible. |
| **Context Model** | `ClientContextModel` encapsula el estado del cliente seleccionado para el panel derecho. |
| **Mapper** | `ClientesSummaryModel.from(Cliente)` y `RecetasSummaryModel` mapean entidades a modelos de presentación. |
| **Factory** | `ComboBoxFactory.createFilterCombo()` para combos filtrables. |
| **Observer** | Listeners en `searchField.textProperty` y combos disparan `applyFilters()`. |

## Integración con Otros Módulos

| Módulo | Dirección | Descripción |
|--------|-----------|-------------|
| `Clientes` | Recetas ← Clientes | Las recetas están asociadas a un `clienteId`. El buscador de clientes usa `store.clientes`. |
| `VentaOptica` | Recetas → VentaOptica | El wizard de venta selecciona una receta en la etapa 2 (`getRecetasForCliente()`). |
| `OrdenesLaboratorio` | Recetas → OrdenesLab | Las vinculaciones con órdenes referencian órdenes de laboratorio. |
| `Agenda` | Recetas → Agenda | Tras un examen visual, se puede crear/actualizar la receta del paciente. |

## Archivos Relacionados

```
modules/recetas/
├── RecetasController.java
├── RecetasFacade.java
├── RecetasFilters.java
├── RecetasRowModel.java
├── RecetasSummaryModel.java
└── RecetasView.fxml

shared/query/
└── FilterSupport.java

shared/domain/cliente/
└── Cliente.java

shared/util/
└── MoneyUtils.java
```
