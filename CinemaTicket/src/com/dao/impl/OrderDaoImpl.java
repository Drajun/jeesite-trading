package com.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.Entity.OrderEntity;

/**
 * @author   李家俊(Drajun)
 * @Date     2017年12月20日下午3:17:34
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * 操作订单数据的接口
 * @Version  1.0
 */
public interface OrderDaoImpl {
	//返回所有订单
	@Select("select * from orderList")
	List<OrderEntity> getAllOrder();
	
	//根据ID查订单
	@Select("select * from orderList where id=#{para}")
	OrderEntity getOrderByID(int id);
	
	//根据用户id查订单
	@Select("select * from orderList where buyerID=#{para}")
	List<OrderEntity> getOrderByUserID(int buyerID);
	
	//获取已选购的座位
	List<String> getSeatsByMovieIDAndPlayTimeAndCinemaID(@Param("movieID")int movieID,@Param("playTime")String playTime,@Param("cinemaID")int cinemaID)throws SQLException;
	
	//插入新订单
	@Insert("insert into orderlist(`movieID`,`buyerID`,`seats`,`playTime`,`totalPrice`,`getNum`,`cinemaID`) value(#{movieID},#{buyerID},#{seats},#{playTime},#{totalPrice},#{getNum},#{cinemaID})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insertNewOrder(@Param("order")OrderEntity order);
}
