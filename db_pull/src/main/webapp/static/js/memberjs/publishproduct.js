var locat = (window.location + '').split('/'); 
$(function() {
	if ('member' == locat[3]) {
		locat = locat[0] + '//' + locat[2];
	} else {
		locat = locat[0] + '//' + locat[2] + '/' + locat[3];
	}
	; 
	$('.date-picker').datepicker();
});

function getImageUploadServer() {
	var server = "";
	$.ajax({
		type : "GET",
		url : "./index/getimguploadserver",
		async : false,
		cache : false,
		success : function(data) {
			server = data;
		}
	});

	return server;
}
function save() {
	if (validateInput()) {
		// $("#CONTENT").val(getContent());
	
		$('#productForm').submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
}
$(function() {
	ueditor();
	// setTimeout("ueditor()", 500);
	// setTimeout(function() {
	// setContent($("#tempContent").html());
	// }, 1000);

});

function validateInput() {
	var isCheck = true;
	var isFirst = true;
	var text=$("#CONTENT").text();
	if(text.indexOf("服务器可能拒绝保存")>0){
		isCheck = false;
		$("#CONTENT").tips({
			side : 3,
			msg : message3,
			bg : '#AE81FF',
			time : 5
		});
	}
	var test=/^\d+(\.\d+)?$/;
	$("[validate]").each(function() {
		if ($(this).attr("validate") == "num") {
			if(!test.test($(this).val()))
				{
				isCheck = false;
				$(this).tips({
					side : 3,
					msg : message2,
					bg : '#AE81FF',
					time : 5
				});
				if (isFirst) {
					$(this).focus();
					isFirst = false;
				}
				}
		} else {
			if ($(this).val().trim() == "") {
				isCheck = false;
				// 如果是隐藏域在父容器显示
				if ($(this).attr("type") == "hidden") {
					$(this).parent().tips({
						side : 3,
						msg : message1,
						bg : '#AE81FF',
						time : 5
					});
				} else {					
					$(this).tips({
						side : 3,
						msg : message1,
						bg : '#AE81FF',
						time : 5
					});
					if (isFirst) {
						$(this).focus();
						isFirst = false;
					}
				}
			}
		}
	});

	return isCheck;

}
var ue;
function ueditor() {
	if (ue == null) {
		ue = new UE.ui.Editor();
		ue = ue.render('CONTENT');
	}
	return ue;
}

function setContent(content) {
	ueditor().setContent(content);
}
function getContent() {
	return ueditor().getContent();
}

