# Nazwa obrazu
$IMAGE_NAME = "library-management"

# Budowanie aplikacji Spring Boot
mvn clean package

# Budowanie obrazu Docker
docker build -t $IMAGE_NAME .

# Uruchamianie kontenera Dockera
docker run -d -p 8080:8080 $IMAGE_NAME

Write-Host "Aplikacja jest dostępna pod adresem http://localhost:8080"