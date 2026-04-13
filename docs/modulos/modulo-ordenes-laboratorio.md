# Órdenes de Laboratorio - Documentación Técnica

## Resumen
- Gestión de órdenes enviadas a laboratorios externos e internos para fabricación de lentes y montaje de monturas.
- 5 sub-vistas: Cola de Órdenes, Etapas del Proceso, Envío y Recepción, Incidencias, Histórico.
- Patrón arquitectónico: **Facade + Paginación + Panel resumen de orden**.

## Estructura de Clases

| Clase | Responsabilidad |
|-------|----------------|
| `OrdenesLabController.java` | Controlador del módulo. Gestiona 5 sub-vistas con ToggleGroup, filtros, tabla paginada de órdenes y panel resumen lateral. Delega toda lógica a `OrdenesLabFacade`. |
| `OrdenesLabFacade.java` | Capa de abstracción que proporciona datos de demostración para la gestión de órdenes. Usa seed data interno para cola de órdenes, etapas, incidencias e histórico. Complementa con `DemoStore` para sucursales. |
| `OrdenesLabFilters.java` | Criterios de filtrado: texto de búsqueda, estado, laboratorio, prioridad, sucursal, rango de fechas. |
| `OrdenesLabRowModel.java` | Contiene 5 records inner: `ColaRow`, `EtapaRow`, `EnvioRow`, `RecepcionRow`, `IncidenciaRow`, `HistoricoRow`. |
| `OrdenesLabSummaryModel.java` | Modelo resumen para el panel lateral derecho. Contiene información detallada de la orden: tipo de trabajo, componentes ópticos, estado de fabricación y datos de envío. |

### Inner Records de OrdenesLabRowModel

| Record | Campos | Tabla/Sección que alimenta |
|--------|--------|---------------------------|
| `ColaRow` | referencia, cliente, tipoTrabajo, ingreso, fechaPromesa, estado, laboratorio, prioridad, sucursal | Cola de Órdenes (TableView paginado) |
| `EtapaRow` | fecha, etapa, responsable, observacion | Etapas del Proceso (tracking visual) |
| `EnvioRow` | laboratorio, fechaEnvio, guia, transportista, fechaEstimada | Envío al laboratorio (detalle) |
| `RecepcionRow` | fecha, responsable, conformidad, observacion | Recepción en sucursal (detalle) |
| `IncidenciaRow` | fecha, tipo, estado, responsable, observacion | Incidencias (TableView) |
| `HistoricoRow` | referencia, cliente, ingreso, entrega, estadoFinal, laboratorio, sucursal | Histórico (Tabla filtrada) |

### OrdenesLabSummaryModel

| Campo | Descripción |
|-------|-------------|
| referencia | Referencia única de la orden |
| tipoTrabajo | Tipo: solo lente, montura+lente, etc. |
| prioridad | Alta, Normal, Baja |
| cliente | Nombre del cliente |
| codigoCliente | Código interno del cliente |
| sucursal | Sucursal de origen |
| fechaPromesa | Fecha prometida de finalización |
| montura | Referencia de la montura |
| lente | Tipo de lente |
| tratamientos | Tratamientos especiales |
| pd | Distancia pupilar |
| estadoActual | Estado en el flujo de laboratorio |
| laboratorio | Laboratorio externo asignado |
| guia | Número de guía de envío |
| recepcionEsperada | Fecha estimada de recepción |

## Sub-vistas

| Sub-vista | ToggleButton | Qué datos muestra | Qué RowModel usa |
|-----------|-------------|-------------------|------------------|
| Cola de Órdenes | N/A (vista principal) | Tabla paginada con referencia, cliente, tipo de trabajo, fechas, estado, laboratorio, prioridad, sucursal | `ColaRow` |
| Etapas del Proceso | N/A | Tracking visual del progreso de la orden: Recibida → Validada → En producción → En control → Lista → Enviada → Recibida → Entregada | `EtapaRow` |
| Envío y Recepción | N/A | Datos de envío al laboratorio (nombre, fecha, guía, transportista) y recepción en sucursal | `EnvioRow`, `RecepcionRow` |
| Incidencias | N/A | Registro de problemas: lentes rayados, retrasos, retrabajos | `IncidenciaRow` |
| Histórico | N/A | Órdenes completadas con fecha de ingreso y entrega | `HistoricoRow` |

## Flujo de Datos

