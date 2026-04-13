package com.marcosmoreira.opticademo.modules.reportes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Row models for the Reportes module TableView entries.
 */
public final class ReportesRowModel {

    private ReportesRowModel() {
    }

    // ------------------------------------------------------------------ KpiResumenRow

    public record KpiResumenRow(
            String area,
            String indicador,
            String valor,
            String estado
    ) {

        public static KpiResumenRow seed(String area, String indicador, String valor, String estado) {
            return new KpiResumenRow(area, indicador, valor, estado);
        }

        public StringProperty areaProperty() {
            return new SimpleStringProperty(area);
        }

        public StringProperty indicadorProperty() {
            return new SimpleStringProperty(indicador);
        }

        public StringProperty valorProperty() {
            return new SimpleStringProperty(valor);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }
    }

    // ------------------------------------------------------------------ ComercialRow

    public record ComercialRow(
            String categoria,
            String ventas,
            String unidades,
            String participacion,
            String observacion
    ) {

        public static ComercialRow seed(String categoria, String ventas, String unidades,
                                         String participacion, String observacion) {
            return new ComercialRow(categoria, ventas, unidades, participacion, observacion);
        }

        public StringProperty categoriaProperty() {
            return new SimpleStringProperty(categoria);
        }

        public StringProperty ventasProperty() {
            return new SimpleStringProperty(ventas);
        }

        public StringProperty unidadesProperty() {
            return new SimpleStringProperty(unidades);
        }

        public StringProperty participacionProperty() {
            return new SimpleStringProperty(participacion);
        }

        public StringProperty observacionProperty() {
            return new SimpleStringProperty(observacion);
        }
    }

    // ------------------------------------------------------------------ RotacionRow

    public record RotacionRow(
            String referencia,
            String nombre,
            String categoria,
            String rotacion,
            String ultimaSalida,
            String estado,
            String observacion
    ) {

        public static RotacionRow seed(String referencia, String nombre, String categoria,
                                        String rotacion, String ultimaSalida, String estado,
                                        String observacion) {
            return new RotacionRow(referencia, nombre, categoria, rotacion, ultimaSalida, estado, observacion);
        }

        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty nombreProperty() {
            return new SimpleStringProperty(nombre);
        }

        public StringProperty categoriaProperty() {
            return new SimpleStringProperty(categoria);
        }

        public StringProperty rotacionProperty() {
            return new SimpleStringProperty(rotacion);
        }

        public StringProperty ultimaSalidaProperty() {
            return new SimpleStringProperty(ultimaSalida);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty observacionProperty() {
            return new SimpleStringProperty(observacion);
        }
    }

    // ------------------------------------------------------------------ AgendaRow

    public record AgendaRow(
            String indicador,
            String valor,
            String meta,
            String estado,
            String observacion
    ) {

        public static AgendaRow seed(String indicador, String valor, String meta,
                                      String estado, String observacion) {
            return new AgendaRow(indicador, valor, meta, estado, observacion);
        }

        public StringProperty indicadorProperty() {
            return new SimpleStringProperty(indicador);
        }

        public StringProperty valorProperty() {
            return new SimpleStringProperty(valor);
        }

        public StringProperty metaProperty() {
            return new SimpleStringProperty(meta);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty observacionProperty() {
            return new SimpleStringProperty(observacion);
        }
    }

    // ------------------------------------------------------------------ LaboratorioRow

    public record LaboratorioRow(
            String indicador,
            String valor,
            String estado,
            String observacion
    ) {

        public static LaboratorioRow seed(String indicador, String valor,
                                           String estado, String observacion) {
            return new LaboratorioRow(indicador, valor, estado, observacion);
        }

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

    // ------------------------------------------------------------------ CarteraRow

    public record CarteraRow(
            String orden,
            String cliente,
            String saldo,
            String ultimoPago,
            String estado,
            String sucursal
    ) {

        public static CarteraRow seed(String orden, String cliente, String saldo,
                                       String ultimoPago, String estado, String sucursal) {
            return new CarteraRow(orden, cliente, saldo, ultimoPago, estado, sucursal);
        }

        public StringProperty ordenProperty() {
            return new SimpleStringProperty(orden);
        }

        public StringProperty clienteProperty() {
            return new SimpleStringProperty(cliente);
        }

        public StringProperty saldoProperty() {
            return new SimpleStringProperty(saldo);
        }

        public StringProperty ultimoPagoProperty() {
            return new SimpleStringProperty(ultimoPago);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }
    }

    // ------------------------------------------------------------------ RetencionRow

    public record RetencionRow(
            String cliente,
            String tipo,
            String estado,
            String fechaObjetivo,
            String resultado,
            String sucursal
    ) {

        public static RetencionRow seed(String cliente, String tipo, String estado,
                                         String fechaObjetivo, String resultado, String sucursal) {
            return new RetencionRow(cliente, tipo, estado, fechaObjetivo, resultado, sucursal);
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

        public StringProperty fechaObjetivoProperty() {
            return new SimpleStringProperty(fechaObjetivo);
        }

        public StringProperty resultadoProperty() {
            return new SimpleStringProperty(resultado);
        }

        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }
    }
}
