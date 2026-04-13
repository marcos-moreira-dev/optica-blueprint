package com.marcosmoreira.opticademo.modules.reportes;

import java.util.ArrayList;
import java.util.List;

/**
 * Facade that provides all report data for the Reportes module.
 * Demo/static data for now -- no business logic.
 */
public class ReportesFacade {

    // ------------------------------------------------------------------ Periodos y categorias

    public List<String> getPeriodos() {
        return List.of(
                "Abril 2026",
                "Marzo 2026",
                "Febrero 2026",
                "Enero 2026",
                "Diciembre 2025",
                "Noviembre 2025"
        );
    }

    public List<String> getCategorias() {
        return List.of(
                "Todas",
                "Lentes oftalmicos",
                "Lentes de sol",
                "Lentes de contacto",
                "Monturas",
                "Accesorios"
        );
    }

    // ------------------------------------------------------------------ Resumen ejecutivo (sub-view 1)

    public List<ReportesRowModel.KpiResumenRow> getKpisResumen() {
        List<ReportesRowModel.KpiResumenRow> list = new ArrayList<>();

        list.add(ReportesRowModel.KpiResumenRow.seed(
                "Ventas", "Ventas del periodo", "$8,420", "En meta"
        ));
        list.add(ReportesRowModel.KpiResumenRow.seed(
                "Ventas", "Ticket promedio", "$96.30", "Arriba"
        ));
        list.add(ReportesRowModel.KpiResumenRow.seed(
                "Operaciones", "Ordenes activas", "24", "Normal"
        ));
        list.add(ReportesRowModel.KpiResumenRow.seed(
                "Operaciones", "Trabajos retrasados", "4", "Critico"
        ));
        list.add(ReportesRowModel.KpiResumenRow.seed(
                "Inventario", "Stock critico", "11", "Critico"
        ));
        list.add(ReportesRowModel.KpiResumenRow.seed(
                "Retencion", "Recalls pendientes", "18", "Atencion"
        ));
        list.add(ReportesRowModel.KpiResumenRow.seed(
                "Cobros", "Cobros pendientes", "$412", "Atencion"
        ));
        list.add(ReportesRowModel.KpiResumenRow.seed(
                "Agenda", "Utilizacion agenda", "78%", "Abajo"
        ));

        return list;
    }

    // ------------------------------------------------------------------ Ventas y desempeno comercial (sub-view 2)

    public List<ReportesRowModel.ComercialRow> getComercial() {
        List<ReportesRowModel.ComercialRow> list = new ArrayList<>();

        list.add(ReportesRowModel.ComercialRow.seed(
                "Lentes oftalmicos", "$3,790", "42", "45%", "Mejor categoria del mes"
        ));
        list.add(ReportesRowModel.ComercialRow.seed(
                "Monturas", "$2,100", "28", "25%", "Demanda estable"
        ));
        list.add(ReportesRowModel.ComercialRow.seed(
                "Lentes de sol", "$1,260", "15", "15%", "Crecimiento por temporada"
        ));
        list.add(ReportesRowModel.ComercialRow.seed(
                "Lentes de contacto", "$842", "20", "10%", "Alta recurrencia"
        ));
        list.add(ReportesRowModel.ComercialRow.seed(
                "Accesorios", "$428", "35", "5%", "Venta cruzada activa"
        ));

        return list;
    }

    // ------------------------------------------------------------------ Inventario y rotacion (sub-view 3)

    public List<ReportesRowModel.RotacionRow> getRotacion() {
        List<ReportesRowModel.RotacionRow> list = new ArrayList<>();

        list.add(ReportesRowModel.RotacionRow.seed(
                "MNT-001", "Montura Ray-Ban RB5154", "Monturas", "4.2",
                "14/04/2026", "Alta rotacion", "Best seller, reabastecer"
        ));
        list.add(ReportesRowModel.RotacionRow.seed(
                "LNT-045", "Lente oftalmico Varilux", "Lentes oftalmicos", "3.8",
                "13/04/2026", "Alta rotacion", "Demanda constante"
        ));
        list.add(ReportesRowModel.RotacionRow.seed(
                "LNT-089", "Lente sol Oakley Holbrook", "Lentes de sol", "2.1",
                "10/04/2026", "Media", "Creciendo por temporada"
        ));
        list.add(ReportesRowModel.RotacionRow.seed(
                "ACC-012", "Estuche premium negro", "Accesorios", "0.8",
                "02/04/2026", "Lento", "Revisar pricing"
        ));
        list.add(ReportesRowModel.RotacionRow.seed(
                "LDC-003", "ACUVUE OASYS 6-pack", "Lentes de contacto", "5.1",
                "15/04/2026", "Alta rotacion", "Stock critico, ordenar"
        ));
        list.add(ReportesRowModel.RotacionRow.seed(
                "MNT-078", "Montura vintage sin marca", "Monturas", "0.3",
                "18/03/2026", "Envejecido", "Sin ventas en 25 dias"
        ));
        list.add(ReportesRowModel.RotacionRow.seed(
                "LNT-102", "Lente progresivo basico", "Lentes oftalmicos", "1.5",
                "08/04/2026", "Media", "Rotacion aceptable"
        ));
        list.add(ReportesRowModel.RotacionRow.seed(
                "ACC-034", "Microfibra x10 pack", "Accesorios", "0.5",
                "25/03/2026", "Lento", "Stock abundante"
        ));

        return list;
    }

    // ------------------------------------------------------------------ Agenda y atencion (sub-view 4)

