CREATE DATABASE Wholesaler;

USE Wholesaler;

CREATE TABLE Supplier (
    Sup_id INT NOT NULL PRIMARY KEY,
    Sup_name VARCHAR(255) NOT NULL,
    Sup_address VARCHAR(255),
    phone VARCHAR(15) NOT NULL
);

CREATE TABLE Customer (
    Cust_id INT NOT NULL PRIMARY KEY,
    f_name VARCHAR(50) NOT NULL,
    m_name VARCHAR(50),
    l_name VARCHAR(50) NOT NULL,
    Cust_address VARCHAR(255),
    Cust_phone VARCHAR(15) NOT NULL
);

CREATE TABLE Product (
    Prod_id INT NOT NULL PRIMARY KEY,
    Prod_name VARCHAR(255) NOT NULL, 
    whole_price DECIMAL(10,2) NOT NULL,
    retail_price DECIMAL(10,2) NOT NULL
);

CREATE TABLE Purchase_order (
    PO_num INT NOT NULL PRIMARY KEY,
    purchase_date DATE NOT NULL 
);

CREATE TABLE Inventory (
    Sup_id INT NOT NULL,
    Prod_id INT NOT NULL,
    Inv_quantity INT NOT NULL,
    PRIMARY KEY (Sup_id, Prod_id),
    FOREIGN KEY (Sup_id) REFERENCES Supplier(Sup_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (Prod_id) REFERENCES Product(Prod_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Purchase (
    PO_num INT NOT NULL,
    Prod_id INT NOT NULL,
    P_quantity INT NOT NULL,
    PRIMARY KEY (PO_num, Prod_id),
    FOREIGN KEY (PO_num) REFERENCES Purchase_order(PO_num)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (Prod_id) REFERENCES Product(Prod_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Transactions (
    Cust_id INT NOT NULL,
    Sup_id INT NOT NULL, 
    PO_num INT NOT NULL,
    PRIMARY KEY (Cust_id, Sup_id, PO_num),
    FOREIGN KEY (Cust_id) REFERENCES Customer(Cust_id)
    ON DELETE CASCADE ON UPDATE CASCADE, 
    FOREIGN KEY (Sup_id) REFERENCES Supplier(Sup_id)
    ON DELETE CASCADE ON UPDATE CASCADE, 
    FOREIGN KEY (PO_num) REFERENCES Purchase_order(PO_num)
    ON DELETE CASCADE ON UPDATE CASCADE
);