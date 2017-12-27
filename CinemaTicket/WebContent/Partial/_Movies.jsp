<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="movieList">
<c:forEach items="${movies}" var="m">
	<div class="movie">
		<a target="_blank" href="<%=request.getContextPath() %>/MovieInfoServlet?id=${m.id}">
			<img src="${m.imgUrl}" width="190" height="280" />
			<br  />
			<span>${m.name}</span>
		</a>
	</div>
</c:forEach>
</div>