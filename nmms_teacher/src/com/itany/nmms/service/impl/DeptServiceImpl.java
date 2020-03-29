package com.itany.nmms.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itany.nmms.constant.DictConstant;
import com.itany.nmms.constant.StatusConstant;
import com.itany.nmms.dao.DeptMapper;
import com.itany.nmms.dao.SequenceDao;
import com.itany.nmms.entity.Dept;
import com.itany.nmms.entity.DeptExample;
import com.itany.nmms.entity.Sequence;
import com.itany.nmms.entity.Staff;
import com.itany.nmms.exception.DeptException;
import com.itany.nmms.exception.DeptExistException;
import com.itany.nmms.exception.ParameterException;
import com.itany.nmms.factory.ObjectFactory;
import com.itany.nmms.service.DeptService;
import com.itany.nmms.util.ParameterUtil;

public class DeptServiceImpl implements DeptService{

	public void addFatherDept(String name, String remark, Staff staff) throws DeptExistException {
		DeptMapper deptMapper = (DeptMapper) ObjectFactory.getObject("deptMapper");
		SequenceDao sequenceDao = (SequenceDao) ObjectFactory.getObject("sequenceDao");
		
		DeptExample example = new DeptExample();
		example.or()
			   .andDeptNameEqualTo(name);
		List<Dept> selectDepts = deptMapper.selectByExample(example);
		if(!selectDepts.isEmpty()){
			throw new DeptExistException("该部门名称已经存在");
		}
			   
		
		Dept dept = new Dept();
		dept.setDeptName(name);
		dept.setRemark(remark);
		dept.setCreateDate(new Date());
		dept.setCreateStaffId(staff.getStaffId());
		dept.setIsValid(StatusConstant.IS_VALID_TRUE);
		
		//生成对应的部门编号
		Sequence selectSequence = sequenceDao.selectByName(DictConstant.DEPT_NO_PREFIX);
		if(selectSequence == null){
			Sequence sequence = new Sequence();
			sequence.setName(DictConstant.DEPT_NO_PREFIX);
			sequence.setValue(DictConstant.DEPT_NO_INIT_VALUE);
			sequenceDao.insertSequence(sequence);
			
			//将商品编号交给DEPT
			dept.setDeptNo(DictConstant.DEPT_NO_PREFIX+new SimpleDateFormat("yyyyMMdd").format(new Date())+DictConstant.DEPT_NO_INIT_VALUE);
		}else{
			String value = selectSequence.getValue();
			//若查询到对应的序列号信息
			//则根据当前的值判断下一个值
			//如果值为999999,则下一个值为000001
			//否则下一个值为当前值+1
			if(DictConstant.DEPT_NO_MAX_VALUE.equals(value)){
				value = DictConstant.DEPT_NO_INIT_VALUE;
			}else{
				value = String.format("%06d", Integer.parseInt(value)+1);
			}
			sequenceDao.updateValueByName(selectSequence.getName(), value);
			
			//将商品编号交给DEPT
			dept.setDeptNo(DictConstant.DEPT_NO_PREFIX+new SimpleDateFormat("yyyyMMdd").format(new Date())+value);
		}
		
		deptMapper.insertSelective(dept);
		
	}

	public List<Dept> findAll() {
		DeptMapper deptMapper = (DeptMapper) ObjectFactory.getObject("deptMapper");
		return deptMapper.selectAll();
	}

	public void addSonDept(String fatherId, String name, String remark,
			Staff staff) throws DeptExistException {
		DeptMapper deptMapper = (DeptMapper) ObjectFactory.getObject("deptMapper");
		SequenceDao sequenceDao = (SequenceDao) ObjectFactory.getObject("sequenceDao");
		
		DeptExample example = new DeptExample();
		example.or()
			   .andDeptNameEqualTo(name);
		List<Dept> selectDepts = deptMapper.selectByExample(example);
		if(!selectDepts.isEmpty()){
			throw new DeptExistException("该部门名称已经存在");
		}
			   
		
		Dept dept = new Dept();
		dept.setDeptName(name);
		dept.setRemark(remark);
		dept.setFatherDeptId(Integer.parseInt(fatherId));
		dept.setCreateDate(new Date());
		dept.setCreateStaffId(staff.getStaffId());
		dept.setIsValid(StatusConstant.IS_VALID_TRUE);
		
		//生成对应的部门编号
		Sequence selectSequence = sequenceDao.selectByName(DictConstant.DEPT_NO_PREFIX);
		if(selectSequence == null){
			Sequence sequence = new Sequence();
			sequence.setName(DictConstant.DEPT_NO_PREFIX);
			sequence.setValue(DictConstant.DEPT_NO_INIT_VALUE);
			sequenceDao.insertSequence(sequence);
			
			//将商品编号交给DEPT
			dept.setDeptNo(DictConstant.DEPT_NO_PREFIX+new SimpleDateFormat("yyyyMMdd").format(new Date())+DictConstant.DEPT_NO_INIT_VALUE);
		}else{
			String value = selectSequence.getValue();
			//若查询到对应的序列号信息
			//则根据当前的值判断下一个值
			//如果值为999999,则下一个值为000001
			//否则下一个值为当前值+1
			if(DictConstant.DEPT_NO_MAX_VALUE.equals(value)){
				value = DictConstant.DEPT_NO_INIT_VALUE;
			}else{
				value = String.format("%06d", Integer.parseInt(value)+1);
			}
			sequenceDao.updateValueByName(selectSequence.getName(), value);
			
			//将商品编号交给DEPT
			dept.setDeptNo(DictConstant.DEPT_NO_PREFIX+new SimpleDateFormat("yyyyMMdd").format(new Date())+value);
		}
		
		deptMapper.insertSelective(dept);
	}

