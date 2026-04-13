package com.marcosmoreira.opticademo.modules.configuracion;

/**
 * Model records for tabular data displayed in the Configuracion module.
 */
public class ConfiguracionRowModel {

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
