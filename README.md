# Militarized College System

## Descripción

Este proyecto es un sistema de gestión para una universidad militarizada, desarrollado utilizando Java y Spring Boot. El sistema está dividido en varios microservicios y un frontend que interactúa con estos microservicios. Cada microservicio maneja una parte específica del sistema, como usuarios, admisiones, becas, etc.

## Estructura del Proyecto

- **frontend**: Contiene el código del frontend de la aplicación, desarrollado con Thymeleaf y Spring Boot. Este módulo maneja la interfaz de usuario y la seguridad.
- **microservices**: Contiene los diferentes microservicios que componen el sistema. Cada microservicio tiene su propio subdirectorio.
    - **academics**: Microservicio que maneja la información académica.
    - **admissions**: Microservicio que maneja las admisiones de estudiantes.
    - **scholarships**: Microservicio que maneja las becas.
    - **staff**: Microservicio que maneja la información del personal.
    - **users**: Microservicio que maneja la información de los usuarios y roles.
- **model**: Contiene las entidades JPA y otros modelos compartidos entre los diferentes microservicios.

## Requisitos

- Java 17
- Maven 3.6+
- MySQL

## Configuración

### Base de Datos

1. Crear una base de datos MySQL para cada microservicio.
2. Configurar las credenciales de la base de datos en los archivos `application.yml` de cada microservicio.

### Compilación y Ejecución

1. Clonar el repositorio:
    ```bash
    git clone https://github.com/tu-usuario/militarizedcollegesystem.git
    cd militarizedcollegesystem
    ```

2. Compilar el proyecto:
    ```bash
    mvn clean install
    ```

3. Ejecutar los microservicios y el frontend:
    ```bash
    cd microservices/academics
    mvn spring-boot:run
    cd ../admissions
    mvn spring-boot:run
    cd ../scholarships
    mvn spring-boot:run
    cd ../staff
    mvn spring-boot:run
    cd ../users
    mvn spring-boot:run
    cd ../../frontend
    mvn spring-boot:run
    ```

## Uso

Acceder a la aplicación en `http://localhost:8080`.


## Licencia

Este proyecto es propiedad intelectual de la Universidad Tecnológica de León. Todos los derechos reservados.
```