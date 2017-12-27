package com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.CartInfo;

/**
 * Servlet implementation class DeleteCartServlet
 */
@WebServlet("/DeleteCartServlet")
public class DeleteCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String CartID=request.getParameter("CartID");
		if(CartID!=null&&CartID!="") {
			try {
				//删除购物车信息
				CartInfo.cart.remove(Integer.parseInt(CartID));
				CartInfo.cartList=new ArrayList<>(CartInfo.cart.values());
				request.setAttribute("cartList", CartInfo.cartList);
				request.getRequestDispatcher("/OrdinaryPage/Cart.jsp").forward(request, response);
			}
			catch(NumberFormatException e) {
				request.setAttribute("exceptionInfo", "非法的购物车ID");
				request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
				e.printStackTrace();
			}
			catch(Exception e) {
				request.setAttribute("exceptionInfo", "删除失败");
				request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
				e.printStackTrace();
			}
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
