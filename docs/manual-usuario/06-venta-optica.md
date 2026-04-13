# Venta Óptica

## ¿Para qué sirve este módulo?

El módulo **Venta Óptica** es el asistente paso a paso que guía todo el proceso de venta de lentes recetados al paciente. Desde la selección del cliente hasta la confirmación de la orden, pasando por la elección de montura, tipo de lentes, medidas y forma de pago. Es el corazón comercial de su óptica.

## ¿Quién usa esta pantalla?

- **Asesores de venta:** es su herramienta principal para completar una venta.
- **Optometristas:** puede revisar la receta aplicada durante la venta.
- **Caja:** consulta los detalles de la venta para procesar el cobro.

---

## Cómo funciona el wizard de venta

El proceso de venta se divide en **7 pasos**. Cada paso se presenta en pantalla con un indicador de progreso en la parte superior. Puede avanzar y retroceder entre pasos, pero debe completar cada uno antes de continuar.

| Paso | Nombre | Qué se hace |
|------|--------|-------------|
| 1 | **Cliente** | Selecciona o crea un cliente para la venta |
| 2 | **Receta** | Selecciona la receta oftalmológica a aplicar |
| 3 | **Montura** | Elige el marco/lente del catálogo |
| 4 | **Lentes** | Configura tipo de lente, materiales y tratamientos |
| 5 | **Medidas** | Registra distancia pupilar, altura de montaje, etc. |
| 6 | **Pago** | Define precio, descuentos, forma de pago y seguro |
| 7 | **Confirmación** | Revisa el resumen y confirma la orden |

---

## Paso 1: Cliente

### Buscar un cliente existente
1. Escriba el nombre, código o teléfono del cliente en el campo de búsqueda
2. Seleccione al cliente de los resultados
3. Se mostrará un resumen del cliente seleccionado

### Crear un cliente nuevo
Si el cliente no está registrado:
1. Haga clic en **Crear cliente nuevo**
2. Complete los datos obligatorios: nombre, documento, teléfono
3. Opcionalmente: email, dirección, ciudad
4. Haga clic en **Guardar**

> **Tip:** Siempre verifique que los datos de contacto estén actualizados. Así podrá notificar al paciente cuando sus lentes estén listos.

---

## Paso 2: Receta

### Seleccionar la receta
1. El sistema muestra automáticamente las recetas vigentes del cliente seleccionado
2. Seleccione la receta que corresponda (generalmente la más reciente)
3. Se mostrarán los valores OD y OI de la receta

### Qué datos de la receta se aplican
- **Esfera, Cilindro, Eje** de cada ojo
- **Adición** si aplica (lentes progresivos/bifocales)
- **Prismas** si aplica
- **Distancia pupilar** si está registrada

### ¿El cliente no tiene receta vigente?
Si la receta está vencida o no existe:
1. El sistema le advertirá que el cliente necesita un examen visual
2. Puede ofrecer agendar una cita desde el módulo **Agenda**
3. Opcionalmente, puede continuar la venta sin receta si el paciente compra lentes de sol o de lectura sin graduación

---

## Paso 3: Montura

### Elegir una montura del catálogo
1. Se muestra el catálogo de monturas disponibles en inventario
2. Puede filtrar por marca, material, color, precio
3. Seleccione la montura que el paciente desea
4. Se mostrará el precio de la montura

### Información que muestra cada montura
| Campo | Descripción |
|-------|-------------|
| **Código** | Identificador de la montura (ej: MON-00045) |
| **Marca** | Fabricante de la montura (ej: Ray-Ban, Oakley) |
| **Modelo** | Nombre o referencia del modelo |
| **Material** | Metal, Acetato, TR-90, Titanio, etc. |
| **Color** | Color del marco |
| **Precio** | Precio de venta |
| **Stock** | Unidades disponibles |

### Si la montura no está en stock
Si la montura elegida no está disponible en la sucursal:
1. El sistema le indicará que debe solicitarse al proveedor
2. La venta se registra igualmente como orden de compra
3. El estado de la orden reflejará que la montura está en pedido

---

## Paso 4: Lentes

### Configurar los lentes

#### Tipo de lente
| Tipo | Para qué sirve |
|------|----------------|
| **Monofocal** | Corrección simple: miopía, hipermetropía o astigmatismo |
| **Bifocal** | Dos zonas de enfoque: lejos y cerca |
| **Progresivo** | Enfoque gradual sin líneas visibles: lejos, intermedia, cerca |

#### Material del lente
| Material | Características |
|----------|-----------------|
| **CR-39 (Orgánico)** | Material estándar, buena relación calidad-precio |
| **Policarbonato** | Más delgado y resistente, ideal para niños y deportistas |
| **High-Index 1.67** | Ultra delgado para prescripciones altas |
| **High-Index 1.74** | El más delgado disponible |

