# Manual de Usuario - Optica Manager

## Sistema Integral de Gestión para Ópticas

Bienvenido al manual de usuario de **Optica Manager**, el sistema de gestión integral para ópticas diseñado para facilitar la operación diaria de su negocio.

---

## ¿Cómo usar este manual?

Este manual está organizado en **20 módulos**, cada uno correspondiente a una sección del sistema. Cada archivo `.md` explica:

- ✅ Para qué sirve el módulo
- ✅ Quiénes lo usan
- ✅ Qué información muestra
- ✅ Cómo realizar las acciones paso a paso
- ✅ Casos de uso comunes del día a día
- ✅ Notas y recomendaciones importantes

### Navegación rápida

| # | Módulo | Descripción | Archivo |
|---|--------|-------------|---------|
| 1 | **Inicio de Sesión** | Acceso seguro al sistema | [01-login.md](01-login.md) |
| 2 | **Panel Principal** | Dashboard con indicadores del día | [02-inicio.md](02-inicio.md) |
| 3 | **Agenda** | Gestión de citas y horarios | [03-agenda.md](03-agenda.md) |
| 4 | **Clientes** | Expediente de pacientes | [04-clientes.md](04-clientes.md) |
| 5 | **Recetas** | Recetas oftalmológicas (OD/OI) | [05-recetas.md](05-recetas.md) |
| 6 | **Venta Óptica** | Wizard de venta de lentes | [06-venta-optica.md](06-venta-optica.md) |
| 7 | **Órdenes de Laboratorio** | Seguimiento de fabricación | [07-ordenes-laboratorio.md](07-ordenes-laboratorio.md) |
| 8 | **Inventario** | Catálogo de productos y stock | [08-inventario.md](08-inventario.md) |
| 9 | **Caja** | Cobros, comprobantes y cierre | [09-caja.md](09-caja.md) |
| 10 | **Entregas** | Entrega de trabajos al paciente | [10-entregas.md](10-entregas.md) |
| 11 | **Seguimiento** | Recalls, recordatorios y cobros | [11-seguimiento.md](11-seguimiento.md) |
| 12 | **Reportes** | Análisis y estadísticas del negocio | [12-reportes.md](12-reportes.md) |
| 13 | **Configuración** | Ajustes del sistema y del negocio | [13-configuracion.md](13-configuracion.md) |
| 14 | **Seguros y Coberturas** | Seguros visuales y convenios | [14-seguros-cobertura.md](14-seguros-cobertura.md) |
| 15 | **Proveedores** | Gestión de proveedores | [15-proveedores.md](15-proveedores.md) |
| 16 | **Compras** | Órdenes de compra y abastecimiento | [16-compras.md](16-compras.md) |
| 17 | **Notificaciones** | Centro de comunicaciones | [17-notificaciones.md](17-notificaciones.md) |
| 18 | **Usuarios y Roles** | Acceso y permisos del sistema | [18-usuarios-roles.md](18-usuarios-roles.md) |
| 19 | **Taller de Reparaciones** | Reparaciones y ajustes técnicos | [19-taller.md](19-taller.md) |
| 20 | **Sucursales** | Gestión de múltiples sedes | [20-sucursales.md](20-sucursales.md) |

---

## Flujo típico de trabajo en la óptica

Para entender cómo se conectan los módulos, aquí un ejemplo del flujo completo:

### Cuando un paciente llega a la óptica

```
1. INICIO DE SESIÓN → El usuario ingresa al sistema
       ↓
2. PANEL PRINCIPAL → Ve un resumen del día
       ↓
3. AGENDA → Verifica si el paciente tiene cita programada
       ↓
4. CLIENTES → Consulta el expediente del paciente
       ↓
5. RECETAS → Revisa la receta oftalmológica vigente
       ↓
6. VENTA ÓPTICA → Procesa la venta de montura y lentes (wizard de 7 pasos)
       ↓
7. CAJA → Cobra la venta al paciente
       ↓
8. ÓRDENES DE LABORATORIO → La orden pasa a fabricación
       ↓
9. ENTREGAS → Cuando los lentes están listos, se entregan al paciente
       ↓
10. SEGUIMIENTO → Se programa un recall para el próximo control
```

### Roles del equipo y los módulos que usan

| Rol | Módulos principales |
|-----|---------------------|
| **Optometrista** | Agenda, Clientes, Recetas, Seguimiento |
| **Asesor de Ventas** | Clientes, Venta Óptica, Inventario (consulta) |
| **Cajero** | Caja, Venta Óptica (consulta) |
| **Recepcionista** | Agenda, Clientes, Entregas, Seguimiento, Notificaciones |
| **Técnico de Laboratorio** | Órdenes de Laboratorio, Inventario, Taller |
| **Administrador** | Todos los módulos |

---

## Convenciones del manual

| Símbolo | Significado |
|---------|-------------|
| ✅ | Acción recomendada o información positiva |
| ⚠️ | Precaución o advertencia |
| 💡 | Consejo o tip útil |
| 🔴 | Situación urgente o crítica |
| 🟡 | Situación importante pero no urgente |
| 🟢 | Situación normal o favorable |

---

## Créditos

- **Autor del sistema:** Marcos Moreira
- **Versión:** 1.0.0
- **Tecnología:** JavaFX 21, Java 17
- **Documento:** Manual de usuario para optometristas y personal de óptica

---

## Soporte

Si tiene alguna duda que no encuentra resuelta en este manual:
1. Consulte la sección de **Configuración** para revisar las opciones del sistema.
2. Revise el **Histórico** de cada módulo para entender cómo se registraron acciones anteriores.
3. Contacte al administrador del sistema.
