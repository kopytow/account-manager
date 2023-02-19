# Spring JDBC Работа с базой данных и построение запросов

ДЗ

1. Вспоминаем приложение для управления базой данной контактов (Урок Spring REST).
2. Контакт имеет следующие характеристики – Имя и Фамилия, Телефонный номер, Email.
3. Приложение должно содержать bean ContactDao, который реализует следующие операции - получение всех контактов, получение контакта по идентификатору, добавление контакта (при создании контакту присваивается идентификатор), обновление телефонного номера, обновление email, удаление контакта по идентификатору.
4. ContactDao взаимодействует с локально развернутой Postgres базой данных.

# Spring JDBC: Нестандартные запросы и транзакции

ДЗ

1. Вспоминаем приложение для управления базой данной контактов (Урок Spring REST)
2. Контакт имеет следующие характеристики – Имя и Фамилия, Телефонный номер, Email.
3. Приложение должно содержать bean ContactService, который позволяет загрузить CSV-файл в формате “Имя Фамилия,Номер телефона,Email” в базу данных (пример строки из файла – Иван Иванов,+71234567890,iivanov@gmail.com. Данные должны батчево писаться в базу.
4. ContactDao взаимодействует с локально развернутой Postgres базой данных
