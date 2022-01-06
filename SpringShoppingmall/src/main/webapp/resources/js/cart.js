function setCookie(cookie_name, value, days) {
  var exdate = new Date();
  exdate.setDate(exdate.getDate() + days);
  // 설정 일수만큼 현재시간에 만료값으로 지정

  var cookie_value = escape(value) + ((days == null) ? "" : "; expires=" + exdate.toUTCString());
  document.cookie = cookie_name + "=" + cookie_value;
}

function getCookie(name) {
  let matches = document.cookie.match(new RegExp(
    "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, "\\$1") + "=([^;]*)"
  ));
  return matches ? decodeURIComponent(matches[1]) : undefined;
}

function addcart(id) {
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + 14);
	date = exdate.toUTCString();
	//alert(date);
	
	
	var items = getCookie("cart");
	//alert(", 존재여부: " + items.indexOf(','));
	var itemArray = new Array();
	if(items.indexOf('_') != -1) {
		itemArray = items.split("_");
	} else {
		if(items.length > 0) {
			itemArray.push(items);
		} 
	}
	var num = id.toString();
	//alert("id 중복시 위치: " + itemArray.indexOf(num) + "| items: " + itemArray);
	if(itemArray.indexOf(num) == -1) {
		itemArray.push(id);
		alert("장바구니에 추가되었습니다.");
	} else {
		alert("이미 장바구니에 존재하는 상품입니다.");
	}
	
	if(itemArray.length == 1) {
		item = itemArray;
	} else {
		item = itemArray.join('_');
	}

	document.cookie = "cart=" + item + "; path=/shop; expires=" + date;
	//쿠키에서 카트 내용물이 2개 이상일때 못 읽었던 이유: 
	// 구분자를 , 으로 했었는데 이게 ; 랑 똑같은 역할을 해버림
	
}

function deleteCart() {
	document.cookie = "cart=;path=/shop";
	alert("장바구니의 모든 상품이 삭제되었습니다.");
}