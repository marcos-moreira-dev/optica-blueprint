package com.marcosmoreira.opticademo.modules.sucursales;

import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.shared.domain.sucursal.Sucursal;
import com.marcosmoreira.opticademo.shared.domain.usuario.Usuario;
import com.marcosmoreira.opticademo.shared.domain.producto.Producto;
import com.marcosmoreira.opticademo.shared.query.FilterSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Facade that queries the DemoStore and returns data for the Sucursales module.
 * No business logic -- just view-facing data assembly.
 */
public class SucursalesFacade {

    private final DemoStore store;

    public SucursalesFacade(DemoStore store) {
        this.store = store;
    }

    // =========================================================================
    // Directorio
    // =========================================================================

    /**
     * Returns a filtered list of directorio row models.
     */
    public List<SucursalesRowModel.DirectorioRow> getDirectorio(SucursalesFilters filters) {
        return store.sucursales.stream()
                .filter(s -> matchesFilters(s, filters))
                .map(this::toDirectorioRow)
                .collect(Collectors.toList());
    }

    private SucursalesRowModel.DirectorioRow toDirectorioRow(Sucursal s) {
        String servicios = buildServiciosString(s);
        String ciudad = extractCiudad(s);
        return new SucursalesRowModel.DirectorioRow(
                s.getNombre(),
                ciudad,
                s.getResponsable(),
                s.getHorarioOperativo(),
                s.getEstado() != null ? s.getEstado().name() : "ACTIVO",
                servicios
        );
    }

    private String buildServiciosString(Sucursal s) {
        List<String> svcs = new ArrayList<>();
        if (s.isCajaHabilitada()) svcs.add("Caja");
        if (s.isInventarioPropio()) svcs.add("Inventario");
        if (s.isEntregaHabilitada()) svcs.add("Entregas");
        if (s.isAgendaHabilitada()) svcs.add("Agenda");
        return String.join(", ", svcs);
    }

    private String extractCiudad(Sucursal s) {
        String dir = s.getDireccion() != null ? s.getDireccion() : "";
        if (dir.contains("Guayaquil")) return "Guayaquil";
        if (dir.contains("Quito")) return "Quito";
        if (dir.contains("Cuenca")) return "Cuenca";
        return "Sin asignar";
    }

    private boolean matchesFilters(Sucursal s, SucursalesFilters filters) {
        // Search text: matches nombre, responsable, direccion
        if (!FilterSupport.matchesText(filters.getSearchText(),
                s.getNombre(),
                s.getResponsable(),
                s.getDireccion())) {
            return false;
        }

        // Estado filter
        String estadoStr = s.getEstado() != null ? s.getEstado().name() : "ACTIVO";
        if (!FilterSupport.matchesExact(estadoStr, filters.getEstado())) {
            return false;
        }

        // Servicio filter
        if (!FilterSupport.matchesExact("Todos", filters.getServicio())) {
            String servicios = buildServiciosString(s);
            if (!servicios.contains(filters.getServicio())) {
                return false;
            }
        }

        // Ciudad filter
        String ciudad = extractCiudad(s);
        if (!FilterSupport.matchesExact(ciudad, filters.getCiudad())) {
            return false;
        }

        // Solo con alertas: branches not fully operational
        if (filters.isSoloConAlertas()) {
            String estadoStr2 = s.getEstado() != null ? s.getEstado().name() : "ACTIVO";
            if (!"BAJO_OBSERVACION".equalsIgnoreCase(estadoStr2) &&
                !"INACTIVO".equalsIgnoreCase(estadoStr2)) {
                return false;
            }
        }

        return true;
    }

    // =========================================================================
    // Personal
    // =========================================================================

    /**
     * Returns the list of personal (usuarios) assigned to a given sucursal.
     */
    public List<SucursalesRowModel.PersonalRow> getPersonal(String sucursalId) {
        return store.usuarios.stream()
                .filter(u -> sucursalId.equals(u.getSucursal()))
                .map(this::toPersonalRow)
                .collect(Collectors.toList());
    }

