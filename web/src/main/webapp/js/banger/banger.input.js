/**
 * 按键输入控件
 */
$.input={
	addRule:function(query,fn,ime,callback)
	{
		if(ime)$(query).css({"ime-mode":"disabled"});
		$(query).bind({					//只能按数字键
			"keydown":function(e){
				var key = e.keyCode || e.which;
				var flag = (e.shiftKey && $.keyboard.insert==key) || (e.ctrlKey && key==86) || (e.ctrlKey && key==67);
				var shiftflag=(e.shiftKey && (key==106 || key==107 || key==109 || key==111));//add by wuxj for shift+数字键盘上的加回减乘除				
				
				if(key==229)
				{
					$.input.fn.imeKeyDown(this,fn);
					if(callback)$.input.fn.inputCallback(this,callback);
					return true;
				}
				else
				{	
					if(shiftflag){ /*add by wuxj*/
						return false;
					}
					
					if(!flag)
					{
						if(callback)$.input.fn.inputCallback(this,callback);
						return $.input.fn.textKeyDown(e,this,fn);
					}
					else
					{
						return true;
					}
				}
			}
			,"beforepaste":function(e){
				if(callback)$.input.fn.inputCallback(this,callback);
				return $.input.fn.textPasteNew(e,this,fn);
			}
			,"paste":function(e){
				var key = e.keyCode || e.which;
				if(callback)$.input.fn.inputCallback(this,callback);
				return $.input.fn.textPasteNew(e,this,fn);
			}
		});
	}
	,fn:{
		getInputArgs:function(elem,enter,key)
		{
			var args={};
			var otx=elem.val();
			var len = otx.length;
			var s=elem.selection().start;
			var e=elem.selection().end;
			var start = otx.substr(0,s);
    		var end = (e>0)?otx.substr(e,len-e):otx.substr(s,len-s);
    		var ntx = "";
    		if(s==e && $.keyboard.backspace==key)
    		{
    			ntx = (start!="")?start.substr(0,start.length-1)+end:end;
    		}
    		else if(s==e && $.keyboard.del==key)
    		{
    			ntx = (end!="")?start+end.substr(1,end.length-1):start;
    		}
    		else
    		{
    			ntx = start+enter+end;
    		}
    		args["oldText"]=otx;
    		args["newText"]=ntx;
    		return args;
		}
		,imeKeyDown:function(src,fn)
		{
			var elem = $(src);
			var s=elem.selection().start;
			var e=elem.selection().end;
			var oldText = elem.val();
			setTimeout(function(){
				var text = $(src).val();
				var flag = $.input.fn.exFun(fn,text,src);
				if(!flag){
					$(src).val(oldText);
					$(src).resetCursor(s,e);
				}
			},100);
		}
		,inputCallback:function(src,fn)
		{
			var oldText = $(src).val();
			setTimeout(function(){
				var text = $(src).val();
				var param ={};
				param["oldText"]=oldText;
				param["newText"]=text;
				fn(param,src);
			},100);
		}
		,textKeyDown:function(e,src,fn)
		{
			var elem = $(src);
			var c=$.keyboard.getChar();
			var key = e.keyCode || e.which;
			var char = (key==32)?" ":c;
			var args = this.getInputArgs(elem,char,key);
			var flag = $.input.fn.exFun(fn,args.newText,src);
			return flag;
		}
		,textPaste:function(e,src,fn)
		{
			var elem = $(src);
			var s=elem.selection().start;
			var e=elem.selection().end;
			var text = clipboardData.getData("Text");
			var args = this.getInputArgs(elem,text);
			var flag = $.input.fn.exFun(fn,args.newText,src);
			if(!flag)setTimeout(function(){
				elem.val(args.oldText);
				elem.resetCursor(s,e);
			},20);
			return flag;
		}
		,textPasteNew:function(e,src,fn)
		{
            var elem = $(src);
            var s=elem.selection().start;
            var e=elem.selection().end;
            var oldText = elem.val();
            setTimeout(function(){
                var newText = elem.val();
                var trimText=newText.replace(/(^\s*)|(\s*$)/g,"");
                var flag = $.input.fn.exFun(fn,newText,src);
                if(!flag){
                    if($.input.fn.exFun(fn,trimText,src))flag=1;
                }
                if(!flag){
                    elem.val(oldText);
                    elem.resetCursor(s,e);
                }
                else if(flag===1){
                    elem.val(trimText);
                }
            },20);
			return true;
		}
		,exFun:function(fn,text,src)
		{
			if(typeof fn == "function")return fn(text,src);
			else{
				return fn.fn(text,src,fn.args);
			}
		}
	}
}

function inputRule(query,fn,ime,callback)
{
	$.input.addRule(query,fn,ime,callback);
}

banger.input=$.input;