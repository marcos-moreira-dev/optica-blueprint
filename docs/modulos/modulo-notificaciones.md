# Notificaciones - Documentación Técnica

## Resumen
Módulo de gestión de notificaciones internas y externas del sistema óptica. Administra la bandeja de notificaciones al cliente, notificaciones internas del personal, plantillas de mensajes, historial de envíos y alertas críticas. Implementa la arquitectura estándar de tres paneles con filtros, sub-vistas intercambiables y panel resumen.

## Estructura de Clases
| Clase | Tipo | Responsabilidad |
|-------|------|-----------------|
| `NotificacionesController` | Controller | Configuración JavaFX, toggle de sub-vistas, binding de tablas, acciones de notificación, badges de estado |
| `NotificacionesFacade` | Facade | Lógica de negocio, seed data de notificaciones, plantillas, alertas y estadísticas de envío |
| `NotificacionesFilters` | Model | Criterios de filtrado del módulo de notificaciones |
| `NotificacionesRowModel` | Model | Registros inmutables de filas para cada sub-vista |
| `NotificacionesSummaryModel` | Model | Resumen de la notificación seleccionada para el panel derecho |

### Sub-clases (inner records en NotificacionesRowModel)
| Record | Columnas |
|--------|----------|
| `BandejaRow` | datos de la bandeja principal de notificaciones |
| `NotifClienteRow` | datos de notificaciones enviadas al cliente |
| `NotifInternaRow` | datos de notificaciones internas del personal |
| `PlantillaRow` | datos de plantillas de mensajes configurables |
| `HistorialEnvioRow` | datos del histórico de envíos realizados |
| `AlertaCriticaRow` | datos de alertas críticas del sistema |

### Clase de dominio
| Clase | Paquete | Descripción |
|-------|---------|-------------|
| `Notificacion` | `shared.domain.notificacion` | Entidad de dominio que representa una notificación del sistema |

## Sub-vistas
| # | Sub-vista | Toggle Button | Tabla Principal | Descripción |
|---|-----------|--------------|-----------------|-------------|
| 1 | Bandeja de notificaciones | Toggle correspondiente | `bandejaTable` | Bandeja principal con todas las notificaciones pendientes |
| 2 | Notificaciones al cliente | Toggle correspondiente | Tabla de notificaciones cliente | Comunicaciones enviadas a clientes (SMS, WhatsApp, Email) |
| 3 | Notificaciones internas | Toggle correspondiente | Tabla de notificaciones internas | Alertas y mensajes entre personal del sistema |
| 4 | Plantillas | Toggle correspondiente | Tabla de plantillas | Plantillas configurables de mensajes |
| 5 | Historial de envíos | Toggle correspondiente | Tabla de historial | Registro de todos los envíos realizados |
| 6 | Alertas críticas | Toggle correspondiente | Tabla de alertas | Alertas que requieren atención inmediata |

## Flujo de Datos
```
[Usuario selecciona filtros] → applyFilters()
       ↓
NotificacionesFilters actualizado
       ↓
NotificacionesFacade.getBandeja() / getNotifCliente() / getNotifInterna() / etc.
       ↓
Stream.filter(matchesFilters) → lista filtrada
       ↓
ObservableList → tableView.setItems()
       ↓
[Usuario selecciona fila] → onRowSelected(row)
       ↓
facade.buildSummary(row) → NotificacionesSummaryModel
       ↓
updateSummaryPanel() → Labels del panel derecho actualizados
```

## Filtros Disponibles
| Filtro | Control | Descripción |
|--------|---------|-------------|
| Búsqueda libre | `TextField` | Búsqueda por destinatario, tipo o contenido |
| Tipo de notificación | `ComboBox<String>` | Tipo (recordatorio, notificación trabajo listo, cobro, etc.) |
| Canal | `ComboBox<String>` | Canal de envío (WhatsApp, SMS, Email, Llamada, Interna) |
| Estado | `ComboBox<String>` | Estado del envío (pendiente, enviado, entregado, fallido) |
| Prioridad | `ComboBox<String>` | Prioridad (normal, alta, crítica) |
| Desde | `DatePicker` | Fecha inicio del rango |
| Hasta | `DatePicker` | Fecha fin del rango |
| Solo pendientes | `CheckBox` | Mostrar solo notificaciones pendientes |

## Panel Resumen
El `NotificacionesSummaryModel` muestra los campos relevantes de la notificación seleccionada. El panel derecho persistente presenta información detallada de la notificación, incluyendo destinatario, tipo de mensaje, canal utilizado, estado de entrega, fecha de envío y acciones contextuales para reenviar, marcar como respondida o editar la plantilla.

## Patrones de Diseño
- **Facade Pattern**: `NotificacionesFacade` centraliza la lógica de negocio del módulo
- **MVC (Model-View-Controller)**: separación entre FXML, Controller y modelos de datos
- **Record Pattern**: modelos de fila como records inmutables con JavaFX properties
- **Toggle Group Pattern**: sub-vistas mutuamente exclusivas mediante `ToggleGroup`
- **Status Badge Pattern**: celdas personalizadas con `StatusBadgeTableCell` para indicadores visuales de estado de envío
- **Domain Object Pattern**: entidad `Notificacion` en `shared.domain.notificacion` como objeto de dominio compartido
- **Template Pattern**: plantillas de mensajes reutilizables y configurables
- **Filter Chain Pattern**: filtros encadenados con `FilterSupport`

## Integración con Otros Módulos
| Módulo | Punto de integración | Descripción |
|--------|---------------------|-------------|
| Seguimiento | Recordatorios | Las notificaciones de recall y seguimiento usan este módulo |
| Agenda | Citas programadas | Notificaciones automáticas de citas próximas |
| Ventas | Trabajo listo | Notificaciones al cliente cuando su pedido está listo |
| Cobros | Cobros pendientes | Recordatorios de pago enviados por notificación |
| Configuración | Plantillas | Las plantillas se configuran desde el módulo de configuración |
| Clientes | Destinatarios | Los clientes son destinatarios de las notificaciones externas |
