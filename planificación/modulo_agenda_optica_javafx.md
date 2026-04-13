# Lienzo del módulo Agenda para sistema de óptica en JavaFX

## 1. Identidad del módulo

### Nombre oficial del módulo en el menú
**Agenda**

### Texto visible del botón del sidebar
**Agenda**

### Tooltip del botón del sidebar
**Gestionar citas, disponibilidad, confirmaciones y lista de espera**

### Ícono conceptual
Calendario, reloj o agenda de atención.

### Título visible en pantalla
**Agenda**

### Subtítulo visible en pantalla
**Control diario y semanal de citas, disponibilidad y seguimiento de atención**

### Tipo de módulo
Módulo operativo con varias sub-vistas internas.

### Objetivo del módulo
Permitir que la óptica organice citas, vea disponibilidad real, controle confirmaciones, gestione cancelaciones, reprogramaciones y lista de espera, y mantenga una lectura clara de la atención por día, semana y sucursal.

---

## 2. Rol del módulo dentro de la arquitectura GUI

Agenda debe ser uno de los módulos más usados del sistema. No debe sentirse como una simple tabla ni como un calendario decorativo. Debe sentirse como una herramienta de trabajo diaria.

### Regla arquitectónica principal
Cuando el usuario entre al módulo Agenda, la región central principal del sistema debe reemplazarse por una vista completa llamada conceptualmente:
**AgendaModuleView**

### Estructura interna limpia del módulo
La vista Agenda debe dividirse en:
- encabezado del módulo
- barra de filtros rápidos
- subnavegación interna de la agenda
- región central intercambiable para sub-vistas
- panel lateral de detalle contextual

### Filosofía de implementación
No conviene abrir ventanas nuevas para cada cita ni crear una interfaz desordenada con demasiados popups. La navegación debe resolverse dentro de la misma vista, reescribiendo una región central interna según la sub-vista elegida.

### Regla de arquitectura limpia
La vista principal de Agenda debe mantenerse estable y el contenido cambiante debe vivir en una región interna conceptual como:
**agendaContentHostPane**

Ese contenedor puede ser un **StackPane** o el centro de un **BorderPane** secundario. Allí se cargan las sub-vistas:
- Día
- Semana
- Lista del día
- Lista de espera
- Confirmaciones
- Horarios y bloqueos

---

## 3. Sub-vistas oficiales del módulo Agenda

## Sub-vistas definidas
1. **Día**
2. **Semana**
3. **Lista del día**
4. **Lista de espera**
5. **Confirmaciones**
6. **Horarios y bloqueos**

## Orden de prioridad
1. Día
2. Lista del día
3. Semana
4. Lista de espera
5. Confirmaciones
6. Horarios y bloqueos

## Vista por defecto al abrir Agenda
**Día**

Motivo: es la vista más operativa, más útil para recepción y atención, y la que mejor comunica valor en una óptica real.

---

## 4. Estructura visual general del módulo Agenda

## Contenedor raíz del módulo
**BorderPane**

### Distribución general del BorderPane principal
- **top**: encabezado del módulo + filtros + subnavegación
- **center**: cuerpo principal de Agenda
- **right**: panel de detalle contextual de la cita seleccionada
- **left**: no aplica, porque el sidebar general ya existe fuera del módulo
- **bottom**: no aplica

### Decisión espacial clave
El cuerpo central del módulo debe organizarse con otro **BorderPane** o con un **HBox** principal, donde:
- la región central principal muestra la sub-vista activa
- la región derecha muestra el detalle de la cita seleccionada

### Proporción recomendada
- región central principal: 70% a 75%
- panel lateral derecho: 25% a 30%

### Regla de estabilidad visual
El panel derecho debe permanecer estable mientras el usuario cambia entre citas dentro de la sub-vista activa. Solo debería ocultarse si en alguna sub-vista no aporta valor.

---

## 5. Encabezado del módulo Agenda

## Contenedor sugerido
**VBox** en la región top del BorderPane principal.

### Subestructura del encabezado
1. franja superior con título y acciones
2. franja media con filtros rápidos
3. franja inferior con selector de sub-vistas

