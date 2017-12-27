package com.dao.impl;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.Entity.TypeEntity;

/**
 * @author   李家俊(Drajun)
 * @Date     2017年12月20日下午3:16:02
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * 操作电影类型数据的接口
 * @Version  1.0
 */
public interface TypeDaoImpl {
	//返回所有类型
	@Select("select * from type")
	List<TypeEntity> getAllType();
	
	//根据id查类型
	@Select("select * from type where id=#{para}")
	TypeEntity getTypeByID(int id);
	
	//根据类型名查询
	@Select("select * from type where typeName=#{para}")
	TypeEntity getTypeByName(String name);
}
