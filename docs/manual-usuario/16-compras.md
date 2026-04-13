# Compras

## ¿Para qué sirve este módulo?

El módulo **Compras** gestiona todo el proceso de abastecimiento de su óptica. Desde la solicitud interna de productos hasta la orden de compra al proveedor, la recepción de mercancía y el análisis de compras por proveedor y sucursal.

## ¿Quién usa esta pantalla?

- **Administradores y compradores:** para gestionar pedidos a proveedores.
- **Jefes de sucursal:** para solicitar productos que necesitan en su sede.

---

## Sub-vistas del módulo

El módulo tiene 7 sub-vistas accesibles desde los botones superiores:

| Sub-vista | Qué muestra |
|-----------|-------------|
| **Solicitudes de compra** | Pedidos internos de reposición de productos. |
| **Órdenes de compra** | Pedidos formales enviados a proveedores. |
| **Compras por proveedor** | Resumen de todo lo comprado a cada proveedor. |
| **Back-orders y pendientes** | Productos pedidos que aún no han llegado. |
| **Recepción vinculada** | Registro de productos recibidos contra la orden de compra. |
| **Compras por sucursal** | Qué ha comprado cada sucursal. |
| **Histórico** | Registro completo de todas las compras realizadas. |

---

## Solicitudes de compra

### Pedidos internos

Cuando un producto está por debajo del stock mínimo, se genera una solicitud de compra:

| Campo | Descripción |
|-------|-------------|
| **Producto** | Qué se necesita |
| **Cantidad solicitada** | Cuántas unidades se piden |
| **Sucursal solicitante** | Quién necesita el producto |
| **Motivo** | Stock crítico, reposición programada, pedido especial |
| **Estado** | Pendiente, Aprobada, Rechazada, Convertida en orden de compra |

### Flujo de una solicitud
1. Se detecta stock crítico o un jefe de sucursal solicita productos
2. Se genera la solicitud de compra
3. El administrador la **aprueba** o **rechaza**
4. Si se aprueba, se convierte en **Orden de compra** al proveedor
5. Se envía al proveedor y se da seguimiento

---

## Órdenes de compra

### Pedidos formales al proveedor

Una orden de compra es el pedido formal que se envía al proveedor:

| Campo | Descripción |
|-------|-------------|
| **Número de orden** | Referencia (ej: OC-00012) |
| **Proveedor** | A quién se le compra |
| **Fecha de emisión** | Cuándo se realizó el pedido |
| **Productos** | Qué se pide (código, descripción, cantidad) |
| **Monto total** | Valor del pedido |
| **Condiciones de pago** | Contado, 30 días, 60 días |
| **Fecha estimada de entrega** | Cuándo debería llegar |
| **Estado** | Enviada, Confirmada, En tránsito, Recibida, Parcialmente recibida |

### Crear una orden de compra
1. Vaya a **Órdenes de compra**
2. Haga clic en **Nueva orden de compra**
3. Seleccione el proveedor
4. Agregue los productos y cantidades
5. Verifique precios y condiciones
6. Confirme y envíe al proveedor

---

## Compras por proveedor

### Resumen de actividad por proveedor

Muestra cuánto le ha comprado a cada proveedor en el período seleccionado:

| Proveedor | Compras del mes | Compras del año | Última compra | Calificación |
|-----------|----------------|-----------------|---------------|--------------|
| Distr. Óptica Nacional | $5,200 | $42,000 | 10/04/2026 | ⭐⭐⭐⭐ |
| LensTech Ecuador | $3,800 | $38,500 | 08/04/2026 | ⭐⭐⭐⭐⭐ |
| OpticaSupply Corp. | $2,100 | $25,000 | 12/04/2026 | ⭐⭐⭐ |

### Para qué sirve
- Identificar sus proveedores principales
- Negociar mejores precios por volumen
- Diversificar proveedores si depende mucho de uno solo

---

## Back-orders y pendientes

### Productos pedidos que aún no llegan

