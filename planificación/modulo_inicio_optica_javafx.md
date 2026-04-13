# Lienzo del módulo Inicio para sistema de óptica en JavaFX

## 1. Identidad del módulo

### Nombre oficial del módulo en el menú
**Inicio**

### Texto visible del botón del sidebar
**Inicio**

### Tooltip del botón del sidebar
**Ver el resumen general de la operación diaria**

### Ícono conceptual
Casa, panel principal o inicio de jornada.

### Título visible en pantalla
**Inicio**

### Subtítulo visible en pantalla
**Resumen general de la operación diaria**

### Tipo de módulo
Dashboard operativo transversal.

### Objetivo del módulo
Permitir que cualquier usuario entienda en pocos segundos qué está ocurriendo hoy en la óptica, qué está pendiente, qué urge, qué se movió recientemente y desde qué punto conviene actuar.

---

## 2. Rol del módulo dentro de la arquitectura GUI

Este módulo no debe ser una simple portada. Debe ser una vista activa y útil. Su trabajo es concentrar contexto, prioridad y acceso rápido.

Desde el punto de vista de arquitectura limpia de GUI, el módulo Inicio debe ser una **vista principal compuesta por bloques reutilizables**, sin mezclar demasiada lógica en una sola clase visual.

### Decisión de arquitectura visual
El módulo **Inicio** debe renderizarse dentro del área central del sistema, ocupando la región principal del layout global.

### Decisión de arquitectura interna
El módulo debe dividirse en:
- una vista contenedora del módulo Inicio
- bloques internos independientes del dashboard
- una región de contenido central que puede actualizarse sin afectar sidebar ni topbar global

### Filosofía de implementación visual
Cada módulo principal del sistema debe reemplazar la vista cargada en la región central del sistema. Dentro de ese módulo, los bloques internos no tienen por qué ser ventanas nuevas. Son secciones reutilizables dentro de la misma vista.

### Regla arquitectónica importante
El módulo Inicio **no debe abrir subventanas** para su contenido normal. Todo debe verse como una sola vista estable.

---

## 3. Estructura visual general del módulo Inicio

## Contenedor raíz del módulo
**BorderPane**

### Distribución general
- **top**: encabezado del módulo
- **center**: cuerpo del dashboard dentro de un **ScrollPane**
- **left**: no aplica dentro del módulo, porque el sidebar pertenece al layout global
- **right**: no se usará como panel fijo externo en este módulo; los paneles van integrados en el cuerpo
- **bottom**: no aplica

## Decisión principal
El contenido real del dashboard debe ir dentro de un **ScrollPane** para permitir crecimiento vertical sin romper la composición.

## Estructura interna del centro
Dentro del **ScrollPane**, el contenido debe organizarse con un **VBox** principal llamado conceptualmente:
**inicioContentVBox**

Ese VBox principal debe contener, en este orden:
1. fila de indicadores principales
2. cuerpo principal del dashboard en dos columnas
3. bloque opcional inferior de resumen por sucursal

---

## 4. Encabezado del módulo

## Contenedor sugerido
**HBox** principal dentro del top del BorderPane.

## Composición del encabezado
### Zona izquierda
Debe ir dentro de un **VBox**:
- Label grande: **Inicio**
- Label secundario: **Resumen general de la operación diaria**

### Zona derecha
Debe ir dentro de un **HBox** alineado a la derecha con:
- Label: **Sucursal activa**
- ComboBox con texto visible inicial: **Matriz Centro**
- Label de fecha del día, por ejemplo: **Lunes, 14 de abril de 2026**

## Controles exactos del encabezado

### Título principal
- Control: **Label**
- Texto exacto: **Inicio**
- Tooltip: no necesita
- Estilo esperado: tipografía más grande y más firme que el resto del módulo

### Subtítulo
- Control: **Label**
- Texto exacto: **Resumen general de la operación diaria**
- Tooltip: no necesita
- Estilo esperado: más discreto, de apoyo

### Label de sucursal
- Control: **Label**
- Texto exacto: **Sucursal activa**
- Tooltip: **Seleccione la sede cuyos indicadores desea consultar**

### Selector de sucursal
- Control: **ComboBox**
- Ítems de semilla:
  - Matriz Centro
  - Norte Mall
- Valor inicial: **Matriz Centro**
- Tooltip: **Cambiar la sucursal visible en el panel de inicio**

### Label de fecha
- Control: **Label**
- Texto de semilla: **Lunes, 14 de abril de 2026**
- Tooltip: **Fecha operativa mostrada en el sistema**

