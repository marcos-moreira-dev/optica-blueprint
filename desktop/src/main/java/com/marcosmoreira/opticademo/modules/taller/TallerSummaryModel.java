package com.marcosmoreira.opticademo.modules.taller;

/**
 * Modelo resumen para el panel lateral derecho persistente del modulo Taller.
 * Contiene la informacion detallada de un trabajo tecnico seleccionado, incluyendo
 * tipo de intervencion (reparacion, ajuste, diagnostico), prioridad, tecnico
 * responsable, fechas de ingreso y promesa, y repuestos requeridos.
 * La fachada (TallerFacade) actualiza este modelo al seleccionar un ingreso en la
 * tabla principal, mediante el metodo estatico {@code from}.
 *
 * @author Marcos Moreira
 * @version 1.0.0
 */
public record TallerSummaryModel(
        /** Referencia unica del trabajo de taller. */
        String referencia,
        /** Tipo de intervencion tecnica (reparacion bisagra, ajuste, soldadura, etc.). */
        String tipoIntervencion,
        /** Prioridad del trabajo (alta, media, baja). */
        String prioridad,
        /** Nombre del cliente propietario de la montura. */
        String cliente,
        /** Sucursal donde se recibe el trabajo. */
        String sucursal,
        /** Fecha de ingreso del trabajo al taller. */
        String fechaIngreso,
        /** Estado actual del trabajo tecnico. */
        String estadoActual,
        /** Tecnico responsable de la intervencion. */
        String tecnicoResponsable,
        /** Fecha prometida de finalizacion del trabajo. */
        String fechaPromesa,
        /** Repuesto requerido para la reparacion. */
        String repuestoRequerido,
        /** Indica si la montura fue enviada a un taller externo. */
        String envioExterno,
        /** Observacion breve sobre el trabajo tecnico. */
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
