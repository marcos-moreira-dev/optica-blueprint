# Lienzo del módulo Taller / Reparaciones para sistema de óptica en JavaFX

## 1. Identidad del módulo

### Nombre oficial del módulo en el menú
**Taller / Reparaciones**

### Texto visible del botón del sidebar
**Taller / Reparaciones**

### Tooltip del botón del sidebar
**Consultar, diagnosticar, reparar y entregar trabajos técnicos de servicio postventa óptico**

### Ícono conceptual
Herramienta, llave técnica, gafas en reparación o servicio técnico.

### Título visible en pantalla
**Taller / Reparaciones**

### Subtítulo visible en pantalla
**Servicio técnico, ajustes, repuestos, envíos externos y trazabilidad de reparaciones ópticas**

### Tipo de módulo
Módulo técnico y operativo de servicio postventa con sub-vistas internas especializadas.

### Objetivo del módulo
Permitir que la óptica registre ingresos al taller, diagnostique problemas, ejecute ajustes o reparaciones, controle repuestos, gestione trabajos enviados a terceros y documente la entrega técnica con trazabilidad clara.

---

## 2. Rol del módulo dentro de la arquitectura GUI

Taller / Reparaciones no debe sentirse como una libreta de arreglos. Debe verse como el centro de servicio técnico y continuidad postventa de la óptica.

### Regla arquitectónica principal
Cuando el usuario entre al módulo Taller / Reparaciones, la región central principal del sistema debe reemplazarse por una vista completa llamada conceptualmente:
**TallerReparacionesModuleView**

### Estructura interna limpia del módulo
La vista Taller / Reparaciones debe dividirse en:
- encabezado del módulo
- barra de filtros y búsqueda
- subnavegación interna del módulo
- región central intercambiable para sub-vistas
- panel lateral derecho con resumen persistente del trabajo técnico seleccionado

### Filosofía de implementación
El usuario debe poder ver qué trabajo ingresó, cuál fue el diagnóstico, qué se hizo, si consumió piezas, si salió a tercero y en qué estado quedó, sin perder el contexto del caso.

### Regla de arquitectura limpia
Debe existir una región interna estable que se reescriba solo con el submódulo activo. Esa región se denomina conceptualmente:
**tallerContentHostPane**

Ese contenedor puede ser un **StackPane** o el centro de un **BorderPane** secundario.

### Sub-vistas que viven dentro de tallerContentHostPane
- Bandeja de ingresos al taller
- Diagnóstico y evaluación técnica
- Reparación y ajustes
- Piezas y repuestos
- Envíos a tercero o laboratorio externo
- Entrega técnica y postservicio
- Histórico y búsqueda avanzada

---

## 3. Sub-vistas oficiales del módulo Taller / Reparaciones

## Sub-vistas definidas
1. **Bandeja de ingresos al taller**
2. **Diagnóstico y evaluación técnica**
3. **Reparación y ajustes**
4. **Piezas y repuestos**
5. **Envíos a tercero o laboratorio externo**
6. **Entrega técnica y postservicio**
7. **Histórico y búsqueda avanzada**

## Orden de prioridad
1. Bandeja de ingresos al taller
2. Diagnóstico y evaluación técnica
3. Reparación y ajustes
4. Piezas y repuestos
5. Envíos a tercero o laboratorio externo
6. Entrega técnica y postservicio
7. Histórico y búsqueda avanzada

## Vista por defecto al abrir el módulo
**Bandeja de ingresos al taller**

Motivo: es la vista que mejor responde a la pregunta operativa del día: qué trabajos técnicos están vivos ahora mismo.

---

## 4. Estructura visual general del módulo Taller / Reparaciones

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
- panel derecho: resumen persistente del trabajo técnico seleccionado

### Proporción recomendada
- región de trabajo: 68% a 72%
- panel derecho: 28% a 32%

### Regla de estabilidad visual
El panel derecho debe mantenerse visible durante casi toda la navegación para que el usuario no pierda el contexto del trabajo técnico seleccionado.

