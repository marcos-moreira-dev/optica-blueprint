# Lienzo del módulo Proveedores para sistema de óptica en JavaFX

## 1. Identidad del módulo

### Nombre oficial del módulo en el menú
**Proveedores**

### Texto visible del botón del sidebar
**Proveedores**

### Tooltip del botón del sidebar
**Consultar, administrar y evaluar proveedores, laboratorios y abastecimiento de la óptica**

### Ícono conceptual
Proveedor, laboratorio, edificio comercial o abastecimiento.

### Título visible en pantalla
**Proveedores**

### Subtítulo visible en pantalla
**Relación comercial, catálogo, abastecimiento, recepciones y desempeño de proveedores**

### Tipo de módulo
Módulo administrativo y operativo de abastecimiento con sub-vistas internas especializadas.

### Objetivo del módulo
Permitir que la óptica controle con qué proveedores trabaja, qué productos o servicios abastece cada uno, cómo se relacionan con compras y recepciones, y qué tan confiable es su desempeño operativo.

---

## 2. Rol del módulo dentro de la arquitectura GUI

Proveedores no debe sentirse como una libreta de contactos. Debe verse como el módulo donde la óptica administra a quienes abastecen monturas, lentes, accesorios, insumos técnicos y trabajos de laboratorio.

### Regla arquitectónica principal
Cuando el usuario entre al módulo Proveedores, la región central principal del sistema debe reemplazarse por una vista completa llamada conceptualmente:
**ProveedoresModuleView**

### Estructura interna limpia del módulo
La vista Proveedores debe dividirse en:
- encabezado del módulo
- barra de filtros y búsqueda
- subnavegación interna del módulo
- región central intercambiable para sub-vistas
- panel lateral derecho con resumen persistente del proveedor seleccionado

### Filosofía de implementación
El usuario debe poder identificar un proveedor, revisar su perfil, ver qué abastece, consultar órdenes, revisar incidencias y evaluar desempeño sin salir del mismo módulo.

### Regla de arquitectura limpia
Debe existir una región interna estable que se reescriba solo con el submódulo activo. Esa región se denomina conceptualmente:
**proveedoresContentHostPane**

Ese contenedor puede ser un **StackPane** o el centro de un **BorderPane** secundario.

### Sub-vistas que viven dentro de proveedoresContentHostPane
- Directorio de proveedores
- Perfil comercial del proveedor
- Catálogo vinculado del proveedor
- Órdenes y abastecimiento
- Recepciones e incidencias
- Desempeño del proveedor
- Histórico y búsqueda avanzada

---

## 3. Sub-vistas oficiales del módulo Proveedores

## Sub-vistas definidas
1. **Directorio de proveedores**
2. **Perfil comercial del proveedor**
3. **Catálogo vinculado del proveedor**
4. **Órdenes y abastecimiento**
5. **Recepciones e incidencias**
6. **Desempeño del proveedor**
7. **Histórico y búsqueda avanzada**

## Orden de prioridad
1. Directorio de proveedores
2. Perfil comercial del proveedor
3. Catálogo vinculado del proveedor
4. Órdenes y abastecimiento
5. Recepciones e incidencias
6. Desempeño del proveedor
7. Histórico y búsqueda avanzada

## Vista por defecto al abrir el módulo
**Directorio de proveedores**

Motivo: es la vista que mejor responde a la pregunta base de abastecimiento: con qué proveedores trabaja la óptica y cuál es su situación actual.

---

## 4. Estructura visual general del módulo Proveedores

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
- panel derecho: resumen persistente del proveedor seleccionado

### Proporción recomendada
- región de trabajo: 68% a 72%
- panel derecho: 28% a 32%

### Regla de estabilidad visual
El panel derecho debe mantenerse visible durante casi toda la navegación para que el usuario no pierda contexto comercial y operativo del proveedor seleccionado.

---

## 5. Encabezado del módulo Proveedores

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
- Label principal: **Proveedores**
- Label secundario: **Relación comercial, catálogo, abastecimiento, recepciones y desempeño de proveedores**

### Zona derecha
Un **HBox** con:
- Button secundario: **Actualizar proveedores**
- Button secundario: **Exportar proveedores**
- Button principal: **Nuevo proveedor**

