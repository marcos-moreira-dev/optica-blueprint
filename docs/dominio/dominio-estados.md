# Estados del Sistema - Guía Completa

## EstadoGeneral: Enum Polimórfico

El sistema utiliza un único enum `EstadoGeneral` con **25 valores** que se aplica a todas las entidades. Este documento explica qué significa cada estado en el contexto de cada entidad.

---

## Mapa de Estados por Entidad

### Clientes

| Estado | Cuándo aplica | Color UI | Acción posible |
|--------|--------------|----------|----------------|
| **ACTIVO** | El paciente está registrado y puede operar | 🟢 Verde | Agendar citas, hacer compras |
| **INACTIVO** | El paciente fue desactivado (no eliminado) | ⚫ Gris | No se le pueden hacer operaciones nuevas |

### Recetas (estadoReceta como String)

| Estado | Cuándo aplica | Color UI | Acción posible |
|--------|--------------|----------|----------------|
| **VIGENTE** | La receta tiene menos de 6 meses | 🟢 Verde | Usar para ventas de lentes |
| **POR_VENCER** | La receta tiene entre 6 y 12 meses | 🟡 Ámbar | Ofrecer nuevo examen visual |
| **VENCIDA** | La receta tiene más de 12 meses | 🔴 Rojo | No usar para fabricar lentes nuevos |
| **SIN_RECETA** | El paciente no tiene examen registrado | ⚫ Gris | Ofrecer examen visual |

### Citas (Agenda)

| Estado | Cuándo aplica | Color UI |
|--------|--------------|----------|
| **PENDIENTE** | Cita agendada pero no confirmada por el paciente | 🟡 Ámbar |
| **CONFIRMADO** | El paciente confirmó que asistirá | 🟢 Verde |
| **EN_PROCESO** | El paciente está siendo atendido ahora | 🔵 Azul |
| **ATENDIDO** | La cita ya fue completada | ⚫ Gris |
| **CANCELADO** | La cita fue anulada | 🔴 Rojo |
| **REPROGRAMADO** | La cita cambió de fecha/hora | 🟣 Morado |

### Ventas Ópticas

| Estado | Cuándo aplica | Color UI | Flujo siguiente |
|--------|--------------|----------|-----------------|
| **EN_PROCESO** | La orden fue creada pero aún no se cobra | 🔵 Azul | Ir a Caja para cobrar |
| **POR_COBRAR** | La venta está pendiente de pago | 🟡 Ámbar | Procesar cobro |
| **PAGADO** | El paciente pagó el total | 🟢 Verde | Enviar a laboratorio |
| **ENTREGADO** | Los lentes fueron entregados al paciente | ⚫ Gris | Cerrar ciclo |
| **CANCELADO** | La venta fue anulada | 🔴 Rojo | Revertir inventario |

### Órdenes de Laboratorio

| Estado | Cuándo aplica | Color UI |
|--------|--------------|----------|
| **PENDIENTE** | La orden ingresó pero no inicia fabricación | 🟡 Ámbar |
| **EN_PROCESO** | Se está fabricando activamente | 🔵 Azul |
| **EN_ESPERA** | Detenida por falta de insumo o incidencia | 🟡 Ámbar |
| **ENVIADO** | Enviada a laboratorio externo | 🟣 Morado |
| **RECIBIDO** | Vuelta del laboratorio externo, en verificación | 🟢 Verde |
| **LISTO** | Terminada, esperando al paciente | 🟢 Verde |
| **ENTREGADO** | Entregada al paciente | ⚫ Gris |
| **RECHAZADO** | No se puede fabricar (error de receta) | 🔴 Rojo |

### Productos (Inventario)

| Estado | Cuándo aplica | Color UI | Acción automática |
|--------|--------------|----------|-------------------|
| **ACTIVO** | Stock por encima del mínimo | 🟢 Verde | Ninguna |
| **BAJO_STOCK** | Stock por debajo del mínimo | 🟡 Ámbar | Alertar reposición |
| **AGOTADO** | No quedan unidades | 🔴 Rojo | Generar orden de compra urgente |

### Cobros

| Estado | Cuándo aplica | Color UI |
|--------|--------------|----------|
| **PAGADO** | El cobro fue procesado exitosamente | 🟢 Verde |
| **PARCIAL** | Se registró un abono parcial | 🟡 Ámbar |
| **CANCELADO** | El cobro fue anulado | 🔴 Rojo |

### Usuarios

| Estado | Cuándo aplica | Color UI |
|--------|--------------|----------|
| **ACTIVO** | El usuario puede acceder al sistema | 🟢 Verde |
| **INACTIVO** | El usuario fue desactivado | ⚫ Gris |
| **BLOQUEADO** | Acceso denegado por seguridad | 🔴 Rojo |

