
var DialogImagePath = "Images/Controls/PopupLayer/";
var PagePath = "";
var isIE = navigator.userAgent.toLowerCase().indexOf("msie") != -1;
var isIE6 = navigator.userAgent.toLowerCase().indexOf("msie 6.0") != -1;
var isGecko = navigator.userAgent.toLowerCase().indexOf("gecko") != -1;

var isQuirks = document.compatMode == "BackCompat"; //to determine the current browser rendering

function $(ele) {
	if(typeof(ele) == 'string') {
		ele = document.getElementById(ele);
		if(!ele) {
			return null;
		}
  	}
  	if(ele){
		Core.attachMethod(ele);
	}
  	return ele;
}
function $T(tagName,ele){
	ele = $(ele);
	ele = ele || document;
	var ts = ele.getElementsByTagName(tagName); //here to return to is not an array
	var arr = [];
	var len = ts.length;
	for(var i=0;i<len;i++){
		arr.push($(ts[i]));
	}
	return arr;
}

//prevent all incidents of execution, including the browser's default event
function stopEvent(event){
	event = window.event||event;
	if(!event){
		return;
	}
	if(isGecko){
		event.preventDefault();
		event.stopPropagation();
	}
	event.cancelBubble = true;
	event.returnValue = false;
}

Array.prototype.remove = function(s){
  for(var i=0;i<this.length;i++){
    if(s == this[i]){
    	this.splice(i, 1);
    }
  }
}
if(window.HTMLElement){//给FF添加IE专有的属性和方法
    HTMLElement.prototype.__defineGetter__("parentElement",function(){
        if(this.parentNode==this.ownerDocument)return null;
        return this.parentNode;
        });
	HTMLElement.prototype.__defineSetter__("outerHTML",function(sHTML){
        var r=this.ownerDocument.createRange();
        r.setStartBefore(this);
        var df=r.createContextualFragment(sHTML);
        this.parentNode.replaceChild(df,this);
        return sHTML;
        });
    HTMLElement.prototype.__defineGetter__("outerHTML",function(){
        var attr;
        var attrs=this.attributes;
        var str="<"+this.tagName;
        for(var i=0;i<attrs.length;i++){
            attr=attrs[i];
            if(attr.specified)
                str+=" "+attr.name+'="'+attr.value+'"';
            }
        if(!this.canHaveChildren)
            return str+">";
        return str+">"+this.innerHTML+"</"+this.tagName+">";
        });
    HTMLElement.prototype.__defineSetter__("innerText",function(sText){
        var parsedText=document.createTextNode(sText);
        this.innerHTML=parsedText;
        return parsedText;
        });
    HTMLElement.prototype.__defineGetter__("innerText",function(){
        var r=this.ownerDocument.createRange();
        r.selectNodeContents(this);
        return r.toString();
        });
}

//Create object $E
var $E = {};
//Get the value of element attributes
$E.$A = function(attr, ele) {
	ele = ele || this;
	ele = $(ele);
	return ele.getAttribute ? ele.getAttribute(attr) : null;
}
//Get top-level window of the element
$E.getTopLevelWindow = function(){
	var pw = window;
	while(pw != pw.parent){
		pw = pw.parent;
	}
	return pw;
}
//Hide the element
$E.hide = function(ele) {
	ele = ele || this;
	ele = $(ele);
  	ele.style.display = "none";
}
//Display the element
$E.show = function(ele) {
	ele = ele || this;
	ele = $(ele);
	ele.style.display = '';
}
//Set the availability of element
$E.visible = function(ele) {
	ele = ele || this;
	ele = $(ele);
	if(ele.style.display == "none"){
		return false;
	}
	return true;
}

////Create object Core
var Core = {};
Core.attachMethod = function(ele){
	if(!ele || ele["$A"]){
		return;
	}
	if(ele.nodeType == 9){
		return;
	}
	var win;
	try{
		if(isGecko){
			win = ele.ownerDocument.defaultView;
		}else{
			win = ele.ownerDocument.parentWindow;
		}
		for(var prop in $E){
			ele[prop] = win.$E[prop];
		}
	} catch(ex) {
		//alert("Core.attachMethod:"+ele)//有些对象不能附加属性，如flash
	}
}

