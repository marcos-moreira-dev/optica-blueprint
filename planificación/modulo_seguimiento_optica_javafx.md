# Lienzo del módulo Seguimiento para sistema de óptica en JavaFX

## 1. Identidad del módulo

### Nombre oficial del módulo en el menú
**Seguimiento**

### Texto visible del botón del sidebar
**Seguimiento**

### Tooltip del botón del sidebar
**Consultar recalls, pendientes, recordatorios, no retirados y casos que requieren contacto**

### Ícono conceptual
Campana, recordatorio, contacto o seguimiento de cliente.

### Título visible en pantalla
**Seguimiento**

### Subtítulo visible en pantalla
**Continuidad de atención, recordatorios, casos pendientes y contacto con clientes**

### Tipo de módulo
Módulo operativo de continuidad y relación con el cliente, con sub-vistas internas especializadas.

### Objetivo del módulo
Permitir que la óptica controle qué clientes requieren contacto, por qué motivo, en qué estado está cada caso y qué acción debe ejecutarse para mantener continuidad comercial, clínica u operativa.

---

## 2. Rol del módulo dentro de la arquitectura GUI

Seguimiento no debe sentirse como un CRM genérico. Debe verse como el punto donde convergen recalls, recordatorios, no retirados, saldos pendientes y observaciones de continuidad.

### Regla arquitectónica principal
Cuando el usuario entre al módulo Seguimiento, la región central principal del sistema debe reemplazarse por una vista completa llamada conceptualmente:
**SeguimientoModuleView**

### Estructura interna limpia del módulo
La vista Seguimiento debe dividirse en:
- encabezado del módulo
- barra de filtros y búsqueda
- subnavegación interna del módulo
- región central intercambiable para sub-vistas
- panel lateral derecho con resumen persistente del caso o cliente seleccionado

### Filosofía de implementación
El usuario debe poder revisar casos, registrar contacto, resolver pendientes o derivar hacia otros módulos sin perder el contexto del cliente.

### Regla de arquitectura limpia
Debe existir una región interna estable que se reescriba solo con el submódulo activo. Esa región se denomina conceptualmente:
**seguimientoContentHostPane**

Ese contenedor puede ser un **StackPane** o el centro de un **BorderPane** secundario.

### Sub-vistas que viven dentro de seguimientoContentHostPane
- Bandeja de seguimiento
- Recall y revisiones
- Pedidos listos y no retirados
- Cobros pendientes
- Mensajes y recordatorios
- Histórico de seguimiento

---

## 3. Sub-vistas oficiales del módulo Seguimiento

## Sub-vistas definidas
1. **Bandeja de seguimiento**
2. **Recall y revisiones**
3. **Pedidos listos y no retirados**
4. **Cobros pendientes**
5. **Mensajes y recordatorios**
6. **Histórico de seguimiento**

## Orden de prioridad
1. Bandeja de seguimiento
2. Recall y revisiones
3. Pedidos listos y no retirados
4. Cobros pendientes
5. Mensajes y recordatorios
6. Histórico de seguimiento

## Vista por defecto al abrir el módulo
**Bandeja de seguimiento**

Motivo: es la vista que mejor responde a la pregunta operativa del día: qué clientes o casos requieren acción ahora mismo.

---

## 4. Estructura visual general del módulo Seguimiento

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
- panel derecho: resumen persistente del caso o cliente seleccionado

### Proporción recomendada
- región de trabajo: 68% a 72%
- panel derecho: 28% a 32%

### Regla de estabilidad visual
El panel derecho debe mantenerse visible durante casi toda la navegación para que el usuario no pierda el contexto del seguimiento activo.

---

## 5. Encabezado del módulo Seguimiento

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
- Label principal: **Seguimiento**
- Label secundario: **Continuidad de atención, recordatorios, casos pendientes y contacto con clientes**

### Zona derecha
Un **HBox** con:
- Button secundario: **Actualizar seguimiento**
- Button secundario: **Exportar seguimiento**
- Button principal: **Nuevo seguimiento**

