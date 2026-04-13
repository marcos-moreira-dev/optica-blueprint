# Lienzo del módulo Inventario para sistema de óptica en JavaFX

## 1. Identidad del módulo

### Nombre oficial del módulo en el menú
**Inventario**

### Texto visible del botón del sidebar
**Inventario**

### Tooltip del botón del sidebar
**Consultar existencias, movimientos, reposición y recepción de productos ópticos**

### Ícono conceptual
Caja, almacén, stock o producto óptico.

### Título visible en pantalla
**Inventario**

### Subtítulo visible en pantalla
**Control de monturas, lentes, existencias, movimientos, reposición y abastecimiento**

### Tipo de módulo
Módulo operativo de control de existencias con sub-vistas internas especializadas.

### Objetivo del módulo
Permitir que la óptica controle productos, variantes, movimientos, recepción de mercadería, stock crítico y reposición por sucursal, manteniendo una visión clara de disponibilidad y rotación.

---

## 2. Rol del módulo dentro de la arquitectura GUI

Inventario no debe sentirse como una tabla genérica de bodega. Debe verse como el centro de control de productos ópticos y sus existencias reales.

### Regla arquitectónica principal
Cuando el usuario entre al módulo Inventario, la región central principal del sistema debe reemplazarse por una vista completa llamada conceptualmente:
**InventarioModuleView**

### Estructura interna limpia del módulo
La vista Inventario debe dividirse en:
- encabezado del módulo
- barra de filtros y búsqueda
- subnavegación interna del módulo
- región central intercambiable para sub-vistas
- panel lateral derecho con resumen persistente del producto o movimiento seleccionado

### Filosofía de implementación
El usuario debe poder revisar catálogo, stock, movimientos y reposición sin salir del módulo ni perder el contexto del producto seleccionado.

### Regla de arquitectura limpia
Debe existir una región interna estable que se reescriba solo con el submódulo activo. Esa región se denomina conceptualmente:
**inventarioContentHostPane**

Ese contenedor puede ser un **StackPane** o el centro de un **BorderPane** secundario.

### Sub-vistas que viven dentro de inventarioContentHostPane
- Catálogo general
- Monturas
- Lentes y variantes
- Movimientos de inventario
- Reposición y stock crítico
- Recepción y abastecimiento
- Histórico y análisis de stock

---

## 3. Sub-vistas oficiales del módulo Inventario

## Sub-vistas definidas
1. **Catálogo general**
2. **Monturas**
3. **Lentes y variantes**
4. **Movimientos de inventario**
5. **Reposición y stock crítico**
6. **Recepción y abastecimiento**
7. **Histórico y análisis de stock**

## Orden de prioridad
1. Catálogo general
2. Monturas
3. Lentes y variantes
4. Movimientos de inventario
5. Reposición y stock crítico
6. Recepción y abastecimiento
7. Histórico y análisis de stock

## Vista por defecto al abrir el módulo
**Catálogo general**

Motivo: es la vista más útil para responder qué productos existen, dónde están y en qué estado se encuentran.

---

## 4. Estructura visual general del módulo Inventario

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
- panel derecho: resumen persistente del producto, variante o movimiento seleccionado

### Proporción recomendada
- región de trabajo: 68% a 72%
- panel derecho: 28% a 32%

### Regla de estabilidad visual
El panel derecho debe mantenerse visible en casi toda la navegación para que el usuario no pierda referencia sobre el producto seleccionado.

---

## 5. Encabezado del módulo Inventario

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
- Label principal: **Inventario**
- Label secundario: **Control de monturas, lentes, existencias, movimientos, reposición y abastecimiento**

### Zona derecha
Un **HBox** con:
- Button secundario: **Actualizar inventario**
- Button secundario: **Exportar inventario**
- Button principal: **Nuevo producto**

## Controles exactos

### Label principal
- Control: **Label**
- Texto exacto: **Inventario**
- Tooltip: no necesita

### Label secundario
- Control: **Label**
- Texto exacto: **Control de monturas, lentes, existencias, movimientos, reposición y abastecimiento**
- Tooltip: no necesita

### Botón secundario 1
- Control: **Button**
- Texto exacto: **Actualizar inventario**
- Tooltip: **Recargar el inventario visible según la sucursal y los filtros aplicados**
- Estilo: secundario