## Controles exactos

### Label principal
- Control: **Label**
- Texto exacto: **Proveedores**
- Tooltip: no necesita

### Label secundario
- Control: **Label**
- Texto exacto: **Relación comercial, catálogo, abastecimiento, recepciones y desempeño de proveedores**
- Tooltip: no necesita

### Botón secundario 1
- Control: **Button**
- Texto exacto: **Actualizar proveedores**
- Tooltip: **Recargar la información visible de proveedores y abastecimiento**
- Estilo: secundario

### Botón secundario 2
- Control: **Button**
- Texto exacto: **Exportar proveedores**
- Tooltip: **Exportar el directorio o el resumen visible de proveedores**
- Estilo: secundario

### Botón principal
- Control: **Button**
- Texto exacto: **Nuevo proveedor**
- Tooltip: **Registrar un nuevo proveedor o laboratorio para la óptica**
- Estilo: acción principal

---

## 7. Franja media: filtros y búsqueda

## Contenedor sugerido
**FlowPane**

## Función
Permitir localizar proveedores por nombre, tipo, estado, categoría abastecida o sucursal atendida.

## Controles oficiales

### Búsqueda rápida
- Control: **TextField**
- Label asociado: **Buscar**
- Placeholder exacto: **Proveedor, laboratorio, contacto o referencia**
- Tooltip: **Buscar proveedores por nombre, laboratorio, contacto o texto relacionado**

### Filtro por tipo de proveedor
- Control: **ComboBox**
- Label asociado: **Tipo**
- Ítems de semilla:
  - Todos
  - Proveedor de monturas
  - Laboratorio de lentes
  - Proveedor de lentes de contacto
  - Proveedor de accesorios
  - Proveedor de insumos técnicos
- Valor inicial: **Todos**
- Tooltip: **Filtrar proveedores según el tipo principal de abastecimiento**

### Filtro por estado
- Control: **ComboBox**
- Label asociado: **Estado**
- Ítems de semilla:
  - Todos
  - Activo
  - Inactivo
  - Bajo observación
  - Preferente
- Valor inicial: **Todos**
- Tooltip: **Filtrar proveedores según su estado general de uso**

### Filtro por categoría abastecida
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
- Tooltip: **Filtrar por la categoría principal que abastece el proveedor**

### Filtro por sucursal atendida
- Control: **ComboBox**
- Label asociado: **Sucursal**
- Ítems de semilla:
  - Todas
  - Matriz Centro
  - Norte Mall
  - Sur Express
- Valor inicial: **Todas**
- Tooltip: **Filtrar por la sede que recibe abastecimiento de este proveedor**

### Opción rápida
- Control: **CheckBox**
- Texto exacto: **Solo con incidencias recientes**
- Tooltip: **Mostrar solo proveedores con incidencias o diferencias recientes**

### Botón limpiar
- Control: **Button**
- Texto exacto: **Limpiar filtros**
- Tooltip: **Restablecer la búsqueda y todos los filtros del módulo**

---

## 8. Franja inferior: subnavegación interna del módulo

## Contenedor sugerido
**HBox** con **ToggleButton** dentro de un **ToggleGroup**

## Sub-vistas visibles en la barra
- Directorio de proveedores
- Perfil comercial del proveedor
- Catálogo vinculado del proveedor
- Órdenes y abastecimiento
- Recepciones e incidencias
- Desempeño del proveedor
- Histórico y búsqueda avanzada

## Tooltips exactos
- Directorio de proveedores: **Consultar el listado general de proveedores y laboratorios**
- Perfil comercial del proveedor: **Ver la ficha comercial y operativa del proveedor seleccionado**
- Catálogo vinculado del proveedor: **Consultar los productos, líneas o servicios que abastece este proveedor**
- Órdenes y abastecimiento: **Consultar pedidos, abastecimiento y estado de órdenes vinculadas al proveedor**
- Recepciones e incidencias: **Consultar recepciones, diferencias y problemas relacionados con el proveedor**
- Desempeño del proveedor: **Consultar tiempos, cumplimiento e indicadores operativos del proveedor**
- Histórico y búsqueda avanzada: **Consultar proveedores, órdenes o recepciones históricas mediante filtros amplios**

