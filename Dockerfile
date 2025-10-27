# Etapa 1 - Compila o projeto com Maven + Java 21
FROM maven:3.9.8-eclipse-temurin-21 AS builder

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o pom.xml primeiro (para baixar dependências antes do código)
COPY pom.xml .

# Baixa as dependências Maven (cache eficiente)
RUN mvn dependency:go-offline

# Copia o restante do código fonte
COPY src ./src

# Executa o build da aplicação, gerando o JAR
RUN mvn clean package -DskipTests

# Etapa 2 - Cria uma imagem limpa só com o Java e o JAR final
FROM eclipse-temurin:21-jdk

# Define o diretório de trabalho
WORKDIR /app

# Copia o jar da primeira imagem (builder)
COPY --from=builder /app/target/transferencia-pix-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta da aplicação
EXPOSE 8080

# Comando de entrada: inicia a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
