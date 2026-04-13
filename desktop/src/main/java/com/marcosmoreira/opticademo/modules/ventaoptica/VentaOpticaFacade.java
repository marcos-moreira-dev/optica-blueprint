package com.marcosmoreira.opticademo.modules.ventaoptica;

import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.shared.domain.cliente.Cliente;
import com.marcosmoreira.opticademo.shared.domain.venta.VentaOptica;
import com.marcosmoreira.opticademo.demo.seed.SharedSeedSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Facade para el modulo de Venta Optica (wizard de venta).
 * <p>
 * Este facade gestiona el flujo de datos del wizard de venta optica, un proceso
 * multi-etapa que abarca desde la busqueda del cliente hasta la confirmacion
 * de la orden. Los datos de monturas y lentes provienen de seed data estatico,
 * mientras que las ventas historicas se obtienen del {@link DemoStore}.
 * </p>
 * <p>
 * Etapas del wizard que alimenta:
 * <ul>
 *   <li><b>Etapa 1 - Busqueda de Cliente:</b> busqueda por nombre, codigo o telefono.</li>
 *   <li><b>Etapa 2 - Seleccion de Receta:</b> recetas disponibles para el cliente.</li>
 *   <li><b>Etapa 3 - Catalogo de Monturas:</b> monturas filtrables por marca y material.</li>
 *   <li><b>Etapa 4 - Lentes Disponibles:</b> tipos de lente desde {@link SharedSeedSupport}.</li>
 *   <li><b>Etapa 7 - Ventas Historicas:</b> historial de ventas del cliente.</li>
 *   <li><b>Resumen de Orden:</b> modelo consolidado de la orden en construccion.</li>
 * </ul>
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see DemoStore
 * @see SharedSeedSupport
 * @see VentaOptica
 */
public class VentaOpticaFacade {

    private final DemoStore store;

    /**
     * Construye el facade con referencia al almacén de datos de demostración.
     *
     * @param store instancia del {@link DemoStore}
     */
    public VentaOpticaFacade(DemoStore store) {
        this.store = store;
    }

    // ---- Stage 1: Client search ----

    /**
     * Searches clients matching the given text (nombre, codigo, telefono).
     */
    public List<VentaOpticaRowModel.ClientSearchRow> searchClientes(String searchText) {
        return store.clientes.stream()
                .filter(c -> matchesText(searchText,
                        c.getNombreCompleto(),
                        c.getCodigoInterno(),
                        c.getTelefono()))
                .map(c -> new VentaOpticaRowModel.ClientSearchRow(
                        c.getNombreCompleto(),
                        c.getCodigoInterno(),
                        c.getTelefono(),
                        c.getUltimaVisita(),
                        c.getEstado() != null ? c.getEstado().name() : "ACTIVO",
                        c.getSucursalHabitual()))
                .collect(Collectors.toList());
    }

    // ---- Stage 2: Recipe selection ----

    /**
     * Returns available recipes for a given client.
     */
    public List<VentaOpticaRowModel.RecipeRow> getRecetasForCliente(String clienteId) {
        Cliente cliente = store.clientes.stream()
                .filter(c -> c.getId().equals(clienteId))
                .findFirst()
                .orElse(null);

        if (cliente == null || "SIN_RECETA".equals(cliente.getEstadoReceta())) {
            return List.of();
        }

        // Demo: return sample recipe data based on client
        List<VentaOpticaRowModel.RecipeRow> recetas = new ArrayList<>();
        String estado = cliente.getEstadoReceta() != null ? cliente.getEstadoReceta() : "VIGENTE";
        recetas.add(new VentaOpticaRowModel.RecipeRow(
                cliente.getUltimaReceta() != null ? cliente.getUltimaReceta() : "2026-01-15",
                estado,
                "Dr. Andres Villavicencio",
                "OD: Esf -2.00 / Cil -0.75 / Eje 180",
                "OI: Esf -1.75 / Cil -0.50 / Eje 175",
                "62"));
        return recetas;
    }

    // ---- Stage 3: Montura catalog ----

