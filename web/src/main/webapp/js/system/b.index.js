// JavaScript Document

var h1 = '<div class=\'header\'>'
	   + '<table>'
	   + '<thead>'
	   + '<tr>'
	   + '<th width=\'180\'><label title=\'field\'>field</label></th>'
	   + '<th width=\'80\'><label title=\'field\'>field</label></th>'
	   + '<th width=\'150\'><label title=\'field\'>field</label></th>'
	   + '<th width=\'60\'><label title=\'field\'>field</label></th>'
	   + '<th width=\'60\'><label title=\'field\'>field</label></th>'
	   + '</tr>'
	   + '</thead>'
	   + '</table>'
	   + '</div>'
	   + '<div class=\'body\'>'
	   + '<table>'
	   + '<tbody>'
	   + '<tr>'
	   + '<td width=\'180\' align=\'left\'><a href=\'#2\'>field</a></td>'
	   + '<td width=\'80\'><label>field</label></td>'
	   + '<td width=\'150\'><label>field</label></td>'
	   + '<td width=\'60\'><label>field</label></td>'
	   + '<td width=\'60\'><i class=\'phone\' onclick=\'tel()\'>&nbsp;</i></td>'
	   + '</tr>'
	   + '</tbody>'
	   + '</table>'
	   + '</div>';
	   
var h2 = '<div class=\'header\'>'
	   + '<table>'
	   + '<thead>'
	   + '<tr>'
	   + '<th width=\'180\'><label title=\'field\'>field</label></th>'
	   + '<th width=\'80\'><label title=\'field\'>field</label></th>'
	   + '<th width=\'150\'><label title=\'field\'>field</label></th>'
	   + '<th width=\'60\'><label title=\'field\'>field</label></th>'
	   + '<th width=\'80\'><label title=\'field\'>field</label></th>'
	   + '<th width=\'60\'><label title=\'field\'>field</label></th>'
	   + '</tr>'
	   + '</thead>'
	   + '</table>'
	   + '</div>'
	   + '<div class=\'body\'>'
	   + '<table>'
	   + '<tbody>'
	   + '<tr>'
	   + '<td width=\'180\' align=\'left\'><a href=\'#2\' title=\'field\'>field</a></td>'
	   + '<td width=\'80\'><label title=\'field\'>手机一</label></td>'
	   + '<td width=\'150\'><label title=\'field\'>13838380438</label></td>'
	   + '<td width=\'60\'><label title=\'field\'>男</label></td>'
	   + '<td width=\'80\'><label title=\'field\'>张先生</label></td>'
	   + '<td width=\'60\'><i class=\'phone\' onclick=\'tel()\'>&nbsp;</i></td>'
	   + '</tr>'
	   + '</tbody>'
	   + '</table>'
	   + '</div>';


var f = {};
//Set the height of the iframe
f.monitor = function(){
	var iframe = $('iframe:visible')[0];
	
	try{
		iframe.style.height = iframe.contentWindow.document.body.scrollHeight + 100 + 'px';
	}
	catch(e){
		//alert('error');
	}
	
    setTimeout('f.monitor()', 2000);
};
//Switch user status
f.state = function(state, callback){
	if(state == 'online'){
		
	}
	else if(state == 'leave'){
		
	}
	
	
	if(callback && typeof(callback)=='function'){
		var bool = callback();
		if(bool){
			if(state == 'online'){
				$('.status span label', $('.page-nav')).css({
					'background-image': 'url(../images/index/online.gif)'
				}).text('在线');
			}
			else if(state == 'leave'){
				$('.status span label', $('.page-nav')).css({
					'background-image': 'url(../images/index/leave.gif)'
				}).text('离开');
			}
		}
	}
};
//工具
f.tools = function(pointer, txt){
	switch(pointer){
		case 'call':
			f.switcher(pointer, '关闭通话窗口');
			
			break;
		case 'words':
			f.switcher(pointer, '关闭屏幕取词');
			
			break;
	}
};
//工具文本变更
f.switcher = function(pointer, txt){
	switch(pointer){
		case 'call':
			$('ul.ul-tools li.c label').text(txt).attr('title', txt);
			break;
		case 'words':
			$('ul.ul-tools li.w label').text(txt).attr('title', txt);
			break;
	}
};
//号码输入框获取光标或按键弹起触发
f.tel = function(html){
	if(typeof(html) != null){
		if($('body div.phone-items').length == 0){
			$('body').append('<div class=\'phone-items\'></div>');
		}
		
		$('body div.phone-items').html(html).bind('click', function(e){
			e.stopPropagation();
		});
		//去除最后一行下边线
		$('body div.phone-items div.body td').css('border-bottom', '0');
	}
};
f.hidetel = function(){
	$('body div.phone-items').unbind('click').remove();
};


