# Lienzo del módulo Notificaciones para sistema de óptica en JavaFX

## 1. Identidad del módulo

### Nombre oficial del módulo en el menú
**Notificaciones**

### Texto visible del botón del sidebar
**Notificaciones**

### Tooltip del botón del sidebar
**Consultar avisos, recordatorios, alertas y trazabilidad de mensajes del sistema óptico**

### Ícono conceptual
Campana, aviso, mensaje o alerta operativa.

### Título visible en pantalla
**Notificaciones**

### Subtítulo visible en pantalla
**Avisos al cliente, alertas internas, recordatorios y trazabilidad de comunicación**

### Tipo de módulo
Módulo operativo de comunicación y alertas con sub-vistas internas especializadas.

### Objetivo del módulo
Permitir que la óptica organice, priorice y consulte mensajes salientes, alertas internas, recordatorios, recalls y trazabilidad de contacto, manteniendo visible qué ocurrió, a quién afecta, qué canal se utilizó y qué acción sigue.

---

## 2. Rol del módulo dentro de la arquitectura GUI

Notificaciones no debe sentirse como un buzón caótico. Debe verse como el centro donde el sistema reúne comunicación útil y alertas accionables.

### Regla arquitectónica principal
Cuando el usuario entre al módulo Notificaciones, la región central principal del sistema debe reemplazarse por una vista completa llamada conceptualmente:
**NotificacionesModuleView**

### Estructura interna limpia del módulo
La vista Notificaciones debe dividirse en:
- encabezado del módulo
- barra de filtros y búsqueda
- subnavegación interna del módulo
- región central intercambiable para sub-vistas
- panel lateral derecho con resumen persistente de la notificación o alerta seleccionada

### Filosofía de implementación
El usuario debe poder revisar qué notificación salió, qué alerta sigue pendiente, qué cliente fue contactado y qué aviso requiere acción, sin salir del módulo ni perder el contexto del caso.

### Regla de arquitectura limpia
Debe existir una región interna estable que se reescriba solo con el submódulo activo. Esa región se denomina conceptualmente:
**notificacionesContentHostPane**

Ese contenedor puede ser un **StackPane** o el centro de un **BorderPane** secundario.

### Sub-vistas que viven dentro de notificacionesContentHostPane
- Bandeja general de notificaciones
- Notificaciones al cliente
- Notificaciones operativas internas
- Campañas y plantillas
- Historial de envíos y respuestas
- Preferencias de notificación
- Alertas críticas y priorizadas

---

## 3. Sub-vistas oficiales del módulo Notificaciones

## Sub-vistas definidas
1. **Bandeja general de notificaciones**
2. **Notificaciones al cliente**
3. **Notificaciones operativas internas**
4. **Campañas y plantillas**
5. **Historial de envíos y respuestas**
6. **Preferencias de notificación**
7. **Alertas críticas y priorizadas**

## Orden de prioridad
1. Bandeja general de notificaciones
2. Notificaciones al cliente
3. Notificaciones operativas internas
4. Campañas y plantillas
5. Historial de envíos y respuestas
6. Preferencias de notificación
7. Alertas críticas y priorizadas

## Vista por defecto al abrir el módulo
**Bandeja general de notificaciones**

Motivo: es la vista que mejor responde a la pregunta operativa del día: qué avisos, recordatorios o alertas requieren atención ahora mismo.

---

## 4. Estructura visual general del módulo Notificaciones

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
- panel derecho: resumen persistente de la notificación o alerta seleccionada

### Proporción recomendada
- región de trabajo: 68% a 72%
- panel derecho: 28% a 32%

### Regla de estabilidad visual
El panel derecho debe mantenerse visible durante casi toda la navegación para que el usuario no pierda el contexto del aviso, alerta o comunicación seleccionada.

---

## 5. Encabezado del módulo Notificaciones

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
- Label principal: **Notificaciones**
- Label secundario: **Avisos al cliente, alertas internas, recordatorios y trazabilidad de comunicación**

### Zona derecha
Un **HBox** con:
- Button secundario: **Actualizar notificaciones**
- Button secundario: **Exportar bandeja**
- Button principal: **Nueva notificación**

## Controles exactos

### Label principal
- Control: **Label**
- Texto exacto: **Notificaciones**
- Tooltip: no necesita

### Label secundario
- Control: **Label**
- Texto exacto: **Avisos al cliente, alertas internas, recordatorios y trazabilidad de comunicación**
- Tooltip: no necesita

