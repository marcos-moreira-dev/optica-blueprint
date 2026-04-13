package com.marcosmoreira.opticademo.modules.caja;

import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.shared.domain.caja.Cobro;
import com.marcosmoreira.opticademo.shared.domain.common.EstadoGeneral;
import com.marcosmoreira.opticademo.shared.domain.venta.VentaOptica;
import com.marcosmoreira.opticademo.shared.query.FilterSupport;
import com.marcosmoreira.opticademo.shared.query.PageRequest;
import com.marcosmoreira.opticademo.shared.query.PageResult;
import com.marcosmoreira.opticademo.shared.query.PaginationHelper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Facade that queries the DemoStore and returns data for the Caja module.
 * No business logic -- just view-facing data assembly.
 */
public class CajaFacade {

    private final DemoStore store;

    public CajaFacade(DemoStore store) {
        this.store = store;
    }

    // ------------------------------------------------------------------ Cobros (sub-view 1 & 6)

    /**
     * Returns a paginated, filtered list of cobro row models.
     */
    public PageResult<CajaRowModel.CobroRow> getCobros(CajaFilters filters, PageRequest pageRequest) {
        List<CajaRowModel.CobroRow> filtered = store.cobros.stream()
                .filter(c -> matchesCobroFilters(c, filters))
                .map(CajaRowModel.CobroRow::from)
                .collect(Collectors.toList());

        return PaginationHelper.page(filtered, pageRequest);
    }

    /**
     * Returns all cobros as a list (for historical view without pagination).
     */
    public List<CajaRowModel.CobroRow> getCobrosList(CajaFilters filters) {
        return store.cobros.stream()
                .filter(c -> matchesCobroFilters(c, filters))
                .map(CajaRowModel.CobroRow::from)
                .collect(Collectors.toList());
    }

    // ------------------------------------------------------------------ Saldos (sub-view 2)

    /**
     * Returns orders with pending balance.
     */
    public List<CajaRowModel.SaldoRow> getSaldoPendiente() {
        return store.ventas.stream()
                .filter(v -> v.getSaldo() > 0)
                .map(CajaRowModel.SaldoRow::from)
                .collect(Collectors.toList());
    }

    // ------------------------------------------------------------------ Comprobantes (sub-view 3)

    /**
     * Returns all receipts/invoices.
     */
    public List<CajaRowModel.ComprobanteRow> getComprobantes() {
        return store.cobros.stream()
                .filter(c -> c.getComprobante() != null && !c.getComprobante().isBlank())
                .map(CajaRowModel.ComprobanteRow::from)
                .collect(Collectors.toList());
    }

    // ------------------------------------------------------------------ Cierre de caja (sub-view 4)

    /**
     * Returns day summary statistics grouped by payment method.
     */
    public CierreDiaSummary getCierreDia() {
        List<Cobro> cobrosHoy = store.cobros;

        int totalCobros = cobrosHoy.size();
        double totalDia = cobrosHoy.stream().mapToDouble(Cobro::getMonto).sum();

        Map<String, Double> porMetodo = new LinkedHashMap<>();
        for (Cobro c : cobrosHoy) {
            String metodo = c.getMetodoPago() != null ? c.getMetodoPago() : "SIN_METODO";
            porMetodo.merge(metodo, c.getMonto(), Double::sum);
        }

        Map<String, Integer> porSucursal = new LinkedHashMap<>();
        for (Cobro c : cobrosHoy) {
            String sucursal = c.getSucursal() != null ? c.getSucursal() : "Sin sucursal";
            porSucursal.merge(sucursal, 1, Integer::sum);
        }

        String fechaHoy = cobrosHoy.isEmpty() ? "Sin datos" :
                cobrosHoy.stream().map(Cobro::getFecha).filter(f -> f != null && !f.isBlank()).findFirst().orElse("Sin fecha");

        String sucursal = cobrosHoy.isEmpty() ? "Todas" :
                cobrosHoy.stream().map(Cobro::getSucursal).filter(s -> s != null && !s.isBlank()).findFirst().orElse("Todas");

        return new CierreDiaSummary(
                fechaHoy,
                sucursal,
                totalCobros,
                totalDia,
                porMetodo,
                porSucursal
        );
    }

