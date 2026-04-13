# Lienzo del módulo Seguros / Cobertura para sistema de óptica en JavaFX

## 1. Identidad del módulo

### Nombre oficial del módulo en el menú
**Seguros / Cobertura**

### Texto visible del botón del sidebar
**Seguros / Cobertura**

### Tooltip del botón del sidebar
**Consultar cobertura, beneficios, autorizaciones, reclamos y aplicación de convenios en la óptica**

### Ícono conceptual
Escudo, tarjeta de beneficio, cobertura o validación administrativa.

### Título visible en pantalla
**Seguros / Cobertura**

### Subtítulo visible en pantalla
**Validación de beneficios, convenios, autorizaciones, reclamos y cobertura aplicada a la venta**

### Tipo de módulo
Módulo administrativo y comercial de beneficios, convenios y cobertura.

### Objetivo del módulo
Permitir que la óptica valide si un cliente posee cobertura vigente, consulte qué beneficios aplica, registre autorizaciones, gestione reclamos y determine con claridad qué monto cubre el plan y qué monto debe asumir el cliente.

---

## 2. Rol del módulo dentro de la arquitectura GUI

Seguros / Cobertura no debe sentirse como una tabla de descuentos ni como un sistema médico pesado. Debe verse como el centro donde la óptica administra convenios, validaciones y reclamos de forma clara y controlada.

### Regla arquitectónica principal
Cuando el usuario entre al módulo Seguros / Cobertura, la región central principal del sistema debe reemplazarse por una vista completa llamada conceptualmente:
**SegurosCoberturaModuleView**

### Estructura interna limpia del módulo
La vista Seguros / Cobertura debe dividirse en:
- encabezado del módulo
- barra de filtros y búsqueda
- subnavegación interna del módulo
- región central intercambiable para sub-vistas
- panel lateral derecho con resumen persistente del caso de cobertura seleccionado

### Filosofía de implementación
El usuario debe poder verificar una cobertura, revisar el plan, consultar autorizaciones, ver el estado del reclamo y entender su impacto en la venta sin perder el contexto del cliente.

### Regla de arquitectura limpia
Debe existir una región interna estable que se reescriba solo con el submódulo activo. Esa región se denomina conceptualmente:
**segurosContentHostPane**

Ese contenedor puede ser un **StackPane** o el centro de un **BorderPane** secundario.

### Sub-vistas que viven dentro de segurosContentHostPane
- Verificación de cobertura
- Planes, convenios y beneficios
- Autorizaciones y validaciones
- Reclamos / claims
- Respuestas, observaciones y rechazos
- Cobertura aplicada a venta
- Histórico y búsqueda avanzada

---

## 3. Sub-vistas oficiales del módulo Seguros / Cobertura

## Sub-vistas definidas
1. **Verificación de cobertura**
2. **Planes, convenios y beneficios**
3. **Autorizaciones y validaciones**
4. **Reclamos / claims**
5. **Respuestas, observaciones y rechazos**
6. **Cobertura aplicada a venta**
7. **Histórico y búsqueda avanzada**

## Orden de prioridad
1. Verificación de cobertura
2. Planes, convenios y beneficios
3. Autorizaciones y validaciones
4. Reclamos / claims
5. Respuestas, observaciones y rechazos
6. Cobertura aplicada a venta
7. Histórico y búsqueda avanzada

## Vista por defecto al abrir el módulo
**Verificación de cobertura**

Motivo: es la vista que mejor responde a la pregunta operativa más útil del día: este cliente tiene cobertura, qué le cubre y qué parte deberá pagar.

---

## 4. Estructura visual general del módulo Seguros / Cobertura

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
- panel derecho: resumen persistente del caso de cobertura seleccionado

### Proporción recomendada
- región de trabajo: 68% a 72%
- panel derecho: 28% a 32%

### Regla de estabilidad visual
El panel derecho debe mantenerse visible durante casi toda la navegación para que el usuario no pierda el contexto del cliente, del plan y del estado administrativo de cobertura.

