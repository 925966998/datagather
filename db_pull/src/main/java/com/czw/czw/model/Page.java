package com.czw.czw.model;

import java.io.Serializable;

import com.czw.czw.util.Const;

public class Page  implements Serializable{
	
	private int showCount; //每页显示记录数
	private int totalPage;		//总页数
	private int totalResult;	//总记录数
	private int currentPage;	//当前页
	private int currentResult;	//当前记录起始索引
	private boolean entityOrField;	//true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性
	private String pageStr;		//最终页面显示的底部翻页导航，详细见：getPageStr();
	private String newPageStr;		//最终页面显示的底部翻页导航，详细见：getPageStr();
	private PageData pd = new PageData();
	


	public Page(){
		try {
//			this.showCount = Integer.parseInt(Tools.readTxtFile(Const.PAGE));
			this.showCount = Const.PAGE_COUNT;
		} catch (Exception e) {
			this.showCount = 15;
		}
	}
	
	public int getTotalPage() {
		if(totalResult%showCount==0)
			totalPage = totalResult/showCount;
		else
			totalPage = totalResult/showCount+1;
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getTotalResult() {
		return totalResult;
	}
	
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}
	
	public int getCurrentPage() {
		if(currentPage<=0)
			currentPage = 1;
		if(currentPage>getTotalPage())
			currentPage = getTotalPage();
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	
	public void setPageStr(String pageStr) {
		this.pageStr = pageStr;
	}
	
	public int getShowCount() {
		return showCount;
	}
	
	public void setShowCount(int showCount) {
		
		this.showCount = showCount;
	}
	
	public int getCurrentResult() {
		currentResult = (getCurrentPage()-1)*getShowCount();
		if(currentResult<0)
			currentResult = 0;
		return currentResult;
	}
	
	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}
	
	public boolean isEntityOrField() {
		return entityOrField;
	}
	
	public void setEntityOrField(boolean entityOrField) {
		this.entityOrField = entityOrField;
	}
	
	public PageData getPd() {
		return pd;
	}

	public void setPd(PageData pd) {
		this.pd = pd;
	}
	
	public String getPageStr() {
		StringBuffer sb = new StringBuffer();
		if(totalResult>0){
			sb.append("<div class=\"pagination\">	<ul>\n");
			if(currentPage==1){
				sb.append("	<li><a>"+("共")+"<font color=red>"+totalResult+"</font>"+("条")+"</a></li>\n");
				sb.append("	<li><input type=\"number\" value=\"\" id=\"toGoPage\" style=\"width:50px;text-align:center;float:left\" placeholder=\""+("页码")+"\"/></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"toTZ();\"  class=\"btn btn-mini btn-success\">"+("跳转")+"</a></li>\n");
				sb.append("	<li><a>"+("首页")+"</a></li>\n");
				sb.append("	<li><a>"+("上页")+"</a></li>\n");
			}else{
				sb.append("	<li><a>"+("共")+"<font color=red>"+totalResult+"</font>"+("条")+"</a></li>\n");
				sb.append("	<li><input type=\"number\" value=\"\" id=\"toGoPage\" style=\"width:50px;text-align:center;float:left\" placeholder=\""+("页码")+"\"/></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"toTZ();\"  class=\"btn btn-mini btn-success\">"+("跳转")+"</a></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage(1)\">"+("首页")+"</a></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage("+(currentPage-1)+")\">"+("上页")+"</a></li>\n");
			}
			int showTag = 5;//分页标签显示数量
			int startTag = 1;
			if(currentPage>showTag){
				startTag = currentPage-1;
			}
			int endTag = startTag+showTag-1;
			for(int i=startTag; i<=totalPage && i<=endTag; i++){
				if(currentPage==i)
					sb.append("<li><a><font color='red'>"+i+"</font></a></li>\n");
				else
					sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage("+i+")\">"+i+"</a></li>\n");
			}
			if(currentPage==totalPage){
				sb.append("	<li><a>"+("下页")+"</a></li>\n");
				sb.append("	<li><a>"+("尾页")+"</a></li>\n");
			}else{
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage("+(currentPage+1)+")\">"+("下页")+"</a></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage("+totalPage+")\">"+("尾页")+"</a></li>\n");
			}
			sb.append("	<li><a>"+("第")+""+currentPage+""+("页")+"</a></li>\n");
			sb.append("	<li><a>"+("共")+""+totalPage+""+("页")+"</a></li>\n");
			
			sb.append("</ul></div>\n");
			sb.append("<script type=\"text/javascript\">\n");
			