### Sucursales

| Estado | Cuándo aplica | Color UI |
|--------|--------------|----------|
| **ACTIVO** | La sucursal opera normalmente | 🟢 Verde |
| **OBSERVADO** | Tiene observaciones pendientes | 🟡 Ámbar |
| **CERRADO** | La sucursal no opera temporalmente | 🔴 Rojo |

### Proveedores

| Estado | Cuándo aplica | Color UI |
|--------|--------------|----------|
| **ACTIVO** | Se le pueden hacer pedidos | 🟢 Verde |
| **OBSERVADO** | Tiene incidencias pendientes | 🟡 Ámbar |
| **INACTIVO** | No se le hacen pedidos | ⚫ Gris |

### Notificaciones

| Estado | Cuándo aplica | Color UI |
|--------|--------------|----------|
| **PENDIENTE** | Programada pero no enviada | 🟡 Ámbar |
| **ENVIADO** | Enviada al destinatario | 🔵 Azul |
| **RECIBIDO** | El destinatario la recibió | 🟢 Verde |
| **RECHAZADO** | No se pudo enviar (email inválido, etc.) | 🔴 Rojo |

### Seguros / Coberturas

| Estado | Cuándo aplica | Color UI |
|--------|--------------|----------|
| **ACTIVO** | La cobertura es vigente | 🟢 Verde |
| **PENDIENTE** | Esperando aprobación de la aseguradora | 🟡 Ámbar |
| **VENCIDO** | La cobertura expiró | 🔴 Rojo |
| **RECHAZADO** | La aseguradora rechazó la reclamación | 🔴 Rojo |

### Compras / Órdenes de Compra

| Estado | Cuándo aplica | Color UI |
|--------|--------------|----------|
| **ENVIADO** | El pedido fue enviado al proveedor | 🔵 Azul |
| **EN_PROCESO** | El proveedor está procesando el pedido | 🟡 Ámbar |
| **RECIBIDO** | Todo el pedido llegó correctamente | 🟢 Verde |
| **PARCIAL** | Llegó parte del pedido | 🟡 Ámbar |

### Taller / Reparaciones

| Estado | Cuándo aplica | Color UI |
|--------|--------------|----------|
| **PENDIENTE** | Ingresado pero aún no diagnosticado | 🟡 Ámbar |
| **EN_PROCESO** | El técnico está trabajando | 🔵 Azul |
| **LISTO** | Reparación completada | 🟢 Verde |
| **ENTREGADO** | Devuelto al paciente | ⚫ Gris |
| **ENVIADO** | Enviado a laboratorio externo | 🟣 Morado |

---

## Transiciones de Estado

### Flujo de Estado de una Venta

```
EN_PROCESO ──(cobro)──► POR_COBRAR ──(pago completo)──► PAGADO
     │                                                         │
     │                                                         ▼
     │                                                  (fabricación)
     │                                                         │
     ▼                                                         ▼
 CANCELADO                                              ENTREGADO
```

### Flujo de Estado de una Cita

```
PENDIENTE ──(confirmación)──► CONFIRMADO ──(atención)──► ATENDIDO
    │                               │
    │                               └──(no asistió)──► CANCELADO
    │
    └──(reprogramación)──► REPROGRAMADO ──(nueva fecha)──► PENDIENTE
```

### Flujo de Estado de una Orden de Laboratorio

```
PENDIENTE ──(inicio fabricación)──► EN_PROCESO ──(corte)──► EN_PROCESO
     │                                    │
     │                                    ▼
     │                            (montaje → QC) ──► LISTO
     │                                    │
     │                                    ├──(incidencia)──► EN_ESPERA
     │                                    │                       │
     │                                    │                       ▼
     │                                    │                 EN_PROCESO (reelaboración)
     │                                    │
     │                                    ▼
     │                               ENVIADO ──(lab externo)──► RECIBIDO
     │                                                              │
     ▼                                                              ▼
 RECHAZADO                                                   ENTREGADO
```

### Flujo de Estado de un Producto

```
ACTIVO ──(venta reduce stock)──► ACTIVO (stock >= mínimo)
              │
              ▼
         BAJO_STOCK ──(reposición)──► ACTIVO
              │
              ▼
          AGOTADO ──(recepción de compra)──► ACTIVO
```

---

## Colores UI Asociados

### Mapeo de Estado a Color

