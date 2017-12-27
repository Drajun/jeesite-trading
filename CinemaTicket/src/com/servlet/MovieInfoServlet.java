package com.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

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
 * Servlet implementation class MovieInfoServlet
 */
@WebServlet("/MovieInfoServlet")
public class MovieInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private SqlSession s=MybatisConnectionUtils.getSqlSession();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SqlSession s=MybatisConnectionUtils.getSqlSession();
		String id=request.getParameter("id");
		if(id!=null&&id!="") {
			try {
				//获取电影和电影信息数据
				String statement="com.dao.impl.MovieDaoImpl.getMovieByID";
				MovieEntity movie=s.selectOne(statement, Integer.parseInt(id));
				request.setAttribute("movie", movie);
				request.setAttribute("movieInfo", getMovieInfoByHtml(movie.name));
				
				//获取电影类型
				statement="com.dao.impl.TypeDaoImpl.getTypeByID";
				TypeEntity type=s.selectOne(statement,movie.typeID);
				request.setAttribute("type", type);
				
				//获取相关电影
				statement="com.dao.impl.MovieDaoImpl.getMovieByType";
				List<MovieEntity> relevantMovie=s.selectList(statement, type.id);
				request.setAttribute("relevantMovie", relevantMovie);
				
				request.getRequestDispatcher("/MovieInfo.jsp").forward(request, response);
			}
			catch(NumberFormatException e) {
				request.setAttribute("exceptionInfo", "非法的ID");
				request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
			}
		}
		else {
			request.setAttribute("exceptionInfo", "ID不能为空");
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
	
	/*去百度百科截取电影信息*/
	private String getMovieInfoByHtml(String name) {
		try {
			URL url=new URL("https://baike.baidu.com/item/" + name);
			HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
			BufferedReader br=new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
			String pageHTML="",line="";
			while((line=br.readLine())!=null) {
				pageHTML+=line;
			}
			br.close();
			
			//截取相关信息
			 int startIndex = pageHTML.indexOf("<h2 class=\"headline-1 custom-title \">") + "<h2 class=\"headline-1 custom-title \">".length();
             if (startIndex < 0) startIndex = pageHTML.indexOf("div class=\"anchor-list\"", 2);

             int endIndex = pageHTML.indexOf("<a name=\"2_2\"") - "<div class=\"anchor-list\">".length() - 1;
             if (endIndex < 0) endIndex = pageHTML.indexOf("<a name=\"职员表\"");
             if (endIndex < 0) endIndex = pageHTML.indexOf("<dl class=\"lemma-reference");
             
             String movieInfo = "";
             if ((endIndex - startIndex) <= 0)
                 movieInfo = "暂无电影信息";
             else
                 movieInfo = pageHTML.substring(startIndex, endIndex+1);
             return movieInfo;
             
		}
		catch(Exception e) {
			e.printStackTrace();
			return "网络异常";
		}	
	}

	@Override
	public void destroy() {
		// TODO 自动生成的方法存根
		super.destroy();
	}
	
	
}
