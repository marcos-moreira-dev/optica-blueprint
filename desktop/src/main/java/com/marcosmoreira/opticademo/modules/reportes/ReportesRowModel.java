package com.marcosmoreira.opticademo.modules.reportes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Modelos de fila para las vistas del modulo Reportes (analitica y KPIs).
 * <p>
 * Estos registros alimentan los {@code TableView} del modulo: resumen de KPIs,
 * analisis comercial, rotacion de inventario, metricas de agenda, rendimiento
 * de laboratorio, cartera de cobros y retencion de clientes. La fachada crea
 * estas instancias a partir de datos agregados de los distintos modulos del sistema.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see com.marcosmoreira.opticademo.modules.reportes.ReportesFacade
 */
public final class ReportesRowModel {

    private ReportesRowModel() {
    }

    // ------------------------------------------------------------------ KpiResumenRow

    /**
     * Modelo de fila para el resumen de KPIs del dashboard de reportes.
     * <p>
     * Cada registro es un indicador clave de rendimiento. El campo {@code estado}
     * indica si el KPI cumple la meta ("Cumplido", "Parcial", "No cumplido").
     * </p>
     *
     * @param area        area del negocio: "Ventas", "Agenda", "Inventario" (columna "Area")
     * @param indicador   nombre del KPI (columna "Indicador")
     * @param valor       valor actual del indicador (columna "Valor")
     * @param estado      estado vs. la meta (columna "Estado")
     */
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

    /**
     * Modelo de fila para el analisis comercial por categoria.
     * <p>
     * Desglose de ventas por tipo de producto con participacion porcentual.
     * </p>
     *
     * @param categoria      categoria: "Monturas", "Lentes", "Accesorios" (columna "Categoria")
     * @param ventas         monto total vendido (columna "Ventas")
     * @param unidades       cantidad de unidades vendidas (columna "Unidades")
     * @param participacion  porcentaje del total (columna "Participacion")
     * @param observacion    nota analitica (columna "Observacion")
     */
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

    /**
     * Modelo de fila para el reporte de rotacion de inventario.
     * <p>
     * Muestra la velocidad de rotacion de cada producto en el periodo analizado.
     * </p>
     *
     * @param referencia    codigo SKU (columna "Referencia")
     * @param nombre        nombre del producto (columna "Nombre")
     * @param categoria     categoria (columna "Categoria")
     * @param rotacion      indice de rotacion (columna "Rotacion")
     * @param ultimaSalida  fecha de ultima venta (columna "Ultima Salida")
     * @param estado        estado de rotacion (columna "Estado")
     * @param observacion   nota analitica (columna "Observacion")
     */
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

    /**
     * Modelo de fila para las metricas de rendimiento de la agenda.
     * <p>
     * KPIs de gestion de citas: tasa de asistencia, cancelaciones, etc.
     * Incluye comparacion del valor actual vs. la meta establecida.
     * </p>
     *
     * @param indicador   nombre de la metrica (columna "Indicador")
     * @param valor       valor medido (columna "Valor")
     * @param meta        objetivo establecido (columna "Meta")
     * @param estado      cumplimiento: "Cumplido", "No cumplido" (columna "Estado")
     * @param observacion nota analitica (columna "Observacion")
     */
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

    /**
     * Modelo de fila para las metricas de rendimiento del laboratorio.
     * <p>
     * KPIs de produccion: tiempo promedio de elaboracion, tasa de reclamos,
     * ordenes a tiempo, etc.
     * </p>
     *
     * @param indicador   nombre de la metrica (columna "Indicador")
     * @param valor       valor medido (columna "Valor")
     * @param estado      estado del indicador (columna "Estado")
     * @param observacion nota analitica (columna "Observacion")
     */
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

    /**
     * Modelo de fila para el reporte de cartera (cobros pendientes).
     * <p>
     * Lista ordenes con saldo pendiente para analisis de cobranza.
     * </p>
     *
     * @param orden      numero de orden (columna "Orden")
     * @param cliente    nombre del paciente (columna "Cliente")
     * @param saldo      monto pendiente (columna "Saldo")
     * @param ultimoPago fecha del ultimo pago (columna "Ultimo Pago")
     * @param estado     estado del cobro (columna "Estado")
     * @param sucursal   sede de la orden (columna "Sucursal")
     */
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

    /**
     * Modelo de fila para el reporte de retencion de clientes.
     * <p>
     * Muestra acciones de retencion realizadas y su resultado: recalls,
     * seguimientos, campanas. El campo {@code estado} indica el resultado
     * de la gestion.
     * </p>
     *
     * @param cliente        nombre del paciente (columna "Cliente")
     * @param tipo           tipo de accion: "Recall", "Seguimiento" (columna "Tipo")
     * @param estado         resultado de la gestion (columna "Estado")
     * @param fechaObjetivo  fecha planificada (columna "Fecha Objetivo")
     * @param resultado      resultado obtenido (columna "Resultado")
     * @param sucursal       sede responsable (columna "Sucursal")
     */
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