## Controles exactos

### Label principal
- Control: **Label**
- Texto exacto: **Seguimiento**
- Tooltip: no necesita

### Label secundario
- Control: **Label**
- Texto exacto: **Continuidad de atención, recordatorios, casos pendientes y contacto con clientes**
- Tooltip: no necesita

### Botón secundario 1
- Control: **Button**
- Texto exacto: **Actualizar seguimiento**
- Tooltip: **Recargar los casos visibles del módulo Seguimiento**
- Estilo: secundario

### Botón secundario 2
- Control: **Button**
- Texto exacto: **Exportar seguimiento**
- Tooltip: **Exportar los casos visibles según los filtros aplicados**
- Estilo: secundario

### Botón principal
- Control: **Button**
- Texto exacto: **Nuevo seguimiento**
- Tooltip: **Registrar manualmente un nuevo caso de seguimiento**
- Estilo: acción principal

---

## 7. Franja media: filtros y búsqueda

## Contenedor sugerido
**FlowPane**

## Función
Permitir localizar casos por cliente, orden, motivo, estado, prioridad o sucursal.

## Controles oficiales

### Búsqueda rápida
- Control: **TextField**
- Label asociado: **Buscar**
- Placeholder exacto: **Cliente, orden, motivo o referencia**
- Tooltip: **Buscar seguimientos por cliente, orden, motivo o texto relacionado**

### Filtro por tipo de seguimiento
- Control: **ComboBox**
- Label asociado: **Tipo**
- Ítems de semilla:
  - Todos
  - Recall anual
  - Recordatorio de cita
  - Trabajo listo sin retiro
  - Saldo pendiente
  - Revisión por molestia inicial
  - Vencimiento de receta
  - Contacto general
- Valor inicial: **Todos**
- Tooltip: **Filtrar casos por tipo de seguimiento**

### Filtro por estado
- Control: **ComboBox**
- Label asociado: **Estado**
- Ítems de semilla:
  - Todos
  - Pendiente
  - En contacto
  - Recordatorio enviado
  - Recall pendiente
  - Sin respuesta
  - Reprogramado
  - Resuelto
  - Cerrado
  - No retirado
  - Con saldo pendiente
- Valor inicial: **Todos**
- Tooltip: **Filtrar casos según el estado actual del seguimiento**

### Filtro por prioridad
- Control: **ComboBox**
- Label asociado: **Prioridad**
- Ítems de semilla:
  - Todas
  - Alta
  - Media
  - Baja
- Valor inicial: **Todas**
- Tooltip: **Filtrar según prioridad operativa del caso**

### Filtro por sucursal
- Control: **ComboBox**
- Label asociado: **Sucursal**
- Ítems de semilla:
  - Todas
  - Matriz Centro
  - Norte Mall
- Valor inicial: **Todas**
- Tooltip: **Filtrar seguimientos por sucursal asociada**

### Filtro por canal
- Control: **ComboBox**
- Label asociado: **Canal**
- Ítems de semilla:
  - Todos
  - SMS
  - WhatsApp
  - Llamada
  - Correo
  - Interno
- Valor inicial: **Todos**
- Tooltip: **Filtrar por canal de contacto o recordatorio**

### Filtro por fecha inicial
- Control: **DatePicker**
- Label asociado: **Desde**
- Tooltip: **Seleccionar la fecha inicial para consultar seguimientos**

### Filtro por fecha final
- Control: **DatePicker**
- Label asociado: **Hasta**
- Tooltip: **Seleccionar la fecha final para consultar seguimientos**

### Opción rápida
- Control: **CheckBox**
- Texto exacto: **Solo casos abiertos**
- Tooltip: **Mostrar únicamente casos pendientes, sin resolver o en curso**

### Botón limpiar
- Control: **Button**
- Texto exacto: **Limpiar filtros**
- Tooltip: **Restablecer la búsqueda y todos los filtros del módulo**

---