---

## 5. Encabezado del módulo Seguros / Cobertura

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
- Label principal: **Seguros / Cobertura**
- Label secundario: **Validación de beneficios, convenios, autorizaciones, reclamos y cobertura aplicada a la venta**

### Zona derecha
Un **HBox** con:
- Button secundario: **Actualizar cobertura**
- Button secundario: **Exportar casos**
- Button principal: **Nueva validación**

## Controles exactos

### Label principal
- Control: **Label**
- Texto exacto: **Seguros / Cobertura**
- Tooltip: no necesita

### Label secundario
- Control: **Label**
- Texto exacto: **Validación de beneficios, convenios, autorizaciones, reclamos y cobertura aplicada a la venta**
- Tooltip: no necesita

### Botón secundario 1
- Control: **Button**
- Texto exacto: **Actualizar cobertura**
- Tooltip: **Recargar verificaciones, autorizaciones y reclamos visibles del módulo**
- Estilo: secundario

### Botón secundario 2
- Control: **Button**
- Texto exacto: **Exportar casos**
- Tooltip: **Exportar la vista visible de cobertura, reclamos o autorizaciones**
- Estilo: secundario

### Botón principal
- Control: **Button**
- Texto exacto: **Nueva validación**
- Tooltip: **Registrar o ejecutar una nueva verificación de cobertura para un cliente**
- Estilo: acción principal

---

## 7. Franja media: filtros y búsqueda

## Contenedor sugerido
**FlowPane**

## Función
Permitir localizar verificaciones, planes, autorizaciones y reclamos por cliente, estado, convenio o referencia.

## Controles oficiales

### Búsqueda rápida
- Control: **TextField**
- Label asociado: **Buscar**
- Placeholder exacto: **Cliente, plan, reclamo, autorización o referencia**
- Tooltip: **Buscar casos de cobertura por cliente, plan, reclamo o texto relacionado**

### Filtro por estado de cobertura
- Control: **ComboBox**
- Label asociado: **Estado**
- Ítems de semilla:
  - Todos
  - Cobertura activa
  - Cobertura vencida
  - Pendiente de validación
  - Autorizada
  - Observada
  - Rechazada
  - Reclamo enviado
  - Reclamo aceptado
  - Reclamo parcial
  - Cerrado
- Valor inicial: **Todos**
- Tooltip: **Filtrar por el estado actual del caso de cobertura o reclamo**

### Filtro por plan o convenio
- Control: **ComboBox**
- Label asociado: **Plan / convenio**
- Ítems de semilla:
  - Todos
  - Convenio Visual Plus
  - Plan Empresarial Centro
  - Cobertura Premium Familiar
  - Beneficio Óptico Básico
- Valor inicial: **Todos**
- Tooltip: **Filtrar casos según el plan, convenio o beneficio asociado**

### Filtro por tipo de caso
- Control: **ComboBox**
- Label asociado: **Tipo de caso**
- Ítems de semilla:
  - Todos
  - Verificación
  - Autorización
  - Reclamo
  - Respuesta
  - Aplicación a venta
- Valor inicial: **Todos**
- Tooltip: **Filtrar según el tipo administrativo del caso de cobertura**

### Filtro por sucursal
- Control: **ComboBox**
- Label asociado: **Sucursal**
- Ítems de semilla:
  - Todas
  - Matriz Centro
  - Norte Mall
  - Sur Express
- Valor inicial: **Todas**
- Tooltip: **Filtrar casos según la sucursal donde se gestiona o aplica la cobertura**

### Filtro por fecha inicial
- Control: **DatePicker**
- Label asociado: **Desde**
- Tooltip: **Seleccionar la fecha inicial para consultar casos de cobertura**

### Filtro por fecha final
- Control: **DatePicker**
- Label asociado: **Hasta**
- Tooltip: **Seleccionar la fecha final para consultar casos de cobertura**