## Regla visual del encabezado
- No debe ser alto en exceso
- Debe respirar
- Debe separar con claridad la identidad del módulo de su contenido operativo
- La acción de cambiar sucursal no debe verse más importante que el título

---

## 5. Cuerpo del módulo: vista principal del dashboard

## Contenedor general del cuerpo
**VBox** dentro del ScrollPane

### Orden interno del contenido
1. Bloque de indicadores KPI
2. Bloque principal de dos columnas
3. Bloque inferior complementario

---

## 6. Bloque 1: indicadores principales del día

## Contenedor sugerido
**HBox** si el ancho alcanza bien, o **GridPane** de dos filas si se quiere más estabilidad visual.

### Decisión recomendada
Usar **GridPane** de dos filas y tres columnas para evitar que las tarjetas queden aplastadas en resoluciones medianas.

## Seis indicadores oficiales
1. Citas de hoy
2. Ventas de hoy
3. Órdenes pendientes
4. Listas para entregar
5. Por cobrar
6. Stock crítico

## Cada indicador debe ser una superficie propia
Cada indicador debe vivir dentro de un **VBox** con estructura simple:
- Label pequeño del nombre
- Label grande del valor
- Label pequeño de contexto secundario

## Seeds recomendadas
### Indicador 1
- Título: **Citas de hoy**
- Valor: **18**
- Secundario: **12 confirmadas, 2 reprogramadas**

### Indicador 2
- Título: **Ventas de hoy**
- Valor: **$ 1,240.50**
- Secundario: **8 ventas registradas**

### Indicador 3
- Título: **Órdenes pendientes**
- Valor: **11**
- Secundario: **3 requieren revisión**

### Indicador 4
- Título: **Listas para entregar**
- Valor: **6**
- Secundario: **2 con saldo pendiente**

### Indicador 5
- Título: **Por cobrar**
- Valor: **$ 420.00**
- Secundario: **5 trabajos pendientes de pago**

### Indicador 6
- Título: **Stock crítico**
- Valor: **4**
- Secundario: **2 productos agotados**

## Tooltips de los indicadores
- Citas de hoy: **Cantidad total de citas programadas para la jornada**
- Ventas de hoy: **Total vendido en la sucursal seleccionada durante el día**
- Órdenes pendientes: **Trabajos que aún no han sido completados o entregados**
- Listas para entregar: **Trabajos terminados y pendientes de retiro**
- Por cobrar: **Monto pendiente de pago por trabajos u órdenes activas**
- Stock crítico: **Productos con existencias bajas o agotadas**

## Regla visual
- No usar tarjetas infladas ni bordes redondeados exagerados
- Superficies limpias
- Bordes discretos
- Valor principal con mucha jerarquía
- Texto secundario más tenue

---

## 7. Bloque 2: cuerpo principal en dos columnas

## Contenedor sugerido
**HBox** principal con dos columnas verticales.

### Columna izquierda
Debe ser ligeramente más ancha que la derecha.

### Columna derecha
Debe ser un poco más estrecha, con bloques de apoyo.

---

## 8. Columna izquierda

La columna izquierda debe contener las vistas más operativas del día.

### Orden recomendado de bloques
1. Próximas citas
2. Pendientes y alertas

### Contenedor de la columna izquierda
**VBox**

---

## 9. Bloque: Próximas citas

## Función
Permitir una lectura rápida de la agenda inmediata sin entrar al módulo completo de Agenda.

## Contenedor sugerido
**VBox** con título arriba y contenido debajo.

## Encabezado del bloque
- Label título: **Próximas citas**
- Button secundario: **Ver agenda**
- Tooltip del botón: **Abrir el módulo Agenda**

## Área de contenido
Usar una **TableView** simple con pocas columnas.

## Columnas exactas
- Hora
- Cliente
- Atención
- Estado
- Profesional

## Seeds recomendadas
1. 09:00 | Sofía Ramírez | Examen visual | Confirmada | Dra. Salazar
2. 09:30 | Juan Cedeño | Ajuste de montura | Pendiente | Técnico Rivera
3. 10:15 | Carmen López | Entrega de lentes | Confirmada | Asesor Molina
4. 11:00 | Luis Andrade | Revisión de receta | Requiere revisión | Dr. Paredes

## Tooltip de la tabla
**Resumen de las próximas atenciones programadas en la sucursal seleccionada**

## Acción secundaria opcional por fila
No hace falta complicarlo. Puede omitirse en la primera versión de propaganda.

## Estado vacío
Texto exacto: **No hay citas programadas para esta jornada**

---

## 10. Bloque: Pendientes y alertas

