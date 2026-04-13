# Árbol oficial mínimo para demo desktop JavaFX autocontenida de óptica

## 1. Objetivo de esta arquitectura

Este árbol existe para resolver cuatro problemas al mismo tiempo:

1. que la aplicación se vea grande y seria aunque sea un blueprint
2. que toda la data viva únicamente en memoria
3. que la IA pueda implementar módulo por módulo sin colapsar su ventana de contexto
4. que la estructura sea suficientemente ordenada para crecer sin romperse de inmediato

Este proyecto no busca una arquitectura empresarial final. Busca una **fachada desktop convincente, autocontenida, repetible y estable**.

---

## 2. Decisiones base escritas en piedra

### Decisión 1
La arquitectura general será:

**feature-first + MVC por módulo + fachada por módulo + store global en memoria + utilidades compartidas de paginación, filtros y navegación**

### Decisión 2
No habrá:
- base de datos
- H2
- JSON
- archivos externos de datos
- backend
- API
- persistencia real

### Decisión 3
Los seeds vivirán en código Java, aislados en fábricas o inicializadores.

### Decisión 4
Cada módulo tendrá estructura casi idéntica para que la IA trabaje con patrones repetibles.

### Decisión 5
La navegación global será única. Cada módulo reemplaza el área central principal, y cada módulo maneja internamente su propia subnavegación.

### Decisión 6
Toda tabla grande debe soportar:
- búsqueda
- filtros
- ordenamiento
- paginación
- resumen de resultados visibles

### Decisión 7
Los controladores JavaFX no deben contener seeds, ni lógica de filtrado compleja, ni paginación, ni composición profunda de datos.

---

## 3. Nombre base del proyecto

Nombre sugerido del proyecto:

**optica-demo-desktop**

Paquete base sugerido:

**com.marcosmoreira.opticademo**

Si luego deseas un nombre más neutro o comercial, eso se puede cambiar. Pero para construir la maqueta, este nombre es suficiente.

---

## 4. Árbol oficial mínimo del proyecto

