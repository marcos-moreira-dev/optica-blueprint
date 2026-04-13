# Seguros y Coberturas

## ¿Para qué sirve este módulo?

El módulo **Seguros y Coberturas** gestiona los planes de cobertura visual que ofrecen aseguradoras o convenios empresariales. Aquí usted puede verificar si un paciente tiene cobertura, qué beneficios le aplican, autorizar servicios y procesar reclamaciones ante la aseguradora.

## ¿Quién usa esta pantalla?

- **Recepcionistas:** para verificar cobertura al agendar citas.
- **Asesores de venta:** para aplicar descuentos de cobertura en las ventas.
- **Administradores:** para gestionar convenios con aseguradoras y empresas.

---

## Sub-vistas del módulo

El módulo tiene 7 sub-vistas accesibles desde los botones superiores:

| Sub-vista | Qué muestra |
|-----------|-------------|
| **Verificación de cobertura** | Consultar si un paciente tiene seguro activo y qué cubre. |
| **Planes y convenios** | Catálogo de planes de cobertura y acuerdos con aseguradoras. |
| **Autorizaciones** | Solicitudes de aprobación previa a la aseguradora. |
| **Reclamaciones** | Solicitudes de reembolso enviadas a la aseguradora. |
| **Respuestas y observaciones** | Respuestas de la aseguradora sobre reclamaciones. |
| **Cobertura aplicada a venta** | Seguros aplicados en ventas concretas. |
| **Histórico** | Registro completo de todas las gestiones de cobertura. |

---

## Verificación de cobertura

### Consultar si un paciente tiene seguro

Cuando un paciente llega a la óptica:

1. Vaya a **Verificación de cobertura**
2. Ingrese el documento de identidad o número de póliza del paciente
3. El sistema muestra:
   - **Aseguradora:** Nombre de la compañía
   - **Plan:** Tipo de cobertura (Básico, Estándar, Premium)
   - **Vigencia:** Hasta cuándo es válida
   - **Beneficios incluidos:**
     - Examen visual: cubierto o no
     - Montura: monto máximo cubierto
     - Lentes: tipo y monto máximo
     - Tratamientos: cuáles cubre
   - **Copago:** Cuánto paga el paciente directamente
   - **Deducible:** Monto que el paciente debe pagar antes de que aplique la cobertura

### Resultado de la verificación
| Estado | Qué significa |
|--------|---------------|
| **Activa** | El seguro está vigente y el paciente puede usarlo |
| **Por vencer** | El seguro vence próximamente |
| **Vencida** | El seguro ya no es válido |
| **No encontrada** | No se encontró póliza con ese documento |

---

## Planes y convenios

### Catálogo de planes de cobertura

Aquí se configuran los diferentes planes que ofrece cada aseguradora o convenio empresarial:

#### Datos de un plan
| Campo | Descripción |
|-------|-------------|
| **Nombre del plan** | Ej: "Visual Plus", "Corporativo ABC" |
| **Aseguradora/Empresa** | Quién ofrece el plan |
| **Tipo** | Seguro visual, Convenio empresarial, Plan gubernamental |
| **Cobertura de examen** | Cubre 100%, copago fijo, o no cubre |
| **Cobertura de montura** | Monto máximo cubierto (ej: hasta $80) |
| **Cobertura de lentes** | Tipo (monofocal, progresivo) y monto máximo |
| **Tratamientos cubiertos** | Cuáles incluye |
| **Vigencia** | Período de validez del plan |
| **Copago** | Porcentaje o monto que paga el paciente |

---

## Autorizaciones

### Aprobación previa de la aseguradora

Algunos servicios requieren autorización previa de la aseguradora antes de prestarse:

#### Cuándo necesita autorización
- Lentes de alto índice (high-index)
- Tratamientos especiales (fotocromáticos, progresivos premium)
- Monturas de gama alta que exceden el monto estándar cubierto

#### Cómo solicitar una autorización
1. Vaya a **Autorizaciones**
2. Haga clic en **Nueva autorización**
3. Seleccione al paciente y su plan de cobertura
4. Describa el servicio solicitado
5. Incluya la receta y justificación clínica
6. Envíe a la aseguradora
7. Espere la respuesta (generalmente 24-48 horas)

