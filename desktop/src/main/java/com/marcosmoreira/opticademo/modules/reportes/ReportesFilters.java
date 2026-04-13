package com.marcosmoreira.opticademo.modules.reportes;

/**
 * Filter criteria for the Reportes module.
 */
public class ReportesFilters {

    private String periodo;
    private String sucursal;
    private String categoria;
    private String desde;
    private String hasta;
    private boolean soloDatosCriticos;

    public ReportesFilters() {
        this.periodo = "Abril 2026";
        this.sucursal = "Todas";
        this.categoria = "Todas";
        this.desde = "";
        this.hasta = "";
        this.soloDatosCriticos = false;
    }

    public ReportesFilters(String periodo, String sucursal, String categoria,
                            String desde, String hasta, boolean soloDatosCriticos) {
        this.periodo = periodo != null ? periodo : "Abril 2026";
        this.sucursal = sucursal != null ? sucursal : "Todas";
        this.categoria = categoria != null ? categoria : "Todas";
        this.desde = desde != null ? desde : "";
        this.hasta = hasta != null ? hasta : "";
        this.soloDatosCriticos = soloDatosCriticos;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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

    public boolean isSoloDatosCriticos() {
        return soloDatosCriticos;
    }

    public void setSoloDatosCriticos(boolean soloDatosCriticos) {
        this.soloDatosCriticos = soloDatosCriticos;
    }
}