### Opción rápida
- Control: **CheckBox**
- Texto exacto: **Solo casos pendientes**
- Tooltip: **Mostrar únicamente casos que aún requieren validación, respuesta o cierre**

### Botón limpiar
- Control: **Button**
- Texto exacto: **Limpiar filtros**
- Tooltip: **Restablecer la búsqueda y todos los filtros del módulo**

---

## 8. Franja inferior: subnavegación interna del módulo

## Contenedor sugerido
**HBox** con **ToggleButton** dentro de un **ToggleGroup**

## Sub-vistas visibles en la barra
- Verificación de cobertura
- Planes, convenios y beneficios
- Autorizaciones y validaciones
- Reclamos / claims
- Respuestas, observaciones y rechazos
- Cobertura aplicada a venta
- Histórico y búsqueda avanzada

## Tooltips exactos
- Verificación de cobertura: **Consultar si el cliente tiene cobertura vigente y qué beneficio aplica**
- Planes, convenios y beneficios: **Consultar los planes o convenios disponibles y sus reglas principales**
- Autorizaciones y validaciones: **Consultar aprobaciones o validaciones necesarias antes de aplicar cobertura**
- Reclamos / claims: **Consultar reclamos enviados y su monto reclamado o cubierto**
- Respuestas, observaciones y rechazos: **Consultar observaciones del pagador o resultado del reclamo**
- Cobertura aplicada a venta: **Consultar cómo la cobertura impacta en la venta y el saldo del cliente**
- Histórico y búsqueda avanzada: **Consultar verificaciones, reclamos y coberturas antiguas mediante filtros amplios**

## Regla visual
La sub-vista activa debe quedar clara, pero sin competir con la lectura administrativa del caso.

---

## 9. Cuerpo principal del módulo Seguros / Cobertura

## Contenedor sugerido
**SplitPane** horizontal

### Panel izquierdo o principal
Sub-vista activa del módulo.

### Panel derecho
Resumen persistente del caso de cobertura seleccionado.

---

# 10. Panel derecho persistente: resumen del caso de cobertura

## Función
Mantener visible el contexto del caso seleccionado mientras se navega por el módulo.

## Contenedor sugerido
**ScrollPane** con un **VBox** interno.

## Bloques internos del panel derecho
1. Identidad del caso
2. Contexto del cliente
3. Estado de cobertura
4. Impacto económico
5. Acciones rápidas

## 10.1. Bloque: Identidad del caso

### Campos visibles
- Referencia
- Tipo de caso
- Plan o convenio

### Seeds
- **Referencia: CB-001**
- **Tipo de caso: Verificación de cobertura**
- **Plan o convenio: Convenio Visual Plus**

## 10.2. Bloque: Contexto del cliente

### Campos visibles
- Cliente
- Sucursal
- Vigencia

### Seeds
- **Cliente: Sofía Ramírez**
- **Sucursal: Matriz Centro**
- **Vigencia: 31/12/2026**

## 10.3. Bloque: Estado de cobertura

### Campos visibles
- Estado actual
- Autorización
- Reclamo asociado

### Seeds
- **Estado actual: Cobertura activa**
- **Autorización: AU-014**
- **Reclamo asociado: RC-022**

## 10.4. Bloque: Impacto económico

### Campos visibles
- Monto cubierto
- Copago
- Saldo cliente

### Seeds
- **Monto cubierto: $ 80.00**
- **Copago: $ 25.00**
- **Saldo cliente: $ 15.00**

## 10.5. Bloque: Acciones rápidas

### Botones oficiales
- **Validar cobertura**
- **Registrar autorización**
- **Aplicar a venta**
- **Crear reclamo**
- **Abrir cliente**

### Tooltips exactos
- Validar cobertura: **Registrar o actualizar la verificación de cobertura del cliente**
- Registrar autorización: **Registrar la autorización asociada a este caso**
- Aplicar a venta: **Abrir o actualizar la cobertura aplicada a la venta relacionada**
- Crear reclamo: **Crear un reclamo administrativo asociado a la cobertura**
- Abrir cliente: **Abrir la ficha del cliente relacionado con este caso**

