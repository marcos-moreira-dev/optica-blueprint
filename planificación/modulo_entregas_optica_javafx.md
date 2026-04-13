# Lienzo del módulo Entregas para sistema de óptica en JavaFX

## 1. Identidad del módulo

### Nombre oficial del módulo en el menú
**Entregas**

### Texto visible del botón del sidebar
**Entregas**

### Tooltip del botón del sidebar
**Consultar trabajos listos, validar retiro, registrar entrega y controlar pendientes**

### Ícono conceptual
Paquete, retiro, check de entrega o validación final.

### Título visible en pantalla
**Entregas**

### Subtítulo visible en pantalla
**Cierre operativo del trabajo óptico: validación, retiro, ajuste y control de pendientes**

### Tipo de módulo
Módulo operativo de cierre y retiro con sub-vistas internas especializadas.

### Objetivo del módulo
Permitir que la óptica controle qué trabajos están listos, cuáles pueden ser entregados, qué pendientes existen, si hay saldo por cobrar, quién retiró el trabajo y qué observaciones o ajustes se registraron después de la entrega.

---

## 2. Rol del módulo dentro de la arquitectura GUI

Entregas no debe sentirse como una simple lista de “listo / entregado”. Debe verse como el punto de cierre operativo del trabajo óptico.

### Regla arquitectónica principal
Cuando el usuario entre al módulo Entregas, la región central principal del sistema debe reemplazarse por una vista completa llamada conceptualmente:
**EntregasModuleView**

### Estructura interna limpia del módulo
La vista Entregas debe dividirse en:
- encabezado del módulo
- barra de filtros y búsqueda
- subnavegación interna del módulo
- región central intercambiable para sub-vistas
- panel lateral derecho con resumen persistente del trabajo seleccionado

### Filosofía de implementación
El usuario debe poder revisar un trabajo, validar si está listo, confirmar el retiro y registrar observaciones sin abandonar el módulo ni perder el contexto de la orden.

### Regla de arquitectura limpia
Debe existir una región interna estable que se reescriba solo con el submódulo activo. Esa región se denomina conceptualmente:
**entregasContentHostPane**

Ese contenedor puede ser un **StackPane** o el centro de un **BorderPane** secundario.

### Sub-vistas que viven dentro de entregasContentHostPane
- Lista de trabajos listos
- Validación previa a entrega
- Registro de retiro / entrega
- Pendientes de retiro
- Ajustes y observaciones post-entrega
- Histórico de entregas

---

## 3. Sub-vistas oficiales del módulo Entregas

## Sub-vistas definidas
1. **Lista de trabajos listos**
2. **Validación previa a entrega**
3. **Registro de retiro / entrega**
4. **Pendientes de retiro**
5. **Ajustes y observaciones post-entrega**
6. **Histórico de entregas**

## Orden de prioridad
1. Lista de trabajos listos
2. Validación previa a entrega
3. Registro de retiro / entrega
4. Pendientes de retiro
5. Ajustes y observaciones post-entrega
6. Histórico de entregas

## Vista por defecto al abrir el módulo
**Lista de trabajos listos**

Motivo: es la vista que mejor responde a la pregunta operativa del día: qué trabajos pueden entregarse ya.

---

## 4. Estructura visual general del módulo Entregas

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
- panel derecho: resumen persistente del trabajo seleccionado

### Proporción recomendada
- región de trabajo: 68% a 72%
- panel derecho: 28% a 32%

### Regla de estabilidad visual
El panel derecho debe mantenerse visible durante casi toda la navegación para que el usuario no pierda el contexto del trabajo listo, pendiente o entregado.

---

## 5. Encabezado del módulo Entregas

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
- Label principal: **Entregas**
- Label secundario: **Cierre operativo del trabajo óptico: validación, retiro, ajuste y control de pendientes**

### Zona derecha
Un **HBox** con:
- Button secundario: **Actualizar entregas**
- Button secundario: **Exportar entregas**
- Button principal: **Registrar retiro rápido**

## Controles exactos

### Label principal
- Control: **Label**
- Texto exacto: **Entregas**
- Tooltip: no necesita

### Label secundario
- Control: **Label**
- Texto exacto: **Cierre operativo del trabajo óptico: validación, retiro, ajuste y control de pendientes**
- Tooltip: no necesita

### Botón secundario 1
- Control: **Button**
- Texto exacto: **Actualizar entregas**
- Tooltip: **Recargar los trabajos listos, pendientes y entregados visibles en el módulo**
- Estilo: secundario

