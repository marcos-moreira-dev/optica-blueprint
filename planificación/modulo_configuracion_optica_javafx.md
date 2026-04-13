# Lienzo del módulo Configuración para sistema de óptica en JavaFX

## 1. Identidad del módulo

### Nombre oficial del módulo en el menú
**Configuración**

### Texto visible del botón del sidebar
**Configuración**

### Tooltip del botón del sidebar
**Administrar parámetros generales, sucursales, catálogos, operación y apariencia del sistema óptico**

### Ícono conceptual
Engranaje, parámetros o ajustes institucionales.

### Título visible en pantalla
**Configuración**

### Subtítulo visible en pantalla
**Parámetros generales, operación de la óptica, catálogos y experiencia del sistema**

### Tipo de módulo
Módulo administrativo transversal con categorías internas de configuración.

### Objetivo del módulo
Permitir que la óptica defina parámetros base del negocio, reglas operativas, catálogos maestros, datos institucionales, sucursales, permisos y ajustes visuales sin dispersar la configuración por todo el sistema.

---

## 2. Rol del módulo dentro de la arquitectura GUI

Configuración no debe sentirse como un cajón de sastre ni como una simple pantalla de preferencias. Debe verse como el centro administrativo desde donde se ajusta el comportamiento general del sistema.

### Regla arquitectónica principal
Cuando el usuario entre al módulo Configuración, la región central principal del sistema debe reemplazarse por una vista completa llamada conceptualmente:
**ConfiguracionModuleView**

### Estructura interna limpia del módulo
La vista Configuración debe dividirse en:
- encabezado del módulo
- búsqueda rápida de ajustes
- panel izquierdo de categorías
- región central intercambiable para sub-vistas de configuración
- panel lateral derecho con resumen e impacto de la categoría seleccionada

### Filosofía de implementación
El usuario debe poder localizar una categoría, entrar a una sección concreta y editar parámetros sin perder el contexto general del sistema óptico.

### Regla de arquitectura limpia
Debe existir una región interna estable que se reescriba solo con la categoría activa. Esa región se denomina conceptualmente:
**configuracionContentHostPane**

Ese contenedor puede ser un **StackPane** o el centro de un **BorderPane** secundario.

### Categorías que viven dentro de configuracionContentHostPane
- General del negocio
- Sucursales y operación
- Usuarios, roles y permisos
- Catálogos maestros
- Inventario y abastecimiento
- Venta, caja y comprobantes
- Agenda, seguimiento y comunicación
- Laboratorio y entregas
- Apariencia y experiencia de uso

---

## 3. Estructura visual general del módulo Configuración

## Contenedor raíz del módulo
**BorderPane**

### Distribución general del BorderPane principal
- **top**: encabezado del módulo + búsqueda rápida
- **center**: cuerpo principal del módulo
- **left**: no aplica como región externa porque la navegación de categorías vivirá dentro del cuerpo principal
- **right**: no aplica como región externa porque el panel de ayuda ya vivirá dentro del cuerpo principal
- **bottom**: no aplica

### Decisión espacial clave
El cuerpo principal debe organizarse con un **SplitPane** horizontal de tres zonas.

### Distribución del SplitPane
- panel izquierdo: navegación de categorías
- panel central: categoría activa
- panel derecho: resumen, impacto y ayuda contextual

### Proporción recomendada
- panel izquierdo: 22%
- panel central: 50%
- panel derecho: 28%

### Regla de estabilidad visual
El panel izquierdo y el panel derecho deben mantenerse estables mientras el centro cambia de categoría. Eso hace que la experiencia se sienta muy de escritorio y muy organizada.

---

## 4. Encabezado del módulo Configuración

## Contenedor sugerido
**VBox** en la región top del BorderPane principal.

### Subestructura del encabezado
1. franja superior con título y acciones globales
2. franja inferior con búsqueda rápida de ajustes

---

## 5. Franja superior del encabezado

## Contenedor sugerido
**HBox**

### Zona izquierda
Un **VBox** con:
- Label principal: **Configuración**
- Label secundario: **Parámetros generales, operación de la óptica, catálogos y experiencia del sistema**

### Zona derecha
Un **HBox** con:
- Button secundario: **Restablecer vista**
- Button secundario: **Exportar parámetros**
- Button principal: **Guardar cambios**

## Controles exactos

### Label principal
- Control: **Label**
- Texto exacto: **Configuración**
- Tooltip: no necesita

