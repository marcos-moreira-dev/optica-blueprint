package com.marcosmoreira.opticademo.modules.recetas;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Modelos de fila para las sub-vistas del modulo Recetas (gestion de recetas opticas).
 * <p>
 * Estos registros alimentan los {@code TableView} del modulo: historial de recetas
 * del paciente, medidas y parametros opticos (DP, altura, etc.), y vinculacion con
 * ordenes opticas. La fachada crea estas instancias a partir de las entidades
 * {@code Receta} y {@code Medicion} del dominio.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see com.marcosmoreira.opticademo.modules.recetas.RecetasFacade
 * @see com.marcosmoreira.opticademo.modules.recetas.RecetasSummaryModel
 */
public final class RecetasRowModel {

    private RecetasRowModel() {
    }

    /**
     * Modelo de fila para el historial de recetas del paciente.
     * <p>
     * Cada registro representa una receta previa del paciente. Los campos {@code odResumen}
     * y {@code oiResumen} contienen un resumen de la graduacion del ojo derecho e izquierdo
     * respectivamente (esfera, cilindro, eje). El campo {@code estado} indica si la receta
     * esta "Vigente" o "Vencida" segun su fecha de emision.
     * </p>
     *
     * @param fecha             fecha de emision de la receta (columna "Fecha")
     * @param profesional       nombre del profesional que emitio la receta (columna "Profesional")
     * @param estado            vigencia de la receta: "Vigente", "Vencida" (columna "Estado")
     * @param odResumen         resumen de graduacion del ojo derecho, ej. "-2.00 / -0.50 x 180" (columna "OD")
     * @param oiResumen         resumen de graduacion del ojo izquierdo (columna "OI")
     * @param pd                distancia pupilar en milimetros (columna "DP")
     * @param observacionBreve  nota corta del profesional (columna "Observacion")
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
     * Modelo de fila para la tabla de medidas y parametros opticos.
     * <p>
     * Muestra los parametros de medicion asociados a una receta: distancia pupilar,
     * altura de montaje, distancia naso-pupilar, etc. Cada fila representa un parametro
     * individual con su valor y unidad de medida.
     * </p>
     *
     * @param parametro     nombre del parametro, ej. "DP", "Altura", "DNP" (columna "Parametro")
     * @param valor         valor numerico medido (columna "Valor")
     * @param unidad        unidad de medida, generalmente "mm" (columna "Unidad")
     * @param observacion   nota sobre la medicion (columna "Observacion")
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
     * Modelo de fila para la vinculacion de recetas con ordenes opticas.
     * <p>
     * Muestra la conexion entre una receta y la orden de venta generada a partir de ella.
     * Permite rastrear el estado de la orden asociada, el saldo pendiente y la fecha
     * estimada de entrega.
     * </p>
     *
     * @param referenciaOrden  identificador de la orden vinculada (columna "Orden")
     * @param estadoOrden      estado de la orden: "Pendiente", "En proceso", "Entregada" (columna "Estado Orden")
     * @param fecha            fecha de creacion de la orden (columna "Fecha")
     * @param saldo            saldo pendiente de pago (columna "Saldo")
     * @param entrega          fecha estimada de entrega (columna "Entrega")
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
