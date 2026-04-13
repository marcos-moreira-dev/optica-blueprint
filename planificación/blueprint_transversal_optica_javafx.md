# Blueprint transversal de interfaz desktop para sistema de óptica en JavaFX

## Objetivo de este blueprint

Este documento define la base transversal del sistema desktop antes de entrar al diseño particular de cada módulo o submódulo. Su propósito es fijar criterios comunes de composición visual, navegación, jerarquía, identidad, controles JavaFX, estados, patrones de interacción y convenciones de layout para que todo el sistema se sienta coherente, profesional y escalable.

Este blueprint no describe todavía el contenido interno detallado de cada área de trabajo específica. En esta fase se diseña el esqueleto común: todo lo que atraviesa la aplicación completa.

---

## 1. Principio rector del sistema

La aplicación debe percibirse como un **sistema único e integrado**, aunque internamente esté compuesto por módulos funcionales. Desde el punto de vista del usuario, la experiencia debe sentirse consistente, continua y predecible.

La intención visual general no es la de una app genérica ni la de una web improvisada, sino la de una **aplicación administrativa desktop sobria, clara y modular**, orientada a un negocio híbrido entre salud, atención al cliente, retail y logística ligera.

### Atributos visuales y operativos deseados
- Limpia
- Profesional
- Clara
- Estable
- Moderadamente sobria
- Moderna sin exageración
- Ordenada
- Con densidad controlada
- Legible en jornadas largas
- Apta para trabajo administrativo intensivo

### Sensación que debe transmitir
- Confianza
- Control
- Formalidad
- Continuidad operativa
- Negocio serio y organizado

---

## 2. Arquitectura visual global de la ventana

## Contenedor raíz recomendado
La estructura principal debe pensarse como un **BorderPane** raíz.

### Regiones principales
- **left**: navegación principal del sistema
- **top**: barra superior transversal
- **center**: área de trabajo dinámica
- **bottom**: opcional, barra de estado discreta

Esta decisión da una jerarquía espacial muy clara y muy adecuada para sistemas desktop administrativos.

---

## 3. Sidebar principal

## Rol
El sidebar es la columna vertebral de navegación del sistema. Debe expresar la estructura macro del producto.

## Contenedor JavaFX recomendado
- **VBox** principal para apilar logo, separadores, grupos de navegación y acciones inferiores
- eventualmente un **ScrollPane** si el número de módulos crece demasiado

## Estructura del sidebar
### Parte superior
- Logo o isotipo
- Nombre del sistema o sucursal activa
- Separador visual

### Parte media
Botones o ítems de navegación principales, agrupados por bloques lógicos.

Ejemplo de agrupación:
- Operación diaria
- Atención / clínica / cliente
- Comercial / ventas
- Inventario / abastecimiento
- Administración / reportes / configuración

### Parte inferior
- Perfil del usuario
- Configuración rápida
- Cerrar sesión

## Controles JavaFX sugeridos
- **Button** estilizado para navegación principal
- eventualmente **ToggleButton** si se quiere representar selección exclusiva del módulo activo
- **Separator** para dividir secciones
- **Label** para títulos de grupo
- **VBox** anidados para grupos lógicos

## Reglas visuales
- Los botones deben tener alto suficiente para ser cómodos
- El botón activo debe diferenciarse de forma clara pero elegante
- Los íconos deben ser simples y uniformes
- Debe evitarse el exceso de ornamento
- La selección activa debe ser obvia incluso en pantallas largas

## Reglas de comportamiento
- Solo un módulo principal activo a la vez
- Debe ser posible colapsar visualmente el sidebar en una versión futura, pero no es prioridad inicial
- El cambio de módulo debe sentirse inmediato y estable

---

## 4. Barra superior transversal

## Rol
La barra superior complementa al sidebar. No debe competir con él. Su papel es contextual, no protagonista.

## Contenedor JavaFX recomendado
- **HBox** principal dentro de la región top del BorderPane

## Contenido recomendado
- Título del módulo activo
- Breadcrumb opcional si existe subnavegación interna profunda
- Selector de sucursal
- Indicador de usuario logueado
- Notificaciones rápidas
- Acciones globales discretas
- Campo de búsqueda global opcional, según complejidad futura

## Controles JavaFX sugeridos
- **Label** para título
- **ComboBox** para sucursal
- **MenuButton** para perfil del usuario
- **Button** para notificaciones
- **TextField** para búsqueda global, solo si realmente aporta
- **HBox** con espaciadores para separar bloques