function Dialog(Id){
	if(!Id){
		alert("Error Dialog ID!"); return;
	}
	this.Id = Id;
	this.Title = "";
	this.URL = null;
	this.isModal = true;
	this.Width = 0;
	this.Height = 0;
	this.Top = 0;
	this.Left = 0;
	this.Window = null;
	this.ParentWindow = null;
	this.onLoad = null;
	this.innerHTML = null;
	this.innerElementId = null;
	this.DialogArguments = {};
	this.WindowFlag = false;
	this.Message = null;
	this.MessageTitle = null;
	this.ShowMessageRow = false;
	this.ShowButtonRow = true;
	this.Icon = null;
	this.bgdivID = null;
}

Dialog.dArray = [];

Dialog.prototype.showWindow = function(){
	if(isIE){
		alert(1);
		this.ParentWindow.showModalessDialog( this.URL, this.DialogArguments, "dialogWidth:" + this.Width + ";dialogHeight:" + this.Height + ";help:no;scroll:no;status:no") ;
	}
	if(isGecko){
		var sOption = "location=no,menubar=no,status=no;toolbar=no,dependent=yes,dialog=yes,minimizable=no,modal=yes,alwaysRaised=yes,resizable=no";
		this.Window = this.ParentWindow.open('', this.URL, sOption, true);
		var w = this.Window;
		if(!w){
			alert("发现弹出窗口被阻止，请更改浏览器设置，以便正常使用本功能!");
			return ;
		}
		w.moveTo(this.Left, this.Top);
		w.resizeTo(this.Width, this.Height+30);
		w.focus();
		w.location.href = this.URL;
		w.Parent = this.ParentWindow;
		w.dialogArguments = this.DialogArguments;
	}
}

