# Caja - Documentación Técnica

## Resumen
- Gestión de cobros y pagos del sistema Óptica: registro de cobros, saldos pendientes, comprobantes, cierre de caja y pagos pendientes.
- 6 sub-vistas: Cobros, Saldos Pendientes, Comprobantes, Cierre de Caja, Pagos Pendientes, Histórico.
- Patrón arquitectónico: **Facade + Paginación + Panel resumen de cobro**.

## Estructura de Clases

| Clase | Responsabilidad |
|-------|----------------|
| `CajaController.java` | Controlador del módulo. Gestiona 6 sub-vistas con ToggleGroup, filtros, tabla paginada de cobros y panel resumen lateral. Delega toda lógica a `CajaFacade`. |
| `CajaFacade.java` | Capa de abstracción entre `DemoStore` (colecciones de `Cobro` y `VentaOptica`) y las sub-vistas. Proporciona datos de cobros, saldos pendientes, comprobantes, cierre de caja agrupado por método de pago y sucursal, y pagos pendientes. |
| `CajaFilters.java` | Criterios de filtrado: texto de búsqueda, estado, método de pago, sucursal, rango de fechas, flag de solo pendientes. |
| `CajaRowModel.java` | Contiene 5 records inner: `CobroRow`, `SaldoRow`, `ComprobanteRow`, `CierreRow`, `PendienteRow`. Cada uno alimenta una sub-vista diferente. |
| `CajaSummaryModel.java` | Modelo resumen para el panel lateral derecho. Contiene resumen de cobro de una orden: montos (subtotal, descuento, abono, saldo), método de pago principal y comprobante. |

### Inner Records de CajaRowModel

| Record | Campos | Sub-vista que alimenta |
|--------|--------|----------------------|
| `CobroRow` | fecha, orden, cliente, monto, metodo, estado, comprobante | Cobros (TableView paginado) |
| `SaldoRow` | orden, cliente, total, abonado, saldo, ultimoPago, estado | Saldos Pendientes (Tabla de balances) |
| `ComprobanteRow` | comprobante, fecha, cliente, orden, total, estado | Comprobantes (Tabla de facturas/recibos) |
| `CierreRow` | fecha, sucursal, cobros, totalDia | Cierre de Caja (Resumen diario) |
| `PendienteRow` | orden, cliente, saldo, ultimoPago, estado, sucursal, prioridad | Pagos Pendientes (Tabla con prioridad) |

### CajaSummaryModel

| Campo | Descripción |
|-------|-------------|
| referenciaOrden | Referencia de la orden de venta |
| cliente | Nombre del cliente |
| codigoCliente | Código interno del cliente |
| fechaPromesa | Fecha promesa de la orden |
| subtotal | Subtotal antes de descuentos |
| descuento | Monto del descuento aplicado |
| abonoAcumulado | Abono acumulado hasta la fecha |
| saldo | Saldo restante por cobrar |
| estadoCobro | Estado del cobro (PAGADO, POR_COBRAR, PENDIENTE, etc.) |
| metodoPrincipal | Método de pago principal (EFECTIVO, TARJETA, TRANSFERENCIA) |
| comprobante | Número/referencia del comprobante |

### CierreDiaSummary (inner record de CajaFacade)

Record que encapsula el resumen diario de caja: `fecha`, `sucursal`, `totalCobros`, `totalDia`, `porMetodo` (Map<String, Double>), `porSucursal` (Map<String, Integer>).

## Sub-vistas

| Sub-vista | ToggleButton | Qué datos muestra | Qué RowModel usa |
|-----------|-------------|-------------------|------------------|
| Cobros | N/A (vista principal) | Tabla paginada de cobros registrados con fecha, orden, cliente, monto, método, estado, comprobante | `CobroRow` |
| Saldos Pendientes | N/A | Órdenes con balance pendiente: total, abonado, saldo restante, último pago | `SaldoRow` |
| Comprobantes | N/A | Facturas y recibos emitidos con fecha, cliente, orden, total | `ComprobanteRow` |
| Cierre de Caja | N/A | Resumen diario agrupado por método de pago y sucursal | `CierreDiaSummary` (no es RowModel, es DTO especial) |
| Pagos Pendientes | N/A | Órdenes con cobros pendientes o en proceso, con prioridad de cobro | `PendienteRow` |
| Histórico | N/A | Historial completo de cobros con filtros extendidos | `CobroRow` |

## Flujo de Datos

