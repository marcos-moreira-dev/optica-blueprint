# Lienzo del módulo Reportes para sistema de óptica en JavaFX

## 1. Identidad del módulo

### Nombre oficial del módulo en el menú
**Reportes**

### Texto visible del botón del sidebar
**Reportes**

### Tooltip del botón del sidebar
**Consultar indicadores, resúmenes y análisis operativos, comerciales y gerenciales**

### Ícono conceptual
Gráfico, documento analítico o panel de indicadores.

### Título visible en pantalla
**Reportes**

### Subtítulo visible en pantalla
**Lectura gerencial y operativa de ventas, inventario, atención, laboratorio, cobros y retención**

### Tipo de módulo
Módulo analítico y gerencial con sub-vistas internas especializadas.

### Objetivo del módulo
Permitir que la óptica interprete su operación mediante indicadores claros, reportes útiles y métricas accionables, transformando ventas, inventario, agenda, laboratorio, cobros y seguimiento en decisiones concretas.

---

## 2. Rol del módulo dentro de la arquitectura GUI

Reportes no debe sentirse como una galería de gráficos decorativos. Debe verse como el lugar donde la óptica entiende lo que está ocurriendo y toma decisiones.

### Regla arquitectónica principal
Cuando el usuario entre al módulo Reportes, la región central principal del sistema debe reemplazarse por una vista completa llamada conceptualmente:
**ReportesModuleView**

### Estructura interna limpia del módulo
La vista Reportes debe dividirse en:
- encabezado del módulo
- barra de filtros globales
- subnavegación interna del módulo
- región central intercambiable para sub-vistas
- panel lateral derecho con resumen persistente del KPI o reporte seleccionado

### Filosofía de implementación
El usuario debe poder recorrer resúmenes, métricas y análisis sin perder el contexto del período, la sucursal o el indicador que está observando.

### Regla de arquitectura limpia
Debe existir una región interna estable que se reescriba solo con el submódulo activo. Esa región se denomina conceptualmente:
**reportesContentHostPane**

Ese contenedor puede ser un **StackPane** o el centro de un **BorderPane** secundario.

### Sub-vistas que viven dentro de reportesContentHostPane
- Resumen ejecutivo
- Ventas y desempeño comercial
- Inventario y rotación
- Agenda y atención
- Laboratorio y cumplimiento
- Cobros y cartera
- Seguimiento y retención

---

## 3. Sub-vistas oficiales del módulo Reportes

## Sub-vistas definidas
1. **Resumen ejecutivo**
2. **Ventas y desempeño comercial**
3. **Inventario y rotación**
4. **Agenda y atención**
5. **Laboratorio y cumplimiento**
6. **Cobros y cartera**
7. **Seguimiento y retención**

## Orden de prioridad
1. Resumen ejecutivo
2. Ventas y desempeño comercial
3. Inventario y rotación
4. Agenda y atención
5. Laboratorio y cumplimiento
6. Cobros y cartera
7. Seguimiento y retención

## Vista por defecto al abrir el módulo
**Resumen ejecutivo**

Motivo: es la vista que mejor responde a la pregunta gerencial principal: cómo va la óptica en este momento.

---

## 4. Estructura visual general del módulo Reportes

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
- panel derecho: resumen persistente del KPI, bloque o reporte seleccionado

### Proporción recomendada
- región de trabajo: 70% a 74%
- panel derecho: 26% a 30%

### Regla de estabilidad visual
El panel derecho debe mantenerse visible durante casi toda la navegación para conservar el contexto del indicador, período y observación principal.

---

## 5. Encabezado del módulo Reportes

## Contenedor sugerido
**VBox** en la región top del BorderPane principal.

### Subestructura del encabezado
1. franja superior con título y acciones globales
2. franja media con filtros globales de reporte
3. franja inferior con subnavegación interna

---

## 6. Franja superior del encabezado

## Contenedor sugerido
**HBox**

### Zona izquierda
Un **VBox** con:
- Label principal: **Reportes**
- Label secundario: **Lectura gerencial y operativa de ventas, inventario, atención, laboratorio, cobros y retención**

