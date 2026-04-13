# Relaciones entre Entidades de Dominio

## Diagrama de Relaciones

Este documento describe cómo se relacionan todas las entidades del dominio de OpticaDemo.

---

## Relaciones Principales

### 1. Cliente como Centro

```
                    ┌──────────────────┐
                    │     CLIENTE      │
                    │  (paciente)      │
                    └────────┬─────────┘
                             │
          ┌──────────────────┼──────────────────┐
          │                  │                  │
          ▼                  ▼                  ▼
   ┌──────────────┐  ┌──────────────┐  ┌──────────────┐
   │   VENTA      │  │   RECETA     │  │  SEGUIMIENTO │
   │   OPTICA     │  │ (examen)     │  │  (recall)    │
   └──────┬───────┘  └──────────────┘  └──────────────┘
          │
          ├──► COBRO (1:N pagos)
          │
          ├──► ORDEN LAB (1:1 fabricación)
          │       │
          │       └──► ETAPAS (1:N etapas de proceso)
          │       └──► INCIDENCIAS (1:N problemas)
          │
          └──► ENTREGA (1:1 entrega al paciente)
```

### 2. Sucursal como Contenedor

```
                    ┌──────────────────┐
                    │    SUCURSAL      │
                    │    (sede)        │
                    └────────┬─────────┘
                             │
          ┌──────────────────┼──────────────────┐
          │                  │                  │
          ▼                  ▼                  ▼
   ┌──────────────┐  ┌──────────────┐  ┌──────────────┐
   │   USUARIO    │  │  PRODUCTO    │  │     CITA     │
   │  (empleado)  │  │ (inventario) │  │   (agenda)   │
   └──────────────┘  └──────┬───────┘  └──────────────┘
                            │
                            ├──► MOVIMIENTO (1:N entradas/salidas)
                            │
                            └──► PROVEEDOR (N:1 proveedor principal)
```

### 3. Flujo de Venta Completo

```
CLIENTE ──tiene──► RECETA ──se aplica a──► VENTA OPTICA
                                               │
                    ┌──────────────────────────┼──────────────────────────┐
                    │                          │                          │
                    ▼                          ▼                          ▼
             ┌──────────────┐          ┌──────────────┐          ┌──────────────┐
             │   COBRO      │          │ ORDEN LAB    │          │  SEGUIMIENTO │
             │  (pago)      │          │ (fabricación)│          │   (recall)   │
             └──────┬───────┘          └──────┬───────┘          └──────────────┘
                    │                         │
                    │                         ├──► ETAPAS (Corte, Montaje, QC)
                    │                         │
                    │                         └──► ENTREGA
                    │
                    └──► COMPROBANTE (recibo)
```

---

## Relaciones Detalladas

### Cliente ↔ VentaOptica
| Tipo | Cardinalidad | Campo | Descripción |
|------|-------------|-------|-------------|
| Referencia | 1:N | `VentaOptica.clienteId` | Un cliente tiene N ventas |
| Denormalización | - | `VentaOptica.clienteNombre` | Se copia el nombre para evitar joins |

### Cliente ↔ Receta
| Tipo | Cardinalidad | Campo | Descripción |
|------|-------------|-------|-------------|
| Histórica | 1:N | `Cliente.ultimaReceta` | Un cliente tiene N recetas a lo largo del tiempo |

### VentaOptica ↔ Cobro
| Tipo | Cardinalidad | Campo | Descripción |
|------|-------------|-------|-------------|
| Pago | 1:N | `Cobro.ventaId` | Una venta puede tener múltiples cobros (abonos parciales) |
| Acumulativo | - | `VentaOptica.abono`, `VentaOptica.saldo` | Se calcula sumando todos los cobros |

### VentaOptica ↔ OrdenLaboratorio
| Tipo | Cardinalidad | Campo | Descripción |
|------|-------------|-------|-------------|
| Generación | 1:1 | Automática | Al confirmar venta, se genera orden de laboratorio |
| Estado vinculado | - | `OrdenLab.estado` refleja el estado de fabricación |

### OrdenLaboratorio ↔ Etapas
| Tipo | Cardinalidad | Campo | Descripción |
|------|-------------|-------|-------------|
| Proceso | 1:N | Lista de etapas | Cada orden pasa por: Recepción → Corte → Montaje → QC → Entrega |

### OrdenLaboratorio ↔ Incidencia
| Tipo | Cardinalidad | Campo | Descripción |
|------|-------------|-------|-------------|
| Problema | 1:N | Lista de incidencias | Una orden puede tener múltiples incidencias (reelaboraciones) |

### Producto ↔ Proveedor
| Tipo | Cardinalidad | Campo | Descripción |
|------|-------------|-------|-------------|
| Suministro | N:1 | `Producto.proveedorPrincipal` | Un producto tiene un proveedor principal |
| Catálogo | N:M | Implícito | Un proveedor suministra múltiples productos |

### Sucursal ↔ Usuario
| Tipo | Cardinalidad | Campo | Descripción |
|------|-------------|-------|-------------|
| Asignación | 1:N | `Usuario.sucursal` | Una sucursal tiene N usuarios asignados |

### Sucursal ↔ Producto
| Tipo | Cardinalidad | Campo | Descripción |
|------|-------------|-------|-------------|
| Inventario | 1:N | `Producto.sucursal` | Cada sucursal maneja su propio stock |

