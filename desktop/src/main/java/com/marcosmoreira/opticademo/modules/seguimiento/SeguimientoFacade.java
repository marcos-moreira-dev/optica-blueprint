package com.marcosmoreira.opticademo.modules.seguimiento;

import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.shared.query.FilterSupport;
import com.marcosmoreira.opticademo.shared.query.PageRequest;
import com.marcosmoreira.opticademo.shared.query.PageResult;
import com.marcosmoreira.opticademo.shared.query.PaginationHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Facade para el modulo de Seguimiento (seguimiento post-venta y retencion).
 * <p>
 * Este facade proporciona datos de demostracion para todas las sub-vistas del modulo
 * de Seguimiento, el cual gestiona el seguimiento post-venta, recalls, cobros pendientes,
 * mensajes y retencion de clientes. Todos los datos provienen de seed data estatico.
 * </p>
 * <p>
 * Sub-vistas que alimenta:
 * <ul>
 *   <li><b>Bandeja de Seguimiento:</b> tabla paginada de casos activos con prioridad.</li>
 *   <li><b>Recall y Revisiones:</b> citas de control y revisiones proximas.</li>
 *   <li><b>Pedidos No Retirados:</b> trabajos listos no retirados por el cliente.</li>
 *   <li><b>Cobros Pendientes:</b> saldos pendientes con acciones de cobro.</li>
 *   <li><b>Mensajes y Recordatorios:</b> historial de comunicaciones con clientes.</li>
 *   <li><b>Historico:</b> casos cerrados con filtros extendidos.</li>
 * </ul>
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see DemoStore
 * @see FilterSupport
 * @see PaginationHelper
 */
public class SeguimientoFacade {

    private final DemoStore store;

    /**
     * Construye el facade con referencia al almacén de datos de demostración.
     *
     * @param store instancia del {@link DemoStore}
     */
    public SeguimientoFacade(DemoStore store) {
        this.store = store;
    }

    // ------------------------------------------------------------------ Bandeja de seguimiento (sub-view 1)

    /**
     * Retorna una pagina de la bandeja de seguimiento filtrada segun
     * los criterios de busqueda.
     *
     * @param filters     criterios de filtrado
     * @param pageRequest solicitud de paginacion
     * @return {@link PageResult} con la pagina de {@link SeguimientoRowModel.BandejaRow}
     */
    public PageResult<SeguimientoRowModel.BandejaRow> getBandeja(SeguimientoFilters filters, PageRequest pageRequest) {
        List<SeguimientoRowModel.BandejaRow> filtered = buildBandeja().stream()
                .filter(r -> matchesBandejaFilters(r, filters))
                .collect(Collectors.toList());

        return PaginationHelper.page(filtered, pageRequest);
    }

    public List<SeguimientoRowModel.BandejaRow> getBandeja(SeguimientoFilters filters) {
        return buildBandeja().stream()
                .filter(r -> matchesBandejaFilters(r, filters))
                .collect(Collectors.toList());
    }

    // ------------------------------------------------------------------ Recall y revisiones (sub-view 2)

    public List<SeguimientoRowModel.RecallRow> getRecalls() {
        List<SeguimientoRowModel.RecallRow> list = new ArrayList<>();

        list.add(SeguimientoRowModel.RecallRow.seed(
                "Sofia Ramirez", "15/10/2025", "Recall anual",
                "18/04/2026", "Recall pendiente", "Matriz Centro"
        ));
        list.add(SeguimientoRowModel.RecallRow.seed(
                "Carlos Mendoza", "20/09/2025", "Revision de receta",
                "20/04/2026", "Receta vencida", "Norte Mall"
        ));
        list.add(SeguimientoRowModel.RecallRow.seed(
                "Ana Vera", "10/11/2025", "Revision semestral",
                "22/04/2026", "Revision proxima", "Matriz Centro"
        ));
        list.add(SeguimientoRowModel.RecallRow.seed(
                "Luis Andrade", "05/08/2025", "Control de lente de contacto",
                "25/04/2026", "Recall pendiente", "Norte Mall"
        ));

        return list;
    }

    // ------------------------------------------------------------------ Pedidos listos y no retirados (sub-view 3)