---

# 11. Sub-vista 1: Verificación de cobertura

## Función
Consultar si un cliente posee cobertura vigente y qué parte de su compra puede quedar cubierta.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: resumen de verificaciones
- **center**: **TableView** principal
- **bottom**: resumen de resultados visibles

## 11.1. Franja superior de la bandeja

### Contenido
- Label: **Verificación de cobertura**
- Label secundario: **9 verificaciones visibles**

### Tooltips
- Verificación de cobertura: **Casos visibles de elegibilidad y verificación según los filtros actuales**
- 9 verificaciones visibles: **Cantidad de verificaciones mostradas en la bandeja actual**

## 11.2. TableView principal

### Columnas oficiales
- Referencia
- Cliente
- Plan o convenio
- Estado
- Vigencia
- Monto disponible
- Sucursal

### Seeds oficiales
1. CB-001 | Sofía Ramírez | Convenio Visual Plus | Cobertura activa | 31/12/2026 | $ 80.00 | Matriz Centro
2. CB-002 | Ana Vera | Beneficio Óptico Básico | Pendiente de validación | 30/06/2026 | $ 40.00 | Norte Mall
3. CB-003 | Carlos Mendoza | Cobertura Premium Familiar | Cobertura vencida | 01/03/2026 | $ 0.00 | Matriz Centro
4. CB-004 | Diana Vélez | Plan Empresarial Centro | Cobertura activa | 31/12/2026 | $ 120.00 | Sur Express

### Tooltip de la tabla
**Seleccione un caso para ver cobertura vigente, beneficios y acción sugerida**

### Estado vacío
**No hay verificaciones visibles con los filtros actuales**

## 11.3. Pie del listado

### Textos exactos
- **Mostrando 4 de 9 verificaciones**
- **Ordenado por vigencia y estado**

### Tooltips
- Mostrando 4 de 9 verificaciones: **Resumen de verificaciones visibles en la bandeja actual**
- Ordenado por vigencia y estado: **Criterio de ordenamiento aplicado a la verificación de cobertura**

### Botones oficiales del submódulo
- **Validar ahora**
- **Editar caso**
- **Cerrar verificación**

### Tooltips
- Validar ahora: **Registrar o actualizar la verificación de elegibilidad del caso seleccionado**
- Editar caso: **Modificar la información administrativa del caso de cobertura**
- Cerrar verificación: **Cerrar el caso si ya no requiere gestión adicional**

---

# 12. Sub-vista 2: Planes, convenios y beneficios

## Función
Consultar y administrar los planes o convenios disponibles dentro del sistema óptico.

## Contenedor principal sugerido
**SplitPane** horizontal

### Panel izquierdo
**ListView** o **TableView** de planes y convenios

### Panel derecho
Detalle del plan seleccionado

## 12.1. Listado de planes

### Valores visibles
- Convenio Visual Plus
- Plan Empresarial Centro
- Cobertura Premium Familiar
- Beneficio Óptico Básico

### Tooltip del listado
**Planes, convenios o beneficios disponibles para aplicar en la óptica**

## 12.2. Detalle del plan

### Campos visibles
- Nombre del plan
- Vigencia general
- Cobertura máxima
- Copago
- Categorías cubiertas
- Restricciones

### Seeds
- Nombre del plan: **Convenio Visual Plus**
- Vigencia general: **Anual**
- Cobertura máxima: **$ 80.00**
- Copago: **$ 25.00**
- Categorías cubiertas: **Monturas, lentes monofocales, accesorios básicos**
- Restricciones: **No cubre reparaciones ni progresivos premium**

### Botones oficiales del submódulo
- **Guardar plan**
- **Duplicar convenio**
- **Desactivar plan**