### Label secundario
- Control: **Label**
- Texto exacto: **Parámetros generales, operación de la óptica, catálogos y experiencia del sistema**
- Tooltip: no necesita

### Botón secundario 1
- Control: **Button**
- Texto exacto: **Restablecer vista**
- Tooltip: **Volver a la categoría inicial sin perder necesariamente los cambios guardados**
- Estilo: secundario

### Botón secundario 2
- Control: **Button**
- Texto exacto: **Exportar parámetros**
- Tooltip: **Exportar la configuración visible o un resumen general del sistema**
- Estilo: secundario

### Botón principal
- Control: **Button**
- Texto exacto: **Guardar cambios**
- Tooltip: **Guardar los cambios realizados en la categoría activa**
- Estilo: acción principal

---

## 6. Franja inferior: búsqueda rápida de ajustes

## Contenedor sugerido
**HBox** o **FlowPane**. Se recomienda **HBox** por simplicidad.

## Función
Permitir localizar rápidamente un ajuste dentro del módulo sin recorrer manualmente todas las categorías.

## Controles oficiales

### Label de búsqueda
- Control: **Label**
- Texto exacto: **Buscar ajuste**
- Tooltip: **Buscar configuraciones por nombre, categoría o palabra clave**

### Campo de búsqueda
- Control: **TextField**
- Placeholder exacto: **Sucursal, comprobante, WhatsApp, stock mínimo, colores...**
- Tooltip: **Buscar configuraciones por texto relacionado**

### Botón auxiliar
- Control: **Button**
- Texto exacto: **Limpiar búsqueda**
- Tooltip: **Limpiar el texto de búsqueda y volver a la navegación normal**

---

## 7. Panel izquierdo: navegación de categorías

## Función
Permitir al usuario moverse entre bloques de configuración amplios y claramente separados.

## Contenedor sugerido
**BorderPane** o **VBox** con un control principal de navegación.

### Decisión recomendada
Usar un **TreeView** simple o una **ListView**. Para esta maqueta, se recomienda **ListView** porque es más sobria y directa visualmente.

## Categorías visibles
- General del negocio
- Sucursales y operación
- Usuarios, roles y permisos
- Catálogos maestros
- Inventario y abastecimiento
- Venta, caja y comprobantes
- Agenda, seguimiento y comunicación
- Laboratorio y entregas
- Apariencia y experiencia de uso

## Tooltips exactos
- General del negocio: **Configurar datos institucionales base de la óptica**
- Sucursales y operación: **Configurar sedes, horarios y reglas operativas por sucursal**
- Usuarios, roles y permisos: **Administrar perfiles de acceso y control del sistema**
- Catálogos maestros: **Administrar listas base que usa toda la aplicación**
- Inventario y abastecimiento: **Definir reglas globales de stock, reposición y recepción**
- Venta, caja y comprobantes: **Definir numeraciones, pagos, abonos y salida documental**
- Agenda, seguimiento y comunicación: **Definir reglas de cita, recall y contacto con clientes**
- Laboratorio y entregas: **Configurar tiempos promesa, estados y validación de trabajos**
- Apariencia y experiencia de uso: **Definir apariencia visual y comportamiento general de la interfaz**

## Regla visual
La categoría seleccionada debe verse claramente activa, pero sin exageración. Debe sentirse administrativa, no decorativa.

---

## 8. Panel derecho: resumen e impacto de categoría

## Función
Dar contexto, explicar el alcance de la categoría activa y recordar al usuario qué partes del sistema se verán afectadas.

## Contenedor sugerido
**ScrollPane** con un **VBox** interno.

## Bloques internos del panel derecho
1. Categoría activa
2. Alcance
3. Impacto operativo
4. Recomendación de uso
5. Acciones rápidas

## 8.1. Bloque: Categoría activa

### Campos visibles
- Categoría
- Estado de cambios

### Seeds
- **Categoría: General del negocio**
- **Estado de cambios: Sin cambios pendientes**

## 8.2. Bloque: Alcance

### Seed
**Esta categoría define los datos institucionales que identifican a la óptica dentro del sistema, comprobantes y módulos de uso diario.**

## 8.3. Bloque: Impacto operativo

### Seed
**Los cambios aquí pueden afectar encabezados, comprobantes, sucursal predeterminada y datos visibles en módulos administrativos.**

## 8.4. Bloque: Recomendación de uso

### Seed
**Revise cuidadosamente los datos institucionales antes de modificar numeraciones, sucursales o datos de contacto visibles.**

## 8.5. Acciones rápidas

