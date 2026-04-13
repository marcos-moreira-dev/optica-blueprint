package com.marcosmoreira.opticademo.modules.seguimiento;

/**
 * Filter criteria for the Seguimiento module.
 */
public class SeguimientoFilters {

    private String searchText;
    private String tipo;
    private String estado;
    private String prioridad;
    private String sucursal;
    private String canal;
    private String desde;
    private String hasta;
    private boolean soloCasosAbiertos;

    public SeguimientoFilters() {
        this.searchText = "";
        this.tipo = "Todos";
        this.estado = "Todos";
        this.prioridad = "Todas";
        this.sucursal = "Todas";
        this.canal = "Todos";
        this.desde = "";
        this.hasta = "";
        this.soloCasosAbiertos = false;
    }

    public SeguimientoFilters(String searchText, String tipo, String estado,
                               String prioridad, String sucursal, String canal,
                               String desde, String hasta, boolean soloCasosAbiertos) {
        this.searchText = searchText != null ? searchText : "";
        this.tipo = tipo != null ? tipo : "Todos";
        this.estado = estado != null ? estado : "Todos";
        this.prioridad = prioridad != null ? prioridad : "Todas";
        this.sucursal = sucursal != null ? sucursal : "Todas";
        this.canal = canal != null ? canal : "Todos";
        this.desde = desde != null ? desde : "";
        this.hasta = hasta != null ? hasta : "";
        this.soloCasosAbiertos = soloCasosAbiertos;
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

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
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

    public boolean isSoloCasosAbiertos() {
        return soloCasosAbiertos;
    }

    public void setSoloCasosAbiertos(boolean soloCasosAbiertos) {
        this.soloCasosAbiertos = soloCasosAbiertos;
    }
}
