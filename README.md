# Начало работы

Перед запуском приложения необходимо скачать и установить PostgreSQL с сайта https://www.postgresql.org/. Во время установки, во всех местах где потребуется, указать пароль admin.

После установки и запука БД (базы данных), необходимо выполнить скрипт создания таблицы:

    CREATE TABLE IF NOT EXISTS DIRECTOR(
        ID SERIAL PRIMARY KEY,
        NAME VARCHAR NOT NULL
    );
    
    CREATE TABLE IF NOT EXISTS ACTOR(
        ID SERIAL PRIMARY KEY,
        NAME VARCHAR NOT NULL
    );
    
    CREATE TABLE IF NOT EXISTS FILM(
        ID SERIAL PRIMARY KEY,
        TITLE VARCHAR NOT NULL,
        DIRECTOR_ID INTEGER,
        FOREIGN KEY (DIRECTOR_ID) REFERENCES DIRECTOR(ID)
    );
    
    CREATE TABLE IF NOT EXISTS ACTOR_FILM(
        FILM_ID INTEGER  NOT NULL,
        ACTOR_ID INTEGER  NOT NULL,
        PRIMARY KEY(FILM_ID, ACTOR_ID),
        FOREIGN KEY (FILM_ID) REFERENCES FILM(ID),
        FOREIGN KEY (ACTOR_ID) REFERENCES ACTOR(ID)
    );

Для доступа в БД и выполнения скрипта можно использовать PgAdmin4, который устанавливается вместе с базой данных, его можно найти через поиск Windows.

После открытия PgAdmin, необходимо открыть меню Query Tool:

![Скриншот PgAdmin4](/pgadmin.png)