### Tooltips
- Guardar plan: **Guardar la definición actual del plan o convenio**
- Duplicar convenio: **Crear un nuevo plan usando este como base**
- Desactivar plan: **Retirar el plan del uso activo dentro del sistema**

### Estado vacío
**Seleccione un plan o convenio para ver su definición operativa**

---

# 13. Sub-vista 3: Autorizaciones y validaciones

## Función
Registrar aprobaciones o validaciones previas necesarias antes de aplicar la cobertura.

## Contenedor principal sugerido
**BorderPane** con resumen superior y **TableView** central.

### Estructura interna
1. Resumen de autorizaciones
2. Tabla de autorizaciones
3. Acciones de validación

## 13.1. Resumen superior

### Campos visibles
- Autorizaciones abiertas
- Autorizaciones aprobadas
- Validaciones observadas

### Seeds
- Autorizaciones abiertas: **4**
- Autorizaciones aprobadas: **6**
- Validaciones observadas: **2**

## 13.2. TableView principal

### Columnas oficiales
- Autorización
- Cliente
- Plan
- Fecha
- Estado
- Observación

### Seeds oficiales
1. AU-014 | Sofía Ramírez | Convenio Visual Plus | 16/04/2026 | Autorizada | Cobertura validada para montura y lente básico
2. AU-015 | Ana Vera | Beneficio Óptico Básico | 16/04/2026 | Pendiente de validación | Falta confirmar vigencia
3. AU-016 | Carlos Mendoza | Cobertura Premium Familiar | 15/04/2026 | Observada | Plan vencido según registro actual

### Tooltip de la tabla
**Autorizaciones o validaciones administrativas previas a la aplicación de cobertura**

### Botones oficiales del submódulo
- **Registrar autorización**
- **Marcar validada**
- **Marcar observada**

### Tooltips
- Registrar autorización: **Crear o registrar una autorización administrativa**
- Marcar validada: **Confirmar que la autorización ya quedó aprobada**
- Marcar observada: **Registrar una observación o bloqueo sobre la autorización**

### Estado vacío
**No hay autorizaciones visibles con los filtros actuales**

---

# 14. Sub-vista 4: Reclamos / claims

## Función
Registrar y consultar reclamos administrativos enviados a planes o convenios.

## Contenedor principal sugerido
**BorderPane** con indicadores superiores y **TableView** central.

### Estructura interna
1. Resumen de reclamos
2. Tabla de reclamos
3. Acciones documentales

## 14.1. Resumen superior

### Campos visibles
- Reclamos enviados
- Reclamos aceptados
- Reclamos parciales
- Reclamos rechazados

### Seeds
- Reclamos enviados: **7**
- Reclamos aceptados: **4**
- Reclamos parciales: **2**
- Reclamos rechazados: **1**

## 14.2. TableView principal

### Columnas oficiales
- Reclamo
- Cliente
- Orden relacionada
- Monto reclamado
- Estado
- Fecha de envío

### Seeds oficiales
1. RC-022 | Sofía Ramírez | OV-124 | $ 80.00 | Reclamo enviado | 16/04/2026
2. RC-023 | Diana Vélez | OV-128 | $ 60.00 | Reclamo aceptado | 15/04/2026
3. RC-024 | Ana Vera | OV-131 | $ 40.00 | Reclamo parcial | 14/04/2026
4. RC-025 | Carlos Mendoza | OV-133 | $ 95.00 | Rechazada | 13/04/2026

### Tooltip de la tabla
**Reclamos administrativos vinculados a coberturas aplicadas en venta**

### Estado vacío
**No hay reclamos visibles con los filtros actuales**

### Botones oficiales del submódulo
- **Abrir reclamo**
- **Registrar envío**
- **Actualizar estado**

### Tooltips
- Abrir reclamo: **Consultar el detalle del reclamo seleccionado**
- Registrar envío: **Registrar el envío administrativo del reclamo**
- Actualizar estado: **Actualizar la respuesta o avance del reclamo seleccionado**

