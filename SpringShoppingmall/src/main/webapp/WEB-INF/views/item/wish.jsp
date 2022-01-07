<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>위시리스트</title>
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
					<td>ID</td>
					<td>제품명</td>
					<td>판매자</td>
					<td>작성시간</td>
					<td>썸네일 주소</td>
					
				</tr>
				<c:choose>
					<c:when test="${datas != null }">
						<c:forEach var="data" items="${datas }">
							<tr>
								<td>${data.getId() }</td>
								<c:url var="detailURL" value="/itemlist/detail?id=${data.getId() }" />
								<td><a href="${detailURL}">${data.getItemname()}</a></td>
								<td>${data.getSellername() }</td>
								<td>
								<fmt:formatDate value="${data.getUploadtime() }" type="both" pattern="YY/MM/dd(E) a hh:mm"/>
								</td>
								<td>
									<c:if test="${data.getThumURL() != null}">
										<a href="${detailURL}">${data.getThumURL()}</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5">즐겨찾기에 물건이 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
		</section>
	</main>
</body>
</html>