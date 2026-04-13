package com.marcosmoreira.opticademo.modules.entregas;

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
 * Facade para el modulo de Entregas (gestion de entregas de trabajos).
 * <p>
 * Este facade proporciona datos de demostracion para todas las sub-vistas del modulo
 * de Entregas, el cual gestiona la entrega de trabajos opticos a los clientes.
 * Los datos de trabajos listos combinan seed data estatico con datos del
 * {@link DemoStore} (ventas en estado LISTO o EN_PROCESO).
 * </p>
 * <p>
 * Sub-vistas que alimenta:
 * <ul>
 *   <li><b>Trabajos Listos:</b> tabla paginada con estado, saldo y notificacion.</li>
 *   <li><b>Validaciones:</b> checklist de validacion previa a la entrega.</li>
 *   <li><b>Pendientes de Retiro:</b> trabajos listos no retirados por el cliente.</li>
 *   <li><b>Post-Entrega:</b> ajustes y observaciones posteriores a la entrega.</li>
 *   <li><b>Historico:</b> registro historico de entregas con filtros extendidos.</li>
 * </ul>
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see DemoStore
 * @see VentaOptica
 * @see FilterSupport
 * @see PaginationHelper
 */
public class EntregasFacade {

    private final DemoStore store;

    /**
     * Construye el facade con referencia al almacén de datos de demostración.
     *
     * @param store instancia del {@link DemoStore}
     */
    public EntregasFacade(DemoStore store) {
        this.store = store;
    }

    // ------------------------------------------------------------------ Trabajos listos (sub-view 1)

    /**
     * Retorna una pagina de trabajos listos para entrega filtrada segun
     * los criterios de busqueda.
     *
     * @param filters     criterios de filtrado
     * @param pageRequest solicitud de paginacion
     * @return {@link PageResult} con la pagina de {@link EntregasRowModel.TrabajoListoRow}
     */
    public PageResult<EntregasRowModel.TrabajoListoRow> getTrabajosListos(EntregasFilters filters, PageRequest pageRequest) {
        List<EntregasRowModel.TrabajoListoRow> filtered = buildTrabajosListos().stream()
                .filter(r -> matchesTrabajoListoFilters(r, filters))
                .collect(Collectors.toList());

        return PaginationHelper.page(filtered, pageRequest);
    }

    /**
     * Retorna todos los trabajos listos como lista (sin paginacion).
     *
     * @param filters criterios de filtrado
     * @return lista de {@link EntregasRowModel.TrabajoListoRow} filtrados
     */
    public List<EntregasRowModel.TrabajoListoRow> getTrabajosListos(EntregasFilters filters) {
        return buildTrabajosListos().stream()
                .filter(r -> matchesTrabajoListoFilters(r, filters))
                .collect(Collectors.toList());
    }

    // ------------------------------------------------------------------ Validaciones (sub-view 2)

    /**
     * Retorna las filas de validacion para el checklist de validacion previa
     * a la entrega, verificando coincidencia de lentes, montura y cobro.
     *
     * @return lista de {@link EntregasRowModel.ValidacionRow} con resultados de validacion
     */
    public List<EntregasRowModel.ValidacionRow> getValidaciones() {
        List<EntregasRowModel.ValidacionRow> list = new ArrayList<>();

        list.add(EntregasRowModel.ValidacionRow.seed(
                "ET-041", "Sofia Ramirez", "Montura + lente", "16/04/2026",
                "Si", "Si", "No", "$0.00"
        ));
        list.add(EntregasRowModel.ValidacionRow.seed(
                "ET-042", "Ana Vera", "Lentes monofocales", "16/04/2026",
                "Si", "Pendiente", "No", "$0.00"
        ));
        list.add(EntregasRowModel.ValidacionRow.seed(
                "ET-043", "Luis Andrade", "Cambio de lente", "17/04/2026",
                "Si", "Si", "Si", "$55.00"
        ));
        list.add(EntregasRowModel.ValidacionRow.seed(
                "ET-044", "Diana Velez", "Progresivos", "15/04/2026",
                "Si", "Si", "No", "$0.00"
        ));
        list.add(EntregasRowModel.ValidacionRow.seed(
                "ET-045", "Carlos Mendoza", "Montura + lente", "14/04/2026",
                "Si", "Pendiente", "No", "$18.00"
        ));
        list.add(EntregasRowModel.ValidacionRow.seed(
                "ET-046", "Ana Vera", "Lentes fotocromaticos", "15/04/2026",
                "Si", "Si", "Si", "$0.00"
        ));
        list.add(EntregasRowModel.ValidacionRow.seed(
                "ET-049", "Carmen Lopez", "Lentes blue cut", "17/04/2026",
                "Si", "Pendiente", "No", "$0.00"
        ));

        return list;
    }