### Botón secundario 2
- Control: **Button**
- Texto exacto: **Exportar entregas**
- Tooltip: **Exportar los registros visibles del módulo Entregas**
- Estilo: secundario

### Botón principal
- Control: **Button**
- Texto exacto: **Registrar retiro rápido**
- Tooltip: **Registrar rápidamente la entrega de un trabajo listo**
- Estilo: acción principal

---

## 7. Franja media: filtros y búsqueda

## Contenedor sugerido
**FlowPane**

## Función
Permitir localizar trabajos por orden, cliente, estado, sucursal o situación de retiro.

## Controles oficiales

### Búsqueda rápida
- Control: **TextField**
- Label asociado: **Buscar**
- Placeholder exacto: **Orden, cliente o referencia**
- Tooltip: **Buscar trabajos por orden, cliente o texto relacionado**

### Filtro por estado de entrega
- Control: **ComboBox**
- Label asociado: **Estado**
- Ítems de semilla:
  - Todos
  - Lista para entrega
  - Pendiente de validación
  - Pendiente de retiro
  - Notificado
  - Entregada
  - Reprogramada
  - Con ajuste pendiente
  - Con saldo pendiente
  - No retirada
- Valor inicial: **Todos**
- Tooltip: **Filtrar trabajos según su estado actual de entrega**

### Filtro por sucursal
- Control: **ComboBox**
- Label asociado: **Sucursal**
- Ítems de semilla:
  - Todas
  - Matriz Centro
  - Norte Mall
- Valor inicial: **Todas**
- Tooltip: **Filtrar entregas por sucursal de retiro o recepción**

### Filtro por notificación
- Control: **ComboBox**
- Label asociado: **Notificación**
- Ítems de semilla:
  - Todas
  - Pendiente de notificación
  - Notificado por SMS
  - Recordatorio enviado
- Valor inicial: **Todas**
- Tooltip: **Filtrar trabajos según el estado de comunicación con el cliente**

### Filtro por fecha inicial
- Control: **DatePicker**
- Label asociado: **Desde**
- Tooltip: **Seleccionar la fecha inicial para consultar entregas**

### Filtro por fecha final
- Control: **DatePicker**
- Label asociado: **Hasta**
- Tooltip: **Seleccionar la fecha final para consultar entregas**

### Opción rápida
- Control: **CheckBox**
- Texto exacto: **Solo con saldo pendiente**
- Tooltip: **Mostrar únicamente trabajos que no pueden cerrarse por saldo pendiente**

### Botón limpiar
- Control: **Button**
- Texto exacto: **Limpiar filtros**
- Tooltip: **Restablecer la búsqueda y todos los filtros del módulo**

---

## 8. Franja inferior: subnavegación interna del módulo

## Contenedor sugerido
**HBox** con **ToggleButton** dentro de un **ToggleGroup**

## Sub-vistas visibles en la barra
- Lista de trabajos listos
- Validación previa a entrega
- Registro de retiro / entrega
- Pendientes de retiro
- Ajustes y observaciones post-entrega
- Histórico de entregas

## Tooltips exactos
- Lista de trabajos listos: **Consultar trabajos disponibles para entregar**
- Validación previa a entrega: **Verificar conformidad y condiciones antes del retiro**
- Registro de retiro / entrega: **Registrar la entrega o retiro del trabajo por el cliente**
- Pendientes de retiro: **Consultar trabajos listos que aún no han sido retirados**
- Ajustes y observaciones post-entrega: **Registrar ajustes, molestias u observaciones después del retiro**
- Histórico de entregas: **Consultar entregas registradas, reprogramadas o no retiradas**

## Regla visual
La sub-vista activa debe quedar clara, pero sin competir con la lectura operativa del trabajo.

---

## 9. Cuerpo principal del módulo Entregas

## Contenedor sugerido
**SplitPane** horizontal

### Panel izquierdo o principal
Sub-vista activa del módulo.

### Panel derecho
Resumen persistente del trabajo seleccionado.

---

# 10. Panel derecho persistente: resumen del trabajo

## Función
Mantener el contexto operativo del trabajo seleccionado mientras se navega por el módulo.

## Contenedor sugerido
**ScrollPane** con un **VBox** interno.

## Bloques internos del panel derecho
1. Identidad de la orden
2. Contexto del cliente
3. Estado de entrega
4. Contexto económico
5. Acciones rápidas

