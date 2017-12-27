package com.dao.impl;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.Entity.RolesEntity;

/**
 * @author   李家俊(Drajun)
 * @Date     2017年12月20日下午2:57:52
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * 操作角色数据的接口
 * @Version  1.0
 */
public interface RolesDaoImpl {
	//查询所有角色
	@Select("select * from roles")
	List<RolesEntity> getAllRoles(); 
	
	//根据ID查询角色
	@Select("select * from roles where id=#{para}")
	RolesEntity getRoleByID(int id);
}
