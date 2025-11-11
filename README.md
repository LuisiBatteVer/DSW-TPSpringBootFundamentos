#Alumna: BATTELLA VERCESI, LUISINA
# Legajo: 47494
# 📝 Sistema de Gestión de Tareas - Fundamentos de Spring Boot

Trabajo Práctico

---

## 📝 Descripción del proyecto

Esta es una aplicación de consola desarrollada con Spring Boot que simula un gestor de tareas (To-Do List). El objetivo del proyecto es aplicar los conceptos fundamentales de Spring Boot aprendidos en la cátedra, incluyendo:

* **Inyección de Dependencias (DI):** Utilizando inyección por constructor para desacoplar componentes.
* **Estereotipos:** Organización de la arquitectura en capas usando `@Service` y `@Repository`.
* **Configuración Externa:** Uso de `application.properties` y la anotación `@Value` para gestionar valores como el límite de tareas.
* **Perfiles (Profiles):** Gestión de diferentes entornos (`dev` y `prod`) con configuraciones (`.properties`) y *beans* condicionales (`@Profile`).

---

## ⚙️ Tecnologías utilizadas

* **Java:** 21 (o 17+)
* **Spring Boot:** 3.5.7
* **Maven:** Gestor de dependencias y build
* **Lombok:** Para reducción de código boilerplate (constructores, getters, setters).

---

## 🚀 Instrucciones para clonar y ejecutar el proyecto

1.  Clona el repositorio desde GitHub:
    ```bash
    git clone [https://github.com/LuisiBatteVer/DSW-TPSpringBootFundamentos.git](https://github.com/LuisiBatteVer/DSW-TPSpringBootFundamentos.git)
    ```
2.  Navega al directorio del proyecto:
    ```bash
    cd DSW-TPSpringBootFundamentos
    ```
3.  Ejecuta la aplicación usando el wrapper de Maven. La aplicación se ejecutará usando el perfil (`dev` o `prod`) que esté definido en `src/main/resources/application.properties`.
    ```bash
    ./mvnw spring-boot:run
    ```

---

## 🔧 Cómo cambiar entre profiles (dev/prod)

Esta aplicación tiene dos perfiles: `dev` (desarrollo) y `prod` (producción). El método recomendado para cambiar entre ellos es editando el archivo de configuración base.

**Ubicación del archivo:** `src/main/resources/application.properties`

### Perfil dev (Desarrollo)
Para activar el perfil de desarrollo (límite de 10 tareas, estadísticas visibles), asegura que la línea esté así:
```properties
spring.profiles.active=dev
```
### Perfil dev (Desarrollo)
Para activar el perfil de producción (límite de 1000 tareas, estadísticas ocultas ), asegura que la línea esté asi:
```properties
spring.profiles.active=prod
```
## Capturas de pantalla de la consola con ambos profiles
DEV: <img width="886" height="471" alt="image" src="https://github.com/user-attachments/assets/73dafce2-3b98-438b-8862-8aadd4c02ed8" />
PORD:
---
## CONCLUSIONES

Este trabajo sirvió para entender cómo funciona Spring Boot en la práctica. Lo más importante fue aprender a usar la Inyección de Dependencias por constructor, que básicamente es dejar que Spring "conecte los cables" (como el Repositorio dentro del Servicio) por nosotros.

También quedó claro cómo usar los estereotipos (@Service, @Repository) para ordenar el proyecto y decirle a Spring qué hace cada clase.

Finalmente, fue muy útil ver cómo usar @Value y los Perfiles (@Profile) para cambiar el comportamiento de la app (como el límite de tareas o los mensajes) simplemente cambiando el entorno de dev a prod, sin tocar el código Java.

### 📊 Comparativa de Perfiles: dev vs. prod

| Característica | ✅ Perfil `dev` (Prueba 1) | 🚀 Perfil `prod` (Prueba 2) |
| :--- | :--- | :--- |
| **Perfil Activo** | `The following 1 profile is active: "dev"` | `The following 1 profile is active: "prod"` |
| **Mensajes (Bean)** | `MensajeDevService` (Ej: `[PROFILE: DEV]...`) | `MensajeProdService` (Ej: `[PROFILE: PROD]...`) |
| **Límites (@Value)** | `Límite Máximo de Tareas: 10` (de `...dev.properties`) | `Límite Máximo de Tareas: 1000` (de `...prod.properties`) |
| **Estadísticas** | Se muestran los números (Ej: `Total: 6...`) | Muestra "Visualización deshabilitada..." |
| **Logging** | Muestra logs `DEBUG`, `INFO`, `WARN` | Muestra solo logs `ERROR` |
---








