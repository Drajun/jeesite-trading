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

import com.Entity.OrderEntity;
import com.Entity.UserEntity;
import com.util.CartInfo;
import com.util.MybatisConnectionUtils;
import com.util.OrderByCart;

/**
 * Servlet implementation class CartForBuyServlet
 */
@WebServlet("/CartForBuyServlet")
public class CartForBuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SqlSession s=MybatisConnectionUtils.getSqlSession(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartForBuyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cartList=request.getParameter("cartList");
		HttpSession session=request.getSession();
		if(cartList!=null&&cartList!="") {
			try {
				//提取码
				int getNum=Integer.parseInt(cartList.split("-")[0]);
				
				
				//购买人ID
				String statement="com.dao.impl.UserDaoImpl.getUserByName";
				UserEntity user=s.selectOne(statement, session.getAttribute("userName"));
				int buyerID=user.getId();
				
				//将购物车的订单加入数据库
				statement="com.dao.impl.OrderDaoImpl.insertNewOrder";
				OrderByCart orderByCart=null;
				for(String cID:cartList.split("-")) {
					orderByCart=CartInfo.cart.get(Integer.parseInt(cID));
					OrderEntity order=new OrderEntity();
					order.setMovieID(orderByCart.getMovieID());
					order.setBuyerID(buyerID);
					order.setSeats(orderByCart.getSeats());
					order.setPlayTime(orderByCart.getPlayTime());
					order.setTotalPrice(orderByCart.getTotalPrice());
					order.setGetNum(getNum);
					order.setCinemaID(orderByCart.getCinemaID());
					
					int line=s.insert(statement, order);
					s.commit();
					if(line>0) {
						System.out.println("购买成功");
					}
					else {
						System.out.println("购买失败");
					}
					
				}
				request.setAttribute("getNum", getNum);
				request.getRequestDispatcher("/OrdinaryPage/PaySuccess.jsp").forward(request, response);
			}
			catch(NumberFormatException e){
				request.setAttribute("exceptionInfo", "非法的购物车ID");
				request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
				e.printStackTrace();
			}
			catch(Exception e){
				request.setAttribute("exceptionInfo", "提交失败");
				request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
				e.printStackTrace();
			}
		}
		else {
			request.setAttribute("exceptionInfo", "空的购物车ID");
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
