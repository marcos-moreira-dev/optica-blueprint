package com.marcosmoreira.opticademo.modules.proveedores;

import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.demo.generator.DateGenerator;
import com.marcosmoreira.opticademo.shared.domain.proveedor.Proveedor;
import com.marcosmoreira.opticademo.shared.query.FilterSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Facade para el modulo de Proveedores (gestion de proveedores y abastecimiento).
 * <p>
 * Este facade actua como capa de abstraccion entre el {@link DemoStore} (colecciones
 * de {@link Proveedor} y sucursales) y las siete sub-vistas del modulo de Proveedores.
 * Proporciona datos del directorio, perfil comercial, catalogo vinculado, ordenes,
 * recepciones, desempeno e historico de cada proveedor.
 * </p>
 * <p>
 * Sub-vistas que alimenta:
 * <ul>
 *   <li><b>Directorio:</b> lista de proveedores con tipo, contacto y estado.</li>
 *   <li><b>Perfil Comercial:</b> datos detallados del proveedor seleccionado.</li>
 *   <li><b>Catalogo Vinculado:</b> productos asociados al proveedor.</li>
 *   <li><b>Ordenes:</b> ordenes de compra asociadas al proveedor.</li>
 *   <li><b>Recepciones:</b> historial de recepciones de mercaderia.</li>
 *   <li><b>Desempeno:</b> metricas de cumplimiento y calidad.</li>
 *   <li><b>Historico:</b> registro historico de operaciones.</li>
 * </ul>
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see DemoStore
 * @see Proveedor
 * @see DateGenerator
 * @see FilterSupport
 */
public class ProveedoresFacade {

    private final DemoStore store;
    private final DateGenerator dates = new DateGenerator();

    /**
     * Construye el facade con referencia al almacén de datos de demostración.
     *
     * @param store instancia del {@link DemoStore}
     */
    public ProveedoresFacade(DemoStore store) {
        this.store = store;
    }

    // ==================== Sub-view 1: Directorio de proveedores ====================

    /**
     * Retorna el directorio de proveedores filtrado por tipo, estado, categoria y sucursal.
     *
     * @param filters criterios de filtrado
     * @return lista de {@link ProveedoresRowModel.DirectorioRow}
     */
    public List<ProveedoresRowModel.DirectorioRow> getDirectorio(ProveedoresFilters filters) {
        return store.proveedores.stream()
                .filter(p -> matchesDirectorioFilters(p, filters))
                .map(this::toDirectorioRow)
                .collect(Collectors.toList());
    }

    private boolean matchesDirectorioFilters(Proveedor p, ProveedoresFilters filters) {
        if (!FilterSupport.matchesText(filters.getSearchText(),
                p.getNombreComercial(), p.getTipoProveedor(), p.getContacto(), p.getCiudad())) {
            return false;
        }
        String tipo = p.getTipoProveedor() != null ? p.getTipoProveedor() : "";
        if (!FilterSupport.matchesExact(tipo, filters.getTipo())) {
            return false;
        }
        String estadoStr = p.getEstado() != null ? p.getEstado().name() : "ACTIVO";
        String estadoDisplay = switch (estadoStr) {
            case "ACTIVO" -> "Activo";
            case "INACTIVO" -> "Inactivo";
            case "BAJO_OBSERVACION" -> "Bajo observacion";
            case "PENDIENTE" -> "Pendiente";
            default -> estadoStr;
        };
        if (!FilterSupport.matchesExact(estadoDisplay, filters.getEstado())) {
            return false;
        }
        String cat = p.getCategoriaAbastecida() != null ? p.getCategoriaAbastecida() : "";
        if (!FilterSupport.matchesExact(cat, filters.getCategoria())) {
            return false;
        }
        String suc = p.getSucursalesAtendidas() != null ? p.getSucursalesAtendidas() : "";
        if (!FilterSupport.matchesExact(suc, filters.getSucursal())) {
            return false;
        }
        return true;
    }

    private ProveedoresRowModel.DirectorioRow toDirectorioRow(Proveedor p) {
        String estadoStr = p.getEstado() != null ? p.getEstado().name() : "ACTIVO";
        String estadoDisplay = switch (estadoStr) {
            case "ACTIVO" -> "Activo";
            case "INACTIVO" -> "Inactivo";
            case "BAJO_OBSERVACION" -> "Bajo observacion";
            case "PENDIENTE" -> "Pendiente";
            default -> estadoStr;
        };
        return new ProveedoresRowModel.DirectorioRow(
                p.getNombreComercial(),
                p.getTipoProveedor() != null ? p.getTipoProveedor() : "-",
                p.getContacto() != null ? p.getContacto() : "-",
                p.getTiempoEstimadoDias() > 0 ? p.getTiempoEstimadoDias() + " dias" : "-",
                estadoDisplay,
                p.getCategoriaAbastecida() != null ? p.getCategoriaAbastecida() : "-"
        );
    }

    // ==================== Sub-view 2: Perfil comercial ====================

