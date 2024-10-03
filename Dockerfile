# Imagen base de OpenJDK
FROM openjdk:17-jdk-alpine

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR generado por tu aplicación Spring Boot
COPY target/Api_Rest-0.0.1-SNAPSHOT.jar /app/api_rest.jar

# Exponer el puerto en el que corre tu aplicación (por defecto Spring Boot usa el puerto 8080)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/api_rest.jar"]
