// JavaScript Document
$(function(){  

})

 function ruzhu_shop() {
	if ($("#ruzhu_shop").attr("class") == "current") {
		 $("#ruzhu_shop_div").show();
		 $("#ruzhu_name_div").hide();
		 $("#ruzhu_pwd_div").hide();
		 $("#ruzhu_name").attr("class","");
		 $("#ruzhu_pwd").attr("class","");
	}
}
function ruzhu_name() {
	if ($("#ruzhu_name").attr("class") == "current") {
		 $("#ruzhu_shop_div").hide();
		 $("#ruzhu_name_div").show();
		 $("#ruzhu_pwd_div").hide();
		 $("#ruzhu_pwd").attr("class","");
	}
}
function ruzhu_pwd() {
	if ($("#ruzhu_pwd").attr("class") == "current") {
		 $("#ruzhu_shop_div").hide();
		 $("#ruzhu_name_div").hide();
		 $("#ruzhu_pwd_div").show();
	}
}


 function tijiao_shop(){
	   var account = $.trim($("#account").val());
	   if(account.length<=0){
			$("#account").tips({
					side:3,
		            msg:'请输入账户名称',
		            bg:'#AE81FF',
		            time:3
		    });
			$("#account").focus();
			return false;
	   }
	   $("#account").val(account);
	   var companyName =$("#companyName").val();
	   if(companyName.length<=0){
			$("#companyName").tips({
					side:3,
		            msg:'请输入店铺名称',
		            bg:'#AE81FF',
		            time:3
		    });
			$("#companyName").focus();
			return false;
		}
	   
	   var companyPic=$("#COMPANY_PIC_2").val();
	   if(companyPic.length<=0){
			$("#COMPANY_PIC_1 img").tips({
					side:3,
		            msg:'请上传店铺图片',
		            bg:'#AE81FF',
		            time:3
		    });
			$("#companyName").focus();
			return false;
		}
	   
	   var companyDec =$("#companyDec").val();
	   if(companyDec.length<=0){
			$("#companyDec").tips({
					side:3,
		            msg:'请输入店铺简介',
		            bg:'#AE81FF',
		            time:3
		    });
			$("#companyDec").focus();
			return false;
		}
	   
	   var registerArea =$("#city-picker3").val();
	   if(registerArea.length<=0){
			$(".placeholder").tips({
					side:3,
		            msg:'请输入地址',
		            bg:'#AE81FF',
		            time:3
		    });
			$(".placeholder").focus();
			return false;
		}
	   var str=$("#hidd").val();
	   var strs=str.split(",");
	   if(strs.length<3){
		   $(".container").tips({
				side:3,
	            msg:'至少择到市级地区或者是您忘了点击确定按钮！',
	            bg:'#AE81FF',
	            time:3
	    });
		$(".placeholder").focus();
		return false;
	   }
	   
	   var registerAddress =$("#registerAddress").val();
	   if(registerAddress.length<=0){
			$("#registerAddress").tips({
					side:3,
		            msg:'请输入详细地址',
		            bg:'#AE81FF',
		            time:3
		    });
			$("#registerAddress").focus();
			return false;
		}
	   
	   
	   var brand =$("#brand").val();
	   if(brand==null){
			$(".filter-option").tips({
					side:3,
		            msg:'请输入主营品牌',
		            bg:'#AE81FF',
		            time:3
		    });
			$("#brand").focus();
			return false;
		}
	   var serviceTel =$("#serviceTel").val();
	   if(serviceTel.length<=0){
			$("#serviceTel").tips({
					side:3,
		            msg:'请输入客服电话',
		            bg:'#AE81FF',
		            time:3
		    });
			$("#serviceTel").focus();
			return false;
		}
	   
	    $.post('shop/verify',{'account':account,"companyName":companyName},function(data){
	    	if(data=="success"){
	    		    $("#ruzhu_shop_div").hide();
	    		    $("#ruzhu_name_div").show();
	    		    $("#ruzhu_pwd_div").hide();
	    		    $("#ruzhu_name").attr("class","current");
	    		    $("#legalPerson").focus();
	    	}else{
	    		  if(data=="account"){
	    			  $("#account").tips({
	    					side:3,
	    		            msg:'该账户已被注册',
	    		            bg:'#AE81FF',
	    		            time:3
	    		    });
	    			$("#account").focus();
	    			return false;
	    		  }else if(data=="company"){
	    			  $("#companyName").tips({
	    					side:3,
	    		            msg:'该账店铺名称已注册',
	    		            bg:'#AE81FF',
	    		            time:3
	    		    });
	    			$("#companyName").focus();
	    			return false;
	    		  }
	    			
	    	}
	    });
	   
	   
   }


