# Lienzo del módulo Sucursales para sistema de óptica en JavaFX

## 1. Identidad del módulo

### Nombre oficial del módulo en el menú
**Sucursales**

### Texto visible del botón del sidebar
**Sucursales**

### Tooltip del botón del sidebar
**Consultar, administrar y comparar sedes de la óptica y su operación local**

### Ícono conceptual
Edificio, ubicación, sede o nodo operativo.

### Título visible en pantalla
**Sucursales**

### Subtítulo visible en pantalla
**Control de sedes, personal, inventario local, atención, caja y desempeño por ubicación**

### Tipo de módulo
Módulo administrativo y operativo de sedes con sub-vistas internas especializadas.

### Objetivo del módulo
Permitir que la óptica defina, consulte y compare sus sucursales como unidades reales de operación, mostrando datos institucionales, responsables, horarios, servicios habilitados, inventario local, atención, caja y desempeño por sede.

---

## 2. Rol del módulo dentro de la arquitectura GUI

Sucursales no debe sentirse como una simple libreta de direcciones. Debe verse como el módulo donde cada sede se entiende como un nodo operativo real del negocio.

### Regla arquitectónica principal
Cuando el usuario entre al módulo Sucursales, la región central principal del sistema debe reemplazarse por una vista completa llamada conceptualmente:
**SucursalesModuleView**

### Estructura interna limpia del módulo
La vista Sucursales debe dividirse en:
- encabezado del módulo
- barra de filtros y búsqueda
- subnavegación interna del módulo
- región central intercambiable para sub-vistas
- panel lateral derecho con resumen persistente de la sucursal seleccionada

### Filosofía de implementación
El usuario debe poder moverse entre sedes, revisar su perfil, mirar operación local y comparar resultados sin perder el contexto de la sede activa.

### Regla de arquitectura limpia
Debe existir una región interna estable que se reescriba solo con el submódulo activo. Esa región se denomina conceptualmente:
**sucursalesContentHostPane**

Ese contenedor puede ser un **StackPane** o el centro de un **BorderPane** secundario.

### Sub-vistas que viven dentro de sucursalesContentHostPane
- Directorio de sucursales
- Perfil operativo de sucursal
- Personal y permisos por sede
- Inventario y stock por sucursal
- Agenda, atención y flujo local
- Caja y desempeño por sucursal
- Comparativo entre sucursales

---

## 3. Sub-vistas oficiales del módulo Sucursales

## Sub-vistas definidas
1. **Directorio de sucursales**
2. **Perfil operativo de sucursal**
3. **Personal y permisos por sede**
4. **Inventario y stock por sucursal**
5. **Agenda, atención y flujo local**
6. **Caja y desempeño por sucursal**
7. **Comparativo entre sucursales**

## Orden de prioridad
1. Directorio de sucursales
2. Perfil operativo de sucursal
3. Personal y permisos por sede
4. Inventario y stock por sucursal
5. Agenda, atención y flujo local
6. Caja y desempeño por sucursal
7. Comparativo entre sucursales

## Vista por defecto al abrir el módulo
**Directorio de sucursales**

Motivo: es la vista que mejor responde a la pregunta base de organización: qué sedes existen y cómo están configuradas.

---

## 4. Estructura visual general del módulo Sucursales

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
- panel derecho: resumen persistente de la sucursal seleccionada

### Proporción recomendada
- región de trabajo: 68% a 72%
- panel derecho: 28% a 32%

### Regla de estabilidad visual
El panel derecho debe mantenerse visible durante casi toda la navegación para que el usuario no pierda el contexto de la sede seleccionada.

---

## 5. Encabezado del módulo Sucursales

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
- Label principal: **Sucursales**
- Label secundario: **Control de sedes, personal, inventario local, atención, caja y desempeño por ubicación**

### Zona derecha
Un **HBox** con:
- Button secundario: **Actualizar sucursales**
- Button secundario: **Exportar sedes**
- Button principal: **Nueva sucursal**

## Controles exactos

