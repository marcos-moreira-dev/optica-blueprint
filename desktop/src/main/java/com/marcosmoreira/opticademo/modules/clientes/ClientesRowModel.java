package com.marcosmoreira.opticademo.modules.clientes;

import com.marcosmoreira.opticademo.shared.domain.cliente.Cliente;
import javafx.beans.binding.StringBinding;

/**
 * Row model for a single client in the Clientes TableView.
 */
public record ClientesRowModel(
        String nombre,
        String codigo,
        String telefono,
        String ultimaVisita,
        String receta,
        String estado,
        String sucursal
) {

    /**
     * Creates a ClientesRowModel from a Cliente entity.
     */
    public static ClientesRowModel from(Cliente cliente) {
        return new ClientesRowModel(
                cliente.getNombreCompleto(),
                cliente.getCodigoInterno(),
                cliente.getTelefono(),
                cliente.getUltimaVisita(),
                cliente.getEstadoReceta(),
                cliente.getEstado() != null ? cliente.getEstado().name() : "ACTIVO",
                cliente.getSucursalHabitual()
        );
    }

    // ---- JavaFX property bindings for TableView ----

    public javafx.beans.property.StringProperty nombreProperty() {
        return new javafx.beans.property.SimpleStringProperty(nombre);
    }

    public javafx.beans.property.StringProperty codigoProperty() {
        return new javafx.beans.property.SimpleStringProperty(codigo);
    }

    public javafx.beans.property.StringProperty telefonoProperty() {
        return new javafx.beans.property.SimpleStringProperty(telefono);
    }

    public javafx.beans.property.StringProperty ultimaVisitaProperty() {
        return new javafx.beans.property.SimpleStringProperty(ultimaVisita);
    }

    public javafx.beans.property.StringProperty recetaProperty() {
        return new javafx.beans.property.SimpleStringProperty(receta);
    }

    public javafx.beans.property.StringProperty estadoProperty() {
        return new javafx.beans.property.SimpleStringProperty(estado);
    }

    public javafx.beans.property.StringProperty sucursalProperty() {
        return new javafx.beans.property.SimpleStringProperty(sucursal);
    }
}
