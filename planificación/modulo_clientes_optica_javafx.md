# Lienzo del módulo Clientes para sistema de óptica en JavaFX

## 1. Identidad del módulo

### Nombre oficial del módulo en el menú
**Clientes**

### Texto visible del botón del sidebar
**Clientes**

### Tooltip del botón del sidebar
**Consultar fichas, historial, recetas, órdenes y seguimiento de clientes**

### Ícono conceptual
Usuario, ficha de cliente o libreta de atención.

### Título visible en pantalla
**Clientes**

### Subtítulo visible en pantalla
**Fichas integrales de clientes, historial y seguimiento comercial y operativo**

### Tipo de módulo
Módulo maestro-detalle con sub-vistas internas del cliente seleccionado.

### Objetivo del módulo
Permitir que la óptica consulte, registre y mantenga una ficha unificada por cliente, conectando datos personales, historial de atención, recetas, órdenes, entregas y seguimiento.

---

## 2. Rol del módulo dentro de la arquitectura GUI

Clientes no debe sentirse como una agenda de contactos ni como una simple tabla de nombres. Debe verse como un centro de contexto del negocio.

### Regla arquitectónica principal
Cuando el usuario entre al módulo Clientes, la región central principal del sistema debe reemplazarse por una vista completa llamada conceptualmente:
**ClientesModuleView**

### Estructura interna limpia del módulo
La vista Clientes debe dividirse en:
- encabezado del módulo
- barra de filtros y búsqueda
- cuerpo principal en maestro-detalle
- subnavegación interna del cliente seleccionado
- panel de ficha o detalle extendido

### Filosofía de implementación
La experiencia debe evitar abrir ventanas nuevas para tareas normales. La consulta, selección y navegación del cliente deben ocurrir dentro de la misma vista.

### Regla de arquitectura limpia
Debe existir una región estable para el listado principal y otra región estable para el contexto del cliente seleccionado. Dentro del panel derecho o detalle, la información interna del cliente puede cambiar mediante sub-vistas.

### Región conceptual que se reescribe
Dentro del panel de detalle, debe existir una región interna conceptual como:
**clienteDetailContentHostPane**

Ese contenedor puede ser un **StackPane** o el centro de un **BorderPane** secundario. Allí se cargan las sub-vistas del cliente:
- Ficha general
- Historial
- Recetas
- Órdenes y entregas
- Seguimiento

---

## 3. Sub-vistas oficiales del módulo Clientes

## Sub-vistas definidas
1. **Ficha general**
2. **Historial**
3. **Recetas**
4. **Órdenes y entregas**
5. **Seguimiento**

## Orden de prioridad
1. Ficha general
2. Historial
3. Recetas
4. Órdenes y entregas
5. Seguimiento

## Vista por defecto al abrir Clientes
**Ficha general** del cliente seleccionado.

## Condición inicial del módulo
Si todavía no hay selección, el sistema debe mostrar una indicación clara en el panel de detalle.

Texto exacto del estado inicial:
**Seleccione un cliente para ver su información**

Motivo: el foco natural del módulo es escoger una persona y luego navegar dentro de su ficha.

---

## 4. Estructura visual general del módulo Clientes

## Contenedor raíz del módulo
**BorderPane**

### Distribución general del BorderPane principal
- **top**: encabezado del módulo + filtros
- **center**: cuerpo principal maestro-detalle
- **right**: no aplica como región adicional, porque el detalle ya vive dentro del cuerpo principal
- **left**: no aplica, porque el sidebar general ya existe fuera del módulo
- **bottom**: no aplica

### Decisión espacial clave
El cuerpo principal debe organizarse con un **SplitPane** horizontal.

### Distribución del SplitPane
- panel izquierdo: listado principal de clientes
- panel derecho: detalle del cliente seleccionado

### Proporción recomendada
- panel izquierdo: 40%
- panel derecho: 60%

### Regla de estabilidad visual
La lista de clientes debe mantenerse siempre visible mientras el usuario navega entre las sub-vistas del cliente. Esto hace que la GUI se sienta limpia y predecible.

---

## 5. Encabezado del módulo Clientes

## Contenedor sugerido
**VBox** en la región top del BorderPane principal.

### Subestructura del encabezado
1. franja superior con título y acciones principales
2. franja inferior con filtros y búsqueda

---

## 6. Franja superior del encabezado

## Contenedor sugerido
**HBox**

### Zona izquierda
Un **VBox** con:
- Label principal: **Clientes**
- Label secundario: **Fichas integrales de clientes, historial y seguimiento comercial y operativo**

