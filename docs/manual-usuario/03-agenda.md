# Agenda

## ¿Para qué sirve este módulo?

El módulo **Agenda** es su herramienta para gestionar las citas del consultorio de optometría. Aquí usted puede programar, ver y organizar las citas de sus pacientes por día, semana o en lista. También puede gestionar la lista de espera y los bloques de horario de su equipo.

## ¿Quién usa esta pantalla?

- **Optometristas:** para ver su agenda del día y las citas programadas.
- **Recepcionistas:** para agendar, reprogramar y confirmar citas.
- **Administradores:** para revisar la ocupación de los horarios y el rendimiento de la agenda.

---

## Vistas de la Agenda

La Agenda tiene 6 vistas diferentes. Puede cambiar entre ellas usando los botones en la parte superior del módulo:

| Vista | Para qué sirve |
|-------|---------------|
| **Día** | Vista principal con línea de tiempo hora a hora. Ideal para ver la agenda del día de un vistazo. |
| **Semana** | Vista semanal para planificar los próximos días. |
| **Lista del día** | Lista simple de las citas del día, ordenadas por hora. |
| **Lista de espera** | Pacientes que esperan un cupo disponible. |
| **Confirmaciones** | Citas pendientes de confirmación por parte del paciente. |
| **Bloques de horario** | Configuración de horarios disponibles y bloques no laborables. |

---

## Vista Día (la principal)

### Cómo se ve
Una línea de tiempo vertical con franjas horarias (por ejemplo, de 8:00 a 19:00). Cada cita aparece como una tarjeta en su franja horaria correspondiente.

### Qué información muestra cada cita
- **Hora** de inicio y fin
- **Nombre del paciente**
- **Profesional** asignado
- **Tipo de atención** (Examen visual, Control, Urgencia, etc.)
- **Estado** de la cita (Confirmada, Pendiente, En curso, Completada, Cancelada)

### Qué puede hacer aquí
- **Agendar nueva cita:** Haga clic en el botón **Nueva cita** y complete los datos del paciente, profesional, hora y tipo de atención.
- **Ver detalle de una cita:** Haga clic sobre la tarjeta de la cita para ver toda la información.
- **Cambiar estado:** Puede marcar una cita como Confirmada, En curso o Completada según corresponda.

---

## Vista Semana

Muestra los 7 días de la semana en columnas. Cada día tiene su lista de citas. Es ideal para:
- Ver la carga de trabajo semanal
- Identificar días con pocos pacientes para ofrecer promociones
- Planificar vacaciones o ausencias del equipo

---

## Lista de espera

Cuando un paciente quiere cita pero no hay horario disponible, puede agregarlo a la **lista de espera**.

### Cómo agregar un paciente a la lista de espera
1. Vaya a la vista **Lista de espera**
2. Haga clic en **Agregar a lista de espera**
3. Seleccione el paciente (o cree uno nuevo)
4. Indique el profesional preferido y el tipo de atención
5. Opcionalmente, agregue una nota (ej: "Prefiere mañana")

### Qué pasa cuando se libera un horario
1. Vaya a la lista de espera
2. Identifique un paciente que pueda tomar el horario liberado
3. Haga clic en **Agendar** para convertir la espera en cita confirmada
4. Confirme con el paciente la nueva fecha y hora

---

## Confirmaciones

Aquí verá las citas que aún no han sido confirmadas por el paciente. Puede:
- **Confirmar:** El paciente dijo que sí asistirá
- **Cancelar:** El paciente no puede asistir
- **Enviar recordatorio:** Reenviar la notificación al paciente

> **Recomendación:** Reviste esta vista al inicio de cada día para asegurar que las citas del día estén confirmadas.

---

## Bloques de horario

En esta vista puede configurar:
- **Horario laboral:** Qué horas están disponibles para agendar (ej: 9:00 - 18:00)
- **Bloques no laborables:** Horas donde no se agendan citas (almuerzo, reuniones, mantenimiento)
- **Duración estándar:** Cuánto dura cada tipo de cita (ej: Examen visual = 30 min, Control = 15 min)

---

## Casos de uso comunes

### "Quiero agendar una cita para un paciente"
1. Vaya a la vista **Día** o **Semana**
2. Haga clic en **Nueva cita**
3. Busque o seleccione el paciente
4. Elija el profesional, la fecha, la hora y el tipo de atención
5. Haga clic en **Guardar**

### "Un paciente canceló y quiero ofrecer el cupo"
1. Vaya a la vista **Lista de espera**
2. Identifique un paciente que pueda tomar ese cupo
3. Llame al paciente para confirmar
4. Si acepta, haga clic en **Agendar** para crear la cita

### "Necesito ver las citas de la semana que viene"
Simplemente vaya a la vista **Semana** y navegue con las flechas de navegación.

### "Quiero confirmar las citas de mañana"
1. Vaya a la vista **Confirmaciones**
2. Filtre por la fecha de mañana
3. Llame o envíe recordatorio a cada paciente
4. Marque cada cita como Confirmada o Cancelada según la respuesta

---

## Estados de las citas y sus colores

| Estado | Color | Qué significa |
|--------|-------|---------------|
| **Confirmada** | 🟢 Verde | El paciente confirmó que asistirá |
| **Pendiente** | 🟡 Ámbar | Esperando confirmación del paciente |
| **En curso** | 🔵 Azul | El paciente está siendo atendido ahora |
| **Completada** | ⚫ Gris | La cita ya finalizó |
| **Cancelada** | 🔴 Rojo | La cita fue cancelada |
| **No asistió** | ⚫ Gris oscuro | El paciente no llegó sin cancelar |

---

## Filtros disponibles

En la barra de filtros de la Agenda puede:
- **Buscar por nombre:** Escriba el nombre del paciente
- **Filtrar por profesional:** Seleccione un optometrista específico
- **Filtrar por estado:** Solo citas Confirmadas, Pendientes, etc.
- **Filtrar por tipo de atención:** Solo Exámenes visuales, Controles, etc.

---

## Notas importantes
- Las vistas se actualizan automáticamente cuando se agendan o modifican citas desde otros módulos.
- Si cambia de sucursal, la agenda mostrará las citas de esa sucursal.
- Puede imprimir la vista del día si necesita tener una copia física.
