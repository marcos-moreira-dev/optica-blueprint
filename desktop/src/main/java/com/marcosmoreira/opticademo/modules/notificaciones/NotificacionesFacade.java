package com.marcosmoreira.opticademo.modules.notificaciones;

import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.shared.domain.notificacion.Notificacion;
import com.marcosmoreira.opticademo.shared.query.FilterSupport;
import com.marcosmoreira.opticademo.shared.query.PageRequest;
import com.marcosmoreira.opticademo.shared.query.PageResult;
import com.marcosmoreira.opticademo.shared.query.PaginationHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Facade para el modulo de Notificaciones (gestion de comunicaciones).
 * <p>
 * Este facade actua como capa de abstraccion entre el {@link DemoStore} (coleccion
 * de {@link Notificacion}) y las sub-vistas del modulo de Notificaciones. Proporciona
 * datos de la bandeja general, notificaciones al cliente, notificaciones internas,
 * plantillas, historial de envios y alertas criticas.
 * </p>
 * <p>
 * Sub-vistas que alimenta:
 * <ul>
 *   <li><b>Bandeja General:</b> tabla paginada de todas las notificaciones.</li>
 *   <li><b>Notificaciones al Cliente:</b> comunicaciones enviadas a clientes.</li>
 *   <li><b>Notificaciones Internas:</b> alertas operativas entre modulos.</li>
 *   <li><b>Campanas y Plantillas:</b> plantillas de mensajes configuradas.</li>
 *   <li><b>Historial de Envios:</b> registro de envios y respuestas.</li>
 *   <li><b>Alertas Criticas:</b> alertas de alta prioridad activas.</li>
 * </ul>
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see DemoStore
 * @see Notificacion
 * @see FilterSupport
 * @see PaginationHelper
 */
public class NotificacionesFacade {

    private final DemoStore store;

    /**
     * Construye el facade con referencia al almacén de datos de demostración.
     *
     * @param store instancia del {@link DemoStore}
     */
    public NotificacionesFacade(DemoStore store) {
        this.store = store;
    }

    // ---- Bandeja general ----

    /**
     * Retorna una pagina de la bandeja general de notificaciones filtrada
     * segun los criterios de busqueda.
     *
     * @param filters     criterios de filtrado
     * @param pageRequest solicitud de paginacion
     * @return {@link PageResult} con la pagina de {@link NotificacionesRowModel.BandejaRow}
     */
    public PageResult<NotificacionesRowModel.BandejaRow> getBandeja(NotificacionesFilters filters, PageRequest pageRequest) {
        List<NotificacionesRowModel.BandejaRow> filtered = store.notificaciones.stream()
                .filter(n -> matchesBandejaFilters(n, filters))
                .map(n -> new NotificacionesRowModel.BandejaRow(
                        n.getReferencia(),
                        n.getTipo(),
                        n.getClienteNombre(),
                        n.getCanal(),
                        n.getEstado() != null ? n.getEstado().name() : "PENDIENTE",
                        n.getPrioridad() != null ? n.getPrioridad().name() : "MEDIA",
                        n.getFechaHora(),
                        n.getModuloOrigen()
                ))
                .collect(Collectors.toList());

        return PaginationHelper.page(filtered, pageRequest);
    }

    // ---- Notificaciones al cliente ----

    public List<NotificacionesRowModel.NotifClienteRow> getNotifCliente() {
        List<NotificacionesRowModel.NotifClienteRow> rows = new ArrayList<>();
        rows.add(new NotificacionesRowModel.NotifClienteRow(
                "Sofia Ramirez", "Trabajo listo", "WhatsApp", "Enviada", "16/04/2026", "Media"));
        rows.add(new NotificacionesRowModel.NotifClienteRow(
                "Ana Vera", "Recall", "SMS", "Pendiente", "17/04/2026", "Media"));
        rows.add(new NotificacionesRowModel.NotifClienteRow(
                "Carlos Mendoza", "Saldo pendiente", "Llamada", "Sin respuesta", "15/04/2026", "Alta"));
        return rows;
    }

    // ---- Notificaciones operativas internas ----

    public List<NotificacionesRowModel.NotifInternaRow> getNotifInterna() {
        List<NotificacionesRowModel.NotifInternaRow> rows = new ArrayList<>();
        rows.add(new NotificacionesRowModel.NotifInternaRow(
                "NI-001", "Stock critico", "Inventario", "Pendiente", "Alta", "17/04/2026"));
        rows.add(new NotificacionesRowModel.NotifInternaRow(
                "NI-002", "Orden urgente", "Taller", "En proceso", "Media", "16/04/2026"));
        rows.add(new NotificacionesRowModel.NotifInternaRow(
                "NI-003", "Cierre de caja", "Caja", "Cerrado", "Baja", "15/04/2026"));
        return rows;
    }

    // ---- Campanas y plantillas ----

    public List<NotificacionesRowModel.PlantillaRow> getPlantillas() {
        List<NotificacionesRowModel.PlantillaRow> rows = new ArrayList<>();
        rows.add(new NotificacionesRowModel.PlantillaRow(
                "Trabajo listo", "Notificacion de entrega", "WhatsApp", "Activa"));
        rows.add(new NotificacionesRowModel.PlantillaRow(
                "Recall anual", "Recordatorio de revision", "SMS", "Activa"));
        rows.add(new NotificacionesRowModel.PlantillaRow(
                "Saldo pendiente", "Aviso de cobro", "Llamada", "Activa"));
        return rows;
    }

