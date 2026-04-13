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
 * Facade para el modulo de Caja (gestion de cobros y pagos).
 * <p>
 * Este facade actua como capa de abstraccion entre el {@link DemoStore} (colecciones
 * de {@link Cobro} y {@link VentaOptica}) y las sub-vistas del modulo de Caja.
 * Proporciona datos de cobros, saldos pendientes, comprobantes, cierre de caja
 * y pagos pendientes, todo sin logica de negocio.
 * </p>
 * <p>
 * Sub-vistas que alimenta:
 * <ul>
 *   <li><b>Cobros:</b> tabla paginada de cobros registrados.</li>
 *   <li><b>Saldos Pendientes:</b> ordenes con balance pendiente.</li>
 *   <li><b>Comprobantes:</b> listado de facturas/recibos emitidos.</li>
 *   <li><b>Cierre de Caja:</b> resumen diario agrupado por metodo de pago y sucursal.</li>
 *   <li><b>Pagos Pendientes:</b> ordenes con cobros pendientes o en proceso.</li>
 *   <li><b>Historico:</b> historial completo de cobros con filtros extendidos.</li>
 * </ul>
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see DemoStore
 * @see Cobro
 * @see VentaOptica
 * @see FilterSupport
 * @see PaginationHelper
 */
public class CajaFacade {

    private final DemoStore store;

    /**
     * Construye el facade con referencia al almacén de datos de demostración.
     *
     * @param store instancia del {@link DemoStore}
     */
    public CajaFacade(DemoStore store) {
        this.store = store;
    }

    // ------------------------------------------------------------------ Cobros (sub-view 1 & 6)

    /**
     * Retorna una pagina de cobros filtrada segun los criterios de busqueda.
     *
     * @param filters     criterios de filtrado
     * @param pageRequest solicitud de paginacion
     * @return {@link PageResult} con la pagina de {@link CajaRowModel.CobroRow}
     */
    public PageResult<CajaRowModel.CobroRow> getCobros(CajaFilters filters, PageRequest pageRequest) {
        List<CajaRowModel.CobroRow> filtered = store.cobros.stream()
                .filter(c -> matchesCobroFilters(c, filters))
                .map(CajaRowModel.CobroRow::from)
                .collect(Collectors.toList());

        return PaginationHelper.page(filtered, pageRequest);
    }

    /**
     * Retorna todos los cobros como lista (para vista historica sin paginacion).
     *
     * @param filters criterios de filtrado
     * @return lista de {@link CajaRowModel.CobroRow} filtrados
     */
    public List<CajaRowModel.CobroRow> getCobrosList(CajaFilters filters) {
        return store.cobros.stream()
                .filter(c -> matchesCobroFilters(c, filters))
                .map(CajaRowModel.CobroRow::from)
                .collect(Collectors.toList());
    }

    // ------------------------------------------------------------------ Saldos (sub-view 2)

    /**
     * Retorna las ordenes con saldo pendiente de pago.
     *
     * @return lista de {@link CajaRowModel.SaldoRow} con saldos pendientes
     */
    public List<CajaRowModel.SaldoRow> getSaldoPendiente() {
        return store.ventas.stream()
                .filter(v -> v.getSaldo() > 0)
                .map(CajaRowModel.SaldoRow::from)
                .collect(Collectors.toList());
    }

    // ------------------------------------------------------------------ Comprobantes (sub-view 3)

    /**
     * Retorna todos los comprobantes (facturas/recibos) emitidos.
     *
     * @return lista de {@link CajaRowModel.ComprobanteRow} con datos de comprobantes
     */
    public List<CajaRowModel.ComprobanteRow> getComprobantes() {
        return store.cobros.stream()
                .filter(c -> c.getComprobante() != null && !c.getComprobante().isBlank())
                .map(CajaRowModel.ComprobanteRow::from)
                .collect(Collectors.toList());
    }

    // ------------------------------------------------------------------ Cierre de caja (sub-view 4)

    /**
     * Retorna el resumen del dia agrupado por metodo de pago y sucursal,
     * con totales de cobros y monto acumulado.
     *
     * @return {@link CierreDiaSummary} con estadisticas del dia
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

    /**
     * Record que encapsula el resumen diario de caja.
     *
     * @param fecha        fecha del cierre
     * @param sucursal     sucursal del cierre
     * @param totalCobros  cantidad total de cobros del dia
     * @param totalDia     monto total cobrado en el dia
     * @param porMetodo    mapa de monto acumulado por metodo de pago
     * @param porSucursal  mapa de cantidad de cobros por sucursal
     */
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
     * Retorna las ordenes con pagos pendientes, en proceso o por cobrar.
     *
     * @return lista de {@link CajaRowModel.PendienteRow} con pagos pendientes
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
     * Construye un modelo de resumen de pago para la orden seleccionada.
     *
     * @param venta entidad {@link VentaOptica} seleccionada
     * @return {@link CajaSummaryModel} con datos de la venta, o demo seed si es nula
     */
    public CajaSummaryModel buildSummary(VentaOptica venta) {
        if (venta == null) {
            return CajaSummaryModel.demoSeed();
        }
        return CajaSummaryModel.from(venta);
    }

    // ------------------------------------------------------------------ Filter combos

    /**
     * Retorna los estados distinct de cobros para el combo de filtro.
     *
     * @return lista ordenada de estados de cobro
     */
    public List<String> getEstadosCobro() {
        return store.cobros.stream()
                .map(c -> c.getEstado() != null ? c.getEstado().name() : "SIN_ESTADO")
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Retorna los metodos de pago distinct para el combo de filtro.
     *
     * @return lista ordenada de metodos de pago
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
     * Busca una venta optica por su referencia (e.g. "OV-124").
     *
     * @param referencia referencia de la venta
     * @return {@link VentaOptica} encontrada o {@code null}
     */
    public VentaOptica findVentaByReferencia(String referencia) {
        return store.ventas.stream()
                .filter(v -> referencia != null && referencia.equals(v.getReferencia()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Busca una venta optica por nombre del cliente (coincidencia parcial).
     *
     * @param nombreCliente nombre del cliente a buscar
     * @return {@link VentaOptica} encontrada o {@code null}
     */
    public VentaOptica findVentaByCliente(String nombreCliente) {
        return store.ventas.stream()
                .filter(v -> nombreCliente != null && v.getClienteNombre() != null &&
                        v.getClienteNombre().contains(nombreCliente))
                .findFirst()
                .orElse(null);
    }
}