var menu = {};
//装载菜单 
menu.init = function(d){
	$.each(d, function(i, model){
		var dt = $('<dt><i>icon</i><label></label></dt>'), dd = $('<dd><ul></ul></dd>');
		
		//第一项设置为展开状态
		if(i == 0){
			dt.addClass('active');
			dd.css('display', 'block');
		}
		
		if(model && model.id){
			dt.attr('id', 'model' + model.id);
		}
		else{
			alert('error: this model id not output!');
		}
		
		if(model && model.name){
			$('label', dt).text(model.name);
		}
		else{
			$('label', dt).text('undefined');
		}
		
		if(model && model.items){
			$.each(model.items, function(j, it){
				var li = $('<li><label></label></li>');
				
				if(it.sort){
					li.attr('sort', it.sort);
				}
				
				if(it.name){
					$('label', li).text(it.name);
				}
				else{
					$('label', li).text('undefined');
				}
				
				if(it.id){
					li.attr('id', 'li' + it.id);
				}
				
				li.click(function(){
					$('li.active', $('#menu')).removeAttr('class');
					$(this).addClass('active');
					
					window.scroll(0, 0);
					
					//新增页卡（如果URL地址不存在或错误， 则添加或者更新URL地址，指向错误提示页面）
					if(it.url && it.url != ''){
						tabs.add(it);
					}
					else{
						it.url = 'error.html';
						tabs.add(it);
					}
				});
				
				$('ul', dd).append(li);
			});
		}
		
		$('#menu').append(dt).append(dd);
	});
};
//重命名一个菜单名称
menu.name = function(id, name){
	if($('#menu #li' + id).length != 0){
		$('label', $('#menu #li' + id)).text(name);
	}
	else{
		alert('not found ' + id);
	}
};
//返回已存在的自定义搜索菜单集合（这里只返回ID和NAME）
menu.exsit = function(sort){
	var a = [];
	
	$('#menu li[sort=\'custom\']').each(function(i){
		var json = {};
		
		json.id = $(this).attr('id').substring(2, $(this).attr('id').length);
		json.name = $('label', this).text();
		
		a[i] = json;
	});
	
	return a;
};
//添加、上移下移、删除一个自定义搜索菜单
menu.reset = function(prev, d){
	if($('#menu #li' + prev).length != 0){
		if(d && d.length > 0){
			$('#menu li[sort=\'custom\']').unbind('click').remove();
			
			$.each(d, function(i){
				var li = $('<li><label></label></li>'), it = d[d.length - i - 1];
				
				if(it.sort && it.sort!=''){
					li.attr('sort', it.sort);
				}
				
				if(it.name){
					$('label', li).text(it.name);
				}
				else{
					$('label', li).text('undefined');
				}
				
				if(it.id){
					li.attr('id', 'li' + it.id);
				}
				
				li.click(function(){
					$('#menu li.active').removeAttr('class');
					$(this).addClass('active');
					
					window.scroll(0, 0);
					
					//新增页卡（如果URL地址不存在或错误， 则添加或者更新URL地址，指向错误提示页面）
					if(it.url && it.url != ''){
						tabs.add(it);
					}
					else{
						it.url = 'error.html';
						tabs.add(it);
					}
				});
				
				$('#menu #li' + prev).after(li);
			});
		}
		else{
			alert('error: data error!');
		}
	}
	else{
		alert('error: not found the prev node!');
	}
};