### Label principal
- Control: **Label**
- Texto exacto: **Sucursales**
- Tooltip: no necesita

### Label secundario
- Control: **Label**
- Texto exacto: **Control de sedes, personal, inventario local, atención, caja y desempeño por ubicación**
- Tooltip: no necesita

### Botón secundario 1
- Control: **Button**
- Texto exacto: **Actualizar sucursales**
- Tooltip: **Recargar la información visible de las sedes registradas**
- Estilo: secundario

### Botón secundario 2
- Control: **Button**
- Texto exacto: **Exportar sedes**
- Tooltip: **Exportar el directorio o comparativo visible de sucursales**
- Estilo: secundario

### Botón principal
- Control: **Button**
- Texto exacto: **Nueva sucursal**
- Tooltip: **Registrar una nueva sede para la óptica**
- Estilo: acción principal

---

## 7. Franja media: filtros y búsqueda

## Contenedor sugerido
**FlowPane**

## Función
Permitir localizar sedes por nombre, responsable, estado o servicios habilitados.

## Controles oficiales

### Búsqueda rápida
- Control: **TextField**
- Label asociado: **Buscar**
- Placeholder exacto: **Sucursal, responsable, ciudad o referencia**
- Tooltip: **Buscar sucursales por nombre, responsable, ciudad o texto relacionado**

### Filtro por estado
- Control: **ComboBox**
- Label asociado: **Estado**
- Ítems de semilla:
  - Todas
  - Activa
  - Inactiva
  - Operativa
  - Bajo observación
- Valor inicial: **Todas**
- Tooltip: **Filtrar las sucursales según su estado general**

### Filtro por servicio habilitado
- Control: **ComboBox**
- Label asociado: **Servicio**
- Ítems de semilla:
  - Todos
  - Caja habilitada
  - Inventario propio
  - Entrega habilitada
  - Agenda habilitada
  - Laboratorio local
- Valor inicial: **Todos**
- Tooltip: **Filtrar sucursales según el servicio o capacidad habilitada**

### Filtro por ciudad
- Control: **ComboBox**
- Label asociado: **Ciudad**
- Ítems de semilla:
  - Todas
  - Guayaquil
- Valor inicial: **Todas**
- Tooltip: **Filtrar sucursales por ciudad registrada**

### Opción rápida
- Control: **CheckBox**
- Texto exacto: **Solo con alertas operativas**
- Tooltip: **Mostrar únicamente sedes con atención o revisión sugerida**

### Botón limpiar
- Control: **Button**
- Texto exacto: **Limpiar filtros**
- Tooltip: **Restablecer la búsqueda y todos los filtros del módulo**

---

## 8. Franja inferior: subnavegación interna del módulo

## Contenedor sugerido
**HBox** con **ToggleButton** dentro de un **ToggleGroup**

## Sub-vistas visibles en la barra
- Directorio de sucursales
- Perfil operativo de sucursal
- Personal y permisos por sede
- Inventario y stock por sucursal
- Agenda, atención y flujo local
- Caja y desempeño por sucursal
- Comparativo entre sucursales

## Tooltips exactos
- Directorio de sucursales: **Consultar el listado general de sedes registradas**
- Perfil operativo de sucursal: **Ver la ficha operativa completa de la sucursal seleccionada**
- Personal y permisos por sede: **Consultar usuarios, roles y permisos asignados a la sucursal**
- Inventario y stock por sucursal: **Consultar el inventario local y alertas de stock por sede**
- Agenda, atención y flujo local: **Consultar la capacidad operativa y atención de la sucursal**
- Caja y desempeño por sucursal: **Consultar el desempeño comercial y económico de la sede**
- Comparativo entre sucursales: **Comparar desempeño, alertas y métricas entre sedes**

## Regla visual
La sub-vista activa debe quedar clara, pero sin competir con el contenido central del módulo.

---

## 9. Cuerpo principal del módulo Sucursales

## Contenedor sugerido
**SplitPane** horizontal

### Panel izquierdo o principal
Sub-vista activa del módulo.

