package com.marcosmoreira.opticademo.modules.taller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Row models for the Taller module TableView entries.
 */
public final class TallerRowModel {

    private TallerRowModel() {
    }

    // ------------------------------------------------------------------ IngresoRow

    public record IngresoRow(
            String referencia,
            String cliente,
            String tipo,
            String estado,
            String tecnico,
            String fechaPromesa,
            String sucursal
    ) {

        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty clienteProperty() {
            return new SimpleStringProperty(cliente);
        }

        public StringProperty tipoProperty() {
            return new SimpleStringProperty(tipo);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty tecnicoProperty() {
            return new SimpleStringProperty(tecnico);
        }

        public StringProperty fechaPromesaProperty() {
            return new SimpleStringProperty(fechaPromesa);
        }

        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }
    }

    // ------------------------------------------------------------------ DiagnosticoRow

    public record DiagnosticoRow(
            String referencia,
            String cliente,
            String tipoTrabajo,
            String fechaIngreso,
            String danioPrincipal,
            String complejidad,
            String requiereRepuesto,
            String requiereEnvioExterno
    ) {

        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty clienteProperty() {
            return new SimpleStringProperty(cliente);
        }

        public StringProperty tipoTrabajoProperty() {
            return new SimpleStringProperty(tipoTrabajo);
        }

        public StringProperty fechaIngresoProperty() {
            return new SimpleStringProperty(fechaIngreso);
        }

        public StringProperty danioPrincipalProperty() {
            return new SimpleStringProperty(danioPrincipal);
        }

        public StringProperty complejidadProperty() {
            return new SimpleStringProperty(complejidad);
        }

        public StringProperty requiereRepuestoProperty() {
            return new SimpleStringProperty(requiereRepuesto);
        }

        public StringProperty requiereEnvioExternoProperty() {
            return new SimpleStringProperty(requiereEnvioExterno);
        }
    }

    // ------------------------------------------------------------------ ReparacionRow

    public record ReparacionRow(
            String fecha,
            String referencia,
            String intervencion,
            String tecnico,
            String estado
    ) {

        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty intervencionProperty() {
            return new SimpleStringProperty(intervencion);
        }

        public StringProperty tecnicoProperty() {
            return new SimpleStringProperty(tecnico);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }
    }

    // ------------------------------------------------------------------ PiezaRow

    public record PiezaRow(
            String refTrabajo,
            String pieza,
            String cantidad,
            String estado,
            String observacion
    ) {

        public StringProperty refTrabajoProperty() {
            return new SimpleStringProperty(refTrabajo);
        }

        public StringProperty piezaProperty() {
            return new SimpleStringProperty(pieza);
        }

        public StringProperty cantidadProperty() {
            return new SimpleStringProperty(cantidad);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty observacionProperty() {
            return new SimpleStringProperty(observacion);
        }
    }

    // ------------------------------------------------------------------ EnvioExternoRow

    public record EnvioExternoRow(
            String referencia,
            String tipoTrabajo,
            String tercero,
            String fechaEnvio,
            String estado,
            String fechaEstimada
    ) {

        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty tipoTrabajoProperty() {
            return new SimpleStringProperty(tipoTrabajo);
        }

        public StringProperty terceroProperty() {
            return new SimpleStringProperty(tercero);
        }

        public StringProperty fechaEnvioProperty() {
            return new SimpleStringProperty(fechaEnvio);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty fechaEstimadaProperty() {
            return new SimpleStringProperty(fechaEstimada);
        }
    }

    // ------------------------------------------------------------------ EntregaRow

    public record EntregaRow(
            String referencia,
            String fechaEntrega,
            String responsableEntrega,
            String estadoFinal,
            String clienteConforme
    ) {

        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty fechaEntregaProperty() {
            return new SimpleStringProperty(fechaEntrega);
        }

        public StringProperty responsableEntregaProperty() {
            return new SimpleStringProperty(responsableEntrega);
        }

        public StringProperty estadoFinalProperty() {
            return new SimpleStringProperty(estadoFinal);
        }

        public StringProperty clienteConformeProperty() {
            return new SimpleStringProperty(clienteConforme);
        }
    }

    // ------------------------------------------------------------------ HistoricoRow

    public record HistoricoRow(
            String fecha,
            String referencia,
            String cliente,
            String tipo,
            String estadoFinal,
            String observacion
    ) {

        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty clienteProperty() {
            return new SimpleStringProperty(cliente);
        }

        public StringProperty tipoProperty() {
            return new SimpleStringProperty(tipo);
        }

        public StringProperty estadoFinalProperty() {
            return new SimpleStringProperty(estadoFinal);
        }

        public StringProperty observacionProperty() {
            return new SimpleStringProperty(observacion);
        }
    }
}