---

## 5. Encabezado del módulo Taller / Reparaciones

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
- Label principal: **Taller / Reparaciones**
- Label secundario: **Servicio técnico, ajustes, repuestos, envíos externos y trazabilidad de reparaciones ópticas**

### Zona derecha
Un **HBox** con:
- Button secundario: **Actualizar taller**
- Button secundario: **Exportar trabajos**
- Button principal: **Nuevo ingreso técnico**

## Controles exactos

### Label principal
- Control: **Label**
- Texto exacto: **Taller / Reparaciones**
- Tooltip: no necesita

### Label secundario
- Control: **Label**
- Texto exacto: **Servicio técnico, ajustes, repuestos, envíos externos y trazabilidad de reparaciones ópticas**
- Tooltip: no necesita

### Botón secundario 1
- Control: **Button**
- Texto exacto: **Actualizar taller**
- Tooltip: **Recargar los trabajos técnicos visibles del módulo**
- Estilo: secundario

### Botón secundario 2
- Control: **Button**
- Texto exacto: **Exportar trabajos**
- Tooltip: **Exportar la vista visible de reparaciones o ajustes**
- Estilo: secundario

### Botón principal
- Control: **Button**
- Texto exacto: **Nuevo ingreso técnico**
- Tooltip: **Registrar un nuevo trabajo de ajuste o reparación**
- Estilo: acción principal

---

## 7. Franja media: filtros y búsqueda

## Contenedor sugerido
**FlowPane**

## Función
Permitir localizar trabajos por cliente, tipo de intervención, estado, técnico o referencia.

## Controles oficiales

### Búsqueda rápida
- Control: **TextField**
- Label asociado: **Buscar**
- Placeholder exacto: **Cliente, trabajo, reparación o referencia**
- Tooltip: **Buscar trabajos técnicos por cliente, tipo, pieza o texto relacionado**

### Filtro por tipo de intervención
- Control: **ComboBox**
- Label asociado: **Tipo**
- Ítems de semilla:
  - Todos
  - Ajuste rápido
  - Cambio de plaquetas
  - Cambio de tornillo
  - Reparación de bisagra
  - Soldadura de puente
  - Ajuste post-entrega
  - Envío externo
- Valor inicial: **Todos**
- Tooltip: **Filtrar por el tipo principal de intervención técnica**

### Filtro por estado
- Control: **ComboBox**
- Label asociado: **Estado**
- Ítems de semilla:
  - Todos
  - Ingresado
  - En diagnóstico
  - En reparación
  - Esperando repuesto
  - Enviado a tercero
  - Listo para entrega
  - Entregado
  - Requiere revisión
  - Cancelado
- Valor inicial: **Todos**
- Tooltip: **Filtrar trabajos según su estado técnico actual**

### Filtro por técnico responsable
- Control: **ComboBox**
- Label asociado: **Técnico**
- Ítems de semilla:
  - Todos
  - Técnico Rivera
  - Laura Gómez
  - Carlos Mendoza
  - Ana Vera
- Valor inicial: **Todos**
- Tooltip: **Filtrar por responsable técnico del trabajo**

### Filtro por sucursal
- Control: **ComboBox**
- Label asociado: **Sucursal**
- Ítems de semilla:
  - Todas
  - Matriz Centro
  - Norte Mall
  - Sur Express
- Valor inicial: **Todas**
- Tooltip: **Filtrar trabajos técnicos por sede de recepción o entrega**

### Filtro por fecha inicial
- Control: **DatePicker**
- Label asociado: **Desde**
- Tooltip: **Seleccionar la fecha inicial para consultar reparaciones**

### Filtro por fecha final
- Control: **DatePicker**
- Label asociado: **Hasta**
- Tooltip: **Seleccionar la fecha final para consultar reparaciones**

### Opción rápida
- Control: **CheckBox**
- Texto exacto: **Solo pendientes urgentes**
- Tooltip: **Mostrar únicamente trabajos que requieren atención prioritaria**

