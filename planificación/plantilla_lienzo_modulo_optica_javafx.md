# Plantilla de lienzo por módulo para sistema de óptica en JavaFX

## Propósito

Esta plantilla define cómo debe documentarse cada módulo del sistema de óptica de forma individual, manteniendo coherencia con el blueprint transversal ya definido. Su objetivo es garantizar consistencia entre módulos, evitar improvisación y permitir que cada vista se describa con suficiente detalle funcional, visual y espacial antes de programarla.

---

## 1. Identidad del módulo

### Nombre oficial del módulo
Debe indicarse el nombre tal como aparecerá en el sidebar.

### Nombre visible en pantalla
Debe indicarse el título principal tal como aparecerá en el encabezado del área de trabajo.

### Descripción corta del módulo
Breve frase que explique para qué sirve el módulo dentro del sistema.

### Tipo de módulo
Especificar si el módulo es principalmente:
- dashboard
- catálogo
- transaccional
- seguimiento
- inventario
- reportes
- configuración
- wizard
- mixto

---

## 2. Ubicación en la navegación principal

### Grupo del sidebar al que pertenece
Ejemplo:
- Operación diaria
- Atención y clientes
- Comercial
- Inventario y abastecimiento
- Administración

### Texto exacto del botón del sidebar
Debe escribirse el texto exacto.

### Tooltip del botón del sidebar
Debe escribirse el tooltip exacto.

### Ícono conceptual del botón del sidebar
No hace falta nombrar librería todavía, pero sí el concepto del ícono.

### Estado visual del botón activo
Definir cómo se verá cuando el módulo esté seleccionado.

---

## 3. Objetivo operativo del módulo

### Qué problema resuelve
Describir con claridad qué parte del negocio cubre.

### Usuario principal del módulo
Indicar qué rol o roles usarán más esta pantalla.

### Frecuencia esperada de uso
- alta
- media
- baja

### Riesgo operacional
Indicar qué tan delicado es equivocarse dentro del módulo.

---

## 4. Layout general del módulo

### Contenedor JavaFX principal sugerido
Debe indicarse el contenedor dominante del módulo, por ejemplo:
- BorderPane
- SplitPane
- VBox
- GridPane
- TabPane
- ScrollPane
- combinación de varios

### Estructura espacial general
Debe describirse la distribución de regiones del módulo, por ejemplo:
- encabezado arriba
- filtros debajo
- tabla al centro
- detalle a la derecha

### Nivel de densidad visual
- ligero
- medio
- denso

### Presencia de panel de detalle contextual
- sí
- no
- opcional

### Presencia de subnavegación interna
- sí
- no
- futura

---

## 5. Encabezado del módulo

Debe describirse todo lo que aparece en la franja superior del módulo.

### Contenedor JavaFX sugerido
Ejemplo: HBox, VBox

### Elementos obligatorios
- título del módulo
- subtítulo descriptivo
- acciones principales

### Por cada control del encabezado debe indicarse
- tipo de control JavaFX
- texto visible exacto
- tooltip exacto
- alineación aproximada
- prioridad visual

---

## 6. Barra de filtros y búsqueda

### Contenedor JavaFX sugerido
Ejemplo: HBox, GridPane, FlowPane

### Objetivo de la barra
Qué tipo de consulta o refinamiento permite.

### Por cada filtro o control debe especificarse
- tipo de control JavaFX
- texto visible exacto del label
- placeholder si aplica
- valor por defecto si aplica
- tooltip exacto
- comportamiento esperado

### Controles posibles
- Label
- TextField
- ComboBox
- DatePicker
- CheckBox
- Button
- MenuButton

---

## 7. Área principal de trabajo

### Contenedor JavaFX dominante
Ejemplo: TableView, GridPane, SplitPane, TilePane, ScrollPane

### Qué se ve primero al entrar al módulo
Describir la primera impresión operativa del usuario.

### Elemento principal del módulo
Definir el objeto visual dominante:
- tabla
- formulario
- tablero
- calendario
- catálogo
- wizard
- ficha

### Por cada bloque principal debe describirse
- nombre del bloque
- función
- tipo de contenedor JavaFX
- posición espacial
- densidad de información

---

## 8. Panel lateral o detalle contextual

Si existe panel lateral, debe documentarse con detalle.

### Contenedor JavaFX sugerido
Ejemplo: VBox dentro de ScrollPane

### Qué muestra
- resumen
- historial
- acciones rápidas
- observaciones
- estados

### Por cada control del panel debe indicarse
- tipo de control
- texto visible exacto
- tooltip exacto si corresponde
- estado habilitado / deshabilitado esperado

---

## 9. Submódulos o vistas internas

Si el módulo contiene pestañas, vistas alternas o pasos internos, deben enumerarse.

### Para cada subvista indicar
- nombre exacto
- tipo de navegación interna
- rol operativo
- cuándo aparece
- contenedor JavaFX sugerido