    /**
     * Returns monturas matching the given filters.
     */
    public List<VentaOpticaRowModel.MonturaRow> getMonturas(VentaOpticaFilters filters) {
        List<VentaOpticaRowModel.MonturaRow> result = new ArrayList<>();

        // Demo seed data for monturas catalog
        result.add(new VentaOpticaRowModel.MonturaRow(
                "MZ-201", "Ray-Ban RB5154 Negro", "Ray-Ban", "Negro", "$185.00", "15", "Matriz Centro"));
        result.add(new VentaOpticaRowModel.MonturaRow(
                "MZ-202", "Oakley OX8091 Mate", "Oakley", "Mate", "$220.00", "8", "Norte Mall"));
        result.add(new VentaOpticaRowModel.MonturaRow(
                "MZ-203", "Vogue VO2876 Rosa", "Vogue", "Rosa", "$145.00", "12", "Matriz Centro"));
        result.add(new VentaOpticaRowModel.MonturaRow(
                "MZ-204", "Carey Clasico Acetato", "Arnette", "Carey", "$165.00", "6", "Matriz Centro"));
        result.add(new VentaOpticaRowModel.MonturaRow(
                "MZ-205", "Titanio Premium Gris", "Ray-Ban", "Gris", "$310.00", "10", "Norte Mall"));

        // Apply filters
        return result.stream()
                .filter(m -> matchesText(filters.getMonturaMarca(), m.marca()))
                .filter(m -> "Todos".equals(filters.getMonturaMaterial()) || matchesMaterial(m, filters.getMonturaMaterial()))
                .collect(Collectors.toList());
    }

    // ---- Stage 4: Lentes disponibles ----

    /**
     * Returns available lens options.
     */
    public List<String> getLentesDisponibles() {
        return new ArrayList<>(SharedSeedSupport.LENTE_TYPES);
    }

    // ---- Helper: Marcas montura ----

    /**
     * Returns distinct montura brands for filter combo.
     */
    public List<String> getMarcasMontura() {
        return List.of("Ray-Ban", "Oakley", "Vogue", "Arnette", "Todas");
    }

    // ---- Stage 7: Historical sales ----

    /**
     * Returns historical sales records.
     */
    public List<VentaOpticaRowModel.HistoricRow> getVentasHistoricas() {
        return store.ventas.stream()
                .map(v -> new VentaOpticaRowModel.HistoricRow(
                        v.getFechaOrden(),
                        v.getReferencia(),
                        v.getClienteNombre(),
                        String.format("$%.2f", v.getPrecioMontura() + v.getPrecioLente()),
                        "EFECTIVO",
                        v.getEstado() != null ? v.getEstado().name() : "EN_PROCESO",
                        "FAC-000001"))
                .collect(Collectors.toList());
    }

    // ---- Order summary ----

    /**
     * Construye el resumen de la orden actual con todos los parametros
     * seleccionados en el wizard: cliente, receta, montura, lente, precios
     * y fecha estimada de entrega. Calcula automaticamente el saldo pendiente.
     *
     * @param cliente         nombre del cliente
     * @param codigo          codigo del cliente
     * @param receta          fecha de la receta seleccionada
     * @param montura         descripcion de la montura
     * @param lente           tipo de lente
     * @param pd              distancia pupilar
     * @param subtotal        subtotal de la orden
     * @param descuento       monto de descuento aplicado
     * @param abono           monto del abono inicial
     * @param entregaEstimada fecha estimada de entrega
     * @param laboratorio     laboratorio asignado
     * @return {@link VentaOpticaSummaryModel} con datos consolidados de la orden
     */
    public VentaOpticaSummaryModel buildOrderSummary(
            String cliente, String codigo, String receta, String montura,
            String lente, String pd, double subtotal, double descuento,
            double abono, String entregaEstimada, String laboratorio) {

        double saldo = subtotal - descuento - abono;
        return new VentaOpticaSummaryModel(
                cliente,
                codigo,
                receta,
                montura,
                lente,
                pd,
                String.format("$%.2f", subtotal),
                String.format("$%.2f", descuento),
                String.format("$%.2f", abono),
                String.format("$%.2f", Math.max(0, saldo)),
                "EN_PROCESO",
                entregaEstimada,
                laboratorio
        );
    }

    // ---- Private helpers ----

    private boolean matchesText(String search, String... fields) {
        if (search == null || search.isBlank()) {
            return true;
        }
        String lower = search.toLowerCase();
        for (String field : fields) {
            if (field != null && field.toLowerCase().contains(lower)) {
                return true;
            }
        }
        return false;
    }

    private boolean matchesMaterial(VentaOpticaRowModel.MonturaRow montura, String material) {
        // Simple heuristic based on name
        String nombre = montura.nombre().toLowerCase();
        return switch (material) {
            case "Acetato" -> nombre.contains("acetato") || nombre.contains("carey");
            case "Metal" -> nombre.contains("titanio") || nombre.contains("metal");
            case "Policarbonato" -> nombre.contains("policarbonato");
            default -> true;
        };
    }
}
