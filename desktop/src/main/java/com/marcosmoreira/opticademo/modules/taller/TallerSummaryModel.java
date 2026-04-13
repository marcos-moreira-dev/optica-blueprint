package com.marcosmoreira.opticademo.modules.taller;

/**
 * Summary model for the right-hand technical work summary panel in the Taller module.
 */
public record TallerSummaryModel(
        String referencia,
        String tipoIntervencion,
        String prioridad,
        String cliente,
        String sucursal,
        String fechaIngreso,
        String estadoActual,
        String tecnicoResponsable,
        String fechaPromesa,
        String repuestoRequerido,
        String envioExterno,
        String observacionBreve
) {

    /**
     * Creates a demo summary using seed data from the blueprint.
     */
    public static TallerSummaryModel demoSeed() {
        return new TallerSummaryModel(
                "TR-001",
                "Reparacion bisagra",
                "MEDIA",
                "Sofia Ramirez",
                "Matriz Centro",
                "14/04/2026",
                "En diagnostico",
                "Tecnico Rivera",
                "18/04/2026",
                "No definido",
                "No",
                "Bisagra suelta en montura metalica; evaluar soldadura o cambio de pieza."
        );
    }

    /**
     * Builds a TallerSummaryModel from an IngresoRow.
     */
    public static TallerSummaryModel from(TallerRowModel.IngresoRow ingreso) {
        String prioridad = switch (ingreso.estado().toLowerCase()) {
            case "urgente", "en reparacion urgente" -> "ALTA";
            case "en diagnostico", "pendiente" -> "MEDIA";
            default -> "BAJA";
        };

        return new TallerSummaryModel(
                ingreso.referencia(),
                ingreso.tipo(),
                prioridad,
                ingreso.cliente(),
                ingreso.sucursal(),
                "",
                ingreso.estado(),
                ingreso.tecnico(),
                ingreso.fechaPromesa(),
                "No definido",
                "No",
                "Trabajo tecnico en proceso."
        );
    }
}
