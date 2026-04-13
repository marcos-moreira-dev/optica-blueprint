# Lienzo del módulo Órdenes de laboratorio para sistema de óptica en JavaFX

## 1. Identidad del módulo

### Nombre oficial del módulo en el menú
**Órdenes de laboratorio**

### Texto visible del botón del sidebar
**Órdenes de laboratorio**

### Tooltip del botón del sidebar
**Consultar, rastrear y actualizar el estado de los trabajos enviados a laboratorio**

### Ícono conceptual
Orden de trabajo, laboratorio, proceso o trazabilidad.

### Título visible en pantalla
**Órdenes de laboratorio**

### Subtítulo visible en pantalla
**Seguimiento operativo de trabajos, estados, envíos, incidencias y recepción**

### Tipo de módulo
Módulo logístico y de trazabilidad con sub-vistas internas especializadas.

### Objetivo del módulo
Permitir que la óptica controle el recorrido completo de cada trabajo óptico, desde su ingreso al flujo de laboratorio hasta su recepción en sucursal, resolución de incidencias y preparación para entrega.

---

## 2. Rol del módulo dentro de la arquitectura GUI

Órdenes de laboratorio no debe sentirse como una tabla de pedidos genérica. Debe verse como el centro de control de trabajos en proceso.

### Regla arquitectónica principal
Cuando el usuario entre al módulo Órdenes de laboratorio, la región central principal del sistema debe reemplazarse por una vista completa llamada conceptualmente:
**OrdenesLaboratorioModuleView**

### Estructura interna limpia del módulo
La vista Órdenes de laboratorio debe dividirse en:
- encabezado del módulo
- barra de filtros y búsqueda
- subnavegación interna del módulo
- región central intercambiable para sub-vistas
- panel lateral derecho con resumen persistente de la orden seleccionada

### Filosofía de implementación
El usuario debe poder recorrer el estado de las órdenes sin perder el contexto. La vista completa debe sentirse como una estación de seguimiento logístico.

### Regla de arquitectura limpia
Debe existir una región interna estable que se reescriba solo con el submódulo activo. Esa región se denomina conceptualmente:
**labOrderContentHostPane**

Ese contenedor puede ser un **StackPane** o el centro de un **BorderPane** secundario.

### Sub-vistas que viven dentro de labOrderContentHostPane
- Cola de órdenes
- Detalle de orden
- Seguimiento por etapas
- Envío y recepción
- Incidencias y retrabajos
- Histórico y búsqueda avanzada

---

## 3. Sub-vistas oficiales del módulo Órdenes de laboratorio

## Sub-vistas definidas
1. **Cola de órdenes**
2. **Detalle de orden**
3. **Seguimiento por etapas**
4. **Envío y recepción**
5. **Incidencias y retrabajos**
6. **Histórico y búsqueda avanzada**

## Orden de prioridad
1. Cola de órdenes
2. Detalle de orden
3. Seguimiento por etapas
4. Envío y recepción
5. Incidencias y retrabajos
6. Histórico y búsqueda avanzada

## Vista por defecto al abrir el módulo
**Cola de órdenes**

Motivo: es la vista operativa principal y la que mejor permite entender qué trabajos están vivos en este momento.

---

## 4. Estructura visual general del módulo Órdenes de laboratorio

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
- panel derecho: resumen persistente de la orden seleccionada

### Proporción recomendada
- región de trabajo: 68% a 72%
- panel derecho: 28% a 32%

### Regla de estabilidad visual
El panel derecho debe mantenerse visible en casi toda la navegación del módulo para que el usuario no pierda la referencia de la orden seleccionada.

---

## 5. Encabezado del módulo Órdenes de laboratorio

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
- Label principal: **Órdenes de laboratorio**
- Label secundario: **Seguimiento operativo de trabajos, estados, envíos, incidencias y recepción**

### Zona derecha
Un **HBox** con:
- Button secundario: **Actualizar módulo**
- Button secundario: **Exportar órdenes**
- Button principal: **Nueva orden manual**

