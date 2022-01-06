<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${data.getItemname() }</title>
<jsp:include page="/WEB-INF/views/module/default.jsp" flush="false" />
<c:url var="jscart" value="/static/js/cart.js" />
<script type="text/javascript" src=${jscart } ></script>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/module/top_navi.jsp" flush="false" />
	</header>
	<main role="main">
	<c:set var="path" value="${sessionScope.path}" />
	<c:url var="upURL" value="/static/up/" />
		<section>
			<table>
				<tr>
					<td rowspan="3">
						<c:choose>
							<c:when test="${data.getThumURL() != null}">
								<img src="${upURL}${data.getThumURL() }" width="300" height="300">
							</c:when>
							<c:otherwise>
								<p>NO IMAGE</p>
							</c:otherwise>
						</c:choose>
					</td>
					<td>제품명</td>
					<td>${data.getItemname() }</td>
				</tr>
				<tr>
					<td>판매자</td>
					<td>${data.getSellername() }</td>
				</tr>
				<tr>
					<td>작성시간</td>
					<td><fmt:formatDate value="${data.getUploadtime() }" type="both" pattern="YY/MM/dd(E) a hh:mm"/></td>
				</tr>
			</table>
			<div>
				<button type="button" onclick="addcart(${data.getId()})">장바구니에 담기</button>
			</div>
			<table>
				<tr>
					<td>제품 상세설명</td>
				</tr>
				<tr>
					<td>${data.getInfotext() }</td>
				</tr>
				<c:if test="${data.getInfoURL() != null}">
					<tr>
						<td>제품 상세설명 이미지</td>
					</tr>
					<tr>
						<td><img src="${upURL}${data.getInfoURL() }" width="300" height="300"></td>
					</tr>
				</c:if>
			</table>
		</section>
	</main>
</body>
</html>