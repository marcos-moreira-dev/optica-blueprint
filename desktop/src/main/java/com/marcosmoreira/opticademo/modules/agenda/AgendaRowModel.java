package com.marcosmoreira.opticademo.modules.agenda;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Row models for the Agenda module TableView and timeline entries.
 */
public final class AgendaRowModel {

    private AgendaRowModel() {
    }

    // ------------------------------------------------------------------ CitaDiaRow

    public record CitaDiaRow(
            String hora,
            String cliente,
            String tipoAtencion,
            String estado,
            String profesional,
            String sucursal,
            String tooltip
    ) {

        public static CitaDiaRow seed(String hora, String cliente, String tipoAtencion,
                                       String estado, String profesional, String sucursal,
                                       String tooltip) {
            return new CitaDiaRow(hora, cliente, tipoAtencion, estado, profesional, sucursal, tooltip);
        }

        public StringProperty horaProperty() {
            return new SimpleStringProperty(hora);
        }

        public StringProperty clienteProperty() {
            return new SimpleStringProperty(cliente);
        }

        public StringProperty tipoAtencionProperty() {
            return new SimpleStringProperty(tipoAtencion);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty profesionalProperty() {
            return new SimpleStringProperty(profesional);
        }

        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }

        public StringProperty tooltipProperty() {
            return new SimpleStringProperty(tooltip);
        }
    }

    // ------------------------------------------------------------------ SemanaRow

    public record SemanaRow(
            String dia,
            String totalCitas,
            String confirmadas,
            String pendientes,
            String canceladas
    ) {

        public static SemanaRow seed(String dia, String totalCitas, String confirmadas,
                                      String pendientes, String canceladas) {
            return new SemanaRow(dia, totalCitas, confirmadas, pendientes, canceladas);
        }

        public StringProperty diaProperty() {
            return new SimpleStringProperty(dia);
        }

        public StringProperty totalCitasProperty() {
            return new SimpleStringProperty(totalCitas);
        }

        public StringProperty confirmadasProperty() {
            return new SimpleStringProperty(confirmadas);
        }

        public StringProperty pendientesProperty() {
            return new SimpleStringProperty(pendientes);
        }

        public StringProperty canceladasProperty() {
            return new SimpleStringProperty(canceladas);
        }
    }

    // ------------------------------------------------------------------ ListaDiaRow

    public record ListaDiaRow(
            String hora,
            String cliente,
            String atencion,
            String estado,
            String profesional,
            String sucursal,
            String observacion
    ) {

        public static ListaDiaRow seed(String hora, String cliente, String atencion,
                                        String estado, String profesional, String sucursal,
                                        String observacion) {
            return new ListaDiaRow(hora, cliente, atencion, estado, profesional, sucursal, observacion);
        }

        public StringProperty horaProperty() {
            return new SimpleStringProperty(hora);
        }

        public StringProperty clienteProperty() {
            return new SimpleStringProperty(cliente);
        }

        public StringProperty atencionProperty() {
            return new SimpleStringProperty(atencion);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty profesionalProperty() {
            return new SimpleStringProperty(profesional);
        }

        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }

        public StringProperty observacionProperty() {
            return new SimpleStringProperty(observacion);
        }
    }

    // ------------------------------------------------------------------ EsperaRow

    public record EsperaRow(
            String cliente,
            String atencionSolicitada,
            String franjaPreferida,
            String sucursal,
            String prioridad,
            String estadoContacto
    ) {

        public static EsperaRow seed(String cliente, String atencionSolicitada, String franjaPreferida,
                                      String sucursal, String prioridad, String estadoContacto) {
            return new EsperaRow(cliente, atencionSolicitada, franjaPreferida, sucursal, prioridad, estadoContacto);
        }

        public StringProperty clienteProperty() {
            return new SimpleStringProperty(cliente);
        }

        public StringProperty atencionSolicitadaProperty() {
            return new SimpleStringProperty(atencionSolicitada);
        }

        public StringProperty franjaPreferidaProperty() {
            return new SimpleStringProperty(franjaPreferida);
        }

        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }

        public StringProperty prioridadProperty() {
            return new SimpleStringProperty(prioridad);
        }

        public StringProperty estadoContactoProperty() {
            return new SimpleStringProperty(estadoContacto);
        }
    }

    // ------------------------------------------------------------------ ConfirmacionRow

    public record ConfirmacionRow(
            String fecha,
            String hora,
            String cliente,
            String atencion,
            String estadoConfirmacion,
            String ultimoRecordatorio,
            String sucursal
    ) {

        public static ConfirmacionRow seed(String fecha, String hora, String cliente,
                                            String atencion, String estadoConfirmacion,
                                            String ultimoRecordatorio, String sucursal) {
            return new ConfirmacionRow(fecha, hora, cliente, atencion, estadoConfirmacion, ultimoRecordatorio, sucursal);
        }

        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty horaProperty() {
            return new SimpleStringProperty(hora);
        }

        public StringProperty clienteProperty() {
            return new SimpleStringProperty(cliente);
        }

        public StringProperty atencionProperty() {
            return new SimpleStringProperty(atencion);
        }

        public StringProperty estadoConfirmacionProperty() {
            return new SimpleStringProperty(estadoConfirmacion);
        }

        public StringProperty ultimoRecordatorioProperty() {
            return new SimpleStringProperty(ultimoRecordatorio);
        }

        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }
    }

    // ------------------------------------------------------------------ HorarioRow

    public record HorarioRow(
            String franja,
            String tipo,
            String estado,
            String responsable
    ) {

        public static HorarioRow seed(String franja, String tipo, String estado, String responsable) {
            return new HorarioRow(franja, tipo, estado, responsable);
        }

        public StringProperty franjaProperty() {
            return new SimpleStringProperty(franja);
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
}
