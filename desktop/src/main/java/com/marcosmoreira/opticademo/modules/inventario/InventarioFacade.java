package com.marcosmoreira.opticademo.modules.inventario;

import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.demo.generator.DateGenerator;
import com.marcosmoreira.opticademo.shared.domain.producto.Producto;
import com.marcosmoreira.opticademo.shared.domain.common.EstadoGeneral;
import com.marcosmoreira.opticademo.shared.query.FilterSupport;
import com.marcosmoreira.opticademo.shared.query.PageRequest;
import com.marcosmoreira.opticademo.shared.query.PageResult;
import com.marcosmoreira.opticademo.shared.query.PaginationHelper;
import com.marcosmoreira.opticademo.shared.util.MoneyUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Facade para el modulo de Inventario (gestion de productos y stock).
 * <p>
 * Este facade actua como capa de abstraccion entre el {@link DemoStore} (colecciones
 * de {@link Producto} y proveedores) y las siete sub-vistas del modulo de Inventario.
 * Proporciona consultas paginadas, filtrado por categoria/marca/sucursal, y datos
 * de movimientos, stock critico, recepciones y analisis de rotacion.
 * </p>
 * <p>
 * Sub-vistas que alimenta:
 * <ul>
 *   <li><b>Catalogo General:</b> tabla paginada de todos los productos.</li>
 *   <li><b>Monturas:</b> vista especifica con material, color y precio.</li>
 *   <li><b>Lentes y Variantes:</b> tipo, indice, tratamiento y stock.</li>
 *   <li><b>Movimientos:</b> entradas, salidas, ajustes y transferencias.</li>
 *   <li><b>Stock Critico:</b> productos con stock bajo o agotado.</li>
 *   <li><b>Recepcion:</b> ordenes de abastecimiento y su estado.</li>
 *   <li><b>Historico y Analisis:</b> rotacion y observaciones de stock.</li>
 * </ul>
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see DemoStore
 * @see Producto
 * @see DateGenerator
 * @see MoneyUtils
 */
public class InventarioFacade {

    private final DemoStore store;
    private final DateGenerator dates = new DateGenerator();

    /**
     * Construye el facade con referencia al almacén de datos de demostración.
     *
     * @param store instancia del {@link DemoStore}
     */
    public InventarioFacade(DemoStore store) {
        this.store = store;
    }

    // ==================== Sub-view 1: Catalogo general ====================

    /**
     * Retorna una pagina del catalogo general de productos filtrada por
     * categoria, marca, sucursal y opcion de stock critico.
     *
     * @param filters     criterios de filtrado
     * @param pageRequest solicitud de paginacion
     * @return {@link PageResult} con la pagina de {@link InventarioRowModel.CatalogoRow}
     */
    public PageResult<InventarioRowModel.CatalogoRow> getCatalogo(InventarioFilters filters, PageRequest pageRequest) {
        List<InventarioRowModel.CatalogoRow> filtered = store.productos.stream()
                .filter(p -> matchesCatalogFilters(p, filters))
                .map(this::toCatalogoRow)
                .collect(Collectors.toList());
        return PaginationHelper.page(filtered, pageRequest);
    }

    private boolean matchesCatalogFilters(Producto p, InventarioFilters filters) {
        if (!FilterSupport.matchesText(filters.getSearchText(),
                p.getReferencia(), p.getNombre(), p.getCategoria(), p.getMarca())) {
            return false;
        }
        String cat = p.getCategoria() != null ? p.getCategoria() : "";
        if (!FilterSupport.matchesExact(cat, filters.getCategoria())) {
            return false;
        }
        String suc = p.getSucursal() != null ? p.getSucursal() : "";
        if (!FilterSupport.matchesExact(suc, filters.getSucursal())) {
            return false;
        }
        String marca = p.getMarca() != null ? p.getMarca() : "";
        if (!FilterSupport.matchesExact(marca, filters.getMarca())) {
            return false;
        }
        if (filters.isSoloStockCritico()) {
            if (p.getStock() > p.getStockMinimo()) {
                return false;
            }
        }
        return true;
    }

