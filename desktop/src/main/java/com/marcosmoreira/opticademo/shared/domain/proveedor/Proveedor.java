package com.marcosmoreira.opticademo.shared.domain.proveedor;

import com.marcosmoreira.opticademo.shared.domain.common.BaseEntity;
import com.marcosmoreira.opticademo.shared.domain.common.EstadoGeneral;

public class Proveedor extends BaseEntity {
    private String nombreComercial;
    private String codigoInterno;
    private String tipoProveedor;
    private String contacto;
    private String telefono;
    private String correo;
    private String ciudad;
    private String categoriaAbastecida;
    private String sucursalesAtendidas;
    private int tiempoEstimadoDias;
    private EstadoGeneral estado;

    public Proveedor(String id, String nombreComercial) {
        super(id);
        this.nombreComercial = nombreComercial;
        this.estado = EstadoGeneral.ACTIVO;
    }

    public String getNombreComercial() { return nombreComercial; }
    public void setNombreComercial(String nombreComercial) { this.nombreComercial = nombreComercial; }
    public String getCodigoInterno() { return codigoInterno; }
    public void setCodigoInterno(String codigoInterno) { this.codigoInterno = codigoInterno; }
    public String getTipoProveedor() { return tipoProveedor; }
    public void setTipoProveedor(String tipoProveedor) { this.tipoProveedor = tipoProveedor; }
    public String getContacto() { return contacto; }
    public void setContacto(String contacto) { this.contacto = contacto; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public String getCategoriaAbastecida() { return categoriaAbastecida; }
    public void setCategoriaAbastecida(String categoriaAbastecida) { this.categoriaAbastecida = categoriaAbastecida; }
    public String getSucursalesAtendidas() { return sucursalesAtendidas; }
    public void setSucursalesAtendidas(String sucursalesAtendidas) { this.sucursalesAtendidas = sucursalesAtendidas; }
    public int getTiempoEstimadoDias() { return tiempoEstimadoDias; }
    public void setTiempoEstimadoDias(int tiempoEstimadoDias) { this.tiempoEstimadoDias = tiempoEstimadoDias; }
    public EstadoGeneral getEstado() { return estado; }
    public void setEstado(EstadoGeneral estado) { this.estado = estado; }
}
