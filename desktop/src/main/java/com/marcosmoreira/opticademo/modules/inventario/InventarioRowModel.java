package com.marcosmoreira.opticademo.modules.inventario;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Row models for all 7 sub-views of the Inventario module.
 */
public final class InventarioRowModel {

    private InventarioRowModel() {
    }

    // ==================== Sub-view 1: Catalogo general ====================

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
