# Lienzo del módulo Recetas para sistema de óptica en JavaFX

## 1. Identidad del módulo

### Nombre oficial del módulo en el menú
**Recetas**

### Texto visible del botón del sidebar
**Recetas**

### Tooltip del botón del sidebar
**Consultar, registrar y vincular recetas ópticas con el historial y la venta**

### Ícono conceptual
Ojo, lente o ficha técnica óptica.

### Título visible en pantalla
**Recetas**

### Subtítulo visible en pantalla
**Prescripción óptica, medidas, observaciones y vínculo con el trabajo comercial**

### Tipo de módulo
Módulo técnico con sub-vistas internas especializadas.

### Objetivo del módulo
Permitir que la óptica registre y consulte la receta óptica de cada cliente, mantenga su historial, guarde medidas y observaciones relevantes, y conecte la prescripción con la venta óptica o la orden de trabajo.

---

## 2. Rol del módulo dentro de la arquitectura GUI

Recetas no debe sentirse como un formulario aislado ni como una historia clínica pesada. Debe verse como una ficha técnica óptica bien organizada, reutilizable y conectada con el flujo real del negocio.

### Regla arquitectónica principal
Cuando el usuario entre al módulo Recetas, la región central principal del sistema debe reemplazarse por una vista completa llamada conceptualmente:
**RecetasModuleView**

### Estructura interna limpia del módulo
La vista Recetas debe dividirse en:
- encabezado del módulo
- barra de filtros o contexto rápido
- subnavegación interna del módulo
- región central intercambiable para sub-vistas
- panel lateral derecho con contexto del cliente y acciones rápidas

### Filosofía de implementación
El módulo debe evitar ventanas nuevas para tareas normales. La receta, su historial y su relación con la orden deben navegarse dentro de la misma vista.

### Regla de arquitectura limpia
Debe existir una región interna estable que se reescriba solo con el submódulo activo. Esa región se denomina conceptualmente:
**recetaContentHostPane**

Ese contenedor puede ser un **StackPane** o el centro de un **BorderPane** secundario.

### Sub-vistas que viven dentro de recetaContentHostPane
- Receta actual
- Historial de recetas
- Medidas y parámetros
- Recomendaciones y observaciones
- Vinculación con orden óptica

---

## 3. Sub-vistas oficiales del módulo Recetas

## Sub-vistas definidas
1. **Receta actual**
2. **Historial de recetas**
3. **Medidas y parámetros**
4. **Recomendaciones y observaciones**
5. **Vinculación con orden óptica**

## Orden de prioridad
1. Receta actual
2. Historial de recetas
3. Medidas y parámetros
4. Recomendaciones y observaciones
5. Vinculación con orden óptica

## Vista por defecto al abrir Recetas
**Receta actual**

Motivo: es la vista técnica más importante y la que mejor transmite valor inmediato.

---

## 4. Estructura visual general del módulo Recetas

## Contenedor raíz del módulo
**BorderPane**

### Distribución general del BorderPane principal
- **top**: encabezado del módulo + barra de contexto + subnavegación interna
- **center**: cuerpo principal del módulo
- **right**: no aplica como región aparte, porque el panel contextual ya vive dentro del cuerpo
- **left**: no aplica, porque el sidebar global ya existe fuera del módulo
- **bottom**: no aplica

### Decisión espacial clave
El cuerpo principal debe organizarse con un **SplitPane** horizontal.

### Distribución del SplitPane
- panel izquierdo o central amplio: sub-vista activa del módulo
- panel derecho: contexto del cliente y acciones rápidas

### Proporción recomendada
- región de contenido técnico: 68% a 72%
- panel derecho: 28% a 32%

### Regla de estabilidad visual
El panel derecho debe permanecer visible en la mayoría de sub-vistas para que el usuario no pierda el contexto del cliente.

---

## 5. Encabezado del módulo Recetas

## Contenedor sugerido
**VBox** en la región top del BorderPane principal.

### Subestructura del encabezado
1. franja superior con título y acciones generales
2. franja media con contexto rápido del cliente y de la receta
3. franja inferior con subnavegación interna

---