### Zona derecha
Un **HBox** con:
- Label: **Sucursal activa**
- ComboBox de sucursal
- Button primario: **Nuevo cliente**
- Button secundario: **Actualizar listado**

## Controles exactos

### Label principal
- Control: **Label**
- Texto exacto: **Clientes**
- Tooltip: no necesita

### Label secundario
- Control: **Label**
- Texto exacto: **Fichas integrales de clientes, historial y seguimiento comercial y operativo**
- Tooltip: no necesita

### Label sucursal
- Control: **Label**
- Texto exacto: **Sucursal activa**
- Tooltip: **Seleccione la sede cuyos clientes desea consultar**

### Selector de sucursal
- Control: **ComboBox**
- Ítems de semilla:
  - Todas las sucursales
  - Matriz Centro
  - Norte Mall
- Valor inicial: **Todas las sucursales**
- Tooltip: **Filtrar clientes por sucursal de atención o registro**

### Botón principal
- Control: **Button**
- Texto exacto: **Nuevo cliente**
- Tooltip: **Registrar una nueva ficha de cliente o paciente**
- Estilo: acción principal

### Botón secundario
- Control: **Button**
- Texto exacto: **Actualizar listado**
- Tooltip: **Recargar el listado visible de clientes**
- Estilo: secundario

---

## 7. Franja inferior: filtros y búsqueda

## Contenedor sugerido
**FlowPane**

## Función de la franja
Permitir localizar clientes por nombre, contacto, estado, sucursal o condición operativa sin salir del módulo.

## Controles oficiales de filtro

### Búsqueda rápida
- Control: **TextField**
- Label asociado: **Buscar**
- Placeholder exacto: **Nombre, cédula, teléfono o código**
- Tooltip: **Buscar clientes por nombre, documento, teléfono o referencia**

### Filtro por estado del cliente
- Control: **ComboBox**
- Label asociado: **Estado**
- Ítems de semilla:
  - Todos
  - Activo
  - Inactivo
  - Con saldo pendiente
  - Requiere seguimiento
- Valor inicial: **Todos**
- Tooltip: **Filtrar fichas según estado general del cliente**

### Filtro por última visita
- Control: **ComboBox**
- Label asociado: **Última visita**
- Ítems de semilla:
  - Todas
  - Este mes
  - Últimos 3 meses
  - Últimos 6 meses
  - Sin visitas recientes
- Valor inicial: **Todas**
- Tooltip: **Filtrar según la fecha de la última atención o compra**

### Filtro por receta
- Control: **ComboBox**
- Label asociado: **Receta**
- Ítems de semilla:
  - Todas
  - Vigente
  - Vencida
  - Sin receta registrada
- Valor inicial: **Todas**
- Tooltip: **Filtrar clientes por vigencia de receta asociada**

### Botón limpiar
- Control: **Button**
- Texto exacto: **Limpiar filtros**
- Tooltip: **Restablecer la búsqueda y todos los filtros del módulo**

---

## 8. Cuerpo principal: maestro-detalle

## Contenedor sugerido
**SplitPane** horizontal

### Panel izquierdo
Listado principal de clientes.

### Panel derecho
Fichero extendido del cliente seleccionado.

### Regla visual
El usuario siempre debe poder ver el listado mientras consulta el detalle. Eso hace que el módulo se sienta robusto y de escritorio.

---

# 9. Panel izquierdo: listado principal de clientes

## Función
Permitir encontrar rápidamente una ficha y cambiar de contexto sin perder la estructura del módulo.

## Contenedor sugerido
**BorderPane** interno

### Distribución interna
- **top**: pequeño resumen del listado
- **center**: **TableView** principal
- **bottom**: resumen de resultados o paginación visual simple, si se quiere simular

## 9.1. Franja superior del listado

### Contenedor sugerido
**HBox**

### Contenido
- Label: **Listado de clientes**
- Label secundario con conteo

### Textos exactos
- **Listado de clientes**
- **128 registros visibles**

### Tooltips
- Listado de clientes: **Fichas de clientes registradas en el sistema**
- Conteo: **Cantidad de clientes visibles según los filtros actuales**

## 9.2. TableView principal

### Control principal
**TableView**

### Columnas oficiales
- Cliente
- Código
- Teléfono
- Última visita
- Receta
- Estado
- Sucursal

### Orden visual recomendado
1. Cliente
2. Código
3. Teléfono
4. Última visita
5. Receta
6. Estado
7. Sucursal

