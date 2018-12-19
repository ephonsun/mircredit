/* 扩展核心API */
$.extend({
	namespace:function()	//添加命名空间的功能
	{
	    var a=arguments, o=null, i, j, d, rt;
	    for (i=0; i<a.length; ++i) {
	        d=a[i].split(".");
	        rt = d[0];
	        eval('if (typeof ' + rt + ' == "undefined"){' + rt + ' = {};} o = ' + rt + ';');
	        for (j=1; j<d.length; ++j) {
	            o[d[j]]=o[d[j]] || {};
	            o=o[d[j]];
	        }
	    }
	}
});

/* 判断浏览器 */
$.ua=navigator.userAgent;
$.opera=/opera[56789]|opera\/[56789]/i.test($.ua);
$.ie=(!$.opera)&&/MSIE/.test($.ua);
$.ie6=$.ie&&/MSIE [6]/.test($.ua);
$.moz=!$.opera&&/gecko/i.test($.ua);
$.chrome=navigator.userAgent.indexOf("Chrome") > -1;

/* 键盘操作 */
$.extend({
	keyboard:{
		enter:13, tab:9, up:38, down:40, left:37, right:39, space:32, shift:16, ctrl:17, alt:18, esc:27, f1:112, f2:113, f3:114, f4:115, f5:116, f6:117, f7:118, f8:119, f9:120, f10:121, f11:122, f12:123, del:46, backspace:8, insert:45, home:36, end:35, pgUp:33, pgDn:34, numLock:144, numPad0:96, numPad1:97, numPad2:98, numPad3:99, numPad4:100, numPad5:101, numPad6:102, numPad7:103, numPad8:104, numPad9:105, numPadDivide:111, numPadMultiply:106, numPadMinus:109,numPadPlus:107
		,shiftSymbol:[["~",192],["!",49],["@",50],["#",51],["$",52],["%",53],["^",54],["&",55],["*",56],["(",57],[")",48],["|",220],["{",219],["}",221],["\"",222],["<",188],[">",190],["?",191]].concat($.ie?[["_",189],["+",187],[":",186]]:$.moz?[["_",109],["+",61],[":",59]]:[])
		,unShiftSymbol:[["`",192],["-",189],["\\",220],["[",219],["]",221],["'",222],[",",188],[".",190],["/",191],["/",111],["*",106],["-",109],["+",107],[".",110]].concat($.ie?[["=",187],[";",186]]:$.moz?[["=",61],[";",59]]:[])
		,hasKeys:function(keys,e)
		{
			if (e==null)e =window.event;
			var ks = (typeof keys =="string")?keys.split(","):keys;
			for(var i=0;i<ks.length;i++)
			{
				var k=ks[i];
				if(k && this[k])
				{
					if(this.hasKey(k,e))return true;
				}
			}
			return false;
		}
		,hasKey:function(key,e)
		{
			if (e==null)e =window.event;
			if(e.keyCode == this.enter[key] || e.which == this.enter[key])return true;
		}
		,isSpecialKey:function(key)
	    {
	        return ((key >=112 &&key <=123)||key ==13 ||key ==32);
	    }
	    ,isDirection:function(key,e)
	    {
	        if (e==null)e =window.event;
	        if (key==null)key =e.keyCode || e.which;
	        return (37<=key &&key<=40);
	    }
	    ,isLetter:function(key,e)
	    {
	        if (e==null)e =window.event;
	        if (key==null)key =e.keyCode || e.which;
	        return (65<=key &&key<=90);
	    }
	    ,isUpperCaseLetter:function(key,e)
	    {
	        if (e==null)e =window.event;
	        if (key==null)key =e.keyCode || e.which;
	        return (65<=key &&key<=90 &&!e.shiftKey);
	    }
	    ,isLowerCaseLetter:function(key,e)
	    {
	        if (e==null)e =window.event;
	        if (key==null)key =e.keyCode || e.which;
	        return (65<=key &&key<=90 &&e.shiftKey);
	    }
	    ,isNumber:function(key,e)
	    {
	        if (e==null)e =window.event;
	        if (key==null)key =e.keyCode || e.which;
	        return ((48<=key &&key<=57 && !e.shiftKey) || (96<=key && key<=105) || e.ctrlKey);
	    }
	    ,isShiftSymbol:function(key,e)
	    {
	        if (e==null)e =window.event;
	        if (key==null)key =e.keyCode || e.which;
	        if (!e.shiftKey)return false;
	        var flag =false;
	        for(var index=0;index<this.shiftSymbol.length; index++)
	        {
	            if (this.shiftSymbol[index][1]==key)
	            {
	                flag =true;
	                break;
	            }
	        }
	        return flag;
	    }
	    ,isUnShiftSymbol:function(key,e)
	    {
	        if (e==null)e =window.event;
	        if (key==null)key =e.keyCode || e.which;
	        if (e.shiftKey)return false;
	        var flag =false;
	        for(var index=0;index<this.unShiftSymbol.length; index++)
	        {
	            if (this.unShiftSymbol[index][1]==key)
	            {
	                flag =true;
	                break;
	            }
	        }
	        return flag;
	    }
	    ,isSymbol:function (key,e)
	    {
	        return (this.isShiftSymbol(key,e)||this.isUnShiftSymbol(key,e));
	    }
	    ,getChar:function(key,e)
	    {
	        var c ="";
	        if (e==null)e =window.event;
	        if (key==null)key =e.keyCode || e.which;
	        if (this.isLetter(key))
	        {
	            c =String.fromCharCode(key);
	            if (!e.shiftKey)c =c.toLowerCase();
	        }
	        else
	        {
	            if (this.isNumber(key))
	            {
	                if (48<=key &&key<=57) c =key -48;
	                else if (96<=key &&key<=105) c =key -96;
	                c =c.toString();
	            }
	            else
	            {
	                if (e.shiftKey)
	                {
	                    for(var i=0;i<this.shiftSymbol.length; i++)
	                    {
	                        if (this.shiftSymbol[i][1]==key)
	                        {
	                            c =this.shiftSymbol[i][0];
	                            break;
	                        }
	                    }
	                }
	                else
	                {
	                    for(var i=0;i<this.unShiftSymbol.length; i++)
	                    {
	                        if (this.unShiftSymbol[i][1]==key)
	                        {
	                            c =this.unShiftSymbol[i][0];
	                            break;
	                        }
	                    }
	                }
	            }
	        }
	        return c;
	    }
	}
});

