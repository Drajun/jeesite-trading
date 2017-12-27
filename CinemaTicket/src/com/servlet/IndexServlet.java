package com.servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.util.MybatisConnectionUtils;

/**
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //private SqlSession s=MybatisConnectionUtils.getSqlSession();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
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
		SqlSession s=MybatisConnectionUtils.getSqlSession();
		s.clearCache();
		//获取全部电影
		String stateMent="com.dao.impl.MovieDaoImpl.getAllMovie";
		request.setAttribute("movies", s.selectList(stateMent));
		
		//获取全部类型
		stateMent="com.dao.impl.TypeDaoImpl.getAllType";
		request.setAttribute("typeList", s.selectList(stateMent));
		
		//获取全部拍摄地区(去重)
		stateMent="com.dao.impl.MovieDaoImpl.getAllArea";
		Set<String> areaSet=new HashSet<>(s.selectList(stateMent));
		request.setAttribute("areaList", areaSet);
		
		s.close();
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
	}
	
	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("进入Index");
	}

	@Override
	public void destroy() {
		// TODO 自动生成的方法存根
		System.out.println("关闭数据库连接");
		MybatisConnectionUtils.close(MybatisConnectionUtils.getSqlSession());
		super.destroy();
		
	}
	
	
}
