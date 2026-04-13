# Lienzo del módulo Usuarios y roles para sistema de óptica en JavaFX

## 1. Identidad del módulo

### Nombre oficial del módulo en el menú
**Usuarios y roles**

### Texto visible del botón del sidebar
**Usuarios y roles**

### Tooltip del botón del sidebar
**Consultar, administrar y auditar usuarios, roles, permisos y accesos del sistema óptico**

### Ícono conceptual
Usuario, credencial, escudo de permisos o control de acceso.

### Título visible en pantalla
**Usuarios y roles**

### Subtítulo visible en pantalla
**Control de acceso, distribución por sede, permisos por módulo y trazabilidad de actividad**

### Tipo de módulo
Módulo administrativo transversal de acceso y control operativo.

### Objetivo del módulo
Permitir que la óptica administre qué personas usan el sistema, a qué sucursal pertenecen, qué rol cumplen, qué módulos pueden utilizar y qué acciones quedan registradas para trazabilidad y control.

---

## 2. Rol del módulo dentro de la arquitectura GUI

Usuarios y roles no debe sentirse como una simple lista de correos o contraseñas. Debe verse como el centro de control de acceso y responsabilidad operativa del sistema.

### Regla arquitectónica principal
Cuando el usuario entre al módulo Usuarios y roles, la región central principal del sistema debe reemplazarse por una vista completa llamada conceptualmente:
**UsuariosRolesModuleView**

### Estructura interna limpia del módulo
La vista Usuarios y roles debe dividirse en:
- encabezado del módulo
- barra de filtros y búsqueda
- subnavegación interna del módulo
- región central intercambiable para sub-vistas
- panel lateral derecho con resumen persistente del usuario, rol o registro seleccionado

### Filosofía de implementación
El usuario administrador debe poder ver personas, roles, permisos, sucursales, sesiones y trazabilidad sin perder el contexto del perfil seleccionado.

### Regla de arquitectura limpia
Debe existir una región interna estable que se reescriba solo con el submódulo activo. Esa región se denomina conceptualmente:
**usuariosRolesContentHostPane**

Ese contenedor puede ser un **StackPane** o el centro de un **BorderPane** secundario.

### Sub-vistas que viven dentro de usuariosRolesContentHostPane
- Directorio de usuarios
- Roles del sistema
- Permisos por módulo
- Usuarios por sucursal
- Sesiones y accesos
- Auditoría y trazabilidad
- Histórico y búsqueda avanzada

---

## 3. Sub-vistas oficiales del módulo Usuarios y roles

## Sub-vistas definidas
1. **Directorio de usuarios**
2. **Roles del sistema**
3. **Permisos por módulo**
4. **Usuarios por sucursal**
5. **Sesiones y accesos**
6. **Auditoría y trazabilidad**
7. **Histórico y búsqueda avanzada**

## Orden de prioridad
1. Directorio de usuarios
2. Roles del sistema
3. Permisos por módulo
4. Usuarios por sucursal
5. Sesiones y accesos
6. Auditoría y trazabilidad
7. Histórico y búsqueda avanzada

## Vista por defecto al abrir el módulo
**Directorio de usuarios**

Motivo: es la vista que mejor responde a la pregunta base de administración: quiénes están dentro del sistema y cómo están distribuidos.

---

## 4. Estructura visual general del módulo Usuarios y roles

## Contenedor raíz del módulo
**BorderPane**

### Distribución general del BorderPane principal
- **top**: encabezado del módulo + filtros + subnavegación
- **center**: cuerpo principal del módulo
- **right**: no aplica como región separada, porque el panel persistente ya vive dentro del cuerpo
- **left**: no aplica, porque el sidebar global ya existe fuera del módulo
- **bottom**: no aplica

### Decisión espacial clave
El cuerpo principal debe organizarse con un **SplitPane** horizontal.

