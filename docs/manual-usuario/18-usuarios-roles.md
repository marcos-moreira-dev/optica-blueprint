# Usuarios y Roles

## ¿Para qué sirve este módulo?

El módulo **Usuarios y Roles** gestiona quién puede acceder al sistema y qué puede hacer cada persona dentro de la aplicación. Aquí usted crea usuarios, asigna roles de permiso, controla el acceso por sucursal y revisa el historial de sesiones y actividad.

## ¿Quién usa esta pantalla?

- **Administradores generales:** para crear usuarios, asignar roles y gestionar permisos.
- **Dueños del negocio:** para controlar quién accede a qué información.

---

## Sub-vistas del módulo

El módulo tiene 7 sub-vistas accesibles desde los botones superiores:

| Sub-vista | Qué muestra |
|-----------|-------------|
| **Directorio de usuarios** | Lista de todos los usuarios del sistema. |
| **Roles del sistema** | Perfiles de acceso predefinidos (Administrador, Cajero, etc.). |
| **Permisos por rol** | Qué puede hacer cada rol en cada módulo. |
| **Usuarios por sucursal** | Qué usuarios están asignados a cada sede. |
| **Sesiones activas** | Quiénes están conectados ahora mismo. |
| **Auditoría y trazabilidad** | Registro de acciones realizadas por cada usuario. |
| **Histórico** | Actividad completa de todos los usuarios. |

---

## Directorio de usuarios

### Lista de todos los usuarios

Cada usuario del sistema tiene:

| Campo | Descripción |
|-------|-------------|
| **Código** | Identificador (ej: USR-001) |
| **Correo electrónico** | Email de acceso al sistema |
| **Nombre completo** | Nombre del usuario |
| **Rol** | Perfil de permisos asignado |
| **Sucursal asignada** | Sede principal del usuario |
| **Estado** | Activo / Inactivo |
| **Último acceso** | Cuándo entró por última vez |

### Crear un nuevo usuario
1. Vaya a **Directorio de usuarios**
2. Haga clic en **Nuevo usuario**
3. Complete:
   - Nombre completo
   - Correo electrónico (será su usuario para login)
   - Contraseña temporal
   - Rol asignado
   - Sucursal
4. Guarde
5. El usuario podrá ingresar con su correo y la contraseña temporal

> **Importante:** Indique al usuario que cambie su contraseña en el primer acceso.

---

## Roles del sistema

### Perfiles de acceso predefinidos

El sistema incluye roles con permisos pre-configurados:

| Rol | Qué puede hacer |
|-----|-----------------|
| **Administrador General** | Acceso total a todos los módulos y configuraciones. |
| **Administrador de Sede** | Gestión completa de su sucursal, sin acceso a configuración global. |
| **Asesor de Ventas** | Clientes, Venta Óptica, Inventario (consulta), Recetas (consulta). |
| **Cajero** | Caja (cobros), Clientes (consulta), Venta Óptica (consulta). |
| **Técnico Óptico** | Órdenes de Laboratorio, Inventario, Taller. |
| **Recepcionista** | Agenda, Clientes, Entregas, Seguimiento, Notificaciones. |
| **Optometrista** | Clientes, Recetas, Agenda, Seguimiento. |
| **Supervisor** | Todos los módulos en modo consulta + Reportes. |

### Crear un rol personalizado
1. Vaya a **Roles del sistema**
2. Haga clic en **Nuevo rol**
3. Asigne un nombre descriptivo
4. Seleccione qué módulos puede ver
5. Para cada módulo, seleccione el nivel de acceso:
   - **Solo lectura:** Puede ver pero no editar
   - **Completo:** Puede ver, crear, editar y eliminar
6. Guarde el rol

---

## Permisos por rol

### Detalle de qué puede hacer cada rol

Esta vista muestra una matriz de permisos:

| Módulo | Administrador | Asesor | Cajero | Recepcionista |
|--------|--------------|--------|--------|---------------|
| Inicio | ✅ | ✅ | ✅ | ✅ |
| Agenda | Completo | Consulta | - | Completo |
| Clientes | Completo | Completo | Consulta | Completo |
| Recetas | Completo | Consulta | - | Consulta |
| Venta Óptica | Completo | Completo | Consulta | - |
| Inventario | Completo | Completo | - | Consulta |
| Caja | Completo | - | Completo | - |
| Entregas | Completo | - | - | Completo |
| Reportes | Completo | - | - | Completo |
| Configuración | Completo | - | - | - |

