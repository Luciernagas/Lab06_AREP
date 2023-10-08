## TALLER DE PATRONES ARQUITECTURALES
Aplicación web con la siguiente arquitectura propuesta y desplegada en AWS usando EC2 y Docker.
![image](https://github.com/Luciernagas/Lab03_AREP/assets/104604359/99df3e56-ed4c-4867-8d00-6748e6b0264c)

* * *
### Prerrequisitos
Se debe tener instalado java, maven y git.
* Descargar maven en  http://maven.apache.org/download.html
* Descargar git en https://git-scm.com/book/en/v2/Getting-Started-Installing-Git

* * *
### Instalando
1. Se debe clonar el proyecto con el comando:
~~~
git clone https://github.com/Luciernagas/Lab06_AREP.git
~~~
2. En la terminal se debe ingresar el siguiente comando para compilar y empaquetar el proyecto:
~~~
mvn package
mvn clean install
~~~
* * *
### Despliegue
1. En la terminal se debe ingresar los siguientes comandos para construir las imagenes de docker:
~~~
docker build --tag roundrobin -f DockerfileRoundRobin .
docker run -d -p 4568:4568 -e PORT=4568 roundrobin
docker run -d -p 4569:4569 -e PORT=4569 roundrobin
docker run -d -p 4570:4570 -e PORT=4570 roundrobin
~~~
~~~
docker build --tag logservice -f DockerfileLogservice .
docker run -d -p 6000:6000 logservice 
~~~
~~~
docker network create my_network
docker run -d -p 27017:27017 -v mongodb:/data/db -v mongodb_config:/data/configdb --name db --network my_network mongo:3.6.1 mongod 
~~~
~~~
docker build --tag index -f DockerfileIndex .        
docker run -d -p 80:80 --name front index          
~~~

* * *
### Ejecutando pruebas
En la sección de pruebas se confirmará el correcto funcionamiento del servidor web, para ello se ejecuto el siguiente comando:
~~~
java -cp "./target/classes" org.example.logservice.Logservice       
~~~
se ingresa mediante un browser a la siguiente ruta:
~~~
localhost:35000      
~~~
y al ingresar cadenas se iran almacenando en la base de datos y devolviendo las 10 últimas con su  correspondiente fecha de ingreso, como se observa en la siguiente imagen:
![image](https://github.com/Luciernagas/Lab06_AREP/assets/104604359/2ba50ffd-a382-4f9a-ae12-4f81fc8602fd)

* * *
### Construido con
* Maven - Gestión de dependencias
* Java - Lenguaje de programación
* HTML, JavaScript - Interfaz de usuario
* DockerHub
* MongoDB
* Amazon Web Services

* * *
### Autores
♡ Luisa Valentina De la hoz Rocha ♡
