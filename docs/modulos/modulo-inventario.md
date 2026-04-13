# Inventario - Documentación Técnica

## Resumen
- Gestión de productos y stock de la óptica: catálogo general, monturas, lentes, movimientos de inventario, stock crítico, recepciones y análisis de rotación.
- 7 sub-vistas: Catálogo General, Monturas, Lentes y Variantes, Movimientos, Stock Crítico, Recepción, Histórico y Análisis.
- Patrón arquitectónico: **Facade + Paginación + Panel resumen de producto**.

## Estructura de Clases

| Clase | Responsabilidad |
|-------|----------------|
| `InventarioController.java` | Controlador del módulo. Gestiona 7 sub-vistas con ToggleGroup, filtros, tabla paginada de catálogo y panel resumen lateral. Delega toda lógica a `InventarioFacade`. |
| `InventarioFacade.java` | Capa de abstracción entre `DemoStore` (colecciones de `Producto` y proveedores) y las 7 sub-vistas. Proporciona consultas paginadas, filtrado por categoría/marca/sucursal, y datos de movimientos, stock crítico, recepciones y análisis de rotación. |
| `InventarioFilters.java` | Criterios de filtrado: texto de búsqueda, categoría, stock, marca, sucursal, proveedor, flag de solo stock crítico. |
| `InventarioRowModel.java` | Contiene 7 records inner: `CatalogoRow`, `MonturaRow`, `LenteRow`, `MovimientoRow`, `CriticoRow`, `RecepcionRow`, `AnalisisRow`. |
| `InventarioSummaryModel.java` | Modelo resumen para el panel lateral derecho. Presenta el detalle completo de un producto: clasificación, estado de stock, ubicación física y proveedor principal. |

### Inner Records de InventarioRowModel

| Record | Campos | Sub-vista que alimenta |
|--------|--------|----------------------|
| `CatalogoRow` | referencia, nombre, categoria, marca, sucursal, stock, precio, estado | Catálogo General (TableView paginado) |
| `MonturaRow` | referencia, nombre, marca, material, color, precio, stock, sucursal | Monturas (TableView específico) |
| `LenteRow` | referencia, nombre, tipo, indice, tratamiento, stock, precioBase, estado | Lentes y Variantes (TableView) |
| `MovimientoRow` | fecha, tipo, referencia, producto, cantidad, sucursal, responsable | Movimientos de Inventario (Tabla) |
| `CriticoRow` | referencia, nombre, categoria, stockActual, stockMinimo, estado, proveedor, sucursal | Stock Crítico (Tabla de alertas) |
| `RecepcionRow` | referencia, fecha, proveedor, sucursal, estado, responsable | Recepción y Abastecimiento (Tabla) |
| `AnalisisRow` | referencia, nombre, categoria, rotacion, ultimaSalida, estadoActual, observacion | Histórico y Análisis (Tabla analítica) |

### InventarioSummaryModel

| Campo | Descripción |
|-------|-------------|
| referencia | Código de referencia del producto |
| nombre | Nombre descriptivo |
| categoria | Categoría (montura, lente, accesorio, etc.) |
| marca | Marca comercial |
| estadoStock | Normal, Bajo stock, Agotado |
| stockActual | Unidades actuales |
| sucursal | Ubicación del producto |
| ubicacion | Ubicación física (estante, sección) |
| precioVenta | Precio de venta unitario |
| ultimaSalida | Fecha de última salida |
| ultimaRecepcion | Fecha de última recepción |
| proveedorPrincipal | Proveedor principal |
| estadoReposicion | Estado de reposición (si hay pedido pendiente) |

### StatsCritico (inner record de InventarioFacade)

Record que encapsula estadísticas de stock crítico: `agotados`, `bajoStock`, `enReposicion`.

## Sub-vistas

| Sub-vista | ToggleButton | Qué datos muestra | Qué RowModel usa |
|-----------|-------------|-------------------|------------------|
| Catálogo General | N/A (vista principal) | Tabla paginada de todos los productos con referencia, nombre, categoría, marca, sucursal, stock, precio, estado | `CatalogoRow` |
| Monturas | N/A | Vista específica de armazones con material, color y precio | `MonturaRow` |
| Lentes y Variantes | N/A | Tipos de lente oftálmico con índice de refracción, tratamiento y stock | `LenteRow` |
| Movimientos | N/A | Registro de entradas, salidas, ajustes y transferencias de inventario | `MovimientoRow` |
| Stock Crítico | N/A | Productos con stock bajo o agotado, con proveedor sugerido para reposición | `CriticoRow` |
| Recepción | N/A | Órdenes de abastecimiento con estado (Completado, En tránsito, Pendiente, Parcial) | `RecepcionRow` |
| Histórico y Análisis | N/A | Datos analíticos de rotación de stock, última salida y observaciones | `AnalisisRow` |

## Flujo de Datos