### Modificar permisos de un rol
1. Vaya a **Permisos por rol**
2. Seleccione el rol a modificar
3. Active o desactive permisos por módulo
4. Guarde los cambios

> **Nota:** Los cambios de permisos afectan inmediatamente a todos los usuarios con ese rol.

---

## Usuarios por sucursal

### Asignación de usuarios a sedes

Muestra qué usuarios están asignados a cada sucursal:

| Sucursal | Usuarios asignados |
|----------|-------------------|
| Matriz Centro | Marcos Moreira (Admin), Laura Escobar (Caja), Carlos Zambrano (Ventas) |
| Norte Mall | Patricia Mendoza (Admin Sede), Monica Paredes (Recepción) |
| Sur Express | Dra. Gabriela Ruiz (Optometrista) |

### Cambiar la sucursal de un usuario
1. Vaya a **Usuarios por sucursal**
2. Seleccione al usuario
3. Cambie la sucursal asignada
4. Guarde

---

## Sesiones activas

### Quiénes están conectados ahora

Muestra los usuarios que tienen sesión abierta en este momento:

| Columna | Descripción |
|---------|-------------|
| **Usuario** | Nombre del usuario conectado |
| **Sucursal** | Desde qué sucursal está conectado |
| **Hora de inicio** | Cuándo inició sesión |
| **Última actividad** | Cuándo fue su última acción |
| **IP** | Dirección IP de conexión |

### Cerrar sesión de un usuario
Si necesita desconectar a un usuario:
1. Seleccione la sesión activa
2. Haga clic en **Cerrar sesión**
3. El usuario será desconectado inmediatamente

> **Use esta función con precaución.** Solo cierre sesiones si hay una razón justificada (usuario que no debería estar conectado, cambio de permisos, etc.).

---

## Auditoría y trazabilidad

### Registro de acciones del sistema

El sistema registra automáticamente las acciones importantes de cada usuario:

| Campo | Descripción |
|-------|-------------|
| **Fecha y hora** | Cuándo ocurrió la acción |
| **Usuario** | Quién realizó la acción |
| **Módulo** | En qué módulo actuó |
| **Acción** | Qué hizo (crear, editar, eliminar, cobrar, etc.) |
| **Detalle** | Qué entidad fue afectada (ej: "Venta OV-00045 cobrada") |

### Acciones que se auditan
- Inicios y cierres de sesión
- Creación, edición y eliminación de clientes
- Confirmación de ventas
- Cobros procesados
- Cambios de configuración
- Modificaciones de stock
- Envío de notificaciones

### Para qué sirve la auditoría
- **Investigar errores:** ¿Quién hizo qué cambio?
- **Seguridad:** Detectar actividad inusual
- **Cumplimiento:** Demostrar trazabilidad de operaciones

---

## Casos de uso comunes

### "Un nuevo asesor comienza mañana"
1. Vaya a **Directorio de usuarios**
2. Haga clic en **Nuevo usuario**
3. Complete nombre, email, contraseña temporal
4. Asigne el rol **Asesor de Ventas**
5. Asigne la sucursal
6. Indique al nuevo usuario sus credenciales de acceso

### "Quiero que la recepcionista también pueda ver reportes"
1. Vaya a **Roles del sistema**
2. Seleccione el rol **Recepcionista**
3. Vaya a **Permisos por rol**
4. Active el módulo **Reportes** con acceso **Solo lectura**
5. Guarde

### "Necesito saber quién cobró la venta OV-00045"
1. Vaya a **Auditoría y trazabilidad**
2. Filtre por "OV-00045" o "cobro"
3. El registro mostrará quién procesó el cobro y cuándo

### "Hay un usuario conectado que no debería estarlo"
1. Vaya a **Sesiones activas**
2. Identifique la sesión
3. Haga clic en **Cerrar sesión**
4. Si es necesario, desactive al usuario en el **Directorio**

### "Quiero ver qué usuarios tiene cada sucursal"
1. Vaya a **Usuarios por sucursal**
2. La tabla muestra la asignación completa

---

## Notas importantes
- Nunca elimine un usuario; desactívelo. Esto preserva el histórico de auditoría.
- Los cambios de permisos afectan inmediatamente a todos los usuarios del rol.
- La contraseña temporal debe ser cambiada por el usuario en su primer acceso.
- El registro de auditoría no puede ser modificado ni eliminado por ningún usuario.
- Las sesiones activas se cierran automáticamente al cerrar la aplicación.
- Un usuario solo puede acceder a la sucursal que tiene asignada.