    private InventarioRowModel.CatalogoRow toCatalogoRow(Producto p) {
        String estadoStr = p.getEstado() != null ? p.getEstado().name() : "ACTIVO";
        String estadoDisplay = switch (estadoStr) {
            case "AGOTADO" -> "Agotado";
            case "BAJO_STOCK" -> "Bajo stock";
            case "ACTIVO" -> "Activo";
            default -> estadoStr;
        };
        return new InventarioRowModel.CatalogoRow(
                p.getReferencia(),
                p.getNombre(),
                p.getCategoria() != null ? p.getCategoria() : "-",
                p.getMarca() != null ? p.getMarca() : "-",
                p.getSucursal() != null ? p.getSucursal() : "-",
                p.getStock() + " unidades",
                MoneyUtils.formatOrDefault(p.getPrecioVenta()),
                estadoDisplay
        );
    }

    // ==================== Sub-view 2: Monturas ====================

    public List<InventarioRowModel.MonturaRow> getMonturas(InventarioFilters filters) {
        List<String> monturaCats = List.of("MONTURA");
        return store.productos.stream()
                .filter(p -> monturaCats.contains(p.getCategoria()))
                .filter(p -> matchesMonturaFilters(p, filters))
                .map(this::toMonturaRow)
                .collect(Collectors.toList());
    }

    private boolean matchesMonturaFilters(Producto p, InventarioFilters filters) {
        if (!FilterSupport.matchesText(filters.getSearchText(),
                p.getReferencia(), p.getNombre(), p.getMarca())) {
            return false;
        }
        String marca = p.getMarca() != null ? p.getMarca() : "";
        if (!FilterSupport.matchesExact(marca, filters.getMarca())) {
            return false;
        }
        String suc = p.getSucursal() != null ? p.getSucursal() : "";
        if (!FilterSupport.matchesExact(suc, filters.getSucursal())) {
            return false;
        }
        return true;
    }

    private InventarioRowModel.MonturaRow toMonturaRow(Producto p) {
        String ref = p.getReferencia();
        String material = ref.contains("ACETATO") ? "Acetato" : "Metal";
        String color = ref.contains("Negro") ? "Negro" : ref.contains("Rosa") ? "Rosa" : ref.contains("Carey") ? "Carey" : "Mate";
        return new InventarioRowModel.MonturaRow(
                ref,
                p.getNombre(),
                p.getMarca() != null ? p.getMarca() : "-",
                material,
                color,
                MoneyUtils.formatOrDefault(p.getPrecioVenta()),
                String.valueOf(p.getStock()),
                p.getSucursal() != null ? p.getSucursal() : "-"
        );
    }

    // ==================== Sub-view 3: Lentes y variantes ====================

    public List<InventarioRowModel.LenteRow> getLentes(InventarioFilters filters) {
        List<String> lenteCats = List.of("LENTES");
        return store.productos.stream()
                .filter(p -> lenteCats.contains(p.getCategoria()))
                .filter(p -> matchesLenteFilters(p, filters))
                .map(this::toLenteRow)
                .collect(Collectors.toList());
    }

    private boolean matchesLenteFilters(Producto p, InventarioFilters filters) {
        if (!FilterSupport.matchesText(filters.getSearchText(),
                p.getReferencia(), p.getNombre())) {
            return false;
        }
        return true;
    }

    private InventarioRowModel.LenteRow toLenteRow(Producto p) {
        String tipo = p.getNombre().toLowerCase().contains("progresivo") ? "Progresivo"
                : p.getNombre().toLowerCase().contains("bifocal") ? "Bifocal"
                : "Monofocal";
        String indice = p.getNombre().contains("1.67") ? "1.67"
                : p.getNombre().contains("1.56") ? "1.56"
                : "1.50";
        String tratamiento = p.getNombre().toLowerCase().contains("antirreflejo") ? "Antirreflejo"
                : p.getNombre().toLowerCase().contains("fotocromatico") ? "Fotocromatico"
                : "Estandard";
        String estadoStr = p.getEstado() != null ? p.getEstado().name() : "ACTIVO";
        String estadoDisplay = switch (estadoStr) {
            case "AGOTADO" -> "Agotado";
            case "BAJO_STOCK" -> "Bajo stock";
            default -> "Activo";
        };
        return new InventarioRowModel.LenteRow(
                p.getReferencia(),
                p.getNombre(),
                tipo,
                indice,
                tratamiento,
                String.valueOf(p.getStock()),
                MoneyUtils.formatOrDefault(p.getPrecioVenta()),
                estadoDisplay
        );
    }