### Botón secundario 2
- Control: **Button**
- Texto exacto: **Exportar inventario**
- Tooltip: **Exportar el contenido visible del inventario**
- Estilo: secundario

### Botón principal
- Control: **Button**
- Texto exacto: **Nuevo producto**
- Tooltip: **Registrar un nuevo producto, montura, lente o accesorio**
- Estilo: acción principal

---

## 7. Franja media: filtros y búsqueda

## Contenedor sugerido
**FlowPane**

## Función
Permitir localizar productos por referencia, nombre, categoría, marca, sucursal o estado de stock.

## Controles oficiales

### Búsqueda rápida
- Control: **TextField**
- Label asociado: **Buscar**
- Placeholder exacto: **Código, nombre, marca o referencia**
- Tooltip: **Buscar productos por referencia, nombre, marca o texto relacionado**

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
- Tooltip: **Filtrar productos por categoría general**

### Filtro por estado de stock
- Control: **ComboBox**
- Label asociado: **Stock**
- Ítems de semilla:
  - Todos
  - Disponible
  - Bajo stock
  - Agotado
  - En reposición
  - Descontinuado
- Valor inicial: **Todos**
- Tooltip: **Filtrar productos según su estado actual de inventario**

### Filtro por marca
- Control: **ComboBox**
- Label asociado: **Marca**
- Ítems de semilla:
  - Todas
  - VisionLine
  - NovaFrame
  - UrbanEye
  - LabVision
- Valor inicial: **Todas**
- Tooltip: **Filtrar productos por marca**

### Filtro por sucursal
- Control: **ComboBox**
- Label asociado: **Sucursal**
- Ítems de semilla:
  - Todas
  - Matriz Centro
  - Norte Mall
- Valor inicial: **Todas**
- Tooltip: **Filtrar inventario por sucursal o ubicación**

### Filtro por proveedor
- Control: **ComboBox**
- Label asociado: **Proveedor**
- Ítems de semilla:
  - Todos
  - VisionLine Distribución
  - LabVision Externo
  - NovaFrame Import
- Valor inicial: **Todos**
- Tooltip: **Filtrar por proveedor principal del producto**

### Opción crítica
- Control: **CheckBox**
- Texto exacto: **Solo stock crítico**
- Tooltip: **Mostrar solo productos agotados o con bajo stock**

### Botón limpiar
- Control: **Button**
- Texto exacto: **Limpiar filtros**
- Tooltip: **Restablecer la búsqueda y todos los filtros del módulo**

---

## 8. Franja inferior: subnavegación interna del módulo

## Contenedor sugerido
**HBox** con **ToggleButton** dentro de un **ToggleGroup**

## Sub-vistas visibles en la barra
- Catálogo general
- Monturas
- Lentes y variantes
- Movimientos de inventario
- Reposición y stock crítico
- Recepción y abastecimiento
- Histórico y análisis de stock

## Tooltips exactos
- Catálogo general: **Ver el catálogo general de productos del inventario**
- Monturas: **Consultar monturas, marcas, colores, materiales y existencias**
- Lentes y variantes: **Consultar lentes, índices, tratamientos y variantes disponibles**
- Movimientos de inventario: **Revisar entradas, salidas y ajustes de stock**
- Reposición y stock crítico: **Identificar productos agotados, críticos o en reposición**
- Recepción y abastecimiento: **Registrar y consultar recepciones de mercadería**
- Histórico y análisis de stock: **Consultar comportamiento histórico, rotación y productos lentos**

## Regla visual
La sub-vista activa debe quedar clara, pero sin competir con el contenido principal.

---

## 9. Cuerpo principal del módulo Inventario

## Contenedor sugerido
**SplitPane** horizontal

### Panel izquierdo o principal
Sub-vista activa del módulo.

### Panel derecho
Resumen persistente del producto, variante o movimiento seleccionado.

---

# 10. Panel derecho persistente: resumen del producto

## Función
Mantener el contexto del producto o movimiento seleccionado mientras se navega entre sub-vistas.

## Contenedor sugerido
**ScrollPane** con un **VBox** interno.

## Bloques internos del panel derecho
1. Identidad del producto
2. Contexto de stock
3. Contexto comercial
4. Contexto de abastecimiento
5. Acciones rápidas

