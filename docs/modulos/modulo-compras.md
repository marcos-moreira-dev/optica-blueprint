# Compras - Documentación Técnica

## Resumen
Módulo de gestión de compras y abastecimiento del sistema óptica. Administra solicitudes de compra, órdenes de compra, seguimiento de proveedores, recepciones, back-orders y el histórico de compras. Implementa la arquitectura de tres paneles con filtros superiores, sub-vistas intercambiables y panel de resumen lateral.

## Estructura de Clases
| Clase | Tipo | Responsabilidad |
|-------|------|-----------------|
| `ComprasController` | Controller | Configuración JavaFX, toggle de sub-vistas, binding de tablas, acciones de compra |
| `ComprasFacade` | Facade | Lógica de negocio, seed data de compras, órdenes, recepciones y métricas |
| `ComprasFilters` | Model | Criterios de filtrado del módulo de compras |
| `ComprasRowModel` | Model | Registros inmutables de filas para cada sub-vista |
| `ComprasSummaryModel` | Model | Resumen de la compra seleccionada para el panel derecho |

### Sub-clases (inner records en ComprasRowModel)
| Record | Columnas |
|--------|----------|
| `SolicitudRow` | datos de solicitudes de compra internas |
| `OrdenCompraRow` | datos de órdenes de compra emitidas |
| `ProveedorCompraRow` | datos del proveedor asociado a la compra |
| `RecepcionCompraRow` | datos de recepciones de mercadería comprada |
| `BackOrderRow` | datos de back-orders (pedidos pendientes de entrega parcial) |
| `SucursalCompraRow` | datos de sucursal destino de la compra |
| `HistoricoCompraRow` | datos del histórico de compras completadas |

## Sub-vistas
| # | Sub-vista | Toggle Button | Tabla Principal | Descripción |
|---|-----------|--------------|-----------------|-------------|
| 1 | Solicitudes de compra | Toggle correspondiente | Tabla de solicitudes | Solicitudes internas generadas por el sistema o usuario |
| 2 | Órdenes de compra | Toggle correspondiente | Tabla de órdenes | Órdenes de compra emitidas a proveedores |
| 3 | Proveedor | Toggle correspondiente | Tabla de proveedores | Compras agrupadas por proveedor |
| 4 | Recepciones | Toggle correspondiente | Tabla de recepciones | Mercadería recibida de compras |
| 5 | Back-orders | Toggle correspondiente | Tabla de back-orders | Productos pendientes de entrega parcial |
| 6 | Sucursal | Toggle correspondiente | Tabla de sucursales | Compras agrupadas por sucursal destino |
| 7 | Histórico | Toggle correspondiente | Tabla histórico | Archivo de compras completadas |

## Flujo de Datos
```
[Usuario selecciona filtros] → applyFilters()
       ↓
ComprasFilters actualizado
       ↓
ComprasFacade.getSolicitudes() / getOrdenes() / getRecepciones() / etc.
       ↓
Stream.filter(matchesFilters) → lista filtrada
       ↓
ObservableList → tableView.setItems()
       ↓
[Usuario selecciona fila] → onRowSelected(row)
       ↓
facade.buildSummary(row) → ComprasSummaryModel
       ↓
updateSummaryPanel() → Labels del panel derecho actualizados
```

## Filtros Disponibles
| Filtro | Control | Descripción |
|--------|---------|-------------|
| Búsqueda libre | `TextField` | Búsqueda por número de orden, proveedor o referencia |
| Estado | `ComboBox<String>` | Estado de la compra (pendiente, enviada, recibida, cancelada) |
| Proveedor | `ComboBox<String>` | Proveedor de la compra |
| Sucursal | `ComboBox<String>` | Sucursal destino de la mercadería |
| Prioridad | `ComboBox<String>` | Prioridad de la compra (alta, media, baja, urgente) |
| Desde | `DatePicker` | Fecha inicio del rango |
| Hasta | `DatePicker` | Fecha fin del rango |

## Panel Resumen
El `ComprasSummaryModel` muestra los campos relevantes de la compra seleccionada. El panel derecho persistente presenta información detallada de la orden de compra, incluyendo proveedor, productos solicitados, estado de entrega, fechas estimadas y acciones contextuales para gestionar el proceso de compra.

## Patrones de Diseño
- **Facade Pattern**: `ComprasFacade` centraliza la lógica de negocio del módulo
- **MVC (Model-View-Controller)**: separación entre FXML, Controller y modelos de datos
- **Record Pattern**: modelos de fila como records inmutables con JavaFX properties
- **Toggle Group Pattern**: sub-vistas mutuamente exclusivas mediante `ToggleGroup`
- **Pagination Pattern**: paginación controlada por `PaginationBarController`
- **Filter Chain Pattern**: filtros encadenados con `FilterSupport`
- **Multi-View Pattern**: 7 sub-vistas especializadas con toggle buttons

## Integración con Otros Módulos
| Módulo | Punto de integración | Descripción |
|--------|---------------------|-------------|
| Proveedores | Proveedor de compra | Las órdenes de compra referencian proveedores del catálogo |
| Inventario | Recepciones | Mercadería recibida ingresa automáticamente al inventario |
| Configuración | Inventario y abastecimiento | Reglas de stock mínimo generan solicitudes de compra |
| Sucursales | Sucursal destino | Cada compra se asigna a una sucursal específica |
| Ventas | Disponibilidad | Productos comprados disponibles para venta tras recepción |
| Reportes | Rotación | Datos de compras en análisis de abastecimiento |