### Opciones de navegación interna
- TabPane
- ToggleButton group
- botones secundarios
- wizard por pasos
- paneles contextuales

---

## 10. Acciones del módulo

### Acciones primarias
Para cada una indicar:
- texto exacto del botón
- tooltip exacto
- ubicación espacial
- condición para estar habilitada

### Acciones secundarias
Para cada una indicar:
- texto exacto del botón o menú
- tooltip exacto
- agrupación sugerida

### Acciones peligrosas
Para cada una indicar:
- texto exacto
- confirmación requerida
- señal visual
- impacto funcional

---

## 11. Estados visuales del módulo

### Estados vacíos
Definir cómo se verá el módulo cuando no tenga datos.

### Estados de carga
Definir cómo se representa una operación en progreso.

### Estados de error
Definir cómo se muestra un error de forma clara.

### Estados de éxito
Definir cómo se confirma una operación exitosa.

### Estados del dominio
Enumerar los estados propios del módulo y su representación visual.

---

## 12. Formularios asociados

Si el módulo incluye formularios, deben documentarse por separado dentro del mismo lienzo.

### Para cada formulario indicar
- nombre del formulario
- objetivo
- contenedor JavaFX principal
- grupos de campos
- campos obligatorios
- acciones finales

### Por cada campo indicar
- tipo de control JavaFX
- label exacto
- placeholder exacto si aplica
- tooltip exacto
- valor por defecto si aplica
- validación esperada

---

## 13. Tabla principal, si existe

### Columnas
Listar todas las columnas visibles con su nombre exacto.

### Orden recomendado de columnas
Especificar el orden visual.

### Qué columna tiene más jerarquía
Indicar cuál debe destacarse más.

### Acciones por fila
Definir cuáles existen y cómo se muestran.

### Tooltips o ayudas contextuales
Indicar cuáles columnas o acciones deben tener ayuda.

---

## 14. Reglas de espaciado y composición

### Márgenes internos del módulo
### Separación entre bloques
### Altura visual aproximada de encabezados
### Tamaño relativo del panel lateral si existe
### Comportamiento al redimensionar ventana

Estas reglas deben alinearse con el blueprint transversal.

---

## 15. Reglas de consistencia visual

Cada lienzo de módulo debe respetar estas reglas generales:
- evitar abuso de paneles con bordes excesivamente redondeados
- preferir esquinas discretas o casi rectas
- mantener la misma familia visual entre módulos
- repetir patrones de encabezado, filtros, tablas y paneles
- no reinventar estilos innecesariamente
- mantener lenguaje visual administrativo, no infantil
- cuidar el equilibrio entre aire y densidad

---

## 16. Relación con otros módulos

### Qué módulos alimentan a este
### Qué módulos consumen información de este
### Qué dependencias visibles debe percibir el usuario

Esto ayuda a mantener continuidad entre pantallas.

---

## 17. Roles y permisos visibles

### Qué acciones podría ver un administrador
### Qué acciones podría ver un usuario operativo
### Qué acciones podrían ocultarse o deshabilitarse

---

## 18. Tooltips y microcopy

Cada lienzo debe ser explícito con los textos visibles del sistema.

### Se debe definir
- texto exacto de títulos
- texto exacto de labels
- texto exacto de botones
- texto exacto de placeholders
- texto exacto de tooltips
- texto exacto de mensajes vacíos si aplica

La intención es que el diseño no quede en abstracto, sino listo para convertirse luego en maqueta o frontend.

---

## 19. Checklist de cierre del módulo

Antes de considerar listo el lienzo de un módulo, debe verificarse que incluya:
- nombre oficial del módulo
- ubicación en sidebar
- layout general
- controles JavaFX sugeridos
- texto exacto de controles visibles
- acciones principales y secundarias
- tooltips
- estados visuales
- reglas de consistencia
- relación con otros módulos

---

## 20. Orden sugerido de trabajo módulo por módulo

Para mantener consistencia, conviene documentar primero:
1. Dashboard
2. Agenda
3. Clientes
4. Recetas / Prescripción
5. Venta óptica
6. Órdenes de laboratorio
7. Inventario
8. Caja / Facturación
9. Entregas
10. CRM / Seguimiento
11. Reportes
12. Configuración
13. Seguros / Cobertura
14. Proveedores
15. Compras / Abastecimiento
16. Usuarios y roles
17. Taller / Reparaciones
18. Notificaciones / Recordatorios
19. Sucursales

---

## 21. Nota final de planificación

Antes de lanzarse a cada módulo, sí conviene cerrar algunos acuerdos globales adicionales:
- estilo exacto de botones
- nivel de redondez permitido
- tamaños base de padding y spacing
- familia de íconos
- convención de nombres de acciones
- estilo de tooltips
- reglas de diálogo y confirmación
- gramática de estados
- estructura mínima obligatoria que todo módulo debe respetar

Una vez fijadas esas convenciones, ya es totalmente razonable pasar a trabajar lienzo por lienzo, uno por módulo.