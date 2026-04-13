# Sucursales - Documentación Técnica

## Resumen
Módulo de gestión y monitoreo de sucursales del sistema óptica. Proporciona un directorio de sedes, información de inventario por sucursal, personal asignado, agenda de citas, estado de cajas y un panel comparativo entre sucursales. Implementa la arquitectura de tres paneles con filtros, sub-vistas intercambiables y panel de resumen con badges de estado.

## Estructura de Clases
| Clase | Tipo | Responsabilidad |
|-------|------|-----------------|
| `SucursalesController` | Controller | Configuración JavaFX, toggle de sub-vistas, binding de tablas, acciones de sucursal, badges de estado con `EstadoBadgeCell` y `ComparativoEstadoCell` |
| `SucursalesFacade` | Facade | Lógica de negocio, seed data de sucursales, inventario, personal, agenda, cajas y métricas comparativas |
| `SucursalesFilters` | Model | Criterios de filtrado del módulo de sucursales |
| `SucursalesRowModel` | Model | Registros inmutables de filas para cada sub-vista |
| `SucursalesSummaryModel` | Model | Resumen de la sucursal seleccionada para el panel derecho |

### Sub-clases (inner records en SucursalesRowModel)
| Record | Columnas |
|--------|----------|
| `DirectorioRow` | datos del directorio de sucursales (nombre, dirección, horario, responsable, estado) |
| `InventarioRow` | datos de inventario por sucursal (stock, alertas, movimientos) |
| `PersonalRow` | datos de personal asignado a cada sucursal |
| `AgendaRow` | datos de agenda y citas programadas por sucursal |
| `CajaRow` | datos de estado de cajas y operaciones de cobro por sucursal |
| `ComparativoRow` | datos comparativos entre sucursales (ventas, citas, inventario) |

### Clases de dominio y sesión
| Clase | Paquete | Descripción |
|-------|---------|-------------|
| `Sucursal` | `shared.domain.sucursal` | Entidad de dominio que representa una sucursal del sistema |
| `CurrentSucursalContext` | `app.session` | Contexto de sesión que mantiene la sucursal activa del usuario |

## Sub-vistas
| # | Sub-vista | Toggle Button | Tabla Principal | Descripción |
|---|-----------|--------------|-----------------|-------------|
| 1 | Directorio de sucursales | Toggle correspondiente | Tabla de directorio | Listado de todas las sedes con su información básica |
| 2 | Inventario por sucursal | Toggle correspondiente | Tabla de inventario | Stock y alertas de inventario filtrados por sede |
| 3 | Personal asignado | Toggle correspondiente | Tabla de personal | Empleados asignados a cada sucursal |
| 4 | Agenda de sucursal | Toggle correspondiente | Tabla de agenda | Citas programadas y ocupación por sede |
| 5 | Cajas y operaciones | Toggle correspondiente | Tabla de cajas | Estado de cajas y operaciones de cobro activas |
| 6 | Comparativo | Toggle correspondiente | Tabla comparativa | Métricas comparativas entre sucursales con badges de estado |

## Flujo de Datos
```
[Usuario selecciona sucursal/filtros] → applyFilters()
       ↓
SucursalesFilters actualizado
       ↓
SucursalesFacade.getDirectorio() / getInventario() / getPersonal() / etc.
       ↓
Stream.filter(matchesFilters) → lista filtrada
       ↓
ObservableList → tableView.setItems()
       ↓
[Usuario selecciona fila/sucursal] → onRowSelected(row)
       ↓
facade.buildSummary(row) → SucursalesSummaryModel
       ↓
updateSummaryPanel() → Labels del panel derecho actualizados
```

## Filtros Disponibles
| Filtro | Control | Descripción |
|--------|---------|-------------|
| Búsqueda libre | `TextField` | Búsqueda por nombre de sucursal, dirección o responsable |
| Estado | `ComboBox<String>` | Estado de la sucursal (activa, inactiva, mantenimiento) |
| Zona/Región | `ComboBox<String>` | Zona geográfica de la sucursal |
| Tipo de operación | `ComboBox<String>` | Tipo (matriz, sucursal, punto de atención) |
| Desde | `DatePicker` | Fecha inicio del rango (para comparativos) |
| Hasta | `DatePicker` | Fecha fin del rango |

## Panel Resumen
El `SucursalesSummaryModel` muestra los campos relevantes de la sucursal seleccionada. El panel derecho persistente presenta información detallada de la sucursal, incluyendo datos de contacto, métricas de inventario, personal asignado, citas del día, estado de cajas y acciones contextuales para editar la sucursal, ver reportes específicos o cambiar el contexto de sesión.

## Patrones de Diseño
- **Facade Pattern**: `SucursalesFacade` centraliza la lógica de negocio del módulo
- **MVC (Model-View-Controller)**: separación entre FXML, Controller y modelos de datos
- **Record Pattern**: modelos de fila como records inmutables con JavaFX properties
- **Toggle Group Pattern**: sub-vistas mutuamente exclusivas mediante `ToggleGroup`
- **Status Badge Pattern**: celdas personalizadas con `EstadoBadgeCell` y `ComparativoEstadoCell` para indicadores visuales
- **Domain Object Pattern**: entidad `Sucursal` en `shared.domain.sucursal` como objeto de dominio compartido
- **Session Context Pattern**: `CurrentSucursalContext` mantiene la sucursal activa en la sesión del usuario
- **Comparative Analysis Pattern**: tabla comparativa con métricas lado a lado entre sucursales

## Integración con Otros Módulos
| Módulo | Punto de integración | Descripción |
|--------|---------------------|-------------|
| Configuración | Sucursales y operación | La categoría 2 de configuración define las sucursales |
| Usuarios y Roles | Asignación por sucursal | Los usuarios se asignan a sucursales específicas |
| Inventario | Stock por sucursal | Cada sucursal tiene su propio inventario |
| Ventas | Ventas por sucursal | Las ventas se registran con la sucursal activa |
| Agenda | Citas por sucursal | Las citas se filtran por sucursal |
| Compras | Sucursal destino | Las compras se asignan a una sucursal |
| Todos los módulos | Filtro global | La sucursal activa filtra datos en prácticamente todos los módulos |
| Reportes | Filtro de sucursal | Los reportes se pueden generar por sucursal o todas |
