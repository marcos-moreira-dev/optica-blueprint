# Proveedores

## ¿Para qué sirve este módulo?

El módulo **Proveedores** gestiona la información de todos sus proveedores de monturas, lentes, accesorios y servicios de laboratorio externo. Aquí usted puede consultar datos de contacto, historial de compras, desempeños y el catálogo de productos que cada proveedor le ofrece.

## ¿Quién usa esta pantalla?

- **Administradores y compradores:** para gestionar la relación con proveedores.
- **Recepcionistas:** para contactar al proveedor en caso de consultas rápidas.

---

## Sub-vistas del módulo

El módulo tiene 7 sub-vistas accesibles desde los botones superiores:

| Sub-vista | Qué muestra |
|-----------|-------------|
| **Directorio** | Lista completa de proveedores con datos de contacto. |
| **Perfil comercial** | Detalle de cada proveedor: condiciones comerciales, tiempos de entrega. |
| **Catálogo vinculado** | Productos que ofrece cada proveedor. |
| **Órdenes de compra** | Historial de pedidos realizados a cada proveedor. |
| **Recepciones e incidencias** | Productos recibidos y problemas detectados. |
| **Evaluación de desempeño** | Calificación del proveedor: puntualidad, calidad, precio. |
| **Histórico** | Toda la actividad con el proveedor. |

---

## Directorio de proveedores

### Lista de todos los proveedores

Cada proveedor en el sistema tiene:

| Campo | Descripción |
|-------|-------------|
| **Código** | Identificador (ej: PROV-001) |
| **Nombre** | Razón social de la empresa |
| **Tipo** | Distribuidor, Fabricante, Importador |
| **Categoría** | Monturas, Lentes, Accesorios, Laboratorio, Monturas y Lentes |
| **Contacto** | Persona de contacto principal |
| **Teléfono** | Número de contacto |
| **Correo** | Email para pedidos y consultas |
| **Ciudad** | Ubicación geográfica |
| **Sucursales que abastece** | Qué sucursales de su óptica le compran |
| **Estado** | Activo / Inactivo / Observado |

---

## Perfil comercial

### Condiciones comerciales del proveedor

Para cada proveedor puede gestionar:

| Campo | Descripción |
|-------|-------------|
| **Condiciones de pago** | Contado, 30 días, 60 días, etc. |
| **Descuento por volumen** | Si ofrece descuento por compras grandes |
| **Tiempo de entrega promedio** | Cuántos días tarda en entregar |
| **Pedido mínimo** | Monto mínimo para procesar un pedido |
| **Garantía** | Condiciones de garantía de productos |
| **Política de devoluciones** | Si acepta devoluciones y en qué condiciones |
| **Notas** | Observaciones generales |

---

## Catálogo vinculado

### Productos que ofrece el proveedor

Muestra qué productos de su inventario son suministrados por este proveedor:

- Monturas por marca y modelo
- Lentes por tipo y material
- Accesorios (estuches, soluciones, paños)

### Para qué sirve
- Saber a quién pedir cuando necesita reponer un producto
- Comparar qué proveedores ofrecen el mismo producto
- Negociar precios con base en alternativas disponibles

---

## Órdenes de compra al proveedor

### Historial de pedidos

Muestra todas las órdenes de compra realizadas a este proveedor:

| Columna | Descripción |
|---------|-------------|
| **Número de orden** | Referencia de compra |
| **Fecha** | Cuándo se realizó el pedido |
| **Productos** | Qué se pidió |
| **Monto total** | Valor del pedido |
| **Estado** | Pendiente, Enviada, Recibida, Parcialmente recibida |
| **Fecha estimada de entrega** | Cuándo debería llegar |

---

## Recepciones e incidencias

### Qué se recibió y qué problemas hubo

#### Recepciones
Cada vez que recibe un pedido de este proveedor:
- Productos que llegaron
- Cantidad recibida vs. cantidad pedida
- Fecha de recepción
- Usuario que recibió

#### Incidencias
Problemas detectados en las recepciones:
- Productos faltantes
- Productos dañados o defectuosos
- Productos diferentes a los pedidos
- Retrasos en la entrega

---

## Evaluación de desempeño

### Calificación del proveedor

El sistema evalúa automáticamente a cada proveedor con base en:

| Criterio | Qué mide |
|----------|----------|
| **Puntualidad** | Porcentaje de entregas a tiempo |
| **Calidad** | Porcentaje de productos sin defectos |
| **Precio** | Competitividad de precios vs. otros proveedores |
| **Servicio** | Capacidad de respuesta ante problemas |
| **Cumplimiento** | Porcentaje de pedidos completos (sin faltantes) |

### Calificación general
| Calificación | Significado |
|--------------|-------------|
| **⭐⭐⭐⭐⭐ Excelente** | Cumple o supera expectativas en todo |
| **⭐⭐⭐⭐ Bueno** | Cumple en la mayoría de criterios |
| **⭐⭐⭐ Aceptable** | Cumple lo básico pero con observaciones |
| **⭐⭐ Deficiente** | Problemas frecuentes en entregas o calidad |
| **⭐ Crítico** | Incumplimiento grave; considerar cambio de proveedor |

---

## Casos de uso comunes

### "Necesito reponer monturas Ray-Ban"
1. Vaya a **Directorio**
2. Filtre por categoría **Monturas**
3. Identifique qué proveedores ofrecen Ray-Ban
4. Compare precios y tiempos de entrega
5. Genere una orden de compra desde el módulo **Compras**

### "El último pedido llegó con productos dañados"
1. Vaya a **Recepciones e incidencias**
2. Registre la incidencia con fotos y descripción
3. Notifique al proveedor
4. El sistema ajusta la evaluación de calidad del proveedor

### "Quiero saber qué proveedor me conviene más"
1. Vaya a **Evaluación de desempeño**
2. Compare las calificaciones de los proveedores que ofrecen el mismo producto
3. Considere precio, puntualidad y calidad

### "Necesito el teléfono de mi proveedor de lentes"
1. Vaya a **Directorio**
2. Busque por nombre o filtre por categoría **Lentes**
3. El teléfono de contacto está en la ficha del proveedor

---

## Notas importantes
- Mantenga los datos de contacto actualizados para agilizar las compras.
- La evaluación de desempeño se calcula automáticamente con base en el histórico.
- Un proveedor con calificación "Crítico" debería ser reemplazado si existen alternativas.
- Las incidencias repetidas con un proveedor son señal de alerta.
- El módulo **Compras** utiliza la información de este módulo para generar órdenes de compra.
