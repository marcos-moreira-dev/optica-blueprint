package com.marcosmoreira.opticademo.modules.usuariosroles;

import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.shared.domain.usuario.Usuario;
import com.marcosmoreira.opticademo.shared.query.FilterSupport;
import com.marcosmoreira.opticademo.shared.query.PageRequest;
import com.marcosmoreira.opticademo.shared.query.PageResult;
import com.marcosmoreira.opticademo.shared.query.PaginationHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Facade that queries the DemoStore and returns data for the Usuarios y Roles module.
 */
public class UsuariosRolesFacade {

    private final DemoStore store;

    public UsuariosRolesFacade(DemoStore store) {
        this.store = store;
    }

    // ---- Directorio de usuarios ----

    public PageResult<UsuariosRolesRowModel.UsuarioRow> getDirectorio(UsuariosRolesFilters filters, PageRequest pageRequest) {
        List<UsuariosRolesRowModel.UsuarioRow> filtered = store.usuarios.stream()
                .filter(u -> matchesDirectorioFilters(u, filters))
                .map(u -> new UsuariosRolesRowModel.UsuarioRow(
                        u.getCorreo(),
                        u.getNombreVisible(),
                        u.getRol() != null ? u.getRol().name() : "SIN_ROL",
                        u.getSucursal(),
                        u.getEstado() != null ? u.getEstado().name() : "ACTIVO",
                        u.getUltimoAcceso()
                ))
                .collect(Collectors.toList());

        return PaginationHelper.page(filtered, pageRequest);
    }

    // ---- Roles del sistema ----

    public List<UsuariosRolesRowModel.RolRow> getRoles() {
        List<UsuariosRolesRowModel.RolRow> rows = new ArrayList<>();
        rows.add(new UsuariosRolesRowModel.RolRow(
                "Administrador general", "Control total del sistema", "Todas", "Completo"));
        rows.add(new UsuariosRolesRowModel.RolRow(
                "Administrador de sede", "Administracion de una sede especifica", "Matriz Centro", "Amplio"));
        rows.add(new UsuariosRolesRowModel.RolRow(
                "Recepcion", "Recepcion de clientes y agendamiento", "Todas", "Limitado"));
        rows.add(new UsuariosRolesRowModel.RolRow(
                "Asesor de ventas", "Gestion de ventas y atencion al cliente", "Todas", "Limitado"));
        rows.add(new UsuariosRolesRowModel.RolRow(
                "Tecnico optico", "Trabajos de laboratorio y ajustes", "Matriz Centro", "Tecnico"));
        rows.add(new UsuariosRolesRowModel.RolRow(
                "Caja", "Cobros y manejo de caja", "Todas", "Restringido"));
        rows.add(new UsuariosRolesRowModel.RolRow(
                "Supervisor", "Supervision y reportes", "Todas", "Amplio"));
        return rows;
    }

    // ---- Permisos por modulo ----

