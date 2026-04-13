# Arquitectura de UI

## Componentes Custom, Temas y Diseño Visual

La interfaz de OpticaDemo sigue un **sistema de diseño consistente** basado en tokens CSS, componentes reutilizables y patrones de layout transversales aplicados en los 19 módulos.

---

## Sistema de Temas

### ThemeTokens

Constantes CSS que definen la paleta visual completa del sistema:

#### Colores de Acento
| Token | Valor | Uso |
|-------|-------|-----|
| `COLOR_PRIMARY` | `#2B5B6E` | Botones principales, encabezados, links activos |
| `COLOR_SECONDARY` | `#5A7D8C` | Elementos secundarios, bordes sutiles |
| `COLOR_ALERT` | `#C4882B` | Advertencias, estados pendientes |
| `COLOR_SUCCESS` | `#4A8C5C` | Confirmaciones, estados activos, éxito |
| `COLOR_DANGER` | `#A64B4B` | Errores, estados críticos, eliminaciones |

#### Fondos
| Token | Valor | Uso |
|-------|-------|-----|
| `BG_GENERAL` | `#F4F6F8` | Fondo general de la aplicación |
| `BG_SURFACE` | `#FFFFFF` | Fondo de tarjetas, paneles, tablas |
| `BG_SIDEBAR` | `#1E3A4A` | Fondo de la barra lateral de navegación |

#### Textos
| Token | Valor | Uso |
|-------|-------|-----|
| `TEXT_PRIMARY` | `#1A202C` | Texto principal (encabezados, datos) |
| `TEXT_SECONDARY` | `#5A6474` | Texto secundario (labels, descripciones) |
| `TEXT_ON_PRIMARY` | `#FFFFFF` | Texto sobre fondo primary (botones) |

### ThemeMode

Enum con dos modos visuales:
```java
public enum ThemeMode {
    LIGHT,  // Modo claro (por defecto)
    DARK    // Modo oscuro (configurable)
}
```

### ThemeManager

Singleton estático que gestiona el modo de tema actual:
```java
ThemeManager.setThemeMode(ThemeMode.LIGHT);  // Configurar
ThemeManager.getCurrentTheme();               // Consultar
ThemeManager.isDark();                        // Boolean check
```

---

## Hojas de Estilo CSS

### tokens.css
Define las variables CSS custom properties basadas en ThemeTokens:
```css
.root {
    -color-primary: #2B5B6E;
    -color-secondary: #5A7D8C;
    -bg-general: #F4F6F8;
    -bg-surface: #FFFFFF;
    -bg-sidebar: #1E3A4A;
    /* ... más variables */
}
```

### app.css
Estilos generales de la aplicación:
- Layout del BorderPane root
- Estilos del sidebar (botones, hover, active)
- Estilos de la top bar
- Estilos de tablas (cabeceras, filas, hover)
- Estilos de paneles divididos (SplitPane)

### custom-controls.css
Estilos específicos para los componentes custom:
- OpticaButton (variantes PRIMARY, SECONDARY, DANGER, SUCCESS)
- OpticaTextField (estados VALID, INVALID, WARNING)
- OpticaComboBox (iconos, placeholder)
- OpticaDatePicker (ícono de calendario)
- OpticaToggleButton (estado seleccionado/no seleccionado)

### login.css
Estilos exclusivos de la pantalla de login:
- Panel de marca (izquierda): gradiente, logo, texto
- Panel de formulario (derecha): campos, botón, errores

---

## Componentes UI Custom

### OpticaButton

Extiende `ButtonBase` con soporte integrado para variantes de estilo e íconos.

#### Variantes de estilo
```java
public enum Style {
    PRIMARY,    // Fondo primary, texto blanco
    SECONDARY,  // Borde primary, fondo transparente
    DANGER,     // Fondo danger (rojo)
    SUCCESS     // Fondo success (verde)
}
```

#### Tamaños
```java
public enum Size {
    SMALL,    // Botón compacto para tablas
    MEDIUM,   // Tamaño estándar (por defecto)
    LARGE     // Botón prominente para acciones principales
}
```

#### Constructores encadenados
```java
new OpticaButton("Guardar");                          // Default: PRIMARY, MEDIUM
new OpticaButton("Eliminar", Style.DANGER);           // DANGER, MEDIUM
new OpticaButton("Guardar", Style.PRIMARY, Size.LARGE); // Explicito
```

#### Integración con FontAwesome
Soporta íconos Unicode de FontAwesome como prefijo del texto.