## Regla visual
La sub-vista activa debe quedar clara, pero sin competir con el contenido administrativo principal.

---

## 9. Cuerpo principal del módulo Proveedores

## Contenedor sugerido
**SplitPane** horizontal

### Panel izquierdo o principal
Sub-vista activa del módulo.

### Panel derecho
Resumen persistente del proveedor seleccionado.

---

# 10. Panel derecho persistente: resumen del proveedor

## Función
Mantener visible el contexto comercial y operativo del proveedor seleccionado.

## Contenedor sugerido
**ScrollPane** con un **VBox** interno.

## Bloques internos del panel derecho
1. Identidad del proveedor
2. Contacto principal
3. Cobertura de abastecimiento
4. Estado operativo
5. Acciones rápidas

## 10.1. Bloque: Identidad del proveedor

### Campos visibles
- Proveedor
- Tipo principal
- Estado

### Seeds
- **Proveedor: VisionLine Distribución**
- **Tipo principal: Proveedor de monturas**
- **Estado: Preferente**

## 10.2. Bloque: Contacto principal

### Campos visibles
- Contacto
- Teléfono
- Correo

### Seeds
- **Contacto: Laura Torres**
- **Teléfono: 099 600 1000**
- **Correo: ventas@visionline.local**

## 10.3. Bloque: Cobertura de abastecimiento

### Campos visibles
- Categoría abastecida
- Sucursales atendidas
- Última recepción

### Seeds
- **Categoría abastecida: Monturas**
- **Sucursales atendidas: Matriz Centro, Norte Mall**
- **Última recepción: 14/04/2026**

## 10.4. Bloque: Estado operativo

### Campos visibles
- Tiempo estimado
- Incidencias recientes
- Observación clave

### Seeds
- **Tiempo estimado: 3 días**
- **Incidencias recientes: 1**
- **Observación clave: Buen cumplimiento en pedidos de monturas clásicas**

## 10.5. Bloque: Acciones rápidas

### Botones oficiales
- **Abrir perfil**
- **Ver catálogo**
- **Ver órdenes**
- **Registrar incidencia**
- **Abrir compras**

### Tooltips exactos
- Abrir perfil: **Abrir la ficha completa del proveedor seleccionado**
- Ver catálogo: **Consultar el catálogo vinculado al proveedor**
- Ver órdenes: **Consultar pedidos y abastecimiento relacionados con este proveedor**
- Registrar incidencia: **Registrar una diferencia o problema asociado al proveedor**
- Abrir compras: **Abrir el módulo Compras vinculado al proveedor seleccionado**

---

# 11. Sub-vista 1: Directorio de proveedores

## Función
Servir como listado principal de proveedores, laboratorios y abastecedores registrados.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: pequeño resumen del directorio
- **center**: **TableView** principal
- **bottom**: resumen de resultados visibles

## 11.1. Franja superior del directorio

### Contenido
- Label: **Directorio de proveedores**
- Label secundario: **5 proveedores visibles**

### Tooltips
- Directorio de proveedores: **Proveedores y laboratorios visibles según los filtros actuales**
- 5 proveedores visibles: **Cantidad de proveedores mostrados en el directorio**

## 11.2. TableView principal

### Columnas oficiales
- Proveedor
- Tipo
- Contacto
- Tiempo estimado
- Estado
- Cobertura

### Seeds oficiales
1. VisionLine Distribución | Proveedor de monturas | Laura Torres | 3 días | Preferente | Matriz Centro, Norte Mall
2. LabVision Externo | Laboratorio de lentes | Mario Salazar | 4 días | Activo | Matriz Centro, Norte Mall, Sur Express
3. NovaFrame Import | Proveedor de monturas | Andrea Ponce | 5 días | Activo | Norte Mall
4. ABB Optical | Proveedor de lentes de contacto | Diego Celi | 4 días | Activo | Matriz Centro
5. CooperVision | Proveedor de lentes de contacto | Paola Ruiz | 6 días | Bajo observación | Norte Mall

### Tooltip de la tabla
**Seleccione un proveedor para ver su perfil, catálogo, órdenes y desempeño**

