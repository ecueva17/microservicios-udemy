#Crear e iniciar contenedor postgres:latest, crear un contenedor llamado postgres_server, con la contraseña de usuario postgres y el volumen volumen_postgres para persistencia de datos. Exponer el puerto 5432 del contenedor al puerto 5432 del host.
>docker run -d --name postgres_server -p 5432:5432 -e POSTGRES_PASSWORD=postgres -v volumen_postgres:/var/lib/postgresql postgres:latest

#Iniciar contener postgres_server
>docker start postgres_server

#empaquetar proyecto con Java 21 y saltar las pruebas unitarias
>mvnw clean package -Dmaven.compiler.release=21 -DskipTests
 
#crear imagen ms-cursos:latest a partir del Dockerfile en el directorio actual
>docker build -t ms-cursos:latest .

#crear red docker llamada red_escuela
>docker network create red_escuela

#conectar contenedor postgres_server a la red red_escuela
>docker network connect red_escuela postgres_server

#Iniciar contenedor ms-cursos:latest con el nombre app_ms_cursos, conectarlo a la red red_escuela y exponer el puerto 8002 del contenedor al puerto 8002 del host.
>docker run -d --name app_ms_cursos --network red_escuela -p 8002:8002 ms-cursos:latest

#Crear e iniciar contenedor mysql:latest, crear un contenedor llamado mysql_server, con la contraseña de usuario root y el volumen volumen_mysql para persistencia de datos. Exponer el puerto 3306 del contenedor al puerto 3306 del host.
>docker run -d --name mysql_server -p 3306:3306 -e MYSQL_ROOT_PASSWORD=sasa -v volumen_mysql:/var/lib/mysql mysql:latest

#conectar contenedor mysql_server a la red red_escuela
>docker network connect red_escuela mysql_server

#Crear imagen ms-usuarios:latest a partir del Dockerfile en el directorio actual
>docker build -t ms-usuarios:latest .

#Crear e iniciar contenedor ms-usuarios:latest con el nombre app_ms_usuarios, conectarlo a la red red_escuela y exponer el puerto 8001 del contenedor al puerto 8001 del host.
>docker run -d --name app_ms_usuarios --network red_escuela -p 8001:8001 ms-usuarios:latest