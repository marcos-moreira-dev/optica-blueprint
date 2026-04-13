# Lienzo del módulo Venta óptica para sistema de óptica en JavaFX

## 1. Identidad del módulo

### Nombre oficial del módulo en el menú
**Venta óptica**

### Texto visible del botón del sidebar
**Venta óptica**

### Tooltip del botón del sidebar
**Armar, cotizar, cobrar y confirmar una orden óptica completa**

### Ícono conceptual
Gafas, orden comercial o trabajo óptico.

### Título visible en pantalla
**Venta óptica**

### Subtítulo visible en pantalla
**Armado integral de orden: cliente, receta, montura, lentes, medidas y cobro**

### Tipo de módulo
Módulo transaccional guiado por etapas, con panel persistente de resumen.

### Objetivo del módulo
Permitir que la óptica construya una orden óptica completa desde el contexto del cliente y la receta hasta la selección de montura, lente, medidas, cobro y confirmación final del trabajo.

---

## 2. Rol del módulo dentro de la arquitectura GUI

Venta óptica debe ser uno de los módulos más importantes del sistema. No debe sentirse como un POS genérico ni como un formulario comercial simple. Debe verse como una estación de armado de trabajo óptico.

### Regla arquitectónica principal
Cuando el usuario entre al módulo Venta óptica, la región central principal del sistema debe reemplazarse por una vista completa llamada conceptualmente:
**VentaOpticaModuleView**

### Estructura interna limpia del módulo
La vista Venta óptica debe dividirse en:
- encabezado del módulo
- barra de contexto general de la orden
- subnavegación por etapas
- región central intercambiable para sub-vistas
- panel derecho persistente con resumen de la orden

### Filosofía de implementación
El usuario no debe perder el hilo de la orden. Toda la experiencia debe desarrollarse dentro de una sola vista principal, con etapas internas bien delimitadas.

### Regla de arquitectura limpia
Debe existir una región interna estable que se reescriba solo con la etapa activa del flujo. Esa región se denomina conceptualmente:
**ventaContentHostPane**

Ese contenedor puede ser un **StackPane** o el centro de un **BorderPane** secundario.

### Sub-vistas que viven dentro de ventaContentHostPane
- Cliente y contexto
- Receta aplicada
- Montura
- Lentes
- Medidas y personalización
- Precio y cobro
- Resumen y confirmación de orden

---

## 3. Sub-vistas oficiales del módulo Venta óptica

## Sub-vistas definidas
1. **Cliente y contexto**
2. **Receta aplicada**
3. **Montura**
4. **Lentes**
5. **Medidas y personalización**
6. **Precio y cobro**
7. **Resumen y confirmación de orden**

## Orden de prioridad
1. Cliente y contexto
2. Receta aplicada
3. Montura
4. Lentes
5. Medidas y personalización
6. Precio y cobro
7. Resumen y confirmación de orden

## Vista por defecto al abrir Venta óptica
**Cliente y contexto**

Motivo: la orden debe nacer desde una persona real y una ficha coherente, no desde un producto suelto.

---

## 4. Estructura visual general del módulo Venta óptica

## Contenedor raíz del módulo
**BorderPane**

### Distribución general del BorderPane principal
- **top**: encabezado del módulo + contexto rápido + barra de etapas
- **center**: cuerpo principal del módulo
- **right**: no aplica como región adicional, porque el panel persistente vive dentro del cuerpo
- **left**: no aplica, porque el sidebar global ya existe fuera del módulo
- **bottom**: no aplica

### Decisión espacial clave
El cuerpo principal debe organizarse con un **SplitPane** horizontal.

### Distribución del SplitPane
- panel izquierdo o central amplio: etapa activa del flujo
- panel derecho: resumen persistente de la orden

### Proporción recomendada
- región de trabajo: 68% a 72%
- panel derecho: 28% a 32%

### Regla de estabilidad visual
El panel derecho debe permanecer visible durante casi toda la navegación. Su función es que el usuario nunca pierda el contexto de la orden.

---

## 5. Encabezado del módulo Venta óptica

## Contenedor sugerido
**VBox** en la región top del BorderPane principal.

