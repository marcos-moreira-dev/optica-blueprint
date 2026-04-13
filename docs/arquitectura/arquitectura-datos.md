# Arquitectura de Datos

## DemoStore y Sistema de Datos en Memoria

OpticaDemo utiliza un enfoque de **almacén de datos en memoria** (in-memory store) en lugar de una base de datos relacional. Esta decisión arquitectónica permite que la aplicación sea completamente autocontenida y funcional sin configuración externa.

---

## DemoStore

### Qué es
`DemoStore` es la clase que actúa como "base de datos" de toda la aplicación. Contiene listas de todas las entidades del dominio como `ArrayList` públicos y mutables.

### Estructura

```java
public class DemoStore {
    public final List<Cliente> clientes = new ArrayList<>();
    public final List<Sucursal> sucursales = new ArrayList<>();
    public final List<Usuario> usuarios = new ArrayList<>();
    public final List<Producto> productos = new ArrayList<>();
    public final List<Proveedor> proveedores = new ArrayList<>();
    public final List<VentaOptica> ventas = new ArrayList<>();
    public final List<Cobro> cobros = new ArrayList<>();
    public final List<Notificacion> notificaciones = new ArrayList<>();
    public final List<CasoCobertura> coberturas = new ArrayList<>();
    public final List<TrabajoTecnico> trabajosTecnicos = new ArrayList<>();
}
```

### Por qué ArrayList y no ObservableList
En una versión futura, estas listas podrían envolverce en `ObservableList` para binding automático con JavaFX. Actualmente se usa `ArrayList` por simplicidad y los Controllers actualizan las tablas manualmente.

### Ciclo de Vida

```
App.init()
    │
    ▼
new DemoStore()          → Listas vacías
    │
    ▼
DemoDataInitializer.initialize()
    │
    ├── createSucursales()    → 5 sucursales
    ├── createUsuarios()      → 10 usuarios con roles
    ├── createClientes()      → 50 clientes con datos realistas
    ├── createProveedores()   → 15+ proveedores
    ├── createProductos()     → 40+ productos
    ├── createVentas()        → Ventas simuladas
    ├── createCobros()        → Cobros asociados
    ├── createNotificaciones() → Notificaciones pendientes
    ├── createCoberturas()    → Casos de seguro
    └── createTrabajosTecnicos() → Reparaciones del taller
    │
    ▼
App.getDemoStore()           → Acceso global estático
```

### Acceso Global

```java
// Desde cualquier punto de la aplicación:
DemoStore store = App.getDemoStore();
List<Cliente> clientes = store.clientes;
```

El patrón `AppSessionHolder` (inner class estática) permite este acceso global sin crear un singleton tradicional.

---

## DemoDataInitializer

### Qué es
Clase responsable de poblar el `DemoStore` con datos realistas. Utiliza generadores auxiliares para crear datos variados y coherentes.

### Generadores Utilizados

| Generador | Qué produce |
|-----------|-------------|
| **ReferenceGenerator** | IDs secuenciales (CLI-001, OV-001, LAB-001) |
| **DateGenerator** | Fechas realistas (pasadas, futuras, offsets) |
| **DemoRandoms** | Selección aleatoria de listas, montos, probabilidades |
| **SharedSeedSupport** | Constantes: nombres de clientes, marcas, categorías |

### Estrategia de Poblado

1. **Sucursales primero:** Son la base (todo se asigna a una sucursal)
2. **Usuarios segundo:** Se asignan a sucursales existentes
3. **Clientes tercero:** Se asignan a sucursales
4. **Proveedores:** Independientes de sucursales
5. **Productos:** Asignados a sucursales y proveedores
6. **Ventas:** Referencian clientes, sucursales, usuarios y productos
7. **Cobros, notificaciones, coberturas:** Derivados de ventas y clientes

### Seed Data Principal

#### Clientes (50 registrados)
Datos realistas con:
- Nombres de personas ecuatorianas
- Documentos de identidad válidos
- Teléfonos y emails
- Estados de receta: VIGENTE, POR_VENCER, VENCIDA, SIN_RECETA
- Distribución entre sucursales

#### Sucursales (5 sedes)
| Código | Nombre | Estado |
|--------|--------|--------|
| SUC-001 | Matriz Centro | Activo |
| SUC-002 | Norte Mall | Activo |
| SUC-003 | Sur Express | Observado |
| SUC-004 | Este Plaza | Activo |
| SUC-005 | Oeste Center | Observado |

#### Usuarios (10 con roles variados)
| Usuario | Rol | Sucursal |
|---------|-----|----------|
| Marcos Moreira | Administrador General | Matriz Centro |
| Patricia Mendoza | Administrador de Sede | Norte Mall |
| Carlos Zambrano | Asesor de Ventas | Matriz Centro |
| Laura Escobar | Cajero | Matriz Centro |
| Ricardo Salazar | Técnico Óptico | Matriz Centro |

---

## ReferenceGenerator

### Qué es
Generador de identificadores secuenciales con prefijo configurable y thread-safe.

### Cómo funciona
```java
ReferenceGenerator refs = new ReferenceGenerator();
refs.next("CLI");  // "CLI-00001"
refs.next("CLI");  // "CLI-00002"
refs.next("OV");   // "OV-00001" (contador independiente)
refs.of("LAB", 5); // "LAB-00005" (sin incrementar)
```

### Thread-safety
Usa `ConcurrentHashMap<String, AtomicInteger>` para garantizar atomicidad por prefijo en entornos concurrentes.

### Padding configurable
Por defecto usa 5 dígitos (`00001`). Se puede configurar:
```java
new ReferenceGenerator(3);  // "CLI-001"
new ReferenceGenerator(7);  // "CLI-0000001"
```

---

## DateGenerator

