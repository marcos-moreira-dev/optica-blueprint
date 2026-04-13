package com.marcosmoreira.opticademo.modules.taller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Modelos de fila para las vistas del modulo Taller (reparacion y ajuste de lentes).
 * <p>
 * Estos registros alimentan los {@code TableView} del modulo: ingreso de trabajos,
 * diagnostico tecnico, reparaciones, repuestos utilizados, envios externos,
 * entregas e historico. La fachada crea estas instancias a partir de las entidades
 * {@code TrabajoTaller} del dominio.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see com.marcosmoreira.opticademo.modules.taller.TallerFacade
 */
public final class TallerRowModel {

    private TallerRowModel() {
    }

    // ------------------------------------------------------------------ IngresoRow

    /**
     * Modelo de fila para la tabla de ingresos de trabajos al taller.
     * <p>
     * Cada registro es un trabajo ingresado al taller para reparacion o ajuste.
     * El campo {@code estado} refleja la etapa actual del flujo.
     * </p>
     *
     * @param referencia    identificador del trabajo (columna "Referencia")
     * @param cliente       nombre del paciente (columna "Cliente")
     * @param tipo          tipo: "Reparacion", "Ajuste", "Cambio repuesto" (columna "Tipo")
     * @param estado        estado: "Ingresado", "En diagnostico", "En reparacion" (columna "Estado")
     * @param tecnico       tecnico asignado (columna "Tecnico")
     * @param fechaPromesa  fecha prometida de entrega (columna "Fecha Promesa")
     * @param sucursal      sede del taller (columna "Sucursal")
     */
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

    /**
     * Modelo de fila para el diagnostico tecnico de los trabajos.
     * <p>
     * Detalla el danio encontrado, la complejidad de la reparacion y si se
     * requieren repuestos o envio a taller externo.
     * </p>
     *
     * @param referencia           identificador del trabajo (columna "Referencia")
     * @param cliente              nombre del paciente (columna "Cliente")
     * @param tipoTrabajo          tipo de trabajo (columna "Tipo Trabajo")
     * @param fechaIngreso         fecha de ingreso al taller (columna "Fecha Ingreso")
     * @param danioPrincipal       danio encontrado (columna "Dano Principal")
     * @param complejidad          nivel: "Simple", "Media", "Compleja" (columna "Complejidad")
     * @param requiereRepuesto     si necesita repuestos: "Si", "No" (columna "Requiere Repuesto")
     * @param requiereEnvioExterno si se envia a externo: "Si", "No" (columna "Envio Externo")
     */
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

    /**
     * Modelo de fila para el registro de intervenciones de reparacion.
     *
     * @param fecha        fecha de la intervencion (columna "Fecha")
     * @param referencia   identificador del trabajo (columna "Referencia")
     * @param intervencion descripcion del trabajo realizado (columna "Intervencion")
     * @param tecnico      tecnico que realizo la reparacion (columna "Tecnico")
     * @param estado       estado de la reparacion (columna "Estado")
     */
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

    /**
     * Modelo de fila para las piezas y repuestos utilizados en el trabajo.
     *
     * @param refTrabajo  referencia del trabajo (columna "Ref Trabajo")
     * @param pieza       nombre de la pieza utilizada (columna "Pieza")
     * @param cantidad    cantidad utilizada (columna "Cantidad")
     * @param estado      estado de la pieza: "En stock", "Solicitada" (columna "Estado")
     * @param observacion nota sobre el repuesto (columna "Observacion")
     */
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

    /**
     * Modelo de fila para los trabajos enviados a taller externo.
     * <p>
     * Registra los trabajos que no pueden repararse internamente y se envian
     * a un tercero especializado.
     * </p>
     *
     * @param referencia     identificador del trabajo (columna "Referencia")
     * @param tipoTrabajo    tipo de trabajo enviado (columna "Tipo Trabajo")
     * @param tercero        nombre del tercero receptor (columna "Tercero")
     * @param fechaEnvio     fecha de envio (columna "Fecha Envio")
     * @param estado         estado del envio externo (columna "Estado")
     * @param fechaEstimada  fecha estimada de retorno (columna "Fecha Estimada")
     */
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

    /**
     * Modelo de fila para las entregas de trabajos del taller.
     *
     * @param referencia        identificador del trabajo (columna "Referencia")
     * @param fechaEntrega      fecha de entrega (columna "Fecha Entrega")
     * @param responsableEntrega quien realizo la entrega (columna "Responsable Entrega")
     * @param estadoFinal       estado final: "Entregado", "Pendiente" (columna "Estado Final")
     * @param clienteConforme   si el cliente recibio conforme (columna "Cliente Conforme")
     */
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

    /**
     * Modelo de fila para el historico de trabajos del taller.
     *
     * @param fecha        fecha del trabajo (columna "Fecha")
     * @param referencia   identificador (columna "Referencia")
     * @param cliente      nombre del paciente (columna "Cliente")
     * @param tipo         tipo de trabajo (columna "Tipo")
     * @param estadoFinal  resultado final (columna "Estado Final")
     * @param observacion  nota sobre el trabajo (columna "Observacion")
     */
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
