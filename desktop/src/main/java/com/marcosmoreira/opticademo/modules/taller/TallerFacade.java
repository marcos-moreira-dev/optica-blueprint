package com.marcosmoreira.opticademo.modules.taller;

import com.marcosmoreira.opticademo.shared.query.FilterSupport;
import com.marcosmoreira.opticademo.shared.query.PageRequest;
import com.marcosmoreira.opticademo.shared.query.PageResult;
import com.marcosmoreira.opticademo.shared.query.PaginationHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Facade that provides demo data for the Taller module.
 * No business logic -- just view-facing data assembly.
 */
public class TallerFacade {

    // ---- Seed data: Ingresos ----
    private static final List<TallerRowModel.IngresoRow> INGRESOS = List.of(
            new TallerRowModel.IngresoRow("TR-001", "Sofia Ramirez", "Reparacion bisagra", "En diagnostico", "Tecnico Rivera", "18/04/2026", "Matriz Centro"),
            new TallerRowModel.IngresoRow("TR-002", "Carlos Mendoza", "Cambio plaquetas", "En reparacion", "Laura Gomez", "17/04/2026", "Norte Mall"),
            new TallerRowModel.IngresoRow("TR-003", "Diana Velez", "Ajuste post-entrega", "Listo para entrega", "Ana Vera", "16/04/2026", "Sur Express"),
            new TallerRowModel.IngresoRow("TR-004", "Ana Vera", "Soldadura puente", "Esperando repuesto", "Tecnico Rivera", "20/04/2026", "Matriz Centro")
    );

    // ---- Seed data: Diagnosticos ----
    private static final List<TallerRowModel.DiagnosticoRow> DIAGNOSTICOS = List.of(
            new TallerRowModel.DiagnosticoRow("TR-001", "Sofia Ramirez", "Reparacion bisagra", "14/04/2026", "Bisagra suelta", "Media", "No", "No"),
            new TallerRowModel.DiagnosticoRow("TR-002", "Carlos Mendoza", "Cambio plaquetas", "13/04/2026", "Plaquetas desgastadas", "Baja", "Si", "No"),
            new TallerRowModel.DiagnosticoRow("TR-004", "Ana Vera", "Soldadura puente", "15/04/2026", "Puente fracturado", "Alta", "Si", "No")
    );

    // ---- Seed data: Reparaciones ----
    private static final List<TallerRowModel.ReparacionRow> REPARACIONES = List.of(
            new TallerRowModel.ReparacionRow("14/04/2026", "TR-001", "Diagnostico inicial bisagra", "Tecnico Rivera", "En proceso"),
            new TallerRowModel.ReparacionRow("15/04/2026", "TR-002", "Retiro de plaquetas viejas", "Laura Gomez", "En proceso"),
            new TallerRowModel.ReparacionRow("15/04/2026", "TR-003", "Ajuste de varillas y puente", "Ana Vera", "Completado"),
            new TallerRowModel.ReparacionRow("16/04/2026", "TR-004", "Preparacion para soldadura", "Tecnico Rivera", "Pendiente repuesto")
    );

    // ---- Seed data: Piezas ----
    private static final List<TallerRowModel.PiezaRow> PIEZAS = List.of(
            new TallerRowModel.PiezaRow("TR-001", "Bisagra metalica", "1", "Disponible", "Bisagra estandar para montura metalica"),
            new TallerRowModel.PiezaRow("TR-002", "Plaqueta silicona", "2", "En pedido", "Plaquetas antideslizantes pack x2"),
            new TallerRowModel.PiezaRow("TR-004", "Tornillo estandar", "1", "Disponible", "Tornillo puente M1.4"),
            new TallerRowModel.PiezaRow("TR-004", "Varilla terminal", "1", "En pedido", "Varilla terminal derecha titanio")
    );

    // ---- Seed data: Envios Externos ----
    private static final List<TallerRowModel.EnvioExternoRow> ENVIOS_EXTERNOS = List.of(
            new TallerRowModel.EnvioExternoRow("TR-004", "Soldadura puente", "Taller Externo OpticaTech", "16/04/2026", "En transito", "22/04/2026")
    );

    // ---- Seed data: Entregas ----
    private static final List<TallerRowModel.EntregaRow> ENTREGAS = List.of(
            new TallerRowModel.EntregaRow("TR-003", "16/04/2026", "Ana Vera", "Entregado", "Si")
    );

