<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${sessionScope.username } 님의 구매자용 주문목록</title>
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
					<td>주문ID</td>
					<td>주문번호</td>
					<td>itemlist</td>
					<td>가격</td>
					<td>주문상태</td>
					<td>송장번호</td>
				</tr>
				
				<c:forEach var="data" items="${datas }">
				
					<tr>
						<td>${data.getId() }</td>
						<c:url var="orderdetailURL" value="/orders/detail?id=${data.getId() }" />
						<td><a href="${orderdetailURL }">${data.getOrderno() }</a></td>
						<td>${data.getOrderlist() }</td>
						<td>${data.getPrice() }</td>
						<td>${data.getOrderstate() }</td>
						<c:if test="${data.getPackageno() != null} ">
							<td>${data.getPackageno() }</td>
						</c:if>
					</tr>
				</c:forEach>
				
			</table>
		</section>
	</main>
</body>
</html>