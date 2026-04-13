package com.marcosmoreira.opticademo.modules.sucursales;

/**
 * Summary model for the right-hand persistent panel of the Sucursales module.
 * Shows a complete operational profile of the selected branch.
 */
public record SucursalesSummaryModel(
        String sucursal,
        String ciudad,
        String direccion,
        String responsable,
        String telefono,
        String horario,
        String cajaHabilitada,
        String inventarioPropio,
        String entregaHabilitada,
        String agendaHabilitada,
        String estadoActual,
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
