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
<title>결제 화면</title>
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
							<td colspan="3"> 배송비 </td>
							<td>3000 원</td>
						</tr>
						<tr>
							<td colspan="3"> 총 금액: </td>
							<td>${payPrice} 원</td>
						</tr>
					</c:otherwise>
				</c:choose>
					
			</table>
		</section>
		<hr>
		<section>
			<div>
				
			</div>
			<div>
				<form action="./payment" method="post">
					<div>
						<p>주문자 정보 입력</p>
						<div>
							<label for="packagename">이름</label>
							<input type="text" name="packagename" id="packagename">
							
						</div>
						<div>
							<label for="address1">기본주소</label>
							<input type="text" name="address1" id="address1" readonly>
						</div>
						<div>
							<label for="address2">상세주소</label>
							<input type="text" name="address2" id="address2">
						</div>
						<div>
							<label for="address3">추가주소</label>
							<input type="text" name="address3" id="address3" readonly>
						</div>
						
						<div>
							<label for="postnumber">우편번호</label>
							<input type="number" name="postnumber" id="postnumber" readonly>
							<button type="button" onclick="DaumPostcodeScroll()">우편번호 찾기</button>
						</div>
						<div>
							<label for="phonenumber">전화번호</label>
							<input type="number" name="phonenumber" id="phonenumber">
						</div>
						<div>
							<button type="button" onclick="FinalAddr()">주소 최종확인</button>
							<label for="address">최종주소</label>
							<input type="text" name="address" id="address" readonly>
							<p>비회원주문의 경우 주문번호로만 주문확인이 가능하며 주소변경이 어렵습니다. 반드시 잘 확인해주세요.</p>
							<script type="text/javascript">
								function FinalAddr() {
									var address1 = document.getElementById('address1').value;
									var address2 = document.getElementById('address2').value;
									var address3 = document.getElementById('address3').value;
									var addr = '';
									addr += address1;
									addr += ' ';
									addr += address2;
									addr += ' ';
									addr += address3;
									document.getElementById('address').value = addr;
								}
							</script>
						</div>
					</div>
					<div>
						<label><input type="checkbox" name="rulecheck" id="rulecheck" value="rulecheck">정보통신법.....구매에 동의하십니까?</label>
					</div>
					<div>
						<input type="radio" id="bank" name="paymethod" value="bank">
						<label for="bank">계좌이체</label>
						<input type="radio" id="paycard" name="paymethod" value="card">
						<label for="card">카드결제</label>
					</div>
					<div>
						<input type="hidden" name="orderlist" value="${sessionScope.ordervo.getOrderlist()}">
						<input type="hidden" name="price" value="${payPrice}">
						
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