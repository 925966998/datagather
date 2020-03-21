function validateInput() {
	var isCheck = true;
	var isFirst=true;
	var test=/^\d+(\.\d+)?$/;
	$("[validate]").each(function() {
		if ($(this).attr("validate") == "num") {
			if(!test.test($(this).val()))
				{
				isCheck = false;
				$(this).tips({
					side : 3,
					msg : "请输入数字",
					bg : '#AE81FF',
					time : 2
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
						msg : "请填写信息",
						bg : '#AE81FF',
						time : 2
					});
				} else {					
					$(this).tips({
						side : 3,
						msg : "请填写信息",
						bg : '#AE81FF',
						time : 2
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
