# Reportes

## ¿Para qué sirve este módulo?

El módulo **Reportes** genera vistas ejecutivas del estado de su óptica. Aquí usted puede analizar ventas, inventario, rendimiento del laboratorio, ocupación de la agenda, cobros y retención de pacientes. Es la herramienta para tomar decisiones informadas sobre el negocio.

## ¿Quién usa esta pantalla?

- **Administradores y dueños:** para evaluar el rendimiento general del negocio.
- **Optometristas:** para revisar estadísticas de atención y pacientes.
- **Jefes de área:** para monitorear el rendimiento de su departamento.

---

## Sub-vistas del módulo

El módulo tiene 7 sub-vistas de reportes:

| Sub-vista | Qué analiza |
|-----------|-------------|
| **Resumen ejecutivo** | Panorama general de la óptica con los KPIs más importantes. |
| **Rendimiento comercial** | Ventas por período, asesor, producto, sucursal. |
| **Inventario y rotación** | Qué productos se mueven, cuáles están estancados. |
| **Agenda y atención** | Ocupación de citas, cancelaciones, tiempos de espera. |
| **Rendimiento de laboratorio** | Tiempos de fabricación, incidencias, reelaboraciones. |
| **Cartera y cobros** | Cuentas por cobrar, efectividad de cobro, morosidad. |
| **Seguimiento y retención** | Recalls efectivos, pacientes perdidos, fidelización. |

---

## Resumen ejecutivo

### Panorama general del negocio

Esta vista muestra los indicadores clave del negocio en un solo golpe de vista:

| KPI | Qué mide |
|-----|----------|
| **Ventas del mes** | Cuántas ventas se hicieron y el monto total |
| **Ticket promedio** | Cuánto gasta en promedio cada paciente por compra |
| **Citas del mes** | Cuántas citas se atendieron |
| **Tasa de ocupación** | Qué porcentaje de los horarios disponibles están ocupados |
| **Órdenes entregadas** | Cuántos trabajos se entregaron al paciente |
| **Cuentas por cobrar** | Total de dinero pendiente de cobro |
| **Rotación de inventario** | Qué tan rápido se mueve el stock |

### Gráficos incluidos
- **Tendencia de ventas:** Comparación mes a mes
- **Top 5 monturas más vendidas:** Ranking de marcas/modelos
- **Distribución por tipo de pago:** Efectivo, tarjeta, transferencia

---

## Rendimiento comercial

### Análisis de ventas

#### Ventas por período
Seleccione un rango de fechas para ver:
- Número total de ventas
- Monto total facturado
- Comparación con el período anterior
- Tendencia (creciente, decreciente, estable)

#### Ventas por asesor
Ranking de asesores de venta por:
- Número de ventas cerradas
- Monto total facturado
- Ticket promedio por venta

#### Ventas por producto
- Monturas más vendidas por marca y categoría
- Tipos de lente más solicitados (monofocal, progresivo, etc.)
- Tratamientos más populares (antirreflejo, blue-cut, fotocromático)

#### Ventas por sucursal
Si tiene múltiples sucursales:
- Comparación de ventas entre sucursales
- Sucursal con mayor ticket promedio
- Sucursal con mayor crecimiento

---

## Inventario y rotación

### Qué se mueve y qué no

#### Productos de mayor rotación
Los 10 productos que más se venden en el período seleccionado.

#### Productos estancados
Productos que no se han vendido en los últimos X días.

#### Nivel de stock por categoría
- Monturas: stock total vs. stock mínimo
- Lentes: stock por tipo (orgánico, policarbonato, high-index)
- Accesorios: stock total

#### Valor del inventario
- Cuánto dinero tiene invertido en stock
- Distribución por categoría (cuánto en monturas, cuánto en lentes)
- Tendencia del valor de inventario

---

## Agenda y atención

### Rendimiento del consultorio

#### Ocupación de citas
- Porcentaje de horarios disponibles que fueron ocupados
- Citas atendidas vs. citas canceladas vs. pacientes que no asistieron
- Tendencia de ocupación por mes