---

### OpticaTextField

Extiende `TextField` con validación visual integrada.

#### Estados de validación
| Estado | Visual | Cuándo aplica |
|--------|--------|---------------|
| **VALID** | Borde verde | El valor pasa la validación |
| **INVALID** | Borde rojo + mensaje de error | El valor falla la validación |
| **WARNING** | Borde ámbar | Advertencia (no bloqueo) |

#### Validadores soportados
| Tipo | Configuración |
|------|---------------|
| **Required** | Campo no puede estar vacío |
| **Regex** | Debe coincidir con patrón (ej: email, teléfono) |
| **Custom** | Validador personalizado vía lambda |

---

### OpticaComboBox

Extiende `ComboBox` con soporte para íconos y placeholder.

#### Características
- Ícono FontAwesome como prefijo visual
- Texto placeholder cuando no hay selección
- Búsqueda integrada (searchable)
- Estilo consistente con el tema

---

### OpticaDatePicker

Extiende `DatePicker` con validación de rangos.

#### Validaciones
| Restricción | Qué hace |
|-------------|----------|
| `minDate` | No permite fechas anteriores |
| `maxDate` | No permite fechas posteriores |

---

### OpticaToggleButton

Extiende `ToggleButton` con estilizado de estados.

#### Pseudo-clases CSS
| Estado | Pseudo-clase | Visual |
|--------|-------------|--------|
| Seleccionado | `:selected` | Fondo primary, texto blanco |
| No seleccionado | default | Borde sutil, fondo transparente |

---

## Patrones de Layout Transversales

### BorderPane Root
Cada módulo usa un `BorderPane` como contenedor raíz:

```
┌─────────────────────────────────────────────┐
│                  Top                         │  ← Header del módulo
├─────────────────────────────────────────────┤
│                                             │
│                 Center                       │  ← Contenido principal
│                 (SplitPane)                  │     (SplitPane horizontal)
│                                             │
├─────────────────────────────┬───────────────┤
│                             │               │
│     Tabla / Contenido       │   Resumen     │  ← Panel derecho
│     (68-72% del ancho)     │   (28-32%)    │     (ScrollPane + VBox)
│                             │               │
└─────────────────────────────┴───────────────┘
```

### Sub-navegación por ToggleGroup
Cada módulo con sub-vistas usa un grupo de `ToggleButton` para cambiar entre ellas:

```
[ Día ] [ Semana ] [ Lista ] [ Espera ] [ Confirmaciones ] [ Horarios ]
   ↑         ↑         ↑         ↑            ↑                  ↑
 ToggleButton conectados al mismo ToggleGroup
```

Al seleccionar un botón, el Controller:
1. Oculta la sub-vista actual
2. Muestra la sub-vista seleccionada
3. Actualiza el panel resumen

### Filtros en FlowPane
La barra de filtros usa un `FlowPane` con disposición horizontal:

```
┌──────────────────────────────────────────────────────────────────┐
│ [🔍 Buscar...]  [Estado: Todos ▼]  [Tipo: Todos ▼]  [Buscar]   │
└──────────────────────────────────────────────────────────────────┘
```

### Paginación
Las tablas con muchos datos usan paginación:
- Por defecto: 10-15 filas por página
- Controles: Primera, Anterior, Siguiente, Última
- El Facade calcula el `PageResult` con offset y limit

---

## Estados Visuales con Colores

### Badges de Estado
Los estados se muestran con colores consistentes en toda la aplicación:

| Estado | Color | Hex |
|--------|-------|-----|
| Activo / Vigente / Completada | 🟢 Verde | `#4A8C5C` |
| Pendiente / Por vencer / Observado | 🟡 Ámbar | `#C4882B` |
| Inactivo / Vencida / Cancelada | 🔴 Rojo | `#A64B4B` |
| En proceso / Enviada | 🔵 Azul | `#2B5B6E` |
| Neutral / Sin estado | ⚫ Gris | `#5A6474` |

### CellFactory para StatusBadge
Las columnas de estado usan una `CellFactory` personalizada que renderiza un badge con el color correspondiente al valor del estado.

---

## Tipografía

### Familia
Sans-serif genérica (`System`, `Segoe UI` en Windows) para legibilidad en pantalla.

### Jerarquía
| Nivel | Tamaño | Peso | Uso |
|-------|--------|------|-----|
| **H1** | 24px | Bold | Títulos de módulo |
| **H2** | 18px | SemiBold | Subtítulos de sección |
| **H3** | 14px | SemiBold | Labels de campo |
| **Body** | 13px | Regular | Texto general, tablas |
| **Small** | 11px | Regular | Notas al pie, tooltips |