### Distribución del SplitPane
- panel izquierdo o central amplio: sub-vista activa del módulo
- panel derecho: resumen persistente del usuario, rol o registro seleccionado

### Proporción recomendada
- región de trabajo: 68% a 72%
- panel derecho: 28% a 32%

### Regla de estabilidad visual
El panel derecho debe mantenerse visible durante casi toda la navegación para que el usuario no pierda el contexto del acceso o perfil seleccionado.

---

## 5. Encabezado del módulo Usuarios y roles

## Contenedor sugerido
**VBox** en la región top del BorderPane principal.

### Subestructura del encabezado
1. franja superior con título y acciones globales
2. franja media con filtros y búsqueda
3. franja inferior con subnavegación interna

---

## 6. Franja superior del encabezado

## Contenedor sugerido
**HBox**

### Zona izquierda
Un **VBox** con:
- Label principal: **Usuarios y roles**
- Label secundario: **Control de acceso, distribución por sede, permisos por módulo y trazabilidad de actividad**

### Zona derecha
Un **HBox** con:
- Button secundario: **Actualizar usuarios**
- Button secundario: **Exportar accesos**
- Button principal: **Nuevo usuario**

## Controles exactos

### Label principal
- Control: **Label**
- Texto exacto: **Usuarios y roles**
- Tooltip: no necesita

### Label secundario
- Control: **Label**
- Texto exacto: **Control de acceso, distribución por sede, permisos por módulo y trazabilidad de actividad**
- Tooltip: no necesita

### Botón secundario 1
- Control: **Button**
- Texto exacto: **Actualizar usuarios**
- Tooltip: **Recargar usuarios, roles y accesos visibles del módulo**
- Estilo: secundario

### Botón secundario 2
- Control: **Button**
- Texto exacto: **Exportar accesos**
- Tooltip: **Exportar la vista visible de usuarios, roles o auditoría**
- Estilo: secundario

### Botón principal
- Control: **Button**
- Texto exacto: **Nuevo usuario**
- Tooltip: **Registrar un nuevo usuario para uso del sistema óptico**
- Estilo: acción principal

---

## 7. Franja media: filtros y búsqueda

## Contenedor sugerido
**FlowPane**

## Función
Permitir localizar usuarios, roles o eventos de acceso por nombre, sucursal, estado o perfil.

## Controles oficiales

### Búsqueda rápida
- Control: **TextField**
- Label asociado: **Buscar**
- Placeholder exacto: **Usuario, rol, sede o evento de acceso**
- Tooltip: **Buscar usuarios, roles o accesos por texto relacionado**

### Filtro por rol
- Control: **ComboBox**
- Label asociado: **Rol**
- Ítems de semilla:
  - Todos
  - Administrador general
  - Administrador de sede
  - Recepción
  - Asesor de ventas
  - Técnico óptico
  - Caja
  - Supervisor
- Valor inicial: **Todos**
- Tooltip: **Filtrar usuarios según su rol principal**

### Filtro por estado
- Control: **ComboBox**
- Label asociado: **Estado**
- Ítems de semilla:
  - Todos
  - Activo
  - Inactivo
  - Suspendido
  - Pendiente de activación
  - Con acceso restringido
  - Bajo revisión
- Valor inicial: **Todos**
- Tooltip: **Filtrar usuarios o accesos según el estado actual**

### Filtro por sucursal
- Control: **ComboBox**
- Label asociado: **Sucursal**
- Ítems de semilla:
  - Todas
  - Matriz Centro
  - Norte Mall
  - Sur Express
- Valor inicial: **Todas**
- Tooltip: **Filtrar usuarios según la sede a la que están vinculados**

### Filtro por actividad
- Control: **ComboBox**
- Label asociado: **Actividad**
- Ítems de semilla:
  - Todas
  - Con último acceso hoy
  - Sin acceso reciente
  - Con eventos auditados
  - Con accesos fallidos
- Valor inicial: **Todas**
- Tooltip: **Filtrar por actividad reciente del usuario o del registro**