### Subestructura del encabezado
1. franja superior con título y acciones globales
2. franja media con contexto rápido de orden
3. franja inferior con etapas del flujo

---

## 6. Franja superior del encabezado

## Contenedor sugerido
**HBox**

### Zona izquierda
Un **VBox** con:
- Label principal: **Venta óptica**
- Label secundario: **Armado integral de orden: cliente, receta, montura, lentes, medidas y cobro**

### Zona derecha
Un **HBox** con:
- Button secundario: **Guardar borrador**
- Button secundario: **Cancelar orden**
- Button principal: **Nueva orden**

## Controles exactos

### Label principal
- Control: **Label**
- Texto exacto: **Venta óptica**
- Tooltip: no necesita

### Label secundario
- Control: **Label**
- Texto exacto: **Armado integral de orden: cliente, receta, montura, lentes, medidas y cobro**
- Tooltip: no necesita

### Botón secundario 1
- Control: **Button**
- Texto exacto: **Guardar borrador**
- Tooltip: **Guardar la orden actual como borrador sin cerrarla**
- Estilo: secundario

### Botón secundario 2
- Control: **Button**
- Texto exacto: **Cancelar orden**
- Tooltip: **Cancelar el flujo actual de la orden óptica**
- Estilo: peligroso

### Botón principal
- Control: **Button**
- Texto exacto: **Nueva orden**
- Tooltip: **Iniciar una nueva orden óptica desde cero**
- Estilo: acción principal

---

## 7. Franja media: contexto rápido de la orden

## Contenedor sugerido
**FlowPane**

## Función
Mostrar en todo momento qué orden se está armando y bajo qué contexto general.

## Campos visibles
- Cliente
- Código de orden
- Sucursal
- Estado de orden
- Receta aplicada
- Responsable

## Seeds
- Cliente: **Sofía Ramírez**
- Código de orden: **OV-124**
- Sucursal: **Matriz Centro**
- Estado de orden: **En armado**
- Receta aplicada: **12/04/2026**
- Responsable: **Asesor Molina**

## Tooltips
- Cliente: **Cliente asociado a la orden óptica actual**
- Código de orden: **Referencia interna de la orden en construcción**
- Sucursal: **Sede donde se registra la orden**
- Estado de orden: **Situación actual del flujo de armado**
- Receta aplicada: **Fecha de la receta usada en la orden**
- Responsable: **Usuario que está construyendo la orden**

---

## 8. Franja inferior: etapas del flujo

## Contenedor sugerido
**HBox** con **ToggleButton** dentro de un **ToggleGroup**

## Etapas visibles en la barra
- Cliente
- Receta
- Montura
- Lentes
- Medidas
- Cobro
- Confirmación

## Tooltips exactos
- Cliente: **Seleccionar el cliente y revisar su contexto general**
- Receta: **Elegir la receta que se aplicará a la orden**
- Montura: **Seleccionar la montura para la orden óptica**
- Lentes: **Configurar tipo de lente, material y tratamientos**
- Medidas: **Registrar medidas y notas de personalización**
- Cobro: **Calcular precio, registrar abono o método de pago**
- Confirmación: **Revisar y cerrar la orden óptica**

## Regla de comportamiento
El avance debe poder bloquearse si faltan datos mínimos de una etapa crítica.

### Bloqueos lógicos recomendados
- no pasar de Cliente a Receta si no hay cliente seleccionado
- no pasar de Receta a Montura si no hay receta definida
- no pasar de Lentes a Medidas si no hay lente seleccionado
- no pasar de Cobro a Confirmación si no hay resumen económico coherente

## Estado visual de etapa activa
El ToggleButton activo debe verse claramente seleccionado, sin recargar la interfaz.

---

## 9. Cuerpo principal del módulo Venta óptica

## Contenedor sugerido
**SplitPane** horizontal

### Panel izquierdo o principal
Etapa activa del flujo.

### Panel derecho
Resumen persistente de la orden.

---

# 10. Panel derecho persistente: resumen de orden

## Función
Mantener visible el estado acumulado de la orden óptica mientras se avanza por etapas.