---

## Espaciado

| Elemento | Valor | Uso |
|----------|-------|-----|
| **Gap entre campos** | 8px | Espacio vertical entre inputs |
| **Padding de paneles** | 16px | Margen interno de contenedores |
| **Gap de botones** | 10px | Espacio horizontal entre botones |
| **Padding de celdas** | 6px | Espacio interno de celdas de tabla |

---

## Iconografía

### Enfoque
Se prefieren **íconos Unicode/FontAwesome** sobre imágenes para:
- Escalabilidad (se ven bien a cualquier tamaño)
- Consistencia visual
- Menor peso en recursos

### Catálogo por módulo
| Módulo | Ícono | Unicode |
|--------|-------|---------|
| Inicio | ⌂ | `\u2302` |
| Agenda | 📅 | `\uD83D\uDCC5` |
| Clientes | 👤 | `\uD83D\uDC64` |
| Recetas | 👁 | `\uD83D\uDC41` |
| Venta Óptica | 🥃 | `\uD83E\uDD43` |
| Órdenes Lab | 📋 | `\uD83D\uDCCB` |
| Inventario | 📦 | `\uD83D\uDCE6` |
| Caja | 💰 | `\uD83D\uDCB0` |
| Entregas | ✅ | `\u2705` |
| Seguimiento | 🔔 | `\uD83D\uDD14` |
| Reportes | 📊 | `\uD83D\uDCCA` |
| Configuración | ⚙ | `\u2699` |
| Seguros | 🛡 | `\uD83D\uDEE1` |
| Proveedores | 🏮 | `\uD83C\uFE2E` |
| Compras | 🛒 | `\uD83D\uDED2` |
| Usuarios/Roles | 🔑 | `\uD83D\uDD11` |
| Taller | 🔧 | `\uD83D\uDD27` |
| Notificaciones | 🔔 | `\uD83D\uDD14` |
| Sucursales | 🏢 | `\uD83C\uFE22` |

---

## Patrones de UI Aplicados

| Patrón | Dónde | Descripción |
|--------|-------|-------------|
| **Design Tokens** | tokens.css + ThemeTokens | Valores centralizados para consistencia |
| **Component Library** | Optica* components | Controles reutilizables con estilo propio |
| **Responsive-ish** | SplitPane con divider | El usuario ajusta proporciones del layout |
| **Empty State** | Placeholder en tablas vacías | Mensaje amigable cuando no hay datos |
| **Loading State** | ProgressBar durante carga | Indicador visual de operación en curso |
| **Error State** | Dialog de error | Mensaje claro cuando algo falla |
| **Confirmation Dialog** | Antes de acciones destructivas | "¿Está seguro?" antes de eliminar |

---

## Iconos del Aplicativo (Branding)

### Estructura de recursos de iconos

```
src/main/resources/
├── icons/
│   ├── icon-16.png    ← Barra de tareas Windows (16px)
│   ├── icon-32.png    ← Barra de tareas Windows HD (32px)
│   ├── icon-48.png    ← Barra de título de ventana (48px)
│   ├── icon-64.png    ← Alt+Tab (64px)
│   ├── icon-128.png   ← Pantallas HiDPI
│   └── icon-256.png   ← Máxima resolución
├── logo.png           ← Logo original (568x393, fallback)
```

### logo.ico
Archivo `.ico` multiresolución para el empaquetado con jpackage. Contiene las 6 resoluciones embebidas en un solo archivo para que Windows lo use en todos los contextos (taskbar, menú inicio, escritorio, Alt+Tab).

### Asignación en JavaFX
```java
primaryStage.getIcons().addAll(
    new Image(getClass().getResourceAsStream("/icons/icon-16.png")),
    new Image(getClass().getResourceAsStream("/icons/icon-32.png")),
    new Image(getClass().getResourceAsStream("/icons/icon-48.png")),
    new Image(getClass().getResourceAsStream("/icons/icon-64.png")),
    new Image(getClass().getResourceAsStream("/icons/icon-128.png")),
    new Image(getClass().getResourceAsStream("/icons/icon-256.png"))
);
```

JavaFX selecciona automáticamente el tamaño más apropiado para cada contexto.

---

## Créditos

- **Autor:** Marcos Moreira
- **Versión:** 1.0.0
