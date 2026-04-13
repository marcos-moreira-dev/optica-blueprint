package com.marcosmoreira.opticademo.modules.ordeneslaboratorio;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Modelos de fila para las vistas del modulo Ordenes de Laboratorio.
 * <p>
 * Estos registros alimentan los {@code TableView} del modulo: cola de ordenes
 * (vista principal), seguimiento por etapas, envio externo, recepcion, incidencias
 * e historico. La fachada crea estas instancias a partir de las entidades
 * {@code OrdenLaboratorio} del dominio, mapeando el estado de cada etapa del flujo
 * de laboratorio (recepcion, elaboracion, control de calidad, despacho).
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see com.marcosmoreira.opticademo.modules.ordeneslaboratorio.OrdenesLabFacade
 */
public class OrdenesLabRowModel {

    private OrdenesLabRowModel() {
    }

    /**
     * Modelo de fila para la cola de ordenes de laboratorio (vista principal).
     * <p>
     * Cada registro representa una orden en proceso de elaboracion. El campo
     * {@code estado} refleja la etapa actual: "Recibida", "En elaboracion",
     * "Enviado a externo", "Retornado", "Entregado". El campo {@code prioridad}
     * indica la urgencia ("Alta", "Normal", "Baja").
     * </p>
     *
     * @param referencia    identificador de la orden (columna "Referencia")
     * @param cliente       nombre del paciente (columna "Cliente")
     * @param tipoTrabajo   tipo de trabajo: "Lentes graduados", "Montura + Lentes" (columna "Tipo")
     * @param ingreso       fecha de ingreso de la orden (columna "Ingreso")
     * @param fechaPromesa  fecha prometida de entrega (columna "Fecha Promesa")
     * @param estado        estado actual de la orden (columna "Estado")
     * @param laboratorio   laboratorio asignado (columna "Laboratorio")
     * @param prioridad     nivel de prioridad (columna "Prioridad")
     * @param sucursal      sucursal de origen (columna "Sucursal")
     */
    public record ColaRow(
            String referencia,
            String cliente,
            String tipoTrabajo,
            String ingreso,
            String fechaPromesa,
            String estado,
            String laboratorio,
            String prioridad,
            String sucursal
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

        public StringProperty ingresoProperty() {
            return new SimpleStringProperty(ingreso);
        }

        public StringProperty fechaPromesaProperty() {
            return new SimpleStringProperty(fechaPromesa);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty laboratorioProperty() {
            return new SimpleStringProperty(laboratorio);
        }

        public StringProperty prioridadProperty() {
            return new SimpleStringProperty(prioridad);
        }

        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }
    }

    /**
     * Modelo de fila para el seguimiento por etapas de una orden.
     * <p>
     * Muestra la trazabilidad de eventos de una orden de laboratorio: cada registro
     * es un paso del flujo (recepcion, montaje, control de calidad, despacho).
     * </p>
     *
     * @param fecha        fecha del evento (columna "Fecha")
     * @param evento       tipo de evento: "Recibido", "Montaje", "Control" (columna "Evento")
     * @param responsable  tecnico o persona que realizo el paso (columna "Responsable")
     * @param observacion  detalles del evento (columna "Observacion")
     */
    public record EtapaRow(
            String fecha,
            String evento,
            String responsable,
            String observacion
    ) {
        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty eventoProperty() {
            return new SimpleStringProperty(evento);
        }

        public StringProperty responsableProperty() {
            return new SimpleStringProperty(responsable);
        }

        public StringProperty observacionProperty() {
            return new SimpleStringProperty(observacion);
        }
    }

    /**
     * Modelo de fila para el bloque de envio a laboratorio externo.
     *
     * @param laboratorio            nombre del laboratorio externo (columna "Laboratorio")
     * @param fechaEnvio             fecha de despacho al laboratorio (columna "Fecha Envio")
     * @param guia                   numero de guia de despacho (columna "Guia")
     * @param transportista          empresa de transporte (columna "Transportista")
     * @param fechaEstimadaRetorno   fecha estimada de retorno del trabajo (columna "Retorno Estimado")
     */
    public record EnvioRow(
            String laboratorio,
            String fechaEnvio,
            String guia,
            String transportista,
            String fechaEstimadaRetorno
    ) {
        public StringProperty laboratorioProperty() {
            return new SimpleStringProperty(laboratorio);
        }

        public StringProperty fechaEnvioProperty() {
            return new SimpleStringProperty(fechaEnvio);
        }

        public StringProperty guiaProperty() {
            return new SimpleStringProperty(guia);
        }

        public StringProperty transportistaProperty() {
            return new SimpleStringProperty(transportista);
        }

        public StringProperty fechaEstimadaRetornoProperty() {
            return new SimpleStringProperty(fechaEstimadaRetorno);
        }
    }