## Contenedor sugerido
**ScrollPane** con un **VBox** interno.

## Bloques internos del panel derecho
1. Resumen del cliente
2. Resumen técnico
3. Resumen comercial
4. Estado de orden
5. Acciones rápidas

## 10.1. Bloque: Resumen del cliente

### Campos visibles
- Nombre completo
- Código del cliente
- Última visita
- Sucursal habitual

### Seeds
- **Sofía Ramírez**
- **CL-00124**
- **Última visita: 12/04/2026**
- **Sucursal habitual: Matriz Centro**

## 10.2. Bloque: Resumen técnico

### Campos visibles
- Receta aplicada
- Montura elegida
- Lente elegido
- PD

### Seeds
- **Receta aplicada: 12/04/2026**
- **Montura elegida: MZ-201**
- **Lente elegido: Monofocal 1.56**
- **PD: 62 mm**

## 10.3. Bloque: Resumen comercial

### Campos visibles
- Subtotal
- Descuento
- Abono
- Saldo

### Seeds
- **Subtotal: $ 110.00**
- **Descuento: $ 5.00**
- **Abono: $ 50.00**
- **Saldo: $ 55.00**

## 10.4. Bloque: Estado de orden

### Campos visibles
- Estado general
- Entrega estimada
- Laboratorio

### Seeds
- **Estado general: En proceso**
- **Entrega estimada: 16/04/2026**
- **Laboratorio: Óptica Interna**

## 10.5. Bloque: Acciones rápidas

### Botones oficiales
- **Abrir cliente**
- **Ver receta**
- **Registrar abono**
- **Abrir inventario**

### Tooltips exactos
- Abrir cliente: **Abrir la ficha del cliente asociado a esta orden**
- Ver receta: **Consultar la receta aplicada a esta orden**
- Registrar abono: **Registrar un abono adicional para esta orden**
- Abrir inventario: **Consultar el inventario relacionado con montura o lente**

---

# 11. Sub-vista 1: Cliente y contexto

## Función
Seleccionar el cliente para la orden y mostrar su contexto básico antes de continuar.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: búsqueda y acciones
- **center**: **SplitPane** con resultados y resumen

## 11.1. Barra superior

### Controles oficiales
- Label: **Buscar cliente**
- TextField con placeholder: **Nombre, cédula, teléfono o código**
- Button: **Buscar**
- Button: **Nuevo cliente**

### Tooltips
- Buscar cliente: **Buscar una ficha de cliente para asociarla a la orden**
- Buscar: **Ejecutar la búsqueda del cliente ingresado**
- Nuevo cliente: **Registrar una nueva ficha de cliente sin salir del flujo de venta**

## 11.2. Resultados de búsqueda

### Contenedor sugerido
**TableView**

### Columnas oficiales
- Cliente
- Código
- Teléfono
- Última visita
- Estado
- Sucursal

### Seeds
1. Sofía Ramírez | CL-00124 | 099 123 4567 | 12/04/2026 | Activo | Matriz Centro
2. Sofía Ramírez López | CL-00458 | 098 333 1111 | 08/02/2026 | Requiere seguimiento | Norte Mall

### Tooltip de la tabla
**Seleccione el cliente que desea asociar a la orden óptica**

## 11.3. Panel de contexto del cliente seleccionado

### Contenedor sugerido
**VBox** dentro del mismo SplitPane

### Campos visibles
- Nombre completo
- Teléfono principal
- Última compra
- Última receta
- Saldo pendiente
- Observación rápida

### Seeds
- **Sofía Ramírez**
- **099 123 4567**
- **Última compra: 10/04/2026**
- **Última receta: 12/04/2026**
- **Saldo pendiente: $ 0.00**
- **Observación rápida: Prefiere retiro en Matriz Centro**

### Botones oficiales del submódulo
- **Seleccionar cliente**
- **Abrir ficha**
- **Agendar cita**

### Tooltips
- Seleccionar cliente: **Usar este cliente como base de la orden actual**
- Abrir ficha: **Consultar la ficha completa del cliente**
- Agendar cita: **Programar una nueva cita para este cliente**

