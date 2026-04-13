package com.marcosmoreira.opticademo.modules.notificaciones;

/**
 * Summary model for the right-hand detail panel of the Notificaciones module.
 */
public record NotificacionesSummaryModel(
        String referencia,
        String tipo,
        String canal,
        String cliente,
        String ordenRelacionada,
        String sucursal,
        String estadoActual,
        String fechaHora,
        String moduloOrigen,
        String accionSugerida,
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
