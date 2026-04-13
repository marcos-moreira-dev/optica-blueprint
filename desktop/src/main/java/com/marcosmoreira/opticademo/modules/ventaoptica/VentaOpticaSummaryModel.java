package com.marcosmoreira.opticademo.modules.ventaoptica;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Summary model for the Venta Optica order displayed in the right panel.
 */
public record VentaOpticaSummaryModel(
        String cliente,
        String codigo,
        String receta,
        String montura,
        String lente,
        String pd,
        String subtotal,
        String descuento,
        String abono,
        String saldo,
        String estado,
        String entregaEstimada,
        String laboratorio
) {

    public StringProperty clienteProperty() {
        return new SimpleStringProperty(cliente);
    }

    public StringProperty codigoProperty() {
        return new SimpleStringProperty(codigo);
    }

    public StringProperty recetaProperty() {
        return new SimpleStringProperty(receta);
    }

    public StringProperty monturaProperty() {
        return new SimpleStringProperty(montura);
    }

    public StringProperty lenteProperty() {
        return new SimpleStringProperty(lente);
    }

    public StringProperty pdProperty() {
        return new SimpleStringProperty(pd);
    }

    public StringProperty subtotalProperty() {
        return new SimpleStringProperty(subtotal);
    }

    public StringProperty descuentoProperty() {
        return new SimpleStringProperty(descuento);
    }

    public StringProperty abonoProperty() {
        return new SimpleStringProperty(abono);
    }

    public StringProperty saldoProperty() {
        return new SimpleStringProperty(saldo);
    }

    public StringProperty estadoProperty() {
        return new SimpleStringProperty(estado);
    }

    public StringProperty entregaEstimadaProperty() {
        return new SimpleStringProperty(entregaEstimada);
    }

    public StringProperty laboratorioProperty() {
        return new SimpleStringProperty(laboratorio);
    }

    /**
     * Creates an empty/default summary model.
     */
    public static VentaOpticaSummaryModel empty() {
        return new VentaOpticaSummaryModel(
                "Sin seleccionar", "", "Sin seleccionar", "Sin seleccionar",
                "Sin seleccionar", "", "$0.00", "$0.00", "$0.00", "$0.00",
                "BORRADOR", "", ""
        );
    }
}
