# Laboratorio#06 - Construir y desplegar una aplicación con Tomcat
## Objetivo del proyecto
Desplegar una aplicación en Tomcat capaz de conectarse a una base de datos MySQL.

## Pasos realizados

1. Con `XAMPP` levantamos los servicios de `Tomcat` y `MySQL` en los puertos 3306 y 8080.

![img1](img/1.png)

2. Creamos un proyecto en `Maven`, donde configuraremos todo el entorno.

![img2](img/2.png)

3. En el archivo `pom.xml` agregamos la dependencia `JDBC` para que `Java` pueda comunicarse con MySQL. Sin ella no sería posible la comunicación entre Java y MySQL.
   
![img3](img/3.png)

4. El servidor levanta correctamente.

![img4](img/4.png)

5. Creamos la clase `ConexionMySQL.java`, responsable de establecer la conexión con MySQL y cargar el driver JDBC. También se crea la clase `UsuariosServlet.java`, responsable de atender las solicitudes HTTP, consultar
   la tabla usuarios de MySQL y generar una respuesta HTML con los datos obtenidos.

![img5](img/5.png)

6. Configuramos el archivo ConexionMySQL.java. El host es la máquina local y se utiliza el puerto 3306.

![img6](img/6.png)

7. Configuramos el archivo Usuarios.Servlet.java. Se genera la respuesta HTML con los datos de las tablas.

![img7](img/7.png)

8. Creamos la base de datos `appdb`, que especificamos anteriormente en el archivo ConexionMySQL.java. Además creamos las tablas en appdb e insertamos datos en ellas.

![img8](img/8.png)

9. Datos de las tablas en appdb. Ejecutamos una consulta y responde correctamente.

![img9](img/9.png)

10. Ponemos la URL en el navegador web y se ve bien la tabla HTML. La aplicación logró conectarse correctamente a MySQL.

![img10](img/10.png)