### Botones oficiales
- **Guardar categoría**
- **Restablecer cambios**
- **Ir a módulo relacionado**

### Tooltips exactos
- Guardar categoría: **Guardar los cambios realizados en esta categoría**
- Restablecer cambios: **Descartar los cambios no guardados en esta categoría**
- Ir a módulo relacionado: **Abrir el módulo más relacionado con esta configuración**

---

# 9. Categoría 1: General del negocio

## Función
Definir los datos institucionales y de identidad principal de la óptica.

## Contenedor principal sugerido
**ScrollPane** con un **VBox** interno y bloques en **GridPane**.

### Estructura interna
1. Identidad institucional
2. Contacto principal
3. Parámetros generales de operación

## 9.1. Bloque: Identidad institucional

### Contenedor sugerido
**GridPane**

### Campos visibles
- Nombre comercial
- Razón social
- RUC o identificación
- Logo o nombre del sistema

### Seeds
- Nombre comercial: **Óptica Manager Demo**
- Razón social: **Óptica Visual Centro S.A.**
- RUC o identificación: **0999999999001**
- Logo o nombre del sistema: **Óptica Manager**

### Tooltips
- Nombre comercial: **Nombre visible de la óptica en el sistema**
- Razón social: **Razón social o nombre jurídico del negocio**
- RUC o identificación: **Identificación institucional usada en comprobantes y registro**
- Logo o nombre del sistema: **Nombre o identidad visual usada en la aplicación**

## 9.2. Bloque: Contacto principal

### Contenedor sugerido
**GridPane**

### Campos visibles
- Teléfono general
- Correo general
- Dirección principal
- Ciudad

### Seeds
- Teléfono general: **04 600 0000**
- Correo general: **contacto@opticamanager.local**
- Dirección principal: **Av. Principal y calle comercial**
- Ciudad: **Guayaquil**

## 9.3. Bloque: Parámetros generales de operación

### Contenedor sugerido
**GridPane**

### Campos visibles
- Sucursal predeterminada
- Moneda
- Zona horaria

### Seeds
- Sucursal predeterminada: **Matriz Centro**
- Moneda: **USD**
- Zona horaria: **América/Guayaquil**

### Botones oficiales de la categoría
- **Guardar datos del negocio**
- **Restablecer valores**

---

# 10. Categoría 2: Sucursales y operación

## Función
Definir sedes, horarios y capacidad operativa básica por sucursal.

## Contenedor principal sugerido
**SplitPane** horizontal

### Panel izquierdo
**TableView** de sucursales

### Panel derecho
Detalle operativo de la sucursal seleccionada

## 10.1. TableView de sucursales

### Columnas oficiales
- Sucursal
- Dirección
- Horario
- Responsable
- Estado

### Seeds oficiales
1. Matriz Centro | Av. Principal y calle comercial | 08:00 - 18:00 | Laura Gómez | Activa
2. Norte Mall | Centro comercial, local 12 | 10:00 - 20:00 | Carlos Mendoza | Activa

## 10.2. Detalle operativo

### Campos visibles
- Nombre de sucursal
- Teléfono
- Horario operativo
- Responsable
- Maneja inventario propio
- Permite retiro de trabajos
- Caja habilitada

### Seeds
- Nombre de sucursal: **Matriz Centro**
- Teléfono: **04 600 0010**
- Horario operativo: **08:00 - 18:00**
- Responsable: **Laura Gómez**
- Maneja inventario propio: **Sí**
- Permite retiro de trabajos: **Sí**
- Caja habilitada: **Sí**

### Botones oficiales de la categoría
- **Guardar sucursal**
- **Nueva sucursal**
- **Desactivar sucursal**

---

# 11. Categoría 3: Usuarios, roles y permisos

## Función
Administrar accesos al sistema y alcance operativo por usuario.

## Contenedor principal sugerido
**SplitPane** horizontal

### Panel izquierdo
**TableView** de usuarios

### Panel derecho
Detalle de rol y permisos

## 11.1. TableView de usuarios

### Columnas oficiales
- Usuario
- Rol
- Sucursal
- Estado

### Seeds oficiales
1. admin@opticamanager.local | Administrador general | Matriz Centro | Activo
2. recepcion@opticamanager.local | Recepción | Norte Mall | Activo
3. ventas@opticamanager.local | Asesor de ventas | Matriz Centro | Activo
4. tecnico@opticamanager.local | Técnico óptico | Matriz Centro | Activo

## 11.2. Detalle de permisos