## Controles exactos

### Label principal
- Control: **Label**
- Texto exacto: **Órdenes de laboratorio**
- Tooltip: no necesita

### Label secundario
- Control: **Label**
- Texto exacto: **Seguimiento operativo de trabajos, estados, envíos, incidencias y recepción**
- Tooltip: no necesita

### Botón secundario 1
- Control: **Button**
- Texto exacto: **Actualizar módulo**
- Tooltip: **Recargar las órdenes visibles del módulo**
- Estilo: secundario

### Botón secundario 2
- Control: **Button**
- Texto exacto: **Exportar órdenes**
- Tooltip: **Exportar las órdenes visibles según los filtros aplicados**
- Estilo: secundario

### Botón principal
- Control: **Button**
- Texto exacto: **Nueva orden manual**
- Tooltip: **Registrar manualmente una nueva orden para laboratorio**
- Estilo: acción principal

---

## 7. Franja media: filtros y búsqueda

## Contenedor sugerido
**FlowPane**

## Función
Permitir localizar rápidamente órdenes por referencia, cliente, estado, laboratorio, sucursal o fecha.

## Controles oficiales

### Búsqueda rápida
- Control: **TextField**
- Label asociado: **Buscar**
- Placeholder exacto: **Orden, cliente, guía o referencia**
- Tooltip: **Buscar órdenes por número, cliente, guía o texto relacionado**

### Filtro por estado
- Control: **ComboBox**
- Label asociado: **Estado**
- Ítems de semilla:
  - Todos
  - Recibida
  - Validada
  - En producción
  - En control
  - Lista
  - Enviada
  - Recibida en sucursal
  - Entregada
  - Retrasada
  - Con incidencia
  - En retrabajo
  - Cancelada
- Valor inicial: **Todos**
- Tooltip: **Filtrar órdenes según su estado operativo actual**

### Filtro por laboratorio
- Control: **ComboBox**
- Label asociado: **Laboratorio**
- Ítems de semilla:
  - Todos
  - Óptica Interna
  - LabVision Externo
- Valor inicial: **Todos**
- Tooltip: **Filtrar por laboratorio responsable del trabajo**

### Filtro por sucursal
- Control: **ComboBox**
- Label asociado: **Sucursal**
- Ítems de semilla:
  - Todas
  - Matriz Centro
  - Norte Mall
- Valor inicial: **Todas**
- Tooltip: **Filtrar por sucursal donde se registró o recibirá la orden**

### Filtro por prioridad
- Control: **ComboBox**
- Label asociado: **Prioridad**
- Ítems de semilla:
  - Todas
  - Alta
  - Media
  - Baja
- Valor inicial: **Todas**
- Tooltip: **Filtrar por prioridad operativa de la orden**

### Filtro por fecha
- Control: **DatePicker**
- Label asociado: **Desde**
- Tooltip: **Seleccionar la fecha inicial para filtrar órdenes**

### Filtro por fecha final
- Control: **DatePicker**
- Label asociado: **Hasta**
- Tooltip: **Seleccionar la fecha final para filtrar órdenes**

### Botón limpiar
- Control: **Button**
- Texto exacto: **Limpiar filtros**
- Tooltip: **Restablecer la búsqueda y todos los filtros del módulo**

---

## 8. Franja inferior: subnavegación interna del módulo

## Contenedor sugerido
**HBox** con **ToggleButton** dentro de un **ToggleGroup**

## Sub-vistas visibles en la barra
- Cola de órdenes
- Detalle de orden
- Seguimiento por etapas
- Envío y recepción
- Incidencias y retrabajos
- Histórico y búsqueda avanzada

## Tooltips exactos
- Cola de órdenes: **Ver las órdenes activas del flujo de laboratorio**
- Detalle de orden: **Consultar la información completa de la orden seleccionada**
- Seguimiento por etapas: **Revisar el avance de la orden a través de sus etapas**
- Envío y recepción: **Consultar envíos, guías, fechas y recepción de trabajos**
- Incidencias y retrabajos: **Registrar o revisar problemas, devoluciones y retrabajos**
- Histórico y búsqueda avanzada: **Consultar órdenes antiguas mediante filtros más amplios**

