# Lienzo del módulo Compras para sistema de óptica en JavaFX

## 1. Identidad del módulo

### Nombre oficial del módulo en el menú
**Compras**

### Texto visible del botón del sidebar
**Compras**

### Tooltip del botón del sidebar
**Gestionar solicitudes, órdenes de compra, pendientes, recepciones y abastecimiento de la óptica**

### Ícono conceptual
Carrito de abastecimiento, documento de compra o caja de reposición.

### Título visible en pantalla
**Compras**

### Subtítulo visible en pantalla
**Solicitudes, órdenes, abastecimiento, pendientes y recepción de compras para el negocio óptico**

### Tipo de módulo
Módulo operativo y administrativo de aprovisionamiento con sub-vistas internas especializadas.

### Objetivo del módulo
Permitir que la óptica identifique necesidades de compra, emita órdenes de compra, controle pendientes, relacione recepciones y mantenga visibilidad clara del abastecimiento por proveedor y por sucursal.

---

## 2. Rol del módulo dentro de la arquitectura GUI

Compras no debe sentirse como una lista suelta de pedidos. Debe verse como el centro desde donde la óptica decide qué necesita comprar, a quién se lo pedirá, para qué sede y en qué estado se encuentra cada abastecimiento.

### Regla arquitectónica principal
Cuando el usuario entre al módulo Compras, la región central principal del sistema debe reemplazarse por una vista completa llamada conceptualmente:
**ComprasModuleView**

### Estructura interna limpia del módulo
La vista Compras debe dividirse en:
- encabezado del módulo
- barra de filtros y búsqueda
- subnavegación interna del módulo
- región central intercambiable para sub-vistas
- panel lateral derecho con resumen persistente de la compra, solicitud u orden seleccionada

### Filosofía de implementación
El usuario debe poder detectar una necesidad, transformarla en orden, revisar pendientes, vincular recepciones y entender el impacto sobre el abastecimiento sin salir del módulo.

### Regla de arquitectura limpia
Debe existir una región interna estable que se reescriba solo con el submódulo activo. Esa región se denomina conceptualmente:
**comprasContentHostPane**

Ese contenedor puede ser un **StackPane** o el centro de un **BorderPane** secundario.

### Sub-vistas que viven dentro de comprasContentHostPane
- Solicitudes de compra
- Órdenes de compra
- Compras por proveedor
- Back-orders y pendientes
- Recepción vinculada a compras
- Compras por sucursal
- Histórico y búsqueda avanzada

---

## 3. Sub-vistas oficiales del módulo Compras

## Sub-vistas definidas
1. **Solicitudes de compra**
2. **Órdenes de compra**
3. **Compras por proveedor**
4. **Back-orders y pendientes**
5. **Recepción vinculada a compras**
6. **Compras por sucursal**
7. **Histórico y búsqueda avanzada**

## Orden de prioridad
1. Solicitudes de compra
2. Órdenes de compra
3. Compras por proveedor
4. Back-orders y pendientes
5. Recepción vinculada a compras
6. Compras por sucursal
7. Histórico y búsqueda avanzada

## Vista por defecto al abrir el módulo
**Solicitudes de compra**

Motivo: es la vista que mejor transmite que la óptica compra con criterio y no improvisando pedidos sin contexto.

---

## 4. Estructura visual general del módulo Compras

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
- panel derecho: resumen persistente de la solicitud, compra u orden seleccionada

### Proporción recomendada
- región de trabajo: 68% a 72%
- panel derecho: 28% a 32%

### Regla de estabilidad visual
El panel derecho debe mantenerse visible durante casi toda la navegación para que el usuario no pierda el contexto del abastecimiento seleccionado.

---

## 5. Encabezado del módulo Compras

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
- Label principal: **Compras**
- Label secundario: **Solicitudes, órdenes, abastecimiento, pendientes y recepción de compras para el negocio óptico**

### Zona derecha
Un **HBox** con:
- Button secundario: **Actualizar compras**
- Button secundario: **Exportar compras**
- Button principal: **Nueva orden de compra**

## Controles exactos

### Label principal
- Control: **Label**
- Texto exacto: **Compras**
- Tooltip: no necesita

### Label secundario
- Control: **Label**
- Texto exacto: **Solicitudes, órdenes, abastecimiento, pendientes y recepción de compras para el negocio óptico**
- Tooltip: no necesita