### Filtro por fecha inicial
- Control: **DatePicker**
- Label asociado: **Desde**
- Tooltip: **Seleccionar la fecha inicial para consultar actividad o auditoría**

### Filtro por fecha final
- Control: **DatePicker**
- Label asociado: **Hasta**
- Tooltip: **Seleccionar la fecha final para consultar actividad o auditoría**

### Opción rápida
- Control: **CheckBox**
- Texto exacto: **Solo eventos sensibles**
- Tooltip: **Mostrar únicamente cambios de permisos, accesos fallidos o eventos importantes**

### Botón limpiar
- Control: **Button**
- Texto exacto: **Limpiar filtros**
- Tooltip: **Restablecer la búsqueda y todos los filtros del módulo**

---

## 8. Franja inferior: subnavegación interna del módulo

## Contenedor sugerido
**HBox** con **ToggleButton** dentro de un **ToggleGroup**

## Sub-vistas visibles en la barra
- Directorio de usuarios
- Roles del sistema
- Permisos por módulo
- Usuarios por sucursal
- Sesiones y accesos
- Auditoría y trazabilidad
- Histórico y búsqueda avanzada

## Tooltips exactos
- Directorio de usuarios: **Consultar el listado general de usuarios registrados**
- Roles del sistema: **Consultar y administrar los perfiles funcionales del sistema**
- Permisos por módulo: **Consultar el alcance de acceso por módulo y acción**
- Usuarios por sucursal: **Consultar distribución de usuarios y perfiles por sede**
- Sesiones y accesos: **Consultar actividad reciente de ingreso al sistema**
- Auditoría y trazabilidad: **Consultar cambios y acciones registradas dentro del sistema**
- Histórico y búsqueda avanzada: **Consultar eventos históricos de acceso, rol o auditoría**

## Regla visual
La sub-vista activa debe quedar clara, pero sin competir con la lectura administrativa principal.

---

## 9. Cuerpo principal del módulo Usuarios y roles

## Contenedor sugerido
**SplitPane** horizontal

### Panel izquierdo o principal
Sub-vista activa del módulo.

### Panel derecho
Resumen persistente del usuario, rol o evento seleccionado.

---

# 10. Panel derecho persistente: resumen del usuario o rol

## Función
Mantener visible el contexto del acceso seleccionado mientras se navega por el módulo.

## Contenedor sugerido
**ScrollPane** con un **VBox** interno.

## Bloques internos del panel derecho
1. Identidad del perfil
2. Contexto operativo
3. Estado de acceso
4. Actividad reciente
5. Acciones rápidas

## 10.1. Bloque: Identidad del perfil

### Campos visibles
- Usuario
- Rol principal
- Sucursal

### Seeds
- **Usuario: admin@opticamanager.local**
- **Rol principal: Administrador general**
- **Sucursal: Matriz Centro**

## 10.2. Bloque: Contexto operativo

### Campos visibles
- Acceso a caja
- Acceso a inventario
- Acceso a reportes

### Seeds
- **Acceso a caja: Sí**
- **Acceso a inventario: Sí**
- **Acceso a reportes: Sí**

## 10.3. Bloque: Estado de acceso

### Campos visibles
- Estado actual
- Último acceso
- Nivel de revisión

### Seeds
- **Estado actual: Activo**
- **Último acceso: 16/04/2026 09:18**
- **Nivel de revisión: Normal**

## 10.4. Bloque: Actividad reciente

### Campos visibles
- Última acción registrada
- Observación breve

### Seeds
- **Última acción registrada: Exportó reporte comercial del período**
- **Observación breve: Usuario con actividad frecuente y sin incidentes**

## 10.5. Bloque: Acciones rápidas

### Botones oficiales
- **Abrir perfil**
- **Editar rol**
- **Cambiar sucursal**
- **Ver auditoría**
- **Desactivar usuario**