## 6. Franja superior del encabezado

## Contenedor sugerido
**HBox**

### Zona izquierda
Un **VBox** con:
- Label principal: **Recetas**
- Label secundario: **Prescripción óptica, medidas, observaciones y vínculo con el trabajo comercial**

### Zona derecha
Un **HBox** con:
- Button principal: **Nueva receta**
- Button secundario: **Actualizar módulo**
- Button secundario: **Imprimir receta**

## Controles exactos

### Label principal
- Control: **Label**
- Texto exacto: **Recetas**
- Tooltip: no necesita

### Label secundario
- Control: **Label**
- Texto exacto: **Prescripción óptica, medidas, observaciones y vínculo con el trabajo comercial**
- Tooltip: no necesita

### Botón principal
- Control: **Button**
- Texto exacto: **Nueva receta**
- Tooltip: **Registrar una nueva receta para el cliente seleccionado**
- Estilo: acción principal

### Botón secundario 1
- Control: **Button**
- Texto exacto: **Actualizar módulo**
- Tooltip: **Recargar la información visible del módulo Recetas**
- Estilo: secundario

### Botón secundario 2
- Control: **Button**
- Texto exacto: **Imprimir receta**
- Tooltip: **Generar una versión imprimible de la receta activa**
- Estilo: secundario

---

## 7. Franja media: contexto rápido

## Contenedor sugerido
**FlowPane** o **HBox**. Se recomienda **FlowPane** para mayor elasticidad visual.

## Función
Mostrar de manera rápida el cliente actual y el estado de su receta.

## Controles oficiales

### Label de cliente
- Control: **Label**
- Texto exacto: **Cliente**
- Valor seed: **Sofía Ramírez**
- Tooltip: **Cliente asociado a la receta visible**

### Label de código
- Control: **Label**
- Texto exacto: **Código**
- Valor seed: **CL-00124**
- Tooltip: **Código interno del cliente**

### Label de receta activa
- Control: **Label**
- Texto exacto: **Receta activa**
- Valor seed: **12/04/2026**
- Tooltip: **Fecha de la receta actualmente visible**

### Label de estado
- Control: **Label**
- Texto exacto: **Estado**
- Valor seed: **Vigente**
- Tooltip: **Estado actual de la receta seleccionada**

### Label de profesional
- Control: **Label**
- Texto exacto: **Profesional**
- Valor seed: **Dra. Salazar**
- Tooltip: **Profesional responsable de la receta activa**

### Label de sucursal
- Control: **Label**
- Texto exacto: **Sucursal**
- Valor seed: **Matriz Centro**
- Tooltip: **Sucursal asociada al registro de la receta**

---

## 8. Franja inferior: subnavegación interna del módulo

## Contenedor sugerido
**HBox** con **ToggleButton** dentro de un **ToggleGroup**

## Sub-vistas visibles en la barra
- Receta actual
- Historial de recetas
- Medidas y parámetros
- Recomendaciones y observaciones
- Vinculación con orden óptica

## Tooltips exactos
- Receta actual: **Ver la receta vigente o seleccionada del cliente**
- Historial de recetas: **Consultar recetas anteriores del cliente**
- Medidas y parámetros: **Ver medidas complementarias y parámetros ópticos asociados**
- Recomendaciones y observaciones: **Consultar notas, recomendaciones y observaciones de uso**
- Vinculación con orden óptica: **Consultar o iniciar la vinculación de la receta con una orden de venta**

## Estado visual de sub-vista activa
El ToggleButton activo debe verse claramente seleccionado, con estilo sobrio y administrativo.

---

## 9. Cuerpo principal del módulo Recetas

## Contenedor sugerido
**SplitPane** horizontal

### Panel izquierdo o principal
Sub-vista activa del módulo Recetas.

### Panel derecho
Contexto del cliente y acciones rápidas.

### Regla visual
El panel derecho debe mantener continuidad visual con el módulo Clientes y no parecer una segunda aplicación aparte.

---

# 10. Panel derecho estable: contexto del cliente

## Función
Mantener visible el contexto del cliente mientras se navega entre sub-vistas técnicas.

