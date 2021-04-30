INSERT INTO Supplier 
(`Sup_id`, `Sup_name`, `Sup_address`, `phone`)
VALUES
(1, 'Number One Supplier', '111 First Ave', '111-111-1111'),
(2, 'Twin City Goods', '2 Pear St.', '222-222-0010'),
(3, 'Trinity Trinkets', '11 Jefferson Ct.', '333-011-3333'),
(4, 'ATV Retail', '40 Dollar Way', '100-444-0100'),
(5, 'The Sullivan Bros', '5 Guys Dr.', '555-555-0101'),
(6, 'Kicks', '123 main', '222-555-1212'),
(7, 'Dicks', '33375 Sequoia', '123-456-7890'),
(8, 'Rogue', '1 Rogue Drive', '0293458872');



INSERT INTO Customer
(`Cust_id`, `f_name`, `m_name`, `l_name`, `Cust_address`, `Cust_phone`)
VALUES
(1, 'Gene', 'Hung', 'Li', '360 Skymanor Blvd.', '908-619-1234'),
(2, 'Eric', NULL, 'Fritts', '123 Sesame St.', '212-829-1923'),
(3, 'Bryce', NULL, 'Wager', '720 Space Station Way', '809-914-4312'),
(4, 'Ying', NULL, 'M', '1 Avenue A', '212-995-1845');


INSERT INTO Product
(`Prod_id`, `Prod_name`, `whole_price`, `retail_price`)
VALUES
(11, 'widget', 99.0, 125.0),
(22, 'gewgaw', 12.75, 33.99),
(33, 'whipple-wopple', 0.99, 4.99),
(44, 'zinger zanger', 999, 1099.99),
(55, 'Reebok Nano 9', 75.0, 95.0),
(66, 'Nike Metcon', 100, 125),
(77, 'RPM Session 4', 65, 75),
(88, 'RPM Session 4', 55, 59)
;

INSERT INTO Purchase_order
(`PO_num`, `purchase_date`)
VALUES 
(12345, '2019-01-21'),
(23456, '2020-02-02'),
(34567, '2020-06-28'),
(45678, '2017-01-01'),
(56789, '2018-12-21')
;

INSERT INTO Inventory
(`Sup_id`, `Prod_id`, `Inv_quantity`)
VALUES
(1,11,100),
(1,22,67),
(2,33,456),
(3,11,12),
(3,22,54),
(3,33,87),
(3,44,2),
(4,44,5),
(6,55,100),
(6,66,12),
(6,77,43),
(7,88,4),
(7,77,77)
;

INSERT INTO Purchase
(`PO_num`, `Prod_id`, `P_quantity`)
VALUES
(12345, 11, 12),
(12345, 22, 32),
(23456, 33, 33),
(34567, 44, 6),
(34567, 77, 2),
(45678, 88, 4),
(45678, 66, 8),
(56789, 55, 54)
;


INSERT INTO Transactions
(`Cust_id`, `Sup_id`, `PO_num`)
VALUES
(1,1,12345),
(2,2,23456),
(3,3,34567),
(4,6,45678),
(4,8,56789)
;