    public List<UsuariosRolesRowModel.PermisoRow> getPermisosPorRol(String rol) {
        List<UsuariosRolesRowModel.PermisoRow> rows = new ArrayList<>();
        boolean admin = "Administrador general".equals(rol) || "ADMINISTRADOR_GENERAL".equals(rol);
        boolean caja = "Caja".equals(rol) || "CAJA".equals(rol);
        boolean tecnico = "Tecnico optico".equals(rol) || "TECNICO_OPTICO".equals(rol);

        rows.add(new UsuariosRolesRowModel.PermisoRow(
                "Clientes", "Si", admin ? "Si" : "Si", admin ? "Si" : "Si", admin ? "Si" : "No", admin ? "Si" : "No", admin ? "Si" : "No"));
        rows.add(new UsuariosRolesRowModel.PermisoRow(
                "Inventario", "Si", admin ? "Si" : "No", admin ? "Si" : "No", admin ? "Si" : "No", admin ? "Si" : "No", admin ? "Si" : "No"));
        rows.add(new UsuariosRolesRowModel.PermisoRow(
                "Caja", "Si", admin ? "Si" : (caja ? "Si" : "No"), admin ? "Si" : (caja ? "Si" : "No"), admin ? "Si" : (caja ? "Si" : "No"), admin ? "Si" : (caja ? "Si" : "No"), admin ? "Si" : "No"));
        rows.add(new UsuariosRolesRowModel.PermisoRow(
                "Agenda", "Si", admin ? "Si" : "Si", admin ? "Si" : "Si", admin ? "Si" : "No", admin ? "Si" : "No", admin ? "Si" : "No"));
        rows.add(new UsuariosRolesRowModel.PermisoRow(
                "Taller", "Si", admin ? "Si" : (tecnico ? "Si" : "No"), admin ? "Si" : (tecnico ? "Si" : "No"), admin ? "Si" : "No", admin ? "Si" : "No", admin ? "Si" : "No"));
        rows.add(new UsuariosRolesRowModel.PermisoRow(
                "Reportes", "Si", admin ? "Si" : "No", admin ? "Si" : "No", admin ? "Si" : "No", admin ? "Si" : "No", admin ? "Si" : "No"));
        rows.add(new UsuariosRolesRowModel.PermisoRow(
                "Configuracion", "Si", admin ? "Si" : "No", admin ? "Si" : "No", admin ? "Si" : "No", admin ? "Si" : "No", admin ? "Si" : "No"));
        return rows;
    }

    // ---- Usuarios por sucursal ----

    public List<UsuariosRolesRowModel.SucursalUsuarioRow> getUsuariosPorSucursal() {
        List<UsuariosRolesRowModel.SucursalUsuarioRow> rows = new ArrayList<>();
        rows.add(new UsuariosRolesRowModel.SucursalUsuarioRow(
                "Matriz Centro", "4", "Admin, Recepcion, Tecnico, Caja", "Operativa"));
        rows.add(new UsuariosRolesRowModel.SucursalUsuarioRow(
                "Norte Mall", "1", "Asesor ventas", "Operativa"));
        return rows;
    }

    // ---- Sesiones y accesos ----

    public List<UsuariosRolesRowModel.SesionRow> getSesiones() {
        List<UsuariosRolesRowModel.SesionRow> rows = new ArrayList<>();
        rows.add(new UsuariosRolesRowModel.SesionRow(
                "17/04/2026 08:15", "admin@opticamanager.local", "Inicio de sesion", "Matriz Centro", "Exitoso", ""));
        rows.add(new UsuariosRolesRowModel.SesionRow(
                "17/04/2026 08:30", "recepcion.centro@", "Inicio de sesion", "Matriz Centro", "Exitoso", ""));
        rows.add(new UsuariosRolesRowModel.SesionRow(
                "17/04/2026 09:00", "ventas.norte@", "Inicio de sesion", "Norte Mall", "Exitoso", ""));
        rows.add(new UsuariosRolesRowModel.SesionRow(
                "16/04/2026 18:00", "caja.matriz@", "Cierre de sesion", "Matriz Centro", "Exitoso", "Cierre de caja realizado"));
        rows.add(new UsuariosRolesRowModel.SesionRow(
                "16/04/2026 14:00", "tecnico.laboratorio@", "Cambio de rol", "Matriz Centro", "Exitoso", "Temporalmente en recepcion"));
        return rows;
    }

    // ---- Auditoria y trazabilidad ----

    public List<UsuariosRolesRowModel.AuditoriaRow> getAuditoria() {
        List<UsuariosRolesRowModel.AuditoriaRow> rows = new ArrayList<>();
        rows.add(new UsuariosRolesRowModel.AuditoriaRow(
                "17/04/2026 08:15", "admin@opticamanager.local", "Configuracion", "Cambio de permisos", "ROL-003", "Permisos actualizados para Recepcion"));
        rows.add(new UsuariosRolesRowModel.AuditoriaRow(
                "16/04/2026 18:00", "caja.matriz@", "Caja", "Cierre de caja", "CJ-045", "Cierre sin novedades"));
        rows.add(new UsuariosRolesRowModel.AuditoriaRow(
                "16/04/2026 15:30", "admin@opticamanager.local", "Inventario", "Ajuste de stock", "MZ-201", "Reabastecimiento registrado"));
        return rows;
    }