## 8. Franja inferior: subnavegación interna del módulo

## Contenedor sugerido
**HBox** con **ToggleButton** dentro de un **ToggleGroup**

## Sub-vistas visibles en la barra
- Bandeja de seguimiento
- Recall y revisiones
- Pedidos listos y no retirados
- Cobros pendientes
- Mensajes y recordatorios
- Histórico de seguimiento

## Tooltips exactos
- Bandeja de seguimiento: **Consultar todos los casos que requieren acción o revisión**
- Recall y revisiones: **Consultar clientes que requieren revisión, recall o nuevo control**
- Pedidos listos y no retirados: **Consultar trabajos listos que siguen pendientes de retiro**
- Cobros pendientes: **Consultar casos vinculados a saldos o pagos incompletos**
- Mensajes y recordatorios: **Consultar mensajes, recordatorios y resultado del contacto**
- Histórico de seguimiento: **Consultar casos cerrados o históricos por cliente, fecha o motivo**

## Regla visual
La sub-vista activa debe quedar clara, pero sin competir con el contenido operativo.

---

## 9. Cuerpo principal del módulo Seguimiento

## Contenedor sugerido
**SplitPane** horizontal

### Panel izquierdo o principal
Sub-vista activa del módulo.

### Panel derecho
Resumen persistente del caso o cliente seleccionado.

---

# 10. Panel derecho persistente: resumen del caso

## Función
Mantener el contexto del caso de seguimiento seleccionado mientras se navega por el módulo.

## Contenedor sugerido
**ScrollPane** con un **VBox** interno.

## Bloques internos del panel derecho
1. Identidad del caso
2. Contexto del cliente
3. Estado del seguimiento
4. Próxima acción
5. Acciones rápidas

## 10.1. Bloque: Identidad del caso

### Campos visibles
- Referencia
- Tipo de seguimiento
- Prioridad

### Seeds
- **Referencia: SG-021**
- **Tipo de seguimiento: Recall anual**
- **Prioridad: Media**

## 10.2. Bloque: Contexto del cliente

### Campos visibles
- Cliente
- Código del cliente
- Última visita
- Sucursal

### Seeds
- **Cliente: Sofía Ramírez**
- **Código del cliente: CL-00124**
- **Última visita: 12/04/2026**
- **Sucursal: Matriz Centro**

## 10.3. Bloque: Estado del seguimiento

### Campos visibles
- Estado actual
- Canal de contacto
- Última interacción

### Seeds
- **Estado actual: Recall pendiente**
- **Canal de contacto: WhatsApp**
- **Última interacción: 14/04/2026**

## 10.4. Bloque: Próxima acción

### Campos visibles
- Acción sugerida
- Fecha objetivo
- Observación clave

### Seeds
- **Acción sugerida: Enviar recordatorio de revisión**
- **Fecha objetivo: 18/04/2026**
- **Observación clave: Cliente suele responder en horario vespertino**

## 10.5. Bloque: Acciones rápidas

### Botones oficiales
- **Registrar contacto**
- **Marcar resuelto**
- **Agendar cita**
- **Abrir cliente**
- **Abrir orden**

### Tooltips exactos
- Registrar contacto: **Registrar una nueva interacción con el cliente**
- Marcar resuelto: **Cerrar el caso de seguimiento seleccionado**
- Agendar cita: **Programar una nueva cita vinculada a este caso**
- Abrir cliente: **Abrir la ficha del cliente relacionado**
- Abrir orden: **Abrir la orden asociada si existe**

---

# 11. Sub-vista 1: Bandeja de seguimiento

## Función
Servir como bandeja principal de casos abiertos o relevantes que requieren acción.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: pequeño resumen operativo
- **center**: **TableView** principal
- **bottom**: resumen de resultados visibles

## 11.1. Franja superior del listado

### Contenido
- Label: **Casos activos de seguimiento**
- Label secundario: **23 casos visibles**

