<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<h2>我的历史订单</h2>
		<br/>
		<table class="table">
			<tr>
				<th>片名</th>
				<th>影院</th>
				<th>场次</th>
				<th>座位</th>
				<th>票价</th>
				<th>取票码</th>
			</tr>
			<c:forEach items="${orderInfo}" var="o">
				<tr>
					<td>${o.getMovieName() }</td>
					<td>${o.getCinemaName() }</td>
					<td>${o.getPlayTime() }</td>
					<td>${o.getSeats() }</td>
					<td>${o.getTotalPrice() }</td>
					<td>${o.getcID() }</td>
				</tr>
			</c:forEach>
		</table>
	
	</div>

</body>
</html>