## Reglas visuales
- Debe ser más baja que el sidebar es ancho
- Debe tener aire suficiente
- Debe evitar parecer una barra web cargada
- Título del módulo siempre claramente visible

---

## 5. Área de trabajo central

## Rol
Es la zona donde vive cada módulo. Debe ser flexible sin perder consistencia.

## Contenedor JavaFX recomendado
Dentro del **center** del BorderPane raíz, conviene montar un contenedor intermedio:
- **StackPane** si se quiere reemplazar vistas de forma limpia
- o **BorderPane** secundario para cada módulo

## Regla general
Cada módulo debe heredar una estructura transversal común aunque su contenido cambie. Esto permite que el usuario reconozca patrones repetidos.

## Estructura interna recomendada por módulo
- Encabezado del módulo
- Zona de filtros / búsqueda / acciones
- Contenido principal
- Panel de detalle contextual opcional
- Navegación secundaria opcional

---

## 6. Plantilla transversal de módulo

Cada módulo, salvo casos muy especiales, debería construirse con una plantilla visual común.

## 6.1 Encabezado del módulo
## Contenedor recomendado
- **VBox** o **HBox** según densidad

## Elementos
- Título del módulo
- Breve descripción operacional
- Acciones principales del módulo

## Controles sugeridos
- **Label** para título
- **Label** secundario para subtítulo
- **Button** para acciones primarias
- **HBox** para ubicar acciones a la derecha

## 6.2 Zona de filtros y búsqueda
## Contenedor recomendado
- **HBox** si hay pocos filtros
- **GridPane** si hay formularios de filtro más estructurados
- **FlowPane** si hay muchos filtros variables

## Controles sugeridos
- **TextField** para búsqueda libre
- **ComboBox** para filtros por estado, sucursal, categoría
- **DatePicker** para rangos de fecha
- **CheckBox** para filtros booleanos
- **Button** para limpiar filtros
- **Button** para ejecutar búsqueda avanzada

## 6.3 Cuerpo del módulo
## Contenedores posibles según necesidad
- **TableView** para gestión densa
- **SplitPane** para maestro-detalle
- **TabPane** para submódulos o vistas internas
- **GridPane** para formularios técnicos
- **TilePane** o **FlowPane** para catálogos
- **ScrollPane** para fichas largas
- **VBox** para bloques apilados

## 6.4 Panel de detalle contextual
## Uso recomendado
Muy útil en desktop para evitar abrir demasiadas pantallas.

## Contenedor recomendado
- **VBox** o **ScrollPane** dentro del lado derecho de un **BorderPane** interno o de un **SplitPane**

## Contenido típico
- Resumen del elemento seleccionado
- Acciones secundarias
- Historial corto
- Observaciones
- Estados

---

## 7. Jerarquía de acciones

El sistema debe distinguir claramente entre acciones primarias, secundarias y peligrosas.

## Acciones primarias
Son las más frecuentes o valiosas del módulo.

Ejemplos:
- Nuevo cliente
- Nueva cita
- Registrar venta
- Guardar cambios

## Controles sugeridos
- **Button** con estilo destacado

## Acciones secundarias
Son útiles pero no centrales.

Ejemplos:
- Exportar
- Imprimir
- Duplicar
- Ver historial

## Controles sugeridos
- **Button** de estilo neutro
- **MenuButton** si hay varias acciones relacionadas

## Acciones peligrosas
Tienen impacto sensible.

Ejemplos:
- Eliminar
- Anular
- Marcar como entregado sin validación
- Cerrar caja

## Controles sugeridos
- **Button** con estilo de advertencia
- confirmación obligatoria previa

---

## 8. Paleta de colores transversal

## Objetivo cromático
La interfaz debe sentirse limpia, tecnológica, confiable y empresarial. No infantil ni excesivamente médica.

## Línea general
- Base clara
- Grises suaves
- Azul suave / azul acero / azul petróleo claro como color estructural principal
- Verde, ámbar y rojo reservados para estados operativos

## Distribución sugerida
- Fondo general: gris muy claro o blanco roto
- Sidebar: azul más profundo o gris azulado
- Superficies internas: blanco
- Bordes: grises suaves
- Color de acento principal: azul sobrio
- Estados:
  - éxito / listo / confirmado: verde moderado
  - pendiente / atención: ámbar moderado
  - error / vencido / cancelado: rojo sobrio
  - informativo: azul secundario

