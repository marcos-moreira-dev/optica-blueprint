package com.marcosmoreira.opticademo.modules.caja;

import com.marcosmoreira.opticademo.shared.domain.caja.Cobro;
import com.marcosmoreira.opticademo.shared.domain.common.EstadoGeneral;
import com.marcosmoreira.opticademo.shared.domain.venta.VentaOptica;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Row models for the Caja module TableView entries.
 */
public final class CajaRowModel {

    private CajaRowModel() {
    }

    // ------------------------------------------------------------------ CobroRow

    public record CobroRow(
            String fecha,
            String orden,
            String cliente,
            String monto,
            String metodo,
            String estado,
            String comprobante
    ) {

        public static CobroRow from(Cobro cobro) {
            return new CobroRow(
                    cobro.getFecha() != null ? cobro.getFecha() : "",
                    cobro.getOrdenId() != null ? cobro.getOrdenId() : "",
                    cobro.getClienteNombre() != null ? cobro.getClienteNombre() : "",
                    formatMoney(cobro.getMonto()),
                    cobro.getMetodoPago() != null ? cobro.getMetodoPago() : "",
                    cobro.getEstado() != null ? cobro.getEstado().name() : "",
                    cobro.getComprobante() != null ? cobro.getComprobante() : ""
            );
        }

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

    // ------------------------------------------------------------------ SaldoRow

    public record SaldoRow(
            String orden,
            String cliente,
            String total,
            String abonado,
            String saldo,
            String ultimoPago,
            String estado
    ) {

        public static SaldoRow from(VentaOptica venta) {
            double total = venta.getPrecioMontura() + venta.getPrecioLente() - venta.getDescuento();
            return new SaldoRow(
                    venta.getReferencia() != null ? venta.getReferencia() : "",
                    venta.getClienteNombre() != null ? venta.getClienteNombre() : "",
                    formatMoney(total),
                    formatMoney(venta.getAbono()),
                    formatMoney(venta.getSaldo()),
                    venta.getFechaOrden() != null ? venta.getFechaOrden() : "",
                    venta.getEstado() != null ? venta.getEstado().name() : ""
            );
        }

        public StringProperty ordenProperty() {
            return new SimpleStringProperty(orden);
        }

        public StringProperty clienteProperty() {
            return new SimpleStringProperty(cliente);
        }

        public StringProperty totalProperty() {
            return new SimpleStringProperty(total);
        }

        public StringProperty abonadoProperty() {
            return new SimpleStringProperty(abonado);
        }

        public StringProperty saldoProperty() {
            return new SimpleStringProperty(saldo);
        }

        public StringProperty ultimoPagoProperty() {
            return new SimpleStringProperty(ultimoPago);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }
    }

    // ------------------------------------------------------------------ ComprobanteRow

    public record ComprobanteRow(
            String comprobante,
            String fecha,
            String cliente,
            String orden,
            String total,
            String estado
    ) {

        public static ComprobanteRow from(Cobro cobro) {
            return new ComprobanteRow(
                    cobro.getComprobante() != null ? cobro.getComprobante() : "",
                    cobro.getFecha() != null ? cobro.getFecha() : "",
                    cobro.getClienteNombre() != null ? cobro.getClienteNombre() : "",
                    cobro.getOrdenId() != null ? cobro.getOrdenId() : "",
                    formatMoney(cobro.getMonto()),
                    cobro.getEstado() != null ? cobro.getEstado().name() : ""
            );
        }

        public StringProperty comprobanteProperty() {
            return new SimpleStringProperty(comprobante);
        }

        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty clienteProperty() {
            return new SimpleStringProperty(cliente);
        }

        public StringProperty ordenProperty() {
            return new SimpleStringProperty(orden);
        }

        public StringProperty totalProperty() {
            return new SimpleStringProperty(total);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }
    }

    // ------------------------------------------------------------------ CierreRow

    public record CierreRow(
            String fecha,
            String sucursal,
            String cobros,
            String totalDia
    ) {

        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }

        public StringProperty cobrosProperty() {
            return new SimpleStringProperty(cobros);
        }

        public StringProperty totalDiaProperty() {
            return new SimpleStringProperty(totalDia);
        }
    }

    // ------------------------------------------------------------------ PendienteRow

    public record PendienteRow(
            String orden,
            String cliente,
            String saldo,
            String ultimoPago,
            String estado,
            String sucursal,
            String prioridad
    ) {

        public static PendienteRow from(VentaOptica venta) {
            String prioridad = switch (venta.getEstado()) {
                case EN_PROCESO, POR_COBRAR -> "ALTA";
                case EN_ESPERA -> "MEDIA";
                default -> "BAJA";
            };
            return new PendienteRow(
                    venta.getReferencia() != null ? venta.getReferencia() : "",
                    venta.getClienteNombre() != null ? venta.getClienteNombre() : "",
                    formatMoney(venta.getSaldo()),
                    venta.getFechaOrden() != null ? venta.getFechaOrden() : "",
                    venta.getEstado() != null ? venta.getEstado().name() : "",
                    venta.getSucursal() != null ? venta.getSucursal() : "",
                    prioridad
            );
        }

        public StringProperty ordenProperty() {
            return new SimpleStringProperty(orden);
        }

        public StringProperty clienteProperty() {
            return new SimpleStringProperty(cliente);
        }

        public StringProperty saldoProperty() {
            return new SimpleStringProperty(saldo);
        }

        public StringProperty ultimoPagoProperty() {
            return new SimpleStringProperty(ultimoPago);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }

        public StringProperty prioridadProperty() {
            return new SimpleStringProperty(prioridad);
        }
    }

    // ------------------------------------------------------------------ helpers

    private static String formatMoney(double value) {
        return String.format("$%.2f", value);
    }
}
