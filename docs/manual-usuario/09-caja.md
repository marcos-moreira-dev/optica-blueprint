# Caja

## ¿Para qué sirve este módulo?

El módulo **Caja** gestiona todos los cobros y movimientos de dinero de su óptica. Desde cobrar una venta al paciente hasta realizar el cierre de caja del día, pasando por el registro de pagos parciales, emisión de comprobantes y seguimiento de cuentas por cobrar.

## ¿Quién usa esta pantalla?

- **Cajeros:** para procesar cobros y registrar pagos.
- **Administradores:** para revisar los cobros del día, hacer el cierre de caja y monitorear cuentas pendientes.
- **Asesores de venta:** para consultar el estado de pago de una orden.

---

## Sub-vistas del módulo

El módulo tiene 6 sub-vistas accesibles desde los botones superiores:

| Sub-vista | Qué muestra |
|-----------|-------------|
| **Cobro de órdenes** | Ventas pendientes de cobro para procesar el pago. |
| **Abonos y saldos** | Pagos parciales y saldos pendientes de los pacientes. |
| **Comprobantes y facturación** | Recibos y facturas emitidas. |
| **Cierre de caja** | Arqueo de caja del día: efectivo, tarjetas, totales. |
| **Pagos pendientes** | Cuentas por cobrar de pacientes con pago diferido. |
| **Histórico** | Historial completo de todos los cobros realizados. |

---

## Cobro de órdenes

### Procesar el cobro de una venta

Cuando un asesor confirma una venta en el módulo **Venta Óptica**, la orden aparece aquí pendiente de cobro:

### Pasos para cobrar
1. Vaya a **Cobro de órdenes**
2. Busque la orden por número (ej: OV-00045), nombre del cliente o fecha
3. Seleccione la orden
4. Verifique el monto total a cobrar
5. Seleccione la forma de pago:
   - **Efectivo:** Ingrese el monto recibido; el sistema calcula el cambio
   - **Tarjeta de débito:** Registre la transacción
   - **Tarjeta de crédito:** Seleccione cuotas si aplica
   - **Transferencia:** Ingrese referencia de transferencia
   - **Mixto:** Combine varias formas de pago
6. Confirme el cobro
7. Se genera el comprobante automáticamente

### Resumen que muestra cada orden
| Campo | Descripción |
|-------|-------------|
| **Número de orden** | Referencia de la venta |
| **Cliente** | Nombre del paciente |
| **Total** | Monto total de la venta |
| **Forma de pago** | Cómo pagará |
| **Estado de pago** | Pagada, Parcialmente pagada, Pendiente |

---

## Abonos y saldos

### Registro de pagos parciales

Si un paciente no puede pagar el total de su compra de una vez:

1. Seleccione la orden con saldo pendiente
2. Haga clic en **Registrar abono**
3. Ingrese el monto del abono
4. Seleccione la forma de pago del abono
5. Confirme
6. El saldo pendiente se actualiza automáticamente

### Qué muestra esta vista
| Columna | Descripción |
|---------|-------------|
| **Orden** | Número de la orden |
| **Cliente** | Nombre del paciente |
| **Total de la venta** | Monto original |
| **Total abonado** | Cuánto ha pagado hasta ahora |
| **Saldo pendiente** | Cuánto debe aún |
| **Último abono** | Fecha y monto del último pago |
| **Estado** | Al día, Con saldo, Vencido |

> **Tip:** Establezca un plazo máximo para que los pacientes cancelen sus saldos pendientes. Puede configurarlo en **Configuración → Reglas de venta y caja**.

---

## Comprobantes y facturación

### Recibos emitidos

Cada cobro genera un comprobante automáticamente. Aquí puede:

- **Consultar un comprobante:** Busque por número de orden o fecha
- **Reimprimir un recibo:** Si el paciente extravió su comprobante
- **Ver el detalle:** Qué se cobró, cómo pagó, fecha y hora

### Datos del comprobante
- Número de comprobante
- Fecha y hora de emisión
- Cliente
- Detalle de productos cobrados
- Forma de pago
- Monto total
- Usuario que procesó el cobro
- Sucursal

---

## Cierre de caja

### Arqueo del día

Al finalizar la jornada, realice el cierre de caja para verificar que los montos coinciden:

### Pasos para el cierre de caja
1. Vaya a **Cierre de caja**
2. Seleccione la fecha del cierre (generalmente hoy)
3. El sistema muestra el resumen por forma de pago:
   - **Total en efectivo:** Cuánto debería haber en la gaveta
   - **Total en tarjetas:** Suma de transacciones con tarjeta
   - **Total en transferencias:** Suma de transferencias
