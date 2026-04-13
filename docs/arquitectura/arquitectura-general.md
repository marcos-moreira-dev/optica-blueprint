# Arquitectura General - OpticaDemo

## Visión General del Sistema

**OpticaDemo** es un sistema integral de gestión para ópticas, desarrollado como aplicación desktop con **Java 17** y **JavaFX 21**. El sistema cubre las 19 áreas operativas de una óptica: desde la agenda de citas y gestión de pacientes, hasta ventas, inventario, laboratorio, caja y reportes.

---

## Stack Tecnológico

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| **Java (JDK)** | 17 | Lenguaje base, records, pattern matching |
| **JavaFX** | 21.0.1 | Framework UI (controles, CSS, FXML, binding) |
| **Maven** | 3.9+ | Gestión de dependencias y build |
| **jpackage** | JDK 17+ | Empaquetado nativo Windows (MSI) |

---

## Arquitectura de Capas

```
┌─────────────────────────────────────────────────────────┐
│                    Capa de Presentación                  │
│  ┌──────────┐  ┌──────────┐  ┌───────────────────────┐  │
│  │ FXML     │  │ CSS      │  │ Controllers           │  │
│  │ (vistas) │  │ (temas)  │  │ (lógica de UI)        │  │
│  └──────────┘  └──────────┘  └───────────────────────┘  │
├─────────────────────────────────────────────────────────┤
│                    Capa de Negocio (Facade)              │
│  ┌──────────────────────────────────────────────────┐   │
│  │ Facades: ensamblan datos para la vista            │   │
│  │ - Query methods (getColaOrdenes, getKpiCards)    │   │
│  │ - Filter combo providers (getEstados, getTipos)  │   │
│  │ - Summary builders (buildSummary)                │   │
│  └──────────────────────────────────────────────────┘   │
├─────────────────────────────────────────────────────────┤
│                    Capa de Datos (DemoStore)             │
│  ┌──────────────────────────────────────────────────┐   │
│  │ DemoStore: almacén en memoria con ArrayLists      │   │
│  │ - clientes, productos, ventas, citas, etc.       │   │
│  │ - ObservableList para binding con JavaFX         │   │
│  └──────────────────────────────────────────────────┘   │
├─────────────────────────────────────────────────────────┤
│                    Infraestructura                       │
│  ┌──────────┐  ┌──────────┐  ┌───────────────────────┐  │
│  │ Filter   │  │Paginación│  │ Componentes Custom    │  │
│  │ Support  │  │ Helper   │  │ (OpticaButton, etc.)  │  │
│  └──────────┘  └──────────┘  └───────────────────────┘  │
└─────────────────────────────────────────────────────────┘
```

---

## Patrones de Diseño Principales

### 1. MVC (Model-View-Controller)

Cada módulo sigue el patrón MVC:

| Capa | Responsable | Archivos |
|------|------------|----------|
| **Model** | Datos de presentación (RowModel, SummaryModel, Filters) | `*RowModel.java`, `*SummaryModel.java`, `*Filters.java` |
| **View** | Definición visual (layout, controles, estilos) | `*View.fxml`, CSS |
| **Controller** | Lógica de UI (eventos, navegación, binding) | `*Controller.java` |

### 2. Facade

El **Facade** actúa como intermediario entre el Controller y los datos:

```
Controller → Facade → DemoStore → RowModel → TableView
```

**Responsabilidades del Facade:**
- Consultar datos del store y transformarlos en RowModels
- Aplicar filtros sobre las colecciones
- Proveer opciones para combos (estados, tipos, categorías)
- Construir resúmenes para el panel derecho

**Los Facades NO contienen lógica de negocio.** Solo ensamblan datos orientados a la vista.

### 3. Feature-First + Module Package

La organización del código es **por módulo funcional**:

```
com.marcosmoreira.opticademo.modules
├── agenda/
│   ├── AgendaController.java
│   ├── AgendaFacade.java
│   ├── AgendaFilters.java
│   ├── AgendaRowModel.java
│   ├── AgendaSummaryModel.java
│   └── AgendaView.fxml
├── clientes/
│   ├── ClientesController.java
│   ├── ClientesFacade.java
│   └── ...
└── ...
```

**Ventaja:** Cada módulo es autocontenido. Para entender un módulo, solo necesitas leer sus 5-6 archivos.

---

## Flujo de Datos Típico

```
1. Usuario hace clic en "Clientes" en el sidebar
2. ModuleNavigator carga ClientesView.fxml
3. ClientesController.initialize() se ejecuta:
   a. Crea ClientesFacade con referencia al DemoStore
   b. Configura filtros (combos de estado, última visita, receta)
   c. Carga la primera página de clientes
   d. Actualiza el panel resumen derecho
4. Usuario escribe en el campo de búsqueda
5. Controller filtra localmente con FilterSupport
6. Controller pide nueva página al Facade
7. Facade consulta DemoStore, aplica filtros, retorna PageResult
8. Controller actualiza la TableView con los nuevos RowModels
```