### Botón secundario 1
- Control: **Button**
- Texto exacto: **Actualizar compras**
- Tooltip: **Recargar solicitudes, órdenes y pendientes visibles del módulo**
- Estilo: secundario

### Botón secundario 2
- Control: **Button**
- Texto exacto: **Exportar compras**
- Tooltip: **Exportar la información visible de compras según los filtros actuales**
- Estilo: secundario

### Botón principal
- Control: **Button**
- Texto exacto: **Nueva orden de compra**
- Tooltip: **Crear una nueva orden de compra para abastecimiento**
- Estilo: acción principal

---

## 7. Franja media: filtros y búsqueda

## Contenedor sugerido
**FlowPane**

## Función
Permitir localizar solicitudes, órdenes y abastecimientos por proveedor, sucursal, estado, categoría o referencia.

## Controles oficiales

### Búsqueda rápida
- Control: **TextField**
- Label asociado: **Buscar**
- Placeholder exacto: **Orden, solicitud, proveedor, producto o referencia**
- Tooltip: **Buscar compras por orden, solicitud, proveedor, producto o texto relacionado**

### Filtro por estado
- Control: **ComboBox**
- Label asociado: **Estado**
- Ítems de semilla:
  - Todos
  - Solicitud abierta
  - En revisión
  - Borrador
  - Enviada
  - Parcial
  - Recibida
  - Cancelada
  - Back-order
  - Con diferencia
  - Cerrada
- Valor inicial: **Todos**
- Tooltip: **Filtrar solicitudes y órdenes según su estado actual**

### Filtro por proveedor
- Control: **ComboBox**
- Label asociado: **Proveedor**
- Ítems de semilla:
  - Todos
  - VisionLine Distribución
  - LabVision Externo
  - NovaFrame Import
  - ABB Optical
  - CooperVision
- Valor inicial: **Todos**
- Tooltip: **Filtrar compras según el proveedor seleccionado**

### Filtro por categoría
- Control: **ComboBox**
- Label asociado: **Categoría**
- Ítems de semilla:
  - Todas
  - Monturas
  - Lentes
  - Lentes de contacto
  - Accesorios
  - Insumos
- Valor inicial: **Todas**
- Tooltip: **Filtrar compras según la categoría abastecida**

### Filtro por sucursal
- Control: **ComboBox**
- Label asociado: **Sucursal destino**
- Ítems de semilla:
  - Todas
  - Matriz Centro
  - Norte Mall
  - Sur Express
- Valor inicial: **Todas**
- Tooltip: **Filtrar compras según la sede destino del abastecimiento**

### Filtro por fecha inicial
- Control: **DatePicker**
- Label asociado: **Desde**
- Tooltip: **Seleccionar la fecha inicial para consultar compras**

### Filtro por fecha final
- Control: **DatePicker**
- Label asociado: **Hasta**
- Tooltip: **Seleccionar la fecha final para consultar compras**

### Opción rápida
- Control: **CheckBox**
- Texto exacto: **Solo pendientes críticos**
- Tooltip: **Mostrar únicamente pedidos o solicitudes que requieren atención prioritaria**

### Botón limpiar
- Control: **Button**
- Texto exacto: **Limpiar filtros**
- Tooltip: **Restablecer la búsqueda y todos los filtros del módulo**

---

## 8. Franja inferior: subnavegación interna del módulo

## Contenedor sugerido
**HBox** con **ToggleButton** dentro de un **ToggleGroup**

## Sub-vistas visibles en la barra
- Solicitudes de compra
- Órdenes de compra
- Compras por proveedor
- Back-orders y pendientes
- Recepción vinculada a compras
- Compras por sucursal
- Histórico y búsqueda avanzada

## Tooltips exactos
- Solicitudes de compra: **Consultar necesidades de reposición o compra antes de emitir una orden**
- Órdenes de compra: **Consultar y administrar órdenes formales de compra**
- Compras por proveedor: **Consultar compras y abastecimiento agrupados por proveedor**
- Back-orders y pendientes: **Consultar pedidos pendientes, parciales o bloqueados por falta de stock**
- Recepción vinculada a compras: **Consultar cómo cada compra se relaciona con su recepción física**
- Compras por sucursal: **Consultar abastecimiento agrupado por sede destino**
- Histórico y búsqueda avanzada: **Consultar compras históricas mediante filtros amplios**