$(function(){
	//loading menu
	menu.init(d);
	
	//声明页卡对象，并创建工作台页卡
	window.tabs = ntabs.init();
	tabs.add({ id: 'main', pid: '', title: '工作台', url: 'main.html', lock: true });
	
	//实时监控页卡内可见的框架元素高度并更新
	f.monitor();
	
	var ss = $('.skin-side'), sm = $('.skin-main'), pn = $('.page-nav'), ur = $('ul.ul-role', pn), us = $('ul.ul-state', pn), ub = $('ul.ul-balance', pn), ut = $('ul.ul-tools', pn), ps = $('.page-side'), pm = $('.page-main'), sw = $('#switch'), m = $('#menu'), dt = $('dt', m), dd = $('dd', m), li = $('li', m);
	
	var ie = $.browser.msie && $.browser.version<7.0;
	
	//role
	$('#role').click(function(){
		
	}).hover(
		function(){
			if($('li', ur).length == 0){ return false; }
			
			$(this).addClass('hover');
			ur.show().css({
				'top': $(this).offset().top - 9,
				'left': $(this).offset().left
			});
		},
		function(){
			$(this).removeClass('hover');
			ur.hide();
		}
	);
	
	//state
	$('#state').click(function(e){
		if(us.is(':hidden')){
			us.show().css({
				top: $(this).offset().top - 9,
				left: $(this).offset().left
			});
		}
		else{
			us.hide();
		}
		e.stopPropagation();
	}).hover(
		function(){ $(this).addClass('hover'); },
		function(){ $(this).removeClass('hover'); }
	);
	//state hover & click
	$('li', us).click(function(){
		us.hide();
		f.state($(this).attr('rel'));
	}).hover(
		function(){ $(this).addClass('hover'); },
		function(){ $(this).removeClass('hover'); }
	);
	
	//余额
	$('#balance').click(function(){
		
	}).hover(
		function(){
			$(this).addClass('hover');
			ub.show().css({
				'top': $(this).offset().top - 9,
				'left': $(this).offset().left
			});
		},
		function(){
			$(this).removeClass('hover');
			ub.hide();
		}
	);
	
	//tools
	$('#tools').click(function(e){
		if(ut.is(':hidden')){
			ut.show();
		}
		else{
			ut.hide();
		}
		e.stopPropagation();
	}).hover(
		function(){ $(this).addClass('hover'); },
		function(){ $(this).removeClass('hover'); }
	);
	//tools hover & click
	$('li', ut).click(function(){
		ut.hide();
		f.tools($(this).attr('rel'), $('label', this).text());
	}).hover(
		function(){ $(this).addClass('hover'); },
		function(){ $(this).removeClass('hover'); }
	);
	
	//拨号
	$('input.tel').click(function(e){
		e.stopPropagation();
	});
	$(document).click(function(){
		f.hidetel();
	});
	
	//menu
	dt.slice(0).click(function(){
		var s = $(this), n = s.next();
		if(n.is(':visible')){
			s.removeAttr('class');
			n.css('display', 'none');
		}
		else{
			s.addClass('active').siblings('dt.active').removeAttr('class');
			n.css('display', 'block').siblings('dd:visible').css('display', 'none');
		}
	});
	
	//switch
	sw.toggle(
		function(){
			$(this).addClass('swi-active');
			ss.css('left', -ps.outerWidth() - 1);
			sm.css('left', 0);
			ps.css('margin-left', -ps.outerWidth());
			pm.css('margin-left', 0);
		},
		function(){
			$(this).removeClass('swi-active');
			ss.css('left', 0);
			sm.css('left', ps.outerWidth() + 11); 
			ps.css('margin-left', 0);
			pm.css('margin-left', ps.outerWidth() + 11);
		}
	);
});

