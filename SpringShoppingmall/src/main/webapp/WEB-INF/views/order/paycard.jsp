<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카드 결제가 완료되었습니다.</title>
<jsp:include page="/WEB-INF/views/module/default.jsp" flush="false" />
</head>
<body>
	<script type="text/javascript">
		window.onload = function() {
			document.cookie = "cart=;path=/";
		}
	</script>
	<header>
		<jsp:include page="/WEB-INF/views/module/top_navi.jsp" flush="false" />
	</header>
	<main role="main">
		<section>
			<h5>결제가 성공적으로 처리되었습니다.</h5>
			<p>${sessionScope.account.username } 님의 주문번호 ${orderno }번 주문이 정상적으로 완료되었습니다.</p><br>
			<ul>
				<li><a href="${orderURL }">주문 목록으로 가기</a></li>
				<li><a href="${homeURL }">메인 페이지로 가기</a>
			</ul>
		</section>
	</main>
</body>
</html>