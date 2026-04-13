package com.marcosmoreira.opticademo.modules.configuracion;

/**
 * Modelos de fila para las vistas del modulo Configuracion (parametros del sistema).
 * <p>
 * Estos registros alimentan los {@code TableView} del modulo: catalogos del sistema,
 * configuracion de sucursales y gestion de usuarios. La fachada crea estas instancias
 * a partir de los parametros de configuracion almacenados.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see com.marcosmoreira.opticademo.modules.configuracion.ConfiguracionFacade
 */
public class ConfiguracionRowModel {

    /**
     * Modelo de fila para la tabla de catalogos del sistema.
     * <p>
     * Representa un valor configurable del sistema (estados, tipos, categorias).
     * El campo {@code estado} indica si el catalogo esta activo o desactivado.
     * </p>
     *
     * @param valor     valor del catalogo (columna "Valor")
     * @param estado    estado: "Activo", "Inactivo" (columna "Estado")
     * @param categoria categoria de configuracion (columna "Categoria")
     */
    public record CatalogoRow(String valor, String estado, String categoria) {
        public javafx.beans.property.StringProperty valorProperty() {
            return new javafx.beans.property.SimpleStringProperty(valor);
        }
        public javafx.beans.property.StringProperty estadoProperty() {
            return new javafx.beans.property.SimpleStringProperty(estado);
        }
        public javafx.beans.property.StringProperty categoriaProperty() {
            return new javafx.beans.property.SimpleStringProperty(categoria);
        }
    }

    /**
     * Modelo de fila para la tabla de configuracion de sucursales.
     *
     * @param nombre      nombre de la sucursal (columna "Nombre")
     * @param direccion   direccion fisica (columna "Direccion")
     * @param horario     horario de atencion (columna "Horario")
     * @param responsable encargado de la sede (columna "Responsable")
     * @param estado      estado: "Activa", "Inactiva" (columna "Estado")
     */
    public record SucursalRow(String nombre, String direccion, String horario, String responsable, String estado) {
        public javafx.beans.property.StringProperty nombreProperty() {
            return new javafx.beans.property.SimpleStringProperty(nombre);
        }
        public javafx.beans.property.StringProperty direccionProperty() {
            return new javafx.beans.property.SimpleStringProperty(direccion);
        }
        public javafx.beans.property.StringProperty horarioProperty() {
            return new javafx.beans.property.SimpleStringProperty(horario);
        }
        public javafx.beans.property.StringProperty responsableProperty() {
            return new javafx.beans.property.SimpleStringProperty(responsable);
        }
        public javafx.beans.property.StringProperty estadoProperty() {
            return new javafx.beans.property.SimpleStringProperty(estado);
        }
    }

    /**
     * Modelo de fila para la tabla de gestion de usuarios.
     *
     * @param usuario   nombre de usuario del sistema (columna "Usuario")
     * @param rol       rol asignado: "Admin", "Optometrista", "Vendedor" (columna "Rol")
     * @param sucursal  sede asignada al usuario (columna "Sucursal")
     * @param estado    estado: "Activo", "Inactivo" (columna "Estado")
     */
    public record UsuarioRow(String usuario, String rol, String sucursal, String estado) {
        public javafx.beans.property.StringProperty usuarioProperty() {
            return new javafx.beans.property.SimpleStringProperty(usuario);
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
    }
}
