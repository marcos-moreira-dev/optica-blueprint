package com.marcosmoreira.opticademo.modules.seguros;

import com.marcosmoreira.opticademo.shared.query.FilterSupport;
import com.marcosmoreira.opticademo.shared.query.PageRequest;
import com.marcosmoreira.opticademo.shared.query.PageResult;
import com.marcosmoreira.opticademo.shared.query.PaginationHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Facade para el modulo de Seguros (gestion de convenios y coberturas).
 * <p>
 * Este facade proporciona datos de demostracion para todas las sub-vistas del modulo
 * de Seguros, el cual gestiona la verificacion de coberturas de seguros/convenios,
 * autorizaciones, reclamos y el historico de gestiones. Todos los datos provienen
 * de seed data estatico embebido en constantes de clase.
 * </p>
 * <p>
 * Sub-vistas que alimenta:
 * <ul>
 *   <li><b>Verificaciones:</b> tabla paginada de verificaciones de cobertura.</li>
 *   <li><b>Planes:</b> catalogos de planes de convenio con detalles.</li>
 *   <li><b>Autorizaciones:</b> autorizaciones pendientes y aprobadas.</li>
 *   <li><b>Reclamos:</b> reclamos enviados a aseguradoras.</li>
 *   <li><b>Respuestas:</b> respuestas de aseguradoras a reclamos.</li>
 *   <li><b>Cobertura en Venta:</b> coberturas aplicadas a ventas.</li>
 *   <li><b>Historico:</b> registro historico de todas las gestiones.</li>
 * </ul>
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see FilterSupport
 * @see PaginationHelper
 */
public class SegurosFacade {

    // ---- Seed data: Verificaciones ----
    /** Seed data para verificaciones de cobertura. */
    private static final List<SegurosRowModel.VerificacionRow> VERIFICACIONES = List.of(
            new SegurosRowModel.VerificacionRow("CB-001", "Sofia Ramirez", "Convenio Visual Plus", "Cobertura activa", "31/12/2026", "$80", "Matriz Centro"),
            new SegurosRowModel.VerificacionRow("CB-002", "Ana Vera", "Beneficio Optico Basico", "Pendiente validacion", "30/06/2026", "$0", "Norte Mall"),
            new SegurosRowModel.VerificacionRow("CB-004", "Diana Velez", "Plan Empresarial Centro", "Cobertura activa", "31/12/2026", "$120", "Sur Express")
    );

    // ---- Seed data: Planes ----
    private static final List<SegurosRowModel.PlanRow> PLANES = List.of(
            new SegurosRowModel.PlanRow("Convenio Visual Plus", "01/01/2026 - 31/12/2026", "$200", "15%", "Monturas, Lentes de contacto, Examenes", "Requiere autorizacion previa para monturas premium"),
            new SegurosRowModel.PlanRow("Beneficio Optico Basico", "01/01/2026 - 30/06/2026", "$100", "20%", "Monturas estandar, Cristales monofocales", "No cubre lentes de contacto"),
            new SegurosRowModel.PlanRow("Plan Empresarial Centro", "01/01/2026 - 31/12/2026", "$300", "10%", "Monturas, Lentes de contacto, Examenes, Cristales progresivos", "Solo empleados registrados")
    );

    // ---- Seed data: Autorizaciones ----
    private static final List<SegurosRowModel.AutorizacionRow> AUTORIZACIONES = List.of(
            new SegurosRowModel.AutorizacionRow("AU-014", "Sofia Ramirez", "Convenio Visual Plus", "14/04/2026", "Autorizada", "Cobertura aprobada para montura y cristales"),
            new SegurosRowModel.AutorizacionRow("AU-015", "Ana Vera", "Beneficio Optico Basico", "15/04/2026", "Pendiente", "En espera de validacion de empresa")
    );

    // ---- Seed data: Reclamos ----
    private static final List<SegurosRowModel.ReclamoRow> RECLAMOS = List.of(
            new SegurosRowModel.ReclamoRow("RC-022", "Sofia Ramirez", "OV-124", "$80", "Reclamo enviado", "15/04/2026"),
            new SegurosRowModel.ReclamoRow("RC-023", "Diana Velez", "OV-128", "$60", "Reclamo aceptado", "14/04/2026")
    );

    // ---- Seed data: Respuestas ----
    private static final List<SegurosRowModel.RespuestaRow> RESPUESTAS = List.of(
            new SegurosRowModel.RespuestaRow("15/04/2026", "RC-022", "Respuesta de aseguradora", "En proceso", "Reclamo bajo revision, respuesta en 5 dias habiles"),
            new SegurosRowModel.RespuestaRow("14/04/2026", "RC-023", "Respuesta de aseguradora", "Aceptado", "Reclamo aprobado, cobertura aplicada al 90%")
    );

    // ---- Seed data: CoberturaVenta ----
    private static final List<SegurosRowModel.CoberturaVentaRow> COBERTURA_VENTA = List.of(
            new SegurosRowModel.CoberturaVentaRow("OV-124", "Sofia Ramirez", "Convenio Visual Plus", "$80", "$10", "$0", "Aplicada"),
            new SegurosRowModel.CoberturaVentaRow("OV-128", "Diana Velez", "Plan Empresarial Centro", "$60", "$6", "$0", "Aplicada")
    );

