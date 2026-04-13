package com.marcosmoreira.opticademo.modules.clientes;

import com.marcosmoreira.opticademo.shared.domain.cliente.Cliente;
import javafx.beans.binding.StringBinding;

/**
 * Modelo de fila para la tabla principal del modulo Clientes.
 * <p>
 * Este registro representa un cliente en el {@code TableView} principal del modulo.
 * La fachada crea estas instancias a partir de las entidades {@code Cliente} del
 * dominio, mapeando los campos del modelo a las columnas de la vista. El metodo
 * estatico {@code from(Cliente)} convierte una entidad de dominio en un modelo
 * de presentacion. El campo {@code estado} refleja el estado del cliente en el
 * sistema ("ACTIVO", "INACTIVO") y {@code receta} indica si el paciente tiene
 * una receta vigente ("Vigente", "Vencida", "Sin receta").
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see com.marcosmoreira.opticademo.modules.clientes.ClientesFacade
 * @see com.marcosmoreira.opticademo.modules.clientes.ClientesSummaryModel
 * @see com.marcosmoreira.opticademo.shared.domain.cliente.Cliente
 */
public record ClientesRowModel(
        /** nombre completo del paciente (columna "Nombre") */
        String nombre,
        /** codigo interno asignado al cliente (columna "Codigo") */
        String codigo,
        /** numero de telefono de contacto (columna "Telefono") */
        String telefono,
        /** fecha de la ultima visita registrada (columna "Ultima Visita") */
        String ultimaVisita,
        /** estado de la receta: "Vigente", "Vencida", "Sin receta" (columna "Receta") */
        String receta,
        /** estado del cliente: "ACTIVO", "INACTIVO"; determina el color de la fila (columna "Estado") */
        String estado,
        /** sucursal habitual del cliente (columna "Sucursal") */
        String sucursal
) {

    /**
     * Crea un {@code ClientesRowModel} a partir de una entidad {@code Cliente} del dominio.
     * <p>
     * Convierte los campos de la entidad de dominio en valores de presentacion para
     * la tabla. El estado del cliente se obtiene del enum {@code EstadoCliente} y se
     * formatea como cadena. Si el estado es nulo, se asume "ACTIVO".
     * </p>
     *
     * @param cliente entidad de dominio a convertir
     * @return modelo de fila listo para mostrar en el {@code TableView}
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