---

## 6. Franja superior del encabezado

## Contenedor sugerido
**HBox**

### Zona izquierda
Un **VBox** con:
- Label principal: **Agenda**
- Label secundario: **Control diario y semanal de citas, disponibilidad y seguimiento de atención**

### Zona derecha
Un **HBox** con:
- Label: **Sucursal activa**
- ComboBox de sucursal
- Button primario: **Nueva cita**
- Button secundario: **Actualizar agenda**

## Controles exactos

### Label principal
- Control: **Label**
- Texto exacto: **Agenda**
- Tooltip: no necesita

### Label secundario
- Control: **Label**
- Texto exacto: **Control diario y semanal de citas, disponibilidad y seguimiento de atención**
- Tooltip: no necesita

### Label sucursal
- Control: **Label**
- Texto exacto: **Sucursal activa**
- Tooltip: **Seleccione la sede que desea consultar en la agenda**

### Selector de sucursal
- Control: **ComboBox**
- Ítems de semilla:
  - Matriz Centro
  - Norte Mall
- Valor inicial: **Matriz Centro**
- Tooltip: **Cambiar la sucursal visible en el módulo Agenda**

### Botón principal
- Control: **Button**
- Texto exacto: **Nueva cita**
- Tooltip: **Registrar una nueva cita en la sucursal activa**
- Estilo: acción principal

### Botón secundario
- Control: **Button**
- Texto exacto: **Actualizar agenda**
- Tooltip: **Recargar las citas visibles en la agenda**
- Estilo: secundario

---

## 7. Franja media: filtros rápidos

## Contenedor sugerido
**HBox** o **FlowPane** si se quiere tolerar mejor reducción horizontal. Para este proyecto se recomienda **FlowPane**, porque da más elasticidad sin romper composición.

## Función de la franja
Permitir que el usuario refine la agenda sin salir de la vista actual.

## Controles oficiales de filtro

### Filtro por fecha
- Control: **DatePicker**
- Label asociado: **Fecha**
- Tooltip: **Seleccionar la fecha a consultar en la agenda**
- Valor inicial: fecha del día

### Filtro por profesional
- Control: **ComboBox**
- Label asociado: **Profesional**
- Ítems de semilla:
  - Todos
  - Dra. Salazar
  - Dr. Paredes
  - Técnico Rivera
  - Asesor Molina
- Valor inicial: **Todos**
- Tooltip: **Filtrar citas por profesional o responsable**

### Filtro por estado
- Control: **ComboBox**
- Label asociado: **Estado**
- Ítems de semilla:
  - Todos
  - Confirmada
  - Pendiente
  - Cancelada
  - Reprogramada
  - Atendida
  - En espera
  - Recall pendiente
- Valor inicial: **Todos**
- Tooltip: **Filtrar citas según su estado actual**

### Filtro por tipo de atención
- Control: **ComboBox**
- Label asociado: **Atención**
- Ítems de semilla:
  - Todas
  - Examen visual
  - Control
  - Ajuste de montura
  - Entrega de lentes
  - Revisión de receta
  - Toma de medidas
  - Adaptación de lentes de contacto
- Valor inicial: **Todas**
- Tooltip: **Filtrar por tipo de cita o atención**

### Búsqueda rápida
- Control: **TextField**
- Label asociado: **Buscar**
- Placeholder exacto: **Cliente, observación o referencia**
- Tooltip: **Buscar coincidencias rápidas dentro de las citas visibles**

### Botón limpiar
- Control: **Button**
- Texto exacto: **Limpiar filtros**
- Tooltip: **Restablecer fecha, filtros y búsqueda**

---

## 8. Franja inferior: subnavegación interna del módulo Agenda

## Contenedor sugerido
**HBox** con botones de cambio de sub-vista.

### Opción recomendada
Usar **ToggleButton** agrupados en un **ToggleGroup** para que quede claro que solo una sub-vista está activa a la vez.

## Sub-vistas visibles en la barra
- Día
- Semana
- Lista del día
- Lista de espera
- Confirmaciones
- Horarios y bloqueos

