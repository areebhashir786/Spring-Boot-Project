
create database AP
go
use AP
go

create table Person(
	--id int Identity(1,1) primary key,

	email nvarchar(100) primary key,
	name nvarchar(100),
	password nvarchar (100),
	gender nvarchar(100),
	dob nvarchar(100)
);


select * from Person
