# Lienzo del módulo Caja para sistema de óptica en JavaFX

## 1. Identidad del módulo

### Nombre oficial del módulo en el menú
**Caja**

### Texto visible del botón del sidebar
**Caja**

### Tooltip del botón del sidebar
**Registrar cobros, abonos, comprobantes, cierres y pagos pendientes**

### Ícono conceptual
Caja registradora, dinero, recibo o pago.

### Título visible en pantalla
**Caja**

### Subtítulo visible en pantalla
**Cobro de órdenes ópticas, abonos, comprobantes, cierres y control de pagos**

### Tipo de módulo
Módulo transaccional y administrativo con sub-vistas internas especializadas.

### Objetivo del módulo
Permitir que la óptica cobre órdenes, registre abonos, consulte saldos, emita comprobantes, controle cierres diarios y mantenga visibilidad clara sobre pagos pendientes e historial de cobros.

---

## 2. Rol del módulo dentro de la arquitectura GUI

Caja no debe sentirse como un POS genérico. Debe verse como el punto de cierre económico del trabajo óptico.

### Regla arquitectónica principal
Cuando el usuario entre al módulo Caja, la región central principal del sistema debe reemplazarse por una vista completa llamada conceptualmente:
**CajaModuleView**

### Estructura interna limpia del módulo
La vista Caja debe dividirse en:
- encabezado del módulo
- barra de filtros y búsqueda
- subnavegación interna del módulo
- región central intercambiable para sub-vistas
- panel lateral derecho con resumen persistente del cobro o la orden seleccionada

### Filosofía de implementación
El usuario debe poder cobrar, consultar saldos, revisar comprobantes y cerrar caja sin perder el contexto de la orden o del cliente.

### Regla de arquitectura limpia
Debe existir una región interna estable que se reescriba solo con el submódulo activo. Esa región se denomina conceptualmente:
**cajaContentHostPane**

Ese contenedor puede ser un **StackPane** o el centro de un **BorderPane** secundario.

### Sub-vistas que viven dentro de cajaContentHostPane
- Cobro de orden
- Abonos y saldos
- Comprobantes y facturación
- Cierre de caja
- Pagos pendientes
- Histórico de cobros

---

## 3. Sub-vistas oficiales del módulo Caja

## Sub-vistas definidas
1. **Cobro de orden**
2. **Abonos y saldos**
3. **Comprobantes y facturación**
4. **Cierre de caja**
5. **Pagos pendientes**
6. **Histórico de cobros**

## Orden de prioridad
1. Cobro de orden
2. Abonos y saldos
3. Comprobantes y facturación
4. Cierre de caja
5. Pagos pendientes
6. Histórico de cobros

## Vista por defecto al abrir el módulo
**Cobro de orden**

Motivo: es la operación diaria más frecuente y la que más valor transmite en una óptica real.

---

## 4. Estructura visual general del módulo Caja

## Contenedor raíz del módulo
**BorderPane**

### Distribución general del BorderPane principal
- **top**: encabezado del módulo + filtros + subnavegación
- **center**: cuerpo principal del módulo
- **right**: no aplica como región separada, porque el panel persistente vive dentro del cuerpo
- **left**: no aplica, porque el sidebar global ya existe fuera del módulo
- **bottom**: no aplica

### Decisión espacial clave
El cuerpo principal debe organizarse con un **SplitPane** horizontal.

### Distribución del SplitPane
- panel izquierdo o central amplio: sub-vista activa del módulo
- panel derecho: resumen persistente del cobro o la orden seleccionada

### Proporción recomendada
- región de trabajo: 68% a 72%
- panel derecho: 28% a 32%

### Regla de estabilidad visual
El panel derecho debe mantenerse visible durante casi toda la navegación del módulo para no perder contexto económico.

---

## 5. Encabezado del módulo Caja

## Contenedor sugerido
**VBox** en la región top del BorderPane principal.

### Subestructura del encabezado
1. franja superior con título y acciones globales
2. franja media con filtros y búsqueda
3. franja inferior con subnavegación interna

