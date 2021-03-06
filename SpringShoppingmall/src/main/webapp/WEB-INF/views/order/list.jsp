<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${sessionScope.username } 님의 판매자용 주문목록</title>
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
					<td>구매자</td>
				</tr>
				
				<c:forEach var="data" items="${datas }">
				
					<tr>
						<td>${data.order.getId() }</td>
						<c:url var="orderdetailURL" value="/orders/detail?id=${data.order.getId() }" />
						<td><a href="${orderdetailURL }">${data.orderseller.getOrderno() }</a></td>
						<td>${data.orderseller.getItemlist() }</td>
						<td>${data.order.getBuyername() }</td>
					</tr>
				</c:forEach>
				
			</table>
		</section>
	</main>
</body>
</html>