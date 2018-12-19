// JavaScript Document

window.vtabs = {
	updateurl: function(url, name, value){
		var reg = new RegExp("(\\\?|&)" + name + "=([^&]+)(&|$)", "i");
		var m = url.match(reg);
		if(typeof value != "undefined"){
			if(m){
				return (url.replace(reg, function($0, $1, $2){
					return ($0.replace($2, value));
				}));
			}else{
				if(url.indexOf("?") == -1){
					return (url + "?" + name + "=" + value);
				}else{
					return (url + "&" + name + "=" + value);
				}
			}
		}else{
			if(m){
				return m[2];
			}else{
				return "";
			}
		}
	},
	refresh: function(id){

		if($('#' + id).size() != 0){
			var i = $('iframe', $('#body' + id)), u = i[0].src, r = Math.random();

		}
	},
	add: function(o){
		//判断当前需要添加的页卡是否已经存在，存在则激活
		if(this.isexist(o.id)){
			if(typeof(o.refresh) != 'undefined' && !o.refresh){
				this.click(o.id, false);
			}
			else{
				this.click(o.id, false);
				//this.click(o.id, true, o.url);
			}
			return false;
		}
		//反之则创建一个新页卡
		this.create(o);
	},
	isexist: function(id){
		return $('#' + id).size() > 0;
	},
	click: function(id, point, url){
		var t = $('#' + id);
		t.addClass(this.active).siblings().removeAttr('class');
		
		//某页卡激活时判断其在页卡头容器的相对位置并做位移
		if((t.position().left + this.head.position().left) < 0){
			this.move(3, 10, 20, id);
		}
		else if((this.head.parent().width() - (t.position().left + this.head.position().left)) < t.width()){
			this.move(4, 10, 20, id);
		}
		
		this.show(id, point, url);
	},
	show: function(id, point, url){
		/**
		 * t: 通过页卡t的索引查找对应的body元素
		 * point: 指示页卡body显示后是否重刷新
		**/
		if($('#body' + id).size() > 0){
			var b = $('#body' + id), i = $('iframe', b);
			b.show().siblings().hide();
			
			if(typeof(url) != 'undefined'){
				var r = Math.random();
				i[0].src = this.updateurl(url, 'random', r);
			}
			else if(point){
				var u = i[0].src, r = Math.random();
				i[0].src = this.updateurl(u, 'random', r);
			}
			
			//重置内框架高度
			this.updateh(i[0]);
		}else{
			//alert('error');
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////////
	},

	close: function(id, point, cfirm){
		//判断关闭页卡时是否需要进行一个确认对话
		if(typeof(cfirm) != 'undefined' && cfirm == true){
			var win = $('iframe', $('#body' + id))[0];
			
			if(win){
				var cw = win.contentWindow;
				
				if(cw.cfirm){
					var bool = cw.cfirm();
					
					if(!bool){
						this.click(id, false);
						return false;
					}else{
						//alert('error');
					}
				}
			}
		}
		
		var t = $('#' + id), b = $('#body' + id), l = $('li[tid=\'' + t[0].id + '\']', this.list);

		/**
		 * 若当前页卡处于激活状态，则首先判断其是否存在父页卡，存在则激活父页卡
		 * 若父页卡不存在，继续查找当前页卡的前一项，存在则激活前一项
		 * 若父页卡及前一项都不存在，继续查找当前页卡的后一项，存在则激活后一项
		**/
		if(t.hasClass(this.active)){
			if(t.attr('pid') && $('#' + t.attr('pid')).size() != 0){
				this.click(t.attr('pid'), point);
			}else if(t.prev().size() != 0){
				this.click(t.prev()[0].id, false);
			}else if(t.next().length != 0){
				this.click(t.next()[0].id, false);
			}
		}
		//移除页卡（包括页卡头、页卡内容及列表部分对应项）
		t.remove();
		setTimeout(function(){b.remove();}, 200);
		l.remove();
		//重置页卡头容器宽度
		this.resize();
		//对页卡部分做判断位移
		this.move(2, 10, 10);
	},
	closeAll: function(){
		var s = this;
		//遍历页卡头内未锁定的页卡，激发其中关闭按钮的单击事件
		$('li[lock=\'false\']', s.head).each(function(){
			$('span', this).click();
		});
		//若所有未锁定页卡关闭后，页卡头内部还存在锁定项且都不处于激活状态，则激活剩余的第一个页卡
		if(s.head.children().size()!=0 && $('.' + this.active, s.head).size()==0){
			s.click($('li:first', s.head)[0].id, false);
		}
		
		s.head.css('left', 0);
		
		//重置页卡头容器宽度
		//this.resize();
		//对页卡部分做判断位移
		//this.move(2, 10, 10);
	},
	create: function(o){
		var s = this;
		
		//创建页卡头
		var t = $(s.html(o, 1));
		t.click(function(){
			s.click(o.id, false);
		});
		$('span', t).click(function(){
			s.close(o.id, false, o.confirm);
		});
		s.head.append(t);
		//重置页卡头容器宽度
		s.resize();
		
		//创建页卡身躯
		var b = $(s.html(o, 2));
		$('iframe', b).load(function(){
			s.updateh(this);
			
			//隐藏拨号记录列表
			$(this.contentWindow.document).click(function(){
				fn.hidetel();
			});
		});
		s.body.append(b);
		
		//创建页卡列表
		var l = $(s.html(o, 3));
		l.click(function(){
			s.click(l.attr('tid'), false);
			s.list.hide();
		}).hover(
			function(){ l.addClass('hover'); },
			function(){ l.removeAttr('class'); }
		);
		s.list.append(l);
		
		//页卡创建完成后激活
		$('#' + o.id).click();
	},
	html: function(o, pointer){
		var html = '';
		switch(pointer){
			case 1:
				html = '<li id=\'' + o.id + '\' pid=\'' + o.pid + '\' lock=\'' + o.lock + '\'>'
			     	 + '<i class=\'l\'></i>'
				 	 + '<label class=\'title\' title=\'' + o.title + '\'>' + o.title + '</label>'
				 	 + (o.lock ? '' : '<span class=\'close\'></span>')
				 	 + '<i class=\'r\'></i>'
				 	 + '</li>';
				break;
			case 2:
				html = '<div id=\'body' + o.id + '\' class=\'x-panel-body-child\'>'
				     + '<iframe name=\'' + o.id + '\' frameborder=\'0\' scrolling=\'no\' allowTransparency=\'true\' src=\'' + o.url + '\'></iframe>'
				     + '</div>';
				break;
			case 3:
				html = '<li tid=\'' + o.id + '\' title=\''+ o.title +'\'>' + o.title + '</li>';
				break;
		}
		return html;
	},
	resize: function(){
		var li = $('li', this.head), val = 0;
		if(li.size() > 0){
			li.each(function(){
				val += $(this).outerWidth(true);
			});
			this.head.width(val);
		}
	},
	title: function(obj, title){
		if(obj && obj.length>0){
			$('label', obj).html(title).attr('title', title);
			
			/* 同步修改统计列表中的文本 */
			$('li[tid="' + obj.attr('id') + '"]', this.list).html(title).attr('title', title);
		}
	},
	updateh: function(obj){
		try{
			obj.style.height = obj.contentWindow.document.body.scrollHeight;
		}
		catch(e){
			//alert('error');
		}
	},
	move: function(n, interval, speed, id){
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
					var sp = $("#" + id).position().left + s.head.position().left;
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
	},
	handler: function(){
		var s = this;
		
		s.btnle.mousedown(function(){
			s.move(0, 10, 20);
		});
		s.btnle.mouseup(function(){
			clearInterval(s.timer);
		});
		s.btnri.mousedown(function(){
			s.move(1, 10, 20);
		});
		s.btnri.mouseup(function(){
			clearInterval(s.timer);
		});
		s.btnli.click(function(){
			if(s.list.is(':hidden')){
				s.list.show();
			}else{ s.list.hide(); }
		});
		s.btncl.click(function(){
			s.closeAll();
		});
		
		return s;
	}
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
	this.list = $('#' + o.favId);
	this.btnle = $('#' + o.btnLeftId);
	this.btnri = $('#' + o.btnRightId);
	this.btnli = $('#' + o.btnFavId);
	this.btncl = $('#' + o.btnCloseId);
	this.timer = null,
	this.active = o.active;
}

$.extend(ntabs.prototype, vtabs);

window.ntabs.init = function(o){
	var tab = new ntabs(o);
	return tab.handler();
}

