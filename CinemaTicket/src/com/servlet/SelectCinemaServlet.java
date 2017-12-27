package com.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.Entity.AreaEntity;
import com.Entity.MovieEntity;
import com.Entity.TypeEntity;
import com.util.MybatisConnectionUtils;

/**
 * Servlet implementation class SelectCinemaServlet
 */
@WebServlet("/SelectCinemaServlet")
public class SelectCinemaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SqlSession s=MybatisConnectionUtils.getSqlSession();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectCinemaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id=request.getParameter("id");
		if(id!=null||id!="") {
			try {
				//获取电影和电影信息数据
				String statement="com.dao.impl.MovieDaoImpl.getMovieByID";
				MovieEntity movie=s.selectOne(statement, Integer.parseInt(id));
				request.setAttribute("movie", movie);
				
				//获取电影类型
				statement="com.dao.impl.TypeDaoImpl.getTypeByID";
				TypeEntity type=s.selectOne(statement,movie.typeID);
				request.setAttribute("type", type);
				
				//获取放映地区
				statement="com.dao.impl.AreaDaoImpl.getAllArea";
				List<AreaEntity> areaList=s.selectList(statement);
				request.setAttribute("areaList", areaList);
				
				//连续三天的放映时间
				Date[] dates=new Date[3];
				for(int i=0;i<dates.length;i++) {
					dates[i]=new Date();
					Calendar c=new GregorianCalendar();
					c.setTime(dates[i]);
					c.add(c.DATE, i);
					dates[i]=c.getTime();
				}
				request.setAttribute("dates", dates);
				request.getRequestDispatcher("/SelectCinema.jsp").forward(request, response);
			}
			catch(NumberFormatException e) {
				request.setAttribute("exceptionInfo", "非法的ID");
				request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
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
