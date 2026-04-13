package com.marcosmoreira.opticademo.modules.caja;

import com.marcosmoreira.opticademo.shared.domain.venta.VentaOptica;

/**
 * Summary model for the right-hand payment summary panel in the Caja module.
 */
public record CajaSummaryModel(
        String referenciaOrden,
        String cliente,
        String codigoCliente,
        String fechaPromesa,
        String subtotal,
        String descuento,
        String abonoAcumulado,
        String saldo,
        String estadoCobro,
        String metodoPrincipal,
        String comprobante
) {

    /**
     * Builds a CajaSummaryModel from a VentaOptica entity and related cobro data.
     * For the demo we derive values directly from the venta.
     */
    public static CajaSummaryModel from(VentaOptica venta) {
        double subtotal = venta.getPrecioMontura() + venta.getPrecioLente();
        double descuento = venta.getDescuento();
        double abonado = venta.getAbono();
        double saldo = venta.getSaldo();

        String estadoCobro = determineEstadoCobro(venta, saldo);
        String metodoPrincipal = venta.getAbono() > 0 ? "EFECTIVO" : "PENDIENTE";
        String comprobante = venta.getAbono() > 0 ? "FAC-2026-0184" : "Sin comprobante";

        return new CajaSummaryModel(
                venta.getReferencia() != null ? venta.getReferencia() : "-",
                venta.getClienteNombre() != null ? venta.getClienteNombre() : "-",
                extractClienteCodigo(venta.getClienteId()),
                venta.getFechaPromesa() != null ? venta.getFechaPromesa() : "-",
                formatMoney(subtotal),
                formatMoney(descuento),
                formatMoney(abonado),
                formatMoney(saldo),
                estadoCobro,
                metodoPrincipal,
                comprobante
        );
    }

    /**
     * Creates a demo summary using seed data from the blueprint.
     * OV-124 Sofia Ramirez $105 subtotal $5 descuento $50 abonado $55 saldo
     */
    public static CajaSummaryModel demoSeed() {
        return new CajaSummaryModel(
                "OV-124",
                "Sofia Ramirez",
                "CL-00124",
                "18/04/2026",
                "$105.00",
                "$5.00",
                "$50.00",
                "$55.00",
                "PENDIENTE",
                "EFECTIVO",
                "FAC-2026-0184"
        );
    }

    private static String determineEstadoCobro(VentaOptica venta, double saldo) {
        if (saldo <= 0) {
            return "PAGADO";
        }
        return switch (venta.getEstado()) {
            case EN_PROCESO -> "POR_COBRAR";
            case LISTO -> "LISTO_PARA_ENTREGA";
            case ENTREGADO, COMPLETADO -> "PAGADO";
            case EN_ESPERA -> "EN_ESPERA";
            default -> "PENDIENTE";
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
