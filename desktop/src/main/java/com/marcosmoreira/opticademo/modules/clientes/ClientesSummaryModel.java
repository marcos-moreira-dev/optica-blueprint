package com.marcosmoreira.opticademo.modules.clientes;

import com.marcosmoreira.opticademo.shared.domain.cliente.Cliente;

/**
 * Summary model for the right-hand detail panel of a selected client.
 */
public record ClientesSummaryModel(
        String nombre,
        String codigo,
        String estado,
        String sucursal,
        String telefono,
        String email,
        String ultimaCompra,
        String saldoPendiente,
        String ordenesActivas,
        String entregasPendientes
) {

    /**
     * Creates a ClientesSummaryModel from a Cliente entity and related data.
     * In a real app this would join ventas, ordenes, entregas, etc.
     * For the demo we return placeholder values.
     */
    public static ClientesSummaryModel from(Cliente cliente) {
        return new ClientesSummaryModel(
                cliente.getNombreCompleto(),
                cliente.getCodigoInterno(),
                cliente.getEstado() != null ? cliente.getEstado().name() : "ACTIVO",
                cliente.getSucursalHabitual(),
                cliente.getTelefono(),
                cliente.getEmail(),
                "Sin compras recientes",
                "$0.00",
                "0",
                "0"
        );
    }
}
