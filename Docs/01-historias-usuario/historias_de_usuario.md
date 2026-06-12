# 📋 Historias de Usuario

---

## HU-01 — Registro de usuario

**Como** visitante, **quiero** registrarme en el sistema con correo y contraseña, **para** acceder a las funcionalidades disponibles.

**Criterios de aceptación:**

- El correo debe ser único en el sistema.
- La contraseña debe tener mínimo 8 caracteres.
- La contraseña se almacena cifrada con BCrypt.
- El rol asignado por defecto es `CLIENTE`.
- Se muestra un mensaje de confirmación.

---

## HU-02 — Inicio de sesión

**Como** usuario registrado, **quiero** iniciar sesión en el sistema, **para** acceder a mi cuenta.

**Criterios de aceptación:**

- El usuario debe ingresar correo y contraseña.
- El sistema valida las credenciales.
- El sistema permite el acceso si los datos son correctos.
- Se muestra un mensaje de error si las credenciales son inválidas.
- El usuario es redirigido según su rol.

---

## HU-03 — Cierre de sesión

**Como** usuario autenticado, **quiero** cerrar sesión, **para** proteger mi información.

**Criterios de aceptación:**

- El sistema muestra la opción de cerrar sesión.
- La sesión se cierra correctamente.
- El usuario es redirigido a la página principal.
- No se puede acceder a páginas protegidas después del cierre.

---

## HU-04 — Gestión de usuarios

**Como** administrador, **quiero** visualizar los usuarios registrados, **para** administrar sus cuentas.

**Criterios de aceptación:**

- El sistema muestra una lista de usuarios.
- Se visualiza nombre, correo y rol.
- Solo los administradores tienen acceso.
- La información se carga correctamente.

---

## HU-05 — Asignación de roles

**Como** administrador, **quiero** asignar roles a los usuarios, **para** controlar los permisos del sistema.

**Criterios de aceptación:**

- El sistema permite seleccionar un rol.
- Solo los administradores pueden modificar roles.
- Los cambios se guardan correctamente.
- Se muestra un mensaje de confirmación.

---

## HU-06 — Registro de propiedades

**Como** agente inmobiliario, **quiero** registrar propiedades, **para** ofrecerlas a los clientes.

**Criterios de aceptación:**

- El sistema muestra un formulario de registro.
- Se pueden ingresar los datos de la propiedad.
- Los campos obligatorios son validados.
- La propiedad queda almacenada en la base de datos.

---

## HU-07 — Consulta de propiedades

**Como** agente inmobiliario, **quiero** visualizar las propiedades registradas, **para** administrarlas fácilmente.

**Criterios de aceptación:**

- El sistema muestra una lista de propiedades.
- Se visualizan los datos principales.
- La información se actualiza automáticamente.
- Solo usuarios autorizados pueden acceder.

---

## HU-08 — Edición de propiedades

**Como** agente inmobiliario, **quiero** modificar la información de una propiedad, **para** mantener los datos actualizados.

**Criterios de aceptación:**

- El sistema permite editar los datos registrados.
- Los cambios son validados.
- La información se actualiza correctamente.
- Se muestra un mensaje de confirmación.

---

## HU-09 — Eliminación de propiedades

**Como** administrador, **quiero** eliminar propiedades, **para** mantener actualizado el sistema.

**Criterios de aceptación:**

- El sistema muestra una opción para eliminar.
- Se solicita confirmación antes de eliminar.
- El registro se elimina correctamente.
- La lista se actualiza automáticamente.

---

## HU-10 — Visualización de propiedades

**Como** cliente, **quiero** visualizar las propiedades disponibles, **para** encontrar una opción de interés.

**Criterios de aceptación:**

- El sistema muestra las propiedades disponibles.
- Cada propiedad muestra información básica.
- La información es visible para todos los clientes.
- La carga de datos es correcta.

---

## HU-11 — Búsqueda de propiedades

**Como** cliente, **quiero** buscar propiedades por ubicación, **para** encontrar opciones cercanas a mi interés.

**Criterios de aceptación:**

- Existe un campo de búsqueda.
- Se puede filtrar por ubicación.
- Los resultados coinciden con el criterio ingresado.
- El sistema muestra los resultados encontrados.

---

## HU-12 — Detalle de propiedad

**Como** cliente, **quiero** consultar el detalle de una propiedad, **para** conocer más información antes de tomar una decisión.

**Criterios de aceptación:**

- Se muestra la descripción completa.
- Se muestra el precio.
- Se muestra la ubicación.
- Se muestran imágenes de la propiedad.

---

## HU-13 — Solicitud de visita

**Como** cliente, **quiero** solicitar una visita a una propiedad, **para** conocerla personalmente.

**Criterios de aceptación:**

- El sistema permite seleccionar una fecha.
- La solicitud queda registrada.
- La solicitud se asocia al cliente y a la propiedad.
- Se muestra un mensaje de confirmación.

---

## HU-14 — Consulta de solicitudes

**Como** agente inmobiliario, **quiero** visualizar las solicitudes de visita, **para** dar seguimiento a los clientes.

**Criterios de aceptación:**

- El sistema muestra todas las solicitudes.
- Se visualiza el cliente asociado.
- Se visualiza la propiedad solicitada.
- La información se actualiza correctamente.

---

## HU-15 — Aprobación de visitas

**Como** agente inmobiliario, **quiero** aprobar solicitudes de visita, **para** coordinar reuniones con los clientes.

**Criterios de aceptación:**

- El sistema permite cambiar el estado de la solicitud.
- El estado se guarda correctamente.
- El cliente puede visualizar el nuevo estado.
- Se muestra un mensaje de confirmación.

---

## HU-16 — Estado de solicitudes

**Como** cliente, **quiero** consultar el estado de mis solicitudes, **para** dar seguimiento a mis visitas.

**Criterios de aceptación:**

- El sistema muestra las solicitudes realizadas.
- Se visualiza el estado de cada solicitud.
- La información está actualizada.
- Solo el cliente puede visualizar sus solicitudes.

---

## HU-17 — Reporte de propiedades

**Como** administrador, **quiero** generar un reporte de propiedades, **para** conocer el inventario disponible.

**Criterios de aceptación:**

- El sistema muestra la cantidad de propiedades.
- El reporte presenta información organizada.
- Solo administradores pueden acceder.
- Los datos son correctos.

---

## HU-18 — Propiedades recientes

**Como** cliente, **quiero** visualizar las propiedades más recientes, **para** conocer las últimas opciones disponibles.

**Criterios de aceptación:**

- El sistema muestra las propiedades más recientes.
- La lista se ordena por fecha de registro.
- La información se actualiza automáticamente.
- Los datos se muestran correctamente.

---

## HU-19 — Cambio de estado de propiedad

**Como** agente inmobiliario, **quiero** cambiar el estado de una propiedad, **para** indicar si está disponible, vendida o alquilada.

**Criterios de aceptación:**

- El sistema permite seleccionar un estado.
- El cambio se guarda correctamente.
- El nuevo estado se muestra en la lista de propiedades.
- Los clientes pueden visualizar el estado actualizado.

---

## HU-20 — Consulta del clima

**Como** cliente, **quiero** consultar el clima de la ubicación de una propiedad, **para** planificar mejor mi visita.

**Criterios de aceptación:**

- El sistema consume una API pública del clima.
- Se muestra la temperatura actual.
- Se muestra el estado del tiempo.
- Se informa al usuario si no se pueden obtener los datos.
- La información se actualiza al consultar la propiedad.

---
