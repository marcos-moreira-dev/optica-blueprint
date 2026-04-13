package com.marcosmoreira.opticademo.modules.caja;

import com.marcosmoreira.opticademo.shared.domain.venta.VentaOptica;

/**
 * Modelo resumen para el panel lateral derecho persistente del modulo Caja.
 * Contiene el resumen de cobro de una orden seleccionada, incluyendo montos
 * (subtotal, descuento, abono, saldo), metodo de pago principal y referencia
 * del comprobante. La fachada (CajaFacade) actualiza este modelo al seleccionar
 * una orden pendiente de cobro, derivando los valores de la entidad {@link VentaOptica}
 * mediante el metodo estatico {@code from}.
 *
 * @author Marcos Moreira
 * @version 1.0.0
 */
public record CajaSummaryModel(
        /** Referencia de la orden de venta asociada al cobro. */
        String referenciaOrden,
        /** Nombre del cliente que realiza el pago. */
        String cliente,
        /** Codigo interno del cliente. */
        String codigoCliente,
        /** Fecha promesa de la orden. */
        String fechaPromesa,
        /** Subtotal de la venta antes de descuentos. */
        String subtotal,
        /** Monto del descuento aplicado. */
        String descuento,
        /** Abono acumulado hasta la fecha. */
        String abonoAcumulado,
        /** Saldo restante por cobrar. */
        String saldo,
        /** Estado del cobro (pagado, por cobrar, pendiente, etc.). */
        String estadoCobro,
        /** Metodo de pago principal utilizado (efectivo, tarjeta, transferencia). */
        String metodoPrincipal,
        /** Numero o referencia del comprobante generado. */
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
