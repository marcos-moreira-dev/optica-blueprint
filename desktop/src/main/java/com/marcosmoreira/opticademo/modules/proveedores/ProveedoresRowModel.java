package com.marcosmoreira.opticademo.modules.proveedores;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Row models for all 7 sub-views of the Proveedores module.
 */
public final class ProveedoresRowModel {

    private ProveedoresRowModel() {
    }

    // ==================== Sub-view 1: Directorio de proveedores ====================

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