## Textos exactos
- **Día**
- **Semana**
- **Lista del día**
- **Lista de espera**
- **Confirmaciones**
- **Horarios y bloqueos**

## Tooltips exactos
- Día: **Ver la agenda detallada de una jornada**
- Semana: **Ver la distribución semanal de citas**
- Lista del día: **Ver las citas del día en formato tabular**
- Lista de espera: **Consultar clientes pendientes por cupo disponible**
- Confirmaciones: **Revisar citas pendientes de confirmar o recordar**
- Horarios y bloqueos: **Consultar disponibilidad, pausas y bloqueos de agenda**

## Estado visual de sub-vista activa
El ToggleButton activo debe verse claramente seleccionado, pero sin volverse un tab llamativo. Debe ser funcional y sobrio.

---

## 9. Región central del módulo Agenda

## Contenedor sugerido
**BorderPane** secundario o **HBox** principal dentro del center del módulo.

### Distribución
- región central: sub-vista activa
- región derecha: detalle contextual

## Contenedor conceptual de la sub-vista activa
**agendaContentHostPane**

### Reglas de arquitectura
- esta región debe reescribirse al cambiar de sub-vista
- el panel lateral derecho debe seguir estable en la mayoría de sub-vistas
- la lógica de cambio no debe destruir toda la vista, solo reemplazar la sub-vista visible

---

# 10. Sub-vista principal: Día

Esta es la sub-vista más importante del módulo Agenda y debe diseñarse con especial cuidado.

## Función
Permitir ver y gestionar una jornada completa con foco horario, lectura rápida de carga operativa, disponibilidad y estado de cada cita.

## Contenedor principal sugerido
**BorderPane** o **HBox** interno con dos áreas:
- izquierda angosta para horas
- centro ancho para el timeline o grilla de citas

### Decisión recomendada
Usar un **BorderPane** donde:
- **left**: columna fija de horas
- **center**: superficie de agenda del día

## 10.1. Columna de horas

### Contenedor sugerido
**VBox**

### Contenido
Filas horarias desde 08:00 hasta 18:00 o el rango que se decida como estándar operativo.

### Seeds de horario recomendadas
- 08:00
- 08:30
- 09:00
- 09:30
- 10:00
- 10:30
- 11:00
- 11:30
- 12:00
- 12:30
- 13:00
- 13:30
- 14:00
- 14:30
- 15:00
- 15:30
- 16:00
- 16:30
- 17:00
- 17:30
- 18:00

### Regla visual
La columna de horas debe ser limpia, discreta y estable. No debe competir con las citas.

## 10.2. Superficie central de agenda del día

### Contenedor sugerido
**Pane** o **GridPane** semi personalizado dentro de un **ScrollPane** si hace falta.

### Decisión inteligente para esta maqueta
No conviene intentar un calendario totalmente artesanal ni excesivamente complejo. Lo mejor es una superficie que parezca un timeline diario claro, donde cada cita se represente como un bloque.

### Qué debe representar esta superficie
- franjas horarias
- citas ocupando su espacio temporal
- huecos libres
- posibles bloqueos

### Cada cita debe verse como un bloque visual
Cada bloque de cita puede modelarse conceptualmente como una superficie simple con:
- hora
- nombre del cliente
- tipo de atención
- estado
- profesional

### Seeds oficiales de citas para la vista Día
1. **09:00** | Sofía Ramírez | Examen visual | Confirmada | Dra. Salazar
2. **09:30** | Juan Cedeño | Ajuste de montura | Pendiente | Técnico Rivera
3. **10:15** | Carmen López | Entrega de lentes | Confirmada | Asesor Molina
4. **11:00** | Luis Andrade | Revisión de receta | Requiere revisión | Dr. Paredes
5. **12:00** | Bloqueo interno | Pausa operativa | Confirmado | Sistema
6. **14:00** | María León | Toma de medidas | Confirmada | Asesor Molina
7. **15:30** | Ana Vera | Control | Reprogramada | Dra. Salazar
8. **16:30** | Carlos Mendoza | Adaptación de lentes de contacto | Pendiente | Dr. Paredes

