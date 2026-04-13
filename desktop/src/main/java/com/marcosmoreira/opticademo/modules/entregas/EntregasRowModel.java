package com.marcosmoreira.opticademo.modules.entregas;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Modelos de fila para las vistas del modulo Entregas (gestion de entrega de trabajos).
 * <p>
 * Estos registros alimentan los {@code TableView} del modulo: trabajos listos para
 * entrega, validacion previa, registro de retiro, trabajos pendientes, post-entrega
 * e historico. La fachada crea estas instancias a partir de las entidades
 * {@code Orden} y {@code Entrega} del dominio.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see com.marcosmoreira.opticademo.modules.entregas.EntregasFacade
 */
public final class EntregasRowModel {

    private EntregasRowModel() {
    }

    // ------------------------------------------------------------------ TrabajoListoRow

    /**
     * Modelo de fila para la tabla de trabajos listos para entrega.
     * <p>
     * Muestra los trabajos completados en taller/laboratorio que estan disponibles
     * para que el cliente los retire. El campo {@code notificacion} indica si se
     * envio un aviso al cliente ("Notificado", "Pendiente").
     * </p>
     *
     * @param referencia   identificador del trabajo (columna "Referencia")
     * @param cliente      nombre del paciente (columna "Cliente")
     * @param tipoTrabajo  tipo: "Lentes graduados", "Reparacion" (columna "Tipo")
     * @param fechaPromesa fecha prometida de entrega (columna "Fecha Promesa")
     * @param estado       estado: "Listo", "En taller" (columna "Estado")
     * @param saldo        saldo pendiente de pago (columna "Saldo")
     * @param sucursal     sede donde se retira (columna "Sucursal")
     * @param notificacion estado de notificacion al cliente (columna "Notificacion")
     */
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

    /**
     * Modelo de fila para la tabla de validacion previa a la entrega.
     * <p>
     * Registra la verificacion de calidad antes de entregar al cliente: si el trabajo
     * fue recibido conforme, si el montaje es correcto y si requiere ajustes.
     * </p>
     *
     * @param referencia       identificador del trabajo (columna "Referencia")
     * @param cliente          nombre del paciente (columna "Cliente")
     * @param tipoTrabajo      tipo de trabajo (columna "Tipo")
     * @param fechaPromesa     fecha prometida (columna "Fecha Promesa")
     * @param trabajoRecibido  si se recibio conforme: "Si", "No" (columna "Trabajo Recibido")
     * @param montajeConforme  si el montaje es correcto: "Si", "No" (columna "Montaje Conforme")
     * @param requiereAjuste   si necesita ajuste adicional (columna "Requiere Ajuste")
     * @param saldoPendiente   saldo pendiente de pago (columna "Saldo")
     */
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

    /**
     * Modelo de fila para el registro de retiro de trabajo por el cliente.
     * <p>
     * Documenta quien retiro el trabajo, cuando y si hubo observaciones.
     * </p>
     *
     * @param referencia     identificador del trabajo (columna "Referencia")
     * @param cliente        nombre del paciente (columna "Cliente")
     * @param fechaHoraRetiro fecha y hora del retiro (columna "Fecha/Hora Retiro")
     * @param retira         nombre de quien retira (columna "Retira")
     * @param documento      documento de identidad verificado (columna "Documento")
     * @param observacion    notas sobre la entrega (columna "Observacion")
     */
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

    /**
     * Modelo de fila para la tabla de trabajos pendientes de entrega.
     * <p>
     * Muestra los trabajos que aun no han sido retirados por el cliente.
     * El campo {@code diasEsperando} calcula los dias transcurridos desde
     * la fecha promesa.
     * </p>
     *
     * @param referencia     identificador (columna "Referencia")
     * @param cliente        nombre del paciente (columna "Cliente")
     * @param diasEsperando  dias desde la fecha promesa (columna "Dias Esperando")
     * @param saldo          saldo pendiente (columna "Saldo")
     * @param estado         estado actual (columna "Estado")
     * @param notificacion   estado de notificacion (columna "Notificacion")
     * @param sucursal       sede del trabajo (columna "Sucursal")
     */
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

    /**
     * Modelo de fila para el seguimiento post-entrega.
     * <p>
     * Registra acciones posteriores a la entrega: llamadas de seguimiento,
     * garantias, ajustes posteriores.
     * </p>
     *
     * @param fecha        fecha del evento post-entrega (columna "Fecha")
     * @param referencia   identificador del trabajo (columna "Referencia")
     * @param tipo         tipo: "Seguimiento", "Garantia", "Ajuste" (columna "Tipo")
     * @param estado       estado de la gestion (columna "Estado")
     * @param responsable  quien realizo la accion (columna "Responsable")
     */
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

    /**
     * Modelo de fila para el historico de entregas.
     * <p>
     * Archivo de todas las entregas realizadas con su estado final y observaciones.
     * </p>
     *
     * @param fecha        fecha de entrega (columna "Fecha")
     * @param referencia   identificador del trabajo (columna "Referencia")
     * @param cliente      nombre del paciente (columna "Cliente")
     * @param estadoFinal  resultado: "Entregado", "No retirado", "Anulado" (columna "Estado Final")
     * @param saldo        saldo final despues de la entrega (columna "Saldo")
     * @param sucursal     sede de entrega (columna "Sucursal")
     * @param observacion  notas finales sobre la entrega (columna "Observacion")
     */
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
