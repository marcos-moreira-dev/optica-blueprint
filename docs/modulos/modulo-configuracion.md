# Configuración - Documentación Técnica

## Resumen
Módulo de administración de todos los parámetros del sistema óptica. Proporciona una interfaz de tres paneles para gestionar nueve categorías de configuración: datos institucionales, sucursales, usuarios y permisos, catálogos maestros, inventario, venta y comprobantes, agenda y seguimiento, laboratorio y entregas, y apariencia del sistema.

## Estructura de Clases
| Clase | Tipo | Responsabilidad |
|-------|------|-----------------|
| `ConfiguracionController` | Controller | Navegación de categorías, formularios de configuración, gestión de tablas, panel de resumen de impacto |
| `ConfiguracionFacade` | Facade | Seed data de configuración, acceso a parámetros, construcción de resúmenes de impacto operativo |
| `ConfiguracionFilters` | Model | Estado de filtrado (searchText) con método `matches()` para filtrado de categorías |
| `ConfiguracionRowModel` | Model | Registros de fila: SucursalRow, UsuarioRow, CatalogoRow |
| `ConfiguracionSummaryModel` | Model | Resumen del impacto operativo de cada categoría de configuración |

### Sub-clases (inner records en ConfiguracionRowModel)
| Record | Columnas |
|--------|----------|
| `SucursalRow` | nombre, direccion, horario, responsable, estado |
| `UsuarioRow` | usuario, rol, sucursal, estado |
| `CatalogoRow` | valor, estado, categoria |

### Sub-clases (inner records en ConfiguracionFacade)
| Record | Descripción |
|--------|-------------|
| `GeneralNegocio` | nombreComercial, razonSocial, ruc, telefono, correo, direccion, ciudad, sucursalPredeterminada, moneda, zonaHoraria |
| `InventarioConfig` | stockMinimo, ubicacionDefault, alertasCriticas, generacionAviso, recepcionParcial |
| `VentaConfig` | prefijoOrden, prefijoComprobante, secuenciaInicial, permiteAbonos, descuentoMaximo, permiteSaldoPendiente |
| `AgendaConfig` | duracionCita, recallActivo, diasAnticipacion, canalPreferido |
| `LaboratorioConfig` | laboratoriosHabilitados, tiempoPromesa, requiereValidacion, bloqueaConSaldo |
| `AparienciaConfig` | modoApariencia, densidadVisual, mostrarIconos, mostrarTooltips, confirmarAccionesCriticas |

## Sub-vistas
| # | Categoría | Sección FXML | Componentes Principales | Acciones |
|---|-----------|-------------|------------------------|----------|
| 1 | General del negocio | `categoriaGeneralSection` | 7 TextFields + 3 ComboBoxes | Guardar, Restablecer |
| 2 | Sucursales y operación | `categoriaSucursalesSection` | TableView (sucursalesTable) + formulario detalle | Guardar, Nueva, Desactivar |
| 3 | Usuarios, roles y permisos | `categoriaUsuariosSection` | TableView (usuariosTable) + formulario detalle con checkboxes de permisos | Guardar, Nuevo, Desactivar |
| 4 | Catálogos maestros | `categoriaCatalogosSection` | ListView (tipos) + TableView (valores) | Guardar, Nuevo valor, Desactivar |
| 5 | Inventario y abastecimiento | `categoriaInventarioSection` | 2 TextFields + 6 Checkboxes + 1 ComboBox | Guardar, Restablecer |
| 6 | Venta, caja y comprobantes | `categoriaVentaSection` | 4 TextFields + 4 CheckButtons + 1 TextArea | Guardar, Vista previa |
| 7 | Agenda, seguimiento y comunicación | `categoriaAgendaSection` | 2 ComboBoxes + 4 Checkboxes + 2 TextAreas | Guardar, Probar plantilla |
| 8 | Laboratorio y entregas | `categoriaLaboratorioSection` | 2 TextFields + 4 Checkboxes | Guardar, Restablecer |
| 9 | Apariencia y experiencia de uso | `categoriaAparienciaSection` | 2 ComboBoxes + 5 Checkboxes | Guardar, Restablecer, Vista previa |

