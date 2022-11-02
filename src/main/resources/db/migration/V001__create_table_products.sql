create table products (
    id int IDENTITY PRIMARY KEY,
    name varchar(1000),
    category varchar(1000),
    img varchar(1000),
    description varchar(8000),
    price int,
    stock int
);