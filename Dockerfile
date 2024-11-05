# Usando uma imagem do JDK 21 da Amazon Corretto como base
FROM amazoncorretto:21
  
# Definindo o diretório de trabalho dentro do contêiner
WORKDIR /app
  
# Copiando o JAR da aplicação para o contêiner
COPY target/hurr-0.0.1-SNAPSHOT.jar /app/app.jar

# Copiando o arquivo PFX para o contêiner
COPY Sandbox_InterAPI_PFX.pfx /app/Sandbox_InterAPI_PFX.pfx

# Copiando todos os scripts SQL para o diretório /app/sql no contêiner
COPY sql/ /app/sql/
  
# Variável de ambiente para o tempo de execução Java
ENV JAVA_OPTS=""
  
# Expondo a porta que a aplicação usa
EXPOSE 8080
  
# Comando para rodar a aplicação
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]