## 10.1. Bloque: Identidad del producto

### Campos visibles
- Referencia
- Nombre
- Categoría
- Marca

### Seeds
- **Referencia: MZ-201**
- **Nombre: Armazón clásico**
- **Categoría: Monturas**
- **Marca: VisionLine**

## 10.2. Bloque: Contexto de stock

### Campos visibles
- Estado de stock
- Stock actual
- Sucursal
- Ubicación

### Seeds
- **Estado de stock: Bajo stock**
- **Stock actual: 2 unidades**
- **Sucursal: Matriz Centro**
- **Ubicación: Vitrina A - Nivel 2**

## 10.3. Bloque: Contexto comercial

### Campos visibles
- Precio de venta
- Última salida
- Rotación estimada

### Seeds
- **Precio de venta: $ 68.00**
- **Última salida: 12/04/2026**
- **Rotación estimada: Media**

## 10.4. Bloque: Contexto de abastecimiento

### Campos visibles
- Proveedor principal
- Última recepción
- Estado de reposición

### Seeds
- **Proveedor principal: VisionLine Distribución**
- **Última recepción: 08/04/2026**
- **Estado de reposición: Pendiente**

## 10.5. Bloque: Acciones rápidas

### Botones oficiales
- **Ver detalle**
- **Editar producto**
- **Ajustar stock**
- **Registrar recepción**
- **Ver movimientos**

### Tooltips exactos
- Ver detalle: **Abrir el detalle completo del producto seleccionado**
- Editar producto: **Modificar la información base del producto seleccionado**
- Ajustar stock: **Registrar un ajuste manual de existencias**
- Registrar recepción: **Registrar una entrada de mercadería para este producto**
- Ver movimientos: **Consultar los movimientos asociados al producto**

---

# 11. Sub-vista 1: Catálogo general

## Función
Servir como bandeja general de productos activos del inventario.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: pequeño resumen del catálogo visible
- **center**: **TableView** principal
- **bottom**: resumen de resultados visibles

## 11.1. Franja superior del catálogo

### Contenido
- Label: **Catálogo general**
- Label secundario: **186 productos visibles**

### Tooltips
- Catálogo general: **Productos visibles según los filtros aplicados**
- 186 productos visibles: **Cantidad total de productos mostrados en el catálogo**

## 11.2. TableView principal

### Columnas oficiales
- Referencia
- Nombre
- Categoría
- Marca
- Sucursal
- Stock
- Precio
- Estado

### Seeds oficiales
1. MZ-201 | Armazón clásico | Monturas | VisionLine | Matriz Centro | 2 | $ 68.00 | Bajo stock
2. NV-118 | Montura ligera | Monturas | NovaFrame | Norte Mall | 1 | $ 74.00 | Bajo stock
3. LN-156-AR | Monofocal 1.56 | Lentes | LabVision | Matriz Centro | 14 | $ 42.00 | Disponible
4. LN-160-BC | Monofocal 1.60 Blue cut | Lentes | LabVision | Norte Mall | 3 | $ 58.00 | Bajo stock
5. ACC-021 | Plaquetas de silicona estándar | Accesorios | Genérico | Matriz Centro | 0 | $ 3.50 | Agotado
6. ACC-045 | Estuche rígido negro | Accesorios | Genérico | Norte Mall | 12 | $ 7.00 | Disponible

### Tooltip de la tabla
**Seleccione un producto para ver su resumen, movimientos o estado de reposición**

### Estado vacío
**No hay productos visibles con los filtros actuales**

## 11.3. Pie del listado

### Textos exactos
- **Mostrando 6 de 186 productos**
- **Ordenado por estado y referencia**

### Tooltips
- Mostrando 6 de 186 productos: **Resumen de productos visibles en el catálogo actual**
- Ordenado por estado y referencia: **Criterio actual de ordenamiento del catálogo**

---

# 12. Sub-vista 2: Monturas

## Función
Mostrar el inventario específico de monturas con criterios propios del rubro óptico.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: filtros específicos de monturas
- **center**: **TableView** de monturas
- **bottom**: resumen visual simple

## 12.1. Filtros específicos de monturas

