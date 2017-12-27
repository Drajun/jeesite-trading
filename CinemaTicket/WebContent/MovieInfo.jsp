<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="bundle.html"></jsp:include>
<link rel="stylesheet" type="text/css" href="https://bkssl.bdimg.com/static/wiki-common/pkg/wiki-common-base_4c062e6.css" />
<link rel="stylesheet" type="text/css" href="https://bkssl.bdimg.com/static/wiki-common/widget/component/userbar-n/userbar-n_5b008b4.css" />
<link rel="stylesheet" type="text/css" href="https://bkssl.bdimg.com/static/wiki-lemma/pkg/wiki-lemma-module_d32faa4.css" />
<link rel="stylesheet" type="text/css" href="https://bkssl.bdimg.com/static/wiki-lemma/widget/tools/announcement/announcement_cba33f4.css" />
<link rel="stylesheet" type="text/css" href="https://bkssl.bdimg.com/static/wiki-lemma/widget/tools/label/label_ca156c6.css" />
<link rel="stylesheet" type="text/css" href="https://bkssl.bdimg.com/static/wiki-lemma/widget/tools/newSideShare/sideShare_2af1cbd.css" />
<link rel="stylesheet" type="text/css" href="https://bkssl.bdimg.com/static/wiki-lemma/widget/tools/video/pageMask/pageMask_ff9a193.css" />
<link rel="stylesheet" type="text/css" href="https://bkssl.bdimg.com/static/wiki-lemma/widget/lemma_content/configModule/secondsKnow/perfect-scrollbar/index_c66d49f.css" />
<link rel="stylesheet" type="text/css" href="https://bkssl.bdimg.com/static/wiki-lemma/widget/lemma_content/configModule/secondsKnow/secondsKnow_073e9d1.css" />
<link rel="stylesheet" type="text/css" href="https://bkssl.bdimg.com/static/wiki-lemma/pkg/wiki-feature-common_427055b.css" />
<link rel="stylesheet" type="text/css" href="https://bkssl.bdimg.com/static/wiki-lemma/widget/lemma_content/configModule/posterBg/posterBg_c175eb9.css" />
<link rel="stylesheet" type="text/css" href="https://bkssl.bdimg.com/static/wiki-lemma/widget/lemma_content/configModule/majorActors/majorActors_42879b8.css" />
<link rel="stylesheet" type="text/css" href="https://bkssl.bdimg.com/static/wiki-lemma/widget/feature/drama/dramaActors/dramaActors_c9e9011.css" />
<link rel="stylesheet" type="text/css" href="https://bkssl.bdimg.com/static/wiki-lemma/widget/feature/drama/staff/staff_b93de47.css" />
<link rel="stylesheet" type="text/css" href="https://bkssl.bdimg.com/static/wiki-lemma/widget/lemma_content/mainContent/mainContent_98e3702.css" />
<link rel="stylesheet" type="text/css" href="https://bkssl.bdimg.com/static/wiki-lemma/widget/lemma_content/configModule/movieComments/movieComments_92824a4.css" />
<link rel="stylesheet" type="text/css" href="https://bkssl.bdimg.com/static/wiki-lemma/widget/lemma_content/mainContent/lemmaRelation/lemmaRelation_ac86a59.css" />
<link rel="stylesheet" type="text/css" href="https://bkssl.bdimg.com/static/wiki-lemma/widget/lemma_content/configModule/tab/video/video_a0b6610.css" />
<link rel="stylesheet" type="text/css" href="https://bkssl.bdimg.com/static/wiki-lemma/widget/lemma_content/configModule/zhixin/zhixin_3b0d7a5.css" />
<link rel="stylesheet" type="text/css" href="https://bkssl.bdimg.com/static/wiki-lemma/widget/tools/searchHeader/toolButtons-n/toolButtons-n_6e90fdd.css" />
<link rel="stylesheet" type="text/css" href="https://bkssl.bdimg.com/static/wiki-lemma/widget/tools/searchHeader/toolButtons-n/userInfo-n_7e90184.css" />
<link rel="stylesheet" type="text/css" href="https://bkssl.bdimg.com/static/wiki-lemma/widget/tools/searchHeader/searchHeader-n_f9a6e5b.css" />
<link rel="stylesheet" type="text/css" href="https://bkssl.bdimg.com/static/wiki-lemma/pkg/wiki-lemma_7c6775d.css" />
<link rel="stylesheet" href="CSS/movie-Info.css" />
</head>
<body>
	<jsp:include page="Layout/head.jsp"></jsp:include>
	<div class="bodyContent">
	
		<!--
        	作者：longlou.d@foxmail.com
        	时间：2017-12-09
        	描述：电影信息头
        -->
		<div class="movieInfoHead">
			<img id="poster" src="${movie.imgUrl}" width="220px" height="310px"  />
			<table id="headInfo">
				<tr>
					<td>${movie.name}</td>
				</tr>
				<tr>
					<td>${type.typeName}</td>
				</tr>
				<tr>
					<td>${movie.area}</td>
				</tr>
				<tr>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${movie.showTime}" /></td>
				</tr>
			</table>
			<a href="<%=request.getContextPath() %>/SelectCinemaServlet?id=${movie.id}" id="selectCinema">
				选择影院
			</a>
		</div>
		
		<!--
        	作者：longlou.d@foxmail.com
        	时间：2017-12-09
        	描述：电影信息主体
        -->
		<div class="infoContent">
			${movieInfo}
		</div>

<!--
	作者：longlou.d@foxmail.com
	时间：2017-12-09
	描述：推荐电影
-->
<div class="recommend">
	<h2 class="headline-1 custom-title ">
		<span class="headline-content">相关电影</span>
	</h2>
	
	<c:forEach items="${relevantMovie}" var="rm">
		<c:if test="${rm.name!=movie.name}">
		<div class="movieItem">
			<a href="<%=request.getContextPath() %>/MovieInfoServlet?id=${rm.id}">
				<img src="${rm.imgUrl }" width="120px" height="170px" />
				<br />
				<span>${rm.name}</span>
			</a>
		</div>
		</c:if>
	</c:forEach>
	
</div>
	
	</div>
	<%@include file="Layout/foot.html" %>
</body>
</html>