    /**
     * Modelo de fila para el bloque de recepcion de orden del laboratorio.
     *
     * @param fechaRecepcion     fecha de recepcion del trabajo (columna "Fecha Recepcion")
     * @param recibidoPor        persona que recibio (columna "Recibido Por")
     * @param sucursalRecepcion  sede donde se recibio (columna "Sucursal")
     * @param estadoAlRecibir    estado del trabajo al recibirlo: "Conforme", "Con novedad" (columna "Estado")
     */
    public record RecepcionRow(
            String fechaRecepcion,
            String recibidoPor,
            String sucursalRecepcion,
            String estadoAlRecibir
    ) {
        public StringProperty fechaRecepcionProperty() {
            return new SimpleStringProperty(fechaRecepcion);
        }

        public StringProperty recibidoPorProperty() {
            return new SimpleStringProperty(recibidoPor);
        }

        public StringProperty sucursalRecepcionProperty() {
            return new SimpleStringProperty(sucursalRecepcion);
        }

        public StringProperty estadoAlRecibirProperty() {
            return new SimpleStringProperty(estadoAlRecibir);
        }
    }

    /**
     * Modelo de fila para la tabla de incidencias de laboratorio.
     * <p>
     * Registra problemas durante el proceso de elaboracion: lentes rotos,
     * medidas incorrectas, montura danada. El campo {@code estado} indica
     * si la incidencia fue "Resuelta" o esta "Pendiente".
     * </p>
     *
     * @param fecha        fecha de registro de la incidencia (columna "Fecha")
     * @param tipo         tipo de incidencia: "Daño", "Medida incorrecta", "Falta material" (columna "Tipo")
     * @param estado       estado de resolucion (columna "Estado")
     * @param responsable  quien atendio la incidencia (columna "Responsable")
     * @param resolucion   descripcion de la solucion aplicada (columna "Resolucion")
     */
    public record IncidenciaRow(
            String fecha,
            String tipo,
            String estado,
            String responsable,
            String resolucion
    ) {
        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty tipoProperty() {
            return new SimpleStringProperty(tipo);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty responsableProperty() {
            return new SimpleStringProperty(responsable);
        }

        public StringProperty resolucionProperty() {
            return new SimpleStringProperty(resolucion);
        }
    }

    /**
     * Modelo de fila para el historico de ordenes de laboratorio.
     * <p>
     * Vista de archivo con todas las ordenes completadas. El campo {@code estadoFinal}
     * muestra el resultado: "Entregada", "Anulada", "Devolvida".
     * </p>
     *
     * @param referencia    identificador de la orden (columna "Referencia")
     * @param cliente       nombre del paciente (columna "Cliente")
     * @param ingreso       fecha de ingreso (columna "Ingreso")
     * @param fechaPromesa  fecha prometida original (columna "Fecha Promesa")
     * @param estadoFinal   resultado final de la orden (columna "Estado Final")
     * @param laboratorio   laboratorio que elaboro (columna "Laboratorio")
     * @param sucursal      sucursal de destino (columna "Sucursal")
     */
    public record HistoricoRow(
            String referencia,
            String cliente,
            String ingreso,
            String fechaPromesa,
            String estadoFinal,
            String laboratorio,
            String sucursal
    ) {
        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty clienteProperty() {
            return new SimpleStringProperty(cliente);
        }

        public StringProperty ingresoProperty() {
            return new SimpleStringProperty(ingreso);
        }

        public StringProperty fechaPromesaProperty() {
            return new SimpleStringProperty(fechaPromesa);
        }

        public StringProperty estadoFinalProperty() {
            return new SimpleStringProperty(estadoFinal);
        }

        public StringProperty laboratorioProperty() {
            return new SimpleStringProperty(laboratorio);
        }

        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }
    }
}
