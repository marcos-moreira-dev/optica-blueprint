package com.marcosmoreira.opticademo.modules.inicio;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Record types for dashboard row data used by the Inicio module.
 */
public final class InicioRowModel {

    private InicioRowModel() {
        // utility class
    }

    /**
     * Row model for an upcoming appointment.
     */
    public record CitaRow(String hora, String cliente, String atencion, String estado, String profesional) {
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
    }

    /**
     * Row model for a dashboard alert.
     */
    public record AlertaRow(String tipo, String texto, String estado) {
        public StringProperty tipoProperty() {
            return new SimpleStringProperty(tipo);
        }
        public StringProperty textoProperty() {
            return new SimpleStringProperty(texto);
        }
        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }
    }

    /**
     * Row model for a recent activity entry.
     */
    public record ActividadRow(String texto) {
        public StringProperty textoProperty() {
            return new SimpleStringProperty(texto);
        }
    }
}