### Regla visual de los bloques de cita
- deben ser claramente clicables
- deben distinguirse por estado
- deben tener buena jerarquía interna
- no deben llevar esquinas exageradamente redondeadas
- deben mostrar suficiente información sin saturarse

### Tooltip de cada cita
Ejemplo de tooltip completo:
**Sofía Ramírez · Examen visual · 09:00 · Confirmada · Dra. Salazar**

### Tooltip de bloqueo interno
**Bloque de agenda no disponible para citas**

## 10.3. Estado vacío de la vista Día
Texto exacto:
**No hay citas registradas para esta fecha**

## 10.4. Acción principal de la vista Día
Button visible cerca del encabezado general, no dentro del timeline:
**Nueva cita**

### Acciones contextuales posibles al seleccionar una cita
Pueden mostrarse en el panel lateral derecho, no directamente sobre el bloque.

---

# 11. Panel lateral derecho: detalle contextual de cita

## Función
Mostrar detalle útil de la cita seleccionada sin abrir otra pantalla.

## Contenedor sugerido
**ScrollPane** con un **VBox** interno.

## Encabezado del panel
- Label: **Detalle de la cita**
- Tooltip: **Información detallada de la cita seleccionada**

## Contenido oficial del panel

### Campo 1
- Label de título: **Cliente**
- Valor de semilla: **Sofía Ramírez**

### Campo 2
- Label de título: **Hora**
- Valor de semilla: **09:00**

### Campo 3
- Label de título: **Atención**
- Valor de semilla: **Examen visual**

### Campo 4
- Label de título: **Estado**
- Valor de semilla: **Confirmada**

### Campo 5
- Label de título: **Profesional**
- Valor de semilla: **Dra. Salazar**

### Campo 6
- Label de título: **Sucursal**
- Valor de semilla: **Matriz Centro**

### Campo 7
- Label de título: **Contacto**
- Valor de semilla: **099 123 4567**

### Campo 8
- Label de título: **Observaciones**
- Valor de semilla: **Cliente solicita atención puntual por salida laboral**

## Botones del panel lateral

### Botón 1
- Control: **Button**
- Texto exacto: **Editar cita**
- Tooltip: **Modificar los datos de la cita seleccionada**
- Tipo: secundario

### Botón 2
- Control: **Button**
- Texto exacto: **Confirmar cita**
- Tooltip: **Marcar la cita como confirmada**
- Tipo: principal cuando la cita esté pendiente

### Botón 3
- Control: **Button**
- Texto exacto: **Reprogramar**
- Tooltip: **Cambiar la fecha u hora de la cita seleccionada**
- Tipo: secundario

### Botón 4
- Control: **Button**
- Texto exacto: **Cancelar cita**
- Tooltip: **Cancelar la cita seleccionada**
- Tipo: peligroso

### Botón 5
- Control: **Button**
- Texto exacto: **Marcar como atendida**
- Tooltip: **Registrar que la atención ya fue realizada**
- Tipo: secundario

### Botón 6
- Control: **Button**
- Texto exacto: **Abrir cliente**
- Tooltip: **Abrir la ficha del cliente asociado a esta cita**
- Tipo: secundario

## Regla visual del panel lateral
- debe ser muy legible
- no debe parecer formulario pesado
- debe sentirse como ficha rápida de contexto
- los botones deben ir agrupados al final

---

# 12. Sub-vista: Semana

## Función
Mostrar una distribución general de la semana sin el detalle fino de la vista Día.

## Contenedor sugerido
**GridPane** o **HBox** con cinco o seis columnas, una por día visible.

## Días de ejemplo
- Lunes
- Martes
- Miércoles
- Jueves
- Viernes
- Sábado

## Cada columna debe mostrar
- nombre del día
- cantidad total de citas
- pequeños bloques de resumen

## Seeds de ejemplo
- Lunes: 18 citas
- Martes: 14 citas
- Miércoles: 20 citas
- Jueves: 12 citas
- Viernes: 16 citas
- Sábado: 8 citas

