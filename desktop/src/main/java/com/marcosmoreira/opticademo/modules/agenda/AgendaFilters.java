package com.marcosmoreira.opticademo.modules.agenda;

/**
 * Filter criteria for the Agenda module.
 */
public class AgendaFilters {

    private String fecha;
    private String profesional;
    private String estado;
    private String tipoAtencion;
    private String searchText;

    public AgendaFilters() {
        this.fecha = "";
        this.profesional = "Todos";
        this.estado = "Todos";
        this.tipoAtencion = "Todos";
        this.searchText = "";
    }

    public AgendaFilters(String fecha, String profesional, String estado,
                          String tipoAtencion, String searchText) {
        this.fecha = fecha != null ? fecha : "";
        this.profesional = profesional != null ? profesional : "Todos";
        this.estado = estado != null ? estado : "Todos";
        this.tipoAtencion = tipoAtencion != null ? tipoAtencion : "Todos";
        this.searchText = searchText != null ? searchText : "";
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getProfesional() {
        return profesional;
    }

    public void setProfesional(String profesional) {
        this.profesional = profesional;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoAtencion() {
        return tipoAtencion;
    }

    public void setTipoAtencion(String tipoAtencion) {
        this.tipoAtencion = tipoAtencion;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
