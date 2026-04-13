package com.marcosmoreira.opticademo.modules.inicio;

import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.shared.ui.model.KpiCardModel;

import java.util.List;

/**
 * Facade para el modulo de Inicio (Dashboard principal).
 * <p>
 * Este facade actua como capa de abstraccion entre el {@link DemoStore} y las sub-vistas
 * del panel de inicio, proporcionando datos de resumen operativo para la visualizacion
 * inmediata del estado del negocio. Utiliza exclusivamente datos estaticos embebidos
 * (seed data) para la demostracion del prototipo.
 * </p>
 * <p>
 * Sub-vistas que alimenta:
 * <ul>
 *   <li><b>KPI Cards:</b> tarjetas de indicadores clave (citas, ventas, ordenes, entregas, cobros, stock).</li>
 *   <li><b>Proximas Citas:</b> tabla de citas del dia con hora, cliente, tipo de atencion y profesional.</li>
 *   <li><b>Alertas Pendientes:</b> listado de alertas operativas (retrasos, stock bajo, cobros).</li>
 *   <li><b>Actividad Reciente:</b> log cronologico de acciones recientes del sistema.</li>
 * </ul>
 * </p>
 * <p>
 * Patron de flujo de datos: las sub-vistas invocan los metodos de consulta del facade,
 * este construye los modelos de fila a partir de datos estaticos y los retorna directamente
 * para su enlace con los controles JavaFX de la interfaz.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see DemoStore
 * @see KpiCardModel
 */
public class InicioFacade {

    private final DemoStore store;

    /**
     * Construye el facade con referencia al almacén de datos de demostración.
     *
     * @param store instancia del {@link DemoStore} que contiene los datos seed del sistema
     */
    public InicioFacade(DemoStore store) {
        this.store = store;
    }

    /**
     * Retorna las seis tarjetas KPI del dashboard principal con indicadores
     * clave del estado operativo: citas del dia, ventas, ordenes pendientes,
     * trabajos listos para entregar, montos por cobrar y stock critico.
     *
     * @return lista de {@link KpiCardModel} con titulo, valor, subtexto e icono
     */
    public List<KpiCardModel> getKpiCards() {
        return List.of(
                new KpiCardModel("Citas de hoy", "18", "+2 vs ayer", "\uD83D\uDCC5"),
                new KpiCardModel("Ventas de hoy", "$1,240.50", "5 ventas", "\uD83D\uDCB0"),
                new KpiCardModel("Ordenes pendientes", "11", "3 urgentes", "\uD83D\uDCCB"),
                new KpiCardModel("Listas para entregar", "6", "2 con cobro pendiente", "\uD83D\uDCE6"),
                new KpiCardModel("Por cobrar", "$420.00", "3 abonos pendientes", "\uD83D\uDCB3"),
                new KpiCardModel("Stock critico", "4", "2 monturas, 2 lentes", "\u26A0\uFE0F")
        );
    }

    /**
     * Retorna la lista de citas proximas programadas para el dia actual,
     * incluyendo hora, nombre del cliente, tipo de atencion, estado de
     * confirmacion y profesional asignado.
     *
     * @return lista de {@link InicioRowModel.CitaRow} con las citas del dia
     */
    public List<InicioRowModel.CitaRow> getProximasCitas() {
        return List.of(
                new InicioRowModel.CitaRow("09:00", "Sofia Ramirez", "Examen visual", "Confirmada", "Dra. Salazar"),
                new InicioRowModel.CitaRow("09:30", "Juan Cede\u00F1o", "Ajuste", "Pendiente", "Tec. Morales"),
                new InicioRowModel.CitaRow("10:00", "Maria Torres", "Entrega de lentes", "Confirmada", "Dra. Salazar"),
                new InicioRowModel.CitaRow("10:30", "Carlos Zamora", "Revisión de receta", "Confirmada", "Dr. Vega"),
                new InicioRowModel.CitaRow("11:00", "Ana Gutierrez", "Examen visual", "En espera", "Dra. Salazar"),
                new InicioRowModel.CitaRow("11:30", "Pedro Murillo", "Ajuste de montura", "Pendiente", "Tec. Morales")
        );
    }

    /**
     * Retorna las alertas pendientes del dashboard, que incluyen retrasos de
     * ordenes de laboratorio, trabajos listos para notificar, saldos pendientes
     * de cobro, stock critico y coberturas de seguros por vencer.
     *
     * @return lista de {@link InicioRowModel.AlertaRow} con tipo, texto descriptivo y nivel de severidad
     */
    public List<InicioRowModel.AlertaRow> getAlertas() {
        return List.of(
                new InicioRowModel.AlertaRow("Lab order delayed", "Orden #LAB-0042 retrasada — lente progressivo pendiente", "warning"),
                new InicioRowModel.AlertaRow("Work ready", "Orden #ORD-0118 lista desde ayer — notificar cliente", "info"),
                new InicioRowModel.AlertaRow("Pending balance", "Abono pendiente de $85.00 — Cliente: R. Jimenez", "warning"),
                new InicioRowModel.AlertaRow("Stock bajo", "Montura Ray-Ban RB5154 — solo 1 unidad disponible", "danger"),
                new InicioRowModel.AlertaRow("Stock bajo", "Lentes fotocromaticos 1.60 — inventario critico", "danger"),
                new InicioRowModel.AlertaRow("Cobertura por vencer", "Seguro visual de L. Chaves vence en 5 dias", "info")
        );
    }

    /**
     * Retorna el registro de actividad reciente del sistema, mostrando
     * eventos como ventas registradas, ordenes completadas, recetas actualizadas,
     * entregas completadas, ajustes de stock y nuevas citas agendadas.
     *
     * @return lista de {@link InicioRowModel.ActividadRow} con descripciones de eventos recientes
     */
    public List<InicioRowModel.ActividadRow> getActividadReciente() {
        return List.of(
                new InicioRowModel.ActividadRow("Nueva venta registrada — Venta #V-0087 — $245.00 — 08:45"),
                new InicioRowModel.ActividadRow("Orden marcada como lista — Orden #ORD-0118 — 08:30"),
                new InicioRowModel.ActividadRow("Receta actualizada — Paciente: Sofia Ramirez — 08:15"),
                new InicioRowModel.ActividadRow("Entrega completada — Orden #ORD-0112 — Cliente: M. Torres — 08:00"),
                new InicioRowModel.ActividadRow("Stock ajustado — Montura Dolce & Gabbana DG5031 — -2 unidades — 07:50"),
                new InicioRowModel.ActividadRow("Nueva cita agendada — 14:00 — Carlos Zamora — Examen visual — 07:40")
        );
    }
}
