<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../bundle.html"></jsp:include>
<style type="text/css">
table{
margin-top:30px;
}
input,select{
width: 200px;
}
</style>

</head>
<body>
	<jsp:include page="../Layout/head.jsp"></jsp:include>
	<div class="bodyContent">
	<br/>
	<h2>添加电影</h2>
	<br/>
	<form action="/CinemaTicket/AddNewMovieServlet" method="post" enctype="multipart/form-data">
	<table class="table">
		<tr>
			<th>片名：</th>
			<td><input type="text" name="movieName" /></td>
		</tr>
		<tr>
			<th>海报：</th>
			<td><input type="file" name="imgUrl" /></td>
		</tr>
		<tr>
			<th>类型：</th>
			<td><%@include file="../Partial/_TypeSelect.jsp"  %></td>
		</tr>
		<tr>
			<th>拍摄地区：</th>
			<td><input type="text" name="area" /></td>
		</tr>
		<tr>
			<th>上映时间：</th>
			<td><input type="date" name="showTime" /></td>
		</tr>
		<tr>
			<th>票价：</th>
			<td><input type="text" name="price" /></td>
		</tr>
		<tr>
			<th>备注：</th>
			<td><input type="text" name="remarks" /></td>
		</tr>
		<tr>
			<th></th>
			<td><input type="submit" value="提交" /></td>
		</tr>
	</table>
	</form>
	<br/><br/>
		<a href="/CinemaTicket/MovieManageServlet">返回电影列表</a>
	</div>
	<%@include file="../Layout/foot.html" %>
</body>
</html>