			//换页函数
			sb.append("function nextPage(page){");
			sb.append(" try{ top.jzts();} catch(e){}");
			sb.append("	if(true && document.forms[0]){\n");
			sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
			sb.append("		if(url.indexOf('?')>-1){url += \"&"+(entityOrField?"currentPage":"page.currentPage")+"=\"+page;}\n");
			sb.append("		else{url += \"?"+(entityOrField?"currentPage":"page.currentPage")+"=\"+page;}\n");
			sb.append("		\n");
			sb.append("		document.forms[0].action = url;\n");
			sb.append("		document.forms[0].submit();\n");
			sb.append("	}else{\n");
			sb.append("		var url = document.location+'';\n");
			sb.append("		if(url.indexOf('?')>-1){\n");
			sb.append("			if(url.indexOf('currentPage')>-1){\n");
			sb.append("				var reg = /currentPage=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'currentPage='+page);\n");
			sb.append("			}else{\n");
			sb.append("				url += \"&"+(entityOrField?"currentPage":"page.currentPage")+"=\"+page;\n");
			sb.append("			}\n");
			sb.append("		}else{url += \"?"+(entityOrField?"currentPage":"page.currentPage")+"=\"+page;}\n");
			sb.append("		\n");
			sb.append("		document.location = url;\n");
			sb.append("	}\n");
			sb.append("}\n");
			
			//调整每页显示条数
			sb.append("function changeCount(value){");
			sb.append("  try{ top.jzts();} catch(e){}");
			sb.append("	if(true && document.forms[0]){\n");
			sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
			sb.append("		if(url.indexOf('?')>-1){url += \"&"+(entityOrField?"showCount":"page.showCount")+"=\"+value;}\n");
			sb.append("		else{url += \"?"+(entityOrField?"showCount":"page.showCount")+"=\"+value;}\n");
			sb.append("		\n");
			sb.append("		document.forms[0].action = url;\n");
			sb.append("		document.forms[0].submit();\n");
			sb.append("	}else{\n");
			sb.append("		var url = document.location+'';\n");
			sb.append("		if(url.indexOf('?')>-1){\n");
			sb.append("			if(url.indexOf('"+(entityOrField?"showCount":"page.showCount")+"')>-1){\n");
			sb.append("				var reg = /"+(entityOrField?"showCount":"page.showCount")+"=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'"+(entityOrField?"showCount":"page.showCount")+"='+value);\n");
			sb.append("			}else{\n");
			sb.append("				url += \"&"+(entityOrField?"showCount":"page.showCount")+"=\"+value;\n");
			sb.append("			}\n");
			sb.append("		}else{url += \"?"+(entityOrField?"showCount":"page.showCount")+"=\"+value;}\n");
			sb.append("		\n");
			sb.append("		document.location = url;\n");
			sb.append("	}\n");
			sb.append("}\n");
			