### Zona derecha
Un **HBox** con:
- Button secundario: **Actualizar reportes**
- Button secundario: **Exportar reporte**
- Button principal: **Generar resumen**

## Controles exactos

### Label principal
- Control: **Label**
- Texto exacto: **Reportes**
- Tooltip: no necesita

### Label secundario
- Control: **Label**
- Texto exacto: **Lectura gerencial y operativa de ventas, inventario, atención, laboratorio, cobros y retención**
- Tooltip: no necesita

### Botón secundario 1
- Control: **Button**
- Texto exacto: **Actualizar reportes**
- Tooltip: **Recargar los indicadores y reportes visibles del módulo**
- Estilo: secundario

### Botón secundario 2
- Control: **Button**
- Texto exacto: **Exportar reporte**
- Tooltip: **Exportar el reporte o resumen actualmente visible**
- Estilo: secundario

### Botón principal
- Control: **Button**
- Texto exacto: **Generar resumen**
- Tooltip: **Construir el resumen general según período, sucursal y filtros actuales**
- Estilo: acción principal

---

## 7. Franja media: filtros globales

## Contenedor sugerido
**FlowPane**

## Función
Permitir cambiar el contexto temporal y operativo del análisis sin abandonar el módulo.

## Controles oficiales

### Filtro por período
- Control: **ComboBox**
- Label asociado: **Período**
- Ítems de semilla:
  - Hoy
  - Esta semana
  - Este mes
  - Últimos 3 meses
  - Año actual
  - Personalizado
- Valor inicial: **Este mes**
- Tooltip: **Seleccionar el período general del reporte**

### Filtro por sucursal
- Control: **ComboBox**
- Label asociado: **Sucursal**
- Ítems de semilla:
  - Todas
  - Matriz Centro
  - Norte Mall
- Valor inicial: **Todas**
- Tooltip: **Filtrar los indicadores por sucursal**

### Filtro por fecha inicial
- Control: **DatePicker**
- Label asociado: **Desde**
- Tooltip: **Seleccionar fecha inicial del análisis cuando el período sea personalizado**

### Filtro por fecha final
- Control: **DatePicker**
- Label asociado: **Hasta**
- Tooltip: **Seleccionar fecha final del análisis cuando el período sea personalizado**

### Filtro por categoría comercial
- Control: **ComboBox**
- Label asociado: **Categoría**
- Ítems de semilla:
  - Todas
  - Monturas
  - Lentes
  - Accesorios
  - Servicios
- Valor inicial: **Todas**
- Tooltip: **Filtrar el análisis por categoría principal cuando aplique**

### Opción rápida
- Control: **CheckBox**
- Texto exacto: **Solo datos críticos**
- Tooltip: **Mostrar solo indicadores o registros con atención prioritaria**

### Botón limpiar
- Control: **Button**
- Texto exacto: **Limpiar filtros**
- Tooltip: **Restablecer el período y todos los filtros del módulo**

---

## 8. Franja inferior: subnavegación interna del módulo

## Contenedor sugerido
**HBox** con **ToggleButton** dentro de un **ToggleGroup**

## Sub-vistas visibles en la barra
- Resumen ejecutivo
- Ventas y desempeño comercial
- Inventario y rotación
- Agenda y atención
- Laboratorio y cumplimiento
- Cobros y cartera
- Seguimiento y retención

## Tooltips exactos
- Resumen ejecutivo: **Ver el resumen general de la operación en el período seleccionado**
- Ventas y desempeño comercial: **Consultar ventas, ticket promedio y desempeño comercial**
- Inventario y rotación: **Consultar rotación, best sellers, stock lento y productos críticos**
- Agenda y atención: **Consultar utilización, no-shows y actividad de atención**
- Laboratorio y cumplimiento: **Consultar órdenes, tiempos, retrasos e incidencias del laboratorio**
- Cobros y cartera: **Consultar cobrado, pendiente, saldos y cartera**
- Seguimiento y retención: **Consultar recalls, no retirados y continuidad de clientes**

