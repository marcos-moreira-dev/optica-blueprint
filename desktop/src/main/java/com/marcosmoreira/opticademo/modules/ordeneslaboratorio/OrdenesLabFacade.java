package com.marcosmoreira.opticademo.modules.ordeneslaboratorio;

import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.shared.domain.venta.VentaOptica;
import com.marcosmoreira.opticademo.shared.query.FilterSupport;
import com.marcosmoreira.opticademo.shared.query.PageRequest;
import com.marcosmoreira.opticademo.shared.query.PageResult;
import com.marcosmoreira.opticademo.shared.query.PaginationHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Facade para el modulo de Ordenes de Laboratorio.
 * <p>
 * Este facade proporciona datos de demostracion para la gestion de ordenes
 * enviadas a laboratorios externos e internos. Utiliza seed data inicializado
 * en el constructor para la cola de ordenes, etapas del proceso, incidencias
 * e historico. Complementa con datos del {@link DemoStore} para sucursales.
 * </p>
 * <p>
 * Sub-vistas que alimenta:
 * <ul>
 *   <li><b>Cola de Ordenes:</b> tabla paginada con estado, prioridad, laboratorio y sucursal.</li>
 *   <li><b>Etapas del Proceso:</b> tracking visual del progreso de cada orden.</li>
 *   <li><b>Envio y Recepcion:</b> datos de envio al laboratorio y recepcion en sucursal.</li>
 *   <li><b>Incidencias:</b> registro de problemas (lentes rayados, retrasos, etc.).</li>
 *   <li><b>Historico:</b> ordenes completadas con fecha de ingreso y entrega.</li>
 *   <li><b>Panel de Resumen:</b> datos detallados de la orden seleccionada.</li>
 * </ul>
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see DemoStore
 * @see VentaOptica
 * @see FilterSupport
 * @see PaginationHelper
 */
public class OrdenesLabFacade {

    private final DemoStore store;

    /** Seed data para la cola de ordenes de laboratorio. */
    private final List<OrdenesLabRowModel.ColaRow> seedCola;
    /** Seed data para las etapas del proceso de laboratorio. */
    private final List<OrdenesLabRowModel.EtapaRow> seedEtapas;
    /** Seed data para incidencias de laboratorio. */
    private final List<OrdenesLabRowModel.IncidenciaRow> seedIncidencias;
    /** Seed data para el historico de ordenes. */
    private final List<OrdenesLabRowModel.HistoricoRow> seedHistorico;

    /**
     * Construye el facade e inicializa los datos seed de laboratorio.
     *
     * @param store instancia del {@link DemoStore}
     */
    public OrdenesLabFacade(DemoStore store) {
        this.store = store;
        this.seedCola = initSeedCola();
        this.seedEtapas = initSeedEtapas();
        this.seedIncidencias = initSeedIncidencias();
        this.seedHistorico = initSeedHistorico();
    }

    /**
     * Retorna una pagina de ordenes de laboratorio filtrada segun los criterios
     * de busqueda, mostrando referencia, cliente, tipo de trabajo, fechas,
     * estado, laboratorio, prioridad y sucursal.
     *
     * @param filters     criterios de filtrado
     * @param pageRequest solicitud de paginacion
     * @return {@link PageResult} con la pagina de {@link OrdenesLabRowModel.ColaRow}
     */
    public PageResult<OrdenesLabRowModel.ColaRow> getColaOrdenes(OrdenesLabFilters filters, PageRequest pageRequest) {
        List<OrdenesLabRowModel.ColaRow> filtered = seedCola.stream()
                .filter(r -> matchesFilters(r, filters))
                .collect(Collectors.toList());

        return PaginationHelper.page(filtered, pageRequest);
    }

    /**
     * Retorna la lista de etapas del proceso para una orden de laboratorio,
     * desde "Recibida" hasta "Entregada", con fecha, responsable y observaciones.
     *
     * @param ordenId identificador de la orden
     * @return lista de {@link OrdenesLabRowModel.EtapaRow} con el tracking del proceso
     */
    public List<OrdenesLabRowModel.EtapaRow> getEtapas(String ordenId) {
        if (ordenId == null || ordenId.isBlank()) {
            return new ArrayList<>(seedEtapas);
        }
        return seedEtapas.stream()
                .filter(e -> e.responsable() != null && e.responsable().contains(ordenId))
                .collect(Collectors.toList());
    }

    /**
     * Retorna los datos de envio de la orden al laboratorio externo,
     * incluyendo nombre del laboratorio, fecha de envio, guia y fecha estimada.
     *
     * @param ordenId identificador de la orden
     * @return {@link OrdenesLabRowModel.EnvioRow} con datos del envio
     */
    public OrdenesLabRowModel.EnvioRow getEnvio(String ordenId) {
        return new OrdenesLabRowModel.EnvioRow(
                "LabVision",
                "10/04/2026",
                "GV-2026-0456",
                "DHL Express",
                "18/04/2026"
        );
    }

