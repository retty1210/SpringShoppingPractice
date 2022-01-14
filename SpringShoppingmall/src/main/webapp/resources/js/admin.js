$(document).ready(function() {
	$("select[name=selectstat]").change(function() {
			var sel = $(this).attr('id');
			var sss = sel.substr(10);
			//alert(sss + " change function");
			$.ajax({
				url: "./changeorderstat",
				type: "post",
				data: {
					id: $("#id" + sss).val(),
					selectstat: $("#selectstat" + sss).val(),
					orgstat: $("#orgstat" + sss).val()
				},
				dataType: "json",
				success: function(data) {
					alert(sss + "번 주문을" + data.selectstat + "번 주문상태로 변경하였습니다.");
					//$('#selectstat').attr('value',data.selectstat);
				}
			});
			
			/*$("#selectstatform").attr({
				"method": "post";
				"action": "/changeorderstat";
			});
			$("#selectstatform").submit();*/
	})
})