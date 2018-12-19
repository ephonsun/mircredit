// JavaScript Document

var fn = {};
//Set the height of the iframe度
fn.monitor = function(){
	var iframe = $('iframe:visible')[0];
	
	try{
		iframe.style.height = iframe.contentWindow.document.body.scrollHeight + 100 + 'px';
	}
	catch(e){
		//alert('error');
	}
	
    setTimeout('fn.monitor()', 1000);
};
//State
fn.state = function(state){
	var flag = getAnswerSwitchEnable();
	if(!flag)return;
	switch(state){
		case 'online':
			closePhoneAnswer();
			jQuery.ajax({
				type: 'POST',
				data: { 'type': state },
				url: 'changeUserStatus.html',
				success: function(data){
					$('#state label').css({
						backgroundImage: 'url(../images/index/online.gif)'
					}).text('在线');
				}
			});
			
			break;
		case 'leave':
			openPhoneAnswer();
			jQuery.ajax({
				type: 'POST',
				data: {'type': state},
				url: 'changeUserStatus.html',
				success: function(data){
					$('#state label').css({
						backgroundImage: 'url(../images/index/leave.gif)'
					}).text('离开');
				}
			});
			
			break;
	}
};
//To open or close a tool
fn.tools = function(pointer, txt){
	switch(pointer){
		case 'call':
			var flag = (txt=='打开通话窗口') ? true : false;
			var isPopUp = flag ? 1 : 0;
			jQuery.post('../phoneConfig/updateIsPopUp.html?&random=' + Math.random()*1000000, { 'phoneConfig.isPopUp': isPopUp },
				function(){
					fn.switcher(pointer, flag ? '关闭通话窗口' : '打开通话窗口');
					setConfigParams('talkForm', 'autoOpen', flag);
			});
			
			break;
		case 'words':
			var flag = (txt=='打开屏幕取词') ? true : false;
			var openWord = flag ? 1 : 0;
			jQuery.post('../phoneConfig/updateIsScreamWord.html?&random=' + Math.random()*1000000,{ 'phoneConfig.isScreamWord': openWord },
				function(){
					fn.switcher(pointer,flag?"关闭屏幕取词":"打开屏幕取词");
					setGetWord(openWord);
			});
			
			break;
	}
};
//Change the toolbox text
fn.switcher = function(pointer, txt){
	switch(pointer){
		case 'call':
			$('ul.ul-tools li.c label').text(txt).attr('title', txt);
			break;
		case 'words':
			$('ul.ul-tools li.w label').text(txt).attr('title', txt);
			break;
	}
};
//Enter the numbers match
fn.tel = function(html){
	if(typeof(html) != null){
		if($('body div.phoneitems').length == 0){
			$('body').append('<div class=\'phoneitems\'></div>');
		}
		
		$('body div.phoneitems').html(html).bind('click', function(e){
			e.stopPropagation();
		});
		//去除最后一行下边线
		$('body div.phoneitems div.body td').css('border-bottom', '0');
		
		if($.browser.msie && $.browser.version<7.0){
			$('body div.phoneitems tr').hover(
				function(){ $(this).addClass('hover'); },
				function(){ $(this).removeClass('hover'); }
			);
		}
	}
};
//Hide the match results
fn.hidetel = function(){
	$('body div.phoneitems').unbind('click').remove();
};
//Install
fn.install = function(){
	/*
	jQuery.post("../upgrade/getDownSetupPage.html?random="+Math.random()*1000000,{},
		function(html){
			if(html!="")
			{
				if(window.art){
					art.dialog({
						title:'托盘安装',
						content:html
					});
				}
			}
		}
	);
	*/
};

