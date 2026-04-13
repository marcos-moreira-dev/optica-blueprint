# Optica Demo Desktop

Sistema integral de optica — demo desktop JavaFX autocontenida.

## Ejecucion

```bash
mvn clean javafx:run
```

## Arquitectura

- JavaFX 21 + Maven
- Feature-first + MVC por modulo + Facade + Store en memoria
- Sin base de datos, sin backend, sin persistencia externa
- Toda la data vive en memoria via `DemoStore` + seed factories

## Modulos

Inicio | Agenda | Clientes | Recetas | Venta Optica | Ordenes de Laboratorio | Inventario | Caja | Entregas | Seguimiento | Reportes | Configuracion | Seguros | Proveedores | Compras | Usuarios y Roles | Taller | Notificaciones | Sucursales