### Tooltips exactos
- Abrir perfil: **Abrir la ficha completa del usuario seleccionado**
- Editar rol: **Modificar el rol principal del usuario**
- Cambiar sucursal: **Reasignar la sede principal del usuario**
- Ver auditoría: **Consultar el histórico de acciones del usuario**
- Desactivar usuario: **Suspender o desactivar el acceso del usuario seleccionado**

---

# 11. Sub-vista 1: Directorio de usuarios

## Función
Servir como bandeja principal de usuarios activos, inactivos o en revisión dentro del sistema.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: pequeño resumen del directorio
- **center**: **TableView** principal
- **bottom**: resumen de resultados visibles

## 11.1. Franja superior del directorio

### Contenido
- Label: **Directorio de usuarios**
- Label secundario: **8 usuarios visibles**

### Tooltips
- Directorio de usuarios: **Usuarios visibles según los filtros actuales**
- 8 usuarios visibles: **Cantidad de usuarios mostrados en el directorio actual**

## 11.2. TableView principal

### Columnas oficiales
- Usuario
- Nombre visible
- Rol
- Sucursal
- Estado
- Último acceso

### Seeds oficiales
1. admin@opticamanager.local | Administración general | Administrador general | Matriz Centro | Activo | 16/04/2026 09:18
2. recepcion.centro@opticamanager.local | Recepción Centro | Recepción | Matriz Centro | Activo | 16/04/2026 08:52
3. ventas.norte@opticamanager.local | Ventas Norte | Asesor de ventas | Norte Mall | Activo | 16/04/2026 09:03
4. tecnico.laboratorio@opticamanager.local | Técnico Laboratorio | Técnico óptico | Matriz Centro | Activo | 16/04/2026 08:40
5. caja.matriz@opticamanager.local | Caja Matriz | Caja | Matriz Centro | Con acceso restringido | 15/04/2026 18:10

### Tooltip de la tabla
**Seleccione un usuario para ver su perfil, permisos y actividad reciente**

### Estado vacío
**No hay usuarios visibles con los filtros actuales**

## 11.3. Pie del listado

### Textos exactos
- **Mostrando 5 de 8 usuarios**
- **Ordenado por estado y último acceso**

### Tooltips
- Mostrando 5 de 8 usuarios: **Resumen de usuarios visibles en la bandeja actual**
- Ordenado por estado y último acceso: **Criterio de ordenamiento aplicado al directorio**

### Botones oficiales del submódulo
- **Abrir usuario**
- **Editar usuario**
- **Desactivar usuario**

### Tooltips
- Abrir usuario: **Consultar la ficha completa del usuario seleccionado**
- Editar usuario: **Modificar la información base del usuario**
- Desactivar usuario: **Marcar el usuario como no operativo o restringido**

---

# 12. Sub-vista 2: Roles del sistema

## Función
Mostrar y administrar los perfiles funcionales disponibles dentro del sistema.

## Contenedor principal sugerido
**SplitPane** horizontal

### Panel izquierdo
**ListView** o **TableView** de roles

### Panel derecho
Detalle funcional del rol seleccionado

## 12.1. Listado de roles

### Valores visibles
- Administrador general
- Administrador de sede
- Recepción
- Asesor de ventas
- Técnico óptico
- Caja
- Supervisor

### Tooltip del listado
**Roles funcionales disponibles dentro del sistema**

## 12.2. Detalle del rol

### Campos visibles
- Nombre del rol
- Descripción operativa
- Sede recomendada
- Nivel de acceso

### Seeds
- Nombre del rol: **Administrador general**
- Descripción operativa: **Controla configuración global, reportes, usuarios y supervisión general del sistema**
- Sede recomendada: **Todas**
- Nivel de acceso: **Total**

### Botones oficiales del submódulo
- **Guardar rol**
- **Duplicar rol**
- **Desactivar rol**

