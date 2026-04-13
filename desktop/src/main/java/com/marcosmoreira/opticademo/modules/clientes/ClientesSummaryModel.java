package com.marcosmoreira.opticademo.modules.clientes;

import com.marcosmoreira.opticademo.shared.domain.cliente.Cliente;

/**
 * Modelo resumen para el panel lateral derecho persistente del modulo Clientes.
 * Presenta un resumen integral del cliente seleccionado, incluyendo datos personales,
 * estado comercial y metricas de actividad (ultima compra, saldo pendiente, ordenes activas).
 * La fachada (ClientesFacade) actualiza este modelo al seleccionar un cliente en la tabla
 * principal, mapeando la entidad {@link Cliente} mediante el metodo estatico {@code from}.
 *
 * @author Marcos Moreira
 * @version 1.0.0
 */
public record ClientesSummaryModel(
        /** Nombre completo del cliente. */
        String nombre,
        /** Codigo interno asignado al cliente. */
        String codigo,
        /** Estado del cliente (activo, inactivo, etc.). */
        String estado,
        /** Sucursal habitual del cliente. */
        String sucursal,
        /** Telefono de contacto del cliente. */
        String telefono,
        /** Correo electronico del cliente. */
        String email,
        /** Fecha o descripcion de la ultima compra realizada. */
        String ultimaCompra,
        /** Saldo pendiente de pago del cliente. */
        String saldoPendiente,
        /** Cantidad de ordenes activas asociadas al cliente. */
        String ordenesActivas,
        /** Cantidad de entregas pendientes del cliente. */
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
