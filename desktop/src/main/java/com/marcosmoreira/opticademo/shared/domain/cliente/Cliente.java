package com.marcosmoreira.opticademo.shared.domain.cliente;

import com.marcosmoreira.opticademo.shared.domain.common.BaseEntity;
import com.marcosmoreira.opticademo.shared.domain.common.EstadoGeneral;

public class Cliente extends BaseEntity {
    private String nombreCompleto;
    private String documento;
    private String telefono;
    private String telefonoAlterno;
    private String email;
    private String direccion;
    private String ciudad;
    private String codigoInterno;
    private String sucursalHabitual;
    private EstadoGeneral estado;
    private String ultimaVisita;
    private String ultimaReceta;
    private String estadoReceta;

    public Cliente(String id, String nombreCompleto) {
        super(id);
        this.nombreCompleto = nombreCompleto;
        this.estado = EstadoGeneral.ACTIVO;
    }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getTelefonoAlterno() { return telefonoAlterno; }
    public void setTelefonoAlterno(String telefonoAlterno) { this.telefonoAlterno = telefonoAlterno; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public String getCodigoInterno() { return codigoInterno; }
    public void setCodigoInterno(String codigoInterno) { this.codigoInterno = codigoInterno; }
    public String getSucursalHabitual() { return sucursalHabitual; }
    public void setSucursalHabitual(String sucursalHabitual) { this.sucursalHabitual = sucursalHabitual; }
    public EstadoGeneral getEstado() { return estado; }
    public void setEstado(EstadoGeneral estado) { this.estado = estado; }
    public String getUltimaVisita() { return ultimaVisita; }
    public void setUltimaVisita(String ultimaVisita) { this.ultimaVisita = ultimaVisita; }
    public String getUltimaReceta() { return ultimaReceta; }
    public void setUltimaReceta(String ultimaReceta) { this.ultimaReceta = ultimaReceta; }
    public String getEstadoReceta() { return estadoReceta; }
    public void setEstadoReceta(String estadoReceta) { this.estadoReceta = estadoReceta; }
}
