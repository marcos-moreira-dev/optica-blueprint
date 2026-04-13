package com.marcosmoreira.opticademo.modules.inventario;

import com.marcosmoreira.opticademo.shared.domain.producto.Producto;
import com.marcosmoreira.opticademo.shared.util.MoneyUtils;
import com.marcosmoreira.opticademo.shared.util.StringUtils;

/**
 * Summary model for the right-hand product detail panel in the Inventario module.
 */
public record InventarioSummaryModel(
        String referencia,
        String nombre,
        String categoria,
        String marca,
        String estadoStock,
        String stockActual,
        String sucursal,
        String ubicacion,
        String precioVenta,
        String ultimaSalida,
        String proveedorPrincipal,
        String ultimaRecepcion,
        String estadoReposicion
) {

    public static InventarioSummaryModel from(Producto producto) {
        String estadoStr = producto.getEstado() != null ? producto.getEstado().name() : "ACTIVO";
        String estadoStock = switch (estadoStr) {
            case "AGOTADO" -> "Agotado";
            case "BAJO_STOCK" -> "Bajo stock";
            case "ACTIVO" -> "Normal";
            default -> estadoStr;
        };

        return new InventarioSummaryModel(
                producto.getReferencia(),
                producto.getNombre(),
                StringUtils.defaultIfBlank(producto.getCategoria(), "-"),
                StringUtils.defaultIfBlank(producto.getMarca(), "-"),
                estadoStock,
                String.valueOf(producto.getStock()),
                StringUtils.defaultIfBlank(producto.getSucursal(), "-"),
                "Estante A-" + (producto.getStock() % 10 + 1),
                MoneyUtils.formatOrDefault(producto.getPrecioVenta()),
                "Sin registros recientes",
                StringUtils.defaultIfBlank(producto.getProveedorPrincipal(), "-"),
                "Sin recepcion reciente",
                "Sin reposicion pendiente"
        );
    }
}
