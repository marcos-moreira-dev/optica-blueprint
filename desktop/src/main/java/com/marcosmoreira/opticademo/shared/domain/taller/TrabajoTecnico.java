package com.marcosmoreira.opticademo.shared.domain.taller;

import com.marcosmoreira.opticademo.shared.domain.common.BaseEntity;
import com.marcosmoreira.opticademo.shared.domain.common.EstadoGeneral;
import com.marcosmoreira.opticademo.shared.domain.common.Prioridad;

public class TrabajoTecnico extends BaseEntity {
    private String referencia;
    private String clienteNombre;
    private String tipoIntervencion;
    private String sucursal;
    private String tecnicoResponsable;
    private EstadoGeneral estado;
    private Prioridad prioridad;
    private String fechaIngreso;
    private String fechaPromesa;
    private String repuestoRequerido;
    private boolean envioExterno;
    private String observacion;

    public TrabajoTecnico(String id, String referencia) {
        super(id);
        this.referencia = referencia;
        this.prioridad = Prioridad.MEDIA;
    }

    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }
    public String getClienteNombre() { return clienteNombre; }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre = clienteNombre; }
    public String getTipoIntervencion() { return tipoIntervencion; }
    public void setTipoIntervencion(String tipoIntervencion) { this.tipoIntervencion = tipoIntervencion; }
    public String getSucursal() { return sucursal; }
    public void setSucursal(String sucursal) { this.sucursal = sucursal; }
    public String getTecnicoResponsable() { return tecnicoResponsable; }
    public void setTecnicoResponsable(String tecnicoResponsable) { this.tecnicoResponsable = tecnicoResponsable; }
    public EstadoGeneral getEstado() { return estado; }
    public void setEstado(EstadoGeneral estado) { this.estado = estado; }
    public Prioridad getPrioridad() { return prioridad; }
    public void setPrioridad(Prioridad prioridad) { this.prioridad = prioridad; }
    public String getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(String fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    public String getFechaPromesa() { return fechaPromesa; }
    public void setFechaPromesa(String fechaPromesa) { this.fechaPromesa = fechaPromesa; }
    public String getRepuestoRequerido() { return repuestoRequerido; }
    public void setRepuestoRequerido(String repuestoRequerido) { this.repuestoRequerido = repuestoRequerido; }
    public boolean isEnvioExterno() { return envioExterno; }
    public void setEnvioExterno(boolean envioExterno) { this.envioExterno = envioExterno; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
}