### Campos visibles
- Nombre de usuario
- Rol
- Sucursal asociada
- Acceso a caja
- Acceso a inventario
- Acceso a reportes
- Acceso a configuración

### Seeds
- Nombre de usuario: **admin@opticamanager.local**
- Rol: **Administrador general**
- Sucursal asociada: **Matriz Centro**
- Acceso a caja: **Sí**
- Acceso a inventario: **Sí**
- Acceso a reportes: **Sí**
- Acceso a configuración: **Sí**

### Botones oficiales de la categoría
- **Guardar permisos**
- **Nuevo usuario**
- **Desactivar usuario**

---

# 12. Categoría 4: Catálogos maestros

## Función
Centralizar listas maestras que alimentan al resto del sistema.

## Contenedor principal sugerido
**SplitPane** horizontal

### Panel izquierdo
**ListView** o **TableView** de catálogos

### Panel derecho
Detalle de elementos del catálogo seleccionado

## Catálogos visibles
- Marcas
- Categorías de productos
- Materiales de montura
- Tipos de lente
- Tratamientos
- Tipos de seguimiento
- Estados del sistema
- Prioridades
- Tipos de cita

## Ejemplo de detalle
### Catálogo seleccionado
**Tratamientos**

### Valores seed
- Antirreflejo
- Blue cut
- Fotocromático
- Hidrofóbico

### Botones oficiales de la categoría
- **Guardar catálogo**
- **Nuevo valor**
- **Desactivar valor**

---

# 13. Categoría 5: Inventario y abastecimiento

## Función
Definir reglas globales que afectan stock, reposición y recepción.

## Contenedor principal sugerido
**ScrollPane** con **VBox** y bloques en **GridPane**.

### Estructura interna
1. Stock base
2. Reposición
3. Recepción

## 13.1. Bloque: Stock base

### Campos visibles
- Stock mínimo por defecto
- Ubicación por defecto
- Mostrar alertas críticas

### Seeds
- Stock mínimo por defecto: **5**
- Ubicación por defecto: **Bodega general**
- Mostrar alertas críticas: **Sí**

## 13.2. Bloque: Reposición

### Campos visibles
- Generar aviso por bajo stock
- Permitir marcar pedido
- Proveedor preferido por categoría

### Seeds
- Generar aviso por bajo stock: **Sí**
- Permitir marcar pedido: **Sí**
- Proveedor preferido por categoría: **Configurable**

## 13.3. Bloque: Recepción

### Campos visibles
- Permitir recepción parcial
- Requerir responsable de recepción
- Actualizar inventario al confirmar recepción

### Seeds
- Permitir recepción parcial: **Sí**
- Requerir responsable de recepción: **Sí**
- Actualizar inventario al confirmar recepción: **Sí**

### Botones oficiales de la categoría
- **Guardar reglas de inventario**
- **Restablecer reglas**

---

# 14. Categoría 6: Venta, caja y comprobantes

## Función
Definir parámetros globales del flujo comercial y económico.

## Contenedor principal sugerido
**ScrollPane** con **VBox** y bloques en **GridPane**.

### Estructura interna
1. Numeración
2. Pago y abonos
3. Comprobantes

## 14.1. Bloque: Numeración

### Campos visibles
- Prefijo de orden óptica
- Prefijo de comprobante
- Secuencia inicial

### Seeds
- Prefijo de orden óptica: **OV**
- Prefijo de comprobante: **FAC**
- Secuencia inicial: **1000**

## 14.2. Bloque: Pago y abonos

### Campos visibles
- Permitir abonos
- Descuento máximo permitido
- Permitir saldo pendiente

### Seeds
- Permitir abonos: **Sí**
- Descuento máximo permitido: **15%**
- Permitir saldo pendiente: **Sí**

## 14.3. Bloque: Comprobantes

### Campos visibles
- Mostrar dirección en comprobante
- Mostrar mensaje institucional
- Texto final del comprobante

### Seeds
- Mostrar dirección en comprobante: **Sí**
- Mostrar mensaje institucional: **Sí**
- Texto final del comprobante: **Gracias por confiar en nuestra óptica**

### Botones oficiales de la categoría
- **Guardar reglas comerciales**
- **Vista previa de comprobante**

---

# 15. Categoría 7: Agenda, seguimiento y comunicación

## Función
Definir parámetros operativos relacionados con citas, recalls, recordatorios y contacto con clientes.

## Contenedor principal sugerido
**ScrollPane** con **VBox** y bloques en **GridPane**.