var menu = {};
//装载菜单 
menu.init = function(d){
	$.each(d, function(i){
		if(d){
			var dt = $('<dt><i>icon</i><label></label></dt>'), dd = $('<dd><ul></ul></dd>');
			
			//主菜单标识
			if(d[i].mid){
				dt[0].id = 'model' + d[i].mid;
			}
			else{
				alert('error: model id');
			}
			//主菜单名称
			if(d[i].model){
				$('label', dt).text(d[i].model);
			}
			else{
				$('label', dt).text('Undefined');
			}
			
			//遍历创建子菜单
			if(d[i].items){
				var it = d[i].items;
				
				$.each(it, function(j){
					var li = $('<li><label></label></li>'), args = it[j];
					
					//菜单归类
					if(args.sort && args.sort!=''){
						li.attr('sort', args.sort);
					}
					//菜单名称（不存在或为空时定义为'未定义'）
					if(args.modelname && args.modelname!=''){
						$('label', li).text(args.modelname);
					}
					else{
						$('label', li).text('Undefined');
					}
					//菜单标识
					if(args.id){
						li[0].id = 'li' + args.id;
					}
					
					li.click(function(){
						//单击菜单时更改样式
						$('li.active', $('#menu')).removeAttr('class');
						$(this).addClass('active');
						//页面跳回到顶部
						window.scroll(0, 0);
						//新增页卡（如果URL地址不存在或错误， 则添加或者更新URL地址，指向错误提示页面）
						if(args.url && args.url != ''){
							tabs.add(args);
						}
						else{
							//args.url = 'error.html';
							//tabs.add(args);
						}
					});
					
					$('ul', dd).append(li);
				});
			}
			if(i == 0){
				dt.addClass('active');
				dd.css('display', 'block');
			}
			
			$('#menu').append(dt).append(dd);
		}
		else{
			alert('error');
		}
	});
};
//动态创建一个菜单项
menu.add = function(args){
	//args = { previd: '', modelname: '', id: '', pid: '', title: '', url: '', lock: true, confirm: false }
	if(typeof(args) != 'undefined'){
		var li = $('<li><label></label></li>');
		
		//菜单名称（不存在或为空时定义为'未定义'）
		if(args.modelname && args.modelname!=''){
			$('label', li).text(args.modelname);
		}
		else{
			$('label', li).text('Undefined');
		}
		//菜单标识
		if(args.id){
			li[0].id = 'li' + args.id;
		}
		//
		if(args.sort){
			li.attr('sort', args.sort);
		}
		
		if(args.previd && $('#li' + args.previd).size()!=0){
			li.click(function(){
				//单击菜单时更改样式
				$('li.active', $('#menu')).removeAttr('class');
				$(this).addClass('active');
				//页面跳回到顶部
				window.scroll(0, 0);
				//新增页卡（如果URL地址不存在或错误， 则添加或者更新URL地址，指向错误提示页面）
				if(args.url && args.url != ''){
					d[0].items.push(args);//把新增的自定义搜索追加到菜单list
					tabs.add(args);
				}
				else{
					//args.url = 'error.html';
					//tabs.add(args);
				}
			});
			
			$('#li' + args.previd).after(li);
		}
		else{
			alert('Not found #li' + args.mid);
		}
	}
	else{
		alert('error');
	}
};
//移除一个菜单项
menu.del = function(id){
	if($('#li' + id).length != 0){
		$('#li' + id).remove();
	}
	else{
		//alert('Not found #li' + id);
	}
};
//重命名一个菜单项名称
menu.setname = function(id, name){
	if($('#li' + id).length != 0){
		$('label', $('#li' + id)).text(name);
		for(var i = 0; i < d.length; i++){
			var subMenus = d[i].items;
			for(var j = 0; j < subMenus.length; j++){
				var subMenu = subMenus[j];
				if(subMenu && subMenu.id == id){
					subMenu.title = name;
				}
			}
		}
	}
	else{
		//alert('Not found #li' + id);
	}
};
//移动一个菜单项
menu.move = function(id, direction){
	if($('#li' + id).length != 0){
		if(direction == 'up' && $('#li' + id).prev().length > 0){
			$('#li' + id).prev().before($('#li' + id));
		}
		else if(direction == 'down' && $('#li' + id).next().length > 0){
			$('#li' + id).before($('#li' + id).next());
		}
	}
	else{
		//alert('Not found #li' + id);
	}
};
//添加、上移下移、删除一个自定义搜索菜单项
menu.reset = function(prev, data){
	if($('#li' + prev).length != 0){
		if(typeof(data) != null){
			$('#menu li[sort=\'custom\']').unbind('click').remove();
			
			$.each(data, function(i){
				var li = $('<li><label></label></li>'), d = data[data.length - i - 1];
				
				//菜单归类
				if(d.sort && d.sort!=''){
					li.attr('sort', d.sort);
				}
				//菜单名称（不存在或为空时定义为'未定义'）
				if(d.modelname && d.modelname!=''){
					$('label', li).text(d.modelname);
				}
				else{
					$('label', li).text('Undefined');
				}
				//菜单标识
				if(d.id){
					li.attr('id', 'li' + d.id);
				}
				
				li.click(function(){
					//单击菜单时更改样式
					$('#menu li.active').removeAttr('class');
					$(this).addClass('active');
					
					//页面跳回到顶部
					window.scroll(0, 0);
					
					//新增页卡（如果URL地址不存在或错误， 则添加或者更新URL地址，指向错误提示页面）
					if(d.url && d.url != ''){
						tabs.add(d);
					}
					else{
						//d.url = 'error.html';
						//tabs.add(d);
					}
				});
				
				$('#li' + prev).after(li);
			});
		}
	}
	else{
		//alert('Not found #li' + id);
	}
};
//返回已存在的自定义搜索菜单集合（这里只返回id和modelname）
menu.exsit = function(){
	var a = [];
	
	$('#menu li[sort=\'custom\']').each(function(i){
		var json = {};
		json.id = $(this).attr('id').substring(2, $(this).attr('id').length);
		json.modelname = $('label', this).text();
		
		a[i] = json;
	});
	
	return a;
};
//关闭某菜单对应的页卡
menu.closeTabs = function(id){
	if(id && $('#' + id).length>0){
		$('#' + id).find('.close').click();
	}
};

