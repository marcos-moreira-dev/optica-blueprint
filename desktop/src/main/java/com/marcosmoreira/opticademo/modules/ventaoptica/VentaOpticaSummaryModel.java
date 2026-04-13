package com.marcosmoreira.opticademo.modules.ventaoptica;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Modelo resumen para el panel lateral derecho persistente del modulo Venta Optica.
 * Presenta el detalle completo de una orden de venta optica, incluyendo componentes
 * de montura, lentes, valores economicos y estado de la orden. La fachada
 * (VentaOpticaFacade) actualiza este modelo cuando el usuario selecciona una orden
 * en la tabla principal o al crear/modificar una venta.
 *
 * @author Marcos Moreira
 * @version 1.0.0
 */
public record VentaOpticaSummaryModel(
        /** Nombre del cliente asociado a la venta. */
        String cliente,
        /** Codigo o referencia de la orden de venta. */
        String codigo,
        /** Receta optica vinculada a la venta. */
        String receta,
        /** Referencia de la montura seleccionada. */
        String montura,
        /** Tipo de lente seleccionado. */
        String lente,
        /** Distancia pupilar utilizada para el lente. */
        String pd,
        /** Subtotal de la venta antes de descuentos. */
        String subtotal,
        /** Monto del descuento aplicado. */
        String descuento,
        /** Abono inicial o pago parcial realizado. */
        String abono,
        /** Saldo pendiente de pago. */
        String saldo,
        /** Estado actual de la orden (borrador, en proceso, listo, entregado). */
        String estado,
        /** Fecha estimada de entrega al cliente. */
        String entregaEstimada,
        /** Laboratorio asignado para la elaboracion del lente. */
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