Muestra las órdenes de compra que están en tránsito o pendientes de recepción:

| Columna | Descripción |
|---------|-------------|
| **Orden** | Número de referencia |
| **Proveedor** | A quién se le compró |
| **Productos** | Qué se espera recibir |
| **Fecha de pedido** | Cuándo se ordenó |
| **Fecha estimada** | Cuándo debería llegar |
| **Días de espera** | Cuántos días lleva esperando |
| **Estado** | En tránsito, Retrasada, Confirmada |

### Si un pedido se retrasa
- Contacte al proveedor para confirmar la nueva fecha
- Registre la observación en la orden
- Si el retraso es excesivo, considere pedir a otro proveedor

---

## Recepción vinculada

### Registrar lo que llegó

Cuando recibe un pedido del proveedor:

1. Vaya a **Recepción vinculada**
2. Seleccione la orden de compra correspondiente
3. Verifique los productos recibidos:
   - Marque los que llegaron correctamente
   - Indique los que faltan o están dañados
4. Confirme la recepción
5. El inventario se actualiza automáticamente

### Si hay diferencias
- **Faltantes:** Registre lo que falta; la orden queda "Parcialmente recibida"
- **Dañados:** Registre la incidencia y notifique al proveedor
- **Productos diferentes:** Registre la discrepancia y contacte al proveedor

---

## Compras por sucursal

### Qué ha comprado cada sede

Si su óptica tiene múltiples sucursales, puede ver el desglose de compras por sede:

| Sucursal | Compras del mes | Compras del año | Productos más solicitados |
|----------|----------------|-----------------|---------------------------|
| Matriz Centro | $4,500 | $48,000 | Monturas Ray-Ban, Lentes progresivos |
| Norte Mall | $3,200 | $35,000 | Monturas Oakley, Lentes fotocromáticos |
| Sur Express | $1,800 | $18,000 | Monturas Vogue, Lentes orgánicos |

### Para qué sirve
- Identificar qué sucursal consume más productos
- Planificar compras centralizadas
- Detectar si alguna sucursal está sobre-comprando

---

## Casos de uso comunes

### "Necesito reponer stock de monturas Ray-Ban"
1. El sistema generó automáticamente una **Solicitud de compra** por stock crítico
2. Vaya a **Solicitudes de compra** y apruebe la solicitud
3. Vaya a **Órdenes de compra** y cree la orden al proveedor que ofrece mejor precio
4. Envíe al proveedor

### "Quiero ver si mi pedido ya llegó"
1. Vaya a **Back-orders y pendientes**
2. Busque su orden de compra
3. Si dice "En tránsito" y ya pasó la fecha estimada, contacte al proveedor

### "Recibí un pedido pero faltan 3 monturas"
1. Vaya a **Recepción vinculada**
2. Seleccione la orden de compra
3. Marque las monturas que sí llegaron
4. Indique las 3 faltantes
5. La orden queda "Parcialmente recibida"
6. Contacte al proveedor por las faltantes

### "Quiero saber cuánto le compré a LensTech este mes"
1. Vaya a **Compras por proveedor**
2. Busque LensTech en la lista
3. El resumen muestra las compras del mes y del año

---

## Estados de las órdenes de compra

| Estado | Qué significa |
|--------|---------------|
| **Enviada** | El pedido fue enviado al proveedor |
| **Confirmada** | El proveedor confirmó que procesará el pedido |
| **En tránsito** | El pedido está en camino a su óptica |
| **Recibida** | Todo el pedido llegó correctamente |
| **Parcialmente recibida** | Llegó parte del pedido; faltan productos |

---

## Notas importantes
- Las solicitudes de compra pueden generarse automáticamente por stock crítico.
- Una orden de compra no puede modificarse después de ser enviada al proveedor.
- Si necesita cambiar algo, cancele la orden y cree una nueva.
- Siempre verifique los productos recibidos contra la orden antes de confirmar la recepción.
- El histórico de compras le ayuda a negociar mejores precios con los proveedores.