### Estado vacío
**Busque y seleccione un cliente para continuar con la orden**

---

# 12. Sub-vista 2: Receta aplicada

## Función
Elegir la receta que se utilizará en la orden óptica.

## Contenedor principal sugerido
**SplitPane** horizontal

### Panel izquierdo
**TableView** de recetas disponibles

### Panel derecho
Detalle resumido de la receta seleccionada

## 12.1. Tabla de recetas disponibles

### Columnas oficiales
- Fecha
- Estado
- Profesional
- OD resumen
- OI resumen
- PD

### Seeds
1. 12/04/2026 | Vigente | Dra. Salazar | -1.25 / -0.50 x 180 | -1.00 / -0.25 x 170 | 62 mm
2. 15/11/2025 | Vencida | Dr. Paredes | -1.00 / -0.25 x 180 | -0.75 / -0.25 x 170 | 61 mm

### Tooltip de la tabla
**Seleccione la receta que se utilizará en esta orden**

## 12.2. Detalle de receta seleccionada

### Campos visibles
- Fecha
- Estado
- Profesional
- Resumen OD
- Resumen OI
- PD
- Observación rápida

### Seeds
- **Fecha: 12/04/2026**
- **Estado: Vigente**
- **Profesional: Dra. Salazar**
- **Resumen OD: -1.25 / -0.50 x 180**
- **Resumen OI: -1.00 / -0.25 x 170**
- **PD: 62 mm**
- **Observación rápida: Fatiga visual al final de la jornada**

### Botones oficiales del submódulo
- **Usar receta**
- **Ver receta completa**
- **Marcar revisión**
- **Duplicar receta**

### Tooltips
- Usar receta: **Aplicar esta receta a la orden actual**
- Ver receta completa: **Consultar la receta técnica completa**
- Marcar revisión: **Indicar que esta receta requiere revisión antes de usarla**
- Duplicar receta: **Crear una nueva receta basada en la seleccionada**

### Estado vacío
**Este cliente no tiene recetas disponibles para aplicar**

---

# 13. Sub-vista 3: Montura

## Función
Seleccionar la montura que se usará en la orden.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: filtros y búsqueda
- **center**: catálogo administrativo de monturas
- **right** o panel inferior contextual: detalle de montura seleccionada

## 13.1. Barra de filtros

### Controles oficiales
- TextField: búsqueda con placeholder **Código o nombre de montura**
- ComboBox: **Marca**
- ComboBox: **Material**
- ComboBox: **Color**
- ComboBox: **Stock**
- Button: **Limpiar filtros**

### Seeds de filtros
- Marca: Todas, VisionLine, NovaFrame, UrbanEye
- Material: Todos, Metal, Acetato, Mixto
- Color: Todos, Negro, Carey, Azul, Plateado
- Stock: Todos, Disponible, Bajo stock, Agotado

### Tooltips
- Código o nombre de montura: **Buscar monturas por referencia o nombre comercial**
- Marca: **Filtrar por marca de montura**
- Material: **Filtrar por material de la montura**
- Color: **Filtrar por color principal**
- Stock: **Filtrar por disponibilidad en inventario**
- Limpiar filtros: **Restablecer los filtros del catálogo de monturas**

## 13.2. Catálogo administrativo de monturas

### Contenedor sugerido
**TableView** o **TilePane** administrativo. Para mantener simplicidad y seriedad, se recomienda **TableView**.

### Columnas oficiales
- Referencia
- Nombre
- Marca
- Color
- Precio
- Stock
- Sucursal

### Seeds
1. MZ-201 | Armazón clásico | VisionLine | Negro mate | $ 68.00 | 2 | Matriz Centro
2. NV-118 | Montura ligera | NovaFrame | Carey | $ 74.00 | 1 | Norte Mall
3. UE-332 | Montura metálica | UrbanEye | Plateado | $ 61.00 | 0 | Matriz Centro

### Tooltip de la tabla
**Seleccione la montura que desea asociar a la orden**

## 13.3. Panel de detalle de montura

### Campos visibles
- Referencia
- Marca
- Material
- Color
- Precio
- Stock
- Sucursal

