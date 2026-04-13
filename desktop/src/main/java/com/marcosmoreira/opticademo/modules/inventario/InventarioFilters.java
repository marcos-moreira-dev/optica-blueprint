package com.marcosmoreira.opticademo.modules.inventario;

/**
 * Filter criteria for the Inventario module.
 */
public class InventarioFilters {

    private String searchText;
    private String categoria;
    private String stock;
    private String marca;
    private String sucursal;
    private String proveedor;
    private boolean soloStockCritico;

    public InventarioFilters() {
        this.searchText = "";
        this.categoria = "Todas";
        this.stock = "Todos";
        this.marca = "Todas";
        this.sucursal = "Todas";
        this.proveedor = "Todos";
        this.soloStockCritico = false;
    }

    public InventarioFilters(String searchText, String categoria, String stock,
                             String marca, String sucursal, String proveedor,
                             boolean soloStockCritico) {
        this.searchText = searchText != null ? searchText : "";
        this.categoria = categoria != null ? categoria : "Todas";
        this.stock = stock != null ? stock : "Todos";
        this.marca = marca != null ? marca : "Todas";
        this.sucursal = sucursal != null ? sucursal : "Todas";
        this.proveedor = proveedor != null ? proveedor : "Todos";
        this.soloStockCritico = soloStockCritico;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public boolean isSoloStockCritico() {
        return soloStockCritico;
    }

    public void setSoloStockCritico(boolean soloStockCritico) {
        this.soloStockCritico = soloStockCritico;
    }
}