## Regla visual
La sub-vista activa debe quedar clara, pero sin competir con el contenido administrativo principal.

---

## 9. Cuerpo principal del módulo Compras

## Contenedor sugerido
**SplitPane** horizontal

### Panel izquierdo o principal
Sub-vista activa del módulo.

### Panel derecho
Resumen persistente de la solicitud, compra u orden seleccionada.

---

# 10. Panel derecho persistente: resumen de la compra

## Función
Mantener visible el contexto operativo de la solicitud, orden o abastecimiento seleccionado.

## Contenedor sugerido
**ScrollPane** con un **VBox** interno.

## Bloques internos del panel derecho
1. Identidad del registro
2. Contexto del proveedor
3. Contexto de abastecimiento
4. Estado de recepción
5. Acciones rápidas

## 10.1. Bloque: Identidad del registro

### Campos visibles
- Referencia
- Tipo de registro
- Estado

### Seeds
- **Referencia: OC-082**
- **Tipo de registro: Orden de compra**
- **Estado: Enviada**

## 10.2. Bloque: Contexto del proveedor

### Campos visibles
- Proveedor
- Categoría principal
- Tiempo estimado

### Seeds
- **Proveedor: VisionLine Distribución**
- **Categoría principal: Monturas**
- **Tiempo estimado: 3 días**

## 10.3. Bloque: Contexto de abastecimiento

### Campos visibles
- Sucursal destino
- Total ítems
- Total estimado

### Seeds
- **Sucursal destino: Matriz Centro**
- **Total ítems: 12**
- **Total estimado: $ 1,280.00**

## 10.4. Bloque: Estado de recepción

### Campos visibles
- Recepción vinculada
- Diferencias
- Observación clave

### Seeds
- **Recepción vinculada: Parcial**
- **Diferencias: 2 referencias pendientes**
- **Observación clave: Proveedor confirmó entrega restante en 48 horas**

## 10.5. Bloque: Acciones rápidas

### Botones oficiales
- **Abrir orden**
- **Editar compra**
- **Registrar recepción**
- **Abrir proveedor**
- **Ver inventario**

### Tooltips exactos
- Abrir orden: **Abrir el detalle completo de la compra seleccionada**
- Editar compra: **Modificar la solicitud u orden seleccionada**
- Registrar recepción: **Registrar la recepción parcial o completa de esta compra**
- Abrir proveedor: **Abrir el módulo Proveedores con el proveedor seleccionado**
- Ver inventario: **Consultar el impacto del abastecimiento en Inventario**

---

# 11. Sub-vista 1: Solicitudes de compra

## Función
Mostrar necesidades de reposición o compra antes de emitir una orden formal.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: resumen de solicitudes
- **center**: **TableView** principal
- **bottom**: resumen de resultados visibles

## 11.1. Franja superior del listado

### Contenido
- Label: **Solicitudes de compra**
- Label secundario: **8 solicitudes visibles**

### Tooltips
- Solicitudes de compra: **Solicitudes de reposición o abastecimiento visibles según los filtros actuales**
- 8 solicitudes visibles: **Cantidad de solicitudes mostradas en la bandeja**

## 11.2. TableView principal

### Columnas oficiales
- Solicitud
- Motivo
- Categoría
- Sucursal destino
- Prioridad
- Estado
- Proveedor sugerido

### Seeds oficiales
1. SC-014 | Stock crítico | Monturas | Matriz Centro | Alta | Solicitud abierta | VisionLine Distribución
2. SC-015 | Reposición manual | Lentes | Norte Mall | Media | En revisión | LabVision Externo
3. SC-016 | Pedido especial de cliente | Lentes de contacto | Matriz Centro | Alta | En revisión | CooperVision
4. SC-017 | Back-order previo | Accesorios | Sur Express | Media | Solicitud abierta | NovaFrame Import

### Tooltip de la tabla
**Seleccione una solicitud para emitir una orden o revisar su necesidad de compra**

### Estado vacío
**No hay solicitudes visibles con los filtros actuales**

## 11.3. Pie del listado