```
Controller.initialize()
    → Obtiene DemoStore desde App.getDemoStore()
    → Crea InventarioFacade(store)
    → Crea InventarioFilters()
    → setupFilterCombos() — categoría, marca, sucursal, proveedor, stock, búsqueda
    → setupSubViewToggle() — ToggleGroup con 7 botones
    → loadCatalogo() — facade.getCatalogo(filters, pageRequest) → PageResult<CatalogoRow>
    → loadMonturas() — facade.getMonturas(filters) → List<MonturaRow>
    → loadLentes() — facade.getLentes(filters) → List<LenteRow>
    → loadMovimientos() — facade.getMovimientos(filters) → List<MovimientoRow>
    → loadStockCritico() — facade.getStockCritico() → List<CriticoRow>
    → loadRecepciones() — facade.getRecepciones() → List<RecepcionRow>
    → loadAnalisis() — facade.getAnalisis() → List<AnalisisRow>
    → loadCombos() — categorías, marcas, proveedores, sucursales desde store
    → loadStatsCritico() — facade.getStatsCritico() → StatsCritico

Al seleccionar un producto:
    onProductoSelected(producto)
        → facade.buildSummary(producto) → InventarioSummaryModel
        → updateSummaryPanel(summary)

Al aplicar filtros:
    applyFilters()
        → Actualiza currentFilters desde combos
        → Recarga la sub-vista activa con datos filtrados
```

## Filtros Disponibles

| Filtro | Tipo | Opciones | Cómo filtra el Facade |
|--------|------|----------|----------------------|
| Búsqueda | TextField | Texto libre | `FilterSupport.matchesText(searchText, referencia, nombre, categoria, marca)` — búsqueda parcial en 4 campos. |
| Categoría | ComboBox | Categorías de `store.productos` (MONTURA, LENTES, ACCESORIO, etc.) | `FilterSupport.matchesExact(categoria, filters.getCategoria())` |
| Marca | ComboBox | Marcas de `store.productos` | `FilterSupport.matchesExact(marca, filters.getMarca())` |
| Sucursal | ComboBox | Sucursales del `DemoStore` | `FilterSupport.matchesExact(sucursal, filters.getSucursal())` |
| Proveedor | ComboBox | Proveedores del `DemoStore` | Filtro de UI; el facade no lo aplica directamente en los métodos principales. |
| Stock | ComboBox | Todos, Activo, Bajo stock, Agotado | Mapea `Producto.getEstado()` a string display y filtra por `FilterSupport.matchesExact()`. |
| Solo Stock Crítico | CheckBox | Boolean | Si activo, filtra `p.getStock() <= p.getStockMinimo()`. |

## Panel Resumen (Derecho)

El `InventarioSummaryModel` se actualiza al seleccionar un producto:

- `facade.buildSummary(producto)` → `InventarioSummaryModel.from(producto)` — mapea la entidad `Producto` al modelo resumen.
- Calcula `estadoStock` a partir de `Producto.getEstado()`: ACTIVO→Normal, BAJO_STOCK→Bajo stock, AGOTADO→Agotado.
- Genera ubicación física como `"Estante A-" + (stock % 10 + 1)` (valor demo).
- Usa `MoneyUtils.formatOrDefault()` para formatear precios.
- Usa `StringUtils.defaultIfBlank()` para manejar campos nulos/vacíos.

## Patrones de Diseño Aplicados

| Patrón | Descripción |
|--------|-------------|
| **Facade** | `InventarioFacade` abstrae el acceso a `store.productos` y `store.proveedores`. |
| **Paginación** | `PaginationHelper.page(filtered, pageRequest)` para el catálogo general. |
| **ToggleGroup** | 7 `ToggleButton` mutuamente excluyentes controlan qué sub-vista es visible. |
| **Mapper** | `InventarioSummaryModel.from(Producto)` convierte entidad de dominio a modelo de presentación. |
| **DTO** | `StatsCritico` encapsula estadísticas agregadas de stock crítico. |
| **Factory** | `ComboBoxFactory.createFilterCombo()` para combos filtrables. |
| **Observer** | Listeners en combos y búsqueda disparan `applyFilters()`. |
| **Heurística** | `toMonturaRow()` y `toLenteRow()` derivan material, color, tipo, índice y tratamiento del nombre del producto. |

## Integración con Otros Módulos

| Módulo | Dirección | Descripción |
|--------|-----------|-------------|
| `VentaOptica` | Inventario ← VentaOptica | El catálogo de monturas del wizard de venta consulta el inventario. |
| `OrdenesLaboratorio` | Inventario ← OrdenesLab | Las órdenes de laboratorio consumen materiales del inventario. |
| `Inicio` | Inventario → Inicio | KPI "Stock crítico" del dashboard refleja productos con stock bajo mínimo. |
| `Proveedores` | Inventario ← Proveedores | Los productos referencian `proveedorPrincipal`. |
| `Sucursales` | Inventario ← Sucursales | Los productos están asociados a una sucursal. |

## Archivos Relacionados

```
modules/inventario/
├── InventarioController.java
├── InventarioFacade.java
├── InventarioFilters.java
├── InventarioRowModel.java
├── InventarioSummaryModel.java
└── InventarioView.fxml

shared/query/
├── FilterSupport.java
├── PaginationHelper.java
├── PageRequest.java
└── PageResult.java

shared/domain/producto/
└── Producto.java

shared/util/
├── MoneyUtils.java
└── StringUtils.java
```
