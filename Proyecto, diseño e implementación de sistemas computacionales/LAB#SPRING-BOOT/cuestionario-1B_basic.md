# Cuestionario sobre Spring Framework

## Definición y Origen:
1. ¿Qué es Spring Boot y para qué se utiliza?
    - Spring Boot es un framework que se utiliza para desarrollar aplicaciones basadas en Java.
2. ¿Cuál es la relación entre Spring Boot y el Spring Framework?
    - Spring Boot funciona como una extensión de Spring Framework, pero con un enfoque en simplificar el proceso de configuración y despliegue de aplicaciones.
3. ¿Para qué tipos de aplicaciones es ideal Spring Boot?
    - Es ideal para construir tanto aplicaciones web como servicios de backend.

## Características Principales:
1. ¿Cuál es una de las principales ventajas de usar Spring Boot en términos de configuración?
    - Spring Boot minimiza o reduce la configuración requerida para arrancar una aplicación Spring.
2. ¿Qué significa el principio de "convenio sobre configuración" en el contexto de Spring Boot?
    - Significa que intenta adivinar la configuración que necesitas en función de las bibliotecas que hay en el classpath.
3. ¿Cómo ayuda Spring Boot a reducir el tiempo de arranque y desarrollo?
    - Reduce el tiempo de arranque y desarrollo gracias a una configuración predeterminada.

## Ecosistema:
1. ¿Qué otros proyectos forman parte del ecosistema de Spring junto con Spring Boot?
    - Otros proyectos del ecosistema Spring son Spring Framework, Spring Data, Spring Security, etc.
2. ¿Cómo facilita Spring Boot la creación de aplicaciones independientes y arquitecturas de microservicios?
    - Despliegue Independiente y microservicios: facilita la creación de aplicaciones independientes y su relevancia en la construcción de arquitecturas de microservicios.

## MVC (Modelo-Vista-Controlador):
1. Describe el rol del Modelo en una aplicación Spring Boot.
    - El modelo Modelo-Vista-Controlador (MVC) es un patrón de arquitectura de software ampliamente utilizado en el desarrollo de aplicaciones web.
2. ¿Cómo se maneja la capa de datos en el modelo utilizando Spring Boot?
    - Consiste en objetos que llevan datos y la lógica relacionada con estos datos. Estos objetos suelen ser POJOs (Plain Old Java Objects) que representan.
entidades, es decir, reflejan las tablas en una base de datos.
3. Explica cómo se genera la Vista en una aplicación Spring Boot.
    - Spring Boot utiliza plantillas para generar la vista. Estas plantillas definen cómo se presenta la información del modelo al usuario, y Spring Boot las procesa para generar el HTML final que se envía al navegador.
4. ¿Qué tecnologías de plantillas son compatibles con Spring Boot para generar la Vista?
    - Pueden ser Thymeleaf, Mustache, JSP, etc.
5. ¿Cuál es la función del Controlador en el patrón MVC y cómo se implementa en Spring Boot?
    - El Controlador actúa como intermediario entre la Vista y el Modelo. Maneja las solicitudes entrantes del usuario, interactúa con el modelo para procesar datos o realizar operaciones de negocio, y luego elige una vista para presentar la salida. En Spring Boot, los controladores se implementan como clases anotadas con @Controller o @RestController. Estas clases utilizan anotaciones como @RequestMapping o variantes para mapear diferentes acciones a métodos
específicos.
6. ¿Qué diferencia hay entre un @Controller y un @RestController en Spring Boot?
    - @RestController, se utiliza principalmente para servicios API donde la respuesta es generalmente JSON o XML, en lugar de una vista HTML. @Controller se usa para devolver vistas visuales como páginas HTML.
7. Describe el flujo de trabajo de solicitud/respuesta en una aplicación Spring Boot.
    - Cuando un usuario realiza una solicitud, esta solicitud es manejada por un controlador. El controlador procesa la solicitud, interactúa con el modelo si es necesario, y luego devuelve una respuesta. Esta respuesta puede ser una vista renderizada o datos.

## Maven:
1. ¿Qué es Maven y para qué se utiliza en proyectos Spring Boot?
2. ¿Qué tipo de información se define en el archivo `pom.xml` de un proyecto Spring Boot?

## Configuración de un Proyecto Básico:
1. ¿Qué es Spring Initializr y cómo facilita la generación de un proyecto Spring Boot?
2. ¿Qué directorios y archivos son importantes en la estructura de un proyecto Spring Boot?
3. ¿Cuál es el propósito del archivo `application.properties` en un proyecto Spring Boot?
4. ¿Qué anotación se utiliza para marcar la clase principal de una aplicación Spring Boot y qué funciones realiza?

## Ejemplo Práctico:
1. Describe la estructura de un proyecto Spring Boot utilizando Mustache para las vistas.
2. Explica el propósito del controlador `SaludoControlador.java` en el ejemplo proporcionado.
3. ¿Cómo se define una plantilla Mustache y cómo se enlaza con el controlador en Spring Boot?