### Estado vacío
**No hay proveedores visibles con los filtros actuales**

## 11.3. Pie del listado

### Textos exactos
- **Mostrando 5 de 5 proveedores**
- **Ordenado por estado y nombre**

### Tooltips
- Mostrando 5 de 5 proveedores: **Resumen de proveedores visibles en el directorio actual**
- Ordenado por estado y nombre: **Criterio de ordenamiento aplicado al directorio**

---

# 12. Sub-vista 2: Perfil comercial del proveedor

## Función
Mostrar la ficha completa y operativa del proveedor seleccionado.

## Contenedor principal sugerido
**ScrollPane** con un **VBox** interno y bloques en **GridPane**.

### Estructura interna
1. Identidad comercial
2. Contacto y atención
3. Cobertura y condiciones
4. Observaciones internas

## 12.1. Bloque: Identidad comercial

### Campos visibles
- Nombre comercial
- Código interno
- Tipo de proveedor
- Estado

### Seeds
- Nombre comercial: **VisionLine Distribución**
- Código interno: **PV-001**
- Tipo de proveedor: **Proveedor de monturas**
- Estado: **Preferente**

## 12.2. Bloque: Contacto y atención

### Campos visibles
- Contacto principal
- Teléfono
- Correo
- Ciudad

### Seeds
- Contacto principal: **Laura Torres**
- Teléfono: **099 600 1000**
- Correo: **ventas@visionline.local**
- Ciudad: **Guayaquil**

## 12.3. Bloque: Cobertura y condiciones

### Campos visibles
- Categoría abastecida
- Sucursales atendidas
- Tiempo estimado
- Entrega parcial permitida

### Seeds
- Categoría abastecida: **Monturas**
- Sucursales atendidas: **Matriz Centro, Norte Mall**
- Tiempo estimado: **3 días**
- Entrega parcial permitida: **Sí**

## 12.4. Bloque: Observaciones internas

### Seed
**Proveedor preferente para monturas de rotación media y alta. Buen cumplimiento y respuesta rápida.**

### Botones oficiales del submódulo
- **Guardar perfil**
- **Editar proveedor**
- **Desactivar proveedor**

### Tooltips
- Guardar perfil: **Guardar los cambios realizados en la ficha del proveedor**
- Editar proveedor: **Modificar información comercial y operativa del proveedor**
- Desactivar proveedor: **Marcar el proveedor como no operativo dentro del sistema**

### Estado vacío
**Seleccione un proveedor para ver su perfil comercial**

---

# 13. Sub-vista 3: Catálogo vinculado del proveedor

## Función
Mostrar los productos, líneas o servicios que el proveedor abastece a la óptica.

## Contenedor principal sugerido
**SplitPane** horizontal

### Panel izquierdo
**TableView** del catálogo vinculado

### Panel derecho
Detalle del ítem seleccionado

## 13.1. TableView principal

### Columnas oficiales
- Referencia
- Nombre
- Categoría
- Marca o línea
- Estado

### Seeds oficiales
1. MZ-201 | Armazón clásico | Monturas | VisionLine | Activo
2. MZ-204 | Armazón urbano | Monturas | VisionLine | Activo
3. MZ-220 | Montura liviana | Monturas | VisionLine Lite | Activo

### Tooltip de la tabla
**Productos o líneas comerciales abastecidas por el proveedor seleccionado**

## 13.2. Detalle del ítem

### Campos visibles
- Referencia
- Nombre
- Categoría
- Línea comercial
- Observación de abastecimiento

### Seeds
- Referencia: **MZ-201**
- Nombre: **Armazón clásico**
- Categoría: **Monturas**
- Línea comercial: **VisionLine**
- Observación de abastecimiento: **Buena rotación en Matriz Centro**

### Botones oficiales del submódulo
- **Ver producto**
- **Marcar preferente**
- **Abrir inventario**

### Tooltips
- Ver producto: **Consultar el producto dentro del contexto del proveedor**
- Marcar preferente: **Marcar esta línea como preferente para abastecimiento**
- Abrir inventario: **Abrir el producto seleccionado dentro del módulo Inventario**

### Estado vacío
**No hay catálogo vinculado visible para este proveedor**

