package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.util.MybatisConnectionUtils;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SqlSession s=MybatisConnectionUtils.getSqlSession();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		if(id!=""&&id!=null) {
			try {
				String statement="com.dao.impl.MovieDaoImpl.DeleteMovieByID";
				int line=s.delete(statement, Integer.parseInt(id));
				s.commit();
				s.clearCache();
				if(line>0) {
					s.close();
					response.sendRedirect("/CinemaTicket/MovieManageServlet");
				}
				else {
					request.setAttribute("exceptionInfo", "删除失败");
					request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
				}
			}
			catch(NumberFormatException e) {
				request.setAttribute("exceptionInfo", "非法的ID");
				request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
				e.printStackTrace();
			}
			catch(Exception e) {
				request.setAttribute("exceptionInfo", "删除电影错误");
				request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
				e.printStackTrace();
			}
		}
		else {
			request.setAttribute("exceptionInfo", "电影ID不能为空");
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
