package com.marcosmoreira.opticademo.modules.seguimiento;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Modelos de fila para las vistas del modulo Seguimiento (gestion de seguimiento
 * y retencion de clientes).
 * <p>
 * Estos registros alimentan los {@code TableView} del modulo: bandeja de seguimiento,
 * recalls (recordatorios de revision), no retirados, cobros pendientes, mensajes
 * enviados e historico. La fachada crea estas instancias para dar trazabilidad a
 * las interacciones post-venta con los clientes.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see com.marcosmoreira.opticademo.modules.seguimiento.SeguimientoFacade
 */
public final class SeguimientoRowModel {

    private SeguimientoRowModel() {
    }

    // ------------------------------------------------------------------ BandejaRow

    /**
     * Modelo de fila para la bandeja principal de seguimiento.
     * <p>
     * Vista central que lista todos los casos activos que requieren seguimiento.
     * El campo {@code prioridad} determina el color de la fila en la UI.
     * </p>
     *
     * @param referencia    identificador del caso (columna "Referencia")
     * @param cliente       nombre del paciente (columna "Cliente")
     * @param tipo          tipo: "Recall", "No retirado", "Cobro", "Garantia" (columna "Tipo")
     * @param estado        estado del seguimiento (columna "Estado")
     * @param fechaObjetivo fecha limite para la accion (columna "Fecha Objetivo")
     * @param prioridad     prioridad: "Alta", "Media", "Baja" (columna "Prioridad")
     * @param sucursal      sede responsable (columna "Sucursal")
     * @param responsable   asignado al caso (columna "Responsable")
     */
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

    /**
     * Modelo de fila para la tabla de recalls (recordatorios de revision).
     * <p>
     * Lista pacientes que deben ser contactados para una revision periodica.
     * </p>
     *
     * @param cliente        nombre del paciente (columna "Cliente")
     * @param ultimaVisita   fecha de la ultima visita (columna "Ultima Visita")
     * @param motivo         motivo del recall: "Revision anual", "Control" (columna "Motivo")
     * @param fechaSugerida  fecha sugerida para la cita (columna "Fecha Sugerida")
     * @param estado         estado: "Pendiente", "Contactado", "Agendado" (columna "Estado")
     * @param sucursal       sede de seguimiento (columna "Sucursal")
     */
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

    /**
     * Modelo de fila para la tabla de trabajos no retirados.
     * <p>
     * Muestra ordenes completadas que el cliente no ha retirado. El campo
     * {@code notificacion} indica el estado del aviso al cliente.
     * </p>
     *
     * @param orden          numero de orden (columna "Orden")
     * @param cliente        nombre del paciente (columna "Cliente")
     * @param diasEsperando  dias transcurridos desde que esta listo (columna "Dias")
     * @param notificacion   estado de notificacion (columna "Notificacion")
     * @param saldo          saldo pendiente (columna "Saldo")
     * @param estado         estado del retiro (columna "Estado")
     * @param sucursal       sede del trabajo (columna "Sucursal")
     */
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

    /**
     * Modelo de fila para la tabla de cobros pendientes de seguimiento.
     * <p>
     * Lista ordenes con saldo pendiente y la proxima accion de cobro planificada.
     * </p>
     *
     * @param orden         numero de orden (columna "Orden")
     * @param cliente       nombre del paciente (columna "Cliente")
     * @param saldo         monto pendiente (columna "Saldo")
     * @param ultimoPago    fecha del ultimo pago (columna "Ultimo Pago")
     * @param estado        estado del cobro (columna "Estado")
     * @param proximaAccion proxima gestion planificada (columna "Proxima Accion")
     * @param sucursal      sede de la orden (columna "Sucursal")
     */
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

    /**
     * Modelo de fila para el registro de mensajes enviados al cliente.
     * <p>
     * Historial de comunicaciones: SMS, emails, llamadas realizadas como parte
     * del seguimiento. El campo {@code resultado} indica si el mensaje fue
     * entregado exitosamente.
     * </p>
     *
     * @param fecha     fecha de envio (columna "Fecha")
     * @param cliente   nombre del paciente (columna "Cliente")
     * @param tipo      tipo: "SMS", "Email", "Llamada" (columna "Tipo")
     * @param canal     canal utilizado (columna "Canal")
     * @param estado    estado del envio (columna "Estado")
     * @param resultado resultado: "Entregado", "Fallido", "Sin respuesta" (columna "Resultado")
     */
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

    /**
     * Modelo de fila para el historico de seguimiento.
     * <p>
     * Archivo de todos los casos de seguimiento completados con su resultado final.
     * </p>
     *
     * @param fecha        fecha del caso (columna "Fecha")
     * @param referencia   identificador (columna "Referencia")
     * @param cliente      nombre del paciente (columna "Cliente")
     * @param tipo         tipo de caso (columna "Tipo")
     * @param estadoFinal  resultado final (columna "Estado Final")
     * @param resultado    descripcion del resultado (columna "Resultado")
     * @param sucursal     sede responsable (columna "Sucursal")
     */
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