### Tooltips
- Guardar rol: **Guardar los cambios realizados al rol seleccionado**
- Duplicar rol: **Crear un nuevo rol tomando como base el actual**
- Desactivar rol: **Retirar el rol de circulación dentro del sistema**

### Estado vacío
**Seleccione un rol para ver su definición funcional**

---

# 13. Sub-vista 3: Permisos por módulo

## Función
Definir el alcance de acceso por módulo y tipo de acción para cada rol o usuario.

## Contenedor principal sugerido
**BorderPane** con una **TableView** matricial o un **GridPane** sobrio.

### Estructura interna
1. Selector de rol o usuario
2. Matriz de permisos
3. Acciones de guardado

## 13.1. Selector superior

### Controles sugeridos
- ComboBox: **Aplicar a rol**
- ComboBox: **Aplicar a usuario**

### Seeds
- Aplicar a rol: **Recepción**
- Aplicar a usuario: **Opcional según edición puntual**

## 13.2. Matriz de permisos

### Columnas sugeridas
- Módulo
- Ver
- Crear
- Editar
- Aprobar
- Exportar
- Cerrar

### Filas oficiales
- Inicio
- Agenda
- Clientes
- Recetas
- Venta óptica
- Órdenes de laboratorio
- Inventario
- Caja
- Entregas
- Seguimiento
- Reportes
- Configuración
- Compras
- Proveedores
- Notificaciones

### Ejemplo seed para rol Recepción
- Inicio: Ver Sí
- Agenda: Ver Sí, Crear Sí, Editar Sí
- Clientes: Ver Sí, Crear Sí, Editar Sí
- Recetas: Ver Sí
- Venta óptica: Ver Sí
- Caja: Ver No
- Configuración: Ver No

### Botones oficiales del submódulo
- **Guardar permisos**
- **Restablecer permisos**
- **Copiar desde rol**

### Tooltips
- Guardar permisos: **Guardar la matriz actual de permisos**
- Restablecer permisos: **Volver al esquema anterior de permisos**
- Copiar desde rol: **Copiar permisos desde otro rol base**

### Estado vacío
**Seleccione un rol o usuario para ver su matriz de permisos**

---

# 14. Sub-vista 4: Usuarios por sucursal

## Función
Consultar la distribución de personas y perfiles por sede.

## Contenedor principal sugerido
**SplitPane** horizontal

### Panel izquierdo
**TableView** de sucursales

### Panel derecho
**TableView** o bloque de usuarios asignados a la sede seleccionada

## 14.1. TableView de sucursales

### Columnas oficiales
- Sucursal
- Usuarios activos
- Roles principales
- Estado

### Seeds oficiales
1. Matriz Centro | 4 | Administración, Caja, Técnico | Activa
2. Norte Mall | 2 | Recepción, Ventas | Activa
3. Sur Express | 1 | Supervisor | Bajo revisión

## 14.2. Detalle de usuarios por sede

### Columnas oficiales
- Usuario
- Rol
- Estado
- Último acceso

### Seeds para Matriz Centro
1. admin@opticamanager.local | Administrador general | Activo | 16/04/2026 09:18
2. recepcion.centro@opticamanager.local | Recepción | Activo | 16/04/2026 08:52
3. tecnico.laboratorio@opticamanager.local | Técnico óptico | Activo | 16/04/2026 08:40
4. caja.matriz@opticamanager.local | Caja | Con acceso restringido | 15/04/2026 18:10

### Botones oficiales del submódulo
- **Asignar a sucursal**
- **Cambiar sede**
- **Ver rol**

### Tooltips
- Asignar a sucursal: **Asignar un usuario existente a la sede seleccionada**
- Cambiar sede: **Reasignar la sede principal del usuario seleccionado**
- Ver rol: **Consultar el rol funcional del usuario seleccionado**

### Estado vacío
**No hay usuarios visibles asignados a esta sucursal**

---

# 15. Sub-vista 5: Sesiones y accesos

