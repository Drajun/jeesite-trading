package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.Entity.MovieEntity;
import com.Entity.TypeEntity;
import com.util.MybatisConnectionUtils;

/**
 * Servlet implementation class DetailsServlet
 */
@WebServlet("/DetailsServlet")
public class DetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SqlSession s=MybatisConnectionUtils.getSqlSession();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		if(id!="") {
			try {
				String statement="com.dao.impl.MovieDaoImpl.getMovieByID";
				MovieEntity movie=s.selectOne(statement,Integer.parseInt(id));
				request.setAttribute("movie", movie);
				
				//类型信息
				statement="com.dao.impl.TypeDaoImpl.getTypeByID";
				TypeEntity type=s.selectOne(statement, movie.getTypeID());
				request.setAttribute("type", type);
				
				request.getRequestDispatcher("/MovieManage/MovieDetails.jsp").forward(request, response);
			}
			catch(NumberFormatException e) {
				request.setAttribute("exceptionInfo", "非法的ID");
				request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
				e.printStackTrace();
			}
			catch(Exception e) {
				request.setAttribute("exceptionInfo", "获取电影信息错误");
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