---

# 15. Sub-vista 5: Respuestas, observaciones y rechazos

## Función
Consultar la respuesta administrativa recibida para cada reclamo o validación.

## Contenedor principal sugerido
**SplitPane** horizontal

### Panel izquierdo
**TableView** de respuestas y observaciones

### Panel derecho
Detalle del registro seleccionado

## 15.1. TableView principal

### Columnas oficiales
- Fecha
- Referencia
- Tipo de respuesta
- Estado
- Resultado

### Seeds oficiales
1. 16/04/2026 | RC-022 | Respuesta de reclamo | En revisión | Pendiente de aceptación final
2. 15/04/2026 | RC-023 | Reclamo aceptado | Cerrado | Monto completo cubierto
3. 14/04/2026 | RC-024 | Reclamo parcial | Bajo observación | Cobertura menor a la esperada
4. 13/04/2026 | RC-025 | Reclamo rechazado | Cerrado | Plan sin vigencia activa

### Tooltip de la tabla
**Respuestas administrativas ligadas a reclamos, observaciones o rechazos**

## 15.2. Detalle de respuesta

### Campos visibles
- Referencia
- Estado
- Resultado
- Observación administrativa
- Próxima acción

### Seeds
- Referencia: **RC-024**
- Estado: **Reclamo parcial**
- Resultado: **Cobertura aprobada por $ 20.00**
- Observación administrativa: **El plan solo cubre montura básica y no cubre tratamiento adicional**
- Próxima acción: **Actualizar saldo del cliente en venta**

### Botones oficiales del submódulo
- **Registrar respuesta**
- **Marcar cerrada**
- **Abrir venta**

### Tooltips
- Registrar respuesta: **Registrar la respuesta administrativa del reclamo o validación**
- Marcar cerrada: **Cerrar el caso una vez procesada la respuesta**
- Abrir venta: **Abrir la venta relacionada para reflejar el resultado de cobertura**

### Estado vacío
**No hay respuestas visibles con los filtros actuales**

---

# 16. Sub-vista 6: Cobertura aplicada a venta

## Función
Mostrar cómo la cobertura impacta en la venta, el copago y el saldo del cliente.

## Contenedor principal sugerido
**BorderPane** con resumen superior y **TableView** central.

### Estructura interna
1. Resumen de aplicación económica
2. Tabla de ventas con cobertura
3. Acciones vinculadas

## 16.1. Resumen superior

### Campos visibles
- Ventas con cobertura
- Monto cubierto total
- Copagos registrados
- Saldos pendientes

### Seeds
- Ventas con cobertura: **6**
- Monto cubierto total: **$ 320.00**
- Copagos registrados: **$ 110.00**
- Saldos pendientes: **$ 42.00**

## 16.2. TableView principal

### Columnas oficiales
- Venta
- Cliente
- Plan aplicado
- Monto cubierto
- Copago
- Saldo cliente
- Estado

### Seeds oficiales
1. OV-124 | Sofía Ramírez | Convenio Visual Plus | $ 80.00 | $ 25.00 | $ 15.00 | Cobertura aplicada
2. OV-128 | Diana Vélez | Plan Empresarial Centro | $ 60.00 | $ 0.00 | $ 20.00 | Reclamo aceptado
3. OV-131 | Ana Vera | Beneficio Óptico Básico | $ 20.00 | $ 10.00 | $ 12.00 | Reclamo parcial

### Tooltip de la tabla
**Impacto real de la cobertura sobre la venta y el saldo del cliente**

### Botones oficiales del submódulo
- **Aplicar cobertura**
- **Actualizar saldo**
- **Abrir caja**

### Tooltips
- Aplicar cobertura: **Aplicar o actualizar la cobertura sobre la venta seleccionada**
- Actualizar saldo: **Recalcular o registrar el saldo restante del cliente**
- Abrir caja: **Abrir el módulo Caja con la venta seleccionada**

