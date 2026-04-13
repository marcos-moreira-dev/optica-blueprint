package com.marcosmoreira.opticademo.modules.recetas;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Row models for the Recetas module sub-views.
 */
public final class RecetasRowModel {

    private RecetasRowModel() {
    }

    /**
     * Historial de recetas row.
     */
    public record HistorialRow(
            String fecha,
            String profesional,
            String estado,
            String odResumen,
            String oiResumen,
            String pd,
            String observacionBreve
    ) {
        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty profesionalProperty() {
            return new SimpleStringProperty(profesional);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
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

        public StringProperty observacionBreveProperty() {
            return new SimpleStringProperty(observacionBreve);
        }
    }

    /**
     * Medidas y parametros row.
     */
    public record MedicionRow(
            String parametro,
            String valor,
            String unidad,
            String observacion
    ) {
        public StringProperty parametroProperty() {
            return new SimpleStringProperty(parametro);
        }

        public StringProperty valorProperty() {
            return new SimpleStringProperty(valor);
        }

        public StringProperty unidadProperty() {
            return new SimpleStringProperty(unidad);
        }

        public StringProperty observacionProperty() {
            return new SimpleStringProperty(observacion);
        }
    }

    /**
     * Vinculacion con orden optica row.
     */
    public record VinculacionRow(
            String referenciaOrden,
            String estadoOrden,
            String fecha,
            String saldo,
            String entrega
    ) {
        public StringProperty referenciaOrdenProperty() {
            return new SimpleStringProperty(referenciaOrden);
        }

        public StringProperty estadoOrdenProperty() {
            return new SimpleStringProperty(estadoOrden);
        }

        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty saldoProperty() {
            return new SimpleStringProperty(saldo);
        }

        public StringProperty entregaProperty() {
            return new SimpleStringProperty(entrega);
        }
    }
}
