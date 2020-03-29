insert into t_user(id,login_name,nickname,password,role,email,login_date,create_date,status)
values (10,1,1,1,'normal',1,'2018-11-11 10:33:59' , '2018-10-17 15:28:03' ,0);
insert into t_user(id,login_name,nickname,password,role,email,login_date,create_date,status)
values (20,2,2,2,'normal',2,'2018-11-12 09:20:49' , '2018-11-12 10:34:03' ,0);

insert into t_course_type values(null,'java',null,0);
insert into t_course_type values(null,'ios',null,0);

insert into t_resource(id,title,file_type,click_count,create_date,user_id,status)
values(10,'a','mp4',0,'2018-11-10 07:26:02',10,0);
insert into t_resource(id,title,file_type,click_count,create_date,user_id,status)
values(20,'b','mp4',0,'2018-11-12 10:33:59',20,0);

insert into t_user_resource values(null,10,10,null,null);
insert into t_user_resource values(null,20,20,null,null);
insert into t_comment values (null,'111','2018-09-10 06:44:03',10,10,2);
insert into t_comment values (null,'aaa','2018-10-11 09:17:41',10,10,2);
insert into t_comment values (null,'bbb','2018-11-12 10:33:59',20,20,2);