## Regla visual
La sub-vista activa debe quedar clara, pero sin competir con el contenido operativo.

---

## 9. Cuerpo principal del módulo Órdenes de laboratorio

## Contenedor sugerido
**SplitPane** horizontal

### Panel izquierdo o principal
Sub-vista activa del módulo.

### Panel derecho
Resumen persistente de la orden seleccionada.

---

# 10. Panel derecho persistente: resumen de orden

## Función
Mantener el contexto de la orden seleccionada mientras se navega por el módulo.

## Contenedor sugerido
**ScrollPane** con un **VBox** interno.

## Bloques internos del panel derecho
1. Identidad de la orden
2. Contexto del cliente
3. Resumen técnico
4. Estado y logística
5. Acciones rápidas

## 10.1. Bloque: Identidad de la orden

### Campos visibles
- Referencia
- Tipo de trabajo
- Prioridad

### Seeds
- **Referencia: LAB-203**
- **Tipo de trabajo: Lentes monofocales**
- **Prioridad: Alta**

## 10.2. Bloque: Contexto del cliente

### Campos visibles
- Cliente
- Código del cliente
- Sucursal
- Fecha promesa

### Seeds
- **Cliente: Sofía Ramírez**
- **Código del cliente: CL-00124**
- **Sucursal: Matriz Centro**
- **Fecha promesa: 16/04/2026**

## 10.3. Bloque: Resumen técnico

### Campos visibles
- Montura
- Lente
- Tratamientos
- PD

### Seeds
- **Montura: MZ-201**
- **Lente: Monofocal 1.56**
- **Tratamientos: Antirreflejo + Blue cut**
- **PD: 62 mm**

## 10.4. Bloque: Estado y logística

### Campos visibles
- Estado actual
- Laboratorio
- Guía
- Recepción esperada

### Seeds
- **Estado actual: En producción**
- **Laboratorio: LabVision Externo**
- **Guía: TRK-99821**
- **Recepción esperada: 16/04/2026**

## 10.5. Bloque: Acciones rápidas

### Botones oficiales
- **Abrir detalle**
- **Actualizar estado**
- **Registrar envío**
- **Registrar recepción**
- **Marcar incidencia**

### Tooltips exactos
- Abrir detalle: **Abrir el detalle completo de la orden seleccionada**
- Actualizar estado: **Cambiar el estado operativo actual de la orden**
- Registrar envío: **Registrar que la orden fue enviada al laboratorio o a sucursal**
- Registrar recepción: **Registrar la recepción de la orden en la sede correspondiente**
- Marcar incidencia: **Registrar una incidencia o problema relacionado con la orden**

---

# 11. Sub-vista 1: Cola de órdenes

## Función
Servir como bandeja principal de trabajo del laboratorio y de la óptica.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: pequeño resumen operativo
- **center**: **TableView** principal
- **bottom**: resumen de resultados visibles

## 11.1. Franja superior de la cola

### Contenido
- Label: **Órdenes activas**
- Label secundario: **42 órdenes visibles**

### Tooltips
- Órdenes activas: **Órdenes visibles según los filtros y el estado operativo**
- 42 órdenes visibles: **Cantidad de órdenes mostradas en la bandeja actual**

## 11.2. TableView principal

### Columnas oficiales
- Referencia
- Cliente
- Tipo de trabajo
- Ingreso
- Fecha promesa
- Estado
- Laboratorio
- Prioridad
- Sucursal