### Botón limpiar
- Control: **Button**
- Texto exacto: **Limpiar filtros**
- Tooltip: **Restablecer la búsqueda y todos los filtros del módulo**

---

## 8. Franja inferior: subnavegación interna del módulo

## Contenedor sugerido
**HBox** con **ToggleButton** dentro de un **ToggleGroup**

## Sub-vistas visibles en la barra
- Bandeja de ingresos al taller
- Diagnóstico y evaluación técnica
- Reparación y ajustes
- Piezas y repuestos
- Envíos a tercero o laboratorio externo
- Entrega técnica y postservicio
- Histórico y búsqueda avanzada

## Tooltips exactos
- Bandeja de ingresos al taller: **Consultar trabajos técnicos ingresados y pendientes**
- Diagnóstico y evaluación técnica: **Consultar el análisis inicial y decisión técnica del caso**
- Reparación y ajustes: **Consultar o registrar intervenciones técnicas realizadas**
- Piezas y repuestos: **Consultar piezas usadas o requeridas para el trabajo**
- Envíos a tercero o laboratorio externo: **Consultar trabajos derivados a un técnico o laboratorio externo**
- Entrega técnica y postservicio: **Consultar la entrega del arreglo y su conformidad posterior**
- Histórico y búsqueda avanzada: **Consultar reparaciones y ajustes históricos con filtros amplios**

## Regla visual
La sub-vista activa debe quedar clara, pero sin competir con la lectura técnica principal.

---

## 9. Cuerpo principal del módulo Taller / Reparaciones

## Contenedor sugerido
**SplitPane** horizontal

### Panel izquierdo o principal
Sub-vista activa del módulo.

### Panel derecho
Resumen persistente del trabajo técnico seleccionado.

---

# 10. Panel derecho persistente: resumen del trabajo técnico

## Función
Mantener visible el contexto técnico y operativo del trabajo seleccionado.

## Contenedor sugerido
**ScrollPane** con un **VBox** interno.

## Bloques internos del panel derecho
1. Identidad del trabajo
2. Contexto del cliente
3. Estado técnico
4. Componentes y tiempos
5. Acciones rápidas

## 10.1. Bloque: Identidad del trabajo

### Campos visibles
- Referencia
- Tipo de intervención
- Prioridad

### Seeds
- **Referencia: TR-001**
- **Tipo de intervención: Reparación de bisagra**
- **Prioridad: Media**

## 10.2. Bloque: Contexto del cliente

### Campos visibles
- Cliente
- Sucursal
- Fecha de ingreso

### Seeds
- **Cliente: Sofía Ramírez**
- **Sucursal: Matriz Centro**
- **Fecha de ingreso: 16/04/2026**

## 10.3. Bloque: Estado técnico

### Campos visibles
- Estado actual
- Técnico responsable
- Fecha promesa

### Seeds
- **Estado actual: En diagnóstico**
- **Técnico responsable: Técnico Rivera**
- **Fecha promesa: 18/04/2026**

## 10.4. Bloque: Componentes y tiempos

### Campos visibles
- Repuesto requerido
- Envío externo
- Observación breve

### Seeds
- **Repuesto requerido: Bisagra metálica**
- **Envío externo: No**
- **Observación breve: La montura presenta juego excesivo en lado derecho**

## 10.5. Bloque: Acciones rápidas

### Botones oficiales
- **Abrir ficha**
- **Registrar diagnóstico**
- **Registrar ajuste**
- **Usar repuesto**
- **Marcar listo**

### Tooltips exactos
- Abrir ficha: **Abrir la ficha completa del trabajo técnico seleccionado**
- Registrar diagnóstico: **Registrar o actualizar el análisis técnico del caso**
- Registrar ajuste: **Registrar una intervención técnica o ajuste realizado**
- Usar repuesto: **Registrar una pieza o repuesto usado en el trabajo**
- Marcar listo: **Indicar que el trabajo ya quedó listo para entrega**