```text
optica-demo-desktop/
├── pom.xml
├── README.md
├── mvnw
├── mvnw.cmd
├── .gitignore
└── src/
    └── main/
        ├── java/
        │   └── com/marcosmoreira/opticademo/
        │       ├── app/
        │       │   ├── App.java
        │       │   ├── AppLauncher.java
        │       │   ├── MainLayoutController.java
        │       │   ├── navigation/
        │       │   │   ├── ModuleId.java
        │       │   │   ├── ModuleNavigator.java
        │       │   │   ├── ModuleViewLoader.java
        │       │   │   └── NavigationItem.java
        │       │   ├── session/
        │       │   │   ├── DemoSession.java
        │       │   │   ├── CurrentUserContext.java
        │       │   │   └── CurrentSucursalContext.java
        │       │   └── theme/
        │       │       ├── ThemeMode.java
        │       │       ├── ThemeTokens.java
        │       │       └── ThemeManager.java
        │       │
        │       ├── demo/
        │       │   ├── DemoStore.java
        │       │   ├── DemoDataInitializer.java
        │       │   ├── DemoResetService.java
        │       │   ├── seed/
        │       │   │   ├── ClienteSeedFactory.java
        │       │   │   ├── ProductoSeedFactory.java
        │       │   │   ├── UsuarioSeedFactory.java
        │       │   │   ├── SucursalSeedFactory.java
        │       │   │   ├── ProveedorSeedFactory.java
        │       │   │   ├── VentaSeedFactory.java
        │       │   │   ├── CobroSeedFactory.java
        │       │   │   ├── NotificacionSeedFactory.java
        │       │   │   ├── CoberturaSeedFactory.java
        │       │   │   ├── TallerSeedFactory.java
        │       │   │   └── SharedSeedSupport.java
        │       │   └── generator/
        │       │       ├── ReferenceGenerator.java
        │       │       ├── DateGenerator.java
        │       │       └── DemoRandoms.java
        │       │
        │       ├── shared/
        │       │   ├── domain/
        │       │   │   ├── common/
        │       │   │   │   ├── BaseEntity.java
        │       │   │   │   ├── EstadoGeneral.java
        │       │   │   │   ├── Prioridad.java
        │       │   │   │   └── AuditStamp.java
        │       │   │   ├── cliente/
        │       │   │   │   └── Cliente.java
        │       │   │   ├── sucursal/
        │       │   │   │   └── Sucursal.java
        │       │   │   ├── usuario/
        │       │   │   │   ├── Usuario.java
        │       │   │   │   └── RolSistema.java
        │       │   │   ├── producto/
        │       │   │   │   └── Producto.java
        │       │   │   ├── proveedor/
        │       │   │   │   └── Proveedor.java
        │       │   │   ├── venta/
        │       │   │   │   └── VentaOptica.java
        │       │   │   ├── caja/
        │       │   │   │   └── Cobro.java
        │       │   │   ├── notificacion/
        │       │   │   │   └── Notificacion.java
        │       │   │   ├── cobertura/
        │       │   │   │   └── CasoCobertura.java
        │       │   │   └── taller/
        │       │   │       └── TrabajoTecnico.java
        │       │   │
        │       │   ├── query/
        │       │   │   ├── PageRequest.java
        │       │   │   ├── PageResult.java
        │       │   │   ├── SortDirection.java
        │       │   │   ├── SortSpec.java
        │       │   │   ├── PaginationHelper.java
        │       │   │   └── FilterSupport.java
        │       │   │
        │       │   ├── ui/
        │       │   │   ├── components/
        │       │   │   │   ├── FilterBarController.java
        │       │   │   │   ├── PaginationBarController.java
        │       │   │   │   ├── SummarySidePanelController.java
        │       │   │   │   ├── StatusBadgeController.java
        │       │   │   │   ├── KpiCardController.java
        │       │   │   │   └── EmptyStatePaneController.java
        │       │   │   ├── model/
        │       │   │   │   ├── StatusBadgeModel.java
        │       │   │   │   ├── KpiCardModel.java
        │       │   │   │   └── SummaryFieldModel.java
        │       │   │   └── util/
        │       │   │       ├── TableViewFactory.java
        │       │   │       ├── ComboBoxFactory.java
        │       │   │       ├── Formatters.java
        │       │   │       └── UiMessages.java
        │       │   │
        │       │   └── util/
        │       │       ├── DateUtils.java
        │       │       ├── MoneyUtils.java
        │       │       ├── StringUtils.java
        │       │       ├── CollectionUtils.java
        │       │       └── DemoAssertions.java
        │       │
        │       └── modules/
        │           ├── inicio/
        │           │   ├── InicioController.java
        │           │   ├── InicioFacade.java
        │           │   ├── InicioFilters.java
        │           │   ├── InicioRowModel.java
        │           │   └── InicioSummaryModel.java
        │           │
        │           ├── clientes/
        │           │   ├── ClientesController.java
        │           │   ├── ClientesFacade.java
        │           │   ├── ClientesFilters.java
        │           │   ├── ClientesRowModel.java
        │           │   └── ClientesSummaryModel.java
        │           │
        │           ├── recetas/
        │           │   ├── RecetasController.java
        │           │   ├── RecetasFacade.java
        │           │   ├── RecetasFilters.java
        │           │   ├── RecetasRowModel.java
        │           │   └── RecetasSummaryModel.java
        │           │
        │           ├── ventaoptica/
        │           │   ├── VentaOpticaController.java
        │           │   ├── VentaOpticaFacade.java
        │           │   ├── VentaOpticaFilters.java
        │           │   ├── VentaOpticaRowModel.java
        │           │   └── VentaOpticaSummaryModel.java
        │           │
        │           ├── caja/
        │           │   ├── CajaController.java
        │           │   ├── CajaFacade.java
        │           │   ├── CajaFilters.java
        │           │   ├── CajaRowModel.java
        │           │   └── CajaSummaryModel.java
        │           │
        │           ├── inventario/
        │           │   ├── InventarioController.java
        │           │   ├── InventarioFacade.java
        │           │   ├── InventarioFilters.java
        │           │   ├── InventarioRowModel.java
        │           │   └── InventarioSummaryModel.java
        │           │
        │           ├── entregas/
        │           │   ├── EntregasController.java
        │           │   ├── EntregasFacade.java
        │           │   ├── EntregasFilters.java
        │           │   ├── EntregasRowModel.java
        │           │   └── EntregasSummaryModel.java
        │           │
        │           ├── seguimiento/
        │           │   ├── SeguimientoController.java
        │           │   ├── SeguimientoFacade.java
        │           │   ├── SeguimientoFilters.java
        │           │   ├── SeguimientoRowModel.java
        │           │   └── SeguimientoSummaryModel.java
        │           │
        │           ├── reportes/
        │           │   ├── ReportesController.java
        │           │   ├── ReportesFacade.java
        │           │   ├── ReportesFilters.java
        │           │   ├── ReportesRowModel.java
        │           │   └── ReportesSummaryModel.java
        │           │
        │           ├── configuracion/
        │           │   ├── ConfiguracionController.java
        │           │   ├── ConfiguracionFacade.java
        │           │   ├── ConfiguracionFilters.java
        │           │   ├── ConfiguracionRowModel.java
        │           │   └── ConfiguracionSummaryModel.java
        │           │
        │           ├── sucursales/
        │           │   ├── SucursalesController.java
        │           │   ├── SucursalesFacade.java
        │           │   ├── SucursalesFilters.java
        │           │   ├── SucursalesRowModel.java
        │           │   └── SucursalesSummaryModel.java
        │           │
        │           ├── proveedores/
        │           │   ├── ProveedoresController.java
        │           │   ├── ProveedoresFacade.java
        │           │   ├── ProveedoresFilters.java
        │           │   ├── ProveedoresRowModel.java
        │           │   └── ProveedoresSummaryModel.java
        │           │
        │           ├── compras/
        │           │   ├── ComprasController.java
        │           │   ├── ComprasFacade.java
        │           │   ├── ComprasFilters.java
        │           │   ├── ComprasRowModel.java
        │           │   └── ComprasSummaryModel.java
        │           │
        │           ├── notificaciones/
        │           │   ├── NotificacionesController.java
        │           │   ├── NotificacionesFacade.java
        │           │   ├── NotificacionesFilters.java
        │           │   ├── NotificacionesRowModel.java
        │           │   └── NotificacionesSummaryModel.java
        │           │
        │           ├── usuariosroles/
        │           │   ├── UsuariosRolesController.java
        │           │   ├── UsuariosRolesFacade.java
        │           │   ├── UsuariosRolesFilters.java
        │           │   ├── UsuariosRolesRowModel.java
        │           │   └── UsuariosRolesSummaryModel.java
        │           │
        │           ├── taller/
        │           │   ├── TallerController.java
        │           │   ├── TallerFacade.java
        │           │   ├── TallerFilters.java
        │           │   ├── TallerRowModel.java
        │           │   └── TallerSummaryModel.java
        │           │
        │           └── seguros/
        │               ├── SegurosController.java
        │               ├── SegurosFacade.java
        │               ├── SegurosFilters.java
        │               ├── SegurosRowModel.java
        │               └── SegurosSummaryModel.java
        │
        └── resources/
            ├── fxml/
            │   ├── app/
            │   │   └── MainLayout.fxml
            │   ├── shared/
            │   │   └── components/
            │   │       ├── FilterBar.fxml
            │   │       ├── PaginationBar.fxml
            │   │       ├── SummarySidePanel.fxml
            │   │       ├── StatusBadge.fxml
            │   │       ├── KpiCard.fxml
            │   │       └── EmptyStatePane.fxml
            │   └── modules/
            │       ├── inicio/InicioView.fxml
            │       ├── clientes/ClientesView.fxml
            │       ├── recetas/RecetasView.fxml
            │       ├── ventaoptica/VentaOpticaView.fxml
            │       ├── caja/CajaView.fxml
            │       ├── inventario/InventarioView.fxml
            │       ├── entregas/EntregasView.fxml
            │       ├── seguimiento/SeguimientoView.fxml
            │       ├── reportes/ReportesView.fxml
            │       ├── configuracion/ConfiguracionView.fxml
            │       ├── sucursales/SucursalesView.fxml
            │       ├── proveedores/ProveedoresView.fxml
            │       ├── compras/ComprasView.fxml
            │       ├── notificaciones/NotificacionesView.fxml
            │       ├── usuariosroles/UsuariosRolesView.fxml
            │       ├── taller/TallerView.fxml
            │       └── seguros/SegurosView.fxml
            ├── css/
            │   ├── app.css
            │   ├── tokens.css
            │   ├── light.css
            │   └── dark.css
            ├── icons/
            │   ├── app/
            │   └── modules/
            └── images/
                ├── branding/
                └── placeholders/
```