### Estructura interna
1. Agenda
2. Recall
3. Comunicación

## 15.1. Bloque: Agenda

### Campos visibles
- Duración estándar de cita
- Tipos de cita habilitados
- Permitir reprogramación rápida

### Seeds
- Duración estándar de cita: **30 minutos**
- Tipos de cita habilitados: **Configurables**
- Permitir reprogramación rápida: **Sí**

## 15.2. Bloque: Recall

### Campos visibles
- Activar recall anual
- Días de anticipación
- Crear seguimiento automático

### Seeds
- Activar recall anual: **Sí**
- Días de anticipación: **30**
- Crear seguimiento automático: **Sí**

## 15.3. Bloque: Comunicación

### Campos visibles
- Canal preferido por defecto
- Plantilla de trabajo listo
- Plantilla de saldo pendiente

### Seeds
- Canal preferido por defecto: **WhatsApp**
- Plantilla de trabajo listo: **Su trabajo está listo para retirar**
- Plantilla de saldo pendiente: **Su orden mantiene un saldo pendiente**

### Botones oficiales de la categoría
- **Guardar comunicación**
- **Probar plantilla**

---

# 16. Categoría 8: Laboratorio y entregas

## Función
Definir tiempos promesa, estados y reglas de validación del flujo de trabajos.

## Contenedor principal sugerido
**ScrollPane** con **VBox** y bloques en **GridPane**.

### Estructura interna
1. Laboratorios
2. Estados de trabajo
3. Entrega

## 16.1. Bloque: Laboratorios

### Campos visibles
- Laboratorios habilitados
- Tiempo promesa estándar
- Permitir laboratorio externo

### Seeds
- Laboratorios habilitados: **Óptica Interna, LabVision Externo**
- Tiempo promesa estándar: **3 días**
- Permitir laboratorio externo: **Sí**

## 16.2. Bloque: Estados de trabajo

### Campos visibles
- Estados activos de laboratorio
- Estados activos de entrega

### Seeds
- Estados activos de laboratorio: **Configurables**
- Estados activos de entrega: **Configurables**

## 16.3. Bloque: Entrega

### Campos visibles
- Requerir validación previa
- Bloquear entrega con saldo
- Recordar trabajos no retirados

### Seeds
- Requerir validación previa: **Sí**
- Bloquear entrega con saldo: **Sí**
- Recordar trabajos no retirados: **Sí**

### Botones oficiales de la categoría
- **Guardar flujo de trabajos**
- **Restablecer tiempos promesa**

---

# 17. Categoría 9: Apariencia y experiencia de uso

## Función
Definir parámetros visuales básicos y experiencia general de la aplicación.

## Contenedor principal sugerido
**ScrollPane** con **VBox** y bloques en **GridPane**.

### Estructura interna
1. Apariencia general
2. Colores base
3. Experiencia visual

## 17.1. Bloque: Apariencia general

### Campos visibles
- Modo de apariencia
- Seguir tema del sistema
- Densidad visual

### Seeds
- Modo de apariencia: **Claro**
- Seguir tema del sistema: **No**
- Densidad visual: **Normal**

### Valores sugeridos
- Modo de apariencia: Claro, Oscuro, Seguir sistema
- Densidad visual: Compacta, Normal, Cómoda

## 17.2. Bloque: Colores base

### Campos visibles
- Color principal
- Color secundario
- Color de alerta
- Color de éxito

### Seeds
- Color principal: **Azul petróleo**
- Color secundario: **Azul grisáceo**
- Color de alerta: **Ámbar sobrio**
- Color de éxito: **Verde moderado**

### Interpretación visual
Este bloque puede mostrarse como pequeñas muestras de color con **Region** o superficies simples dentro de un **HBox**, acompañadas de su label.

## 17.3. Bloque: Experiencia visual

### Campos visibles
- Mostrar íconos en navegación
- Mostrar tooltips contextuales
- Confirmar acciones críticas

### Seeds
- Mostrar íconos en navegación: **Sí**
- Mostrar tooltips contextuales: **Sí**
- Confirmar acciones críticas: **Sí**

### Botones oficiales de la categoría
- **Guardar apariencia**
- **Restablecer tema sugerido**
- **Vista previa visual**

---

# 18. Formulario conceptual: Búsqueda de ajuste

## Función
Permitir localizar rápidamente una configuración sin recorrer todas las categorías.

## Contenedor sugerido
No requiere una ventana aparte. Puede resolverse dentro del mismo encabezado y, al seleccionar un resultado, enfocar la categoría correspondiente.

