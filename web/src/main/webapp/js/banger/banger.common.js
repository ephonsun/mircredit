// JavaScript Document

// Reset Url
function cropurl(url, name, value){
	var reg = new RegExp("(\\\?|&)" + name + "=([^&]+)(&|$)", "i");
	var m = url.match(reg);
	
	if(typeof value != "undefined"){
		if(m){
			return (url.replace(reg, function($0, $1, $2){
				return ($0.replace($2, value));
			}));
		}
		else{
			if(url.indexOf("?") == -1){
				return (url + "?" + name + "=" + value);
			}
			else{
				return (url + "&" + name + "=" + value);
			}
		}
	}
	else{
		if(m){
			return m[2];
		}
		else{
			return "";
		}
	}
}


//重新包装jQuery Datepicker组件
$.Calendar = {};
$.Calendar.defaults = {
	showAnim: '',
	showButtonPanel: true,
	prevText: '上一月',
	nextText: '下一月',
	monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
	monthNamesShort: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
	dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
	closeText: '关闭',
	clearText: '清除',
	currentText: '今天',
	showOtherMonths: true,
	selectOtherMonths: false,
	changeMonth: true,
	changeYear: true,
	yearRange: '-100:+10',
	dateFormat: 'yy-mm-dd',
	onSelect: function(text, inst){
		$(inst.input).focus().blur();
	}
};
$.Calendar.init = function(args1, args2){
	args1 = typeof(args1) === 'string' ? args1 : args1.join(',');
	
	args2 = $.extend({}, $.Calendar.defaults, args2);
	
	$(args1).datepicker(args2).next('i, span').click(function(){
		$(this).prev('input[type=\'text\']').click();
	});
};


$(function(){
	try{
		//Datepicker
		$.Calendar.init('#datepicker1, #datepicker2, :text[render="date"]');
	}catch(e){}
	
	try{
		//Change htmlTable td:even, td:odd background-color
		$(".htmlTable tr").find("td:even").css({"backgroundColor": "#eff4f8"});
		$(".htmlTable tr").find("td:odd").css({"backgroundColor": "#f3f7fb"});
	}catch(e){}
	
	try{
		//Form interlaced background-color change, and change the background-color selected
		$(".dataTable, .datatbl").table();
	}catch(e){}
	
	try{
		//Set the button style
		$(".btn1, .btn2").attr("hidefocus", "true").hover(
			function(){ $(this).addClass("btn2hover"); },
			function(){ $(this).removeClass("btn2hover").removeClass("btn2active"); }
		).mousedown(function(){
			$(this).addClass("btn2active");
			//$('.atree, .btree, .ui-subtree, .ui-agetree, .combobox').hide();
		}).mouseup(function(){
			$(this).removeClass("btn2active");
		});
	}catch(e){}
	
	try{
		//默认让页面中的第一个INPUT标记获得焦点
		$("input[type='text']:not([focusFlag='false']):first").focus();
	}catch(e){}
});

function initDate(id, args){
	//Datepicker
	$.Calendar.init('#' + id, args);
};

function checkPostName(name,filter)				//过滤提交的字段名称
{
	if(name!=null && name!="")
	{
		if(filter!=null && filter!="")
		{
			var ns = [];
			if(typeof filter == "string")
			{
				ns = filter.split(",");
			}
			else if(filter.constructor==Array)
			{
				ns = filter;
			}
			for(var i=0;i<ns.length;i++)
			{
				if(name.indexOf(ns[i])>-1)return true;
			}
			return false;
		}
		return true;
	}
	return false;
}

function getPostJson(filter){
	var json = {};
	
	$("input[type$='text']").each(function(i){
   		if(checkPostName(this.name,filter))json[this.name]=this.value.replace(/(^\s*)|(\s*$)/g,"");
 	});
 	$("input[type$='hidden']").each(function(i){
   		if(checkPostName(this.name,filter))json[this.name]=this.value.replace(/(^\s*)|(\s*$)/g,"");
 	});
 	$("input[type$='radio']").each(function(i){
   		if(checkPostName(this.name,filter))if(this.checked)json[this.name]=this.value;
 	});
 	$("input[type$='password']").each(function(i){
   		if(checkPostName(this.name,filter))json[this.name]=this.value.replace(/(^\s*)|(\s*$)/g,"");
 	});
 	$("input[type$='checkbox']").each(function(i){
		if(checkPostName(this.name,filter))if(this.checked)json[this.name]=this.value;
 	});
    $("textarea").each(function(i){
   		if(checkPostName(this.name,filter))json[this.name]=this.value.replace(/(^\s*)|(\s*$)/g,"");
 	});
    $("select").each(function(i){
   		if(checkPostName(this.name,filter))json[this.name]=this.value;
 	});
	return json;
};

