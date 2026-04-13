package com.marcosmoreira.opticademo.modules.seguros;

/**
 * Filter criteria for the Seguros module.
 */
public class SegurosFilters {

    private String searchText;
    private String estado;
    private String planConvenio;
    private String tipoCaso;
    private String sucursal;
    private String desde;
    private String hasta;
    private boolean soloCasosPendientes;

    public SegurosFilters() {
        this.searchText = "";
        this.estado = "Todos";
        this.planConvenio = "Todos";
        this.tipoCaso = "Todos";
        this.sucursal = "Todas";
        this.desde = "";
        this.hasta = "";
        this.soloCasosPendientes = false;
    }

    public SegurosFilters(String searchText, String estado, String planConvenio,
                          String tipoCaso, String sucursal, String desde,
                          String hasta, boolean soloCasosPendientes) {
        this.searchText = searchText != null ? searchText : "";
        this.estado = estado != null ? estado : "Todos";
        this.planConvenio = planConvenio != null ? planConvenio : "Todos";
        this.tipoCaso = tipoCaso != null ? tipoCaso : "Todos";
        this.sucursal = sucursal != null ? sucursal : "Todas";
        this.desde = desde != null ? desde : "";
        this.hasta = hasta != null ? hasta : "";
        this.soloCasosPendientes = soloCasosPendientes;
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

    public String getPlanConvenio() {
        return planConvenio;
    }

    public void setPlanConvenio(String planConvenio) {
        this.planConvenio = planConvenio;
    }

    public String getTipoCaso() {
        return tipoCaso;
    }

    public void setTipoCaso(String tipoCaso) {
        this.tipoCaso = tipoCaso;
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

    public boolean isSoloCasosPendientes() {
        return soloCasosPendientes;
    }

    public void setSoloCasosPendientes(boolean soloCasosPendientes) {
        this.soloCasosPendientes = soloCasosPendientes;
    }
}
