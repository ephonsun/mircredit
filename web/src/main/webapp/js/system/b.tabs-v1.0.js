// JavaScript Document

/**
 * jQuery.tabs-v1.0.js
 * Date: 2012-09-12 13:30 (c) 2012 KIWI
 * The imitation Browse page card effects.
**/

window.vtabs = {};

vtabs.add = function(o){
	if(this.isexist(o.id)){
		this.click({ id: o.id, title: o.title, url: o.url });
		return false;
	}
	
	this.builder(o);
};
vtabs.isexist = function(id){
	return $('#' + id).length > 0;
};
vtabs.click = function(o){
	$('#' + o.id).addClass(this.active).siblings().removeAttr('class');
	
	if(o.title){
		$('#' + o.id).children('label').html(o.title);
	}
	
	//某页卡激活时判断其在页卡头容器的相对位置并做位移
	if(($('#' + o.id).position().left + this.head.position().left) < 0){
		this.move(3, 10, 20, o.id);
	}
	else if((this.head.parent().width() - ($('#' + o.id).position().left + this.head.position().left)) < $('#' + o.id).width()){
		this.move(4, 10, 20, o.id);
	}
	
	this.show(o);
};
vtabs.show = function(o){
	if($('#body' + o.id).length > 0){
		var b = $('#body' + o.id), i = $('iframe', b)[0];
		
		b.show().siblings().hide();
		
		if(o.refreshPcard){
			try{
				i.src = this.seturl(i.src, 'random', Math.random());
			}
			catch(e){  }
		}
		else if(o.url){
			try{
				i.src = this.seturl(o.url, 'random', Math.random());
			}
			catch(e){  }
		}
	}
	else{
		alert('The page card already exists, but could not find the body content!');
	}
};
vtabs.seturl = function(url, name, value){
	var reg = new RegExp('(\\\?|&)' + name + '=([^&]+)(&|$)', 'i'), m = url.match(reg);
	if(typeof value != 'undefined'){
		if(m){
			return (url.replace(reg, function($0, $1, $2){
				return ($0.replace($2, value));
			}));
		}
		else{
			if(url.indexOf('?') == -1){
				return (url + '?' + name + '=' + value);
			}
			else{
				return (url + '&' + name + '=' + value);
			}
		}
	}
	else{
		if(m){
			return m[2];
		}
		else{
			return '';
		}
	}
};
vtabs.setheight = function(iframe){
	try{
		iframe.style.height = iframe.contentWindow.document.body.scrollHeight + 100 + 'px';
	}
	catch(e){
		//alert('error');
	}
};
vtabs.close = function(o){
	//判断关闭页卡时是否需要进行一个确认对话
	if(typeof(o.cfirm) != 'undefined' && o.cfirm == true){
		var bool = $('iframe', $('#body' + o.id))[0].contentWindow.cfirm();
		if(!bool){
			this.click({ id: o.id });
			return false;
		}else{
			//alert('close');
		}
	}
	
	var t = $('#' + o.id, this.head), b = $('#body' + o.id, this.body), f = $('li[fid=\'' + o.id + '\']', this.fav);
	
	/**
	 * 若当前页卡处于激活状态，则首先判断其是否存在父页卡，存在则激活父页卡
	 * 若父页卡不存在，继续查找当前页卡的前一项，存在则激活前一项
	 * 若父页卡及前一项都不存在，继续查找当前页卡的后一项，存在则激活后一项
	**/
	if(t.hasClass(this.active)){
		if(t.attr('pid') != '' && $('#' + t.attr('pid')).length != 0){
			if(o.refreshPcard){
				this.click({ id: t.attr('pid'), refreshPcard: o.refreshPcard });
			}
			else{
				this.click({ id: t.attr('pid') });
			}
		}else if(t.prev().length != 0){
			this.click({ id: t.prev().attr('id') });
		}else if(t.next().length != 0){
			this.click({ id: t.next().attr('id') });
		}
	}
	//移除页卡（包括页卡头、页卡躯体及统计列表对应项）
	t.remove(); b.remove(); f.remove();
	
	//重置页卡头尺寸
	this.resize();
	
	//对页卡部分做判断位移
	this.move(2, 10, 10);
};
vtabs.closeAll = function(){
	var s = this;
	
	//遍历页卡头内未锁定的页卡，触发其中关闭按钮的单击事件
	$('li[lock=\'false\']', s.head).each(function(){
		$('span', this).click();
	});
	
	////若所有未锁定页卡关闭后，页卡头内部还存在锁定项且都不处于激活状态，则激活剩余的第一项
	//if(s.head.children().length!=0 && s.head.children('li.' + s.active).length==0){
	//	s.click({ id: $('li', s.head)[0].id });
	//}
};
vtabs.builder = function(o){
	var s = this;
		
	//创建页卡头
	var t = $(s.html(o, 1));
	t.click(function(){
		s.click({ id: o.id });
	});
	$('span', t).click(function(){
		if(o.cfirm){
			s.close({ id: o.id, cfirm: o.cfirm });
		}
		else{
			s.close({ id: o.id });
		}
	});
	if(o.pid != '' && $('#' + o.pid, s.head).length > 0){
		//紧跟父页卡之后插入子页卡头
		$('#' + o.pid, s.head).after(t);
	}
	else{
		s.head.append(t);
	}
	
	//重置页卡头尺寸
	s.resize();
	
	//创建页卡躯体
	var b = $(s.html(o, 2));
	$('iframe', b).load(function(){
		s.setheight(this);
	});
	if(o.pid != '' && $('#body' + o.pid, s.body).length > 0){
		//紧跟父页卡之后插入子页卡躯体
		$('#body' + o.pid, s.body).after(b);
	}
	else{
		s.body.append(b);
	}
	
	//创建统计列表
	var f = $(s.html(o, 3));
	f.click(function(){
		s.click({ id: f.attr('fid') });
		s.fav.hide();
	}).hover(
		function(){ f.addClass('hover'); },
		function(){ f.removeAttr('class'); }
	);
	if(o.pid != '' && $('li[fid=\'' + o.pid + '\']', s.fav).length > 0){
		//紧跟父页卡之后插入子页卡统计项
		$('li[fid=\'' + o.pid + '\']', s.fav).after(f);
	}
	else{
		s.fav.append(f);
	}
	
	//页卡创建完成后激活
	$('#' + o.id, s.head).click();
};
vtabs.html = function(o, pointer){
	var html = '';
	switch(pointer){
		case 1:
			html = '<li id=\'' + o.id + '\' pid=\'' + o.pid + '\' lock=\'' + o.lock + '\'>'
				 + '<i class=\'l\'></i>'
			 	 + '<label title=\'' + o.title + '\'>' + o.title + '</label>'
			 	 + (o.lock ? '' : '<span></span>')
			 	 + '<i class=\'r\'></i>'
			 	 + '</li>';
			break;
		case 2:
			html = '<div id=\'body' + o.id + '\' class=\'x-panel-body-child\'>'
			     + '<iframe name=\'' + o.id + '\' frameborder=\'0\' scrolling=\'no\' src=\'' + o.url + '\'></iframe>'
			     + '</div>';
			break;
		case 3:
			html = '<li fid=\'' + o.id + '\'><label>' + o.title + '</label></li>';
			break;
	}
	return html;
};
vtabs.refresh = function(id){
	if($('#body' + id).length > 0){
		var i = $('iframe', $('#body' + id))[0], u = i.src, r = Math.random();
		i.src = this.seturl(u, 'random', r);
	}
};
vtabs.resize = function(){
	var li = $('li', this.head), w = 0;
	if(li.length > 0){
		li.each(function(){
			w += $(this).outerWidth(true);
		});
		this.head.width(w);
	}
};
vtabs.title = function(obj, title){
	$('label', obj).html(title);
};
vtabs.move = function(n, interval, speed, id){
	var s = this;
	
	switch(n){
		case 0:
			clearInterval(s.timer);
			s.timer = setInterval(
			function(){
				var sp = s.head.position().left;
				if(s.head.position().left >= 0){
					clearInterval(s.timer);
				}
				else{
					if(Math.abs(sp) < speed){
						s.head.css({ left: s.head.position().left + Math.abs(sp) });
					}
					else{
						s.head.css({ left: s.head.position().left + speed });
					}
				}
			},
			interval);
			break;
		case 1:
			clearInterval(s.timer);
			s.timer = setInterval(
			function(){
				var sp = (s.head.width() + s.head.position().left) - s.head.parent().width();
				if((s.head.width() + s.head.position().left) <= s.head.parent().width()){
					clearInterval(s.timer);
				}
				else{
					if(sp < speed){
						s.head.css({ left: s.head.position().left - Math.abs(sp) });
					}
					else{
						s.head.css({ left: s.head.position().left - speed });
					}
				}
			},
			interval);
			break;
		case 2:
			clearInterval(s.timer);
			s.timer = setInterval(
			function(){
				var sp = s.head.position().left;
				if((s.head.position().left >= 0) || ((s.head.width() + s.head.position().left) >= s.head.parent().width())){
					clearInterval(s.timer);
				}
				else{
					if(Math.abs(sp) < speed){
						s.head.css({ left: s.head.position().left + Math.abs(sp) });
					}
					else{
						s.head.css({ left: s.head.position().left + speed });
					}
				}
			},
			interval);
			break;
		case 3:
			clearInterval(s.timer);
			s.timer = setInterval(
			function(){
				var sp = $('#' + id).position().left + s.head.position().left;
				if(sp >= 0){
					clearInterval(s.timer);
				}
				else{
					if(Math.abs(sp) < speed){
						s.head.css({ left: s.head.position().left + Math.abs(sp) });
					}
					else{
						s.head.css({ left: s.head.position().left + speed });
					}
				}
			},
			interval);
			break;
		case 4:
			clearInterval(s.timer);
			s.timer = setInterval(
			function(){
				var sp = $("#" + id).width() - (s.head.parent().width() - ($("#" + id).position().left + s.head.position().left));
				if((s.head.parent().width() - ($("#" + id).position().left + s.head.position().left)) >= $("#" + id).width()){
					clearInterval(s.timer);
				}
				else{
					if(sp < speed){
						s.head.css({ left: s.head.position().left - Math.abs(sp) });
					}
					else{
						s.head.css({ left: s.head.position().left - speed });
					}
				}
			},
			interval);
			break;
	}
};
vtabs.handler = function(){
	var s = this;
		
	s.btnLeft.mousedown(function(){
		s.move(0, 10, 20);
	});
	s.btnLeft.mouseup(function(){
		clearInterval(s.timer);
	});
	s.btnRight.mousedown(function(){
		s.move(1, 10, 20);
	});
	s.btnRight.mouseup(function(){
		clearInterval(s.timer);
	});
	s.btnFav.click(function(){
		if(s.fav.is(':hidden')){
			s.fav.show();
		}else{ s.fav.hide(); }
	});
	s.btnClose.click(function(){
		s.closeAll();
	});
	
	return s;
};


window.ntabs = function(o){
	o = $.extend({
		headId: 'head',
		bodyId: 'body',
		favId: 'fav',
		btnLeftId: 'bar-left',
		btnRightId: 'bar-right',
		btnFavId: 'bar-fav',
		btnCloseId: 'bar-close',
		active: 'x-tab-active'
	}, o);
	
	this.head = $('#' + o.headId);
	this.body = $('#' + o.bodyId);
	this.fav = $('#' + o.favId);
	this.btnFav = $('#' + o.btnFavId);
	this.btnLeft = $('#' + o.btnLeftId);
	this.btnRight = $('#' + o.btnRightId);
	this.btnClose = $('#' + o.btnCloseId);
	this.timer = null,
	this.active = o.active;
};

$.extend(ntabs.prototype, vtabs);

window.ntabs.init = function(o){
	var tab = new ntabs(o);
	return tab.handler();
};





