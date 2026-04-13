package com.marcosmoreira.opticademo.modules.caja;

import com.marcosmoreira.opticademo.shared.domain.caja.Cobro;
import com.marcosmoreira.opticademo.shared.domain.common.EstadoGeneral;
import com.marcosmoreira.opticademo.shared.domain.venta.VentaOptica;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Modelos de fila para las vistas del modulo Caja (gestion de cobros y pagos).
 * <p>
 * Estos registros alimentan los {@code TableView} del modulo: registro de cobros,
 * saldos pendientes, comprobantes emitidos, cierres de caja y pagos pendientes.
 * La fachada crea estas instancias a partir de las entidades {@code Cobro} y
 * {@code VentaOptica} del dominio. El metodo auxiliar {@code formatMoney(double)}
 * formatea los valores monetarios con el simbolo de dolar y dos decimales.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see com.marcosmoreira.opticademo.modules.caja.CajaFacade
 * @see com.marcosmoreira.opticademo.shared.domain.caja.Cobro
 * @see com.marcosmoreira.opticademo.shared.domain.venta.VentaOptica
 */
public final class CajaRowModel {

    private CajaRowModel() {
    }

    // ------------------------------------------------------------------ CobroRow

    /**
     * Modelo de fila para la tabla de cobros registrados.
     * <p>
     * Cada registro representa un cobro realizado. La fachada crea estos registros
     * mediante el metodo estatico {@code from(Cobro)} que mapea una entidad de
     * dominio al modelo de presentacion. El campo {@code estado} refleja el resultado
     * del cobro: "COMPLETADO", "RECHAZADO", "ANULADO".
     * </p>
     *
     * @param fecha        fecha y hora del cobro (columna "Fecha")
     * @param orden        numero de orden asociada (columna "Orden")
     * @param cliente      nombre del cliente (columna "Cliente")
     * @param monto        monto cobrado formateado como "$XXX.XX" (columna "Monto")
     * @param metodo       metodo de pago: "Efectivo", "Tarjeta", "Transferencia" (columna "Metodo")
     * @param estado       estado del cobro (columna "Estado")
     * @param comprobante  numero de comprobante emitido (columna "Comprobante")
     */
    public record CobroRow(
            String fecha,
            String orden,
            String cliente,
            String monto,
            String metodo,
            String estado,
            String comprobante
    ) {

        /**
         * Crea un {@code CobroRow} a partir de una entidad {@code Cobro} del dominio.
         * <p>
         * Convierte los campos de la entidad en valores de presentacion, formateando
         * el monto con {@code formatMoney(double)}. Los campos nulos se reemplazan
         * por cadenas vacias para evitar errores en la UI.
         * </p>
         *
         * @param cobro entidad de dominio a convertir
         * @return modelo de fila para el {@code TableView}
         */
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

    /**
     * Modelo de fila para la tabla de saldos pendientes por orden.
     * <p>
     * Muestra el estado financiero de cada venta: total, monto abonado y saldo
     * restante. La fachada crea estos registros mediante {@code from(VentaOptica)},
     * calculando el total como {@code precioMontura + precioLente - descuento}.
     * </p>
     *
     * @param orden     numero de orden (columna "Orden")
     * @param cliente   nombre del cliente (columna "Cliente")
     * @param total     total de la venta formateado (columna "Total")
     * @param abonado   monto ya pagado formateado (columna "Abonado")
     * @param saldo     saldo pendiente formateado (columna "Saldo")
     * @param ultimoPago fecha del ultimo pago registrado (columna "Ultimo Pago")
     * @param estado    estado de la orden (columna "Estado")
     */
    public record SaldoRow(
            String orden,
            String cliente,
            String total,
            String abonado,
            String saldo,
            String ultimoPago,
            String estado
    ) {

        /**
         * Crea un {@code SaldoRow} a partir de una entidad {@code VentaOptica}.
         * <p>
         * Calcula el total como {@code precioMontura + precioLente - descuento} y
         * formatea todos los valores monetarios.
         * </p>
         *
         * @param venta entidad de dominio a convertir
         * @return modelo de fila para el {@code TableView}
         */
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

    /**
     * Modelo de fila para la tabla de comprobantes emitidos.
     * <p>
     * Permite consultar los comprobantes de pago generados. La fachada convierte
     * entidades {@code Cobro} mediante {@code from(Cobro)}.
     * </p>
     *
     * @param comprobante numero de comprobante (columna "Comprobante")
     * @param fecha       fecha de emision (columna "Fecha")
     * @param cliente     nombre del cliente (columna "Cliente")
     * @param orden       orden asociada (columna "Orden")
     * @param total       monto total facturado (columna "Total")
     * @param estado      estado del comprobante (columna "Estado")
     */
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

    /**
     * Modelo de fila para la tabla de cierres de caja diarios.
     * <p>
     * Resume los cobros y el total recaudado por sucursal en cada jornada.
     * </p>
     *
     * @param fecha     fecha del cierre (columna "Fecha")
     * @param sucursal  sede del cierre (columna "Sucursal")
     * @param cobros    cantidad de cobros procesados (columna "Cobros")
     * @param totalDia  total recaudado en el dia (columna "Total Dia")
     */
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

    /**
     * Modelo de fila para la tabla de cobros pendientes.
     * <p>
     * Muestra las ordenes con saldo pendiente de pago. El campo {@code prioridad}
     * se calcula automaticamente segun el estado de la venta: "ALTA" para
     * {@code EN_PROCESO} o {@code POR_COBRAR}, "MEDIA" para {@code EN_ESPERA},
     * "BAJA" para el resto.
     * </p>
     *
     * @param orden      numero de orden (columna "Orden")
     * @param cliente    nombre del cliente (columna "Cliente")
     * @param saldo      saldo pendiente formateado (columna "Saldo")
     * @param ultimoPago fecha del ultimo pago (columna "Ultimo Pago")
     * @param estado     estado de la venta (columna "Estado")
     * @param sucursal   sede de la orden (columna "Sucursal")
     * @param prioridad  nivel de prioridad de cobro (columna "Prioridad")
     */
    public record PendienteRow(
            String orden,
            String cliente,
            String saldo,
            String ultimoPago,
            String estado,
            String sucursal,
            String prioridad
    ) {

        /**
         * Crea un {@code PendienteRow} a partir de una entidad {@code VentaOptica}.
         * <p>
         * Calcula la prioridad segun el estado de la venta usando un switch pattern:
         * {@code EN_PROCESO} o {@code POR_COBRAR} → "ALTA", {@code EN_ESPERA} → "MEDIA",
         * otro → "BAJA".
         * </p>
         *
         * @param venta entidad de dominio a convertir
         * @return modelo de fila para el {@code TableView}
         */
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

    /**
     * Formatea un valor monetario con simbolo de dolar y dos decimales.
     *
     * @param value valor numerico a formatear
     * @return cadena formateada, ej. "$150.00"
     */
    private static String formatMoney(double value) {
        return String.format("$%.2f", value);
    }
}
