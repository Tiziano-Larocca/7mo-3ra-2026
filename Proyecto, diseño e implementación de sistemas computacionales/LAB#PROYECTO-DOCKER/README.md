# Proyecto: Desplegar una Aplicación Web en Docker con Tomcat

## Paso 1
Descargamos la aplicación web Jenkins para poder desplegarla en tomcat.

![img1](img/1.png)

Además, nos ponemos con wsl por powershell. Sin wsl no funciona docker.

![img2](img/2.png)

## Paso 2

Hacemos toda la estructura de directorios.

![img3](img/3.png)

Intentamos copiar la aplicación .war en el directorio del proyecto, pero no podemos.

![img4](img/4.png)
![img5](img/5.png)

Por ello decidimos descargar la aplicación directamente en el directorio.

![img6](img/6.png)
![img7](img/7.png)

## Paso 3

Previo a modificar el archivo Dockerfile para la imágen, verificamos la imágen de tomcat que tenemos.

![img8](img/8.png)

Una vez verificamos, modificamos el archivo con la versión correspondiente.

![img9](img/9.png)

## Paso 4

Construimos la imágen de Docker y ejecutamos el contenedor creado.

![img10](img/10.png)
![img11](img/11.png)

## Paso 5

Una vez este ejecutado, verificamos que la aplicación web funcione.

![img12](img/12.png)

Funciona 😃
