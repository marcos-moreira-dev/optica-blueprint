package com.marcosmoreira.opticademo.modules.usuariosroles;

/**
 * Row models for the Usuarios y Roles module views.
 */
public class UsuariosRolesRowModel {

    private UsuariosRolesRowModel() {}

    /**
     * Directorio de usuarios row.
     */
    public record UsuarioRow(
            String usuario,
            String nombreVisible,
            String rol,
            String sucursal,
            String estado,
            String ultimoAcceso
    ) {
        public javafx.beans.property.StringProperty usuarioProperty() {
            return new javafx.beans.property.SimpleStringProperty(usuario);
        }
        public javafx.beans.property.StringProperty nombreVisibleProperty() {
            return new javafx.beans.property.SimpleStringProperty(nombreVisible);
        }
        public javafx.beans.property.StringProperty rolProperty() {
            return new javafx.beans.property.SimpleStringProperty(rol);
        }
        public javafx.beans.property.StringProperty sucursalProperty() {
            return new javafx.beans.property.SimpleStringProperty(sucursal);
        }
        public javafx.beans.property.StringProperty estadoProperty() {
            return new javafx.beans.property.SimpleStringProperty(estado);
        }
        public javafx.beans.property.StringProperty ultimoAccesoProperty() {
            return new javafx.beans.property.SimpleStringProperty(ultimoAcceso);
        }
    }

    /**
     * Roles del sistema row.
     */
    public record RolRow(
            String nombre,
            String descripcion,
            String sedeRecomendada,
            String nivelAcceso
    ) {
        public javafx.beans.property.StringProperty nombreProperty() {
            return new javafx.beans.property.SimpleStringProperty(nombre);
        }
        public javafx.beans.property.StringProperty descripcionProperty() {
            return new javafx.beans.property.SimpleStringProperty(descripcion);
        }
        public javafx.beans.property.StringProperty sedeRecomendadaProperty() {
            return new javafx.beans.property.SimpleStringProperty(sedeRecomendada);
        }
        public javafx.beans.property.StringProperty nivelAccesoProperty() {
            return new javafx.beans.property.SimpleStringProperty(nivelAcceso);
        }
    }

    /**
     * Permisos por modulo row.
     */
    public record PermisoRow(
            String modulo,
            String ver,
            String crear,
            String editar,
            String aprobar,
            String exportar,
            String cerrar
    ) {
        public javafx.beans.property.StringProperty moduloProperty() {
            return new javafx.beans.property.SimpleStringProperty(modulo);
        }
        public javafx.beans.property.StringProperty verProperty() {
            return new javafx.beans.property.SimpleStringProperty(ver);
        }
        public javafx.beans.property.StringProperty crearProperty() {
            return new javafx.beans.property.SimpleStringProperty(crear);
        }
        public javafx.beans.property.StringProperty editarProperty() {
            return new javafx.beans.property.SimpleStringProperty(editar);
        }
        public javafx.beans.property.StringProperty aprobarProperty() {
            return new javafx.beans.property.SimpleStringProperty(aprobar);
        }
        public javafx.beans.property.StringProperty exportarProperty() {
            return new javafx.beans.property.SimpleStringProperty(exportar);
        }
        public javafx.beans.property.StringProperty cerrarProperty() {
            return new javafx.beans.property.SimpleStringProperty(cerrar);
        }
    }

    /**
     * Usuarios por sucursal row.
     */
    public record SucursalUsuarioRow(
            String sucursal,
            String usuariosActivos,
            String rolesPrincipales,
            String estado
    ) {
        public javafx.beans.property.StringProperty sucursalProperty() {
            return new javafx.beans.property.SimpleStringProperty(sucursal);
        }
        public javafx.beans.property.StringProperty usuariosActivosProperty() {
            return new javafx.beans.property.SimpleStringProperty(usuariosActivos);
        }
        public javafx.beans.property.StringProperty rolesPrincipalesProperty() {
            return new javafx.beans.property.SimpleStringProperty(rolesPrincipales);
        }
        public javafx.beans.property.StringProperty estadoProperty() {
            return new javafx.beans.property.SimpleStringProperty(estado);
        }
    }

    /**
     * Sesiones y accesos row.
     */
    public record SesionRow(
            String fechaHora,
            String usuario,
            String evento,
            String sucursal,
            String estado,
            String observacion
    ) {
        public javafx.beans.property.StringProperty fechaHoraProperty() {
            return new javafx.beans.property.SimpleStringProperty(fechaHora);
        }
        public javafx.beans.property.StringProperty usuarioProperty() {
            return new javafx.beans.property.SimpleStringProperty(usuario);
        }
        public javafx.beans.property.StringProperty eventoProperty() {
            return new javafx.beans.property.SimpleStringProperty(evento);
        }
        public javafx.beans.property.StringProperty sucursalProperty() {
            return new javafx.beans.property.SimpleStringProperty(sucursal);
        }
        public javafx.beans.property.StringProperty estadoProperty() {
            return new javafx.beans.property.SimpleStringProperty(estado);
        }
        public javafx.beans.property.StringProperty observacionProperty() {
            return new javafx.beans.property.SimpleStringProperty(observacion);
        }
    }

    /**
     * Auditoria y trazabilidad row.
     */
    public record AuditoriaRow(
            String fechaHora,
            String usuario,
            String modulo,
            String accion,
            String registroAfectado,
            String observacion
    ) {
        public javafx.beans.property.StringProperty fechaHoraProperty() {
            return new javafx.beans.property.SimpleStringProperty(fechaHora);
        }
        public javafx.beans.property.StringProperty usuarioProperty() {
            return new javafx.beans.property.SimpleStringProperty(usuario);
        }
        public javafx.beans.property.StringProperty moduloProperty() {
            return new javafx.beans.property.SimpleStringProperty(modulo);
        }
        public javafx.beans.property.StringProperty accionProperty() {
            return new javafx.beans.property.SimpleStringProperty(accion);
        }
        public javafx.beans.property.StringProperty registroAfectadoProperty() {
            return new javafx.beans.property.SimpleStringProperty(registroAfectado);
        }
        public javafx.beans.property.StringProperty observacionProperty() {
            return new javafx.beans.property.SimpleStringProperty(observacion);
        }
    }

    /**
     * Historico de acceso row.
     */
    public record HistoricoAccesoRow(
            String fecha,
            String usuario,
            String tipoEvento,
            String rolOModulo,
            String estado,
            String observacion
    ) {
        public javafx.beans.property.StringProperty fechaProperty() {
            return new javafx.beans.property.SimpleStringProperty(fecha);
        }
        public javafx.beans.property.StringProperty usuarioProperty() {
            return new javafx.beans.property.SimpleStringProperty(usuario);
        }
        public javafx.beans.property.StringProperty tipoEventoProperty() {
            return new javafx.beans.property.SimpleStringProperty(tipoEvento);
        }
        public javafx.beans.property.StringProperty rolOModuloProperty() {
            return new javafx.beans.property.SimpleStringProperty(rolOModulo);
        }
        public javafx.beans.property.StringProperty estadoProperty() {
            return new javafx.beans.property.SimpleStringProperty(estado);
        }
        public javafx.beans.property.StringProperty observacionProperty() {
            return new javafx.beans.property.SimpleStringProperty(observacion);
        }
    }
}
