# Modelos de Dominio - Entidades Generales

## Visión General

Las entidades de dominio representan los objetos del negocio de la óptica. Todas heredan de `BaseEntity` y utilizan `EstadoGeneral` como enum polimórfico de estados.

---

## BaseEntity

### Qué es
Clase abstracta base para todas las entidades del dominio. Proporciona un identificador único.

```java
public abstract class BaseEntity {
    private String id;
    
    public BaseEntity(String id) {
        this.id = id;
    }
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
}
```

### Entidades que heredan de BaseEntity
| Entidad | Package | Identificador típico |
|---------|---------|---------------------|
| **Cliente** | `shared.domain.cliente` | `CLI-001`, `CLI-002` |
| **Sucursal** | `shared.domain.sucursal` | `SUC-001`, `SUC-002` |
| **Usuario** | `shared.domain.usuario` | `USR-001`, `USR-002` |
| **Producto** | `shared.domain.producto` | `MON-00045`, `LEN-00012` |
| **Proveedor** | `shared.domain.proveedor` | `PROV-001`, `PROV-002` |
| **VentaOptica** | `shared.domain.venta` | `OV-00001`, `OV-00002` |
| **Cobro** | `shared.domain.caja` | `COB-00001` |
| **Notificacion** | `shared.domain.notificacion` | `NOT-00001` |
| **CasoCobertura** | `shared.domain.cobertura` | `COB-00001` |
| **TrabajoTecnico** | `shared.domain.taller` | `TR-00001` |

---

## EstadoGeneral

### Qué es
Enum polimórfico que representa **todos los estados posibles** de cualquier entidad en el sistema.

### Valores disponibles

| Estado | Módulo típico | Significado |
|--------|--------------|-------------|
| **ACTIVO** | Cliente, Proveedor, Usuario | Entidad habilitada para uso |
| **INACTIVO** | Cliente, Usuario | Entidad deshabilitada (no eliminada) |
| **PENDIENTE** | Cita, Orden, Pago | Esperando acción |
| **CONFIRMADO** | Cita | El paciente confirmó asistencia |
| **CANCELADO** | Cita, Orden | Fue anulado |
| **EN_PROCESO** | Venta, Orden Lab, Trabajo Taller | Se está trabajando en ello |
| **LISTO** | Orden Lab, Trabajo Taller | Terminado, esperando retiro |
| **ENTREGADO** | Venta, Trabajo Taller | Ya fue entregado al paciente |
| **POR_COBRAR** | Venta | Tiene saldo pendiente de pago |
| **PAGADO** | Venta, Cobro | Pago completo |
| **VENCIDO** | Receta, Pago | Expiró el plazo |
| **BAJO_STOCK** | Producto | Stock por debajo del mínimo |
| **AGOTADO** | Producto | Sin unidades disponibles |
| **REQUIERE_REVISION** | Orden, Trabajo | Necesita verificación adicional |
| **EN_ESPERA** | Cita, Orden | Detenido temporalmente |
| **REPROGRAMADO** | Cita | Cambió de fecha/hora |
| **ATENDIDO** | Cita | Ya fue atendido |
| **CERRADO** | Sesión, Caja | Finalizado |
| **ENVIADO** | Orden Lab, Notificación | Despachado a destino |
| **RECIBIDO** | Orden Lab | Vuelto del laboratorio externo |
| **PARCIAL** | Cobro, Recepción | Parcialmente completado |
| **RECHAZADO** | Reclamación, Autorización | No aprobado |
| **OBSERVADO** | Sucursal, Proveedor | Con observaciones pendientes |
| **BLOQUEADO** | Usuario | Acceso denegado |
| **COMPLETADO** | Proceso, Tarea | Finalizado exitosamente |

### Por qué un solo enum y no varios
**Ventaja:** Simplifica el binding con la UI. Un solo `ComboBox<EstadoGeneral>` puede usarse en múltiples módulos.
**Desventaja:** No todos los estados aplican a todas las entidades (ej: `BAJO_STOCK` no aplica a Clientes).

---

## Prioridad

### Qué es
Enum para niveles de urgencia en tareas, seguimientos y notificaciones.

```java
public enum Prioridad {
    ALTA,    // Requiere atención inmediata
    MEDIA,   // Importante pero no urgente
    BAJA     // Informativo o programable
}
```

### Uso
- **Notificaciones:** Alertas críticas (ALTA), recordatorios (MEDIA), informativas (BAJA)
- **Seguimiento:** Cobros vencidos (ALTA), recalls (MEDIA), mensajes (BAJA)
- **Taller:** Reparaciones urgentes (ALTA), ajustes normales (MEDIA)

