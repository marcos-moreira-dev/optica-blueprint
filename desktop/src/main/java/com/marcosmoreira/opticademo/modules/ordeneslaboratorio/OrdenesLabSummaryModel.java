package com.marcosmoreira.opticademo.modules.ordeneslaboratorio;

import com.marcosmoreira.opticademo.shared.domain.venta.VentaOptica;

/**
 * Modelo resumen para el panel lateral derecho persistente del modulo Ordenes Laboratorio.
 * Contiene la informacion detallada de una orden de laboratorio seleccionada, incluyendo
 * tipo de trabajo, componentes opticos, estado de fabricacion y datos de envio.
 * La fachada (OrdenesLabFacade) actualiza este modelo al seleccionar una orden en la tabla
 * principal, mapeando la entidad {@link VentaOptica} mediante el metodo estatico {@code from}.
 *
 * @author Marcos Moreira
 * @version 1.0.0
 */
public record OrdenesLabSummaryModel(
        /** Referencia unica de la orden de laboratorio. */
        String referencia,
        /** Tipo de trabajo solicitado (solo lente, montura+lente, etc.). */
        String tipoTrabajo,
        /** Prioridad asignada a la orden (alta, normal, baja). */
        String prioridad,
        /** Nombre del cliente asociado a la orden. */
        String cliente,
        /** Codigo interno del cliente. */
        String codigoCliente,
        /** Sucursal de origen de la orden. */
        String sucursal,
        /** Fecha prometida de finalizacion. */
        String fechaPromesa,
        /** Referencia de la montura utilizada. */
        String montura,
        /** Tipo de lente solicitado. */
        String lente,
        /** Tratamientos especiales aplicados al lente. */
        String tratamientos,
        /** Distancia pupilar para la fabricacion. */
        String pd,
        /** Estado actual de la orden en el flujo de laboratorio. */
        String estadoActual,
        /** Laboratorio externo encargado de la fabricacion. */
        String laboratorio,
        /** Numero de guia de envio/recepcion. */
        String guia,
        /** Fecha estimada de recepcion del trabajo terminado. */
        String recepcionEsperada
) {

    /**
     * Creates an OrdenesLabSummaryModel from a VentaOptica entity.
     */
    public static OrdenesLabSummaryModel from(VentaOptica venta) {
        return new OrdenesLabSummaryModel(
                venta.getReferencia(),
                venta.getLenteTipo(),
                "Normal",
                venta.getClienteNombre(),
                venta.getClienteId(),
                venta.getSucursal(),
                venta.getFechaPromesa(),
                venta.getMonturaRef() != null ? venta.getMonturaRef() : "-",
                venta.getLenteTipo(),
                "Ninguno",
                "-",
                venta.getEstado() != null ? venta.getEstado().name() : "En proceso",
                venta.getLaboratorio() != null ? venta.getLaboratorio() : "-",
                "-",
                venta.getFechaPromesa()
        );
    }
}
