package com.marcosmoreira.opticademo.modules.compras;

import com.marcosmoreira.opticademo.shared.util.StringUtils;

/**
 * Modelo resumen para el panel lateral derecho persistente del modulo Compras.
 * Contiene el detalle de una orden o solicitud de compra seleccionada, incluyendo
 * proveedor, categoria principal, totales estimados y estado de recepcion.
 * La fachada (ComprasFacade) actualiza este modelo al seleccionar una orden de compra
 * o solicitud en la tabla principal, mediante los metodos estaticos {@code fromOrden}
 * o {@code fromSolicitud}.
 *
 * @author Marcos Moreira
 * @version 1.0.0
 */
public record ComprasSummaryModel(
        /** Referencia de la orden o solicitud de compra. */
        String referencia,
        /** Tipo de registro (orden de compra, solicitud de compra). */
        String tipoRegistro,
        /** Estado actual del proceso de compra. */
        String estado,
        /** Proveedor asociado a la compra. */
        String proveedor,
        /** Categoria principal de productos adquiridos. */
        String categoriaPrincipal,
        /** Tiempo estimado de entrega del proveedor. */
        String tiempoEstimado,
        /** Sucursal destino de la mercaderia. */
        String sucursalDestino,
        /** Cantidad total de items en la compra. */
        String totalItems,
        /** Total estimado de la compra. */
        String totalEstimado,
        /** Recepcion vinculada a la orden (si ya fue recibida). */
        String recepcionVinculada,
        /** Diferencias encontradas entre lo ordenado y lo recibido. */
        String diferencias,
        /** Observacion clave sobre la compra. */
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