## Regla visual
La sub-vista activa debe quedar clara, pero sin competir con el contenido analítico.

---

## 9. Cuerpo principal del módulo Reportes

## Contenedor sugerido
**SplitPane** horizontal

### Panel izquierdo o principal
Sub-vista activa del módulo.

### Panel derecho
Resumen persistente del KPI, bloque o reporte seleccionado.

---

# 10. Panel derecho persistente: resumen del KPI o reporte

## Función
Mantener visible el contexto del indicador seleccionado y orientar la lectura gerencial.

## Contenedor sugerido
**ScrollPane** con un **VBox** interno.

## Bloques internos del panel derecho
1. Identidad del indicador
2. Valor actual
3. Contexto temporal
4. Lectura rápida
5. Acciones rápidas

## 10.1. Bloque: Identidad del indicador

### Campos visibles
- Indicador
- Categoría
- Sucursal

### Seeds
- **Indicador: Ventas del período**
- **Categoría: Comercial**
- **Sucursal: Todas**

## 10.2. Bloque: Valor actual

### Campos visibles
- Valor principal
- Variación breve

### Seeds
- **Valor principal: $ 8,420.00**
- **Variación breve: +8.4% frente al período anterior**

## 10.3. Bloque: Contexto temporal

### Campos visibles
- Período
- Última actualización

### Seeds
- **Período: Este mes**
- **Última actualización: 16/04/2026 10:45**

## 10.4. Bloque: Lectura rápida

### Campos visibles
- Observación gerencial
- Acción sugerida

### Seeds
- **Observación gerencial: El crecimiento se concentra en monturas y lentes de uso diario**
- **Acción sugerida: Revisar stock de productos de mayor rotación**

## 10.5. Bloque: Acciones rápidas

### Botones oficiales
- **Ver detalle**
- **Exportar reporte**
- **Abrir módulo relacionado**
- **Cambiar período**

### Tooltips exactos
- Ver detalle: **Abrir una vista más detallada del indicador seleccionado**
- Exportar reporte: **Exportar el indicador o reporte actualmente visible**
- Abrir módulo relacionado: **Abrir el módulo operativo relacionado con este indicador**
- Cambiar período: **Modificar rápidamente el período visible del reporte**

---

# 11. Sub-vista 1: Resumen ejecutivo

## Función
Mostrar una lectura corta y de alto valor sobre el estado general de la óptica.

## Contenedor principal sugerido
**VBox** con bloques KPI y tablas o resúmenes complementarios.

### Estructura interna
1. Franja de KPI principales
2. Bloque operativo resumido
3. Bloque de alertas gerenciales

## 11.1. Franja de KPI principales

### Contenedor sugerido
**GridPane** de dos filas y cuatro columnas

### KPI oficiales
- Ventas del período
- Ticket promedio
- Órdenes activas
- Trabajos retrasados
- Stock crítico
- Recalls pendientes
- Cobros pendientes
- Utilización de agenda

### Seeds oficiales
- Ventas del período: **$ 8,420.00**
- Ticket promedio: **$ 96.30**
- Órdenes activas: **24**
- Trabajos retrasados: **4**
- Stock crítico: **11**
- Recalls pendientes: **18**
- Cobros pendientes: **$ 412.00**
- Utilización de agenda: **78%**

### Tooltips ejemplo
- Ventas del período: **Total vendido en el período seleccionado**
- Ticket promedio: **Valor promedio por venta registrada**
- Órdenes activas: **Órdenes todavía abiertas o en proceso**
- Trabajos retrasados: **Trabajos que superaron su fecha promesa**
- Stock crítico: **Productos agotados o con bajo stock**
- Recalls pendientes: **Clientes que requieren revisión o recall**
- Cobros pendientes: **Monto total pendiente de cobro**
- Utilización de agenda: **Porcentaje estimado de uso de la agenda**

## 11.2. Bloque operativo resumido

### Contenedor sugerido
**TableView** corta o **GridPane**. Se recomienda **TableView** por claridad.

### Columnas oficiales
- Área
- Indicador
- Valor
- Estado

