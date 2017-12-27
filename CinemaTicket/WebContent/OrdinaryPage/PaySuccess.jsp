<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../bundle.html"></jsp:include>
<link rel="stylesheet" href="/CinemaTicket/CSS/success.css"/>
</head>
<body>
	<jsp:include page="../Layout/head.jsp"></jsp:include>
	<div class="bodyContent">
	
		<div id="successTip">
			<table>
				<tr>
					<td rowspan="2"><img src="/CinemaTicket/logoImg/交易成功.png" width="150" height="150" /></td>
				</tr>
				<tr>
					<td>交易成功,系统已收到您的付款<br/>您的取票码为：${getNum}</td>
				</tr>
			</table>
			
		</div>
	
	</div>
</body>
</html>