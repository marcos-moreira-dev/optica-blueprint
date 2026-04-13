package com.marcosmoreira.opticademo.shared.domain.cobertura;

import com.marcosmoreira.opticademo.shared.domain.common.BaseEntity;
import com.marcosmoreira.opticademo.shared.domain.common.EstadoGeneral;

public class CasoCobertura extends BaseEntity {
    private String referencia;
    private String tipoCaso;
    private String clienteNombre;
    private String planConvenio;
    private String sucursal;
    private String vigencia;
    private double montoCubierto;
    private double copago;
    private double saldoCliente;
    private EstadoGeneral estado;

    public CasoCobertura(String id, String referencia) {
        super(id);
        this.referencia = referencia;
    }

    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }
    public String getTipoCaso() { return tipoCaso; }
    public void setTipoCaso(String tipoCaso) { this.tipoCaso = tipoCaso; }
    public String getClienteNombre() { return clienteNombre; }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre = clienteNombre; }
    public String getPlanConvenio() { return planConvenio; }
    public void setPlanConvenio(String planConvenio) { this.planConvenio = planConvenio; }
    public String getSucursal() { return sucursal; }
    public void setSucursal(String sucursal) { this.sucursal = sucursal; }
    public String getVigencia() { return vigencia; }
    public void setVigencia(String vigencia) { this.vigencia = vigencia; }
    public double getMontoCubierto() { return montoCubierto; }
    public void setMontoCubierto(double montoCubierto) { this.montoCubierto = montoCubierto; }
    public double getCopago() { return copago; }
    public void setCopago(double copago) { this.copago = copago; }
    public double getSaldoCliente() { return saldoCliente; }
    public void setSaldoCliente(double saldoCliente) { this.saldoCliente = saldoCliente; }
    public EstadoGeneral getEstado() { return estado; }
    public void setEstado(EstadoGeneral estado) { this.estado = estado; }
}