### Jerarquía de columnas
La columna **Cliente** debe tener más peso visual que las demás.

### Seeds oficiales del listado
1. Sofía Ramírez | CL-00124 | 099 123 4567 | 12/04/2026 | Vigente | Activo | Matriz Centro
2. Juan Cedeño | CL-00125 | 098 222 3344 | 03/04/2026 | Sin receta | Requiere seguimiento | Norte Mall
3. Carmen López | CL-00126 | 097 112 7788 | 28/03/2026 | Vigente | Activo | Matriz Centro
4. Luis Andrade | CL-00127 | 096 889 2233 | 17/02/2026 | Vencida | Con saldo pendiente | Matriz Centro
5. María León | CL-00128 | 095 555 4411 | 10/04/2026 | Vigente | Activo | Norte Mall
6. Ana Vera | CL-00129 | 094 121 2121 | 21/03/2026 | Vigente | Activo | Matriz Centro
7. Carlos Mendoza | CL-00130 | 093 456 9876 | 02/01/2026 | Vencida | Requiere seguimiento | Norte Mall
8. Diana Vélez | CL-00131 | 092 707 8080 | 11/04/2026 | Sin receta | Activo | Matriz Centro

### Tooltip de la tabla
**Seleccione un cliente para ver su ficha, historial, recetas y seguimiento**

### Regla de selección
Una fila seleccionada debe refrescar el contenido del panel derecho sin abandonar el módulo.

### Estado vacío
Texto exacto:
**No hay clientes que coincidan con los filtros actuales**

### Acciones por fila
No hace falta recargar la tabla con muchas acciones. Basta con selección de fila y, como máximo, una columna discreta con acción contextual.

### Acción contextual opcional
- Ícono o botón pequeño: **Abrir ficha**
- Tooltip: **Ver la ficha completa del cliente**

## 9.3. Pie del listado

### Contenedor sugerido
**HBox**

### Textos exactos
- **Mostrando 8 de 128 clientes**
- **Ordenado por última visita**

### Tooltips
- Mostrando 8 de 128 clientes: **Resumen del listado visible en pantalla**
- Ordenado por última visita: **Criterio actual de ordenamiento del listado**

---

# 10. Panel derecho: detalle del cliente seleccionado

## Función
Mostrar una ficha rica, navegable y consistente del cliente, sin perder el vínculo con el listado.

## Contenedor sugerido
**BorderPane** interno

### Distribución interna
- **top**: cabecera resumida del cliente + acciones principales
- **center**: sub-vista activa del cliente dentro de un **StackPane** o centro de BorderPane
- **bottom**: opcional, no necesario inicialmente

---

## 11. Cabecera del cliente seleccionado

## Contenedor sugerido
**VBox**

### Bloque superior de identidad
Un **HBox** con:
- ícono o avatar discreto del cliente
- datos principales del cliente en un **VBox**
- acciones rápidas a la derecha

### Datos principales visibles
- Nombre completo
- Código del cliente
- Estado general
- Sucursal habitual
- Última visita

### Seeds visibles de ejemplo
- **Sofía Ramírez**
- **CL-00124**
- **Activo**
- **Matriz Centro**
- **Última visita: 12/04/2026**

### Tooltips de cabecera
- Nombre: no necesita
- Código: **Código interno de referencia del cliente**
- Estado general: **Situación actual de la ficha del cliente**
- Sucursal habitual: **Sede donde el cliente suele ser atendido**
- Última visita: **Fecha de la atención, compra o revisión más reciente**

### Acciones rápidas de cabecera

#### Botón 1
- Control: **Button**
- Texto exacto: **Editar cliente**
- Tooltip: **Modificar los datos de la ficha seleccionada**
- Tipo: secundario

#### Botón 2
- Control: **Button**
- Texto exacto: **Nueva receta**
- Tooltip: **Registrar una nueva receta para este cliente**
- Tipo: secundario

#### Botón 3
- Control: **Button**
- Texto exacto: **Nueva venta óptica**
- Tooltip: **Iniciar una nueva orden de venta para este cliente**
- Tipo: principal

#### Botón 4
- Control: **Button**
- Texto exacto: **Agendar cita**
- Tooltip: **Registrar una nueva cita para este cliente**
- Tipo: secundario

---

## 12. Subnavegación interna del cliente seleccionado

## Contenedor sugerido
**HBox** con **ToggleButton** dentro de un **ToggleGroup**