---

## 6. Franja superior del encabezado

## Contenedor sugerido
**HBox**

### Zona izquierda
Un **VBox** con:
- Label principal: **Caja**
- Label secundario: **Cobro de órdenes ópticas, abonos, comprobantes, cierres y control de pagos**

### Zona derecha
Un **HBox** con:
- Button secundario: **Actualizar caja**
- Button secundario: **Exportar cobros**
- Button principal: **Nuevo cobro**

## Controles exactos

### Label principal
- Control: **Label**
- Texto exacto: **Caja**
- Tooltip: no necesita

### Label secundario
- Control: **Label**
- Texto exacto: **Cobro de órdenes ópticas, abonos, comprobantes, cierres y control de pagos**
- Tooltip: no necesita

### Botón secundario 1
- Control: **Button**
- Texto exacto: **Actualizar caja**
- Tooltip: **Recargar los cobros, saldos y comprobantes visibles del módulo**
- Estilo: secundario

### Botón secundario 2
- Control: **Button**
- Texto exacto: **Exportar cobros**
- Tooltip: **Exportar los cobros visibles según los filtros aplicados**
- Estilo: secundario

### Botón principal
- Control: **Button**
- Texto exacto: **Nuevo cobro**
- Tooltip: **Iniciar el registro de un nuevo cobro u orden en caja**
- Estilo: acción principal

---

## 7. Franja media: filtros y búsqueda

## Contenedor sugerido
**FlowPane**

## Función
Permitir localizar cobros, órdenes, saldos y comprobantes por cliente, orden, estado, sucursal o fecha.

## Controles oficiales

### Búsqueda rápida
- Control: **TextField**
- Label asociado: **Buscar**
- Placeholder exacto: **Orden, cliente, comprobante o referencia**
- Tooltip: **Buscar cobros por orden, cliente, comprobante o texto relacionado**

### Filtro por estado de cobro
- Control: **ComboBox**
- Label asociado: **Estado**
- Ítems de semilla:
  - Todos
  - Pendiente de pago
  - Abono parcial
  - Pagado
  - Facturado
  - Anulado
  - Vencido
  - Listo para entrega
  - Requiere validación
- Valor inicial: **Todos**
- Tooltip: **Filtrar cobros según su estado actual**

### Filtro por método de pago
- Control: **ComboBox**
- Label asociado: **Método de pago**
- Ítems de semilla:
  - Todos
  - Efectivo
  - Tarjeta
  - Transferencia
  - Mixto
- Valor inicial: **Todos**
- Tooltip: **Filtrar según el método de pago registrado**

### Filtro por sucursal
- Control: **ComboBox**
- Label asociado: **Sucursal**
- Ítems de semilla:
  - Todas
  - Matriz Centro
  - Norte Mall
- Valor inicial: **Todas**
- Tooltip: **Filtrar cobros y saldos por sucursal**

### Filtro por fecha inicial
- Control: **DatePicker**
- Label asociado: **Desde**
- Tooltip: **Seleccionar la fecha inicial para consultar cobros**

### Filtro por fecha final
- Control: **DatePicker**
- Label asociado: **Hasta**
- Tooltip: **Seleccionar la fecha final para consultar cobros**

### Opción rápida
- Control: **CheckBox**
- Texto exacto: **Solo pagos pendientes**
- Tooltip: **Mostrar únicamente órdenes con saldo o pago incompleto**

### Botón limpiar
- Control: **Button**
- Texto exacto: **Limpiar filtros**
- Tooltip: **Restablecer la búsqueda y todos los filtros del módulo**

---

## 8. Franja inferior: subnavegación interna del módulo

## Contenedor sugerido
**HBox** con **ToggleButton** dentro de un **ToggleGroup**

## Sub-vistas visibles en la barra
- Cobro de orden
- Abonos y saldos
- Comprobantes y facturación
- Cierre de caja
- Pagos pendientes
- Histórico de cobros