### Textos exactos
- **Mostrando 4 de 8 solicitudes**
- **Ordenado por prioridad y fecha**

### Tooltips
- Mostrando 4 de 8 solicitudes: **Resumen de solicitudes visibles en la bandeja actual**
- Ordenado por prioridad y fecha: **Criterio de ordenamiento aplicado a las solicitudes**

### Botones oficiales del submódulo
- **Convertir en orden**
- **Editar solicitud**
- **Cancelar solicitud**

### Tooltips
- Convertir en orden: **Transformar la solicitud seleccionada en una orden de compra**
- Editar solicitud: **Modificar el motivo, prioridad o sede destino de la solicitud**
- Cancelar solicitud: **Cerrar la solicitud sin emitir orden**

---

# 12. Sub-vista 2: Órdenes de compra

## Función
Mostrar y administrar órdenes formales de compra emitidas a proveedores.

## Contenedor principal sugerido
**BorderPane** con indicadores superiores y **TableView** central.

### Estructura interna
1. Resumen de órdenes
2. Tabla de órdenes
3. Acciones documentales

## 12.1. Resumen superior

### Campos visibles
- Órdenes abiertas
- Órdenes parciales
- Órdenes recibidas
- Total comprometido

### Seeds
- Órdenes abiertas: **6**
- Órdenes parciales: **2**
- Órdenes recibidas: **9**
- Total comprometido: **$ 6,480.00**

## 12.2. TableView principal

### Columnas oficiales
- Orden
- Proveedor
- Fecha
- Sucursal destino
- Estado
- Ítems
- Total estimado

### Seeds oficiales
1. OC-082 | VisionLine Distribución | 12/04/2026 | Matriz Centro | Enviada | 12 | $ 1,280.00
2. OC-083 | LabVision Externo | 13/04/2026 | Norte Mall | Parcial | 8 | $ 980.00
3. OC-084 | ABB Optical | 14/04/2026 | Matriz Centro | Borrador | 5 | $ 460.00
4. OC-079 | NovaFrame Import | 08/04/2026 | Norte Mall | Recibida | 8 | $ 1,120.00

### Tooltip de la tabla
**Órdenes formales de compra emitidas o en preparación**

### Estado vacío
**No hay órdenes visibles con los filtros actuales**

### Botones oficiales del submódulo
- **Abrir orden**
- **Duplicar orden**
- **Cancelar orden**
- **Registrar recepción**

### Tooltips
- Abrir orden: **Consultar el detalle completo de la orden seleccionada**
- Duplicar orden: **Crear una nueva orden usando esta como base**
- Cancelar orden: **Cancelar la orden seleccionada**
- Registrar recepción: **Registrar la recepción asociada a esta orden**

---

# 13. Sub-vista 3: Compras por proveedor

## Función
Consultar el abastecimiento agrupado por proveedor y facilitar una lectura relacional de compras.

## Contenedor principal sugerido
**SplitPane** horizontal

### Panel izquierdo
**TableView** de proveedores con actividad de compra

### Panel derecho
Detalle de compras del proveedor seleccionado

## 13.1. TableView principal

### Columnas oficiales
- Proveedor
- Órdenes abiertas
- Última compra
- Categoría principal
- Estado

### Seeds oficiales
1. VisionLine Distribución | 2 | 12/04/2026 | Monturas | Activo
2. LabVision Externo | 1 | 13/04/2026 | Lentes | Activo
3. CooperVision | 1 | 10/04/2026 | Lentes de contacto | Bajo observación

### Tooltip de la tabla
**Seleccione un proveedor para ver su actividad reciente de compras**

## 13.2. Detalle por proveedor

### Campos visibles
- Proveedor
- Órdenes abiertas
- Última compra
- Tiempo promedio
- Observación comercial

### Seeds
- Proveedor: **VisionLine Distribución**
- Órdenes abiertas: **2**
- Última compra: **12/04/2026**
- Tiempo promedio: **3.2 días**
- Observación comercial: **Abastece principalmente monturas de alta rotación**

### Botones oficiales del submódulo
- **Abrir proveedor**
- **Nueva orden con proveedor**
- **Ver histórico**

### Tooltips
- Abrir proveedor: **Abrir la ficha completa del proveedor seleccionado**
- Nueva orden con proveedor: **Crear una orden usando el proveedor seleccionado**
- Ver histórico: **Consultar el histórico de compras de este proveedor**

