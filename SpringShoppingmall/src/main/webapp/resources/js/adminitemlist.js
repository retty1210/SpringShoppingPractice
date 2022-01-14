

$(document).ready(function() {
		$("td[name=hidden]").attr('style', "display:none;");
		$("button[name=closeb]").attr('style', "display:none;");
		$("button[name=deleteb]").attr('style', "display:none;");
		
		
		
	})
	
function opencheck() {
	$("td[name=hidden]").attr('style', "display:;");
	$("button[name=closeb]").attr('style', "display:;");
	$("button[name=deleteb]").attr('style', "display:;");
	$("button[name=openb]").attr('style', "display:none;");
}
	
function closecheck() {
	$("td[name=hidden]").attr('style', "display:none;");
	$("button[name=closeb]").attr('style', "display:none;");
	$("button[name=deleteb]").attr('style', "display:none;");
	$("button[name=openb]").attr('style', "display:;");
}

function deletecheckall() {
	var chkarr = [];
	$('input[name=deletecheck]:checked').each(function() {
			var chk = $(this).val();
			chkarr.push(chk);
		})
	var jo = chkarr.join("_");
	alert("삭제할 아이템 리스트: " + jo);
	$.ajax({
		url:"./deleteitem",
		type:"post",
		data: {
			arr: jo
		},
		dataType: "json",
		success: function(data) {
			alert(data.msg);
			
		}
	});
	
	$("td[name=hidden]").attr('style', "display:none;");
	$("button[name=closeb]").attr('style', "display:none;");
	$("button[name=deleteb]").attr('style', "display:none;");
	$("button[name=openb]").attr('style', "display:;");
	window.location.replace("/itemlist");
}