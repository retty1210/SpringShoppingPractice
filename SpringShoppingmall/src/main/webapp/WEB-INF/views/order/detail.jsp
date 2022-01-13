<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 상세보기</title>
<jsp:include page="/WEB-INF/views/module/default.jsp" flush="false" />
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/module/top_navi.jsp" flush="false" />
	</header>
	<main role="main">
		<section>
			<table border="1">
				<tr>
					<td>주문번호</td>
					<td>${data.getOrderno() }
				</tr>
				<tr>
					<td>구매자 아이디</td>
					<td>${data.getBuyername() }
				</tr>
				<tr>
					<td>구매자 이름</td>
					<td>${data.getPackagename() }
				</tr>
				<tr>
					<td>구매자 주소</td>
					<td>${data.getAddress() }
				</tr>
				
			</table>
			<table border="1">
				<tr>
					<td>물건 ID</td>
					<td>물건 이름</td>
					<td>판매자</td>
					<td>가격</td>
				</tr>
				
				<c:forEach var="data" items="${datas }">
					<tr>
						<td>${data.getId() }</td>
						<td>${data.getItemname() }</td>
						<td>${data.getSellername() }</td>
						<td>${data.getPrice() }</td>
					</tr>
				</c:forEach>
			</table>
		</section>
	</main>
</body>
</html>