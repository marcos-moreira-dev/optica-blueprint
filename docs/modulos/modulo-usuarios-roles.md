# Usuarios y Roles - Documentación Técnica

## Resumen
Módulo de administración de usuarios, roles, permisos, sesiones y auditoría del sistema óptica. Gestiona cuentas de usuario, asignación de roles por sucursal, control de permisos granulares, monitoreo de sesiones activas, registro de auditoría y el histórico de accesos. Implementa la arquitectura de tres paneles con panel de resumen lateral y badges de estado.

## Estructura de Clases
| Clase | Tipo | Responsabilidad |
|-------|------|-----------------|
| `UsuariosRolesController` | Controller | Configuración JavaFX, toggle de sub-vistas, binding de tablas, acciones de usuario, badges de estado con `URStatusBadgeTableCell` |
| `UsuariosRolesFacade` | Facade | Lógica de negocio, seed data de usuarios, roles, permisos, sesiones y auditoría |
| `UsuariosRolesFilters` | Model | Criterios de filtrado del módulo de usuarios y roles |
| `UsuariosRolesRowModel` | Model | Registros inmutables de filas para cada sub-vista |
| `UsuariosRolesSummaryModel` | Model | Resumen del usuario o rol seleccionado para el panel derecho |

### Sub-clases (inner records en UsuariosRolesRowModel)
| Record | Columnas |
|--------|----------|
| `UsuarioRow` | datos de los usuarios del sistema (nombre, email, rol, estado) |
| `RolRow` | datos de roles disponibles y sus permisos asociados |
| `PermisoRow` | datos de permisos granulares por módulo |
| `SucursalUsuarioRow` | asignación de usuarios a sucursales |
| `SesionRow` | datos de sesiones activas y su estado |
| `AuditoriaRow` | registros de auditoría de acciones del sistema |
| `HistoricoAccesoRow` | histórico de accesos y actividades de usuarios |

### Clase de dominio
| Clase | Paquete | Descripción |
|-------|---------|-------------|
| `Usuario` | `shared.domain.usuario` | Entidad de dominio que representa un usuario del sistema |

## Sub-vistas
| # | Sub-vista | Toggle Button | Tabla Principal | Descripción |
|---|-----------|--------------|-----------------|-------------|
| 1 | Gestión de usuarios | Toggle correspondiente | Tabla de usuarios | Alta, baja y edición de cuentas de usuario |
| 2 | Roles del sistema | Toggle correspondiente | Tabla de roles | Definición de roles y sus permisos asociados |
| 3 | Permisos | Toggle correspondiente | Tabla de permisos | Permisos granulares por módulo y acción |
| 4 | Usuarios por sucursal | Toggle correspondiente | Tabla de asignación | Asignación de usuarios a sucursales |
| 5 | Sesiones activas | Toggle correspondiente | Tabla de sesiones | Monitoreo de sesiones activas en tiempo real |
| 6 | Auditoría | Toggle correspondiente | Tabla de auditoría | Registro de acciones realizadas en el sistema |
| 7 | Histórico de accesos | Toggle correspondiente | Tabla histórico | Archivo de accesos y actividades pasadas |

## Flujo de Datos
```
[Usuario selecciona filtros] → applyFilters()
       ↓
UsuariosRolesFilters actualizado
       ↓
UsuariosRolesFacade.getUsuarios() / getRoles() / getPermisos() / etc.
       ↓
Stream.filter(matchesFilters) → lista filtrada
       ↓
ObservableList → tableView.setItems()
       ↓
[Usuario selecciona fila] → onRowSelected(row)
       ↓
facade.buildSummary(row) → UsuariosRolesSummaryModel
       ↓
updateSummaryPanel() → Labels del panel derecho actualizados
```

## Filtros Disponibles
| Filtro | Control | Descripción |
|--------|---------|-------------|
| Búsqueda libre | `TextField` | Búsqueda por nombre, email o rol de usuario |
| Rol | `ComboBox<String>` | Rol del usuario (Administrador, Recepción, Asesor, Técnico, etc.) |
| Estado | `ComboBox<String>` | Estado del usuario (activo, inactivo, bloqueado) |
| Sucursal | `ComboBox<String>` | Sucursal asignada al usuario |
| Módulo | `ComboBox<String>` | Módulo para filtrar permisos |
| Desde | `DatePicker` | Fecha inicio del rango (para auditoría e histórico) |
| Hasta | `DatePicker` | Fecha fin del rango |
| Solo activos | `CheckBox` | Mostrar solo usuarios/sesiones activas |

## Panel Resumen
El `UsuariosRolesSummaryModel` muestra los campos relevantes del usuario o rol seleccionado. El panel derecho persistente presenta información detallada del usuario, incluyendo datos de acceso, rol asignado, sucursal, permisos activos, sesiones actuales y acciones contextuales para editar, desactivar o auditar la cuenta.

## Patrones de Diseño
- **Facade Pattern**: `UsuariosRolesFacade` centraliza la lógica de negocio del módulo
- **MVC (Model-View-Controller)**: separación entre FXML, Controller y modelos de datos
- **Record Pattern**: modelos de fila como records inmutables con JavaFX properties
- **Toggle Group Pattern**: sub-vistas mutuamente exclusivas mediante `ToggleGroup`
- **Status Badge Pattern**: celdas personalizadas con `URStatusBadgeTableCell` para indicadores visuales de estado
- **Domain Object Pattern**: entidad `Usuario` en `shared.domain.usuario` como objeto de dominio compartido
- **RBAC (Role-Based Access Control)**: modelo de permisos basado en roles con asignación granular
- **Audit Trail Pattern**: registro inmutable de acciones de auditoría con fecha, usuario y acción
- **Session Management Pattern**: monitoreo de sesiones activas con estado y tiempo de conexión

## Integración con Otros Módulos
| Módulo | Punto de integración | Descripción |
|--------|---------------------|-------------|
| Configuración | Usuarios y permisos | La categoría 3 de configuración referencia este módulo |
| Todos los módulos | Control de permisos | Cada módulo valida permisos del usuario activo |
| Sucursales | Asignación por sede | Los usuarios se asignan a sucursales específicas |
| Seguridad | Sesiones y auditoría | Registro de accesos y acciones en todo el sistema |
| App/Session | Contexto de usuario | La sesión actual contiene el usuario autenticado y su rol |