### Botón secundario 1
- Control: **Button**
- Texto exacto: **Actualizar notificaciones**
- Tooltip: **Recargar la bandeja visible de avisos y alertas**
- Estilo: secundario

### Botón secundario 2
- Control: **Button**
- Texto exacto: **Exportar bandeja**
- Tooltip: **Exportar la vista visible de notificaciones o alertas**
- Estilo: secundario

### Botón principal
- Control: **Button**
- Texto exacto: **Nueva notificación**
- Tooltip: **Registrar una notificación manual o un aviso interno**
- Estilo: acción principal

---

## 7. Franja media: filtros y búsqueda

## Contenedor sugerido
**FlowPane**

## Función
Permitir localizar notificaciones por tipo, canal, estado, cliente, orden o prioridad.

## Controles oficiales

### Búsqueda rápida
- Control: **TextField**
- Label asociado: **Buscar**
- Placeholder exacto: **Cliente, orden, tipo de aviso o referencia**
- Tooltip: **Buscar notificaciones por cliente, orden, tipo o texto relacionado**

### Filtro por tipo de notificación
- Control: **ComboBox**
- Label asociado: **Tipo**
- Ítems de semilla:
  - Todas
  - Recordatorio de cita
  - Recall anual
  - Trabajo listo para retirar
  - Saldo pendiente
  - Trabajo no retirado
  - Seguimiento post-entrega
  - Stock crítico
  - Retraso de laboratorio
  - Mensaje interno operativo
- Valor inicial: **Todas**
- Tooltip: **Filtrar avisos según el tipo de notificación**

### Filtro por canal
- Control: **ComboBox**
- Label asociado: **Canal**
- Ítems de semilla:
  - Todos
  - SMS
  - WhatsApp
  - Correo
  - Llamada
  - Interno
- Valor inicial: **Todos**
- Tooltip: **Filtrar según el canal usado para la notificación**

### Filtro por estado
- Control: **ComboBox**
- Label asociado: **Estado**
- Ítems de semilla:
  - Todos
  - Pendiente
  - Enviada
  - Entregada
  - Leída
  - Respondida
  - Sin respuesta
  - Atendida
  - Reenviada
  - Crítica
  - Cerrada
- Valor inicial: **Todos**
- Tooltip: **Filtrar notificaciones según su estado actual**

### Filtro por prioridad
- Control: **ComboBox**
- Label asociado: **Prioridad**
- Ítems de semilla:
  - Todas
  - Alta
  - Media
  - Baja
- Valor inicial: **Todas**
- Tooltip: **Filtrar notificaciones según prioridad operativa**

### Filtro por sucursal
- Control: **ComboBox**
- Label asociado: **Sucursal**
- Ítems de semilla:
  - Todas
  - Matriz Centro
  - Norte Mall
  - Sur Express
- Valor inicial: **Todas**
- Tooltip: **Filtrar avisos por sucursal asociada**

### Filtro por fecha inicial
- Control: **DatePicker**
- Label asociado: **Desde**
- Tooltip: **Seleccionar la fecha inicial para consultar notificaciones**

### Filtro por fecha final
- Control: **DatePicker**
- Label asociado: **Hasta**
- Tooltip: **Seleccionar la fecha final para consultar notificaciones**

### Opción rápida
- Control: **CheckBox**
- Texto exacto: **Solo pendientes y críticas**
- Tooltip: **Mostrar únicamente notificaciones que requieren atención inmediata**

### Botón limpiar
- Control: **Button**
- Texto exacto: **Limpiar filtros**
- Tooltip: **Restablecer la búsqueda y todos los filtros del módulo**

---

## 8. Franja inferior: subnavegación interna del módulo

## Contenedor sugerido
**HBox** con **ToggleButton** dentro de un **ToggleGroup**

## Sub-vistas visibles en la barra
- Bandeja general de notificaciones
- Notificaciones al cliente
- Notificaciones operativas internas
- Campañas y plantillas
- Historial de envíos y respuestas
- Preferencias de notificación
- Alertas críticas y priorizadas