### Estado vacío
**No hay actividad de compras visible para este proveedor**

---

# 14. Sub-vista 4: Back-orders y pendientes

## Función
Concentrar pedidos, ítems o referencias bloqueadas por falta de stock o entrega incompleta.

## Contenedor principal sugerido
**BorderPane** con indicadores superiores y **TableView** central.

### Estructura interna
1. Resumen de pendientes
2. Tabla de back-orders
3. Acciones de atención

## 14.1. Resumen superior

### Campos visibles
- Pendientes críticos
- Back-orders abiertos
- Ítems bloqueados

### Seeds
- Pendientes críticos: **3**
- Back-orders abiertos: **2**
- Ítems bloqueados: **7**

## 14.2. TableView principal

### Columnas oficiales
- Referencia
- Orden
- Proveedor
- Ítem pendiente
- Cantidad
- Fecha esperada
- Estado

### Seeds oficiales
1. BO-009 | OC-075 | LabVision Externo | Lente monofocal 1.60 | 2 | 18/04/2026 | Back-order
2. BO-010 | OC-082 | VisionLine Distribución | Montura MZ-220 | 2 | 17/04/2026 | Parcial
3. BO-011 | OC-084 | ABB Optical | Lente de contacto mensual | 1 | 19/04/2026 | En revisión

### Tooltip de la tabla
**Ítems pendientes o bloqueados por falta de entrega completa**

### Botones oficiales del submódulo
- **Marcar seguimiento**
- **Actualizar fecha esperada**
- **Notificar sucursal**

### Tooltips
- Marcar seguimiento: **Mantener el caso pendiente bajo revisión operativa**
- Actualizar fecha esperada: **Modificar la fecha estimada de disponibilidad**
- Notificar sucursal: **Informar a la sede afectada sobre el pendiente actual**

### Estado vacío
**No hay back-orders ni pendientes críticos en este momento**

---

# 15. Sub-vista 5: Recepción vinculada a compras

## Función
Relacionar cada orden de compra con su recepción parcial o completa y su impacto en el abastecimiento real.

## Contenedor principal sugerido
**SplitPane** horizontal

### Panel izquierdo
**TableView** de recepciones vinculadas

### Panel derecho
Detalle de la recepción seleccionada

## 15.1. TableView principal

### Columnas oficiales
- Recepción
- Orden
- Fecha
- Estado
- Diferencias
- Responsable

### Seeds oficiales
1. RC-038 | OC-082 | 14/04/2026 | Parcial | 2 referencias pendientes | Laura Gómez
2. RC-039 | OC-079 | 09/04/2026 | Completa | Sin diferencias | Carlos Mendoza
3. RC-040 | OC-083 | 15/04/2026 | Parcial | 1 ítem faltante | Ana Vera

### Tooltip de la tabla
**Recepciones físicas vinculadas a órdenes de compra**

## 15.2. Detalle de recepción

### Campos visibles
- Recepción
- Orden
- Proveedor
- Estado
- Diferencias
- Impacto en inventario

### Seeds
- Recepción: **RC-038**
- Orden: **OC-082**
- Proveedor: **VisionLine Distribución**
- Estado: **Parcial**
- Diferencias: **2 referencias pendientes**
- Impacto en inventario: **Se actualizó el stock recibido de 10 ítems**

### Botones oficiales del submódulo
- **Registrar recepción**
- **Marcar completa**
- **Registrar diferencia**

### Tooltips
- Registrar recepción: **Crear o completar un registro de recepción vinculado a la orden**
- Marcar completa: **Indicar que la recepción ya quedó completa**
- Registrar diferencia: **Registrar un faltante, sobrante o diferencia documental**

### Estado vacío
**No hay recepciones visibles vinculadas a compras**

---

# 16. Sub-vista 6: Compras por sucursal

## Función
Mostrar el abastecimiento agrupado por sede destino para entender necesidades locales y carga de reposición.

## Contenedor principal sugerido
**BorderPane** con indicadores superiores y **TableView** central.

### Estructura interna
1. Resumen por sede
2. Tabla de compras por sucursal
3. Lectura operativa breve

## 16.1. Resumen superior

