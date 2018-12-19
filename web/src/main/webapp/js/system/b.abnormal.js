// JavaScript Document

var model = {
	wrapper:function(options){
		var callback = options["complete"] || options["success"];
		if(callback)
		{
			var request = this.getRequestWrapper(options);
			var fn=function(responseText){
				switch(responseText)
				{
					case "expired":
						model.expired(request);
						return false;
					//kicked
					case "kicked":
						model.kicked();
						return false;
					//disabled
					case "disabled":
						model.disabled();
						return false;
					//deleted
					case "deleted":
						model.deleted();
						return false;
					case "rolechang":
						model.rolechang();
						return false;	
					default:
						callback(responseText);
				}
			};
			if(options["complete"])options["complete"]=fn;
			if(options["success"])options["success"]=fn;
		}
	}
	,getRequestWrapper:function(options){
		return function(){
			jQuery.ajax(options);
		};
    },
    writeCookie:function(name,value,days){
        var expires = "";
        if (days){
            var date = new Date();
            date.setTime(date.getTime()+(days*24*60*60*1000));
            expires = "; expires="+date.toGMTString();
        }
        document.cookie = name + "=" + escape(value) + expires;
    }
	,enter:function(request){
		var bool = false,
			mAccount = model.element('mAccount'),
			mPassword = model.element('mPassword'),
			aError=model.element('aError'),
			pError=model.element('pError');
			
		if(!mAccount.value){
			aError.innerHTML = '用户名不能为空！';
			aError.style.display='block';
			pError.style.display='none';
			bool = false;
		}
		else if(!mPassword.value){
			aError.style.display='none';
			pError.innerHTML = '密码不能为空！';
			pError.style.display='block';
			bool = false;
		}
		else{
			pError.style.display='none';
			aError.style.display='none';
			jQuery.ajax({
			    type: 'post',
			    async: false,
			    url: '../login/userLogin.html',
			    data: { account: mAccount.value, password: mPassword.value },
		    	success: function(data){
		    		if(data == 'Pass'){
		    			var old = '';
						if(window.dialogArguments){
							old = window.dialogArguments.window.top.document.getElementById('loginAccount');
						}
						else{
							old = window.top.document.getElementById('loginAccount');
						}

						if(mAccount.value != old.value){
                            model.writeCookie("account",mAccount.value,1);
							if(window.dialogArguments){
								window.dialogArguments.window.top.location.href = '../login/getMainPage.html';
								window.close();
							}
							else if(window.top.tabs){
								window.top.location.href = '../login/getMainPage.html';
							}
						}
						else{
							if(window.dialogArguments){

							}
							else if(window.top.tabs){
								var iframe = $('iframe[name=\'' + $('li.' + window.top.tabs.active, window.top.tabs.head).attr('id') + '\']', window.top.document);

								if(iframe[0].contentWindow && $('input[type=\'hidden\'][id=\'refresh\']', iframe[0].contentWindow.document).length!=0){
									window.top.tabs.refresh($('li.' + window.top.tabs.active, window.top.tabs.head).attr('id'));
								}
                                else{
								    //超时登录成功刷新贷款查看照片弹出层add by wuxj
                                    var auiIframe=$('#auiIframe');
                                    if( auiIframe && auiIframe.is(':visible') && auiIframe.attr('src')){
                                        window.frames["auiIframe"].location.reload();
                                    }
                                }
							}
						}

						if(request)request();

						bool = true;
					}else{
						if(data == 'passwordreset'){
							if(window.dialogArguments){
								window.dialogArguments.window.top.location.href = '../login/showconfirmPwd.html';
								window.close();
							}else if(window.top.tabs){
								window.top.location.href = '../login/showconfirmPwd.html';
							}
						}else if(data == 'timeoutpasswordreset'){
							if(window.dialogArguments){
								window.dialogArguments.window.top.location.href = '../login/showTimeOutPwdSuccessPage.html';
								window.close();
							}else if(window.top.tabs){
								window.top.location.href = '../login/showTimeOutPwdSuccessPage.html';
							}
						}else{
							model.element('aError').innerHTML = data;
							model.element('pError').innerHTML = '';
							aError.style.display='block';
							bool = false;
						}
					}
		    	}
			});
		}
		
		return bool;
	}
	,back:function(){
		if(window.dialogArguments){
			window.dialogArguments.window.top.location.href = '../login/showLogin.html';
			window.close();
		}
		else{
			window.top.location.href = '../login/showLogin.html';
		}
	}
	,element:function(id){
		return window.top.document.getElementById(id);
	}
	,keydown:function(e){
		if(e.keyCode != 13) return false;
		$('button.aui_state_highlight').click();
	}
	,html:function(pointer){
		var html = '';
		
		switch(pointer){
			case 'expired':
				html = '<div class=\'ui-panel-model\'>'
					 + '<div class=\'info\'><label>系统闲置时间过长，请重新登录！</label></div>'
					 + '<div class=\'error\' id=\'aError\' style=\'display: none;\'></div>'
					 + '<div class=\'field-item\' style=\'height:34px;\'>'
					 + '<label class=\'field\' style=\'float:left; width:54px; overflow:hidden; text-align:right; line-height:30px;\'>用&nbsp;户&nbsp;名</label>'
					 + '<input id=\'mAccount\' name=\'account\' type=\'text\' class=\'text\' onkeydown=\'model.keydown(event)\' style=\'float:left;\' />'
					 + '</div>'
					 + '<div class=\'error\' id=\'pError\' style=\'display: none;\'></div>'
					 + '<div class=\'field-item\' style=\'height:34px;\'>'
					 + '<label class=\'field\' style=\'float:left; width:54px; overflow:hidden; text-align:right; line-height:30px;\'>密&nbsp;码</label>'
					 + '<input id=\'mPassword\' name=\'password\' type=\'password\' onpaste=\'javascript:return false;\' oncopy=\'javascript:return false;\' onkeydown=\'model.keydown(event)\' class=\'text\' style=\'float:left;\' />'
					 + '</div>'
					 + '</div>';
				break;
			case 'kicked':
				html = '<div class=\'ui-panel-model\'>'
					 + '<div class=\'info\'><label>您的帐户在其它地方登录，被强制下线! </label></div>'
					 + '</div>';
				break;
			case 'disabled':
				html = '<div class=\'ui-panel-model\'>'
					 + '<div class=\'info\'><label>您的帐号被停用，强制下线!</label></div>'
					 + '</div>';
				break;
			case 'deleted':
				html = '<div class=\'ui-panel-model\'>'
					 + '<div class=\'info\'><label>您的帐号被删除，强制下线! </label></div>'
					 + '</div>';
				break;
			case 'rolechang':
				html = '<div class=\'ui-panel-model\'>'
					 + '<div class=\'info\'><label>您的帐号权限发生变化，请重新登录! </label></div>'
					 + '</div>';
				break;
		}
		
		return html;
	}
	,open:function(o){
		var art = window.dialogArguments ? window.art : window.top.art;
		
		var args = { id: 'system-abnormal', title: '系统提醒', padding: 15, lock: true, esc: false, opacity: 0.2 }
		
		if(o.content){
			args.content = o.content;
		}
		if(o.ok){
			args.ok = o.ok;
		}
		if(o.okVal){
			args.okVal = o.okVal;
		}
		if(o.cancel){
			args.cancel = o.cancel;
		}
		if(o.cancelVal){
			args.cancelVal = o.cancelVal;
		}
		if(o.close){
			args.close = o.close;
		}
		
		art.dialog(args);
	}
	,expired:function(request){
		this.open({
			content: this.html('expired'),
			ok: function(){return model.enter(request);},
			okVal: '登录',
			cancel: this.back,
			cancelVal: '取消',
			close: null
		});
	}
	,kicked:function(){
		this.open({
			content: this.html('kicked'),
			ok: function(){  },
			okVal: '确定',
			cancel: null,
			close: this.back
		});
	}
	,disabled:function(){
		this.open({
			content: this.html('disabled'),
			ok: function(){  },
			okVal: '确定',
			cancel: null,
			close: this.back
		});
	}
	,deleted:function(){
		this.open({
			content: this.html('deleted'),
			ok: function(){  },
			okVal: '确定',
			cancel: null,
			close: this.back
		});
	}
	,rolechang:function(){
		this.open({
			content: this.html('rolechang'),
			ok: function(){  },
			okVal: '确定',
			cancel: null,
			close: this.back
		});
	}
};