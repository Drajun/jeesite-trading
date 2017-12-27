package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Entity.AreaEntity;
import com.dao.impl.AreaDaoImpl;
import com.jdbc.ConnectionFactory;

public class AreaDao implements AreaDaoImpl {
	private Connection conn = ConnectionFactory.getConnection();
	
	@Override
	public List<AreaEntity> getAllArea() throws SQLException {
		List<AreaEntity> areaList=new ArrayList<>();
		final String sql="select * from area";
		PreparedStatement ps=conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			AreaEntity area=this.areaData(rs);
			areaList.add(area);
		}
		return areaList;
	}

	@Override
	public AreaEntity getAreaByID(int id)throws SQLException {
		final String sql="select * from area where `id`=?";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setInt(1,id);
		ResultSet rs= ps.executeQuery();
		AreaEntity area=null;
		while(rs.next()) {
			area=this.areaData(rs);
		}
		return area;
	}

	@Override
	public List<AreaEntity> getAreaByState(String state)throws SQLException {
		List<AreaEntity> areaList=new ArrayList<>();
		final String sql="select * from area where state=?";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, state);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			AreaEntity area=this.areaData(rs);
			areaList.add(area);
		}
		return areaList;
	}
	
	private AreaEntity areaData(ResultSet rs) throws SQLException {
		AreaEntity area=new AreaEntity();
		area.setId(rs.getInt(1));
		area.setAreaName(rs.getString(1));
		area.setState(rs.getString(3));
		return area;
	}

	@Override
	public AreaEntity getAreaByName(String name) {
		// TODO 自动生成的方法存根
		return null;
	}
}
