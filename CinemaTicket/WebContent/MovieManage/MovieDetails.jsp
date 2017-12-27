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
		<br/>
		<h2>${movie.getName()} 详细信息</h2>
		<table class="table">
			<tr>
				<td>片名</td>
				<td>${movie.getName()}</td>
				<td rowspan="4">
					<img src="/CinemaTicket/${movie.getImgUrl()}" width="110" height="155"/>
				</td>
			</tr>
			<tr>
				<td>类型</td>
				<td>${type.typeName}</td>
			</tr>
			<tr>
				<td>拍摄地区</td>
				<td>${movie.getArea()}</td>
			</tr>
			<tr>
				<td>票价</td>
				<td>￥${movie.getPrice()}</td>
			</tr>
			<tr>
				<td>备注</td>
				<td colspan="2">${movie.getRemarks()}</td>
			</tr>
		</table>
		<br/><br/>
		<a href="/CinemaTicket/MovieManageServlet">返回电影列表</a>
	</div>
	<%@include file="../Layout/foot.html" %>
</body>
</html>