    private SucursalesRowModel.PersonalRow toPersonalRow(Usuario u) {
        return new SucursalesRowModel.PersonalRow(
                u.getNombreVisible(),
                u.getRol() != null ? u.getRol().name() : "SIN_ROL",
                u.getEstado() != null ? u.getEstado().name() : "ACTIVO",
                u.getCorreo()
        );
    }

    // =========================================================================
    // Inventario local
    // =========================================================================

    /**
     * Returns the list of productos for a given sucursal.
     */
    public List<SucursalesRowModel.InventarioRow> getInventarioLocal(String sucursalId) {
        return store.productos.stream()
                .filter(p -> sucursalId.equals(p.getSucursal()))
                .map(this::toInventarioRow)
                .collect(Collectors.toList());
    }

    private SucursalesRowModel.InventarioRow toInventarioRow(Producto p) {
        return new SucursalesRowModel.InventarioRow(
                p.getReferencia(),
                p.getNombre(),
                p.getCategoria(),
                String.valueOf(p.getStock()),
                p.getEstado() != null ? p.getEstado().name() : "ACTIVO",
                p.getStock() <= p.getStockMinimo() ? "Stock bajo minimo" : ""
        );
    }

    // =========================================================================
    // Agenda local
    // =========================================================================

    /**
     * Returns agenda KPI rows for a given sucursal.
     */
    public List<SucursalesRowModel.AgendaRow> getAgendaLocal(String sucursalId) {
        List<SucursalesRowModel.AgendaRow> rows = new ArrayList<>();
        long citasHoy = 12;
        long citasPendientes = 5;
        long citasConfirmadas = 8;
        long cancelacionesMes = 2;

        rows.add(new SucursalesRowModel.AgendaRow(
                "Citas hoy", String.valueOf(citasHoy), "Normal", ""
        ));
        rows.add(new SucursalesRowModel.AgendaRow(
                "Citas pendientes", String.valueOf(citasPendientes), "Atencion requerida", "Revisar disponibilidad"
        ));
        rows.add(new SucursalesRowModel.AgendaRow(
                "Citas confirmadas", String.valueOf(citasConfirmadas), "Normal", ""
        ));
        rows.add(new SucursalesRowModel.AgendaRow(
                "Cancelaciones mes", String.valueOf(cancelacionesMes), "Normal", "Dentro del rango esperado"
        ));

        return rows;
    }

    // =========================================================================
    // Caja local
    // =========================================================================

    /**
     * Returns caja KPI rows for a given sucursal.
     */
    public List<SucursalesRowModel.CajaRow> getCajaLocal(String sucursalId) {
        List<SucursalesRowModel.CajaRow> rows = new ArrayList<>();

        rows.add(new SucursalesRowModel.CajaRow(
                "Caja habilitada", "Si", "Normal", ""
        ));
        rows.add(new SucursalesRowModel.CajaRow(
                "Ventas del dia", "$1,245.00", "Normal", ""
        ));
        rows.add(new SucursalesRowModel.CajaRow(
                "Cobros pendientes", "$387.25", "Atencion requerida", "3 cobros por confirmar"
        ));
        rows.add(new SucursalesRowModel.CajaRow(
                "Ticket promedio", "$156.00", "Normal", ""
        ));

        return rows;
    }

    // =========================================================================
    // Comparativo
    // =========================================================================

    /**
     * Returns comparativo rows for all sucursales.
     */
    public List<SucursalesRowModel.ComparativoRow> getComparativo() {
        return store.sucursales.stream()
                .map(this::toComparativoRow)
                .collect(Collectors.toList());
    }