### Sub-vistas visibles en la barra
- Ficha general
- Historial
- Recetas
- Órdenes y entregas
- Seguimiento

### Tooltips exactos
- Ficha general: **Ver datos principales y contacto del cliente**
- Historial: **Consultar visitas, compras y movimientos recientes**
- Recetas: **Ver recetas registradas para este cliente**
- Órdenes y entregas: **Consultar trabajos, estados y entregas asociadas**
- Seguimiento: **Ver observaciones, recordatorios y acciones pendientes**

### Regla visual
La sub-vista activa debe quedar clara, pero sin llamar más la atención que la ficha del cliente.

---

# 13. Sub-vista principal: Ficha general

## Función
Presentar los datos nucleares del cliente de forma ordenada y rápida de leer.

## Contenedor sugerido
**ScrollPane** con un **VBox** interno.

### Estructura recomendada
La ficha debe dividirse en bloques verticales. Cada bloque puede ser un **VBox** con título y contenido.

## Bloques internos oficiales
1. Datos personales
2. Contacto
3. Datos ópticos rápidos
4. Situación comercial resumida
5. Observaciones

---

## 13.1. Bloque: Datos personales

### Contenedor sugerido
**GridPane**

### Campos visibles
- Nombre completo
- Documento
- Fecha de nacimiento
- Edad
- Género
- Código interno

### Seeds de ejemplo
- Nombre completo: **Sofía Ramírez**
- Documento: **0912345678**
- Fecha de nacimiento: **26/08/2000**
- Edad: **25 años**
- Género: **Femenino**
- Código interno: **CL-00124**

### Tooltips
- Documento: **Número de documento registrado en la ficha del cliente**
- Código interno: **Referencia interna usada por el sistema**

---

## 13.2. Bloque: Contacto

### Contenedor sugerido
**GridPane**

### Campos visibles
- Teléfono principal
- Correo electrónico
- Dirección
- Ciudad
- Contacto alterno

### Seeds de ejemplo
- Teléfono principal: **099 123 4567**
- Correo electrónico: **sofia.ramirez@email.com**
- Dirección: **Cdla. La Garzota, Mz. 12**
- Ciudad: **Guayaquil**
- Contacto alterno: **099 765 4321**

### Tooltips
- Teléfono principal: **Número principal de contacto del cliente**
- Correo electrónico: **Correo registrado para contacto y recordatorios**
- Contacto alterno: **Número secundario de referencia**

---

## 13.3. Bloque: Datos ópticos rápidos

### Contenedor sugerido
**GridPane**

### Campos visibles
- Última receta
- Estado de receta
- Uso principal
- PD registrada
- Observación técnica breve

### Seeds de ejemplo
- Última receta: **12/04/2026**
- Estado de receta: **Vigente**
- Uso principal: **Lentes de uso diario**
- PD registrada: **62 mm**
- Observación técnica breve: **Refiere fatiga visual al final de la jornada**

### Tooltips
- Estado de receta: **Indica si la receta asociada sigue vigente o requiere renovación**
- PD registrada: **Distancia pupilar registrada en la ficha del cliente**

---

## 13.4. Bloque: Situación comercial resumida

### Contenedor sugerido
**HBox** o **GridPane** de pequeños resúmenes

### Campos visibles
- Última compra
- Saldo pendiente
- Órdenes activas
- Entregas pendientes

### Seeds de ejemplo
- Última compra: **10/04/2026**
- Saldo pendiente: **$ 0.00**
- Órdenes activas: **1**
- Entregas pendientes: **1**

### Tooltips
- Saldo pendiente: **Monto actualmente pendiente de pago**
- Órdenes activas: **Trabajos aún no cerrados para este cliente**

---

## 13.5. Bloque: Observaciones

### Contenedor sugerido
**VBox** con **TextArea** de solo lectura o Label multilínea simulada

### Texto seed
**Cliente frecuente. Prefiere atención en jornada matutina y suele retirar en sucursal Matriz Centro. Solicita aviso previo cuando el trabajo esté listo.**

### Tooltip
**Observaciones internas de atención y continuidad del cliente**

---

# 14. Sub-vista: Historial

## Función
Mostrar una vista cronológica de interacciones relevantes del cliente.

## Contenedor sugerido
**TableView**

### Columnas oficiales
- Fecha
- Tipo
- Referencia
- Detalle
- Estado
- Sucursal

