<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매자 회원가입</title>
<jsp:include page="/WEB-INF/views/module/default.jsp" flush="false" />
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/module/top_navi.jsp" flush="false" />
	</header>
	<main role="main">
		<section>
			<div>
				<h4>판매자 회원가입</h4>
			</div>
			<div>
				
				<form action="./joinseller" method="post">
					<div>
						<label for="username">아이디</label>
						<input type="text" name="username" required>
						
					</div>
					<div>
						<label for="password">비밀번호</label>
						<input type="password" name="password" required>
					</div>
					<div>
						<label for="email">이메일</label>
						<input type="email" name="email" required>
					</div>
					<div>
						<label for="phonenumber">전화번호</label>
						<input type="number" name="phonenumber" required>
					</div>
					<div>
						<label for="birthday">생년월일</label>
						<input type="date" name="birthday" value="2011-12-31" min="1930-01-01" max="2012-12-31" pattern="\d{4}-\d{2}-\d{2}" required>
					</div>
					<div>
						<button type="submit">회원가입</button>
					</div>
				</form>
			</div>
		</section>
	</main>
</body>
</html>