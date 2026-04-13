package com.marcosmoreira.opticademo.modules.ventaoptica;

/**
 * Filter criteria for the Venta Optica module wizard stages.
 */
public class VentaOpticaFilters {

    private String clienteSearch;
    private String monturaMarca;
    private String monturaMaterial;
    private String lenteTipo;
    private String lenteIndice;

    public VentaOpticaFilters() {
        this.clienteSearch = "";
        this.monturaMarca = "Todas";
        this.monturaMaterial = "Todos";
        this.lenteTipo = "Todos";
        this.lenteIndice = "Todos";
    }

    public VentaOpticaFilters(String clienteSearch, String monturaMarca,
            String monturaMaterial, String lenteTipo, String lenteIndice) {
        this.clienteSearch = clienteSearch != null ? clienteSearch : "";
        this.monturaMarca = monturaMarca != null ? monturaMarca : "Todas";
        this.monturaMaterial = monturaMaterial != null ? monturaMaterial : "Todos";
        this.lenteTipo = lenteTipo != null ? lenteTipo : "Todos";
        this.lenteIndice = lenteIndice != null ? lenteIndice : "Todos";
    }

    public String getClienteSearch() {
        return clienteSearch;
    }

    public void setClienteSearch(String clienteSearch) {
        this.clienteSearch = clienteSearch;
    }

    public String getMonturaMarca() {
        return monturaMarca;
    }

    public void setMonturaMarca(String monturaMarca) {
        this.monturaMarca = monturaMarca;
    }

    public String getMonturaMaterial() {
        return monturaMaterial;
    }

    public void setMonturaMaterial(String monturaMaterial) {
        this.monturaMaterial = monturaMaterial;
    }

    public String getLenteTipo() {
        return lenteTipo;
    }

    public void setLenteTipo(String lenteTipo) {
        this.lenteTipo = lenteTipo;
    }

    public String getLenteIndice() {
        return lenteIndice;
    }

    public void setLenteIndice(String lenteIndice) {
        this.lenteIndice = lenteIndice;
    }
}