### Panel derecho
Resumen persistente de la sucursal seleccionada.

---

# 10. Panel derecho persistente: resumen de la sucursal

## Función
Mantener visible el contexto rápido de la sede seleccionada mientras se navega por el módulo.

## Contenedor sugerido
**ScrollPane** con un **VBox** interno.

## Bloques internos del panel derecho
1. Identidad de la sucursal
2. Responsable y contacto
3. Capacidades habilitadas
4. Estado operativo
5. Acciones rápidas

## 10.1. Bloque: Identidad de la sucursal

### Campos visibles
- Sucursal
- Ciudad
- Dirección

### Seeds
- **Sucursal: Matriz Centro**
- **Ciudad: Guayaquil**
- **Dirección: Av. Principal y calle comercial**

## 10.2. Bloque: Responsable y contacto

### Campos visibles
- Responsable
- Teléfono
- Horario operativo

### Seeds
- **Responsable: Laura Gómez**
- **Teléfono: 04 600 0010**
- **Horario operativo: 08:00 - 18:00**

## 10.3. Bloque: Capacidades habilitadas

### Campos visibles
- Caja habilitada
- Inventario propio
- Entrega habilitada
- Agenda habilitada

### Seeds
- **Caja habilitada: Sí**
- **Inventario propio: Sí**
- **Entrega habilitada: Sí**
- **Agenda habilitada: Sí**

## 10.4. Bloque: Estado operativo

### Campos visibles
- Estado actual
- Observación operativa

### Seeds
- **Estado actual: Operativa**
- **Observación operativa: Flujo estable con atención, caja e inventario activos**

## 10.5. Bloque: Acciones rápidas

### Botones oficiales
- **Abrir perfil**
- **Editar sucursal**
- **Ver inventario**
- **Ver caja**
- **Ver agenda**

### Tooltips exactos
- Abrir perfil: **Abrir la ficha completa de la sucursal seleccionada**
- Editar sucursal: **Modificar los datos base de la sucursal**
- Ver inventario: **Abrir el contexto de inventario de esta sede**
- Ver caja: **Abrir el contexto económico de esta sede**
- Ver agenda: **Abrir el contexto operativo de agenda de esta sede**

---

# 11. Sub-vista 1: Directorio de sucursales

## Función
Servir como listado principal de sedes registradas y punto de entrada al módulo.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: pequeño resumen del directorio
- **center**: **TableView** principal
- **bottom**: resumen de resultados visibles

## 11.1. Franja superior del directorio

### Contenido
- Label: **Directorio de sucursales**
- Label secundario: **3 sedes visibles**

### Tooltips
- Directorio de sucursales: **Sucursales visibles según los filtros actuales**
- 3 sedes visibles: **Cantidad de sedes mostradas en el directorio**

## 11.2. TableView principal

### Columnas oficiales
- Sucursal
- Ciudad
- Responsable
- Horario
- Estado
- Servicios

### Seeds oficiales
1. Matriz Centro | Guayaquil | Laura Gómez | 08:00 - 18:00 | Operativa | Caja, Inventario, Entrega, Agenda
2. Norte Mall | Guayaquil | Carlos Mendoza | 10:00 - 20:00 | Activa | Caja, Inventario, Entrega
3. Sur Express | Guayaquil | Ana Vera | 09:00 - 17:00 | Bajo observación | Caja, Agenda

### Tooltip de la tabla
**Seleccione una sucursal para ver su perfil, operación local y comparativos**

### Estado vacío
**No hay sucursales visibles con los filtros actuales**

## 11.3. Pie del listado

### Textos exactos
- **Mostrando 3 de 3 sucursales**
- **Ordenado por estado y nombre**

### Tooltips
- Mostrando 3 de 3 sucursales: **Resumen de sedes visibles en el directorio actual**
- Ordenado por estado y nombre: **Criterio de ordenamiento aplicado al directorio**

---

# 12. Sub-vista 2: Perfil operativo de sucursal

## Función
Mostrar la ficha completa y operativa de la sucursal seleccionada.

