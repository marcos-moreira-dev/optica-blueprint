package com.marcosmoreira.opticademo.shared.domain.caja;

import com.marcosmoreira.opticademo.shared.domain.common.BaseEntity;
import com.marcosmoreira.opticademo.shared.domain.common.EstadoGeneral;

public class Cobro extends BaseEntity {
    private String referencia;
    private String ordenId;
    private String clienteNombre;
    private double monto;
    private String metodoPago;
    private String sucursal;
    private String fecha;
    private String comprobante;
    private EstadoGeneral estado;

    public Cobro(String id, String referencia) {
        super(id);
        this.referencia = referencia;
        this.estado = EstadoGeneral.PAGADO;
    }

    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }
    public String getOrdenId() { return ordenId; }
    public void setOrdenId(String ordenId) { this.ordenId = ordenId; }
    public String getClienteNombre() { return clienteNombre; }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre = clienteNombre; }
    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }
    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
    public String getSucursal() { return sucursal; }
    public void setSucursal(String sucursal) { this.sucursal = sucursal; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public String getComprobante() { return comprobante; }
    public void setComprobante(String comprobante) { this.comprobante = comprobante; }
    public EstadoGeneral getEstado() { return estado; }
    public void setEstado(EstadoGeneral estado) { this.estado = estado; }
}