## Tooltips exactos
- Bandeja general de notificaciones: **Consultar la vista consolidada de avisos y alertas**
- Notificaciones al cliente: **Consultar mensajes salientes relacionados con clientes y pedidos**
- Notificaciones operativas internas: **Consultar alertas internas para coordinación del equipo**
- Campañas y plantillas: **Consultar y editar plantillas de mensajes y campañas simples**
- Historial de envíos y respuestas: **Consultar envíos realizados, entregas y resultado de contacto**
- Preferencias de notificación: **Consultar canales y reglas preferidas de aviso**
- Alertas críticas y priorizadas: **Consultar casos que requieren atención inmediata**

## Regla visual
La sub-vista activa debe quedar clara, pero sin competir con la lectura de la bandeja principal.

---

## 9. Cuerpo principal del módulo Notificaciones

## Contenedor sugerido
**SplitPane** horizontal

### Panel izquierdo o principal
Sub-vista activa del módulo.

### Panel derecho
Resumen persistente de la notificación o alerta seleccionada.

---

# 10. Panel derecho persistente: resumen de la notificación

## Función
Mantener visible el contexto y la acción sugerida de la notificación seleccionada.

## Contenedor sugerido
**ScrollPane** con un **VBox** interno.

## Bloques internos del panel derecho
1. Identidad de la notificación
2. Contexto del cliente o caso
3. Estado del aviso
4. Próxima acción
5. Acciones rápidas

## 10.1. Bloque: Identidad de la notificación

### Campos visibles
- Referencia
- Tipo
- Canal

### Seeds
- **Referencia: NT-001**
- **Tipo: Trabajo listo para retirar**
- **Canal: WhatsApp**

## 10.2. Bloque: Contexto del cliente o caso

### Campos visibles
- Cliente
- Orden relacionada
- Sucursal

### Seeds
- **Cliente: Sofía Ramírez**
- **Orden relacionada: ET-044**
- **Sucursal: Matriz Centro**

## 10.3. Bloque: Estado del aviso

### Campos visibles
- Estado actual
- Fecha y hora
- Módulo origen

### Seeds
- **Estado actual: Enviada**
- **Fecha y hora: 16/04/2026 10:40**
- **Módulo origen: Entregas**

## 10.4. Bloque: Próxima acción

### Campos visibles
- Acción sugerida
- Observación clave

### Seeds
- **Acción sugerida: Confirmar si el cliente responde o registra retiro**
- **Observación clave: Trabajo listo con saldo en cero y validación completa**

## 10.5. Bloque: Acciones rápidas

### Botones oficiales
- **Marcar atendida**
- **Abrir caso**
- **Reenviar**
- **Abrir cliente**
- **Abrir orden**

### Tooltips exactos
- Marcar atendida: **Cerrar o marcar como atendida la notificación seleccionada**
- Abrir caso: **Abrir el módulo o caso asociado a la notificación**
- Reenviar: **Registrar o reenviar nuevamente la notificación seleccionada**
- Abrir cliente: **Abrir la ficha del cliente relacionado**
- Abrir orden: **Abrir la orden o referencia vinculada a la notificación**

---

# 11. Sub-vista 1: Bandeja general de notificaciones

## Función
Servir como bandeja central de avisos, recordatorios y alertas generadas por el sistema.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: pequeño resumen operativo
- **center**: **TableView** principal
- **bottom**: resumen de resultados visibles

## 11.1. Franja superior de la bandeja

### Contenido
- Label: **Bandeja general de notificaciones**
- Label secundario: **29 notificaciones visibles**

### Tooltips
- Bandeja general de notificaciones: **Vista consolidada de avisos y alertas visibles con los filtros actuales**
- 29 notificaciones visibles: **Cantidad de notificaciones mostradas en la bandeja actual**

## 11.2. TableView principal

### Columnas oficiales
- Referencia
- Tipo
- Cliente o caso
- Canal
- Estado
- Prioridad
- Fecha
n- Módulo origen

### Seeds oficiales
1. NT-001 | Trabajo listo para retirar | Sofía Ramírez | WhatsApp | Enviada | Media | 16/04/2026 10:40 | Entregas
2. NT-002 | Recall anual | Ana Vera | SMS | Pendiente | Media | 16/04/2026 09:20 | Seguimiento
3. NT-003 | Stock crítico | MZ-201 | Interno | Crítica | Alta | 16/04/2026 08:15 | Inventario
4. NT-004 | Saldo pendiente | Carlos Mendoza | Llamada | Sin respuesta | Alta | 15/04/2026 17:40 | Caja

### Tooltip de la tabla
**Seleccione una notificación para ver su contexto, estado y acción siguiente**

