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
import com.Entity.TypeEntity;
import com.dao.impl.MovieDaoImpl;
import com.util.MybatisConnectionUtils;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SqlSession s=MybatisConnectionUtils.getSqlSession();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeString=request.getParameter("typeString");
		String areaString=request.getParameter("areaString");
		String searchString=request.getParameter("searchString");
		if(typeString!=null&&areaString!=null&&typeString.equals("全部")&&!areaString.equals("全部")) {
			System.out.println("根据地区查找电影："+typeString+","+areaString);
			//根据地区获取电影
			String statement="com.dao.impl.MovieDaoImpl.getMovieByArea";
			List<MovieEntity> movieList=s.selectList(statement, areaString);
			
			request.setAttribute("movies", movieList);
			
			request.getRequestDispatcher("/Partial/_Movies.jsp").forward(request, response);
		}
		else if(typeString!=null&&areaString!=null&&!typeString.equals("全部")&&areaString.equals("全部")) {
			//获取类型ID
			System.out.println("根据类型查找电影："+typeString+","+areaString);
			String statement ="com.dao.impl.TypeDaoImpl.getTypeByName";
			TypeEntity type=s.selectOne(statement, typeString);
			
			//根据类型ID获取电影
			statement="com.dao.impl.MovieDaoImpl.getMovieByType";
			List<MovieEntity> movieList=s.selectList(statement, type.getId());
			
			request.setAttribute("movies", movieList);
			
			request.getRequestDispatcher("/Partial/_Movies.jsp").forward(request, response);
		}
		else if(typeString!=null&&areaString!=null&&!typeString.equals("全部")&&!areaString.equals("全部")) {
			System.out.println("根据地区和类型查找电影："+typeString+","+areaString);
			//获取类型ID
			String statement ="com.dao.impl.TypeDaoImpl.getTypeByName";
			TypeEntity type=s.selectOne(statement, typeString);
			
			//根据类型ID和地区获取电影
			MovieDaoImpl movie=s.getMapper(MovieDaoImpl.class);
			List<MovieEntity> movieList=movie.getMovieByTypeIDAndArea(type.getId(), areaString);
			
			request.setAttribute("movies", movieList);
			
			request.getRequestDispatcher("/Partial/_Movies.jsp").forward(request, response);
		}
		else {
			if(searchString!=null&&searchString!="") {
				System.out.println("根据片名查找电影");
				String statement="com.dao.impl.MovieDaoImpl.getMovieByName";
				request.setAttribute("movies", s.selectList(statement,"%"+searchString+"%"));
				request.getRequestDispatcher("/Partial/_Movies.jsp").forward(request, response);
			}
			else {
				System.out.println("获取全部电影："+typeString+","+areaString);
				//获取全部电影
				String stateMent="com.dao.impl.MovieDaoImpl.getAllMovie";
				request.setAttribute("movies", s.selectList(stateMent));
				request.getRequestDispatcher("/Partial/_Movies.jsp").forward(request, response);	
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
