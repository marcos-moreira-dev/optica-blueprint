package com.marcosmoreira.opticademo.shared.domain.producto;

import com.marcosmoreira.opticademo.shared.domain.common.BaseEntity;
import com.marcosmoreira.opticademo.shared.domain.common.EstadoGeneral;

public class Producto extends BaseEntity {
    private String referencia;
    private String nombre;
    private String categoria;
    private String marca;
    private String sucursal;
    private int stock;
    private int stockMinimo;
    private double precioVenta;
    private EstadoGeneral estado;
    private String proveedorPrincipal;

    public Producto(String id, String referencia, String nombre) {
        super(id);
        this.referencia = referencia;
        this.nombre = nombre;
        this.estado = EstadoGeneral.ACTIVO;
    }

    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getSucursal() { return sucursal; }
    public void setSucursal(String sucursal) { this.sucursal = sucursal; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public int getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(int stockMinimo) { this.stockMinimo = stockMinimo; }
    public double getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(double precioVenta) { this.precioVenta = precioVenta; }
    public EstadoGeneral getEstado() { return estado; }
    public void setEstado(EstadoGeneral estado) { this.estado = estado; }
    public String getProveedorPrincipal() { return proveedorPrincipal; }
    public void setProveedorPrincipal(String proveedorPrincipal) { this.proveedorPrincipal = proveedorPrincipal; }
}
