package com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.Entity.CinemaEntity;
import com.Entity.MovieEntity;
import com.util.CartInfo;
import com.util.MybatisConnectionUtils;
import com.util.OrderByCart;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SqlSession s=MybatisConnectionUtils.getSqlSession();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cinemaName=request.getParameter("cinemaName");
		String movieID=request.getParameter("movieID");
		String areaName=request.getParameter("area");
		String playTime=request.getParameter("playTime");
		String seats=request.getParameter("seats");
		String movieTotalPrice=request.getParameter("movieTotalPrice");
		
		if(playTime!=null&&cinemaName!=null&&movieID!=null&&areaName!=null&&seats!=null&&movieTotalPrice!=null
				&&playTime!=""&&cinemaName!=""&&movieID!=""&&areaName!=""&&seats!=""&&movieTotalPrice!="") {
			try {
				//获取电影信息
				String statement="com.dao.impl.MovieDaoImpl.getMovieByID";
				MovieEntity movie=s.selectOne(statement, Integer.parseInt(movieID));
				
				//获取电影院信息
				statement="com.dao.impl.CinemaDaoImpl.getCinemaByName";
				CinemaEntity cinema=s.selectOne(statement,cinemaName);
				
				//封装进购物车
				int cartID=(cinema.getCinemaName().hashCode()+seats.hashCode()+playTime.hashCode());
				int getNum=cartID>0?cartID/10000:cartID*-1/10000;
				OrderByCart oc=new OrderByCart(movie.getId(),movie.getName(),seats,playTime,
						Float.parseFloat(movieTotalPrice),cinema.getCinemaName(),areaName,cinema.getId(),getNum,movie.getImgUrl());
				CartInfo.cart.put(getNum, oc);
				CartInfo.cartList=new ArrayList<>(CartInfo.cart.values());
				request.setAttribute("cartList", CartInfo.cartList);
				
				request.getRequestDispatcher("/OrdinaryPage/Cart.jsp").forward(request, response);
			}
			catch(NumberFormatException e) {
				request.setAttribute("exceptionInfo", "错误的数字");
				request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
				e.printStackTrace();
			}
			catch(Exception e) {
				request.setAttribute("exceptionInfo", "获取数据出错");
				request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
				e.printStackTrace();
			}
		}
		else {
			CartInfo.cartList=new ArrayList<>(CartInfo.cart.values());
			request.getRequestDispatcher("/OrdinaryPage/Cart.jsp").forward(request, response);
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