---

## 5. Qué significa cada bloque

## `app/`
Aquí vive lo global del desktop.

### `App.java`
Clase JavaFX principal.

### `AppLauncher.java`
Clase auxiliar para evitar problemas de empaquetado o lanzamiento.

### `MainLayoutController.java`
Controla el layout maestro: sidebar, topbar opcional y contenedor central.

### `navigation/`
Todo lo relacionado con el cambio entre módulos.

### `session/`
Contexto de sesión demo: usuario actual, sucursal activa, etc.

### `theme/`
Manejo mínimo del tema visual y tokens de color.

---

## `demo/`
Aquí vive el universo entero de datos en memoria.

### `DemoStore.java`
Debe contener las listas vivas del sistema demo.

Ejemplo conceptual:
- clientes
- sucursales
- usuarios
- productos
- proveedores
- ventas
- cobros
- notificaciones
- casos de cobertura
- trabajos técnicos

### `DemoDataInitializer.java`
Inicializa el store completo al arrancar la app.

### `DemoResetService.java`
Reconstruye el store desde cero para resetear el demo.

### `seed/`
Factories Java puras para crear seeds.

### `generator/`
Soporte para referencias, fechas y variaciones repetibles.

---

## `shared/domain/`
Solo entidades realmente compartidas por varios módulos.

Regla importante:
No meter aquí modelos de vista.
No meter aquí DTOs de tabla.
No meter aquí componentes JavaFX.

Aquí solo vive el dominio reutilizable.

---

## `shared/query/`
Infraestructura mínima y universal para:
- paginación
- ordenamiento
- aplicación de filtros

Este bloque es obligatorio porque muchas tablas del demo van a tener volumen.

---

## `shared/ui/`
Componentes reutilizables mínimos para no repetir siempre la misma fachada.

Regla importante:
No crear veinte componentes raros.
Solo los estrictamente necesarios para consistencia visual.

---

## `modules/`
El corazón del proyecto.

Cada carpeta es un módulo funcional del negocio.

Regla importante:
La IA debe poder trabajar por subárbol. Por eso todos los módulos deben parecerse entre sí.

---

## `resources/fxml/`
Vistas JavaFX.

Regla importante:
El FXML principal por módulo debe ser solo uno al inicio. Si luego aparecen subcomponentes, se agregan con cuidado.

---

## `resources/css/`
Estilos base del demo.

### `app.css`
Estilo base general.

### `tokens.css`
Variables visuales base.

### `light.css`
Ajustes del modo claro.

### `dark.css`
Ajustes del modo oscuro, si decides activarlo luego.

---

## 6. Contrato mínimo de cada módulo

Cada módulo debe tener, como mínimo:

- un `Controller`
- una `Facade`
- una clase `Filters`
- una clase `RowModel`
- una clase `SummaryModel`
- un FXML principal

### Ejemplo canónico

```text
modules/clientes/
├── ClientesController.java
├── ClientesFacade.java
├── ClientesFilters.java
├── ClientesRowModel.java
└── ClientesSummaryModel.java
```

### Significado