    public List<ProveedoresRowModel.PerfilRow> getPerfilComercial(Proveedor proveedor) {
        List<ProveedoresRowModel.PerfilRow> rows = new ArrayList<>();
        if (proveedor == null) return rows;
        rows.add(new ProveedoresRowModel.PerfilRow("Nombre comercial", proveedor.getNombreComercial()));
        rows.add(new ProveedoresRowModel.PerfilRow("Codigo interno", proveedor.getCodigoInterno() != null ? proveedor.getCodigoInterno() : "-"));
        rows.add(new ProveedoresRowModel.PerfilRow("Tipo", proveedor.getTipoProveedor() != null ? proveedor.getTipoProveedor() : "-"));
        rows.add(new ProveedoresRowModel.PerfilRow("Contacto", proveedor.getContacto() != null ? proveedor.getContacto() : "-"));
        rows.add(new ProveedoresRowModel.PerfilRow("Telefono", proveedor.getTelefono() != null ? proveedor.getTelefono() : "-"));
        rows.add(new ProveedoresRowModel.PerfilRow("Correo", proveedor.getCorreo() != null ? proveedor.getCorreo() : "-"));
        rows.add(new ProveedoresRowModel.PerfilRow("Ciudad", proveedor.getCiudad() != null ? proveedor.getCiudad() : "-"));
        rows.add(new ProveedoresRowModel.PerfilRow("Categoria abastecida", proveedor.getCategoriaAbastecida() != null ? proveedor.getCategoriaAbastecida() : "-"));
        rows.add(new ProveedoresRowModel.PerfilRow("Sucursales atendidas", proveedor.getSucursalesAtendidas() != null ? proveedor.getSucursalesAtendidas() : "-"));
        rows.add(new ProveedoresRowModel.PerfilRow("Tiempo estimado", proveedor.getTiempoEstimadoDias() > 0 ? proveedor.getTiempoEstimadoDias() + " dias" : "-"));
        String estadoStr = proveedor.getEstado() != null ? proveedor.getEstado().name() : "ACTIVO";
        String estadoDisplay = switch (estadoStr) {
            case "ACTIVO" -> "Activo";
            case "INACTIVO" -> "Inactivo";
            case "BAJO_OBSERVACION" -> "Bajo observacion";
            case "PENDIENTE" -> "Pendiente";
            default -> estadoStr;
        };
        rows.add(new ProveedoresRowModel.PerfilRow("Estado", estadoDisplay));
        return rows;
    }

    // ==================== Sub-view 3: Catalogo vinculado ====================

    public List<ProveedoresRowModel.CatalogoRow> getCatalogoVinculado(String proveedorId) {
        List<ProveedoresRowModel.CatalogoRow> rows = new ArrayList<>();
        String[] referencias = {"REF-001", "REF-002", "REF-003", "REF-004", "REF-005"};
        String[] nombres = {"Montura clasica acetato", "Lente monofocal 1.50", "Montura deportiva metal", "Lente progresivo 1.67", "Montura infantil flexible"};
        String[] categorias = {"MONTURA", "LENTE", "MONTURA", "LENTE", "MONTURA"};
        String[] marcas = {"RayOptic", "VisionClear", "SportFrame", "ProgresVision", "KidsFrame"};
        String[] estados = {"Activo", "Activo", "Activo", "Bajo pedido", "Activo"};

        for (int i = 0; i < referencias.length; i++) {
            rows.add(new ProveedoresRowModel.CatalogoRow(
                    referencias[i],
                    nombres[i],
                    categorias[i],
                    marcas[i],
                    estados[i]
            ));
        }
        return rows;
    }

    // ==================== Sub-view 4: Ordenes y abastecimiento ====================

    public List<ProveedoresRowModel.OrdenRow> getOrdenes(String proveedorId) {
        List<ProveedoresRowModel.OrdenRow> rows = new ArrayList<>();
        Proveedor proveedor = store.proveedores.stream()
                .filter(p -> p.getId().equals(proveedorId))
                .findFirst().orElse(null);
        String proveedorNombre = proveedor != null ? proveedor.getNombreComercial() : "-";

        rows.add(new ProveedoresRowModel.OrdenRow(
                "OC-082",
                dates.pastDate(2),
                "Enviada",
                "12 items",
                "Pendiente",
                "Orden regular monturas"
        ));
        rows.add(new ProveedoresRowModel.OrdenRow(
                "OC-078",
                dates.pastDate(10),
                "Completada",
                "8 items",
                "RC-035",
                "Entrega completa sin novedades"
        ));
        rows.add(new ProveedoresRowModel.OrdenRow(
                "OC-071",
                dates.pastDate(20),
                "Completada",
                "15 items",
                "RC-030",
                "Entrega con 1 item diferido"
        ));
        return rows;
    }

    // ==================== Sub-view 5: Recepciones e incidencias ====================

