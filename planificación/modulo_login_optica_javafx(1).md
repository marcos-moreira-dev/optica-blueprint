# Lienzo del login para sistema de óptica en JavaFX

## 1. Propósito del login

Este login no debe sentirse como una pantalla técnica o fría. Debe funcionar como una entrada visual seria al sistema, dando la impresión de que la óptica trabaja con una plataforma ordenada, moderna y profesional.

Como este login será usado con fines demostrativos y propagandísticos, se asume que los campos de acceso ya pueden aparecer precargados con información de ejemplo. La meta no es reforzar seguridad real en esta maqueta, sino permitir una entrada rápida y visualmente convincente al sistema.

---

## 2. Objetivo visual

La pantalla debe transmitir tres cosas desde el primer vistazo:

- formalidad
- limpieza
- control

No debe parecer una app juvenil ni una pantalla genérica improvisada. Debe verse como el acceso a un sistema empresarial de una óptica con criterio clínico y comercial.

---

## 3. Estructura general de la ventana

## Contenedor raíz sugerido
**BorderPane** o **HBox** principal.

## Decisión recomendada
Para que se vea más propagandístico y mejor balanceado, conviene usar un **HBox** raíz dividido en dos zonas verticales bien marcadas.

### Zona izquierda
Bloque visual institucional.

### Zona derecha
Bloque del formulario de acceso.

## Proporción recomendada
- izquierda: 45%
- derecha: 55%

Esta composición ayuda a que el login no se vea como un formulario perdido en medio de la pantalla.

---

## 4. Zona izquierda: bloque institucional

## Función
No sirve para meter mucha información. Sirve para dar identidad, contexto y una sensación de marca.

## Contenedor sugerido
**VBox** centrado verticalmente.

## Elementos visibles
- logo o isotipo de la óptica
- nombre del sistema
- subtítulo corto
- una frase sobria de apoyo
- uno o dos íconos grandes y discretos relacionados con óptica

## Textos exactos sugeridos
### Nombre principal
**Óptica Manager**

### Subtítulo
**Sistema integral para atención, ventas y control óptico**

### Frase de apoyo
**Gestione clientes, recetas, órdenes, inventario y entregas desde una sola plataforma**

## Íconos sugeridos
- ícono de gafas
- ícono de ojo
- ícono de panel o dashboard

## Regla visual
No debe parecer un cartel publicitario. Debe verse limpio, con aire, muy sobrio y con pocos elementos.

---

## 5. Zona derecha: formulario de acceso

## Función
Permitir una entrada rápida, clara y elegante al sistema.

## Contenedor sugerido
**StackPane** con una superficie interna centrada, o directamente un **VBox** bien contenido.

## Decisión recomendada
Usar un **StackPane** como región base y dentro de él una tarjeta sobria hecha con un **VBox**.

## Estructura interna del formulario
1. encabezado del formulario
2. campos de acceso
3. ayuda visual breve
4. botón principal
5. acceso rápido o perfil de demostración

---

## 6. Encabezado del formulario

## Contenedor sugerido
**VBox**

## Elementos
- título del formulario
- subtítulo breve

## Textos exactos
### Título
**Iniciar sesión**

### Subtítulo
**Acceda al panel operativo de la óptica**

## Regla visual
El título debe verse claro y firme. El subtítulo debe apoyar, no competir.

---

## 7. Campos del formulario

## Contenedor sugerido
**GridPane** o **VBox** con buena separación vertical.

## Campos oficiales
### Campo 1
- Control: **TextField**
- Label exacto: **Usuario**
- Texto precargado: **admin@opticamanager.local**
- Tooltip exacto: **Usuario de demostración para ingresar al sistema**

### Campo 2
- Control: **PasswordField** o **TextField** solo si quieres que visualmente se vea cargada la contraseña de forma directa en la demo
- Label exacto: **Contraseña**
- Texto precargado: **Optica2026**
- Tooltip exacto: **Contraseña de demostración precargada para acceso rápido**

## Nota práctica para la maqueta
Si quieres que el demo sea más claro visualmente, puedes usar un **TextField** estilizado como contraseña visible de demostración. Si quieres que parezca más realista, usa **PasswordField** ya rellenado.

## Recomendación para esta maqueta
Usar **PasswordField** precargado para mantener la apariencia seria.

---

## 8. Ayuda visual breve debajo de los campos

## Contenedor sugerido
**VBox** pequeño

## Texto exacto sugerido
**Perfil cargado para demostración. Puede ingresar directamente al sistema.**

## Tooltip
**Este acceso está configurado solo para fines de demostración visual**

## Regla visual
Debe sentirse como una ayuda discreta, no como una advertencia técnica.

---

## 9. Botón principal del login

## Control sugerido
**Button**

