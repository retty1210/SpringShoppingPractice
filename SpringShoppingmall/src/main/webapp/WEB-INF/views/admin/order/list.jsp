<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${sessionScope.username } 님의 관리자용 주문목록</title>
<jsp:include page="/WEB-INF/views/module/default.jsp" flush="false" />
<c:url var="admin" value="/static/js/admin.js" />
<script type="text/javascript" src=${admin } ></script>
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
					<td>구매자 아이디</td>
					<td>구매자 이름</td>
					<td>주소</td>
					<td>우편번호</td>
					<td>전화번호</td>
					<td>결제방법</td>
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
						<td>${data.getBuyername() }</td>
						<td>${data.getPackagename() }</td>
						<td>${data.getAddress() }</td>
						<td>${data.getPostnumber() }</td>
						<td>${data.getPhonenumber() }</td>
						<td>${data.getPaymethod() }</td>
						<td>${data.getPrice() }</td>
						
						<td>
							<form id="selectstatform${data.getId() }" method="post" action="/changeorderstat">
								<select name="selectstat" id="selectstat${data.getId() }">
									<c:forEach var = "orderstat" items="${orderstats }">
										<c:choose>
											<c:when test="${orderstat.getId() == data.getOrderstate()}">
												<option value="${orderstat.getId() }" selected>${orderstat.getOrderst()}</option>
											</c:when>
											<c:otherwise>
												<option value="${orderstat.getId() }">${orderstat.getOrderst()}</option>
											</c:otherwise>
										</c:choose>
										
									</c:forEach>
								</select>
								<input type="hidden" name="id" id="id${data.getId() }" value="${data.getId() }">
								<input type="hidden" name="orgstat" id="orgstat${data.getId() }" value="${data.getOrderstate() }">
							</form>
						</td>
						
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