## Tooltips exactos
- Cobro de orden: **Cobrar una orden óptica completa o registrar un pago**
- Abonos y saldos: **Consultar pagos parciales y saldos pendientes por orden**
- Comprobantes y facturación: **Consultar o emitir comprobantes asociados a los cobros**
- Cierre de caja: **Revisar el cierre diario de caja y totales por método de pago**
- Pagos pendientes: **Consultar órdenes con saldo pendiente o vencido**
- Histórico de cobros: **Consultar cobros antiguos, completos, parciales o anulados**

## Regla visual
La sub-vista activa debe quedar clara, pero sin competir con el contenido operativo.

---

## 9. Cuerpo principal del módulo Caja

## Contenedor sugerido
**SplitPane** horizontal

### Panel izquierdo o principal
Sub-vista activa del módulo.

### Panel derecho
Resumen persistente del cobro o la orden seleccionada.

---

# 10. Panel derecho persistente: resumen del cobro

## Función
Mantener el contexto económico de la orden o pago seleccionado mientras se navega por el módulo.

## Contenedor sugerido
**ScrollPane** con un **VBox** interno.

## Bloques internos del panel derecho
1. Identidad de la orden
2. Contexto del cliente
3. Resumen económico
4. Estado del cobro
5. Acciones rápidas

## 10.1. Bloque: Identidad de la orden

### Campos visibles
- Referencia de orden
- Tipo de trabajo
- Sucursal

### Seeds
- **Referencia de orden: OV-124**
- **Tipo de trabajo: Lentes monofocales**
- **Sucursal: Matriz Centro**

## 10.2. Bloque: Contexto del cliente

### Campos visibles
- Cliente
- Código del cliente
- Fecha promesa

### Seeds
- **Cliente: Sofía Ramírez**
- **Código del cliente: CL-00124**
- **Fecha promesa: 16/04/2026**

## 10.3. Bloque: Resumen económico

### Campos visibles
- Subtotal
- Descuento
- Abono acumulado
- Saldo

### Seeds
- **Subtotal: $ 110.00**
- **Descuento: $ 5.00**
- **Abono acumulado: $ 50.00**
- **Saldo: $ 55.00**

## 10.4. Bloque: Estado del cobro

### Campos visibles
- Estado actual
- Método principal
- Comprobante

### Seeds
- **Estado actual: Abono parcial**
- **Método principal: Transferencia**
- **Comprobante: FAC-2026-0184**

## 10.5. Bloque: Acciones rápidas

### Botones oficiales
- **Registrar pago**
- **Registrar abono**
- **Emitir comprobante**
- **Abrir orden**
- **Ver entregas**

### Tooltips exactos
- Registrar pago: **Registrar el pago completo de la orden seleccionada**
- Registrar abono: **Registrar un nuevo abono para la orden seleccionada**
- Emitir comprobante: **Emitir o consultar el comprobante del cobro actual**
- Abrir orden: **Abrir la orden óptica asociada al cobro**
- Ver entregas: **Consultar la situación de entrega de la orden relacionada**

---

# 11. Sub-vista 1: Cobro de orden

## Función
Registrar el cobro principal de una orden óptica desde una vista clara y controlada.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: selección o búsqueda de orden
- **center**: bloque económico principal
- **bottom**: acciones de cobro

## 11.1. Barra superior de selección

### Controles oficiales
- Label: **Buscar orden**
- TextField con placeholder: **Orden, cliente o referencia**
- Button: **Buscar**
- Button: **Usar orden seleccionada**

### Tooltips
- Buscar orden: **Buscar una orden para registrar su cobro**
- Buscar: **Ejecutar la búsqueda de la orden**
- Usar orden seleccionada: **Usar la orden seleccionada como base del cobro actual**

## 11.2. Bloque económico principal

### Contenedor sugerido
**GridPane** o **VBox** con bloques en **GridPane**

### Campos visibles
- Cliente
- Orden
- Concepto
- Precio montura
- Precio lente
- Subtotal
- Descuento
- Total final