---

# 11. Sub-vista 1: Bandeja de ingresos al taller

## Función
Servir como bandeja principal de trabajos técnicos ingresados al taller.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: pequeño resumen operativo
- **center**: **TableView** principal
- **bottom**: resumen de resultados visibles

## 11.1. Franja superior de la bandeja

### Contenido
- Label: **Bandeja de ingresos al taller**
- Label secundario: **11 trabajos visibles**

### Tooltips
- Bandeja de ingresos al taller: **Trabajos técnicos visibles según los filtros actuales**
- 11 trabajos visibles: **Cantidad de trabajos mostrados en la bandeja actual**

## 11.2. TableView principal

### Columnas oficiales
- Referencia
- Cliente
- Tipo
- Estado
- Técnico
- Fecha promesa
- Sucursal

### Seeds oficiales
1. TR-001 | Sofía Ramírez | Reparación de bisagra | En diagnóstico | Técnico Rivera | 18/04/2026 | Matriz Centro
2. TR-002 | Carlos Mendoza | Cambio de plaquetas | En reparación | Laura Gómez | 17/04/2026 | Norte Mall
3. TR-003 | Diana Vélez | Ajuste post-entrega | Listo para entrega | Ana Vera | 16/04/2026 | Matriz Centro
4. TR-004 | Ana Vera | Soldadura de puente | Esperando repuesto | Técnico Rivera | 19/04/2026 | Sur Express

### Tooltip de la tabla
**Seleccione un trabajo para ver su estado técnico, piezas y próxima acción**

### Estado vacío
**No hay trabajos técnicos visibles con los filtros actuales**

## 11.3. Pie del listado

### Textos exactos
- **Mostrando 4 de 11 trabajos**
- **Ordenado por prioridad y fecha promesa**

### Tooltips
- Mostrando 4 de 11 trabajos: **Resumen de trabajos visibles en la bandeja actual**
- Ordenado por prioridad y fecha promesa: **Criterio de ordenamiento aplicado al taller**

### Botones oficiales del submódulo
- **Abrir trabajo**
- **Editar ingreso**
- **Cancelar trabajo**

### Tooltips
- Abrir trabajo: **Consultar la ficha completa del trabajo seleccionado**
- Editar ingreso: **Modificar datos iniciales del ingreso técnico**
- Cancelar trabajo: **Cancelar el trabajo técnico seleccionado**

---

# 12. Sub-vista 2: Diagnóstico y evaluación técnica

## Función
Registrar la evaluación inicial del caso y definir el tipo de intervención requerida.

## Contenedor principal sugerido
**ScrollPane** con un **VBox** interno y bloques en **GridPane**.

### Estructura interna
1. Resumen del caso
2. Evaluación del daño
3. Decisión técnica
4. Observaciones

## 12.1. Bloque: Resumen del caso

### Campos visibles
- Referencia
- Cliente
- Tipo de trabajo
- Fecha de ingreso

### Seeds
- Referencia: **TR-001**
- Cliente: **Sofía Ramírez**
- Tipo de trabajo: **Reparación de bisagra**
- Fecha de ingreso: **16/04/2026**

## 12.2. Bloque: Evaluación del daño

### Campos visibles
- Daño principal
- Complejidad
- Requiere repuesto
- Requiere envío externo

### Seeds
- Daño principal: **Bisagra derecha desgastada**
- Complejidad: **Media**
- Requiere repuesto: **Sí**
- Requiere envío externo: **No**

## 12.3. Bloque: Decisión técnica

### Campos visibles
- Tipo de intervención
- Tiempo estimado
- Responsable asignado

### Seeds
- Tipo de intervención: **Cambio de bisagra y ajuste final**
- Tiempo estimado: **2 días**
- Responsable asignado: **Técnico Rivera**

## 12.4. Bloque: Observaciones

### Control sugerido
- **TextArea** para observación técnica

### Seed
**Montura estable en general, pero el lado derecho requiere reemplazo y alineación posterior.**