### Botones oficiales del submódulo
- **Seleccionar montura**
- **Ver inventario**
- **Cambiar sucursal**

### Tooltips
- Seleccionar montura: **Usar esta montura en la orden actual**
- Ver inventario: **Consultar existencias y movimientos de esta montura**
- Cambiar sucursal: **Consultar esta montura en otra sede**

### Estado vacío
**No hay monturas disponibles con los filtros actuales**

---

# 14. Sub-vista 4: Lentes

## Función
Definir el tipo de lente, material y tratamientos compatibles con la receta.

## Contenedor principal sugerido
**BorderPane** con tres bloques verticales dentro de un **VBox** o **ScrollPane**.

### Estructura interna
1. Selección base del lente
2. Tratamientos y extras
3. Compatibilidad y resumen técnico

## 14.1. Selección base del lente

### Controles oficiales
- ComboBox: **Tipo de lente**
- ComboBox: **Índice**
- ComboBox: **Uso principal**

### Seeds
- Tipo de lente: Monofocal, Bifocal, Progresivo, Ocupacional
- Índice: 1.50, 1.56, 1.60, 1.67
- Uso principal: Uso diario, Pantalla, Lectura, Conducción

### Tooltips
- Tipo de lente: **Seleccione el tipo de lente para esta orden**
- Índice: **Seleccione el índice del lente**
- Uso principal: **Seleccione el contexto principal de uso**

## 14.2. Tratamientos y extras

### Controles sugeridos
**CheckBox** para:
- Antirreflejo
- Blue cut
- Fotocromático
- Hidrofóbico

### Tooltip general
**Seleccione los tratamientos adicionales que se aplicarán al lente**

## 14.3. Compatibilidad y resumen técnico

### Campos visibles
- Compatibilidad con receta
- Compatibilidad con montura
- Recomendación del sistema
- Precio del lente

### Seeds
- Compatibilidad con receta: **Compatible**
- Compatibilidad con montura: **Compatible**
- Recomendación del sistema: **Monofocal 1.56 antirreflejo + blue cut**
- Precio del lente: **$ 42.00**

### Botones oficiales del submódulo
- **Seleccionar lente**
- **Aplicar recomendación**
- **Limpiar selección**

### Tooltips
- Seleccionar lente: **Aplicar este lente y sus tratamientos a la orden**
- Aplicar recomendación: **Usar la combinación sugerida por el sistema**
- Limpiar selección: **Eliminar la selección actual de lente**

### Estado vacío
**Seleccione el tipo de lente para continuar**

---

# 15. Sub-vista 5: Medidas y personalización

## Función
Registrar medidas y notas de personalización necesarias para el trabajo final.

## Contenedor principal sugerido
**ScrollPane** con un **VBox** y bloques en **GridPane**.

### Estructura interna
1. Medidas principales
2. Personalización
3. Observaciones de armado

## 15.1. Bloque: Medidas principales

### Campos visibles
- PD
- Altura de montaje
- Centrado

### Seeds
- PD: **62 mm**
- Altura de montaje: **18 mm**
- Centrado: **Estándar**

### Tooltips
- PD: **Distancia pupilar aplicada a la orden**
- Altura de montaje: **Altura usada para el montaje del lente**
- Centrado: **Referencia de centrado del trabajo**

## 15.2. Bloque: Personalización

### Campos visibles
- Preferencia de peso
- Preferencia de acabado
- Prioridad estética

### Seeds
- Preferencia de peso: **Lente liviano**
- Preferencia de acabado: **Acabado estándar**
- Prioridad estética: **Discreción visual**

## 15.3. Bloque: Observaciones de armado

### Campo visible
- Observación de armado

### Seed
**Cliente solicita acabado discreto y retiro en Matriz Centro**

### Botones oficiales del submódulo
- **Guardar medidas**
- **Copiar desde receta**
- **Continuar a cobro**

### Tooltips
- Guardar medidas: **Guardar las medidas y personalización de la orden**
- Copiar desde receta: **Usar las medidas registradas en la receta activa**
- Continuar a cobro: **Pasar a la etapa de precio y cobro**

