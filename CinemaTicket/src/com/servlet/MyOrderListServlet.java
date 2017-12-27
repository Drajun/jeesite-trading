package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.Entity.CinemaEntity;
import com.Entity.MovieEntity;
import com.Entity.OrderEntity;
import com.Entity.UserEntity;
import com.util.MybatisConnectionUtils;
import com.util.OrderByCart;

/**
 * Servlet implementation class MyOrderList
 */
@WebServlet("/MyOrderListServlet")
public class MyOrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private SqlSession s=MybatisConnectionUtils.getSqlSession();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyOrderListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SqlSession s=MybatisConnectionUtils.getSqlSession();
		HttpSession session=request.getSession();
		if(session.getAttribute("role").equals("admin")) {
			response.sendRedirect("/CinemaTicket/IndexServlet");
		}
		else {
			//查询用户ID
			String statement="com.dao.impl.UserDaoImpl.getUserByName";
			UserEntity user=s.selectOne(statement,session.getAttribute("userName"));
			statement="com.dao.impl.OrderDaoImpl.getOrderByUserID";
			List<OrderEntity> orderList=s.selectList(statement,user.getId());
			List<OrderByCart> orderInfo=new ArrayList<>();
			
			for(OrderEntity o :orderList) {
				statement="com.dao.impl.MovieDaoImpl.getMovieByID";
				MovieEntity m=s.selectOne(statement,o.getMovieID());
				statement="com.dao.impl.CinemaDaoImpl.getCinemaByID";
				CinemaEntity c=s.selectOne(statement, o.getCinemaID());
				
				OrderByCart order=new OrderByCart();
				order.setMovieName(m.getName());
				order.setCinemaName(c.getCinemaName());
				order.setPlayTime(o.getPlayTime());
				order.setSeats(o.getSeats());
				order.setTotalPrice(o.getTotalPrice());
				order.setcID(o.getGetNum());
				
				orderInfo.add(order);
			}
			s.close();
			request.setAttribute("orderInfo", orderInfo);
			request.getRequestDispatcher("/OrdinaryPage/MyOrderList.jsp").forward(request, response);
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
