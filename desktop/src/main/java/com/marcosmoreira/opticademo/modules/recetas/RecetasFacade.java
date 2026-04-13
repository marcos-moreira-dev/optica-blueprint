package com.marcosmoreira.opticademo.modules.recetas;

import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.shared.domain.cliente.Cliente;
import com.marcosmoreira.opticademo.shared.query.FilterSupport;
import com.marcosmoreira.opticademo.shared.util.MoneyUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Facade that queries the DemoStore and returns data for the Recetas module.
 * No business logic -- just view-facing data assembly.
 */
public class RecetasFacade {

    private final DemoStore store;

    public RecetasFacade(DemoStore store) {
        this.store = store;
    }

    /**
     * Returns the current (active) recipe summary for the given client.
     */
    public RecetasSummaryModel getCurrentRecipe(String clienteId) {
        Cliente cliente = store.clientes.stream()
                .filter(c -> c.getId().equals(clienteId))
                .findFirst()
                .orElse(null);

        if (cliente == null) {
            return null;
        }

        // Seed data for Sofia Ramirez (first client)
        if ("CLI-001".equals(clienteId)) {
            return new RecetasSummaryModel(
                    cliente.getNombreCompleto(),
                    cliente.getCodigoInterno(),
                    "Vigente",
                    "Dra. Salazar",
                    "12/04/2026",
                    "-1.25",
                    "-0.50",
                    "180",
                    "-1.00",
                    "-0.25",
                    "170",
                    "+1.00",
                    "62mm",
                    "Antirreflejo + Blue Cut",
                    "Diario y pantalla"
            );
        }

        return new RecetasSummaryModel(
                cliente.getNombreCompleto(),
                cliente.getCodigoInterno(),
                cliente.getEstadoReceta() != null ? cliente.getEstadoReceta() : "Sin receta",
                "-",
                "-",
                "-", "-", "-",
                "-", "-", "-",
                "-", "-",
                "-",
                "-"
        );
    }

    /**
     * Returns the recipe history for the given client.
     */
    public List<RecetasRowModel.HistorialRow> getHistorial(String clienteId) {
        List<RecetasRowModel.HistorialRow> rows = new ArrayList<>();

        if ("CLI-001".equals(clienteId)) {
            // Current recipe
            rows.add(new RecetasRowModel.HistorialRow(
                    "12/04/2026",
                    "Dra. Salazar",
                    "Vigente",
                    "OD -1.25/-0.50x180",
                    "OI -1.00/-0.25x170",
                    "62mm",
                    "Antirreflejo + Blue Cut sugerido"
            ));
            // Previous recipe
            rows.add(new RecetasRowModel.HistorialRow(
                    "15/11/2025",
                    "Dr. Paredes",
                    "Vencida",
                    "OD -1.00/-0.50x175",
                    "OI -0.75/-0.25x165",
                    "62mm",
                    "Uso diario basico"
            ));
            // Older recipe
            rows.add(new RecetasRowModel.HistorialRow(
                    "21/05/2025",
                    "Dra. Salazar",
                    "Historica",
                    "OD -0.75/-0.25x170",
                    "OI -0.50/-0.25x180",
                    "61mm",
                    "Primera evaluacion miopia leve"
            ));
        }

        return rows;
    }

    /**
     * Returns medidas y parametros for the given client.
     */
    public List<RecetasRowModel.MedicionRow> getMedidas(String clienteId) {
        List<RecetasRowModel.MedicionRow> rows = new ArrayList<>();

        if ("CLI-001".equals(clienteId)) {
            // Medidas basicas
            rows.add(new RecetasRowModel.MedicionRow("Distancia pupilar (DP)", "62", "mm", "Medida estandar"));
            rows.add(new RecetasRowModel.MedicionRow("Altura de montaje", "18", "mm", "Centro geometrico"));
            rows.add(new RecetasRowModel.MedicionRow("Observacion de centrado", "-", "-", "Sin observaciones especiales"));

            // Uso y contexto
            rows.add(new RecetasRowModel.MedicionRow("Uso principal", "Diario y pantalla", "", "Horas prolongadas frente a pantalla"));
            rows.add(new RecetasRowModel.MedicionRow("Actividad laboral", "Oficina", "", "Trabajo administrativo"));

            // Preferencias tecnicas
            rows.add(new RecetasRowModel.MedicionRow("Material preferido", "Policarbonato", "", "Ligero y resistente"));
            rows.add(new RecetasRowModel.MedicionRow("Tratamiento preferido", "Antirreflejo + Blue Cut", "", "Proteccion pantalla"));
        }

        return rows;
    }