## Contenedor principal sugerido
**ScrollPane** con un **VBox** interno y bloques en **GridPane**.

### Estructura interna
1. Identidad institucional
2. Contacto local
3. Horario y operación
4. Servicios habilitados
5. Observaciones locales

## 12.1. Bloque: Identidad institucional

### Campos visibles
- Nombre de sucursal
- Código interno
- Ciudad
- Dirección

### Seeds
- Nombre de sucursal: **Matriz Centro**
- Código interno: **SC-001**
- Ciudad: **Guayaquil**
- Dirección: **Av. Principal y calle comercial**

## 12.2. Bloque: Contacto local

### Campos visibles
- Teléfono
- Correo local
- Responsable

### Seeds
- Teléfono: **04 600 0010**
- Correo local: **matriz@opticamanager.local**
- Responsable: **Laura Gómez**

## 12.3. Bloque: Horario y operación

### Campos visibles
- Horario operativo
- Apertura sábados
- Recepción de trabajos

### Seeds
- Horario operativo: **08:00 - 18:00**
- Apertura sábados: **Sí**
- Recepción de trabajos: **Sí**

## 12.4. Bloque: Servicios habilitados

### Campos visibles
- Caja habilitada
- Inventario propio
- Entrega habilitada
- Agenda habilitada
- Laboratorio local

### Seeds
- Caja habilitada: **Sí**
- Inventario propio: **Sí**
- Entrega habilitada: **Sí**
- Agenda habilitada: **Sí**
- Laboratorio local: **No**

## 12.5. Bloque: Observaciones locales

### Seed
**Sucursal principal con mayor flujo comercial y retiro frecuente de trabajos terminados**

### Botones oficiales del submódulo
- **Guardar perfil**
- **Editar operación**
- **Desactivar sucursal**

### Tooltips
- Guardar perfil: **Guardar los cambios realizados en la ficha operativa**
- Editar operación: **Modificar capacidades y parámetros de trabajo de la sede**
- Desactivar sucursal: **Marcar la sucursal como no operativa**

### Estado vacío
**Seleccione una sucursal para ver su perfil operativo**

---

# 13. Sub-vista 3: Personal y permisos por sede

## Función
Consultar qué usuarios trabajan en la sucursal y qué nivel de acceso poseen dentro del sistema.

## Contenedor principal sugerido
**SplitPane** horizontal

### Panel izquierdo
**TableView** de usuarios asignados

### Panel derecho
Detalle de rol y permisos del usuario seleccionado

## 13.1. TableView principal

### Columnas oficiales
- Usuario
- Rol
- Estado
- Acceso principal

### Seeds oficiales
1. laura.gomez@opticamanager.local | Administradora de sede | Activo | Caja, Reportes, Agenda
2. ventas.centro@opticamanager.local | Asesor de ventas | Activo | Venta óptica, Seguimiento
3. tecnico.centro@opticamanager.local | Técnico óptico | Activo | Laboratorio, Entregas

### Tooltip de la tabla
**Usuarios asignados a la sucursal seleccionada**

## 13.2. Detalle de permisos

### Campos visibles
- Nombre de usuario
- Rol
- Acceso a caja
- Acceso a inventario
- Acceso a agenda
- Acceso a reportes
- Acceso a configuración local

### Seeds
- Nombre de usuario: **laura.gomez@opticamanager.local**
- Rol: **Administradora de sede**
- Acceso a caja: **Sí**
- Acceso a inventario: **Sí**
- Acceso a agenda: **Sí**
- Acceso a reportes: **Sí**
- Acceso a configuración local: **Sí**

### Botones oficiales del submódulo
- **Asignar usuario**
- **Editar permisos**
- **Desvincular usuario**

### Tooltips
- Asignar usuario: **Asignar un usuario existente a la sucursal seleccionada**
- Editar permisos: **Modificar el alcance operativo del usuario en esta sede**
- Desvincular usuario: **Retirar al usuario de la sucursal actual**

### Estado vacío
**No hay usuarios asignados a esta sucursal**

---