### Botones oficiales del submódulo
- **Guardar diagnóstico**
- **Enviar a reparación**
- **Enviar a tercero**

### Tooltips
- Guardar diagnóstico: **Guardar la evaluación técnica del caso**
- Enviar a reparación: **Pasar el trabajo a la fase de intervención interna**
- Enviar a tercero: **Derivar el trabajo a un técnico o laboratorio externo**

### Estado vacío
**Seleccione un trabajo para registrar su diagnóstico técnico**

---

# 13. Sub-vista 3: Reparación y ajustes

## Función
Registrar las intervenciones técnicas efectuadas sobre la montura o el trabajo óptico.

## Contenedor principal sugerido
**SplitPane** horizontal

### Panel izquierdo
**TableView** de intervenciones

### Panel derecho
Detalle técnico de la intervención seleccionada

## 13.1. TableView principal

### Columnas oficiales
- Fecha
- Referencia
- Intervención
- Técnico
- Estado

### Seeds oficiales
1. 16/04/2026 | TR-001 | Cambio de bisagra | Técnico Rivera | En proceso
2. 16/04/2026 | TR-002 | Cambio de plaquetas | Laura Gómez | Completado
3. 15/04/2026 | TR-003 | Ajuste post-entrega | Ana Vera | Completado
4. 15/04/2026 | TR-004 | Revisión de soldadura | Técnico Rivera | Pendiente

### Tooltip de la tabla
**Intervenciones técnicas realizadas o en curso dentro del taller**

## 13.2. Detalle de la intervención

### Campos visibles
- Intervención
- Fecha
- Técnico
- Estado
- Descripción técnica
- Resultado

### Seeds
- Intervención: **Cambio de bisagra**
- Fecha: **16/04/2026**
- Técnico: **Técnico Rivera**
- Estado: **En proceso**
- Descripción técnica: **Se retiró bisagra dañada y se preparó ajuste de nueva pieza**
- Resultado: **Pendiente de cierre técnico**

### Botones oficiales del submódulo
- **Registrar intervención**
- **Marcar completada**
- **Solicitar revisión**

### Tooltips
- Registrar intervención: **Registrar una nueva acción técnica sobre el trabajo**
- Marcar completada: **Cerrar la intervención técnica seleccionada**
- Solicitar revisión: **Marcar el trabajo para una revisión adicional**

### Estado vacío
**No hay intervenciones visibles para el trabajo seleccionado**

---

# 14. Sub-vista 4: Piezas y repuestos

## Función
Registrar piezas consumidas, repuestos requeridos y su relación con el trabajo técnico.

## Contenedor principal sugerido
**SplitPane** horizontal

### Panel izquierdo
**TableView** de piezas y repuestos

### Panel derecho
Detalle del repuesto seleccionado

## 14.1. TableView principal

### Columnas oficiales
- Referencia de trabajo
- Pieza
- Cantidad
- Estado
- Observación

### Seeds oficiales
1. TR-001 | Bisagra metálica | 1 | Requerida | Cambio completo de lado derecho
2. TR-002 | Plaqueta silicona | 2 | Usada | Reemplazo estándar
3. TR-003 | Tornillo estándar | 1 | Usado | Ajuste menor
4. TR-004 | Varilla terminal | 1 | Pendiente | Esperando disponibilidad

### Tooltip de la tabla
**Piezas y repuestos asociados a los trabajos técnicos**

## 14.2. Detalle del repuesto

### Campos visibles
- Pieza
- Estado
- Cantidad
- Fuente
- Impacto en inventario

### Seeds
- Pieza: **Bisagra metálica**
- Estado: **Requerida**
- Cantidad: **1**
- Fuente: **Inventario interno**
- Impacto en inventario: **Descontar al confirmar uso**

### Botones oficiales del submódulo
- **Registrar repuesto**
- **Marcar usado**
- **Solicitar pieza**