---

# 14. Sub-vista 4: Órdenes y abastecimiento

## Función
Consultar pedidos, estado de abastecimiento y cumplimiento del proveedor.

## Contenedor principal sugerido
**BorderPane** con indicadores superiores y **TableView** central.

### Estructura interna
1. Resumen de órdenes
2. Tabla de abastecimiento
3. Acciones operativas

## 14.1. Resumen superior

### Campos visibles
- Órdenes abiertas
- Órdenes parciales
- Back-order
- Última orden

### Seeds
- Órdenes abiertas: **3**
- Órdenes parciales: **1**
- Back-order: **1**
- Última orden: **OC-082**

## 14.2. TableView principal

### Columnas oficiales
- Orden
- Fecha
- Estado
- Total ítems
- Recepción
- Observación

### Seeds oficiales
1. OC-082 | 12/04/2026 | Pendiente | 12 | Parcial | Faltan 2 referencias
2. OC-079 | 08/04/2026 | Recibida | 8 | Completa | Sin novedades
3. OC-075 | 02/04/2026 | Back-order | 5 | Pendiente | Esperando reposición de laboratorio

### Tooltip de la tabla
**Órdenes de compra o abastecimiento vinculadas al proveedor seleccionado**

### Botones oficiales del submódulo
- **Abrir orden**
- **Registrar recepción**
- **Abrir compras**

### Tooltips
- Abrir orden: **Consultar la orden vinculada al proveedor**
- Registrar recepción: **Registrar la recepción parcial o completa de la orden seleccionada**
- Abrir compras: **Abrir el módulo Compras con el proveedor seleccionado**

### Estado vacío
**No hay órdenes visibles para este proveedor**

---

# 15. Sub-vista 5: Recepciones e incidencias

## Función
Registrar diferencias, recepciones incompletas, entregas tardías o problemas de abastecimiento.

## Contenedor principal sugerido
**SplitPane** horizontal

### Panel izquierdo
**TableView** de recepciones e incidencias

### Panel derecho
Detalle del registro seleccionado

## 15.1. TableView principal

### Columnas oficiales
- Fecha
- Tipo
- Orden
- Estado
- Responsable

### Seeds oficiales
1. 14/04/2026 | Recepción parcial | OC-082 | Abierta | Laura Gómez
2. 09/04/2026 | Entrega completa | OC-079 | Cerrada | Carlos Mendoza
3. 03/04/2026 | Diferencia de referencia | OC-075 | Bajo observación | Ana Vera

### Tooltip de la tabla
**Recepciones, diferencias o incidencias asociadas al proveedor seleccionado**

## 15.2. Detalle del registro

### Campos visibles
- Tipo de registro
- Fecha
- Orden
- Estado
- Descripción
- Resolución

### Seeds
- Tipo de registro: **Recepción parcial**
- Fecha: **14/04/2026**
- Orden: **OC-082**
- Estado: **Abierta**
- Descripción: **No llegaron 2 monturas solicitadas en el pedido**
- Resolución: **Pendiente de confirmación del proveedor**

### Botones oficiales del submódulo
- **Registrar incidencia**
- **Marcar resuelta**
- **Notificar compras**

### Tooltips
- Registrar incidencia: **Registrar una nueva diferencia o problema del proveedor**
- Marcar resuelta: **Cerrar el registro seleccionado**
- Notificar compras: **Derivar el caso al flujo de compras o abastecimiento**

### Estado vacío
**No hay recepciones ni incidencias visibles para este proveedor**

---

# 16. Sub-vista 6: Desempeño del proveedor

## Función
Mostrar una lectura simple y útil del rendimiento del proveedor en el tiempo.

## Contenedor principal sugerido
**BorderPane** con KPI superiores y **TableView** central.

### Estructura interna
1. KPI de desempeño
2. Tabla de indicadores
3. Lectura gerencial breve

## 16.1. KPI de desempeño

### Campos visibles
- Tiempo promedio de entrega
- Entregas completas
- Incidencias recientes
- Evaluación interna

### Seeds
- Tiempo promedio de entrega: **3.2 días**
- Entregas completas: **84%**
- Incidencias recientes: **1**
- Evaluación interna: **Alta**

