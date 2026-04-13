package com.marcosmoreira.opticademo.modules.inicio;

import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.shared.ui.model.KpiCardModel;

import java.util.List;

/**
 * Facade that queries the DemoStore and returns seed data for the Inicio dashboard.
 * No business logic — just view-facing data assembly.
 */
public class InicioFacade {

    private final DemoStore store;

    public InicioFacade(DemoStore store) {
        this.store = store;
    }

    /**
     * Returns the six KPI cards for the dashboard.
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
     * Returns upcoming appointments for today.
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
     * Returns pending alerts for the dashboard.
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
     * Returns recent activity log entries.
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