### Tooltips
- Registrar repuesto: **Registrar una nueva pieza o repuesto para el trabajo seleccionado**
- Marcar usado: **Confirmar el uso de la pieza en el trabajo técnico**
- Solicitar pieza: **Registrar que la pieza debe conseguirse o comprarse**

### Estado vacío
**No hay piezas ni repuestos visibles para este trabajo**

---

# 15. Sub-vista 5: Envíos a tercero o laboratorio externo

## Función
Consultar trabajos derivados a un tercero y su seguimiento logístico.

## Contenedor principal sugerido
**BorderPane** con resumen superior y **TableView** central.

### Estructura interna
1. Resumen de envíos externos
2. Tabla de trabajos externos
3. Acciones de seguimiento

## 15.1. Resumen superior

### Campos visibles
- Trabajos externos abiertos
- Esperando retorno
- Incidencias externas

### Seeds
- Trabajos externos abiertos: **2**
- Esperando retorno: **2**
- Incidencias externas: **1**

## 15.2. TableView principal

### Columnas oficiales
- Referencia
- Tipo de trabajo
- Tercero o laboratorio
- Fecha de envío
- Estado
- Fecha estimada

### Seeds oficiales
1. TR-005 | Soldadura de puente | Taller Metal Óptico | 15/04/2026 | Enviado a tercero | 18/04/2026
2. TR-006 | Reparación estructural | LabVision Externo | 14/04/2026 | En revisión externa | 19/04/2026

### Tooltip de la tabla
**Trabajos técnicos derivados a terceros o laboratorios externos**

### Botones oficiales del submódulo
- **Registrar envío externo**
- **Actualizar estado**
- **Registrar retorno**

### Tooltips
- Registrar envío externo: **Registrar la salida de un trabajo hacia un tercero o laboratorio**
- Actualizar estado: **Modificar el estado del trabajo externo seleccionado**
- Registrar retorno: **Registrar el retorno del trabajo al taller o a la sucursal**

### Estado vacío
**No hay trabajos externos visibles en este momento**

---

# 16. Sub-vista 6: Entrega técnica y postservicio

## Función
Registrar la entrega del trabajo reparado y cualquier conformidad o seguimiento posterior.

## Contenedor principal sugerido
**ScrollPane** con **VBox** interno y bloques en **GridPane**.

### Estructura interna
1. Datos de entrega
2. Conformidad del cliente
3. Seguimiento posterior

## 16.1. Bloque: Datos de entrega

### Campos visibles
- Referencia
- Fecha de entrega
- Responsable de entrega
- Estado final

### Seeds
- Referencia: **TR-003**
- Fecha de entrega: **16/04/2026**
- Responsable de entrega: **Ana Vera**
- Estado final: **Entregado**

## 16.2. Bloque: Conformidad del cliente

### Campos visibles
- Cliente conforme
- Requiere nuevo ajuste
- Observación del cliente

### Seeds
- Cliente conforme: **Sí**
- Requiere nuevo ajuste: **No**
- Observación del cliente: **La montura quedó estable y cómoda**

## 16.3. Bloque: Seguimiento posterior

### Campos visibles
- Requiere control posterior
- Fecha sugerida

### Seeds
- Requiere control posterior: **No**
- Fecha sugerida: **No aplica**

### Botones oficiales del submódulo
- **Registrar entrega técnica**
- **Marcar conforme**
- **Crear seguimiento**

### Tooltips
- Registrar entrega técnica: **Registrar la entrega final del trabajo técnico**
- Marcar conforme: **Indicar que el cliente recibió el trabajo sin observaciones**
- Crear seguimiento: **Crear un seguimiento posterior si el caso lo requiere**

### Estado vacío
**No hay entrega técnica visible para este trabajo**

---

# 17. Sub-vista 7: Histórico y búsqueda avanzada

## Función
Consultar trabajos técnicos históricos mediante filtros amplios por cliente, tipo, estado o pieza usada.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: filtros extendidos
- **center**: **TableView** de resultados