Dialog.prototype.show = function(win, str){
	var pw = $E.getTopLevelWindow();
	var doc = pw.document;
	var cw = doc.compatMode=="BackCompat" ? doc.body.clientWidth : doc.documentElement.clientWidth;
	var ch = doc.compatMode=="BackCompat" ? doc.body.clientHeight : doc.documentElement.clientHeight;//必须考虑文本框处于页面边缘处，控件显示不全的问题
	var sl = Math.max(doc.documentElement.scrollLeft, doc.body.scrollLeft);
	var st = Math.max(doc.documentElement.scrollTop, doc.body.scrollTop);//考虑滚动的情况
	var sw = Math.max(doc.documentElement.scrollWidth, doc.body.scrollWidth);
	var sh = Math.max(doc.documentElement.scrollHeight, doc.body.scrollHeight);//考虑滚动的情况
	sw = Math.max(sw, cw);
	sh = Math.max(sh, ch);
	//alert("\n"+cw+"\n"+ch+"\n"+sw+"\n"+sh)

	if(!this.ParentWindow){
	 	this.ParentWindow = window;
	}
	this.DialogArguments._DialogInstance = this;
	this.DialogArguments.ID = this.Id;
	if(!this.Height){
		this.Height = this.Width/2;
	}
	if(this.Top==0){
		this.Top = (ch - this.Height - 30) / 2 + st - 8;
	}
	if(this.Left==0){
		this.Left = (cw - this.Width - 12) / 2 + sl;
	}
	if(this.ShowButtonRow){//button row line-height 36
		this.Top -= 18;
	}
	if(this.WindowFlag){
		this.showWindow();
		return;
	}
	var arr = [];
	arr.push("<table bgcolor=\"#ffffff\" border=\"0\" width=\"" + this.Width + "\" style=\"-moz-user-select: none;\" cellpadding=\"0\" cellspacing=\"0\" onselectstart=\"stopEvent(event);\" oncontextmenu=\"stopEvent(event);\">");
	arr.push("	<tr id=\"dBox-Header-" + this.Id + "\" style=\"cursor: move;\">");
	arr.push("	  <td width=\"10\" height=\"36\" style=\"background: url(" + DialogImagePath + "dialog_lt.png) no-repeat;\"><div style=\"width: 10px;\"></div></td>");
	arr.push("    <td height=\"36\" style=\"position: relative; background: url(" + DialogImagePath + "dialog_ct.png) repeat-x;\">");
	arr.push("		<div style=\"line-height: 36px; color: #ffffff; font-weight: bold;\">" + this.Title + "</div>");
	arr.push("      <div style=\"cursor: pointer; position: absolute; top: 0px; right: 0px; width: 42px; height: 19px; background: url(" + DialogImagePath + "dialog_close.png) no-repeat;\" onmouseover=\"this.style.background='url(" + DialogImagePath + "dialog_close_hover.png) no-repeat'\" onmouseout=\"this.style.background='url(" + DialogImagePath + "dialog_close.png) no-repeat'\" drag=\"false\" onclick=\"Dialog.getInstance('" + this.Id + "').ButtonCancel.onclick.apply(Dialog.getInstance('" + this.Id + "').ButtonCancel,[])\"></div>");
	arr.push("	  </td>");
	arr.push("    <td width=\"10\" height=\"36\" style=\"background: url(" + DialogImagePath + "dialog_rt.png) no-repeat;\"><div style=\"width: 10px;\"></div></td>");
	arr.push("  </tr>");
	arr.push("  <tr drag=\"false\">");
	arr.push("	  <td width=\"10\" style=\"background: url(" + DialogImagePath + "dialog_mlm.png) repeat-y;\"></td>");
	arr.push("    <td align=\"center\" valign=\"top\">");
	arr.push("		<a href=\"#;\" id=\"_forTab_" + this.Id + "\"></a>");
	arr.push("    	<table border=\"0\" bgcolor=\"#ffffff\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">");
	arr.push("        <tr id=\"dMessageRow-" + this.Id + "\" style=\"display: none;\">");
	arr.push("			<td height=\"50\" valign=\"top\">");
	arr.push("            <table id=\"dMessageRow-Table-" + this.Id + "\" border=\"0\" width=\"100%\" style=\"background: #eaece9 url(" + DialogImagePath + "dialog_bg.jpg) no-repeat right top;\" cellspacing=\"0\" cellpadding=\"8\">");
	arr.push("				<tr>");
	arr.push("                <td width=\"25\" height=\"50\" align=\"right\"><img id=\"_MessageIcon_" + this.Id + "\" src=\"" + DialogImagePath + "window.gif\" width=\"32\" height=\"32\"></td>");
	arr.push("                <td align=\"left\" style=\"line-height: 16px;\">");
	arr.push("                	<h5 class=\"fb\" id=\"_MessageTitle_" + this.Id + "\">&nbsp;</h5>");
	arr.push("                	<div id=\"_Message_" + this.Id + "\">&nbsp;</div>");
	arr.push("				  </td>");
	arr.push("			  </table>");
	arr.push("			</td>");
	arr.push("		  </tr>");
	arr.push("		  <tr>");
	arr.push("        	<td align=\"center\" valign=\"top\">");
	arr.push("			  <div style=\"position: relative; width: " + this.Width + "px; height: " + this.Height + "px;\">");
	arr.push("			  <div id=\"dCovering-" + this.Id + "\" style=\"position: absolute; height: 100%; width: 100%; display: none;\">&nbsp;</div>");
							if(this.innerHTML) {
								arr.push(this.innerHTML);
							}
							else if(this.innerElementId) {
								
							}
							else if(this.URL) {
								arr.push("          <iframe src=\"");
								if(this.URL.substr(0,7) == "http://" || this.URL.substr(0,1) == "/") {
									arr.push(this.URL);
								}
								else {
									arr.push(PagePath + this.URL);
								}
								arr.push("\" id=\"_DialogFrame_" + this.Id + "\" allowTransparency=\"true\" width=\"100%\" height=\"100%\" frameborder=\"0\" style=\"background-color: #fff; border: none;\"></iframe>");
							}
	arr.push("			  </div>");
	arr.push("			</td>");
	arr.push("        </tr>");
	arr.push("        <tr drag=\"false\" id=\"dButtonRow-" + this.Id + "\">");
	arr.push("			<td height=\"36\">");
	arr.push("            <div id=\"_DialogButtons_" + this.Id + "\" style=\"border-top: 1px solid #b8b8b8; background-color: #e6e6e6; padding: 8px 20px;\">");
	arr.push("           	<input id=\"_ButtonOK_" + this.Id + "\" type=\"button\" value=\"OK\" style=\"cursor: pointer; border: 0px; width: 58px; height: 22px; background: url(" + DialogImagePath + "dialog_btn.png) no-repeat;\">");
	arr.push("           	<input id=\"_ButtonCancel_" + this.Id + "\" type=\"button\" value=\"Cancel\" style=\"cursor: pointer; border: 0px; width: 58px; height: 22px; background: url(" + DialogImagePath + "dialog_btn.png) no-repeat;\" onclick=\"Dialog.getInstance('"+this.Id+"').close();\">");
	arr.push("			  </div>");
	arr.push("			</td>");
	arr.push("        </tr>");
	arr.push("		</table>");
	arr.push("		<a href=\"#;\" onfocus=\"$('_forTab_" + this.Id + "').focus();\"></a>");
	arr.push("	  </td>");
	arr.push("	  <td width=\"10\" style=\"background: url(" + DialogImagePath + "dialog_mrm.png) repeat-y;\"></td>");
	arr.push("	</tr>");
	arr.push("  <tr>");
	arr.push("	  <td width=\"10\" height=\"9\" style=\"background: url(" + DialogImagePath + "dialog_lb.png) no-repeat;\"></td>");
	arr.push("    <td height=\"9\" style=\"background: url(" + DialogImagePath + "dialog_cb.png) repeat-x;\"></td>");
	arr.push("    <td width=\"10\" height=\"9\" style=\"background: url(" + DialogImagePath + "dialog_rb.png) no-repeat;\"></td>");
	arr.push("  </tr>");
	arr.push("</table>");
	this.TopWindow = pw;

	var bgdiv = pw.$("_DialogBGDiv");
	if(!bgdiv){
		bgdiv = pw.document.createElement("div");
		bgdiv.id = "_DialogBGDiv";
		$E.hide(bgdiv);
	 	pw.$T("body")[0].appendChild(bgdiv);
		if(isIE6){
			var bgIframeBox=pw.document.createElement('<div style="position:relative;width:100%;height:100%;"></div>');
			var bgIframe=pw.document.createElement('<iframe src="about:blank" style="filter:alpha(opacity=1);" width="100%" height="100%"></iframe>');
			var bgIframeMask=pw.document.createElement('<div src="about:blank" style="position:absolute;background-color:#333;filter:alpha(opacity=1);width:100%;height:100%;"></div>');
			bgIframeBox.appendChild(bgIframeMask);
			bgIframeBox.appendChild(bgIframe);
			
			bgdiv.appendChild(bgIframeBox);
			
			var bgIframeDoc = bgIframe.contentWindow.document;
			bgIframeDoc.open();
			bgIframeDoc.write("<body style='background-color:#333' oncontextmenu='return false;'></body>") ;
			bgIframeDoc.close();
		}
	}

	var div = pw.$("_DialogDiv_"+this.Id);
	if(!div){
		div = pw.document.createElement("div");
		$E.hide(div);
		div.id = "_DialogDiv_"+this.Id;
		//div.className = "dialogdiv";
		//div.setAttribute("dragStart","Dialog.dragStart");
	 	pw.$T("body")[0].appendChild(div);
	}
	/*div.onmousedown = function(evt){
		var w = $E.getTopLevelWindow();
		//w.DragManager.onMouseDown(evt||w.event,this);//拖拽处理
	}*/

	this.DialogDiv = div;
	div.innerHTML = arr.join('\n');
	if(this.innerElementId){
		var innerElement=$(this.innerElementId);
		innerElement.style.position="";
		innerElement.style.display="";
		if(isIE){
			var fragment=pw.document.createElement("div");
			fragment.innerHTML=innerElement.outerHTML;
			innerElement.outerHTML="";
			pw.$("dCovering-"+this.Id).parentNode.appendChild(fragment)
		}else{
			pw.$("dCovering-"+this.Id).parentNode.appendChild(innerElement)
		}
	}
	pw.$("_DialogDiv_"+this.Id).DialogInstance = this;
	if(this.URL)
		pw.$("_DialogFrame_"+this.Id).DialogInstance = this;
	pw.Drag.init(pw.$("dBox-Header-"+this.Id),pw.$("_DialogDiv_"+this.Id));//注册拖拽方法
	if(!isIE){
		pw.$("_DialogDiv_"+this.Id).dialogId=this.Id;
		pw.$("_DialogDiv_"+this.Id).onDragStart = function(){$("dCovering-"+this.dialogId).style.display=""}
		pw.$("_DialogDiv_"+this.Id).onDragEnd = function(){$("dCovering-"+this.dialogId).style.display="none"}
	}

	this.ButtonOK = pw.$("_ButtonOK_"+this.Id);
	this.ButtonCancel = pw.$("_ButtonCancel_"+this.Id);

	//显示标题图片
	if(this.ShowMessageRow){
		$E.show(pw.$("dMessageRow-"+this.Id));
		if(this.MessageTitle){
			pw.$("_MessageTitle_"+this.Id).innerHTML = this.MessageTitle;
		}
		if(this.Message){
			pw.$("_Message_"+this.Id).innerHTML = this.Message;
		}
	}

	//显示按钮栏
	if(!this.ShowButtonRow){
		pw.$("dButtonRow-"+this.Id).hide();
	}
	//if(this.OKEvent){
	//	this.ButtonOK.onclick = this.OKEvent;
	//}
	this.ButtonOK.onclick = function(win, str){
		alert(win);
	}
	if(this.CancelEvent){
		this.ButtonCancel.onclick = this.CancelEvent;
	}
	if(!this.AlertFlag){
		$E.show(bgdiv);
		this.bgdivID = "_DialogBGDiv";
	}else{
		bgdiv = pw.$("_AlertBGDiv");
		if(!bgdiv){
			bgdiv = pw.document.createElement("div");
			bgdiv.id = "_AlertBGDiv";
			$E.hide(bgdiv);
		 	pw.$T("body")[0].appendChild(bgdiv);
			if(isIE6){
				var bgIframeBox=pw.document.createElement('<div style="position:relative;width:100%;height:100%;"></div>');
				var bgIframe=pw.document.createElement('<iframe src="about:blank" style="filter:alpha(opacity=1);" width="100%" height="100%"></iframe>');
				var bgIframeMask=pw.document.createElement('<div src="about:blank" style="position:absolute;background-color:#333;filter:alpha(opacity=1);width:100%;height:100%;"></div>');
				bgIframeBox.appendChild(bgIframeMask);
				bgIframeBox.appendChild(bgIframe);
				bgdiv.appendChild(bgIframeBox);
				var bgIframeDoc = bgIframe.contentWindow.document;
				bgIframeDoc.open();
				bgIframeDoc.write("<body style='background-color:#333' oncontextmenu='return false;'></body>") ;
				bgIframeDoc.close();
			}
			bgdiv.style.cssText = "background-color:#333;position:absolute;left:0px;top:0px;opacity:0.4;filter:alpha(opacity=40);width:100%;height:" + sh + "px;z-index:991";
		}
		$E.show(bgdiv);
		this.bgdivID = "_AlertBGDiv";
	}
	this.DialogDiv.style.cssText = "position:absolute; display:block;z-index:"+(this.AlertFlag?992:990)+";left:"+this.Left+"px;top:"+this.Top+"px";

	//判断当前窗口是否是对话框，如果是，则将其置在bgdiv之后
	if(!this.AlertFlag){
		var win = window;
		var flag = false;
		while(win!=win.parent){//需要考虑父窗口是弹出窗口中的一个iframe的情况
			if(win._DialogInstance){
				win._DialogInstance.DialogDiv.style.zIndex = 959;
				flag = true;
				break;
			}
			win = win.parent;
		}
		if(!flag){
			bgdiv.style.cssText = "background-color:#333;position:absolute;left:0px;top:0px;opacity:0.4;filter:alpha(opacity=40);width:100%;height:" + sh + "px;z-index:960";
		}
		//this.ParentWindow.$D = this;
	}
	this.ButtonOK.focus();

	var pwbody=doc.getElementsByTagName(isQuirks?"BODY":"HTML")[0];
	pwbody.style.overflow="hidden";//禁止出现滚动条

	pw.Dialog.dArray.push(this.Id);//放入队列中，以便于ESC时正确关闭
}

Dialog.prototype.addParam = function(paramName,paramValue){
		this.DialogArguments[paramName] = paramValue;
}

Dialog.prototype.close = function(){
	if(this.innerElementId){
		var innerElement=$E.getTopLevelWindow().$(this.innerElementId);
		innerElement.style.display="none";
		if(isIE){
			//ie下不能跨窗口拷贝元素
			var fragment=document.createElement("div");
			fragment.innerHTML=innerElement.outerHTML;
			innerElement.outerHTML="";
			$T("body")[0].appendChild(fragment)
		}else{
			$T("body")[0].appendChild(innerElement)
		}

	}
	if(this.WindowFlag){
		this.ParentWindow.$D = null;
		this.ParentWindow.$DW = null;
		this.Window.opener = null;
		this.Window.close();
		this.Window = null;
	}else{
		//如果上级窗口是对话框，则将其置于bgdiv前
		var pw = $E.getTopLevelWindow();
		var doc=pw.document;
		var win = window;
		var flag = false;
		while(win!=win.parent){
			if(win._DialogInstance){
				flag = true;
				win._DialogInstance.DialogDiv.style.zIndex = 960;
				break;
			}
			win = win.parent;
		}
		if(this.AlertFlag){
			$E.hide(pw.$("_AlertBGDiv"));
		}
		if(!flag&&!this.AlertFlag){//此处是为处理弹出窗口被关闭后iframe立即被重定向时背景层不消失的问题
			pw.eval('window._OpacityFunc = function(){var w = $E.getTopLevelWindow();$E.hide(w.$("_DialogBGDiv"));}');
			pw._OpacityFunc();
		}
		this.DialogDiv.outerHTML = "";
		var pwbody=doc.getElementsByTagName(isQuirks?"BODY":"HTML")[0];
		pwbody.style.overflow="auto";//还原滚动条
		pw.Dialog.dArray.remove(this.Id);
	}
}

Dialog.prototype.addButton = function(id,txt,func){
	var html = "<input id='_Button_"+this.Id+"_"+id+"' type='button' value='"+txt+"'> ";
	var pw = $E.getTopLevelWindow();
	pw.$("_DialogButtons_"+this.Id).$T("input")[0].getParent("a").insertAdjacentHTML("beforeBegin",html);
	pw.$("_Button_"+this.Id+"_"+id).onclick = func;
}

Dialog.close = function(evt){
	window.Args._DialogInstance.close();
}

Dialog.getInstance = function(id){
	var pw = $E.getTopLevelWindow()
	var f = pw.$("_DialogDiv_"+id);
	if(!f){
		return null;
	}
	return f.DialogInstance;
}


Dialog.AlertId = 0;
//-- Dialog.Alert(msg, func, w, h) --//
Dialog.Alert = function(msg, func, w, h){
	var pw = $E.getTopLevelWindow()
	var diag = new Dialog("dAlert" + Dialog.AlertId++);
	diag.ParentWindow = pw;
	diag.Width = w ? w : 300;
	diag.Height = h ? h : 120;
	diag.Title = "提示信息";
	diag.URL = "javascript:void(0);";
	diag.AlertFlag = true;
	//If user clicks the Cancel button ...
	diag.CancelEvent = function(){
		diag.close();
		if(func){
			func();
		}
	};
	diag.show();
	pw.$("_AlertBGDiv").style.display = "";
	$E.hide(pw.$("_ButtonOK_"+diag.Id));
	var win = pw.$("_DialogFrame_"+diag.Id).contentWindow;
	var doc = win.document;
	doc.open();
	doc.write("<body style='margin: 0px; padding: 0px;' oncontextmenu='return false;'></body>");
	var tableHTML =  "<table style='border: 0px; height: 100%; margin:0 auto; text-align: center;' cellpadding='10' cellspacing='0'>";
		tableHTML += "<tbody>";
		tableHTML += "<tr>";
		tableHTML += "<td align='center'>";
		tableHTML += "<img id='Icon' src='" + DialogImagePath + "success.png' width='48' height='48'>";
		tableHTML += "</td>";
		tableHTML += "<td id='Message' align='left' style='font-size: 14px; font-weight: bold;'>";
		tableHTML += msg;
		tableHTML += "</td>";
		tableHTML += "</tr>";
		tableHTML += "</tbody>";
		tableHTML += "</table>";
	doc.body.innerHTML = tableHTML;
	doc.close();
	var w = Math.max(doc.documentElement.scrollWidth, doc.body.scrollWidth);
	var h = Math.max(doc.documentElement.scrollHeight, doc.body.scrollHeight);
	if(w>300){
		win.frameElement.width = w;
	}
	if(h>120){
		win.frameElement.height = h;
	}
	diag.ButtonCancel.value = "关闭";
	diag.ButtonCancel.focus();
	pw.$("_DialogButtons_"+diag.Id).style.textAlign = "center";
}

//-- Dialog.Confirm(msg, func1, func2, w, h) --//
Dialog.Confirm = function(msg, func1, func2, w, h){
	var pw = $E.getTopLevelWindow()
	var diag = new Dialog("_DialogAlert"+Dialog.AlertId++);
	diag.Width = w ? w : 300;
	diag.Height = h ? h : 120;
	diag.Title = "信息确认";
	diag.URL = "javascript:void(0);";
	diag.AlertFlag = true;
	//If user clicks the OK button ...
	diag.OKEvent = function(){
		diag.close();
		if(func1){
			func1();
		}
	};
	//If user clicks the Cancel button ...
	diag.CancelEvent = function(){
		diag.close();
		if(func2){
			func2();
		}
	};
	diag.show();
	pw.$("_AlertBGDiv").style.dispaly="";
	var win = pw.$("_DialogFrame_"+diag.Id).contentWindow;
	var doc = win.document;
	doc.open();
	doc.write("<body style='margin: 0px; padding: 0px;' oncontextmenu='return false;'></body>");
	var tableHTML =  "<table style='border: 0px; height: 100%; margin:0 auto; text-align: center;' cellpadding='10' cellspacing='0'>";
		tableHTML += "<tbody>";
		tableHTML += "<tr>";
		tableHTML += "<td align='center'>";
		tableHTML += "<img id='Icon' src='" + DialogImagePath + "confirm.png' width='48' height='48'>";
		tableHTML += "</td>";
		tableHTML += "<td id='Message' align='left' style='font-size: 14px; font-weight: bold;'>";
		tableHTML += msg;
		tableHTML += "</td>";
		tableHTML += "</tr>";
		tableHTML += "</tbody>";
		tableHTML += "</table>";
	doc.body.innerHTML = tableHTML;
	doc.close();
	diag.ButtonOK.focus();
	pw.$("_DialogButtons_"+diag.Id).style.textAlign = "center";
}

var _DialogInstance = window.frameElement?window.frameElement.DialogInstance:null;
var Page={};
Page.onDialogLoad = function(){
	if(_DialogInstance){
		if(_DialogInstance.Title){
			document.title = _DialogInstance.Title;
		}
		window.Args = _DialogInstance.DialogArguments;
		_DialogInstance.Window = window;
		window.Parent = _DialogInstance.ParentWindow;
	}
}

Page.onDialogLoad();

PageOnLoad=function (){
	var d = _DialogInstance;
	if(d){
		try{
			d.ParentWindow.$D = d;
			d.ParentWindow.$DW = d.Window;
			var flag = false;
			if(!this.AlertFlag){
				var win = d.ParentWindow;
				while(win!=win.parent){
					if(win._DialogInstance){
						flag = true;
						break;
					}
					win = win.parent;
				}
				if(!flag){
					$E.getTopLevelWindow().$("_DialogBGDiv").style.opacity="0.4";
					$E.getTopLevelWindow().$("_DialogBGDiv").style.filter="alpha(opacity=40)";
				}
			}
			if(d.AlertFlag){
				$E.show($E.getTopLevelWindow().$("_AlertBGDiv"));
			}
			if(d.ShowButtonRow&&$E.visible(d.ButtonCancel)){
				d.ButtonCancel.focus();
			}
			if(d.onLoad){
				d.onLoad();
			}
		}catch(ex){alert("DialogOnLoad:"+ex.message+"\t("+ex.fileName+" "+ex.lineNumber+")");}
	}
}

Dialog.onKeyDown = function(event){
	if(event.shiftKey&&event.keyCode==9){//shift键
		var pw = $E.getTopLevelWindow();
		if(pw.Dialog.dArray.length>0){
			stopEvent(event);
			return false;
		}
	}
	if(event.keyCode==27){//ESC键
		var pw = $E.getTopLevelWindow();
		if(pw.Dialog.dArray.length>0){
			//Page.mousedown();
			//Page.click();
			var diag = pw.Dialog.getInstance(pw.Dialog.dArray[pw.Dialog.dArray.length-1]);
			diag.ButtonCancel.onclick.apply(diag.ButtonCancel,[]);
		}
	}
}

Dialog.dragStart = function(evt){
	//DragManager.doDrag(evt,this.getParent("div"));//拖拽处理
}
Dialog.setPosition=function(){
	if(window.parent!=window)return;
	var pw = $E.getTopLevelWindow();
	var DialogArr=pw.Dialog.dArray;
	if(DialogArr==null||DialogArr.length==0)return;

	for(i=0;i<DialogArr.length;i++)
	{
		pw.$("_DialogDiv_"+DialogArr[i]).DialogInstance.setPosition();
	}
}
Dialog.prototype.setPosition=function(){
	var pw = $E.getTopLevelWindow();
	var doc = pw.document;
	var cw = doc.compatMode == "BackCompat"?doc.body.clientWidth:doc.documentElement.clientWidth;
	var ch = doc.compatMode == "BackCompat"?doc.body.clientHeight:doc.documentElement.clientHeight;//必须考虑文本框处于页面边缘处，控件显示不全的问题
	var sl = Math.max(doc.documentElement.scrollLeft, doc.body.scrollLeft);
	var st = Math.max(doc.documentElement.scrollTop, doc.body.scrollTop);//考虑滚动的情况
	var sw = Math.max(doc.documentElement.scrollWidth, doc.body.scrollWidth);
	var sh = Math.max(doc.documentElement.scrollHeight, doc.body.scrollHeight);
	sw=Math.max(sw,cw);
	sh=Math.max(sh,ch);
	this.Top = (ch - this.Height - 30) / 2 + st - 8;//有8像素的透明背景
	this.Left = (cw - this.Width - 12) / 2 +sl;
	if(this.ShowButtonRow){//按钮行高36
		this.Top -= 18;
	}
	this.DialogDiv.style.top=this.Top+"px";
	this.DialogDiv.style.left=this.Left+"px";
	//pw.$(this.bgdivId).style.width= sw + "px";
	pw.$(this.bgdivID).style.height= sh + "px";
}

var Drag={
    "obj":null,
	"init":function(handle, dragBody, e){
		if (e == null) {
			handle.onmousedown=Drag.start;
		}
		handle.root = dragBody;

		if(isNaN(parseInt(handle.root.style.left)))handle.root.style.left="0px";
		if(isNaN(parseInt(handle.root.style.top)))handle.root.style.top="0px";
		handle.root.onDragStart=new Function();
		handle.root.onDragEnd=new Function();
		handle.root.onDrag=new Function();
		if (e !=null) {
			var handle=Drag.obj=handle;
			e=Drag.fixe(e);
			var top=parseInt(handle.root.style.top);
			var left=parseInt(handle.root.style.left);
			handle.root.onDragStart(left,top,e.pageX,e.pageY);
			handle.lastMouseX=e.pageX;
			handle.lastMouseY=e.pageY;
			document.onmousemove=Drag.drag;
			document.onmouseup=Drag.end;
		}
	},
	"start":function(e){
		var handle=Drag.obj=this;
		e=Drag.fixEvent(e);
		var top=parseInt(handle.root.style.top);
		var left=parseInt(handle.root.style.left);
		//alert(left)
		handle.root.onDragStart(left,top,e.pageX,e.pageY);
		handle.lastMouseX=e.pageX;
		handle.lastMouseY=e.pageY;
		document.onmousemove=Drag.drag;
		document.onmouseup=Drag.end;
		return false;
	},
	"drag":function(e){
		e=Drag.fixEvent(e);
							
		var handle=Drag.obj;
		var mouseY=e.pageY;
		var mouseX=e.pageX;
		var top=parseInt(handle.root.style.top);
		var left=parseInt(handle.root.style.left);
		
		if(isIE){Drag.obj.setCapture();}else{e.preventDefault();};//作用是将所有鼠标事件捕获到handle对象，对于firefox，以用preventDefault来取消事件的默认动作：

		var currentLeft,currentTop;
		currentLeft=left+mouseX-handle.lastMouseX;
		currentTop=top+(mouseY-handle.lastMouseY);
		handle.root.style.left=currentLeft +"px";
		handle.root.style.top=currentTop+"px";
		handle.lastMouseX=mouseX;
		handle.lastMouseY=mouseY;
		handle.root.onDrag(currentLeft,currentTop,e.pageX,e.pageY);
		return false;
	},
	"end":function(){
		if(isIE){Drag.obj.releaseCapture();};//取消所有鼠标事件捕获到handle对象
		document.onmousemove=null;
		document.onmouseup=null;
		Drag.obj.root.onDragEnd(parseInt(Drag.obj.root.style.left),parseInt(Drag.obj.root.style.top));
		Drag.obj=null;
	},
	"fixEvent":function(e){//格式化事件参数对象
		var sl = Math.max(document.documentElement.scrollLeft, document.body.scrollLeft);
		var st = Math.max(document.documentElement.scrollTop, document.body.scrollTop);
		if(typeof e=="undefined")e=window.event;
		if(typeof e.layerX=="undefined")e.layerX=e.offsetX;
		if(typeof e.layerY=="undefined")e.layerY=e.offsetY;
		if(typeof e.pageX == "undefined")e.pageX = e.clientX + sl - document.body.clientLeft;
		if(typeof e.pageY == "undefined")e.pageY = e.clientY + st - document.body.clientTop;
		return e;
	}
};

if(isIE){
	document.attachEvent("onkeydown",Dialog.onKeyDown);
	window.attachEvent("onload",PageOnLoad);
	window.attachEvent('onresize',Dialog.setPosition);
}else{
	document.addEventListener("keydown",Dialog.onKeyDown,false);
	window.addEventListener("load",PageOnLoad,false);
	window.addEventListener('resize',Dialog.setPosition,false);
}