### Estado vacío
**No hay notificaciones visibles con los filtros actuales**

## 11.3. Pie del listado

### Textos exactos
- **Mostrando 4 de 29 notificaciones**
- **Ordenado por prioridad y fecha**

### Tooltips
- Mostrando 4 de 29 notificaciones: **Resumen de notificaciones visibles en la bandeja actual**
- Ordenado por prioridad y fecha: **Criterio de ordenamiento aplicado a la bandeja**

---

# 12. Sub-vista 2: Notificaciones al cliente

## Función
Concentrar mensajes salientes dirigidos a clientes para retiro, recall, cita, seguimiento o pago.

## Contenedor principal sugerido
**BorderPane** con indicadores superiores y **TableView** central.

### Estructura interna
1. Resumen de avisos al cliente
2. Tabla de notificaciones al cliente
3. Acciones de comunicación

## 12.1. Resumen superior

### Campos visibles
- Recordatorios enviados
- Trabajos listos avisados
- Saldos notificados
- Recall activos

### Seeds
- Recordatorios enviados: **12**
- Trabajos listos avisados: **7**
- Saldos notificados: **3**
- Recall activos: **5**

## 12.2. TableView principal

### Columnas oficiales
- Cliente
- Tipo
- Canal
- Estado
- Fecha
- Resultado

### Seeds oficiales
1. Sofía Ramírez | Trabajo listo para retirar | WhatsApp | Enviada | 16/04/2026 | Pendiente de retiro
2. Ana Vera | Recall anual | SMS | Pendiente | 16/04/2026 | Aún no enviado
3. Carlos Mendoza | Saldo pendiente | Llamada | Sin respuesta | 15/04/2026 | Solicita reintento mañana
4. Diana Vélez | Seguimiento post-entrega | Correo | Entregada | 15/04/2026 | Sin respuesta

### Tooltip de la tabla
**Mensajes salientes relacionados con clientes, pedidos o continuidad de atención**

### Botones oficiales del submódulo
- **Enviar notificación**
- **Registrar contacto**
- **Abrir seguimiento**

### Tooltips
- Enviar notificación: **Registrar o ejecutar el envío de una notificación al cliente**
- Registrar contacto: **Registrar una interacción manual con el cliente**
- Abrir seguimiento: **Abrir el módulo Seguimiento asociado al caso**

### Estado vacío
**No hay notificaciones al cliente visibles con los filtros actuales**

---

# 13. Sub-vista 3: Notificaciones operativas internas

## Función
Concentrar alertas para coordinación interna del equipo y operación diaria.

## Contenedor principal sugerido
**BorderPane** con indicadores superiores y **TableView** central.

### Estructura interna
1. Resumen de alertas internas
2. Tabla de notificaciones operativas
3. Acciones internas

## 13.1. Resumen superior

### Campos visibles
- Alertas críticas
- Alertas operativas
- Incidencias abiertas
- Pendientes de validación

### Seeds
- Alertas críticas: **4**
- Alertas operativas: **11**
- Incidencias abiertas: **3**
- Pendientes de validación: **2**

## 13.2. TableView principal

### Columnas oficiales
- Referencia
- Tipo
- Área
- Estado
- Prioridad
- Fecha

### Seeds oficiales
1. NT-003 | Stock crítico | Inventario | Crítica | Alta | 16/04/2026
2. NT-005 | Retraso de laboratorio | Laboratorio | Pendiente | Alta | 16/04/2026
3. NT-006 | Diferencia en recepción | Compras | Pendiente | Media | 15/04/2026
4. NT-007 | Validación previa de entrega | Entregas | Pendiente | Media | 15/04/2026

### Tooltip de la tabla
**Alertas internas generadas por la operación diaria de la óptica**

### Botones oficiales del submódulo
- **Marcar atendida**
- **Abrir módulo origen**
- **Asignar responsable**

### Tooltips
- Marcar atendida: **Cerrar o marcar como atendida la alerta seleccionada**
- Abrir módulo origen: **Abrir el módulo que originó la alerta**
- Asignar responsable: **Asignar o registrar quién atenderá la alerta**

### Estado vacío
**No hay alertas operativas internas visibles con los filtros actuales**

---

# 14. Sub-vista 4: Campañas y plantillas

## Función
Administrar plantillas de mensajes y campañas simples reutilizables en la operación óptica.

## Contenedor principal sugerido
**SplitPane** horizontal