## 10.1. Bloque: Identidad de la orden

### Campos visibles
- Referencia
- Tipo de trabajo
- Sucursal

### Seeds
- **Referencia: ET-041**
- **Tipo de trabajo: Montura + lente**
- **Sucursal: Matriz Centro**

## 10.2. Bloque: Contexto del cliente

### Campos visibles
- Cliente
- Código del cliente
- Fecha promesa
- Fecha de recepción

### Seeds
- **Cliente: Sofía Ramírez**
- **Código del cliente: CL-00124**
- **Fecha promesa: 16/04/2026**
- **Fecha de recepción: 15/04/2026**

## 10.3. Bloque: Estado de entrega

### Campos visibles
- Estado actual
- Notificación
- Prioridad

### Seeds
- **Estado actual: Lista para entrega**
- **Notificación: Notificado por SMS**
- **Prioridad: Media**

## 10.4. Bloque: Contexto económico

### Campos visibles
- Saldo pendiente
- Estado de cobro

### Seeds
- **Saldo pendiente: $ 0.00**
- **Estado de cobro: Pagado**

## 10.5. Bloque: Acciones rápidas

### Botones oficiales
- **Abrir detalle**
- **Validar entrega**
- **Registrar retiro**
- **Notificar cliente**
- **Ver saldo**

### Tooltips exactos
- Abrir detalle: **Abrir el detalle completo del trabajo seleccionado**
- Validar entrega: **Verificar que el trabajo esté listo y conforme para retirar**
- Registrar retiro: **Registrar formalmente el retiro o entrega del trabajo**
- Notificar cliente: **Registrar o ejecutar una notificación al cliente**
- Ver saldo: **Consultar el estado de pago y saldo de la orden**

---

# 11. Sub-vista 1: Lista de trabajos listos

## Función
Servir como bandeja principal de trabajos que ya pueden pasar al retiro o entrega.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: pequeño resumen operativo
- **center**: **TableView** principal
- **bottom**: resumen de resultados visibles

## 11.1. Franja superior del listado

### Contenido
- Label: **Trabajos listos**
- Label secundario: **14 trabajos visibles**

### Tooltips
- Trabajos listos: **Trabajos disponibles para validación, retiro o entrega**
- 14 trabajos visibles: **Cantidad de trabajos listos mostrados actualmente**

## 11.2. TableView principal

### Columnas oficiales
- Referencia
- Cliente
- Tipo de trabajo
- Fecha promesa
- Estado
- Saldo
- Sucursal
- Notificación

### Seeds oficiales
1. ET-041 | Sofía Ramírez | Montura + lente | 16/04/2026 | Lista para entrega | $ 0.00 | Matriz Centro | Notificado por SMS
2. ET-042 | Ana Vera | Lentes monofocales | 16/04/2026 | Pendiente de validación | $ 0.00 | Norte Mall | Pendiente de notificación
3. ET-043 | Luis Andrade | Cambio de lente | 17/04/2026 | Con saldo pendiente | $ 55.00 | Matriz Centro | Recordatorio enviado
4. ET-044 | Diana Vélez | Progresivos | 15/04/2026 | Pendiente de retiro | $ 0.00 | Norte Mall | Notificado por SMS

### Tooltip de la tabla
**Seleccione un trabajo para ver su estado de entrega, saldo y validación**

### Estado vacío
**No hay trabajos listos visibles con los filtros actuales**

## 11.3. Pie del listado

### Textos exactos
- **Mostrando 4 de 14 trabajos**
- **Ordenado por fecha promesa**

### Tooltips
- Mostrando 4 de 14 trabajos: **Resumen de trabajos visibles en la bandeja actual**
- Ordenado por fecha promesa: **Criterio de ordenamiento aplicado a la lista**

---

# 12. Sub-vista 2: Validación previa a entrega

## Función
Verificar que el trabajo esté correcto y listo antes del retiro del cliente.

## Contenedor principal sugerido
**ScrollPane** con un **VBox** interno.

### Estructura interna
1. Resumen del trabajo
2. Validación técnica
3. Validación económica
4. Confirmación final

## 12.1. Resumen del trabajo

### Campos visibles
- Referencia
- Cliente
- Tipo de trabajo
- Fecha promesa
- Sucursal

### Seeds
- **ET-041**
- **Sofía Ramírez**
- **Montura + lente**
- **16/04/2026**
- **Matriz Centro**