#### Citas por tipo de atención
- Cuántos exámenes visuales nuevos
- Cuántos controles
- Cuántas urgencias

#### Citas por profesional
- Cuántas citas atendió cada optometrista
- Promedio de citas por día por profesional
- Tasa de cancelación por profesional

---

## Rendimiento de laboratorio

### Eficiencia de fabricación

#### Tiempos de producción
- Tiempo promedio desde ingreso al laboratorio hasta completado
- Comparación por tipo de lente (monofocal vs. progresivo vs. bifocal)
- Tendencia de tiempos por mes

#### Tasa de reelaboración
- Porcentaje de órdenes que requirieron reelaboración
- Causas más comunes de reelaboración
- Tendencia: ¿mejora o empeora?

#### Órdenes completadas
- Total de órdenes terminadas en el período
- Órdenes a tiempo vs. órdenes atrasadas
- Promedio de órdenes por día

---

## Cartera y cobros

### Salud financiera

#### Cuentas por cobrar
- Total adeudado por pacientes
- Distribución por antigüedad (0-30 días, 31-60, 61-90, +90)
- Tendencia: ¿sube o baja la cartera?

#### Efectividad de cobro
- Porcentaje de ventas cobradas al contado vs. a crédito
- Porcentaje de saldos recuperados
- Días promedio de mora

#### Formas de pago
- Distribución de ventas por forma de pago
- Tendencia de uso de tarjetas vs. efectivo
- Cuotas de crédito más populares

---

## Seguimiento y retención

### Fidelización de pacientes

#### Recalls efectivos
- Cuántos recalls resultaron en citas agendadas
- Tasa de conversión de recalls
- Recalls pendientes vs. recalls completados

#### Pacientes retenidos vs. perdidos
- Pacientes que volvieron en los últimos 12 meses
- Pacientes que no regresaron hace más de 12 meses
- Tasa de retención anual

#### Satisfacción implícita
- Porcentaje de entregas sin reclamos post-entrega
- Tasa de reelaboración como indicador de calidad
- Tendencias de seguimiento

---

## Generar un reporte

### Cómo acceder a los reportes

1. Vaya al módulo **Reportes**
2. Seleccione la sub-vista del tema que le interesa
3. Configure los filtros:
   - **Rango de fechas:** Desde/Hasta
   - **Sucursal:** Todas o una específica
   - **Profesional/Asesor:** Todos o uno específico
4. El reporte se genera automáticamente
5. Puede exportar los datos si la función está disponible

---

## Casos de uso comunes

### "Quiero saber cuánto vendimos este mes"
1. Vaya a **Resumen ejecutivo**
2. El KPI **Ventas del mes** muestra el monto total
3. Para más detalle, vaya a **Rendimiento comercial**

### "Necesito saber qué monturas se venden más"
1. Vaya a **Rendimiento comercial → Ventas por producto**
2. Filtre por categoría **Monturas**
3. El ranking muestra las más vendidas

### "Quiero ver si el laboratorio está mejorando"
1. Vaya a **Rendimiento de laboratorio**
2. Revise **Tiempo promedio de producción**: ¿baja?
3. Revise **Tasa de reelaboración**: ¿baja?
4. Si ambos bajan, el laboratorio está mejorando

### "Me preocupa la cantidad de dinero que deben los pacientes"
1. Vaya a **Cartera y cobros**
2. Revise **Cuentas por cobrar → Total adeudado**
3. Vea la distribución por antigüedad
4. Si hay mucho en +90 días, intensifique el cobro desde **Seguimiento**

### "Quiero saber si los pacientes vuelven"
1. Vaya a **Seguimiento y retención**
2. Revise **Tasa de retención anual**
3. Si es baja, considere campañas de fidelización

---

## Notas importantes
- Los reportes se generan en base a los datos almacenados en el sistema.
- El período seleccionado afecta los resultados; siempre verifique las fechas.
- Los datos reflejan la sucursal seleccionada en la barra superior.
- Para decisiones importantes, siempre cruce múltiples reportes (ej: ventas + cartera + satisfacción).
- Exporte los reportes periódicamente para tener un respaldo fuera del sistema.
