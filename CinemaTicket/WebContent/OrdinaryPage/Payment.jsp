<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../bundle.html"></jsp:include>
<link rel="stylesheet" href="/CinemaTicket/CSS/movie-pay.css"/>
</head>
<body>
	<jsp:include page="../Layout/head.jsp"></jsp:include>
	<div class="bodyContent">
	
	<c:forEach items="${orders}" var="o">
	<div class="movieAndCinemaInfo">
			<table>
					<tr>
					<td rowspan="6">
						<img src="/CinemaTicket/${o.getImgUrl()}" width="180" height="270" />
					</td>
				</tr>
				<tr>
					<td>片名：</td>
					<td>${o.getMovieName()}</td>
				</tr>
				<tr>
					<td>影院：</td>
					<td>${o.getCinemaName()}</td>
				</tr>
				<tr>
					<td>地区：</td>
					<td>${o.getArea()}</td>
				</tr>
				<tr>
					<td>场次：</td>
					<td>${o.getPlayTime()}</td>
				</tr>
				<tr>
					<td>总价：</td>
					<td>￥${o.getTotalPrice()}</td>
				</tr>

			</table>
		</div>
		</c:forEach>

		<div id="QRCode">
			<table>
				<tr>
					<td><img onclick="ChangeSuccess();" src="/CinemaTicket/logoImg/付款二维码.png" width="190" height="280" /></td>
					<td>
						请扫描左边二维码<br/>并完成付款
					</td>
				</tr>
			</table>
		</div>

		<div id="cancelBtn">
			<a onclick="javascript:window.history.back();">返回上一页</a>
			<a href="javascript:window.opener=null;window.close();">取消订单</a>
		</div>

		<form id="dataForm" method="post" action="/CinemaTicket/PaySuccessServlet">
			<input type="text" name="movieID" value="${movie.id}" />
			<input type="text" name="playTime" value="${playTime}" />
			<input type="text" name="cinemaID" value="${cinema.id}" />
			<input type="text" name="movieTotalPrice" value="${movieTotalPrice}" />
			<input type="text" name="seats" value="${seats}" />
			<input type="text" name="areaID" value="${area.id}" />
		</form>
	
	</div>
	<%@include file="../Layout/foot.html" %>
	<script>
		function ChangeSuccess() {
			$('#dataForm').submit();
		}
	</script>
</body>
</html>