<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../bundle.html"></jsp:include>
<script type="text/javascript" src="/CinemaTicket/JS/shoppingcart.js"></script>
<link rel="stylesheet" href="/CinemaTicket/CSS/my-order.css" />
</head>
<body>
	<jsp:include page="../Layout/head.jsp"></jsp:include>
	<div class="bodyContent">
	<br/>
	<h2>我的购物车</h2>
	<br/>
	<table border="1" class="table">
		<tr>
			<th>片名</th>
            <th>场次</th>
            <th>座位</th>
            <th>影院</th>
            <th>地区</th>
            <th>总价</th>
            <th>全选<input id="allCheck" type="checkbox" /></th>
            <th></th>
		</tr>
		<c:forEach items="${cartList}" var="cart">
			<tr>
			<td>${cart.getMovieName()}</td>
			<td>${cart.getPlayTime()}</td>
			<td>${cart.getSeats()}</td>
			<td>${cart.getArea()}</td>
			<td>${cart.getTotalPrice()}</td>
			<td>${cart.getMovieName()}</td>
			<td>&nbsp;&nbsp;&nbsp;<input class="checkOrder" type="checkbox" value="${cart.getcID()}" /></td>
			<td><a href="/CinemaTicket/DeleteCartServlet?CartID=${cart.getcID()}">删除</a></td>
			</tr>
		</c:forEach>
	</table>
	<a onclick="submitOrder();" id="submitOrder">立即购买</a>
	</div>

</body>
</html>