---

## AuditStamp

### Qué es
Record que encapsula metadatos de auditoría para cualquier entidad.

```java
public record AuditStamp(
    String creadoPor,
    String fechaCreacion,
    String modificadoPor,
    String fechaModificacion
) {}
```

### Para qué sirve
- Trazabilidad: quién creó y modificó cada registro
- Auditoría: cuándo ocurrieron los cambios
- En la demo: se usa mínimamente; en producción sería más robusto

---

## Cliente

### Qué es
Representa un paciente/cliente de la óptica.

### Atributos

| Campo | Tipo | Descripción | Ejemplo |
|-------|------|-------------|---------|
| **id** | String | Identificador único (heredado) | `CLI-00124` |
| **nombreCompleto** | String | Nombre y apellido | `Sofia Ramirez` |
| **documento** | String | Cédula o pasaporte | `1712345678` |
| **telefono** | String | Teléfono principal | `0991234567` |
| **telefonoAlterno** | String | Segundo teléfono | `0999991234` |
| **email** | String | Correo electrónico | `sofia.ramirez@email.com` |
| **direccion** | String | Dirección de domicilio | `Calle 1 N41-11` |
| **ciudad** | String | Ciudad de residencia | `Quito`, `Cumbaya` |
| **codigoInterno** | String | Código alternativo | `CL-00124` |
| **sucursalHabitual** | String | Sucursal más frecuente | `Matriz Centro` |
| **estado** | EstadoGeneral | ACTIVO / INACTIVO | `ACTIVO` |
| **ultimaVisita** | String (dd/MM/yyyy) | Fecha última visita | `08/04/2026` |
| **ultimaReceta** | String (dd/MM/yyyy) | Fecha última receta | `15/10/2025` |
| **estadoReceta** | String | VIGENTE / POR_VENCER / VENCIDA / SIN_RECETA | `VIGENTE` |

### Relaciones
- **1:N** con Ventas (un cliente tiene múltiples ventas)
- **1:N** con Recetas (un cliente tiene múltiples exámenes)
- **1:N** con Seguimientos (múltiples acciones de seguimiento)
- **1:N** con Notificaciones (múltiples mensajes)

---

## Producto

### Qué es
Representa un artículo del inventario: montura, lente, accesorio o solución.

### Atributos

| Campo | Tipo | Descripción | Ejemplo |
|-------|------|-------------|---------|
| **id** | String | Identificador único | `MON-00045` |
| **referencia** | String | Código de referencia | `MON-RB-00045` |
| **nombre** | String | Nombre descriptivo | `Ray-Ban Aviator Classic` |
| **categoria** | String | Montura, Lente, Accesorio, Solucion | `Montura` |
| **marca** | String | Fabricante | `Ray-Ban`, `Oakley` |
| **sucursal** | String | Sucursal donde está el stock | `Matriz Centro` |
| **stock** | int | Unidades disponibles | `15` |
| **stockMinimo** | int | Nivel mínimo antes de alerta | `5` |
| **precioVenta** | double | Precio al público | `120.00` |
| **estado** | EstadoGeneral | ACTIVO / BAJO_STOCK / AGOTADO | `ACTIVO` |
| **proveedorPrincipal** | String | Proveedor habitual | `Distribuidora Optica Nacional` |

### Relaciones
- **N:1** con Proveedor (un producto tiene un proveedor principal)
- **N:1** con Sucursal (un producto pertenece a una sucursal)
- **1:N** con Movimientos de inventario

---

## VentaOptica

### Qué es
Representa una venta de lentes recetados al paciente.

### Atributos

| Campo | Tipo | Descripción | Ejemplo |
|-------|------|-------------|---------|
| **id** | String | Identificador único | `OV-00001` |
| **referencia** | String | Número de orden | `OV-00045` |
| **clienteId** | String | ID del cliente | `CLI-00124` |
| **clienteNombre** | String | Nombre del paciente | `Sofia Ramirez` |
| **recetaId** | String | ID de receta aplicada | `RC-00003` |
| **monturaRef** | String | Referencia de la montura | `MON-RB-00045` |
| **lenteTipo** | String | Tipo de lente | `Progresivo, High-Index 1.67` |
| **sucursal** | String | Sucursal de la venta | `Matriz Centro` |
| **responsable** | String | Asesor que atendió | `Carlos Zambrano` |
| **precioMontura** | double | Precio de la montura | `80.00` |
| **precioLente** | double | Precio de los lentes | `150.00` |
| **descuento** | double | Descuento aplicado | `20.00` |
| **abono** | double | Monto ya pagado | `100.00` |
| **saldo** | double | Saldo pendiente | `110.00` |
| **estado** | EstadoGeneral | EN_PROCESO / POR_COBRAR / PAGADO / ENTREGADO | `EN_PROCESO` |
| **fechaOrden** | String (dd/MM/yyyy) | Cuándo se registró | `13/04/2026` |
| **fechaPromesa** | String (dd/MM/yyyy) | Entrega estimada | `20/04/2026` |
| **laboratorio** | String | Laboratorio asignado | `Interno` o nombre de externo |

