# Reportes - Documentación Técnica

## Resumen
Módulo de panel de indicadores y análisis gerencial. Consolida KPIs de ventas, inventario, agenda, laboratorio, cobros y retención en siete sub-vistas analíticas especializadas. Proporciona tablas de datos, tarjetas de indicadores y alertas gerenciales para la toma de decisiones.

## Estructura de Clases
| Clase | Tipo | Responsabilidad |
|-------|------|-----------------|
| `ReportesController` | Controller | Configuración JavaFX, toggle de sub-vistas, binding de tablas, actualización del panel resumen |
| `ReportesFacade` | Facade | Seed data estático, cálculo de indicadores, construcción de resúmenes por tipo |
| `ReportesFilters` | Model | Criterios de filtrado (periodo, sucursal, categoria, desde, hasta, soloDatosCriticos) |
| `ReportesRowModel` | Model | Registros inmutables de filas para cada sub-vista analítica |
| `ReportesSummaryModel` | Model | Resumen del KPI seleccionado para el panel derecho |

### Sub-clases (inner records en ReportesRowModel)
| Record | Columnas |
|--------|----------|
| `KpiResumenRow` | area, indicador, valor, estado |
| `ComercialRow` | categoria, ventas, unidades, participacion, observacion |
| `RotacionRow` | referencia, nombre, categoria, rotacion, ultimaSalida, estado, observacion |
| `AgendaRow` | indicador, valor, meta, estado, observacion |
| `LaboratorioRow` | indicador, valor, estado, observacion |
| `CarteraRow` | orden, cliente, saldo, ultimoPago, estado, sucursal |
| `RetencionRow` | cliente, tipo, estado, fechaObjetivo, resultado, sucursal |

## Sub-vistas
| # | Sub-vista | Toggle Button | Tabla Principal | KPIs |
|---|-----------|--------------|-----------------|------|
| 1 | Resumen ejecutivo | `btnResumen` | `resumenTable` | Ventas periodo, Ticket promedio, Órdenes activas, Trabajos retrasados, Stock crítico, Recalls pendientes, Cobros pendientes, Utilización agenda |
| 2 | Ventas y desempeño comercial | `btnVentas` | `comercialTable` | Ventas totales, Ticket promedio, Revenue por cliente, Capture rate |
| 3 | Inventario y rotación | `btnInventario` | `rotacionTable` | Best sellers, Lentos, Envejecido, Crítico |
| 4 | Agenda y atención | `btnAgenda` | `agendaTable` | Citas registradas, Utilización, No-shows, Conversión |
| 5 | Laboratorio y cumplimiento | `btnLaboratorio` | `laboratorioTable` | Órdenes creadas, Entregas a tiempo, Retrasos, Retrabajos |
| 6 | Cobros y cartera | `btnCobros` | `carteraTable` | Cobrado, Pendiente, Abono parcial, Vencidos |
| 7 | Seguimiento y retención | `btnRetencion` | `retencionTable` | Recalls pendientes, Recordatorios enviados, No retirados, Resueltos |

## Flujo de Datos
```
[Usuario selecciona periodo/sucursal/categoría] → refreshCurrentView()
       ↓
ReportesFilters actualizado (periodo, sucursal, categoria, fechas, soloDatosCriticos)
       ↓
showSubView(subview) → switch sub-vista activa
       ↓
facade.getKpisResumen() / getComercial() / getRotacion() / etc.
       ↓
List<RowModel> → ObservableList → tableView.setItems()
       ↓
[Usuario selecciona fila] → onResumenRowSelected(row)
       ↓
facade.buildSummary(indicador) → ReportesSummaryModel.{resumenSeed/comercialSeed/...}
       ↓
updateSummaryPanel() → Labels del panel derecho actualizados
```

## Filtros Disponibles
| Filtro | Control | Valores posibles | Fuente |
|--------|---------|------------------|--------|
| Periodo | `ComboBox<String>` | Abril 2026, Marzo 2026, Febrero 2026, Enero 2026, Diciembre 2025, Noviembre 2025 | `facade.getPeriodos()` |
| Sucursal | `ComboBox<String>` | Todas, Matriz Centro, Norte Mall | Hardcoded en Controller |
| Categoría | `ComboBox<String>` | Todas, Lentes oftálmicos, Lentes de sol, Lentes de contacto, Monturas, Accesorios | `facade.getCategorias()` |
| Desde | `DatePicker` | Fecha inicio del rango | Listener en Controller |
| Hasta | `DatePicker` | Fecha fin del rango | Listener en Controller |
| Solo datos críticos | `CheckBox` | true/false | Filtro booleano |

## Panel Resumen
El `ReportesSummaryModel` muestra los siguientes campos del indicador seleccionado:
- **indicador**: nombre del KPI visualizado (ej. "Ventas del periodo", "Participación lentes")
- **categoria**: categoría del reporte (General, Ventas, Inventario, Agenda, Laboratorio, Cobros, Retención)
- **sucursal**: alcance del indicador (Todas, Matriz Centro, Norte Mall)
- **valorPrincipal**: valor principal del indicador (ej. "$8,420", "45%", "11 items")
- **variacionBreve**: variación respecto al periodo anterior (ej. "+12% vs periodo anterior")
- **periodo**: periodo de tiempo del reporte (ej. "Abril 2026")
- **ultimaActualizacion**: fecha y hora de la última actualización
- **observacionGerencial**: observación gerencial sobre el resultado
- **accionSugerida**: acción sugerida derivada del análisis

El facade contiene 7 métodos factory estáticos: `resumenSeed()`, `comercialSeed()`, `rotacionSeed()`, `agendaSeed()`, `laboratorioSeed()`, `carteraSeed()`, `retencionSeed()`.

## Patrones de Diseño
- **Facade Pattern**: `ReportesFacade` centraliza agregación de datos y cálculo de indicadores
- **MVC (Model-View-Controller)**: separación entre FXML, Controller y modelos de datos
- **Record Pattern**: modelos de fila como records inmutables con JavaFX properties
- **Toggle Group Pattern**: sub-vistas analíticas mutuamente exclusivas
- **Alertas Gerenciales Pattern**: construcción dinámica de alertas basada en KPIs críticos ("Critico", "Atencion", "Abajo")
- **Strategy Pattern (implícito)**: `buildSummary(String indicador)` selecciona estrategia de resumen según tipo
- **Pagination Pattern**: paginación en resumen ejecutivo con `PaginationBarController`

## Integración con Otros Módulos
| Módulo | Punto de integración | Descripción |
|--------|---------------------|-------------|
| Ventas | Ventas y desempeño | Agrega datos de ventas por categoría de producto |
| Inventario | Rotación de inventario | Consume datos de stock y rotación de productos |
| Agenda | Agenda y atención | Métricas de citas, utilización y conversión |
| Laboratorio | Laboratorio y cumplimiento | Órdenes creadas, entregas a tiempo, retrabajos |
| Seguimiento | Seguimiento y retención | Recalls, revisiones y estado de retención |
| Cobros | Cobros y cartera | Datos de cartera vencida y saldos pendientes |
| Sucursales | Filtro global | Todas las sub-vistas filtran por sucursal activa |
