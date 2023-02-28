DROP TABLE IF EXISTS CONTACT;

CREATE TABLE CONTACT(
    ID serial primary key,
    FIRSTNAME varchar(255),
    SURNAME varchar(255),
    EMAIL varchar (255),
    PHONE_NUMBER varchar (255)
);

INSERT INTO CONTACT(ID, FIRSTNAME, SURNAME, EMAIL, PHONE_NUMBER)
VALUES (1000, 'Ivan', 'Ivanov', 'iivanov@gmail.com', '1234567');

INSERT INTO CONTACT(ID, FIRSTNAME, SURNAME, EMAIL, PHONE_NUMBER)
VALUES (2000, 'Maria', 'Ivanova', 'mivanova@gmail.com', '7654321');

INSERT INTO CONTACT (FIRSTNAME, SURNAME, EMAIL, PHONE_NUMBER)
VALUES ('Cris', 'Tacker', 'c_tacker@gmail.com', '555-555-55-55');

select * from contact;