    private SucursalesRowModel.ComparativoRow toComparativoRow(Sucursal s) {
        // Seed data varied per branch
        String nombre = s.getNombre();
        String ventas;
        String ticketPromedio;
        String stockCritico;
        String recallsPendientes;
        String retrasos;
        String estadoGeneral;

        if ("Matriz Centro".equals(nombre)) {
            ventas = "$24,500";
            ticketPromedio = "$185";
            stockCritico = "2";
            recallsPendientes = "0";
            retrasos = "1";
            estadoGeneral = "Optimo";
        } else if ("Norte Mall".equals(nombre)) {
            ventas = "$18,750";
            ticketPromedio = "$165";
            stockCritico = "1";
            recallsPendientes = "1";
            retrasos = "0";
            estadoGeneral = "Bueno";
        } else if ("Sur Express".equals(nombre)) {
            ventas = "$12,300";
            ticketPromedio = "$142";
            stockCritico = "4";
            recallsPendientes = "2";
            retrasos = "3";
            estadoGeneral = "Bajo observacion";
        } else {
            ventas = "$0";
            ticketPromedio = "$0";
            stockCritico = "0";
            recallsPendientes = "0";
            retrasos = "0";
            estadoGeneral = "Sin datos";
        }

        return new SucursalesRowModel.ComparativoRow(
                nombre, ventas, ticketPromedio, stockCritico, recallsPendientes, retrasos, estadoGeneral
        );
    }

    // =========================================================================
    // Summary
    // =========================================================================

    /**
     * Builds a summary model for the selected sucursal.
     */
    public SucursalesSummaryModel buildSummary(Sucursal s) {
        return new SucursalesSummaryModel(
                s.getNombre(),
                extractCiudad(s),
                s.getDireccion() != null ? s.getDireccion() : "Sin direccion",
                s.getResponsable() != null ? s.getResponsable() : "Sin asignar",
                s.getTelefono() != null ? s.getTelefono() : "Sin telefono",
                s.getHorarioOperativo() != null ? s.getHorarioOperativo() : "Sin definir",
                s.isCajaHabilitada() ? "Habilitada" : "No habilitada",
                s.isInventarioPropio() ? "Si - gestion propia" : "No - centralizado",
                s.isEntregaHabilitada() ? "Habilitada" : "No habilitada",
                s.isAgendaHabilitada() ? "Habilitada" : "No habilitada",
                s.getEstado() != null ? s.getEstado().name() : "ACTIVO",
                buildObservacionOperativa(s)
        );
    }

    private String buildObservacionOperativa(Sucursal s) {
        List<String> obs = new ArrayList<>();
        if (!s.isCajaHabilitada()) obs.add("Caja no habilitada");
        if (!s.isInventarioPropio()) obs.add("Inventario centralizado");
        if (!s.isEntregaHabilitada()) obs.add("Entregas no disponibles");
        if (!s.isAgendaHabilitada()) obs.add("Agenda no habilitada");
        if (s.getEstado() != null && "BAJO_OBSERVACION".equals(s.getEstado().name())) {
            obs.add("Sucursal bajo observacion operativa");
        }
        if (obs.isEmpty()) {
            return "Operacion normal, todos los servicios habilitados";
        }
        return String.join(". ", obs);
    }

    // =========================================================================
    // Filter helpers
    // =========================================================================

    /**
     * Returns all distinct estado values for the filter combo.
     */
    public List<String> getEstados() {
        return store.sucursales.stream()
                .map(s -> s.getEstado() != null ? s.getEstado().name() : "ACTIVO")
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Returns all distinct servicios for the filter combo.
     */
    public List<String> getServicios() {
        return List.of("Caja", "Inventario", "Entregas", "Agenda");
    }

    /**
     * Returns all distinct ciudades for the filter combo.
     */
    public List<String> getCiudades() {
        return store.sucursales.stream()
                .map(this::extractCiudad)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Returns a sucursal by name.
     */
    public Sucursal findSucursalByName(String nombre) {
        return store.sucursales.stream()
                .filter(s -> s.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);
    }
}
