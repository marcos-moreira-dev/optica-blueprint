package com.marcosmoreira.opticademo.modules.notificaciones;

/**
 * Modelo resumen para el panel lateral derecho persistente del modulo Notificaciones.
 * Contiene los datos de una notificacion seleccionada, incluyendo tipo, canal de envio,
 * cliente destinatario, orden relacionada, estado actual y modulo de origen.
 * La fachada (NotificacionesFacade) actualiza este modelo al seleccionar una notificacion
 * en la bandeja principal, mediante el metodo estatico {@code fromBandeja}.
 *
 * @author Marcos Moreira
 * @version 1.0.0
 */
public record NotificacionesSummaryModel(
        /** Referencia unica de la notificacion. */
        String referencia,
        /** Tipo de notificacion (recordatorio, confirmacion, alerta, etc.). */
        String tipo,
        /** Canal de envio (SMS, WhatsApp, email, push). */
        String canal,
        /** Nombre del cliente o caso destinatario. */
        String cliente,
        /** Referencia de la orden relacionada con la notificacion. */
        String ordenRelacionada,
        /** Sucursal asociada a la notificacion. */
        String sucursal,
        /** Estado actual de la notificacion (enviada, pendiente, fallida, etc.). */
        String estadoActual,
        /** Fecha y hora de la notificacion. */
        String fechaHora,
        /** Modulo del sistema que origino la notificacion. */
        String moduloOrigen,
        /** Accion sugerida tras revisar la notificacion. */
        String accionSugerida,
        /** Observacion clave o detalle adicional de la notificacion. */
        String observacionClave
) {

    public static NotificacionesSummaryModel fromBandeja(NotificacionesRowModel.BandejaRow row) {
        return new NotificacionesSummaryModel(
                row.referencia(),
                row.tipo(),
                row.canal(),
                row.clienteOCaso(),
                "-",
                "-",
                row.estado(),
                row.fecha(),
                row.moduloOrigen(),
                "Revisar detalle",
                ""
        );
    }

    public static NotificacionesSummaryModel empty() {
        return new NotificacionesSummaryModel(
                "", "", "", "", "", "", "", "", "", "", "Sin seleccion"
        );
    }
}