## Función
Mostrar actividad reciente de ingreso al sistema y eventos básicos de autenticación.

## Contenedor principal sugerido
**BorderPane** con resumen superior y **TableView** central.

### Estructura interna
1. Resumen de sesiones
2. Tabla de accesos
3. Acciones de revisión

## 15.1. Resumen superior

### Campos visibles
- Ingresos exitosos hoy
- Accesos fallidos
- Usuarios activos
- Sesiones cerradas

### Seeds
- Ingresos exitosos hoy: **18**
- Accesos fallidos: **2**
- Usuarios activos: **6**
- Sesiones cerradas: **14**

## 15.2. TableView principal

### Columnas oficiales
- Fecha y hora
- Usuario
- Evento
- Sucursal
- Estado
- Observación

### Seeds oficiales
1. 16/04/2026 09:18 | admin@opticamanager.local | Inicio de sesión correcto | Matriz Centro | Correcto | Acceso normal
2. 16/04/2026 08:57 | ventas.norte@opticamanager.local | Inicio de sesión correcto | Norte Mall | Correcto | Acceso normal
3. 16/04/2026 08:44 | caja.matriz@opticamanager.local | Acceso fallido | Matriz Centro | Bajo revisión | Intento con credencial inválida
4. 15/04/2026 18:10 | tecnico.laboratorio@opticamanager.local | Cierre de sesión | Matriz Centro | Correcto | Fin de jornada

### Tooltip de la tabla
**Actividad reciente de acceso e ingreso al sistema**

### Botones oficiales del submódulo
- **Marcar revisión**
- **Cerrar sesión remota**
- **Exportar accesos**

### Tooltips
- Marcar revisión: **Marcar el evento seleccionado para revisión interna**
- Cerrar sesión remota: **Forzar el cierre de la sesión seleccionada cuando aplique**
- Exportar accesos: **Exportar el histórico visible de accesos**

### Estado vacío
**No hay sesiones ni accesos visibles con los filtros actuales**

---

# 16. Sub-vista 6: Auditoría y trazabilidad

## Función
Consultar quién hizo qué, cuándo y sobre qué módulo o registro del sistema.

## Contenedor principal sugerido
**BorderPane** con filtros ligeros arriba y **TableView** central.

### Estructura interna
1. Resumen de auditoría
2. Tabla de eventos auditados
3. Acciones de revisión

## 16.1. Resumen superior

### Campos visibles
- Cambios de permiso
- Cambios de rol
- Registros sensibles editados
- Exportaciones recientes

### Seeds
- Cambios de permiso: **3**
- Cambios de rol: **1**
- Registros sensibles editados: **5**
- Exportaciones recientes: **4**

## 16.2. TableView principal

### Columnas oficiales
- Fecha y hora
- Usuario
- Módulo
- Acción
- Registro afectado
- Observación

### Seeds oficiales
1. 16/04/2026 09:05 | admin@opticamanager.local | Usuarios y roles | Cambio de permiso | rol Caja | Se retiró exportación en Reportes
2. 16/04/2026 08:40 | recepcion.centro@opticamanager.local | Agenda | Edición de cita | AG-204 | Reprogramación solicitada por cliente
3. 15/04/2026 17:50 | caja.matriz@opticamanager.local | Caja | Cierre de caja confirmado | CJ-044 | Cierre sin diferencias
4. 15/04/2026 16:25 | tecnico.laboratorio@opticamanager.local | Recetas | Edición de receta | RC-188 | Ajuste de observación técnica

### Tooltip de la tabla
**Trazabilidad de cambios y acciones relevantes dentro del sistema**

### Botones oficiales del submódulo
- **Abrir registro**
- **Marcar sensible**
- **Exportar auditoría**

### Tooltips
- Abrir registro: **Abrir el contexto del registro afectado cuando sea posible**
- Marcar sensible: **Señalar el evento seleccionado como revisión prioritaria**
- Exportar auditoría: **Exportar el histórico visible de auditoría**

