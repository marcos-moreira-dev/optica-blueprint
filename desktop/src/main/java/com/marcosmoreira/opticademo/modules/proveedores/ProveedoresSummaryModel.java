package com.marcosmoreira.opticademo.modules.proveedores;

import com.marcosmoreira.opticademo.shared.domain.proveedor.Proveedor;
import com.marcosmoreira.opticademo.shared.util.StringUtils;

/**
 * Summary model for the right-hand provider detail panel in the Proveedores module.
 */
public record ProveedoresSummaryModel(
        String proveedor,
        String tipoPrincipal,
        String estado,
        String contacto,
        String telefono,
        String correo,
        String categoriaAbastecida,
        String sucursalesAtendidas,
        String ultimaRecepcion,
        String tiempoEstimado,
        String incidenciasRecientes,
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