## Regla importante
Los colores de estado no deben competir con el color de marca o navegación.

---

## 9. Tipografía y densidad

## Criterios
- Alta legibilidad
- Buena lectura en tablas y formularios
- Jerarquía clara entre títulos, subtítulos, etiquetas y datos

## Reglas generales
- Título de módulo claramente superior en peso y tamaño
- Subtítulos discretos
- Labels de formularios legibles, no demasiado pequeños
- Datos importantes destacados visualmente sin exageración
- Evitar interfaces excesivamente compactas en la primera versión

---

## 10. Espaciado y ritmo visual

## Principio
La interfaz debe respirar. El espacio es parte del diseño.

## Reglas transversales
- Separación consistente entre bloques
- Margen externo claro alrededor del área útil
- Padding interno homogéneo en cards, formularios y tablas
- Distancia suficiente entre filtros y acciones para no generar ruido
- No pegar tablas al borde
- Mantener un ritmo repetible entre módulo y módulo

---

## 11. Componentes base reutilizables

La coherencia del sistema se apoya en componentes recurrentes.

## Lista de componentes base sugeridos
- Card KPI
- Encabezado de módulo
- Barra de filtros
- Tabla administrativa estándar
- Panel de detalle lateral
- Formulario estándar
- Diálogo de confirmación
- Banner de estado / mensaje
- Badge de estado
- Empty state
- Toolbar secundaria
- Selector de sucursal
- Componente de búsqueda rápida

Estos deben definirse como patrón visual antes de entrar a cada módulo particular.

---

## 12. Tabla administrativa estándar

## Contenedor y control principal
- **TableView**

## Qué debe resolver transversalmente
- Encabezados claros
- Filtrado externo
- Selección de fila clara
- Acciones por fila cuando sea necesario
- Scroll cómodo
- Alineación correcta de columnas numéricas y de texto
- Estado vacío bien presentado

## Recomendaciones
- No saturar con demasiadas columnas visibles si no es necesario
- Priorización de columnas útiles
- Posibilidad futura de ocultar/mostrar columnas
- Filas con altura suficiente
- Columna de estado muy clara

---

## 13. Formularios estándar

## Contenedor recomendado
- **GridPane** para formularios estructurados
- **VBox** para formularios simples
- **ScrollPane** si el formulario es largo

## Reglas transversales
- Labels alineados consistentemente
- Campos agrupados por bloques semánticos
- No mezclar acciones arriba y abajo sin criterio
- Guardar / cancelar siempre ubicados de forma predecible
- Campos obligatorios claramente marcados

## Controles JavaFX frecuentes
- **TextField** para texto corto
- **TextArea** para observaciones
- **ComboBox** para selección cerrada
- **DatePicker** para fechas
- **CheckBox** para banderas booleanas
- **RadioButton** si hay pocas opciones excluyentes
- **Spinner** para cantidades o valores numéricos controlados
- **PasswordField** para credenciales

---

## 14. Navegación secundaria dentro de módulos

No todo debe depender del sidebar principal. Algunos módulos requerirán una subestructura.

## Controles sugeridos
- **TabPane** para submódulos hermanos
- **ToggleButton** agrupados para vistas alternas
- **Accordion** solo si realmente ayuda en paneles laterales o secciones colapsables
- **SplitPane** para maestro-detalle

## Regla
La navegación secundaria debe sentirse interna al módulo, no como otro sistema aparte.

---

## 15. Estados visuales transversales

El sistema debe definir una gramática visual de estados.

## Tipos de estado
- Activo
- Inactivo
- Pendiente
- Confirmado
- Cancelado
- En proceso
- Listo
- Entregado
- Error
- Sin stock
- Bajo stock

## Representación sugerida
- **Label** estilizado como badge
- color + borde + tipografía consistente
- evitar depender solo del color; añadir texto claro siempre

---

## 16. Mensajes, feedback y validación

## Qué debe existir transversalmente
- Mensajes de éxito
- Mensajes de error
- Confirmaciones
- Validaciones inline en formularios
- Estados vacíos
- Indicadores de carga

## Controles JavaFX y patrones sugeridos
- **Alert** para casos puntuales, pero no abusar
- banners internos o contenedores dedicados para feedback más elegante
- etiquetas de error cerca del campo
- overlay o indicador de carga ligero para procesos importantes
- botones deshabilitados cuando la acción no procede

---

## 17. Diálogos y ventanas emergentes

## Uso recomendado
Solo cuando aporten foco real.