### Seeds
- Cliente: **Sofía Ramírez**
- Orden: **OV-124**
- Concepto: **Orden óptica completa**
- Precio montura: **$ 68.00**
- Precio lente: **$ 42.00**
- Subtotal: **$ 110.00**
- Descuento: **$ 5.00**
- Total final: **$ 105.00**

### Tooltips
- Concepto: **Concepto general del cobro asociado a la orden**
- Precio montura: **Valor correspondiente a la montura seleccionada**
- Precio lente: **Valor correspondiente al lente y sus tratamientos**
- Total final: **Monto final después de descuentos aplicados**

## 11.3. Método de pago

### Controles sugeridos
- ComboBox: **Método de pago**
- CheckBox: **Dejar saldo pendiente**

### Seeds
- Método de pago: Efectivo, Tarjeta, Transferencia, Mixto
- Valor inicial: **Transferencia**

### Tooltips
- Método de pago: **Seleccione el método principal de pago**
- Dejar saldo pendiente: **Registrar el cobro como pago parcial dejando un saldo pendiente**

## 11.4. Botones oficiales del submódulo
- **Registrar cobro**
- **Registrar abono**
- **Cancelar cobro**
- **Emitir comprobante**

### Tooltips
- Registrar cobro: **Registrar el pago de la orden actual**
- Registrar abono: **Registrar un abono sin cerrar totalmente la orden**
- Cancelar cobro: **Cancelar el proceso de cobro actual**
- Emitir comprobante: **Emitir el comprobante del pago registrado**

### Estado vacío
**Seleccione una orden para registrar su cobro**

---

# 12. Sub-vista 2: Abonos y saldos

## Función
Consultar y registrar pagos parciales asociados a órdenes con saldo pendiente.

## Contenedor principal sugerido
**SplitPane** horizontal

### Panel izquierdo
**TableView** de órdenes con saldo

### Panel derecho
Detalle del saldo y registro de nuevo abono

## 12.1. TableView principal

### Columnas oficiales
- Orden
- Cliente
- Total
- Abonado
- Saldo
- Último pago
- Estado

### Seeds oficiales
1. OV-124 | Sofía Ramírez | $ 105.00 | $ 50.00 | $ 55.00 | 12/04/2026 | Abono parcial
2. OV-128 | Luis Andrade | $ 148.00 | $ 0.00 | $ 148.00 | — | Pendiente de pago
3. OV-131 | Diana Vélez | $ 96.00 | $ 40.00 | $ 56.00 | 11/04/2026 | Abono parcial

### Tooltip de la tabla
**Órdenes con saldo pendiente o pagos parciales**

## 12.2. Panel derecho de saldo

### Campos visibles
- Cliente
- Orden
- Total
- Abonado
- Saldo
- Último método de pago

### Seeds
- Cliente: **Sofía Ramírez**
- Orden: **OV-124**
- Total: **$ 105.00**
- Abonado: **$ 50.00**
- Saldo: **$ 55.00**
- Último método de pago: **Transferencia**

### Campo de nuevo abono
- Label: **Nuevo abono**
- Control sugerido: **TextField**
- Seed sugerida: **55.00**
- Tooltip: **Ingrese el monto del nuevo abono a registrar**

### Botones oficiales del submódulo
- **Registrar nuevo abono**
- **Marcar pagado**
- **Abrir orden**

### Tooltips
- Registrar nuevo abono: **Registrar el nuevo abono para la orden seleccionada**
- Marcar pagado: **Cerrar el saldo pendiente y marcar la orden como pagada**
- Abrir orden: **Abrir la orden óptica relacionada**

### Estado vacío
**No hay órdenes con saldo pendiente en este momento**

---

# 13. Sub-vista 3: Comprobantes y facturación

## Función
Consultar y gestionar los comprobantes emitidos a partir de los cobros realizados.

## Contenedor principal sugerido
**SplitPane** horizontal

### Panel izquierdo
**TableView** de comprobantes

### Panel derecho
Detalle del comprobante seleccionado

## 13.1. TableView de comprobantes

### Columnas oficiales
- Comprobante
- Fecha
- Cliente
- Orden
- Total
- Estado

