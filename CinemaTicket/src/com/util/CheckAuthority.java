package com.util;

import org.apache.ibatis.session.SqlSession;

import com.Entity.UserEntity;

/**
 * @author   李家俊(Drajun)
 * @Date     2017年12月24日下午5:24:31
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * 账号密码、权限判断
 * @Version  1.0
 */
public class CheckAuthority {
	public static Boolean isLoginSuccess=false;
	
	public static UserEntity check(SqlSession s,String account,String pwd) {
		String statement="com.dao.impl.UserDaoImpl.getUserByName";
		UserEntity user=s.selectOne(statement,account);
		if(pwd.equals(user.getPassword())) {
			isLoginSuccess=true;
			return user;
		}
		else {
			return null;			
		}
	}
}