$(function(){
	//装载菜单
	menu.init(d);
	
	//声明页卡对象，并创建工作台页卡
	window.tabs = ntabs.init();
	tabs.add({ id: 'desk', pid: '', title: '工作台', url: '../login/getWorkBench.html', lock: true });
	
	//实时监控页卡内可见的框架元素高度并更新
	fn.monitor();
	
	var ss = $('.skin-side'), sm = $('.skin-main'), pn = $('.page-nav'), ur = $('ul.ul-role', pn), us = $('ul.ul-state', pn), ut = $('ul.ul-tools', pn), ub = $('ul.ul-balance', pn), ps = $('.page-side'), pm = $('.page-main'), sw = $('#switch'), m = $('#menu'), dt = $('dt', m), dd = $('dd', m), li = $('li', m);
	
	
	//role
	$('#role').click(function(){
		
	}).hover(
		function(){
			if($('li', ur).length == 0) return false;
			
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
		var flag = getAnswerSwitchEnable();
		if(!flag)return;
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
		fn.state($(this).attr('rel'));
	}).hover(
		function(){ $(this).addClass('hover'); },
		function(){ $(this).removeClass('hover'); }
	);
	
	//balance
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
		fn.tools($(this).attr('rel'), $('label', this).text());
	}).hover(
		function(){ $(this).addClass('hover'); },
		function(){ $(this).removeClass('hover'); }
	);
	
	//拨号
	$('#dial .text').click(function(e){
		e.stopPropagation();
	});
	$(document).click(function(){
		fn.hidetel();
	});
	
	//菜单操作
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
	
	//菜单缩进按钮
	sw.toggle(
		function(){
			$(this).addClass("swi-active");
			$(this).parent().find('#swP').css('width','0px');//add by wuxj 20130131
			ss.css("left", -ps.outerWidth()-1);
			sm.css("left", 0);
			ps.css("margin-left", -ps.outerWidth());
			pm.css("margin-left", 0);
		},
		function(){
			$(this).removeClass("swi-active");
			$(this).parent().find('#swP').css('width','10px');//add by wuxj 20130131
			ss.css("left", 0);
			sm.css("left", ps.outerWidth()+11);
			ps.css("margin-left", 0);
			pm.css("margin-left", ps.outerWidth()+11);
		}
	);
	
	$(document).click(function(){
		us.hide();
		ut.hide();
	});
	
	if($.browser.msie && $.browser.version<7.0){
		$(window).resize(function(){
			var w = document.documentElement.clientWidth || document.body.clientWidth;
			
			if(w < 1002){
				$('body').css({ 'width': '1002px' });
			}
			else{
				$('body').removeAttr('style');
			}
		});
	}
});

