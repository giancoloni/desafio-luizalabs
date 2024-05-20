# Use a imagem base do OpenJDK 22
FROM openjdk:22-jdk-slim

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo JAR do serviço para o diretório de trabalho
COPY target/desafio-luizalabs-0.0.1-SNAPSHOT.jar desafio-luizalabs.jar

# Expõe a porta que o serviço estará utilizando
EXPOSE 8080

# Comando para executar o serviço
ENTRYPOINT ["java", "-jar", "desafio-luizalabs.jar"]