### Campos visibles
- Sede con más compras
- Sede con más pendientes
- Sede con más stock crítico

### Seeds
- Sede con más compras: **Matriz Centro**
- Sede con más pendientes: **Norte Mall**
- Sede con más stock crítico: **Sur Express**

## 16.2. TableView principal

### Columnas oficiales
- Sucursal
- Solicitudes
- Órdenes abiertas
- Pendientes
- Total estimado
- Estado general

### Seeds oficiales
1. Matriz Centro | 4 | 3 | 1 | $ 2,740.00 | Operativa
2. Norte Mall | 3 | 2 | 2 | $ 1,980.00 | Bajo observación
3. Sur Express | 1 | 1 | 1 | $ 620.00 | Crítica

### Tooltip de la tabla
**Lectura del abastecimiento agrupado por sucursal destino**

### Botones oficiales del submódulo
- **Abrir sucursal**
- **Abrir inventario local**
- **Exportar por sucursal**

### Tooltips
- Abrir sucursal: **Abrir el módulo Sucursales con la sede seleccionada**
- Abrir inventario local: **Abrir el inventario filtrado por la sucursal seleccionada**
- Exportar por sucursal: **Exportar la lectura de compras por sede**

### Estado vacío
**No hay datos de compras suficientes por sucursal en el período actual**

---

# 17. Sub-vista 7: Histórico y búsqueda avanzada

## Función
Consultar compras históricas, solicitudes antiguas, recepciones cerradas y registros por proveedor o categoría.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: filtros extendidos
- **center**: **TableView** de resultados

## 17.1. Filtros extendidos

### Controles sugeridos
- TextField: **Proveedor, orden, solicitud o referencia**
- ComboBox: **Estado**
- ComboBox: **Proveedor**
- ComboBox: **Categoría**
- ComboBox: **Sucursal**
- DatePicker: **Desde**
- DatePicker: **Hasta**
- Button: **Buscar histórico**
- Button: **Limpiar filtros**

### Tooltip general
**Filtre compras históricas por referencia, proveedor, categoría, estado o fecha**

## 17.2. TableView principal

### Columnas oficiales
- Fecha
- Referencia
- Tipo de registro
- Proveedor
- Estado
- Observación

### Seeds oficiales
1. 10/04/2026 | OC-079 | Orden recibida | NovaFrame Import | Cerrada | Abastecimiento completo
2. 08/04/2026 | SC-010 | Solicitud | VisionLine Distribución | Cerrada | Convertida en orden
3. 05/04/2026 | BO-007 | Back-order | CooperVision | Abierta | Esperando reposición de contacto

### Tooltip de la tabla
**Consulte el histórico de compras, solicitudes y pendientes del módulo**

### Botones oficiales del submódulo
- **Abrir registro**
- **Exportar histórico**

### Tooltips
- Abrir registro: **Abrir el detalle del registro histórico seleccionado**
- Exportar histórico: **Exportar el histórico visible de compras**

### Estado vacío
**No hay registros históricos que coincidan con los criterios actuales**

---

# 18. Formulario conceptual: Nueva orden de compra

## Función
Permitir registrar una orden de compra nueva dentro de la arquitectura del módulo.

## Contenedor sugerido
Vista interna o diálogo amplio controlado. Para esta maqueta conviene resolverlo dentro del mismo módulo.

## Estructura sugerida
**ScrollPane** con **VBox** y bloques en **GridPane**.

### Grupos de campos
1. Datos base de la orden
2. Proveedor y sucursal destino
3. Ítems de compra
4. Observaciones y prioridad

### Campos mínimos
- Referencia de orden
- Proveedor
- Sucursal destino
- Categoría principal
- Prioridad
- Fecha esperada
- Observación

### Botones finales
- **Guardar orden**
- **Cancelar**

### Tooltips
- Guardar orden: **Registrar la nueva orden de compra dentro del sistema**
- Cancelar: **Cerrar la creación de la orden sin guardar**

---

# 19. Seed data oficial del módulo Compras

## Proveedores
- VisionLine Distribución
- LabVision Externo
- NovaFrame Import
- ABB Optical
- CooperVision

## Sucursales
- Matriz Centro
- Norte Mall
- Sur Express

## Categorías
- Monturas
- Lentes
- Lentes de contacto
- Accesorios
- Insumos

