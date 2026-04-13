package com.marcosmoreira.opticademo.modules.compras;

/**
 * Filter criteria for the Compras module.
 */
public class ComprasFilters {

    private String searchText;
    private String estado;
    private String proveedor;
    private String categoria;
    private String sucursal;
    private String desde;
    private String hasta;
    private boolean soloPendientesCriticos;

    public ComprasFilters() {
        this.searchText = "";
        this.estado = "Todos";
        this.proveedor = "Todos";
        this.categoria = "Todas";
        this.sucursal = "Todas";
        this.desde = "";
        this.hasta = "";
        this.soloPendientesCriticos = false;
    }

    public ComprasFilters(String searchText, String estado, String proveedor,
                          String categoria, String sucursal, String desde,
                          String hasta, boolean soloPendientesCriticos) {
        this.searchText = searchText != null ? searchText : "";
        this.estado = estado != null ? estado : "Todos";
        this.proveedor = proveedor != null ? proveedor : "Todos";
        this.categoria = categoria != null ? categoria : "Todas";
        this.sucursal = sucursal != null ? sucursal : "Todas";
        this.desde = desde != null ? desde : "";
        this.hasta = hasta != null ? hasta : "";
        this.soloPendientesCriticos = soloPendientesCriticos;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    public boolean isSoloPendientesCriticos() {
        return soloPendientesCriticos;
    }

    public void setSoloPendientesCriticos(boolean soloPendientesCriticos) {
        this.soloPendientesCriticos = soloPendientesCriticos;
    }
}
