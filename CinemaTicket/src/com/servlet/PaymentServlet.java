package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.Entity.AreaEntity;
import com.Entity.CinemaEntity;
import com.Entity.MovieEntity;
import com.util.MybatisConnectionUtils;
import com.util.OrderByCart;

/**
 * Servlet implementation class PayMentServlet
 */
@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private SqlSession s=MybatisConnectionUtils.getSqlSession();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SqlSession s=MybatisConnectionUtils.getSqlSession();
		
		String cinemaName=request.getParameter("cinemaName");
		String movieID=request.getParameter("movieID");
		String areaName=request.getParameter("area");
		String playTime=request.getParameter("playTime");
		String seats=request.getParameter("seats");
		String movieTotalPrice=request.getParameter("movieTotalPrice");
		
		if(playTime!=""&&cinemaName!=""&&movieID!=""&&areaName!=""&&seats!=""&&movieTotalPrice!="") {
			try {
				//获取所购买的电影信息
				String statement="com.dao.impl.MovieDaoImpl.getMovieByID";
				MovieEntity movie=s.selectOne(statement, Integer.parseInt(movieID));
				request.setAttribute("movie", movie);
				
				//获取电影院信息
				statement="com.dao.impl.CinemaDaoImpl.getCinemaByName";
				CinemaEntity cinema=s.selectOne(statement,cinemaName);
				request.setAttribute("cinema", cinema);
				
				//获取地区信息
				statement="com.dao.impl.AreaDaoImpl.getAreaByName";
				AreaEntity area=s.selectOne(statement, areaName);
				request.setAttribute("area", area);
				
				//封装场次、座位、总价等信息
				request.setAttribute("playTime", playTime);
				request.setAttribute("seats", seats);
				request.setAttribute("movieTotalPrice", movieTotalPrice);
				
				//封装订单信息
				int numID=playTime.hashCode()+seats.hashCode()+cinema.getCinemaName().hashCode();
				int getNum=numID>0?numID/10000:numID*-1/10000;
				List<OrderByCart> orders=new ArrayList<>();
				OrderByCart oInfo=new OrderByCart(movie.getId(), movie.getName(), seats, playTime, Float.parseFloat(movieTotalPrice), 
						cinema.getCinemaName(), area.getAreaName(), cinema.getId(), getNum, movie.getImgUrl());
				orders.add(oInfo);
				request.setAttribute("orders", orders);
				s.close();
				request.getRequestDispatcher("/OrdinaryPage/Payment.jsp").forward(request, response);
			}
			catch(NumberFormatException e) {
				request.setAttribute("exceptionInfo", "非法的ID");
				request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
				e.printStackTrace();
			}
			catch(Exception e) {
				request.setAttribute("exceptionInfo", "查询目标数据错误");
				request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
				e.printStackTrace();
			}
		}
		else {
			request.setAttribute("exceptionInfo", "参数不能为空");
			request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