## 12.2. Validación técnica

### Campos visibles
- Trabajo recibido
- Montaje conforme
- Requiere ajuste
- Observación técnica breve

### Seeds
- Trabajo recibido: **Sí**
- Montaje conforme: **Sí**
- Requiere ajuste: **No**
- Observación técnica breve: **Conforme para entrega**

## 12.3. Validación económica

### Campos visibles
- Estado de pago
- Saldo pendiente

### Seeds
- Estado de pago: **Pagado**
- Saldo pendiente: **$ 0.00**

## 12.4. Botones oficiales del submódulo
- **Marcar validado**
- **Registrar ajuste**
- **Bloquear entrega**

### Tooltips
- Marcar validado: **Confirmar que el trabajo está listo para retiro**
- Registrar ajuste: **Registrar que el trabajo requiere ajuste antes de entregar**
- Bloquear entrega: **Impedir temporalmente la entrega por incidencia o pendiente**

### Estado vacío
**Seleccione un trabajo para validar su entrega**

---

# 13. Sub-vista 3: Registro de retiro / entrega

## Función
Registrar formalmente el retiro o entrega del trabajo al cliente.

## Contenedor principal sugerido
**BorderPane** o **VBox** con bloques en **GridPane**.

### Estructura interna
1. Datos del retiro
2. Confirmación de entrega
3. Observación final

## 13.1. Datos del retiro

### Campos visibles
- Referencia
- Cliente
- Fecha y hora de retiro
- Retira
- Documento de referencia

### Seeds
- Referencia: **ET-041**
- Cliente: **Sofía Ramírez**
- Fecha y hora de retiro: **16/04/2026 16:30**
- Retira: **Titular**
- Documento de referencia: **0912345678**

## 13.2. Confirmación de entrega

### Campos visibles
- Entrega conforme
- Requiere seguimiento posterior
- Trabajo retirado completamente

### Seeds
- Entrega conforme: **Sí**
- Requiere seguimiento posterior: **No**
- Trabajo retirado completamente: **Sí**

## 13.3. Observación final

### Campo visible
- Observación final

### Seed
**Cliente recibió el trabajo en conformidad y no reporta molestias iniciales**

### Botones oficiales del submódulo
- **Confirmar entrega**
- **Reprogramar retiro**
- **Cancelar registro**

### Tooltips
- Confirmar entrega: **Registrar la entrega final del trabajo al cliente**
- Reprogramar retiro: **Dejar el trabajo pendiente para otro momento de retiro**
- Cancelar registro: **Cancelar el registro actual de entrega**

### Estado vacío
**Seleccione un trabajo para registrar su retiro o entrega**

---

# 14. Sub-vista 4: Pendientes de retiro

## Función
Consultar trabajos listos que todavía no han sido retirados por el cliente.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: resumen de pendientes
- **center**: **TableView** principal

## 14.1. Resumen superior

### Campos visibles
- Pendientes de retiro
- No retirados con saldo
- No retirados notificados

### Seeds
- Pendientes de retiro: **9**
- No retirados con saldo: **2**
- No retirados notificados: **6**

## 14.2. TableView principal

### Columnas oficiales
- Referencia
- Cliente
- Días esperando
- Saldo
- Estado
- Notificación
- Sucursal

### Seeds oficiales
1. ET-044 | Diana Vélez | 1 día | $ 0.00 | Pendiente de retiro | Notificado por SMS | Norte Mall
2. ET-045 | Carlos Mendoza | 3 días | $ 18.00 | Con saldo pendiente | Recordatorio enviado | Matriz Centro
3. ET-046 | Ana Vera | 2 días | $ 0.00 | No retirada | Pendiente de notificación | Norte Mall

### Tooltip de la tabla
**Trabajos listos que aún no han sido retirados por el cliente**

### Botones oficiales del submódulo
- **Notificar cliente**
- **Registrar retiro**
- **Marcar seguimiento**

### Tooltips
- Notificar cliente: **Registrar o ejecutar una notificación al cliente pendiente de retiro**
- Registrar retiro: **Registrar la entrega de un trabajo pendiente**
- Marcar seguimiento: **Marcar el trabajo para una acción posterior de contacto**

### Estado vacío
**No hay trabajos pendientes de retiro en este momento**

---

# 15. Sub-vista 5: Ajustes y observaciones post-entrega

## Función
Registrar ajustes menores, molestias reportadas u observaciones posteriores al retiro del trabajo.

