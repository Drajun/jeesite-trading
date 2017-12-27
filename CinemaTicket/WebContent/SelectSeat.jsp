<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="bundle.html"></jsp:include>
<script type="text/javascript" src="JS/movie-seat.js"></script>
<link rel="stylesheet" href="CSS/movie-seat.css" />
</head>
<body>
	<jsp:include page="Layout/head.jsp"></jsp:include>
	<div class="bodyContent">
	
				<div id="allSelectSeats">
		<!--
        	作者：longlou.d@foxmail.com
        	时间：2017-12-16
        	描述：座位选择
       -->
		<div id="seats">
			<div id="tip">
				<img src="logoImg/空座.png" /><span>可选座位</span>
				<img src="logoImg/已选.png" /><span>已选座位</span>
				<img src="logoImg/已售.png" /><span>已售座位</span>
			</div>
			<div id="checkSeat">
				<table >
					<tr>
						<td></td>
						<td colspan="12">
							<hr color="#7BA7AB" width="700px" />
						</td>
					</tr>
					<tr>
						<td></td>
						<td colspan="12" style="text-align: center;">
							屏幕中央
						</td>
					</tr>
					
					<!-- 座位格子 -->
					<%
						int[] i={1,2,3,4,5,6,7,8,9,10};
						int[] j={1,2,3,4,5,6,7,8,9,10,11,12};
						request.setAttribute("iList", i);
						request.setAttribute("jList", j);
					%>
					<c:forEach items="${iList}" var="i">
						<tr class="choice">
							<td>${i}&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<c:forEach items="${jList}" var="j">
								<c:set var="s" scope="page" value="${i},${j}"/>
								
								<c:if test="${seatList.contains(s)}">
									<td><div class="sell" title="${i},${j}"></div></td>
								</c:if>
								<c:if test="${!seatList.contains(s)}">
									<td><div class="empty" title="${i},${j}"></div></td>								
								</c:if>
							</c:forEach>
					</c:forEach>

				</table>
			</div>
		</div>
		
		<!--
        	作者：longlou.d@foxmail.com
        	时间：2017-12-16
        	描述：边栏电影信息
        -->
        <div class="seat-Movie">
        	<img src="${movie.imgUrl}" width="150px" height="220px" />
        	<table class="seat-movieInfo">
        		<tr>
        			<td colspan="2">${movie.name}</td>
        			<td id="movieID" style="display: none;">${movie.id}</td>
        		</tr>
        		<tr>
        			<td>类型：</td>
        			<td>${type.typeName}</td>
        		</tr>
        		<tr>
        			<td>地区：</td>
        			<td>${movie.area}</td>
        		</tr>
        	</table>
        	
        	
        	<table class="seat-cinemaInfo">
        	<tr>
        			<td>影院：</td>
        			<td id="cinemaName">${cinemaName}</td>
        		</tr>
        		<tr>
        			<td>场次：</td>
        			<td id="movieTime">${playTime}</td>
        		</tr>
        		<tr>
                    <td>地区：</td>
                    <td id="cinemaArea">${playArea}</td>
                </tr>
        		<tr>
        			<td>票价：</td>
        			<td id="price">￥${movie.price}</td>
        		</tr>
        	</table>
        	
        	<div id="isSeatChecked">
        		座位：
        	</div>
        	
        	<p id="totalPrice">
        		总价:
        		<span>0</span>
        	</p>
        	
        	<br />
        	<div id="buyTicketBtn">
        		<a onclick="buyClick('cars')">加入购物车</a>
        		<a onclick="buyClick('buy');">立即购买</a>
        	</div>
        </div>
        
        </div>
	
	</div>
	<%@include file="Layout/foot.html" %>
</body>
</html>