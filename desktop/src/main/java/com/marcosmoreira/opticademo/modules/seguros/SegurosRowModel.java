package com.marcosmoreira.opticademo.modules.seguros;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Row models for the Seguros module TableView entries.
 */
public final class SegurosRowModel {

    private SegurosRowModel() {
    }

    // ------------------------------------------------------------------ VerificacionRow

    public record VerificacionRow(
            String referencia,
            String cliente,
            String planConvenio,
            String estado,
            String vigencia,
            String montoDisponible,
            String sucursal
    ) {

        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty clienteProperty() {
            return new SimpleStringProperty(cliente);
        }

        public StringProperty planConvenioProperty() {
            return new SimpleStringProperty(planConvenio);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty vigenciaProperty() {
            return new SimpleStringProperty(vigencia);
        }

        public StringProperty montoDisponibleProperty() {
            return new SimpleStringProperty(montoDisponible);
        }

        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }
    }

    // ------------------------------------------------------------------ PlanRow

    public record PlanRow(
            String nombre,
            String vigenciaGeneral,
            String coberturaMaxima,
            String copago,
            String categoriasCubiertas,
            String restricciones
    ) {

        public StringProperty nombreProperty() {
            return new SimpleStringProperty(nombre);
        }

        public StringProperty vigenciaGeneralProperty() {
            return new SimpleStringProperty(vigenciaGeneral);
        }

        public StringProperty coberturaMaximaProperty() {
            return new SimpleStringProperty(coberturaMaxima);
        }

        public StringProperty copagoProperty() {
            return new SimpleStringProperty(copago);
        }

        public StringProperty categoriasCubiertasProperty() {
            return new SimpleStringProperty(categoriasCubiertas);
        }

        public StringProperty restriccionesProperty() {
            return new SimpleStringProperty(restricciones);
        }
    }

    // ------------------------------------------------------------------ AutorizacionRow

    public record AutorizacionRow(
            String autorizacion,
            String cliente,
            String plan,
            String fecha,
            String estado,
            String observacion
    ) {

        public StringProperty autorizacionProperty() {
            return new SimpleStringProperty(autorizacion);
        }

        public StringProperty clienteProperty() {
            return new SimpleStringProperty(cliente);
        }

        public StringProperty planProperty() {
            return new SimpleStringProperty(plan);
        }

        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty observacionProperty() {
            return new SimpleStringProperty(observacion);
        }
    }

    // ------------------------------------------------------------------ ReclamoRow

    public record ReclamoRow(
            String reclamo,
            String cliente,
            String ordenRelacionada,
            String montoReclamado,
            String estado,
            String fechaEnvio
    ) {

        public StringProperty reclamoProperty() {
            return new SimpleStringProperty(reclamo);
        }

        public StringProperty clienteProperty() {
            return new SimpleStringProperty(cliente);
        }

        public StringProperty ordenRelacionadaProperty() {
            return new SimpleStringProperty(ordenRelacionada);
        }

        public StringProperty montoReclamadoProperty() {
            return new SimpleStringProperty(montoReclamado);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty fechaEnvioProperty() {
            return new SimpleStringProperty(fechaEnvio);
        }
    }

    // ------------------------------------------------------------------ RespuestaRow

    public record RespuestaRow(
            String fecha,
            String referencia,
            String tipoRespuesta,
            String estado,
            String resultado
    ) {

        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty tipoRespuestaProperty() {
            return new SimpleStringProperty(tipoRespuesta);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty resultadoProperty() {
            return new SimpleStringProperty(resultado);
        }
    }

    // ------------------------------------------------------------------ CoberturaVentaRow

    public record CoberturaVentaRow(
            String venta,
            String cliente,
            String planAplicado,
            String montoCubierto,
            String copago,
            String saldoCliente,
            String estado
    ) {

        public StringProperty ventaProperty() {
            return new SimpleStringProperty(venta);
        }

        public StringProperty clienteProperty() {
            return new SimpleStringProperty(cliente);
        }

        public StringProperty planAplicadoProperty() {
            return new SimpleStringProperty(planAplicado);
        }

        public StringProperty montoCubiertoProperty() {
            return new SimpleStringProperty(montoCubierto);
        }

        public StringProperty copagoProperty() {
            return new SimpleStringProperty(copago);
        }

        public StringProperty saldoClienteProperty() {
            return new SimpleStringProperty(saldoCliente);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }
    }

    // ------------------------------------------------------------------ HistoricoRow

    public record HistoricoRow(
            String fecha,
            String referencia,
            String cliente,
            String tipoCaso,
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

        public StringProperty tipoCasoProperty() {
            return new SimpleStringProperty(tipoCaso);
        }

        public StringProperty estadoFinalProperty() {
            return new SimpleStringProperty(estadoFinal);
        }

        public StringProperty observacionProperty() {
            return new SimpleStringProperty(observacion);
        }
    }
}
