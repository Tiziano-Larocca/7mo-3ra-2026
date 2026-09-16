# API REST Spring Boot

Alumno: **Tiziano Larocca**

Profesor: **Vicente Cersosimo**

Curso: **7mo 3ra**

## Objetivo
Desarrollar una API REST funcional aplicando una arquitectura por capas. El proyecto deberá separar responsabilidades entre Controller, Service, Repository, DTO y Entity, utilizar JPA para la persistencia y
centralizar la configuración en application.yaml.

## Cuestionario

1. ¿Qué responsabilidad tiene cada capa?
   -  **Controller:** Toma la petición HTTP (GET, POST, PUT, DELETE) e invoca a la lógica necesaria según sea.
   -  **DTO:** Decide que datos entran y salen de la API sin exponerla. Por ejemplo id, datos de cuenta bancaria, etc. Como adicional se encarga de las validaciones, controlando que un objeto cumpla con determinadas 		restricciones
   -  **Entity:** No solo funciona como clase para crear productos, mapea los atributos a la base de datos para crear la tabla products de db_product;
   -  **Repository:** Se encarga del acceso a la base de datos con métodos como `findAll()`, `findById(id)`, `save`, etc.
   -  **Service:** Es la lógica de la aplicación. Puede modificar entidades, borrarlas, crear, etc.
2. ¿Por qué no es recomendable devolver directamente una Entity desde el Controller?
   - Porque podrían exponerse datos internos que no deberían ser vistos. Además, la API no queda atada a la estructura de la base de datos.
3. ¿Cuál es la diferencia entre Entity y DTO?
   - Entity mapea sus atributos a la base de datos para crear la tabla products. DTO controla qué datos entran y salen de la base de datos sin exponer datos sensibles.
4. ¿Qué función cumple JpaRepository?
   - `JpaRepository` facilita las consultas a la base de datos sin escribir las consultas SQL comunes. Se usan métodos como findAll(), deleteById(), save(), etc.
5. ¿Qué ocurre cuando el Service necesita consultar un registro que no existe?
   - Cuando el Service consulta un registro que no existe lanza un `EntityNotFoundException` para que el controlador luego responda con un `HTTP 404 Not found`.
6. ¿Qué ventajas ofrece application.yaml?  
   - `application.yaml` permite configurar la aplicación de forma ordenada, por ejemplo, la conexión a la base de datos, el puerto del servidor o configuraciones de JPA. Facilita cambiar configuraciones sin                       modificar el código.
7. ¿Por qué una API debería devolver diferentes códigos HTTP según el resultado de la operación?
   - Porque los códigos HTTP indican al cliente qué ocurrió con la solicitud. Por ejemplo:
        - `GET → 200 OK:` los datos fueron obtenidos correctamente.
        - `POST → 201 Created:` se creó un nuevo recurso.
        - `PUT → 200 OK:` el recurso fue actualizado correctamente.
        - `DELETE → 204 No Content:` el recurso fue eliminado correctamente.