    // ==================== Sub-view 4: Movimientos de inventario ====================

    public List<InventarioRowModel.MovimientoRow> getMovimientos(InventarioFilters filters) {
        List<InventarioRowModel.MovimientoRow> rows = new ArrayList<>();
        // Seed movimientos from products
        String[] tipos = {"Entrada", "Salida", "Ajuste", "Transferencia"};
        String[] responsables = {"Carlos Zambrano", "Laura Escobar", "Patricia Mendoza"};
        int idx = 0;
        for (Producto p : store.productos) {
            if (matchesMovimientoFilters(p, filters)) {
                rows.add(new InventarioRowModel.MovimientoRow(
                        dates.pastDate(30 - idx),
                        tipos[idx % tipos.length],
                        p.getReferencia(),
                        p.getNombre(),
                        (idx % 2 == 0 ? "+" : "-") + (idx + 3),
                        p.getSucursal() != null ? p.getSucursal() : "-",
                        responsables[idx % responsables.length]
                ));
                idx++;
            }
        }
        return rows;
    }

    private boolean matchesMovimientoFilters(Producto p, InventarioFilters filters) {
        if (!FilterSupport.matchesText(filters.getSearchText(),
                p.getReferencia(), p.getNombre())) {
            return false;
        }
        String suc = p.getSucursal() != null ? p.getSucursal() : "";
        if (!FilterSupport.matchesExact(suc, filters.getSucursal())) {
            return false;
        }
        return true;
    }

    // ==================== Sub-view 5: Reposicion y stock critico ====================

    public List<InventarioRowModel.CriticoRow> getStockCritico() {
        List<InventarioRowModel.CriticoRow> rows = new ArrayList<>();
        for (Producto p : store.productos) {
            if (p.getStock() <= p.getStockMinimo()) {
                String estadoStr = p.getStock() == 0 ? "Agotado" : "Bajo stock";
                rows.add(new InventarioRowModel.CriticoRow(
                        p.getReferencia(),
                        p.getNombre(),
                        p.getCategoria() != null ? p.getCategoria() : "-",
                        String.valueOf(p.getStock()),
                        String.valueOf(p.getStockMinimo()),
                        estadoStr,
                        p.getProveedorPrincipal() != null ? p.getProveedorPrincipal() : "-",
                        p.getSucursal() != null ? p.getSucursal() : "-"
                ));
            }
        }
        // Add seed-specific critical items
        rows.add(new InventarioRowModel.CriticoRow(
                "ACC-021", "Plaquetas silicona", "ACCESORIO",
                "0", "5", "Agotado", "Accesorios Visuales Import", "Matriz Centro"
        ));
        return rows;
    }

    // ==================== Sub-view 6: Recepcion y abastecimiento ====================

    public List<InventarioRowModel.RecepcionRow> getRecepciones() {
        List<InventarioRowModel.RecepcionRow> rows = new ArrayList<>();
        String[] proveedores = {"Distribuidora Optica Nacional S.A.", "LensTech Ecuador Cia. Ltda.", "Accesorios Visuales Import"};
        String[] estados = {"Completado", "En transito", "Pendiente", "Parcial"};
        String[] responsables = {"Carlos Zambrano", "Laura Escobar", "Patricia Mendoza"};
        for (int i = 0; i < 6; i++) {
            rows.add(new InventarioRowModel.RecepcionRow(
                    "REC-" + String.format("%03d", i + 1),
                    dates.pastDate(i * 5 + 2),
                    proveedores[i % proveedores.length],
                    i % 2 == 0 ? "Matriz Centro" : "Norte Mall",
                    estados[i % estados.length],
                    responsables[i % responsables.length]
            ));
        }
        return rows;
    }

    // ==================== Sub-view 7: Historico y analisis de stock ====================