### Relaciones
- **N:1** con Cliente
- **N:1** con Sucursal
- **N:1** con Usuario (responsable)
- **1:N** con Cobro (una venta puede tener múltiples pagos)
- **1:1** con Orden de Laboratorio (generada automáticamente)

---

## Sucursal

### Qué es
Representa una sede física de la óptica.

### Atributos

| Campo | Tipo | Descripción | Ejemplo |
|-------|------|-------------|---------|
| **id** | String | Identificador único | `SUC-001` |
| **nombre** | String | Nombre de la sucursal | `Matriz Centro` |
| **direccion** | String | Dirección física completa | `Av. 5 de Junio N34-120, Quito` |
| **telefono** | String | Teléfono de contacto | `02-245-8901` |
| **correo** | String | Email de la sucursal | `matrizcentro@optica.ec` |
| **responsable** | String | Persona a cargo | `Dr. Andres Villavicencio` |
| **horarioOperativo** | String | Horario de atención | `Lun-Sab 09:00-19:00` |
| **estado** | EstadoGeneral | ACTIVO / OBSERVADO | `ACTIVO` |
| **cajaHabilitada** | boolean | Procesa cobros | `true` |
| **inventarioPropio** | boolean | Maneja stock independiente | `true` |
| **entregaHabilitada** | boolean | Entrega trabajos al paciente | `true` |
| **agendaHabilitada** | boolean | Programa citas | `true` |

---

## Usuario

### Qué es
Representa un usuario del sistema con rol y sucursal asignada.

### Atributos

| Campo | Tipo | Descripción | Ejemplo |
|-------|------|-------------|---------|
| **id** | String | Identificador único | `USR-001` |
| **email** | String | Correo (usuario de login) | `admin@optica.ec` |
| **nombreCompleto** | String | Nombre real | `Marcos Moreira` |
| **rol** | RolSistema | Perfil de permisos | `ADMINISTRADOR_GENERAL` |
| **sucursal** | String | Sede asignada | `Matriz Centro` |
| **estado** | EstadoGeneral | ACTIVO / INACTIVO / BLOQUEADO | `ACTIVO` |
| **ultimoAcceso** | String (dd/MM/yyyy) | Última vez que entró | `13/04/2026` |

---

## RolSistema

### Qué es
Enum con los perfiles de acceso predefinidos del sistema.

| Rol | Descripción | Módulos principales |
|-----|-------------|---------------------|
| **ADMINISTRADOR_GENERAL** | Acceso total a todo | Todos los módulos |
| **ADMINISTRADOR_DE_SEDE** | Gestión de su sucursal | Todos menos Configuración global |
| **RECEPCION** | Atención al paciente | Agenda, Clientes, Entregas, Seguimiento |
| **ASESOR_DE_VENTAS** | Ventas y consultas | Clientes, Venta Óptica, Inventario |
| **TECNICO_OPTICO** | Laboratorio y taller | Órdenes Lab, Inventario, Taller |
| **CAJA** | Procesamiento de cobros | Caja, Venta Óptica (consulta) |
| **SUPERVISOR** | Monitoreo general | Todos en consulta + Reportes |

---

## Proveedor

### Qué es
Representa una empresa proveedora de productos o servicios.

### Atributos clave

| Campo | Tipo | Ejemplo |
|-------|------|---------|
| **id** | String | `PROV-001` |
| **nombre** | String | `Distribuidora Optica Nacional S.A.` |
| **codigoInterno** | String | `DON-001` |
| **tipo** | String | `DISTRIBUIDOR`, `FABRICANTE`, `IMPORTADOR` |
| **categoria** | String | `MONTURAS_Y_LENTES`, `LENTES_Y_LABORATORIO`, `ACCESORIOS` |
| **contacto** | String | `Ing. Fernando Paredes` |
| **telefono** | String | `02-289-4567` |
| **correo** | String | `ventas@don-optica.ec` |
| **ciudad** | String | `Quito`, `Guayaquil` |
| **sucursalesQueAbastece** | String | `Matriz Centro, Norte Mall` |
| **diasEntregaPromedio** | int | `5` |
| **estado** | EstadoGeneral | `ACTIVO` |

