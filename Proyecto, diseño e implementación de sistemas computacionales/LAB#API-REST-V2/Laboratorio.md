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
3. ¿Por qué no es recomendable devolver directamente una Entity desde el Controller?
4. ¿Cuál es la diferencia entre Entity y DTO?
5. ¿Qué función cumple JpaRepository?
6. ¿Qué ocurre cuando el Service necesita consultar un registro que no existe?
7. ¿Qué ventajas ofrece application.yaml?
8. ¿Por qué una API debería devolver diferentes códigos HTTP según 
