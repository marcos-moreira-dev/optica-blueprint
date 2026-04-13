package com.marcosmoreira.opticademo.modules.seguros;

/**
 * Summary model for the right-hand coverage case summary panel in the Seguros module.
 */
public record SegurosSummaryModel(
        String referencia,
        String tipoCaso,
        String planConvenio,
        String cliente,
        String sucursal,
        String vigencia,
        String estadoActual,
        String autorizacion,
        String reclamoAsociado,
        String montoCubierto,
        String copago,
        String saldoCliente
) {

    /**
     * Creates a demo summary using seed data from the blueprint.
     */
    public static SegurosSummaryModel demoSeed() {
        return new SegurosSummaryModel(
                "CB-001",
                "Verificacion de cobertura",
                "Convenio Visual Plus",
                "Sofia Ramirez",
                "Matriz Centro",
                "31/12/2026",
                "Cobertura activa",
                "AU-014 - Autorizada",
                "RC-022 - Reclamo enviado",
                "$80.00",
                "$10.00",
                "$0.00"
        );
    }

    /**
     * Builds a SegurosSummaryModel from a VerificacionRow.
     */
    public static SegurosSummaryModel from(SegurosRowModel.VerificacionRow verificacion) {
        return new SegurosSummaryModel(
                verificacion.referencia(),
                "Verificacion de cobertura",
                verificacion.planConvenio(),
                verificacion.cliente(),
                verificacion.sucursal(),
                verificacion.vigencia(),
                verificacion.estado(),
                "Sin autorizacion",
                "Sin reclamo",
                verificacion.montoDisponible(),
                "-",
                "-"
        );
    }
}
