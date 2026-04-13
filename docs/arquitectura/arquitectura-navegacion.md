# Arquitectura de Navegación

## Sistema de Routing entre Módulos

El sistema de navegación de OpticaDemo permite cambiar entre los **19 módulos** de la aplicación de forma dinámica, cargando las vistas FXML bajo demanda sin recrear la ventana principal.

---

## Componentes del Sistema de Navegación

### Diagrama de Flujo

```
Usuario hace clic en sidebar button
         │
         ▼
┌─────────────────────────┐
│  MainLayoutController   │  ← Detecta el evento del botón
│  (event handler)        │
└───────────┬─────────────┘
            │
            ▼
┌─────────────────────────┐
│   ModuleNavigator       │  ← navigateTo(ModuleId)
│  (gestor de vistas)     │     verifica idempotencia
└───────────┬─────────────┘
            │
            ▼
┌─────────────────────────┐
│   ModuleViewLoader      │  ← loadView(ModuleId)
│  (cargador FXML)        │     mapea ModuleId → ruta FXML
└───────────┬─────────────┘
            │
            ▼
┌─────────────────────────┐
│  StackPane contentHost  │  ← setAll(nueva vista)
│  (contenedor principal) │     elimina la vista anterior
└─────────────────────────┘
```

---

## ModuleId (Enum)

### Qué es
Enumeración canonica de los 19 módulos del sistema. Cada constante tiene:
- **Nombre de enum:** Identificador técnico (ej: `VENTA_OPTICA`)
- **displayName:** Texto legible para la UI (ej: `"Venta Optica"`)

### Constantes

```java
public enum ModuleId {
    INICIO("Inicio"),
    AGENDA("Agenda"),
    CLIENTES("Clientes"),
    RECETAS("Recetas"),
    VENTA_OPTICA("Venta Optica"),
    ORDENES_LABORATORIO("Ordenes de Laboratorio"),
    INVENTARIO("Inventario"),
    CAJA("Caja"),
    ENTREGAS("Entregas"),
    SEGUIMIENTO("Seguimiento"),
    REPORTES("Reportes"),
    CONFIGURACION("Configuracion"),
    SEGUROS("Seguros / Cobertura"),
    PROVEEDORES("Proveedores"),
    COMPRAS("Compras"),
    USUARIOS_ROLES("Usuarios y Roles"),
    TALLER("Taller / Reparaciones"),
    NOTIFICACIONES("Notificaciones"),
    SUCURSALES("Sucursales");
}
```

### Quiénes lo usan
| Consumidor | Para qué |
|-----------|----------|
| `ModuleNavigator` | Identificar qué módulo cargar |
| `ModuleViewLoader` | Mapear a la ruta FXML correcta |
| `MainLayoutController` | Construir botones del sidebar |
| `NavigationItem` | Asociar ícono Unicode al módulo |

---

## NavigationItem (Record)

### Qué es
Record que agrupa un `ModuleId` con un **ícono Unicode** para mostrar en el sidebar.

```java
public record NavigationItem(ModuleId moduleId, String icon) {
    public String getDisplayName() {
        return moduleId.getDisplayName();
    }
}
```

### Ejemplo de uso
```java
new NavigationItem(ModuleId.INICIO, "\u2302")        // ⌂
new NavigationItem(ModuleId.AGENDA, "\uD83D\uDCC5")   // 📅
new NavigationItem(ModuleId.CLIENTES, "\uD83D\uDC64") // 👤
```

---

## ModuleViewLoader

### Qué es
Clase que mapea cada `ModuleId` a su archivo FXML correspondiente y lo carga dinámicamente.

### Mapa de rutas FXML

```java
private static final Map<ModuleId, String> FXML_PATHS = Map.ofEntries(
    Map.entry(ModuleId.INICIO, "/fxml/modules/inicio/InicioView.fxml"),
    Map.entry(ModuleId.AGENDA, "/fxml/modules/agenda/AgendaView.fxml"),
    Map.entry(ModuleId.CLIENTES, "/fxml/modules/clientes/ClientesView.fxml"),
    // ... 19 entradas en total
);
```

### Estrategia de carga

```
loadView(ModuleId)
    │
    ├─ Si el ModuleId NO está mapeado → retorna placeholder vacío
    │
    ├─ Si la ruta FXML NO existe como recurso → retorna placeholder
    │
    └─ Si todo OK → FXMLLoader.load(url) → retorna Node cargado
```

### Manejo de errores
El loader **nunca lanza excepciones** al caller. Si algo falla, retorna un `StackPane` placeholder con clase CSS `module-placeholder`. Esto evita que un módulo mal configurado rompa toda la aplicación.

---

## ModuleNavigator

### Qué es
Gestor central de navegación. Recibe un `StackPane` contenedor y reemplaza su contenido con la vista del módulo solicitado.

### Método principal: navigateTo()

