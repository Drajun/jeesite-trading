package com.dao.impl;

import java.security.cert.PKIXRevocationChecker.Option;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Flush;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.Entity.MovieEntity;

/**
 * @author   李家俊(Drajun)
 * @Date     2017年12月20日下午3:12:19
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * 操作电影数据的接口
 * @Version  1.0
 */
public interface MovieDaoImpl {
	//查找所有电影
	@Select("select * from movie")
	@Options(useCache = false,flushCache = Options.FlushCachePolicy.TRUE)
	List<MovieEntity> getAllMovie();
	
	//根据ID查电影
	@Select("select * from movie where id=#{para}")
	MovieEntity getMovieByID(int id);
	
	//根据地区查电影
	@Select("select * from movie where area=#{para}")
	List<MovieEntity> getMovieByArea(String area);
	
	//根据类型ID查电影
	@Select("select * from movie where typeID=#{para}")
	List<MovieEntity> getMovieByType(int typeID);
	
	//根据ID删除电影
	@Delete("delete from movie where id=#{para}")
	@Options(useCache = false,flushCache = Options.FlushCachePolicy.TRUE)
	int DeleteMovieByID(int id); 
	
	//添加新电影
	@Insert("Insert into movie(`name`,`typeID`,`imgUrl`,`area`,`showTime`,`price`,`remarks`) value(#{name},#{typeID},#{imgUrl},#{area},#{showTime},#{price},#{remarks})")
	@Options(useCache = false,flushCache = Options.FlushCachePolicy.TRUE)
	int InsertNewMovie(MovieEntity movie);
	
	//修改电影
	@Update("update movie set name=#{name},typeID=#{typeID},imgUrl=#{imgUrl},area=#{area},showTime=#{showTime},price=#{price},remarks=#{remarks} where id=#{id}")
	int UpdateMovie(MovieEntity movie);
	
	//根据类型ID和地区搜索
	@Select("select * from movie where typeID=#{typeID} and area=#{area}")
	List<MovieEntity> getMovieByTypeIDAndArea(@Param("typeID")int typeID,@Param("area")String area);
	
	//获取电影的全部地区
	@Select("select area from movie")
	List<String> getAllArea();
	
	//根据电影名称模糊搜索电影
	@Select("select * from movie where name like #{name}")
	List<MovieEntity> getMovieByName(@Param("name")String name);
	
}