### Seeds
1. Ventas | Monturas | $ 3,200.00 | En meta
2. Inventario | Productos lentos | 9 referencias | Bajo observación
3. Laboratorio | Retrasos | 4 órdenes | Crítico
4. Agenda | No-shows | 6% | En meta

## 11.3. Bloque de alertas gerenciales

### Contenedor sugerido
**VBox**

### Seeds
- **El producto MZ-201 mantiene alta rotación y requiere revisión de stock**
- **Existen 2 casos vencidos en cobros pendientes**
- **El laboratorio externo presenta 4 trabajos retrasados**

### Estado vacío
**No hay alertas gerenciales destacadas en este período**

---

# 12. Sub-vista 2: Ventas y desempeño comercial

## Función
Analizar ingresos, categorías, ticket promedio y resultados comerciales del período.

## Contenedor principal sugerido
**BorderPane** con bloques KPI arriba y **TableView** central.

### Estructura interna
1. KPI comerciales
2. Resumen por categoría y asesor
3. Detalle tabular

## 12.1. KPI comerciales

### Campos visibles
- Ventas totales
- Ticket promedio
- Revenue por cliente
- Capture rate óptico

### Seeds
- Ventas totales: **$ 8,420.00**
- Ticket promedio: **$ 96.30**
- Revenue por cliente: **$ 88.50**
- Capture rate óptico: **41%**

## 12.2. Resumen tabular

### Columnas oficiales
- Categoría
- Ventas
- Unidades
- Participación
- Observación

### Seeds oficiales
1. Monturas | $ 3,200.00 | 44 | 38% | Mayor aporte del período
2. Lentes | $ 2,850.00 | 39 | 34% | Buena rotación
3. Accesorios | $ 620.00 | 71 | 7% | Venta complementaria baja
4. Servicios | $ 1,750.00 | 26 | 21% | Ticket medio estable

### Botones oficiales del submódulo
- **Ver ventas por asesor**
- **Exportar ventas**
- **Abrir caja**

### Tooltips
- Ver ventas por asesor: **Consultar el desempeño comercial por usuario o asesor**
- Exportar ventas: **Exportar el reporte comercial visible**
- Abrir caja: **Abrir el módulo Caja relacionado con el período visible**

### Estado vacío
**No hay ventas registradas para el período seleccionado**

---

# 13. Sub-vista 3: Inventario y rotación

## Función
Analizar rotación, productos lentos, best sellers y stock crítico.

## Contenedor principal sugerido
**BorderPane** con indicadores superiores y **TableView** central.

### Estructura interna
1. KPI de inventario
2. Tabla de rotación
3. Observaciones analíticas

## 13.1. KPI de inventario

### Campos visibles
- Best sellers
- Productos lentos
- Stock envejecido
- Stock crítico

### Seeds
- Best sellers: **12 referencias**
- Productos lentos: **9 referencias**
- Stock envejecido: **6 referencias**
- Stock crítico: **11 referencias**

## 13.2. Tabla de rotación

### Columnas oficiales
- Referencia
- Nombre
- Categoría
- Rotación
- Última salida
- Estado
- Observación

### Seeds oficiales
1. MZ-201 | Armazón clásico | Monturas | Media | 12/04/2026 | Bajo stock | Alta salida en Matriz Centro
2. UE-332 | Montura metálica | Monturas | Baja | 02/01/2026 | Agotado | Producto lento
3. LN-156-AR | Monofocal 1.56 | Lentes | Alta | 13/04/2026 | Disponible | Rotación constante
4. ACC-045 | Estuche rígido negro | Accesorios | Baja | 20/02/2026 | Disponible | Baja salida

### Botones oficiales del submódulo
- **Abrir inventario**
- **Exportar rotación**
- **Marcar revisión**

### Tooltips
- Abrir inventario: **Abrir el módulo Inventario relacionado con el producto seleccionado**
- Exportar rotación: **Exportar el análisis de rotación visible**
- Marcar revisión: **Marcar el producto para revisión comercial o de stock**

