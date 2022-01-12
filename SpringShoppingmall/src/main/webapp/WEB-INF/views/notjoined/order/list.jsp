<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비회원용 주문조회 페이지</title>
<jsp:include page="/WEB-INF/views/module/default.jsp" flush="false" />
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/module/top_navi.jsp" flush="false" />
	</header>
	<main role="main">
		<section>
			<form action="./notloginorder" method="post">
				<div>
					<input type="text" name="orderno" id="orderno">
				</div>
				<div>
					<button type="submit">검색하기</button>
				</div>
			</form>
			<div>
				<ul>
					<li><a href="/">메인으로 돌아가기</a></li>
					<li><a href="/login">로그인하기</a></li>
					<li><a href="/join">회원가입</a></li>
				</ul>
			</div>
		</section>
	</main>
</body>
</html>