    public List<SeguimientoRowModel.NoRetiradoRow> getNoRetirados() {
        List<SeguimientoRowModel.NoRetiradoRow> list = new ArrayList<>();

        list.add(SeguimientoRowModel.NoRetiradoRow.seed(
                "SG-022", "Diana Velez", "3 dias",
                "Notificado por SMS", "$0.00", "No retirado", "Norte Mall"
        ));
        list.add(SeguimientoRowModel.NoRetiradoRow.seed(
                "ET-045", "Carlos Mendoza", "5 dias",
                "Recordatorio enviado", "$18.00", "Con saldo pendiente", "Matriz Centro"
        ));
        list.add(SeguimientoRowModel.NoRetiradoRow.seed(
                "ET-046", "Ana Vera", "2 dias",
                "Pendiente de notificacion", "$0.00", "No retirado", "Norte Mall"
        ));
        list.add(SeguimientoRowModel.NoRetiradoRow.seed(
                "ET-047", "Roberto Guzman", "7 dias",
                "Notificado por WhatsApp", "$35.00", "No retirado", "Matriz Centro"
        ));

        return list;
    }

    // ------------------------------------------------------------------ Cobros pendientes (sub-view 4)

    public List<SeguimientoRowModel.CobroPendienteRow> getCobrosPendientes() {
        List<SeguimientoRowModel.CobroPendienteRow> list = new ArrayList<>();

        list.add(SeguimientoRowModel.CobroPendienteRow.seed(
                "SG-023", "Carlos Mendoza", "$120.00",
                "10/04/2026", "Con saldo pendiente", "Llamar al cliente", "Matriz Centro"
        ));
        list.add(SeguimientoRowModel.CobroPendienteRow.seed(
                "ET-045", "Carlos Mendoza", "$18.00",
                "12/04/2026", "Pago parcial", "Enviar recordatorio", "Matriz Centro"
        ));
        list.add(SeguimientoRowModel.CobroPendienteRow.seed(
                "ET-047", "Roberto Guzman", "$35.00",
                "08/04/2026", "Vencido", "Contactar urgente", "Matriz Centro"
        ));
        list.add(SeguimientoRowModel.CobroPendienteRow.seed(
                "VE-012", "Diana Velez", "$75.00",
                "14/04/2026", "Con saldo pendiente", "Agendar cobro", "Norte Mall"
        ));

        return list;
    }

    // ------------------------------------------------------------------ Mensajes y recordatorios (sub-view 5)

    public List<SeguimientoRowModel.MensajeRow> getMensajes() {
        List<SeguimientoRowModel.MensajeRow> list = new ArrayList<>();

        list.add(SeguimientoRowModel.MensajeRow.seed(
                "16/04/2026", "Sofia Ramirez", "Recordatorio",
                "WhatsApp", "Enviado", "Recibido"
        ));
        list.add(SeguimientoRowModel.MensajeRow.seed(
                "15/04/2026", "Diana Velez", "Notificacion trabajo listo",
                "SMS", "Enviado", "Recibido"
        ));
        list.add(SeguimientoRowModel.MensajeRow.seed(
                "14/04/2026", "Carlos Mendoza", "Cobro pendiente",
                "Llamada", "Pendiente", "-"
        ));
        list.add(SeguimientoRowModel.MensajeRow.seed(
                "13/04/2026", "Ana Vera", "Revision programada",
                "Email", "Enviado", "Leido"
        ));
        list.add(SeguimientoRowModel.MensajeRow.seed(
                "12/04/2026", "Luis Andrade", "Recall anual",
                "WhatsApp", "No enviado", "-"
        ));

        return list;
    }

    // ------------------------------------------------------------------ Historico de seguimiento (sub-view 6)

    public List<SeguimientoRowModel.HistoricoRow> getHistorico(SeguimientoFilters filters) {
        List<SeguimientoRowModel.HistoricoRow> list = new ArrayList<>();

        list.add(SeguimientoRowModel.HistoricoRow.seed(
                "10/04/2026", "SG-018", "Maria Leon", "Recall",
                "Cerrado", "Revision completada", "Matriz Centro"
        ));
        list.add(SeguimientoRowModel.HistoricoRow.seed(
                "08/04/2026", "SG-017", "Juan Cedeno", "Cobro",
                "Cerrado", "Pago recibido", "Norte Mall"
        ));
        list.add(SeguimientoRowModel.HistoricoRow.seed(
                "05/04/2026", "SG-016", "Pedro Rivas", "No retirado",
                "Cerrado", "Trabajo retirado", "Matriz Centro"
        ));
        list.add(SeguimientoRowModel.HistoricoRow.seed(
                "02/04/2026", "SG-015", "Ana Vera", "Mensaje",
                "Cerrado", "Mensaje respondido", "Norte Mall"
        ));
        list.add(SeguimientoRowModel.HistoricoRow.seed(
                "28/03/2026", "SG-014", "Carlos Mendoza", "Recall",
                "Reprogramado", "Cliente solicito cambio de fecha", "Matriz Centro"
        ));

        return list.stream()
                .filter(r -> matchesHistoricoFilters(r, filters))
                .collect(Collectors.toList());
    }