#### `Controller`
Controla la vista JavaFX.
No debe filtrar, ni paginar, ni sembrar datos.

#### `Facade`
Es la capa que consulta el `DemoStore`, aplica filtros, ordenamiento, paginación y arma modelos listos para la vista.

#### `Filters`
Representa el estado actual de búsqueda y filtros del módulo.

#### `RowModel`
Modelo para una fila de `TableView`.

#### `SummaryModel`
Modelo para el panel derecho o resumen del elemento seleccionado.

---

## 7. Reglas de arquitectura que no se deben romper

### Regla 1
Ningún `Controller` debe acceder directamente a listas grandes del `DemoStore` para filtrar o paginar.

### Regla 2
Toda lógica de consulta visible debe pasar por la `Facade` del módulo.

### Regla 3
Los seeds nunca van dentro del `Controller`.

### Regla 4
Los componentes compartidos deben ser pocos, sobrios y muy reutilizables.

### Regla 5
Las entidades compartidas no deben contaminarse con detalles puramente visuales.

### Regla 6
Toda tabla mediana o grande debe usar paginación.

### Regla 7
Toda vista grande debe tener, como mínimo:
- búsqueda
- filtros
- ordenamiento
- paginación
- empty state
- panel derecho o resumen contextual cuando aplique

### Regla 8
La estructura de los módulos debe ser lo más repetitiva posible. La monotonía aquí es una ventaja.

---

## 8. Qué clases globales sí o sí deben existir

## `DemoStore`
Clase única que contiene toda la data viva del demo.

## `DemoDataInitializer`
Construye toda la data inicial en memoria.

## `DemoResetService`
Permite volver el demo a su estado original.

## `ModuleNavigator`
Cambia el módulo central cargando la vista correspondiente.

## `PageRequest`
Describe la página solicitada.

## `PageResult<T>`
Devuelve el resultado paginado.

## `PaginationHelper`
Aplica paginación a listas filtradas.

## `FilterSupport`
Ayuda con filtros comunes, búsqueda de texto, coincidencias básicas, etc.

## `ThemeManager`
Aplica tema visual si luego decides mantener modo claro y oscuro.

---

## 9. Modelo mínimo del store demo

`DemoStore` debe ser extremadamente simple. Una clase con listas en memoria.

Ejemplo conceptual de lo que debe contener:

- `List<Cliente> clientes`
- `List<Sucursal> sucursales`
- `List<Usuario> usuarios`
- `List<Producto> productos`
- `List<Proveedor> proveedores`
- `List<VentaOptica> ventas`
- `List<Cobro> cobros`
- `List<Notificacion> notificaciones`
- `List<CasoCobertura> coberturas`
- `List<TrabajoTecnico> trabajosTecnicos`

No hace falta infraestructura más pesada para este blueprint.

---

## 10. Cómo deben funcionar los seeds

Como no habrá JSON ni persistencia externa, los seeds se construirán en Java.

### Regla recomendada
Cada grupo de seeds debe salir de una factory especializada.

### Ejemplo
- `ClienteSeedFactory`
- `ProductoSeedFactory`
- `ProveedorSeedFactory`
- `VentaSeedFactory`

### Estrategia de seeds
Mezclar:
- datos fijos y bonitos para demo visible
- datos generados para dar volumen

### Idea práctica
No escribir 500 registros a mano.
Escribir 20 o 30 registros bonitos y luego generar volumen controlado para tablas, paginación y reportes.

---

## 11. Infraestructura mínima de paginación

Toda paginación debe resolverse con una infraestructura compartida simple.

## `PageRequest`
Debe contener, como mínimo:
- `pageIndex`
- `pageSize`
- `sortField`
- `sortDirection`

## `PageResult<T>`
Debe contener, como mínimo:
- `List<T> items`
- `pageIndex`
- `pageSize`
- `totalItems`
- `totalPages`

## `PaginationHelper`
Debe tomar una lista, aplicar el corte correcto y devolver un `PageResult`.

### Regla importante
Primero se filtra.
Luego se ordena.
Finalmente se pagina.

---

## 12. Flujo estándar de consulta por módulo

Todo módulo debe seguir este flujo:

1. la vista recoge búsqueda, filtros, orden y página
2. el `Controller` arma `Filters` y `PageRequest`
3. el `Controller` llama a la `Facade`
4. la `Facade` consulta el `DemoStore`
5. la `Facade` filtra, ordena y pagina
6. la `Facade` transforma entidades en `RowModel`
7. devuelve `PageResult<RowModel>`
8. el `Controller` actualiza tabla, paginación y resumen

Ese flujo debe repetirse casi igual en todos los módulos.

---

## 13. Componentes compartidos mínimos recomendados

No deben ser muchos.

### `FilterBar`
Barra visual consistente para búsqueda y filtros.

### `PaginationBar`
Barra con:
- página actual
- total de páginas
- botones siguiente/anterior
- resumen de resultados

### `SummarySidePanel`
Panel derecho reutilizable para mostrar contexto del registro seleccionado.

### `StatusBadge`
Componente pequeño para mostrar estado con estilo consistente.

### `KpiCard`
Tarjeta mínima para KPIs en módulos como Inicio o Reportes.

### `EmptyStatePane`
Vista uniforme para estados vacíos.

---

## 14. Convenciones de nombres

## Convención de clases de módulo