### Controles sugeridos
- TextField: **Código o nombre de montura**
- ComboBox: **Material**
- ComboBox: **Color**
- ComboBox: **Forma**
- ComboBox: **Marca**
- ComboBox: **Stock**

### Seeds de filtros
- Material: Todos, Metal, Acetato, Mixto
- Color: Todos, Negro mate, Carey, Azul, Plateado
- Forma: Todas, Rectangular, Redonda, Ovalada, Aviador

### Tooltips
- Código o nombre de montura: **Buscar monturas por referencia o nombre**
- Material: **Filtrar monturas por material**
- Color: **Filtrar monturas por color principal**
- Forma: **Filtrar monturas por forma**
- Marca: **Filtrar monturas por marca**
- Stock: **Filtrar por disponibilidad de monturas**

## 12.2. TableView de monturas

### Columnas oficiales
- Referencia
- Nombre
- Marca
- Material
- Color
- Precio
- Stock
- Sucursal

### Seeds oficiales
1. MZ-201 | Armazón clásico | VisionLine | Acetato | Negro mate | $ 68.00 | 2 | Matriz Centro
2. NV-118 | Montura ligera | NovaFrame | Metal | Carey | $ 74.00 | 1 | Norte Mall
3. UE-332 | Montura metálica | UrbanEye | Metal | Plateado | $ 61.00 | 0 | Matriz Centro

### Tooltip de la tabla
**Seleccione una montura para ver detalle, stock y abastecimiento**

### Estado vacío
**No hay monturas visibles con los filtros actuales**

### Botones oficiales del submódulo
- **Ver montura**
- **Ajustar stock**
- **Ver sucursales**

### Tooltips
- Ver montura: **Consultar el detalle de la montura seleccionada**
- Ajustar stock: **Registrar un ajuste de existencias para la montura**
- Ver sucursales: **Consultar disponibilidad de la montura por sede**

---

# 13. Sub-vista 3: Lentes y variantes

## Función
Mostrar lentes, índices, tratamientos y variantes con una lógica más técnica que la de monturas.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: filtros específicos de lente
- **center**: **TableView** técnico
- **right** o panel integrado: detalle de variante seleccionada

## 13.1. Filtros específicos de lentes

### Controles sugeridos
- TextField: **Código o nombre de lente**
- ComboBox: **Tipo de lente**
- ComboBox: **Índice**
- ComboBox: **Tratamiento**
- ComboBox: **Estado**

### Seeds de filtros
- Tipo de lente: Todos, Monofocal, Progresivo, Ocupacional, Bifocal
- Índice: Todos, 1.50, 1.56, 1.60, 1.67
- Tratamiento: Todos, Antirreflejo, Blue cut, Fotocromático, Hidrofóbico

### Tooltips
- Código o nombre de lente: **Buscar lentes por referencia o nombre técnico**
- Tipo de lente: **Filtrar por tipo general de lente**
- Índice: **Filtrar por índice del lente**
- Tratamiento: **Filtrar por tratamiento principal**
- Estado: **Filtrar por disponibilidad del lente**

## 13.2. TableView de lentes y variantes

### Columnas oficiales
- Referencia
- Nombre
- Tipo
- Índice
- Tratamiento
- Stock
- Precio base
- Estado

### Seeds oficiales
1. LN-156-AR | Monofocal 1.56 | Monofocal | 1.56 | Antirreflejo | 14 | $ 42.00 | Disponible
2. LN-160-BC | Monofocal 1.60 Blue cut | Monofocal | 1.60 | Blue cut | 3 | $ 58.00 | Bajo stock
3. LN-167-PG | Progresivo 1.67 | Progresivo | 1.67 | Antirreflejo | 1 | $ 98.00 | Bajo stock

### Tooltip de la tabla
**Seleccione un lente o variante para ver disponibilidad y abastecimiento**

### Estado vacío
**No hay lentes visibles con los filtros actuales**

### Botones oficiales del submódulo
- **Ver lente**
- **Ajustar stock**
- **Ver compatibilidad**

### Tooltips
- Ver lente: **Consultar el detalle técnico y comercial del lente seleccionado**
- Ajustar stock: **Registrar un ajuste de existencias del lente seleccionado**
- Ver compatibilidad: **Consultar su uso dentro del flujo óptico**

---

# 14. Sub-vista 4: Movimientos de inventario

