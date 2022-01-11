<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매자 회원가입</title>
<jsp:include page="/WEB-INF/views/module/default.jsp" flush="false" />
</head>
<body>
	<script>
		function DaumPostcodeScroll() {
	        // 현재 scroll 위치를 저장해놓는다.
	        new daum.Postcode({
	            oncomplete: function(data) {
	                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

	                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var addr = ''; // 주소 변수
	                var extraAddr = ''; // 참고항목 변수

	                //어떤 주소 타입을 선택하든 도로명 주소로 나오게 한다.
	                //if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                //    addr = data.roadAddress;
	                //} else { // 사용자가 지번 주소를 선택했을 경우(J)
	                //    addr = data.jibunAddress;
	                //}
	                addr = data.roadAddress;

	                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	                //if(data.userSelectedType === 'R'){
	                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                        extraAddr += data.bname;
	                    }
	                    // 건물명이 있고, 공동주택일 경우 추가한다.
	                    if(data.buildingName !== '' && data.apartment === 'Y'){
	                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                    }
	                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                    if(extraAddr !== ''){
	                        extraAddr = ' (' + extraAddr + ')';
	                    }
	                    // 조합된 참고항목을 해당 필드에 넣는다.
	                    document.getElementById("address3").value = extraAddr;
	                
	                //} else {
	                //    document.getElementById("address3").value = '';
	                //}

	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('postnumber').value = data.zonecode;
	                document.getElementById("address1").value = addr;
	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById("address2").focus();

	            }
	        }).open();
	    }
	</script>
	<header>
		<jsp:include page="/WEB-INF/views/module/top_navi.jsp" flush="false" />
	</header>
	<main role="main">
		<section>
			<div>
				<h4>구매자 회원가입</h4>
			</div>
			<div>
				
				<form action="./joinbuyer" method="post">
					<div>
						<label for="username">아이디</label>
						<input type="text" name="username" required>
						
					</div>
					<div>
						<label for="packagename">이름(실명)</label>
						<input type="text" name="packagename">
					</div>
					<div>
						<label for="password">비밀번호</label>
						<input type="password" name="password" required>
					</div>
					<div>
						<label for="password2">비밀번호 확인</label>
						<input type="password" name="password2" required>
					</div>
					<div>
						<label for="email">이메일</label>
						<input type="email" name="email" required>
					</div>
					<div>
						<label for="phonenumber">전화번호</label>
						<input type="number" name="phonenumber" required>
					</div>
					<div>
						<label for="postnumber">우편번호</label>
						<input type="number" name="postnumber" id="postnumber" required readonly>
						<button type="button" onclick="DaumPostcodeScroll()">우편번호 찾기</button>
					</div>
					<div>
						<label for="address1">주소</label>
						<input type="text" name="address1" id="address1" required readonly><br>
						<input type="text" name="address2" id="address2" required>
						<input type="text" name="address3" id="address3" required readonly>
					</div>
					<div>
						<label for="birthday">생년월일</label>
						<input type="date" name="birthday" value="2013-01-01" min="1930-01-01" max="2012-12-31" pattern="\d{4}-\d{2}-\d{2}" required>
					</div>
					
					<div>
						<button type="submit">회원가입</button>
					</div>
				</form>
			</div>
		</section>
	</main>
</body>
</html>