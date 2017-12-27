<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../bundle.html"></jsp:include>
</head>
<body>
	<jsp:include page="../Layout/head.jsp"></jsp:include>
	<div class="bodyContent">
	
		<h2>电影列表</h2>
		<br/>
		<a href="/CinemaTicket/AddMovieServlet">添加新电影</a>
		<br/>
		<br/>
		
		<div id="movieList">
			<%@include file="../Partial/_Mange-MovieList.jsp" %>
		</div>
		
	</div>
	<%@include file="../Layout/foot.html" %>
</body>
</html>