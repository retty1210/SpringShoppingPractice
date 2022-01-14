<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
	<title>Home</title>
	<jsp:include page="/WEB-INF/views/module/default.jsp" flush="false" />
</head>
<body>
	<script type="text/javascript">
		window.onload = function() {
			<% 
			if(session.getAttribute("error") != null) {
			%>
				var error = '<%=(boolean)session.getAttribute("error") %>';
				if(error) {
					var error_msg = '<%=(String)session.getAttribute("error_msg") %>';
					alert(error_msg);
				}
			<%
			}
			%>
		}
	</script>
	<header>
		<jsp:include page="/WEB-INF/views/module/top_navi.jsp" flush="false" />
	</header>
	<div>
	<c:url var="homeURL" value="/" />
		<h1>
			Hello world!  
		</h1>
		
		<P>  The time on the server is ${serverTime}. </P>
	</div>
	<c:if test="${sessionScope.logined ne null }">
		<p>${sessionScope.account.username }님 환영합니다.</p><br>
		<p>${sessionScope.usertype } 타입의 회원입니다.</p>
	</c:if>
	<div>
	<c:url var="joinURL" value="/join" />
	<c:url var="loginURL" value="/login" />
		<ul>
			<li><a href="${joinURL}">회원가입</a></li>
			<li><a href="${loginURL}">로그인</a></li>
			
		</ul>
	</div>
	<div>
		<p>이미지 경로 테스트</p>
		<img src="${homeURL }static/up/pet0026.jpg">
	</div>
</body>
</html>