## Referencias
- SC-014
- SC-015
- SC-016
- SC-017
- OC-082
- OC-083
- OC-084
- OC-079
- BO-009
- BO-010
- BO-011
- RC-038
- RC-039
- RC-040

## Estados usados en el módulo
- Solicitud abierta
- En revisión
- Borrador
- Enviada
- Parcial
- Recibida
- Cancelada
- Back-order
- Con diferencia
- Cerrada

---

# 20. Textos oficiales del módulo Compras

## Títulos y labels principales
- Compras
- Solicitudes, órdenes, abastecimiento, pendientes y recepción de compras para el negocio óptico
- Buscar
- Estado
- Proveedor
- Categoría
- Sucursal destino
- Desde
- Hasta
- Solo pendientes críticos
- Solicitudes de compra
- Órdenes de compra
- Compras por proveedor
- Back-orders y pendientes
- Recepción vinculada a compras
- Compras por sucursal
- Histórico y búsqueda avanzada
- Referencia
- Tipo de registro
- Total estimado
- Tiempo estimado
- Recepción vinculada
- Diferencias
- Observación clave

## Botones oficiales
- Actualizar compras
- Exportar compras
- Nueva orden de compra
- Limpiar filtros
- Abrir orden
- Editar compra
- Registrar recepción
- Abrir proveedor
- Ver inventario
- Convertir en orden
- Editar solicitud
- Cancelar solicitud
- Duplicar orden
- Cancelar orden
- Nueva orden con proveedor
- Ver histórico
- Marcar seguimiento
- Actualizar fecha esperada
- Notificar sucursal
- Marcar completa
- Registrar diferencia
- Abrir sucursal
- Abrir inventario local
- Exportar por sucursal
- Buscar histórico
- Abrir registro
- Exportar histórico
- Guardar orden
- Cancelar

## Placeholders
- Orden, solicitud, proveedor, producto o referencia
- Proveedor, orden, solicitud o referencia

## Empty states
- No hay solicitudes visibles con los filtros actuales
- No hay órdenes visibles con los filtros actuales
- No hay actividad de compras visible para este proveedor
- No hay back-orders ni pendientes críticos en este momento
- No hay recepciones visibles vinculadas a compras
- No hay datos de compras suficientes por sucursal en el período actual
- No hay registros históricos que coincidan con los criterios actuales

---

# 21. Reglas visuales específicas del módulo Compras

- Solicitudes de compra debe sentirse como la puerta de entrada estratégica del módulo
- Órdenes de compra debe verse documental y muy clara en estados
- Compras por proveedor debe comunicar relación operativa y comercial
- Back-orders y pendientes debe verse como bandeja de atención prioritaria
- Recepción vinculada a compras debe comunicar trazabilidad real entre pedido y entrada física
- Compras por sucursal debe mostrar lectura territorial del abastecimiento
- Histórico y búsqueda avanzada debe sentirse fuerte para consulta
- el panel derecho debe resumir bien la compra seleccionada sin saturar
- no abusar de formas redondeadas ni de tarjetas decorativas
- la prioridad debe ser entender el flujo de aprovisionamiento óptico de forma ordenada

---

# 22. Relación del módulo Compras con otros módulos

Compras debe conectarse con:
- Proveedores, porque el abastecimiento nace de relaciones comerciales concretas
- Inventario, porque muchas compras nacen de stock crítico o reposición
- Sucursales, porque cada compra suele tener una sede destino
- Reportes, porque el comportamiento de compras también debe poder medirse
- Configuración, porque algunas reglas globales de abastecimiento pueden venir desde allí
- Inicio, porque el panel principal puede resumir pendientes críticos, back-orders o órdenes abiertas

---

# 23. Cierre del módulo Compras

Este módulo debe transmitir que la óptica no repone ni compra improvisando. Debe verse como una herramienta que permite detectar necesidades reales, transformarlas en órdenes formales, entender el estado del abastecimiento, relacionar recepciones y controlar pendientes. No se trata solo de crear pedidos, sino de administrar el flujo completo de compra hasta que el abastecimiento impacta en la operación.

La complejidad correcta del módulo está en que cada sub-vista resuelve una parte clara del aprovisionamiento sin convertir la experiencia en un sistema de procurement excesivamente industrial.