## Tooltip del día
**Ver detalle de citas registradas en este día**

## Acción secundaria posible
Button: **Ir al día seleccionado**
Tooltip: **Abrir la vista Día para la fecha seleccionada**

---

# 13. Sub-vista: Lista del día

## Función
Mostrar la agenda diaria en formato tabular, más cómoda para lectura administrativa o de recepción.

## Contenedor sugerido
**TableView**

## Columnas oficiales
- Hora
- Cliente
- Atención
- Estado
- Profesional
- Sucursal
- Observación breve

## Seeds
Reutilizar las mismas citas de la vista Día.

## Botones superiores recomendados
- **Ver día**
- **Nueva cita**
- **Exportar agenda**

## Tooltips
- Ver día: **Cambiar a la vista horaria del día**
- Exportar agenda: **Exportar las citas visibles del día**

## Estado vacío
**No hay citas registradas para esta fecha**

---

# 14. Sub-vista: Lista de espera

## Función
Mostrar clientes disponibles para ocupar huecos libres o reprogramaciones.

## Contenedor sugerido
**TableView** con panel lateral de detalle opcional.

## Columnas oficiales
- Cliente
- Atención solicitada
- Franja preferida
- Sucursal
- Prioridad
- Estado de contacto

## Seeds oficiales
1. Daniela Mora | Examen visual | Mañana | Matriz Centro | Alta | Pendiente
2. Roberto Núñez | Ajuste de montura | Tarde | Norte Mall | Media | Contactado
3. Elena Cárdenas | Control | Flexible | Matriz Centro | Baja | Pendiente

## Botones recomendados
- **Asignar cupo**
- **Contactar cliente**
- **Eliminar de espera**

## Tooltips
- Asignar cupo: **Asignar un espacio disponible al cliente seleccionado**
- Contactar cliente: **Registrar contacto o intento de confirmación**
- Eliminar de espera: **Quitar el cliente de la lista de espera**

## Estado vacío
**No hay clientes en lista de espera**

---

# 15. Sub-vista: Confirmaciones

## Función
Controlar qué citas todavía requieren confirmación o recordatorio.

## Contenedor sugerido
**TableView** o **VBox** con filas de trabajo. Para claridad administrativa se recomienda **TableView**.

## Columnas oficiales
- Fecha
- Hora
- Cliente
- Atención
- Estado de confirmación
- Último recordatorio
- Sucursal

## Seeds oficiales
1. 14/04/2026 | 09:30 | Juan Cedeño | Ajuste de montura | Pendiente | No enviado | Matriz Centro
2. 14/04/2026 | 16:30 | Carlos Mendoza | Adaptación de lentes de contacto | Pendiente | Enviado hoy | Matriz Centro
3. 15/04/2026 | 10:00 | Diana Vélez | Examen visual | Recall pendiente | No enviado | Norte Mall

## Botones recomendados
- **Marcar confirmada**
- **Enviar recordatorio**
- **Reprogramar cita**

## Tooltips
- Marcar confirmada: **Registrar que el cliente confirmó su asistencia**
- Enviar recordatorio: **Registrar envío de recordatorio al cliente**
- Reprogramar cita: **Cambiar fecha u hora de la cita**

## Estado vacío
**No hay confirmaciones pendientes**

---

# 16. Sub-vista: Horarios y bloqueos

## Función
Permitir ver disponibilidad real de atención, pausas y bloqueos operativos.

## Contenedor sugerido
Vista híbrida con **VBox** de bloques informativos y una **TableView** corta o lista de rangos horarios.

## Bloques internos recomendados
### 1. Horario general de sucursal
- Lunes a viernes: 08:00 - 18:00
- Sábado: 09:00 - 14:00

### 2. Pausas o bloqueos de ejemplo
- 12:00 - 13:00 | Pausa operativa | Confirmado
- 16:00 - 16:30 | Reunión interna | Confirmado

### 3. Disponibilidad visible
- Turnos disponibles en mañana
- Turnos disponibles en tarde