### Panel izquierdo
**TableView** o **ListView** de plantillas

### Panel derecho
Vista previa y parámetros de la plantilla seleccionada

## 14.1. Listado de plantillas

### Columnas oficiales
- Plantilla
- Tipo
- Canal sugerido
- Estado

### Seeds oficiales
1. Trabajo listo para retirar | Operativa | WhatsApp | Activa
2. Recall anual | Seguimiento | SMS | Activa
3. Saldo pendiente | Caja | Llamada | Activa
4. Seguimiento post-entrega | Atención | Correo | Activa

### Tooltip del listado
**Plantillas disponibles para avisos recurrentes del sistema**

## 14.2. Vista previa de plantilla

### Campos visibles
- Nombre de plantilla
- Tipo
- Canal sugerido
- Texto de la plantilla
- Observación de uso

### Seeds
- Nombre de plantilla: **Trabajo listo para retirar**
- Tipo: **Operativa**
- Canal sugerido: **WhatsApp**
- Texto de la plantilla: **Su trabajo óptico ya está listo para retirar en la sucursal seleccionada.**
- Observación de uso: **Usar cuando la orden esté validada, pagada y disponible para entrega**

### Controles sugeridos
- **TextArea** para texto de plantilla
- **ComboBox** para canal sugerido
- **CheckBox** para plantilla activa

### Botones oficiales del submódulo
- **Guardar plantilla**
- **Duplicar plantilla**
- **Vista previa**

### Tooltips
- Guardar plantilla: **Guardar el contenido de la plantilla seleccionada**
- Duplicar plantilla: **Crear una copia editable de la plantilla actual**
- Vista previa: **Visualizar cómo se vería el mensaje final**

### Estado vacío
**No hay plantillas visibles con los filtros actuales**

---

# 15. Sub-vista 5: Historial de envíos y respuestas

## Función
Consultar qué se envió, por qué canal, con qué resultado y si hubo respuesta o confirmación.

## Contenedor principal sugerido
**BorderPane** con filtros ligeros arriba y **TableView** central.

### Estructura interna
1. Resumen de envíos
2. Tabla histórica
3. Acciones de consulta

## 15.1. Resumen superior

### Campos visibles
- Envíos realizados
- Entregados
- Respondidos
- Sin respuesta

### Seeds
- Envíos realizados: **54**
- Entregados: **42**
- Respondidos: **11**
- Sin respuesta: **8**

## 15.2. TableView principal

### Columnas oficiales
- Fecha
- Referencia
- Cliente o caso
- Canal
- Estado
- Resultado

### Seeds oficiales
1. 16/04/2026 | NT-001 | Sofía Ramírez | WhatsApp | Entregada | Pendiente de retiro
2. 15/04/2026 | NT-004 | Carlos Mendoza | Llamada | Sin respuesta | Reintentar mañana
3. 15/04/2026 | NT-008 | Ana Vera | SMS | Respondida | Confirma cita
4. 14/04/2026 | NT-009 | Diana Vélez | Correo | Leída | Sin nueva acción

### Tooltip de la tabla
**Historial de comunicaciones y resultado del contacto realizado**

### Botones oficiales del submódulo
- **Abrir detalle**
- **Reenviar aviso**
- **Exportar historial**

### Tooltips
- Abrir detalle: **Consultar el detalle del envío o respuesta seleccionada**
- Reenviar aviso: **Volver a registrar o enviar el aviso seleccionado**
- Exportar historial: **Exportar el histórico visible de envíos y respuestas**

### Estado vacío
**No hay historial visible con los filtros actuales**

---

# 16. Sub-vista 6: Preferencias de notificación

## Función
Definir canales preferidos y reglas simples de aviso por tipo de evento.

## Contenedor principal sugerido
**ScrollPane** con **VBox** interno y bloques en **GridPane**.

### Estructura interna
1. Canales preferidos
2. Reglas por tipo
3. Confirmaciones y seguridad

## 16.1. Bloque: Canales preferidos

### Campos visibles
- Canal principal para recordatorios
- Canal principal para trabajo listo
- Canal principal para saldo pendiente
- Canal principal para recall

### Seeds
- Canal principal para recordatorios: **SMS**
- Canal principal para trabajo listo: **WhatsApp**
- Canal principal para saldo pendiente: **Llamada**
- Canal principal para recall: **SMS**

### Controles sugeridos
- **ComboBox** para cada canal principal

