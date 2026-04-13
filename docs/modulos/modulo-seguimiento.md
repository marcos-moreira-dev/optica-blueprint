# Seguimiento - Documentación Técnica

## Resumen
Módulo de seguimiento post-venta y retención de clientes. Gestiona casos activos de recall, pedidos no retirados, cobros pendientes, mensajes enviados y proporciona un histórico de todas las interacciones. Implementa una arquitectura de tres paneles con barra de filtros, sub-vistas intercambiables mediante toggle buttons y panel de resumen persistente.

## Estructura de Clases
| Clase | Tipo | Responsabilidad |
|-------|------|-----------------|
| `SeguimientoController` | Controller | Configuración JavaFX, binding de datos, gestión de filtros, toggle de sub-vistas, actualización del panel resumen |
| `SeguimientoFacade` | Facade | Lógica de negocio, seed data, filtrado, paginación, estadísticas (recalls, no retirados, cobros) |
| `SeguimientoFilters` | Model | Criterios de filtrado (searchText, tipo, estado, prioridad, sucursal, canal, desde, hasta, soloCasosAbiertos) |
| `SeguimientoRowModel` | Model | Registros inmutables de filas para cada sub-vista |
| `SeguimientoSummaryModel` | Model | Resumen del caso seleccionado para el panel derecho |

### Sub-clases (inner records en SeguimientoRowModel)
| Record | Columnas |
|--------|----------|
| `BandejaRow` | referencia, cliente, tipo, estado, fechaObjetivo, prioridad, sucursal, responsable |
| `RecallRow` | cliente, ultimaVisita, motivo, fechaSugerida, estado, sucursal |
| `NoRetiradoRow` | orden, cliente, diasEsperando, notificacion, saldo, estado, sucursal |
| `CobroPendienteRow` | orden, cliente, saldo, ultimoPago, estado, proximaAccion, sucursal |
| `MensajeRow` | fecha, cliente, tipo, canal, estado, resultado |
| `HistoricoRow` | fecha, referencia, cliente, tipo, estadoFinal, resultado, sucursal |

### Sub-clases (inner records en SeguimientoFacade)
| Record | Descripción |
|--------|-------------|
| `StatsRecalls` | recallPendiente, recetasVencidas, revisionesProximas |
| `StatsNoRetirados` | noRetirados, notificados, conSaldo |
| `StatsCobros` | casosConSaldo, montoPendiente, casosVencidos |

## Sub-vistas
| # | Sub-vista | Toggle Button | Tabla Principal | KPIs |
|---|-----------|--------------|-----------------|------|
| 1 | Bandeja de seguimiento | `btnBandeja` | `bandejaTable` | lblBandejaCount |
| 2 | Recall y revisiones | `btnRecall` | `recallTable` | lblRecallPendiente, lblRecetasVencidas, lblRevisionesProximas |
| 3 | Pedidos listos y no retirados | `btnNoRetirados` | `noRetiradosTable` | lblNoRetiradosTotal, lblNoRetiradosNotificados, lblNoRetiradosConSaldo |
| 4 | Cobros pendientes | `btnCobros` | `cobrosTable` | lblCobrosCasosConSaldo, lblCobrosMontoPendiente, lblCobrosVencidos |
| 5 | Mensajes y recordatorios | `btnMensajes` | `mensajesTable` | — |
| 6 | Histórico | `btnHistorico` | `historicoTable` | footerHistorico |

## Flujo de Datos
```
[Usuario selecciona filtros] → applyFilters()
       ↓
SeguimientoFilters actualizado (searchText, tipo, estado, prioridad, sucursal, canal, fechas)
       ↓
SeguimientoFacade.getBandeja(filters, pageRequest)
       ↓
Stream.filter(matchesBandejaFilters) → PaginationHelper.page()
       ↓
PageResult<BandejaRow> → ObservableList → bandejaTable.setItems()
       ↓
[Usuario selecciona fila] → onBandejaRowSelected(row)
       ↓
facade.buildSummary(row) → SeguimientoSummaryModel.fromBandeja(row)
       ↓
updateSummaryPanel() → Labels del panel derecho actualizados
```

