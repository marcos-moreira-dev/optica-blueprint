# Inicio (Dashboard) - Documentación Técnica

## Resumen
- Dashboard principal que presenta un resumen ejecutivo del estado del negocio: KPIs, citas del día, alertas operativas, accesos rápidos y actividad reciente.
- 1 sub-vista (dashboard unificado con múltiples secciones).
- Patrón arquitectónico: **Facade + Componentes Reutilizables** (KpiCard, StatusBadge).

## Estructura de Clases

| Clase | Responsabilidad |
|-------|----------------|
| `InicioController.java` | Orquesta la carga del dashboard: combo de sucursal, tarjetas KPI, tabla de citas, alertas, accesos rápidos y actividad reciente. Delega toda la lógica a `InicioFacade`. |
| `InicioFacade.java` | Capa de abstracción entre `DemoStore` y las sub-vistas. Retorna datos seed estáticos para KPIs, citas, alertas y actividad reciente. |
| `InicioRowModel.java` | Contiene 3 records inner: `CitaRow`, `AlertaRow`, `ActividadRow`. Cada uno alimenta una sección diferente del dashboard. |

**No existen** `InicioFilters.java` ni `InicioSummaryModel.java` — el dashboard no aplica filtros complejos ni panel lateral de resumen.

### Inner Records de InicioRowModel

| Record | Campos | Tabla/Sección que alimenta |
|--------|--------|---------------------------|
| `CitaRow` | hora, cliente, atencion, estado, profesional | `citasTable` (TableView de próximas citas) |
| `AlertaRow` | tipo, texto, estado | `alertasContainer` (VBox de alertas operativas) |
| `ActividadRow` | texto | `actividadContainer` (VBox de actividad reciente) |

## Sub-vistas

| Sub-vista | ToggleButton | Qué datos muestra | Qué RowModel usa |
|-----------|-------------|-------------------|------------------|
| Dashboard Principal | N/A (sección única) | 6 KPI cards, tabla de citas, alertas, accesos rápidos, actividad reciente | `CitaRow`, `AlertaRow`, `ActividadRow` |

## Flujo de Datos

```
Controller.initialize()
    → Obtiene DemoStore desde App.getDemoStore()
    → Crea InicioFacade(store)
    → populateSucursalCombo() — llena combo con sucursales del store
    → loadKpiCards() — facade.getKpiCards() → 6 KpiCardModel → GridPane
    → loadProximasCitas() — facade.getProximasCitas() → List<CitaRow> → TableView
    → loadAlertas() — facade.getAlertas() → List<AlertaRow> → VBox con badges
    → loadActividadReciente() — facade.getActividadReciente() → List<ActividadRow> → VBox
    → buildAccesosRapidos() — 6 botones de acceso rápido → GridPane
```

## Filtros Disponibles

| Filtro | Tipo | Opciones | Cómo filtra el Facade |
|--------|------|----------|----------------------|
| Sucursal | ComboBox | "Todas" + nombres de sucursales del `DemoStore` | En la demo actual, los datos seed son globales; el filtro de sucursal está preparado pero no aplicado activamente en el facade. |

## Panel Resumen (Derecho)

No aplica panel lateral derecho. El dashboard actúa como panel resumen global del sistema.

### Tarjetas KPI (Panel Central)
El `InicioFacade.getKpiCards()` retorna 6 `KpiCardModel`:

| KPI | Valor | Subtítulo |
|-----|-------|-----------|
| Citas de hoy | 18 | +2 vs ayer |
| Ventas de hoy | $1,240.50 | 5 ventas |
| Órdenes pendientes | 11 | 3 urgentes |
| Listas para entregar | 6 | 2 con cobro pendiente |
| Por cobrar | $420.00 | 3 abonos pendientes |
| Stock crítico | 4 | 2 monturas, 2 lentes |

## Patrones de Diseño Aplicados

| Patrón | Descripción |
|--------|-------------|
| **Facade** | `InicioFacade` abstrae el acceso a `DemoStore`, retornando modelos de fila ya construidos. |
| **Componentes Reutilizables** | Usa `KpiCardController`, `StatusBadgeController` como componentes UI independientes. |
| **Factory** | `ComboBoxFactory.createFilterCombo()` y `TableViewFactory.setupColumn()` para creación declarativa de controles. |
| **Cell Factory personalizado** | `colEstado` usa `TableCell` custom para renderizar `StatusBadge` según el valor (confirmada→success, pendiente→warning, etc.). |
| **MVVM-like** | Los `record` de `InicioRowModel` actúan como ViewModels inmutables con `StringProperty` para binding. |

## Integración con Otros Módulos

| Módulo | Dirección | Descripción |
|--------|-----------|-------------|
| `Agenda` | Inicio ← Agenda | Las citas del dashboard reflejan datos del módulo Agenda. |
| `VentaOptica` | Inicio ← VentaOptica | Los KPIs de ventas y órdenes provienen de datos de ventas. |
| `Entregas` | Inicio ← Entregas | KPI "Listas para entregar" refleja estado de entregas. |
| `Caja` | Inicio ← Caja | KPI "Por cobrar" refleja saldos pendientes del módulo Caja. |
| `Inventario` | Inicio ← Inventario | KPI "Stock crítico" refleja productos con stock bajo mínimo. |
| `ModuleNavigator` | Inicio → Todos | Los accesos rápidos navegan a otros módulos vía `ModuleNavigator`. |

## Archivos Relacionados

```
modules/inicio/
├── InicioController.java
├── InicioFacade.java
├── InicioRowModel.java
└── InicioView.fxml

shared/ui/components/
├── KpiCardController.java
├── KpiCardModel.java
├── StatusBadgeController.java
└── StatusBadgeModel.java

shared/ui/util/
├── ComboBoxFactory.java
└── TableViewFactory.java
```
