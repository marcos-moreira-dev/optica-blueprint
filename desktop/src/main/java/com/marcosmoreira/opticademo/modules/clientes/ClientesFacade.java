package com.marcosmoreira.opticademo.modules.clientes;

import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.shared.domain.cliente.Cliente;
import com.marcosmoreira.opticademo.shared.query.FilterSupport;
import com.marcosmoreira.opticademo.shared.query.PageRequest;
import com.marcosmoreira.opticademo.shared.query.PageResult;
import com.marcosmoreira.opticademo.shared.query.PaginationHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Facade para el modulo de Clientes (gestion de clientes).
 * <p>
 * Este facade actua como capa de abstraccion entre el {@link DemoStore} (que contiene
 * la coleccion de {@link Cliente}) y las sub-vistas del modulo de Clientes. Proporciona
 * consultas paginadas y filtradas, asi como datos para combos de filtro.
 * </p>
 * <p>
 * Sub-vistas que alimenta:
 * <ul>
 *   <li><b>Lista de Clientes:</b> tabla paginada con datos basicos de cada cliente.</li>
 *   <li><b>Panel de Resumen:</b> datos consolidados del cliente seleccionado.</li>
 * </ul>
 * </p>
 * <p>
 * Patron de flujo de datos: las vistas pasan {@link ClientesFilters} y {@link PageRequest};
 * el facade filtra la coleccion {@code store.clientes}, mapea a {@link ClientesRowModel}
 * y aplica paginacion mediante {@link PaginationHelper}.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see DemoStore
 * @see Cliente
 * @see FilterSupport
 * @see PaginationHelper
 */
public class ClientesFacade {

    private final DemoStore store;

    /**
     * Construye el facade con referencia al almacén de datos de demostración.
     *
     * @param store instancia del {@link DemoStore}
     */
    public ClientesFacade(DemoStore store) {
        this.store = store;
    }

    /**
     * Retorna una pagina de clientes filtrada segun los criterios de busqueda,
     * mapeando cada {@link Cliente} a su representacion en fila de tabla.
     *
     * @param filters     criterios de filtrado (texto, estado, ultima visita, receta)
     * @param pageRequest solicitud de paginacion (pagina actual, tamaño)
     * @return {@link PageResult} con la pagina de {@link ClientesRowModel}
     */
    public PageResult<ClientesRowModel> findPage(ClientesFilters filters, PageRequest pageRequest) {
        List<ClientesRowModel> filtered = store.clientes.stream()
                .filter(c -> matchesFilters(c, filters))
                .map(ClientesRowModel::from)
                .collect(Collectors.toList());

        return PaginationHelper.page(filtered, pageRequest);
    }

    /**
     * Construye un modelo de resumen para el cliente seleccionado, con datos
     * consolidados para mostrar en el panel lateral derecho.
     *
     * @param cliente entidad {@link Cliente} seleccionada en la vista
     * @return {@link ClientesSummaryModel} con datos del cliente
     */
    public ClientesSummaryModel buildSummary(Cliente cliente) {
        return ClientesSummaryModel.from(cliente);
    }

    /**
     * Retorna todos los valores distinct de estado de cliente para el combo de filtro.
     *
     * @return lista ordenada de estados (ACTIVO, INACTIVO, etc.)
     */
    public List<String> getAllEstados() {
        return store.clientes.stream()
                .map(c -> c.getEstado() != null ? c.getEstado().name() : "ACTIVO")
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Retorna todas las fechas de ultima visita distinct para el combo de filtro.
     *
     * @return lista ordenada de fechas de ultima visita
     */
    public List<String> getAllUltimasVisitas() {
        return store.clientes.stream()
                .map(Cliente::getUltimaVisita)
                .filter(v -> v != null && !v.isBlank())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Retorna todos los valores distinct de estado de receta para el combo de filtro.
     *
     * @return lista ordenada de estados de receta (Vigente, Vencida, Sin receta, etc.)
     */
    public List<String> getAllRecetaEstados() {
        return store.clientes.stream()
                .map(Cliente::getEstadoReceta)
                .filter(v -> v != null && !v.isBlank())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    private boolean matchesFilters(Cliente cliente, ClientesFilters filters) {
        // Search text: matches nombre, codigo, telefono
        if (!FilterSupport.matchesText(filters.getSearchText(),
                cliente.getNombreCompleto(),
                cliente.getCodigoInterno(),
                cliente.getTelefono())) {
            return false;
        }

        // Estado filter
        String estadoStr = cliente.getEstado() != null ? cliente.getEstado().name() : "ACTIVO";
        if (!FilterSupport.matchesExact(estadoStr, filters.getEstado())) {
            return false;
        }

        // Ultima visita filter
        if (!FilterSupport.matchesExact(cliente.getUltimaVisita(), filters.getUltimaVisita())) {
            return false;
        }

        // Receta filter
        if (!FilterSupport.matchesExact(cliente.getEstadoReceta(), filters.getReceta())) {
            return false;
        }

        return true;
    }
}
