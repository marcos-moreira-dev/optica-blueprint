package com.marcosmoreira.opticademo.modules.usuariosroles;

/**
 * Modelos de fila para las vistas del modulo Usuarios y Roles (gestion de acceso).
 * <p>
 * Estos registros alimentan los {@code TableView} del modulo: directorio de usuarios,
 * roles del sistema, permisos por modulo, usuarios por sucursal, sesiones activas,
 * auditoria e historico de accesos. La fachada crea estas instancias a partir de
 * las entidades {@code Usuario} y {@code Rol} del dominio.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see com.marcosmoreira.opticademo.modules.usuariosroles.UsuariosRolesFacade
 */
public class UsuariosRolesRowModel {

    private UsuariosRolesRowModel() {}

    /**
     * Modelo de fila para el directorio de usuarios del sistema.
     *
     * @param usuario       nombre de login del usuario (columna "Usuario")
     * @param nombreVisible nombre completo para mostrar (columna "Nombre")
     * @param rol           rol asignado (columna "Rol")
     * @param sucursal      sede asignada (columna "Sucursal")
     * @param estado        estado: "Activo", "Inactivo", "Bloqueado" (columna "Estado")
     * @param ultimoAcceso   fecha y hora del ultimo acceso (columna "Ultimo Acceso")
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
     * Modelo de fila para los roles del sistema.
     * <p>
     * Define los roles disponibles y su nivel de acceso recomendado.
     * </p>
     *
     * @param nombre           nombre del rol (columna "Nombre")
     * @param descripcion      descripcion de las responsabilidades (columna "Descripcion")
     * @param sedeRecomendada  sede recomendada para este rol (columna "Sede Recomendada")
     * @param nivelAcceso      nivel: "Administrador", "Operativo", "Consulta" (columna "Nivel Acceso")
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
     * Modelo de fila para la matriz de permisos por modulo.
     * <p>
     * Muestra las acciones permitidas para un rol en cada modulo del sistema.
     * Cada columna de permiso contiene "Si" o "No" indicando si el rol puede
     * ejecutar esa accion.
     * </p>
     *
     * @param modulo  nombre del modulo (columna "Modulo")
     * @param ver     permiso de lectura (columna "Ver")
     * @param crear   permiso de creacion (columna "Crear")
     * @param editar  permiso de edicion (columna "Editar")
     * @param aprobar permiso de aprobacion (columna "Aprobar")
     * @param exportar permiso de exportacion (columna "Exportar")
     * @param cerrar  permiso de cierre (columna "Cerrar")
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
     * Modelo de fila para el resumen de usuarios por sucursal.
     *
     * @param sucursal        nombre de la sede (columna "Sucursal")
     * @param usuariosActivos cantidad de usuarios activos (columna "Usuarios Activos")
     * @param rolesPrincipales roles mas comunes en esa sede (columna "Roles Principales")
     * @param estado          estado general de la sede (columna "Estado")
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
     * Modelo de fila para el registro de sesiones y eventos de acceso.
     * <p>
     * Historial de inicios y cierres de sesion de los usuarios.
     * </p>
     *
     * @param fechaHora  fecha y hora del evento (columna "Fecha/Hora")
     * @param usuario    nombre de usuario (columna "Usuario")
     * @param evento     tipo: "Login", "Logout", "Intento fallido" (columna "Evento")
     * @param sucursal   sede desde donde accedio (columna "Sucursal")
     * @param estado     resultado: "Exitoso", "Fallido" (columna "Estado")
     * @param observacion detalles adicionales (columna "Observacion")
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
     * Modelo de fila para la auditoria y trazabilidad de acciones.
     * <p>
     * Registro de todas las acciones relevantes realizadas por los usuarios,
     * utilizado para auditoria y control interno.
     * </p>
     *
     * @param fechaHora       fecha y hora de la accion (columna "Fecha/Hora")
     * @param usuario         usuario que realizo la accion (columna "Usuario")
     * @param modulo          modulo donde se actuo (columna "Modulo")
     * @param accion          tipo de accion: "Crear", "Editar", "Eliminar" (columna "Accion")
     * @param registroAfectado registro modificado (columna "Registro Afectado")
     * @param observacion     detalles de la accion (columna "Observacion")
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
     * Modelo de fila para el historico de accesos al sistema.
     *
     * @param fecha         fecha del evento (columna "Fecha")
     * @param usuario       nombre de usuario (columna "Usuario")
     * @param tipoEvento    tipo de evento (columna "Tipo Evento")
     * @param rolOModulo    rol o modulo involucrado (columna "Rol/Modulo")
     * @param estado        estado del evento (columna "Estado")
     * @param observacion   detalles adicionales (columna "Observacion")
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
