package com.marcosmoreira.opticademo.modules.taller;

/**
 * Filter criteria for the Taller module.
 */
public class TallerFilters {

    private String searchText;
    private String tipo;
    private String estado;
    private String tecnico;
    private String sucursal;
    private String desde;
    private String hasta;
    private boolean soloPendientesUrgentes;

    public TallerFilters() {
        this.searchText = "";
        this.tipo = "Todos";
        this.estado = "Todos";
        this.tecnico = "Todos";
        this.sucursal = "Todas";
        this.desde = "";
        this.hasta = "";
        this.soloPendientesUrgentes = false;
    }

    public TallerFilters(String searchText, String tipo, String estado,
                         String tecnico, String sucursal, String desde,
                         String hasta, boolean soloPendientesUrgentes) {
        this.searchText = searchText != null ? searchText : "";
        this.tipo = tipo != null ? tipo : "Todos";
        this.estado = estado != null ? estado : "Todos";
        this.tecnico = tecnico != null ? tecnico : "Todos";
        this.sucursal = sucursal != null ? sucursal : "Todas";
        this.desde = desde != null ? desde : "";
        this.hasta = hasta != null ? hasta : "";
        this.soloPendientesUrgentes = soloPendientesUrgentes;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
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

    public boolean isSoloPendientesUrgentes() {
        return soloPendientesUrgentes;
    }

    public void setSoloPendientesUrgentes(boolean soloPendientesUrgentes) {
        this.soloPendientesUrgentes = soloPendientesUrgentes;
    }
}
