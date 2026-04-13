package com.marcosmoreira.opticademo.modules.inventario;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Modelos de fila para las 7 sub-vistas del modulo Inventario.
 * <p>
 * Estos registros alimentan los {@code TableView} del modulo: catalogo general de
 * productos, monturas, lentes y variantes, movimientos de inventario, stock critico,
 * recepcion y abastecimiento, e historico con analisis de rotacion. La fachada crea
 * estas instancias a partir de las entidades {@code Producto}, {@code Movimiento}
 * y {@code Stock} del dominio.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see com.marcosmoreira.opticademo.modules.inventario.InventarioFacade
 */
public final class InventarioRowModel {

    private InventarioRowModel() {
    }

    // ==================== Sub-view 1: Catalogo general ====================

    /**
     * Modelo de fila para el catalogo general de productos.
     * <p>
     * Vista principal que lista todos los productos del inventario con su informacion
     * basica. El campo {@code estado} indica disponibilidad: "Disponible", "Agotado",
     * "Descontinuado".
     * </p>
     *
     * @param referencia codigo SKU del producto (columna "Referencia")
     * @param nombre     nombre comercial (columna "Nombre")
     * @param categoria  categoria: "Montura", "Lente monofocal", "Lente progresivo" (columna "Categoria")
     * @param marca      marca del fabricante (columna "Marca")
     * @param sucursal   ubicacion en inventario (columna "Sucursal")
     * @param stock      unidades actuales (columna "Stock")
     * @param precio     precio de venta (columna "Precio")
     * @param estado     estado del producto (columna "Estado")
     */
    public record CatalogoRow(
            String referencia,
            String nombre,
            String categoria,
            String marca,
            String sucursal,
            String stock,
            String precio,
            String estado
    ) {
        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty nombreProperty() {
            return new SimpleStringProperty(nombre);
        }

        public StringProperty categoriaProperty() {
            return new SimpleStringProperty(categoria);
        }

        public StringProperty marcaProperty() {
            return new SimpleStringProperty(marca);
        }

        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }

        public StringProperty stockProperty() {
            return new SimpleStringProperty(stock);
        }

