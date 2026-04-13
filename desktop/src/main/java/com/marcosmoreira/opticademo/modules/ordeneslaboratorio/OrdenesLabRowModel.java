package com.marcosmoreira.opticademo.modules.ordeneslaboratorio;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Row models for the Ordenes de Laboratorio module.
 */
public class OrdenesLabRowModel {

    private OrdenesLabRowModel() {
    }

    /**
     * Row model for the Cola de ordenes TableView.
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
     * Row model for the Seguimiento por etapas TableView.
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
     * Row model for the Envio block.
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
     * Row model for the Recepcion block.
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
     * Row model for the Incidencias TableView.
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
     * Row model for the Historico TableView.
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