4. Compare con el conteo físico:
   - Cuente el dinero real en la gaveta
   - Ingrese el monto contado
5. Si hay diferencia, el sistema la señala:
   - **Sin diferencia:** Todo cuadra perfecto
   - **Sobrante:** Hay más dinero del esperado
   - **Faltante:** Falta dinero
6. Confirme el cierre

### Resumen del cierre
| Concepto | Monto |
|----------|-------|
| Ventas del día | $XXX.XX |
| Abonos recibidos | $XXX.XX |
| **Total cobrado** | $XXX.XX |
| Efectivo esperado | $XXX.XX |
| Efectivo contado | $XXX.XX |
| Diferencia | $XX.XX |
| Ventas por tarjeta | $XXX.XX |
| Ventas por transferencia | $XXX.XX |

---

## Pagos pendientes

### Cuentas por cobrar

Muestra todas las órdenes con saldo pendiente de pago:

| Columna | Descripción |
|---------|-------------|
| **Orden** | Número de referencia |
| **Cliente** | Nombre y teléfono del paciente |
| **Total** | Monto total de la venta |
| **Saldo** | Cuánto debe aún |
| **Vencimiento** | Fecha límite para cancelar |
| **Días de atraso** | Si ya venció, cuántos días tiene de atraso |

### Acciones disponibles
- **Contactar al paciente:** Llame al número que se muestra
- **Registrar un abono:** Si el paciente viene a pagar
- **Notificar al paciente:** Enviar recordatorio de pago

---

## Histórico

### Historial completo de cobros

Muestra todos los cobros procesados, sin importar la fecha:

| Columna | Descripción |
|---------|-------------|
| **Fecha** | Cuándo se procesó el cobro |
| **Orden** | Número de referencia |
| **Cliente** | Nombre del paciente |
| **Monto** | Total cobrado |
| **Forma de pago** | Efectivo, tarjeta, etc. |
| **Usuario** | Quién procesó el cobro |
| **Sucursal** | Dónde se cobró |

### Para qué sirve el histórico
- **Auditoría:** Revisar cualquier cobro pasado.
- **Reportes:** Cuánto se cobró en un período determinado.
- **Verificación:** Confirmar si un paciente pagó o no.

---

## Casos de uso comunes

### "Un paciente viene a pagar su compra"
1. Vaya a **Cobro de órdenes**
2. Busque al paciente por nombre o número de orden
3. Verifique el monto
4. Seleccione la forma de pago
5. Procese el cobro y entregue el comprobante

### "El paciente quiere hacer un abono parcial"
1. Vaya a **Abonos y saldos**
2. Busque la orden del paciente
3. Haga clic en **Registrar abono**
4. Ingrese el monto del abono
5. Confirme

### "Necesito hacer el cierre de caja del día"
1. Vaya a **Cierre de caja**
2. Revise los totales por forma de pago
3. Cuente el dinero físico
4. Ingrese el monto contado
5. Si cuadra, confirme el cierre

### "Un paciente dice que ya pagó pero no aparece"
1. Vaya a **Histórico**
2. Busque al paciente por nombre o número de orden
3. Verifique si existe un cobro registrado
4. Si no hay cobro, pida al paciente el comprobante de pago

### "Quiero ver qué pacientes deben dinero"
1. Vaya a **Pagos pendientes**
2. La tabla muestra todos los saldos pendientes
3. Contacte a los pacientes con pagos vencidos

---

## Formas de pago aceptadas

| Forma | Descripción |
|-------|-------------|
| **Efectivo** | Dinero en efectivo. El sistema calcula cambio automático. |
| **Tarjeta de débito** | Débito directo de cuenta bancaria. |
| **Tarjeta de crédito** | Pago a cuotas. Seleccione número de cuotas. |
| **Transferencia** | Transferencia bancaria. Registre la referencia. |
| **Mixto** | Combinación de dos o más formas de pago. |

---

## Notas importantes
- Una venta no se considera completada hasta que se procesa el cobro en Caja.
- Los abonos parciales se registran individualmente con fecha y forma de pago.
- El cierre de caja es obligatorio al final de cada jornada.
- Las diferencias de caja (sobrantes o faltantes) quedan registradas en el histórico.
- Los comprobantes no pueden eliminarse; si hubo un error, se debe anular y generar uno nuevo.
- El módulo **Reportes** utiliza los datos de Caja para generar reportes financieros.