function getUrlServer(url)
{
	if(url==null || url=="")url=document.location.href;
	var n = url.indexOf("http://");
	var m = url.indexOf("/",7);
	if(n!=-1 && m!=-1)
	{
		var addr = url.substr(7,m-7);
		return addr;
	}
}

function inChars(str,chars)
{
	for(var i=0;i<str.length;i++)
	{
		var c = str.charAt(i);
		if(!inChar(c,chars))return false;
	}
	return true;
}

function inChar(c,chars)
{
	for(var i=0;i<chars.length;i++)
	{
		if(c==chars.charAt(i))return true;
	}
	return false;
}

function hasChars(str,chars){
	for(var i=0;i<str.length;i++)
	{
		var c = str.charAt(i);
		if(chars.indexOf(c)>-1)return true;
	}
	return false;
}

function inCharCount(c,chars)
{
	var count = 0;
	for(var i=0;i<chars.length;i++)
	{
		if(c==chars.charAt(i))count++;
	}
	return count;
}

function inCharsCount(str,chars)
{
	var count = 0;
	for(var i=0;i<str.length;i++)
	{
		var c = str.charAt(i);
		count+=inCharCount(c,chars);
	}
	return count;
}

function maxTextArea(query)
{
	inputRule(query,function(text,src){
		if(text!="")
		{
			var maxStr = $(src).attr("maxLength");
			var max = parseInt(maxStr);
			if(text.length>max)return false;
			return true;
		}
		return true;
	},false);
}

function maxInput(query,max)
{
	inputRule(query,function(text){
		if(text!="")
		{
			if(text.length>max)return false;
			return true;
		}
		return true;
	},false);
}


function escapeInput(query,change)		//只能输入中文下划线数字
{
	inputRule(query,function(text){
		if(text!="")
		{
			var patrn=/^[a-zA-Z0-9-_\u4e00-\u9fa5]+$/;
			if(!patrn.exec(text))return false;
			return true;
		}
		return true;
	},false,function(param){if(change && param["oldText"]!=param["newText"])change();});
}

function codeInput(query,change)		//只能输入英文下划线数字
{
    inputRule(query,function(text){
        if(text!="")
        {
            var patrn=/^[a-zA-Z0-9-_]+$/;
            if(!patrn.exec(text))return false;
            return true;
        }
        return true;
    },false,function(param){if(change && param["oldText"]!=param["newText"])change();});
}

function telInput(query,change)		//座机
{
	inputRule(query,function(text){
		if(text!="")
		{
			if(!inChars(text,"0123456789#*"))return false;
			return true;
		}
		return true;
	},true,function(param){if(change && param["oldText"]!=param["newText"])change();});
}

function emailInput(query)		//邮箱
{
	inputRule(query,function(text){
		if(text!="")
		{
			if(!inChars(text,"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_@."))return false;
			else
			{
				var n = text.indexOf("@");
				var m = text.indexOf(".");
				if(n==0 || m==0)return false;
				if(inCharCount("@",text)>1)return false;
				if(n>-1){
					if(text.indexOf("@.")>-1)return false;
					if(text.indexOf("..",n)>-1)return false;
				}
				return true;
			}
		}
		return true;
	},true);
}

function idInput(query,change)		  //身份证
{
	inputRule(query,function(text){
		if(text!="")
		{
			if(!inChars(text,"0123456789xXyY"))return false;
			if(!inChar(text.substr(0,1),"0123456789"))return false;
			if(inCharsCount("xX",text)>1)return false;
			if(inCharsCount("yY",text)>1)return false;
			return true;
		}
		return true;
	},true,function(param){if(change && param["oldText"]!=param["newText"])change();});
}

function numInput(query,change)		//数字，不含小数点
{
	inputRule(query,function(text){
		if(text!="")
		{
			if(!inChars(text,"0123456789"))return false;
			else return true;
		}
		return true;
	},true,function(param){if(change && param["oldText"]!=param["newText"])change();});
}

function unsignedIntInput(query,change)		//无符号整数 大于0
{
	inputRule(query,function(text){
		if(text!="")
		{
			if(!inChars(text,"0123456789"))return false;
			if(text.substr(0,1)=='0') return false;
			return true;
		}
		return true;
	},true,function(param){if(change && param["oldText"]!=param["newText"])change();});
}

function mobileInput(query,change){			//手机
	inputRule(query,function(text){
		if(text!="")
		{
			if(!inChars(text,"0123456789"))return false;
			return text.substr(0,1)=='1';
		}
		return true;
	},true,function(param){if(change && param["oldText"]!=param["newText"])change();});
}