    // ------------------------------------------------------------------ Pendientes de retiro (sub-view 4)

    /**
     * Retorna los trabajos que estan listos pero no han sido retirados
     * por el cliente, mostrando dias de espera, saldo y estado de notificacion.
     *
     * @return lista de {@link EntregasRowModel.PendienteRow} con trabajos pendientes de retiro
     */
    public List<EntregasRowModel.PendienteRow> getPendientesRetiro() {
        List<EntregasRowModel.PendienteRow> list = new ArrayList<>();

        list.add(EntregasRowModel.PendienteRow.seed(
                "ET-044", "Diana Velez", "1 dia", "$0.00",
                "Pendiente de retiro", "Notificado por SMS", "Norte Mall"
        ));
        list.add(EntregasRowModel.PendienteRow.seed(
                "ET-045", "Carlos Mendoza", "3 dias", "$18.00",
                "Con saldo pendiente", "Recordatorio enviado", "Matriz Centro"
        ));
        list.add(EntregasRowModel.PendienteRow.seed(
                "ET-046", "Ana Vera", "2 dias", "$0.00",
                "No retirada", "Pendiente de notificacion", "Norte Mall"
        ));
        list.add(EntregasRowModel.PendienteRow.seed(
                "ET-050", "Sofia Ramirez", "1 dia", "$0.00",
                "Pendiente de retiro", "Notificado por SMS", "Norte Mall"
        ));

        return list;
    }

    // ------------------------------------------------------------------ Post-entrega (sub-view 5)

    /**
     * Retorna los registros de ajustes y observaciones posteriores a la entrega,
     * con fecha, tipo de ajuste, estado y responsable.
     *
     * @return lista de {@link EntregasRowModel.PostEntregaRow} con datos post-entrega
     */
    public List<EntregasRowModel.PostEntregaRow> getPostEntrega() {
        List<EntregasRowModel.PostEntregaRow> list = new ArrayList<>();

        list.add(EntregasRowModel.PostEntregaRow.seed(
                "16/04/2026", "ET-041", "Ajuste de montura", "Cerrado", "Tecnico Rivera"
        ));
        list.add(EntregasRowModel.PostEntregaRow.seed(
                "17/04/2026", "ET-043", "Molestia inicial", "Pendiente", "Dra. Salazar"
        ));
        list.add(EntregasRowModel.PostEntregaRow.seed(
                "17/04/2026", "ET-044", "Revision de comodidad", "Cerrado", "Asesor Molina"
        ));
        list.add(EntregasRowModel.PostEntregaRow.seed(
                "15/04/2026", "ET-047", "Ajuste post-entrega", "Cerrado", "Ana Vera"
        ));
        list.add(EntregasRowModel.PostEntregaRow.seed(
                "14/04/2026", "ET-048", "Revision general", "Cerrado", "Laura Gomez"
        ));

        return list;
    }

    // ------------------------------------------------------------------ Historico (sub-view 6)

    /**
     * Retorna el historico de entregas con filtros extendidos de busqueda,
     * mostrando fecha, referencia, cliente, estado final, saldo y observaciones.
     *
     * @param filters criterios de filtrado
     * @return lista de {@link EntregasRowModel.HistoricoRow} filtradas
     */
    public List<EntregasRowModel.HistoricoRow> getHistorico(EntregasFilters filters) {
        List<EntregasRowModel.HistoricoRow> list = new ArrayList<>();

        list.add(EntregasRowModel.HistoricoRow.seed(
                "15/04/2026", "ET-038", "Maria Leon", "Entregada", "$0.00",
                "Matriz Centro", "Entrega conforme"
        ));
        list.add(EntregasRowModel.HistoricoRow.seed(
                "14/04/2026", "ET-037", "Juan Cedeno", "Reprogramada", "$0.00",
                "Norte Mall", "Cliente solicito retiro posterior"
        ));
        list.add(EntregasRowModel.HistoricoRow.seed(
                "13/04/2026", "ET-036", "Carlos Mendoza", "No retirada", "$22.00",
                "Matriz Centro", "Pendiente de pago y retiro"
        ));

        return list.stream()
                .filter(r -> matchesHistoricoFilters(r, filters))
                .collect(Collectors.toList());
    }

    // ------------------------------------------------------------------ Summary