### Seeds oficiales
1. LAB-203 | Sofía Ramírez | Lentes monofocales | 12/04/2026 | 16/04/2026 | En producción | LabVision Externo | Alta | Matriz Centro
2. LAB-204 | Luis Andrade | Progresivos | 11/04/2026 | 17/04/2026 | Validada | Óptica Interna | Media | Norte Mall
3. LAB-205 | Ana Vera | Cambio de lente en montura propia | 10/04/2026 | 15/04/2026 | Lista | Óptica Interna | Alta | Matriz Centro
4. LAB-206 | Carlos Mendoza | Ajuste y montaje | 09/04/2026 | 14/04/2026 | Retrasada | LabVision Externo | Alta | Norte Mall
5. LAB-207 | Diana Vélez | Lentes monofocales | 08/04/2026 | 13/04/2026 | Con incidencia | LabVision Externo | Media | Matriz Centro

### Tooltip de la tabla
**Seleccione una orden para ver su resumen, detalle o avanzar en su seguimiento**

### Estado vacío
**No hay órdenes visibles con los filtros actuales**

## 11.3. Pie del listado

### Textos exactos
- **Mostrando 5 de 42 órdenes**
- **Ordenado por fecha promesa**

### Tooltips
- Mostrando 5 de 42 órdenes: **Resumen de registros visibles en la bandeja actual**
- Ordenado por fecha promesa: **Criterio de ordenamiento aplicado a la bandeja**

---

# 12. Sub-vista 2: Detalle de orden

## Función
Mostrar toda la información relevante de una orden como ficha logística y técnica.

## Contenedor principal sugerido
**ScrollPane** con un **VBox** interno.

### Estructura interna
1. Cabecera de orden
2. Bloque técnico
3. Bloque logístico
4. Bloque de observaciones
5. Acciones del detalle

## 12.1. Cabecera de orden

### Campos visibles
- Referencia
- Cliente
- Tipo de trabajo
- Estado
- Prioridad
- Fecha promesa

### Seeds
- **LAB-203**
- **Sofía Ramírez**
- **Lentes monofocales**
- **En producción**
- **Alta**
- **16/04/2026**

## 12.2. Bloque técnico

### Campos visibles
- Receta aplicada
- Montura
- Lente
- Tratamientos
- PD
- Observación técnica

### Seeds
- **Receta aplicada: 12/04/2026**
- **Montura: MZ-201**
- **Lente: Monofocal 1.56**
- **Tratamientos: Antirreflejo + Blue cut**
- **PD: 62 mm**
- **Observación técnica: Montaje estándar para uso diario**

## 12.3. Bloque logístico

### Campos visibles
- Laboratorio
- Fecha de ingreso
- Fecha de envío
- Guía
- Recepción esperada
- Responsable

### Seeds
- **Laboratorio: LabVision Externo**
- **Fecha de ingreso: 12/04/2026**
- **Fecha de envío: 13/04/2026**
- **Guía: TRK-99821**
- **Recepción esperada: 16/04/2026**
- **Responsable: Asesor Molina**

## 12.4. Bloque de observaciones

### Seed
**Cliente solicita aviso previo cuando la orden esté lista y retiro en Matriz Centro**

## 12.5. Botones oficiales del submódulo
- **Editar orden**
- **Actualizar estado**
- **Registrar envío**
- **Registrar recepción**
- **Imprimir hoja de trabajo**

### Tooltips
- Editar orden: **Modificar información de la orden seleccionada**
- Actualizar estado: **Cambiar el estado operativo de la orden**
- Registrar envío: **Registrar el envío de la orden al laboratorio o a otra sede**
- Registrar recepción: **Registrar la recepción física del trabajo**
- Imprimir hoja de trabajo: **Generar una hoja resumida para la orden**

### Estado vacío
**Seleccione una orden para ver su detalle completo**

---

# 13. Sub-vista 3: Seguimiento por etapas

## Función
Mostrar el recorrido de la orden a través de estados operativos claros.

## Contenedor principal sugerido
**VBox** con un bloque superior de etapas y un bloque inferior de eventos.

### Estructura interna
1. Línea o franja de etapas
2. Lista de eventos o checkpoints

## 13.1. Línea de etapas

