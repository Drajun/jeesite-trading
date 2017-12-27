package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Entity.CinemaEntity;
import com.dao.impl.CinemaDaoImpl;
import com.jdbc.ConnectionFactory;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class CinemaDap implements CinemaDaoImpl {
	private Connection conn=(Connection) ConnectionFactory.getConnection();
	
	@Override
	public List<CinemaEntity> getALLCinema() throws SQLException{
		List<CinemaEntity> cinemaList=new ArrayList<>();
		final String sql="select * from cinema";
		PreparedStatement ps=(PreparedStatement) conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			CinemaEntity cinema=this.cinemaDate(rs);
			cinemaList.add(cinema);
		}
		return cinemaList;
	}

	@Override
	public CinemaEntity getCinemaByID(int id) throws SQLException{
		final String sql="select * from cinema where `id`=?";
		PreparedStatement ps=(PreparedStatement) conn.prepareStatement(sql);
		ps.setInt(1,id);
		ResultSet rs=ps.executeQuery();
		CinemaEntity cinema=null;
		while(rs.next()) {
			cinema=this.cinemaDate(rs);
		}
		return cinema;
	}

	@Override
	public List<CinemaEntity> getCinemaByArea(int areaID) throws SQLException{
		List<CinemaEntity> cinemaList=new ArrayList<>();
		final String sql="select * from cinema where `areaID`=?";
		PreparedStatement ps=(PreparedStatement) conn.prepareStatement(sql);
		ps.setInt(1, areaID);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			CinemaEntity cinema=this.cinemaDate(rs);
			cinemaList.add(cinema);
		}
		return cinemaList;
	}
	
	private CinemaEntity cinemaDate(ResultSet rs)throws SQLException {
		CinemaEntity cinema =new CinemaEntity();
		cinema.setId(rs.getInt(1));
		cinema.setCinemaName(rs.getString(2));
		cinema.setAreaID(rs.getInt(3));
		cinema.setAddress(rs.getString(4));
		cinema.setPhone(rs.getString(5));
		cinema.setReamrks(rs.getString(6));
		return cinema;
	}

	@Override
	public CinemaEntity getCinemaByName(String cinemaName) {
		// TODO 自动生成的方法存根
		return null;
	}
}
