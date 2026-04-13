package com.marcosmoreira.opticademo.shared.domain.usuario;

import com.marcosmoreira.opticademo.shared.domain.common.BaseEntity;
import com.marcosmoreira.opticademo.shared.domain.common.EstadoGeneral;

public class Usuario extends BaseEntity {
    private String correo;
    private String nombreVisible;
    private RolSistema rol;
    private String sucursal;
    private EstadoGeneral estado;
    private String ultimoAcceso;

    public Usuario(String id, String correo, String nombreVisible, RolSistema rol) {
        super(id);
        this.correo = correo;
        this.nombreVisible = nombreVisible;
        this.rol = rol;
        this.estado = EstadoGeneral.ACTIVO;
    }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getNombreVisible() { return nombreVisible; }
    public void setNombreVisible(String nombreVisible) { this.nombreVisible = nombreVisible; }
    public RolSistema getRol() { return rol; }
    public void setRol(RolSistema rol) { this.rol = rol; }
    public String getSucursal() { return sucursal; }
    public void setSucursal(String sucursal) { this.sucursal = sucursal; }
    public EstadoGeneral getEstado() { return estado; }
    public void setEstado(EstadoGeneral estado) { this.estado = estado; }
    public String getUltimoAcceso() { return ultimoAcceso; }
    public void setUltimoAcceso(String ultimoAcceso) { this.ultimoAcceso = ultimoAcceso; }
}
