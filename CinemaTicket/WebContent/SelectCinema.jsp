<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="bundle.html"></jsp:include>
<link rel="stylesheet" href="CSS/movie-Info.css" />
<link rel="stylesheet" href="CSS/movie-selectCinema.css" />
<script type="text/javascript" src="JS/movie-cinema.js" ></script>
</head>
<body>
	<jsp:include page="Layout/head.jsp"></jsp:include>
	<div class="bodyContent">
	
		<!--
        	作者：longlou.d@foxmail.com
        	时间：2017-12-09
        	描述：电影信息头
        -->
		<div class="movieInfoHead">
			<img id="poster" src="${movie.imgUrl}" width="220px" height="310px" />
			<table id="headInfo">
				<tr>
					<td>${movie.name}</td>
					<td id="movieID" style="display: none;">${movie.id}</td>
				</tr>
				<tr>
					<td>${type.typeName}</td>
				</tr>
				<tr>
					<td>${movie.area}</td>
				</tr>
				<tr>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${movie.showTime}" /></td>
				</tr>
			</table>
			<a href="<%=request.getContextPath() %>/MovieInfoServlet?id=${movie.id}" id="selectCinema">
				电影信息
			</a>
		</div>

		<!--
        	作者：longlou.d@foxmail.com
        	时间：2017-12-09
        	描述：影院信息主体
        -->
		<div class="cinemaInfo">
			<div id="date">
				<br />
				<br />
				<br />
				<p class="tagName">日期：</p>
				<ul class="typeTags date">
				<% request.setAttribute("i", 0); %>
					<c:forEach items="${dates}" var="d">
						<c:if test="${i==0}">
							<li>
								<a title="<fmt:formatDate pattern="MM月dd日" value="${d}" />" onclick="times('${movie.price}')"><fmt:formatDate pattern="今天MM月dd日" value="${d}" /></a>
							</li>
						</c:if>
						<c:if test="${i!=0}">
							<li>
								<a title="<fmt:formatDate pattern="MM月dd日" value="${d}" />" onclick="times('${movie.price}')"><fmt:formatDate pattern="MM月dd日" value="${d}" /></a>
							</li>
						</c:if>
						<% request.setAttribute("i", 1); %>
					</c:forEach>
					
				</ul>
				
				<br />
				<br />
				<br />
				<p class="tagName">地区：</p>
				<ul class="typeTags area">

				</ul>
								
				<br />
				<br />
				<br />
				<p class="tagName">影院：</p>
				<ul class="typeTags cinema">
					

				</ul>
			</div>
			
			<br /><br /><br />
			<div id="cinemaList">
				<h3>放映信息</h3>
				<table id="times">
					<thead>
						<tr>
						  <th>放映时间</th>
						  <th>票价</th>
						  <th>选座购票</th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
			</div>
		</div>

	</div>
	<%@include file="Layout/foot.html" %>
</body>
</html>