    public record CierreDiaSummary(
            String fecha,
            String sucursal,
            int totalCobros,
            double totalDia,
            Map<String, Double> porMetodo,
            Map<String, Integer> porSucursal
    ) {
    }

    // ------------------------------------------------------------------ Pagos pendientes (sub-view 5)

    /**
     * Returns orders with pending payments.
     */
    public List<CajaRowModel.PendienteRow> getPagosPendientes() {
        return store.ventas.stream()
                .filter(v -> v.getSaldo() > 0 || v.getEstado() == EstadoGeneral.EN_PROCESO || v.getEstado() == EstadoGeneral.POR_COBRAR)
                .map(CajaRowModel.PendienteRow::from)
                .collect(Collectors.toList());
    }

    // ------------------------------------------------------------------ Historico (sub-view 6)

    /**
     * Returns historical cobros with extended filters.
     */
    public List<CajaRowModel.CobroRow> getHistorico(CajaFilters filters) {
        return getCobrosList(filters);
    }

    // ------------------------------------------------------------------ Summary

    /**
     * Builds a payment summary model for the selected order.
     */
    public CajaSummaryModel buildSummary(VentaOptica venta) {
        if (venta == null) {
            return CajaSummaryModel.demoSeed();
        }
        return CajaSummaryModel.from(venta);
    }

    // ------------------------------------------------------------------ Filter combos

    /**
     * Returns all distinct estado values for cobros.
     */
    public List<String> getEstadosCobro() {
        return store.cobros.stream()
                .map(c -> c.getEstado() != null ? c.getEstado().name() : "SIN_ESTADO")
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Returns all distinct payment method values.
     */
    public List<String> getMetodosPago() {
        return store.cobros.stream()
                .map(c -> c.getMetodoPago() != null ? c.getMetodoPago() : "SIN_METODO")
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    // ------------------------------------------------------------------ Filter matching

    private boolean matchesCobroFilters(Cobro cobro, CajaFilters filters) {
        // Search text: matches cliente, orden, comprobante
        if (!FilterSupport.matchesText(filters.getSearchText(),
                cobro.getClienteNombre(),
                cobro.getOrdenId(),
                cobro.getComprobante())) {
            return false;
        }

        // Estado filter
        String estadoStr = cobro.getEstado() != null ? cobro.getEstado().name() : "";
        if (!FilterSupport.matchesExact(estadoStr, filters.getEstado())) {
            return false;
        }

        // Metodo pago filter
        if (!FilterSupport.matchesExact(cobro.getMetodoPago(), filters.getMetodoPago())) {
            return false;
        }

        // Sucursal filter
        if (!FilterSupport.matchesExact(cobro.getSucursal(), filters.getSucursal())) {
            return false;
        }

        // Date range
        if (!FilterSupport.inRange(cobro.getFecha(), filters.getDesde(), filters.getHasta())) {
            return false;
        }

        // Solo pendientes: only include non-PAGADO
        if (filters.isSoloPendientes() && cobro.getEstado() == EstadoGeneral.PAGADO) {
            return false;
        }

        return true;
    }

    /**
     * Finds a VentaOptica by its referencia (e.g. "OV-124").
     */
    public VentaOptica findVentaByReferencia(String referencia) {
        return store.ventas.stream()
                .filter(v -> referencia != null && referencia.equals(v.getReferencia()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Finds a VentaOptica by client name (partial match).
     */
    public VentaOptica findVentaByCliente(String nombreCliente) {
        return store.ventas.stream()
                .filter(v -> nombreCliente != null && v.getClienteNombre() != null &&
                        v.getClienteNombre().contains(nombreCliente))
                .findFirst()
                .orElse(null);
    }
}