### Estado vacío
**No hay datos de inventario suficientes para el período seleccionado**

---

# 14. Sub-vista 4: Agenda y atención

## Función
Analizar utilización de agenda, no-shows, actividad de atención y conversión operativa.

## Contenedor principal sugerido
**BorderPane** con KPI superiores y **TableView** central.

### Estructura interna
1. KPI de agenda
2. Tabla de desempeño de atención
3. Observaciones operativas

## 14.1. KPI de agenda

### Campos visibles
- Citas registradas
- Utilización de agenda
- No-shows
- Conversión a venta

### Seeds
- Citas registradas: **186**
- Utilización de agenda: **78%**
- No-shows: **6%**
- Conversión a venta: **41%**

## 14.2. Tabla de desempeño

### Columnas oficiales
- Indicador
- Valor
- Meta
- Estado
- Observación

### Seeds oficiales
1. Citas efectivas | 174 | 170 | En meta | Buena ocupación
2. No-shows | 6% | 5% | Bajo observación | Ligero incremento
3. Conversión a venta | 41% | 40% | En meta | Conversión estable
4. Recall agendado | 12 | 15 | Bajo observación | Reforzar contacto

### Botones oficiales del submódulo
- **Abrir agenda**
- **Exportar atención**
- **Ver recalls**

### Tooltips
- Abrir agenda: **Abrir el módulo Agenda relacionado con el período seleccionado**
- Exportar atención: **Exportar el reporte de agenda y atención visible**
- Ver recalls: **Abrir los casos de recall vinculados a este reporte**

### Estado vacío
**No hay datos de agenda suficientes para el período seleccionado**

---

# 15. Sub-vista 5: Laboratorio y cumplimiento

## Función
Analizar tiempos, retrasos, incidencias y cumplimiento del flujo de laboratorio.

## Contenedor principal sugerido
**BorderPane** con KPI superiores y **TableView** central.

### Estructura interna
1. KPI de laboratorio
2. Tabla de cumplimiento
3. Observaciones de flujo

## 15.1. KPI de laboratorio

### Campos visibles
- Órdenes creadas
- Órdenes a tiempo
- Retrasos
- Retrabajos

### Seeds
- Órdenes creadas: **64**
- Órdenes a tiempo: **84%**
- Retrasos: **4**
- Retrabajos: **3**

## 15.2. Tabla de cumplimiento

### Columnas oficiales
- Indicador
- Valor
- Estado
- Observación

### Seeds oficiales
1. Tiempo promedio de entrega | 3.2 días | En meta | Flujo estable
2. Retrasos | 4 órdenes | Crítico | Revisar laboratorio externo
3. Incidencias | 3 casos | Bajo observación | Reforzar validación
4. Retrabajos | 3 casos | Bajo observación | Revisar montaje

### Botones oficiales del submódulo
- **Abrir laboratorio**
- **Exportar cumplimiento**
- **Ver incidencias**

### Tooltips
- Abrir laboratorio: **Abrir el módulo Órdenes de laboratorio relacionado**
- Exportar cumplimiento: **Exportar el reporte de cumplimiento visible**
- Ver incidencias: **Consultar incidencias vinculadas al período seleccionado**

### Estado vacío
**No hay datos de laboratorio suficientes para el período seleccionado**

---

# 16. Sub-vista 6: Cobros y cartera

## Función
Analizar cobros, saldos pendientes, aging simple y comportamiento de cartera.

## Contenedor principal sugerido
**BorderPane** con KPI superiores y **TableView** central.

### Estructura interna
1. KPI de cobro
2. Tabla de cartera
3. Observaciones financieras simples

## 16.1. KPI de cobro

### Campos visibles
- Cobrado del período
- Pendiente de cobro
- Órdenes con abono parcial
- Casos vencidos

### Seeds
- Cobrado del período: **$ 7,980.00**
- Pendiente de cobro: **$ 412.00**
- Órdenes con abono parcial: **5**
- Casos vencidos: **2**

## 16.2. Tabla de cartera

