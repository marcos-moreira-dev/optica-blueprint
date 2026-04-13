package com.marcosmoreira.opticademo.modules.ordeneslaboratorio;

import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.shared.domain.venta.VentaOptica;
import com.marcosmoreira.opticademo.shared.query.FilterSupport;
import com.marcosmoreira.opticademo.shared.query.PageRequest;
import com.marcosmoreira.opticademo.shared.query.PageResult;
import com.marcosmoreira.opticademo.shared.query.PaginationHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Facade that queries the DemoStore and returns data for the Ordenes de Laboratorio module.
 * No business logic -- just view-facing data assembly.
 */
public class OrdenesLabFacade {

    private final DemoStore store;

    // Seed data for lab orders
    private final List<OrdenesLabRowModel.ColaRow> seedCola;
    private final List<OrdenesLabRowModel.EtapaRow> seedEtapas;
    private final List<OrdenesLabRowModel.IncidenciaRow> seedIncidencias;
    private final List<OrdenesLabRowModel.HistoricoRow> seedHistorico;

    public OrdenesLabFacade(DemoStore store) {
        this.store = store;
        this.seedCola = initSeedCola();
        this.seedEtapas = initSeedEtapas();
        this.seedIncidencias = initSeedIncidencias();
        this.seedHistorico = initSeedHistorico();
    }

    /**
     * Returns a paginated, filtered list of lab order row models for the Cola de ordenes.
     */
    public PageResult<OrdenesLabRowModel.ColaRow> getColaOrdenes(OrdenesLabFilters filters, PageRequest pageRequest) {
        List<OrdenesLabRowModel.ColaRow> filtered = seedCola.stream()
                .filter(r -> matchesFilters(r, filters))
                .collect(Collectors.toList());

        return PaginationHelper.page(filtered, pageRequest);
    }

    /**
     * Returns the list of etapa rows for a given order.
     */
    public List<OrdenesLabRowModel.EtapaRow> getEtapas(String ordenId) {
        if (ordenId == null || ordenId.isBlank()) {
            return new ArrayList<>(seedEtapas);
        }
        return seedEtapas.stream()
                .filter(e -> e.responsable() != null && e.responsable().contains(ordenId))
                .collect(Collectors.toList());
    }

    /**
     * Returns the envio row for a given order.
     */
    public OrdenesLabRowModel.EnvioRow getEnvio(String ordenId) {
        return new OrdenesLabRowModel.EnvioRow(
                "LabVision",
                "10/04/2026",
                "GV-2026-0456",
                "DHL Express",
                "18/04/2026"
        );
    }

    /**
     * Returns the recepcion row for a given order.
     */
    public OrdenesLabRowModel.RecepcionRow getRecepcion(String ordenId) {
        return new OrdenesLabRowModel.RecepcionRow(
                "-",
                "-",
                "-",
                "Pendiente"
        );
    }

    /**
     * Returns the list of incidencia rows for a given order.
     */
    public List<OrdenesLabRowModel.IncidenciaRow> getIncidencias(String ordenId) {
        if (ordenId != null && !"LAB-207".equals(ordenId)) {
            return List.of();
        }
        return new ArrayList<>(seedIncidencias);
    }

    /**
     * Returns all incidencias for the list view.
     */
    public List<OrdenesLabRowModel.IncidenciaRow> getAllIncidencias() {
        return new ArrayList<>(seedIncidencias);
    }

    /**
     * Returns the filtered list of historico rows.
     */
    public List<OrdenesLabRowModel.HistoricoRow> getHistorico(OrdenesLabFilters filters) {
        return seedHistorico.stream()
                .filter(r -> matchesHistoricoFilters(r, filters))
                .collect(Collectors.toList());
    }

    /**
     * Builds a summary model for the selected order.
     */
    public OrdenesLabSummaryModel buildSummary(VentaOptica ventaOptica) {
        return OrdenesLabSummaryModel.from(ventaOptica);
    }

    /**
     * Builds a summary model from a ColaRow.
     */
    public OrdenesLabSummaryModel buildSummaryFromRow(OrdenesLabRowModel.ColaRow row) {
        return new OrdenesLabSummaryModel(
                row.referencia(),
                row.tipoTrabajo(),
                row.prioridad(),
                row.cliente(),
                "-",
                row.sucursal(),
                row.fechaPromesa(),
                "Standard",
                row.tipoTrabajo(),
                "Anti-reflejo",
                "62mm",
                row.estado(),
                row.laboratorio(),
                "-",
                row.fechaPromesa()
        );
    }

