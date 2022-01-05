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
	if(items.indexOf(',') != -1) {
		var itemArray = items.split(",");
	} else {
		if(items.length > 0) {
			var itemArray = [items];
		} else {
			var itemArray=[];
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
	
	//alert("0 위치: " + itemArray.indexOf('0'));
	if(itemArray.indexOf('0') != -1) {
		itemArray.splice(itemArray.indexOf('0'), 1);
	}
	if(itemArray.length == 1) {
		item = itemArray;
	} else {
		item = itemArray.join(',');
	}

	document.cookie = "cart=" + item + ";path=/shop;expires=" + date;
	//카트값을 어디에 따로 저장해서 갖다써야할거같은데 왜 쿠키에서 못읽지
	//alert(document.cookie);
	/*
	if(items) {
		var itemArray = items.split(",");
		if(itemArray.indexOf(id) != -1) { //이미 카트에 해당 제품이 있음
			alert("Already Exists.");
		} else {
			itemArray.push(id);
			if(itemArray.length > maxItem) {
				itemArray.length = maxItem;
				console.log("Too many items in the cart");
			}
			items = itemArray.join(",");
			setCookie("cart", items, expire);
		}
		
	} else {
		setCookie("cart", id, expire);
	}*/
}

function deleteCart() {
	document.cookie = "cart;path=/shop;max-age=0";
}