# 14. Sub-vista 4: Inventario y stock por sucursal

## Función
Mostrar la lectura local del inventario de la sede seleccionada.

## Contenedor principal sugerido
**BorderPane** con indicadores superiores y **TableView** central.

### Estructura interna
1. Resumen local de stock
2. Tabla de productos clave
3. Alertas de stock

## 14.1. Resumen local de stock

### Campos visibles
- Productos disponibles
- Bajo stock
- Agotados
- Transferencias pendientes

### Seeds
- Productos disponibles: **128**
- Bajo stock: **11**
- Agotados: **4**
- Transferencias pendientes: **2**

## 14.2. TableView principal

### Columnas oficiales
- Referencia
- Nombre
- Categoría
- Stock
- Estado
- Observación

### Seeds oficiales
1. MZ-201 | Armazón clásico | Monturas | 2 | Bajo stock | Alta salida en Matriz Centro
2. LN-156-AR | Monofocal 1.56 | Lentes | 14 | Disponible | Rotación constante
3. ACC-021 | Plaquetas de silicona estándar | Accesorios | 0 | Agotado | Requiere reposición

### Tooltip de la tabla
**Inventario local y alertas de stock de la sucursal seleccionada**

### Botones oficiales del submódulo
- **Abrir inventario**
- **Solicitar transferencia**
- **Marcar reposición**

### Tooltips
- Abrir inventario: **Abrir el módulo Inventario con contexto de esta sucursal**
- Solicitar transferencia: **Preparar un movimiento entre sedes para el producto seleccionado**
- Marcar reposición: **Registrar que el producto será reabastecido**

### Estado vacío
**No hay datos de inventario local suficientes para esta sucursal**

---

# 15. Sub-vista 5: Agenda, atención y flujo local

## Función
Mostrar la capacidad operativa de la sucursal respecto a citas, horarios y actividad de atención.

## Contenedor principal sugerido
**BorderPane** con KPI superiores y **TableView** central.

### Estructura interna
1. Resumen de atención local
2. Tabla de actividad de agenda
3. Observaciones operativas

## 15.1. Resumen de atención local

### Campos visibles
- Citas del día
- Utilización local
- No-shows
- Tipos de atención habilitados

### Seeds
- Citas del día: **18**
- Utilización local: **82%**
- No-shows: **1**
- Tipos de atención habilitados: **4**

## 15.2. TableView principal

### Columnas oficiales
- Indicador
- Valor
- Estado
- Observación

### Seeds oficiales
1. Utilización de agenda | 82% | En meta | Buena ocupación diaria
2. Citas efectivas | 17 | En meta | Flujo estable
3. No-shows | 1 | Bajo observación | Ligeramente controlado
4. Recall convertido en cita | 3 | En meta | Buena respuesta local

### Botones oficiales del submódulo
- **Abrir agenda**
- **Editar horario**
- **Ver personal**

### Tooltips
- Abrir agenda: **Abrir el módulo Agenda con la sucursal seleccionada**
- Editar horario: **Modificar el horario operativo de atención de la sede**
- Ver personal: **Consultar el personal local vinculado a la atención**

### Estado vacío
**No hay datos de agenda suficientes para esta sucursal**

---

# 16. Sub-vista 6: Caja y desempeño por sucursal

## Función
Mostrar una lectura económica y de rendimiento comercial de la sede seleccionada.

## Contenedor principal sugerido
**BorderPane** con KPI superiores y **TableView** central.

### Estructura interna
1. Resumen económico local
2. Tabla de desempeño
3. Observaciones comerciales

## 16.1. Resumen económico local

### Campos visibles
- Cobrado del período
- Ticket promedio
- Órdenes abiertas
- Saldos pendientes

### Seeds
- Cobrado del período: **$ 3,240.00**
- Ticket promedio: **$ 98.20**
- Órdenes abiertas: **9**
- Saldos pendientes: **$ 118.00**

## 16.2. TableView principal

### Columnas oficiales
- Indicador
- Valor
- Estado
- Observación