## 16.2. Bloque: Reglas por tipo

### Campos visibles
- Reenviar si no hay respuesta
- Marcar crítica si pasan 3 días
- Permitir avisos internos automáticos

### Seeds
- Reenviar si no hay respuesta: **Sí**
- Marcar crítica si pasan 3 días: **Sí**
- Permitir avisos internos automáticos: **Sí**

### Controles sugeridos
- **CheckBox** para reglas booleanas

## 16.3. Bloque: Confirmaciones y seguridad

### Campos visibles
- Confirmar antes de enviar manualmente
- Registrar trazabilidad de cambio de estado

### Seeds
- Confirmar antes de enviar manualmente: **Sí**
- Registrar trazabilidad de cambio de estado: **Sí**

### Botones oficiales del submódulo
- **Guardar preferencias**
- **Restablecer valores sugeridos**

### Tooltips
- Guardar preferencias: **Guardar las reglas actuales de notificación**
- Restablecer valores sugeridos: **Volver a la configuración sugerida por la aplicación**

### Estado vacío
No aplica normalmente en esta vista.

---

# 17. Sub-vista 7: Alertas críticas y priorizadas

## Función
Agrupar las alertas que requieren atención inmediata o seguimiento prioritario.

## Contenedor principal sugerido
**BorderPane** con indicadores superiores y **TableView** central.

### Estructura interna
1. Resumen de criticidad
2. Tabla de alertas priorizadas
3. Acciones urgentes

## 17.1. Resumen superior

### Campos visibles
- Alertas críticas
- Casos vencidos
- Riesgo operativo

### Seeds
- Alertas críticas: **4**
- Casos vencidos: **2**
- Riesgo operativo: **Moderado**

## 17.2. TableView principal

### Columnas oficiales
- Referencia
- Tipo
- Caso afectado
- Prioridad
- Estado
- Acción sugerida

### Seeds oficiales
1. NT-003 | Stock crítico | MZ-201 | Alta | Crítica | Revisar reposición inmediata
2. NT-005 | Retraso de laboratorio | ET-046 | Alta | Pendiente | Confirmar nueva fecha con proveedor
3. NT-010 | Trabajo no retirado | ET-044 | Media | Pendiente | Recontactar cliente hoy
4. NT-011 | Saldo pendiente bloqueando entrega | OV-124 | Alta | Pendiente | Coordinar con caja antes de entregar

### Tooltip de la tabla
**Alertas priorizadas que requieren acción rápida o coordinación inmediata**

### Botones oficiales del submódulo
- **Atender alerta**
- **Abrir módulo origen**
- **Marcar cerrada**

### Tooltips
- Atender alerta: **Registrar la gestión inmediata de la alerta seleccionada**
- Abrir módulo origen: **Abrir el módulo que originó la alerta crítica**
- Marcar cerrada: **Cerrar la alerta una vez resuelta**

### Estado vacío
**No hay alertas críticas visibles en este momento**

---

# 18. Formulario conceptual: Nueva notificación

## Función
Permitir registrar manualmente un aviso o mensaje dentro de la arquitectura del módulo.

## Contenedor sugerido
Vista interna o diálogo amplio controlado. Para esta maqueta conviene resolverlo dentro del mismo módulo.

## Estructura sugerida
**ScrollPane** con **VBox** y bloques en **GridPane**.

### Grupos de campos
1. Tipo y referencia
2. Cliente o caso
3. Canal y prioridad
4. Contenido y observación

### Campos mínimos
- Tipo de notificación
- Referencia relacionada
- Cliente o caso
- Canal
- Prioridad
- Texto del aviso
- Observación

### Botones finales
- **Guardar notificación**
- **Cancelar**

### Tooltips
- Guardar notificación: **Registrar la nueva notificación dentro del sistema**
- Cancelar: **Cerrar el registro de la notificación sin guardar**

---

# 19. Seed data oficial del módulo Notificaciones

## Clientes
- Sofía Ramírez
- Ana Vera
- Carlos Mendoza
- Diana Vélez

## Tipos de notificación
- Recordatorio de cita
- Recall anual
- Trabajo listo para retirar
- Saldo pendiente
- Trabajo no retirado
- Seguimiento post-entrega
- Stock crítico
- Retraso de laboratorio
- Mensaje interno operativo

## Canales
- SMS
- WhatsApp
- Correo
- Llamada
- Interno

