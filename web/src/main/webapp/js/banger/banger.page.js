/**
 * 按键输入控件
 */
$.page={
	openDialog:function(url,arguments,width,height,fProperty)			//在模态对话框封装
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
		
		if(jQuery.browser.msie && jQuery.browser.version<7.0){
			height = height + 30;
		}
	
		left = (sw-width)/2;
		top = (sh-height)/2;
	
		features = "dialogWidth=" + width + "px;dialogHeight=" + height + "px;";
		features +="dialogLeft=" + left + "px;dialogTop=" + top + "px;";
		features += (fProperty != null && fProperty != "") ?fProperty:"toolbar=no;menubar=no;scrollbars=no;resizable=no;location=no;status=no;"
		
		var args = this.fn.addDialogWindowArgument(arguments);
		
		var dialogReturn = window.showModalDialog(url,args,features);
		return dialogReturn;
	}
	,showMessageBox:function(arguments)					//弹出消息对话框
	{
		var box = window.top.art;
		if(box)
		{
			var args = this.fn.addMessageBoxDefaultArguments(arguments);
			box.dialog(args);
		}
	}
	,fn:{
		addDialogWindowArgument:function(args)			//在打开模态对话框时,参数追加window对像参数
		{
			var newArgs = {"window":window};
			if(args!=null)
			{
				switch(typeof args)
		        {
		            case "object":
		            {
		            	for(var nm in args)newArgs[nm]=args[nm];
		            }break;
		            case "string":
		            {
		            	newArgs["arg"]=args;
		            }break;
		        }
			}
			return newArgs;
		}
		,addMessageBoxDefaultArguments:function(args)			//添加消息对话框的缺省参数
		{
			var newArgs = {"title":"警告","content":"","icon":"warning","lock":true,"fixed":true,"opacity":0};
			switch(typeof args)
	        {
	            case "string":
	            {
	            	newArgs["content"]=args;
	            }break;
	            case "object":
	            {
	            	if(args["type"])
	            	{
	            		switch(args["type"])
	            		{
	            			case "warning":
	            				newArgs["title"]="警告";
	            				newArgs["icon"]="warning";
	            				break;
	            			case "success":
	            				newArgs["title"]="成功";
	            				newArgs["icon"]="succeed";
	            				newArgs["time"]=2;
	            				break;
	            			case "error":
	            				newArgs["title"]="错误";
	            				newArgs["icon"]="error";
	            				break;
	            		}
	            	}
	            	for(var nm in args)newArgs[nm]=args[nm];
	            }break;
	        }
			if(!newArgs["ok"] && newArgs["icon"]!="succeed")newArgs["ok"]=true;
			return newArgs;
		}
	}
}

banger.page=$.page;