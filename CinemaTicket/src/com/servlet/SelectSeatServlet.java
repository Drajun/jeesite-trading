package com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.Entity.CinemaEntity;
import com.Entity.MovieEntity;
import com.Entity.OrderEntity;
import com.Entity.TypeEntity;
import com.dao.OrderDao;
import com.dao.impl.OrderDaoImpl;
import com.util.MybatisConnectionUtils;

/**
 * Servlet implementation class SelectSeatServlet
 */
@WebServlet("/SelectSeatServlet")
public class SelectSeatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private SqlSession s=MybatisConnectionUtils.getSqlSession();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectSeatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SqlSession s=MybatisConnectionUtils.getSqlSession();
		
		String date=request.getParameter("date");
		String cinemaName=request.getParameter("cinema");
		String id=request.getParameter("id");
		String area=request.getParameter("area");
		
		if(date!=""&&cinemaName!=""&&id!=""&&area!="") {
			try {
				//获取电影信息
				String statement="com.dao.impl.MovieDaoImpl.getMovieByID";
				MovieEntity movie=s.selectOne(statement, Integer.parseInt(id));
				request.setAttribute("movie", movie);
				
				//获取电影类型
				statement="com.dao.impl.TypeDaoImpl.getTypeByID";
				TypeEntity type=s.selectOne(statement,movie.typeID);
				request.setAttribute("type", type);
				
				//传递电影院、场次、放映地区信息
				request.setAttribute("cinemaName", cinemaName);
				request.setAttribute("playTime", date);
				request.setAttribute("playArea", area);
				
				//获取电影院信息
				statement="com.dao.impl.CinemaDaoImpl.getCinemaByName";
				CinemaEntity cinema=s.selectOne(statement,cinemaName);
				
				//获取已售座位
				OrderDao order=new OrderDao();
				List<String> seats=order.getSeatsByMovieIDAndPlayTimeAndCinemaID(movie.id, date, cinema.id);
				List<String> seatList=new ArrayList<>();
				if(seats!=null) {
					for(String item : seats) {
						for(String ss:item.split(";")) {
							seatList.add(ss);						
						}
					}					
				}
				request.setAttribute("seatList", seatList);
				s.close();
				request.getRequestDispatcher("/SelectSeat.jsp").forward(request, response);
			}
			catch(NumberFormatException e) {
				request.setAttribute("exceptionInfo", "非法的ID");
				request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
				e.printStackTrace();
			}
			catch(SQLException e) {
				request.setAttribute("exceptionInfo", "查询目标数据错误");
				request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
				e.printStackTrace();
			}
		}
		else {
			request.setAttribute("exceptionInfo", "参数不能为空");
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