### Estado vacío
**Complete las medidas necesarias para continuar**

---

# 16. Sub-vista 6: Precio y cobro

## Función
Convertir la orden técnica en una venta económica coherente.

## Contenedor principal sugerido
**BorderPane** o **VBox** con bloques financieros.

### Estructura interna
1. Resumen económico
2. Descuento y abono
3. Método de pago

## 16.1. Bloque: Resumen económico

### Campos visibles
- Precio montura
- Precio lente
- Subtotal

### Seeds
- Precio montura: **$ 68.00**
- Precio lente: **$ 42.00**
- Subtotal: **$ 110.00**

## 16.2. Bloque: Descuento y abono

### Campos visibles
- Descuento
- Total final
- Abono
- Saldo

### Seeds
- Descuento: **$ 5.00**
- Total final: **$ 105.00**
- Abono: **$ 50.00**
- Saldo: **$ 55.00**

## 16.3. Bloque: Método de pago

### Controles sugeridos
- ComboBox: **Método de pago**
- CheckBox: **Dejar saldo pendiente**

### Seeds
- Método de pago: Efectivo, Tarjeta, Transferencia, Mixto
- Valor inicial: **Transferencia**

### Botones oficiales del submódulo
- **Registrar abono**
- **Recalcular total**
- **Continuar a confirmación**

### Tooltips
- Registrar abono: **Registrar el abono actual de la orden**
- Recalcular total: **Recalcular el total con descuento, abono y saldo**
- Continuar a confirmación: **Pasar a la revisión final de la orden**

### Estado vacío
**No hay información económica suficiente para calcular el cobro**

---

# 17. Sub-vista 7: Resumen y confirmación de orden

## Función
Revisar toda la orden antes de guardarla, enviarla o imprimirla.

## Contenedor principal sugerido
**ScrollPane** con un **VBox** interno.

### Estructura interna
1. Resumen del cliente y receta
2. Resumen de montura y lente
3. Resumen de medidas
4. Resumen económico
5. Acciones finales

## 17.1. Bloque: Resumen del cliente y receta

### Seeds
- Cliente: **Sofía Ramírez**
- Receta aplicada: **12/04/2026**
- Profesional: **Dra. Salazar**

## 17.2. Bloque: Resumen de montura y lente

### Seeds
- Montura: **MZ-201 | VisionLine | Negro mate**
- Lente: **Monofocal 1.56**
- Tratamientos: **Antirreflejo + Blue cut**

## 17.3. Bloque: Resumen de medidas

### Seeds
- PD: **62 mm**
- Altura de montaje: **18 mm**
- Observación: **Retiro en Matriz Centro**

## 17.4. Bloque: Resumen económico

### Seeds
- Total final: **$ 105.00**
- Abono: **$ 50.00**
- Saldo: **$ 55.00**

## 17.5. Acciones finales

### Botones oficiales
- **Guardar orden**
- **Enviar a laboratorio**
- **Imprimir comprobante**
- **Volver a cobro**

### Tooltips
- Guardar orden: **Guardar la orden óptica y dejarla registrada en el sistema**
- Enviar a laboratorio: **Enviar esta orden al flujo de laboratorio o fabricación**
- Imprimir comprobante: **Generar el comprobante de la orden actual**
- Volver a cobro: **Regresar a la etapa de cobro para realizar ajustes**

### Estado final sugerido
**Orden lista para procesamiento**

---

# 18. Formulario conceptual: Nueva orden óptica

## Función
Iniciar una nueva orden sin abandonar la arquitectura del módulo.

## Contenedor sugerido
No requiere una ventana separada. Debe resolverse iniciando el flujo y limpiando el panel derecho con una nueva referencia.

### Datos mínimos iniciales
- Cliente
- Sucursal
- Responsable
- Fecha de orden

### Texto inicial sugerido
**Comience seleccionando el cliente para la nueva orden óptica**

---

# 19. Seed data oficial del módulo Venta óptica

## Cliente principal
- Sofía Ramírez

## Profesionales y responsables
- Dra. Salazar
- Asesor Molina

