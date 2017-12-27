<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="bundle.html"></jsp:include>
<link type="text/css" rel="stylesheet" href="/CinemaTicket/CSS/login.css" />
</head>
<body>
	<jsp:include page="Layout/head.jsp"></jsp:include>
	<div class="bodyContent">
	
	<form id="loginBody" method="post" action="<%=request.getContextPath() %>/LoginServlet">
		<table>
			<tr>
				<td colspan="2"><h1>用户登陆</h1></td>
			</tr>
			<tr>
				<td>登陆账号：</td>
				<td><input id="uname" type="text" name="uname" value="<c:if test="${account!=null}">${account}</c:if>" /></td>
			</tr>
			<tr>
				<td>登陆密码：</td>
				<td><input id="upwd" type="password" name="upwd" /></td>
			</tr>
			<tr>
				<td><input type="reset" value="重置" /></td>
				<td><input type="submit" value="登陆" /></td>
			</tr>
			<tr>
				<td id="msg" colspan="2">
					<c:if test="${msg!=null}">
						<span style='color: red;'>${msg}</span>
					</c:if>
				</td>
			</tr>
		</table>
	</form>
	
	</div>
</body>
</html>