#### Tratamientos adicionales
| Tratamiento | Para qué sirve |
|-------------|----------------|
| **Antirreflejo** | Reduce reflejos molestos, mejora estética y visión nocturna |
| **Filtro UV** | Protege los ojos de rayos ultravioleta |
| **Blue-cut** | Filtra luz azul de pantallas digitales |
| **Fotocromático** | El lente se oscurece con la luz solar |
| **Endurecido** | Mayor resistencia a rayones |

### Selección
1. Seleccione el tipo de lente para cada ojo (generalmente el mismo tipo para ambos)
2. Seleccione el material
3. Marque los tratamientos deseados
4. El sistema calcula el precio automáticamente

---

## Paso 5: Medidas

### Registro de medidas oftalmológicas

Para que los lentes se fabriquen correctamente, el laboratorio necesita estas medidas:

| Medida | Qué es | Cómo se toma |
|--------|--------|--------------|
| **Distancia pupilar (DP)** | Distancia entre centros de pupilas en mm | Con pupilómetro o regla pupilar |
| **Altura de centro óptico** | Altura desde el borde inferior del marco hasta el centro de la pupila | Con la montura puesta y regla |
| **Distancia vértice** | Distancia entre el ojo y la cara posterior del lente | Con vértice-distanciómetro (para prescripciones altas) |

> **Importante:** Medidas incorrectas = lentes mal centrados = paciente insatisfecho. Tome el tiempo necesario para medir con precisión.

### Cómo registrar las medidas
1. Coloque la montura elegida en el paciente
2. Tome las medidas con los instrumentos adecuados
3. Ingrese los valores en los campos correspondientes
4. Verifique que los valores sean razonables antes de continuar

---

## Paso 6: Pago

### Resumen de precios
El sistema muestra un desglose automático:

| Concepto | Precio |
|----------|--------|
| Montura | $XX.XX |
| Lentes (par) | $XX.XX |
| Tratamientos | $XX.XX |
| **Subtotal** | $XX.XX |
| Descuento | -$XX.XX |
| **Total** | $XX.XX |

### Aplicar descuento (opcional)
1. Ingrese el porcentaje o monto del descuento
2. El sistema recalcula el total automáticamente

### Forma de pago
Seleccione cómo pagará el paciente:
- **Efectivo:** Pago en efectivo
- **Tarjeta de débito:** Pago con tarjeta
- **Tarjeta de crédito:** Pago a cuotas o diferido
- **Transferencia:** Transferencia bancaria
- **Mixto:** Combinación de dos o más formas

### Seguro / Cobertura
Si el paciente tiene seguro visual:
1. Seleccione la cobertura del paciente
2. El sistema calcula cuánto cubre el seguro
3. El saldo restante es lo que paga el paciente

---

## Paso 7: Confirmación

### Resumen de la orden
Revise toda la información antes de confirmar:

- **Cliente:** Nombre y datos de contacto
- **Receta:** Valores OD y OI aplicados
- **Montura:** Marca, modelo, precio
- **Lentes:** Tipo, material, tratamientos
- **Medidas:** DP, altura, distancia vértice
- **Pago:** Total, forma de pago, descuentos
- **Entrega estimada:** Fecha estimada de entrega

### Confirmar la orden
1. Revise cuidadosamente todos los datos
2. Si todo está correcto, haga clic en **Confirmar orden**
3. El sistema genera un número de orden (ej: OV-00045)
4. Se imprime el comprobante de venta

### Qué pasa después
- La orden se envía automáticamente al módulo **Órdenes de Laboratorio**
- El inventario se descuenta (montura y lentes)
- El módulo **Caja** registra el cobro
- El paciente puede consultar el estado desde el módulo **Entregas**

---

## Casos de uso comunes

### "Un paciente quiere comprar lentes nuevos"
1. Vaya a **Venta Óptica**
2. Seleccione al cliente (o cree uno nuevo)
3. Seleccione su receta vigente
4. Ayúdele a elegir montura
5. Configure lentes y tratamientos
6. Tome las medidas
7. Procese el pago y confirme

### "El paciente quiere lentes de sol con graduación"
Siga el mismo proceso, pero en el Paso 4 seleccione:
- Tipo de lente: Monofocal o Progresivo según la receta
- Tratamiento: **Fotocromático** o **Tintado fijo** (si aplica)

### "El paciente tiene seguro visual"
En el Paso 6 (Pago), seleccione la cobertura del paciente. El sistema calcula automáticamente cuánto cubre el seguro y cuánto debe pagar el paciente.

### "Quiero ver el historial de ventas de un cliente"
1. Seleccione al cliente en el Paso 1
2. El sistema muestra un resumen de sus compras anteriores
3. Puede hacer clic en cualquier venta anterior para ver el detalle

---

## Notas importantes
- No puede confirmar una venta sin una receta vigente (excepto lentes sin graduación).
- Las medidas del Paso 5 son críticas; valores incorrectos resultan en lentes mal fabricados.
- Una vez confirmada la orden, no puede editarse directamente. Si necesita cambios, debe cancelar la orden y crear una nueva.
- El número de orden es la referencia que el paciente usará para consultar el estado de su pedido.
- La orden se envía automáticamente al laboratorio para fabricación.