### Seeds oficiales
1. Ticket promedio | $ 98.20 | En meta | Venta media sólida
2. Cobros pendientes | $ 118.00 | Bajo observación | Revisar 2 casos vencidos
3. Órdenes activas | 9 | En meta | Flujo normal
4. Entregas con saldo | 1 | Bajo observación | Requiere coordinación con caja

### Botones oficiales del submódulo
- **Abrir caja**
- **Abrir entregas**
- **Exportar desempeño**

### Tooltips
- Abrir caja: **Abrir el módulo Caja con la sucursal seleccionada**
- Abrir entregas: **Abrir el módulo Entregas vinculado a esta sede**
- Exportar desempeño: **Exportar el resumen económico de la sucursal**

### Estado vacío
**No hay datos económicos suficientes para esta sucursal**

---

# 17. Sub-vista 7: Comparativo entre sucursales

## Función
Comparar sedes desde una lectura gerencial simple y útil.

## Contenedor principal sugerido
**BorderPane** con indicadores superiores y **TableView** comparativa.

### Estructura interna
1. Resumen comparativo
2. Tabla entre sedes
3. Lectura gerencial breve

## 17.1. Resumen comparativo

### Campos visibles
- Mejor ticket promedio
- Más ventas del período
- Mayor stock crítico
- Mayor atraso operativo

### Seeds
- Mejor ticket promedio: **Norte Mall**
- Más ventas del período: **Matriz Centro**
- Mayor stock crítico: **Sur Express**
- Mayor atraso operativo: **Sur Express**

## 17.2. TableView comparativa

### Columnas oficiales
- Sucursal
- Ventas
- Ticket promedio
- Stock crítico
- Recalls pendientes
- Retrasos
- Estado general

### Seeds oficiales
1. Matriz Centro | $ 3,240.00 | $ 98.20 | 11 | 8 | 1 | Operativa
2. Norte Mall | $ 2,890.00 | $ 102.40 | 6 | 5 | 0 | Activa
3. Sur Express | $ 1,420.00 | $ 76.80 | 14 | 5 | 3 | Bajo observación

### Tooltip de la tabla
**Comparación simple de desempeño, alertas y situación entre sucursales**

### Botones oficiales del submódulo
- **Abrir sucursal**
- **Exportar comparativo**
- **Marcar revisión**

### Tooltips
- Abrir sucursal: **Abrir el perfil de la sucursal seleccionada**
- Exportar comparativo: **Exportar la comparación visible entre sedes**
- Marcar revisión: **Señalar la sucursal para revisión operativa o gerencial**

### Estado vacío
**No hay suficientes sucursales activas para generar un comparativo**

---

# 18. Formulario conceptual: Nueva sucursal

## Función
Permitir registrar una nueva sede dentro de la arquitectura del módulo.

## Contenedor sugerido
Vista interna o diálogo amplio controlado. Para esta maqueta conviene resolverlo dentro del mismo módulo.

## Estructura sugerida
**ScrollPane** con **VBox** y bloques en **GridPane**.

### Grupos de campos
1. Identidad de sede
2. Contacto
3. Horario
4. Capacidades habilitadas

### Campos mínimos
- Nombre de sucursal
- Código interno
- Ciudad
- Dirección
- Teléfono
- Responsable
- Horario operativo
- Caja habilitada
- Inventario propio
- Entrega habilitada
- Agenda habilitada

### Botones finales
- **Guardar sucursal**
- **Cancelar**

### Tooltips
- Guardar sucursal: **Registrar la nueva sede dentro del sistema**
- Cancelar: **Cerrar el registro de sucursal sin guardar**

---

# 19. Seed data oficial del módulo Sucursales

## Sucursales
- Matriz Centro
- Norte Mall
- Sur Express

## Responsables
- Laura Gómez
- Carlos Mendoza
- Ana Vera

## Ciudades
- Guayaquil

## Códigos internos
- SC-001
- SC-002
- SC-003

## Estados usados en el módulo
- Activa
- Inactiva
- Operativa
- Bajo observación
- Caja habilitada
- Entrega habilitada
- Agenda habilitada
- Con stock crítico
- Con retrasos