    public List<InventarioRowModel.AnalisisRow> getAnalisis() {
        List<InventarioRowModel.AnalisisRow> rows = new ArrayList<>();
        String[] rotaciones = {"Alta", "Media", "Baja", "Alta", "Media"};
        String[] estadosActuales = {"Normal", "Bajo stock", "Activo", "Normal", "Activo"};
        String[] observaciones = {
                "Rotacion constante, mantener stock",
                "Requiere reposicion urgente",
                "Stock adecuado para demanda",
                "Alta demanda en ultima semana",
                "Sin movimiento en 30 dias"
        };
        int i = 0;
        for (Producto p : store.productos) {
            if (i >= 5) break;
            rows.add(new InventarioRowModel.AnalisisRow(
                    p.getReferencia(),
                    p.getNombre(),
                    p.getCategoria() != null ? p.getCategoria() : "-",
                    rotaciones[i % rotaciones.length],
                    dates.pastDate(i * 3 + 1),
                    estadosActuales[i % estadosActuales.length],
                    observaciones[i % observaciones.length]
            ));
            i++;
        }
        return rows;
    }

    // ==================== Summary and lookup helpers ====================

    /**
     * Construye un modelo de resumen para el producto seleccionado.
     *
     * @param producto entidad {@link Producto} seleccionada
     * @return {@link InventarioSummaryModel} con datos del producto
     */
    public InventarioSummaryModel buildSummary(Producto producto) {
        return InventarioSummaryModel.from(producto);
    }

    /**
     * Retorna los estados de stock disponibles para el combo de filtro.
     *
     * @return lista de estados (Activo, Bajo stock, Agotado)
     */
    public List<String> getEstadosStock() {
        return List.of("Activo", "Bajo stock", "Agotado");
    }

    /**
     * Retorna las categorias distinct de productos desde el {@link DemoStore}.
     *
     * @return lista ordenada de categorias
     */
    public List<String> getCategorias() {
        return store.productos.stream()
                .map(p -> p.getCategoria() != null ? p.getCategoria() : "")
                .filter(s -> !s.isEmpty())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Retorna las marcas distinct de productos desde el {@link DemoStore}.
     *
     * @return lista ordenada de marcas
     */
    public List<String> getMarcas() {
        return store.productos.stream()
                .map(p -> p.getMarca() != null ? p.getMarca() : "")
                .filter(s -> !s.isEmpty())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Retorna los nombres de proveedores desde el {@link DemoStore}.
     *
     * @return lista ordenada de nombres comerciales de proveedores
     */
    public List<String> getProveedores() {
        return store.proveedores.stream()
                .map(p -> p.getNombreComercial())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Retorna las sucursales distinct desde el {@link DemoStore}.
     *
     * @return lista ordenada de nombres de sucursal
     */
    public List<String> getSucursales() {
        return store.sucursales.stream()
                .map(s -> s.getNombre())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Retorna estadisticas de stock critico: cantidad de productos agotados,
     * con bajo stock y en proceso de reposicion.
     *
     * @return {@link StatsCritico} con contadores de estado de stock
     */
    public StatsCritico getStatsCritico() {
        int agotados = 0;
        int bajoStock = 0;
        int enReposicion = 0;
        for (Producto p : store.productos) {
            if (p.getStock() == 0) {
                agotados++;
            } else if (p.getStock() <= p.getStockMinimo()) {
                bajoStock++;
            }
            if (p.getEstado() == EstadoGeneral.PENDIENTE || p.getEstado() == EstadoGeneral.EN_PROCESO) {
                enReposicion++;
            }
        }
        // Seed-specific: add ACC-021 as agotado
        agotados = Math.max(agotados, 1);
        return new StatsCritico(agotados, bajoStock, enReposicion);
    }

    /**
     * Record que encapsula las estadisticas de stock critico.
     *
     * @param agotados     cantidad de productos con stock cero
     * @param bajoStock    cantidad de productos con stock bajo el minimo
     * @param enReposicion cantidad de productos en proceso de reposicion
     */
    public record StatsCritico(int agotados, int bajoStock, int enReposicion) {
    }
}
