package com.dao.impl;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.Entity.UserEntity;

/**
 * @author   李家俊(Drajun)
 * @Date     2017年12月20日下午3:02:23
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * 操作用户数据接口
 * @Version  1.0
 */
public interface UserDaoImpl {
	//返回所有用户
	@Select("select * from user")
	List<UserEntity> getAllUser();
	
	//根据id查找用户
	@Select("select * from user where id=#{para}")
	UserEntity getUserByID(int id);
	
	//根据用户名查找用户
	@Select("select * from user where userName=#{para}")
	UserEntity getUserByName(String Name);
	
	//根据用户名查找用户id
	@Select("select id from user where userName=#{para}")
	int getUserIDByName(String name);
}
