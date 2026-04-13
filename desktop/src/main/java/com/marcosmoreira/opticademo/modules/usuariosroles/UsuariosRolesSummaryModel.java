package com.marcosmoreira.opticademo.modules.usuariosroles;

/**
 * Modelo resumen para el panel lateral derecho persistente del modulo Usuarios y Roles.
 * Presenta el perfil de acceso y permisos de un usuario seleccionado, incluyendo rol
 * principal, permisos por modulo (caja, inventario, reportes), estado actual y
 * registro de actividad reciente. La fachada (UsuariosRolesFacade) actualiza este
 * modelo al seleccionar un usuario en la tabla principal, mediante el metodo
 * estatico {@code fromUsuario}.
 *
 * @author Marcos Moreira
 * @version 1.0.0
 */
public record UsuariosRolesSummaryModel(
        /** Nombre del usuario del sistema. */
        String usuario,
        /** Rol principal asignado al usuario. */
        String rolPrincipal,
        /** Sucursal donde opera el usuario. */
        String sucursal,
        /** Estado del permiso de acceso al modulo Caja. */
        String accesoCaja,
        /** Estado del permiso de acceso al modulo Inventario. */
        String accesoInventario,
        /** Estado del permiso de acceso al modulo Reportes. */
        String accesoReportes,
        /** Estado actual del usuario (activo, inactivo, bloqueado). */
        String estadoActual,
        /** Fecha y hora del ultimo acceso del usuario. */
        String ultimoAcceso,
        /** Nivel de revision o auditoria aplicado al usuario. */
        String nivelRevision,
        /** Descripcion de la ultima accion realizada por el usuario. */
        String ultimaAccion,
        /** Observacion breve sobre el perfil o actividad del usuario. */
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
