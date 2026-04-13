package com.marcosmoreira.opticademo.modules.ventaoptica;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Row models for the Venta Optica module across all 7 wizard stages.
 */
public class VentaOpticaRowModel {

    private VentaOpticaRowModel() {
    }

    // ---- Stage 1: Client search row ----
    public record ClientSearchRow(
            String nombre,
            String codigo,
            String telefono,
            String ultimaVisita,
            String estado,
            String sucursal
    ) {
        public StringProperty nombreProperty() {
            return new SimpleStringProperty(nombre);
        }

        public StringProperty codigoProperty() {
            return new SimpleStringProperty(codigo);
        }

        public StringProperty telefonoProperty() {
            return new SimpleStringProperty(telefono);
        }

        public StringProperty ultimaVisitaProperty() {
            return new SimpleStringProperty(ultimaVisita);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }
    }

    // ---- Stage 2: Recipe selection row ----
    public record RecipeRow(
            String fecha,
            String estado,
            String profesional,
            String odResumen,
            String oiResumen,
            String pd
    ) {
        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty profesionalProperty() {
            return new SimpleStringProperty(profesional);
        }

        public StringProperty odResumenProperty() {
            return new SimpleStringProperty(odResumen);
        }

        public StringProperty oiResumenProperty() {
            return new SimpleStringProperty(oiResumen);
        }

        public StringProperty pdProperty() {
            return new SimpleStringProperty(pd);
        }
    }

    // ---- Stage 3: Montura catalog row ----
    public record MonturaRow(
            String referencia,
            String nombre,
            String marca,
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

    // ---- Stage 7: Historical sales row ----
    public record HistoricRow(
            String fecha,
            String orden,
            String cliente,
            String monto,
            String metodo,
            String estado,
            String comprobante
    ) {
        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty ordenProperty() {
            return new SimpleStringProperty(orden);
        }

        public StringProperty clienteProperty() {
            return new SimpleStringProperty(cliente);
        }

        public StringProperty montoProperty() {
            return new SimpleStringProperty(monto);
        }

        public StringProperty metodoProperty() {
            return new SimpleStringProperty(metodo);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty comprobanteProperty() {
            return new SimpleStringProperty(comprobante);
        }
    }
}