## Contenedor sugerido
**ScrollPane** con un **VBox** interno.

## Bloques internos del panel derecho
1. Resumen del cliente
2. Estado rápido de receta
3. Resumen comercial
4. Acciones rápidas

## 10.1. Bloque: Resumen del cliente

### Campos visibles
- Nombre completo
- Código del cliente
- Teléfono principal
- Última visita
- Sucursal habitual

### Seeds de ejemplo
- **Sofía Ramírez**
- **CL-00124**
- **099 123 4567**
- **Última visita: 12/04/2026**
- **Sucursal habitual: Matriz Centro**

### Tooltips
- Nombre: no necesita
- Código del cliente: **Código interno usado por el sistema**
- Última visita: **Fecha más reciente de atención o movimiento asociado**
- Sucursal habitual: **Sede donde el cliente suele ser atendido**

## 10.2. Bloque: Estado rápido de receta

### Campos visibles
- Última receta
- Estado actual
- PD registrada
- Profesional

### Seeds
- **Última receta: 12/04/2026**
- **Estado actual: Vigente**
- **PD registrada: 62 mm**
- **Profesional: Dra. Salazar**

## 10.3. Bloque: Resumen comercial

### Campos visibles
- Órdenes activas
- Entregas pendientes
- Saldo pendiente

### Seeds
- **Órdenes activas: 1**
- **Entregas pendientes: 1**
- **Saldo pendiente: $ 0.00**

## 10.4. Bloque: Acciones rápidas

### Botones oficiales
- **Abrir cliente**
- **Nueva venta óptica**
- **Ver historial**
- **Agendar cita**

### Tooltips exactos
- Abrir cliente: **Abrir la ficha completa del cliente en el módulo Clientes**
- Nueva venta óptica: **Iniciar una nueva orden óptica para este cliente**
- Ver historial: **Consultar el historial técnico y comercial del cliente**
- Agendar cita: **Programar una nueva cita para el cliente seleccionado**

---

# 11. Sub-vista 1: Receta actual

## Función
Presentar la receta vigente o seleccionada con precisión técnica y lectura clara.

## Contenedor principal sugerido
**ScrollPane** con un **VBox** interno.

### Estructura general
1. Cabecera técnica de la receta
2. Bloque principal OD / OI
3. Bloque inferior de parámetros compartidos
4. Bloque de observación rápida y recomendación

---

## 11.1. Cabecera técnica

## Contenedor sugerido
**HBox**

### Zona izquierda
Un **GridPane** o **VBox** con:
- Fecha
- Estado
- Profesional
- Sucursal

### Zona derecha
Un **HBox** con botones de acción

### Textos exactos de campos
- Fecha de receta
- Estado
- Profesional
- Sucursal

### Seeds
- Fecha de receta: **12/04/2026**
- Estado: **Vigente**
- Profesional: **Dra. Salazar**
- Sucursal: **Matriz Centro**

### Botones oficiales de la cabecera técnica
- **Editar receta**
- **Guardar cambios**
- **Duplicar receta**
- **Pasar a venta óptica**

### Tooltips exactos
- Editar receta: **Modificar la receta activa del cliente**
- Guardar cambios: **Guardar los cambios realizados en la receta actual**
- Duplicar receta: **Crear una nueva receta a partir de la receta seleccionada**
- Pasar a venta óptica: **Usar esta receta para iniciar una orden de venta óptica**

---

## 11.2. Bloque técnico principal OD / OI

## Contenedor sugerido
**GridPane** principal con dos grandes columnas.

### Columna izquierda
**OD**

### Columna derecha
**OI**

### Estructura interna de cada columna
Cada lado debe ser un **GridPane** compacto con estos campos:
- SPH
- CYL
- Axis
- ADD
- Prism
- Base

### Labels exactos por ojo
- SPH
- CYL
- Axis
- ADD
- Prism
- Base

### Seeds oficiales OD
- SPH: **-1.25**
- CYL: **-0.50**
- Axis: **180**
- ADD: **+1.00**
- Prism: **0.00**
- Base: **—**

