CREATE DATABASE SMS;

USE SMS;

CREATE TABLE Customer (
	customer_id int primary key,
    customer_name nvarchar(255) not null ,
    status int default 1
);

CREATE TABLE Employee (
	employee_id int primary key,
    employee_name varchar(255) not null,
    salary double not null,
    spvld int not null,
    status int default 1
);

CREATE TABLE Product (
	product_id int primary key,
    product_name varchar(255) not null,
    list_price int not null,
    status int default 1
);

CREATE TABLE Orders (
	order_id int primary key ,
    order_date date not null ,
    total double not null ,
    customer_id int not null ,
    employee_id int not null ,
    constraint fk_cus foreign key (customer_id) references Customer(customer_id) ,
    constraint fk_emp foreign key (employee_id) references Employee (employee_id)
    
);

CREATE TABLE LineItem (
	order_Id int not null ,
    product_id int not null,
    quantity int not null,
    price double not null ,
    primary key (order_Id , product_id),
    constraint fk_or foreign key (order_Id) references Orders(order_Id) ,
    constraint fk_pro foreign key (product_id) references Product (product_id)
    
);

/* insert data */

drop table Customer;
insert into Customer (customer_id,customer_name)
	values (1, 'Henry');
insert into Employee (employee_id,employee_name,salary,spvld) 
	values (1 , 'Nguyen Van A' , 200, 10);
insert into Product (product_id,product_name,list_price)
	values (15 , 'snacks', 15);
insert into Orders (order_id,order_date,total,customer_id,employee_id)
	values (22 , '2000-01-01', 10,1 , 1);
insert into LineItem (order_Id , product_id , quantity , price) 
	values (12,12,10,10);
 

ALTER TABLE orders
alter total set default 0.0;

DELIMITER //
CREATE procedure AddCustomer (in customer_id int , in customer_name nvarchar(255))
begin 
	insert into Customer (customer_id,customer_name)
	values (customer_id, customer_name);
end//
DELIMITER ;


DELIMITER //
CREATE procedure DeleteCustomer (in id int) 
begin
	update Customer
    set customer_status = 0
    where customer_id = id;
end//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE UpdateCustomer (in CustomerId int,in CustomerName varchar(255))
begin
	update customer
    set customer_name = CustomerName
    where customer_id = CustomerId;
end//
DELIMITER ;

