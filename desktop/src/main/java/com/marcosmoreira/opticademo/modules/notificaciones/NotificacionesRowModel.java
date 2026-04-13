package com.marcosmoreira.opticademo.modules.notificaciones;

/**
 * Row models for the Notificaciones module views.
 */
public class NotificacionesRowModel {

    private NotificacionesRowModel() {}

    /**
     * Bandeja general row.
     */
    public record BandejaRow(
            String referencia,
            String tipo,
            String clienteOCaso,
            String canal,
            String estado,
            String prioridad,
            String fecha,
            String moduloOrigen
    ) {
        public javafx.beans.property.StringProperty referenciaProperty() {
            return new javafx.beans.property.SimpleStringProperty(referencia);
        }
        public javafx.beans.property.StringProperty tipoProperty() {
            return new javafx.beans.property.SimpleStringProperty(tipo);
        }
        public javafx.beans.property.StringProperty clienteOCasoProperty() {
            return new javafx.beans.property.SimpleStringProperty(clienteOCaso);
        }
        public javafx.beans.property.StringProperty canalProperty() {
            return new javafx.beans.property.SimpleStringProperty(canal);
        }
        public javafx.beans.property.StringProperty estadoProperty() {
            return new javafx.beans.property.SimpleStringProperty(estado);
        }
        public javafx.beans.property.StringProperty prioridadProperty() {
            return new javafx.beans.property.SimpleStringProperty(prioridad);
        }
        public javafx.beans.property.StringProperty fechaProperty() {
            return new javafx.beans.property.SimpleStringProperty(fecha);
        }
        public javafx.beans.property.StringProperty moduloOrigenProperty() {
            return new javafx.beans.property.SimpleStringProperty(moduloOrigen);
        }
    }

    /**
     * Notificaciones al cliente row.
     */
    public record NotifClienteRow(
            String cliente,
            String tipo,
            String canal,
            String estado,
            String fecha,
            String resultado
    ) {
        public javafx.beans.property.StringProperty clienteProperty() {
            return new javafx.beans.property.SimpleStringProperty(cliente);
        }
        public javafx.beans.property.StringProperty tipoProperty() {
            return new javafx.beans.property.SimpleStringProperty(tipo);
        }
        public javafx.beans.property.StringProperty canalProperty() {
            return new javafx.beans.property.SimpleStringProperty(canal);
        }
        public javafx.beans.property.StringProperty estadoProperty() {
            return new javafx.beans.property.SimpleStringProperty(estado);
        }
        public javafx.beans.property.StringProperty fechaProperty() {
            return new javafx.beans.property.SimpleStringProperty(fecha);
        }
        public javafx.beans.property.StringProperty resultadoProperty() {
            return new javafx.beans.property.SimpleStringProperty(resultado);
        }
    }

    /**
     * Notificaciones operativas internas row.
     */
    public record NotifInternaRow(
            String referencia,
            String tipo,
            String area,
            String estado,
            String prioridad,
            String fecha
    ) {
        public javafx.beans.property.StringProperty referenciaProperty() {
            return new javafx.beans.property.SimpleStringProperty(referencia);
        }
        public javafx.beans.property.StringProperty tipoProperty() {
            return new javafx.beans.property.SimpleStringProperty(tipo);
        }
        public javafx.beans.property.StringProperty areaProperty() {
            return new javafx.beans.property.SimpleStringProperty(area);
        }
        public javafx.beans.property.StringProperty estadoProperty() {
            return new javafx.beans.property.SimpleStringProperty(estado);
        }
        public javafx.beans.property.StringProperty prioridadProperty() {
            return new javafx.beans.property.SimpleStringProperty(prioridad);
        }
        public javafx.beans.property.StringProperty fechaProperty() {
            return new javafx.beans.property.SimpleStringProperty(fecha);
        }
    }

    /**
     * Campanas y plantillas row.
     */
    public record PlantillaRow(
            String plantilla,
            String tipo,
            String canalSugerido,
            String estado
    ) {
        public javafx.beans.property.StringProperty plantillaProperty() {
            return new javafx.beans.property.SimpleStringProperty(plantilla);
        }
        public javafx.beans.property.StringProperty tipoProperty() {
            return new javafx.beans.property.SimpleStringProperty(tipo);
        }
        public javafx.beans.property.StringProperty canalSugeridoProperty() {
            return new javafx.beans.property.SimpleStringProperty(canalSugerido);
        }
        public javafx.beans.property.StringProperty estadoProperty() {
            return new javafx.beans.property.SimpleStringProperty(estado);
        }
    }

    /**
     * Historial de envios y respuestas row.
     */
    public record HistorialEnvioRow(
            String fecha,
            String referencia,
            String clienteOCaso,
            String canal,
            String estado,
            String resultado
    ) {
        public javafx.beans.property.StringProperty fechaProperty() {
            return new javafx.beans.property.SimpleStringProperty(fecha);
        }
        public javafx.beans.property.StringProperty referenciaProperty() {
            return new javafx.beans.property.SimpleStringProperty(referencia);
        }
        public javafx.beans.property.StringProperty clienteOCasoProperty() {
            return new javafx.beans.property.SimpleStringProperty(clienteOCaso);
        }
        public javafx.beans.property.StringProperty canalProperty() {
            return new javafx.beans.property.SimpleStringProperty(canal);
        }
        public javafx.beans.property.StringProperty estadoProperty() {
            return new javafx.beans.property.SimpleStringProperty(estado);
        }
        public javafx.beans.property.StringProperty resultadoProperty() {
            return new javafx.beans.property.SimpleStringProperty(resultado);
        }
    }

    /**
     * Alertas criticas row.
     */
    public record AlertaCriticaRow(
            String referencia,
            String tipo,
            String casoAfectado,
            String prioridad,
            String estado,
            String accionSugerida
    ) {
        public javafx.beans.property.StringProperty referenciaProperty() {
            return new javafx.beans.property.SimpleStringProperty(referencia);
        }
        public javafx.beans.property.StringProperty tipoProperty() {
            return new javafx.beans.property.SimpleStringProperty(tipo);
        }
        public javafx.beans.property.StringProperty casoAfectadoProperty() {
            return new javafx.beans.property.SimpleStringProperty(casoAfectado);
        }
        public javafx.beans.property.StringProperty prioridadProperty() {
            return new javafx.beans.property.SimpleStringProperty(prioridad);
        }
        public javafx.beans.property.StringProperty estadoProperty() {
            return new javafx.beans.property.SimpleStringProperty(estado);
        }
        public javafx.beans.property.StringProperty accionSugeridaProperty() {
            return new javafx.beans.property.SimpleStringProperty(accionSugerida);
        }
    }
}