## Contenedor principal sugerido
**SplitPane** horizontal

### Panel izquierdo
**TableView** de registros post-entrega

### Panel derecho
Detalle del ajuste u observación seleccionada

## 15.1. TableView principal

### Columnas oficiales
- Fecha
- Referencia
- Tipo
- Estado
- Responsable

### Seeds oficiales
1. 16/04/2026 | ET-041 | Ajuste de montura | Cerrado | Técnico Rivera
2. 17/04/2026 | ET-043 | Molestia inicial | Pendiente | Dra. Salazar
3. 17/04/2026 | ET-044 | Revisión de comodidad | Cerrado | Asesor Molina

### Tooltip de la tabla
**Registros posteriores a la entrega relacionados con ajustes o molestias**

## 15.2. Detalle del registro

### Campos visibles
- Tipo de ajuste u observación
- Fecha
- Estado
- Responsable
- Descripción
- Resolución

### Seeds
- Tipo de ajuste u observación: **Molestia inicial**
- Fecha: **17/04/2026**
- Estado: **Pendiente**
- Responsable: **Dra. Salazar**
- Descripción: **Cliente reporta ligera incomodidad en visión de cerca**
- Resolución: **Agendar revisión si persiste después de 48 horas**

### Botones oficiales del submódulo
- **Registrar ajuste**
- **Marcar resuelto**
- **Agendar revisión**

### Tooltips
- Registrar ajuste: **Registrar un ajuste o incidencia posterior a la entrega**
- Marcar resuelto: **Cerrar el registro seleccionado**
- Agendar revisión: **Programar una nueva revisión para el cliente**

### Estado vacío
**No hay ajustes ni observaciones post-entrega registrados**

---

# 16. Sub-vista 6: Histórico de entregas

## Función
Consultar entregas registradas, reprogramadas, no retiradas o cerradas en un período determinado.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: filtros extendidos
- **center**: **TableView** de resultados

## 16.1. Filtros extendidos

### Controles sugeridos
- TextField: **Cliente, orden o referencia**
- ComboBox: **Estado**
- ComboBox: **Sucursal**
- ComboBox: **Notificación**
- DatePicker: **Desde**
- DatePicker: **Hasta**
- Button: **Buscar histórico**
- Button: **Limpiar filtros**

### Tooltip general
**Filtre entregas históricas por referencia, cliente, estado, sucursal o fecha**

## 16.2. TableView principal

### Columnas oficiales
- Fecha
- Referencia
- Cliente
- Estado final
- Saldo
- Sucursal
- Observación

### Seeds oficiales
1. 15/04/2026 | ET-038 | María León | Entregada | $ 0.00 | Matriz Centro | Entrega conforme
2. 14/04/2026 | ET-037 | Juan Cedeño | Reprogramada | $ 0.00 | Norte Mall | Cliente solicitó retiro posterior
3. 13/04/2026 | ET-036 | Carlos Mendoza | No retirada | $ 22.00 | Matriz Centro | Pendiente de pago y retiro

### Tooltip de la tabla
**Consulte entregas históricas por fecha, estado, sucursal o cliente**

### Botones oficiales del submódulo
- **Abrir entrega**
- **Exportar histórico**

### Tooltips
- Abrir entrega: **Abrir el registro detallado de la entrega seleccionada**
- Exportar histórico: **Exportar el histórico visible de entregas**

### Estado vacío
**No hay entregas históricas que coincidan con los criterios actuales**

---

# 17. Formulario conceptual: Registro rápido de retiro

## Función
Permitir registrar de manera rápida un retiro cuando el trabajo ya está validado y listo.

## Contenedor sugerido
Vista interna o diálogo amplio controlado. Para esta maqueta conviene resolverlo dentro del mismo módulo.

## Estructura sugerida
**VBox** con bloques simples en **GridPane**.

### Campos mínimos
- Referencia
- Cliente
- Fecha y hora
- Retira
- Confirmación de conformidad
- Observación breve

### Botones finales
- **Guardar entrega**
- **Cancelar**

### Tooltips
- Guardar entrega: **Registrar rápidamente la entrega del trabajo seleccionado**
- Cancelar: **Cerrar el registro rápido de entrega sin guardar**

---

# 18. Seed data oficial del módulo Entregas

## Clientes de semilla
- Sofía Ramírez
- Ana Vera
- Luis Andrade
- Diana Vélez
- Carlos Mendoza
- María León
- Juan Cedeño