## Texto exacto
**Ingresar al sistema**

## Tooltip exacto
**Abrir el panel principal del sistema con el perfil de demostración**

## Regla visual
Este debe ser el botón más importante de toda la pantalla. No debe haber otro botón compitiendo con él.

---

## 10. Acceso rápido o perfil visible

## Función
Reforzar que la pantalla es una demo seria y que el sistema ya está “listo para entrar”.

## Contenedor sugerido
**HBox** o **VBox** pequeño debajo del botón principal.

## Texto exacto sugerido
**Perfil activo: Administrador general**

## Tooltip exacto
**Perfil de demostración con acceso a todos los módulos visibles**

## Opción adicional
Podría mostrarse una pequeña línea secundaria:
**Sucursal inicial: Matriz Centro**

---

## 11. Elementos opcionales que sí convienen

### Opción 1
Checkbox discreto:
- Control: **CheckBox**
- Texto exacto: **Recordar este acceso**
- Tooltip exacto: **Mantener este acceso precargado en la demostración**

### Opción 2
Link o botón muy discreto:
- Control: **Hyperlink** o **Button** secundario pequeño
- Texto exacto: **Cambiar perfil de demostración**
- Tooltip exacto: **Usar otro perfil precargado para recorrer el sistema**

### Opción 3
Label informativo muy pequeño:
**Versión demostrativa 1.0**

Estos elementos son opcionales. No hay que saturar el login.

---

## 12. Perfiles de demostración sugeridos

Aunque el login muestre por defecto un solo acceso, sí conviene definir otros perfiles de semilla por si luego quieres simular otro tipo de usuario.

### Perfil 1
- Usuario: **admin@opticamanager.local**
- Contraseña: **Optica2026**
- Rol visible: **Administrador general**

### Perfil 2
- Usuario: **recepcion@opticamanager.local**
- Contraseña: **Optica2026**
- Rol visible: **Recepción**

### Perfil 3
- Usuario: **ventas@opticamanager.local**
- Contraseña: **Optica2026**
- Rol visible: **Asesor de ventas**

### Perfil 4
- Usuario: **tecnico@opticamanager.local**
- Contraseña: **Optica2026**
- Rol visible: **Técnico óptico**

## Regla
Solo uno debe mostrarse precargado por defecto. El resto quedan como semillas conceptuales.

---

## 13. Arquitectura limpia de GUI para el login

Este login debe ser muy simple a nivel estructural.

## Vista principal
**LoginView**

## Sub-bloques internos recomendados
- **LoginBrandPanel** conceptual para la parte izquierda
- **LoginFormPanel** conceptual para la parte derecha

## Regla arquitectónica
No hace falta fragmentarlo en demasiadas piezas. Basta con dos paneles muy bien organizados.

## Transición esperada
Al pulsar **Ingresar al sistema**, la vista LoginView debe ser reemplazada por la vista principal del sistema, probablemente el módulo **Inicio**.

---

## 14. Seeds oficiales del login

## Sistema
- Nombre: **Óptica Manager**
- Subtítulo: **Sistema integral para atención, ventas y control óptico**

## Perfil precargado principal
- Usuario: **admin@opticamanager.local**
- Contraseña: **Optica2026**
- Rol: **Administrador general**
- Sucursal inicial: **Matriz Centro**

---

## 15. Textos oficiales del login

## Títulos y labels
- Óptica Manager
- Sistema integral para atención, ventas y control óptico
- Gestione clientes, recetas, órdenes, inventario y entregas desde una sola plataforma
- Iniciar sesión
- Acceda al panel operativo de la óptica
- Usuario
- Contraseña
- Perfil activo: Administrador general
- Sucursal inicial: Matriz Centro
- Versión demostrativa 1.0

## Botones y acciones
- Ingresar al sistema
- Cambiar perfil de demostración
- Recordar este acceso

## Ayuda breve
- Perfil cargado para demostración. Puede ingresar directamente al sistema.

---

## 16. Reglas visuales específicas del login

- el formulario debe verse centrado y estable
- la parte izquierda debe dar identidad sin sobrecargar
- el botón principal debe tener máxima jerarquía
- los campos precargados deben verse naturales, no forzados
- no usar demasiados adornos
- no usar imágenes de personas
- apoyar la identidad con íconos sobrios
- no abusar de bordes redondeados
- mantener la misma familia visual del resto del sistema

---

## 17. Cierre del login

Este login debe verse como una entrada elegante y lista para demostración. No necesita seguridad real ni complejidad técnica visible. Su función es dejar claro que el sistema ya está preparado para usarse y que la óptica entra a una plataforma seria, ordenada y visualmente coherente.

La pantalla debe permitir que la demostración comience rápido, con usuario y contraseña ya cargados, sin romper la ilusión de un sistema profesional.

