drop table if exists account;

create table account (
    ID serial primary key,
    AMOUNT bigint
);

INSERT INTO account(ID, AMOUNT)
VALUES (1000, 10000);

INSERT INTO account(ID, AMOUNT)
VALUES (2000, 15000);

select * from account;