### Seeds oficiales
1. FAC-2026-0184 | 12/04/2026 | Sofía Ramírez | OV-124 | $ 50.00 | Emitido
2. FAC-2026-0185 | 12/04/2026 | Carlos Mendoza | OV-120 | $ 98.00 | Emitido
3. FAC-2026-0186 | 13/04/2026 | Diana Vélez | OV-131 | $ 40.00 | Parcial

### Tooltip de la tabla
**Comprobantes emitidos a partir de cobros registrados en caja**

## 13.2. Detalle de comprobante

### Campos visibles
- Número de comprobante
- Fecha
- Cliente
- Orden
- Método de pago
- Total
- Estado

### Seeds
- Número de comprobante: **FAC-2026-0184**
- Fecha: **12/04/2026**
- Cliente: **Sofía Ramírez**
- Orden: **OV-124**
- Método de pago: **Transferencia**
- Total: **$ 50.00**
- Estado: **Emitido**

### Botones oficiales del submódulo
- **Reimprimir comprobante**
- **Reenviar comprobante**
- **Abrir orden**

### Tooltips
- Reimprimir comprobante: **Generar nuevamente el comprobante seleccionado**
- Reenviar comprobante: **Registrar o reenviar el comprobante al cliente**
- Abrir orden: **Abrir la orden asociada al comprobante**

### Estado vacío
**No hay comprobantes visibles con los criterios actuales**

---

# 14. Sub-vista 4: Cierre de caja

## Función
Controlar el cierre diario de la caja por sucursal y por método de pago.

## Contenedor principal sugerido
**VBox** con bloques de resumen y validación.

### Estructura interna
1. Resumen del día
2. Totales por método
3. Validación de cierre
4. Acciones finales

## 14.1. Resumen del día

### Campos visibles
- Fecha de cierre
- Sucursal
- Cobros registrados
- Total del día

### Seeds
- Fecha de cierre: **13/04/2026**
- Sucursal: **Matriz Centro**
- Cobros registrados: **18**
- Total del día: **$ 1,240.50**

## 14.2. Totales por método

### Campos visibles
- Efectivo
- Tarjeta
- Transferencia
- Mixto

### Seeds
- Efectivo: **$ 220.00**
- Tarjeta: **$ 310.50**
- Transferencia: **$ 640.00**
- Mixto: **$ 70.00**

## 14.3. Validación de cierre

### Campos visibles
- Caja esperada
- Caja registrada
- Diferencia

### Seeds
- Caja esperada: **$ 1,240.50**
- Caja registrada: **$ 1,240.50**
- Diferencia: **$ 0.00**

## 14.4. Botones oficiales del submódulo
- **Confirmar cierre**
- **Revisar diferencias**
- **Exportar cierre**

### Tooltips
- Confirmar cierre: **Registrar el cierre diario de caja**
- Revisar diferencias: **Consultar si existe alguna diferencia entre caja esperada y registrada**
- Exportar cierre: **Exportar el resumen del cierre diario**

### Estado vacío
**No hay suficientes cobros registrados para mostrar un cierre de caja**

---

# 15. Sub-vista 5: Pagos pendientes

## Función
Consultar órdenes con saldo pendiente, vencido o que requieren seguimiento de cobro.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: resumen de pendientes
- **center**: **TableView** principal

## 15.1. Resumen de pendientes

### Campos visibles
- Órdenes pendientes
- Monto pendiente total
- Pendientes vencidos

### Seeds
- Órdenes pendientes: **7**
- Monto pendiente total: **$ 412.00**
- Pendientes vencidos: **2**

## 15.2. TableView principal

### Columnas oficiales
- Orden
- Cliente
- Saldo
- Último pago
- Estado
- Sucursal
- Prioridad

### Seeds oficiales
1. OV-124 | Sofía Ramírez | $ 55.00 | 12/04/2026 | Abono parcial | Matriz Centro | Media
2. OV-128 | Luis Andrade | $ 148.00 | — | Pendiente de pago | Norte Mall | Alta
3. OV-131 | Diana Vélez | $ 56.00 | 11/04/2026 | Abono parcial | Matriz Centro | Media
4. OV-133 | María León | $ 153.00 | 05/04/2026 | Vencido | Norte Mall | Alta

