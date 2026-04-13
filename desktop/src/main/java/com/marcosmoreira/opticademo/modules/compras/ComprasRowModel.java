package com.marcosmoreira.opticademo.modules.compras;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Row models for all 7 sub-views of the Compras module.
 */
public final class ComprasRowModel {

    private ComprasRowModel() {
    }

    // ==================== Sub-view 1: Solicitudes de compra ====================

    public record SolicitudRow(
            String solicitud,
            String motivo,
            String categoria,
            String sucursalDestino,
            String prioridad,
            String estado,
            String proveedorSugerido
    ) {
        public StringProperty solicitudProperty() {
            return new SimpleStringProperty(solicitud);
        }

        public StringProperty motivoProperty() {
            return new SimpleStringProperty(motivo);
        }

        public StringProperty categoriaProperty() {
            return new SimpleStringProperty(categoria);
        }

        public StringProperty sucursalDestinoProperty() {
            return new SimpleStringProperty(sucursalDestino);
        }

        public StringProperty prioridadProperty() {
            return new SimpleStringProperty(prioridad);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty proveedorSugeridoProperty() {
            return new SimpleStringProperty(proveedorSugerido);
        }
    }

    // ==================== Sub-view 2: Ordenes de compra ====================

    public record OrdenCompraRow(
            String orden,
            String proveedor,
            String fecha,
            String sucursalDestino,
            String estado,
            String items,
            String totalEstimado
    ) {
        public StringProperty ordenProperty() {
            return new SimpleStringProperty(orden);
        }

        public StringProperty proveedorProperty() {
            return new SimpleStringProperty(proveedor);
        }

        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty sucursalDestinoProperty() {
            return new SimpleStringProperty(sucursalDestino);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty itemsProperty() {
            return new SimpleStringProperty(items);
        }

        public StringProperty totalEstimadoProperty() {
            return new SimpleStringProperty(totalEstimado);
        }
    }

    // ==================== Sub-view 3: Compras por proveedor ====================

    public record ProveedorCompraRow(
            String proveedor,
            String ordenesAbiertas,
            String ultimaCompra,
            String categoriaPrincipal,
            String estado
    ) {
        public StringProperty proveedorProperty() {
            return new SimpleStringProperty(proveedor);
        }

        public StringProperty ordenesAbiertasProperty() {
            return new SimpleStringProperty(ordenesAbiertas);
        }

        public StringProperty ultimaCompraProperty() {
            return new SimpleStringProperty(ultimaCompra);
        }

        public StringProperty categoriaPrincipalProperty() {
            return new SimpleStringProperty(categoriaPrincipal);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }
    }

    // ==================== Sub-view 4: Back-orders y pendientes ====================

    public record BackOrderRow(
            String referencia,
            String orden,
            String proveedor,
            String itemPendiente,
            String cantidad,
            String fechaEsperada,
            String estado
    ) {
        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty ordenProperty() {
            return new SimpleStringProperty(orden);
        }

        public StringProperty proveedorProperty() {
            return new SimpleStringProperty(proveedor);
        }

        public StringProperty itemPendienteProperty() {
            return new SimpleStringProperty(itemPendiente);
        }

        public StringProperty cantidadProperty() {
            return new SimpleStringProperty(cantidad);
        }

        public StringProperty fechaEsperadaProperty() {
            return new SimpleStringProperty(fechaEsperada);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }
    }

    // ==================== Sub-view 5: Recepcion vinculada ====================

    public record RecepcionCompraRow(
            String recepcion,
            String orden,
            String fecha,
            String estado,
            String diferencias,
            String responsable
    ) {
        public StringProperty recepcionProperty() {
            return new SimpleStringProperty(recepcion);
        }

        public StringProperty ordenProperty() {
            return new SimpleStringProperty(orden);
        }

        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty diferenciasProperty() {
            return new SimpleStringProperty(diferencias);
        }

        public StringProperty responsableProperty() {
            return new SimpleStringProperty(responsable);
        }
    }

    // ==================== Sub-view 6: Compras por sucursal ====================

    public record SucursalCompraRow(
            String sucursal,
            String solicitudes,
            String ordenesAbiertas,
            String pendientes,
            String totalEstimado,
            String estadoGeneral
    ) {
        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }

        public StringProperty solicitudesProperty() {
            return new SimpleStringProperty(solicitudes);
        }

        public StringProperty ordenesAbiertasProperty() {
            return new SimpleStringProperty(ordenesAbiertas);
        }

        public StringProperty pendientesProperty() {
            return new SimpleStringProperty(pendientes);
        }

        public StringProperty totalEstimadoProperty() {
            return new SimpleStringProperty(totalEstimado);
        }

        public StringProperty estadoGeneralProperty() {
            return new SimpleStringProperty(estadoGeneral);
        }
    }

    // ==================== Sub-view 7: Historico ====================

    public record HistoricoCompraRow(
            String fecha,
            String referencia,
            String tipoRegistro,
            String proveedor,
            String estado,
            String observacion
    ) {
        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty tipoRegistroProperty() {
            return new SimpleStringProperty(tipoRegistro);
        }

        public StringProperty proveedorProperty() {
            return new SimpleStringProperty(proveedor);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty observacionProperty() {
            return new SimpleStringProperty(observacion);
        }
    }
}
