package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.Entity.OrderEntity;
import com.dao.impl.OrderDaoImpl;
import com.mysql.jdbc.PreparedStatement;
import com.util.MybatisConnectionUtils;

public class OrderDao implements OrderDaoImpl {
	private SqlSession s=MybatisConnectionUtils.getSqlSession();
	@Override
	public List<OrderEntity> getAllOrder() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public OrderEntity getOrderByID(int id) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public List<OrderEntity> getOrderByUserID(int buyerID) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	//获得已选购得座位
	public List<String> getSeatsByMovieIDAndPlayTimeAndCinemaID(int movieID, String playTime, int cinemaID) throws SQLException {
		// TODO 自动生成的方法存根
		final String sql= "select seats from orderList where movieID=? and playTime=? and cinemaID=?";
		PreparedStatement ps=(PreparedStatement) s.getConnection().prepareStatement(sql);
		ps.setInt(1, movieID);
		ps.setString(2, playTime);
		ps.setInt(3, cinemaID);
		ResultSet rs=ps.executeQuery();
		List<String> orderList=new ArrayList<>();
		while(rs.next()) {
			orderList.add(rs.getString("seats"));
		}
		return orderList;
	}

	@Override
	public int insertNewOrder(OrderEntity order) {
		// TODO 自动生成的方法存根
		return 0;
	}

}
