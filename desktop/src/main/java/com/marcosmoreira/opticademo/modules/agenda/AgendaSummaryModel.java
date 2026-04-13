package com.marcosmoreira.opticademo.modules.agenda;

/**
 * Summary model for the right-hand appointment detail panel in the Agenda module.
 */
public record AgendaSummaryModel(
        String cliente,
        String hora,
        String atencion,
        String estado,
        String profesional,
        String sucursal,
        String contacto,
        String observaciones,
        String fechaCita
) {

    /**
     * Creates a demo summary using seed data from the blueprint.
     */
    public static AgendaSummaryModel demoSeed() {
        return new AgendaSummaryModel(
                "Sofia Ramirez",
                "09:00",
                "Examen visual",
                "Confirmada",
                "Dra. Salazar",
                "Matriz Centro",
                "099-123-4567",
                "Paciente refiere leve molestia en ojo izquierdo",
                "12/04/2026"
        );
    }

    /**
     * Builds a summary model from a CitaDiaRow.
     */
    public static AgendaSummaryModel fromCitaDia(AgendaRowModel.CitaDiaRow row) {
        if (row == null) return demoSeed();
        return new AgendaSummaryModel(
                row.cliente(),
                row.hora(),
                row.tipoAtencion(),
                row.estado(),
                row.profesional(),
                row.sucursal(),
                "-",
                "Cita de " + row.tipoAtencion() + " para " + row.cliente(),
                "12/04/2026"
        );
    }

    /**
     * Builds a summary model from a ListaDiaRow.
     */
    public static AgendaSummaryModel fromListaDia(AgendaRowModel.ListaDiaRow row) {
        if (row == null) return demoSeed();
        return new AgendaSummaryModel(
                row.cliente(),
                row.hora(),
                row.atencion(),
                row.estado(),
                row.profesional(),
                row.sucursal(),
                "-",
                row.observacion(),
                "12/04/2026"
        );
    }
}