### Estado vacío
**No hay eventos auditados visibles con los filtros actuales**

---

# 17. Sub-vista 7: Histórico y búsqueda avanzada

## Función
Consultar usuarios, cambios de rol, accesos antiguos y eventos históricos de trazabilidad mediante filtros amplios.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: filtros extendidos
- **center**: **TableView** de resultados

## 17.1. Filtros extendidos

### Controles sugeridos
- TextField: **Usuario, rol, módulo o evento**
- ComboBox: **Rol**
- ComboBox: **Estado**
- ComboBox: **Sucursal**
- ComboBox: **Tipo de evento**
- DatePicker: **Desde**
- DatePicker: **Hasta**
- Button: **Buscar histórico**
- Button: **Limpiar filtros**

### Tooltip general
**Filtre eventos históricos de usuarios, roles, accesos o auditoría por fecha o criterio**

## 17.2. TableView principal

### Columnas oficiales
- Fecha
- Usuario
- Tipo de evento
- Rol o módulo
- Estado
- Observación

### Seeds oficiales
1. 14/04/2026 | caja.matriz@opticamanager.local | Cambio de permiso | Caja | Cerrado | Se restringió exportación
2. 13/04/2026 | supervisor.sur@opticamanager.local | Cambio de sucursal | Sur Express | Cerrado | Reasignado desde Norte Mall
3. 12/04/2026 | admin@opticamanager.local | Inicio de sesión fallido | Acceso | Bajo revisión | Credencial inválida en intento previo

### Tooltip de la tabla
**Consulte el histórico de actividad, cambios y accesos dentro del sistema**

### Botones oficiales del submódulo
- **Abrir evento**
- **Exportar histórico**

### Tooltips
- Abrir evento: **Consultar el detalle del evento histórico seleccionado**
- Exportar histórico: **Exportar el histórico visible del módulo Usuarios y roles**

### Estado vacío
**No hay registros históricos que coincidan con los criterios actuales**

---

# 18. Formulario conceptual: Nuevo usuario

## Función
Permitir registrar un nuevo usuario dentro de la arquitectura del módulo.

## Contenedor sugerido
Vista interna o diálogo amplio controlado. Para esta maqueta conviene resolverlo dentro del mismo módulo.

## Estructura sugerida
**ScrollPane** con **VBox** y bloques en **GridPane**.

### Grupos de campos
1. Identidad del usuario
2. Rol y sucursal
3. Estado inicial
4. Observación operativa

### Campos mínimos
- Correo o usuario
- Nombre visible
- Rol principal
- Sucursal
- Estado inicial
- Observación

### Botones finales
- **Guardar usuario**
- **Cancelar**

### Tooltips
- Guardar usuario: **Registrar el nuevo usuario dentro del sistema**
- Cancelar: **Cerrar el registro del usuario sin guardar**

---

# 19. Seed data oficial del módulo Usuarios y roles

## Usuarios
- admin@opticamanager.local
- recepcion.centro@opticamanager.local
- ventas.norte@opticamanager.local
- tecnico.laboratorio@opticamanager.local
- caja.matriz@opticamanager.local
- supervisor.sur@opticamanager.local

## Roles
- Administrador general
- Administrador de sede
- Recepción
- Asesor de ventas
- Técnico óptico
- Caja
- Supervisor

## Sucursales
- Matriz Centro
- Norte Mall
- Sur Express

## Estados usados en el módulo
- Activo
- Inactivo
- Suspendido
- Pendiente de activación
- Con acceso restringido
- Bajo revisión
- Correcto
- Cerrado

## Tipos de evento
- Inicio de sesión correcto
- Acceso fallido
- Cambio de rol
- Cambio de sucursal
- Cambio de permiso
- Registro editado
- Exportación realizada
- Cierre de sesión

---

# 20. Textos oficiales del módulo Usuarios y roles