## Referencias
- NT-001
- NT-002
- NT-003
- NT-004
- NT-005
- NT-006
- NT-007
- NT-008
- NT-009
- NT-010
- NT-011
- ET-044
- OV-124
- SG-021

## Estados usados en el módulo
- Pendiente
- Enviada
- Entregada
- Leída
- Respondida
- Sin respuesta
- Atendida
- Reenviada
- Crítica
- Cerrada

---

# 20. Textos oficiales del módulo Notificaciones

## Títulos y labels principales
- Notificaciones
- Avisos al cliente, alertas internas, recordatorios y trazabilidad de comunicación
- Buscar
- Tipo
- Canal
- Estado
- Prioridad
- Sucursal
- Desde
- Hasta
- Solo pendientes y críticas
- Bandeja general de notificaciones
- Notificaciones al cliente
- Notificaciones operativas internas
- Campañas y plantillas
- Historial de envíos y respuestas
- Preferencias de notificación
- Alertas críticas y priorizadas
- Referencia
- Cliente
- Orden relacionada
- Módulo origen
- Acción sugerida
- Observación clave
- Resultado
- Trabajo listo para retirar
- Recall anual
- Saldo pendiente
- Stock crítico

## Botones oficiales
- Actualizar notificaciones
- Exportar bandeja
- Nueva notificación
- Limpiar filtros
- Marcar atendida
- Abrir caso
- Reenviar
- Abrir cliente
- Abrir orden
- Enviar notificación
- Registrar contacto
- Abrir seguimiento
- Abrir módulo origen
- Asignar responsable
- Guardar plantilla
- Duplicar plantilla
- Vista previa
- Abrir detalle
- Reenviar aviso
- Exportar historial
- Guardar preferencias
- Restablecer valores sugeridos
- Atender alerta
- Marcar cerrada
- Guardar notificación
- Cancelar

## Placeholders
- Cliente, orden, tipo de aviso o referencia

## Empty states
- No hay notificaciones visibles con los filtros actuales
- No hay notificaciones al cliente visibles con los filtros actuales
- No hay alertas operativas internas visibles con los filtros actuales
- No hay plantillas visibles con los filtros actuales
- No hay historial visible con los filtros actuales
- No hay alertas críticas visibles en este momento

---

# 21. Reglas visuales específicas del módulo Notificaciones

- la Bandeja general de notificaciones debe sentirse como la vista principal del módulo
- el panel derecho debe mantener claridad sobre contexto, estado y acción siguiente
- Notificaciones al cliente debe sentirse orientado a servicio y continuidad, no a chat
- Notificaciones operativas internas debe verse sobrio y técnico
- Campañas y plantillas debe verse utilitario y reutilizable, no como marketing pesado
- Historial de envíos y respuestas debe sentirse consultivo y trazable
- Preferencias de notificación debe verse claro y parametrizable
- Alertas críticas y priorizadas debe comunicar urgencia sin volverse caótico
- no abusar de formas redondeadas ni de tarjetas decorativas
- la prioridad debe ser ordenar la comunicación y las alertas del sistema óptico

---

# 22. Relación del módulo Notificaciones con otros módulos

Notificaciones debe conectarse con:
- Agenda, porque recordatorios de cita y reprogramaciones pueden originarse allí
- Seguimiento, porque recalls, follow-ups y mensajes de continuidad se apoyan en ese flujo
- Entregas, porque trabajos listos o no retirados generan avisos directos
- Caja, porque saldos pendientes pueden disparar notificaciones de cobro
- Inventario, porque stock crítico puede generar alertas internas
- Órdenes de laboratorio, porque retrasos y estados de trabajo pueden generar avisos operativos
- Inicio, porque parte de estas alertas y avisos pueden reflejarse como resumen en el panel principal

---

# 23. Cierre del módulo Notificaciones

Este módulo debe transmitir que la óptica no deja que los eventos importantes se pierdan. Debe verse como una herramienta que organiza avisos, recordatorios, mensajes y alertas, dejando claro qué ocurrió, a quién afecta, por qué canal se comunicó y qué acción corresponde ejecutar. No se trata solo de “avisar”, sino de mantener continuidad operativa y trazabilidad de comunicación dentro del negocio óptico.

La complejidad correcta del módulo está en que cada sub-vista resuelve una parte clara del flujo de avisos sin convertir la experiencia en una plataforma de mensajería pesada o un centro de alertas caótico.