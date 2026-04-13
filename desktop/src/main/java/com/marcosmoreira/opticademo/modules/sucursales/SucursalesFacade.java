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
 * Facade para el modulo de Sucursales (gestion de sedes).
 * <p>
 * Este facade actua como capa de abstraccion entre el {@link DemoStore} (colecciones
 * de {@link Sucursal}, {@link Usuario} y {@link Producto}) y las sub-vistas del modulo
 * de Sucursales. Proporciona datos del directorio de sucursales, personal asignado,
 * inventario local, agenda local, caja local, comparativo entre sucursales y resumen.
 * </p>
 * <p>
 * Sub-vistas que alimenta:
 * <ul>
 *   <li><b>Directorio:</b> lista de sucursales con ciudad, responsable, horario y servicios.</li>
 *   <li><b>Personal:</b> usuarios asignados a cada sucursal.</li>
 *   <li><b>Inventario Local:</b> productos almacenados en cada sucursal.</li>
 *   <li><b>Agenda Local:</b> KPIs de citas de la sucursal.</li>
 *   <li><b>Caja Local:</b> KPIs financieros de la sucursal.</li>
 *   <li><b>Comparativo:</b> analisis comparativo entre todas las sucursales.</li>
 *   <li><b>Resumen:</b> panel con datos consolidados de la sucursal seleccionada.</li>
 * </ul>
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see DemoStore
 * @see Sucursal
 * @see Usuario
 * @see Producto
 * @see FilterSupport
 */
public class SucursalesFacade {

    private final DemoStore store;

    /**
     * Construye el facade con referencia al almacén de datos de demostración.
     *
     * @param store instancia del {@link DemoStore}
     */
    public SucursalesFacade(DemoStore store) {
        this.store = store;
    }

    // =========================================================================
    // Directorio
    // =========================================================================

    /**
     * Retorna el directorio de sucursales filtrado por estado, servicio y ciudad.
     *
     * @param filters criterios de filtrado
     * @return lista de {@link SucursalesRowModel.DirectorioRow}
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
     * Retorna la lista de personal (usuarios) asignados a una sucursal especifica.
     *
     * @param sucursalId identificador de la sucursal
     * @return lista de {@link SucursalesRowModel.PersonalRow} con usuarios de la sucursal
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
     * Retorna la lista de productos almacenados en una sucursal especifica.
     *
     * @param sucursalId identificador de la sucursal
     * @return lista de {@link SucursalesRowModel.InventarioRow} con productos locales
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
     * Retorna los KPIs de agenda para una sucursal especifica, con citas de hoy,
     * pendientes, confirmadas y cancelaciones del mes.
     *
     * @param sucursalId identificador de la sucursal
     * @return lista de {@link SucursalesRowModel.AgendaRow} con KPIs de agenda
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
     * Retorna los KPIs financieros para una sucursal especifica, con ventas del dia,
     * cobros pendientes y ticket promedio.
     *
     * @param sucursalId identificador de la sucursal
     * @return lista de {@link SucursalesRowModel.CajaRow} con KPIs de caja
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
     * Retorna el analisis comparativo entre todas las sucursales, con ventas,
     * ticket promedio, stock critico, recalls pendientes y retrasos.
     *
     * @return lista de {@link SucursalesRowModel.ComparativoRow} con datos comparativos
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
     * Construye un modelo de resumen para la sucursal seleccionada, con datos
     * de direccion, responsable, servicios habilitados y observaciones operativas.
     *
     * @param s entidad {@link Sucursal} seleccionada
     * @return {@link SucursalesSummaryModel} con datos consolidados de la sucursal
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
     * Retorna los estados distinct de sucursales desde el {@link DemoStore} para el combo de filtro.
     *
     * @return lista ordenada de estados de sucursal
     */
    public List<String> getEstados() {
        return store.sucursales.stream()
                .map(s -> s.getEstado() != null ? s.getEstado().name() : "ACTIVO")
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Retorna los servicios disponibles para el combo de filtro.
     *
     * @return lista de servicios (Caja, Inventario, Entregas, Agenda)
     */
    public List<String> getServicios() {
        return List.of("Caja", "Inventario", "Entregas", "Agenda");
    }

    /**
     * Retorna las ciudades distinct de sucursales para el combo de filtro.
     *
     * @return lista ordenada de ciudades
     */
    public List<String> getCiudades() {
        return store.sucursales.stream()
                .map(this::extractCiudad)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Busca una sucursal por nombre exacto.
     *
     * @param nombre nombre de la sucursal
     * @return {@link Sucursal} encontrada o {@code null}
     */
    public Sucursal findSucursalByName(String nombre) {
        return store.sucursales.stream()
                .filter(s -> s.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);
    }
}