```java
public void navigateTo(ModuleId moduleId) {
    // 1. Verificación de idempotencia
    if (currentModule == moduleId) {
        return;  // Ya estamos en este módulo, no recargar
    }
    
    // 2. Actualizar módulo actual
    currentModule = moduleId;
    
    // 3. Cargar la vista
    Node view = viewLoader.loadView(moduleId);
    
    // 4. Reemplazar contenido
    contentHost.getChildren().setAll(view);
}
```

### Por qué setAll() en vez de add()
`setAll()` **elimina todos los nodos anteriores** antes de agregar el nuevo. Esto es crucial porque:
- Evita **memory leaks** (el nodo anterior se libera)
- Garantiza que **solo un módulo** esté activo a la vez
- El garbage collector puede recolectar el controller anterior

### Verificación de idempotencia
Si el usuario hace clic repetidamente en el mismo botón del sidebar, el sistema **no recarga** la vista. Esto evita:
- Reinicialización innecesaria del controller
- Pérdida de datos no guardados en formularios
- Parpadeo visual innecesario

---

## MainLayoutController

### Qué es
Controller del layout principal de la aplicación (post-login). Contiene:
- **Sidebar** (izquierda): 19 botones de navegación
- **Top bar** (arriba): usuario actual, sucursal, selector de sucursal
- **Content host** (centro): StackPane donde se cargan las vistas de módulos

### Construcción del sidebar

```java
private void buildSidebar() {
    for (NavigationItem item : NAV_ITEMS) {
        Button btn = new Button(item.icon() + "  " + item.getDisplayName());
        btn.getStyleClass().add("sidebar-button");
        btn.setOnAction(e -> {
            navigator.navigateTo(item.moduleId());
            highlightButton(item.moduleId());
        });
        sidebarItems.getChildren().add(btn);
    }
}
```

### Selector de sucursal
El `ComboBox` de sucursal en la top bar permite cambiar el contexto de sucursal activa:

```java
cmbSucursal.valueProperty().addListener((obs, old, val) -> {
    CurrentSucursalContext.setSucursal(val);
    // Los datos de los módulos se filtran por sucursal activa
});
```

---

## Ciclo de Vida de un Módulo

### 1. Carga inicial
```
navigateto(ModuLeId) → FXMLLoader crea nueva instancia del Controller
                     → Se ejecuta initialize()
                     → El Controller crea su Facade
                     → El Facade consulta DemoStore
                     → Se popula la TableView
                     → Se actualiza el panel resumen
```

### 2. Interacción del usuario
```
Usuario escribe en filtro → Controller filtra localmente
Usuario cambia página    → Controller pide nueva página al Facade
Usuario hace clic en fila → Controller actualiza panel resumen
```

### 3. Descarga
```
Usuario navega a otro módulo → StackPane.setAll() elimina la vista
                             → El Controller queda sin referencias
                             → GC recolecta todo
```

---

## Arquitectura del Login

### Flujo de arranque

```
AppLauncher.main()
    │
    ▼
App.init()
    ├── Crea DemoStore
    ├── Crea DemoSession
    ├── DemoDataInitializer.initialize() → pobla datos demo
    ├── Configura CurrentuserContext
    └── Configura CurrentSucursalContext
    │
    ▼
App.start(primaryStage)
    ├── Carga /fxml/login/LoginView.fxml
    ├── Aplica CSS (tokens, app, custom-controls, login)
    ├── Configura ícono de ventana
    └── Muestra ventana de login
    │
    ▼
Usuario ingresa credenciales
    │
    ▼
LoginController.handleLogin()
    ├── Valida credenciales
    ├── Carga MainLayout (FXML post-login)
    ├── Actualiza Stage (título, tamaño, ícono)
    └── MainLayoutController carga módulo INICIO por defecto
```

---

## Patrones de Navegación Aplicados

| Patrón | Dónde se aplica | Beneficio |
|--------|----------------|-----------|
| **Front Controller** | ModuleNavigator centraliza toda navegación | Un solo punto de control |
| **View Mapper** | ModuleViewLoader mapea IDs a FXMLs | Fácil de mantener y extender |
| **Idempotencia** | Verificación en navigateTo() | Evita recargas innecesarias |
| **Graceful Degradation** | Placeholder si FXML no existe | La app no crashea por un módulo faltante |
| **Lazy Loading** | Vistas se cargan solo cuando se navega | Arranque más rápido |

---

## Extensibilidad

### Agregar un nuevo módulo

1. Crear `ModuleId.NUEVO_MODULO` en el enum
2. Agregar entrada en `FXML_PATHS` del ModuleViewLoader
3. Agregar `NavigationItem` en la lista `NAV_ITEMS` del MainLayoutController
4. Crear los archivos del módulo:
   - `NuevoModuloController.java`
   - `NuevoModuloFacade.java`
   - `NuevoModuloView.fxml`
5. El sidebar mostrará automáticamente el nuevo botón

---

## Créditos

- **Autor:** Marcos Moreira
- **Versión:** 1.0.0
