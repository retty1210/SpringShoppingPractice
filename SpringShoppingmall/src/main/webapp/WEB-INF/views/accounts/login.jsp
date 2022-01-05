<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/module/default.jsp" flush="false" />
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/module/top_navi.jsp" flush="false" />
	</header>
	<main role="main">
		<section>
			<form action="./login" method="post">
				<div>
					<label for="username">아이디</label>
					<input type="text" name="username" required>
				</div>
				<div>
					<label for="password">비밀번호</label>
					<input type="password" name="password" required>
				</div>
				<div>
					<button type="submit">로그인</button>
				</div>
			</form>
		</section>
	</main>
</body>
</html>