    /**
     * Returns vinculaciones con orden optica for the given client.
     */
    public List<RecetasRowModel.VinculacionRow> getVinculaciones(String clienteId) {
        List<RecetasRowModel.VinculacionRow> rows = new ArrayList<>();

        if ("CLI-001".equals(clienteId)) {
            rows.add(new RecetasRowModel.VinculacionRow(
                    "OV-00215",
                    "En proceso",
                    "12/04/2026",
                    MoneyUtils.formatOrDefault(249.50),
                    "17/04/2026"
            ));
            rows.add(new RecetasRowModel.VinculacionRow(
                    "OV-00198",
                    "Entregada",
                    "15/11/2025",
                    MoneyUtils.formatOrDefault(0.00),
                    "25/11/2025"
            ));
        }

        return rows;
    }

    /**
     * Returns recommendations for the given client.
     */
    public List<String> getRecomendaciones(String clienteId) {
        List<String> recs = new ArrayList<>();

        if ("CLI-001".equals(clienteId)) {
            recs.add("Se recomienda lente con filtro blue cut por uso prolongado de pantalla.");
            recs.add("Considerar lente progresivo en proxima receta por edad del paciente.");
            recs.add("Tratamiento antirreflejo indispensable para comodidad visual.");
            recs.add("Control optometrico en 6 meses para seguimiento de miopia.");
        }

        return recs;
    }

    /**
     * Returns all distinct receta estado values for the filter combo.
     */
    public List<String> getEstadosReceta() {
        return store.clientes.stream()
                .map(Cliente::getEstadoReceta)
                .filter(v -> v != null && !v.isBlank())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Returns all distinct professional names from recipes.
     */
    public List<String> getProfesionales() {
        return List.of("Dra. Salazar", "Dr. Paredes");
    }

    /**
     * Builds a client context summary for the right panel.
     */
    public ClientContextModel buildClientContext(Cliente cliente) {
        if (cliente == null) {
            return null;
        }

        // Count active orders for this client
        long ordenesActivas = store.ventas.stream()
                .filter(v -> v.getClienteId() != null && v.getClienteId().equals(cliente.getId()))
                .filter(v -> v.getEstado() != null && (v.getEstado().name().equals("EN_PROCESO") || v.getEstado().name().equals("CONFIRMADO")))
                .count();

        // Count pending deliveries
        long entregasPendientes = store.ventas.stream()
                .filter(v -> v.getClienteId() != null && v.getClienteId().equals(cliente.getId()))
                .filter(v -> v.getEstado() != null && v.getEstado().name().equals("EN_PROCESO"))
                .count();

        // Calculate pending balance
        double saldoPendiente = store.ventas.stream()
                .filter(v -> v.getClienteId() != null && v.getClienteId().equals(cliente.getId()))
                .mapToDouble(v -> v.getSaldo())
                .sum();

        return new ClientContextModel(
                cliente.getNombreCompleto(),
                cliente.getCodigoInterno(),
                cliente.getTelefono(),
                cliente.getUltimaVisita() != null ? cliente.getUltimaVisita() : "-",
                cliente.getSucursalHabitual() != null ? cliente.getSucursalHabitual() : "-",
                cliente.getEstadoReceta() != null ? cliente.getEstadoReceta() : "Sin receta",
                "62mm",
                "Dra. Salazar",
                String.valueOf(ordenesActivas),
                String.valueOf(entregasPendientes),
                MoneyUtils.formatOrDefault(saldoPendiente)
        );
    }

    /**
     * Finds a client by search text (name, code, or phone).
     */
    public List<Cliente> findClients(String searchText) {
        return store.clientes.stream()
                .filter(c -> FilterSupport.matchesText(searchText,
                        c.getNombreCompleto(),
                        c.getCodigoInterno(),
                        c.getTelefono()))
                .collect(Collectors.toList());
    }

    /**
     * Client context model for the right panel.
     */
    public record ClientContextModel(
            String nombre,
            String codigo,
            String telefono,
            String ultimaVisita,
            String sucursal,
            String estadoReceta,
            String pd,
            String profesional,
            String ordenesActivas,
            String entregasPendientes,
            String saldoPendiente
    ) {
    }
}
