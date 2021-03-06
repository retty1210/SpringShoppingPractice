<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
<jsp:include page="/WEB-INF/views/module/default.jsp" flush="false" />
<c:url var="jscart" value="/static/js/cart.js" />
<script type="text/javascript" src=${jscart } ></script>
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
					<td>가격</td>
					<td>카테고리</td>
					<td>장바구니</td>
					<td>위시리스트</td>
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
								<a href="${detailURL}"><img style="height=300; width=300" src="${data.getThumURL()}"></a>
							</c:if>
						</td>
						<td>${data.getPrice() }</td>
						<td>${data.getCategory() }</td>
						<td>
							<button type="button" onclick="addcart(${data.getId()})">장바구니 담기</button>
						</td>
						<td>
							<button type="button" onclick="addwish(${data.getId()})">위시리스트 담기</button>
							<script type="text/javascript">
								function addwish(idnum) {
									$.ajax({
										url: "./wish/add",
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
					</tr>
				</c:forEach>
			</table>
		</section>
		<div>
			<c:url var="addURL" value="/itemlist/add" />
			<a href="${addURL}">업로드</a>
		</div>
		<div class="album py-5 bg-light">
		    <div class="container">
		
		      <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
		        <div class="col">
		          <div class="card shadow-sm">
		            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"></rect><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
		
		            <div class="card-body">
		              <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
		              <div class="d-flex justify-content-between align-items-center">
		                <div class="btn-group">
		                  <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
		                  <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
		                </div>
		                <small class="text-muted">9 mins</small>
		              </div>
		            </div>
		          </div>
		        </div>
		        
		      </div>
		    </div>
		</div>
	</main>
</body>
</html>