## Función
Registrar y consultar entradas, salidas y ajustes de stock.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: filtros de movimientos
- **center**: **TableView** principal
- **bottom**: resumen del período visible

## 14.1. Filtros de movimientos

### Controles sugeridos
- ComboBox: **Tipo de movimiento**
- DatePicker: **Desde**
- DatePicker: **Hasta**
- ComboBox: **Sucursal**
- TextField: **Referencia relacionada**

### Seeds de filtros
- Tipo de movimiento: Todos, Entrada, Salida por venta, Ajuste, Recepción, Transferencia

### Tooltips
- Tipo de movimiento: **Filtrar por tipo de movimiento de inventario**
- Desde: **Fecha inicial para consultar movimientos**
- Hasta: **Fecha final para consultar movimientos**
- Sucursal: **Filtrar movimientos por sede**
- Referencia relacionada: **Buscar por orden, recepción o referencia relacionada**

## 14.2. TableView principal

### Columnas oficiales
- Fecha
- Tipo
- Referencia
- Producto
- Cantidad
- Sucursal
- Responsable

### Seeds oficiales
1. 12/04/2026 | Salida por venta | OV-124 | MZ-201 | -1 | Matriz Centro | Asesor Molina
2. 13/04/2026 | Entrada por recepción | RC-038 | LN-156-AR | +4 | Norte Mall | Laura Gómez
3. 13/04/2026 | Ajuste | AJ-011 | ACC-021 | -2 | Matriz Centro | Técnico Rivera

### Tooltip de la tabla
**Consulte movimientos de inventario por fecha, tipo y sucursal**

### Botones oficiales del submódulo
- **Registrar ajuste**
- **Registrar transferencia**
- **Exportar movimientos**

### Tooltips
- Registrar ajuste: **Registrar un ajuste manual de existencias**
- Registrar transferencia: **Registrar un traslado de productos entre sucursales**
- Exportar movimientos: **Exportar los movimientos visibles del inventario**

### Estado vacío
**No hay movimientos visibles con los filtros actuales**

---

# 15. Sub-vista 5: Reposición y stock crítico

## Función
Concentrar productos agotados, productos con bajo stock y necesidades de reabastecimiento.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: resumen de criticidad
- **center**: **TableView** de productos críticos
- **bottom**: acciones de reposición

## 15.1. Resumen de criticidad

### Campos visibles
- Productos agotados
- Bajo stock
- En reposición

### Seeds
- **Productos agotados: 4**
- **Bajo stock: 11**
- **En reposición: 6**

## 15.2. TableView principal

### Columnas oficiales
- Referencia
- Nombre
- Categoría
- Stock actual
- Stock mínimo
- Estado
- Proveedor
- Sucursal

### Seeds oficiales
1. ACC-021 | Plaquetas de silicona estándar | Accesorios | 0 | 6 | Agotado | Genérico | Matriz Centro
2. LN-160-BC | Monofocal 1.60 Blue cut | Lentes | 3 | 8 | Bajo stock | LabVision Externo | Norte Mall
3. MZ-201 | Armazón clásico | Monturas | 2 | 5 | Bajo stock | VisionLine Distribución | Matriz Centro

### Tooltip de la tabla
**Revise productos que requieren atención de reposición inmediata**

### Botones oficiales del submódulo
- **Generar reposición**
- **Ver proveedor**
- **Marcar pedido**
- **Ajustar stock**

### Tooltips
- Generar reposición: **Preparar una acción de reabastecimiento para el producto seleccionado**
- Ver proveedor: **Consultar el proveedor asociado al producto**
- Marcar pedido: **Registrar que el producto ya fue solicitado**
- Ajustar stock: **Corregir manualmente la cantidad visible del producto**

### Estado vacío
**No hay productos en estado crítico en este momento**

---

# 16. Sub-vista 6: Recepción y abastecimiento

## Función
Registrar y consultar entradas de mercadería o abastecimiento recibido.

## Contenedor principal sugerido
**SplitPane** horizontal

### Panel izquierdo
**TableView** de recepciones

### Panel derecho
Detalle de recepción seleccionada

## 16.1. TableView de recepciones

### Columnas oficiales
- Referencia de recepción
- Fecha
- Proveedor
- Sucursal
- Estado
- Responsable