(function($){
	/* 得到文件光标的位置 */
    $.fn.selection = function(){
        var s,e,range,stored_range;
        if(this[0].selectionStart == undefined){
            var selection=document.selection;
            if (this[0].tagName.toLowerCase() != "textarea") {
                var val = this.val();
                range = selection.createRange().duplicate();
                range.moveEnd("character", val.length);
                s = (range.text == "" ? val.length:val.lastIndexOf(range.text));
                range = selection.createRange().duplicate();
                range.moveStart("character", -val.length);
                e = range.text.length;
            }else {
                range = selection.createRange(),
                stored_range = range.duplicate();
                stored_range.moveToElementText(this[0]);
                stored_range.setEndPoint('EndToEnd', range);
                s = stored_range.text.length - range.text.length;
                e = s + range.text.length;
            }
        }else{
            s=this[0].selectionStart,
            e=this[0].selectionEnd;
        }
        var te=this[0].value.substring(s,e);
        return {start:s,end:e,text:te}
    };
    /* 重置文件光标置未尾 */
    $.fn.resetCursor = function(s,e){
    	if(this[0].createTextRange)
        {
    		if(s!=null)
    		{
	    		var val = this.val();
	            var range=this[0].createTextRange(); 
	            range.moveStart('character',s); 
	            range.collapse(true);
	            range.select(e);
    		}
    		else
    		{
    			var val = this.val();
	            var range=this[0].createTextRange(); 
	            range.moveStart('character',val.length); 
	            range.collapse(true);
	            range.select();
    		}
        }
        else if(this[0].setSelectionRange)
        {
        	if(s!=null)
        	{
            	this[0].setSelectionRange(s,e);
        	}
        	else
        	{
        		var n=this.val().length;
            	this[0].setSelectionRange(n,n);
        	}
        }
    };
})(jQuery);

/* 扩展Json操作 */
$.extend({
	toJsonString:function(json)	//Json对像转化为字符串
	{
		if(json!=null)
		{
			if(json.constructor==Array)return $.json.arrayToJson(json);
			else if(json.constructor==Date)return $.json.dateToJson(json);
			else
			{
				switch(typeof(json))
		        {
		            case "object":
		            	return $.json.objectToJson(json);
		            case "string":
		            	return $.json.stringToJson(json);
		            default:
		            	return json;
		        }
			}
		}
		else return "null";
	}
	,json:{
		objectToJson:function(obj)
		{
			var json = [];   
		    for(var n in obj){   
		        if(!obj.hasOwnProperty(n)) continue;   
		        json.push(
		            $.toJsonString(n) + " : " + 
		            ((obj[n] != null) ? $.toJsonString(obj[n]) : "null")
		        )
		    }
		    var beginStr="{\n ";
		    var endStr="\n};";
		    return  beginStr+json.join(",\n ") + endStr.substr(0,2);
		}
		,arrayToJson:function(array)
		{
			for(var i=0,json=[];i<array.length;i++)   
	        json[i] = (array[i] != null) ? $.toJsonString(array[i]) : "null"; 
	    	return "["+json.join(", ")+"]";
		}
		,stringToJson:function(str)		
		{
			return '"' +   
	        str.replace(/(\\|\")/g,"\\$1")   
	        .replace(/\n|\r|\t/g,function(){   
	            var a = arguments[0];   
	            return  (a == '\n') ? '\\n':   
	                    (a == '\r') ? '\\r':   
	                    (a == '\t') ? '\\t': ""  
	        }) +   
	        '"'
		}
		,dateToJson:function(date)
		{
			return "new Date(" + date.getTime() + date.getTimezoneOffset() + ")";
		}
	}
});