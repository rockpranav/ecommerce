create database ecommerce;
use ecommerce;
Create table category(
	id bigint primary key not null,
	name varchar(50) not null,
	code varchar(50) not null,
	description varchar(255),
	unique(name,code)
);

create table product(
	id bigint primary key not null,
	name varchar(50) not null,
	code varchar(50) not null,
	description varchar(255),
    category_id bigint not null,
	unique(name,code),
	constraint foreign key(category_id) references category(id)
);