### Seeds oficiales
1. RC-038 | 13/04/2026 | LabVision Externo | Norte Mall | Recepción completa | Laura Gómez
2. RC-039 | 14/04/2026 | VisionLine Distribución | Matriz Centro | Recepción parcial | Carlos Mendoza

### Tooltip de la tabla
**Seleccione una recepción para ver detalle de mercadería y diferencias**

## 16.2. Detalle de recepción

### Campos visibles
- Referencia
- Proveedor
- Fecha
- Sucursal
- Estado
- Responsable
- Productos recibidos
- Diferencias

### Seeds
- Referencia: **RC-038**
- Proveedor: **LabVision Externo**
- Fecha: **13/04/2026**
- Sucursal: **Norte Mall**
- Estado: **Recepción completa**
- Responsable: **Laura Gómez**
- Productos recibidos: **4 ítems**
- Diferencias: **Sin diferencias**

### Botones oficiales del submódulo
- **Registrar recepción**
- **Marcar completa**
- **Registrar diferencia**
- **Abrir proveedor**

### Tooltips
- Registrar recepción: **Registrar una nueva recepción de mercadería**
- Marcar completa: **Indicar que la recepción ya fue completada**
- Registrar diferencia: **Registrar faltantes, sobrantes o inconsistencias**
- Abrir proveedor: **Consultar la información del proveedor relacionado**

### Estado vacío
**No hay recepciones registradas con los criterios actuales**

---

# 17. Sub-vista 7: Histórico y análisis de stock

## Función
Consultar comportamiento histórico, productos lentos, rotación y productos más movidos.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: pequeños indicadores analíticos
- **center**: **TableView** principal

## 17.1. Indicadores superiores

### Campos visibles
- Más vendidos
- Lentos
- Stock envejecido

### Seeds
- **Más vendidos: 12 referencias**
- **Lentos: 9 referencias**
- **Stock envejecido: 6 referencias**

## 17.2. TableView principal

### Columnas oficiales
- Referencia
- Nombre
- Categoría
- Rotación
- Última salida
- Estado actual
- Observación analítica

### Seeds oficiales
1. MZ-201 | Armazón clásico | Monturas | Media | 12/04/2026 | Bajo stock | Alta salida en Matriz Centro
2. UE-332 | Montura metálica | Monturas | Baja | 02/01/2026 | Agotado | Reposición no prioritaria
3. LN-156-AR | Monofocal 1.56 | Lentes | Alta | 13/04/2026 | Disponible | Producto de rotación constante
4. ACC-045 | Estuche rígido negro | Accesorios | Baja | 20/02/2026 | Disponible | Producto lento

### Tooltip de la tabla
**Consulte rotación, envejecimiento y comportamiento histórico del stock**

### Botones oficiales del submódulo
- **Exportar análisis**
- **Ver producto**
- **Marcar revisión**

### Tooltips
- Exportar análisis: **Exportar el análisis visible del inventario**
- Ver producto: **Consultar el producto seleccionado dentro del inventario**
- Marcar revisión: **Señalar un producto para revisión comercial o de stock**

### Estado vacío
**No hay datos históricos suficientes para el análisis actual**

---

# 18. Formulario conceptual: Nuevo producto

## Función
Registrar un nuevo producto del inventario sin salir de la arquitectura del módulo.

## Contenedor sugerido
Vista interna o diálogo amplio controlado. Para esta maqueta conviene resolverlo dentro del mismo módulo.

## Estructura sugerida
**ScrollPane** con **VBox** y bloques en **GridPane**.

### Grupos de campos
1. Identidad del producto
2. Clasificación
3. Información comercial
4. Abastecimiento inicial

### Campos mínimos
- Referencia
- Nombre
- Categoría
- Marca
- Sucursal
- Precio de venta
- Stock inicial
- Proveedor principal

### Botones finales
- **Guardar producto**
- **Cancelar**

### Tooltips
- Guardar producto: **Registrar el nuevo producto en el inventario**
- Cancelar: **Cerrar el registro del producto sin guardar**

---

# 19. Seed data oficial del módulo Inventario

## Sucursales
- Matriz Centro
- Norte Mall

## Marcas
- VisionLine
- NovaFrame
- UrbanEye
- LabVision

