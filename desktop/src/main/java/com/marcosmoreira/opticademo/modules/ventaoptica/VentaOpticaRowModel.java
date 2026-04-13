package com.marcosmoreira.opticademo.modules.ventaoptica;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Modelos de fila para las vistas del modulo Venta Optica (wizard de venta de lentes).
 * <p>
 * Estos registros alimentan los {@code TableView} de las 7 etapas del wizard de venta:
 * busqueda de cliente, seleccion de receta, catalogo de monturas, seleccion de lentes,
 * configuracion de pago, resumen de venta e historial de ventas. La fachada crea estas
 * instancias a partir de las entidades {@code Cliente}, {@code Receta}, {@code Producto}
 * y {@code VentaOptica} del dominio.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see com.marcosmoreira.opticademo.modules.ventaoptica.VentaOpticaFacade
 */
public class VentaOpticaRowModel {

    private VentaOpticaRowModel() {
    }

    // ---- Stage 1: Client search row ----
    /**
     * Modelo de fila para la busqueda de clientes en la etapa 1 del wizard.
     *
     * @param nombre       nombre del cliente (columna "Nombre")
     * @param codigo       codigo interno (columna "Codigo")
     * @param telefono     telefono de contacto (columna "Telefono")
     * @param ultimaVisita fecha de ultima visita (columna "Ultima Visita")
     * @param estado       estado del cliente: "ACTIVO", "INACTIVO" (columna "Estado")
     * @param sucursal     sucursal habitual (columna "Sucursal")
     */
    public record ClientSearchRow(
            String nombre,
            String codigo,
            String telefono,
            String ultimaVisita,
            String estado,
            String sucursal
    ) {
        public StringProperty nombreProperty() {
            return new SimpleStringProperty(nombre);
        }

        public StringProperty codigoProperty() {
            return new SimpleStringProperty(codigo);
        }

        public StringProperty telefonoProperty() {
            return new SimpleStringProperty(telefono);
        }

        public StringProperty ultimaVisitaProperty() {
            return new SimpleStringProperty(ultimaVisita);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }
    }

    // ---- Stage 2: Recipe selection row ----
    /**
     * Modelo de fila para la seleccion de receta en la etapa 2 del wizard.
     * <p>
     * Muestra las recetas disponibles del cliente seleccionado. Los campos
     * {@code odResumen} y {@code oiResumen} contienen la graduacion de cada ojo.
     * </p>
     *
     * @param fecha        fecha de emision de la receta (columna "Fecha")
     * @param estado       vigencia: "Vigente", "Vencida" (columna "Estado")
     * @param profesional  profesional que emitio la receta (columna "Profesional")
     * @param odResumen    graduacion ojo derecho (columna "OD")
     * @param oiResumen    graduacion ojo izquierdo (columna "OI")
     * @param pd           distancia pupilar (columna "DP")
     */
    public record RecipeRow(
            String fecha,
            String estado,
            String profesional,
            String odResumen,
            String oiResumen,
            String pd
    ) {
        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty profesionalProperty() {
            return new SimpleStringProperty(profesional);
        }

        public StringProperty odResumenProperty() {
            return new SimpleStringProperty(odResumen);
        }

        public StringProperty oiResumenProperty() {
            return new SimpleStringProperty(oiResumen);
        }

        public StringProperty pdProperty() {
            return new SimpleStringProperty(pd);
        }
    }

    // ---- Stage 3: Montura catalog row ----
    /**
     * Modelo de fila para el catalogo de monturas en la etapa 3 del wizard.
     * <p>
     * Presenta las monturas disponibles para la venta. El campo {@code stock} indica
     * la disponibilidad en la sucursal seleccionada y {@code precio} el precio de venta.
     * </p>
     *
     * @param referencia codigo SKU de la montura (columna "Referencia")
     * @param nombre     nombre comercial del modelo (columna "Nombre")
     * @param marca      marca del fabricante (columna "Marca")
     * @param color      color del armazon (columna "Color")
     * @param precio     precio de venta (columna "Precio")
     * @param stock      unidades disponibles (columna "Stock")
     * @param sucursal   ubicacion del inventario (columna "Sucursal")
     */
    public record MonturaRow(
            String referencia,
            String nombre,
            String marca,
            String color,
            String precio,
            String stock,
            String sucursal
    ) {
        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty nombreProperty() {
            return new SimpleStringProperty(nombre);
        }

        public StringProperty marcaProperty() {
            return new SimpleStringProperty(marca);
        }

        public StringProperty colorProperty() {
            return new SimpleStringProperty(color);
        }

        public StringProperty precioProperty() {
            return new SimpleStringProperty(precio);
        }

        public StringProperty stockProperty() {
            return new SimpleStringProperty(stock);
        }

        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }
    }

    // ---- Stage 7: Historical sales row ----
    /**
     * Modelo de fila para el historial de ventas del cliente (etapa 7 del wizard).
     * <p>
     * Muestra las ventas anteriores del cliente seleccionadao, permitiendo consultar
     * monturas y lentes comprados previamente. El campo {@code estado} indica el estado
     * de la venta ("Cerrada", "Pendiente", "Anulada") y {@code comprobante} el numero
     * de factura o recibo emitido.
     * </p>
     *
     * @param fecha       fecha de la venta (columna "Fecha")
     * @param orden       numero de orden (columna "Orden")
     * @param cliente     nombre del cliente (columna "Cliente")
     * @param monto       total facturado (columna "Monto")
     * @param metodo      metodo de pago utilizado (columna "Metodo")
     * @param estado      estado de la venta (columna "Estado")
     * @param comprobante numero de comprobante fiscal (columna "Comprobante")
     */
    public record HistoricRow(
            String fecha,
            String orden,
            String cliente,
            String monto,
            String metodo,
            String estado,
            String comprobante
    ) {
        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty ordenProperty() {
            return new SimpleStringProperty(orden);
        }

        public StringProperty clienteProperty() {
            return new SimpleStringProperty(cliente);
        }

        public StringProperty montoProperty() {
            return new SimpleStringProperty(monto);
        }

        public StringProperty metodoProperty() {
            return new SimpleStringProperty(metodo);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty comprobanteProperty() {
            return new SimpleStringProperty(comprobante);
        }
    }
}
