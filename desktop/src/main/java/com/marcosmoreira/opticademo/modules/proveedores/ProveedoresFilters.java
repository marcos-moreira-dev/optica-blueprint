package com.marcosmoreira.opticademo.modules.proveedores;

/**
 * Filter criteria for the Proveedores module.
 */
public class ProveedoresFilters {

    private String searchText;
    private String tipo;
    private String estado;
    private String categoria;
    private String sucursal;
    private boolean soloConIncidencias;

    public ProveedoresFilters() {
        this.searchText = "";
        this.tipo = "Todos";
        this.estado = "Todos";
        this.categoria = "Todas";
        this.sucursal = "Todas";
        this.soloConIncidencias = false;
    }

    public ProveedoresFilters(String searchText, String tipo, String estado,
                              String categoria, String sucursal, boolean soloConIncidencias) {
        this.searchText = searchText != null ? searchText : "";
        this.tipo = tipo != null ? tipo : "Todos";
        this.estado = estado != null ? estado : "Todos";
        this.categoria = categoria != null ? categoria : "Todas";
        this.sucursal = sucursal != null ? sucursal : "Todas";
        this.soloConIncidencias = soloConIncidencias;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public boolean isSoloConIncidencias() {
        return soloConIncidencias;
    }

    public void setSoloConIncidencias(boolean soloConIncidencias) {
        this.soloConIncidencias = soloConIncidencias;
    }
}