```
Controller.initialize()
    → Obtiene DemoStore desde App.getDemoStore()
    → Crea OrdenesLabFacade(store) — inicializa seed data en constructor
    → Crea OrdenesLabFilters()
    → setupFilterCombos() — estado, laboratorio, prioridad, sucursal, búsqueda, fechas
    → setupTable() — configura columnas de la cola de órdenes
    → loadColaOrdenes() — facade.getColaOrdenes(filters, pageRequest) → PageResult<ColaRow>
    → loadEtapas() — facade.getEtapas(ordenId) → List<EtapaRow>
    → loadEnvio() — facade.getEnvio(ordenId) → EnvioRow
    → loadRecepcion() — facade.getRecepcion(ordenId) → RecepcionRow
    → loadIncidencias() — facade.getIncidencias(ordenId) → List<IncidenciaRow>
    → loadHistorico() — facade.getHistorico(filters) → List<HistoricoRow>
    → loadCombos() — estados, laboratorios, prioridades, sucursales

Al seleccionar una orden:
    onOrdenSelected(row)
        → facade.buildSummaryFromRow(row) → OrdenesLabSummaryModel
        → updateSummaryPanel(summary)
```

## Filtros Disponibles

| Filtro | Tipo | Opciones | Cómo filtra el Facade |
|--------|------|----------|----------------------|
| Búsqueda | TextField | Texto libre | `FilterSupport.matchesText(searchText, referencia, cliente, tipoTrabajo, laboratorio)` — búsqueda parcial en 4 campos. |
| Estado | ComboBox | En producción, Validada, Lista, Retrasada, Con incidencia (desde seed data) | `FilterSupport.matchesExact(row.estado(), filters.getEstado())` |
| Laboratorio | ComboBox | LabVision, OptiLab Pro (desde seed data) | `FilterSupport.matchesExact(row.laboratorio(), filters.getLaboratorio())` |
| Prioridad | ComboBox | Alta, Normal (desde seed data) | `FilterSupport.matchesExact(row.prioridad(), filters.getPrioridad())` |
| Sucursal | ComboBox | Sucursales del `DemoStore` | `FilterSupport.matchesExact(row.sucursal(), filters.getSucursal())` |
| Desde/Hasta | DatePicker | Rango de fechas | `FilterSupport.inRange(row.ingreso(), filters.getDesde(), filters.getHasta())` |

## Panel Resumen (Derecho)

El `OrdenesLabSummaryModel` se actualiza al seleccionar una orden:

- **Desde fila de cola**: `facade.buildSummaryFromRow(row)` — construye resumen a partir de `ColaRow`.
- **Desde entidad VentaOptica**: `facade.buildSummary(ventaOptica)` — `OrdenesLabSummaryModel.from(venta)` mapea la entidad de venta al resumen de laboratorio.
- Muestra 16 campos: referencia, tipo trabajo, prioridad, cliente, código cliente, sucursal, fecha promesa, montura, lente, tratamientos, PD, estado actual, laboratorio, guía, recepción esperada.

## Patrones de Diseño Aplicados

| Patrón | Descripción |
|--------|-------------|
| **Facade** | `OrdenesLabFacade` abstrae el acceso a seed data interno y `DemoStore`. |
| **Paginación** | `PaginationHelper.page(filtered, pageRequest)` retorna `PageResult<T>` con datos de página. |
| **Seed Data** | Datos inicializados en el constructor: `initSeedCola()`, `initSeedEtapas()`, `initSeedIncidencias()`, `initSeedHistorico()`. |
| **Mapper** | `OrdenesLabSummaryModel.from(VentaOptica)` convierte entidad de venta a modelo de laboratorio. |
| **Factory** | `ComboBoxFactory.createFilterCombo()` para combos filtrables. |
| **Observer** | Listeners en combos y búsqueda disparan `applyFilters()`. |

## Integración con Otros Módulos

| Módulo | Dirección | Descripción |
|--------|-----------|-------------|
| `VentaOptica` | OrdenesLab ← VentaOptica | Las órdenes de laboratorio se generan desde ventas. `OrdenesLabSummaryModel.from(VentaOptica)` mapea la venta. |
| `Entregas` | OrdenesLab → Entregas | Las órdenes completadas generan trabajos listos para entrega. |
| `Inventario` | OrdenesLab ← Inventario | Las órdenes consumen materiales del inventario (lentes, monturas). |
| `Inicio` | OrdenesLab → Inicio | KPI "Órdenes pendientes" y alertas de retrasos reflejan datos del laboratorio. |

## Archivos Relacionados

```
modules/ordeneslaboratorio/
├── OrdenesLabController.java
├── OrdenesLabFacade.java
├── OrdenesLabFilters.java
├── OrdenesLabRowModel.java
├── OrdenesLabSummaryModel.java
└── OrdenesLabView.fxml

shared/query/
├── FilterSupport.java
├── PaginationHelper.java
├── PageRequest.java
└── PageResult.java

shared/domain/venta/
└── VentaOptica.java
```
