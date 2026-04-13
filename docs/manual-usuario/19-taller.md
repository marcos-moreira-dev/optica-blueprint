# Taller de Reparaciones

## ¿Para qué sirve este módulo?

El módulo **Taller de Reparaciones** gestiona los trabajos técnicos que no son fabricación de lentes nuevos: ajustes de montura, soldaduras, cambios de plaquetas, reparaciones de bisagras y cualquier servicio técnico que necesite un paciente en sus lentes existentes.

## ¿Quién usa esta pantalla?

- **Técnicos ópticos:** para recibir, diagnosticar y reparar trabajos.
- **Recepcionistas:** para registrar el ingreso de trabajos al taller y notificar al paciente.
- **Administradores:** para monitorear el rendimiento del taller.

---

## Sub-vistas del módulo

El módulo tiene 7 sub-vistas accesibles desde los botones superiores:

| Sub-vista | Qué muestra |
|-----------|-------------|
| **Bandeja de ingresos** | Trabajos recibidos en el taller pendientes de diagnóstico. |
| **Diagnósticos técnicos** | Evaluación del técnico sobre qué necesita cada trabajo. |
| **Reparaciones y ajustes** | Trabajos en proceso de reparación. |
| **Piezas y repuestos** | Insumos utilizados en las reparaciones. |
| **Envíos a terceros** | Trabajos que se envían a laboratorio externo para reparación especializada. |
| **Entregas técnicas** | Trabajos reparados listos para devolver al paciente. |
| **Histórico** | Registro de todas las reparaciones realizadas. |

---

## Bandeja de ingresos

### Trabajos que llegan al taller

Cuando un paciente trae sus lentes para reparación o ajuste:

1. Vaya a **Bandeja de ingresos**
2. Haga clic en **Nuevo ingreso**
3. Seleccione al paciente (o cree uno nuevo si no está registrado)
4. Describa brevemente el problema que refiere el paciente (ej: "Montura desnivelada", "Plaqueta rota")
5. Asigne una prioridad: Normal, Urgente
6. Guarde

### Qué muestra la bandeja
| Columna | Descripción |
|---------|-------------|
| **Número de trabajo** | Referencia (ej: TR-00034) |
| **Paciente** | Quién trajo los lentes |
| **Descripción** | Problema referido por el paciente |
| **Fecha de ingreso** | Cuándo entró al taller |
| **Prioridad** | Normal, Urgente |
| **Estado** | Pendiente, En diagnóstico, En reparación, Listo, Entregado |

---

## Diagnósticos técnicos

### Evaluación del trabajo

El técnico óptico evalúa cada trabajo ingresado y determina:

#### Diagnóstico
| Campo | Descripción |
|-------|-------------|
| **Problema detectado** | Qué tiene realmente el lente/montura (puede ser diferente al referido por el paciente) |
| **Tipo de reparación** | Ajuste, Soldadura, Cambio de pieza, Revisión |
| **Piezas necesarias** | Qué repuestos necesita (plaquetas, tornillos, bisagras, etc.) |
| **Tiempo estimado** | Cuánto tomará la reparación |
| **Costo estimado** | Cuánto costará al paciente |
| **Requiere envío externo** | Si no puede repararse en el taller interno |

### Cómo registrar un diagnóstico
1. Vaya a **Diagnósticos técnicos**
2. Seleccione el trabajo pendiente
3. Complete el formulario de diagnóstico
4. Si requiere piezas, seleccione del inventario de repuestos
5. Si debe enviarse a externo, vaya a **Envíos a terceros**
6. Si se repara aquí, avance a **Reparaciones y ajustes**

---

## Reparaciones y ajustes

### Trabajo en proceso

Aquí el técnico realiza la reparación:

#### Tipos de reparación comunes
| Tipo | Descripción |
|------|-------------|
| **Ajuste de montura** | Nivelación, apriete de bisagras, ajuste de patillas |
| **Cambio de plaquetas** | Reemplazo de almohadillas nasales deterioradas |
| **Cambio de tornillos** | Reemplazo de tornillos perdidos o dañados |
| **Soldadura** | Reparación de marco partido (metálico) |
| **Cambio de bisagra** | Reemplazo de bisagra rota |
| **Revisión general** | Evaluación completa y ajuste de todos los componentes |

#### Registro de la reparación
1. Vaya a **Reparaciones y ajustes**
2. Seleccione el trabajo
3. Registre las acciones realizadas:
   - Qué se hizo
   - Qué piezas se usaron
   - Cuánto tiempo tomó
4. Marque como **Listo** cuando termine
5. El trabajo pasa a **Entregas técnicas**

---

## Piezas y repuestos

### Inventario del taller

El taller mantiene su propio inventario de repuestos:

