# Proveedores - Documentación Técnica

## Resumen
Módulo de gestión integral de proveedores del sistema óptica. Administra el directorio de proveedores, perfiles detallados, catálogos de productos, órdenes de compra, recepciones, desempeño y el histórico de relaciones comerciales. Sigue la arquitectura de tres paneles con barra de filtros, sub-vistas intercambiables y panel de resumen persistente.

## Estructura de Clases
| Clase | Tipo | Responsabilidad |
|-------|------|-----------------|
| `ProveedoresController` | Controller | Configuración JavaFX, toggle de sub-vistas, binding de tablas, acciones de proveedor |
| `ProveedoresFacade` | Facade | Lógica de negocio, seed data de proveedores, órdenes, recepciones y métricas de desempeño |
| `ProveedoresFilters` | Model | Criterios de filtrado (searchText, categoria, estado, sucursal, etc.) |
| `ProveedoresRowModel` | Model | Registros inmutables de filas para cada sub-vista |
| `ProveedoresSummaryModel` | Model | Resumen del proveedor seleccionado para el panel derecho |

### Sub-clases (inner records en ProveedoresRowModel)
| Record | Columnas |
|--------|----------|
| `DirectorioRow` | datos del directorio de proveedores (nombre, contacto, categoría, estado) |
| `PerfilRow` | datos del perfil detallado del proveedor |
| `CatalogoRow` | datos del catálogo de productos del proveedor |
| `OrdenRow` | datos de órdenes de compra al proveedor |
| `RecepcionRow` | datos de recepciones de mercadería |
| `DesempenoRow` | métricas de desempeño del proveedor |
| `HistoricoRow` | datos del histórico de relaciones comerciales |

### Clase de dominio
| Clase | Paquete | Descripción |
|-------|---------|-------------|
| `Proveedor` | `shared.domain.proveedor` | Entidad de dominio que representa un proveedor del sistema |

## Sub-vistas
| # | Sub-vista | Toggle Button | Tabla Principal | Descripción |
|---|-----------|--------------|-----------------|-------------|
| 1 | Directorio de proveedores | Toggle correspondiente | Tabla de directorio | Listado general de proveedores activos |
| 2 | Perfil de proveedor | Toggle correspondiente | Tabla de perfiles | Información detallada de cada proveedor |
| 3 | Catálogos | Toggle correspondiente | Tabla de catálogos | Productos y servicios que ofrece cada proveedor |
| 4 | Órdenes de compra | Toggle correspondiente | Tabla de órdenes | Órdenes emitidas a proveedores |
| 5 | Recepciones | Toggle correspondiente | Tabla de recepciones | Mercadería recibida de proveedores |
| 6 | Desempeño | Toggle correspondiente | Tabla de desempeño | Métricas de cumplimiento de cada proveedor |
| 7 | Histórico | Toggle correspondiente | Tabla histórico | Archivo de relaciones comerciales completadas |

## Flujo de Datos
```
[Usuario selecciona filtros] → applyFilters()
       ↓
ProveedoresFilters actualizado (searchText, categoria, estado, sucursal, etc.)
       ↓
ProveedoresFacade.getDirectorio() / getPerfiles() / getCatalogos() / etc.
       ↓
Stream.filter(matchesFilters) → lista filtrada
       ↓
ObservableList → tableView.setItems()
       ↓
[Usuario selecciona fila/proveedor] → onRowSelected(row)
       ↓
facade.buildSummary(row) → ProveedoresSummaryModel
       ↓
updateSummaryPanel() → Labels del panel derecho actualizados
```

## Filtros Disponibles
| Filtro | Control | Descripción |
|--------|---------|-------------|
| Búsqueda libre | `TextField` | Búsqueda por nombre de proveedor o referencia |
| Categoría | `ComboBox<String>` | Categoría del proveedor (monturas, lentes, accesorios, etc.) |
| Estado | `ComboBox<String>` | Estado del proveedor (activo, inactivo, evaluando) |
| Sucursal | `ComboBox<String>` | Sede de abastecimiento |
| Desde | `DatePicker` | Fecha inicio del rango |
| Hasta | `DatePicker` | Fecha fin del rango |

## Panel Resumen
El `ProveedoresSummaryModel` muestra los campos relevantes del proveedor seleccionado. El panel derecho persistente presenta información detallada del proveedor, incluyendo datos de contacto, métricas de desempeño, órdenes activas y acciones contextuales para gestionar la relación comercial.

## Patrones de Diseño
- **Facade Pattern**: `ProveedoresFacade` centraliza la lógica de negocio del módulo
- **MVC (Model-View-Controller)**: separación entre FXML, Controller y modelos de datos
- **Record Pattern**: modelos de fila como records inmutables con JavaFX properties
- **Toggle Group Pattern**: sub-vistas mutuamente exclusivas mediante `ToggleGroup`
- **Domain Object Pattern**: entidad `Proveedor` en `shared.domain.proveedor` como objeto de dominio compartido
- **Filter Chain Pattern**: filtros encadenados con `FilterSupport`
- **Master-Detail Pattern**: directorio como lista maestra con detalle de perfil, catálogo y métricas

## Integración con Otros Módulos
| Módulo | Punto de integración | Descripción |
|--------|---------------------|-------------|
| Compras | Órdenes de compra | Los proveedores son destino de las órdenes de compra |
| Inventario | Recepciones | Mercadería recibida que ingresa al inventario |
| Ventas | Catálogos | Productos de proveedores disponibles para venta |
| Configuración | Catálogos maestros | Marcas y categorías vinculadas a proveedores |
| Sucursales | Filtro global | Proveedores asociados a sucursales de abastecimiento |
| Reportes | Rotación | Datos de proveedores en análisis de rotación de inventario |