### Tooltips
- Casos activos de seguimiento: **Casos visibles que requieren acción, contacto o revisión**
- 23 casos visibles: **Cantidad total de casos mostrados actualmente**

## 11.2. TableView principal

### Columnas oficiales
- Referencia
- Cliente
- Tipo
- Estado
- Fecha objetivo
- Prioridad
- Sucursal
- Responsable

### Seeds oficiales
1. SG-021 | Sofía Ramírez | Recall anual | Recall pendiente | 18/04/2026 | Media | Matriz Centro | Asesor Molina
2. SG-022 | Diana Vélez | Trabajo listo sin retiro | No retirado | 16/04/2026 | Alta | Norte Mall | Laura Gómez
3. SG-023 | Carlos Mendoza | Saldo pendiente | Con saldo pendiente | 17/04/2026 | Alta | Matriz Centro | Laura Gómez
4. SG-024 | Ana Vera | Revisión por molestia inicial | Pendiente | 19/04/2026 | Media | Matriz Centro | Dra. Salazar

### Tooltip de la tabla
**Seleccione un caso para ver contexto, registrar contacto o resolver la gestión**

### Estado vacío
**No hay casos visibles con los filtros actuales**

## 11.3. Pie del listado

### Textos exactos
- **Mostrando 4 de 23 casos**
- **Ordenado por fecha objetivo y prioridad**

### Tooltips
- Mostrando 4 de 23 casos: **Resumen de casos visibles en la bandeja actual**
- Ordenado por fecha objetivo y prioridad: **Criterio de ordenamiento aplicado a la bandeja**

---

# 12. Sub-vista 2: Recall y revisiones

## Función
Consultar clientes que requieren revisión, examen de control o renovación por vencimiento de receta o tiempo transcurrido.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: resumen de recalls
- **center**: **TableView** principal

## 12.1. Resumen superior

### Campos visibles
- Recall anual pendiente
- Recetas vencidas
- Revisiones próximas

### Seeds
- **Recall anual pendiente: 11**
- **Recetas vencidas: 6**
- **Revisiones próximas: 4**

## 12.2. TableView principal

### Columnas oficiales
- Cliente
- Última visita
- Motivo
- Fecha sugerida
- Estado
- Sucursal

### Seeds oficiales
1. Sofía Ramírez | 12/04/2025 | Recall anual | 18/04/2026 | Recall pendiente | Matriz Centro
2. Luis Andrade | 10/11/2025 | Receta vencida | 20/04/2026 | Pendiente | Norte Mall
3. Ana Vera | 02/01/2026 | Control recomendado | 22/04/2026 | Recordatorio enviado | Matriz Centro

### Tooltip de la tabla
**Clientes que requieren revisión, recall o control posterior**

### Botones oficiales del submódulo
- **Enviar recordatorio**
- **Agendar revisión**
- **Abrir cliente**

### Tooltips
- Enviar recordatorio: **Registrar o ejecutar un recordatorio para revisión o recall**
- Agendar revisión: **Crear una cita a partir del caso seleccionado**
- Abrir cliente: **Consultar la ficha del cliente relacionado**

### Estado vacío
**No hay recalls ni revisiones pendientes en este momento**

---

# 13. Sub-vista 3: Pedidos listos y no retirados

## Función
Consultar trabajos listos que siguen pendientes de retiro o requieren recontacto.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: resumen de casos no retirados
- **center**: **TableView** principal

## 13.1. Resumen superior

### Campos visibles
- Trabajos no retirados
- No retirados notificados
- No retirados con saldo

### Seeds
- **Trabajos no retirados: 7**
- **No retirados notificados: 5**
- **No retirados con saldo: 2**

## 13.2. TableView principal

### Columnas oficiales
- Orden
- Cliente
- Días esperando
- Notificación
- Saldo
- Estado
- Sucursal

