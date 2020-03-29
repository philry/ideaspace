select sd.dept_id,sd.dept_no,sd.dept_name,sd.remark,
	   fd.dept_name,sd.is_valid
from sys_dept sd
left join sys_dept fd
	on sd.father_dept_id = fd.dept_id
	
	
select s.staff_id,s.staff_name,s.login_name,s.phone,
	   s.email,d.dept_id,d.dept_name,s.is_valid,s.role
from sys_staff s
left join sys_dept d
	on s.dept_id = d.dept_id