package com.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.Entity.AreaEntity;

/**
 * @author   李家俊(Drajun)
 * @Date     2017年12月20日下午3:06:20
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * 操作地区数据的接口
 * @Version  1.0
 */
public interface AreaDaoImpl {
	//查找所有地区
	@Select("select * from area")
	List<AreaEntity> getAllArea()throws SQLException;
	
	//根据ID查找地区
	@Select("select * from area where id=#{para}")
	AreaEntity getAreaByID(int id)throws SQLException;
	
	//根据市查找地区
	@Select("select * from area where state=#{para}")
	List<AreaEntity> getAreaByState(String state)throws SQLException;
	
	//根据地区名称查地区
	@Select("select * from area where areaName=#{para}")
	AreaEntity getAreaByName(String name);
}
