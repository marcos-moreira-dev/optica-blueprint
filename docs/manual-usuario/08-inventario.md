# Inventario

## ¿Para qué sirve este módulo?

El módulo **Inventario** es el catálogo completo de todos los productos de su óptica: monturas, lentes, accesorios y soluciones de limpieza. Aquí usted puede consultar el stock disponible, identificar productos con stock crítico, registrar movimientos de entrada y salida, y gestionar las reposiciones de inventario.

## ¿Quién usa esta pantalla?

- **Asesores de venta:** para consultar qué monturas y lentes están disponibles antes de ofrecerlos.
- **Administradores:** para monitorear el nivel de inventario y decidir qué reponer.
- **Recepcionistas:** para informar al paciente si una montura está en stock o debe pedirse.

---

## Sub-vistas del módulo

El módulo tiene 7 sub-vistas accesibles desde los botones superiores:

| Sub-vista | Qué muestra |
|-----------|-------------|
| **Catálogo general** | Todos los productos del inventario con stock, precio y estado. |
| **Monturas** | Solo monturas/marcos organizados por marca, material y color. |
| **Lentes y variantes** | Lentes oftálmicos: orgánicos, policarbonato, high-index, etc. |
| **Movimientos** | Historial de entradas y salidas de cada producto. |
| **Stock crítico** | Productos por debajo del nivel mínimo que requieren reposición urgente. |
| **Recepción** | Registro de productos recibidos de proveedores. |
| **Análisis histórico** | Tendencia de consumo de productos por período. |

---

## Catálogo general

### Tabla de productos

La vista principal muestra todos los productos registrados. Cada fila incluye:

| Columna | Descripción |
|---------|-------------|
| **Código** | Identificador del producto (ej: MON-00045, LEN-00012) |
| **Nombre** | Descripción del producto |
| **Categoría** | Montura, Lente, Accesorio, Solución |
| **Marca** | Fabricante o marca comercial |
| **Precio de venta** | Precio al público |
| **Stock actual** | Unidades disponibles hoy |
| **Stock mínimo** | Nivel mínimo antes de alertar reposición |
| **Estado** | Disponible, Stock bajo, Agotado |

### Búsqueda y filtros
- **Buscar por nombre:** Escriba parte del nombre del producto
- **Buscar por código:** Ingrese el código exacto
- **Filtrar por categoría:** Solo monturas, solo lentes, etc.
- **Filtrar por marca:** Ver solo productos de una marca específica
- **Filtrar por estado:** Solo productos disponibles, solo con stock bajo, etc.

---

## Monturas

### Vista especializada de monturas

Esta vista muestra solo las monturas con campos específicos:

| Campo | Descripción |
|-------|-------------|
| **Marca** | Ray-Ban, Oakley, Vogue, etc. |
| **Modelo** | Nombre del modelo |
| **Material** | Metal, Acetato, TR-90, Titanio, Mixto |
| **Color** | Color del marco |
| **Género** | Hombre, Mujer, Unisex, Niño |
| **Precio** | Precio de venta |
| **Stock** | Unidades disponibles |
| **Sucursal** | En cuál sucursal está el stock |

### Para qué sirve esta vista
- **El asesor de venta** puede mostrar al paciente las monturas disponibles por marca o material.
- **El administrador** puede ver qué marcas tienen más variedad y cuáles necesitan ampliarse.

---

## Lentes y variantes

### Vista especializada de lentes

Muestra los tipos de lentes disponibles en inventario:

| Tipo de lente | Descripción |
|---------------|-------------|
| **Orgánico (CR-39)** | Lente estándar, buena relación calidad-precio |
| **Policarbonato** | Más delgado y resistente, ideal para niños |
| **High-Index 1.67** | Ultra delgado para prescripciones medias-altas |
| **High-Index 1.74** | El más delgado para prescripciones muy altas |
| **Bifocal** | Dos zonas de enfoque |
| **Progresivo** | Enfoque gradual sin líneas |

### Tratamientos en inventario
Cada tipo de lente puede tener tratamientos asociados:
- Antirreflejo
- Filtro UV
- Blue-cut (filtro luz azul)
- Fotocromático
- Endurecido

---

## Movimientos de inventario

### Historial de entradas y salidas

Cada vez que un producto entra o sale del inventario, se registra un movimiento:

