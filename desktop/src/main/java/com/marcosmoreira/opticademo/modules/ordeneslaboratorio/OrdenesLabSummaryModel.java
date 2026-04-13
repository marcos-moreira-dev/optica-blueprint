package com.marcosmoreira.opticademo.modules.ordeneslaboratorio;

import com.marcosmoreira.opticademo.shared.domain.venta.VentaOptica;

/**
 * Summary model for the right-hand detail panel of a selected lab order.
 */
public record OrdenesLabSummaryModel(
        String referencia,
        String tipoTrabajo,
        String prioridad,
        String cliente,
        String codigoCliente,
        String sucursal,
        String fechaPromesa,
        String montura,
        String lente,
        String tratamientos,
        String pd,
        String estadoActual,
        String laboratorio,
        String guia,
        String recepcionEsperada
) {

    /**
     * Creates an OrdenesLabSummaryModel from a VentaOptica entity.
     */
    public static OrdenesLabSummaryModel from(VentaOptica venta) {
        return new OrdenesLabSummaryModel(
                venta.getReferencia(),
                venta.getLenteTipo(),
                "Normal",
                venta.getClienteNombre(),
                venta.getClienteId(),
                venta.getSucursal(),
                venta.getFechaPromesa(),
                venta.getMonturaRef() != null ? venta.getMonturaRef() : "-",
                venta.getLenteTipo(),
                "Ninguno",
                "-",
                venta.getEstado() != null ? venta.getEstado().name() : "En proceso",
                venta.getLaboratorio() != null ? venta.getLaboratorio() : "-",
                "-",
                venta.getFechaPromesa()
        );
    }
}