## 16.2. Tabla de indicadores

### Columnas oficiales
- Indicador
- Valor
- Estado
- Observación

### Seeds oficiales
1. Tiempo promedio | 3.2 días | En meta | Cumplimiento estable
2. Entregas completas | 84% | En meta | Buena consistencia
3. Incidencias | 1 | Bajo observación | Revisar diferencia reciente
4. Back-order | 1 caso | Bajo observación | Pendiente de cierre

### Botones oficiales del submódulo
- **Exportar desempeño**
- **Marcar revisión**
- **Abrir histórico**

### Tooltips
- Exportar desempeño: **Exportar el desempeño visible del proveedor seleccionado**
- Marcar revisión: **Señalar al proveedor para revisión interna**
- Abrir histórico: **Abrir el histórico asociado al proveedor**

### Estado vacío
**No hay datos suficientes para evaluar el desempeño de este proveedor**

---

# 17. Sub-vista 7: Histórico y búsqueda avanzada

## Función
Consultar proveedores, órdenes, catálogos y recepciones históricas mediante filtros amplios.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: filtros extendidos
- **center**: **TableView** de resultados

## 17.1. Filtros extendidos

### Controles sugeridos
- TextField: **Proveedor, orden o referencia**
- ComboBox: **Tipo**
- ComboBox: **Estado**
- ComboBox: **Categoría**
- ComboBox: **Sucursal**
- DatePicker: **Desde**
- DatePicker: **Hasta**
- Button: **Buscar histórico**
- Button: **Limpiar filtros**

### Tooltip general
**Filtre proveedores, órdenes o registros históricos por tipo, estado, categoría o fecha**

## 17.2. TableView principal

### Columnas oficiales
- Fecha
- Proveedor
- Tipo de registro
- Referencia
- Estado
- Observación

### Seeds oficiales
1. 10/04/2026 | VisionLine Distribución | Orden recibida | OC-079 | Cerrada | Abastecimiento completo
2. 08/04/2026 | LabVision Externo | Recepción parcial | OC-077 | Bajo observación | Pendiente confirmación
3. 05/04/2026 | CooperVision | Back-order | OC-072 | Abierta | Esperando stock de contacto

### Tooltip de la tabla
**Consulte el histórico de proveedores y abastecimiento por período o criterio**

### Botones oficiales del submódulo
- **Abrir registro**
- **Exportar histórico**

### Tooltips
- Abrir registro: **Abrir el registro histórico seleccionado**
- Exportar histórico: **Exportar el histórico visible del módulo Proveedores**

### Estado vacío
**No hay registros históricos que coincidan con los criterios actuales**

---

# 18. Formulario conceptual: Nuevo proveedor

## Función
Permitir registrar un proveedor nuevo dentro de la arquitectura del módulo.

## Contenedor sugerido
Vista interna o diálogo amplio controlado. Para esta maqueta conviene resolverlo dentro del mismo módulo.

## Estructura sugerida
**ScrollPane** con **VBox** y bloques en **GridPane**.

### Grupos de campos
1. Identidad comercial
2. Contacto
3. Tipo y abastecimiento
4. Cobertura y observaciones

### Campos mínimos
- Nombre comercial
- Código interno
- Tipo de proveedor
- Contacto principal
- Teléfono
- Correo
- Categoría abastecida
- Sucursales atendidas
- Tiempo estimado
- Estado

### Botones finales
- **Guardar proveedor**
- **Cancelar**

### Tooltips
- Guardar proveedor: **Registrar el nuevo proveedor dentro del sistema**
- Cancelar: **Cerrar el registro de proveedor sin guardar**

---

# 19. Seed data oficial del módulo Proveedores

## Proveedores
- VisionLine Distribución
- LabVision Externo
- NovaFrame Import
- ABB Optical
- CooperVision

## Tipos de proveedor
- Proveedor de monturas
- Laboratorio de lentes
- Proveedor de lentes de contacto
- Proveedor de accesorios
- Proveedor de insumos técnicos

## Contactos
- Laura Torres
- Mario Salazar
- Andrea Ponce
- Diego Celi
- Paola Ruiz