## Referencias de entrega
- ET-041
- ET-042
- ET-043
- ET-044
- ET-045
- ET-046
- ET-038
- ET-037
- ET-036

## Sucursales
- Matriz Centro
- Norte Mall

## Tipos de trabajo
- Montura + lente
- Lentes monofocales
- Cambio de lente
- Progresivos

## Estados usados en el módulo
- Lista para entrega
- Pendiente de validación
- Pendiente de retiro
- Notificado
- Entregada
- Reprogramada
- Con ajuste pendiente
- Con saldo pendiente
- No retirada

## Estados de notificación
- Pendiente de notificación
- Notificado por SMS
- Recordatorio enviado

---

# 19. Textos oficiales del módulo Entregas

## Títulos y labels principales
- Entregas
- Cierre operativo del trabajo óptico: validación, retiro, ajuste y control de pendientes
- Buscar
- Estado
- Sucursal
- Notificación
- Desde
- Hasta
- Solo con saldo pendiente
- Lista de trabajos listos
- Validación previa a entrega
- Registro de retiro / entrega
- Pendientes de retiro
- Ajustes y observaciones post-entrega
- Histórico de entregas
- Referencia
- Cliente
- Tipo de trabajo
- Fecha promesa
- Fecha de recepción
- Estado actual
- Prioridad
- Saldo pendiente
- Estado de cobro
- Días esperando
- Entrega conforme
- Requiere seguimiento posterior
- Trabajo retirado completamente
- Observación final

## Botones oficiales
- Actualizar entregas
- Exportar entregas
- Registrar retiro rápido
- Limpiar filtros
- Abrir detalle
- Validar entrega
- Registrar retiro
- Notificar cliente
- Ver saldo
- Marcar validado
- Registrar ajuste
- Bloquear entrega
- Confirmar entrega
- Reprogramar retiro
- Cancelar registro
- Marcar seguimiento
- Marcar resuelto
- Agendar revisión
- Buscar histórico
- Abrir entrega
- Exportar histórico
- Guardar entrega
- Cancelar

## Placeholders
- Orden, cliente o referencia
- Cliente, orden o referencia

## Empty states
- No hay trabajos listos visibles con los filtros actuales
- Seleccione un trabajo para validar su entrega
- Seleccione un trabajo para registrar su retiro o entrega
- No hay trabajos pendientes de retiro en este momento
- No hay ajustes ni observaciones post-entrega registrados
- No hay entregas históricas que coincidan con los criterios actuales

---

# 20. Reglas visuales específicas del módulo Entregas

- la lista de trabajos listos debe sentirse como la bandeja principal del módulo
- el panel derecho debe mantener contexto claro del trabajo y su estado económico
- Validación previa a entrega debe verse como checklist serio y limpio
- Registro de retiro / entrega debe ser rápido y muy legible
- Pendientes de retiro debe comunicar seguimiento sin volverse agresivo
- Ajustes y observaciones post-entrega debe sentirse breve pero útil
- Histórico de entregas debe verse consultivo y ordenado
- no abusar de formas redondeadas ni de tarjetas decorativas
- la prioridad debe ser claridad, conformidad del trabajo y control del cierre operativo

---

# 21. Relación del módulo Entregas con otros módulos

Entregas debe conectarse con:
- Órdenes de laboratorio, porque muchos trabajos llegan a este punto después de ser recibidos en sucursal
- Caja, porque algunas entregas dependen del estado de pago o saldo pendiente
- Clientes, porque el retiro y las observaciones quedan asociadas a la ficha
- Seguimiento, porque pendientes o no retirados pueden generar contacto posterior
- Inicio, porque el panel principal puede resumir trabajos listos o pendientes de retiro
- Sucursales, porque la entrega debe distinguir claramente la sede de retiro

---

# 22. Cierre del módulo Entregas

Este módulo debe transmitir que la óptica no improvisa el cierre del trabajo. Debe verse como una herramienta que permite saber qué está listo, qué falta validar, qué ya fue notificado, qué tiene saldo pendiente, qué fue retirado y qué requiere ajuste o seguimiento posterior. No se trata solo de marcar “entregado”, sino de controlar el momento más visible para el cliente: la entrega final.

La complejidad correcta del módulo está en que cada sub-vista resuelve una parte clara del cierre operativo sin convertir la experiencia en una logística pesada o innecesaria.