### Seeds oficiales OI
- SPH: **-1.00**
- CYL: **-0.25**
- Axis: **170**
- ADD: **+1.00**
- Prism: **0.00**
- Base: **—**

### Tooltips sugeridos
- SPH: **Esfera registrada para el ojo correspondiente**
- CYL: **Cilindro registrado para el ojo correspondiente**
- Axis: **Eje registrado para el ojo correspondiente**
- ADD: **Adición registrada para el ojo correspondiente**
- Prism: **Prisma registrado para el ojo correspondiente**
- Base: **Base del prisma si aplica**

### Regla visual
Esta zona debe sentirse simétrica, técnica y muy estable. No debe verse improvisada ni desordenada.

---

## 11.3. Bloque inferior de parámetros compartidos

## Contenedor sugerido
**GridPane**

### Campos visibles
- PD
- Uso principal
- Estado de vigencia
- Tratamiento sugerido

### Seeds
- PD: **62 mm**
- Uso principal: **Uso diario y pantalla**
- Estado de vigencia: **Vigente**
- Tratamiento sugerido: **Antirreflejo + blue cut**

### Tooltips
- PD: **Distancia pupilar registrada en la receta**
- Uso principal: **Uso principal sugerido para la corrección visual**
- Estado de vigencia: **Condición actual de uso de la receta**
- Tratamiento sugerido: **Tratamiento recomendado para la orden óptica**

---

## 11.4. Bloque de observación rápida y recomendación

## Contenedor sugerido
**VBox**

### Campos visibles
- Observación técnica breve
- Recomendación principal

### Seeds
- Observación técnica breve: **Refiere fatiga visual al final de la jornada**
- Recomendación principal: **Uso diario con tratamiento antirreflejo**

### Tooltips
- Observación técnica breve: **Observación técnica registrada en la receta actual**
- Recomendación principal: **Recomendación general asociada a la receta**

### Estado vacío
No aplica normalmente en receta actual. Si no existe receta activa, el texto debe ser:
**No hay una receta activa para este cliente**

---

# 12. Sub-vista 2: Historial de recetas

## Función
Consultar recetas anteriores del cliente y facilitar su comparación o reutilización.

## Contenedor principal sugerido
**SplitPane** horizontal

### Panel izquierdo
**TableView** de historial

### Panel derecho
Detalle resumido de la receta seleccionada

---

## 12.1. Tabla principal del historial

### Columnas oficiales
- Fecha
- Profesional
- Estado
- OD resumen
- OI resumen
- PD
- Observación breve

### Seeds oficiales
1. 12/04/2026 | Dra. Salazar | Vigente | -1.25 / -0.50 x 180 | -1.00 / -0.25 x 170 | 62 mm | Fatiga visual leve
2. 15/11/2025 | Dr. Paredes | Vencida | -1.00 / -0.25 x 180 | -0.75 / -0.25 x 170 | 61 mm | Uso diario
3. 21/05/2025 | Dra. Salazar | Histórica | -0.75 / -0.25 x 180 | -0.50 / -0.25 x 165 | 61 mm | Revisión anual

### Tooltip de la tabla
**Historial de recetas registradas para el cliente seleccionado**

### Estado vacío
**Este cliente no tiene recetas anteriores registradas**

---

## 12.2. Panel derecho del historial

### Contenido recomendado
- Fecha
- Profesional
- Estado
- Resumen OD
- Resumen OI
- PD
- Observación

### Botones oficiales
- **Ver receta**
- **Duplicar receta**
- **Comparar con actual**
- **Imprimir receta**

### Tooltips exactos
- Ver receta: **Consultar el detalle completo de la receta seleccionada**
- Duplicar receta: **Usar esta receta como base para una nueva receta**
- Comparar con actual: **Comparar la receta histórica con la receta actual**
- Imprimir receta: **Generar una impresión de la receta seleccionada**

---

# 13. Sub-vista 3: Medidas y parámetros

## Función
Centralizar datos complementarios que apoyan el trabajo óptico, pero que no necesariamente deben vivir como protagonistas dentro de la receta principal.

## Contenedor principal sugerido
**ScrollPane** con un **VBox** interno.

