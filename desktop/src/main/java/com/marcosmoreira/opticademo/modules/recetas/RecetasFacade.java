package com.marcosmoreira.opticademo.modules.recetas;

import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.shared.domain.cliente.Cliente;
import com.marcosmoreira.opticademo.shared.query.FilterSupport;
import com.marcosmoreira.opticademo.shared.util.MoneyUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Facade para el modulo de Recetas (gestion de recetas oftalmologicas).
 * <p>
 * Este facade proporciona datos de demostracion para todas las sub-vistas del modulo
 * de Recetas, el cual gestiona las recetas oftalmologicas de los clientes, su historial,
 * medidas asociadas y vinculaciones con ordenes opticas. Utiliza seed data estatico
 * para el cliente {@code CLI-001} (Sofia Ramirez) y datos genericos para el resto.
 * </p>
 * <p>
 * Sub-vistas que alimenta:
 * <ul>
 *   <li><b>Receta Actual:</b> resumen de la receta vigente con valores OD/OI, ADD, DP.</li>
 *   <li><b>Historial de Recetas:</b> lista cronologica de recetas anteriores.</li>
 *   <li><b>Medidas y Parametros:</b> tabla con DP, altura de montaje, uso principal, preferencias.</li>
 *   <li><b>Vinculaciones con Ordenes:</b> ordenes opticas asociadas a la receta.</li>
 *   <li><b>Recomendaciones:</b> lista de sugerencias para el cliente.</li>
 *   <li><b>Contexto del Cliente:</b> panel lateral con datos clave del cliente.</li>
 *   <li><b>Buscador de Clientes:</b> busqueda por nombre, codigo o telefono.</li>
 * </ul>
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see DemoStore
 * @see Cliente
 * @see MoneyUtils
 */
public class RecetasFacade {

    private final DemoStore store;

    /**
     * Construye el facade con referencia al almacén de datos de demostración.
     *
     * @param store instancia del {@link DemoStore}
     */
    public RecetasFacade(DemoStore store) {
        this.store = store;
    }

    /**
     * Retorna el resumen de la receta activa (vigente) para el cliente indicado.
     * Incluye valores de ojo derecho (OD), ojo izquierdo (OI), adicion (ADD),
     * distancia pupilar (DP), tratamiento sugerido y uso principal.
     *
     * @param clienteId identificador del cliente (e.g. "CLI-001")
     * @return {@link RecetasSummaryModel} con datos de la receta actual, o {@code null} si no existe
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
     * Retorna el historial de recetas del cliente en orden cronologico,
     * con fecha, profesional emisor, estado, valores OD/OI resumidos, DP y observaciones.
     *
     * @param clienteId identificador del cliente
     * @return lista de {@link RecetasRowModel.HistorialRow} con recetas anteriores
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
     * Retorna las medidas y parametros optometricos del cliente, incluyendo
     * distancia pupilar, altura de montaje, uso principal, actividad laboral
     * y preferencias de material y tratamiento.
     *
     * @param clienteId identificador del cliente
     * @return lista de {@link RecetasRowModel.MedicionRow} con parametros del cliente
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
     * Retorna las vinculaciones de la receta con ordenes opticas asociadas,
     * mostrando referencia, estado, fecha, monto y fecha estimada de entrega.
     *
     * @param clienteId identificador del cliente
     * @return lista de {@link RecetasRowModel.VinculacionRow} con ordenes vinculadas
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
     * Retorna las recomendaciones optometricas para el cliente, basadas en
     * su historial y perfil visual.
     *
     * @param clienteId identificador del cliente
     * @return lista de recomendaciones como cadenas de texto
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
     * Retorna los estados de receta distinct para el combo de filtro.
     *
     * @return lista ordenada de estados de receta
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
     * Retorna los nombres de profesionales que han emitido recetas.
     *
     * @return lista de nombres de profesionales
     */
    public List<String> getProfesionales() {
        return List.of("Dra. Salazar", "Dr. Paredes");
    }

    /**
     * Construye un modelo de contexto del cliente para el panel derecho,
     * consolidando datos personales, estadisticas de ordenes activas, entregas
     * pendientes y saldo pendiente calculado desde el {@link DemoStore}.
     *
     * @param cliente entidad {@link Cliente} seleccionada
     * @return {@link ClientContextModel} con datos consolidados, o {@code null} si el cliente es nulo
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
     * Busca clientes por texto (nombre, codigo o telefono) para el selector
     * de clientes del modulo de recetas.
     *
     * @param searchText texto de busqueda
     * @return lista de {@link Cliente} que coinciden con el texto
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
     * Modelo de contexto del cliente para el panel derecho del modulo de recetas.
     * Contiene datos personales, estado de receta, distancia pupilar, profesional
     * asignado, ordenes activas, entregas pendientes y saldo pendiente.
     *
     * @param nombre          nombre completo del cliente
     * @param codigo          codigo interno del cliente
     * @param telefono        telefono de contacto
     * @param ultimaVisita    fecha de ultima visita
     * @param sucursal        sucursal habitual
     * @param estadoReceta    estado actual de la receta
     * @param pd              distancia pupilar
     * @param profesional     profesional asignado
     * @param ordenesActivas  cantidad de ordenes activas
     * @param entregasPendientes cantidad de entregas pendientes
     * @param saldoPendiente  monto total pendiente
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