    /**
     * Construye un modelo de resumen de entrega para la orden seleccionada.
     *
     * @param venta entidad {@link VentaOptica} seleccionada
     * @return {@link EntregasSummaryModel} con datos de la entrega, o demo seed si es nula
     */
    public EntregasSummaryModel buildSummary(VentaOptica venta) {
        if (venta == null) {
            return EntregasSummaryModel.demoSeed();
        }
        return EntregasSummaryModel.from(venta);
    }

    // ------------------------------------------------------------------ Filter combos

    /**
     * Retorna los estados de entrega distinct para el combo de filtro.
     *
     * @return lista de estados de entrega (Lista para entrega, Pendiente de validacion, etc.)
     */
    public List<String> getEstadosEntrega() {
        return List.of(
                "Lista para entrega",
                "Pendiente de validacion",
                "Pendiente de retiro",
                "Notificado",
                "Entregada",
                "Reprogramada",
                "Con ajuste pendiente",
                "Con saldo pendiente",
                "No retirada"
        );
    }

    /**
     * Retorna los estados de notificacion distinct para el combo de filtro.
     *
     * @return lista de estados de notificacion
     */
    public List<String> getEstadosNotificacion() {
        return List.of(
                "Pendiente de notificacion",
                "Notificado por SMS",
                "Recordatorio enviado"
        );
    }

    // ------------------------------------------------------------------ Stats pendientes

    /**
     * Retorna estadisticas de entregas pendientes: total pendientes,
     * con saldo pendiente y notificados.
     *
     * @return {@link StatsPendientes} con contadores de estado
     */
    public StatsPendientes getStatsPendientes() {
        List<EntregasRowModel.PendienteRow> pendientes = getPendientesRetiro();

        int totalPendientes = pendientes.size();
        int conSaldo = (int) pendientes.stream()
                .filter(p -> !p.saldo().equals("$0.00") && !p.saldo().equals("$0.00"))
                .count();
        int notificados = (int) pendientes.stream()
                .filter(p -> p.notificacion().contains("Notificado") || p.notificacion().contains("Recordatorio"))
                .count();

        return new StatsPendientes(totalPendientes, conSaldo, notificados);
    }

    /**
     * Record que encapsula las estadisticas de entregas pendientes.
     *
     * @param pendientes  total de trabajos pendientes de retiro
     * @param conSaldo    cantidad con saldo pendiente
     * @param notificados cantidad de clientes notificados
     */
    public record StatsPendientes(int pendientes, int conSaldo, int notificados) {
    }

    // ------------------------------------------------------------------ Internal data builders

    private List<EntregasRowModel.TrabajoListoRow> buildTrabajosListos() {
        List<EntregasRowModel.TrabajoListoRow> list = new ArrayList<>();

        // Expanded seed data from blueprint
        list.add(EntregasRowModel.TrabajoListoRow.seed(
                "ET-041", "Sofia Ramirez", "Montura + lente", "16/04/2026",
                "Lista para entrega", "$0.00", "Matriz Centro", "Notificado por SMS"
        ));
        list.add(EntregasRowModel.TrabajoListoRow.seed(
                "ET-042", "Ana Vera", "Lentes monofocales", "16/04/2026",
                "Pendiente de validacion", "$0.00", "Norte Mall", "Pendiente de notificacion"
        ));
        list.add(EntregasRowModel.TrabajoListoRow.seed(
                "ET-043", "Luis Andrade", "Cambio de lente", "17/04/2026",
                "Con saldo pendiente", "$55.00", "Matriz Centro", "Recordatorio enviado"
        ));
        list.add(EntregasRowModel.TrabajoListoRow.seed(
                "ET-044", "Diana Velez", "Progresivos", "15/04/2026",
                "Pendiente de retiro", "$0.00", "Norte Mall", "Notificado por SMS"
        ));
        list.add(EntregasRowModel.TrabajoListoRow.seed(
                "ET-045", "Carlos Mendoza", "Montura + lente", "14/04/2026",
                "Con saldo pendiente", "$18.00", "Matriz Centro", "Recordatorio enviado"
        ));
        list.add(EntregasRowModel.TrabajoListoRow.seed(
                "ET-046", "Ana Vera", "Lentes fotocromaticos", "15/04/2026",
                "Pendiente de retiro", "$0.00", "Norte Mall", "Pendiente de notificacion"
        ));
        list.add(EntregasRowModel.TrabajoListoRow.seed(
                "ET-047", "Maria Leon", "Lentes monofocales", "14/04/2026",
                "Entregada", "$0.00", "Matriz Centro", "Entrega conforme"
        ));
        list.add(EntregasRowModel.TrabajoListoRow.seed(
                "ET-048", "Juan Cedeno", "Montura + lente", "13/04/2026",
                "Entregada", "$0.00", "Norte Mall", "Entrega conforme"
        ));
        list.add(EntregasRowModel.TrabajoListoRow.seed(
                "ET-049", "Carmen Lopez", "Lentes blue cut", "17/04/2026",
                "Lista para entrega", "$0.00", "Matriz Centro", "Notificado por SMS"
        ));
        list.add(EntregasRowModel.TrabajoListoRow.seed(
                "ET-050", "Sofia Ramirez", "Lentes de contacto", "16/04/2026",
                "Pendiente de retiro", "$0.00", "Norte Mall", "Notificado por SMS"
        ));

        // Supplement with data from ventas that are LISTO or EN_PROCESO
        for (VentaOptica venta : store.ventas) {
            String estadoEntrega = determineEstadoEntrega(venta);
            boolean alreadyExists = list.stream().anyMatch(r -> r.referencia().equals(venta.getReferencia()));
            if (!alreadyExists && (venta.getEstado().name().equals("LISTO") ||
                    venta.getEstado().name().equals("EN_PROCESO") ||
                    venta.getEstado().name().equals("EN_ESPERA"))) {
                String saldo = String.format("$%.2f", venta.getSaldo());
                list.add(EntregasRowModel.TrabajoListoRow.seed(
                        venta.getReferencia(),
                        venta.getClienteNombre(),
                        determineTipoTrabajo(venta),
                        venta.getFechaPromesa() != null ? venta.getFechaPromesa() : "-",
                        estadoEntrega,
                        saldo,
                        venta.getSucursal() != null ? venta.getSucursal() : "-",
                        "Pendiente de notificacion"
                ));
            }
        }

        return list;
    }

