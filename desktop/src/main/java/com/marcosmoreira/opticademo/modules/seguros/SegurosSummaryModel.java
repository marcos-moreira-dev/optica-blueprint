package com.marcosmoreira.opticademo.modules.seguros;

/**
 * Modelo resumen para el panel lateral derecho persistente del modulo Seguros.
 * Contiene la informacion de un caso de seguro seleccionado, incluyendo plan de
 * convenio, vigencia, estado de autorizacion, reclamo asociado y montos cubiertos.
 * La fachada (SegurosFacade) actualiza este modelo al seleccionar una verificacion
 * de cobertura en la tabla principal, mediante el metodo estatico {@code from}.
 *
 * @author Marcos Moreira
 * @version 1.0.0
 */
public record SegurosSummaryModel(
        /** Referencia unica del caso de seguro. */
        String referencia,
        /** Tipo de caso (verificacion de cobertura, reclamo, autorizacion). */
        String tipoCaso,
        /** Plan de convenio o seguro asociado. */
        String planConvenio,
        /** Nombre del cliente asegurado. */
        String cliente,
        /** Sucursal donde se gestiona el caso. */
        String sucursal,
        /** Vigencia de la cobertura del seguro. */
        String vigencia,
        /** Estado actual de la cobertura (activa, vencida, en revision). */
        String estadoActual,
        /** Referencia y estado de la autorizacion asociada. */
        String autorizacion,
        /** Referencia y estado del reclamo asociado. */
        String reclamoAsociado,
        /** Monto cubierto por el seguro. */
        String montoCubierto,
        /** Copago que debe asumir el cliente. */
        String copago,
        /** Saldo pendiente del cliente despues de aplicar la cobertura. */
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
