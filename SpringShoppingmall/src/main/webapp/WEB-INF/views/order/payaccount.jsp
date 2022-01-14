<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>무통장입금 주문이 완료되었습니다</title>
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
			<h5>무통장입금 주문완료</h5>
			<p>${sessionScope.account.username } 님의 주문번호 ${orderno}의 주문이 완료되었습니다.</p><br>
			<p>아래 계좌로 입금완료하시면 결제가 완료됩니다.</p><br>
			<p>입금확인은 매일 오후 4~5시 사이에 수동으로 이루어집니다.</p><br>
			<p>예금주: ㅇㅇㅇ</p><br>
			<table>
				<tr>
					<td>기업은행</td>
					<td>000-000000-0000-00</td>
				</tr>
				<tr>
					<td>농협</td>
					<td>0000-000000-000-00</td>
				</tr>
				<tr>
					<td>국민은행</td>
					<td>000-000000-000-000</td>
				</tr>
			</table>
			<ul>
				<li><a href="${orderURL }">주문 목록으로 가기</a></li>
				<li><a href="${homeURL }">메인 페이지로 가기</a>
			</ul>
		</section>
	</main>
</body>
</html>