package com.marcosmoreira.opticademo.modules.entregas;

import com.marcosmoreira.opticademo.shared.domain.venta.VentaOptica;

/**
 * Summary model for the right-hand delivery summary panel in the Entregas module.
 */
public record EntregasSummaryModel(
        String referencia,
        String tipoTrabajo,
        String sucursal,
        String cliente,
        String codigoCliente,
        String fechaPromesa,
        String fechaRecepcion,
        String estadoEntrega,
        String notificacion,
        String prioridad,
        String saldoPendiente,
        String estadoCobro
) {

    /**
     * Builds an EntregasSummaryModel from a VentaOptica entity.
     * For the demo we derive values directly from the venta.
     */
    public static EntregasSummaryModel from(VentaOptica venta) {
        String tipoTrabajo = determineTipoTrabajo(venta);
        String estadoEntrega = determineEstadoEntrega(venta);
        String estadoCobro = venta.getSaldo() <= 0 ? "Pagado" : "Con saldo pendiente";
        String notificacion = "Pendiente de notificacion";
        String prioridad = "Media";

        return new EntregasSummaryModel(
                venta.getReferencia() != null ? venta.getReferencia() : "-",
                tipoTrabajo,
                venta.getSucursal() != null ? venta.getSucursal() : "-",
                venta.getClienteNombre() != null ? venta.getClienteNombre() : "-",
                extractClienteCodigo(venta.getClienteId()),
                venta.getFechaPromesa() != null ? venta.getFechaPromesa() : "-",
                venta.getFechaOrden() != null ? venta.getFechaOrden() : "-",
                estadoEntrega,
                notificacion,
                prioridad,
                formatMoney(venta.getSaldo()),
                estadoCobro
        );
    }

    /**
     * Creates a demo summary using seed data from the blueprint.
     * ET-041 Sofia Ramirez Montura+lente Lista para entrega $0.00 Matriz Centro Notificado por SMS
     */
    public static EntregasSummaryModel demoSeed() {
        return new EntregasSummaryModel(
                "ET-041",
                "Montura + lente",
                "Matriz Centro",
                "Sofia Ramirez",
                "CL-00124",
                "16/04/2026",
                "15/04/2026",
                "Lista para entrega",
                "Notificado por SMS",
                "Media",
                "$0.00",
                "Pagado"
        );
    }

    private static String determineTipoTrabajo(VentaOptica venta) {
        String lente = venta.getLenteTipo();
        String montura = venta.getMonturaRef();
        if (lente != null && !lente.isBlank() && montura != null && !montura.isBlank()) {
            return "Montura + lente";
        } else if (lente != null && !lente.isBlank()) {
            return "Solo lente";
        }
        return "Montura";
    }

    private static String determineEstadoEntrega(VentaOptica venta) {
        return switch (venta.getEstado()) {
            case LISTO -> "Lista para entrega";
            case EN_PROCESO -> "Pendiente de validacion";
            case ENTREGADO, COMPLETADO -> "Entregada";
            case EN_ESPERA -> "Pendiente de retiro";
            case CONFIRMADO -> "Notificado";
            default -> "Pendiente de validacion";
        };
    }

    private static String extractClienteCodigo(String clienteId) {
        if (clienteId == null || clienteId.isBlank()) {
            return "-";
        }
        return clienteId;
    }

    private static String formatMoney(double value) {
        return String.format("$%.2f", value);
    }
}