    // ------------------------------------------------------------------ Summary

    /**
     * Construye un modelo de resumen para el caso de seguimiento seleccionado.
     *
     * @param caso objeto de caso (BandejaRow) seleccionado en la vista
     * @return {@link SeguimientoSummaryModel} con datos del caso
     */
    public SeguimientoSummaryModel buildSummary(Object caso) {
        if (caso instanceof SeguimientoRowModel.BandejaRow row) {
            return SeguimientoSummaryModel.fromBandeja(row);
        }
        return SeguimientoSummaryModel.demoSeed();
    }

    // ------------------------------------------------------------------ Filter combos

    /**
     * Retorna los tipos de seguimiento disponibles para el combo de filtro.
     *
     * @return lista de tipos (Recall, No retirado, Cobro pendiente, Mensaje, Revision)
     */
    public List<String> getTiposSeguimiento() {
        return List.of(
                "Recall",
                "No retirado",
                "Cobro pendiente",
                "Mensaje",
                "Revision"
        );
    }

    /**
     * Retorna los estados de seguimiento disponibles para el combo de filtro.
     *
     * @return lista de estados de seguimiento
     */
    public List<String> getEstadosSeguimiento() {
        return List.of(
                "Recall pendiente",
                "No retirado",
                "Con saldo pendiente",
                "Pendiente",
                "Cerrado",
                "Reprogramado",
                "Pago parcial",
                "Vencido"
        );
    }

    /**
     * Retorna los canales de comunicacion para el combo de filtro.
     *
     * @return lista de canales (WhatsApp, SMS, Llamada, Email, Presencial)
     */
    public List<String> getCanales() {
        return List.of(
                "WhatsApp",
                "SMS",
                "Llamada",
                "Email",
                "Presencial"
        );
    }

    // ------------------------------------------------------------------ Stats Recalls

    /**
     * Retorna estadisticas de recalls: pendientes, recetas vencidas y revisiones proximas.
     *
     * @return {@link StatsRecalls} con contadores de recalls
     */
    public StatsRecalls getStatsRecalls() {
        List<SeguimientoRowModel.RecallRow> recalls = getRecalls();

        int recallPendiente = (int) recalls.stream()
                .filter(r -> r.estado().contains("pendiente") || r.estado().contains("pendiente"))
                .count();
        int recetasVencidas = (int) recalls.stream()
                .filter(r -> r.estado().toLowerCase().contains("vencida"))
                .count();
        int revisionesProximas = (int) recalls.stream()
                .filter(r -> r.estado().toLowerCase().contains("proxima"))
                .count();

        return new StatsRecalls(recallPendiente, recetasVencidas, revisionesProximas);
    }

    /**
     * Record que encapsula las estadisticas de recalls.
     *
     * @param recallPendiente cantidad de recalls pendientes
     * @param recetasVencidas cantidad de recetas vencidas
     * @param revisionesProximas cantidad de revisiones proximas
     */
    public record StatsRecalls(int recallPendiente, int recetasVencidas, int revisionesProximas) {
    }

    // ------------------------------------------------------------------ Stats No Retirados