- `ClientesController`
- `ClientesFacade`
- `ClientesFilters`
- `ClientesRowModel`
- `ClientesSummaryModel`

## Convención de vistas FXML

- `ClientesView.fxml`
- `CajaView.fxml`
- `InventarioView.fxml`

## Convención de paquetes
Todo en minúscula.

Ejemplo:
- `modules/clientes`
- `modules/caja`
- `shared/domain/cliente`

## Convención de FXML compartido
- `FilterBar.fxml`
- `PaginationBar.fxml`
- `SummarySidePanel.fxml`

---

## 15. Orden oficial sugerido de implementación

Para que la IA no colapse, conviene implementar por capas y por subárbol.

### Fase 1: shell base
- `app/`
- `navigation/`
- `theme/`
- `MainLayout.fxml`

### Fase 2: infraestructura demo mínima
- `DemoStore`
- `DemoDataInitializer`
- seed factories base
- entidades compartidas mínimas

### Fase 3: infraestructura compartida de consulta
- `PageRequest`
- `PageResult`
- `PaginationHelper`
- `FilterSupport`

### Fase 4: componentes UI compartidos
- `StatusBadge`
- `EmptyStatePane`
- `PaginationBar`
- `SummarySidePanel`

### Fase 5: módulos prioritarios para demo comercial
Sugerencia de orden:
1. Inicio
2. Clientes
3. Venta óptica
4. Caja
5. Entregas
6. Inventario
7. Seguimiento
8. Reportes

### Fase 6: módulos extendidos
- Sucursales
- Proveedores
- Compras
- Notificaciones
- Usuarios y roles
- Taller
- Seguros
- Configuración
- Recetas

---

## 16. Qué no debe existir en esta versión

Para proteger simplicidad y contexto, no debería existir todavía:

- repositorios complejos
- interfaces de persistencia reales
- DTOs de backend
- servicios HTTP
- ORM
- H2
- JSON
- eventos de dominio complejos
- DDD pesado
- CQRS
- microservicios
- múltiples controllers por una misma vista principal sin necesidad real

---

## 17. Cómo pedirle trabajo a una IA sin que improvise demasiado

Regla recomendada:
Pedir siempre por subárbol pequeño.

### Ejemplo correcto
“Dame el código completo del subárbol `modules/clientes/` siguiendo este árbol oficial.”

### Ejemplo mejor todavía
“Dame `ClientesController`, `ClientesFacade`, `ClientesFilters`, `ClientesRowModel`, `ClientesSummaryModel` y `ClientesView.fxml`, respetando el store en memoria y paginación compartida.”

### Ejemplo incorrecto
“Hazme todo el sistema.”

---

## 18. Núcleo mínimo que debe quedar primero

Si hubiera que congelar el proyecto en un esqueleto mínimo, debería quedar primero:

- `App.java`
- `MainLayoutController.java`
- `ModuleNavigator.java`
- `DemoStore.java`
- `DemoDataInitializer.java`
- `PageRequest.java`
- `PageResult.java`
- `PaginationHelper.java`
- `ClientesController.java`
- `ClientesFacade.java`
- `ClientesFilters.java`
- `ClientesRowModel.java`
- `ClientesSummaryModel.java`
- `ClientesView.fxml`

Con eso ya existe la forma base del sistema.

---

## 19. Infraestructura mínima de soporte para que la maqueta no colapse

Esta sección existe para fijar explícitamente la infraestructura mínima que sostiene la demo y evita que el proyecto se vuelva inmanejable tanto para el desarrollador como para una IA que implemente archivo por archivo.

La idea central no es crear una arquitectura sofisticada. La idea es crear una arquitectura **predecible**, con pocas piezas, con responsabilidades muy claras y con suficiente repetición para que cada módulo se construya casi como una variación controlada del anterior.

### Objetivo técnico real de esta infraestructura

La infraestructura mínima debe resolver lo siguiente:

1. evitar que el controlador JavaFX se convierta en una masa de lógica
2. evitar que cada módulo reinvente paginación, filtros y ordenamiento
3. evitar que la data de demo quede repartida caóticamente por toda la app
4. evitar que la IA tenga que "recordar el sistema entero" para implementar un subárbol pequeño
5. permitir que la app se sienta grande por fuera, pero siga siendo simple por dentro

---

## 20. Principio rector del proyecto

El principio general de esta demo es:

**simular una aplicación administrativa autocontenida, visualmente seria y suficientemente amplia, con infraestructura mínima y comportamiento predecible, sin persistencia real y sin complejidad innecesaria**

Eso significa que toda decisión de arquitectura debe pasar este filtro:

- ¿esto mejora claridad?
- ¿esto reduce repetición dañina?
- ¿esto evita que el controller crezca demasiado?
- ¿esto facilita que una IA implemente por partes?
- ¿esto aporta algo real a la demo?

Si la respuesta es no, no debe entrar.

---

## 21. Reglas anti-colapso escritas en piedra

Estas reglas son obligatorias para proteger la estabilidad del proyecto.

### Regla anti-colapso 1
Cada módulo debe tener exactamente la misma forma general.

### Regla anti-colapso 2
Los controllers no deben filtrar, paginar, ordenar, sembrar datos ni hacer cálculos grandes de composición.

### Regla anti-colapso 3
Toda tabla grande debe pasar por una fachada y por la infraestructura compartida de consulta.