### Estados de la autorización
| Estado | Qué significa |
|--------|---------------|
| **Enviada** | La solicitud está en manos de la aseguradora |
| **Aprobada** | La aseguradora autorizó el servicio |
| **Observada** | La aseguradora pide más información o modifica la cobertura |
| **Rechazada** | La aseguradora no autoriza el servicio |

---

## Reclamaciones

### Solicitar reembolso a la aseguradora

Después de atender a un paciente con cobertura, envíe la reclamación:

1. Vaya a **Reclamaciones**
2. Haga clic en **Nueva reclamación**
3. Seleccione la venta u orden asociada
4. Adjunte:
   - Receta oftalmológica
   - Comprobante de pago
   - Detalle de productos entregados
   - Autorización previa (si aplica)
5. Envíe a la aseguradora

### Seguimiento de la reclamación
| Estado | Qué significa |
|--------|---------------|
| **Enviada** | La reclamación está con la aseguradora |
| **En revisión** | La aseguradora está evaluando |
| **Aprobada** | La aseguradora pagará el monto reclamado |
| **Parcialmente aprobada** | Aprobaron menos de lo reclamado |
| **Rechazada** | No procederá el pago |

### Si la aseguradora rechaza
- Revise el motivo del rechazo
- Si es por documentación faltante, complete y reenvíe
- Si es por falta de cobertura, informe al paciente que debe cubrir el total
- Si no está de acuerdo, puede apelar la decisión

---

## Respuestas y observaciones

### Comunicaciones de la aseguradora

Aquí llegan las respuestas de la aseguradora sobre autorizaciones y reclamaciones:

| Tipo | Descripción |
|------|-------------|
| **Aprobación** | La aseguradora autorizó o pagó |
| **Observación** | Piden más información o ajustan el monto |
| **Rechazo** | No autorizan o no pagan |
| **Contraoferta** | Ofrecen menos de lo solicitado |

### Qué hacer con cada respuesta
- **Aprobación:** Proceda normalmente
- **Observación:** Complete la información solicitada y reenvíe
- **Rechazo:** Informe al paciente o apele si considera que es injusto
- **Contraoferta:** Decida si acepta o apela

---

## Cobertura aplicada a venta

### Seguros usados en ventas concretas

Muestra las ventas donde se aplicó cobertura de seguro:

| Columna | Descripción |
|---------|-------------|
| **Orden** | Número de venta |
| **Paciente** | Quién recibió el servicio |
| **Aseguradora** | Compañía de seguros |
| **Plan** | Tipo de cobertura |
| **Monto total** | Valor de la venta |
| **Cubierto** | Cuánto paga el seguro |
| **Copago** | Cuánto paga el paciente |
| **Estado de reclamación** | Si ya se reclamó a la aseguradora |

---

## Casos de uso comunes

### "Un paciente dice que tiene seguro visual"
1. Vaya a **Verificación de cobertura**
2. Ingrese su documento de identidad
3. Si tiene cobertura activa, revise qué beneficios incluye
4. Aplique los beneficios correspondientes en la venta

### "El paciente quiere lentes progresivos pero su plan solo cubre monofocales"
1. Verifique la cobertura
2. Si el plan no cubre progresivos, informe al paciente
3. Ofrezca pagar la diferencia: el seguro cubre el monofocal y el paciente el extra
4. O solicite una **autorización especial** a la aseguradora con justificación clínica

### "Necesito enviar una reclamación a la aseguradora"
1. Vaya a **Reclamaciones**
2. Seleccione la venta del paciente
3. Adjunte los documentos requeridos
4. Envíe y espere la respuesta

### "La aseguradora rechazó una reclamación"
1. Revise el motivo del rechazo en **Respuestas y observaciones**
2. Si es corregible (documentación faltante), complete y reenvíe
3. Si no es corregible, informe al paciente que debe cubrir el monto rechazado

---

## Notas importantes
- Siempre verifique la cobertura antes de aplicar un descuento por seguro.
- Las autorizaciones previas pueden tomar 24-48 horas; planifique con anticipación.
- Guarde copia de todas las reclamaciones enviadas como respaldo.
- El paciente es responsable del copago aunque el seguro cubra el resto.
- Los convenios empresariales no son seguros médicos; funcionan como descuentos acordados.
