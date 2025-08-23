# Usa uma imagem com Java 17 para o build
FROM maven:3.9.5-amazoncorretto-17 as builder

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo de configuração do projeto
COPY pom.xml .

# Baixa as dependências do Maven, o que acelera o build
RUN mvn dependency:go-offline

# Copia todo o código-fonte
COPY src ./src

# Empacota a aplicação em um arquivo JAR
RUN mvn clean package -DskipTests

# --- Segunda etapa: Cria a imagem final para rodar a aplicação ---
# Usa uma imagem mais leve com apenas a JRE 17
FROM amazoncorretto:17-alpine-jdk

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo JAR da etapa de "builder" para a imagem final
COPY --from=builder /app/target/*.jar ./app.jar

# Define o comando para rodar a aplicação quando o container iniciar
CMD ["java", "-jar", "app.jar"]