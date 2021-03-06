<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 화면</title>
<jsp:include page="/WEB-INF/views/module/default.jsp" flush="false" />
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/module/top_navi.jsp" flush="false" />
	</header>
	<main role="main">
		<section>
			<table>
				<tr>
					<td>ID</td>
					<td>제품명</td>
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
				<c:choose>
					<c:when test="${priceAll >= 30000}">
						<tr>
							<td colspan="4"> 3만원 이상 구매 시 배송비 무료 </td>
						</tr>
						<tr>
							<td colspan="3"> 총 금액: </td>
							<td>${payPrice} 원</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="3"> 배송비(3만원 이상 구매시 무료) </td>
							<td>3000 원</td>
						</tr>
						<tr>
							<td colspan="3"> 총 금액: ${priceAll } + 3000원 = </td>
							<td>${payPrice} 원</td>
						</tr>
					</c:otherwise>
				</c:choose>
					
			</table>
		</section>
		<hr>
		<section>
			<div>
				<p>주문자 정보 확인</p>
				<form action="./payment" method="post">
					<table>
						<tr>
							<td>이름</td>
							<td><input type="text" name="packagename" value="${sessionScope.ordervo.getPackagename()}" readonly></td>
						</tr>
						<tr>
							<td>주소</td>
							<td><input type="text" name="address" value="${sessionScope.ordervo.getAddress()}" readonly></td>
						</tr>
						<tr>
							<td>우편번호</td>
							<td><input type="text" name="postnumber" value="${sessionScope.ordervo.getPostnumber()}" readonly></td>
						</tr>
						<tr>
							<td>전화번호</td>
							<td><input type="text" name="phonenumber" value="${sessionScope.ordervo.getPhonenumber()}" readonly></td>
						</tr>
					</table>
				
					<div>
						<label><input type="checkbox" name="rulecheck" id="rulecheck" value="rulecheck">정보통신법.....구매에 동의하십니까?</label>
					</div>
					<div>
						<input type="radio" id="bank" name="paymethod" value="bank">
						<label for="bank">계좌이체</label>
						<input type="radio" id="card" name="paymethod" value="card">
						<label for="card">카드결제</label>
					</div>
					<div>
						<input type="hidden" name="orderlist" value="${sessionScope.ordervo.getOrderlist()}">
						<input type="hidden" name="price" value="${payPrice}">
						<input type="hidden" name="buyername" value="${sessionScope.ordervo.getBuyername()}">
						
						
						
						
					</div>
					<div>
						<button type="submit">결제하기</button>
					</div>
				</form>
			</div>
		</section>
	</main>
</body>
</html>