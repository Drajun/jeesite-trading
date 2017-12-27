package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.Entity.RolesEntity;
import com.Entity.UserEntity;
import com.util.CartInfo;
import com.util.CheckAuthority;
import com.util.MybatisConnectionUtils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SqlSession s=MybatisConnectionUtils.getSqlSession();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		// TODO Auto-generated method stub
		//String reUrl=request.getParameter("reUrl");//请求的地址
		
		String name = request.getParameter("uname");
		String pwd = request.getParameter("upwd");
		HttpSession session=request.getSession();
		String forward=null;
		try {
			//判断用户名或密码是否为空
			if (name == null || pwd == null || name == "" || pwd == "") {
				request.setAttribute("msg", "用户名或密码为空！");
				request.setAttribute("account", name);
				forward = "/Login.jsp";
				request.getRequestDispatcher(forward).forward(request, response);
				
			} else {
				//判断用户名和密码是否匹配
				UserEntity user=CheckAuthority.check(s, name, pwd);
				if (CheckAuthority.isLoginSuccess&&user!=null) {
					int roleID=user.getRoleID();
					session.setAttribute("userName", name);
					
					//清空购物车的信息
					CartInfo.cart.clear();
					
					//封装角色信息
					String statement="com.dao.impl.RolesDaoImpl.getRoleByID";
					RolesEntity role=s.selectOne(statement,roleID);
					session.setAttribute("role", role.getRoleName());
					
					if(role.getRoleName().equals("ordinary")) {
						response.sendRedirect("/CinemaTicket/IndexServlet");						
					}
					else {
						response.sendRedirect("/CinemaTicket/MovieManageServlet");
					}
				} else {
					request.setAttribute("msg", "用户名或密码错误！");
					request.setAttribute("account", name);
					forward = "/Login.jsp";
					request.getRequestDispatcher(forward).forward(request, response);
				}
			}
		} catch (Exception e) {
			request.setAttribute("exceptionInfo", "登陆错误");
			request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
			e.printStackTrace();
		}

	}

}