    // ---- Historico ----

    public List<UsuariosRolesRowModel.HistoricoAccesoRow> getHistorico(UsuariosRolesFilters filters) {
        List<UsuariosRolesRowModel.HistoricoAccesoRow> rows = new ArrayList<>();
        rows.add(new UsuariosRolesRowModel.HistoricoAccesoRow(
                "17/04/2026", "admin@opticamanager.local", "Inicio sesion", "Administrador general", "Exitoso", ""));
        rows.add(new UsuariosRolesRowModel.HistoricoAccesoRow(
                "17/04/2026", "recepcion.centro@", "Inicio sesion", "Recepcion", "Exitoso", ""));
        rows.add(new UsuariosRolesRowModel.HistoricoAccesoRow(
                "16/04/2026", "ventas.norte@", "Cambio de permisos", "Asesor de ventas", "Modificado", "Permiso de exportacion agregado"));
        rows.add(new UsuariosRolesRowModel.HistoricoAccesoRow(
                "16/04/2026", "tecnico.laboratorio@", "Cambio de rol", "Tecnico optico", "Temporal", "Asignado a recepcion por 2h"));
        rows.add(new UsuariosRolesRowModel.HistoricoAccesoRow(
                "15/04/2026", "caja.matriz@", "Intento fallido", "Caja", "Fallido", "Credenciales incorrectas"));
        return rows;
    }

    // ---- Summary builder ----

    public UsuariosRolesSummaryModel buildSummary(Usuario usuario) {
        if (usuario == null) return UsuariosRolesSummaryModel.empty();
        String rol = usuario.getRol() != null ? usuario.getRol().name() : "SIN_ROL";
        return new UsuariosRolesSummaryModel(
                usuario.getCorreo(),
                rol,
                usuario.getSucursal(),
                "CAJA".equals(rol) || "ADMINISTRADOR_GENERAL".equals(rol) ? "Concedido" : "Denegado",
                "ADMINISTRADOR_GENERAL".equals(rol) || "TECNICO_OPTICO".equals(rol) ? "Concedido" : "Limitado",
                "ADMINISTRADOR_GENERAL".equals(rol) || "SUPERVISOR".equals(rol) ? "Concedido" : "Limitado",
                usuario.getEstado() != null ? usuario.getEstado().name() : "ACTIVO",
                usuario.getUltimoAcceso(),
                "Normal",
                "Sin accion reciente",
                ""
        );
    }

    // ---- Filter options ----

    public List<String> getRolesList() {
        return store.usuarios.stream()
                .map(u -> u.getRol() != null ? u.getRol().name() : "SIN_ROL")
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> getEstados() {
        return store.usuarios.stream()
                .map(u -> u.getEstado() != null ? u.getEstado().name() : "ACTIVO")
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> getSucursales() {
        return store.sucursales.stream()
                .map(s -> s.getNombre())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> getActividades() {
        return List.of("Todas", "Inicio sesion", "Cierre sesion", "Cambio de rol", "Cambio de permisos", "Intento fallido");
    }

    public List<String> getModulos() {
        return List.of("Clientes", "Inventario", "Caja", "Agenda", "Taller", "Reportes", "Configuracion", "Notificaciones", "Usuarios");
    }

    // ---- Private helpers ----

    private boolean matchesDirectorioFilters(Usuario usuario, UsuariosRolesFilters filters) {
        if (!FilterSupport.matchesText(filters.getSearchText(),
                usuario.getCorreo(),
                usuario.getNombreVisible(),
                usuario.getSucursal())) {
            return false;
        }

        String rolStr = usuario.getRol() != null ? usuario.getRol().name() : "SIN_ROL";
        if (!FilterSupport.matchesExact(rolStr, filters.getRol())) {
            return false;
        }

        String estadoStr = usuario.getEstado() != null ? usuario.getEstado().name() : "ACTIVO";
        if (!FilterSupport.matchesExact(estadoStr, filters.getEstado())) {
            return false;
        }

        if (!FilterSupport.matchesExact(usuario.getSucursal(), filters.getSucursal())) {
            return false;
        }

        return true;
    }
}