    // ---- Seed data: Historico ----
    private static final List<TallerRowModel.HistoricoRow> HISTORICO = List.of(
            new TallerRowModel.HistoricoRow("16/04/2026", "TR-003", "Diana Velez", "Ajuste post-entrega", "Completado", "Ajuste exitoso, cliente conforme"),
            new TallerRowModel.HistoricoRow("10/04/2026", "TR-000", "Pedro Luna", "Cambio de charnela", "Completado", "Trabajo realizado sin observaciones"),
            new TallerRowModel.HistoricoRow("05/04/2026", "TR-000B", "Maria Torres", "Pulido de montura", "Completado", "Montura restaurada correctamente")
    );

    // ------------------------------------------------------------------ Queries

    public PageResult<TallerRowModel.IngresoRow> getIngresos(TallerFilters filters, PageRequest pageRequest) {
        List<TallerRowModel.IngresoRow> filtered = INGRESOS.stream()
                .filter(i -> matchesIngresoFilters(i, filters))
                .collect(Collectors.toList());

        return PaginationHelper.page(filtered, pageRequest);
    }

    public List<TallerRowModel.IngresoRow> getIngresosList(TallerFilters filters) {
        return INGRESOS.stream()
                .filter(i -> matchesIngresoFilters(i, filters))
                .collect(Collectors.toList());
    }

    public List<TallerRowModel.DiagnosticoRow> getDiagnosticos() {
        return new ArrayList<>(DIAGNOSTICOS);
    }

    public List<TallerRowModel.ReparacionRow> getReparaciones() {
        return new ArrayList<>(REPARACIONES);
    }

    public List<TallerRowModel.PiezaRow> getPiezas() {
        return new ArrayList<>(PIEZAS);
    }

    public List<TallerRowModel.EnvioExternoRow> getEnviosExternos() {
        return new ArrayList<>(ENVIOS_EXTERNOS);
    }

    public List<TallerRowModel.EntregaRow> getEntregas() {
        return new ArrayList<>(ENTREGAS);
    }

    public List<TallerRowModel.HistoricoRow> getHistorico(TallerFilters filters) {
        return HISTORICO.stream()
                .filter(h -> matchesHistoricoFilters(h, filters))
                .collect(Collectors.toList());
    }

    // ------------------------------------------------------------------ Summary

    public TallerSummaryModel buildSummary(TallerRowModel.IngresoRow ingreso) {
        if (ingreso == null) {
            return TallerSummaryModel.demoSeed();
        }
        return TallerSummaryModel.from(ingreso);
    }

    // ------------------------------------------------------------------ Filter combos

    public List<String> getTiposIntervencion() {
        return INGRESOS.stream()
                .map(TallerRowModel.IngresoRow::tipo)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> getEstados() {
        return INGRESOS.stream()
                .map(TallerRowModel.IngresoRow::estado)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> getTecnicos() {
        return INGRESOS.stream()
                .map(TallerRowModel.IngresoRow::tecnico)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> getSucursales() {
        return INGRESOS.stream()
                .map(TallerRowModel.IngresoRow::sucursal)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    // ------------------------------------------------------------------ Filter matching

    private boolean matchesIngresoFilters(TallerRowModel.IngresoRow row, TallerFilters filters) {
        if (!FilterSupport.matchesText(filters.getSearchText(),
                row.referencia(), row.cliente(), row.tecnico())) {
            return false;
        }
        if (!FilterSupport.matchesExact(row.tipo(), filters.getTipo())) {
            return false;
        }
        if (!FilterSupport.matchesExact(row.estado(), filters.getEstado())) {
            return false;
        }
        if (!FilterSupport.matchesExact(row.tecnico(), filters.getTecnico())) {
            return false;
        }
        if (!FilterSupport.matchesExact(row.sucursal(), filters.getSucursal())) {
            return false;
        }
        if (!FilterSupport.inRange(row.fechaPromesa(), filters.getDesde(), filters.getHasta())) {
            return false;
        }
        if (filters.isSoloPendientesUrgentes()) {
            String estado = row.estado().toLowerCase();
            return estado.contains("pendiente") || estado.contains("urgente") || estado.contains("diagnostico");
        }
        return true;
    }

    private boolean matchesHistoricoFilters(TallerRowModel.HistoricoRow row, TallerFilters filters) {
        if (!FilterSupport.matchesText(filters.getSearchText(),
                row.referencia(), row.cliente(), row.observacion())) {
            return false;
        }
        if (!FilterSupport.matchesExact(row.tipo(), filters.getTipo())) {
            return false;
        }
        if (!FilterSupport.matchesExact(row.estadoFinal(), filters.getEstado())) {
            return false;
        }
        if (!FilterSupport.inRange(row.fecha(), filters.getDesde(), filters.getHasta())) {
            return false;
        }
        return true;
    }
}