    /**
     * Returns all distinct estado values for the filter combo.
     */
    public List<String> getEstados() {
        return seedCola.stream()
                .map(OrdenesLabRowModel.ColaRow::estado)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Returns all distinct laboratorio values for the filter combo.
     */
    public List<String> getLaboratorios() {
        return seedCola.stream()
                .map(OrdenesLabRowModel.ColaRow::laboratorio)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Returns all distinct prioridad values for the filter combo.
     */
    public List<String> getPrioridades() {
        return seedCola.stream()
                .map(OrdenesLabRowModel.ColaRow::prioridad)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Returns all distinct sucursal values.
     */
    public List<String> getSucursales() {
        return store.sucursales.stream()
                .map(s -> s.getNombre())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    // ---- Seed data initialization ----

    private List<OrdenesLabRowModel.ColaRow> initSeedCola() {
        List<OrdenesLabRowModel.ColaRow> rows = new ArrayList<>();
        rows.add(new OrdenesLabRowModel.ColaRow(
                "LAB-203", "Sofia Ramirez", "Lentes monofocales",
                "08/04/2026", "16/04/2026", "En produccion",
                "LabVision", "Alta", "Matriz Centro"
        ));
        rows.add(new OrdenesLabRowModel.ColaRow(
                "LAB-204", "Luis Andrade", "Progresivos",
                "09/04/2026", "18/04/2026", "Validada",
                "OptiLab Pro", "Normal", "Matriz Centro"
        ));
        rows.add(new OrdenesLabRowModel.ColaRow(
                "LAB-205", "Ana Vera", "Cambio de lente",
                "10/04/2026", "15/04/2026", "Lista",
                "LabVision", "Normal", "Sucursal Norte"
        ));
        rows.add(new OrdenesLabRowModel.ColaRow(
                "LAB-206", "Carlos Mendoza", "Ajuste",
                "11/04/2026", "14/04/2026", "Retrasada",
                "OptiLab Pro", "Alta", "Matriz Centro"
        ));
        rows.add(new OrdenesLabRowModel.ColaRow(
                "LAB-207", "Diana Velez", "Lentes bifocales",
                "12/04/2026", "20/04/2026", "Con incidencia",
                "LabVision", "Normal", "Sucursal Norte"
        ));
        return rows;
    }

    private List<OrdenesLabRowModel.EtapaRow> initSeedEtapas() {
        List<OrdenesLabRowModel.EtapaRow> rows = new ArrayList<>();
        rows.add(new OrdenesLabRowModel.EtapaRow("08/04/2026", "Recibida", "Recepcion Matriz", "Orden ingresada al sistema"));
        rows.add(new OrdenesLabRowModel.EtapaRow("09/04/2026", "Validada", "Tecnico Lopez", "Receta verificada, materiales disponibles"));
        rows.add(new OrdenesLabRowModel.EtapaRow("10/04/2026", "En produccion", "LabVision", "Corte de lente en proceso"));
        rows.add(new OrdenesLabRowModel.EtapaRow("", "En control", "-", "Pendiente"));
        rows.add(new OrdenesLabRowModel.EtapaRow("", "Lista", "-", "Pendiente"));
        rows.add(new OrdenesLabRowModel.EtapaRow("", "Enviada", "-", "Pendiente"));
        rows.add(new OrdenesLabRowModel.EtapaRow("", "Recibida en sucursal", "-", "Pendiente"));
        rows.add(new OrdenesLabRowModel.EtapaRow("", "Entregada", "-", "Pendiente"));
        return rows;
    }

    private List<OrdenesLabRowModel.IncidenciaRow> initSeedIncidencias() {
        List<OrdenesLabRowModel.IncidenciaRow> rows = new ArrayList<>();
        rows.add(new OrdenesLabRowModel.IncidenciaRow(
                "12/04/2026", "Lente rayado", "Abierta", "LabVision", "En retrabajo"
        ));
        return rows;
    }

    private List<OrdenesLabRowModel.HistoricoRow> initSeedHistorico() {
        List<OrdenesLabRowModel.HistoricoRow> rows = new ArrayList<>();
        rows.add(new OrdenesLabRowModel.HistoricoRow(
                "LAB-190", "Maria Torres", "01/03/2026", "10/03/2026", "Entregada",
                "LabVision", "Matriz Centro"
        ));
        rows.add(new OrdenesLabRowModel.HistoricoRow(
                "LAB-195", "Jose Hernandez", "05/03/2026", "12/03/2026", "Entregada",
                "OptiLab Pro", "Sucursal Norte"
        ));
        rows.add(new OrdenesLabRowModel.HistoricoRow(
                "LAB-200", "Carmen Delgado", "20/03/2026", "28/03/2026", "Entregada",
                "LabVision", "Matriz Centro"
        ));
        rows.add(new OrdenesLabRowModel.HistoricoRow(
                "LAB-203", "Sofia Ramirez", "08/04/2026", "16/04/2026", "En produccion",
                "LabVision", "Matriz Centro"
        ));
        return rows;
    }

    // ---- Filter matching ----

    private boolean matchesFilters(OrdenesLabRowModel.ColaRow row, OrdenesLabFilters filters) {
        if (!FilterSupport.matchesText(filters.getSearchText(),
                row.referencia(), row.cliente(), row.tipoTrabajo(), row.laboratorio())) {
            return false;
        }
        if (!FilterSupport.matchesExact(row.estado(), filters.getEstado())) {
            return false;
        }
        if (!FilterSupport.matchesExact(row.laboratorio(), filters.getLaboratorio())) {
            return false;
        }
        if (!FilterSupport.matchesExact(row.sucursal(), filters.getSucursal())) {
            return false;
        }
        if (!FilterSupport.matchesExact(row.prioridad(), filters.getPrioridad())) {
            return false;
        }
        if (!FilterSupport.inRange(row.ingreso(), filters.getDesde(), filters.getHasta())) {
            return false;
        }
        return true;
    }

    private boolean matchesHistoricoFilters(OrdenesLabRowModel.HistoricoRow row, OrdenesLabFilters filters) {
        if (!FilterSupport.matchesText(filters.getSearchText(),
                row.referencia(), row.cliente(), row.laboratorio())) {
            return false;
        }
        if (!FilterSupport.matchesExact(row.estadoFinal(), filters.getEstado())) {
            return false;
        }
        if (!FilterSupport.matchesExact(row.laboratorio(), filters.getLaboratorio())) {
            return false;
        }
        if (!FilterSupport.matchesExact(row.sucursal(), filters.getSucursal())) {
            return false;
        }
        if (!FilterSupport.inRange(row.ingreso(), filters.getDesde(), filters.getHasta())) {
            return false;
        }
        return true;
    }
}
