DOCKER
===============
//first, to generate .jar execute
mvn clean compile package

//second, to build docker image, go into ConfigServerProject folder and execute
sudo docker build --tag examen-final:1.0 .

//finally, to run docker image
sudo docker run -p 8080:8080/tcp examen-final:1.0



Spring y Redis
===============

Caso:
------

Para el presente laboratorio, se realizará la implementación de registar un histórico en Redis con cada transacción realizada con la alquiler de una bicicleta, como puede ser:

> - Registrar un alquiler de bicicleta, completar el alquiler o cancelar el alquiler

Cada transacción realizada a AzureSQL significara un registro para redis. Posteriormente en la misma aplicación se realizara la consulta a Redis para obtener el histórico por usuario.


Dependencias:

##### Requerido:

- spring boot web
- spring data jpa
- spring data redis (como capa de abstracción)
- jedis:3.2.0 Cliente para Redis (Cliente Redis)
- mssql-server (version equivalente al JDK)

##### Opcional:

- spring boot validation
- spring boot actuator
- spring boot devtools
- spring boot test


> Las credenciales establecidad para el acceso a la base de datos solo están disponibles hasta el 2020-08-15, despúes de dicha fecha se tendrá que habilitar un nuevo componente AzureSQL para demostrar el laboratorio o en su defecto hacerlo con un base de datos en local.

-----

Pasos:
------

- Agregar las dependencias necesarias al pom.xml
- Realizar las configuraciones a través del JavaConfig *RedisConfig* in *package pe.com.example.bikerental.config;*
- Realizar la definición de los models de terceros para manipular la data de mssql y redis esta definición se encuentra en package **pe.com.example.bikerental.thirdparty**.
- Definir la interfaz que extendienda de CrudRepository interfaz proveida por spring data. Esta nos ayuda a realizar las transacciones con las bases de datos. Como Redis y otras bases de datos relacionales.
- Ahora de sebe implementar el método que nos ayudará a registrar la información sobre redis, Este método se encuentra en la clase **BookingSender** package *package pe.com.example.bikerental.business.fn01*

-----

Pruebas:
--------

Redis:

keys * : para listar las keys existentes en el cache
flushdb : para limpiar cache
hmget {key} : muestra el objecto almacenadowait ''': para eliminar todos los registros dentro de redis.
smember {key} muestra los objectos relacionas a **key**.

Para las pruebas se puedes realizar desde postman ubicacion en  */src/main/resources/postman*


Links:
------

- https://docs.spring.io/spring-data/redis/docs/2.3.2.RELEASE/reference/html/#redis:serializer