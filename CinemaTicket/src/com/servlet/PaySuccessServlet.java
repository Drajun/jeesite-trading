package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.tomcat.util.buf.UEncoder;

import com.Entity.OrderEntity;
import com.Entity.UserEntity;
import com.util.MybatisConnectionUtils;

/**
 * Servlet implementation class PaySuccessServlet
 */
@WebServlet("/PaySuccessServlet")
public class PaySuccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private SqlSession s=MybatisConnectionUtils.getSqlSession();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaySuccessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SqlSession s=MybatisConnectionUtils.getSqlSession();
		
		String movieID=request.getParameter("movieID");
		String seats=request.getParameter("seats");
		String playTime=request.getParameter("playTime");
		String totalPrice=request.getParameter("movieTotalPrice");
		String cinemaID=request.getParameter("cinemaID");
		HttpSession session=request.getSession();
		
		if(movieID!=""&&seats!=""&&playTime!=""&&totalPrice!=""&&cinemaID!="") {
			try{
				//电影票提取码
				int numID=playTime.hashCode()+seats.hashCode()+cinemaID.hashCode();
				int getNum=numID>0?numID/10000:numID*-1/10000;
				
				//购买人ID
				String statement="com.dao.impl.UserDaoImpl.getUserByName";
				UserEntity user=s.selectOne(statement, session.getAttribute("userName"));
				int buyerID=user.getId();
				
				//订单信息
				OrderEntity order=new OrderEntity();
				order.setMovieID(Integer.parseInt(movieID));
				order.setSeats(seats);
				order.setPlayTime(playTime);
				order.setTotalPrice(Float.parseFloat(totalPrice));
				order.setCinemaID(Integer.parseInt(cinemaID));
				order.setGetNum(getNum);
				order.setBuyerID(buyerID);
				
				//插入新订单
			    statement="com.dao.impl.OrderDaoImpl.insertNewOrder";
				s.insert(statement, order);
				s.commit();
				s.clearCache();
				//返回提取码到页面
				if(order.getId()>0) {
					request.setAttribute("getNum", getNum);
					s.close();
					request.getRequestDispatcher("/OrdinaryPage/PaySuccess.jsp").forward(request, response);
				}
				else {
					request.setAttribute("exceptionInfo", "购买失败");
					s.close();
					request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
				}
				
			}
			catch(NumberFormatException e) {
				request.setAttribute("exceptionInfo", "非法的数字输入");
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
			request.setAttribute("exceptionInfo", "参数不能为空");
			request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
		}
	}

}
