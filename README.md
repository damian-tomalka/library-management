# library-management
WSZiB Spring Boot Project

Projekt biblioteka - do zarządzania książkami.
Metody:
- GET (wszystko i książkę po indeksie)
- POST (tworzenie książki)
- PUT/PATCH (aktualizacja z wyjaśnieniem)
- DELETE (usuwanie)

Korzystam z dwóch profili:
- H2
- DAO

Testy 
Testy jednostkowe (BookServiceTest):
- testFindBookByIdSuccess - znaleziono książkę ID
- testFindBookByIdNotFound - nie znaleziono książki po ID

Testy integracyjne (BookControllerIntegrationTest)
testGetAllBooksSuccess - tworzenie i pobranie wszystkich książek (cały proces)
testGetBookByIdNotFound - tworzenie i pobranie wszystkich książek (na wypadek gdyby ich nie było) (cały proces)

Po uruchomieniu projektu można użyć pliku BookRequests.http

@host = http://localhost:8080
# ADMIN
@auth = Basic YWRtaW46YWRtaW4=
# USER
# @auth = Basic dXNlcjpwYXNzd29yZA==
# UNKNOWN USER
#@auth = Basic YWRtaW46cGFzc3dvcmQ=

### Pobierz wszystkie książki
GET {{host}}/api/books
Authorization: {{auth}}

###

### Pobierz książkę o ID 1
GET {{host}}/api/books/1
Authorization: {{auth}}

###

### Dodaj nową książkę
POST {{host}}/api/books
Authorization: {{auth}}
Content-Type: application/json

{
  "title": "1984",
  "author": "George Orwell",
  "publishYear": 1949,
  "isbn": "1234567890"
}

###

### Aktualizuj książkę o ID 1
PUT {{host}}/api/books/1
Authorization: {{auth}}
Content-Type: application/json

{
  "title": "Rok 1984",
  "author": "George Orwell",
  "isbn": "1234567890"
}

###

### Usuń książkę o ID 1
DELETE {{host}}/api/books/1
Authorization: {{auth}}

###

# Różnica PUT i PATCH

# Aktualizuj książkę o ID 1 (PUT)
# To zapytanie używa metody PUT do całkowitej aktualizacji informacji o książce o ID 1.
# Oznacza to, że wszystkie atrybuty książki będą zastąpione danymi przesłanymi w ciele zapytania.
# Jeśli jakiekolwiek pole nie zostanie uwzględnione w ciele zapytania (np. publishYear),
# jego wartość zostanie usunięta lub zresetowana na wartość domyślną (w przypadku publishYear będzie 0).
PUT http://localhost:8080/api/books/1
Authorization: {{auth}}
Content-Type: application/json

{
  "title": "Rok 1984",
  "author": "George Orwell",
  "isbn": "1234567890"
}

###

# Częściowo aktualizuj książkę o ID 1 (PATCH)
# To zapytanie używa metody PATCH do częściowej aktualizacji książki o ID 1.
# Tylko tytuł książki zostanie zmieniony na "Rok 1984 - Edycja Specjalna",
# podczas gdy pozostałe atrybuty książki (autor, rok publikacji, ISBN) pozostaną niezmienione.
# Jest to przydatne, gdy chcemy zaktualizować tylko niektóre pola bez konieczności przesyłania całego obiektu.
PATCH http://localhost:8080/api/books/1
Authorization: {{auth}}
Content-Type: application/json

{
  "title": "Rok 1984 - Edycja Specjalna"
}
