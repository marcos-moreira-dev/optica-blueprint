package com.marcosmoreira.opticademo.modules.notificaciones;

/**
 * Modelos de fila para las vistas del modulo Notificaciones (gestion de comunicaciones).
 * <p>
 * Estos registros alimentan los {@code TableView} del modulo: bandeja general de
 * notificaciones, notificaciones al cliente, notificaciones internas, plantillas
 * de campanas, historial de envios y alertas criticas. La fachada crea estas
 * instancias a partir de las entidades {@code Notificacion} del dominio.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see com.marcosmoreira.opticademo.modules.notificaciones.NotificacionesFacade
 */
public class NotificacionesRowModel {

    private NotificacionesRowModel() {}

    /**
     * Modelo de fila para la bandeja general de notificaciones.
     * <p>
     * Vista central que lista todas las notificaciones del sistema. El campo
     * {@code canal} indica el medio: "SMS", "Email", "WhatsApp", "Interna".
     * El campo {@code prioridad} determina el orden de atencion.
     * </p>
     *
     * @param referencia    identificador de la notificacion (columna "Referencia")
     * @param tipo          tipo: "Recordatorio", "Alerta", "Campana" (columna "Tipo")
     * @param clienteOCaso  destinatario o caso asociado (columna "Cliente/Caso")
     * @param canal         canal de envio (columna "Canal")
     * @param estado        estado: "Pendiente", "Enviado", "Fallido" (columna "Estado")
     * @param prioridad     prioridad: "Alta", "Media", "Baja" (columna "Prioridad")
     * @param fecha         fecha de programacion o envio (columna "Fecha")
     * @param moduloOrigen  modulo que genero la notificacion (columna "Modulo Origen")
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
     * Modelo de fila para las notificaciones enviadas al cliente.
     * <p>
     * Historial de comunicaciones dirigidas a pacientes: recordatorios de cita,
     * avisos de trabajo listo, seguimientos.
     * </p>
     *
     * @param cliente   nombre del destinatario (columna "Cliente")
     * @param tipo      tipo de notificacion (columna "Tipo")
     * @param canal     canal utilizado (columna "Canal")
     * @param estado    estado del envio (columna "Estado")
     * @param fecha     fecha de envio (columna "Fecha")
     * @param resultado resultado: "Entregado", "Fallido" (columna "Resultado")
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
     * Modelo de fila para las notificaciones operativas internas.
     * <p>
     * Alertas del sistema dirigidas al personal: stock critico, ordenes vencidas,
     * etc. El campo {@code area} indica el departamento destinatario.
     * </p>
     *
     * @param referencia identificador (columna "Referencia")
     * @param tipo       tipo de alerta (columna "Tipo")
     * @param area       area destinataria (columna "Area")
     * @param estado     estado de la notificacion (columna "Estado")
     * @param prioridad  prioridad (columna "Prioridad")
     * @param fecha      fecha de generacion (columna "Fecha")
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
     * Modelo de fila para las plantillas de campanas.
     * <p>
     * Lista las plantillas de mensajes configuradas para campanas de marketing
     * o comunicaciones masivas.
     * </p>
     *
     * @param plantilla       nombre de la plantilla (columna "Plantilla")
     * @param tipo            tipo de campana (columna "Tipo")
     * @param canalSugerido   canal recomendado (columna "Canal Sugerido")
     * @param estado          estado: "Activa", "Inactiva" (columna "Estado")
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
     * Modelo de fila para el historial de envios y respuestas.
     *
     * @param fecha         fecha del evento (columna "Fecha")
     * @param referencia    identificador (columna "Referencia")
     * @param clienteOCaso  destinatario (columna "Cliente/Caso")
     * @param canal         canal utilizado (columna "Canal")
     * @param estado        estado del envio (columna "Estado")
     * @param resultado     resultado obtenido (columna "Resultado")
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
     * Modelo de fila para las alertas criticas del sistema.
     * <p>
     * Notificaciones de alta prioridad que requieren accion inmediata: fallos
     * de sistema, emergencias, problemas criticos de operacion.
     * </p>
     *
     * @param referencia     identificador de la alerta (columna "Referencia")
     * @param tipo           tipo de alerta critica (columna "Tipo")
     * @param casoAfectado   caso o paciente afectado (columna "Caso Afectado")
     * @param prioridad      prioridad, generalmente "Critica" (columna "Prioridad")
     * @param estado         estado de resolucion (columna "Estado")
     * @param accionSugerida accion recomendada (columna "Accion Sugerida")
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