### Resultados posibles de ejemplo
- Stock mínimo por defecto → Inventario y abastecimiento
- Plantilla de trabajo listo → Agenda, seguimiento y comunicación
- Prefijo de comprobante → Venta, caja y comprobantes
- Modo de apariencia → Apariencia y experiencia de uso

---

# 19. Seed data oficial del módulo Configuración

## Sucursales
- Matriz Centro
- Norte Mall

## Usuarios de ejemplo
- admin@opticamanager.local
- recepcion@opticamanager.local
- ventas@opticamanager.local
- tecnico@opticamanager.local

## Laboratorios
- Óptica Interna
- LabVision Externo

## Canales de comunicación
- SMS
- WhatsApp
- Llamada
- Correo

## Colores base sugeridos
- Azul petróleo
- Azul grisáceo
- Ámbar sobrio
- Verde moderado

---

# 20. Textos oficiales del módulo Configuración

## Títulos y labels principales
- Configuración
- Parámetros generales, operación de la óptica, catálogos y experiencia del sistema
- Buscar ajuste
- General del negocio
- Sucursales y operación
- Usuarios, roles y permisos
- Catálogos maestros
- Inventario y abastecimiento
- Venta, caja y comprobantes
- Agenda, seguimiento y comunicación
- Laboratorio y entregas
- Apariencia y experiencia de uso
- Nombre comercial
- Razón social
- RUC o identificación
- Teléfono general
- Correo general
- Dirección principal
- Ciudad
- Sucursal predeterminada
- Moneda
- Zona horaria
- Modo de apariencia
- Color principal
- Color secundario
- Color de alerta
- Color de éxito

## Botones oficiales
- Restablecer vista
- Exportar parámetros
- Guardar cambios
- Limpiar búsqueda
- Guardar categoría
- Restablecer cambios
- Ir a módulo relacionado
- Guardar datos del negocio
- Restablecer valores
- Guardar sucursal
- Nueva sucursal
- Desactivar sucursal
- Guardar permisos
- Nuevo usuario
- Desactivar usuario
- Guardar catálogo
- Nuevo valor
- Desactivar valor
- Guardar reglas de inventario
- Guardar reglas comerciales
- Vista previa de comprobante
- Guardar comunicación
- Probar plantilla
- Guardar flujo de trabajos
- Restablecer tiempos promesa
- Guardar apariencia
- Restablecer tema sugerido
- Vista previa visual

## Placeholders
- Sucursal, comprobante, WhatsApp, stock mínimo, colores...

---

# 21. Reglas visuales específicas del módulo Configuración

- la navegación de categorías debe sentirse estable y clara
- el panel central debe mostrar configuraciones agrupadas, no listas caóticas
- el panel derecho debe explicar impacto y alcance, no solo repetir labels
- General del negocio debe verse institucional
- Sucursales y operación debe verse operativa y concreta
- Usuarios, roles y permisos debe verse sobrio y muy controlado
- Catálogos maestros debe sentirse modular
- Inventario, venta, agenda y laboratorio deben verse como reglas del negocio óptico
- Apariencia debe verse ligera y no protagonista
- no abusar de interruptores o controles visuales innecesarios
- la prioridad debe ser orden, impacto y comprensión del ajuste

---

# 22. Relación del módulo Configuración con otros módulos

Configuración debe conectarse con:
- Sucursales, porque define sedes y operación base
- Usuarios y roles, porque el acceso parte de aquí aunque luego exista módulo propio
- Inventario, porque algunas reglas globales de stock nacen aquí
- Caja, porque numeraciones, abonos y comprobantes se parametrizan aquí
- Agenda y Seguimiento, porque tipos de cita, recalls y mensajes parten de esta configuración
- Laboratorio y Entregas, porque tiempos promesa y estados se apoyan aquí
- Inicio, porque parte de la identidad institucional y visual se refleja desde la configuración general

---

# 23. Cierre del módulo Configuración

Este módulo debe transmitir que el sistema puede adaptarse al modo real de trabajo de la óptica. No es una pantalla de preferencias superficial, sino el lugar donde se define cómo opera el negocio, cómo se organizan sus sedes, qué parámetros usa el sistema y cómo se comportan los módulos principales. La apariencia puede ser un complemento elegante, pero el corazón del módulo está en ordenar la operación.

La complejidad correcta del módulo está en que cada categoría resuelve un grupo claro de parámetros sin convertir la experiencia en un panel técnico frío o confuso.