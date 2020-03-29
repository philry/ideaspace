drop database if exists mybatis;
create database mybatis;
use mybatis;
create table t_user(
  id int primary key auto_increment,
  username varchar(20),
  password varchar(50),
  phone varchar(20),
  address varchar(100)
)engine=Innodb default charset utf8;

create table t_dept(
  id int primary key auto_increment,
  name varchar(20)
)engine=Innodb default charset utf8;
create table t_emp(
  id int primary key auto_increment,
  name varchar(20),
  salary Double,
  dept_id int ,
  foreign key(dept_id) references t_dept(id)
)engine=Innodb default charset utf8;
