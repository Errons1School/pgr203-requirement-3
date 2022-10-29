create table products(
  id int IDENTITY PRIMARY KEY,
  nameprod varchar(100) not null default ('unknown'),
  catagory varchar(100) not null default ('unknown'),
  img varchar(100) not null default ('unknown'),
  proddesc varchar(100) not null default ('unknown'),
  price int not null default (0),
  stock int not null default (0),
);