---

## Componentes Transversales

### Navegación
- **ModuleId**: Enum con los 19 módulos
- **ModuleNavigator**: Gestiona el cambio de vistas en un StackPane
- **ModuleViewLoader**: Mapea ModuleId → ruta FXML

### Sesión
- **CurrentUserContext**: Static singleton del usuario logueado
- **CurrentSucursalContext**: Static singleton de la sucursal activa
- **DemoSession**: Credenciales por defecto para demo

### Tema
- **ThemeManager**: Gestiona modo Light/Dark
- **ThemeTokens**: Constantes CSS (colores, fondos, textos)
- **ThemeMode**: Enum LIGHT/DARK

### Componentes UI Custom
- **OpticaButton**: Button con variantes de estilo y tamaños
- **OpticaTextField**: TextField con validación visual
- **OpticaComboBox**: ComboBox con iconos y placeholder
- **OpticaDatePicker**: DatePicker con validación de rangos
- **OpticaToggleButton**: ToggleButton con pseudo-clases CSS

---

## Empaquetado

La aplicación se distribuye como **MSI de Windows** mediante jpackage:

```
build-installer.bat
  ├── mvn clean package          → genera el JAR
  ├── copia JAR a target/libs/
  └── jpackage --icon logo.ico   → genera installer/OpticaDemo-1.0.0.msi
```

El `.ico` es multiresolución (16x16 a 256x256) para que Windows lo muestre correctamente en la barra de tareas, menú inicio y acceso directo.

---

## Decisiones de Arquitectura

### ¿Por qué in-memory y no base de datos?
- **Demo autocontenida**: El usuario instala y usa sin configurar BD
- **Simplicidad**: Sin drivers, conexiones, migraciones
- **Demostración**: Datos realistas pre-cargados

### ¿Por qué Facade y no Service layer?
- **Orientación a vista**: El Facade prepara datos exactamente como la UI los necesita
- **Sin lógica de negocio**: En producción, el Facade se reemplazaría por un Service con lógica real
- **Separación clara**: El Controller no sabe de ArrayLists ni filtros manuales

### ¿Por qué String para fechas en lugar de LocalDate?
- **Presentación directa**: Las fechas ya vienen formateadas para la UI
- **Sin CellFactory en cada columna**: Se muestra el string tal cual
- **Trade-off aceptable**: En producción se usaría LocalDate con formatter

---

## Estructura Completa del Proyecto

```
optica-demo-desktop/
├── pom.xml                              → Maven project file
├── build-installer.bat                  → Script de empaquetado
├── logo.png                             → Logo original
├── logo.ico                             → Icono Windows multiresolución
├── src/main/
│   ├── java/com/marcosmoreira/opticademo/
│   │   ├── app/                         → Aplicación principal
│   │   │   ├── App.java                 → JavaFX Application
│   │   │   ├── AppLauncher.java         → Entry point JVM
│   │   │   ├── MainLayoutController.java → Layout post-login
│   │   │   ├── navigation/              → Sistema de navegación
│   │   │   ├── session/                 → Contexto de sesión
│   │   │   └── theme/                   → Gestión de tema visual
│   │   ├── demo/                        → Datos de demostración
│   │   │   ├── DemoStore.java           → Almacén en memoria
│   │   │   ├── DemoDataInitializer.java → Poblador de datos
│   │   │   ├── DemoResetService.java    → Servicio de reset
│   │   │   ├── generator/               → Generadores (fechas, refs, random)
│   │   │   └── seed/                    → Constantes de seed data
│   │   ├── shared/                      → Componentes compartidos
│   │   │   ├── ui/components/           → Controles custom JavaFX
│   │   │   ├── query/                   → FilterSupport
│   │   │   └── domain/                  → Entidades del dominio
│   │   └── modules/                     → 19 módulos funcionales
│   │       ├── agenda/
│   │       ├── clientes/
│   │       ├── ... (17 más)
│   └── resources/
│       ├── fxml/                        → Vistas FXML
│       ├── css/                         → Hojas de estilo
│       ├── icons/                       → Iconos multiresolución (16-256px)
│       └── logo.png                     → Logo fallback
└── docs/
    ├── manual-usuario/                  → 21 .md para optometristas
    ├── arquitectura/                    → Documentación técnica de arquitectura
    ├── modulos/                         → Documentación técnica por módulo
    └── dominio/                         → Documentación de entidades
```

---

## Créditos

- **Autor:** Marcos Moreira
- **Versión:** 1.0.0
- **Tecnología:** Java 17, JavaFX 21, Maven 3.9
- **Empaquetado:** jpackage MSI para Windows
