<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자용 상품리스트 페이지</title>
<jsp:include page="/WEB-INF/views/module/default.jsp" flush="false" />
<c:url var="jscart" value="/static/js/cart.js" />
<script type="text/javascript" src=${jscart } ></script>
<c:url var="jsadminitem" value="/static/js/adminitemlist.js" />
<script type="text/javascript" src=${jsadminitem } ></script>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/module/top_navi.jsp" flush="false" />
	</header>
	<main role="main">
		<section>
		<form action="./deleteitem" method="post">
			<div>
				<button type="button" name="openb" onclick="opencheck();">삭제할 아이템 체크하기</button>
				<button type="button" name="closeb" onclick="closecheck();">돌아가기</button>
				<button type="button" name="deleteb" onclick="deletecheckall();">한꺼번에 삭제하기</button>
			</div>
			<table border="1">
				<tr>
					<td>ID</td>
					<td>제품명</td>
					<td>판매자</td>
					<td>작성시간</td>
					<td>썸네일 주소</td>
					<td>가격</td>
					<td>카테고리</td>
					
					<td>삭제(관리자용 메뉴)</td>
					<td name="hidden">삭제(hidden)</td>
				</tr>
				
				<c:forEach var="data" items="${datas}">
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
						<td>${data.getPrice() }</td>
						<td>${data.getCategory() }</td>
						
						<td>
							<button type="button" onclick="itemdelete(${data.getId()})">삭제하기</button>
							<script type="text/javascript">
								function itemdelete(idnum) {
									$.ajax({
										url: "./itemlist/delete",
										type: "post",
										data: {
											id: idnum
										},
										dataType: "json",
										success: function(data) {
											alert(data.msg);
										},
										error: function(data) {
											alert(data.msg);
										}
									});
								}
							</script>
						</td>
						<td name="hidden"><input type="checkbox" name="deletecheck" value="${data.getId() }"></td>
					</tr>
				</c:forEach>
			</table>
			</form>
		</section>
		
	</main>
</body>
</html>