    public List<ProveedoresRowModel.RecepcionRow> getRecepciones(String proveedorId) {
        List<ProveedoresRowModel.RecepcionRow> rows = new ArrayList<>();
        String[] tipos = {"Recepcion completa", "Recepcion parcial", "Recepcion completa", "Incidencia"};
        String[] ordenes = {"OC-078", "OC-071", "OC-065", "OC-060"};
        String[] estados = {"Completada", "Con diferencia", "Completada", "Resuelta"};
        String[] responsables = {"Carlos Zambrano", "Laura Escobar", "Patricia Mendoza", "Carlos Zambrano"};

        for (int i = 0; i < tipos.length; i++) {
            rows.add(new ProveedoresRowModel.RecepcionRow(
                    dates.pastDate(5 + i * 7),
                    tipos[i],
                    ordenes[i],
                    estados[i],
                    responsables[i]
            ));
        }
        return rows;
    }

    // ==================== Sub-view 6: Desempeno del proveedor ====================

    public List<ProveedoresRowModel.DesempenoRow> getDesempeno(String proveedorId) {
        List<ProveedoresRowModel.DesempenoRow> rows = new ArrayList<>();
        rows.add(new ProveedoresRowModel.DesempenoRow(
                "Cumplimiento de plazo",
                "92%",
                "Bueno",
                "Dentro del rango aceptable"
        ));
        rows.add(new ProveedoresRowModel.DesempenoRow(
                "Calidad del producto",
                "4.5/5",
                "Excelente",
                "Sin devoluciones en ultimo trimestre"
        ));
        rows.add(new ProveedoresRowModel.DesempenoRow(
                "Tiempo de respuesta",
                "3 dias promedio",
                "Bueno",
                "Respuesta consistente a solicitudes"
        ));
        rows.add(new ProveedoresRowModel.DesempenoRow(
                "Incidencias recientes",
                "1 en 90 dias",
                "Aceptable",
                "Diferencia menor en ultima recepcion"
        ));
        return rows;
    }

    // ==================== Sub-view 7: Historico ====================

    public List<ProveedoresRowModel.HistoricoRow> getHistorico(ProveedoresFilters filters) {
        List<ProveedoresRowModel.HistoricoRow> rows = new ArrayList<>();
        String[] tiposRegistro = {"Orden de compra", "Recepcion", "Orden de compra", "Recepcion", "Orden de compra", "Recepcion"};
        String[] referencias = {"OC-082", "RC-035", "OC-078", "RC-030", "OC-071", "RC-025"};
        String[] estados = {"Enviada", "Completada", "Completada", "Completada", "Completada", "Completada"};
        String[] observaciones = {
                "Orden en transito",
                "Recepcion sin novedades",
                "Entrega completa",
                "Entrega con 1 item diferido",
                "Entrega completa sin novedades",
                "Recepcion estandar"
        };

        for (int i = 0; i < tiposRegistro.length; i++) {
            String proveedorNombre = i < 3 ? "VisionLine Distribucion" :
                    i < 5 ? "LabVision Externo" : "NovaFrame Import";
            if (!FilterSupport.matchesText(filters.getSearchText(),
                    proveedorNombre, referencias[i], tiposRegistro[i])) {
                continue;
            }
            if (!FilterSupport.matchesExact(proveedorNombre, filters.getTipo())) {
                continue;
            }
            if (!FilterSupport.matchesExact(estados[i], filters.getEstado())) {
                continue;
            }
            rows.add(new ProveedoresRowModel.HistoricoRow(
                    dates.pastDate(i * 5 + 1),
                    proveedorNombre,
                    tiposRegistro[i],
                    referencias[i],
                    estados[i],
                    observaciones[i]
            ));
        }
        return rows;
    }

    // ==================== Summary and lookup helpers ====================

    /**
     * Construye un modelo de resumen para el proveedor seleccionado.
     *
     * @param proveedor entidad {@link Proveedor} seleccionada
     * @return {@link ProveedoresSummaryModel} con datos del proveedor
     */
    public ProveedoresSummaryModel buildSummary(Proveedor proveedor) {
        return ProveedoresSummaryModel.from(proveedor);
    }

    /**
     * Retorna los tipos de proveedor distinct desde el {@link DemoStore}.
     *
     * @return lista ordenada de tipos de proveedor
     */
    public List<String> getTiposProveedor() {
        return store.proveedores.stream()
                .map(p -> p.getTipoProveedor() != null ? p.getTipoProveedor() : "")
                .filter(s -> !s.isEmpty())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> getEstados() {
        List<String> estados = new ArrayList<>();
        estados.add("Activo");
        estados.add("Inactivo");
        estados.add("Bajo observacion");
        estados.add("Pendiente");
        return estados;
    }

    /**
     * Retorna las categorias abastecidas distinct para el combo de filtro.
     * Si no hay datos en el store, retorna valores por defecto.
     *
     * @return lista ordenada de categorias abastecidas
     */
    public List<String> getCategoriasAbastecidas() {
        List<String> categorias = store.proveedores.stream()
                .map(p -> p.getCategoriaAbastecida() != null ? p.getCategoriaAbastecida() : "")
                .filter(s -> !s.isEmpty())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        if (categorias.isEmpty()) {
            categorias = List.of("Monturas", "Lentes", "Lentes contacto", "Accesorios");
        }
        return categorias;
    }

    public List<String> getSucursales() {
        return store.sucursales.stream()
                .map(s -> s.getNombre())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}
