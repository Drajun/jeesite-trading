<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="bundle.html"></jsp:include>
<link type="text/css" rel="stylesheet" href="/CinemaTicket/CSS/Login.css" />
</head>
<body>
	<jsp:include page="Layout/head.jsp"></jsp:include>
	<div class="bodyContent">
	<br/>
	<h2>用户注册</h2>
	<br/>
		<form>
		<table class="table">
			<tr>
				<th>用户名</th>
				<td><input type="text" name="uname"/></td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input type="password" name="upsd"/></td>
			</tr>
			<tr>
				<th>Email</th>
				<td><input type="text" name="uEmail"/></td>
			</tr>
			<tr>
				<th>性别</th>
				<td>
					<input type="radio" name="uSex" value="男"/>男
					&nbsp;
					<input type="radio" name="uSex" value="女"/>女
				</td>
			</tr>
			<tr>
				<th>地址</th>
				<td><input type="text" name="uAddress"/></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td colspan="2"><input type="reset" value="重置"/>
				&nbsp;&nbsp;&nbsp;
				<input type="submit" value="提交"/></td>
			</tr>
		</table>
			</form>
	</div>
	
</body>
</html>