### Seeds oficiales
1. ET-044 | Diana Vélez | 1 día | Notificado por SMS | $ 0.00 | No retirado | Norte Mall
2. ET-045 | Carlos Mendoza | 3 días | Recordatorio enviado | $ 18.00 | Con saldo pendiente | Matriz Centro
3. ET-046 | Ana Vera | 2 días | Pendiente de notificación | $ 0.00 | Pendiente | Norte Mall

### Tooltip de la tabla
**Trabajos listos que aún no han sido retirados o requieren nueva gestión**

### Botones oficiales del submódulo
- **Notificar cliente**
- **Registrar retiro**
- **Marcar seguimiento**

### Tooltips
- Notificar cliente: **Registrar o ejecutar una notificación al cliente**
- Registrar retiro: **Registrar la entrega si el cliente ya retira el trabajo**
- Marcar seguimiento: **Mantener el caso activo para una nueva acción**

### Estado vacío
**No hay trabajos no retirados visibles con los filtros actuales**

---

# 14. Sub-vista 4: Cobros pendientes

## Función
Consultar casos donde el cliente requiere contacto por saldo pendiente, pago parcial o cobro vencido.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: resumen de cobros vinculados a seguimiento
- **center**: **TableView** principal

## 14.1. Resumen superior

### Campos visibles
- Casos con saldo
- Monto pendiente total
- Casos vencidos

### Seeds
- **Casos con saldo: 5**
- **Monto pendiente total: $ 412.00**
- **Casos vencidos: 2**

## 14.2. TableView principal

### Columnas oficiales
- Orden
- Cliente
- Saldo
- Último pago
- Estado
- Próxima acción
- Sucursal

### Seeds oficiales
1. OV-124 | Sofía Ramírez | $ 55.00 | 12/04/2026 | Abono parcial | Contactar para completar pago | Matriz Centro
2. OV-133 | María León | $ 153.00 | 05/04/2026 | Vencido | Registrar llamada | Norte Mall
3. OV-131 | Diana Vélez | $ 56.00 | 11/04/2026 | Abono parcial | Enviar recordatorio | Matriz Centro

### Tooltip de la tabla
**Casos de seguimiento asociados a saldos pendientes o pagos incompletos**

### Botones oficiales del submódulo
- **Registrar contacto**
- **Abrir caja**
- **Marcar resuelto**

### Tooltips
- Registrar contacto: **Registrar una nueva interacción relacionada con el cobro**
- Abrir caja: **Abrir el módulo Caja para ver el detalle económico**
- Marcar resuelto: **Cerrar el caso si el cobro ya fue resuelto**

### Estado vacío
**No hay casos de cobro pendientes en este momento**

---

# 15. Sub-vista 5: Mensajes y recordatorios

## Función
Consultar y registrar mensajes, recordatorios y resultado de comunicaciones con clientes.

## Contenedor principal sugerido
**SplitPane** horizontal

### Panel izquierdo
**TableView** de mensajes y recordatorios

### Panel derecho
Detalle del contacto o recordatorio seleccionado

## 15.1. TableView principal

### Columnas oficiales
- Fecha
- Cliente
- Tipo
- Canal
- Estado
- Resultado

### Seeds oficiales
1. 14/04/2026 | Sofía Ramírez | Recall anual | WhatsApp | Recordatorio enviado | Pendiente de respuesta
2. 14/04/2026 | Diana Vélez | Trabajo listo | SMS | Enviado | Cliente informado
3. 15/04/2026 | Carlos Mendoza | Saldo pendiente | Llamada | En contacto | Solicita pagar mañana

### Tooltip de la tabla
**Mensajes y recordatorios enviados o registrados para los clientes**

## 15.2. Detalle del contacto

### Campos visibles
- Cliente
- Tipo
- Canal
- Fecha
- Estado
- Resultado
- Nota de contacto

### Seeds
- Cliente: **Sofía Ramírez**
- Tipo: **Recall anual**
- Canal: **WhatsApp**
- Fecha: **14/04/2026**
- Estado: **Recordatorio enviado**
- Resultado: **Pendiente de respuesta**
- Nota de contacto: **Se envió recordatorio para revisión anual**

