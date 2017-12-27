<table class="table">
	<tr>
		<th>片名</th>
		<th>类型ID</th>
		<th>海报路径</th>
		<th>拍摄地区</th>
		<th>上映时间</th>
		<th>票价</th>
		<th></th>
	</tr>
	<c:forEach items="${movieList}" var="m">
		<tr>
			<td>${m.getName()}</td>
			<td>${m.getTypeID()}</td>
			<td>${m.getImgUrl()}</td>
			<td>${m.getArea()}</td>
			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${m.showTime}" /></td>
			<td>${m.getPrice()}</td>
			<td>
				<a href="/CinemaTicket/DetailsServlet?id=${m.getId()}">详细</a>|
				<a href="/CinemaTicket/EditServlet?id=${m.getId()}">修改</a>|
				<a onclick="delectMovie('${m.getId()}');">删除</a>
			</td>
		</tr>
	</c:forEach>
</table>

<script type="text/javascript">

function delectMovie(id){
	if (confirm("您确定要删除吗？")) {
		window.location.href="<%=request.getContextPath() %>/DeleteServlet?id="+id;
	}
}
</script>