### Estado vacío
**No hay ventas con cobertura visibles con los filtros actuales**

---

# 17. Sub-vista 7: Histórico y búsqueda avanzada

## Función
Consultar verificaciones, autorizaciones, reclamos y aplicaciones de cobertura antiguas mediante filtros amplios.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: filtros extendidos
- **center**: **TableView** de resultados

## 17.1. Filtros extendidos

### Controles sugeridos
- TextField: **Cliente, plan, reclamo o referencia**
- ComboBox: **Estado**
- ComboBox: **Plan / convenio**
- ComboBox: **Tipo de caso**
- ComboBox: **Sucursal**
- DatePicker: **Desde**
- DatePicker: **Hasta**
- Button: **Buscar histórico**
- Button: **Limpiar filtros**

### Tooltip general
**Filtre verificaciones, reclamos y coberturas históricas por cliente, plan, estado o fecha**

## 17.2. TableView principal

### Columnas oficiales
- Fecha
- Referencia
- Cliente
- Tipo de caso
- Estado final
- Observación

### Seeds oficiales
1. 14/04/2026 | CB-010 | María León | Verificación | Cerrado | Cobertura aplicada correctamente
2. 13/04/2026 | AU-011 | Juan Cedeño | Autorización | Cerrado | Validación completada sin observaciones
3. 12/04/2026 | RC-018 | Carlos Mendoza | Reclamo | Rechazada | Plan sin cobertura vigente

### Tooltip de la tabla
**Consulte el histórico de cobertura, convenios y reclamos por cliente o período**

### Botones oficiales del submódulo
- **Abrir caso**
- **Exportar histórico**

### Tooltips
- Abrir caso: **Consultar el detalle histórico del caso seleccionado**
- Exportar histórico: **Exportar el histórico visible del módulo Seguros / Cobertura**

### Estado vacío
**No hay registros históricos que coincidan con los criterios actuales**

---

# 18. Formulario conceptual: Nueva validación

## Función
Permitir registrar una nueva verificación de cobertura dentro de la arquitectura del módulo.

## Contenedor sugerido
Vista interna o diálogo amplio controlado. Para esta maqueta conviene resolverlo dentro del mismo módulo.

## Estructura sugerida
**ScrollPane** con **VBox** y bloques en **GridPane**.

### Grupos de campos
1. Cliente y referencia
2. Plan o convenio
3. Estado inicial y vigencia
4. Beneficio económico y observación

### Campos mínimos
- Cliente
- Referencia del caso
- Plan o convenio
- Vigencia
- Estado inicial
- Monto cubierto disponible
- Copago
- Observación

### Botones finales
- **Guardar validación**
- **Cancelar**

### Tooltips
- Guardar validación: **Registrar el nuevo caso de verificación de cobertura**
- Cancelar: **Cerrar la validación sin guardar**

---

# 19. Seed data oficial del módulo Seguros / Cobertura

## Clientes
- Sofía Ramírez
- Ana Vera
- Carlos Mendoza
- Diana Vélez
- María León
- Juan Cedeño

## Planes y convenios
- Convenio Visual Plus
- Plan Empresarial Centro
- Cobertura Premium Familiar
- Beneficio Óptico Básico

## Referencias
- CB-001
- CB-002
- CB-003
- CB-004
- AU-014
- AU-015
- AU-016
- RC-022
- RC-023
- RC-024
- RC-025
- OV-124
- OV-128
- OV-131

## Estados usados en el módulo
- Cobertura activa
- Cobertura vencida
- Pendiente de validación
- Autorizada
- Observada
- Rechazada
- Reclamo enviado
- Reclamo aceptado
- Reclamo parcial
- Cerrado

---

# 20. Textos oficiales del módulo Seguros / Cobertura

