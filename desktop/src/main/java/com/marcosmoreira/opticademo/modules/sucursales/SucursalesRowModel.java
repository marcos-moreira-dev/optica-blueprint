package com.marcosmoreira.opticademo.modules.sucursales;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Row models for the various TableViews in the Sucursales module.
 */
public final class SucursalesRowModel {

    private SucursalesRowModel() {
    }

    /**
     * Row model for the Directorio de sucursales TableView.
     */
    public record DirectorioRow(
            String sucursal,
            String ciudad,
            String responsable,
            String horario,
            String estado,
            String servicios
    ) {
        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }

        public StringProperty ciudadProperty() {
            return new SimpleStringProperty(ciudad);
        }

        public StringProperty responsableProperty() {
            return new SimpleStringProperty(responsable);
        }

        public StringProperty horarioProperty() {
            return new SimpleStringProperty(horario);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty serviciosProperty() {
            return new SimpleStringProperty(servicios);
        }
    }

    /**
     * Row model for the Personal y permisos TableView.
     */
    public record PersonalRow(
            String usuario,
            String rol,
            String estado,
            String accesoPrincipal
    ) {
        public StringProperty usuarioProperty() {
            return new SimpleStringProperty(usuario);
        }

        public StringProperty rolProperty() {
            return new SimpleStringProperty(rol);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty accesoPrincipalProperty() {
            return new SimpleStringProperty(accesoPrincipal);
        }
    }

    /**
     * Row model for the Inventario y stock por sucursal TableView.
     */
    public record InventarioRow(
            String referencia,
            String nombre,
            String categoria,
            String stock,
            String estado,
            String observacion
    ) {
        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty nombreProperty() {
            return new SimpleStringProperty(nombre);
        }

        public StringProperty categoriaProperty() {
            return new SimpleStringProperty(categoria);
        }

        public StringProperty stockProperty() {
            return new SimpleStringProperty(stock);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty observacionProperty() {
            return new SimpleStringProperty(observacion);
        }
    }

    /**
     * Row model for the Agenda atencion y flujo local TableView.
     */
    public record AgendaRow(
            String indicador,
            String valor,
            String estado,
            String observacion
    ) {
        public StringProperty indicadorProperty() {
            return new SimpleStringProperty(indicador);
        }

        public StringProperty valorProperty() {
            return new SimpleStringProperty(valor);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty observacionProperty() {
            return new SimpleStringProperty(observacion);
        }
    }

    /**
     * Row model for the Caja y desempeno por sucursal TableView.
     */
    public record CajaRow(
            String indicador,
            String valor,
            String estado,
            String observacion
    ) {
        public StringProperty indicadorProperty() {
            return new SimpleStringProperty(indicador);
        }

        public StringProperty valorProperty() {
            return new SimpleStringProperty(valor);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty observacionProperty() {
            return new SimpleStringProperty(observacion);
        }
    }

    /**
     * Row model for the Comparativo entre sucursales TableView.
     */
    public record ComparativoRow(
            String sucursal,
            String ventas,
            String ticketPromedio,
            String stockCritico,
            String recallsPendientes,
            String retrasos,
            String estadoGeneral
    ) {
        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }

        public StringProperty ventasProperty() {
            return new SimpleStringProperty(ventas);
        }

        public StringProperty ticketPromedioProperty() {
            return new SimpleStringProperty(ticketPromedio);
        }

        public StringProperty stockCriticoProperty() {
            return new SimpleStringProperty(stockCritico);
        }

        public StringProperty recallsPendientesProperty() {
            return new SimpleStringProperty(recallsPendientes);
        }

        public StringProperty retrasosProperty() {
            return new SimpleStringProperty(retrasos);
        }

        public StringProperty estadoGeneralProperty() {
            return new SimpleStringProperty(estadoGeneral);
        }
    }
}
