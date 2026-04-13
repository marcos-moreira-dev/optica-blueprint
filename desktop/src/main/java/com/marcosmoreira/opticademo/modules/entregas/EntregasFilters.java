package com.marcosmoreira.opticademo.modules.entregas;

/**
 * Filter criteria for the Entregas module.
 */
public class EntregasFilters {

    private String searchText;
    private String estado;
    private String sucursal;
    private String notificacion;
    private String desde;
    private String hasta;
    private boolean soloConSaldo;

    public EntregasFilters() {
        this.searchText = "";
        this.estado = "Todos";
        this.sucursal = "Todas";
        this.notificacion = "Todas";
        this.desde = "";
        this.hasta = "";
        this.soloConSaldo = false;
    }

    public EntregasFilters(String searchText, String estado, String sucursal,
                           String notificacion, String desde, String hasta, boolean soloConSaldo) {
        this.searchText = searchText != null ? searchText : "";
        this.estado = estado != null ? estado : "Todos";
        this.sucursal = sucursal != null ? sucursal : "Todas";
        this.notificacion = notificacion != null ? notificacion : "Todas";
        this.desde = desde != null ? desde : "";
        this.hasta = hasta != null ? hasta : "";
        this.soloConSaldo = soloConSaldo;
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

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
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

    public boolean isSoloConSaldo() {
        return soloConSaldo;
    }

    public void setSoloConSaldo(boolean soloConSaldo) {
        this.soloConSaldo = soloConSaldo;
    }
}
