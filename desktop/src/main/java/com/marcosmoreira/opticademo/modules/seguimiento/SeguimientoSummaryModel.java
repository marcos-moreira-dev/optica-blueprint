package com.marcosmoreira.opticademo.modules.seguimiento;

/**
 * Modelo resumen para el panel lateral derecho persistente del modulo Seguimiento.
 * Contiene la informacion de un caso de seguimiento seleccionado, incluyendo tipo
 * (recall, queja, post-venta), estado actual, canal de contacto y accion sugerida.
 * La fachada (SeguimientoFacade) actualiza este modelo al seleccionar un caso en la
 * bandeja principal, utilizando los metodos estaticos {@code fromBandeja} o {@code demoSeed}.
 *
 * @author Marcos Moreira
 * @version 1.0.0
 */
public record SeguimientoSummaryModel(
        /** Referencia unica del caso de seguimiento. */
        String referencia,
        /** Tipo de seguimiento (recall, queja, post-venta, etc.). */
        String tipoSeguimiento,
        /** Prioridad asignada al caso (alta, media, baja). */
        String prioridad,
        /** Nombre del cliente asociado al caso. */
        String cliente,
        /** Codigo interno del cliente. */
        String codigoCliente,
        /** Fecha de la ultima visita del cliente. */
        String ultimaVisita,
        /** Sucursal donde se gestiona el caso. */
        String sucursal,
        /** Estado actual del seguimiento. */
        String estadoActual,
        /** Canal de contacto utilizado (WhatsApp, llamada, email, presencial). */
        String canalContacto,
        /** Fecha de la ultima interaccion registrada. */
        String ultimaInteraccion,
        /** Accion sugerida para dar continuidad al caso. */
        String accionSugerida,
        /** Fecha objetivo para la proxima accion. */
        String fechaObjetivo,
        /** Observacion clave o resumen del caso. */
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