### Estructura interna
1. Bloque de medidas básicas
2. Bloque de uso y contexto
3. Bloque de preferencias técnicas

---

## 13.1. Bloque: Medidas básicas

## Contenedor sugerido
**GridPane**

### Campos visibles
- PD
- Altura de montaje
- Observación de centrado

### Seeds
- PD: **62 mm**
- Altura de montaje: **18 mm**
- Observación de centrado: **Montaje estándar para armazón de uso diario**

### Tooltips
- PD: **Distancia pupilar registrada para el cliente**
- Altura de montaje: **Altura sugerida para el montaje del lente**
- Observación de centrado: **Nota relacionada con el centrado o ajuste del lente**

---

## 13.2. Bloque: Uso y contexto

## Contenedor sugerido
**GridPane**

### Campos visibles
- Uso principal
- Jornada de uso
- Preferencia de material
- Requiere protección adicional

### Seeds
- Uso principal: **Pantalla y uso diario**
- Jornada de uso: **Más de 8 horas**
- Preferencia de material: **Lente liviano**
- Requiere protección adicional: **Sí**

### Tooltips
- Uso principal: **Contexto principal de uso de la corrección óptica**
- Jornada de uso: **Tiempo aproximado de uso visual diario**
- Preferencia de material: **Preferencia asociada al tipo de lente o sensación de peso**
- Requiere protección adicional: **Indica si se recomienda un tratamiento o protección adicional**

---

## 13.3. Bloque: Preferencias técnicas

## Contenedor sugerido
**VBox** o **GridPane**

### Campos visibles
- Tratamiento sugerido
- Índice sugerido
- Blue cut
- Fotocromático
- Observación técnica adicional

### Seeds
- Tratamiento sugerido: **Antirreflejo**
- Índice sugerido: **1.56**
- Blue cut: **Sí**
- Fotocromático: **No**
- Observación técnica adicional: **Priorizar comodidad en uso prolongado de pantalla**

### Tooltips
- Tratamiento sugerido: **Tratamiento recomendado para la orden óptica**
- Índice sugerido: **Índice sugerido para el lente del cliente**
- Blue cut: **Indica si se sugiere protección de luz azul**
- Fotocromático: **Indica si se sugiere lente fotocromático**
- Observación técnica adicional: **Nota técnica de apoyo para la selección del lente**

### Botones oficiales del submódulo
- **Editar medidas**
- **Guardar parámetros**
- **Copiar desde receta actual**
- **Pasar a venta óptica**

### Tooltips exactos
- Editar medidas: **Modificar las medidas y parámetros visibles**
- Guardar parámetros: **Guardar los cambios realizados en medidas y parámetros**
- Copiar desde receta actual: **Usar la receta activa como base para completar este bloque**
- Pasar a venta óptica: **Enviar la información técnica a una nueva orden óptica**

### Estado vacío
**No hay medidas complementarias registradas para este cliente**

---

# 14. Sub-vista 4: Recomendaciones y observaciones

## Función
Mostrar la parte narrativa y práctica de la receta, separando recomendaciones de uso, observaciones técnicas y notas internas.

## Contenedor principal sugerido
**ScrollPane** con un **VBox** interno.

### Estructura interna
1. Recomendación principal
2. Observación técnica
3. Notas internas de atención

---

## 14.1. Bloque: Recomendación principal

### Contenedor sugerido
**VBox**

### Campo visible
- Recomendación principal

### Seed
**Uso diario con tratamiento antirreflejo y descanso visual periódico**

### Tooltip
**Recomendación general asociada a la receta actual**

---

## 14.2. Bloque: Observación técnica

### Contenedor sugerido
**VBox**

### Campo visible
- Observación técnica

### Seed
**Refiere fatiga visual al final de la jornada y mejor respuesta en visión intermedia**

### Tooltip
**Observación técnica registrada por el profesional o por el sistema de atención**

---

## 14.3. Bloque: Notas internas de atención

### Contenedor sugerido
**VBox**

### Campo visible
- Nota interna

### Seed
**Cliente prefiere lentes ligeros y solicita aviso previo cuando el trabajo esté listo**

### Tooltip
**Nota interna de atención y continuidad comercial**

