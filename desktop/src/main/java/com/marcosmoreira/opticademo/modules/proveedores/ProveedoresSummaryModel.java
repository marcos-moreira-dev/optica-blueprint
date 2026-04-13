package com.marcosmoreira.opticademo.modules.proveedores;

import com.marcosmoreira.opticademo.shared.domain.proveedor.Proveedor;
import com.marcosmoreira.opticademo.shared.util.StringUtils;

/**
 * Modelo resumen para el panel lateral derecho persistente del modulo Proveedores.
 * Presenta el perfil integral de un proveedor seleccionado, incluyendo datos de
 * contacto, categoria de abastecimiento, tiempos de entrega e incidencias recientes.
 * La fachada (ProveedoresFacade) actualiza este modelo al seleccionar un proveedor
 * en la tabla principal, mapeando la entidad {@link Proveedor} mediante el metodo
 * estatico {@code from}.
 *
 * @author Marcos Moreira
 * @version 1.0.0
 */
public record ProveedoresSummaryModel(
        /** Nombre comercial del proveedor. */
        String proveedor,
        /** Tipo principal del proveedor (monturas, lentes, insumos, etc.). */
        String tipoPrincipal,
        /** Estado del proveedor (activo, inactivo, bajo observacion). */
        String estado,
        /** Persona de contacto en el proveedor. */
        String contacto,
        /** Telefono de contacto del proveedor. */
        String telefono,
        /** Correo electronico del proveedor. */
        String correo,
        /** Categoria de productos que abastece el proveedor. */
        String categoriaAbastecida,
        /** Sucursales que son atendidas por este proveedor. */
        String sucursalesAtendidas,
        /** Fecha de la ultima recepcion de mercaderia. */
        String ultimaRecepcion,
        /** Tiempo estimado de entrega en dias. */
        String tiempoEstimado,
        /** Incidencias recientes reportadas con el proveedor. */
        String incidenciasRecientes,
        /** Observacion clave o resumen del proveedor. */
        String observacionClave
) {

    public static ProveedoresSummaryModel from(Proveedor proveedor) {
        String estadoStr = proveedor.getEstado() != null ? proveedor.getEstado().name() : "ACTIVO";
        String estadoDisplay = switch (estadoStr) {
            case "ACTIVO" -> "Activo";
            case "INACTIVO" -> "Inactivo";
            case "BAJO_OBSERVACION" -> "Bajo observacion";
            case "PENDIENTE" -> "Pendiente";
            default -> estadoStr;
        };

        return new ProveedoresSummaryModel(
                StringUtils.defaultIfBlank(proveedor.getNombreComercial(), "-"),
                StringUtils.defaultIfBlank(proveedor.getTipoProveedor(), "-"),
                estadoDisplay,
                StringUtils.defaultIfBlank(proveedor.getContacto(), "-"),
                StringUtils.defaultIfBlank(proveedor.getTelefono(), "-"),
                StringUtils.defaultIfBlank(proveedor.getCorreo(), "-"),
                StringUtils.defaultIfBlank(proveedor.getCategoriaAbastecida(), "-"),
                StringUtils.defaultIfBlank(proveedor.getSucursalesAtendidas(), "-"),
                "Sin recepcion reciente",
                proveedor.getTiempoEstimadoDias() > 0 ? proveedor.getTiempoEstimadoDias() + " dias" : "-",
                "Sin incidencias recientes",
                "Proveedor registrado"
        );
    }
}
