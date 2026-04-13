package com.marcosmoreira.opticademo.modules.seguimiento;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Row models for the Seguimiento module TableView entries.
 */
public final class SeguimientoRowModel {

    private SeguimientoRowModel() {
    }

    // ------------------------------------------------------------------ BandejaRow

    public record BandejaRow(
            String referencia,
            String cliente,
            String tipo,
            String estado,
            String fechaObjetivo,
            String prioridad,
            String sucursal,
            String responsable
    ) {

        public static BandejaRow seed(String referencia, String cliente, String tipo,
                                       String estado, String fechaObjetivo, String prioridad,
                                       String sucursal, String responsable) {
            return new BandejaRow(referencia, cliente, tipo, estado, fechaObjetivo,
                    prioridad, sucursal, responsable);
        }

        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty clienteProperty() {
            return new SimpleStringProperty(cliente);
        }

        public StringProperty tipoProperty() {
            return new SimpleStringProperty(tipo);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty fechaObjetivoProperty() {
            return new SimpleStringProperty(fechaObjetivo);
        }

        public StringProperty prioridadProperty() {
            return new SimpleStringProperty(prioridad);
        }

        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }

        public StringProperty responsableProperty() {
            return new SimpleStringProperty(responsable);
        }
    }

    // ------------------------------------------------------------------ RecallRow

    public record RecallRow(
            String cliente,
            String ultimaVisita,
            String motivo,
            String fechaSugerida,
            String estado,
            String sucursal
    ) {

        public static RecallRow seed(String cliente, String ultimaVisita, String motivo,
                                      String fechaSugerida, String estado, String sucursal) {
            return new RecallRow(cliente, ultimaVisita, motivo, fechaSugerida, estado, sucursal);
        }

        public StringProperty clienteProperty() {
            return new SimpleStringProperty(cliente);
        }

        public StringProperty ultimaVisitaProperty() {
            return new SimpleStringProperty(ultimaVisita);
        }

        public StringProperty motivoProperty() {
            return new SimpleStringProperty(motivo);
        }

        public StringProperty fechaSugeridaProperty() {
            return new SimpleStringProperty(fechaSugerida);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }
    }

    // ------------------------------------------------------------------ NoRetiradoRow

    public record NoRetiradoRow(
            String orden,
            String cliente,
            String diasEsperando,
            String notificacion,
            String saldo,
            String estado,
            String sucursal
    ) {

        public static NoRetiradoRow seed(String orden, String cliente, String diasEsperando,
                                          String notificacion, String saldo, String estado,
                                          String sucursal) {
            return new NoRetiradoRow(orden, cliente, diasEsperando, notificacion, saldo, estado, sucursal);
        }

        public StringProperty ordenProperty() {
            return new SimpleStringProperty(orden);
        }

        public StringProperty clienteProperty() {
            return new SimpleStringProperty(cliente);
        }

        public StringProperty diasEsperandoProperty() {
            return new SimpleStringProperty(diasEsperando);
        }

        public StringProperty notificacionProperty() {
            return new SimpleStringProperty(notificacion);
        }

        public StringProperty saldoProperty() {
            return new SimpleStringProperty(saldo);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }
    }

    // ------------------------------------------------------------------ CobroPendienteRow

    public record CobroPendienteRow(
            String orden,
            String cliente,
            String saldo,
            String ultimoPago,
            String estado,
            String proximaAccion,
            String sucursal
    ) {

        public static CobroPendienteRow seed(String orden, String cliente, String saldo,
                                              String ultimoPago, String estado,
                                              String proximaAccion, String sucursal) {
            return new CobroPendienteRow(orden, cliente, saldo, ultimoPago, estado, proximaAccion, sucursal);
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

        public StringProperty proximaAccionProperty() {
            return new SimpleStringProperty(proximaAccion);
        }

        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }
    }

    // ------------------------------------------------------------------ MensajeRow

    public record MensajeRow(
            String fecha,
            String cliente,
            String tipo,
            String canal,
            String estado,
            String resultado
    ) {

        public static MensajeRow seed(String fecha, String cliente, String tipo,
                                       String canal, String estado, String resultado) {
            return new MensajeRow(fecha, cliente, tipo, canal, estado, resultado);
        }

        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty clienteProperty() {
            return new SimpleStringProperty(cliente);
        }

        public StringProperty tipoProperty() {
            return new SimpleStringProperty(tipo);
        }

        public StringProperty canalProperty() {
            return new SimpleStringProperty(canal);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty resultadoProperty() {
            return new SimpleStringProperty(resultado);
        }
    }

    // ------------------------------------------------------------------ HistoricoRow

    public record HistoricoRow(
            String fecha,
            String referencia,
            String cliente,
            String tipo,
            String estadoFinal,
            String resultado,
            String sucursal
    ) {

        public static HistoricoRow seed(String fecha, String referencia, String cliente,
                                         String tipo, String estadoFinal, String resultado,
                                         String sucursal) {
            return new HistoricoRow(fecha, referencia, cliente, tipo, estadoFinal, resultado, sucursal);
        }

        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty clienteProperty() {
            return new SimpleStringProperty(cliente);
        }

        public StringProperty tipoProperty() {
            return new SimpleStringProperty(tipo);
        }

        public StringProperty estadoFinalProperty() {
            return new SimpleStringProperty(estadoFinal);
        }

        public StringProperty resultadoProperty() {
            return new SimpleStringProperty(resultado);
        }

        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }
    }
}