## Categorías abastecidas
- Monturas
- Lentes
- Lentes de contacto
- Accesorios
- Insumos

## Estados usados en el módulo
- Activo
- Inactivo
- Bajo observación
- Preferente
- Entrega parcial
- Entrega completa
- Con incidencia
- Back-order

## Referencias
- PV-001
- PV-002
- OC-082
- OC-079
- OC-075

---

# 20. Textos oficiales del módulo Proveedores

## Títulos y labels principales
- Proveedores
- Relación comercial, catálogo, abastecimiento, recepciones y desempeño de proveedores
- Buscar
- Tipo
- Estado
- Categoría
- Sucursal
- Solo con incidencias recientes
- Directorio de proveedores
- Perfil comercial del proveedor
- Catálogo vinculado del proveedor
- Órdenes y abastecimiento
- Recepciones e incidencias
- Desempeño del proveedor
- Histórico y búsqueda avanzada
- Proveedor
- Contacto
- Tiempo estimado
- Cobertura
- Categoría abastecida
- Última recepción
- Tipo principal
- Estado operativo
- Evaluación interna
- Incidencias recientes

## Botones oficiales
- Actualizar proveedores
- Exportar proveedores
- Nuevo proveedor
- Limpiar filtros
- Abrir perfil
- Ver catálogo
- Ver órdenes
- Registrar incidencia
- Abrir compras
- Guardar perfil
- Editar proveedor
- Desactivar proveedor
- Ver producto
- Marcar preferente
- Abrir inventario
- Abrir orden
- Registrar recepción
- Notificar compras
- Marcar resuelta
- Exportar desempeño
- Marcar revisión
- Abrir histórico
- Buscar histórico
- Abrir registro
- Exportar histórico
- Guardar proveedor
- Cancelar

## Placeholders
- Proveedor, laboratorio, contacto o referencia
- Proveedor, orden o referencia

## Empty states
- No hay proveedores visibles con los filtros actuales
- Seleccione un proveedor para ver su perfil comercial
- No hay catálogo vinculado visible para este proveedor
- No hay órdenes visibles para este proveedor
- No hay recepciones ni incidencias visibles para este proveedor
- No hay datos suficientes para evaluar el desempeño de este proveedor
- No hay registros históricos que coincidan con los criterios actuales

---

# 21. Reglas visuales específicas del módulo Proveedores

- el Directorio de proveedores debe sentirse como la vista principal del módulo
- el panel derecho debe mantener contexto rápido sin saturar de texto
- Perfil comercial del proveedor debe verse institucional y operativo a la vez
- Catálogo vinculado del proveedor debe sentirse técnico-comercial, no decorativo
- Órdenes y abastecimiento debe verse administrativo y muy claro en estados
- Recepciones e incidencias debe comunicar control de cumplimiento
- Desempeño del proveedor debe verse analítico pero sobrio
- Histórico y búsqueda avanzada debe sentirse fuerte para consulta
- no abusar de formas redondeadas ni de tarjetas decorativas
- la prioridad debe ser entender al proveedor como parte real del flujo óptico

---

# 22. Relación del módulo Proveedores con otros módulos

Proveedores debe conectarse con:
- Compras, porque muchas órdenes de abastecimiento nacen o se consultan desde aquí
- Inventario, porque los productos y su reposición dependen de proveedores concretos
- Configuración, porque algunos parámetros base de abastecimiento se definen allí
- Sucursales, porque cada proveedor puede abastecer una o varias sedes
- Reportes, porque el desempeño de proveedores también debe poder analizarse
- Inicio, porque el panel principal puede resumir incidencias, back-orders o proveedores bajo observación

---

# 23. Cierre del módulo Proveedores

Este módulo debe transmitir que la óptica no compra ni se abastece a ciegas. Debe verse como una herramienta que permite saber quién abastece, qué abastece, cómo entrega, qué incidencias genera y qué tan confiable resulta cada proveedor o laboratorio. No se trata de guardar teléfonos, sino de administrar relaciones de abastecimiento reales dentro del flujo del negocio.

La complejidad correcta del módulo está en que cada sub-vista resuelve una parte clara de la relación con proveedores sin convertir la experiencia en un ERP pesado o excesivamente industrial.