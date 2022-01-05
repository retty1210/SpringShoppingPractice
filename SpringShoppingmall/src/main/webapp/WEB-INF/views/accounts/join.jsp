<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 - 회원 타입 선택</title>
<jsp:include page="/WEB-INF/views/module/default.jsp" flush="false" />
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/module/top_navi.jsp" flush="false" />
	</header>
	<main role="main">
		<form action="./usertype" method="post">
			<div>
				<h4>어떤 유형의 회원으로 가입할지 선택해주세요.</h4>
				<table>
					<tr>
						<th>판매자</th>
						<th>구매자</th>
					</tr>
					<tr>
						<th>물건을 판매할 수 있는 회원입니다. (기타 설명)</th>
						<th>물건을 구매할 수 있는 회원입니다. (또 설명)</th>
					</tr>
				</table>
			</div>
			<div>
				<label><input type="radio" name="usertype" value="seller">판매자</label>
				<label><input type="radio" name="usertype" value="buyer">구매자</label>
			</div>
			<div>
				<button type="submit">다음 단계로 넘어가기</button>
			</div>
		</form>
	</main>
</body>
</html>