## 17.1. Filtros extendidos

### Controles sugeridos
- TextField: **Cliente, trabajo, tipo o repuesto**
- ComboBox: **Tipo**
- ComboBox: **Estado**
- ComboBox: **Técnico**
- ComboBox: **Sucursal**
- DatePicker: **Desde**
- DatePicker: **Hasta**
- Button: **Buscar histórico**
- Button: **Limpiar filtros**

### Tooltip general
**Filtre reparaciones y ajustes históricos por cliente, técnico, estado o fecha**

## 17.2. TableView principal

### Columnas oficiales
- Fecha
- Referencia
- Cliente
- Tipo
- Estado final
- Observación

### Seeds oficiales
1. 14/04/2026 | TR-009 | Sofía Ramírez | Cambio de tornillo | Entregado | Ajuste menor completado
2. 13/04/2026 | TR-008 | Carlos Mendoza | Cambio de plaquetas | Entregado | Repuesto estándar aplicado
3. 12/04/2026 | TR-007 | Diana Vélez | Soldadura de puente | Requiere revisión | Cliente reporta leve inestabilidad

### Tooltip de la tabla
**Consulte el histórico de trabajos técnicos por cliente, tipo o resultado**

### Botones oficiales del submódulo
- **Abrir historial**
- **Exportar histórico**

### Tooltips
- Abrir historial: **Abrir el detalle histórico del trabajo seleccionado**
- Exportar histórico: **Exportar el histórico visible del módulo Taller / Reparaciones**

### Estado vacío
**No hay registros históricos que coincidan con los criterios actuales**

---

# 18. Formulario conceptual: Nuevo ingreso técnico

## Función
Permitir registrar un nuevo trabajo de taller dentro de la arquitectura del módulo.

## Contenedor sugerido
Vista interna o diálogo amplio controlado. Para esta maqueta conviene resolverlo dentro del mismo módulo.

## Estructura sugerida
**ScrollPane** con **VBox** y bloques en **GridPane**.

### Grupos de campos
1. Datos del cliente
2. Tipo de problema
3. Estado inicial y prioridad
4. Observación técnica inicial

### Campos mínimos
- Cliente
- Referencia del trabajo
- Tipo de intervención
- Sucursal
- Prioridad
- Estado inicial
- Fecha promesa
- Observación inicial

### Botones finales
- **Guardar ingreso**
- **Cancelar**

### Tooltips
- Guardar ingreso: **Registrar el nuevo trabajo técnico dentro del sistema**
- Cancelar: **Cerrar el registro del trabajo sin guardar**

---

# 19. Seed data oficial del módulo Taller / Reparaciones

## Clientes
- Sofía Ramírez
- Carlos Mendoza
- Diana Vélez
- Ana Vera

## Referencias
- TR-001
- TR-002
- TR-003
- TR-004
- TR-005
- TR-006
- TR-007
- TR-008
- TR-009

## Tipos de intervención
- Ajuste rápido
- Cambio de plaquetas
- Cambio de tornillo
- Reparación de bisagra
- Soldadura de puente
- Ajuste post-entrega
- Envío externo

## Piezas y repuestos
- Tornillo estándar
- Plaqueta silicona
- Bisagra metálica
- Varilla terminal

## Estados usados en el módulo
- Ingresado
- En diagnóstico
- En reparación
- Esperando repuesto
- Enviado a tercero
- Listo para entrega
- Entregado
- Requiere revisión
- Cancelado

---

# 20. Textos oficiales del módulo Taller / Reparaciones

## Títulos y labels principales
- Taller / Reparaciones
- Servicio técnico, ajustes, repuestos, envíos externos y trazabilidad de reparaciones ópticas
- Buscar
- Tipo
- Estado
- Técnico
- Sucursal
- Desde
- Hasta
- Solo pendientes urgentes
- Bandeja de ingresos al taller
- Diagnóstico y evaluación técnica
- Reparación y ajustes
- Piezas y repuestos
- Envíos a tercero o laboratorio externo
- Entrega técnica y postservicio
- Histórico y búsqueda avanzada
- Referencia
- Tipo de intervención
- Prioridad
- Fecha de ingreso
- Fecha promesa
- Técnico responsable
- Repuesto requerido
- Observación breve
- Daño principal
- Complejidad
- Tipo de trabajo
- Estado final
n- Cliente conforme
- Requiere nuevo ajuste
- Observación del cliente