### Botones oficiales del submódulo
- **Registrar mensaje**
- **Marcar respondido**
- **Reenviar recordatorio**

### Tooltips
- Registrar mensaje: **Registrar un nuevo mensaje o contacto manual**
- Marcar respondido: **Actualizar el estado del contacto como respondido**
- Reenviar recordatorio: **Registrar o reenviar el recordatorio seleccionado**

### Estado vacío
**No hay mensajes ni recordatorios visibles con los filtros actuales**

---

# 16. Sub-vista 6: Histórico de seguimiento

## Función
Consultar casos cerrados, resueltos o históricos mediante filtros amplios.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: filtros extendidos
- **center**: **TableView** de resultados

## 16.1. Filtros extendidos

### Controles sugeridos
- TextField: **Cliente, orden o motivo**
- ComboBox: **Tipo**
- ComboBox: **Estado**
- ComboBox: **Sucursal**
- ComboBox: **Canal**
- DatePicker: **Desde**
- DatePicker: **Hasta**
- Button: **Buscar histórico**
- Button: **Limpiar filtros**

### Tooltip general
**Filtre seguimientos históricos por cliente, motivo, estado, canal o fecha**

## 16.2. TableView principal

### Columnas oficiales
- Fecha
- Referencia
- Cliente
- Tipo
- Estado final
- Resultado
- Sucursal

### Seeds oficiales
1. 10/04/2026 | SG-011 | María León | Recall anual | Cerrado | Cita agendada | Matriz Centro
2. 09/04/2026 | SG-012 | Juan Cedeño | Trabajo listo sin retiro | Resuelto | Retiro confirmado | Norte Mall
3. 08/04/2026 | SG-013 | Carlos Mendoza | Saldo pendiente | Cerrado | Pago completado | Matriz Centro

### Tooltip de la tabla
**Consulte seguimientos históricos por cliente, motivo o fecha**

### Botones oficiales del submódulo
- **Abrir caso**
- **Exportar histórico**

### Tooltips
- Abrir caso: **Abrir el registro histórico seleccionado**
- Exportar histórico: **Exportar el histórico visible de seguimientos**

### Estado vacío
**No hay seguimientos históricos que coincidan con los criterios actuales**

---

# 17. Formulario conceptual: Nuevo seguimiento

## Función
Permitir registrar manualmente un nuevo caso de seguimiento sin salir de la arquitectura del módulo.

## Contenedor sugerido
Vista interna o diálogo amplio controlado. Para esta maqueta conviene resolverlo dentro del mismo módulo.

## Estructura sugerida
**ScrollPane** con **VBox** y bloques en **GridPane**.

### Grupos de campos
1. Cliente y referencia
2. Tipo y motivo
3. Prioridad y fecha objetivo
4. Canal y observación inicial

### Campos mínimos
- Cliente
- Referencia relacionada
- Tipo
- Motivo
- Prioridad
- Fecha objetivo
- Canal sugerido
- Observación inicial

### Botones finales
- **Guardar seguimiento**
- **Cancelar**

### Tooltips
- Guardar seguimiento: **Registrar el nuevo caso de seguimiento**
- Cancelar: **Cerrar el registro de seguimiento sin guardar**

---

# 18. Seed data oficial del módulo Seguimiento

## Clientes de semilla
- Sofía Ramírez
- Diana Vélez
- Carlos Mendoza
- Ana Vera
- Luis Andrade
- María León
- Juan Cedeño

## Referencias de seguimiento
- SG-021
- SG-022
- SG-023
- SG-024
- SG-011
- SG-012
- SG-013

## Órdenes relacionadas
- OV-124
- OV-133
- OV-131
- ET-044
- ET-045
- ET-046

## Sucursales
- Matriz Centro
- Norte Mall

## Tipos de seguimiento
- Recall anual
- Recordatorio de cita
- Trabajo listo sin retiro
- Saldo pendiente
- Revisión por molestia inicial
- Vencimiento de receta
- Contacto general