## Sucursales
- Matriz Centro
- Norte Mall

## Monturas de semilla
- MZ-201 | VisionLine | Negro mate | $ 68.00 | 2
- NV-118 | NovaFrame | Carey | $ 74.00 | 1
- UE-332 | UrbanEye | Plateado | $ 61.00 | 0

## Lentes de semilla
- Monofocal 1.56 | $ 42.00
- Progresivo 1.60 | $ 98.00
- Ocupacional 1.56 | $ 56.00

## Datos técnicos de semilla
- Receta aplicada: 12/04/2026
- PD: 62 mm
- Altura de montaje: 18 mm
- Tratamientos: Antirreflejo + Blue cut

## Estados usados en el módulo
- En armado
- Compatible
- Incompatible
- En proceso
- Pendiente
- Lista para procesamiento

---

# 20. Textos oficiales del módulo Venta óptica

## Títulos y labels principales
- Venta óptica
- Armado integral de orden: cliente, receta, montura, lentes, medidas y cobro
- Cliente
- Código de orden
- Sucursal
- Estado de orden
- Receta aplicada
- Responsable
- Cliente
- Receta
- Montura
- Lentes
- Medidas
- Cobro
- Confirmación
- Buscar cliente
- Precio montura
- Precio lente
- Subtotal
- Descuento
- Total final
- Abono
- Saldo
- Entrega estimada
- Laboratorio

## Botones oficiales
- Guardar borrador
- Cancelar orden
- Nueva orden
- Buscar
- Nuevo cliente
- Seleccionar cliente
- Abrir ficha
- Agendar cita
- Usar receta
- Ver receta completa
- Marcar revisión
- Duplicar receta
- Seleccionar montura
- Ver inventario
- Cambiar sucursal
- Seleccionar lente
- Aplicar recomendación
- Limpiar selección
- Guardar medidas
- Copiar desde receta
- Continuar a cobro
- Registrar abono
- Recalcular total
- Continuar a confirmación
- Guardar orden
- Enviar a laboratorio
- Imprimir comprobante
- Volver a cobro
- Abrir cliente
- Ver receta
- Abrir inventario

## Placeholders
- Nombre, cédula, teléfono o código
- Código o nombre de montura

## Empty states
- Busque y seleccione un cliente para continuar con la orden
- Este cliente no tiene recetas disponibles para aplicar
- No hay monturas disponibles con los filtros actuales
- Seleccione el tipo de lente para continuar
- Complete las medidas necesarias para continuar
- No hay información económica suficiente para calcular el cobro

---

# 21. Reglas visuales específicas del módulo Venta óptica

- el flujo por etapas debe dominar la experiencia del módulo
- el panel derecho debe mantenerse estable y útil
- la selección de montura debe sentirse administrativa, no tipo tienda web
- la selección de lentes debe verse técnica y controlada
- el cobro debe ser claro y corto, no una caja genérica enorme
- la confirmación final debe parecer una hoja de validación seria
- no abusar de paneles redondeados ni de tarjetas infladas
- no sobrecargar con gráficos o adornos visuales
- la prioridad debe ser claridad, continuidad y control del trabajo óptico

---

# 22. Relación del módulo Venta óptica con otros módulos

Venta óptica debe conectarse con:
- Clientes, porque toda orden nace desde una ficha
- Recetas, porque la prescripción alimenta la venta
- Inventario, por monturas y disponibilidad
- Caja, por cobro y abonos
- Órdenes de laboratorio, porque la orden puede pasar a fabricación
- Entregas, porque el trabajo terminará en retiro o entrega
- Inicio, porque el panel principal puede resumir órdenes en proceso

---

# 23. Cierre del módulo Venta óptica

Este módulo debe transmitir que la óptica no solo vende un producto, sino que arma un trabajo completo con contexto técnico, selección de componentes, medidas, cobro y confirmación. Debe verse como el centro operativo y comercial del sistema.

La complejidad correcta del módulo está en que cada etapa resuelva una parte concreta del proceso, manteniendo siempre visible el resumen de la orden y evitando que el usuario pierda el contexto de lo que está armando.