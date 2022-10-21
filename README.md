# UrlShorter

## Приступая к работе
Необходимо установить:
* Java 17;
* Apache Maven 3.6.3;
* PostgreSQL.

Cоздать БД: **urlShorter**.

В файле **application.properties** (resources) задать переменные окружения:
* Строка подключения к базе данных (`spring.datasource.url`);
* Имя пользователя Postgres (`spring.datasource.username`);
* Пароль пользователя Postgres (`spring.datasource.password`);
* Используемый домен (`domain`, по умолч. http://localhost:8080/).

## Запуск
Запустить командой в корневой директории проекта `mvnw clean install spring-boot:run` или `mvnw spring-boot:run`

## Схема БД
Для управления базой данных использовалась библиотека Liquibase
(изменения описаны в формате xml). В результате автоматически создаются 3 таблицы:
* **Users** - для хранения данных пользователя;
* **Url** - для хранения url;
* **Transition** - для хранения информации о переходах по ссылкам.

(+2 таблицы для работы liquibase - **databasechangelog**, **databasechangeloglock**)

Также создается генератор последовательности **url_sequence**, который необходим для 
генерации короткого url.

## Методы API
Спецификация API представлена в формате **Swagger**: http://localhost:8080/swagger-ui-custom.html (доступна после
запуска приложения).

API для UserController протестированы с помощью интеграционных тестов.