    // ---- Seed data: Historico ----
    private static final List<SegurosRowModel.HistoricoRow> HISTORICO = List.of(
            new SegurosRowModel.HistoricoRow("15/04/2026", "CB-001", "Sofia Ramirez", "Verificacion", "Cobertura activa", "Verificacion exitosa, monto disponible $80"),
            new SegurosRowModel.HistoricoRow("14/04/2026", "RC-023", "Diana Velez", "Reclamo", "Aceptado", "Reclamo aprobado por aseguradora"),
            new SegurosRowModel.HistoricoRow("10/04/2026", "CB-000", "Pedro Luna", "Verificacion", "Cobertura expirada", "Plan vencido el 31/03/2026")
    );

    // ------------------------------------------------------------------ Queries

    public PageResult<SegurosRowModel.VerificacionRow> getVerificaciones(SegurosFilters filters, PageRequest pageRequest) {
        List<SegurosRowModel.VerificacionRow> filtered = VERIFICACIONES.stream()
                .filter(v -> matchesVerificacionFilters(v, filters))
                .collect(Collectors.toList());

        return PaginationHelper.page(filtered, pageRequest);
    }

    public List<SegurosRowModel.VerificacionRow> getVerificacionesList(SegurosFilters filters) {
        return VERIFICACIONES.stream()
                .filter(v -> matchesVerificacionFilters(v, filters))
                .collect(Collectors.toList());
    }

    public List<SegurosRowModel.PlanRow> getPlanes() {
        return new ArrayList<>(PLANES);
    }

    public List<SegurosRowModel.AutorizacionRow> getAutorizaciones() {
        return new ArrayList<>(AUTORIZACIONES);
    }

    public List<SegurosRowModel.ReclamoRow> getReclamos() {
        return new ArrayList<>(RECLAMOS);
    }

    public List<SegurosRowModel.RespuestaRow> getRespuestas() {
        return new ArrayList<>(RESPUESTAS);
    }

    public List<SegurosRowModel.CoberturaVentaRow> getCoberturaVenta() {
        return new ArrayList<>(COBERTURA_VENTA);
    }

    public List<SegurosRowModel.HistoricoRow> getHistorico(SegurosFilters filters) {
        return HISTORICO.stream()
                .filter(h -> matchesHistoricoFilters(h, filters))
                .collect(Collectors.toList());
    }

    // ------------------------------------------------------------------ Summary

    /**
     * Construye un modelo de resumen para la verificacion de cobertura seleccionada.
     *
     * @param verificacion fila de verificacion seleccionada
     * @return {@link SegurosSummaryModel} con datos de la verificacion, o demo seed si es nula
     */
    public SegurosSummaryModel buildSummary(SegurosRowModel.VerificacionRow verificacion) {
        if (verificacion == null) {
            return SegurosSummaryModel.demoSeed();
        }
        return SegurosSummaryModel.from(verificacion);
    }

    // ------------------------------------------------------------------ Filter combos

    public List<String> getEstados() {
        return VERIFICACIONES.stream()
                .map(SegurosRowModel.VerificacionRow::estado)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> getPlanesConvenio() {
        return VERIFICACIONES.stream()
                .map(SegurosRowModel.VerificacionRow::planConvenio)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> getTiposCaso() {
        return HISTORICO.stream()
                .map(SegurosRowModel.HistoricoRow::tipoCaso)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> getSucursales() {
        return VERIFICACIONES.stream()
                .map(SegurosRowModel.VerificacionRow::sucursal)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    // ------------------------------------------------------------------ Filter matching

    private boolean matchesVerificacionFilters(SegurosRowModel.VerificacionRow row, SegurosFilters filters) {
        if (!FilterSupport.matchesText(filters.getSearchText(),
                row.referencia(), row.cliente(), row.planConvenio())) {
            return false;
        }
        if (!FilterSupport.matchesExact(row.estado(), filters.getEstado())) {
            return false;
        }
        if (!FilterSupport.matchesExact(row.planConvenio(), filters.getPlanConvenio())) {
            return false;
        }
        if (!FilterSupport.matchesExact(row.sucursal(), filters.getSucursal())) {
            return false;
        }
        if (!FilterSupport.inRange(row.vigencia(), filters.getDesde(), filters.getHasta())) {
            return false;
        }
        if (filters.isSoloCasosPendientes()) {
            String estado = row.estado().toLowerCase();
            return estado.contains("pendiente") || estado.contains("validacion") || estado.contains("espera");
        }
        return true;
    }

    private boolean matchesHistoricoFilters(SegurosRowModel.HistoricoRow row, SegurosFilters filters) {
        if (!FilterSupport.matchesText(filters.getSearchText(),
                row.referencia(), row.cliente(), row.observacion())) {
            return false;
        }
        if (!FilterSupport.matchesExact(row.tipoCaso(), filters.getTipoCaso())) {
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