## Proveedores
- VisionLine Distribución
- LabVision Externo
- NovaFrame Import
- Genérico

## Categorías
- Monturas
- Lentes
- Lentes de contacto
- Accesorios
- Insumos

## Referencias de semilla
- MZ-201
- NV-118
- UE-332
- LN-156-AR
- LN-160-BC
- LN-167-PG
- ACC-021
- ACC-045
- RC-038
- RC-039
- AJ-011
- OV-124

## Estados usados en el módulo
- Disponible
- Bajo stock
- Agotado
- En reposición
- Descontinuado
- Activo
- Inactivo
- Recepción parcial
- Recepción completa
- Ajustado

---

# 20. Textos oficiales del módulo Inventario

## Títulos y labels principales
- Inventario
- Control de monturas, lentes, existencias, movimientos, reposición y abastecimiento
- Buscar
- Categoría
- Stock
- Marca
- Sucursal
- Proveedor
- Solo stock crítico
- Catálogo general
- Monturas
- Lentes y variantes
- Movimientos de inventario
- Reposición y stock crítico
- Recepción y abastecimiento
- Histórico y análisis de stock
- Referencia
- Nombre
- Estado de stock
- Stock actual
- Precio de venta
- Proveedor principal
- Última recepción
- Rotación estimada
- Productos agotados
- Bajo stock
- En reposición
- Recepción completa
- Recepción parcial

## Botones oficiales
- Actualizar inventario
- Exportar inventario
- Nuevo producto
- Limpiar filtros
- Ver detalle
- Editar producto
- Ajustar stock
- Registrar recepción
- Ver movimientos
- Ver montura
- Ver sucursales
- Ver lente
- Ver compatibilidad
- Registrar ajuste
- Registrar transferencia
- Exportar movimientos
- Generar reposición
- Ver proveedor
- Marcar pedido
- Marcar completa
- Registrar diferencia
- Abrir proveedor
- Exportar análisis
- Ver producto
- Marcar revisión
- Guardar producto
- Cancelar

## Placeholders
- Código, nombre, marca o referencia
- Código o nombre de montura
- Código o nombre de lente
- Referencia relacionada

## Empty states
- No hay productos visibles con los filtros actuales
- No hay monturas visibles con los filtros actuales
- No hay lentes visibles con los filtros actuales
- No hay movimientos visibles con los filtros actuales
- No hay productos en estado crítico en este momento
- No hay recepciones registradas con los criterios actuales
- No hay datos históricos suficientes para el análisis actual

---

# 21. Reglas visuales específicas del módulo Inventario

- el catálogo general debe sentirse como la bandeja principal del módulo
- Monturas debe verse más comercial que Lentes, pero igual administrativa
- Lentes y variantes debe sentirse más técnico y controlado
- Movimientos debe verse como trazabilidad operativa
- Reposición y stock crítico debe comunicar urgencia sin volverse caótica
- Recepción y abastecimiento debe verse logística y ordenada
- Histórico y análisis de stock debe sentirse más gerencial y analítico
- no abusar de formas redondeadas ni de tarjetas infladas
- la prioridad debe ser claridad, estado de stock y continuidad operativa

---

# 22. Relación del módulo Inventario con otros módulos

Inventario debe conectarse con:
- Venta óptica, porque las órdenes consumen monturas, lentes y accesorios
- Órdenes de laboratorio, porque algunos trabajos dependen de disponibilidad técnica y recepción
- Compras, porque la reposición puede derivar en abastecimiento formal
- Proveedores, porque cada producto puede depender de uno o varios proveedores
- Entregas, porque ciertos movimientos se reflejan al cierre del trabajo
- Inicio, porque el panel principal puede resumir stock crítico o agotados
- Sucursales, porque el inventario debe distinguir existencias por sede

---

# 23. Cierre del módulo Inventario

Este módulo debe transmitir que la óptica no trabaja a ciegas con sus existencias. Debe verse como una herramienta que permite saber qué hay, qué falta, qué se mueve, qué está detenido, qué llegó, qué salió y qué necesita reposición. No se trata solo de contar productos, sino de controlar el flujo real del inventario óptico.

La complejidad correcta del módulo está en que cada sub-vista resuelva una parte concreta del control de existencias sin que el sistema se vuelva pesado o innecesariamente industrial.