| Color | Hex | Estados asociados | Significado |
|-------|-----|-------------------|-------------|
| 🟢 Verde | `#4A8C5C` | ACTIVO, PAGADO, COMPLETADO, LISTO, ENTREGADO, RECIBIDO, ATENDIDO, CERRADO, CONFIRMADO, VIGENTE | Favorable / Exitoso |
| 🟡 Ámbar | `#C4882B` | PENDIENTE, EN_PROCESO, POR_COBRAR, BAJO_STOCK, EN_ESPERA, PARCIAL, OBSERVADO, REPROGRAMADO, POR_VENCER | Atención requerida |
| 🔴 Rojo | `#A64B4B` | CANCELADO, RECHAZADO, AGOTADO, VENCIDO, BLOQUEADO, VENCIDA, SIN_RECETA | Crítico / Error |
| 🔵 Azul | `#2B5B6E` | ENVIADO, EN_PROCESO (en algunos contextos) | En curso |
| 🟣 Morado | `#7B5EA7` | REPROGRAMADO, ENVIADO (a externo) | Condicional |
| ⚫ Gris | `#5A6474` | INACTIVO, NEUTRO | Inactivo / Históico |

---

## Validación de Estados

### Estados válidos por entidad

| Entidad | Estados válidos |
|---------|----------------|
| Cliente | ACTIVO, INACTIVO |
| Receta | VIGENTE, POR_VENCER, VENCIDA, SIN_RECETA |
| Cita | PENDIENTE, CONFIRMADO, EN_PROCESO, ATENDIDO, CANCELADO, REPROGRAMADO |
| Venta | EN_PROCESO, POR_COBRAR, PAGADO, ENTREGADO, CANCELADO |
| Orden Lab | PENDIENTE, EN_PROCESO, EN_ESPERA, ENVIADO, RECIBIDO, LISTO, ENTREGADO, RECHAZADO |
| Producto | ACTIVO, BAJO_STOCK, AGOTADO |
| Usuario | ACTIVO, INACTIVO, BLOQUEADO |
| Sucursal | ACTIVO, OBSERVADO, CERRADO |
| Proveedor | ACTIVO, OBSERVADO, INACTIVO |
| Notificación | PENDIENTE, ENVIADO, RECIBIDO, RECHAZADO |
| Cobertura | ACTIVO, PENDIENTE, VENCIDO, RECHAZADO |
| Compra | ENVIADO, EN_PROCESO, RECIBIDO, PARCIAL |
| Taller | PENDIENTE, EN_PROCESO, LISTO, ENTREGADO, ENVIADO |
| Cobro | PAGADO, PARCIAL, CANCELADO |

### Estados inválidos (ejemplos)
| Entidad | Estado inválido | Por qué no aplica |
|---------|----------------|-------------------|
| Cliente | BAJO_STOCK | Un cliente no tiene stock |
| Producto | CONFIRMADO | Un producto no se confirma |
| Cita | PAGADO | Una cita no se paga directamente |
| Usuario | EN_PROCESO | Un usuario no está "en proceso" |

---

## Implementación en la UI

### StatusBadge CellFactory

Las columnas de estado en las `TableView` usan una `CellFactory` personalizada:

```java
// Pseudocode del patrón usado en los Controllers
colEstado.setCellFactory(tc -> new StatusBadgeCell(
    Map.of(
        "ACTIVO", "success",       // → clase CSS verde
        "PENDIENTE", "warning",    // → clase CSS ámbar
        "CANCELADO", "danger",     // → clase CSS roja
        "EN_PROCESO", "info"       // → clase CSS azul
    )
));
```

### CSS Classes por estado

```css
.status-badge.success {
    -fx-background-color: #4A8C5C;
    -fx-text-fill: white;
}

.status-badge.warning {
    -fx-background-color: #C4882B;
    -fx-text-fill: white;
}

.status-badge.danger {
    -fx-background-color: #A64B4B;
    -fx-text-fill: white;
}

.status-badge.info {
    -fx-background-color: #2B5B6E;
    -fx-text-fill: white;
}

.status-badge.neutral {
    -fx-background-color: #5A6474;
    -fx-text-fill: white;
}
```

---

## Estados vs Prioridad

### Diferencia conceptual

| Concepto | Qué mide | Dónde se usa |
|----------|----------|--------------|
| **Estado** | En qué situación está la entidad | Todas las entidades |
| **Prioridad** | Qué urgencia tiene la acción | Seguimiento, Notificaciones, Taller |

### Ejemplo combinado
| Entidad | Estado | Prioridad | Interpretación |
|---------|--------|-----------|----------------|
| Notificación | PENDIENTE | ALTA | Debe enviarse urgentemente |
| Notificación | PENDIENTE | BAJA | Puede esperar |
| Seguimiento | PENDIENTE | ALTA | Cobro vencido hace 30 días |
| Seguimiento | PENDIENTE | MEDIA | Recall programado para dentro de 2 semanas |

---

## Créditos

- **Autor:** Marcos Moreira
- **Versión:** 1.0.0
