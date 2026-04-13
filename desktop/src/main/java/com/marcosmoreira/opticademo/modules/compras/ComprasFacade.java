package com.marcosmoreira.opticademo.modules.compras;

import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.demo.generator.DateGenerator;
import com.marcosmoreira.opticademo.shared.query.FilterSupport;
import com.marcosmoreira.opticademo.shared.util.MoneyUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Facade that queries the DemoStore and returns data for the Compras module.
 * No business logic -- just view-facing data assembly.
 */
public class ComprasFacade {

    private final DemoStore store;
    private final DateGenerator dates = new DateGenerator();

    public ComprasFacade(DemoStore store) {
        this.store = store;
    }

    // ==================== Sub-view 1: Solicitudes de compra ====================

    public List<ComprasRowModel.SolicitudRow> getSolicitudes(ComprasFilters filters) {
        List<ComprasRowModel.SolicitudRow> rows = new ArrayList<>();

        rows.add(new ComprasRowModel.SolicitudRow(
                "SC-014",
                "Stock critico Monturas",
                "MONTURA",
                "Matriz Centro",
                "Alta",
                "Pendiente",
                "VisionLine Distribucion"
        ));
        rows.add(new ComprasRowModel.SolicitudRow(
                "SC-013",
                "Reposicion Lentes oftalmicos",
                "LENTE",
                "Norte Mall",
                "Media",
                "Aprobada",
                "LabVision Externo"
        ));
        rows.add(new ComprasRowModel.SolicitudRow(
                "SC-012",
                "Solicitud Accesorios",
                "ACCESORIO",
                "Matriz Centro",
                "Baja",
                "Completada",
                "Accesorios Visuales Import"
        ));
        rows.add(new ComprasRowModel.SolicitudRow(
                "SC-011",
                "Lentes de contacto",
                "LENTE_CONTACTO",
                "Matriz Centro",
                "Media",
                "En proceso",
                "CooperVision"
        ));

        return rows.stream()
                .filter(r -> matchesSolicitudFilters(r, filters))
                .collect(Collectors.toList());
    }

    private boolean matchesSolicitudFilters(ComprasRowModel.SolicitudRow r, ComprasFilters filters) {
        if (!FilterSupport.matchesText(filters.getSearchText(),
                r.solicitud(), r.motivo(), r.categoria(), r.proveedorSugerido())) {
            return false;
        }
        if (!FilterSupport.matchesExact(r.estado(), filters.getEstado())) {
            return false;
        }
        if (!FilterSupport.matchesExact(r.proveedorSugerido(), filters.getProveedor())) {
            return false;
        }
        if (!FilterSupport.matchesExact(r.categoria(), filters.getCategoria())) {
            return false;
        }
        if (!FilterSupport.matchesExact(r.sucursalDestino(), filters.getSucursal())) {
            return false;
        }
        if (filters.isSoloPendientesCriticos() && !"Alta".equals(r.prioridad())) {
            return false;
        }
        return true;
    }

    // ==================== Sub-view 2: Ordenes de compra ====================

    public List<ComprasRowModel.OrdenCompraRow> getOrdenesCompra(ComprasFilters filters) {
        List<ComprasRowModel.OrdenCompraRow> rows = new ArrayList<>();

        rows.add(new ComprasRowModel.OrdenCompraRow(
                "OC-082",
                "VisionLine Distribucion",
                dates.pastDate(2),
                "Matriz Centro",
                "Enviada",
                "12 items",
                MoneyUtils.format(1280.00)
        ));
        rows.add(new ComprasRowModel.OrdenCompraRow(
                "OC-078",
                "LabVision Externo",
                dates.pastDate(10),
                "Norte Mall",
                "Completada",
                "8 items",
                MoneyUtils.format(960.00)
        ));
        rows.add(new ComprasRowModel.OrdenCompraRow(
                "OC-071",
                "NovaFrame Import",
                dates.pastDate(20),
                "Matriz Centro",
                "Completada",
                "15 items",
                MoneyUtils.format(2150.00)
        ));
        rows.add(new ComprasRowModel.OrdenCompraRow(
                "OC-065",
                "ABB Optical",
                dates.pastDate(30),
                "Norte Mall",
                "Completada",
                "6 items",
                MoneyUtils.format(540.00)
        ));

        return rows.stream()
                .filter(r -> matchesOrdenFilters(r, filters))
                .collect(Collectors.toList());
    }

