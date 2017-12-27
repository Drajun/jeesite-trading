<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="CSS/movie-index.css" />
<jsp:include page="bundle.html"></jsp:include>
</head>
<body>
	<%@include file="Layout/head.jsp" %>
	<div class="bodyContent">
		<div id="all">
			<div class="search">
				 <form method="get"  action="/CinemaTicket/SearchServlet" data-movies-ajax="true" data-movies-target="#movieList">
            		<input type="search" name="searchString" placeholder="输入电影名称..." autocomplete="off">
            		<input type="submit" value="搜索">
       			 </form>
			</div>

			<div id="filter">
				<br /> <br /> <br /> <span class="tagName">类型：</span>
				<ul class="typeTags type">
					<li><a class="active" onclick="typeSearch('全部','type');">全部</a></li>
					<c:forEach items="${typeList}" var="t" >
						<li><a onclick="typeSearch('${t.typeName}','type');">${t.typeName}</a></li>	
					</c:forEach>
					


				</ul>
				<br /> <br /> <br /> <span class="tagName">地区：</span>
				<ul class="typeTags movie-area">
					<li><a class="active" onclick="typeSearch('全部', 'movie-area');">全部</a></li>
					<c:forEach items="${areaList}" var="a" >
						<li><a onclick="typeSearch('${a}','movie-area');">${a}</a></li>	
					</c:forEach>

				</ul>


			</div>
			<br /> <br />
			
			<%@include file="Partial/_Movies.jsp" %>
			

			<div id="page"></div>

		</div>
	</div>
	<%@include file="Layout/foot.html" %>
</body>
</html>