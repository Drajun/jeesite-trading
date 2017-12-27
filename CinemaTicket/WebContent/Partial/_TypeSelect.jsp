<select name="typeID">
	<c:forEach items="${typeList}" var="t" >
		<option value="${t.id}">${t.typeName}</option>
	</c:forEach>
</select>