    public List<ReportesRowModel.AgendaRow> getAgenda() {
        List<ReportesRowModel.AgendaRow> list = new ArrayList<>();

        list.add(ReportesRowModel.AgendaRow.seed(
                "Citas registradas", "124", "130", "En meta", "Cerca del objetivo"
        ));
        list.add(ReportesRowModel.AgendaRow.seed(
                "Utilizacion", "78%", "85%", "Abajo", "5% debajo de la meta"
        ));
        list.add(ReportesRowModel.AgendaRow.seed(
                "No-shows", "8", "5", "Critico", "3 por encima del limite"
        ));
        list.add(ReportesRowModel.AgendaRow.seed(
                "Conversion cita-venta", "62%", "60%", "Arriba", "Supera meta en 2pp"
        ));

        return list;
    }

    // ------------------------------------------------------------------ Laboratorio y cumplimiento (sub-view 5)

    public List<ReportesRowModel.LaboratorioRow> getLaboratorio() {
        List<ReportesRowModel.LaboratorioRow> list = new ArrayList<>();

        list.add(ReportesRowModel.LaboratorioRow.seed(
                "Ordenes creadas", "48", "Normal", "Volumen dentro del rango"
        ));
        list.add(ReportesRowModel.LaboratorioRow.seed(
                "Entregas a tiempo", "92%", "Arriba", "Meta 90% superada"
        ));
        list.add(ReportesRowModel.LaboratorioRow.seed(
                "Retrasos", "4", "Critico", "4 ordenes fuera de plazo"
        ));
        list.add(ReportesRowModel.LaboratorioRow.seed(
                "Retrabajos", "3", "Atencion", "3 casos de ajuste requerida"
        ));

        return list;
    }

    // ------------------------------------------------------------------ Cobros y cartera (sub-view 6)

    public List<ReportesRowModel.CarteraRow> getCartera() {
        List<ReportesRowModel.CarteraRow> list = new ArrayList<>();

        list.add(ReportesRowModel.CarteraRow.seed(
                "VE-012", "Diana Velez", "$75.00", "14/04/2026",
                "Con saldo pendiente", "Norte Mall"
        ));
        list.add(ReportesRowModel.CarteraRow.seed(
                "VE-015", "Carlos Mendoza", "$120.00", "10/04/2026",
                "Vencido", "Matriz Centro"
        ));
        list.add(ReportesRowModel.CarteraRow.seed(
                "VE-018", "Roberto Guzman", "$35.00", "08/04/2026",
                "Vencido", "Matriz Centro"
        ));
        list.add(ReportesRowModel.CarteraRow.seed(
                "VE-020", "Maria Leon", "$92.00", "12/04/2026",
                "Abono parcial", "Norte Mall"
        ));
        list.add(ReportesRowModel.CarteraRow.seed(
                "VE-022", "Juan Cedeno", "$45.00", "15/04/2026",
                "Con saldo pendiente", "Matriz Centro"
        ));
        list.add(ReportesRowModel.CarteraRow.seed(
                "VE-025", "Ana Vera", "$45.00", "13/04/2026",
                "Al dia", "Matriz Centro"
        ));

        return list;
    }

    // ------------------------------------------------------------------ Seguimiento y retencion (sub-view 7)

    public List<ReportesRowModel.RetencionRow> getRetencion() {
        List<ReportesRowModel.RetencionRow> list = new ArrayList<>();

        list.add(ReportesRowModel.RetencionRow.seed(
                "Sofia Ramirez", "Recall anual", "Pendiente",
                "18/04/2026", "No contactada", "Matriz Centro"
        ));
        list.add(ReportesRowModel.RetencionRow.seed(
                "Carlos Mendoza", "Revision de receta", "Vencido",
                "20/04/2026", "Receta vencida", "Norte Mall"
        ));
        list.add(ReportesRowModel.RetencionRow.seed(
                "Ana Vera", "Revision semestral", "Recordatorio enviado",
                "22/04/2026", "Cliente confirmo asistencia", "Matriz Centro"
        ));
        list.add(ReportesRowModel.RetencionRow.seed(
                "Luis Andrade", "Control lente de contacto", "Pendiente",
                "25/04/2026", "No contactado", "Norte Mall"
        ));
        list.add(ReportesRowModel.RetencionRow.seed(
                "Maria Leon", "Recall lentes de sol", "Resuelto",
                "15/04/2026", "Revision completada", "Matriz Centro"
        ));
        list.add(ReportesRowModel.RetencionRow.seed(
                "Pedro Rivas", "Revision anual", "No retirado",
                "10/04/2026", "Pedido listo sin retirar", "Norte Mall"
        ));

        return list;
    }

    // ------------------------------------------------------------------ Build summary by indicator

    public ReportesSummaryModel buildSummary(String indicador) {
        return switch (indicador != null ? indicador : "") {
            case "ventas", "comercial" -> ReportesSummaryModel.comercialSeed();
            case "inventario", "rotacion" -> ReportesSummaryModel.rotacionSeed();
            case "agenda", "atencion" -> ReportesSummaryModel.agendaSeed();
            case "laboratorio", "cumplimiento" -> ReportesSummaryModel.laboratorioSeed();
            case "cobros", "cartera" -> ReportesSummaryModel.carteraSeed();
            case "retencion", "seguimiento" -> ReportesSummaryModel.retencionSeed();
            default -> ReportesSummaryModel.resumenSeed();
        };
    }
}
