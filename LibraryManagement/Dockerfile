# Użyj oficjalny obraz JDK jako bazowy obraz
FROM openjdk:17-jre-slim

# Ustaw katalog roboczy w kontenerze
WORKDIR /app

# Skopiuj plik JAR z lokalnego systemu plików do kontenera
COPY target/your-application.jar /app/

# Określ port, na którym działa aplikacja
EXPOSE 8080

# Uruchom aplikację po uruchomieniu kontenera
CMD ["java", "-jar", "your-application.jar"]