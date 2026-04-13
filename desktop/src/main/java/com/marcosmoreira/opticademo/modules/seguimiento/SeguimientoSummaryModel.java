package com.marcosmoreira.opticademo.modules.seguimiento;

/**
 * Summary model for the right-hand case summary panel in the Seguimiento module.
 */
public record SeguimientoSummaryModel(
        String referencia,
        String tipoSeguimiento,
        String prioridad,
        String cliente,
        String codigoCliente,
        String ultimaVisita,
        String sucursal,
        String estadoActual,
        String canalContacto,
        String ultimaInteraccion,
        String accionSugerida,
        String fechaObjetivo,
        String observacionClave
) {

    /**
     * Creates a demo summary using seed data from the blueprint.
     * SG-021 Sofia Ramirez Recall anual Recall pendiente Media 18/04/2026 Matriz Centro Asesor Molina WhatsApp
     */
    public static SeguimientoSummaryModel demoSeed() {
        return new SeguimientoSummaryModel(
                "SG-021",
                "Recall",
                "Media",
                "Sofia Ramirez",
                "CL-00124",
                "15/10/2025",
                "Matriz Centro",
                "Recall pendiente",
                "WhatsApp",
                "12/04/2026",
                "Contactar para agendar revision anual",
                "18/04/2026",
                "Receta anual proxima a vencer"
        );
    }

    /**
     * Builds a summary model from a BandejaRow.
     */
    public static SeguimientoSummaryModel fromBandeja(SeguimientoRowModel.BandejaRow row) {
        if (row == null) return demoSeed();
        return new SeguimientoSummaryModel(
                row.referencia(),
                row.tipo(),
                row.prioridad(),
                row.cliente(),
                "-",
                "-",
                row.sucursal(),
                row.estado(),
                "-",
                "-",
                "Contactar cliente",
                row.fechaObjetivo(),
                "Caso de seguimiento tipo " + row.tipo()
        );
    }
}
