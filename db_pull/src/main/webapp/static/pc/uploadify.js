$(document).ready(
    			function() {
    				var d = new Date();
    				var savePath = d.getFullYear() + "/" + (d.getMonth() + 1)+"/";;
    				$(".uploadarea input[type='file']")
					.each(
							
							function() {
								var index=$(this).attr("id");
								$(this)
										.uploadify(
												{
													'buttonImg' : "./plugins/uploadify/uploadp.png",
													'uploader' : "./plugins/uploadify/uploadify.swf",
													'script' : "../plugins/uploadify/uploadFile.jsp;jsessionid="+ jsessionid,
													'cancelImg' : "./plugins/uploadify/cancel.png",
													'folder' : "/company/"+savePath,
												    'queueId' : "fileQueue",
													'fileExt' : '*.jpg;*.gif;*.png',
													'auto' : true,	//是否自动上传
													'multi' : false,// 是否允许多文件上传
													'simUploadLimit' : 2,// 同时运行上传的进程数量
													'buttonText' : "files",
													'scriptData' : {
														'uploadPath' : '/company/'+savePath
													},
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
														var path = '/company/'+savePath+response.trim();
													    $("input[id='"+index+"_2'][type='hidden']",p).val(path);
														/*$("#"+index+"_1 img", p).attr('src',$("#picPath").val()+path); */
														$("#"+index+"_1 img").attr('src',$("#picPath").val()+path); 
													},
												});

							});

 });