| Campo | Descripción |
|-------|-------------|
| **Fecha** | Cuándo ocurrió el movimiento |
| **Producto** | Qué producto se movió |
| **Tipo** | Entrada (compra/recepción) o Salida (venta/ajuste) |
| **Cantidad** | Cuántas unidades entraron o salieron |
| **Referencia** | A qué está asociado (venta, orden de compra, etc.) |
| **Sucursal** | Dónde ocurrió el movimiento |
| **Usuario** | Quién registró el movimiento |

### Para qué sirve el historial
- **Auditoría:** Saber exactamente qué pasó con cada producto.
- **Detectar errores:** Si el stock no cuadra, revisar los movimientos.
- **Análisis de consumo:** Qué productos se venden más rápido.

---

## Stock crítico

### Alerta de reposición urgente

Esta vista muestra solo los productos cuyo stock actual está por debajo del nivel mínimo configurado.

| Columna | Qué indica |
|---------|------------|
| **Producto** | Qué producto está bajo |
| **Stock actual** | Cuántas unidades quedan |
| **Stock mínimo** | Cuántas unidades debería haber como mínimo |
| **Diferencia** | Cuántas unidades faltan para llegar al mínimo |
| **Última reposición** | Cuándo se reabasteció por última vez |
| **Proveedor habitual** | Quién le suministra este producto |

> **Acción recomendada:** Cuando un producto aparece en Stock Crítico, genere una orden de compra desde el módulo **Compras** para reponerlo.

---

## Recepción

### Registro de productos recibidos

Cuando llega un pedido de un proveedor:
1. Vaya a la vista **Recepción**
2. Seleccione la orden de compra correspondiente
3. Verifique que los productos recibidos coincidan con la orden
4. Confirme la recepción
5. El stock se actualiza automáticamente

### Si hay discrepancias
Si recibió menos productos de los esperados o productos diferentes:
1. Registre la diferencia en el campo **Observaciones**
2. La orden queda con estado **Recibido parcialmente**
3. Notifique al proveedor desde el módulo **Proveedores**

---

## Análisis histórico

### Tendencia de consumo

Esta vista muestra cómo ha variado el consumo de cada producto a lo largo del tiempo:

- **Productos más vendidos:** Ranking de los que más rotan
- **Productos estancados:** Los que casi no se mueven
- **Tendencia por categoría:** Si se venden más monturas de metal o de acetato este mes
- **Rotación de inventario:** Cuántas veces se renueva el stock por período

### Para qué sirve este análisis
- **Decidir compras:** No compre lo que no se vende.
- **Negociar con proveedores:** "Este modelo se vende mucho, necesito mejor precio."
- **Planificar promociones:** "Tenemos muchas monturas Vogue paradas; hagamos un 20% de descuento."

---

## Casos de uso comunes

### "Un paciente quiere ver monturas Ray-Ban de metal"
1. Vaya a **Monturas**
2. Filtre por **Marca → Ray-Ban** y **Material → Metal**
3. La tabla muestra las opciones disponibles con stock

### "Quiero saber qué productos necesito reponer urgentemente"
1. Vaya a **Stock crítico**
2. La tabla muestra todos los productos por debajo del mínimo
3. Genere órdenes de compra para reponer

### "El stock de un producto no cuadra"
1. Vaya a **Movimientos**
2. Filtre por el producto en cuestión
3. Revise cada entrada y salida para encontrar la discrepancia

### "Recibí un pedido de mi proveedor"
1. Vaya a **Recepción**
2. Seleccione la orden de compra
3. Verifique los productos recibidos
4. Confirme la recepción

### "Quiero saber qué monturas se vendieron más este mes"
1. Vaya a **Análisis histórico**
2. Seleccione el período (este mes)
3. Filtre por categoría **Monturas**
4. El ranking le muestra las más vendidas

---

## Estados de stock

| Estado | Color | Qué significa |
|--------|-------|---------------|
| **Disponible** | 🟢 Verde | Stock por encima del mínimo |
| **Stock bajo** | 🟡 Ámbar | Stock por debajo del mínimo, pero aún hay unidades |
| **Agotado** | 🔴 Rojo | No quedan unidades del producto |

---

## Notas importantes
- Cada venta en el módulo **Venta Óptica** descuenta automáticamente los productos del inventario.
- El stock es independiente por sucursal: cada sucursal maneja su propio inventario.
- Los productos no se eliminan del catálogo; se desactivan si ya no se comercializan.
- Configure niveles mínimos realistas: muy bajos = riesgo de quiebre de stock; muy altos = capital inmovilizado.
- La recepción de productos desde **Compras** actualiza automáticamente el stock.
