
<div id="nav">
	<ul>
		<li>电影汇</li>
		<li><a href="/CinemaTicket/IndexServlet">主页</a></li>
		<li><a href="/CinemaTicket/getTicketFlow.jsp">取票流程</a></li>
		<li><a>联系我们</a></li>
		<li id="rolePage"></li>
	</ul>
	
	<div id="loginBtn">
	</div>
	
</div>
<script>
$(function(){
	$('#loginBtn').innerHTML="";
	if(${userName!=null}){
		$('#loginBtn').append("<a class='login' href='/CinemaTicket/LoginExitServlet'>退出登录</a><a href='/CinemaTicket/MyOrderListServlet' class='login'>欢迎您，${sessionScope.userName}</a>");
	}
	if(${userName==null}){
		$('#loginBtn').append("<a class='login' href='/CinemaTicket/Login.jsp'>登陆</a><a class='login' href='/CinemaTicket/UserRegister.jsp'>注册</a>");
	}
	
	$('#rolePage').innerHTML="";
	if(${role!=null}&&${role=="ordinary"}){
		$('#rolePage').append("<a href='/CinemaTicket/CartServlet'>购物车</a>");
	}
	if(${role!=null}&&${role=="admin"}){
		$('#rolePage').append("<a href='/CinemaTicket/MovieManageServlet'>电影管理</a>");
	}
});
	
</script>