## Función
Mostrar lo urgente, lo vencido o lo que necesita decisión rápida.

## Contenedor sugerido
**VBox**

## Encabezado del bloque
- Label: **Pendientes y alertas**
- Button secundario: **Ver detalle**
- Tooltip del botón: **Abrir el módulo relacionado con los pendientes visibles**

## Área de contenido
Usar un **VBox** con filas de alerta o una **TableView** muy corta. Se recomienda un VBox con filas tipo item para que se vea más visual y menos burocrático.

## Cada fila de alerta debe tener
- etiqueta de tipo
- texto principal
- estado visible
- referencia corta

## Seeds recomendadas
1. **Laboratorio** | Orden #LAB-203 retrasada | Vencido
2. **Entrega** | Trabajo de Ana Vera listo desde ayer | Listo
3. **Caja** | Saldo pendiente en orden #OV-118 | Por cobrar
4. **Inventario** | Lente antirreflejo 1.56 agotado | Agotado
5. **Agenda** | 2 citas pendientes de confirmación | Pendiente

## Tooltips sugeridos
Cada alerta puede tener un tooltip breve según el caso.

Ejemplos:
- **La fecha promesa ya fue superada**
- **Trabajo terminado pendiente de retiro por el cliente**
- **El producto requiere reposición inmediata**

## Estado vacío
Texto exacto: **No hay pendientes críticos en este momento**

---

## 11. Columna derecha

La columna derecha debe contener contexto complementario y accesos operativos.

### Orden recomendado de bloques
1. Actividad reciente
2. Accesos rápidos
3. Estado de sucursal

### Contenedor de la columna derecha
**VBox**

---

## 12. Bloque: Actividad reciente

## Función
Dar sensación de sistema vivo y registrar movimientos recientes del negocio.

## Contenedor sugerido
**VBox**

## Encabezado
- Label: **Actividad reciente**
- Tooltip del título: **Últimos movimientos registrados en el sistema**

## Área de contenido
Usar una lista simple en **VBox** o una **ListView** corta. Para propaganda, un VBox con filas de actividad queda más controlable visualmente.

## Seeds recomendadas
1. **Se registró una nueva venta óptica para María León**
2. **La orden #LAB-198 fue marcada como lista**
3. **Se actualizó la receta de Carlos Mendoza**
4. **Se registró una entrega completada para Diana Vélez**
5. **Se ajustó el stock de montura MV-204**

## Tooltip del bloque
**Movimientos recientes realizados por usuarios en la jornada actual**

## Estado vacío
Texto exacto: **Todavía no hay actividad registrada hoy**

---

## 13. Bloque: Accesos rápidos

## Función
Permitir entrada rápida a operaciones frecuentes.

## Contenedor sugerido
**VBox** con un **GridPane** interno de botones.

## Encabezado
- Label: **Accesos rápidos**

## Botones oficiales
1. **Nueva cita**
2. **Nuevo cliente**
3. **Nueva venta óptica**
4. **Registrar entrega**
5. **Ver inventario**
6. **Abrir reportes**

## Tooltips exactos
- Nueva cita: **Registrar una nueva cita para la sucursal activa**
- Nuevo cliente: **Crear una nueva ficha de cliente o paciente**
- Nueva venta óptica: **Iniciar una nueva orden de venta óptica**
- Registrar entrega: **Registrar la entrega de un trabajo terminado**
- Ver inventario: **Abrir el módulo de inventario**
- Abrir reportes: **Consultar reportes operativos y comerciales**

## Regla visual
- los botones deben ser más funcionales que llamativos
- solo uno, como máximo, puede usar el estilo de acción principal
- el resto deben ser secundarios

## Acción principal recomendada
**Nueva venta óptica**

Motivo: es una acción que une atención, producto y operación comercial, y además vende bien la idea de un sistema completo.

---

## 14. Bloque: Estado de sucursal

## Función
Dar contexto multi-sucursal sin convertir Inicio en un módulo de sucursales.

## Contenedor sugerido
**VBox**

## Encabezado
- Label: **Resumen por sucursal**
- Tooltip: **Resumen operativo de la sucursal seleccionada**

## Contenido recomendado
Mostrar cuatro líneas informativas tipo resumen:
- Sucursal visible: **Matriz Centro**
- Citas activas: **18**
- Trabajos por entregar: **6**
- Productos críticos: **4**
- Caja del día: **$ 1,240.50**

## Acción secundaria opcional
Botón: **Cambiar sucursal**
Tooltip: **Cambiar la sede visible en el panel de inicio**

---

