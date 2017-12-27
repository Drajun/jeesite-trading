package com.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.Entity.CinemaEntity;

/**
 * @author   李家俊(Drajun)
 * @Date     2017年12月20日下午3:09:12
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * 操作电影院数据的接口
 * @Version  1.0
 */
public interface CinemaDaoImpl {
	//返回所有电影院
	@Select("select * from cinema")
	List<CinemaEntity> getALLCinema()throws SQLException;
	
	//根据id查找电影院
	@Select("select * from cinema where id=#{para}")
	CinemaEntity getCinemaByID(int id)throws SQLException;
	
	//根据影院名和地区查电影院
	@Select("select * from cinema where cinemaName=#{name}")
	CinemaEntity getCinemaByName(@Param("name")String cinemaName);
	
	//根据地区查找影院
	@Select("select * from cinema where areaID=#{para}")
	List<CinemaEntity> getCinemaByArea(int areaID)throws SQLException;
	
}
