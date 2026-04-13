package com.marcosmoreira.opticademo.modules.notificaciones;

/**
 * Filter criteria for the Notificaciones module.
 */
public class NotificacionesFilters {

    private String searchText;
    private String tipo;
    private String canal;
    private String estado;
    private String prioridad;
    private String sucursal;
    private String desde;
    private String hasta;
    private boolean soloPendientesYCriticas;

    public NotificacionesFilters() {
        this.searchText = "";
        this.tipo = "Todos";
        this.canal = "Todos";
        this.estado = "Todos";
        this.prioridad = "Todas";
        this.sucursal = "Todas";
        this.desde = "";
        this.hasta = "";
        this.soloPendientesYCriticas = false;
    }

    public NotificacionesFilters(String searchText, String tipo, String canal, String estado,
                                  String prioridad, String sucursal, String desde, String hasta,
                                  boolean soloPendientesYCriticas) {
        this.searchText = searchText != null ? searchText : "";
        this.tipo = tipo != null ? tipo : "Todos";
        this.canal = canal != null ? canal : "Todos";
        this.estado = estado != null ? estado : "Todos";
        this.prioridad = prioridad != null ? prioridad : "Todas";
        this.sucursal = sucursal != null ? sucursal : "Todas";
        this.desde = desde != null ? desde : "";
        this.hasta = hasta != null ? hasta : "";
        this.soloPendientesYCriticas = soloPendientesYCriticas;
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

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
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

    public boolean isSoloPendientesYCriticas() {
        return soloPendientesYCriticas;
    }

    public void setSoloPendientesYCriticas(boolean soloPendientesYCriticas) {
        this.soloPendientesYCriticas = soloPendientesYCriticas;
    }
}
