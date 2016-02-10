CREATE DATABASE Shopping;

CREATE TABLE Users
(
username VARCHAR(20) NOT NULL,
password VARCHAR(20) NOT NULL, 
firstname VARCHAR(20), 
lastname VARCHAR(20), 
email VARCHAR(50),
CONSTRAINT pk_UsersID PRIMARY KEY (username)
);

CREATE TABLE Products
(
SKU VARCHAR(20) NOT NULL,
description VARCHAR(20), 
category VARCHAR(20), 
price FLOAT, 
stock INT,
CONSTRAINT pk_ProductsID PRIMARY KEY (SKU)
);

CREATE TABLE Carts
(
username VARCHAR(20) NOT NULL, 
SKU VARCHAR(20) NOT NULL,
quantity INT,
CONSTRAINT pk_CartsID PRIMARY KEY (username,SKU)
);
