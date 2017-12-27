package com.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.Entity.MovieEntity;
import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.util.MybatisConnectionUtils;

/**
 * Servlet implementation class AddNewMovieServlet
 */
@WebServlet("/AddNewMovieServlet")
public class AddNewMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewMovieServlet() {
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
		//Mybatis获取数据库连接
		SqlSession s=MybatisConnectionUtils.getSqlSession();
		SmartUpload smartUpload=new SmartUpload();
		ServletConfig config=this.getServletConfig();
		smartUpload.initialize(config, request, response);
		try {
			smartUpload.upload();                              //上传文件
			File smartFile=smartUpload.getFiles().getFile(0);  //得到上传对象
			
			//获取其他非文件数据
			String movieName=smartUpload.getRequest().getParameter("movieName");
			String typeID=smartUpload.getRequest().getParameter("typeID");
			String area=smartUpload.getRequest().getParameter("area");
			String showTime=smartUpload.getRequest().getParameter("showTime");
			String price=smartUpload.getRequest().getParameter("price");
			String remarks=smartUpload.getRequest().getParameter("remarks");
			
			if(movieName!=null&&typeID!=null&&area!=null&&price!=null&&smartFile!=null) {
				//保存文件
				String imgUrl="movieImg/"+smartFile.getFileName();
				smartFile.saveAs("/"+imgUrl,smartUpload.SAVE_VIRTUAL);
				MovieEntity movie=new MovieEntity();
				movie.setName(movieName);
				movie.setImgUrl(imgUrl);
				movie.setTypeID(Integer.parseInt(typeID));
				movie.setArea(area);
				movie.setShowTime(new SimpleDateFormat("yyyy-MM-dd").parse(showTime));
				movie.setPrice(Float.parseFloat(price));
				movie.setRemarks(remarks);
				String statement="com.dao.impl.MovieDaoImpl.InsertNewMovie";
				int line=s.insert(statement,movie);
				s.commit();
				s.clearCache();
				if(line>0) {
					s.close();
					request.getRequestDispatcher("/MovieManageServlet").forward(request, response);
				}
				else {
					request.setAttribute("exceptionInfo", "添加失败");
					request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
				}
			}
			else {
				request.setAttribute("exceptionInfo", "电影信息不允许为空");
				request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
			}
		}
		catch(SmartUploadException e) {
			request.setAttribute("exceptionInfo", "上传出错");
			request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
			e.printStackTrace();
		}
		catch(NumberFormatException e){
			request.setAttribute("exceptionInfo", "非法的数字");
			request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
			e.printStackTrace();
		}
		catch(Exception e) {
			request.setAttribute("exceptionInfo", "添加电影出错");
			request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

}