    private String determineEstadoEntrega(VentaOptica venta) {
        return switch (venta.getEstado()) {
            case LISTO -> "Lista para entrega";
            case EN_PROCESO -> "Pendiente de validacion";
            case ENTREGADO, COMPLETADO -> "Entregada";
            case EN_ESPERA -> "Pendiente de retiro";
            case CONFIRMADO -> "Notificado";
            default -> "Pendiente de validacion";
        };
    }

    private String determineTipoTrabajo(VentaOptica venta) {
        String lente = venta.getLenteTipo();
        String montura = venta.getMonturaRef();
        if (lente != null && !lente.isBlank() && montura != null && !montura.isBlank()) {
            return "Montura + lente";
        } else if (lente != null && !lente.isBlank()) {
            return "Solo lente - " + lente;
        }
        return "Montura";
    }

    // ------------------------------------------------------------------ Filter matching

    private boolean matchesTrabajoListoFilters(EntregasRowModel.TrabajoListoRow row, EntregasFilters filters) {
        // Search text: matches referencia, cliente, tipoTrabajo
        if (!FilterSupport.matchesText(filters.getSearchText(),
                row.referencia(), row.cliente(), row.tipoTrabajo())) {
            return false;
        }

        // Estado filter
        if (!FilterSupport.matchesExact(row.estado(), filters.getEstado())) {
            return false;
        }

        // Sucursal filter
        if (!FilterSupport.matchesExact(row.sucursal(), filters.getSucursal())) {
            return false;
        }

        // Notificacion filter
        if (!FilterSupport.matchesExact(row.notificacion(), filters.getNotificacion())) {
            return false;
        }

        // Date range on fechaPromesa
        if (!FilterSupport.inRange(row.fechaPromesa(), filters.getDesde(), filters.getHasta())) {
            return false;
        }

        // Solo con saldo: only include rows with non-zero saldo
        if (filters.isSoloConSaldo()) {
            String saldo = row.saldo().replace("$", "").trim();
            try {
                if (Double.parseDouble(saldo) <= 0.001) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }

    private boolean matchesHistoricoFilters(EntregasRowModel.HistoricoRow row, EntregasFilters filters) {
        // Search text: matches referencia, cliente
        if (!FilterSupport.matchesText(filters.getSearchText(),
                row.referencia(), row.cliente())) {
            return false;
        }

        // Estado filter
        if (!FilterSupport.matchesExact(row.estadoFinal(), filters.getEstado())) {
            return false;
        }

        // Sucursal filter
        if (!FilterSupport.matchesExact(row.sucursal(), filters.getSucursal())) {
            return false;
        }

        // Date range on fecha
        if (!FilterSupport.inRange(row.fecha(), filters.getDesde(), filters.getHasta())) {
            return false;
        }

        return true;
    }
}