### Seeds oficiales
1. 12/04/2026 | Cita | CT-209 | Examen visual | Atendida | Matriz Centro
2. 12/04/2026 | Receta | RC-088 | Receta registrada | Vigente | Matriz Centro
3. 10/04/2026 | Venta | OV-124 | Orden de lentes completos | En proceso | Matriz Centro
4. 03/03/2026 | Entrega | ET-041 | Entrega de montura | Completada | Norte Mall
5. 18/01/2026 | Seguimiento | SG-011 | Recordatorio de control | Cerrado | Matriz Centro

### Botones recomendados
- **Ver movimiento**
- **Exportar historial**

### Tooltips
- Ver movimiento: **Abrir el detalle del registro seleccionado**
- Exportar historial: **Exportar el historial visible del cliente**

### Estado vacío
**Todavía no hay movimientos registrados para este cliente**

---

# 15. Sub-vista: Recetas

## Función
Mostrar las recetas asociadas al cliente sin salir del módulo Clientes.

## Contenedor sugerido
**TableView** con panel inferior o lateral opcional para detalle de receta.

### Columnas oficiales
- Fecha
- Ojo derecho
- Ojo izquierdo
- Estado
- Profesional
- Observación breve

### Seeds oficiales
1. 12/04/2026 | -1.25 / -0.50 x 180 | -1.00 / -0.25 x 170 | Vigente | Dra. Salazar | Fatiga visual leve
2. 15/11/2025 | -1.00 / -0.25 x 180 | -0.75 / -0.25 x 170 | Vencida | Dr. Paredes | Uso diario

### Botones recomendados
- **Ver receta**
- **Nueva receta**
- **Imprimir receta**

### Tooltips
- Ver receta: **Consultar el detalle completo de la receta seleccionada**
- Nueva receta: **Registrar una nueva receta para este cliente**
- Imprimir receta: **Generar una versión imprimible de la receta**

### Estado vacío
**Este cliente no tiene recetas registradas**

---

# 16. Sub-vista: Órdenes y entregas

## Función
Mostrar trabajos comerciales y su estado operativo.

## Contenedor sugerido
**TableView**

### Columnas oficiales
- Referencia
- Fecha
- Tipo
- Estado
- Saldo
- Entrega
- Sucursal

### Seeds oficiales
1. OV-124 | 10/04/2026 | Venta óptica | En proceso | $ 0.00 | Pendiente | Matriz Centro
2. ET-041 | 03/03/2026 | Entrega | Completada | $ 0.00 | Entregada | Norte Mall
3. OV-089 | 15/11/2025 | Venta óptica | Cerrada | $ 0.00 | Entregada | Matriz Centro

### Botones recomendados
- **Ver orden**
- **Registrar entrega**
- **Abrir venta óptica**

### Tooltips
- Ver orden: **Consultar el detalle completo de la orden seleccionada**
- Registrar entrega: **Registrar la entrega del trabajo seleccionado**
- Abrir venta óptica: **Abrir el módulo de venta asociado a este cliente**

### Estado vacío
**Este cliente no tiene órdenes ni entregas registradas**

---

# 17. Sub-vista: Seguimiento

## Función
Mostrar notas internas, recordatorios, tareas de contacto y continuidad comercial.

## Contenedor sugerido
**VBox** con lista de acciones o **TableView** corta. Se recomienda **TableView** para dar seriedad administrativa.

### Columnas oficiales
- Fecha
- Tipo de seguimiento
- Descripción
- Estado
- Responsable

### Seeds oficiales
1. 13/04/2026 | Recordatorio | Avisar cuando el trabajo esté listo | Pendiente | Asesor Molina
2. 02/04/2026 | Llamada | Confirmar interés en lentes blue cut | Cerrado | Técnico Rivera
3. 18/01/2026 | Control anual | Recomendar revisión en seis meses | Pendiente | Dra. Salazar

### Botones recomendados
- **Nuevo seguimiento**
- **Marcar resuelto**
- **Agendar cita**

### Tooltips
- Nuevo seguimiento: **Registrar una nueva observación o tarea de seguimiento**
- Marcar resuelto: **Cerrar la acción de seguimiento seleccionada**
- Agendar cita: **Programar una nueva cita para el cliente**

### Estado vacío
**No hay acciones de seguimiento registradas para este cliente**

---

# 18. Formulario conceptual: Nuevo cliente

## Función
Registrar una nueva ficha sin sobrecargar la interfaz.

## Contenedor sugerido
Vista interna o diálogo amplio dentro de la misma arquitectura del módulo. No se recomienda una ventana compleja externa para esta maqueta.