### Botones oficiales del submódulo
- **Editar recomendaciones**
- **Guardar observaciones**
- **Usar plantilla**
- **Enviar a orden óptica**

### Tooltips exactos
- Editar recomendaciones: **Modificar la recomendación principal visible**
- Guardar observaciones: **Guardar las observaciones y notas registradas**
- Usar plantilla: **Aplicar una plantilla de recomendación frecuente**
- Enviar a orden óptica: **Usar estas recomendaciones en una nueva orden óptica**

### Estado vacío
**No hay recomendaciones ni observaciones registradas**

---

# 15. Sub-vista 5: Vinculación con orden óptica

## Función
Mostrar si la receta ya fue utilizada en una venta, si puede pasar a una nueva orden o si ya está asociada a un trabajo activo.

## Contenedor principal sugerido
**VBox** o **BorderPane** limpio con tres bloques verticales.

### Estructura interna
1. Estado de vinculación
2. Orden relacionada
3. Acciones disponibles

---

## 15.1. Bloque: Estado de vinculación

### Campos visibles
- Estado de vinculación
- Fecha de uso comercial
- Sucursal de uso

### Seed principal
- Estado de vinculación: **Vinculada a venta óptica**
- Fecha de uso comercial: **10/04/2026**
- Sucursal de uso: **Matriz Centro**

### Tooltip
- Estado de vinculación: **Indica si la receta ya fue usada en un trabajo comercial**
- Fecha de uso comercial: **Fecha del trabajo o venta asociada a la receta**
- Sucursal de uso: **Sucursal donde se generó la orden asociada**

---

## 15.2. Bloque: Orden relacionada

### Campos visibles
- Referencia de orden
- Estado de orden
- Saldo
- Entrega

### Seeds
- Referencia de orden: **OV-124**
- Estado de orden: **En proceso**
- Saldo: **$ 0.00**
- Entrega: **Pendiente**

### Tooltip
- Referencia de orden: **Número de orden asociada a la receta actual**
- Estado de orden: **Estado operativo actual de la orden vinculada**
- Saldo: **Monto pendiente asociado a la orden vinculada**
- Entrega: **Situación actual de la entrega relacionada**

---

## 15.3. Bloque: Acciones disponibles

### Botones oficiales
- **Crear venta óptica**
- **Abrir orden relacionada**
- **Duplicar para nueva orden**
- **Imprimir receta**

### Tooltips exactos
- Crear venta óptica: **Iniciar una nueva orden óptica utilizando la receta actual**
- Abrir orden relacionada: **Abrir la orden ya vinculada a esta receta**
- Duplicar para nueva orden: **Usar la receta actual como base para una nueva orden**
- Imprimir receta: **Generar una impresión de la receta antes de usarla comercialmente**

### Estado vacío del submódulo
**Esta receta todavía no está vinculada a una orden óptica**

---

# 16. Formulario conceptual: Nueva receta

## Función
Permitir registrar una nueva receta sin romper la arquitectura del módulo.

## Contenedor sugerido
Vista interna dentro del mismo módulo o diálogo amplio y controlado. Para esta maqueta se recomienda mantenerlo como subvista embebida para no fragmentar la GUI.

## Estructura sugerida
**ScrollPane** con **VBox** y bloques en **GridPane**.

### Grupos oficiales
1. Datos generales de la receta
2. OD
3. OI
4. Parámetros compartidos
5. Observación y recomendación

### Campos mínimos
- Fecha de receta
- Profesional
- Sucursal
- SPH OD
- CYL OD
- Axis OD
- ADD OD
- SPH OI
- CYL OI
- Axis OI
- ADD OI
- PD
- Observación técnica breve
- Recomendación principal

### Botones finales
- **Guardar receta**
- **Cancelar**

### Tooltips exactos
- Guardar receta: **Registrar la nueva receta del cliente seleccionado**
- Cancelar: **Cerrar la creación de receta sin guardar cambios**

---

# 17. Seed data oficial del módulo Recetas

## Cliente de semilla principal
- Sofía Ramírez

## Profesionales de semilla
- Dra. Salazar
- Dr. Paredes