function dateInput(query)
{
	inputRule(query,function(text){
		if(text!="")
		{
			if(!inChars(text,"0123456789-"))return false;
			return true;
		}
		return true;
	},true);
}

function floatInput(query,size,scale)
{
	inputRule(query,{fn:function(text,src,args){
		if(text!="")
		{
			if(!inChars(text,"-0123456789."))return false;
			if(text.substr(0,1)=='.')return false;
			if(inCharsCount("-",text)>1)return false;
			if(inCharsCount(".",text)>1)return false;
			var n=text.indexOf("-");
			if(n>0)return false;
			var ztext = (n==0)?text.substr(1):text;
			var m=ztext.indexOf(".");
			if(args!=null)
			{
				if(args.size!=null)var size=args.size;
				if(args.scale!=null)var scale=args.scale;
			}
			if(size==null)var size=13;
			if(scale==null)var scale=4;
			if(m>-1)
			{
				if(m>size)return false;
				if(ztext.length-m-1>scale)return false;
			}
			else
			{
				if(ztext.length>size)return false;
			}
			return true;
		}
		return true;
	},args:{"size":size,"scale":scale}},true);
}

function positiveFloatInput(query,size,scale)
{
    inputRule(query,{fn:function(text,src,args){
        if(text!="")
        {
            if(!inChars(text,"0123456789."))return false;
            if(text.substr(0,1)=='.')return false;
            if(inCharsCount("-",text)>1)return false;
            if(inCharsCount(".",text)>1)return false;
            var n=text.indexOf("-");
            if(n>0)return false;
            var ztext = (n==0)?text.substr(1):text;
            var m=ztext.indexOf(".");
            if(args!=null)
            {
                if(args.size!=null)var size=args.size;
                if(args.scale!=null)var scale=args.scale;
            }
            if(size==null)var size=13;
            if(scale==null)var scale=4;
            if(m>-1)
            {
                if(m>size)return false;
                if(ztext.length-m-1>scale)return false;
            }
            else
            {
                if(ztext.length>size)return false;
            }
            return true;
        }
        return true;
    },args:{"size":size,"scale":scale}},true);
}

function getUrlParams(url)				//得到Url参数Map
{
    if(url==null || url=="")url=document.location.href;
    var s = url.indexOf("?");
    var str="";
    if(s>-1)str=url.substr(s+1,url.length-s-1);
    var paramParts=str.split('&');
    var params={};
    for(var i=0;i<paramParts.length;i++)
    {
        var part=paramParts[i];
        var ps=part.split('=');
        var key = ps[0];
        var val = ps[1];
        params[key]=val;
    }
    return params;
}

function newGuid()
{
    var guid = "";
    for (var i = 1; i <= 32; i++){
      var n = Math.floor(Math.random()*16.0).toString(16);
      guid +=   n;
      if((i==8)||(i==12)||(i==16)||(i==20))
        guid += "-";
    }
    return guid;    
}

var pc;

function setPageCheck(tableId,ckCol)
{
	if(!pc)pc=PageCheck.Construct(tableId,ckCol);
	var pageNum = document.getElementById("page.currentPage").value;
	pc.LoadPage(pageNum);
	$("#dataList").click(function(){
		pc.PageCheck(pageNum);
	});
}

function clearPageCheck()
{
	if(pc)pc.ClearAllChecks();
}

function getPageCheckValues()
{
	if(pc)
	{
		return pc.GetCheckedValues(); 
	}
	return [];
}

function requiredDate(obj,systemDate){
	if(Date.parse(obj.replace("-","/")) < Date.parse(systemDate.replace("-","/"))){
  		alert("日期必须晚于"+systemDate+"!");
  		return false;
 	}
	return true;
}

/*{"Function":{"Display":"打开一个窗口","Params":[{"url":"页面地址"},{"name":"窗口名称"},{"width":"窗口宽度"},{"height":"窗口高度"}}*/
function openWindow(url,name,width,height)
{
    var features,left,top;
	var sw = window.screen.width;
	var sh = window.screen.height;

	if(isNaN(width) || width == "")
	{
		width = 800;
	}
	if(isNaN(height) || height == "")
	{
		height = 600;
	}

	left = (sw-width)/2;
	top = (sh-height)/2;

	features = "width=" + width + "px,height=" + height + "px,";
	features +="left=" + left + "px,top=" + top + "px,";
	features += "toolbar=no,scrollbars=yes,status=no,directories=no,menubar=no,resizable=yes,scrollable=no";
	var winObj = window.open('about:blank',name,features);
	if(winObj != null)
	{
		winObj.location.href = url;
	}
	else
	{
		window.location.href = url;
	}
}

