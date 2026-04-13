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
 * Facade that queries the DemoStore and returns data for the Clientes module.
 * No business logic -- just view-facing data assembly.
 */
public class ClientesFacade {

    private final DemoStore store;

    public ClientesFacade(DemoStore store) {
        this.store = store;
    }

    /**
     * Returns a paginated, filtered list of client row models.
     */
    public PageResult<ClientesRowModel> findPage(ClientesFilters filters, PageRequest pageRequest) {
        List<ClientesRowModel> filtered = store.clientes.stream()
                .filter(c -> matchesFilters(c, filters))
                .map(ClientesRowModel::from)
                .collect(Collectors.toList());

        return PaginationHelper.page(filtered, pageRequest);
    }

    /**
     * Builds a summary model for the selected client.
     */
    public ClientesSummaryModel buildSummary(Cliente cliente) {
        return ClientesSummaryModel.from(cliente);
    }

    /**
     * Returns all distinct estado values for the filter combo.
     */
    public List<String> getAllEstados() {
        return store.clientes.stream()
                .map(c -> c.getEstado() != null ? c.getEstado().name() : "ACTIVO")
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Returns all distinct ultima visita date values for the filter combo.
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
     * Returns all distinct receta estado values for the filter combo.
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
