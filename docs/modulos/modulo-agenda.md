# Agenda - Documentación Técnica

## Resumen
- Gestiona la programación, seguimiento y control de citas en la óptica.
- 6 sub-vistas: Vista Día, Vista Semana, Lista del Día, Lista de Espera, Confirmaciones, Horarios y Bloqueos.
- Patrón arquitectónico: **Facade + ToggleGroup de sub-vistas + Panel resumen persistente**.

## Estructura de Clases

| Clase | Responsabilidad |
|-------|----------------|
| `AgendaController.java` | Controlador principal (~1019 líneas). Gestiona 6 sub-vistas con ToggleGroup, barra de filtros, botones de acción y panel resumen lateral. Delega toda lógica de negocio a `AgendaFacade`. |
| `AgendaFacade.java` | Capa de abstracción entre `DemoStore` y las sub-vistas. Genera datos seed para citas del día, semana, lista de espera, confirmaciones y horarios. Aplica filtros vía `FilterSupport`. |
| `AgendaFilters.java` | Criterios de filtrado: fecha, profesional, estado, tipo de atención, texto de búsqueda libre. |
| `AgendaRowModel.java` | Contiene 6 records inner que alimentan las distintas tablas del módulo. |
| `AgendaSummaryModel.java` | Modelo resumen para el panel lateral derecho. Muestra datos detallados de la cita seleccionada. |

### Inner Records de AgendaRowModel

| Record | Campos | Tabla que alimenta |
|--------|--------|-------------------|
| `CitaDiaRow` | hora, cliente, tipoAtencion, estado, profesional, sucursal, tooltip | Vista Día (timeline visual con bloques) |
| `SemanaRow` | dia, totalCitas, confirmadas, pendientes, canceladas | Vista Semana (grid de 6 columnas Lun-Sáb) |
| `ListaDiaRow` | hora, cliente, atencion, estado, profesional, sucursal, observacion | Lista del Día (TableView tabular) |
| `EsperaRow` | cliente, atencionSolicitada, franjaPreferida, sucursal, prioridad, estadoContacto | Lista de Espera (TableView) |
| `ConfirmacionRow` | fecha, hora, cliente, atencion, estadoConfirmacion, ultimoRecordatorio, sucursal | Confirmaciones (TableView) |
| `HorarioRow` | franja, tipo, estado, responsable | Horarios y Bloqueos (TableView) |

### AgendaSummaryModel

| Campo | Descripción |
|-------|-------------|
| cliente | Nombre del paciente |
| hora | Hora programada |
| atencion | Tipo de servicio |
| estado | Estado de la cita |
| profesional | Profesional asignado |
| sucursal | Sede de atención |
| contacto | Teléfono/medio de contacto |
| observaciones | Notas sobre la cita |
| fechaCita | Fecha en formato dd/MM/yyyy |

## Sub-vistas

| Sub-vista | ToggleButton | Qué datos muestra | Qué RowModel usa |
|-----------|-------------|-------------------|------------------|
| Vista Día | `btnDia` | Timeline visual con bloques de citas organizados por hora (08:00-18:00), con colores según estado | `CitaDiaRow` |
| Vista Semana | `btnSemana` | Grid semanal (Lun-Sáb) con totales y desglose por estado de citas | `SemanaRow` |
| Lista del Día | `btnListaDia` | Tabla detallada con hora, cliente, atención, estado, profesional, sucursal y observación | `ListaDiaRow` |
| Lista de Espera | `btnListaEspera` | Clientes en espera con franja preferida, prioridad y estado de contacto | `EsperaRow` |
| Confirmaciones | `btnConfirmaciones` | Estado de confirmación de citas con fecha de último recordatorio | `ConfirmacionRow` |
| Horarios y Bloqueos | `btnHorarios` | Franjas horarias con estado (activo/bloqueado/programado) y responsable | `HorarioRow` |

## Flujo de Datos

```
Controller.initialize()
    → Obtiene DemoStore desde App.getDemoStore()
    → Crea AgendaFacade(store)
    → Crea AgendaFilters() (valores por defecto)
    → setupTopBar() — combo sucursal, botones nueva cita/actualizar
    → setupFilterCombos() — fecha, profesional, estado, atención, búsqueda libre
    → setupSubViewToggle() — ToggleGroup con 6 botones
    → setupSubView1_Dia() a setupSubView6_Horarios() — configura columnas y listeners
    → setupSummaryPanel() — configura labels del panel derecho
    → showSubView(0) — muestra Vista Día por defecto
    → loadSummaryPanel() — carga datos de resumen iniciales

Al cambiar de sub-vista:
    showSubView(index)
        → Oculta todas las secciones, muestra la seleccionada
        → Invoca loadSubViewN_XXX() correspondiente
        → Cada loadSubViewN consulta facade con fecha actual + filtros
        → Actualiza tabla/contenedor con datos filtrados

Al seleccionar una fila:
    onCitaSelected(cita) / onListaDiaRowSelected(row) / onEsperaRowSelected(row)
        → facade.buildSummary(cita) → AgendaSummaryModel
        → updateSummaryPanel(summary) — actualiza labels del panel derecho
```