### Etapas oficiales
- Recibida
- Validada
- En producción
- En control
- Lista
- Enviada
- Recibida en sucursal
- Entregada

### Seed de estado actual
**En producción**

### Regla visual
La etapa actual debe verse claramente marcada. Las etapas anteriores deben verse completadas y las siguientes pendientes.

## 13.2. Eventos o checkpoints

### Contenedor sugerido
**TableView** o **VBox** con filas. Se recomienda **TableView** por ser más administrativa.

### Columnas oficiales
- Fecha
- Evento
- Responsable
- Observación

### Seeds
1. 12/04/2026 09:10 | Orden recibida | Asesor Molina | Orden registrada desde venta óptica
2. 12/04/2026 10:00 | Orden validada | Técnico Rivera | Receta y medidas verificadas
3. 13/04/2026 08:30 | En producción | LabVision Externo | Trabajo ingresado a fabricación

### Botones oficiales del submódulo
- **Actualizar etapa**
- **Agregar checkpoint**
- **Marcar lista**

### Tooltips
- Actualizar etapa: **Cambiar la etapa actual del flujo de la orden**
- Agregar checkpoint: **Registrar un nuevo evento en el seguimiento de la orden**
- Marcar lista: **Indicar que la orden ya está lista para envío o recepción**

### Estado vacío
**No hay eventos registrados para esta orden**

---

# 14. Sub-vista 4: Envío y recepción

## Función
Registrar y consultar la logística física de envío y retorno de la orden.

## Contenedor principal sugerido
**ScrollPane** con **VBox** y bloques en **GridPane**.

### Estructura interna
1. Datos de envío
2. Datos de recepción
3. Confirmación logística

## 14.1. Bloque: Datos de envío

### Campos visibles
- Laboratorio
- Fecha de envío
- Guía
- Transportista
- Fecha estimada de retorno

### Seeds
- Laboratorio: **LabVision Externo**
- Fecha de envío: **13/04/2026**
- Guía: **TRK-99821**
- Transportista: **Mensajería Express**
- Fecha estimada de retorno: **16/04/2026**

## 14.2. Bloque: Datos de recepción

### Campos visibles
- Fecha de recepción
- Recibido por
- Sucursal de recepción
- Estado al recibir

### Seeds
- Fecha de recepción: **16/04/2026**
- Recibido por: **Laura Gómez**
- Sucursal de recepción: **Matriz Centro**
- Estado al recibir: **Conforme**

## 14.3. Botones oficiales del submódulo
- **Registrar envío**
- **Registrar recepción**
- **Actualizar guía**
- **Reenviar información**

### Tooltips
- Registrar envío: **Guardar los datos de salida de la orden**
- Registrar recepción: **Guardar los datos de recepción física de la orden**
- Actualizar guía: **Modificar la referencia o guía del envío**
- Reenviar información: **Reenviar datos logísticos de la orden**

### Estado vacío
**Esta orden todavía no tiene registro de envío o recepción**

---

# 15. Sub-vista 5: Incidencias y retrabajos

## Función
Registrar problemas, devoluciones, errores de trabajo y retrabajos relacionados con la orden.

## Contenedor principal sugerido
**SplitPane** horizontal

### Panel izquierdo
**TableView** de incidencias y retrabajos

### Panel derecho
Detalle de la incidencia seleccionada

## 15.1. Tabla de incidencias

### Columnas oficiales
- Fecha
- Tipo
- Estado
- Responsable
- Resolución

### Seeds oficiales
1. 14/04/2026 | Error de tratamiento | Abierta | Técnico Rivera | Pendiente
2. 15/04/2026 | Montaje fuera de centrado | En retrabajo | LabVision Externo | En proceso
3. 16/04/2026 | Retraso de laboratorio externo | Cerrada | Laura Gómez | Cliente notificado

### Tooltip de la tabla
**Incidencias y retrabajos registrados para la orden seleccionada**

## 15.2. Detalle de incidencia

### Campos visibles
- Tipo de incidencia
- Fecha
- Estado
- Responsable
- Descripción
- Resolución

