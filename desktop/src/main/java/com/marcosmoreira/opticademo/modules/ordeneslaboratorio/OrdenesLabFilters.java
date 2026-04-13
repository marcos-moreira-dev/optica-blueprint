package com.marcosmoreira.opticademo.modules.ordeneslaboratorio;

/**
 * Filter criteria for the Ordenes de Laboratorio module.
 */
public class OrdenesLabFilters {

    private String searchText;
    private String estado;
    private String laboratorio;
    private String sucursal;
    private String prioridad;
    private String desde;
    private String hasta;

    public OrdenesLabFilters() {
        this.searchText = "";
        this.estado = "Todos";
        this.laboratorio = "Todos";
        this.sucursal = "Todos";
        this.prioridad = "Todas";
        this.desde = "";
        this.hasta = "";
    }

    public OrdenesLabFilters(String searchText, String estado, String laboratorio,
                             String sucursal, String prioridad, String desde, String hasta) {
        this.searchText = searchText != null ? searchText : "";
        this.estado = estado != null ? estado : "Todos";
        this.laboratorio = laboratorio != null ? laboratorio : "Todos";
        this.sucursal = sucursal != null ? sucursal : "Todos";
        this.prioridad = prioridad != null ? prioridad : "Todas";
        this.desde = desde != null ? desde : "";
        this.hasta = hasta != null ? hasta : "";
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

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
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
}