function tijiao_name(){
	  var legalPerson =$("#legalPerson").val();
 	   if(legalPerson.length<=0){
 			$("#legalPerson").tips({
 					side:3,
 		            msg:'请输入法人名称',
 		            bg:'#AE81FF',
 		            time:3
 		    });
 			$("#legalPerson").focus();
 			return false;
 		}
 	   
 	 var legalPersonPhone =$("#legalPersonPhone").val();
 	 if(!(/^1(3|4|5|7|8)\d{9}$/.test(legalPersonPhone))){
 			$("#legalPersonPhone").tips({
 					side:3,
 		            msg:'请输入联系人手机号',
 		            bg:'#AE81FF',
 		            time:3
 		    });
 			$("#legalPersonPhone").focus();
 			return false;
 		}
 	   
 	 var legalPersonIdCard =$("#legalPersonIdCard").val();
 	   if(legalPersonIdCard.length<=0){
 			$("#legalPersonIdCard").tips({
 					side:3,
 		            msg:'请输入法人身份证号',
 		            bg:'#AE81FF',
 		            time:3
 		    });
 			$("#legalPersonIdCard").focus();
 			return false;
 		}
 	   
 	  var idCardZpic=$("#ID_CARD_ZPIC_2").val();
	   if(idCardZpic.length<=0){
			$("#ID_CARD_ZPIC_1 img").tips({
					side:3,
		            msg:'请上传身份证正面图片',
		            bg:'#AE81FF',
		            time:3
		    });
			$("#legalPersonIdCard").focus();
			return false;
		}
	   
	   var idCardBpic=$("#ID_CARD_BPIC_2").val();
	   if(idCardBpic.length<=0){
			$("#ID_CARD_BPIC_1 img").tips({
					side:3,
		            msg:'请上传身份证背面图片',
		            bg:'#AE81FF',
		            time:3
		    });
			$("#legalPersonIdCard").focus();
			return false;
		}
 	   
     var bank = $("#bank").val();
 	   if(bank.length<=0){
 			$("#bank").tips({
 					side:3,
 		            msg:'请输入开户行',
 		            bg:'#AE81FF',
 		            time:3
 		    });
 			$("#bank").focus();
 			return false;
 		}
 	   
 	 var bankNumber =$("#bankNumber").val();
 	   if(bankNumber.length<=0){
 			$("#bankNumber").tips({
 					side:3,
 		            msg:'请输入开户行卡号',
 		            bg:'#AE81FF',
 		            time:3
 		    });
 			$("#bankNumber").focus();
 			return false;
 		}
 	  var bankName = $("#bankName").val();
 	   if(bankName.length<=0){
 			$("#bankName").tips({
 					side:3,
 		            msg:'请输入户名(对公)',
 		            bg:'#AE81FF',
 		            time:3
 		    });
 			$("#bankName").focus();
 			return false;
 		}
 	   
 	   var businessPic=$("#BUSINESS_LICENCE_PIC_2").val();
	   if(businessPic.length<=0){
			$("#BUSINESS_LICENCE_PIC_1 img").tips({
					side:3,
		            msg:'请上传营业执照图片',
		            bg:'#AE81FF',
		            time:3
		    });
			$("#bankName").focus();
			return false;
		}
 	   
 	    $("#ruzhu_shop_div").hide();
	    $("#ruzhu_name_div").hide();
	    $("#ruzhu_pwd_div").show();
	    $("#ruzhu_pwd").attr("class","current");
	    $("#password").focus();
}



function ruzhu(){
	   var password =$("#password").val();
	   if(password.length<=0){
			$("#password").tips({
					side:3,
		            msg:'请输入登录密码',
		            bg:'#AE81FF',
		            time:3
		    });
			$("#password").focus();
			return false;
		}
	   
	   var password1 =$("#password1").val();
	   if(password1!=password){
			$("#password1").tips({
					side:3,
		            msg:'确认密码错误',
		            bg:'#AE81FF',
		            time:3
		    });
			$("#password1").focus();
			return false;
		}
	   
	  var txPassword =$("#txPassword").val();
	   if(txPassword.length<=0){
			$("#txPassword").tips({
					side:3,
		            msg:'请输入提现密码',
		            bg:'#AE81FF',
		            time:3
		    });
			$("#txPassword").focus();
			return false;
		}
	   
	   var txPassword1 =$("#txPassword1").val();
	   if(txPassword1!=txPassword){
			$("#txPassword1").tips({
					side:3,
		            msg:'确认密码错误',
		            bg:'#AE81FF',
		            time:3
		    });
			$("#txPassword1").focus();
			return false;
		}
	   
	   $("#ruzhuForm").submit();
}