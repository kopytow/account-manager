DROP TABLE IF EXISTS CONTACT;

CREATE TABLE CONTACT (
    ID serial primary key,
    _NAME varchar(255),
    SURNAME varchar(255),
    EMAIL varchar (255),
    PHONE_NUMBER varchar (255)
);

INSERT INTO CONTACT(ID, _NAME, SURNAME, EMAIL, PHONE_NUMBER)
VALUES (1000, 'Федосий', 'Брежнев', 'FedosiyBrezhnev@gmail.com', '+7 (977) 677-95-44');

INSERT INTO CONTACT(ID, _NAME, SURNAME, EMAIL, PHONE_NUMBER)
VALUES (2000, 'Екатерина', 'Зимина', 'EkaterinaZimina@gmail.com', '+7 (967) 874-83-33');

select * from contact;