## Títulos y labels principales
- Seguros / Cobertura
- Validación de beneficios, convenios, autorizaciones, reclamos y cobertura aplicada a la venta
- Buscar
- Estado
- Plan / convenio
- Tipo de caso
- Sucursal
- Desde
- Hasta
- Solo casos pendientes
- Verificación de cobertura
- Planes, convenios y beneficios
- Autorizaciones y validaciones
- Reclamos / claims
- Respuestas, observaciones y rechazos
- Cobertura aplicada a venta
- Histórico y búsqueda avanzada
- Referencia
- Cliente
- Vigencia
- Autorización
- Reclamo asociado
- Monto cubierto
- Copago
- Saldo cliente
- Resultado
- Próxima acción

## Botones oficiales
- Actualizar cobertura
- Exportar casos
- Nueva validación
- Limpiar filtros
- Validar cobertura
- Registrar autorización
- Aplicar a venta
- Crear reclamo
- Abrir cliente
- Validar ahora
- Editar caso
- Cerrar verificación
- Guardar plan
- Duplicar convenio
- Desactivar plan
- Marcar validada
- Marcar observada
- Abrir reclamo
- Registrar envío
- Actualizar estado
- Registrar respuesta
- Marcar cerrada
- Abrir venta
- Actualizar saldo
- Abrir caja
- Buscar histórico
- Abrir caso
- Exportar histórico
- Guardar validación
- Cancelar

## Placeholders
- Cliente, plan, reclamo, autorización o referencia
- Cliente, plan, reclamo o referencia

## Empty states
- No hay verificaciones visibles con los filtros actuales
- Seleccione un plan o convenio para ver su definición operativa
- No hay autorizaciones visibles con los filtros actuales
- No hay reclamos visibles con los filtros actuales
- No hay respuestas visibles con los filtros actuales
- No hay ventas con cobertura visibles con los filtros actuales
- No hay registros históricos que coincidan con los criterios actuales

---

# 21. Reglas visuales específicas del módulo Seguros / Cobertura

- la Verificación de cobertura debe sentirse como la vista principal del módulo
- el panel derecho debe mantener claridad sobre plan, vigencia y efecto económico
- Planes, convenios y beneficios debe verse administrativo y sobrio
- Autorizaciones y validaciones debe verse documental y controlado
- Reclamos / claims debe comunicar flujo administrativo claro
- Respuestas, observaciones y rechazos debe sentirse como bandeja de revisión
- Cobertura aplicada a venta debe verse muy conectada con Venta óptica y Caja
- Histórico y búsqueda avanzada debe sentirse fuerte para consulta y trazabilidad
- no abusar de formas redondeadas ni de tarjetas decorativas
- la prioridad debe ser hacer entendible la cobertura sin convertirla en sistema médico pesado

---

# 22. Relación del módulo Seguros / Cobertura con otros módulos

Seguros / Cobertura debe conectarse con:
- Clientes, porque toda cobertura pertenece a una persona o beneficiario
- Venta óptica, porque la cobertura afecta la composición económica de la venta
- Caja, porque la cobertura modifica copago, saldo y cierre económico
- Seguimiento, porque ciertos casos observados o rechazados pueden requerir contacto posterior
- Reportes, porque las coberturas, reclamos y rechazos también deben medirse
- Notificaciones, porque autorizaciones, rechazos o respuestas pueden generar avisos
- Inicio, porque el panel principal puede resumir casos pendientes, reclamos abiertos o coberturas por vencer

---

# 23. Cierre del módulo Seguros / Cobertura

Este módulo debe transmitir que la óptica puede manejar convenios, beneficios, autorizaciones y reclamos con orden real. Debe verse como una herramienta que permite saber qué cubre el plan, qué parte paga el cliente, si hace falta autorización, cómo va el reclamo y cuál es el estado final del caso. No se trata solo de aplicar un descuento, sino de administrar cobertura de forma clara, trazable y útil para la operación diaria.

La complejidad correcta del módulo está en que cada sub-vista resuelve una parte clara del ciclo de cobertura sin convertir la experiencia en una plataforma médica o contable innecesariamente pesada.

