package com.marcosmoreira.opticademo.modules.reportes;

/**
 * Summary model for the right-hand KPI/report summary panel in the Reportes module.
 */
public record ReportesSummaryModel(
        String indicador,
        String categoria,
        String sucursal,
        String valorPrincipal,
        String variacionBreve,
        String periodo,
        String ultimaActualizacion,
        String observacionGerencial,
        String accionSugerida
) {

    /**
     * Creates a demo summary for the Resumen ejecutivo sub-view.
     */
    public static ReportesSummaryModel resumenSeed() {
        return new ReportesSummaryModel(
                "Ventas del periodo",
                "General",
                "Todas",
                "$8,420",
                "+12% vs periodo anterior",
                "Abril 2026",
                "16/04/2026 14:30",
                "Ventas por encima de la meta mensual",
                "Mantener promocion de monturas hasta fin de mes"
        );
    }

    /**
     * Creates a demo summary for the Ventas sub-view.
     */
    public static ReportesSummaryModel comercialSeed() {
        return new ReportesSummaryModel(
                "Participacion lentes",
                "Ventas",
                "Matriz Centro",
                "45%",
                "+3% vs mes anterior",
                "Abril 2026",
                "16/04/2026 14:30",
                "Lentes siguen siendo el motor principal",
                "Reforzar capacitacion de asesores en lentes premium"
        );
    }

    /**
     * Creates a demo summary for the Inventario sub-view.
     */
    public static ReportesSummaryModel rotacionSeed() {
        return new ReportesSummaryModel(
                "Stock critico",
                "Inventario",
                "Todas",
                "11 items",
                "+3 vs semana anterior",
                "Abril 2026",
                "16/04/2026 14:30",
                "Varios items de alta rotacion en nivel bajo",
                "Ordenar reabastecimiento urgente de best sellers"
        );
    }

    /**
     * Creates a demo summary for the Agenda sub-view.
     */
    public static ReportesSummaryModel agendaSeed() {
        return new ReportesSummaryModel(
                "Utilizacion de agenda",
                "Agenda",
                "Matriz Centro",
                "78%",
                "-5% vs meta objetivo",
                "Abril 2026",
                "16/04/2026 14:30",
                "Utilizacion por debajo del 85% objetivo",
                "Promocionar citas de recall para llenar espacios"
        );
    }

    /**
     * Creates a demo summary for the Laboratorio sub-view.
     */
    public static ReportesSummaryModel laboratorioSeed() {
        return new ReportesSummaryModel(
                "Ordenes a tiempo",
                "Laboratorio",
                "Todas",
                "92%",
                "+2% vs mes anterior",
                "Abril 2026",
                "16/04/2026 14:30",
                "Buen desempeno en tiempos de entrega",
                "Monitorear ordenes con retrabajo recurrente"
        );
    }

    /**
     * Creates a demo summary for the Cobros sub-view.
     */
    public static ReportesSummaryModel carteraSeed() {
        return new ReportesSummaryModel(
                "Cobros pendientes",
                "Cobros",
                "Todas",
                "$412",
                "-8% vs mes anterior",
                "Abril 2026",
                "16/04/2026 14:30",
                "Cartera controlada pero con casos vencidos",
                "Escalar cobranza de casos con mas de 30 dias"
        );
    }

    /**
     * Creates a demo summary for the Retencion sub-view.
     */
    public static ReportesSummaryModel retencionSeed() {
        return new ReportesSummaryModel(
                "Recalls pendientes",
                "Retencion",
                "Todas",
                "18 casos",
                "+2 vs semana anterior",
                "Abril 2026",
                "16/04/2026 14:30",
                "Varios recalls proximos a vencer",
                "Iniciar campana de recordatorios masivos"
        );
    }
}