### Tooltip de la tabla
**Órdenes con pagos pendientes o seguimiento de cobro necesario**

### Botones oficiales del submódulo
- **Registrar abono**
- **Abrir cliente**
- **Marcar seguimiento**

### Tooltips
- Registrar abono: **Registrar un nuevo pago parcial para la orden seleccionada**
- Abrir cliente: **Consultar la ficha del cliente asociado**
- Marcar seguimiento: **Señalar la orden para gestión de cobro o contacto**

### Estado vacío
**No hay pagos pendientes registrados en este momento**

---

# 16. Sub-vista 6: Histórico de cobros

## Función
Consultar cobros antiguos, completos, parciales, anulados o facturados mediante filtros más amplios.

## Contenedor principal sugerido
**BorderPane**

### Distribución interna
- **top**: filtros extendidos
- **center**: **TableView** de resultados

## 16.1. Filtros extendidos

### Controles sugeridos
- TextField: **Cliente, orden o comprobante**
- ComboBox: **Estado**
- ComboBox: **Método de pago**
- ComboBox: **Sucursal**
- DatePicker: **Desde**
- DatePicker: **Hasta**
- Button: **Buscar histórico**
- Button: **Limpiar filtros**

### Tooltip general
**Filtre cobros históricos por referencia, cliente, estado, sucursal o fecha**

## 16.2. TableView principal

### Columnas oficiales
- Fecha
- Orden
- Cliente
- Monto
- Método
- Estado
- Comprobante

### Seeds oficiales
1. 10/04/2026 | OV-118 | Carlos Mendoza | $ 98.00 | Tarjeta | Pagado | FAC-2026-0171
2. 09/04/2026 | OV-111 | Ana Vera | $ 75.00 | Efectivo | Pagado | FAC-2026-0168
3. 08/04/2026 | OV-109 | Juan Cedeño | $ 40.00 | Transferencia | Anulado | FAC-2026-0163

### Tooltip de la tabla
**Consulte el histórico de cobros registrados por orden, fecha o cliente**

### Botones oficiales del submódulo
- **Abrir cobro**
- **Exportar histórico**

### Tooltips
- Abrir cobro: **Abrir el registro detallado del cobro seleccionado**
- Exportar histórico: **Exportar el histórico visible de cobros**

### Estado vacío
**No hay cobros históricos que coincidan con los criterios actuales**

---

# 17. Formulario conceptual: Nuevo cobro

## Función
Permitir iniciar un cobro o registrar un pago nuevo sin salir de la arquitectura del módulo.

## Contenedor sugerido
Vista interna o diálogo amplio controlado. Para esta maqueta conviene resolverlo dentro del mismo módulo.

## Estructura sugerida
**ScrollPane** con **VBox** y bloques en **GridPane**.

### Grupos de campos
1. Orden y cliente
2. Resumen económico
3. Método de pago
4. Emisión de comprobante

### Campos mínimos
- Orden
- Cliente
- Total
- Monto a cobrar
- Método de pago
- Observación

### Botones finales
- **Guardar cobro**
- **Cancelar**

### Tooltips
- Guardar cobro: **Registrar el nuevo cobro en caja**
- Cancelar: **Cerrar el registro de cobro sin guardar**

---

# 18. Seed data oficial del módulo Caja

## Clientes de semilla
- Sofía Ramírez
- Luis Andrade
- Diana Vélez
- Carlos Mendoza
- Ana Vera
- María León
- Juan Cedeño

## Órdenes de semilla
- OV-124
- OV-128
- OV-131
- OV-133
- OV-118
- OV-111
- OV-109

## Comprobantes
- FAC-2026-0184
- FAC-2026-0185
- FAC-2026-0186
- FAC-2026-0171
- FAC-2026-0168
- FAC-2026-0163

## Sucursales
- Matriz Centro
- Norte Mall