        public StringProperty precioProperty() {
            return new SimpleStringProperty(precio);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }
    }

    // ==================== Sub-view 2: Monturas ====================

    /**
     * Modelo de fila para el catalogo especifico de monturas.
     * <p>
     * Similar al catalogo general pero con campos especificos de armazones:
     * material del armazon y color.
     * </p>
     *
     * @param referencia codigo SKU (columna "Referencia")
     * @param nombre     nombre del modelo (columna "Nombre")
     * @param marca      marca fabricante (columna "Marca")
     * @param material   material: "Acetato", "Metal", "Titanio" (columna "Material")
     * @param color      color del armazon (columna "Color")
     * @param precio     precio de venta (columna "Precio")
     * @param stock      unidades disponibles (columna "Stock")
     * @param sucursal   ubicacion (columna "Sucursal")
     */
    public record MonturaRow(
            String referencia,
            String nombre,
            String marca,
            String material,
            String color,
            String precio,
            String stock,
            String sucursal
    ) {
        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty nombreProperty() {
            return new SimpleStringProperty(nombre);
        }

        public StringProperty marcaProperty() {
            return new SimpleStringProperty(marca);
        }

        public StringProperty materialProperty() {
            return new SimpleStringProperty(material);
        }

        public StringProperty colorProperty() {
            return new SimpleStringProperty(color);
        }

        public StringProperty precioProperty() {
            return new SimpleStringProperty(precio);
        }

        public StringProperty stockProperty() {
            return new SimpleStringProperty(stock);
        }

        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }
    }

    // ==================== Sub-view 3: Lentes y variantes ====================

    /**
     * Modelo de fila para el catalogo de lentes oftalmicos.
     * <p>
     * Muestra las variantes de lentes disponibles con sus caracteristicas opticas:
     * tipo (monofocal, bifocal, progresivo), indice de refraccion y tratamientos
     * antirreflejantes. El campo {@code estado} indica disponibilidad.
     * </p>
     *
     * @param referencia   codigo SKU (columna "Referencia")
     * @param nombre       nombre comercial (columna "Nombre")
     * @param tipo         tipo de lente (columna "Tipo")
     * @param indice       indice de refraccion: 1.50, 1.60, 1.67 (columna "Indice")
     * @param tratamiento  tratamiento aplicado (columna "Tratamiento")
     * @param stock        unidades disponibles (columna "Stock")
     * @param precioBase   precio base del lente (columna "Precio Base")
     * @param estado       estado del producto (columna "Estado")
     */
    public record LenteRow(
            String referencia,
            String nombre,
            String tipo,
            String indice,
            String tratamiento,
            String stock,
            String precioBase,
            String estado
    ) {
        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty nombreProperty() {
            return new SimpleStringProperty(nombre);
        }

        public StringProperty tipoProperty() {
            return new SimpleStringProperty(tipo);
        }

        public StringProperty indiceProperty() {
            return new SimpleStringProperty(indice);
        }

        public StringProperty tratamientoProperty() {
            return new SimpleStringProperty(tratamiento);
        }

        public StringProperty stockProperty() {
            return new SimpleStringProperty(stock);
        }

        public StringProperty precioBaseProperty() {
            return new SimpleStringProperty(precioBase);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }
    }

    // ==================== Sub-view 4: Movimientos de inventario ====================

    /**
     * Modelo de fila para el registro de movimientos de inventario.
     * <p>
     * Cada registro representa una entrada, salida o ajuste de stock. El campo
     * {@code tipo} clasifica el movimiento: "Entrada", "Venta", "Ajuste", "Devolucion".
     * </p>
     *
     * @param fecha       fecha del movimiento (columna "Fecha")
     * @param tipo        tipo de movimiento (columna "Tipo")
     * @param referencia  documento asociado (columna "Referencia")
     * @param producto    nombre del producto afectado (columna "Producto")
     * @param cantidad    cantidad movida (positiva o negativa) (columna "Cantidad")
     * @param sucursal    sede donde se realizo (columna "Sucursal")
     * @param responsable usuario que ejecuto el movimiento (columna "Responsable")
     */
    public record MovimientoRow(
            String fecha,
            String tipo,
            String referencia,
            String producto,
            String cantidad,
            String sucursal,
            String responsable
    ) {
        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty tipoProperty() {
            return new SimpleStringProperty(tipo);
        }

        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty productoProperty() {
            return new SimpleStringProperty(producto);
        }

        public StringProperty cantidadProperty() {
            return new SimpleStringProperty(cantidad);
        }

        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }

        public StringProperty responsableProperty() {
            return new SimpleStringProperty(responsable);
        }
    }

    // ==================== Sub-view 5: Reposicion y stock critico ====================

    /**
     * Modelo de fila para la tabla de stock critico.
     * <p>
     * Muestra los productos cuyo stock actual esta por debajo del minimo configurado.
     * El campo {@code estado} resalta la situacion: "Critico" (stock &lt; minimo),
     * "Bajo" (stock cercano al minimo), "Normal".
     * </p>
     *
     * @param referencia    codigo SKU (columna "Referencia")
     * @param nombre        nombre del producto (columna "Nombre")
     * @param categoria     categoria del producto (columna "Categoria")
     * @param stockActual   unidades actuales (columna "Stock Actual")
     * @param stockMinimo   umbral minimo configurado (columna "Stock Minimo")
     * @param estado        nivel de alerta del stock (columna "Estado")
     * @param proveedor     proveedor sugerido para reposicion (columna "Proveedor")
     * @param sucursal      ubicacion afectada (columna "Sucursal")
     */
    public record CriticoRow(
            String referencia,
            String nombre,
            String categoria,
            String stockActual,
            String stockMinimo,
            String estado,
            String proveedor,
            String sucursal
    ) {
        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty nombreProperty() {
            return new SimpleStringProperty(nombre);
        }

        public StringProperty categoriaProperty() {
            return new SimpleStringProperty(categoria);
        }

        public StringProperty stockActualProperty() {
            return new SimpleStringProperty(stockActual);
        }

        public StringProperty stockMinimoProperty() {
            return new SimpleStringProperty(stockMinimo);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty proveedorProperty() {
            return new SimpleStringProperty(proveedor);
        }

        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }
    }

    // ==================== Sub-view 6: Recepcion y abastecimiento ====================

    /**
     * Modelo de fila para la tabla de recepciones pendientes.
     * <p>
     * Muestra las recepciones de mercaderia asociadas a ordenes de compra.
     * </p>
     *
     * @param referencia  identificador de la recepcion (columna "Referencia")
     * @param fecha       fecha de recepcion (columna "Fecha")
     * @param proveedor   proveedor de la mercaderia (columna "Proveedor")
     * @param sucursal    sede destino (columna "Sucursal")
     * @param estado      estado: "Recibido", "Parcial", "Pendiente" (columna "Estado")
     * @param responsable persona que recibio (columna "Responsable")
     */
    public record RecepcionRow(
            String referencia,
            String fecha,
            String proveedor,
            String sucursal,
            String estado,
            String responsable
    ) {
        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty proveedorProperty() {
            return new SimpleStringProperty(proveedor);
        }

        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty responsableProperty() {
            return new SimpleStringProperty(responsable);
        }
    }

    // ==================== Sub-view 7: Historico y analisis de stock ====================

    /**
     * Modelo de fila para el historico y analisis de rotacion de stock.
     * <p>
     * Presenta datos analiticos de cada producto: velocidad de rotacion, ultima
     * fecha de salida y estado actual. El campo {@code rotacion} indica cuantas
     * veces se renovo el stock en el periodo analizado.
     * </p>
     *
     * @param referencia    codigo SKU (columna "Referencia")
     * @param nombre        nombre del producto (columna "Nombre")
     * @param categoria     categoria (columna "Categoria")
     * @param rotacion      indice de rotacion en el periodo (columna "Rotacion")
     * @param ultimaSalida  fecha de la ultima venta (columna "Ultima Salida")
     * @param estadoActual  estado vigente del producto (columna "Estado Actual")
     * @param observacion   nota analitica sobre el producto (columna "Observacion")
     */
    public record AnalisisRow(
            String referencia,
            String nombre,
            String categoria,
            String rotacion,
            String ultimaSalida,
            String estadoActual,
            String observacion
    ) {
        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty nombreProperty() {
            return new SimpleStringProperty(nombre);
        }

        public StringProperty categoriaProperty() {
            return new SimpleStringProperty(categoria);
        }

        public StringProperty rotacionProperty() {
            return new SimpleStringProperty(rotacion);
        }

        public StringProperty ultimaSalidaProperty() {
            return new SimpleStringProperty(ultimaSalida);
        }

        public StringProperty estadoActualProperty() {
            return new SimpleStringProperty(estadoActual);
        }

        public StringProperty observacionProperty() {
            return new SimpleStringProperty(observacion);
        }
    }
}
