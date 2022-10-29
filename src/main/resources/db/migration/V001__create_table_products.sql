create table products
(
    idprod int IDENTITY PRIMARY KEY,
    nameprod varchar(100),
    catagory varchar(100),
    img varchar(100),
    proddesc varchar(100),
    price int,
    stock int
);