package com.marcosmoreira.opticademo.modules.caja;

/**
 * Filter criteria for the Caja module.
 */
public class CajaFilters {

    private String searchText;
    private String estado;
    private String metodoPago;
    private String sucursal;
    private String desde;
    private String hasta;
    private boolean soloPendientes;

    public CajaFilters() {
        this.searchText = "";
        this.estado = "Todos";
        this.metodoPago = "Todos";
        this.sucursal = "Todas";
        this.desde = "";
        this.hasta = "";
        this.soloPendientes = false;
    }

    public CajaFilters(String searchText, String estado, String metodoPago,
                       String sucursal, String desde, String hasta, boolean soloPendientes) {
        this.searchText = searchText != null ? searchText : "";
        this.estado = estado != null ? estado : "Todos";
        this.metodoPago = metodoPago != null ? metodoPago : "Todos";
        this.sucursal = sucursal != null ? sucursal : "Todas";
        this.desde = desde != null ? desde : "";
        this.hasta = hasta != null ? hasta : "";
        this.soloPendientes = soloPendientes;
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

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
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

    public boolean isSoloPendientes() {
        return soloPendientes;
    }

    public void setSoloPendientes(boolean soloPendientes) {
        this.soloPendientes = soloPendientes;
    }
}