## Métodos de pago
- Efectivo
- Tarjeta
- Transferencia
- Mixto

## Estados usados en el módulo
- Pendiente de pago
- Abono parcial
- Pagado
- Facturado
- Anulado
- Vencido
- Listo para entrega
- Requiere validación

---

# 19. Textos oficiales del módulo Caja

## Títulos y labels principales
- Caja
- Cobro de órdenes ópticas, abonos, comprobantes, cierres y control de pagos
- Buscar
- Estado
- Método de pago
- Sucursal
- Desde
- Hasta
- Solo pagos pendientes
- Cobro de orden
- Abonos y saldos
- Comprobantes y facturación
- Cierre de caja
- Pagos pendientes
- Histórico de cobros
- Buscar orden
- Cliente
- Orden
- Concepto
- Precio montura
- Precio lente
- Subtotal
- Descuento
- Total final
- Abono
- Saldo
- Método de pago
- Fecha de cierre
- Cobros registrados
- Total del día
- Caja esperada
- Caja registrada
- Diferencia
- Órdenes pendientes
- Monto pendiente total
- Pendientes vencidos

## Botones oficiales
- Actualizar caja
- Exportar cobros
- Nuevo cobro
- Limpiar filtros
- Buscar
- Usar orden seleccionada
- Registrar cobro
- Registrar abono
- Cancelar cobro
- Emitir comprobante
- Registrar nuevo abono
- Marcar pagado
- Abrir orden
- Reimprimir comprobante
- Reenviar comprobante
- Confirmar cierre
- Revisar diferencias
- Exportar cierre
- Abrir cliente
- Marcar seguimiento
- Buscar histórico
- Abrir cobro
- Exportar histórico
- Guardar cobro
- Cancelar
- Registrar pago
- Ver entregas

## Placeholders
- Orden, cliente, comprobante o referencia
- Orden, cliente o referencia
- Cliente, orden o comprobante

## Empty states
- Seleccione una orden para registrar su cobro
- No hay órdenes con saldo pendiente en este momento
- No hay comprobantes visibles con los criterios actuales
- No hay suficientes cobros registrados para mostrar un cierre de caja
- No hay pagos pendientes registrados en este momento
- No hay cobros históricos que coincidan con los criterios actuales

---

# 20. Reglas visuales específicas del módulo Caja

- el cobro de orden debe sentirse como la operación principal del módulo
- el panel derecho debe mantener visibilidad clara de subtotal, abono y saldo
- Abonos y saldos debe verse muy controlado y legible
- Comprobantes y facturación debe sentirse documental, no protagonista
- Cierre de caja debe verse administrativo y confiable
- Pagos pendientes debe comunicar urgencia moderada, no caos
- Histórico de cobros debe sentirse consultivo y ordenado
- no abusar de formas redondeadas ni de tarjetas infladas
- la prioridad debe ser claridad económica, control del estado de pago y vínculo con la orden óptica

---

# 21. Relación del módulo Caja con otros módulos

Caja debe conectarse con:
- Venta óptica, porque muchas órdenes llegan aquí para su cobro
- Entregas, porque una orden pagada puede quedar lista para retiro o entrega
- Clientes, porque el estado de pago se relaciona con la ficha del cliente
- Seguimiento, porque los saldos pendientes pueden generar contacto o recordatorio
- Reportes, porque los cobros alimentan los indicadores económicos
- Inicio, porque el panel principal puede resumir cobros del día o pagos pendientes
- Sucursales, porque la caja debe distinguir la sede operativa

---

# 22. Cierre del módulo Caja

Este módulo debe transmitir que la óptica cobra con orden, puede manejar pagos completos o parciales, emitir comprobantes, cerrar caja y saber exactamente qué orden está pagada, cuál tiene saldo y cuál requiere seguimiento. No es solo una pantalla de dinero: es el cierre económico del trabajo óptico.

La complejidad correcta del módulo está en que cada sub-vista resuelve una parte clara del control de cobros sin convertir la experiencia en un sistema contable pesado.