package com.marcosmoreira.opticademo.modules.recetas;

/**
 * Filter criteria for the Recetas module.
 */
public class RecetasFilters {

    private String clienteSearch;
    private String estado;
    private String profesional;
    private String desde;
    private String hasta;

    public RecetasFilters() {
        this.clienteSearch = "";
        this.estado = "";
        this.profesional = "";
        this.desde = "";
        this.hasta = "";
    }

    public RecetasFilters(String clienteSearch, String estado, String profesional, String desde, String hasta) {
        this.clienteSearch = clienteSearch;
        this.estado = estado;
        this.profesional = profesional;
        this.desde = desde;
        this.hasta = hasta;
    }

    public String getClienteSearch() {
        return clienteSearch;
    }

    public void setClienteSearch(String clienteSearch) {
        this.clienteSearch = clienteSearch;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getProfesional() {
        return profesional;
    }

    public void setProfesional(String profesional) {
        this.profesional = profesional;
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