## 15. Bloque inferior opcional

Si el ancho y la altura lo permiten, puede existir un bloque inferior adicional con foco más visual.

## Nombre recomendado
**Productos con atención inmediata**

## Función
Mostrar dos o tres referencias de inventario en riesgo.

## Seeds recomendadas
- Montura MZ-201 | Bajo stock
- Lente blue cut 1.60 | Agotado
- Plaquetas de silicona estándar | Bajo stock

## Regla
Este bloque es opcional. Si visualmente recarga demasiado la pantalla, debe eliminarse.

---

## 16. Subvistas o submódulos internos del módulo Inicio

Aunque Inicio no tendrá submódulos pesados como otros módulos, sí conviene pensar sus bloques como sub-vistas lógicas reutilizables.

## Sub-vistas lógicas recomendadas
1. **InicioHeaderView** conceptual
2. **KpiResumenView** conceptual
3. **ProximasCitasView** conceptual
4. **PendientesAlertasView** conceptual
5. **ActividadRecienteView** conceptual
6. **AccesosRapidosView** conceptual
7. **ResumenSucursalView** conceptual

## Regla de arquitectura limpia
Cada bloque debe poder construirse de forma separada y luego insertarse dentro del módulo Inicio. La vista del módulo no debe contener una masa caótica de código visual.

## Regla de reescritura del área de trabajo
Cuando el usuario navega a otro módulo, la vista completa de Inicio se reemplaza en la región central principal del sistema. Dentro de Inicio, los bloques internos no reescriben toda la pantalla; solo presentan contenido fijo de dashboard.

---

## 17. Seed data oficial del módulo Inicio

Estas semillas no buscan exactitud de negocio absoluta. Buscan **credibilidad visual**.

## Personas de semilla
- Sofía Ramírez
- Juan Cedeño
- Carmen López
- Luis Andrade
- María León
- Ana Vera
- Carlos Mendoza
- Diana Vélez

## Profesionales o usuarios de semilla
- Dra. Salazar
- Dr. Paredes
- Técnico Rivera
- Asesor Molina

## Órdenes de semilla
- #LAB-198
- #LAB-203
- #OV-118
- #OV-124

## Productos de semilla
- Montura MZ-201
- Montura MV-204
- Lente antirreflejo 1.56
- Lente blue cut 1.60
- Plaquetas de silicona estándar

## Estados de semilla usados en este módulo
- Confirmada
- Pendiente
- Requiere revisión
- Listo
- Vencido
- Por cobrar
- Agotado
- Bajo stock

---

## 18. Textos oficiales del módulo Inicio

## Labels y títulos
- Inicio
- Resumen general de la operación diaria
- Sucursal activa
- Citas de hoy
- Ventas de hoy
- Órdenes pendientes
- Listas para entregar
- Por cobrar
- Stock crítico
- Próximas citas
- Pendientes y alertas
- Actividad reciente
- Accesos rápidos
- Resumen por sucursal
- Productos con atención inmediata

## Botones
- Ver agenda
- Ver detalle
- Nueva cita
- Nuevo cliente
- Nueva venta óptica
- Registrar entrega
- Ver inventario
- Abrir reportes
- Cambiar sucursal

## Empty states
- No hay citas programadas para esta jornada
- No hay pendientes críticos en este momento
- Todavía no hay actividad registrada hoy

---

## 19. Reglas visuales específicas del módulo Inicio

- No recargar con gráficos innecesarios
- No meter estadísticas decorativas solo por llenar espacio
- Priorizar indicadores y listas útiles
- Mantener bloques bien separados
- No usar redondez exagerada
- No usar colores intensos fuera de los estados
- Mantener el módulo estable, sobrio y operativo
- Hacer que la lectura del día sea más importante que la estética llamativa

---

## 20. Relación del módulo Inicio con otros módulos

Inicio debe dirigir o reflejar información de:
- Agenda
- Clientes
- Venta óptica
- Órdenes de laboratorio
- Inventario
- Caja
- Entregas
- Reportes
- Sucursales

No sustituye a esos módulos. Solo resume y encamina.

---

## 21. Cierre del módulo Inicio

Este módulo debe vender la idea de que la óptica trabaja con control diario real. Debe verse como un panel de jornada, no como una portada de sistema. Debe darle al cliente la impresión de que aquí se puede revisar en segundos la situación de citas, ventas, órdenes, pendientes, entregas y stock, todo dentro de una sola experiencia limpia y seria.

La complejidad correcta del módulo no está en tener veinte widgets, sino en presentar con claridad lo que una óptica realmente necesita vigilar cada día.