package com.marcosmoreira.opticademo.modules.entregas;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Row models for the Entregas module TableView entries.
 */
public final class EntregasRowModel {

    private EntregasRowModel() {
    }

    // ------------------------------------------------------------------ TrabajoListoRow

    public record TrabajoListoRow(
            String referencia,
            String cliente,
            String tipoTrabajo,
            String fechaPromesa,
            String estado,
            String saldo,
            String sucursal,
            String notificacion
    ) {

        public static TrabajoListoRow seed(String referencia, String cliente, String tipoTrabajo,
                                           String fechaPromesa, String estado, String saldo,
                                           String sucursal, String notificacion) {
            return new TrabajoListoRow(referencia, cliente, tipoTrabajo, fechaPromesa,
                    estado, saldo, sucursal, notificacion);
        }

        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty clienteProperty() {
            return new SimpleStringProperty(cliente);
        }

        public StringProperty tipoTrabajoProperty() {
            return new SimpleStringProperty(tipoTrabajo);
        }

        public StringProperty fechaPromesaProperty() {
            return new SimpleStringProperty(fechaPromesa);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty saldoProperty() {
            return new SimpleStringProperty(saldo);
        }

        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }

        public StringProperty notificacionProperty() {
            return new SimpleStringProperty(notificacion);
        }
    }

    // ------------------------------------------------------------------ ValidacionRow

    public record ValidacionRow(
            String referencia,
            String cliente,
            String tipoTrabajo,
            String fechaPromesa,
            String trabajoRecibido,
            String montajeConforme,
            String requiereAjuste,
            String saldoPendiente
    ) {

        public static ValidacionRow seed(String referencia, String cliente, String tipoTrabajo,
                                         String fechaPromesa, String trabajoRecibido,
                                         String montajeConforme, String requiereAjuste,
                                         String saldoPendiente) {
            return new ValidacionRow(referencia, cliente, tipoTrabajo, fechaPromesa,
                    trabajoRecibido, montajeConforme, requiereAjuste, saldoPendiente);
        }

        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty clienteProperty() {
            return new SimpleStringProperty(cliente);
        }

        public StringProperty tipoTrabajoProperty() {
            return new SimpleStringProperty(tipoTrabajo);
        }

        public StringProperty fechaPromesaProperty() {
            return new SimpleStringProperty(fechaPromesa);
        }

        public StringProperty trabajoRecibidoProperty() {
            return new SimpleStringProperty(trabajoRecibido);
        }

        public StringProperty montajeConformeProperty() {
            return new SimpleStringProperty(montajeConforme);
        }

        public StringProperty requiereAjusteProperty() {
            return new SimpleStringProperty(requiereAjuste);
        }

        public StringProperty saldoPendienteProperty() {
            return new SimpleStringProperty(saldoPendiente);
        }
    }

    // ------------------------------------------------------------------ RetiroRow

    public record RetiroRow(
            String referencia,
            String cliente,
            String fechaHoraRetiro,
            String retira,
            String documento,
            String observacion
    ) {

        public static RetiroRow seed(String referencia, String cliente, String fechaHoraRetiro,
                                     String retira, String documento, String observacion) {
            return new RetiroRow(referencia, cliente, fechaHoraRetiro, retira, documento, observacion);
        }

        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty clienteProperty() {
            return new SimpleStringProperty(cliente);
        }

        public StringProperty fechaHoraRetiroProperty() {
            return new SimpleStringProperty(fechaHoraRetiro);
        }

        public StringProperty retiraProperty() {
            return new SimpleStringProperty(retira);
        }

        public StringProperty documentoProperty() {
            return new SimpleStringProperty(documento);
        }

        public StringProperty observacionProperty() {
            return new SimpleStringProperty(observacion);
        }
    }

    // ------------------------------------------------------------------ PendienteRow

    public record PendienteRow(
            String referencia,
            String cliente,
            String diasEsperando,
            String saldo,
            String estado,
            String notificacion,
            String sucursal
    ) {

        public static PendienteRow seed(String referencia, String cliente, String diasEsperando,
                                        String saldo, String estado, String notificacion,
                                        String sucursal) {
            return new PendienteRow(referencia, cliente, diasEsperando, saldo, estado, notificacion, sucursal);
        }

        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty clienteProperty() {
            return new SimpleStringProperty(cliente);
        }

        public StringProperty diasEsperandoProperty() {
            return new SimpleStringProperty(diasEsperando);
        }

        public StringProperty saldoProperty() {
            return new SimpleStringProperty(saldo);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty notificacionProperty() {
            return new SimpleStringProperty(notificacion);
        }

        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }
    }

    // ------------------------------------------------------------------ PostEntregaRow

    public record PostEntregaRow(
            String fecha,
            String referencia,
            String tipo,
            String estado,
            String responsable
    ) {

        public static PostEntregaRow seed(String fecha, String referencia, String tipo,
                                          String estado, String responsable) {
            return new PostEntregaRow(fecha, referencia, tipo, estado, responsable);
        }

        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty tipoProperty() {
            return new SimpleStringProperty(tipo);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty responsableProperty() {
            return new SimpleStringProperty(responsable);
        }
    }

    // ------------------------------------------------------------------ HistoricoRow

    public record HistoricoRow(
            String fecha,
            String referencia,
            String cliente,
            String estadoFinal,
            String saldo,
            String sucursal,
            String observacion
    ) {

        public static HistoricoRow seed(String fecha, String referencia, String cliente,
                                        String estadoFinal, String saldo, String sucursal,
                                        String observacion) {
            return new HistoricoRow(fecha, referencia, cliente, estadoFinal, saldo, sucursal, observacion);
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

        public StringProperty estadoFinalProperty() {
            return new SimpleStringProperty(estadoFinal);
        }

        public StringProperty saldoProperty() {
            return new SimpleStringProperty(saldo);
        }

        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }

        public StringProperty observacionProperty() {
            return new SimpleStringProperty(observacion);
        }
    }
}
