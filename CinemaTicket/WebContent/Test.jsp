<%@page import="com.Entity.OrderEntity"%>
<%@page import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@page import="com.jdbc.DBClose"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dao.AreaDao" %>
<%@ page import="com.jdbc.ConnectionFactory" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder" %>
<%@ page import="com.util.MybatisConnectionUtils" %>
<%@ page import="com.Entity.MovieEntity" %>
<%@ page import="java.util.*" %>
<%@ page import="com.dao.impl.OrderDaoImpl"%>
<%@ page import="com.dao.impl.MovieDaoImpl" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	SqlSession s=MybatisConnectionUtils.getSqlSession();
	String statement="com.dao.impl.MovieDaoImpl.getMovieByTypeIDAndArea";
	MovieDaoImpl movieImpl=s.getMapper(MovieDaoImpl.class);
	List<MovieEntity> movieList=movieImpl.getMovieByTypeIDAndArea(4, "欧美");
	for(MovieEntity m : movieList){
		out.write(m.toString()+"<br/>");
	}
%>
</body>
</html>