package com.util;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConnectionUtils {
	// 得到连接 
	
	// 得到一个Mybatis自带连接工厂 
	public static SqlSessionFactory sqlSessionFactory ;  // null 
	
	// 在类加载的时候就应该去获取Mybatis的连接工厂 
	// java有五大成分 
	// 成员变量 方法 构造器 代码块 内部类
	static{
		try {
			// 类一加载静态代码块立即执行  
			// 加载配置文件然后得到连接工厂 
			// 先把配置文件读进来 
//			File srcFile = new File("src/mybatis-config.xml");
//			InputStream is = new FileInputStream(srcFile);
			InputStream inputStream = Resources.getResourceAsStream("myBatis-conf.xml");
			// is就代表了mybatis-config.xml文件。
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// SqlSession == Connection 
	public static SqlSession getSqlSession(){
		try {
			return sqlSessionFactory.openSession() ;
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}
	}
	
	/**
	 * 关闭资源的
	 * @param session
	 */
	public static void close(SqlSession session){
		try {
			if(session!=null)session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(getSqlSession());
	}
	
	
	
	
}



