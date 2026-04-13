package com.marcosmoreira.opticademo.modules.compras;

import com.marcosmoreira.opticademo.shared.util.StringUtils;

/**
 * Summary model for the right-hand purchase detail panel in the Compras module.
 */
public record ComprasSummaryModel(
        String referencia,
        String tipoRegistro,
        String estado,
        String proveedor,
        String categoriaPrincipal,
        String tiempoEstimado,
        String sucursalDestino,
        String totalItems,
        String totalEstimado,
        String recepcionVinculada,
        String diferencias,
        String observacionClave
) {

    public static ComprasSummaryModel fromOrden(ComprasRowModel.OrdenCompraRow orden) {
        return new ComprasSummaryModel(
                orden.orden(),
                "Orden de compra",
                orden.estado(),
                orden.proveedor(),
                "-",
                "-",
                orden.sucursalDestino(),
                orden.items(),
                orden.totalEstimado(),
                "Pendiente",
                "Sin diferencias",
                "Orden registrada"
        );
    }

    public static ComprasSummaryModel fromSolicitud(ComprasRowModel.SolicitudRow solicitud) {
        return new ComprasSummaryModel(
                solicitud.solicitud(),
                "Solicitud de compra",
                solicitud.estado(),
                solicitud.proveedorSugerido(),
                solicitud.categoria(),
                "-",
                solicitud.sucursalDestino(),
                "-",
                "-",
                "Sin recepcion",
                "-",
                "Solicitud registrada"
        );
    }

    public static ComprasSummaryModel empty() {
        return new ComprasSummaryModel(
                "Sin seleccion",
                "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"
        );
    }
}
