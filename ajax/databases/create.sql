use shop;


create table t_data(
  id int primary key auto_increment,
  spell varchar(20),
  message varchar(100)
)engine=Innodb default charset=utf8;


insert into t_data values(1,'html','Hyper Text Markup Language');
insert into t_data values(2,'css','Cascading Style Sheet');
insert into t_data values(3,'js','JavaScript');
insert into t_data values(4,'dom','Document Object Model');
insert into t_data values(5,'jsp','Java Server Page');
insert into t_data values(6,'ajax','Asynchronous JavaScript And Xml');
insert into t_data values(7,'json','JavaScript Object Notation');
insert into t_data values(8,'asd','aaaaa sssss dddd');
insert into t_data values(9,'pom','Project Object Model');
insert into t_data values(10,'ssm','Spring Springmvc Mybatis');
insert into t_data values(11,'ssh','Spring Springmvc Hibernate');