### Sucursal ↔ VentaOptica
| Tipo | Cardinalidad | Campo | Descripción |
|------|-------------|-------|-------------|
| Transacción | 1:N | `VentaOptica.sucursal` | Una sucursal realiza N ventas |

### Notificación ↔ Cliente
| Tipo | Cardinalidad | Campo | Descripción |
|------|-------------|-------|-------------|
| Comunicación | 1:N | `Notificacion.destinatario` | Un cliente recibe N notificaciones |

### Seguimiento ↔ Cliente
| Tipo | Cardinalidad | Campo | Descripción |
|------|-------------|-------|-------------|
| Acción | 1:N | Implícito | Un cliente tiene N acciones de seguimiento |

### CasoCobertura ↔ Cliente
| Tipo | Cardinalidad | Campo | Descripción |
|------|-------------|-------|-------------|
| Afiliación | 1:N | `CasoCobertura.clienteId` | Un cliente puede tener coberturas activas e históricas |

### CasoCobertura ↔ VentaOptica
| Tipo | Cardinalidad | Campo | Descripción |
|------|-------------|-------|-------------|
| Aplicación | 1:N | Implícito | Una cobertura puede aplicarse a múltiples ventas |

### TrabajoTecnico ↔ Cliente
| Tipo | Cardinalidad | Campo | Descripción |
|------|-------------|-------|-------------|
| Servicio | 1:N | `TrabajoTecnico.clienteId` | Un cliente trae N trabajos de reparación |

---

## Cardinalidades del Sistema

### Relaciones 1:N (Una entidad tiene muchas dependientes)

| Entidad "Uno" | Entidad "Muchos" | Ejemplo |
|---------------|------------------|---------|
| Cliente | VentaOptica | Sofia Ramirez ha hecho 3 compras |
| Cliente | Receta | Sofia tiene 5 recetas históricas |
| Cliente | Notificación | Sofia recibió 12 mensajes |
| Cliente | Seguimiento | Sofia tiene 3 recalls programados |
| Sucursal | Usuario | Matriz Centro tiene 8 empleados |
| Sucursal | Producto | Matriz Centro tiene 150 productos |
| Sucursal | VentaOptica | Matriz Centro hizo 45 ventas este mes |
| VentaOptica | Cobro | OV-00045 tiene 2 pagos (abono + saldo) |
| OrdenLab | Etapa | LAB-00023 tiene 5 etapas registradas |
| Proveedor | Producto | Dist. Óptica Nacional suministra 30 productos |

### Relaciones N:M (Muchos a muchos, implícitas)

| Entidad A | Entidad B | Relación | Ejemplo |
|-----------|-----------|----------|---------|
| Proveedor | Sucursal | Un proveedor abastece múltiples sucursales | Dist. Óptica abastece Matriz + Norte Mall |
| Cliente | Sucursal | Un cliente puede visitar múltiples sucursales | Sofia visita Matriz y Norte Mall |

---

## Flujo de Datos entre Módulos

### Cómo los módulos consumen relaciones

| Módulo | Relaciones que usa |
|--------|-------------------|
| **Clientes** | Cliente → Ventas, Cliente → Recetas, Cliente → Seguimiento |
| **Venta Óptica** | Cliente + Receta + Producto(montura) → VentaOptica → Cobro + OrdenLab |
| **Órdenes Lab** | VentaOptica → OrdenLab → Etapas → Incidencias |
| **Caja** | VentaOptica → Cobro |
| **Entregas** | OrdenLab → Entrega → Cliente |
| **Inventario** | Producto ↔ Proveedor, Producto ↔ Sucursal |
| **Seguros** | Cliente → CasoCobertura → VentaOptica |
| **Proveedores** | Proveedor → Productos, Proveedor → ÓrdenesCompra |
| **Compras** | Proveedor → ÓrdenesCompra → Productos → Sucursal |
| **Seguimiento** | Cliente → Acciones (Recall, Cobro, Entrega) |
| **Reportes** | Cruza TODAS las relaciones para análisis |

---

## Denormalización Intencional

### Qué campos están duplicados y por qué

| Campo duplicado | Dónde | Desde dónde | Por qué |
|-----------------|-------|-------------|---------|
| `clienteNombre` | VentaOptica | Cliente.nombreCompleto | Evitar join al mostrar ventas |
| `clienteId` | VentaOptica | Cliente.id | Referencia para buscar recetas |
| `sucursal` | Producto, VentaOptica, Usuario | Sucursal.nombre | Filtrar por sucursal sin join |
| `responsable` | VentaOptica | Usuario.nombreCompleto | Mostrar quién atendió sin buscar usuario |

### Trade-off
**Beneficio:** Consultas más simples y rápidas (sin joins).
**Riesgo:** Si el nombre de un cliente cambia, las ventas históricas mantienen el nombre viejo.

---

## Integridad Referencial (o falta de ella)

### Situación actual
No hay validación de integridad referencial a nivel de código:
- Se puede crear una venta con un `clienteId` que no existe
- Se puede asignar un producto a una sucursal inexistente
- No hay cascading deletes

### En producción (con BD)
Se agregarían:
- **Foreign keys** en la base de datos
- **Validación** en el Service layer antes de persistir
- **Cascading** para eliminar datos dependientes

---

## Créditos

- **Autor:** Marcos Moreira
- **Versión:** 1.0.0