## Títulos y labels principales
- Usuarios y roles
- Control de acceso, distribución por sede, permisos por módulo y trazabilidad de actividad
- Buscar
- Rol
- Estado
- Sucursal
- Actividad
- Desde
- Hasta
- Solo eventos sensibles
- Directorio de usuarios
- Roles del sistema
- Permisos por módulo
- Usuarios por sucursal
- Sesiones y accesos
- Auditoría y trazabilidad
- Histórico y búsqueda avanzada
- Usuario
- Nombre visible
- Último acceso
- Acceso a caja
- Acceso a inventario
- Acceso a reportes
- Última acción registrada
- Observación breve

## Botones oficiales
- Actualizar usuarios
- Exportar accesos
- Nuevo usuario
- Limpiar filtros
- Abrir perfil
- Editar rol
- Cambiar sucursal
- Ver auditoría
- Desactivar usuario
- Abrir usuario
- Editar usuario
- Guardar rol
- Duplicar rol
- Desactivar rol
- Guardar permisos
- Restablecer permisos
- Copiar desde rol
- Asignar a sucursal
- Cambiar sede
- Ver rol
- Marcar revisión
- Cerrar sesión remota
- Abrir registro
- Marcar sensible
- Exportar auditoría
- Buscar histórico
- Abrir evento
- Exportar histórico
- Guardar usuario
- Cancelar

## Placeholders
- Usuario, rol, sede o evento de acceso
- Usuario, rol, módulo o evento

## Empty states
- No hay usuarios visibles con los filtros actuales
- Seleccione un rol para ver su definición funcional
- Seleccione un rol o usuario para ver su matriz de permisos
- No hay usuarios visibles asignados a esta sucursal
- No hay sesiones ni accesos visibles con los filtros actuales
- No hay eventos auditados visibles con los filtros actuales
- No hay registros históricos que coincidan con los criterios actuales

---

# 21. Reglas visuales específicas del módulo Usuarios y roles

- el Directorio de usuarios debe sentirse como la vista principal del módulo
- el panel derecho debe mantener claridad sobre rol, sede y actividad reciente
- Roles del sistema debe verse estructural y sobrio
- Permisos por módulo debe priorizar legibilidad sobre complejidad visual
- Usuarios por sucursal debe comunicar distribución operativa real
- Sesiones y accesos debe sentirse como bitácora práctica, no técnica en exceso
- Auditoría y trazabilidad debe verse seria y muy ordenada
- Histórico y búsqueda avanzada debe sentirse fuerte para consulta y revisión
- no abusar de formas redondeadas ni de tarjetas decorativas
- la prioridad debe ser control, claridad y trazabilidad del acceso dentro del negocio óptico

---

# 22. Relación del módulo Usuarios y roles con otros módulos

Usuarios y roles debe conectarse con:
- Configuración, porque algunas reglas maestras de acceso pueden definirse allí
- Sucursales, porque cada usuario suele pertenecer a una sede o conjunto de sedes
- Reportes, porque auditoría y actividad también pueden leerse desde indicadores
- Caja, Agenda, Inventario, Entregas, Compras y demás módulos, porque el alcance real de acceso se define aquí
- Inicio, porque el panel principal puede resumir eventos sensibles, accesos fallidos o usuarios bajo revisión

---

# 23. Cierre del módulo Usuarios y roles

Este módulo debe transmitir que la óptica no deja el sistema abierto para cualquiera. Debe verse como una herramienta que controla quién entra, qué puede hacer, a qué sede pertenece, qué actividad reciente tiene y qué acciones quedan registradas. No se trata solo de crear usuarios, sino de administrar acceso, responsabilidad operativa y trazabilidad real dentro del sistema óptico.

La complejidad correcta del módulo está en que cada sub-vista resuelve una parte clara del control de acceso sin convertir la experiencia en una plataforma de seguridad excesivamente técnica.

