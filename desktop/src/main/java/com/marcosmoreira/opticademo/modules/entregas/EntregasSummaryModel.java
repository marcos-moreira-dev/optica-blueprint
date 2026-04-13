package com.marcosmoreira.opticademo.modules.entregas;

import com.marcosmoreira.opticademo.shared.domain.venta.VentaOptica;

/**
 * Modelo resumen para el panel lateral derecho persistente del modulo Entregas.
 * Presenta el estado de entrega de una orden, incluyendo tipo de trabajo, fechas
 * de promesa y recepcion, estado de notificacion al cliente y saldos pendientes.
 * La fachada (EntregasFacade) actualiza este modelo al seleccionar una orden en la
 * tabla principal, derivando los valores de la entidad {@link VentaOptica} mediante
 * el metodo estatico {@code from}.
 *
 * @author Marcos Moreira
 * @version 1.0.0
 */
public record EntregasSummaryModel(
        /** Referencia unica de la orden de entrega. */
        String referencia,
        /** Tipo de trabajo entregado (montura+lente, solo lente, montura). */
        String tipoTrabajo,
        /** Sucursal donde se realiza la entrega. */
        String sucursal,
        /** Nombre del cliente que recibe la entrega. */
        String cliente,
        /** Codigo interno del cliente. */
        String codigoCliente,
        /** Fecha prometida de entrega. */
        String fechaPromesa,
        /** Fecha de recepcion real del trabajo del laboratorio. */
        String fechaRecepcion,
        /** Estado actual de la entrega (lista para entrega, entregada, pendiente). */
        String estadoEntrega,
        /** Estado de notificacion al cliente (notificado por SMS, pendiente, etc.). */
        String notificacion,
        /** Prioridad de la entrega. */
        String prioridad,
        /** Saldo pendiente de pago al momento de la entrega. */
        String saldoPendiente,
        /** Estado del cobro asociado a la entrega. */
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
