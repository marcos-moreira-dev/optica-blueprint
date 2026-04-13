package com.marcosmoreira.opticademo.modules.proveedores;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Modelos de fila para las 7 sub-vistas del modulo Proveedores.
 * <p>
 * Estos registros alimentan los {@code TableView} del modulo: directorio de
 * proveedores, perfil comercial, catalogo vinculado, ordenes de abastecimiento,
 * recepciones e incidencias, desempeno del proveedor e historico. La fachada
 * crea estas instancias a partir de las entidades {@code Proveedor} y
 * {@code OrdenCompra} del dominio.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see com.marcosmoreira.opticademo.modules.proveedores.ProveedoresFacade
 */
public final class ProveedoresRowModel {

    private ProveedoresRowModel() {
    }

    // ==================== Sub-view 1: Directorio de proveedores ====================

    /**
     * Modelo de fila para el directorio de proveedores.
     *
     * @param proveedor       nombre del proveedor (columna "Proveedor")
     * @param tipo            tipo: "Laboratorio", "Monturas", "Lentes" (columna "Tipo")
     * @param contacto        datos de contacto (columna "Contacto")
     * @param tiempoEstimado  tiempo estimado de entrega (columna "Tiempo Estimado")
     * @param estado          estado: "Activo", "Inactivo" (columna "Estado")
     * @param cobertura       area de cobertura (columna "Cobertura")
     */
    public record DirectorioRow(
            String proveedor,
            String tipo,
            String contacto,
            String tiempoEstimado,
            String estado,
            String cobertura
    ) {
        public StringProperty proveedorProperty() {
            return new SimpleStringProperty(proveedor);
        }

        public StringProperty tipoProperty() {
            return new SimpleStringProperty(tipo);
        }

        public StringProperty contactoProperty() {
            return new SimpleStringProperty(contacto);
        }

        public StringProperty tiempoEstimadoProperty() {
            return new SimpleStringProperty(tiempoEstimado);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty coberturaProperty() {
            return new SimpleStringProperty(cobertura);
        }
    }

    // ==================== Sub-view 2: Perfil comercial ====================

    /**
     * Modelo de fila para el perfil comercial de un proveedor (vista clave-valor).
     *
     * @param campo  nombre del campo (columna "Campo")
     * @param valor  valor del campo (columna "Valor")
     */
    public record PerfilRow(
            String campo,
            String valor
    ) {
        public StringProperty campoProperty() {
            return new SimpleStringProperty(campo);
        }

        public StringProperty valorProperty() {
            return new SimpleStringProperty(valor);
        }
    }

    // ==================== Sub-view 3: Catalogo vinculado ====================

    /**
     * Modelo de fila para el catalogo de productos vinculados al proveedor.
     *
     * @param referencia  codigo SKU (columna "Referencia")
     * @param nombre      nombre del producto (columna "Nombre")
     * @param categoria   categoria (columna "Categoria")
     * @param marca       marca (columna "Marca")
     * @param estado      estado del producto (columna "Estado")
     */
    public record CatalogoRow(
            String referencia,
            String nombre,
            String categoria,
            String marca,
            String estado
    ) {
        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty nombreProperty() {
            return new SimpleStringProperty(nombre);
        }

        public StringProperty categoriaProperty() {
            return new SimpleStringProperty(categoria);
        }

        public StringProperty marcaProperty() {
            return new SimpleStringProperty(marca);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }
    }

    // ==================== Sub-view 4: Ordenes y abastecimiento ====================

    /**
     * Modelo de fila para las ordenes de compra al proveedor.
     *
     * @param orden       numero de orden (columna "Orden")
     * @param fecha       fecha de la orden (columna "Fecha")
     * @param estado      estado de la orden (columna "Estado")
     * @param totalItems  cantidad de items solicitados (columna "Total Items")
     * @param recepcion   estado de la recepcion (columna "Recepcion")
     * @param observacion notas sobre la orden (columna "Observacion")
     */
    public record OrdenRow(
            String orden,
            String fecha,
            String estado,
            String totalItems,
            String recepcion,
            String observacion
    ) {
        public StringProperty ordenProperty() {
            return new SimpleStringProperty(orden);
        }

        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty totalItemsProperty() {
            return new SimpleStringProperty(totalItems);
        }

        public StringProperty recepcionProperty() {
            return new SimpleStringProperty(recepcion);
        }

        public StringProperty observacionProperty() {
            return new SimpleStringProperty(observacion);
        }
    }

    // ==================== Sub-view 5: Recepciones e incidencias ====================

    /**
     * Modelo de fila para las recepciones e incidencias del proveedor.
     *
     * @param fecha       fecha del evento (columna "Fecha")
     * @param tipo        tipo: "Recepcion", "Incidencia" (columna "Tipo")
     * @param orden       orden asociada (columna "Orden")
     * @param estado      estado (columna "Estado")
     * @param responsable persona responsable (columna "Responsable")
     */
    public record RecepcionRow(
            String fecha,
            String tipo,
            String orden,
            String estado,
            String responsable
    ) {
        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty tipoProperty() {
            return new SimpleStringProperty(tipo);
        }

        public StringProperty ordenProperty() {
            return new SimpleStringProperty(orden);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty responsableProperty() {
            return new SimpleStringProperty(responsable);
        }
    }

    // ==================== Sub-view 6: Desempeno del proveedor ====================

    /**
     * Modelo de fila para los indicadores de desempeno del proveedor.
     * <p>
     * Muestra KPIs como tiempo promedio de entrega, tasa de incidencias, etc.
     * </p>
     *
     * @param indicador   nombre del KPI (columna "Indicador")
     * @param valor       valor medido (columna "Valor")
     * @param estado      estado vs. meta (columna "Estado")
     * @param observacion nota analitica (columna "Observacion")
     */
    public record DesempenoRow(
            String indicador,
            String valor,
            String estado,
            String observacion
    ) {
        public StringProperty indicadorProperty() {
            return new SimpleStringProperty(indicador);
        }

        public StringProperty valorProperty() {
            return new SimpleStringProperty(valor);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty observacionProperty() {
            return new SimpleStringProperty(observacion);
        }
    }

    // ==================== Sub-view 7: Historico ====================

    /**
     * Modelo de fila para el historico de interacciones con proveedores.
     *
     * @param fecha        fecha del registro (columna "Fecha")
     * @param proveedor    nombre del proveedor (columna "Proveedor")
     * @param tipoRegistro tipo: "Orden", "Recepcion", "Incidencia" (columna "Tipo")
     * @param referencia   identificador del documento (columna "Referencia")
     * @param estado       estado del registro (columna "Estado")
     * @param observacion  nota sobre el evento (columna "Observacion")
     */
    public record HistoricoRow(
            String fecha,
            String proveedor,
            String tipoRegistro,
            String referencia,
            String estado,
            String observacion
    ) {
        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty proveedorProperty() {
            return new SimpleStringProperty(proveedor);
        }

        public StringProperty tipoRegistroProperty() {
            return new SimpleStringProperty(tipoRegistro);
        }

        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty observacionProperty() {
            return new SimpleStringProperty(observacion);
        }
    }
}