```
Controller.initialize()
    → Obtiene DemoStore desde App.getDemoStore()
    → Crea CajaFacade(store)
    → Crea CajaFilters()
    → setupFilterCombos() — estado, método de pago, sucursal, búsqueda, fechas
    → setupSubViewToggle() — ToggleGroup con 6 botones
    → setupTables() — configura columnas y cellValueFactory
    → loadCobros() — facade.getCobros(filters, pageRequest) → PageResult<CobroRow>
    → loadSaldoPendiente() — facade.getSaldoPendiente() → List<SaldoRow>
    → loadComprobantes() — facade.getComprobantes() → List<ComprobanteRow>
    → loadCierreDia() — facade.getCierreDia() → CierreDiaSummary
    → loadPagosPendientes() — facade.getPagosPendientes() → List<PendienteRow>
    → loadHistorico() — facade.getHistorico(filters) → List<CobroRow>
    → loadCombos() — estados de cobro, métodos de pago desde store

Al seleccionar una orden:
    onOrdenSelected(venta)
        → facade.buildSummary(venta) → CajaSummaryModel
        → updateSummaryPanel(summary)

Al aplicar filtros:
    applyFilters()
        → Actualiza currentFilters desde combos
        → Recarga la sub-vista activa con datos filtrados
```

## Filtros Disponibles

| Filtro | Tipo | Opciones | Cómo filtra el Facade |
|--------|------|----------|----------------------|
| Búsqueda | TextField | Texto libre | `FilterSupport.matchesText(searchText, clienteNombre, ordenId, comprobante)` — búsqueda parcial en 3 campos. |
| Estado | ComboBox | Estados de `store.cobros` (PAGADO, PENDIENTE, ANULADO, etc.) | `FilterSupport.matchesExact(estadoStr, filters.getEstado())` |
| Método de Pago | ComboBox | Métodos de `store.cobros` (EFECTIVO, TARJETA, TRANSFERENCIA) | `FilterSupport.matchesExact(metodoPago, filters.getMetodoPago())` |
| Sucursal | ComboBox | Sucursales del `DemoStore` | `FilterSupport.matchesExact(sucursal, filters.getSucursal())` |
| Desde/Hasta | DatePicker | Rango de fechas | `FilterSupport.inRange(cobro.fecha, filters.getDesde(), filters.getHasta())` |
| Solo Pendientes | CheckBox | Boolean | Si activo, excluye cobros con estado `PAGADO`: `cobro.getEstado() == EstadoGeneral.PAGADO → false`. |

## Panel Resumen (Derecho)

El `CajaSummaryModel` se actualiza al seleccionar una orden:

- **Desde entidad VentaOptica**: `facade.buildSummary(venta)` → `CajaSummaryModel.from(venta)`.
- Calcula `subtotal = precioMontura + precioLente`.
- Calcula `saldo = venta.getSaldo()`.
- Determina `estadoCobro` según saldo y estado de la venta:
  - `saldo <= 0` → "PAGADO"
  - `EN_PROCESO` → "POR_COBRAR"
  - `LISTO` → "LISTO_PARA_ENTREGA"
  - `ENTREGADO/COMPLETADO` → "PAGADO"
  - `EN_ESPERA` → "EN_ESPERA"
  - Default → "PENDIENTE"
- Si `abono > 0`, método principal = "EFECTIVO", comprobante = "FAC-2026-0184" (demo).
- Si `venta == null`, retorna `demoSeed()` con datos de ejemplo (OV-124, Sofía Ramírez).

## Patrones de Diseño Aplicados

| Patrón | Descripción |
|--------|-------------|
| **Facade** | `CajaFacade` abstrae el acceso a `store.cobros` y `store.ventas`. |
| **Paginación** | `PaginationHelper.page(filtered, pageRequest)` para cobros. |
| **Mapper** | Métodos estáticos `from(Cobro)` y `from(VentaOptica)` en cada `RowModel` convierten entidades a modelos de presentación. |
| **DTO** | `CierreDiaSummary` y `StatsCritico` encapsulan datos agregados. |
| **Factory** | `ComboBoxFactory.createFilterCombo()` para combos filtrables. |
| **Observer** | Listeners en combos y búsqueda disparan `applyFilters()`. |
| **Pattern Matching** | `determineEstadoCobro()` usa switch pattern matching sobre `venta.getEstado()`. |

## Integración con Otros Módulos

| Módulo | Dirección | Descripción |
|--------|-----------|-------------|
| `VentaOptica` | Caja ← VentaOptica | Los cobros se asocian a ventas. `SaldoRow.from(VentaOptica)` y `PendienteRow.from(VentaOptica)` mapean ventas. |
| `Entregas` | Caja → Entregas | Las entregas verifican saldo pendiente antes de entregar (`conSaldo`). |
| `Inicio` | Caja → Inicio | KPI "Por cobrar" del dashboard refleja saldos pendientes. |
| `Clientes` | Caja ← Clientes | Los cobros referencian `clienteNombre` y `clienteId`. |
| `Sucursales` | Caja ← Sucursales | Los cobros están asociados a una sucursal. |

## Archivos Relacionados

```
modules/caja/
├── CajaController.java
├── CajaFacade.java
├── CajaFilters.java
├── CajaRowModel.java
├── CajaSummaryModel.java
└── CajaView.fxml

shared/query/
├── FilterSupport.java
├── PaginationHelper.java
├── PageRequest.java
└── PageResult.java

shared/domain/
├── caja/Cobro.java
├── venta/VentaOptica.java
└── common/EstadoGeneral.java
```