### Columnas oficiales
- Orden
- Cliente
- Saldo
- Último pago
- Estado
- Sucursal

### Seeds oficiales
1. OV-124 | Sofía Ramírez | $ 55.00 | 12/04/2026 | Abono parcial | Matriz Centro
2. OV-133 | María León | $ 153.00 | 05/04/2026 | Vencido | Norte Mall
3. OV-131 | Diana Vélez | $ 56.00 | 11/04/2026 | Abono parcial | Matriz Centro

### Botones oficiales del submódulo
- **Abrir caja**
- **Exportar cartera**
- **Ver seguimiento**

### Tooltips
- Abrir caja: **Abrir el módulo Caja relacionado con el caso seleccionado**
- Exportar cartera: **Exportar el reporte de cobros y cartera visible**
- Ver seguimiento: **Abrir el caso de seguimiento asociado al saldo pendiente**

### Estado vacío
**No hay datos de cobros suficientes para el período seleccionado**

---

# 17. Sub-vista 7: Seguimiento y retención

## Función
Analizar recalls, recordatorios, no retirados y continuidad de relación con clientes.

## Contenedor principal sugerido
**BorderPane** con KPI superiores y **TableView** central.

### Estructura interna
1. KPI de retención
2. Tabla de casos
3. Observaciones de continuidad

## 17.1. KPI de retención

### Campos visibles
- Recalls pendientes
- Recordatorios enviados
- No retirados
- Casos resueltos

### Seeds
- Recalls pendientes: **18**
- Recordatorios enviados: **34**
- No retirados: **7**
- Casos resueltos: **21**

## 17.2. Tabla de casos

### Columnas oficiales
- Cliente
- Tipo
- Estado
- Fecha objetivo
- Resultado
- Sucursal

### Seeds oficiales
1. Sofía Ramírez | Recall anual | Recall pendiente | 18/04/2026 | Pendiente | Matriz Centro
2. Diana Vélez | Trabajo listo sin retiro | No retirado | 16/04/2026 | Cliente notificado | Norte Mall
3. Carlos Mendoza | Saldo pendiente | En contacto | 17/04/2026 | Solicita pagar mañana | Matriz Centro
4. Ana Vera | Revisión por molestia inicial | Resuelto | 15/04/2026 | Ajuste realizado | Matriz Centro

### Botones oficiales del submódulo
- **Abrir seguimiento**
- **Exportar retención**
- **Abrir cliente**

### Tooltips
- Abrir seguimiento: **Abrir el módulo Seguimiento relacionado con el caso seleccionado**
- Exportar retención: **Exportar el reporte visible de continuidad y retención**
- Abrir cliente: **Abrir la ficha del cliente relacionado**

### Estado vacío
**No hay datos de seguimiento suficientes para el período seleccionado**

---

# 18. Formulario conceptual: Generar resumen

## Función
Permitir construir un resumen filtrado sin salir de la arquitectura del módulo.

## Contenedor sugerido
Vista interna o diálogo amplio controlado. Para esta maqueta conviene resolverlo dentro del mismo módulo.

## Estructura sugerida
**VBox** con bloques en **GridPane**.

### Campos mínimos
- Período
- Sucursal
- Categoría
- Nivel de detalle
- Exportación opcional

### Botones finales
- **Generar reporte**
- **Cancelar**

### Tooltips
- Generar reporte: **Construir el resumen analítico según los filtros definidos**
- Cancelar: **Cerrar la generación del reporte sin aplicar cambios**

---

# 19. Seed data oficial del módulo Reportes

## Sucursales
- Matriz Centro
- Norte Mall

## Categorías
- Monturas
- Lentes
- Accesorios
- Servicios

## Indicadores de semilla
- Ventas del período: $ 8,420.00
- Ticket promedio: $ 96.30
- Órdenes activas: 24
- Trabajos retrasados: 4
- Stock crítico: 11
- Recalls pendientes: 18
- Cobros pendientes: $ 412.00
- Utilización de agenda: 78%

## Referencias de apoyo
- MZ-201
- UE-332
- LN-156-AR
- OV-124
- OV-133
- SG-021
- ET-044