## Estructura sugerida
**ScrollPane** con un **VBox** y bloques en **GridPane**.

### Grupos de campos
1. Datos personales
2. Contacto
3. Datos rápidos de atención
4. Observaciones iniciales

### Campos mínimos
- Nombre completo
- Documento
- Teléfono principal
- Correo electrónico
- Sucursal de registro
- Observación inicial

### Botones finales
- **Guardar cliente**
- **Cancelar**

### Tooltips
- Guardar cliente: **Crear la ficha del nuevo cliente**
- Cancelar: **Cerrar el formulario sin guardar cambios**

---

# 19. Seed data oficial del módulo Clientes

## Clientes de semilla
- Sofía Ramírez
- Juan Cedeño
- Carmen López
- Luis Andrade
- María León
- Ana Vera
- Carlos Mendoza
- Diana Vélez

## Profesionales y responsables
- Dra. Salazar
- Dr. Paredes
- Técnico Rivera
- Asesor Molina

## Sucursales
- Matriz Centro
- Norte Mall

## Estados usados en el módulo
- Activo
- Inactivo
- Con saldo pendiente
- Requiere seguimiento
- Vigente
- Vencida
- En proceso
- Completada
- Pendiente
- Cerrado

---

# 20. Textos oficiales del módulo Clientes

## Títulos y labels principales
- Clientes
- Fichas integrales de clientes, historial y seguimiento comercial y operativo
- Sucursal activa
- Buscar
- Estado
- Última visita
- Receta
- Listado de clientes
- Ficha general
- Historial
- Recetas
- Órdenes y entregas
- Seguimiento
- Seleccione un cliente para ver su información

## Labels de ficha general
- Nombre completo
- Documento
- Fecha de nacimiento
- Edad
- Género
- Código interno
- Teléfono principal
- Correo electrónico
- Dirección
- Ciudad
- Contacto alterno
- Última receta
- Estado de receta
- Uso principal
- PD registrada
- Observación técnica breve
- Última compra
- Saldo pendiente
- Órdenes activas
- Entregas pendientes
- Observaciones

## Botones oficiales
- Nuevo cliente
- Actualizar listado
- Limpiar filtros
- Editar cliente
- Nueva receta
- Nueva venta óptica
- Agendar cita
- Ver movimiento
- Exportar historial
- Ver receta
- Imprimir receta
- Ver orden
- Registrar entrega
- Abrir venta óptica
- Nuevo seguimiento
- Marcar resuelto
- Guardar cliente
- Cancelar

## Placeholders
- Nombre, cédula, teléfono o código

## Empty states
- Seleccione un cliente para ver su información
- No hay clientes que coincidan con los filtros actuales
- Todavía no hay movimientos registrados para este cliente
- Este cliente no tiene recetas registradas
- Este cliente no tiene órdenes ni entregas registradas
- No hay acciones de seguimiento registradas para este cliente

---

# 21. Reglas visuales específicas del módulo Clientes

- el listado debe sentirse administrativo y rápido
- el panel derecho debe sentirse como ficha viva, no como formulario crudo
- las sub-vistas del cliente deben mantener coherencia visual entre sí
- no abusar de cajas redondeadas ni de tarjetas infladas
- el cliente seleccionado debe sentirse claramente activo
- la información importante debe poder leerse en pocos segundos
- la ficha debe transmitir continuidad de atención y relación con el negocio
- el módulo debe dar la sensación de que la óptica conoce a sus clientes, no solo los registra

---

# 22. Relación del módulo Clientes con otros módulos

Clientes debe conectarse con:
- Agenda, porque desde allí se pueden agendar citas
- Recetas, porque la ficha concentra la historia técnica
- Venta óptica, porque un cliente puede iniciar una orden
- Órdenes y entregas, porque la ficha muestra trabajos y estados
- Seguimiento, porque el cliente puede requerir recordatorios o contactos
- Inicio, porque el panel principal resume parte de esta información
- Sucursales, porque el cliente puede atenderse en distintas sedes

---

# 23. Cierre del módulo Clientes

Este módulo debe dar la impresión de que la óptica no trabaja solo con ventas sueltas, sino con clientes identificados, historial acumulado, continuidad de atención y capacidad de seguimiento. Debe verse como una ficha central de relación con el cliente, donde convergen datos personales, información técnica, ventas, órdenes, entregas y recordatorios.

La complejidad correcta del módulo no está en meter veinte formularios, sino en hacer visible que el sistema entiende al cliente como una pieza central de toda la operación.