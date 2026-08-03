# Redes en Docker - Actividad Integradora

## Cuestionario teórico de investigación

Una vez instalado `docker` ejecuta el siguiente comando

```
ip addr show
```

1. ¿Qué interfaz se crea?
   - Se crea la interfaz `eth0`.

2. ¿Cuál es su función principal?
   - Conectar la red del contenedor con el resto de la red. Es la interfaz por la que se envían y reciben paquetes hacia otros equipos.

3. ¿Cuál es su dirección IP?
   - Su IP es 172.27.96.247/20

4. Investiga sobre los `namespace`, `veth pairs` y `bridge`. Dar una explicación breve sobre su funcionalidad y cómo se relaciona con Docker. 

   - **namespace (espacio de nombres):** Un namespace es un mecanismo de Linux que aísla recursos de un proceso, como la red, procesos, etc. En el caso de la red, cada contenedor tiene su propio network namespace, con sus        propias interfaces, direcciones IP y tabla de rutas. Docker crea un network namespace para cada contenedor.
   - **Veth pairs:** Un veth pair es un par de interfaces de red virtuales conectadas entre sí. Todo paquete que entra por un extremo sale automáticamente por el otro. Cuando Docker crea un contenedor, genera un par de           interfaces veth:​ Un extremo queda dentro del namespace del contenedor (generalmente como eth0). El otro extremo permanece en el host y se conecta al bridge de Docker (docker0). De esta manera, el contenedor puede          comunicarse con la red del host y con otros contenedores.
   - **Bridge:** Un bridge es un conmutador (switch) virtual de Linux que conecta varias interfaces de red dentro del mismo equipo. Docker crea por defecto el bridge docker0, al cual conecta los extremos de los veth pairs        de todos los contenedores. Levanta un contenedor cualquiera y ejecuta el siguiente comando ip addr show.

5. ¿Qué pasa con las interfaces, hay diferencia con la anterior salida? Explica la salida y funcionalidad de la evidencia. 
      - Al levantar un contenedor Nginx no hay diferencias significativas. Aún aparecen las 2 interfaces anteriores. Esto se debe a que Docker crea un network namespace para cada contenedor con una interfaz de loopback y          una interfaz de red virtual (eth0), independientemente de la aplicación que ejecute.

### P1. ¿Qué diferencias existen entre la red bridge por defecto (docker0) y una red bridge definida por el usuario? al menos tres.

   - La red bridge por defecto no ofrece resolucion automatica de nombres. Todos los contenedores conectados a docker 0 comparten la misma red y tiene una configuración fija, con poca personalización. En cambio, la red       bridge definida por el usuario ofrece resolución automática de nombres, permite crear redes independientes y permite personalizar la red.

### P2. ¿Por qué en la red bridge por defecto los contenedores no pueden resolverse por nombre, mientras que en una red bridge personalizada sí? 

   - En la red bridge por defecto (docker0), Docker no ejecuta un servidor DNS interno para resolver automáticamente los nombres de los contenedores. En cambio, una red bridge personalizada incorpora un servidor DNS          interno que registra automáticamente el nombre de cada contenedor conectado a esa red.

### P3. Explicá el propósito de las opciones --subnet, --gateway y --ip-range al crear una red con docker network create. 

   - Al crear una red con docker network create, estas opciones permiten definir cómo será la configuración de la red. --subnet: especifica la subred que utilizará la red de Docker --gateway: define la puerta de enlace       (gateway) de la red. --ip-range: establece un subconjunto de la subred desde el cual Docker asignará automáticamente las direcciones IP a los contenedores.

### P4. ¿Qué es el embedded DNS server de Docker y en qué dirección IP interna suele escuchar dentro de cada contenedor? 

   - El Embedded DNS Server de Docker es un servidor DNS interno que Docker crea automáticamente para las redes bridge definidas por el usuario. Su función es resolver los nombres de los contenedores a sus direcciones IP,    permitiendo que se comuniquen entre sí usando el nombre del contenedor en lugar de la IP.  Este servidor DNS suele escuchar en la dirección IP: 127.0.0.11 dentro de cada contenedor. Macvlan / IPvlan

### P5. ¿Cuál es la diferencia conceptual entre un driver macvlan y uno ipvlan (modo L2) respecto a las direcciones MAC asignadas a cada contenedor? 

   - La diferencia conceptual entre macvlan e ipvlan radica en cómo manejan las direcciones MAC de los contenedores. En Macvlan cada contenedor recibe una direccion MAC única y cada contenedor aparece como si fuera un        dispositivo independiente conectado al switch. En IPvlan todos los contenedores comparten la misma dirección MAC de la interfaz física del host. Cada contenedor tiene una dirección IP diferente, pero no una MAC propia.

### P6. ¿Por qué, por defecto, un host Linux no puede comunicarse directamente con un contenedor conectado a una red macvlan creada sobre su misma interfaz física, y cómo se soluciona con una sub-interfaz macvlan en el host?

   - Un host Linux no puede comunicarse directamente con un contenedor conectado a una red macvlan porque el controlador macvlan aísla el tráfico entre la interfaz física del host y las interfaces macvlan. La solución        consiste en crear una subinterfaz macvlan en el propio host y asignarle una dirección IP de la misma subred que utilizan los contenedores.

### P7. ¿En qué escenario recomendarías ipvlan por sobre macvlan, considerando límites de direcciones MAC en switches gestionados?

   - Recomendaría ipvlan por sobre macvlan cuando la infraestructura de red tiene limitaciones en la cantidad de direcciones MAC que puede aprender un switch gestionado o cuando se desea evitar problemas asociados a un       gran número de direcciones MAC. Con macvlan, cada contenedor recibe una dirección MAC única, por lo que un host con muchos contenedores puede hacer que el switch deba almacenar cientos o miles de direcciones MAC.


8. Del Navegador al Servidor Web:​
Entras a la web desde el navegador (http://localhost:8080). Docker toma esa petición en la
computadora y gracias a la redirección de puertos (-p), la reenvía directamente al puerto 80
del servidor web (apache).
Dentro de la red virtual de Docker (El "cable" virtual):​
El paquete de datos pasa del sistema operativo al contenedor atravesando una tarjeta de
red virtual (veth) y un switch virtual (bridge) que Docker creó en la memoria.
Del Servidor Web a la Base de Datos:​
El servidor web le pregunta al DNS interno de Docker en qué IP está db_postgres. Docker le
responde la IP interna (172.19.0.3) y el servidor web se conecta directamente a la base de
datos a través de ese mismo switch virtual en el puerto 5432.
9. Interfaces veth (Virtual Ethernet Pair): Las interfaces veth funcionan en parejas
interconectadas a modo de "cable de red virtual". Un extremo del par se coloca dentro de la
red delimitada del contenedor (eth0), mientras que el otro extremo permanece en el
espacio de red principal del Host. Esto permite conectar el aislamiento del contenedor
(Network Namespace) con el exterior.
Red Bridge: El bridge actúa como un conmutador de red (Switch) en software al que se
conectan todos los extremos de las interfaces veth del host. Permite que los contenedores
conectados a la misma red bridge se comuniquen entre sí mediante tramas / paquetes IP.