### Qué es
Generador de fechas formateadas como strings `dd/MM/yyyy`.

### Métodos principales

| Método | Qué retorna | Ejemplo |
|--------|-------------|---------|
| `today()` | Fecha de hoy | "13/04/2026" |
| `pastDate(30)` | Fecha aleatoria en los últimos 30 días | "25/03/2026" |
| `futureDate(15)` | Fecha aleatoria en los próximos 15 días | "20/04/2026" |
| `offsetDays(-7)` | Fecha hace exactamente 7 días | "06/04/2026" |
| `randomBetween(inicio, fin)` | Fecha aleatoria en un rango | Variable |

### Por qué Strings y no LocalDate
Los domain models almacenan fechas como strings para simplificar la presentación en UI. No se necesitan `StringConverter` ni `CellFactory` en cada columna de tabla.

**Trade-off:** Se pierde la capacidad de hacer operaciones de fecha directamente sobre el modelo. En producción se usaría `LocalDate`.

---

## DemoRandoms

### Qué es
Utilitario estático para selección aleatoria y generación de valores random.

### Métodos

| Método | Qué hace | Uso en DemoDataInitializer |
|--------|----------|---------------------------|
| `pick(List<T>)` | Elemento aleatorio de lista | Elegir sucursal, profesional, producto |
| `pick(T... options)` | Elemento aleatorio de opciones | Elegir estado, material, tratamiento |
| `randomAmount(min, max)` | Double aleatorio con 2 decimales | Generar precios de productos |
| `randomInt(min, max)` | Int aleatorio en rango | Generar cantidades, edades |
| `chance(probability)` | Boolean con probabilidad | Decidir si un cliente está activo (80%) |

---

## SharedSeedSupport

### Qué es
Clase de constantes que provee las listas de datos semilla usadas por los generadores.

### Contenido principal

| Constante | Tipo | Ejemplos |
|-----------|------|----------|
| `NOMBRES_CLIENTES` | List<String> | "Sofia Ramirez", "Juan Cedeno", "Carmen Lopez" |
| `NOMBRES_PROFESIONALES` | List<String> | "Dra. Gabriela Ruiz", "Dr. Andres Villavicencio" |
| `SUCURSALES_NOMBRES` | List<String> | "Matriz Centro", "Norte Mall", "Sur Express" |
| `MARCAS_MONTURAS` | List<String> | "Ray-Ban", "Oakley", "Vogue", "Armani" |
| `CATEGORIAS_PRODUCTO` | List<String> | "Montura", "Lente", "Accesorio" |
| `TIPOS_LENTES` | List<String> | "Monofocal", "Bifocal", "Progresivo" |
| `TRATAMIENTOS` | List<String> | "Antirreflejo", "Filtro UV", "Blue-cut" |
| `MATERIALES_MONTURA` | List<String> | "Metal", "Acetato", "TR-90", "Titanio" |
| `MÉTODOS_PAGO` | List<String> | "Efectivo", "Tarjeta de débito", "Tarjeta de crédito" |
| `NOMBRES_TÉCNICOS` | List<String> | "Carlos Mendez", "Jorge Villamar" |

---

## DemoResetService

### Qué es
Servicio que limpia y repuebla completamente el `DemoStore`.

### Cuándo se usa
- Durante desarrollo: resetear a datos limpios
- Para demos: asegurar datos consistentes
- Testing: volver al estado inicial

### Implementación
```java
public void reset() {
    store.clear();        // Limpia todas las listas
    initializer.initialize(); // Repuebla con seed data
}
```

---

## Flujo de Datos Completo

### Lectura (Query)

```
Controller
    │
    ├── Facade.getDatos(filtros)
    │       │
    │       ├── DemoStore.clientes (ArrayList)
    │       ├── FilterSupport.matchesText()   → filtra por nombre
    │       ├── FilterSupport.matchesExact()  → filtra por estado
    │       ├── FilterSupport.inRange()       → filtra por fecha
    │       │
    │       └── Retorna List<RowModel>
    │
    └── Controller actualiza TableView con RowModels
```

### Escritura (Demo solo lectura)

En la demo, los datos son **solo lectura**. Los Facades consultan pero no modifican el store. En una versión de producción:

```
Controller → Service layer → Repository → Base de datos
                │
                └── Notifica cambios
                      │
                      └── Refresh UI (Observable/EventBus)
```

---

## Patrones de Datos Aplicados

| Patrón | Dónde | Beneficio |
|--------|-------|-----------|
| **In-Memory Store** | DemoStore como única fuente de datos | Sin dependencias externas |
| **Seed Data** | Datos pre-cargados realistas | Demo funcional inmediatamente |
| **Repository Pattern (implícito)** | Facades como capa de acceso a datos | Controllers no saben de ArrayLists |
| **Data Transfer Object** | RowModels como vista aplanada de entidades | TableView recibe datos listos |
| **Generator Pattern** | ReferenceGenerator, DateGenerator, DemoRandoms | Datos coherentes y reproducibles |

---

## Limitaciones del Enfoque In-Memory

| Limitación | Impacto | Solución en producción |
|------------|---------|----------------------|
| **Sin persistencia** | Al cerrar la app se pierden los cambios | Base de datos (PostgreSQL, MySQL) |
| **Sin concurrencia real** | Un solo usuario a la vez | Conexiones pool + transacciones |
| **Sin validación de integridad** | Referencias huérfanas posibles | Foreign keys + constraints |
| **Sin paginación real en BD** | Se filtra todo en memoria | LIMIT/OFFSET en SQL |
| **Sin búsqueda full-text** | Búsqueda por contains() simple | Elasticsearch o LIKE en SQL |

---

## Créditos

- **Autor:** Marcos Moreira
- **Versión:** 1.0.0
