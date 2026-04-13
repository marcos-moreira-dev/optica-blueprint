package com.marcosmoreira.opticademo.modules.agenda;

import com.marcosmoreira.opticademo.modules.agenda.AgendaRowModel;

/**
 * Modelo resumen para el panel lateral derecho persistente del modulo Agenda.
 * Contiene los datos de resumen de una cita seleccionada en la tabla principal.
 * La fachada (AgendaFacade) actualiza este modelo cada vez que el usuario selecciona
 * una fila en la tabla de citas del dia, invocando los metodos estaticos {@code fromCitaDia}
 * o {@code fromListaDia} segun la vista activa.
 *
 * @author Marcos Moreira
 * @version 1.0.0
 */
public record AgendaSummaryModel(
        /** Nombre completo del cliente asociado a la cita. */
        String cliente,
        /** Hora programada de la cita. */
        String hora,
        /** Tipo de atencion solicitada (examen visual, entrega, recall, etc.). */
        String atencion,
        /** Estado actual de la cita (confirmada, pendiente, cancelada, etc.). */
        String estado,
        /** Profesional asignado a la atencion. */
        String profesional,
        /** Sucursal donde se realizara la cita. */
        String sucursal,
        /** Telefono o medio de contacto del cliente. */
        String contacto,
        /** Observaciones adicionales sobre la cita. */
        String observaciones,
        /** Fecha de la cita en formato dd/MM/yyyy. */
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
