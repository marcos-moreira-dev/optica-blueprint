package com.marcosmoreira.opticademo.shared.domain.venta;

import com.marcosmoreira.opticademo.shared.domain.common.BaseEntity;
import com.marcosmoreira.opticademo.shared.domain.common.EstadoGeneral;

public class VentaOptica extends BaseEntity {
    private String referencia;
    private String clienteId;
    private String clienteNombre;
    private String recetaId;
    private String monturaRef;
    private String lenteTipo;
    private String sucursal;
    private String responsable;
    private double precioMontura;
    private double precioLente;
    private double descuento;
    private double abono;
    private double saldo;
    private EstadoGeneral estado;
    private String fechaOrden;
    private String fechaPromesa;
    private String laboratorio;

    public VentaOptica(String id, String referencia) {
        super(id);
        this.referencia = referencia;
        this.estado = EstadoGeneral.EN_PROCESO;
    }

    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }
    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }
    public String getClienteNombre() { return clienteNombre; }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre = clienteNombre; }
    public String getRecetaId() { return recetaId; }
    public void setRecetaId(String recetaId) { this.recetaId = recetaId; }
    public String getMonturaRef() { return monturaRef; }
    public void setMonturaRef(String monturaRef) { this.monturaRef = monturaRef; }
    public String getLenteTipo() { return lenteTipo; }
    public void setLenteTipo(String lenteTipo) { this.lenteTipo = lenteTipo; }
    public String getSucursal() { return sucursal; }
    public void setSucursal(String sucursal) { this.sucursal = sucursal; }
    public String getResponsable() { return responsable; }
    public void setResponsable(String responsable) { this.responsable = responsable; }
    public double getPrecioMontura() { return precioMontura; }
    public void setPrecioMontura(double precioMontura) { this.precioMontura = precioMontura; }
    public double getPrecioLente() { return precioLente; }
    public void setPrecioLente(double precioLente) { this.precioLente = precioLente; }
    public double getDescuento() { return descuento; }
    public void setDescuento(double descuento) { this.descuento = descuento; }
    public double getAbono() { return abono; }
    public void setAbono(double abono) { this.abono = abono; }
    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
    public EstadoGeneral getEstado() { return estado; }
    public void setEstado(EstadoGeneral estado) { this.estado = estado; }
    public String getFechaOrden() { return fechaOrden; }
    public void setFechaOrden(String fechaOrden) { this.fechaOrden = fechaOrden; }
    public String getFechaPromesa() { return fechaPromesa; }
    public void setFechaPromesa(String fechaPromesa) { this.fechaPromesa = fechaPromesa; }
    public String getLaboratorio() { return laboratorio; }
    public void setLaboratorio(String laboratorio) { this.laboratorio = laboratorio; }
}
