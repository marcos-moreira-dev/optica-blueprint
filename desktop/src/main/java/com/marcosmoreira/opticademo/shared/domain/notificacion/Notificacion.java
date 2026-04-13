package com.marcosmoreira.opticademo.shared.domain.notificacion;

import com.marcosmoreira.opticademo.shared.domain.common.BaseEntity;
import com.marcosmoreira.opticademo.shared.domain.common.EstadoGeneral;
import com.marcosmoreira.opticademo.shared.domain.common.Prioridad;

public class Notificacion extends BaseEntity {
    private String referencia;
    private String tipo;
    private String canal;
    private String clienteNombre;
    private String ordenRelacionada;
    private String sucursal;
    private String moduloOrigen;
    private EstadoGeneral estado;
    private Prioridad prioridad;
    private String fechaHora;
    private String observacion;

    public Notificacion(String id, String referencia) {
        super(id);
        this.referencia = referencia;
        this.prioridad = Prioridad.MEDIA;
    }

    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getCanal() { return canal; }
    public void setCanal(String canal) { this.canal = canal; }
    public String getClienteNombre() { return clienteNombre; }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre = clienteNombre; }
    public String getOrdenRelacionada() { return ordenRelacionada; }
    public void setOrdenRelacionada(String ordenRelacionada) { this.ordenRelacionada = ordenRelacionada; }
    public String getSucursal() { return sucursal; }
    public void setSucursal(String sucursal) { this.sucursal = sucursal; }
    public String getModuloOrigen() { return moduloOrigen; }
    public void setModuloOrigen(String moduloOrigen) { this.moduloOrigen = moduloOrigen; }
    public EstadoGeneral getEstado() { return estado; }
    public void setEstado(EstadoGeneral estado) { this.estado = estado; }
    public Prioridad getPrioridad() { return prioridad; }
    public void setPrioridad(Prioridad prioridad) { this.prioridad = prioridad; }
    public String getFechaHora() { return fechaHora; }
    public void setFechaHora(String fechaHora) { this.fechaHora = fechaHora; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
}