    private boolean matchesOrdenFilters(ComprasRowModel.OrdenCompraRow r, ComprasFilters filters) {
        if (!FilterSupport.matchesText(filters.getSearchText(),
                r.orden(), r.proveedor())) {
            return false;
        }
        if (!FilterSupport.matchesExact(r.estado(), filters.getEstado())) {
            return false;
        }
        if (!FilterSupport.matchesExact(r.proveedor(), filters.getProveedor())) {
            return false;
        }
        if (!FilterSupport.matchesExact(r.sucursalDestino(), filters.getSucursal())) {
            return false;
        }
        return true;
    }

    // ==================== Sub-view 3: Compras por proveedor ====================

    public List<ComprasRowModel.ProveedorCompraRow> getComprasPorProveedor() {
        List<ComprasRowModel.ProveedorCompraRow> rows = new ArrayList<>();

        rows.add(new ComprasRowModel.ProveedorCompraRow(
                "VisionLine Distribucion",
                "2 abiertas",
                dates.pastDate(2),
                "Monturas",
                "Activo"
        ));
        rows.add(new ComprasRowModel.ProveedorCompraRow(
                "LabVision Externo",
                "1 abierta",
                dates.pastDate(10),
                "Lentes",
                "Activo"
        ));
        rows.add(new ComprasRowModel.ProveedorCompraRow(
                "NovaFrame Import",
                "0 abiertas",
                dates.pastDate(20),
                "Monturas",
                "Activo"
        ));
        rows.add(new ComprasRowModel.ProveedorCompraRow(
                "ABB Optical",
                "0 abiertas",
                dates.pastDate(30),
                "Lentes contacto",
                "Activo"
        ));
        rows.add(new ComprasRowModel.ProveedorCompraRow(
                "CooperVision",
                "1 abierta",
                dates.pastDate(15),
                "Lentes contacto",
                "Bajo observacion"
        ));

        return rows;
    }

    // ==================== Sub-view 4: Back-orders y pendientes ====================

    public List<ComprasRowModel.BackOrderRow> getBackOrders() {
        List<ComprasRowModel.BackOrderRow> rows = new ArrayList<>();

        rows.add(new ComprasRowModel.BackOrderRow(
                "BO-009",
                "OC-082",
                "LabVision Externo",
                "Lente monofocal 1.50",
                "2 unidades",
                dates.futureDate(6),
                "Back-order"
        ));
        rows.add(new ComprasRowModel.BackOrderRow(
                "BO-008",
                "OC-078",
                "VisionLine Distribucion",
                "Montura acetato negro",
                "1 unidad",
                dates.futureDate(3),
                "En transito"
        ));
        rows.add(new ComprasRowModel.BackOrderRow(
                "BO-007",
                "OC-071",
                "NovaFrame Import",
                "Montura deportiva titanio",
                "3 unidades",
                dates.futureDate(12),
                "Pendiente"
        ));

        return rows;
    }

    // ==================== Sub-view 5: Recepcion vinculada ====================

    public List<ComprasRowModel.RecepcionCompraRow> getRecepcionesVinculadas() {
        List<ComprasRowModel.RecepcionCompraRow> rows = new ArrayList<>();

        rows.add(new ComprasRowModel.RecepcionCompraRow(
                "RC-038",
                "OC-082",
                dates.pastDate(1),
                "Parcial",
                "2 refs pendientes",
                "Carlos Zambrano"
        ));
        rows.add(new ComprasRowModel.RecepcionCompraRow(
                "RC-035",
                "OC-078",
                dates.pastDate(10),
                "Completada",
                "Sin diferencias",
                "Laura Escobar"
        ));
        rows.add(new ComprasRowModel.RecepcionCompraRow(
                "RC-030",
                "OC-071",
                dates.pastDate(20),
                "Completada",
                "1 item diferido",
                "Patricia Mendoza"
        ));
        rows.add(new ComprasRowModel.RecepcionCompraRow(
                "RC-025",
                "OC-065",
                dates.pastDate(30),
                "Completada",
                "Sin diferencias",
                "Carlos Zambrano"
        ));

        return rows;
    }