## Filtros Disponibles
| Filtro | Control | Valores posibles | Fuente |
|--------|---------|------------------|--------|
| Búsqueda libre | `TextField` | Texto libre (referencia, cliente, tipo) | `FilterSupport.matchesText()` |
| Tipo | `ComboBox<String>` | Recall, No retirado, Cobro pendiente, Mensaje, Revision | `facade.getTiposSeguimiento()` |
| Estado | `ComboBox<String>` | Recall pendiente, No retirado, Con saldo pendiente, Pendiente, Cerrado, Reprogramado, Pago parcial, Vencido | `facade.getEstadosSeguimiento()` |
| Prioridad | `ComboBox<String>` | Alta, Media, Baja | ComboBoxFactory.createFilterCombo() |
| Sucursal | `ComboBox<String>` | Matriz Centro, Norte Mall | ComboBoxFactory.createFilterCombo() |
| Canal | `ComboBox<String>` | WhatsApp, SMS, Llamada, Email, Presencial | `facade.getCanales()` |
| Desde | `DatePicker` | Fecha inicio del rango | `FilterSupport.inRange()` |
| Hasta | `DatePicker` | Fecha fin del rango | `FilterSupport.inRange()` |
| Solo casos abiertos | `CheckBox` | true/false | Filtro booleano en histórico |

## Panel Resumen
El `SeguimientoSummaryModel` muestra los siguientes campos del caso seleccionado:
- **referencia**: identificador único del caso (SG-021)
- **tipoSeguimiento**: Recall, No retirado, Cobro pendiente, Mensaje, Revision
- **prioridad**: Alta, Media, Baja
- **cliente**: nombre del paciente asociado
- **codigoCliente**: código interno del cliente
- **ultimaVisita**: fecha de la última visita
- **sucursal**: sede responsable del caso
- **estadoActual**: estado actual del seguimiento
- **canalContacto**: canal utilizado (WhatsApp, Llamada, Email, Presencial)
- **ultimaInteraccion**: fecha de la última interacción
- **accionSugerida**: acción sugerida para dar continuidad
- **fechaObjetivo**: fecha límite para la próxima acción
- **observacionClave**: observación clave o resumen del caso

## Patrones de Diseño
- **Facade Pattern**: `SeguimientoFacade` centraliza toda la lógica de negocio, separando presentación de dominio
- **MVC (Model-View-Controller)**: separación clara entre FXML (View), Controller y modelos de datos
- **Record Pattern**: todos los modelos de fila son records inmutables con JavaFX properties
- **Toggle Group Pattern**: sub-vistas mutuamente exclusivas mediante `ToggleGroup` de JavaFX
- **Status Badge Pattern**: celdas personalizadas con `StatusBadgeController` para indicadores visuales de estado
- **Pagination Pattern**: paginación controlada por `PaginationBarController` con `PageRequest`/`PageResult`
- **Filter Chain Pattern**: filtros encadenados con `FilterSupport.matchesText()`, `matchesExact()`, `inRange()`

## Integración con Otros Módulos
| Módulo | Punto de integración | Descripción |
|--------|---------------------|-------------|
| Ventas | Cobros pendientes | Referencia órdenes de venta con saldo pendiente |
| Laboratorio | Pedidos no retirados | Trabajos completados listos para retiro |
| Agenda | Recall y revisiones | Citas de control programadas y recordatorios |
| Clientes | Todas las sub-vistas | Referencia cruzada a datos del paciente |
| Sucursales | Filtro global | Todas las sub-vistas filtran por sucursal activa |
| Reportes | Seguimiento y retención | Los datos de seguimiento alimentan el reporte de retención |