### Seeds
- Tipo de incidencia: **Error de tratamiento**
- Fecha: **14/04/2026**
- Estado: **Abierta**
- Responsable: **Técnico Rivera**
- Descripción: **El lente recibido no incluye el tratamiento solicitado**
- Resolución: **Pendiente**

### Botones oficiales del submódulo
- **Registrar incidencia**
- **Marcar resuelta**
- **Generar retrabajo**
- **Notificar cliente**

### Tooltips
- Registrar incidencia: **Registrar un nuevo problema asociado a la orden**
- Marcar resuelta: **Cerrar la incidencia seleccionada**
- Generar retrabajo: **Crear un retrabajo para corregir la orden**
- Notificar cliente: **Registrar o ejecutar una notificación al cliente afectado**

### Estado vacío
**No hay incidencias registradas para esta orden**

---

# 16. Sub-vista 6: Histórico y búsqueda avanzada

## Función
Permitir consultar órdenes antiguas, cerradas o filtradas por múltiples criterios.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: filtros extendidos
- **center**: **TableView** de resultados

## 16.1. Filtros extendidos

### Controles sugeridos
- TextField: **Cliente, orden o guía**
- ComboBox: **Estado**
- ComboBox: **Laboratorio**
- ComboBox: **Sucursal**
- ComboBox: **Con incidencia**
- DatePicker: **Desde**
- DatePicker: **Hasta**
- Button: **Buscar histórico**
- Button: **Limpiar filtros**

### Tooltip general
**Filtre órdenes históricas por referencia, estado, laboratorio, sucursal o fechas**

## 16.2. Tabla de resultados

### Columnas oficiales
- Referencia
- Cliente
- Ingreso
- Fecha promesa
- Estado final
- Laboratorio
- Sucursal

### Seeds
1. LAB-181 | María León | 20/03/2026 | 25/03/2026 | Entregada | Óptica Interna | Matriz Centro
2. LAB-176 | Juan Cedeño | 15/03/2026 | 21/03/2026 | Cancelada | LabVision Externo | Norte Mall
3. LAB-169 | Carmen López | 08/03/2026 | 14/03/2026 | Recibida en sucursal | LabVision Externo | Matriz Centro

### Botones oficiales del submódulo
- **Abrir orden**
- **Exportar histórico**

### Tooltips
- Abrir orden: **Abrir la orden histórica seleccionada**
- Exportar histórico: **Exportar el resultado visible de la búsqueda avanzada**

### Estado vacío
**No hay órdenes históricas que coincidan con los criterios actuales**

---

# 17. Formulario conceptual: Nueva orden manual

## Función
Registrar una orden que no nació directamente desde Venta óptica, pero que debe entrar al flujo de laboratorio.

## Contenedor sugerido
Vista interna o diálogo amplio controlado. Para esta maqueta, conviene resolverlo dentro del mismo módulo.

## Estructura sugerida
**ScrollPane** con **VBox** y bloques en **GridPane**.

### Campos mínimos
- Cliente
- Referencia manual
- Tipo de trabajo
- Sucursal
- Laboratorio
- Fecha promesa
- Observación inicial

### Botones finales
- **Guardar orden manual**
- **Cancelar**

### Tooltips
- Guardar orden manual: **Registrar la orden manual dentro del flujo de laboratorio**
- Cancelar: **Cerrar la creación de orden manual sin guardar**

---

# 18. Seed data oficial del módulo Órdenes de laboratorio

## Clientes de semilla
- Sofía Ramírez
- Luis Andrade
- Ana Vera
- Carlos Mendoza
- Diana Vélez
- María León
- Juan Cedeño
- Carmen López

## Sucursales
- Matriz Centro
- Norte Mall

## Laboratorios
- Óptica Interna
- LabVision Externo

## Tipos de trabajo
- Lentes monofocales
- Progresivos
- Cambio de lente en montura propia
- Ajuste y montaje