## Botones recomendados
- **Registrar bloqueo**
- **Editar horario**
- **Quitar bloqueo**

## Tooltips
- Registrar bloqueo: **Reservar un bloque de tiempo no disponible para citas**
- Editar horario: **Modificar el horario visible de atención**
- Quitar bloqueo: **Liberar un bloque previamente reservado**

## Estado vacío
**No hay bloqueos registrados para esta fecha**

---

# 17. Estados oficiales del módulo Agenda

## Estados de cita usados en Agenda
- Confirmada
- Pendiente
- Cancelada
- Reprogramada
- Atendida
- En espera
- Recall pendiente
- Requiere revisión

## Estados visuales complementarios
- hueco disponible
- bloqueado
- pausa operativa

## Regla visual
Todos los estados deben verse con texto claro y apoyo cromático discreto. Nunca depender solo del color.

---

# 18. Seed data oficial del módulo Agenda

## Clientes de semilla
- Sofía Ramírez
- Juan Cedeño
- Carmen López
- Luis Andrade
- María León
- Ana Vera
- Carlos Mendoza
- Daniela Mora
- Roberto Núñez
- Elena Cárdenas
- Diana Vélez

## Profesionales y responsables
- Dra. Salazar
- Dr. Paredes
- Técnico Rivera
- Asesor Molina

## Sucursales
- Matriz Centro
- Norte Mall

## Tipos de atención
- Examen visual
- Control
- Ajuste de montura
- Entrega de lentes
- Revisión de receta
- Toma de medidas
- Adaptación de lentes de contacto

---

# 19. Textos oficiales del módulo Agenda

## Títulos y labels principales
- Agenda
- Control diario y semanal de citas, disponibilidad y seguimiento de atención
- Sucursal activa
- Fecha
- Profesional
- Estado
- Atención
- Buscar
- Día
- Semana
- Lista del día
- Lista de espera
- Confirmaciones
- Horarios y bloqueos
- Detalle de la cita

## Botones oficiales
- Nueva cita
- Actualizar agenda
- Limpiar filtros
- Ver día
- Ver agenda
- Editar cita
- Confirmar cita
- Reprogramar
- Cancelar cita
- Marcar como atendida
- Abrir cliente
- Exportar agenda
- Asignar cupo
- Contactar cliente
- Eliminar de espera
- Marcar confirmada
- Enviar recordatorio
- Registrar bloqueo
- Editar horario
- Quitar bloqueo

## Placeholders
- Cliente, observación o referencia

## Empty states
- No hay citas registradas para esta fecha
- No hay clientes en lista de espera
- No hay confirmaciones pendientes
- No hay bloqueos registrados para esta fecha

---

# 20. Reglas visuales específicas del módulo Agenda

- la vista Día debe ser la estrella del módulo
- la vista Semana debe ser más resumida, no más compleja
- Lista del día debe sentirse administrativa y rápida
- Lista de espera y Confirmaciones deben aportar credibilidad operativa
- Horarios y bloqueos debe ser simple y serio
- no usar formas infantiles ni sobredecoración
- no recargar con demasiadas líneas o cajas visuales
- mantener jerarquía clara entre filtros, vista activa y detalle
- permitir lectura rápida desde recepción o caja de atención

---

# 21. Relación del módulo Agenda con otros módulos

Agenda debe conectarse con:
- Inicio, que la resume
- Clientes, porque cada cita pertenece a una ficha
- Recetas, porque algunas citas derivan en registro técnico
- Venta óptica, porque una atención puede derivar en orden de venta
- Seguimiento, por recordatorios y recalls
- Sucursales, por distribución operativa

---

# 22. Cierre del módulo Agenda

Este módulo debe dar la impresión de que la óptica puede organizar su atención de forma seria, clara y flexible. Debe verse útil para recepción, atención, seguimiento y control diario. No debe vender complejidad artificial, sino control real de citas, tiempos, estados, esperas, confirmaciones y disponibilidad.

La clave de Agenda no está en parecer un calendario sofisticado, sino en parecer una herramienta realmente útil para una óptica con flujo diario de clientes.