## Controles JavaFX sugeridos
- **Dialog** o **Alert** para confirmaciones y mensajes
- ventanas secundarias solo para casos especiales

## Regla
No convertir la app en una cascada de popups. En desktop conviene aprovechar paneles laterales, áreas contextuales y vistas embebidas antes que abrir demasiadas ventanas.

---

## 18. Empty states y first-time experience

Cuando un módulo todavía no tenga datos, debe mostrar un estado vacío útil.

## Qué debe incluir
- mensaje claro
- breve explicación
- acción principal sugerida

## Contenedor sugerido
- **StackPane** o **VBox** centrado dentro del área principal

Esto mejora muchísimo la percepción de calidad.

---

## 19. Soporte para multi-sucursal

Dado que el negocio puede tener más de una sucursal, conviene dejar esta capacidad reflejada visualmente desde el diseño transversal.

## Elementos sugeridos
- selector de sucursal en topbar
- badges o etiquetas de sucursal en tablas y órdenes cuando aplique
- filtros por sede
- claridad visual sobre en qué sede se está operando

No es obligatorio que todo sea multi-sucursal en V1, pero la interfaz debe oler a que puede crecer hacia eso.

---

## 20. Soporte para roles y permisos

Aunque el blueprint es visual, los permisos impactan el front-end.

## Reglas visuales
- Ocultar acciones que el usuario no puede ejecutar cuando sea razonable
- O deshabilitarlas con señal clara si conviene mostrar límite funcional
- Mantener consistencia entre lo visible y lo permitido

Esto afecta botones, menús, acciones por fila y accesos del sidebar.

---

## 21. Módulos con flujo tipo wizard

Algunos procesos merecen una estructura guiada por etapas.

## Casos candidatos
- Venta óptica / creación de orden
- Proceso de reparación
- Alta compleja de cliente o convenio

## Contenedores y controles sugeridos
- barra de pasos superior hecha con **HBox** y nodos visuales
- **StackPane** o **BorderPane** central que cambia el contenido del paso activo
- resumen persistente lateral en **VBox**
- botones Anterior / Siguiente / Finalizar

## Regla de comportamiento
Puede bloquearse el avance o retroceso según validaciones, pero visualmente debe quedar claro qué falta completar.

---

## 22. Sistema de íconos

## Principio
Íconos discretos, coherentes y funcionales.

## Reglas
- mismo grosor visual
- mismo lenguaje gráfico
- no mezclar estilos incompatibles
- no depender del ícono como única fuente de significado

---

## 23. Responsividad dentro del desktop

Aunque no es una web responsive, sí debe tolerar distintos tamaños de ventana.

## Reglas
- evitar layouts rígidos en exceso
- permitir expansión razonable de tablas
- usar **Priority.ALWAYS** conceptualmente en zonas que deban crecer
- no romper formularios con redimensionamientos moderados
- fijar anchos mínimos a paneles sensibles cuando sea necesario

---

## 24. Qué dejar fijo y qué dejar variable

## Debe quedar fijo transversalmente
- estructura global de la ventana
- sidebar
- topbar
- jerarquía de acciones
- paleta base
- reglas de espaciado
- estilo de tablas
- estilo de formularios
- estilo de badges y mensajes
- comportamiento general de filtros

## Puede variar por módulo
- composición del cuerpo principal
- nivel de densidad
- presencia o no de panel lateral
- uso de catálogo, tabla o wizard
- complejidad de subnavegación interna

---

## 25. Entregable conceptual siguiente

Después de este blueprint transversal, lo correcto sería diseñar:
1. sistema de layout base
2. biblioteca mínima de componentes
3. patrones de interacción comunes
4. blueprint individual de cada módulo principal
5. luego recién el detalle de submódulos o vistas internas

---

## 26. Resumen ejecutivo

Este blueprint transversal define la columna vertebral del sistema desktop de óptica en JavaFX. La aplicación debe construirse como un sistema único, limpio y profesional, con una estructura visual estable basada en un BorderPane raíz, sidebar fijo, topbar contextual y área central flexible. La interfaz debe apoyarse en componentes reutilizables, reglas claras de jerarquía, formularios consistentes, tablas bien resueltas, estados visuales comprensibles y soporte conceptual para multi-sucursal, roles y crecimiento modular.

El objetivo no es todavía diseñar cada módulo al detalle, sino fijar todo aquello que debe ser transversal para que, cuando se diseñen los módulos particulares, el sistema completo se sienta como una sola familia visual y operativa.