<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>물건 업로드</title>
<jsp:include page="/WEB-INF/views/module/default.jsp" flush="false" />
</head>
<body>
	
	<header>
		<jsp:include page="/WEB-INF/views/module/top_navi.jsp" flush="false" />
	</header>
	<main role="main">
		<section>
			<form action="./itemup" method="post" enctype="multipart/form-data">
				<div>
					<label for="itemname">물건 이름</label>
					<input type="text" name="itemname" id="itemname">
				</div>
				<div>
					<label for="sellername">판매자 이름</label>
					<input type="text" name="sellername" id="sellername" value="${sessionScope.account.username}" placeholder="${sessionScope.account.username}" readonly>
				</div>
				<div>
					<label for="thum">썸네일 이미지 업로드</label>
					<input type="file" name="thum" id="thum">
				</div>
				<div>
					<label for="infotext">제품 설명</label>
					<textarea name="infotext" id="infotext"></textarea>
				</div>
				<div>
					<label for="price">가격</label>
					<input type="number" name="price" id="price">
				</div>
				<div>
					<label for="category">카테고리</label>
					<select name="category" id="category">
						<option value="" disabled>카테고리를 선택하세요.</option>
						<option value="1">카테고리1</option>
						<option value="2">카테고리2</option>
						<option value="3">카테고리3</option>
					</select>
				</div>
				
				<div>
					<button type="submit">업로드</button>
				</div>
			</form>
		</section>
	</main>
</body>
</html>