## Sucursales
- Matriz Centro
- Norte Mall

## Estados usados en el módulo
- Vigente
- Vencida
- Histórica
- En revisión
- Pendiente de completar
- Vinculada a venta óptica
- En proceso

## Valores técnicos de semilla
### OD
- SPH: -1.25
- CYL: -0.50
- Axis: 180
- ADD: +1.00
- Prism: 0.00
- Base: —

### OI
- SPH: -1.00
- CYL: -0.25
- Axis: 170
- ADD: +1.00
- Prism: 0.00
- Base: —

### Compartidos
- PD: 62 mm
- Altura de montaje: 18 mm
- Tratamiento sugerido: Antirreflejo + blue cut
- Uso principal: Uso diario y pantalla

---

# 18. Textos oficiales del módulo Recetas

## Títulos y labels principales
- Recetas
- Prescripción óptica, medidas, observaciones y vínculo con el trabajo comercial
- Cliente
- Código
- Receta activa
- Estado
- Profesional
- Sucursal
- Receta actual
- Historial de recetas
- Medidas y parámetros
- Recomendaciones y observaciones
- Vinculación con orden óptica
- Fecha de receta
- Fecha
- SPH
- CYL
- Axis
- ADD
- Prism
- Base
- PD
- Uso principal
- Estado de vigencia
- Tratamiento sugerido
- Observación técnica breve
- Recomendación principal
- Estado de vinculación
- Fecha de uso comercial
- Referencia de orden
- Estado de orden
- Saldo
- Entrega

## Botones oficiales
- Nueva receta
- Actualizar módulo
- Imprimir receta
- Editar receta
- Guardar cambios
- Duplicar receta
- Pasar a venta óptica
- Ver receta
- Comparar con actual
- Editar medidas
- Guardar parámetros
- Copiar desde receta actual
- Editar recomendaciones
- Guardar observaciones
- Usar plantilla
- Enviar a orden óptica
- Crear venta óptica
- Abrir orden relacionada
- Duplicar para nueva orden
- Abrir cliente
- Nueva venta óptica
- Ver historial
- Agendar cita
- Guardar receta
- Cancelar

## Empty states
- No hay una receta activa para este cliente
- Este cliente no tiene recetas anteriores registradas
- No hay medidas complementarias registradas para este cliente
- No hay recomendaciones ni observaciones registradas
- Esta receta todavía no está vinculada a una orden óptica

---

# 19. Reglas visuales específicas del módulo Recetas

- la receta actual debe ser la vista más fuerte del módulo
- la zona técnica OD / OI debe verse claramente simétrica
- el panel derecho no debe competir con la receta, solo complementarla
- historial debe sentirse de consulta, no de edición principal
- medidas y parámetros debe verse técnica pero compacta
- recomendaciones y observaciones debe ser narrativa, pero bien ordenada
- vinculación con orden óptica debe comunicar integración real con el negocio
- no usar formas redondeadas exageradas
- no usar elementos decorativos innecesarios
- la prioridad debe ser estructura, precisión y continuidad con el flujo de la óptica

---

# 20. Relación del módulo Recetas con otros módulos

Recetas debe conectarse con:
- Clientes, porque la receta vive dentro del contexto del cliente
- Agenda, porque ciertas citas desembocan en una receta nueva
- Venta óptica, porque la receta puede pasar directamente a una orden
- Órdenes de laboratorio, porque algunos trabajos derivan de la prescripción
- Entregas, porque el resultado comercial puede terminar en un trabajo listo
- Inicio, porque el panel principal puede resumir pendientes relacionados

---

# 21. Cierre del módulo Recetas

Este módulo debe transmitir que la óptica maneja la prescripción con seriedad técnica, continuidad histórica y conexión real con el trabajo comercial. No se trata solo de escribir valores. Se trata de registrar la receta, entender su evolución, guardar medidas complementarias, dejar recomendaciones claras y vincularla con el trabajo óptico que se va a fabricar, vender o entregar.

La complejidad correcta del módulo está en que cada sub-vista resuelva una parte concreta del trabajo, sin que el conjunto se vuelva pesado o confuso.