## Filtros Disponibles

| Filtro | Tipo | Opciones | Cómo filtra el Facade |
|--------|------|----------|----------------------|
| Fecha | DatePicker | Cualquier fecha | Determina qué día de citas consultar. El facade usa la fecha para `getCitasDia(fecha, filters)`. |
| Profesional | ComboBox filtrable | Lista del facade: Dra. Salazar, Dr. Paredes, Técnico Rivera, Asesor Molina, Asesora López | `FilterSupport.matchesExact(row.profesional(), filters.getProfesional())` — filtro exacto. |
| Estado | ComboBox filtrable | Confirmada, Pendiente, Reprogramada, Cancelada, Atendida, Requiere revisión, Confirmado | `FilterSupport.matchesExact(row.estado(), filters.getEstado())` |
| Tipo de Atención | ComboBox filtrable | Examen visual, Ajuste, Entrega, Revisión, Toma medidas, Control, Adaptación LC, Pausa | `FilterSupport.matchesExact(row.tipoAtencion(), filters.getTipoAtencion())` |
| Búsqueda libre | TextField | Texto libre | `FilterSupport.matchesText(searchText, cliente, tipoAtencion, profesional)` — búsqueda parcial en 3 campos. |
| Sucursal (top bar) | ComboBox | "Todas", "Matriz Centro", "Norte Mall" | Filtro de nivel superior, no pasado directamente al facade en la demo. |

## Panel Resumen (Derecho)

El `AgendaSummaryModel` se actualiza cuando el usuario selecciona una fila en cualquiera de las tablas:

- **`onListaDiaRowSelected()`** → `facade.buildSummary(row)` → `updateSummaryPanel()`
- **`onEsperaRowSelected()`** → Crea `AgendaSummaryModel` manualmente con datos de la fila
- **`onCitaSelected()`** → `facade.buildSummary(cita)` → `updateSummaryPanel()`

El panel muestra 9 campos: cliente, hora, atención, estado, profesional, sucursal, contacto, observaciones, fecha de cita. Incluye 6 botones de acción: Editar, Confirmar, Reprogramar, Cancelar, Atendida, Abrir Cliente.

## Patrones de Diseño Aplicados

| Patrón | Descripción |
|--------|-------------|
| **Facade** | `AgendaFacade` abstrae el acceso a `DemoStore`, aplicando filtros y retornando modelos de fila. |
| **ToggleGroup** | 6 `ToggleButton` mutuamente excluyentes controlan qué sub-vista es visible. |
| **Strategy (implícito)** | `buildSummary(Object)` usa pattern matching (`instanceof`) para construir el resumen según el tipo de fila. |
| **Factory** | `ComboBoxFactory.createFilterCombo()` para combos filtrables con prompt. |
| **MVVM-like** | Los `record` tienen `StringProperty` para binding con JavaFX `TableView`. |
| **Observer** | Listeners en `fechaPicker.valueProperty`, `searchField.textProperty`, etc., disparan `applyFilters()`. |
| **Presentation Model** | `AgendaFilters` separa el estado de los filtros de la vista y del dominio. |

## Integración con Otros Módulos

| Módulo | Dirección | Descripción |
|--------|-----------|-------------|
| `Inicio` | Agenda → Inicio | Las citas del día alimentan el KPI "Citas de hoy" y la tabla de próximas citas del dashboard. |
| `Clientes` | Agenda ← Clientes | Los clientes de las citas provienen de `store.clientes`. |
| `VentaOptica` | Agenda → VentaOptica | Una cita de "Examen visual" puede derivar en una venta óptica. |
| `Recetas` | Agenda → Recetas | Tras un examen visual, se puede actualizar la receta del paciente. |

## Archivos Relacionados

```
modules/agenda/
├── AgendaController.java
├── AgendaFacade.java
├── AgendaFilters.java
├── AgendaRowModel.java
├── AgendaSummaryModel.java
└── AgendaView.fxml

shared/query/
└── FilterSupport.java

shared/ui/components/
└── OpticaToggleButton.java
```
