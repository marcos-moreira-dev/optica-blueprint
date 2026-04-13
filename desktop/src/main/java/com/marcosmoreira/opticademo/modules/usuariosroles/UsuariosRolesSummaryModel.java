package com.marcosmoreira.opticademo.modules.usuariosroles;

/**
 * Summary model for the right-hand detail panel of the Usuarios y Roles module.
 */
public record UsuariosRolesSummaryModel(
        String usuario,
        String rolPrincipal,
        String sucursal,
        String accesoCaja,
        String accesoInventario,
        String accesoReportes,
        String estadoActual,
        String ultimoAcceso,
        String nivelRevision,
        String ultimaAccion,
        String observacionBreve
) {

    public static UsuariosRolesSummaryModel fromUsuario(UsuariosRolesRowModel.UsuarioRow row) {
        return new UsuariosRolesSummaryModel(
                row.usuario(),
                row.rol(),
                row.sucursal(),
                "Concedido",
                "Concedido",
                "Limitado",
                row.estado(),
                row.ultimoAcceso(),
                "Normal",
                "Sin accion reciente",
                ""
        );
    }

    public static UsuariosRolesSummaryModel empty() {
        return new UsuariosRolesSummaryModel(
                "", "", "", "", "", "", "", "", "", "", "Sin seleccion"
        );
    }
}
