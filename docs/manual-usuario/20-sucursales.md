# Sucursales

## ¿Para qué sirve este módulo?

El módulo **Sucursales** gestiona toda la información operativa de cada sede de su óptica. Si su negocio tiene más de una ubicación, aquí usted puede ver y comparar el rendimiento de cada una, gestionar el personal asignado, controlar el inventario local y monitorear las operaciones de cada sede.

## ¿Quién usa esta pantalla?

- **Administradores generales:** para gestionar y comparar sucursales.
- **Dueños del negocio:** para monitorear el rendimiento de cada sede.
- **Administradores de sede:** para ver el estado de su sucursal específica.

---

## Sub-vistas del módulo

El módulo tiene 7 sub-vistas accesibles desde los botones superiores:

| Sub-vista | Qué muestra |
|-----------|-------------|
| **Directorio de sucursales** | Lista de todas las sedes con datos de contacto y estado. |
| **Perfil operativo** | Configuración de cada sucursal: horarios, servicios, responsables. |
| **Personal y permisos** | Quién trabaja en cada sede y qué roles tiene. |
| **Inventario local** | Stock de productos en esa sucursal específica. |
| **Agenda local** | Ocupación de citas de esa sucursal. |
| **Caja y rendimiento** | Ventas, cobros y métricas financieras de esa sede. |
| **Comparativo de sucursales** | Comparación lado a lado de todas las sedes. |

---

## Directorio de sucursales

### Lista de todas las sedes

Cada sucursal en el sistema tiene:

| Campo | Descripción |
|-------|-------------|
| **Código** | Identificador (ej: SUC-001) |
| **Nombre** | Nombre de la sucursal (ej: "Matriz Centro") |
| **Dirección** | Ubicación física completa |
| **Teléfono** | Número de contacto |
| **Correo** | Email de la sucursal |
| **Responsable** | Quién está a cargo |
| **Ciudad** | Ubicación geográfica |
| **Estado** | Activo / Observado / Cerrado |
| **Servicios habilitados** | Caja, Inventario, Entregas, Agenda |

---

## Perfil operativo

### Configuración detallada de la sucursal

Para cada sede puede consultar y editar:

#### Datos operativos
| Campo | Descripción |
|-------|-------------|
| **Horario operativo** | Horario de atención al público (ej: "Lun-Sab 09:00-19:00") |
| **Día de cierre** | Si tiene un día de cierre semanal |
| **Caja habilitada** | Si procesa cobros en esta sucursal |
| **Inventario propio** | Si maneja stock independiente |
| **Entregas habilitadas** | Si entrega trabajos al paciente desde aquí |
| **Agenda habilitada** | Si programa citas en esta sede |

#### Equipo de la sucursal
- **Responsable principal:** Quién está a cargo
- **Optometristas asignados:** Profesionales que atienden
- **Asesores de venta:** Personal comercial
- **Cajeros:** Personal de caja

---

## Personal y permisos

### Quién trabaja en cada sede

Muestra todos los usuarios asignados a esta sucursal:

| Usuario | Rol | Estado | Último acceso |
|---------|-----|--------|---------------|
| Marcos Moreira | Administrador General | Activo | Hoy |
| Laura Escobar | Cajero | Activo | Hoy |
| Carlos Zambrano | Asesor de Ventas | Activo | Ayer |
| Ricardo Salazar | Técnico Óptico | Activo | Hace 3 días |

### Cambiar la asignación de personal
1. Vaya a **Personal y permisos**
2. Seleccione al usuario
3. Cambie su rol o sucursal asignada
4. Guarde

---

## Inventario local

### Stock de esta sucursal

Si la sucursal maneja inventario propio, aquí puede ver:

- **Total de productos en stock**
- **Productos con stock crítico**
- **Top 5 productos más abundantes**
- **Top 5 productos más escasos**

### Para qué sirve
- El administrador de la sucursal sabe qué tiene disponible
- El asesor de venta puede confirmar stock antes de ofrecer un producto
- El administrador general puede comparar stock entre sucursales y transferir si es necesario

---

## Agenda local

### Ocupación de citas de esta sucursal

Muestra la actividad de citas de la sede:

| Métrica | Valor |
|---------|-------|
| **Citas hoy** | Cuántas citas hay programadas hoy |
| **Citas de la semana** | Total de citas de esta semana |
| **Tasa de ocupación** | Porcentaje de horarios disponibles que están ocupados |
| **Cancelaciones del mes** | Cuántas citas se cancelaron este mes |
| **No-shows del mes** | Cuántos pacientes no asistieron sin cancelar |

### Para qué sirve
- El responsable de la sucursal puede evaluar la demanda de citas
- Si la ocupación es baja, puede considerar promociones locales
- Si es alta, puede solicitar más horarios o más profesionales

---

## Caja y rendimiento

### Métricas financieras de la sucursal

#### Ventas
| Métrica | Valor |
|---------|-------|
| **Ventas del mes** | Cuántas ventas se hicieron este mes |
| **Monto facturado** | Total facturado en el mes |
| **Ticket promedio** | Cuánto gasta en promedio cada paciente |
| **Comparación con mes anterior** | Si subió o bajó vs. el mes pasado |

#### Cobros
| Métrica | Valor |
|---------|-------|
| **Cobros del mes** | Total cobrado |
| **Cuentas por cobrar** | Saldo pendiente de cobro |
| **Forma de pago más usada** | Efectivo, tarjeta, etc. |

#### Rendimiento general
| Indicador | Estado |
|-----------|--------|
| **Ventas vs. meta** | Si está por encima o debajo del objetivo |
| **Ocupación de agenda** | Si la agenda está llena o tiene huecos |
| **Stock general** | Si el inventario está en niveles saludables |

---

## Comparativo de sucursales

### Comparar todas las sedes lado a lado

Esta vista muestra las principales métricas de todas las sucursales en una sola tabla:

| Métrica | Matriz Centro | Norte Mall | Sur Express |
|---------|--------------|------------|-------------|
| **Ventas del mes** | $12,500 | $9,800 | $6,200 |
| **Citas del mes** | 85 | 62 | 40 |
| **Ticket promedio** | $147 | $158 | $155 |
| **Ocupación agenda** | 78% | 65% | 55% |
| **Stock crítico** | 3 productos | 5 productos | 8 productos |
| **Cuentas por cobrar** | $1,200 | $800 | $650 |

### Para qué sirve el comparativo
- Identificar la sucursal más rentable
- Detectar sucursales con problemas (stock crítico alto, ocupación baja)
- Tomar decisiones de redistribución de recursos
- Establecer metas por sucursal

---

## Casos de uso comunes

### "Quiero ver cómo va la sucursal Norte Mall este mes"
1. Vaya al módulo **Sucursales**
2. Seleccione **Norte Mall** en el directorio
3. Revise **Caja y rendimiento** para las ventas
4. Revise **Agenda local** para la ocupación de citas

### "Necesito comparar las ventas de todas las sucursales"
1. Vaya a **Comparativo de sucursales**
2. La tabla muestra las ventas de cada sede
3. Identifique cuál tiene mejor rendimiento

### "La sucursal Sur Express tiene mucho stock crítico"
1. Vaya a **Inventario local** de Sur Express
2. Identifique los productos críticos
3. Genere una orden de compra desde **Compras**
4. O transfiera stock de otra sucursal si es posible

### "Quiero agregar una nueva sucursal"
1. Vaya a **Configuración → Sucursales y operaciones**
2. Haga clic en **Nueva sucursal**
3. Complete todos los datos
4. Asigne personal y configure servicios habilitados
5. Guarde

### "La ocupación de agenda de Matriz Centro está muy baja"
1. Vaya a **Agenda local** de Matriz Centro
2. Revise la tasa de ocupación
3. Si es baja (menos de 50%), considere:
   - Promociones locales para atraer pacientes
   - Reducir horarios disponibles temporalmente
   - Capacitar al personal para mejorar la conversión

---

## Notas importantes
- Cada sucursal opera con su propio inventario, agenda y caja.
- Los datos de clientes y recetas son compartidos entre todas las sucursales.
- Un usuario solo puede acceder a la sucursal que tiene asignada (excepto administradores generales).
- El comparativo de sucursales es una herramienta clave para la toma de decisiones gerenciales.
- Para agregar o eliminar sucursales, vaya a **Configuración → Sucursales y operaciones**.
