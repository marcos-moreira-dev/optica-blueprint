package com.marcosmoreira.opticademo.modules.sucursales;

/**
 * Modelo resumen para el panel lateral derecho persistente del modulo Sucursales.
 * Presenta el perfil operativo completo de la sucursal seleccionada, incluyendo
 * datos de ubicacion, responsable, horarios y estado de los modulos habilitados
 * (caja, inventario, entregas, agenda). La fachada (SucursalesFacade) actualiza
 * este modelo al seleccionar una sucursal en la tabla principal.
 *
 * @author Marcos Moreira
 * @version 1.0.0
 */
public record SucursalesSummaryModel(
        /** Nombre de la sucursal. */
        String sucursal,
        /** Ciudad donde se ubica la sucursal. */
        String ciudad,
        /** Direccion fisica de la sucursal. */
        String direccion,
        /** Responsable o encargado de la sucursal. */
        String responsable,
        /** Telefono de contacto de la sucursal. */
        String telefono,
        /** Horario de atencion de la sucursal. */
        String horario,
        /** Indica si la caja esta habilitada en esta sucursal. */
        String cajaHabilitada,
        /** Indica si la sucursal maneja inventario propio. */
        String inventarioPropio,
        /** Indica si la sucursal tiene entregas habilitadas. */
        String entregaHabilitada,
        /** Indica si la sucursal tiene agenda de citas habilitada. */
        String agendaHabilitada,
        /** Estado operativo actual de la sucursal. */
        String estadoActual,
        /** Observacion operativa relevante sobre la sucursal. */
        String observacionOperativa
) {
    public SucursalesSummaryModel {
        if (sucursal == null) sucursal = "";
        if (ciudad == null) ciudad = "";
        if (direccion == null) direccion = "";
        if (responsable == null) responsable = "";
        if (telefono == null) telefono = "";
        if (horario == null) horario = "";
        if (cajaHabilitada == null) cajaHabilitada = "";
        if (inventarioPropio == null) inventarioPropio = "";
        if (entregaHabilitada == null) entregaHabilitada = "";
        if (agendaHabilitada == null) agendaHabilitada = "";
        if (estadoActual == null) estadoActual = "";
        if (observacionOperativa == null) observacionOperativa = "";
    }
}
