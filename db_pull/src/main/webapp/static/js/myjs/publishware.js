var locat = (window.location + '').split('/');

var IMG_UPLOAD_SERVER = "";
$(function() {
	IMG_UPLOAD_SERVER = getImageUploadServer();
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
			msg : '输入的内容过多',
			bg : '#AE81FF',
			time : 2
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
					msg : '请填写正确的数字',
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
						msg : '请填写信息',
						bg : '#AE81FF',
						time : 2
					});
				} else {					
					$(this).tips({
						side : 3,
						msg : '请填写信息',
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

$(document)
		.ready(
				function() {
					var d = new Date();
					var str = d.getFullYear() + "/" + (d.getMonth() + 1) + "/"
							+ d.getDate();
					$(".uploadarea input[type='file']")
							.each(
									function() {
										$(this)
												.uploadify(
														{
															'buttonImg' : "./plugins/uploadify/uploadp.png",
															'uploader' : "./plugins/uploadify/uploadify.swf",
															'script' : IMG_UPLOAD_SERVER
																	+ "/plugins/uploadify/uploadFile.jsp;jsessionid="
																	+ jsessionid,
															'cancelImg' : "./plugins/uploadify/cancel.png",
															'folder' : "/uploadFiles/uploadImgs/ware/"
																	+ str + "/",// 上传文件存放的路径,请保持与uploadFile.jsp中PATH的值相同
															'queueId' : "fileQueue",
															'queueSizeLimit' : 100,// 限制上传文件的数量
															// 'fileExt' :
															// "*.rar,*.zip",
															// 'fileDesc' : "RAR
															// *.rar",//限制文件类型
															'fileExt' : '*.jpg;*.gif;*.png',
															'fileDesc' : 'Please choose(.JPG, .GIF, .PNG)',
															'auto' : true,
															'multi' : false,// 是否允许多文件上传
															'simUploadLimit' : 2,// 同时运行上传的进程数量
															'buttonText' : "files",
															'scriptData' : {
																'uploadPath' : '/uploadFiles/uploadImgs/ware/'
																		+ str
																		+ '/'
															},// 这个参数用于传递用户自己的参数，此时'method'
															// 必须设置为GET,
															// 后台可以用request.getParameter('name')获取名字的值
															'method' : "GET",
															'onComplete' : function(
																	event,
																	queueId,
																	fileObj,
																	response,
																	data) {
																var p = $(
																		event.target)
																		.parent()
																		.parent()
																		.parent();
																var path = '/uploadFiles/uploadImgs/ware/'
																		+ str
																		+ '/'
																		+ response
																				.trim();
																$(
																		"input[type='hidden']",
																		p).val(
																		path);
																$("img", p)
																		.attr(
																				'src',
																				IMG_UPLOAD_SERVER
																						+ path);
															},
															'onAllComplete' : function(
																	event, data) {

															},
															'onSelect' : function(
																	event,
																	queueId,
																	fileObj) {
															}
														});

									})

				});
function delpic(obj) {
	var p = $(obj).parent().parent().parent();
	$("input[type='hidden']", p).val("");
	$("img", p).attr('src', './static/images/CH_fbimage/CP_BG.jpg');
}