---

# 20. Textos oficiales del módulo Sucursales

## Títulos y labels principales
- Sucursales
- Control de sedes, personal, inventario local, atención, caja y desempeño por ubicación
- Buscar
- Estado
- Servicio
- Ciudad
- Solo con alertas operativas
- Directorio de sucursales
- Perfil operativo de sucursal
- Personal y permisos por sede
- Inventario y stock por sucursal
- Agenda, atención y flujo local
- Caja y desempeño por sucursal
- Comparativo entre sucursales
- Sucursal
- Responsable
- Horario operativo
- Ciudad
- Dirección
- Caja habilitada
- Inventario propio
- Entrega habilitada
- Agenda habilitada
- Estado actual
- Observación operativa
- Ticket promedio
- Órdenes abiertas
- Saldos pendientes

## Botones oficiales
- Actualizar sucursales
- Exportar sedes
- Nueva sucursal
- Limpiar filtros
- Abrir perfil
- Editar sucursal
- Ver inventario
- Ver caja
- Ver agenda
- Guardar perfil
- Editar operación
- Desactivar sucursal
- Asignar usuario
- Editar permisos
- Desvincular usuario
- Abrir inventario
- Solicitar transferencia
- Marcar reposición
- Abrir agenda
- Editar horario
- Ver personal
- Abrir caja
- Abrir entregas
- Exportar desempeño
- Abrir sucursal
- Exportar comparativo
- Marcar revisión
- Guardar sucursal
- Cancelar

## Placeholders
- Sucursal, responsable, ciudad o referencia

## Empty states
- No hay sucursales visibles con los filtros actuales
- Seleccione una sucursal para ver su perfil operativo
- No hay usuarios asignados a esta sucursal
- No hay datos de inventario local suficientes para esta sucursal
- No hay datos de agenda suficientes para esta sucursal
- No hay datos económicos suficientes para esta sucursal
- No hay suficientes sucursales activas para generar un comparativo

---

# 21. Reglas visuales específicas del módulo Sucursales

- el Directorio de sucursales debe sentirse como la vista principal del módulo
- el panel derecho debe mantener contexto rápido de la sede sin saturarse
- Perfil operativo de sucursal debe verse institucional y operativo a la vez
- Personal y permisos por sede debe sentirse sobrio y administrativo
- Inventario y stock por sucursal debe comunicar lectura local, no global
- Agenda, atención y flujo local debe reflejar capacidad operativa real de la sede
- Caja y desempeño por sucursal debe verse económico pero no contable
- Comparativo entre sucursales debe verse gerencial y directo
- no abusar de formas redondeadas ni de tarjetas decorativas
- la prioridad debe ser comprender la sede como unidad operativa del negocio

---

# 22. Relación del módulo Sucursales con otros módulos

Sucursales debe conectarse con:
- Inventario, porque muchas existencias y alertas son por sede
- Agenda, porque la atención y la capacidad operativa se organizan por sucursal
- Caja, porque el cobro y cierre económico dependen de la sede
- Entregas, porque la entrega final ocurre en una sede concreta
- Usuarios y roles, porque cada persona puede estar vinculada a una sucursal
- Reportes, porque casi todos los indicadores pueden leerse por sede
- Configuración, porque varios parámetros base de sucursal nacen allí
- Inicio, porque el panel principal puede resumir alertas o resultados por sede

---

# 23. Cierre del módulo Sucursales

Este módulo debe transmitir que la óptica puede operar varias sedes con control real, identidad local y lectura comparativa. No se trata solo de guardar direcciones, sino de entender cada sede como una unidad que atiende, cobra, entrega, administra stock y rinde de forma distinta. El sistema debe dejar claro qué hace cada sucursal, quién trabaja allí, qué capacidades tiene y cómo está funcionando.

La complejidad correcta del módulo está en que cada sub-vista resuelve una parte clara de la vida operativa de la sede sin convertir la experiencia en una herramienta corporativa excesivamente pesada.