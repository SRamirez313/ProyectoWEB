# Tierra Fertil Web

Sistema de gestion inmobiliaria desarrollado como proyecto final del curso SC-403 - Desarrollo de Aplicaciones Web y Patrones (Universidad Fidelitas).

## Descripcion

Tierra Fertil Web centraliza la administracion de propiedades, clientes y solicitudes de visita de una empresa inmobiliaria ficticia, reemplazando el manejo actual por hojas de calculo y mensajeria.

## Equipo

- Santiago Ramirez Madrigal
- Stefan Lutz Clarke
- Carlos Steven Aviles Roque
- Abraham Chinchilla Aguilar

## Tecnologias utilizadas

| Tecnologia | Uso |
|---|---|
| Java 21 | Lenguaje principal |
| Spring Boot 3.3 | Framework backend |
| Spring Security | Autenticacion y autorizacion por roles |
| Thymeleaf | Motor de plantillas del lado del servidor |
| Bootstrap 5 | Diseno responsivo |
| Hibernate / Spring Data JPA | Persistencia de datos |
| MySQL | Base de datos relacional |
| Maven | Gestion de dependencias |
| Git & GitHub | Control de versiones |

## Requisitos previos

- JDK 21
- Maven 3.9+
- MySQL 8+
- Un IDE (recomendado: IntelliJ IDEA o Spring Tools)

## Configuracion local

1. Clonar el repositorio:
   ```
   git clone https://github.com/SRamirez313/ProyectoWEB.git
   ```
2. Crear una base de datos en MySQL llamada `tierrafertil_db` (o dejar que `createDatabaseIfNotExist=true` la cree automaticamente).
3. Ajustar `src/main/resources/application.properties` con tu usuario y password de MySQL local.
4. Ejecutar:
   ```
   mvn spring-boot:run
   ```
5. La aplicacion queda disponible en `http://localhost:8080`.

## Estructura del proyecto

```
com.tierrafertil
├── config/          Configuracion de seguridad e internacionalizacion
├── model/           Entidades JPA (Usuario, Propiedad, Rol, etc.)
├── repository/       Interfaces JpaRepository
├── service/         Logica de negocio
├── controller/       Controladores MVC (Thymeleaf)
└── controller/api/  Endpoints REST (JSON)
```

## Modulos del sistema

- Autenticacion y gestion de usuarios (roles: ADMIN, AGENTE, CLIENTE)
- CRUD de propiedades
- Modulo transaccional de solicitudes de visita
- Consulta de clima por ubicacion (API externa)
- API REST de propiedades
- Soporte de idioma espanol / ingles

## Estado del proyecto

En desarrollo - Entrega 2 en progreso.
