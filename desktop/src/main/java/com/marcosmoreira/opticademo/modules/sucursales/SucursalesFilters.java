package com.marcosmoreira.opticademo.modules.sucursales;

/**
 * Filter criteria for the Sucursales module.
 */
public class SucursalesFilters {

    private String searchText;
    private String estado;
    private String servicio;
    private String ciudad;
    private boolean soloConAlertas;

    public SucursalesFilters() {
        this.searchText = "";
        this.estado = "Todos";
        this.servicio = "Todos";
        this.ciudad = "Todas";
        this.soloConAlertas = false;
    }

    public SucursalesFilters(String searchText, String estado, String servicio, String ciudad, boolean soloConAlertas) {
        this.searchText = searchText != null ? searchText : "";
        this.estado = estado != null ? estado : "Todos";
        this.servicio = servicio != null ? servicio : "Todos";
        this.ciudad = ciudad != null ? ciudad : "Todas";
        this.soloConAlertas = soloConAlertas;
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

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public boolean isSoloConAlertas() {
        return soloConAlertas;
    }

    public void setSoloConAlertas(boolean soloConAlertas) {
        this.soloConAlertas = soloConAlertas;
    }
}