    /**
     * Retorna estadisticas de pedidos no retirados: total, notificados y con saldo.
     *
     * @return {@link StatsNoRetirados} con contadores de no retirados
     */
    public StatsNoRetirados getStatsNoRetirados() {
        List<SeguimientoRowModel.NoRetiradoRow> noRetirados = getNoRetirados();

        int total = noRetirados.size();
        int notificados = (int) noRetirados.stream()
                .filter(r -> r.notificacion().contains("Notificado") || r.notificacion().contains("Recordatorio"))
                .count();
        int conSaldo = (int) noRetirados.stream()
                .filter(r -> {
                    String saldo = r.saldo().replace("$", "").trim();
                    try {
                        return Double.parseDouble(saldo) > 0.001;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })
                .count();

        return new StatsNoRetirados(total, notificados, conSaldo);
    }

    /**
     * Record que encapsula las estadisticas de pedidos no retirados.
     *
     * @param noRetirados total de pedidos no retirados
     * @param notificados cantidad de clientes notificados
     * @param conSaldo    cantidad con saldo pendiente
     */
    public record StatsNoRetirados(int noRetirados, int notificados, int conSaldo) {
    }

    // ------------------------------------------------------------------ Stats Cobros

    /**
     * Retorna estadisticas de cobros pendientes: casos con saldo, monto total y vencidos.
     *
     * @return {@link StatsCobros} con estadisticas de cobros
     */
    public StatsCobros getStatsCobros() {
        List<SeguimientoRowModel.CobroPendienteRow> cobros = getCobrosPendientes();

        int casosConSaldo = cobros.size();
        double montoPendiente = 0.0;
        int casosVencidos = 0;

        for (SeguimientoRowModel.CobroPendienteRow c : cobros) {
            String saldo = c.saldo().replace("$", "").trim();
            try {
                montoPendiente += Double.parseDouble(saldo);
            } catch (NumberFormatException e) {
                // ignore
            }
            if (c.estado().toLowerCase().contains("vencido")) {
                casosVencidos++;
            }
        }

        return new StatsCobros(casosConSaldo, montoPendiente, casosVencidos);
    }

    public record StatsCobros(int casosConSaldo, double montoPendiente, int casosVencidos) {
    }

    // ------------------------------------------------------------------ Internal data builders

    private List<SeguimientoRowModel.BandejaRow> buildBandeja() {
        List<SeguimientoRowModel.BandejaRow> list = new ArrayList<>();

        // Seed data from blueprint
        list.add(SeguimientoRowModel.BandejaRow.seed(
                "SG-021", "Sofia Ramirez", "Recall",
                "Recall pendiente", "18/04/2026", "Media",
                "Matriz Centro", "Asesor Molina"
        ));
        list.add(SeguimientoRowModel.BandejaRow.seed(
                "SG-022", "Diana Velez", "No retirado",
                "No retirado", "16/04/2026", "Alta",
                "Norte Mall", "Asesora Lopez"
        ));
        list.add(SeguimientoRowModel.BandejaRow.seed(
                "SG-023", "Carlos Mendoza", "Cobro pendiente",
                "Con saldo pendiente", "17/04/2026", "Alta",
                "Matriz Centro", "Caja Central"
        ));
        list.add(SeguimientoRowModel.BandejaRow.seed(
                "SG-024", "Ana Vera", "Revision",
                "Pendiente", "19/04/2026", "Media",
                "Matriz Centro", "Dra. Salazar"
        ));
        list.add(SeguimientoRowModel.BandejaRow.seed(
                "SG-025", "Luis Andrade", "Recall",
                "Recall pendiente", "25/04/2026", "Baja",
                "Norte Mall", "Asesor Molina"
        ));
        list.add(SeguimientoRowModel.BandejaRow.seed(
                "SG-026", "Roberto Guzman", "Mensaje",
                "Pendiente", "20/04/2026", "Media",
                "Matriz Centro", "Asesora Lopez"
        ));

        return list;
    }

    // ------------------------------------------------------------------ Filter matching

    private boolean matchesBandejaFilters(SeguimientoRowModel.BandejaRow row, SeguimientoFilters filters) {
        if (!FilterSupport.matchesText(filters.getSearchText(),
                row.referencia(), row.cliente(), row.tipo())) {
            return false;
        }

        if (!FilterSupport.matchesExact(row.tipo(), filters.getTipo())) {
            return false;
        }

        if (!FilterSupport.matchesExact(row.estado(), filters.getEstado())) {
            return false;
        }

        if (!FilterSupport.matchesExact(row.prioridad(), filters.getPrioridad())) {
            return false;
        }

        if (!FilterSupport.matchesExact(row.sucursal(), filters.getSucursal())) {
            return false;
        }

        if (!FilterSupport.inRange(row.fechaObjetivo(), filters.getDesde(), filters.getHasta())) {
            return false;
        }

        return true;
    }

    private boolean matchesHistoricoFilters(SeguimientoRowModel.HistoricoRow row, SeguimientoFilters filters) {
        if (!FilterSupport.matchesText(filters.getSearchText(),
                row.referencia(), row.cliente(), row.tipo())) {
            return false;
        }

        if (!FilterSupport.matchesExact(row.tipo(), filters.getTipo())) {
            return false;
        }

        if (!FilterSupport.matchesExact(row.estadoFinal(), filters.getEstado())) {
            return false;
        }

        if (!FilterSupport.matchesExact(row.sucursal(), filters.getSucursal())) {
            return false;
        }

        if (!FilterSupport.inRange(row.fecha(), filters.getDesde(), filters.getHasta())) {
            return false;
        }

        if (filters.isSoloCasosAbiertos()) {
            if ("Cerrado".equals(row.estadoFinal())) {
                return false;
            }
        }

        return true;
    }
}
