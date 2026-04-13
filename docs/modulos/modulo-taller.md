# Taller - Documentación Técnica

## Resumen
Módulo de gestión del taller óptico para administración de reparaciones, diagnósticos, ingreso de piezas, entregas y envíos externos. Controla el flujo de trabajo de elaboración y reparación de lentes y monturas, con seguimiento del estado de cada orden de taller. Implementa la arquitectura de tres paneles con filtros, sub-vistas intercambiables y panel de resumen lateral.

## Estructura de Clases
| Clase | Tipo | Responsabilidad |
|-------|------|-----------------|
| `TallerController` | Controller | Configuración JavaFX, toggle de sub-vistas, binding de tablas, acciones de taller |
| `TallerFacade` | Facade | Lógica de negocio, seed data de diagnósticos, reparaciones, ingresos, piezas, entregas y envíos externos |
| `TallerFilters` | Model | Criterios de filtrado del módulo de taller |
| `TallerRowModel` | Model | Registros inmutables de filas para cada sub-vista |
| `TallerSummaryModel` | Model | Resumen del caso de taller seleccionado para el panel derecho |

### Sub-clases (inner records en TallerRowModel)
| Record | Columnas |
|--------|----------|
| `IngresoRow` | datos de ingreso de trabajos al taller (orden, cliente, tipo trabajo, estado) |
| `DiagnosticoRow` | datos del diagnóstico técnico de cada trabajo |
| `ReparacionRow` | datos de reparaciones en proceso y su avance |
| `PiezaRow` | datos de piezas y materiales utilizados en reparaciones |
| `EntregaRow` | datos de entregas de trabajos completados al cliente |
| `EnvioExternoRow` | datos de envíos a laboratorios externos |
| `HistoricoRow` | datos del histórico de trabajos completados |

## Sub-vistas
| # | Sub-vista | Toggle Button | Tabla Principal | Descripción |
|---|-----------|--------------|-----------------|-------------|
| 1 | Ingreso de trabajos | Toggle correspondiente | Tabla de ingresos | Registro de nuevos trabajos que ingresan al taller |
| 2 | Diagnóstico | Toggle correspondiente | Tabla de diagnósticos | Evaluación técnica de cada trabajo ingresado |
| 3 | Reparaciones en proceso | Toggle correspondiente | Tabla de reparaciones | Seguimiento de reparaciones activas y su avance |
| 4 | Piezas y materiales | Toggle correspondiente | Tabla de piezas | Control de piezas y materiales utilizados |
| 5 | Entregas | Toggle correspondiente | Tabla de entregas | Trabajos completados listos para entrega al cliente |
| 6 | Envíos externos | Toggle correspondiente | Tabla de envíos | Trabajos enviados a laboratorios externos |
| 7 | Histórico | Toggle correspondiente | Tabla histórico | Archivo de trabajos completados |

## Flujo de Datos
```
[Usuario selecciona filtros] → applyFilters()
       ↓
TallerFilters actualizado
       ↓
TallerFacade.getIngresos() / getDiagnosticos() / getReparaciones() / etc.
       ↓
Stream.filter(matchesFilters) → lista filtrada
       ↓
ObservableList → tableView.setItems()
       ↓
[Usuario selecciona fila] → onRowSelected(row)
       ↓
facade.buildSummary(row) → TallerSummaryModel
       ↓
updateSummaryPanel() → Labels del panel derecho actualizados
```

## Filtros Disponibles
| Filtro | Control | Descripción |
|--------|---------|-------------|
| Búsqueda libre | `TextField` | Búsqueda por número de orden, cliente o tipo de trabajo |
| Tipo de trabajo | `ComboBox<String>` | Tipo (reparación, ajuste, elaboración, garantía) |
| Estado | `ComboBox<String>` | Estado del trabajo (ingresado, en diagnóstico, en reparación, listo, entregado) |
| Prioridad | `ComboBox<String>` | Prioridad del trabajo (alta, media, baja, urgente) |
| Sucursal | `ComboBox<String>` | Sucursal donde se gestiona el trabajo |
| Desde | `DatePicker` | Fecha inicio del rango |
| Hasta | `DatePicker` | Fecha fin del rango |
| Solo activos | `CheckBox` | Mostrar solo trabajos en proceso |

## Panel Resumen
El `TallerSummaryModel` muestra los campos relevantes del caso de taller seleccionado. El panel derecho persistente presenta información detallada del trabajo, incluyendo cliente asociado, tipo de trabajo, diagnóstico técnico, estado de reparación, piezas utilizadas, fecha estimada de entrega y acciones contextuales para avanzar el flujo de trabajo.

## Patrones de Diseño
- **Facade Pattern**: `TallerFacade` centraliza la lógica de negocio del módulo
- **MVC (Model-View-Controller)**: separación entre FXML, Controller y modelos de datos
- **Record Pattern**: modelos de fila como records inmutables con JavaFX properties
- **Toggle Group Pattern**: sub-vistas mutuamente exclusivas mediante `ToggleGroup`
- **Workflow Pattern**: flujo de trabajo secuencial (ingreso → diagnóstico → reparación → entrega)
- **Filter Chain Pattern**: filtros encadenados con `FilterSupport`
- **Pagination Pattern**: paginación controlada por `PaginationBarController`

## Integración con Otros Módulos
| Módulo | Punto de integración | Descripción |
|--------|---------------------|-------------|
| Ventas | Orden de trabajo | Las ventas generan órdenes de trabajo para el taller |
| Laboratorio | Envíos externos | Trabajos enviados a laboratorios externos |
| Inventario | Piezas y materiales | Consumo de piezas del inventario del taller |
| Seguimiento | Pedidos no retirados | Trabajos completados no retirados por el cliente |
| Configuración | Laboratorio y entregas | Parámetros de tiempos promesa y validación |
| Clientes | Datos del paciente | Información del cliente asociado a cada trabajo |