## Referencias de orden
- LAB-203
- LAB-204
- LAB-205
- LAB-206
- LAB-207
- LAB-181
- LAB-176
- LAB-169

## Guías
- TRK-99821
- TRK-99822
- TRK-99823

## Estados usados en el módulo
- Recibida
- Validada
- En producción
- En control
- Lista
- Enviada
- Recibida en sucursal
- Entregada
- Retrasada
- Con incidencia
- En retrabajo
- Cancelada

---

# 19. Textos oficiales del módulo Órdenes de laboratorio

## Títulos y labels principales
- Órdenes de laboratorio
- Seguimiento operativo de trabajos, estados, envíos, incidencias y recepción
- Buscar
- Estado
- Laboratorio
- Sucursal
- Prioridad
- Desde
- Hasta
- Cola de órdenes
- Detalle de orden
- Seguimiento por etapas
- Envío y recepción
- Incidencias y retrabajos
- Histórico y búsqueda avanzada
- Órdenes activas
- Referencia
- Cliente
- Tipo de trabajo
- Ingreso
- Fecha promesa
- Estado actual
- Guía
- Recepción esperada
- Receta aplicada
- Montura
- Lente
- Tratamientos
- PD
- Fecha de envío
- Fecha de recepción
- Recibido por
- Tipo de incidencia
- Resolución

## Botones oficiales
- Actualizar módulo
- Exportar órdenes
- Nueva orden manual
- Limpiar filtros
- Abrir detalle
- Actualizar estado
- Registrar envío
- Registrar recepción
- Marcar incidencia
- Editar orden
- Imprimir hoja de trabajo
- Agregar checkpoint
- Marcar lista
- Actualizar guía
- Reenviar información
- Registrar incidencia
- Marcar resuelta
- Generar retrabajo
- Notificar cliente
- Buscar histórico
- Abrir orden
- Exportar histórico
- Guardar orden manual
- Cancelar

## Placeholders
- Orden, cliente, guía o referencia
- Cliente, orden o guía

## Empty states
- No hay órdenes visibles con los filtros actuales
- Seleccione una orden para ver su detalle completo
- No hay eventos registrados para esta orden
- Esta orden todavía no tiene registro de envío o recepción
- No hay incidencias registradas para esta orden
- No hay órdenes históricas que coincidan con los criterios actuales

---

# 20. Reglas visuales específicas del módulo Órdenes de laboratorio

- la cola de órdenes debe sentirse como la bandeja principal de trabajo
- el panel derecho debe dar lectura rápida y estable del contexto
- seguimiento por etapas debe comunicar trazabilidad sin recargar
- envío y recepción debe sentirse logístico y controlado
- incidencias y retrabajos debe verse más crítica, pero no caótica
- histórico y búsqueda avanzada debe ser potente pero sobrio
- no abusar de formas redondeadas ni de tarjetas decorativas
- la prioridad debe ser control, claridad y trazabilidad

---

# 21. Relación del módulo Órdenes de laboratorio con otros módulos

Órdenes de laboratorio debe conectarse con:
- Venta óptica, porque muchas órdenes nacen desde allí
- Recetas, porque la prescripción alimenta el trabajo
- Inventario, por monturas, lentes y disponibilidad
- Entregas, porque la recepción en sucursal precede a la entrega final
- Seguimiento, porque algunas incidencias o retrasos requieren contacto con el cliente
- Inicio, porque el panel principal puede resumir trabajos en proceso o atrasados

---

# 22. Cierre del módulo Órdenes de laboratorio

Este módulo debe transmitir que la óptica controla seriamente el recorrido del trabajo óptico. No solo registra una orden: la rastrea, la valida, la envía, la recibe, la corrige si hace falta y la deja lista para entrega. Debe verse como una herramienta de trazabilidad real, útil para evitar pérdidas, retrasos y errores de seguimiento.

La complejidad correcta del módulo está en que cada sub-vista resuelve una parte clara del flujo logístico sin convertir la experiencia en una pantalla industrial excesiva.