## Botones oficiales
- Actualizar taller
- Exportar trabajos
- Nuevo ingreso técnico
- Limpiar filtros
- Abrir ficha
- Registrar diagnóstico
- Registrar ajuste
- Usar repuesto
- Marcar listo
- Abrir trabajo
- Editar ingreso
- Cancelar trabajo
- Guardar diagnóstico
- Enviar a reparación
- Enviar a tercero
- Registrar intervención
- Marcar completada
- Solicitar revisión
- Registrar repuesto
- Marcar usado
- Solicitar pieza
- Registrar envío externo
- Actualizar estado
- Registrar retorno
- Registrar entrega técnica
- Marcar conforme
- Crear seguimiento
- Buscar histórico
- Abrir historial
- Exportar histórico
- Guardar ingreso
- Cancelar

## Placeholders
- Cliente, trabajo, reparación o referencia
- Cliente, trabajo, tipo o repuesto

## Empty states
- No hay trabajos técnicos visibles con los filtros actuales
- Seleccione un trabajo para registrar su diagnóstico técnico
- No hay intervenciones visibles para el trabajo seleccionado
- No hay piezas ni repuestos visibles para este trabajo
- No hay trabajos externos visibles en este momento
- No hay entrega técnica visible para este trabajo
- No hay registros históricos que coincidan con los criterios actuales

---

# 21. Reglas visuales específicas del módulo Taller / Reparaciones

- la Bandeja de ingresos al taller debe sentirse como la vista principal del módulo
- el panel derecho debe mantener claridad sobre estado técnico, responsable y próxima acción
- Diagnóstico y evaluación técnica debe verse muy ordenado y de lectura técnica
- Reparación y ajustes debe comunicar intervención real y no solo texto descriptivo
- Piezas y repuestos debe mostrar relación clara entre técnica e inventario
- Envíos a tercero o laboratorio externo debe verse logístico y trazable
- Entrega técnica y postservicio debe sentirse breve, clara y orientada a conformidad
- Histórico y búsqueda avanzada debe sentirse fuerte para consulta técnica
- no abusar de formas redondeadas ni de tarjetas decorativas
- la prioridad debe ser mostrar continuidad postventa y trazabilidad del servicio técnico óptico

---

# 22. Relación del módulo Taller / Reparaciones con otros módulos

Taller / Reparaciones debe conectarse con:
- Clientes, porque cada trabajo técnico pertenece a una persona o caso
- Inventario, porque algunas reparaciones consumen piezas o repuestos
- Entregas, porque el trabajo reparado debe pasar a una entrega técnica ordenada
- Seguimiento, porque algunos casos pueden requerir contacto posterior o control adicional
- Proveedores y Compras, porque ciertas piezas o trabajos externos dependen de abastecimiento o terceros
- Notificaciones, porque trabajos listos o enviados a revisión pueden generar avisos
- Inicio, porque el panel principal puede resumir trabajos pendientes, esperas de repuesto o envíos externos

---

# 23. Cierre del módulo Taller / Reparaciones

Este módulo debe transmitir que la óptica no solo vende y entrega, sino que también puede sostener servicio técnico, ajustes y reparaciones con orden real. Debe verse como una herramienta que registra el ingreso, el diagnóstico, la intervención, el uso de piezas, la posible derivación externa y la entrega final, dejando trazabilidad clara del servicio postventa.

La complejidad correcta del módulo está en que cada sub-vista resuelve una parte clara del trabajo técnico sin convertir la experiencia en un taller industrial excesivamente complejo.

