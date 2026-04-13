# Login - Documentación Técnica

## Resumen
- Pantalla de autenticación de usuarios del sistema Óptica.
- 1 sub-vista (pantalla única de login).
- Patrón arquitectónico: **Controlador de presentación** con transición de escena JavaFX.

## Estructura de Clases

| Clase | Responsabilidad |
|-------|----------------|
| `LoginController.java` | Gestiona la validación del nombre de usuario, establece el contexto global (`CurrentUserContext`) y transiciona hacia `MainLayout.fxml` tras login exitoso. |

**No existen** Facade, Filters, RowModel ni SummaryModel para este módulo, ya que no consume datos tabulares ni aplica filtros. La autenticación es directa (sin verificación de contraseña en la demo).

### Clases de soporte relacionadas
- `CurrentUserContext.java` — Almacena el usuario logueado en contexto global estático.
- `CurrentSucursalContext.java` — Contexto de sucursal activa post-login.
- `MainLayoutController.java` — Layout principal que se carga tras el login.

## Sub-vistas

| Sub-vista | ToggleButton | Qué datos muestra | Qué RowModel usa |
|-----------|-------------|-------------------|------------------|
| Pantalla Login | N/A | Campos de usuario y contraseña, botón de login | N/A |

## Flujo de Datos

```
FXML carga LoginController
    → initialize() (sin configuración inicial)
    → Usuario ingresa nombre en txtUsuario
    → Usuario presiona btnLogin
    → handleLogin() valida que txtUsuario no esté vacío
    → CurrentUserContext.setCurrentUser(user)
    → Carga MainLayout.fxml via FXMLLoader
    → Crea nueva Scene (1280x800, maximizada)
    → Aplica estilos: tokens.css + app.css
    → Reemplaza escena del Stage actual
```

## Filtros Disponibles

Este módulo no aplica filtros. Es una pantalla de autenticación simple.

## Panel Resumen (Derecho)

No aplica. El módulo Login no tiene panel de resumen lateral.

## Patrones de Diseño Aplicados

| Patrón | Descripción |
|--------|-------------|
| **MVC (Vista-Controlador)** | FXML como vista, `LoginController` como controlador. Sin modelo de datos propio. |
| **Contexto Global Estático** | `CurrentUserContext` almacena el usuario activo como variable de clase, accesible desde cualquier módulo. |
| **Singleton implícito** | `CurrentUserContext` y `CurrentSucursalContext` operan como singletons estáticos. |
| **Navigation Controller** | El controlador maneja la navegación reemplazando la escena del `Stage` principal. |

## Integración con Otros Módulos

| Módulo | Dirección | Descripción |
|--------|-----------|-------------|
| `MainLayoutController` | Login → MainLayout | Tras login exitoso, carga el layout principal que contiene el navegador de módulos. |
| `CurrentUserContext` | Login → Todos los módulos | Todos los módulos consultan `CurrentUserContext.getCurrentUser()` para saber el usuario activo. |
| `CurrentSucursalContext` | Login → Contexto | Se inicializa tras el login para filtrar datos por sucursal. |

## Archivos Relacionados

```
modules/login/
├── LoginController.java
└── LoginView.fxml

shared/session/
├── CurrentUserContext.java
└── CurrentSucursalContext.java

app/
├── MainLayoutController.java
├── MainLayout.fxml
└── navigation/
    ├── ModuleNavigator.java
    └── ModuleViewLoader.java
```
