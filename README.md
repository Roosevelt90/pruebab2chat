# testB2Chat

Prueba técnica para la empresa b2chat

## Requerimientos

Para construir y ejecutar la aplicación se necesita tener instlado los siguientes requerimientos:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [Lombok](https://projectlombok.org/)
- [Mysql](https://www.mysql.com/)

## Configuración de la Base de Datos

1. Crea una base de datos MySQL llamada `db_b2chat`.
2. Actualiza las configuraciones de la base de datos en `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db_b2chat
spring.datasource.username=nombre_usuario
spring.datasource.password=contraseña
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
```
3. Ejecutas el script del backup que se encuentra en la carpeta `script/backup.sql`

## Ejecutando la aplicación localmente

Puedes ejecutar la aplicación de varias maneras:

-Desde tu IDE: Ejecuta la clase principal TestB2ChatApplication.

-Desde la línea de comandos: Utiliza el siguiente comando en el directorio del proyecto.

```shell
./mvnw spring-boot:run
```



## Copyright

Publicado bajo la licencia Apache 2.0. Ver la [Licencia](https://github.com/codecentric/springboot-sample-app/blob/master/LICENSE).