    // ==================== Sub-view 6: Compras por sucursal ====================

    public List<ComprasRowModel.SucursalCompraRow> getComprasPorSucursal() {
        List<ComprasRowModel.SucursalCompraRow> rows = new ArrayList<>();

        rows.add(new ComprasRowModel.SucursalCompraRow(
                "Matriz Centro",
                "3 solicitudes",
                "2 ordenes",
                "4 pendientes",
                MoneyUtils.format(3430.00),
                "Normal"
        ));
        rows.add(new ComprasRowModel.SucursalCompraRow(
                "Norte Mall",
                "1 solicitud",
                "1 orden",
                "2 pendientes",
                MoneyUtils.format(1500.00),
                "Normal"
        ));
        rows.add(new ComprasRowModel.SucursalCompraRow(
                "Plaza Sur",
                "0 solicitudes",
                "0 ordenes",
                "0 pendientes",
                MoneyUtils.format(0.00),
                "Sin actividad"
        ));

        return rows;
    }

    // ==================== Sub-view 7: Historico ====================

    public List<ComprasRowModel.HistoricoCompraRow> getHistorico(ComprasFilters filters) {
        List<ComprasRowModel.HistoricoCompraRow> rows = new ArrayList<>();

        rows.add(new ComprasRowModel.HistoricoCompraRow(
                dates.pastDate(1),
                "RC-038",
                "Recepcion",
                "VisionLine Distribucion",
                "Parcial",
                "2 refs pendientes de entrega"
        ));
        rows.add(new ComprasRowModel.HistoricoCompraRow(
                dates.pastDate(2),
                "OC-082",
                "Orden de compra",
                "VisionLine Distribucion",
                "Enviada",
                "Orden en transito"
        ));
        rows.add(new ComprasRowModel.HistoricoCompraRow(
                dates.pastDate(10),
                "RC-035",
                "Recepcion",
                "LabVision Externo",
                "Completada",
                "Entrega sin novedades"
        ));
        rows.add(new ComprasRowModel.HistoricoCompraRow(
                dates.pastDate(15),
                "SC-011",
                "Solicitud",
                "CooperVision",
                "En proceso",
                "Solicitud aprobada por gerente"
        ));
        rows.add(new ComprasRowModel.HistoricoCompraRow(
                dates.pastDate(20),
                "RC-030",
                "Recepcion",
                "NovaFrame Import",
                "Completada",
                "1 item diferido a proxima entrega"
        ));
        rows.add(new ComprasRowModel.HistoricoCompraRow(
                dates.pastDate(30),
                "RC-025",
                "Recepcion",
                "ABB Optical",
                "Completada",
                "Recepcion estandar"
        ));

        return rows.stream()
                .filter(r -> matchesHistoricoFilters(r, filters))
                .collect(Collectors.toList());
    }

    private boolean matchesHistoricoFilters(ComprasRowModel.HistoricoCompraRow r, ComprasFilters filters) {
        if (!FilterSupport.matchesText(filters.getSearchText(),
                r.referencia(), r.tipoRegistro(), r.proveedor())) {
            return false;
        }
        if (!FilterSupport.matchesExact(r.proveedor(), filters.getProveedor())) {
            return false;
        }
        if (!FilterSupport.matchesExact(r.estado(), filters.getEstado())) {
            return false;
        }
        return true;
    }

    // ==================== Summary and lookup helpers ====================

    public ComprasSummaryModel buildSummary(ComprasRowModel.OrdenCompraRow orden) {
        return ComprasSummaryModel.fromOrden(orden);
    }

    public ComprasSummaryModel buildSummary(ComprasRowModel.SolicitudRow solicitud) {
        return ComprasSummaryModel.fromSolicitud(solicitud);
    }

    public List<String> getEstados() {
        return List.of("Pendiente", "Aprobada", "En proceso", "Enviada", "Completada", "Cancelada");
    }

    public List<String> getProveedores() {
        return store.proveedores.stream()
                .map(p -> p.getNombreComercial())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> getCategorias() {
        return List.of("MONTURA", "LENTE", "LENTE_CONTACTO", "ACCESORIO");
    }

    public List<String> getSucursales() {
        return store.sucursales.stream()
                .map(s -> s.getNombre())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}