function openDialog(url,arguments,width,height,fProperty)
{
    var features,left,top;
	var sw = window.screen.width;
	var sh = window.screen.height;

	if(isNaN(width) || width == "")
	{
		width = 800;
	}
	if(isNaN(height) || height == "")
	{
		height = 600;
	}

	left = (sw-width)/2;
	top = (sh-height)/2;

	features = "dialogWidth=" + width + "px;dialogHeight=" + height + "px;";
	features +="dialogLeft=" + left + "px;dialogTop=" + top + "px;";
	features += (fProperty != null && fProperty != "") ?fProperty:"toolbar=no;menubar=no;scrollbars=no;resizable=no;location=no;status=no;"
	
	var openUrl=UrlHelper.RepairUrl(url);
	var dialogReturn = window.showModalDialog(openUrl, arguments,features);
	return dialogReturn;
}

/**
 * 判断表单是否被修改
 * @param {Object} form
 * @return {TypeName} 
 */
function formIsChange(form){
    var oElem;
    var isChange = false;
    cycle:for (var i = 0; i < form.elements.length; i++  ) {
        oElem = form[i];
        switch (oElem.type) {
            case "text" :
            case "password" :
            case "textarea" :
            case "file" :
            case "hidden" :
                if (oElem.defaultValue != oElem.value){
                    isChange = true;
                    break cycle;
                }
                break;
            case "checkbox" :
            case "radio" :
                if (oElem.defaultChecked != oElem.checked){
                    isChange = true;
                    break cycle;
                }
                break;
            case "select-one" :
            case "select-multiple" :
                for (var n = 0; n < oElem.options.length; n++  ) {
                    if (oElem.options[n].selected != oElem.options[n].defaultSelected) {
                        isChange = true;
                        break cycle;
                    }
                }
            break;
        }
    }
    return isChange;
}
//取得当前时间星期
function getClock(clock)
{
	var now = new Date();
	var year = now.getFullYear();
	var month = now.getMonth();
	var date = now.getDate();
	var day = now.getDay();
	month = month + 1;
	var arr_week = new Array ("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
	var week = arr_week[day];
	var time = year + "年" + month + "月" + date + "日" +"     "+ week;
	$("#"+clock).text(time);
}

//js时间格式化 和加减
Date.prototype.Format = function(fmt) {
	var o =
	{ 
	"M+" : this.getMonth() + 1, //月份 
	"d+" : this.getDate(), //日 
	"h+" : this.getHours(), //小时 
	"m+" : this.getMinutes(), //分 
	"s+" : this.getSeconds(), //秒 
	"q+" : Math.floor((this.getMonth() + 3) / 3), //季度 
	"S" : this.getMilliseconds() //毫秒 
	}; 
	if (/(y+)/.test(fmt)) 
	fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length)); 
	for (var k in o) 
	if (new RegExp("(" + k + ")").test(fmt)) 
	fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length))); 
	return fmt; 
}

Date.prototype.addDays = function(d){
	this.setDate(this.getDate() + d);
};


Date.prototype.addWeeks = function(w){
	this.addDays(w * 7);
};


Date.prototype.addMonths= function(m){
	var d = this.getDate();
	this.setMonth(this.getMonth() + m);
	
	if (this.getDate() < d)
	this.setDate(0);
};


Date.prototype.addYears = function(y){
	var m = this.getMonth();
	this.setFullYear(this.getFullYear() + y);
	
	if (m < this.getMonth()) 
	{
	this.setDate(0);
	}
};

function forbidBackSpace(e) {
    var ev = e || window.event; //获取event对象    
    var obj = ev.target || ev.srcElement; //获取事件源    
    var t = obj.type || obj.getAttribute('type'); //获取事件源类型    
    //获取作为判断条件的事件类型    
    var vReadOnly = obj.readOnly;
    var vDisabled = obj.disabled;
    //处理undefined值情况    
    vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;
    vDisabled = (vDisabled == undefined) ? true : vDisabled;
    //当敲Backspace键时，事件源类型为密码或单行、多行文本的，    
    //并且readOnly属性为true或disabled属性为true的，则退格键失效    
    var flag1 = ev.keyCode == 8 && (t == "password" || t == "text" || t == "textarea") && (vReadOnly == true || vDisabled == true);
    //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效    
    var flag2 = ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea";
    //判断    
    if (flag2 || flag1) return false;
}
//禁止后退键 作用于Firefox、Opera   
document.onkeypress = forbidBackSpace;
//禁止后退键作用于IE、Chrome   
document.onkeydown = forbidBackSpace; 


