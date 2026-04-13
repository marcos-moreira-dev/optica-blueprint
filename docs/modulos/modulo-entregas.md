# Entregas - Documentación Técnica

## Resumen
- Gestión de entrega de trabajos ópticos a los clientes: trabajos listos, validación previa, pendientes de retiro, post-entrega e histórico.
- 5 sub-vistas: Trabajos Listos, Validaciones, Pendientes de Retiro, Post-Entrega, Histórico.
- Patrón arquitectónico: **Facade + Paginación + Panel resumen de entrega**.

## Estructura de Clases

| Clase | Responsabilidad |
|--------|----------------|
| `EntregasController.java` | Controlador del módulo. Gestiona 5 sub-vistas con ToggleGroup, filtros, tabla paginada de trabajos listos y panel resumen lateral. Delega toda lógica a `EntregasFacade`. |
| `EntregasFacade.java` | Capa de abstracción que proporciona datos de demostración para la gestión de entregas. Combina seed data estático con datos del `DemoStore` (ventas en estado LISTO o EN_PROCESO). |
| `EntregasFilters.java` | Criterios de filtrado: texto de búsqueda, estado, sucursal, notificación, rango de fechas, flag de solo con saldo. |
| `EntregasRowModel.java` | Contiene 6 records inner: `TrabajoListoRow`, `ValidacionRow`, `RetiroRow`, `PendienteRow`, `PostEntregaRow`, `HistoricoRow`. |
| `EntregasSummaryModel.java` | Modelo resumen para el panel lateral derecho. Presenta estado de entrega de una orden: tipo de trabajo, fechas, notificación y saldos. |

### Inner Records de EntregasRowModel

| Record | Campos | Sub-vista que alimenta |
|--------|--------|----------------------|
| `TrabajoListoRow` | referencia, cliente, tipoTrabajo, fechaPromesa, estado, saldo, sucursal, notificacion | Trabajos Listos (TableView paginado) |
| `ValidacionRow` | referencia, cliente, tipoTrabajo, fechaPromesa, trabajoRecibido, montajeConforme, requiereAjuste, saldoPendiente | Validaciones (Checklist de validación) |
| `RetiroRow` | referencia, cliente, fechaHoraRetiro, retira, documento, observacion | Registro de Retiro (documento de entrega) |
| `PendienteRow` | referencia, cliente, diasEsperando, saldo, estado, notificacion, sucursal | Pendientes de Retiro (Tabla de espera) |
| `PostEntregaRow` | fecha, referencia, tipo, estado, responsable | Post-Entrega (Ajustes y seguimiento) |
| `HistoricoRow` | fecha, referencia, cliente, estadoFinal, saldo, sucursal, observacion | Histórico de Entregas (Tabla) |

### EntregasSummaryModel

| Campo | Descripción |
|-------|-------------|
| referencia | Referencia única de la orden |
| tipoTrabajo | Tipo: montura+lente, solo lente, montura |
| sucursal | Sucursal de entrega |
| cliente | Nombre del cliente |
| codigoCliente | Código interno del cliente |
| fechaPromesa | Fecha prometida de entrega |
| fechaRecepcion | Fecha de recepción real del laboratorio |
| estadoEntrega | Estado (Lista para entrega, Entregada, Pendiente) |
| notificacion | Estado de notificación (Notificado por SMS, Pendiente) |
| prioridad | Prioridad de la entrega |
| saldoPendiente | Saldo pendiente de pago |
| estadoCobro | Estado del cobro (Pagado, Con saldo pendiente) |

### StatsPendientes (inner record de EntregasFacade)

Record que encapsula estadísticas de entregas pendientes: `pendientes`, `conSaldo`, `notificados`.

## Sub-vistas

| Sub-vista | ToggleButton | Qué datos muestra | Qué RowModel usa |
|-----------|-------------|-------------------|------------------|
| Trabajos Listos | N/A (vista principal) | Tabla paginada con trabajos completados listos para entrega: referencia, cliente, tipo, fecha promesa, estado, saldo, sucursal, notificación | `TrabajoListoRow` |
| Validaciones | N/A | Checklist de validación previa: trabajo recibido conforme, montaje conforme, requiere ajuste, saldo pendiente | `ValidacionRow` |
| Pendientes de Retiro | N/A | Trabajos listos no retirados por el cliente con días de espera, saldo y estado de notificación | `PendienteRow` |
| Post-Entrega | N/A | Ajustes y observaciones posteriores a la entrega: fecha, tipo de ajuste, estado, responsable | `PostEntregaRow` |
| Histórico | N/A | Registro histórico de entregas con estado final y observaciones | `HistoricoRow` |

## Flujo de Datos

