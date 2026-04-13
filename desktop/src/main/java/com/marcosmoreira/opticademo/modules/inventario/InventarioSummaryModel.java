package com.marcosmoreira.opticademo.modules.inventario;

import com.marcosmoreira.opticademo.shared.domain.producto.Producto;
import com.marcosmoreira.opticademo.shared.util.MoneyUtils;
import com.marcosmoreira.opticademo.shared.util.StringUtils;

/**
 * Modelo resumen para el panel lateral derecho persistente del modulo Inventario.
 * Presenta el detalle completo de un producto seleccionado, incluyendo datos de
 * clasificacion, estado de stock, ubicacion fisica y proveedor principal.
 * La fachada (InventarioFacade) actualiza este modelo al seleccionar un producto
 * en la tabla principal, mapeando la entidad {@link Producto} mediante el metodo
 * estatico {@code from}.
 *
 * @author Marcos Moreira
 * @version 1.0.0
 */
public record InventarioSummaryModel(
        /** Codigo de referencia del producto. */
        String referencia,
        /** Nombre descriptivo del producto. */
        String nombre,
        /** Categoria del producto (montura, lente, accesorio, etc.). */
        String categoria,
        /** Marca comercial del producto. */
        String marca,
        /** Estado del stock (normal, bajo stock, agotado). */
        String estadoStock,
        /** Cantidad actual de unidades en inventario. */
        String stockActual,
        /** Sucursal donde se encuentra el producto. */
        String sucursal,
        /** Ubicacion fisica dentro de la sucursal (estante, seccion). */
        String ubicacion,
        /** Precio de venta unitario del producto. */
        String precioVenta,
        /** Fecha de la ultima salida registrada del producto. */
        String ultimaSalida,
        /** Proveedor principal del producto. */
        String proveedorPrincipal,
        /** Fecha de la ultima recepcion del producto. */
        String ultimaRecepcion,
        /** Estado de reposicion (si hay pedido pendiente de reabastecimiento). */
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
