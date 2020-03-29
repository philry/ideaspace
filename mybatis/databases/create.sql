drop database if exists mybatis;
create database mybatis;
use mybatis;

create table t_user(
  id int primary key auto_increment,
  username varchar(20),
  password varchar(50),
  phone varchar(20),
  address varchar(100)
)engine=Innodb default charset=utf8;

create table t_user2(
  user_id int primary key auto_increment,
  user_username varchar(20),
  user_password varchar(50),
  user_phone varchar(20),
  user_address varchar(100)
)engine=Innodb default charset=utf8;

insert into t_user2 values(1,'admin','admin','13812345678','江苏-南京');


create table t_dept(
  id int primary key auto_increment,
  name varchar (20)
) engine=Innodb default charset=utf8;

create table t_emp(
  id int primary key auto_increment,
  name varchar (20),
  salary double,
  dept_id int,
  foreign key (dept_id) references t_dept(id)
) engine=Innodb default charset=utf8;


select e.id,e.name,e.salary,d.id,d.name
from t_emp e
join t_dept d
  on e.dept_id = d.id
where e.id = 1;

