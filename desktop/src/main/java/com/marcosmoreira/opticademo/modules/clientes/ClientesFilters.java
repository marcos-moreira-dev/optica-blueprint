package com.marcosmoreira.opticademo.modules.clientes;

/**
 * Filter criteria for the Clientes module.
 */
public class ClientesFilters {

    private String searchText;
    private String estado;
    private String ultimaVisita;
    private String receta;

    public ClientesFilters() {
        this.searchText = "";
        this.estado = "Todos";
        this.ultimaVisita = "Todas";
        this.receta = "Todas";
    }

    public ClientesFilters(String searchText, String estado, String ultimaVisita, String receta) {
        this.searchText = searchText != null ? searchText : "";
        this.estado = estado != null ? estado : "Todos";
        this.ultimaVisita = ultimaVisita != null ? ultimaVisita : "Todas";
        this.receta = receta != null ? receta : "Todas";
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

    public String getUltimaVisita() {
        return ultimaVisita;
    }

    public void setUltimaVisita(String ultimaVisita) {
        this.ultimaVisita = ultimaVisita;
    }

    public String getReceta() {
        return receta;
    }

    public void setReceta(String receta) {
        this.receta = receta;
    }
}