### Regla anti-colapso 4
Toda data demo nace del `DemoStore` y de factories Java. Nunca de FXML, nunca de controladores, nunca de listas pegadas por todas partes.

### Regla anti-colapso 5
Toda consulta visible debe poder expresarse como:
- filtros
- ordenamiento
- paginación
- transformación a modelo de fila

### Regla anti-colapso 6
Las entidades compartidas deben ser pocas y estables. Los detalles puramente visuales se quedan dentro del módulo.

### Regla anti-colapso 7
La repetición estructural es una ventaja. La monotonía aquí es deliberada y útil.

### Regla anti-colapso 8
Ningún módulo debe depender de infraestructura más pesada de la estrictamente necesaria para sostener la ilusión del producto.

---

## 22. Capas mínimas reales de la maqueta

Aunque el proyecto no es una arquitectura empresarial final, sí debe entenderse en capas pequeñas.

## Capa 1. Shell global de aplicación

Esta capa sostiene la existencia del desktop.

### Responsabilidades
- arrancar JavaFX
- crear la ventana principal
- montar el layout base
- manejar sidebar y navegación entre módulos
- mantener el contenedor central
- aplicar tema visual global
- sostener contexto de sesión demo

### Qué clases viven aquí
- `App.java`
- `AppLauncher.java`
- `MainLayoutController.java`
- `ModuleNavigator.java`
- `ThemeManager.java`
- `DemoSession.java`

### Qué no debe vivir aquí
- lógica de negocio de módulos
- seeds
- filtrado de tablas
- composición de datos de cada vista

---

## Capa 2. Store global demo en memoria

Esta capa representa el universo de datos de la maqueta.

### Responsabilidades
- contener todas las listas vivas de la aplicación
- ofrecer un punto único y conocido de acceso a los datos demo
- mantener coherencia mínima entre módulos
- permitir reset del demo

### Qué clases viven aquí
- `DemoStore.java`
- `DemoDataInitializer.java`
- `DemoResetService.java`
- factories de seeds

### Qué contiene el store
Como mínimo, listas de entidades compartidas como:
- clientes
- sucursales
- usuarios
- productos
- proveedores
- ventas
- cobros
- notificaciones
- casos de cobertura
- trabajos técnicos

### Qué no debe hacer el store
- no debe contener lógica visual
- no debe conocer FXML
- no debe conocer paginación de JavaFX
- no debe ser un pseudo ORM

---

## Capa 3. Fachadas por módulo

Esta es una de las piezas más importantes de toda la arquitectura.

La fachada existe para que el módulo tenga una capa intermedia entre el controller y el store.

### Responsabilidades de una fachada
- consultar el `DemoStore`
- aplicar filtros
- aplicar ordenamiento
- aplicar paginación
- transformar entidades a modelos de fila o resumen
- calcular textos útiles para el panel derecho
- devolver resultados listos para pintar

### Ejemplos
- `ClientesFacade`
- `CajaFacade`
- `InventarioFacade`
- `EntregasFacade`

### Qué no debe hacer una fachada
- no debe conocer nodos JavaFX
- no debe acceder a controles FXML
- no debe depender de la tabla visual
- no debe generar seeds

### Regla importante
Toda consulta importante del módulo debe pasar por su fachada.

---

## Capa 4. MVC local del módulo

Dentro de cada módulo existe su pequeña organización local.

### Controller
Controla la vista JavaFX.

#### Debe hacer
- recoger valores de búsqueda y filtros
- reaccionar a eventos de botones
- pedir páginas a la fachada
- llenar la tabla
- actualizar el panel derecho
- actualizar la paginación visible

#### No debe hacer
- filtrar listas grandes
- paginar manualmente
- ordenar colecciones complejas
- crear datos demo
- mezclar demasiadas transformaciones de dominio

### View
Es el FXML principal del módulo.

#### Debe hacer
- definir layout
- contener tabla, filtros, paginación y panel derecho
- mantener consistencia visual con el resto del sistema

#### No debe hacer
- no debe contener datos reales hardcodeados
- no debe tener lógica de negocio escondida

### Filters
Representan el estado de búsqueda del módulo.

#### Deben ser pequeños
Solo campos como:
- texto de búsqueda
- estado
- sucursal
- prioridad
- fecha desde
- fecha hasta

### RowModel
Representa una fila de tabla lista para mostrar.

### SummaryModel
Representa el contenido del panel derecho o resumen contextual.

---

## 23. Infraestructura compartida de consulta

La aplicación necesita una infraestructura compartida mínima para que paginación, ordenamiento y filtros no se repitan torpemente en cada módulo.

## `PageRequest`
Representa la consulta deseada.

### Campos mínimos recomendados
- `pageIndex`
- `pageSize`
- `sortField`
- `sortDirection`

## `PageResult<T>`
Representa el resultado paginado.

### Campos mínimos recomendados
- `List<T> items`
- `pageIndex`
- `pageSize`
- `totalItems`
- `totalPages`

## `SortSpec`
Representa la especificación de orden.

## `PaginationHelper`
Aplica el corte correcto a la lista ya filtrada y ordenada.

## `FilterSupport`
Ayuda con búsqueda de texto, comparación simple de estados, fechas, prioridades y otros filtros comunes.

### Orden correcto del proceso
Siempre debe seguirse este flujo:

1. tomar lista base desde el store
2. aplicar filtros
3. aplicar ordenamiento
4. aplicar paginación
5. transformar a `RowModel`
6. devolver `PageResult<RowModel>`

Nunca debe invertirse ese orden.

---

## 24. Paginación obligatoria como parte de la fachada visual

La paginación no es solo una mejora técnica. También es parte de la propaganda visual del sistema.

Da sensación de:
- volumen real de datos
- madurez de aplicación
- orden
- control de consulta
- experiencia más profesional

### Regla visual obligatoria
Toda vista con tabla grande debe mostrar:
- cantidad de resultados visibles
- total de registros
- página actual
- total de páginas
- botón anterior
- botón siguiente
- quizá selector de tamaño de página si luego se desea

### Regla técnica obligatoria
La paginación debe resolverse con listas en memoria, sin infraestructura externa.

### Estructura mínima recomendada de paginación visible
- label tipo: `Mostrando 20 de 143 registros`
- label tipo: `Página 2 de 8`
- botón `Anterior`
- botón `Siguiente`

Eso es suficiente para que la maqueta se vea convincente.

---

## 25. Filtros y búsqueda como parte del soporte común

Cada módulo debe tener su propia clase de filtros, pero la forma general de trabajar debe ser repetible.

### Patrón recomendado
Un módulo pide a su fachada algo así como:

- lista filtrada
- página actual
- resumen del panel derecho

### Filtros típicos que se repetirán mucho
- texto libre
- estado
- sucursal
- prioridad
- fecha desde
- fecha hasta
- categoría
- responsable

### Regla de claridad
Los filtros viven en clases de filtros del módulo, no como una docena de variables sueltas en el controller.

---

## 26. Seeds en memoria como infraestructura formal

Aunque esta maqueta no usa persistencia, los seeds no deben verse como algo improvisado.

## Estructura correcta
Los seeds deben existir como parte formal de la infraestructura demo.

### Qué clases deben sostener esto
- `DemoDataInitializer`
- `ClienteSeedFactory`
- `ProductoSeedFactory`
- `ProveedorSeedFactory`
- `VentaSeedFactory`
- `CoberturaSeedFactory`
- `TallerSeedFactory`
- etc.

### Estrategia correcta de seeds
Combinar dos cosas:

#### 1. Seeds bonitos y fijos
Sirven para mostrar datos creíbles al cliente.

#### 2. Seeds generados
Sirven para dar volumen y activar paginación, reportes y bandejas.

### Regla de oro
Nunca sembrar listas dentro del controller.

### Regla útil adicional
Si una entidad se reutiliza mucho, su seed factory debe vivir separada y ser llamada por otras factories.

---

## 27. Reset del demo como parte de la arquitectura mínima

Como el sistema es autocontenido y en memoria, conviene asumir que el demo puede quedar “tocado” durante pruebas o demostraciones.

Por eso, el reset no es opcional conceptualmente. Debe formar parte de la arquitectura.

## `DemoResetService`
Debe reconstruir el store completo desde las factories de seeds.

### Beneficios
- evita miedo a probar
- permite demos repetidas
- mantiene consistencia visual
- reduce necesidad de mantenimiento manual entre una presentación y otra

### Regla conceptual
Toda mutación fuerte del demo debe ser reversible simplemente reiniciando o reseteando el store.

---

## 28. Responsabilidades prohibidas en controladores

Esta sección debe quedar muy clara porque aquí es donde suelen empezar los problemas.

Un `Controller` JavaFX de esta maqueta no debe:

- construir seeds
- instanciar veinte listas grandes
- aplicar filtros manuales complejos
- ordenar datos complejos a mano
- cortar páginas de resultados
- transformar masivamente entidades a estructuras de vista
- contener reglas de negocio gruesas del módulo
- mezclar navegación global con lógica local del módulo

Si el controller empieza a hacer varias de esas cosas, el módulo ya se está deformando.

---

## 29. Responsabilidades obligatorias de las fachadas

La `Facade` es la válvula de control que mantiene la maqueta ordenada.

Cada fachada debe poder responder, como mínimo, a estos patrones:

### Consulta principal de tabla
Ejemplo conceptual:
- `findPage(filters, pageRequest)`

### Resumen lateral
Ejemplo conceptual:
- `buildSummary(id)`

### KPI o resumen superior cuando aplique
Ejemplo conceptual:
- `buildHeaderStats(filters)`

### Regla de diseño
La fachada no debe devolver entidades de dominio crudas a la vista si el módulo necesita una presentación más elaborada. Debe devolver `RowModel` y `SummaryModel`.

---

## 30. Dominio compartido versus modelos visuales

Aquí hay que fijar una frontera clara.

## Dominio compartido
Representa entidades reales del sistema, por ejemplo:
- `Cliente`
- `Sucursal`
- `Usuario`
- `Producto`
- `Proveedor`
- `VentaOptica`
- `Cobro`
- `Notificacion`
- `CasoCobertura`
- `TrabajoTecnico`

## Modelos visuales
Representan cómo se muestra algo dentro de un módulo.

Ejemplos:
- `ClientesRowModel`
- `CajaSummaryModel`
- `InventarioRowModel`
- `EntregasSummaryModel`

### Regla de frontera
El dominio compartido no debe contaminarse con necesidades visuales de una tabla o un panel derecho.

---

## 31. Componentes UI compartidos mínimos

La aplicación sí necesita reutilización visual, pero muy controlada.