    /**
     * Retorna los datos de recepcion de la orden en sucursal, con fecha,
     * responsable, estado de conformidad y observaciones.
     *
     * @param ordenId identificador de la orden
     * @return {@link OrdenesLabRowModel.RecepcionRow} con datos de la recepcion
     */
    public OrdenesLabRowModel.RecepcionRow getRecepcion(String ordenId) {
        return new OrdenesLabRowModel.RecepcionRow(
                "-",
                "-",
                "-",
                "Pendiente"
        );
    }

    /**
     * Retorna las incidencias asociadas a una orden de laboratorio.
     * Solo retorna datos para la orden {@code LAB-207} (seed data);
     * para las demas retorna lista vacia.
     *
     * @param ordenId identificador de la orden
     * @return lista de {@link OrdenesLabRowModel.IncidenciaRow} con incidencias
     */
    public List<OrdenesLabRowModel.IncidenciaRow> getIncidencias(String ordenId) {
        if (ordenId != null && !"LAB-207".equals(ordenId)) {
            return List.of();
        }
        return new ArrayList<>(seedIncidencias);
    }

    /**
     * Retorna todas las incidencias para la vista de listado general.
     *
     * @return lista de todas las {@link OrdenesLabRowModel.IncidenciaRow}
     */
    public List<OrdenesLabRowModel.IncidenciaRow> getAllIncidencias() {
        return new ArrayList<>(seedIncidencias);
    }

    /**
     * Retorna el historico de ordenes de laboratorio filtrado por criterios
     * de busqueda, incluyendo ordenes completadas y en proceso.
     *
     * @param filters criterios de filtrado
     * @return lista de {@link OrdenesLabRowModel.HistoricoRow} filtradas
     */
    public List<OrdenesLabRowModel.HistoricoRow> getHistorico(OrdenesLabFilters filters) {
        return seedHistorico.stream()
                .filter(r -> matchesHistoricoFilters(r, filters))
                .collect(Collectors.toList());
    }

    /**
     * Construye un modelo de resumen para la orden de laboratorio seleccionada
     * a partir de la entidad {@link VentaOptica}.
     *
     * @param ventaOptica entidad de venta con datos de laboratorio
     * @return {@link OrdenesLabSummaryModel} con datos de la orden
     */
    public OrdenesLabSummaryModel buildSummary(VentaOptica ventaOptica) {
        return OrdenesLabSummaryModel.from(ventaOptica);
    }

    /**
     * Construye un modelo de resumen a partir de una fila de la cola de ordenes.
     *
     * @param row fila seleccionada en la tabla
     * @return {@link OrdenesLabSummaryModel} con datos de la orden
     */
    public OrdenesLabSummaryModel buildSummaryFromRow(OrdenesLabRowModel.ColaRow row) {
        return new OrdenesLabSummaryModel(
                row.referencia(),
                row.tipoTrabajo(),
                row.prioridad(),
                row.cliente(),
                "-",
                row.sucursal(),
                row.fechaPromesa(),
                "Standard",
                row.tipoTrabajo(),
                "Anti-reflejo",
                "62mm",
                row.estado(),
                row.laboratorio(),
                "-",
                row.fechaPromesa()
        );
    }

