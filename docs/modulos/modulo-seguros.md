# Seguros - Documentación Técnica

## Resumen
Módulo de gestión de seguros y autorizaciones del sistema óptica. Administra verificaciones de cobertura, autorizaciones de tratamiento, reclamos a aseguradoras, planes de seguro y el histórico de operaciones. Implementa la arquitectura estándar de tres paneles con sub-vistas intercambiables y panel de resumen lateral.

## Estructura de Clases
| Clase | Tipo | Responsabilidad |
|-------|------|-----------------|
| `SegurosController` | Controller | Configuración JavaFX, toggle de sub-vistas, binding de tablas, acciones de seguro |
| `SegurosFacade` | Facade | Lógica de negocio, seed data de verificaciones, autorizaciones, reclamos y planes |
| `SegurosFilters` | Model | Criterios de filtrado del módulo de seguros |
| `SegurosRowModel` | Model | Registros inmutables de filas para cada sub-vista |
| `SegurosSummaryModel` | Model | Resumen del caso de seguro seleccionado para el panel derecho |

### Sub-clases (inner records en SegurosRowModel)
| Record | Columnas |
|--------|----------|
| `VerificacionRow` | datos de verificación de cobertura del seguro |
| `AutorizacionRow` | datos de autorización de tratamiento |
| `ReclamoRow` | datos de reclamos presentados a aseguradoras |
| `RespuestaRow` | datos de respuestas de aseguradoras |
| `PlanRow` | datos de planes de seguro disponibles |
| `CoberturaVentaRow` | datos de cobertura aplicada a ventas |
| `HistoricoRow` | datos del histórico de operaciones de seguros |

## Sub-vistas
| # | Sub-vista | Toggle Button | Tabla Principal | Descripción |
|---|-----------|--------------|-----------------|-------------|
| 1 | Verificación de cobertura | Toggle correspondiente | Tabla de verificaciones | Consulta y registro de verificaciones de cobertura |
| 2 | Autorizaciones | Toggle correspondiente | Tabla de autorizaciones | Gestión de autorizaciones de tratamiento |
| 3 | Reclamos | Toggle correspondiente | Tabla de reclamos | Seguimiento de reclamos a aseguradoras |
| 4 | Respuestas | Toggle correspondiente | Tabla de respuestas | Registro de respuestas de aseguradoras |
| 5 | Planes de seguro | Toggle correspondiente | Tabla de planes | Catálogo de planes disponibles |
| 6 | Cobertura en ventas | Toggle correspondiente | Tabla de coberturas | Aplicación de cobertura a ventas |
| 7 | Histórico | Toggle correspondiente | Tabla histórico | Archivo de operaciones completadas |

## Flujo de Datos
```
[Usuario selecciona filtros] → applyFilters()
       ↓
SegurosFilters actualizado
       ↓
SegurosFacade.getVerificaciones() / getAutorizaciones() / getReclamos() / etc.
       ↓
Stream.filter(matchesFilters) → lista filtrada
       ↓
ObservableList → tableView.setItems()
       ↓
[Usuario selecciona fila] → onRowSelected(row)
       ↓
facade.buildSummary(row) → SegurosSummaryModel
       ↓
updateSummaryPanel() → Labels del panel derecho actualizados
```

## Filtros Disponibles
| Filtro | Control | Descripción |
|--------|---------|-------------|
| Búsqueda libre | `TextField` | Búsqueda por referencia o cliente |
| Tipo de seguro | `ComboBox<String>` | Tipo de seguro o cobertura |
| Estado | `ComboBox<String>` | Estado de la operación (pendiente, aprobado, rechazado, etc.) |
| Aseguradora | `ComboBox<String>` | Compañía aseguradora |
| Sucursal | `ComboBox<String>` | Sede donde se gestiona el caso |
| Desde | `DatePicker` | Fecha inicio del rango |
| Hasta | `DatePicker` | Fecha fin del rango |
| Solo activos | `CheckBox` | Mostrar solo casos activos |

## Panel Resumen
El `SegurosSummaryModel` muestra los campos relevantes del caso de seguro seleccionado. El panel derecho persistente presenta información detallada de la verificación, autorización o reclamo seleccionado, con acciones contextuales para gestionar la operación.

## Patrones de Diseño
- **Facade Pattern**: `SegurosFacade` centraliza la lógica de negocio del módulo
- **MVC (Model-View-Controller)**: separación entre FXML, Controller y modelos de datos
- **Record Pattern**: modelos de fila como records inmutables con JavaFX properties
- **Toggle Group Pattern**: sub-vistas mutuamente exclusivas mediante `ToggleGroup`
- **Pagination Pattern**: paginación controlada por `PaginationBarController`
- **Filter Chain Pattern**: filtros encadenados con `FilterSupport`

## Integración con Otros Módulos
| Módulo | Punto de integración | Descripción |
|--------|---------------------|-------------|
| Ventas | Cobertura en ventas | Aplicación de cobertura de seguro al momento de la venta |
| Clientes | Verificación de cobertura | Datos del paciente para verificar cobertura |
| Agenda | Autorizaciones | Citas asociadas a autorizaciones de tratamiento |
| Sucursales | Filtro global | Todas las sub-vistas filtran por sucursal activa |
| Proveedores | Aseguradoras | Las aseguradoras operan como proveedores especiales |
| Reportes | Retención | Datos de seguros aplicados al reporte de retención |
