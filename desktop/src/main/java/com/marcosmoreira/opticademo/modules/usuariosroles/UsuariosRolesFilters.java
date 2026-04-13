package com.marcosmoreira.opticademo.modules.usuariosroles;

/**
 * Filter criteria for the Usuarios y Roles module.
 */
public class UsuariosRolesFilters {

    private String searchText;
    private String rol;
    private String estado;
    private String sucursal;
    private String actividad;
    private String desde;
    private String hasta;
    private boolean soloEventosSensibles;

    public UsuariosRolesFilters() {
        this.searchText = "";
        this.rol = "Todos";
        this.estado = "Todos";
        this.sucursal = "Todas";
        this.actividad = "Todas";
        this.desde = "";
        this.hasta = "";
        this.soloEventosSensibles = false;
    }

    public UsuariosRolesFilters(String searchText, String rol, String estado, String sucursal,
                                 String actividad, String desde, String hasta,
                                 boolean soloEventosSensibles) {
        this.searchText = searchText != null ? searchText : "";
        this.rol = rol != null ? rol : "Todos";
        this.estado = estado != null ? estado : "Todos";
        this.sucursal = sucursal != null ? sucursal : "Todas";
        this.actividad = actividad != null ? actividad : "Todas";
        this.desde = desde != null ? desde : "";
        this.hasta = hasta != null ? hasta : "";
        this.soloEventosSensibles = soloEventosSensibles;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
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

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
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

    public boolean isSoloEventosSensibles() {
        return soloEventosSensibles;
    }

    public void setSoloEventosSensibles(boolean soloEventosSensibles) {
        this.soloEventosSensibles = soloEventosSensibles;
    }
}
