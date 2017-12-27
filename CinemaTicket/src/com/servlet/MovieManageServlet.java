package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.Entity.MovieEntity;
import com.dao.impl.MovieDaoImpl;
import com.util.MybatisConnectionUtils;

/**
 * Servlet implementation class MovieManageServlet
 */
@WebServlet("/MovieManageServlet")
public class MovieManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private SqlSession s=MybatisConnectionUtils.getSqlSession();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SqlSession s=MybatisConnectionUtils.getSqlSession();
		try {
			//清除缓存
			s.clearCache();
			
			String statement = "com.dao.impl.MovieDaoImpl.getAllMovie";
			List<MovieEntity> movieList = s.selectList(statement);
			s.close();
			request.setAttribute("movieList", movieList);
			request.getRequestDispatcher("/MovieManage/MovieManage.jsp").forward(request, response);
		}
		catch (Exception e) {
			request.setAttribute("exceptionInfo", "获取电影信息失败");
			request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
			e.printStackTrace();
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