	public Dept findById(String id) throws ParameterException {
		DeptMapper deptMapper = (DeptMapper) ObjectFactory.getObject("deptMapper");
		if(ParameterUtil.isNull(id)){
			throw new ParameterException("部门id不能为空");
		}
		Dept dept = deptMapper.selectByPrimaryKey(Integer.parseInt(id));
		return dept;
	}

	public void modifyDept(String id, String name, String remark) throws DeptExistException {
		DeptMapper deptMapper = (DeptMapper) ObjectFactory.getObject("deptMapper");
		DeptExample example = new DeptExample();
		example.or()
			   .andDeptNameEqualTo(name)
			   .andDeptIdNotEqualTo(Integer.parseInt(id));
		
		List<Dept> selectDepts = deptMapper.selectByExample(example);
		if(!selectDepts.isEmpty()){
			throw new DeptExistException("该部门已经存在");
		}
		
		Dept dept = new Dept();
		dept.setDeptId(Integer.parseInt(id));
		dept.setDeptName(name);
		dept.setRemark(remark);
		deptMapper.updateByPrimaryKeySelective(dept);
	}

	public void modifyStatus(String id) throws DeptException {
		DeptMapper deptMapper = (DeptMapper) ObjectFactory.getObject("deptMapper");
		Dept selectDept = deptMapper.selectByPrimaryKey(Integer.parseInt(id));
		Integer status = selectDept.getIsValid();
		if(status == StatusConstant.IS_VALID_TRUE){
			selectDept.setIsValid(StatusConstant.IS_VALID_FALSE);
			
			//当当前部门为有效时，执行禁用功能
			deptMapper.updateByPrimaryKeySelective(selectDept);
			//禁用的同时需要将其所有的子部门同时禁用
			//查询所有子部门信息
			List<Dept> sonDepts = findSonDepts(Integer.parseInt(id));
			for(Dept dept : sonDepts){
				dept.setIsValid(StatusConstant.IS_VALID_FALSE);
				deptMapper.updateByPrimaryKeySelective(dept);
			}
			
		}else{
			//否则执行启用功能
			if(selectDept.getFatherDeptId() != null){
				Dept fatherDept = deptMapper.selectByPrimaryKey(selectDept.getFatherDeptId());
				if(fatherDept.getIsValid() == StatusConstant.IS_VALID_FALSE){
					throw new DeptException("上级部门为禁用，无法启用子部门");
				}
			}
			selectDept.setIsValid(StatusConstant.IS_VALID_TRUE);
			deptMapper.updateByPrimaryKeySelective(selectDept);
		}
		
		
	}
	
	public List<Dept> findSonDepts(Integer id){
		DeptMapper deptMapper = (DeptMapper) ObjectFactory.getObject("deptMapper");
		List<Dept> depts = new ArrayList<Dept>();
		DeptExample example = new DeptExample();
		example.or()
			   .andFatherDeptIdEqualTo(id);
		List<Dept> selectDepts = deptMapper.selectByExample(example);
		if(!selectDepts.isEmpty()){
			depts.addAll(selectDepts);
			for(Dept dept : selectDepts){
				List<Dept> sonDepts = findSonDepts(dept.getDeptId());
				if(!sonDepts.isEmpty()){
					depts.addAll(sonDepts);
				}
			}
		}
		return depts;
	}

	public List<Dept> findEnable() {
		DeptMapper deptMapper = (DeptMapper) ObjectFactory.getObject("deptMapper");
		DeptExample example = new DeptExample();
		example.or()
			   .andIsValidEqualTo(StatusConstant.IS_VALID_TRUE);
		List<Dept> depts = deptMapper.selectByExample(example);
		return depts;
	}

}