## Canales
- SMS
- WhatsApp
- Llamada
- Correo
- Interno

## Estados usados en el módulo
- Pendiente
- En contacto
- Recordatorio enviado
- Recall pendiente
- Sin respuesta
- Reprogramado
- Resuelto
- Cerrado
- Prioridad alta
- No retirado
- Con saldo pendiente

---

# 19. Textos oficiales del módulo Seguimiento

## Títulos y labels principales
- Seguimiento
- Continuidad de atención, recordatorios, casos pendientes y contacto con clientes
- Buscar
- Tipo
- Estado
- Prioridad
- Sucursal
- Canal
- Desde
- Hasta
- Solo casos abiertos
- Bandeja de seguimiento
- Recall y revisiones
- Pedidos listos y no retirados
- Cobros pendientes
- Mensajes y recordatorios
- Histórico de seguimiento
- Referencia
- Cliente
- Última visita
- Fecha objetivo
- Próxima acción
- Última interacción
- Días esperando
- Notificación
- Saldo
- Resultado
- Motivo

## Botones oficiales
- Actualizar seguimiento
- Exportar seguimiento
- Nuevo seguimiento
- Limpiar filtros
- Registrar contacto
- Marcar resuelto
- Agendar cita
- Abrir cliente
- Abrir orden
- Enviar recordatorio
- Agendar revisión
- Notificar cliente
- Registrar retiro
- Marcar seguimiento
- Abrir caja
- Registrar mensaje
- Marcar respondido
- Reenviar recordatorio
- Buscar histórico
- Abrir caso
- Exportar histórico
- Guardar seguimiento
- Cancelar

## Placeholders
- Cliente, orden, motivo o referencia
- Cliente, orden o motivo

## Empty states
- No hay casos visibles con los filtros actuales
- No hay recalls ni revisiones pendientes en este momento
- No hay trabajos no retirados visibles con los filtros actuales
- No hay casos de cobro pendientes en este momento
- No hay mensajes ni recordatorios visibles con los filtros actuales
- No hay seguimientos históricos que coincidan con los criterios actuales

---

# 20. Reglas visuales específicas del módulo Seguimiento

- la bandeja de seguimiento debe sentirse como la vista central del módulo
- el panel derecho debe mantener claridad sobre motivo, estado y próxima acción
- Recall y revisiones debe sentirse preventivo y clínico, pero ligero
- Pedidos listos y no retirados debe comunicar continuidad operativa sin duplicar Entregas
- Cobros pendientes debe verse sobrio y orientado a acción, no como Caja
- Mensajes y recordatorios debe sentirse práctico, no como bandeja de chat compleja
- Histórico de seguimiento debe verse consultivo y ordenado
- no abusar de formas redondeadas ni de tarjetas decorativas
- la prioridad debe ser visibilidad de casos, acción rápida y continuidad con el cliente

---

# 21. Relación del módulo Seguimiento con otros módulos

Seguimiento debe conectarse con:
- Clientes, porque todos los casos viven alrededor de una ficha
- Agenda, porque recalls o recordatorios pueden terminar en nueva cita
- Entregas, porque no retirados o ajustes post-entrega pueden generar seguimiento
- Caja, porque saldos pendientes pueden requerir contacto
- Inicio, porque el panel principal puede resumir casos abiertos o prioritarios
- Sucursales, porque el seguimiento debe distinguir la sede asociada al cliente o a la orden

---

# 22. Cierre del módulo Seguimiento

Este módulo debe transmitir que la óptica no deja clientes ni casos en el aire. Debe verse como una herramienta que permite saber qué persona requiere contacto, por qué motivo, en qué estado está el caso y qué acción corresponde ejecutar. No se trata de un CRM genérico, sino de continuidad real del servicio óptico.

La complejidad correcta del módulo está en que cada sub-vista resuelve un tipo concreto de seguimiento sin convertir la experiencia en una plataforma pesada de campañas o mensajería.