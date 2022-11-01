create table products
(
    idprod int IDENTITY PRIMARY KEY,
    nameprod varchar(1000),
    catagory varchar(1000),
    img varchar(1000),
    proddesc varchar(8000),
    price int,
    stock int
);