| Repuesto | Descripción | Stock |
|----------|-------------|-------|
| **Plaquetas estándar** | Almohadillas nasales universales | 50 pares |
| **Plaquetas de silicona** | Almohadillas de silicona hipoalergénicas | 30 pares |
| **Tornillos estándar** | Tornillos para bisagras de montura metálica | 100 unidades |
| **Tornillos de seguridad** | Tornillos con tuerca de seguridad | 50 unidades |
| **Bisagras universales** | Bisagras de repuesto | 20 unidades |
| **Patinas (puntas de patilla)** | Protectores de patilla | 40 unidades |

### Qué pasa cuando se usa un repuesto
- Se descuenta automáticamente del stock del taller
- Se registra en el trabajo de reparación como pieza utilizada
- Si el stock está bajo, se alerta para reposición

---

## Envíos a terceros

### Reparaciones especializadas

Algunas reparaciones requieren un laboratorio externo:

#### Cuándo enviar a externo
- Soldaduras complejas que requieren equipo especializado
- Reparaciones de monturas de titanio
- Cambios de componentes que no se tienen en stock
- Lentes rayados que requieren re-pulido profesional

#### Cómo enviar a externo
1. Vaya a **Envíos a terceros**
2. Seleccione el trabajo
3. Seleccione el laboratorio externo (proveedor de reparación)
4. Describa el trabajo a realizar
5. Registre la fecha de envío
6. El trabajo cambia de estado a **Enviado**

#### Recepción del trabajo reparado
1. Cuando el laboratorio externo devuelve el trabajo
2. Vaya a **Envíos a terceros**
3. Seleccione el trabajo recibido
4. Verifique que la reparación sea correcta
5. Registre la recepción
6. El trabajo pasa a **Entregas técnicas**

---

## Entregas técnicas

### Devolver el trabajo reparado al paciente

Cuando la reparación está lista:

1. Vaya a **Entregas técnicas**
2. Busque al paciente por nombre o número de trabajo
3. Verifique que la reparación esté completa
4. Contacte al paciente para que retire
5. Cuando el paciente llega, entregue el trabajo
6. Registre la entrega:
   - Quién recibe
   - Fecha
   - Observaciones (ej: "Se le indicó cómo cuidar las plaquetas nuevas")
7. El trabajo cambia a estado **Entregado**

### Si el paciente no viene a recoger
- Después de 7 días: primer recordatorio
- Después de 15 días: segundo recordatorio
- Después de 30 días: notificar al administrador

---

## Casos de uso comunes

### "Un paciente trae sus lentes porque la montura está desnivelada"
1. Vaya a **Bandeja de ingresos**
2. Haga clic en **Nuevo ingreso**
3. Seleccione al paciente
4. Describa: "Montura desnivelada, patilla izquierda más baja"
5. Guarde

### "El técnico evaluó y necesita cambiar plaquetas"
1. Vaya a **Diagnósticos técnicos**
2. Seleccione el trabajo
3. Diagnóstico: "Plaquetas endurecidas y deterioradas"
4. Tipo: "Cambio de plaquetas"
5. Piezas: "Plaquetas estándar x1 par"
6. Tiempo estimado: 10 minutos
7. Costo: $5.00
8. Avance a **Reparaciones y ajustes**

### "La reparación necesita soldadura que no podemos hacer aquí"
1. Vaya a **Diagnósticos técnicos**
2. Marque **Requiere envío externo**
3. Vaya a **Envíos a terceros**
4. Seleccione el laboratorio externo
5. Envíe el trabajo

### "El paciente viene a recoger su montura reparada"
1. Vaya a **Entregas técnicas**
2. Busque al paciente
3. Verifique la reparación
4. Entregue al paciente
5. Registre la entrega

---

## Estados de los trabajos del taller

| Estado | Qué significa |
|--------|---------------|
| **Pendiente** | Ingresado pero aún no diagnosticado |
| **En diagnóstico** | El técnico está evaluando |
| **En reparación** | Diagnóstico completado, se está reparando |
| **Enviado a externo** | Fue enviado a laboratorio especializado |
| **Listo** | Reparación completada, esperando al paciente |
| **Entregado** | Devuelto al paciente |

---

## Notas importantes
- Las reparaciones simples (ajustes, cambio de plaquetas) pueden hacerse en el momento.
- Si la reparación tiene costo, infórmele al paciente antes de proceder.
- Los repuestos del taller se descuentan automáticamente; mantenga el stock.
- Si una reparación no puede hacerse internamente, envíe a externo sin demora.
- El módulo **Notificaciones** puede avisar al paciente cuando su reparación está lista.
- El histórico de reparaciones ayuda a identificar problemas recurrentes en monturas.
