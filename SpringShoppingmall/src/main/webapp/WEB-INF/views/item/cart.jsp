<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<jsp:include page="/WEB-INF/views/module/default.jsp" flush="false" />
<c:url var="jscart" value="/static/js/cart.js" />
<script type="text/javascript" src=${jscart } ></script>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/module/top_navi.jsp" flush="false" />
	</header>
	<main role="main">
		<div>
			<button type="button" onclick="deleteCart()">장바구니 비우기</button>
		</div>
		<section>
			<table>
				<tr>
					<td>ID</td>
					<td>제품명</td>
					<td>가격</td>
				</tr>
				
				<c:choose>
					<c:when test="${datas != null}">
						<c:forEach var="data" items="${datas }">
							<tr>
								<td>${data.getId() }</td>
								<td>${data.getItemname() }</td>
								<td>${data.getPrice() }</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="2">총 가격</td>
							<td>${priceAll }</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="3">장바구니에 물건이 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
			<c:if test="${datas != null }">
				<div>
					<form action="./buy" method="post">
						<input type="hidden" name="orderlist" id="orderlist" value="${res }">
						<input type="hidden" name="buyername" id="buyername" value="${sessionScope.account.username }">
						<input type="hidden" name="packagename" id="packagename" value="${sessionScope.account.packagename }">
						<input type="hidden" name="address" id="address" value="${sessionScope.account.address }">
						<input type="hidden" name="postnumber" id="postnumber" value="${sessionScope.account.postnumber }">
						<input type="hidden" name="phonenumber" id="phonenumber" value="${sessionScope.account.phonenumber }">
						<button type="submit">결제하기</button>
					</form>
				</div>
			</c:if>
		</section>
	</main>
</body>
</html>