### Componentes compartidos mínimos permitidos
- `FilterBar`
- `PaginationBar`
- `SummarySidePanel`
- `StatusBadge`
- `KpiCard`
- `EmptyStatePane`

### Qué aportan
- consistencia
- menos repetición
- menor carga para la IA
- mantenimiento visual más simple

### Qué no hacer
No crear una pseudo librería completa de componentes. Eso solo metería ruido.

---

## 32. Navegación mínima estable

La navegación también forma parte de la infraestructura de soporte.

## Navegación global
Debe resolver:
- qué módulo está activo
- qué vista se carga en el centro
- cómo reemplazar el contenedor central sin romper la app

## Navegación local del módulo
Cada módulo resuelve su propia subnavegación interna.

### Regla importante
El shell global no debe conocer el detalle interno de submódulos. Solo debe cargar el módulo principal.

### Beneficio
Esto mantiene aisladas las preocupaciones y evita que el layout global se vuelva gigantesco.

---

## 33. Infraestructura mínima para fachada propagandística

Como el objetivo es competir visualmente y parecer serio, hay ciertos elementos que ya son parte de la infraestructura y no solo del diseño.

### Deben existir en la mayoría de módulos
- tabla central bien poblada
- paginación visible
- filtros claros
- panel derecho persistente
- estados con badges
- empty state elegante
- acciones rápidas visibles

### Propósito de estos elementos
No solo mejoran UX. También sostienen la ilusión de un producto más maduro.

---

## 34. Estrategia oficial para que una IA implemente sin colapsar

Esta sección debe quedar explícita porque es una de las razones principales de todo este diseño.

### Regla 1
Siempre pedir por subárbol pequeño.

### Regla 2
Siempre trabajar módulo por módulo.

### Regla 3
Siempre respetar el contrato fijo del módulo:
- controller
- facade
- filters
- row model
- summary model
- fxml

### Regla 4
No pedir “haz todo el sistema”.

### Regla 5
Cuando un módulo use entidades compartidas, pedir primero esas entidades o reutilizar las ya fijadas.

### Regla 6
Mantener un documento maestro corto con:
- árbol oficial
- reglas de arquitectura
- convenciones de nombre
- decisiones escritas en piedra

### Regla 7
Los componentes compartidos deben definirse primero para que luego los módulos los reutilicen y no los reinventen.

---

## 35. Estructura mínima de flujo dentro de un módulo

La secuencia oficial de trabajo de cada módulo debe entenderse así:

1. el usuario interactúa con la vista
2. el controller recoge el estado de búsqueda y navegación
3. el controller arma `Filters` + `PageRequest`
4. la fachada consulta el `DemoStore`
5. la fachada filtra, ordena y pagina
6. la fachada transforma entidades a modelos visuales
7. devuelve `PageResult<RowModel>` y `SummaryModel`
8. el controller actualiza tabla, resumen y paginación

Este flujo debe repetirse casi sin cambios entre módulos.

---

## 36. Núcleo mínimo que hace existir la maqueta

Si hubiera que resumir toda la infraestructura mínima en piezas esenciales, serían estas:

### Shell
- `App`
- `MainLayoutController`
- `ModuleNavigator`

### Demo en memoria
- `DemoStore`
- `DemoDataInitializer`
- `DemoResetService`
- seed factories

### Consulta compartida
- `PageRequest`
- `PageResult`
- `PaginationHelper`
- `FilterSupport`

### Infraestructura visual mínima
- `FilterBar`
- `PaginationBar`
- `SummarySidePanel`
- `StatusBadge`
- `EmptyStatePane`

### Contrato repetible por módulo
- `Controller`
- `Facade`
- `Filters`
- `RowModel`
- `SummaryModel`
- `View.fxml`

Con eso, la maqueta ya tiene una columna vertebral suficiente.

---

## 37. Qué no debe entrar aunque suene tentador

Para proteger la maqueta, no deberían entrar todavía:

- repositorios complejos de persistencia
- patrones empresariales pesados
- base de datos temporal
- serialización de datos a archivos
- lógica de sincronización externa
- servicios HTTP
- ORM
- DDD formal
- CQRS
- microservicios
- mensajería interna compleja
- motor de reglas avanzado

Nada de eso aporta a la demo propagandística rápida.

---

## 38. Cierre extendido

Este árbol oficial mínimo, junto con esta infraestructura de soporte, no busca impresionar por sofisticación interna. Busca algo más útil para este contexto: que la aplicación parezca grande, organizada y seria por fuera, mientras por dentro sigue siendo una maqueta autocontenida, completamente en memoria y lo bastante estable para implementarse módulo por módulo sin colapsar.

La arquitectura correcta para este demo no es la más elegante en abstracto. Es la que logra tres cosas al mismo tiempo:

- que el frontend se vea convincente
- que el código sea repetible y controlable
- que una IA pueda construirlo por partes sin romper la continuidad del sistema

## 39. Cierre

Este árbol oficial mínimo no está diseñado para ganar premios de arquitectura. Está diseñado para que:

- el demo se vea serio
- la app sea autocontenida
- todo viva en memoria
- la IA pueda trabajar por módulos sin desbordarse
- el proyecto tenga orden suficiente para crecer un poco sin romperse

La clave no es hacerlo sofisticado. La clave es hacerlo **repetible, predecible y modular**.