# Laboratorio Docker - Networking - Reverse Proxy
Alumno: **Tiziano Larocca**

Profesor: **Vicente Cersosimo**

Curso: **7mo 3ra**

## Objetivo del trabajo
Configurar un servidor web `Nginx` que actúe como `proxy inverso` para redirigir el tráfico desde tu host hacia un contenedor de aplicaciones `Payara Server` que se ejecuta de forma aislada.

### Paso 1: Crear la Red Virtual de Docker
Para que el proxy inverso se comunique con Payara, ambos contenedores deben compartir una red interna. En una terminal, creamos la y verificamos la red creada.

```
docker network create red-proxy
```

![img1](img/1.png)

### Paso 2: Desplegar el Contenedor Payara (Backend)
Desplegamos el servidor Payara en la red interna. No se expondrán sus puertos directamente al host local, simulando un entorno de producción seguro. En la terminal ejecutamos el siguiente comando para la creación y verificamos.

```
docker run -d --name servidor-payara --network red-proxy payara/server-full:6.2024.6
```
![img2](img/2.png)

### Paso 3: Configurar el Proxy Inverso (Nginx)
Crearemos un archivo de configuración para indicarle a Nginx que todo el tráfico que reciba en el puerto 80 debe redirigirlo al puerto 8080 del contenedor de Payara. Para hacerlo creamos una carpeta llamada `config-nginx` y dentro de ella el archivo `default.conf` con la siguiente configuración:

```
server {
    listen 80;
    server_name localhost;

    location / {
        proxy_pass http://servidor-payara:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    }
}
```

![img3](img/3.png)

### Paso 4: Desplegar el Contenedor Nginx 
Ahora se asociará el archivo de configuración creado con un nuevo contenedor de Nginx. Este contenedor sí estará expuesto al host. Deberemos tener una terminal en la ruta donde creamos el archivo default.conf y ejecutar el siguiente comando:

```
docker run -d --name proxy-nginx --network red-proxy -p 80:80 -v "$(pwd)/default.conf:/etc/nginx/conf.d/default.conf" nginx
```

-v "$(pwd)/default.conf:/etc/nginx/conf.d/default.conf": Monta un volumen. Toma el archivo default.conf de tu directorio actual ($(pwd)) y lo duplica/reemplaza dentro del contenedor en la ruta de configuración de Nginx. Cualquier cambio que hagas en tu archivo local se aplicará en el contenedor. 

Verificamos default.conf en el contenedor.

![img4](img/4.png)

Ambos contenedores están activos.

![img5](img/5.png)

### Paso 5: Verificación desde el Cliente (Host Local)
El cliente (tu navegador web) hará una petición directamente a `http://localhost`. Nginx recibirá la petición y te mostrará la interfaz de Payara de manera transparente. En el navegador web escribimos http://localhost y vemos la interfaz de Payara.

![img6](img/6.png)

## Evaluación Teórica: Comprensión del Flujo de Red

1. **El rol del Host Local:** Cuando ingresas a http://localhost en tu navegador, ¿a cuál de los dos contenedores le está llegando tu petición en primera instancia? Justifica tu respuesta basándote en los puertos mapeados.
    * La petición está llegando en primera instancia al contenedor Nginx. Los puertos de Payara no están expuestos al host local. En cambio, Nginx es el único que tiene el puerto 80 publicado hacia el host mediante el           mapeo -p 80:80.

2. **Aislamiento de Payara:** Si intentas ingresar en tu navegador a http://localhost:8080, la página no va a cargar. Explicá por qué ocurre esto si sabemos que Payara está corriendo internamente en ese puerto.
    * La página no carga porque Payara no tiene expuesto el puerto 8080 hacia el host local. Aunque Payara escucha internamente en ese puerto, solo es accesible desde la red interna de Docker. Al ingresar a                      http://localhost, la solicitud llega a Nginx, que actúa como proxy inverso y la redirige al puerto 8080 del contenedor de Payara.
  
3. **Resolución de nombres en Docker:** En el archivo default.conf de Nginx escribiste la línea proxy_pass http://servidor-payara:8080;. ¿Cómo logra Nginx saber qué dirección IP corresponde a servidor-payara? ¿Qué componente de Docker lo hace posible?
    * Nginx puede resolver el nombre servidor-payara porque ambos contenedores están conectados a la misma red de Docker (red-proxy). Docker incorpora un servidor DNS interno, que traduce automáticamente el nombre del           contenedor a su dirección IP interna.

4. **Inspección de Redes:** Si ejecutás el comando docker network inspect red-proxy en tu terminal, ¿qué direcciones IP internas esperarías ver asignadas y a qué contenedores pertenecerían?
    * Se esperarían ver IP's privadas asignadas a los contenedores de Nginx y Payara.

5. **Análisis de Cabeceras:** En la configuración de Nginx agregaste la línea proxy_set_header X-Real-IP $remote_addr;. ¿Qué utilidad tiene esto para el contenedor de Payara y por qué es necesario en una arquitectura de proxy inverso?
    * La cabecera X-Real-IP permite que el contenedor de Payara conozca la dirección IP real del cliente que realizó la solicitud. Sin esta cabecera, Payara solo vería la IP del contenedor Nginx, ya que es quien le              reenvía la petición. Esto es importante para registrar logs, aplicar controles de seguridad, etc.
