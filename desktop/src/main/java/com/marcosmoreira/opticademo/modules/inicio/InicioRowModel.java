package com.marcosmoreira.opticademo.modules.inicio;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Modelos de fila para las vistas del modulo Inicio (Dashboard principal).
 * <p>
 * Estos registros proporcionan los datos que se muestran en los {@code TableView}
 * del dashboard: citas proximas, alertas operativas y actividad reciente. La facade
 * del modulo Inicio crea estas instancias a partir de datos consolidados de los
 * demas modulos (Agenda, Caja, Entregas, etc.) para presentar un resumen ejecutivo
 * al operador al iniciar sesion.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see com.marcosmoreira.opticademo.modules.inicio.InicioFacade
 */
public final class InicioRowModel {

    private InicioRowModel() {
        // utility class
    }

    /**
     * Modelo de fila para la tabla de citas proximas en el dashboard.
     * <p>
     * Cada campo corresponde a una columna del {@code TableView} de citas del dia:
     * hora de la cita, nombre del cliente, tipo de atencion solicitada, estado de la
     * cita y profesional asignado. La fachada genera estos registros a partir de las
     * entidades {@code Cita} filtradas por la fecha actual.
     * </p>
     *
     * @param hora       hora programada de la cita (columna "Hora")
     * @param cliente    nombre completo del paciente (columna "Cliente")
     * @param atencion   tipo de servicio a prestar, ej. "Examen visual", "Entrega" (columna "Atencion")
     * @param estado     estado de la cita: "Confirmada", "Pendiente", "Cancelada";
     *                   determina el color de la fila en la UI (verde, amarillo, rojo)
     *                   (columna "Estado")
     * @param profesional nombre del profesional asignado (columna "Profesional")
     * @see com.marcosmoreira.opticademo.modules.agenda.AgendaRowModel.CitaDiaRow
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
     * Modelo de fila para la tabla de alertas del dashboard.
     * <p>
     * Representa notificaciones criticas que requieren accion inmediata del operador,
     * como stock critico, ordenes vencidas o pacientes en lista de espera. El campo
     * {@code estado} controla el indicador visual en la UI (rojo para urgente,
     * amarillo para advertencia, verde para informativo).
     * </p>
     *
     * @param tipo   categoria de la alerta, ej. "Stock", "Orden", "Entrega" (columna "Tipo")
     * @param texto  descripcion detallada de la situacion (columna "Detalle")
     * @param estado nivel de urgencia: "Urgente", "Advertencia", "Informativo"
     *               (columna "Estado")
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
     * Modelo de fila para la lista de actividad reciente en el dashboard.
     * <p>
     * Muestra un historial compacto de las ultimas acciones realizadas en el sistema
     * (ventas cerradas, entregas realizadas, citas atendidas). La fachada consolida
     * estos eventos de los distintos modulos y los formatea como una cadena de texto
     * legible para la unica columna del {@code TableView}.
     * </p>
     *
     * @param texto descripcion de la actividad reciente, ej. "Venta V-001 cerrada - $150.00"
     */
    public record ActividadRow(String texto) {
        public StringProperty textoProperty() {
            return new SimpleStringProperty(texto);
        }
    }
}
