package com.marcosmoreira.opticademo.modules.agenda;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Modelos de fila para las vistas del modulo Agenda (gestion de citas).
 * <p>
 * Estos registros alimentan los distintos {@code TableView} del modulo: agenda del dia
 * (timeline semanal), lista detallada de citas, lista de espera, confirmaciones y
 * horarios. La fachada del modulo crea estas instancias a partir de las entidades
 * {@code Cita} y {@code Profesional}, aplicando los filtros seleccionados por el
 * usuario (fecha, profesional, estado, tipo de atencion).
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see com.marcosmoreira.opticademo.modules.agenda.AgendaFacade
 * @see com.marcosmoreira.opticademo.modules.agenda.AgendaSummaryModel
 */
public final class AgendaRowModel {

    private AgendaRowModel() {
    }

    // ------------------------------------------------------------------ CitaDiaRow

    /**
     * Modelo de fila para la tabla de citas del dia (vista principal de la agenda).
     * <p>
     * Cada registro representa una cita programada para el dia seleccionado. Los campos
     * se mapean directamente a las columnas del timeline de la agenda. El campo
     * {@code estado} determina el color de fondo de la celda en la UI: "Confirmada"
     * (verde #4CAF50), "Pendiente" (amarillo #FFC107), "Cancelada" (rojo #F44336),
     * "En curso" (azul #2196F3).
     * </p>
     *
     * @param hora           hora de la cita, formato HH:mm (columna "Hora")
     * @param cliente        nombre del paciente (columna "Cliente")
     * @param tipoAtencion   tipo de servicio: "Examen visual", "Entrega", "Control" (columna "Tipo")
     * @param estado         estado de la cita, controla el color de la fila (columna "Estado")
     * @param profesional    nombre del profesional asignado (columna "Profesional")
     * @param sucursal       sede donde se realiza la atencion (columna "Sucursal")
     * @param tooltip        texto informativo para mostrar como tooltip al hover
     */
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

    /**
     * Modelo de fila para el resumen semanal de la agenda.
     * <p>
     * Presenta un conteo agregado de citas por dia de la semana, mostrando totales
     * y desglose por estado. Utilizado en el panel de resumen semanal del modulo.
     * </p>
     *
     * @param dia           dia de la semana, ej. "Lunes 12/04" (columna "Dia")
     * @param totalCitas    numero total de citas programadas (columna "Total")
     * @param confirmadas   citas con estado "Confirmada" (columna "Confirmadas")
     * @param pendientes    citas con estado "Pendiente" (columna "Pendientes")
     * @param canceladas    citas con estado "Cancelada" (columna "Canceladas")
     */
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

    /**
     * Modelo de fila para la lista detallada de citas del dia.
     * <p>
     * Vista alternativa al timeline que muestra todas las citas en formato tabular con
     * informacion completa incluyendo observaciones. La fachada genera estos registros
     * a partir de las entidades {@code Cita} ordenadas por hora.
     * </p>
     *
     * @param hora        hora de la cita (columna "Hora")
     * @param cliente     nombre del paciente (columna "Cliente")
     * @param atencion    tipo de servicio solicitado (columna "Atencion")
     * @param estado      estado de la cita (columna "Estado")
     * @param profesional profesional asignado (columna "Profesional")
     * @param sucursal    sede de atencion (columna "Sucursal")
     * @param observacion notas adicionales sobre la cita (columna "Observacion")
     */
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

    /**
     * Modelo de fila para la lista de espera de citas.
     * <p>
     * Registra pacientes que solicitaron cita pero no encontraron disponibilidad.
     * El campo {@code prioridad} indica la urgencia del reagendamiento y
     * {@code estadoContacto} muestra el estado de la gestion ("Contactado",
     * "Pendiente", "Reagendado").
     * </p>
     *
     * @param cliente            nombre del paciente en lista de espera (columna "Cliente")
     * @param atencionSolicitada tipo de servicio que requiere (columna "Atencion")
     * @param franjaPreferida    horario preferido del paciente (columna "Franja")
     * @param sucursal           sede solicitada (columna "Sucursal")
     * @param prioridad          nivel de prioridad: "Alta", "Media", "Baja" (columna "Prioridad")
     * @param estadoContacto     estado de la gestion de contacto (columna "Estado Contacto")
     */
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

    /**
     * Modelo de fila para la tabla de confirmaciones de citas.
     * <p>
     * Muestra el historial de confirmaciones y recordatorios enviados a los pacientes.
     * El campo {@code estadoConfirmacion} indica si el paciente confirmo asistencia
     * ("Confirmado", "No respondio", "Cancelo").
     * </p>
     *
     * @param fecha                fecha de la cita (columna "Fecha")
     * @param hora                 hora de la cita (columna "Hora")
     * @param cliente              nombre del paciente (columna "Cliente")
     * @param atencion             tipo de servicio (columna "Atencion")
     * @param estadoConfirmacion   resultado de la confirmacion (columna "Confirmacion")
     * @param ultimoRecordatorio   fecha del ultimo recordatorio enviado (columna "Ultimo Recordatorio")
     * @param sucursal             sede de la cita (columna "Sucursal")
     */
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

    /**
     * Modelo de fila para la tabla de disponibilidad horaria.
     * <p>
     * Muestra las franjas horarias del equipo profesional, indicando si estan
     * ocupadas, libres o bloqueadas. El campo {@code estado} controla el color
     * de la fila: "Disponible" (verde), "Ocupado" (rojo), "Bloqueado" (gris).
     * </p>
     *
     * @param franja      rango horario, ej. "08:00-09:00" (columna "Franja")
     * @param tipo        tipo de disponibilidad: "Cita", "Bloqueo", "Libre" (columna "Tipo")
     * @param estado      estado de la franja (columna "Estado")
     * @param responsable profesional asignado a esa franja (columna "Responsable")
     */
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
