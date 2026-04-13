# Notificaciones

## ¿Para qué sirve este módulo?

El módulo **Notificaciones** es el centro de comunicaciones del sistema. Aquí usted puede ver las alertas del sistema, enviar mensajes a pacientes, gestionar recordatorios automáticos, crear campañas de comunicación y revisar el historial de todas las notificaciones enviadas.

## ¿Quién usa esta pantalla?

- **Recepcionistas:** para enviar recordatorios de citas y notificaciones de entrega.
- **Administradores:** para crear campañas de comunicación y revisar alertas del sistema.
- **Todos los usuarios:** para ver alertas operativas internas.

---

## Sub-vistas del módulo

El módulo tiene 7 sub-vistas accesibles desde los botones superiores:

| Sub-vista | Qué muestra |
|-----------|-------------|
| **Bandeja general** | Todas las notificaciones recibidas, organizadas por fecha. |
| **Notificaciones al cliente** | Comunicaciones enviadas a pacientes (recordatorios, avisos de entrega). |
| **Alertas operativas internas** | Mensajes del sistema para el equipo de la óptica. |
| **Campañas y plantillas** | Mensajes masivos pre-diseñados para promociones o recordatorios. |
| **Historial de envíos** | Registro de todas las notificaciones enviadas y sus respuestas. |
| **Preferencias** | Qué canales usa cada usuario y qué tipo de alertas recibe. |
| **Alertas críticas** | Notificaciones urgentes que requieren atención inmediata. |

---

## Bandeja general

### Todas sus notificaciones

La bandeja muestra todas las notificaciones que usted ha recibido:

| Columna | Descripción |
|---------|-------------|
| **Fecha** | Cuándo se recibió o envió |
| **Tipo** | Recordatorio, Entrega, Alerta, Campaña, Interna |
| **Destinatario** | A quién va dirigida (paciente, usuario, todos) |
| **Asunto** | Resumen del mensaje |
| **Canal** | Llamada, SMS, Email, Notificación en sistema |
| **Estado** | Enviada, Leída, Pendiente, Fallida |

### Qué puede hacer aquí
- **Marcar como leída:** Si ya revisó la notificación
- **Responder:** Si necesita actuar sobre la notificación
- **Eliminar:** Si ya no es relevante
- **Filtrar por tipo:** Ver solo recordatorios, solo alertas, etc.

---

## Notificaciones al cliente

### Comunicaciones con pacientes

Desde aquí gestiona los mensajes que recibe cada paciente:

#### Tipos de notificaciones al cliente
| Tipo | Cuándo se envía | Ejemplo |
|------|-----------------|---------|
| **Recordatorio de cita** | 24 horas antes de la cita | "Le recordamos su cita mañana a las 10:00 con la Dra. Ruiz" |
| **Lente listo para entrega** | Cuando el laboratorio completa la orden | "Sus lentes están listos para recoger. Ord OV-00045" |
| **Receta por vencer** | 1 mes antes del vencimiento | "Su receta visual vence próximamente. Agende un nuevo examen" |
| **Confirmación de compra** | Al confirmar una venta | "Su orden OV-00045 ha sido registrada. Entrega estimada: 20/04" |
| **Cobro pendiente** | Cuando un pago está vencido | "Le recordamos que tiene un saldo pendiente de $45.00" |
| **Cumpleaños** | El día del cumpleaños del paciente | "¡Feliz cumpleaños! Lo esperamos con un 10% de descuento" |

### Enviar una notificación manual
1. Vaya a **Notificaciones al cliente**
2. Haga clic en **Nueva notificación**
3. Seleccione al paciente
4. Seleccione el tipo de mensaje o escriba uno personalizado
5. Seleccione el canal (Llamada, SMS, Email)
6. Envíe inmediatamente o programe para después

---

## Alertas operativas internas

### Mensajes del sistema para el equipo

El sistema genera alertas internas automáticamente cuando detecta situaciones que requieren atención:

| Alerta | Cuándo aparece | Qué hacer |
|--------|---------------|-----------|
| **Stock crítico** | Un producto está por debajo del mínimo | Genere una orden de compra |
| **Cita sin confirmar** | Una cita de mañana no ha sido confirmada | Contacte al paciente |
| **Orden atrasada** | Una orden de laboratorio supera el tiempo estimado | Revise con el laboratorio |
| **Pago vencido** | Un paciente no ha pagado y ya venció el plazo | Contacte al paciente |
| **Receta vencida** | Un paciente activo tiene receta vencida | Ofrezca un examen visual |
| **Diferencia de caja** | El cierre de caja mostró diferencia | Investigue la causa |

### Indicadores de urgencia
| Nivel | Color | Acción requerida |
|-------|-------|-----------------|
| **Crítico** | 🔴 Rojo | Atención inmediata |
| **Importante** | 🟡 Ámbar | Atención hoy |
| **Informativo** | 🔵 Azul | Revisar cuando pueda |

---