    // ---- Historial de envios y respuestas ----

    public List<NotificacionesRowModel.HistorialEnvioRow> getHistorialEnvios() {
        List<NotificacionesRowModel.HistorialEnvioRow> rows = new ArrayList<>();
        rows.add(new NotificacionesRowModel.HistorialEnvioRow(
                "16/04/2026", "NT-001", "Sofia Ramirez", "WhatsApp", "Enviada", "Entregada"));
        rows.add(new NotificacionesRowModel.HistorialEnvioRow(
                "17/04/2026", "NT-002", "Ana Vera", "SMS", "Pendiente", "-"));
        rows.add(new NotificacionesRowModel.HistorialEnvioRow(
                "15/04/2026", "NT-004", "Carlos Mendoza", "Llamada", "Sin respuesta", "-"));
        rows.add(new NotificacionesRowModel.HistorialEnvioRow(
                "14/04/2026", "NT-005", "Luis Torres", "Email", "Enviada", "Leida"));
        return rows;
    }

    // ---- Alertas criticas ----

    public List<NotificacionesRowModel.AlertaCriticaRow> getAlertasCriticas() {
        List<NotificacionesRowModel.AlertaCriticaRow> rows = new ArrayList<>();
        rows.add(new NotificacionesRowModel.AlertaCriticaRow(
                "AC-001", "Stock critico", "MZ-201", "Alta", "Activa", "Reabastecer urgente"));
        rows.add(new NotificacionesRowModel.AlertaCriticaRow(
                "AC-002", "Pago vencido", "V-0034", "Alta", "Pendiente", "Contactar cliente"));
        return rows;
    }

    // ---- Summary builder ----

    /**
     * Construye un modelo de resumen para la notificacion seleccionada.
     *
     * @param notif entidad {@link Notificacion} seleccionada
     * @return {@link NotificacionesSummaryModel} con datos de la notificacion, o vacio si es nula
     */
    public NotificacionesSummaryModel buildSummary(Notificacion notif) {
        if (notif == null) return NotificacionesSummaryModel.empty();
        return new NotificacionesSummaryModel(
                notif.getReferencia(),
                notif.getTipo(),
                notif.getCanal(),
                notif.getClienteNombre(),
                notif.getOrdenRelacionada(),
                notif.getSucursal(),
                notif.getEstado() != null ? notif.getEstado().name() : "PENDIENTE",
                notif.getFechaHora(),
                notif.getModuloOrigen(),
                notif.getObservacion() != null ? notif.getObservacion() : "Revisar detalle",
                notif.getObservacion() != null ? notif.getObservacion() : ""
        );
    }

    // ---- Filter options ----

    public List<String> getTipos() {
        return store.notificaciones.stream()
                .map(Notificacion::getTipo)
                .filter(t -> t != null && !t.isBlank())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> getCanales() {
        return store.notificaciones.stream()
                .map(Notificacion::getCanal)
                .filter(c -> c != null && !c.isBlank())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> getEstados() {
        return store.notificaciones.stream()
                .map(n -> n.getEstado() != null ? n.getEstado().name() : "PENDIENTE")
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> getPrioridades() {
        return store.notificaciones.stream()
                .map(n -> n.getPrioridad() != null ? n.getPrioridad().name() : "MEDIA")
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> getSucursales() {
        return store.sucursales.stream()
                .map(s -> s.getNombre())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    // ---- Private helpers ----

    private boolean matchesBandejaFilters(Notificacion notif, NotificacionesFilters filters) {
        if (!FilterSupport.matchesText(filters.getSearchText(),
                notif.getReferencia(),
                notif.getTipo(),
                notif.getClienteNombre(),
                notif.getCanal())) {
            return false;
        }

        String estadoStr = notif.getEstado() != null ? notif.getEstado().name() : "PENDIENTE";
        if (!FilterSupport.matchesExact(estadoStr, filters.getEstado())) {
            return false;
        }

        if (!FilterSupport.matchesExact(notif.getTipo(), filters.getTipo())) {
            return false;
        }

        if (!FilterSupport.matchesExact(notif.getCanal(), filters.getCanal())) {
            return false;
        }

        String prioridadStr = notif.getPrioridad() != null ? notif.getPrioridad().name() : "MEDIA";
        if (!FilterSupport.matchesExact(prioridadStr, filters.getPrioridad())) {
            return false;
        }

        if (!FilterSupport.matchesExact(notif.getSucursal(), filters.getSucursal())) {
            return false;
        }

        if (filters.isSoloPendientesYCriticas()) {
            boolean isPendiente = "PENDIENTE".equals(estadoStr);
            boolean isCritica = "ALTA".equals(prioridadStr);
            if (!isPendiente && !isCritica) {
                return false;
            }
        }

        if (!FilterSupport.inRange(notif.getFechaHora(), filters.getDesde(), filters.getHasta())) {
            return false;
        }

        return true;
    }
}
