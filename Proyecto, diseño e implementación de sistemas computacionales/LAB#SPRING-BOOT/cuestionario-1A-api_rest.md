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
    - Un recurso es cualquier objeto o dato que el sistema puede nombrar, leer, crear o cambiar.
5. Explica la importancia de las URIs en una API REST.
    - Las URIs son la manera de identificar cada recurso (Uniform Resource Identifier). Esto significa que cualquier recurso puede ser
accesible a través de un URL único.
6. Menciona tres características importantes de las URIs.
    - Cada URI apunta a un solo recurso (página, foto, etc.).
    - Estructura con un esquema, empieza por un protocolo (http, https, etc.) que indica como leer ese recurso.
    - Funciona como la familia principal que incluye tanto a las URL como a los URN.
7. ¿Por qué es recomendable usar nombres en plural para las URIs que representan colecciones de recursos?
    - Es recomendable usar nombres en plural para las URIs con colecciones de recursos para diferenciar un grupo de elementos de un recurso único.

## Métodos HTTP
8. ¿Cuáles son los métodos HTTP principales utilizados en una API REST y cuál es la función de cada uno?
    - **GET:** Leer información de un recurso.
    - **POST:** Crear un nuevo recurso.
    - **DELETE:** Eliminar permanentemente un recurso.
    - **PUT:** Reemplaza un recurso completo.
    - **PATCH:** Aplica modificaciones parciales a un recurso.
9. Describe la diferencia entre los métodos POST y PUT.
    - POST crea un nuevo recurso. PUT reemplaza por completo un recurso.
10. ¿Qué significa que un método HTTP sea idempotente? Da un ejemplo de un método idempotente.
    - Un método HTTP es idempotente si realizar la misma petición varias veces seguidas produce el mismo resultado en el servidor que realizarla una sola vez. Un método idempotente es PUT.
## Códigos de Estado HTTP
11. ¿Qué indican los códigos de estado en las respuestas HTTP de una API REST?
    - Los códigos de estado HTTP indican el resultado de una petición enviada al servidor. Permite saber al cliente si la operación fue exitosa, si hubo un error, etc.
12. Da un ejemplo de un código de estado para cada una de las siguientes categorías y explica su significado: 
    - 2xx (Éxito): 201 Created. Significa que se creó un nuevo recurso en el servidor y la petición tuvo éxito.
    - 4xx (Errores del cliente): 404 Not found. Significa que el servidor no pudo encontrar el recurso solicitado.
    - 5xx (Errores del servidor): 503 Service Unavailable: Significa que el servidor no se encuentra disponible para manejar la petición por mantenimiento o estar sobrecargado.

## JSON
13. ¿Por qué es JSON el formato de datos más comúnmente utilizado en las APIs REST?
    - JSON (JavaScript Object Notation) se convertió en el estándar para las APIs REST debido a su equilibrio perfecto entre legibilidad humana y eficiencia.
14. Explica brevemente la estructura de un objeto JSON.
    -  Es un formato de texto ligero basado en un sistema de llaves ({ }) que almacena datos en pares de clave y valor.
       ```json
       {
          "usuario": "Juan Pérez",
          "edad": 30,
          "activo": true,
          "habilidades": ["REST", "JSON"]
        }
       ```
15. ¿Qué tipos de datos pueden representarse en JSON?
    - **String**
    - **Number**
    - **Boolean**
    - **Null**
    - **Object**
    - **Array**
      
## Postman
16. ¿Qué es Postman y para qué se utiliza en el desarrollo de APIs?
    - Es una plataforma de desarrollo y pruebas de software que permite interactuar con APIs de forma rápida, visual sin necesidad de escribir código de cliente.
17. Menciona dos funcionalidades importantes de Postman que facilitan el trabajo con APIs.
    - **Collections:** Agrupa y organiza múltiples peticiones HTTP en carpetas. Funciona como una biblioteca estructurada que se puede guardar, exportar y compartir con el equipo de desarrollo para evitar tener que configurar cada petición desde cero.

## Ejercicios Prácticos
18. Describe cómo implementarías una operación CRUD (Crear, Leer, Actualizar, Eliminar) en una API REST.
    - Asociaría las operaciones lógicas de la base de datos con los métodos HTTP correctos y las URIs en plural correspondientes.
19. ¿Cómo usarías Postman para probar una nueva API que acabas de desarrollar?
    - Usaría Postman para probar la API de forma progresiva. Verificaría que cada endpoint funciona correctamente y luego casos de error, como IDs inexistentes, formularios sin completar, datos duplicados, etc.
20. Propone un ejemplo de una API REST para gestionar un catálogo de productos y describe brevemente los endpoints necesarios.
    - Se podría usar el recurso /Products para gestionar el catálogo de productos. Los endpoints necesarios serían:
          - **GET /Products**: Obtiene todos los productos.
          - **GET /Products/{id}**: Obtiene un producto en específico.
          - **POST /Products**: Crea un nuevo producto.
          - **DELETE /Products/{id}**: Elimina un producto.
          - **PUT /Products/{id}**: Actualiza completamente un producto.
          - **PATCH /Products/{id}**: Actualiza algunos aspectos del producto.