## Estados usados en el módulo
- En meta
- Bajo observación
- Crítico
- Pendiente
- Vencido
- No retirado
- Bajo stock
- Retrasada

---

# 20. Textos oficiales del módulo Reportes

## Títulos y labels principales
- Reportes
- Lectura gerencial y operativa de ventas, inventario, atención, laboratorio, cobros y retención
- Período
- Sucursal
- Desde
- Hasta
- Categoría
- Solo datos críticos
- Resumen ejecutivo
- Ventas y desempeño comercial
- Inventario y rotación
- Agenda y atención
- Laboratorio y cumplimiento
- Cobros y cartera
- Seguimiento y retención
- Indicador
- Valor principal
- Variación breve
- Última actualización
- Observación gerencial
- Acción sugerida
- Ventas del período
- Ticket promedio
- Órdenes activas
- Trabajos retrasados
- Stock crítico
- Recalls pendientes
- Cobros pendientes
- Utilización de agenda

## Botones oficiales
- Actualizar reportes
- Exportar reporte
- Generar resumen
- Limpiar filtros
- Ver detalle
- Abrir módulo relacionado
- Cambiar período
- Ver ventas por asesor
- Exportar ventas
- Abrir caja
- Abrir inventario
- Exportar rotación
- Marcar revisión
- Abrir agenda
- Exportar atención
- Ver recalls
- Abrir laboratorio
- Exportar cumplimiento
- Ver incidencias
- Exportar cartera
- Ver seguimiento
- Abrir seguimiento
- Exportar retención
- Abrir cliente
- Generar reporte
- Cancelar

## Empty states
- No hay alertas gerenciales destacadas en este período
- No hay ventas registradas para el período seleccionado
- No hay datos de inventario suficientes para el período seleccionado
- No hay datos de agenda suficientes para el período seleccionado
- No hay datos de laboratorio suficientes para el período seleccionado
- No hay datos de cobros suficientes para el período seleccionado
- No hay datos de seguimiento suficientes para el período seleccionado

---

# 21. Reglas visuales específicas del módulo Reportes

- el Resumen ejecutivo debe sentirse como la vista principal del módulo
- el panel derecho debe ayudar a interpretar, no solo repetir números
- Ventas y desempeño comercial debe verse orientado a decisiones, no solo a ingresos
- Inventario y rotación debe comunicar movimiento real, no solo existencias
- Agenda y atención debe enfocarse en uso y eficiencia
- Laboratorio y cumplimiento debe resaltar retrasos, tiempos y retrabajos
- Cobros y cartera debe verse financiero pero no contable
- Seguimiento y retención debe comunicar continuidad de relación y riesgo operativo
- no abusar de gráficas decorativas ni de bloques inflados
- la prioridad debe ser legibilidad, interpretación y acción sugerida

---

# 22. Relación del módulo Reportes con otros módulos

Reportes debe conectarse con:
- Caja, porque los cobros alimentan la lectura financiera
- Inventario, porque rotación y stock crítico salen de ese módulo
- Agenda, porque la utilización y el no-show dependen de la operación diaria
- Órdenes de laboratorio, porque cumplimiento, retrasos y retrabajos nacen allí
- Seguimiento, porque recalls, no retirados y continuidad también deben medirse
- Clientes, porque revenue por cliente y continuidad se apoyan en la ficha
- Inicio, porque el panel principal puede resumir parte de estos indicadores
- Sucursales, porque casi todos los reportes requieren lectura por sede

---

# 23. Cierre del módulo Reportes

Este módulo debe transmitir que la óptica no solo opera, sino que entiende su operación. Debe verse como una herramienta que convierte ventas, inventario, agenda, laboratorio, cobros y seguimiento en información útil para decidir. No se trata de llenar la pantalla de números, sino de mostrar los indicadores correctos con suficiente claridad para actuar.

La complejidad correcta del módulo está en que cada sub-vista resuelve una parte concreta de la lectura gerencial sin convertir la experiencia en una plataforma de BI pesada o excesiva.