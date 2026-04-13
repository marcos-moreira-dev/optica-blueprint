package com.marcosmoreira.opticademo.modules.seguros;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Modelos de fila para las vistas del modulo Seguros (gestion de seguros y convenios).
 * <p>
 * Estos registros alimentan los {@code TableView} del modulo: verificacion de seguros,
 * planes y convenios, autorizaciones, reclamos a aseguradoras, respuestas recibidas,
 * cobertura aplicada en ventas e historico. La fachada crea estas instancias a partir
 * de las entidades {@code Seguro}, {@code Plan} y {@code Reclamo} del dominio.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see com.marcosmoreira.opticademo.modules.seguros.SegurosFacade
 */
public final class SegurosRowModel {

    private SegurosRowModel() {
    }

    // ------------------------------------------------------------------ VerificacionRow

    /**
     * Modelo de fila para la tabla de verificacion de seguros.
     * <p>
     * Muestra el resultado de verificar la cobertura del seguro de un paciente.
     * El campo {@code estado} indica si el seguro esta "Vigente", "Vencido" o
     * "Sin cobertura".
     * </p>
     *
     * @param referencia       identificador de la verificacion (columna "Referencia")
     * @param cliente          nombre del asegurado (columna "Cliente")
     * @param planConvenio     nombre del plan o convenio aplicado (columna "Plan")
     * @param estado           estado de la verificacion (columna "Estado")
     * @param vigencia         periodo de vigencia de la poliza (columna "Vigencia")
     * @param montoDisponible  monto restante del beneficio (columna "Monto Disponible")
     * @param sucursal         sede donde se verifico (columna "Sucursal")
     */
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

    /**
     * Modelo de fila para la tabla de planes y convenios.
     * <p>
     * Detalla los planes de seguros disponibles con sus coberturas y restricciones.
     * </p>
     *
     * @param nombre              nombre del plan (columna "Nombre")
     * @param vigenciaGeneral     vigencia estandar del plan (columna "Vigencia")
     * @param coberturaMaxima     monto maximo cubierto (columna "Cobertura Maxima")
     * @param copago              porcentaje de copago del paciente (columna "Copago")
     * @param categoriasCubiertas categorias incluidas (columna "Categorias Cubiertas")
     * @param restricciones       restricciones del plan (columna "Restricciones")
     */
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

    /**
     * Modelo de fila para la tabla de autorizaciones pre-previas.
     * <p>
     * Registra las autorizaciones solicitadas a la aseguradora antes de realizar
     * el servicio. El campo {@code estado} indica: "Aprobada", "Rechazada", "Pendiente".
     * </p>
     *
     * @param autorizacion numero de autorizacion (columna "Autorizacion")
     * @param cliente      nombre del asegurado (columna "Cliente")
     * @param plan         plan aplicado (columna "Plan")
     * @param fecha        fecha de solicitud (columna "Fecha")
     * @param estado       estado de la autorizacion (columna "Estado")
     * @param observacion  notas sobre la autorizacion (columna "Observacion")
     */
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

    /**
     * Modelo de fila para la tabla de reclamos a aseguradoras.
     * <p>
     * Registra los reclamos enviados para cobro de servicios cubiertos.
     * </p>
     *
     * @param reclamo          numero de reclamo (columna "Reclamo")
     * @param cliente          nombre del asegurado (columna "Cliente")
     * @param ordenRelacionada orden asociada al reclamo (columna "Orden")
     * @param montoReclamado   monto solicitado a la aseguradora (columna "Monto")
     * @param estado           estado: "Enviado", "Aprobado", "Rechazado" (columna "Estado")
     * @param fechaEnvio       fecha de envio del reclamo (columna "Fecha Envio")
     */
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

    /**
     * Modelo de fila para la tabla de respuestas de aseguradoras.
     *
     * @param fecha        fecha de la respuesta (columna "Fecha")
     * @param referencia   identificador del caso (columna "Referencia")
     * @param tipoRespuesta tipo: "Aprobacion", "Rechazo", "Parcial" (columna "Tipo")
     * @param estado       estado de la respuesta (columna "Estado")
     * @param resultado    resultado detallado (columna "Resultado")
     */
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

    /**
     * Modelo de fila para la tabla de cobertura aplicada en ventas.
     * <p>
     * Muestra como se aplico el seguro en cada venta: monto cubierto por la
     * aseguradora, copago del cliente y saldo restante.
     * </p>
     *
     * @param venta          identificador de la venta (columna "Venta")
     * @param cliente        nombre del asegurado (columna "Cliente")
     * @param planAplicado   plan utilizado (columna "Plan Aplicado")
     * @param montoCubierto  monto pagado por el seguro (columna "Monto Cubierto")
     * @param copago         monto que paga el cliente (columna "Copago")
     * @param saldoCliente   saldo pendiente del cliente (columna "Saldo Cliente")
     * @param estado         estado de la cobertura (columna "Estado")
     */
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

    /**
     * Modelo de fila para el historico de gestiones de seguros.
     *
     * @param fecha        fecha del registro (columna "Fecha")
     * @param referencia   identificador (columna "Referencia")
     * @param cliente      nombre del asegurado (columna "Cliente")
     * @param tipoCaso     tipo: "Verificacion", "Reclamo", "Autorizacion" (columna "Tipo")
     * @param estadoFinal  resultado final (columna "Estado Final")
     * @param observacion  nota sobre el caso (columna "Observacion")
     */
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