## Campañas y plantillas

### Mensajes masivos pre-diseñados

#### Plantillas predeterminadas
El sistema incluye plantillas para los mensajes más comunes:
- Recordatorio de cita
- Aviso de lentes listos
- Recordatorio de receta por vencer
- Felicitación de cumpleaños
- Promoción de monturas nuevas
- Campaña de revisión visual

#### Crear una plantilla personalizada
1. Vaya a **Campañas y plantillas**
2. Haga clic en **Nueva plantilla**
3. Escriba el mensaje (puede usar variables como {nombre_paciente}, {fecha_cita})
4. Seleccione el canal predeterminado
5. Guarde la plantilla para uso futuro

#### Crear una campaña masiva
1. Haga clic en **Nueva campaña**
2. Seleccione la plantilla o escriba un mensaje nuevo
3. Seleccione el segmento de pacientes:
   - Todos los pacientes activos
   - Pacientes con receta por vencer
   - Pacientes que no han venido en X meses
   - Pacientes de una sucursal específica
4. Programe la fecha de envío
5. Confirme y envíe

---

## Historial de envíos

### Registro completo de notificaciones

Muestra todas las notificaciones enviadas, con su estado de entrega:

| Columna | Descripción |
|---------|-------------|
| **Fecha de envío** | Cuándo se envió |
| **Destinatario** | Paciente o usuario |
| **Tipo** | Recordatorio, Campaña, Alerta |
| **Canal** | SMS, Email, Llamada |
| **Estado** | Enviada, Entregada, Leída, Fallida |
| **Respuesta** | Si el paciente respondió |

### Para qué sirve el historial
- Verificar que un recordatorio fue enviado
- Identificar mensajes fallidos (ej: email incorrecto)
- Evaluar la efectividad de campañas
- Auditoría de comunicaciones

---

## Preferencias

### Configuración de notificaciones por usuario

Cada usuario puede configurar qué alertas recibe y por qué canal:

| Preferencia | Opciones |
|-------------|----------|
| **Recibir alertas de stock** | Sí / No |
| **Recibir alertas de citas** | Sí / No |
| **Recibir alertas de cobros** | Sí / No |
| **Canal predeterminado** | Notificación en sistema, Email, SMS |
| **Resumen diario** | Sí (recibe un email al inicio del día con todo lo pendiente) / No |

---

## Alertas críticas

### Notificaciones urgentes

Las alertas críticas requieren atención inmediata y se muestran en una sección separada:

| Alerta | Por qué es crítica |
|--------|-------------------|
| **Caja con diferencia importante** | Puede indicar un problema de cobros |
| **Pedido de laboratorio urgente** | Un paciente necesita sus lentes hoy |
| **Reclamo de paciente** | Un paciente tiene una queja que resolver |
| **Proveedor no entregó** | Un pedido crítico está atrasado |
| **Sistema con error** | Algo técnico requiere atención |

---

## Casos de uso comunes

### "Quiero enviar recordatorios de citas de mañana"
1. Vaya a **Notificaciones al cliente**
2. Filtre por **Tipo → Recordatorio de cita**
3. Seleccione las citas de mañana
4. Haga clic en **Enviar recordatorios**
5. Seleccione el canal (SMS o llamada)

### "Quiero avisarle a todos los pacientes que tenemos monturas nuevas"
1. Vaya a **Campañas y plantillas**
2. Haga clic en **Nueva campaña**
3. Seleccione "Todos los pacientes activos"
4. Escriba el mensaje promocional
5. Programe el envío
6. Confirme

### "Un paciente dice que no recibió el aviso de que sus lentes estaban listos"
1. Vaya a **Historial de envíos**
2. Busque al paciente por nombre
3. Verifique si la notificación fue enviada y entregada
4. Si fue fallida, reenvíe por otro canal

### "Quiero saber qué alertas críticas tengo pendientes"
1. Vaya a **Alertas críticas**
2. La lista muestra todas las alertas urgentes
3. Atiéndalas en orden de prioridad

---

## Canales de notificación

| Canal | Descripción | Cuándo usarlo |
|-------|-------------|---------------|
| **Llamada** | Llamada telefónica automatizada o manual | Recordatorios de cita urgentes |
| **SMS** | Mensaje de texto al celular | Recordatorios, avisos de entrega |
| **Email** | Correo electrónico | Campañas, confirmaciones, reportes |
| **Notificación en sistema** | Alerta dentro de la aplicación | Alertas operativas internas |

---

## Notas importantes
- Las notificaciones automáticas se generan según las reglas configuradas en **Configuración → Agenda y seguimiento**.
- Siempre verifique que el teléfono y email del paciente estén actualizados antes de enviar notificaciones.
- Los SMS tienen un costo por envío; configure límites si es necesario.
- Las campañas masivas no deben enviarse con excesiva frecuencia para no molestar al paciente.
- El historial de envíos es útil para verificar que un paciente fue contactado antes de tomar acciones de cobro.