    /**
     * Retorna los estados distinct de ordenes de laboratorio para el combo de filtro.
     *
     * @return lista ordenada de estados (En produccion, Validada, Lista, etc.)
     */
    public List<String> getEstados() {
        return seedCola.stream()
                .map(OrdenesLabRowModel.ColaRow::estado)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Retorna los laboratorios distinct para el combo de filtro.
     *
     * @return lista ordenada de nombres de laboratorio
     */
    public List<String> getLaboratorios() {
        return seedCola.stream()
                .map(OrdenesLabRowModel.ColaRow::laboratorio)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Retorna las prioridades distinct para el combo de filtro.
     *
     * @return lista ordenada de prioridades (Alta, Normal)
     */
    public List<String> getPrioridades() {
        return seedCola.stream()
                .map(OrdenesLabRowModel.ColaRow::prioridad)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Retorna las sucursales distinct desde el {@link DemoStore} para el combo de filtro.
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

    // ---- Seed data initialization ----

    private List<OrdenesLabRowModel.ColaRow> initSeedCola() {
        List<OrdenesLabRowModel.ColaRow> rows = new ArrayList<>();
        rows.add(new OrdenesLabRowModel.ColaRow(
                "LAB-203", "Sofia Ramirez", "Lentes monofocales",
                "08/04/2026", "16/04/2026", "En produccion",
                "LabVision", "Alta", "Matriz Centro"
        ));
        rows.add(new OrdenesLabRowModel.ColaRow(
                "LAB-204", "Luis Andrade", "Progresivos",
                "09/04/2026", "18/04/2026", "Validada",
                "OptiLab Pro", "Normal", "Matriz Centro"
        ));
        rows.add(new OrdenesLabRowModel.ColaRow(
                "LAB-205", "Ana Vera", "Cambio de lente",
                "10/04/2026", "15/04/2026", "Lista",
                "LabVision", "Normal", "Sucursal Norte"
        ));
        rows.add(new OrdenesLabRowModel.ColaRow(
                "LAB-206", "Carlos Mendoza", "Ajuste",
                "11/04/2026", "14/04/2026", "Retrasada",
                "OptiLab Pro", "Alta", "Matriz Centro"
        ));
        rows.add(new OrdenesLabRowModel.ColaRow(
                "LAB-207", "Diana Velez", "Lentes bifocales",
                "12/04/2026", "20/04/2026", "Con incidencia",
                "LabVision", "Normal", "Sucursal Norte"
        ));
        return rows;
    }

    private List<OrdenesLabRowModel.EtapaRow> initSeedEtapas() {
        List<OrdenesLabRowModel.EtapaRow> rows = new ArrayList<>();
        rows.add(new OrdenesLabRowModel.EtapaRow("08/04/2026", "Recibida", "Recepcion Matriz", "Orden ingresada al sistema"));
        rows.add(new OrdenesLabRowModel.EtapaRow("09/04/2026", "Validada", "Tecnico Lopez", "Receta verificada, materiales disponibles"));
        rows.add(new OrdenesLabRowModel.EtapaRow("10/04/2026", "En produccion", "LabVision", "Corte de lente en proceso"));
        rows.add(new OrdenesLabRowModel.EtapaRow("", "En control", "-", "Pendiente"));
        rows.add(new OrdenesLabRowModel.EtapaRow("", "Lista", "-", "Pendiente"));
        rows.add(new OrdenesLabRowModel.EtapaRow("", "Enviada", "-", "Pendiente"));
        rows.add(new OrdenesLabRowModel.EtapaRow("", "Recibida en sucursal", "-", "Pendiente"));
        rows.add(new OrdenesLabRowModel.EtapaRow("", "Entregada", "-", "Pendiente"));
        return rows;
    }

    private List<OrdenesLabRowModel.IncidenciaRow> initSeedIncidencias() {
        List<OrdenesLabRowModel.IncidenciaRow> rows = new ArrayList<>();
        rows.add(new OrdenesLabRowModel.IncidenciaRow(
                "12/04/2026", "Lente rayado", "Abierta", "LabVision", "En retrabajo"
        ));
        return rows;
    }

    private List<OrdenesLabRowModel.HistoricoRow> initSeedHistorico() {
        List<OrdenesLabRowModel.HistoricoRow> rows = new ArrayList<>();
        rows.add(new OrdenesLabRowModel.HistoricoRow(
                "LAB-190", "Maria Torres", "01/03/2026", "10/03/2026", "Entregada",
                "LabVision", "Matriz Centro"
        ));
        rows.add(new OrdenesLabRowModel.HistoricoRow(
                "LAB-195", "Jose Hernandez", "05/03/2026", "12/03/2026", "Entregada",
                "OptiLab Pro", "Sucursal Norte"
        ));
        rows.add(new OrdenesLabRowModel.HistoricoRow(
                "LAB-200", "Carmen Delgado", "20/03/2026", "28/03/2026", "Entregada",
                "LabVision", "Matriz Centro"
        ));
        rows.add(new OrdenesLabRowModel.HistoricoRow(
                "LAB-203", "Sofia Ramirez", "08/04/2026", "16/04/2026", "En produccion",
                "LabVision", "Matriz Centro"
        ));
        return rows;
    }

    // ---- Filter matching ----

    private boolean matchesFilters(OrdenesLabRowModel.ColaRow row, OrdenesLabFilters filters) {
        if (!FilterSupport.matchesText(filters.getSearchText(),
                row.referencia(), row.cliente(), row.tipoTrabajo(), row.laboratorio())) {
            return false;
        }
        if (!FilterSupport.matchesExact(row.estado(), filters.getEstado())) {
            return false;
        }
        if (!FilterSupport.matchesExact(row.laboratorio(), filters.getLaboratorio())) {
            return false;
        }
        if (!FilterSupport.matchesExact(row.sucursal(), filters.getSucursal())) {
            return false;
        }
        if (!FilterSupport.matchesExact(row.prioridad(), filters.getPrioridad())) {
            return false;
        }
        if (!FilterSupport.inRange(row.ingreso(), filters.getDesde(), filters.getHasta())) {
            return false;
        }
        return true;
    }

    private boolean matchesHistoricoFilters(OrdenesLabRowModel.HistoricoRow row, OrdenesLabFilters filters) {
        if (!FilterSupport.matchesText(filters.getSearchText(),
                row.referencia(), row.cliente(), row.laboratorio())) {
            return false;
        }
        if (!FilterSupport.matchesExact(row.estadoFinal(), filters.getEstado())) {
            return false;
        }
        if (!FilterSupport.matchesExact(row.laboratorio(), filters.getLaboratorio())) {
            return false;
        }
        if (!FilterSupport.matchesExact(row.sucursal(), filters.getSucursal())) {
            return false;
        }
        if (!FilterSupport.inRange(row.ingreso(), filters.getDesde(), filters.getHasta())) {
            return false;
        }
        return true;
    }
}
