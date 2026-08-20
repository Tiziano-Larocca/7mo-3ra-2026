# Cuestionario sobre API REST

## Conceptos Básicos
1. ¿Qué es una API y cuál es su función principal?
    - Una API (Interfaz de Programación de Aplicaciones) es un conjunto de reglas que permite que diferentes programas puedan comunicarse entre si e intercambien datos.
2. Define brevemente el estilo arquitectónico REST.
    - Es una lógica de restricciones / estándar donde se construye una API. Define que:
        -  El cliente y el servidor deben estar separados.
        -  El estado necesario para manejar cada request debe estar contenido en el mismo request. No
se debe guardar session state variables del lado del servidor.
        - Cada mensaje de respuesta debe especificar explícitamente si puede ser cacheado o no. De
este modo podemos eliminar algunas interacciones cliente-servidor y prevenir que el cliente use
data desactualizada.
        - El cliente no debería poder identificar a qué capa del sistema está conectado.
        - Los servidores pueden proporcionar código ejecutable (JavaScript) al cliente, permitiendo que
este código se ejecute en el contexto del cliente.
        - Establece que el API y el consumidor deben compartir una sola interfaz técnica: URI, métodos,
media type, etc.

3. ¿Qué significa que una API sea RESTful?
    - Que una API sea RESTful quiere decir que cumple con todas las restricciones ya mencionadas.

## Recursos y URIs
4. ¿Qué es un recurso en el contexto de una API REST?
5. Explica la importancia de las URIs en una API REST.
6. Menciona tres características importantes de las URIs.
7. ¿Por qué es recomendable usar nombres en plural para las URIs que representan colecciones de recursos?

## Métodos HTTP
8. ¿Cuáles son los métodos HTTP principales utilizados en una API REST y cuál es la función de cada uno?
9. Describe la diferencia entre los métodos POST y PUT.
10. ¿Qué significa que un método HTTP sea idempotente? Da un ejemplo de un método idempotente.

## Códigos de Estado HTTP
11. ¿Qué indican los códigos de estado en las respuestas HTTP de una API REST?
12. Da un ejemplo de un código de estado para cada una de las siguientes categorías y explica su significado: 
    - 2xx (Éxito)
    - 4xx (Errores del cliente)
    - 5xx (Errores del servidor)

## JSON
13. ¿Por qué es JSON el formato de datos más comúnmente utilizado en las APIs REST?
14. Explica brevemente la estructura de un objeto JSON.
15. ¿Qué tipos de datos pueden representarse en JSON?

## Postman
16. ¿Qué es Postman y para qué se utiliza en el desarrollo de APIs?
17. Menciona dos funcionalidades importantes de Postman que facilitan el trabajo con APIs.

## Ejercicios Prácticos
18. Describe cómo implementarías una operación CRUD (Crear, Leer, Actualizar, Eliminar) en una API REST.
19. ¿Cómo usarías Postman para probar una nueva API que acabas de desarrollar?
20. Propone un ejemplo de una API REST para gestionar un catálogo de productos y describe brevemente los endpoints necesarios.
