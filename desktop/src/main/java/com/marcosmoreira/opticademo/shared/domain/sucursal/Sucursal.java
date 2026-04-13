package com.marcosmoreira.opticademo.shared.domain.sucursal;

import com.marcosmoreira.opticademo.shared.domain.common.BaseEntity;
import com.marcosmoreira.opticademo.shared.domain.common.EstadoGeneral;

public class Sucursal extends BaseEntity {
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;
    private String responsable;
    private String horarioOperativo;
    private EstadoGeneral estado;
    private boolean cajaHabilitada;
    private boolean inventarioPropio;
    private boolean entregaHabilitada;
    private boolean agendaHabilitada;

    public Sucursal(String id, String nombre) {
        super(id);
        this.nombre = nombre;
        this.estado = EstadoGeneral.ACTIVO;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getResponsable() { return responsable; }
    public void setResponsable(String responsable) { this.responsable = responsable; }
    public String getHorarioOperativo() { return horarioOperativo; }
    public void setHorarioOperativo(String horarioOperativo) { this.horarioOperativo = horarioOperativo; }
    public EstadoGeneral getEstado() { return estado; }
    public void setEstado(EstadoGeneral estado) { this.estado = estado; }
    public boolean isCajaHabilitada() { return cajaHabilitada; }
    public void setCajaHabilitada(boolean cajaHabilitada) { this.cajaHabilitada = cajaHabilitada; }
    public boolean isInventarioPropio() { return inventarioPropio; }
    public void setInventarioPropio(boolean inventarioPropio) { this.inventarioPropio = inventarioPropio; }
    public boolean isEntregaHabilitada() { return entregaHabilitada; }
    public void setEntregaHabilitada(boolean entregaHabilitada) { this.entregaHabilitada = entregaHabilitada; }
    public boolean isAgendaHabilitada() { return agendaHabilitada; }
    public void setAgendaHabilitada(boolean agendaHabilitada) { this.agendaHabilitada = agendaHabilitada; }
}