## Flujo de Datos
```
[Usuario selecciona categoría en ListView izquierdo]
       ↓
selectCategory(index) → muestra sección FXML correspondiente
       ↓
loadCategoryData(index) → switch: loadGeneralNegocio() / loadSucursales() / loadUsuarios() / etc.
       ↓
facade.getGeneralNegocio() / getSucursales() / getUsuarios() / etc.
       ↓
Datos cargados en TextFields, ComboBoxes, CheckBoxes y TableViews
       ↓
facade.buildSummary(categoria) → ConfiguracionSummaryModel del Map SUMMARIES
       ↓
updateSummaryPanel() → Labels de alcance, impacto operativo y recomendación
```

## Filtros Disponibles
| Filtro | Control | Funcionalidad |
|--------|---------|---------------|
| Búsqueda de categorías | `TextField` + `ListView` | Filtra las 9 categorías por nombre usando `ConfiguracionFilters.matches()` |
| Limpiar búsqueda | `Button` | Restablece el filtro y la selección de categorías |

El módulo no utiliza filtros complejos como otros módulos. El único filtro es la búsqueda textual de categorías en el panel izquierdo, que filtra dinámicamente la `ListView` de categorías disponibles.

## Panel Resumen
El `ConfiguracionSummaryModel` muestra el impacto operativo de cada categoría:
- **categoria**: nombre de la categoría de configuración seleccionada
- **estadoCambios**: estado de los cambios pendientes (ej. "Sin cambios pendientes")
- **alcance**: descripción del alcance del impacto de los cambios (global, por sucursal, etc.)
- **impactoOperativo**: nivel de impacto operativo que generan los cambios en el sistema
- **recomendacion**: recomendación sobre la aplicación de los cambios

El facade mantiene un `Map<String, ConfiguracionSummaryModel>` con resúmenes predefinidos para cada una de las 9 categorías, cada uno con alcance, impacto y recomendación específicos.

## Patrones de Diseño
- **Facade Pattern**: `ConfiguracionFacade` centraliza acceso a parámetros y resúmenes de impacto
- **MVC (Model-View-Controller)**: separación entre FXML, Controller y modelos de datos
- **Master-Detail Pattern**: categorías 2 (Sucursales) y 3 (Usuarios) implementan patrón maestro-detalle con TableView + formulario de edición
- **Navigation Panel Pattern**: panel izquierdo con ListView de categorías navegables, similar a preferencias de sistema
- **Record Pattern**: modelos de fila como records inmutables con JavaFX properties
- **Static Map Pattern**: `SUMMARIES` como mapa estático inmutable con resúmenes predefinidos por categoría
- **Category Grouping Pattern**: 9 secciones FXML independientes con visibilidad controlada por categoría seleccionada
- **Tooltip Pattern**: cada categoría tiene tooltip descriptivo en la lista de navegación

## Integración con Otros Módulos
| Módulo | Punto de integración | Descripción |
|--------|---------------------|-------------|
| Sucursales | Categoría 2 | Configura sedes que usan todos los demás módulos como filtro |
| Usuarios y Roles | Categoría 3 | Define permisos de acceso a cada módulo del sistema |
| Inventario | Categoría 5 | Parámetros de stock que afectan módulo de compras e inventario |
| Ventas | Categoría 6 | Prefijos de orden, comprobantes y reglas de pago |
| Agenda | Categoría 7 | Configuración de citas, recall y comunicación |
| Taller/Laboratorio | Categoría 8 | Tiempos promesa, validación y estados de trabajo |
| Todos los módulos | Filtro global | Cada categoría afecta parámetros utilizados por múltiples módulos |