```
Controller.initialize()
    → Obtiene DemoStore desde App.getDemoStore()
    → Crea EntregasFacade(store)
    → Crea EntregasFilters()
    → setupFilterCombos() — estado, sucursal, notificación, búsqueda, fechas
    → setupSubViewToggle() — ToggleGroup con 5 botones
    → setupTables() — configura columnas y cellValueFactory
    → loadTrabajosListos() — facade.getTrabajosListos(filters, pageRequest) → PageResult<TrabajoListoRow>
    → loadValidaciones() — facade.getValidaciones() → List<ValidacionRow>
    → loadPendientesRetiro() — facade.getPendientesRetiro() → List<PendienteRow>
    → loadPostEntrega() — facade.getPostEntrega() → List<PostEntregaRow>
    → loadHistorico() — facade.getHistorico(filters) → List<HistoricoRow>
    → loadCombos() — estados de entrega, estados de notificación
    → loadStatsPendientes() — facade.getStatsPendientes() → StatsPendientes

Al seleccionar una orden:
    onOrdenSelected(venta)
        → facade.buildSummary(venta) → EntregasSummaryModel
        → updateSummaryPanel(summary)

Al aplicar filtros:
    applyFilters()
        → Actualiza currentFilters desde combos
        → Recarga la sub-vista activa con datos filtrados
```

## Filtros Disponibles

| Filtro | Tipo | Opciones | Cómo filtra el Facade |
|--------|------|----------|----------------------|
| Búsqueda | TextField | Texto libre | `FilterSupport.matchesText(searchText, referencia, cliente, tipoTrabajo)` — búsqueda parcial en 3 campos. |
| Estado | ComboBox | Lista para entrega, Pendiente de validación, Pendiente de retiro, Notificado, Entregada, Reprogramada, Con ajuste pendiente, Con saldo pendiente, No retirada | `FilterSupport.matchesExact(row.estado(), filters.getEstado())` |
| Sucursal | ComboBox | Sucursales del `DemoStore` | `FilterSupport.matchesExact(row.sucursal(), filters.getSucursal())` |
| Notificación | ComboBox | Pendiente de notificación, Notificado por SMS, Recordatorio enviado | `FilterSupport.matchesExact(row.notificacion(), filters.getNotificacion())` |
| Desde/Hasta | DatePicker | Rango de fechas | `FilterSupport.inRange(row.fechaPromesa(), filters.getDesde(), filters.getHasta())` |
| Solo con Saldo | CheckBox | Boolean | Si activo, filtra filas con `saldo > $0.00`: parsea el string y compara con 0.001. |

## Panel Resumen (Derecho)

El `EntregasSummaryModel` se actualiza al seleccionar una orden:

- **Desde entidad VentaOptica**: `facade.buildSummary(venta)` → `EntregasSummaryModel.from(venta)`.
- Calcula `tipoTrabajo` según lente y montura:
  - Ambos presentes → "Montura + lente"
  - Solo lente → "Solo lente"
  - Solo montura → "Montura"
- Determina `estadoEntrega` según estado de la venta:
  - `LISTO` → "Lista para entrega"
  - `EN_PROCESO` → "Pendiente de validación"
  - `ENTREGADO/COMPLETADO` → "Entregada"
  - `EN_ESPERA` → "Pendiente de retiro"
  - `CONFIRMADO` → "Notificado"
- Calcula `estadoCobro`: `saldo <= 0` → "Pagado", otro → "Con saldo pendiente".
- Si `venta == null`, retorna `demoSeed()` con datos de ejemplo (ET-041, Sofía Ramírez).

## Patrones de Diseño Aplicados

| Patrón | Descripción |
|--------|-------------|
| **Facade** | `EntregasFacade` abstrae el acceso a `store.ventas` y seed data estático. |
| **Paginación** | `PaginationHelper.page(filtered, pageRequest)` para trabajos listos. |
| **Mapper** | `EntregasSummaryModel.from(VentaOptica)` convierte entidad de venta a modelo de entrega. |
| **DTO** | `StatsPendientes` encapsula estadísticas de entregas pendientes. |
| **Heurística** | `determineTipoTrabajo()` y `determineEstadoEntrega()` derivan valores del estado de la venta. |
| **Factory** | `ComboBoxFactory.createFilterCombo()` para combos filtrables. |
| **Observer** | Listeners en combos y búsqueda disparan `applyFilters()`. |

## Integración con Otros Módulos

| Módulo | Dirección | Descripción |
|--------|-----------|-------------|
| `VentaOptica` | Entregas ← VentaOptica | Los trabajos listos se derivan de ventas en estado LISTO o EN_PROCESO. |
| `Caja` | Entregas ← Caja | Las entregas verifican saldo pendiente antes de entregar. |
| `OrdenesLaboratorio` | Entregas ← OrdenesLab | Las órdenes de laboratorio completadas generan trabajos listos. |
| `Inicio` | Entregas → Inicio | KPI "Listas para entregar" del dashboard refleja datos de entregas. |
| `Clientes` | Entregas ← Clientes | Las entregas referencian clientes asociados a ventas. |

## Archivos Relacionados

```
modules/entregas/
├── EntregasController.java
├── EntregasFacade.java
├── EntregasFilters.java
├── EntregasRowModel.java
├── EntregasSummaryModel.java
└── EntregasView.fxml

shared/query/
├── FilterSupport.java
├── PaginationHelper.java
├── PageRequest.java
└── PageResult.java

shared/domain/venta/
└── VentaOptica.java
```