---

## Cobro

### Qué es
Representa un pago registrado contra una venta.

### Atributos clave

| Campo | Tipo | Ejemplo |
|-------|------|---------|
| **id** | String | `COB-00001` |
| **ventaId** | String | `OV-00045` |
| **monto** | double | `100.00` |
| **formaPago** | String | `Efectivo`, `Tarjeta de crédito` |
| **fecha** | String (dd/MM/yyyy) | `13/04/2026` |
| **usuario** | String | Quien procesó el cobro |
| **sucursal** | String | Donde se cobró |

---

## Notificacion

### Qué es
Representa un mensaje o alerta del sistema.

### Atributos clave

| Campo | Tipo | Ejemplo |
|-------|------|---------|
| **id** | String | `NOT-00001` |
| **tipo** | String | `Recordatorio`, `Entrega`, `Alerta` |
| **destinatario** | String | A quién va dirigida |
| **asunto** | String | Resumen del mensaje |
| **canal** | String | `SMS`, `Email`, `Llamada` |
| **estado** | EstadoGeneral | `PENDIENTE`, `ENVIADA`, `LEIDA` |
| **prioridad** | Prioridad | `ALTA`, `MEDIA`, `BAJA` |
| **fechaProgramada** | String | Cuándo debe enviarse |
| **sucursal** | String | Sede origen |

---

## CasoCobertura

### Qué es
Representa un caso de seguro o convenio aplicado a un paciente.

### Atributos clave

| Campo | Tipo | Ejemplo |
|-------|------|---------|
| **id** | String | `COB-00001` |
| **clienteId** | String | `CLI-00124` |
| **aseguradora** | String | `SegurosVisual Corp.` |
| **plan** | String | `Visual Plus` |
| **tipoCobertura** | String | `Seguro visual`, `Convenio empresarial` |
| **vigencia** | String | `31/12/2026` |
| **estado** | EstadoGeneral | `ACTIVO`, `VENCIDO` |
| **sucursal** | String | `Matriz Centro` |

---

## TrabajoTecnico

### Qué es
Representa un trabajo de reparación o ajuste en el taller.

### Atributos clave

| Campo | Tipo | Ejemplo |
|-------|------|---------|
| **id** | String | `TR-00001` |
| **clienteId** | String | `CLI-00124` |
| **descripcion** | String | Problema referido |
| **tipoTrabajo** | String | `Ajuste`, `Soldadura`, `Cambio de plaquetas` |
| **estado** | EstadoGeneral | `PENDIENTE`, `EN_PROCESO`, `LISTO`, `ENTREGADO` |
| **prioridad** | Prioridad | `ALTA`, `MEDIA`, `BAJA` |
| **fechaIngreso** | String | Cuándo entró al taller |
| **sucursal** | String | Sede del taller |

---

## Diagrama de Herencia

```
BaseEntity (abstract)
    ├── id: String
    │
    ├── Cliente
    ├── Sucursal
    ├── Usuario
    ├── Producto
    ├── Proveedor
    ├── VentaOptica
    ├── Cobro
    ├── Notificacion
    ├── CasoCobertura
    └── TrabajoTecnico

Enums independientes:
    ├── EstadoGeneral (25 valores polimórficos)
    ├── Prioridad (ALTA, MEDIA, BAJA)
    └── RolSistema (7 roles)
```

---

## Decisiones de Diseño

### Strings en vez de LocalDate para fechas
**Razón:** Las fechas se almacenan formateadas (`dd/MM/yyyy`) para presentación directa en la UI sin necesidad de convertidores en cada celda de tabla.

**Trade-off:** Se pierde la capacidad de operaciones de fecha a nivel de dominio. En producción se usaría `LocalDate`.

### Estado polimórfico compartido
**Razón:** Un solo enum `EstadoGeneral` simplifica el UI (un ComboBox sirve para todo).

**Trade-off:** No hay validación a nivel de tipo de que un estado aplique a una entidad específica (ej: nada impide asignar `BAJO_STOCK` a un Cliente).

### Sin validación de integridad referencial
**Razón:** Al ser in-memory sin BD, las referencias entre entidades son strings (IDs). No hay foreign keys.

**Riesgo:** Pueden existir referencias huérfanas (ej: una venta con `clienteId` que no existe).

---

## Créditos

- **Autor:** Marcos Moreira
- **Versión:** 1.0.0
