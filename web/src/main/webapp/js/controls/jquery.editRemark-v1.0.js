document.write('<scr'+'ipt type="text/javascript" src="../js/banger/banger.core.js"></scr'+'ipt>');document.write('<scr'+'ipt type="text/javascript" src="../js/banger/banger.input.js"></scr'+'ipt>');document.write('<scr'+'ipt type="text/javascript" src="../js/banger/banger.common.js"></scr'+'ipt>');/*内部页卡中的修改*/function editRemark(obj){	$(obj).dblclick(function(){		var lbl = $('label', this), v = lbl.text(), tr = $(this).parent();		$(this).attr('id','edit'+Math.round(Math.random() * 10000));		var oid=$(this).attr('id');		var twidth=$(this).css('width').split('px')[0];		var theight=$(this).css('height').split('px')[0];		lbl.replaceWith('<div class=\'v-tips\'><img src=\'../images/public/vtips.gif\' /><textarea  class=\'ui-edit-text\' maxLength=\'200\' id=\'mytextarea\' value=\'' + v + '\' >'+v+'</textarea></div>');		$('div.v-tips').css({			'z-index':1,			position:'absolute',			top: $('#'+oid).offset().top - 87 +  $('#'+oid).outerHeight(true) - 1 + 'px',			left:$('#'+oid).offset().left + 'px',			width:twidth*2		});		$('.v-tips img').css({			position: 'absolute',			top:'-10px', 			left: twidth*0.5		});		$('textarea', this).focus(function(){			$(this).css({				position:'relative',				top: 0,				left: 0,				width: '100%'			});			this.select();			$(window).resize(function() {//监听浏览器 窗口大小是否改变				if($('#mytextarea').css('display')!='none'){					$('#mytextarea').css('display','none');				}			});			maxTextArea('#mytextarea');		}).blur(function(){			if($(this).val().length>200){				return;			}			$('div.v-tips').replaceWith('<label title='+ $(this).val() +'>' + $(this).val() + '</label>');			updateRemark(tr,$(this).val());		}).focus();	});	}/*非内部页卡中的修改*/function editRemarkN(obj){	$(obj).dblclick(function(){		var lbl = $('label', this), v = lbl.text(), tr = $(this).parent();		$(this).attr('id','edit'+Math.round(Math.random() * 10000));		var oid=$(this).attr('id');		var twidth=$(this).css('width').split('px')[0];		var theight=$(this).css('height').split('px')[0];		lbl.replaceWith('<div class=\'v-tips\'><img src=\'../images/public/vtips.gif\' /><textarea  class=\'ui-edit-text\' maxLength=\'200\' id=\'mytextarea\' value=\'' + v + '\' >'+v+'</textarea></div>');		$('div.v-tips').css({			'z-index':1,			position:'absolute',			top: $('#'+oid).offset().top +$('#'+oid).outerHeight(true) - 1 + 'px',			left:$('#'+oid).offset().left  + 'px',			width:twidth*2		});		$('.v-tips img').css({			position: 'absolute',			top:'-10px', 			left: twidth*0.5		});		$('textarea', this).focus(function(){			$(this).css({				position:'relative',				top: 0,				left: 0,				width: '100%'			});			this.select();			$(window).resize(function() {//监听浏览器 窗口大小是否改变				if($('#mytextarea').css('display')!='none'){					$('#mytextarea').css('display','none');				}			});			maxTextArea('#mytextarea');		}).blur(function(){			if($(this).val().length>200){				return;			}			$('div.v-tips').replaceWith('<label title='+ $(this).val() +'>' + $(this).val() + '</label>');			updateRemark(tr,$(this).val());		}).focus();	});	}