			//跳转函数 
			sb.append("function toTZ(){");
			sb.append("var toPaggeVlue = document.getElementById(\"toGoPage\").value;");
			sb.append("if(toPaggeVlue == ''){document.getElementById(\"toGoPage\").value=1;return;}");
			sb.append("if(isNaN(Number(toPaggeVlue))){document.getElementById(\"toGoPage\").value=1;return;}");
			sb.append("nextPage(toPaggeVlue);");
			sb.append("}\n");
			sb.append("</script>\n");
		}
		pageStr = sb.toString();
		return pageStr;
	}
	public String getNewPageStr() {
		StringBuffer sb = new StringBuffer();
		if(totalResult>0){
			sb.append("<div class=\"ju_page\">	<ul>\n");
			if(currentPage==1){
				sb.append("<li class=\"ju_upye\">上一页</li>\n");
			}else{
				sb.append("<li class=\"ju_upye\" onclick=\"nextPage("+(currentPage-1)+")\">上一页</li>\n");
			}
			int showTag = 3;//分页标签显示数量
			int startTag = 1;
			if(currentPage>showTag){
				startTag = currentPage-1;
			}
			int endTag = startTag+showTag-1;
			if(totalPage>=4){
				for(int i=startTag; i<=totalPage-2 && i<=endTag; i++){
					if(currentPage==i)
						sb.append("<li class=\"ju_number3\">"+i+"</li>\n");
					else
						sb.append("	<li class=\"ju_number\" onclick=\"nextPage("+i+")\">"+i+"</li>\n");
				}
					
				sb.append("<li class=\"ju_number\">...</li>");
				if(currentPage==totalPage-1){
					int num=totalPage-1;
					sb.append("<li class=\"ju_number3\">"+num+"</li>");
				}else{
					int num=totalPage-1;
					sb.append("<li class=\"ju_number\" onclick=\"nextPage("+num+")\">"+num+"</li>");
				}
				if(currentPage==totalPage){
					int num=totalPage;
					sb.append("<li class=\"ju_number3\">"+num+"</li>");
				}else{
					int num=totalPage;
					sb.append("<li class=\"ju_number\" onclick=\"nextPage("+num+")\">"+num+"</li>");
				}
			}else{
				for(int i=startTag; i<=totalPage && i<=endTag; i++){
					if(currentPage==i)
						sb.append("<li class=\"ju_number3\">"+i+"</li>\n");
					else
						sb.append("	<li class=\"ju_number\" onclick=\"nextPage("+i+")\">"+i+"</li>\n");
				}
			}
			if(currentPage==totalPage){
				sb.append("<li class=\"ju_upye\">下一页</li>");
			}else{
				sb.append("<li class=\"ju_upye\" onclick=\"nextPage("+(currentPage+1)+")\">下一页</li>");
			}
			sb.append("</ul></div>\n");
			sb.append("<script type=\"text/javascript\">\n");
			
			//换页函数
			sb.append("function nextPage(page){");
			sb.append(" try{ top.jzts();} catch(e){}");
			sb.append("	if(true && document.forms[0]){\n");
			sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
			sb.append("		if(url.indexOf('?')>-1){url += \"&"+(entityOrField?"currentPage":"page.currentPage")+"=\"+page;}\n");
			sb.append("		else{url += \"?"+(entityOrField?"currentPage":"page.currentPage")+"=\"+page;}\n");
			sb.append("		\n");
			sb.append("		document.forms[0].action = url;\n");
			sb.append("		document.forms[0].submit();\n");
			sb.append("	}else{\n");
			sb.append("		var url = document.location+'';\n");
			sb.append("		if(url.indexOf('?')>-1){\n");
			sb.append("			if(url.indexOf('currentPage')>-1){\n");
			sb.append("				var reg = /currentPage=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'currentPage='+page);\n");
			sb.append("			}else{\n");
			sb.append("				url += \"&"+(entityOrField?"currentPage":"page.currentPage")+"=\"+page;\n");
			sb.append("			}\n");
			sb.append("		}else{url += \"?"+(entityOrField?"currentPage":"page.currentPage")+"=\"+page;}\n");
			sb.append("		\n");
			sb.append("		document.location = url;\n");
			sb.append("	}\n");
			sb.append("}\n");
			
			//调整每页显示条数
			sb.append("function changeCount(value){");
			sb.append("  try{ top.jzts();} catch(e){}");
			sb.append("	if(true && document.forms[0]){\n");
			sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
			sb.append("		if(url.indexOf('?')>-1){url += \"&"+(entityOrField?"showCount":"page.showCount")+"=\"+value;}\n");
			sb.append("		else{url += \"?"+(entityOrField?"showCount":"page.showCount")+"=\"+value;}\n");
			sb.append("		\n");
			sb.append("		document.forms[0].action = url;\n");
			sb.append("		document.forms[0].submit();\n");
			sb.append("	}else{\n");
			sb.append("		var url = document.location+'';\n");
			sb.append("		if(url.indexOf('?')>-1){\n");
			sb.append("			if(url.indexOf('"+(entityOrField?"showCount":"page.showCount")+"')>-1){\n");
			sb.append("				var reg = /"+(entityOrField?"showCount":"page.showCount")+"=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'"+(entityOrField?"showCount":"page.showCount")+"='+value);\n");
			sb.append("			}else{\n");
			sb.append("				url += \"&"+(entityOrField?"showCount":"page.showCount")+"=\"+value;\n");
			sb.append("			}\n");
			sb.append("		}else{url += \"?"+(entityOrField?"showCount":"page.showCount")+"=\"+value;}\n");
			sb.append("		\n");
			sb.append("		document.location = url;\n");
			sb.append("	}\n");
			sb.append("}\n");
			
			//跳转函数 
			sb.append("function toTZ(){");
			sb.append("var toPaggeVlue = document.getElementById(\"toGoPage\").value;");
			sb.append("if(toPaggeVlue == ''){document.getElementById(\"toGoPage\").value=1;return;}");
			sb.append("if(isNaN(Number(toPaggeVlue))){document.getElementById(\"toGoPage\").value=1;return;}");
			sb.append("nextPage(toPaggeVlue);");
			sb.append("}\n");
			sb.append("</script>\n");
		}
		pageStr = sb.toString();
		return pageStr;
	}

	public void setNewPageStr(String newPageStr) {
		this.newPageStr = newPageStr;
	}

}
