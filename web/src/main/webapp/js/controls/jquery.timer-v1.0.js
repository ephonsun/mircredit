// JavaScript Document

/**
 * 时间选择控件
**/
(function($){
	var timerfun = {
		html: function(o){
			var tr = $('<tr></tr>');
			
			if(o.hour && o.hour.show){
				tr.append('<td><input type=\'text\' tabindex=\'1\' maxlength=\'2\' class=\'txt hour active\' /></td>');
				
				if(o.hour.id){
					$('input.hour', tr).attr('id', o.hour.id);
				}
				
				if(o.hour.name){
					$('input.hour', tr).attr('name', o.hour.name);
				}
				else{
					$('input.hour', tr).attr('name', 'hour');
				}
				
				if(o.hour.value){
					$('input.hour', tr).val(o.hour.value);
				}
				else{
					$('input.hour', tr).val('00');
				}
			}
			
			if(o.mins && o.mins.show){
				tr.append('<td><input type=\'text\' tabindex=\'1\' maxlength=\'2\' class=\'txt mins\' /></td>');
				
				if(o.mins.id){
					$('input.mins', tr).attr('id', o.mins.id);
				}
				
				if(o.mins.name){
					$('input.mins', tr).attr('name', o.mins.name);
				}
				else{
					$('input.mins', tr).attr('name', 'mins');
				}
				
				if(o.mins.value){
					$('input.mins', tr).val(o.mins.value);
				}
				else{
					$('input.mins', tr).val('00');
				}
			}
			
			if(o.secs && o.secs.show){
				tr.append('<td><input type=\'text\' tabindex=\'1\' maxlength=\'2\' class=\'txt secs\' /></td>');
				
				if(o.secs.id){
					$('input.secs', tr).attr('id', o.secs.id);
				}
				
				if(o.secs.name){
					$('input.secs', tr).attr('name', o.secs.name);
				}
				else{
					$('input.secs', tr).attr('name', 'secs');
				}
				
				if(o.secs.value){
					$('input.secs', tr).val(o.secs.value);
				}
				else{
					$('input.secs', tr).val('00');
				}
			}
			
			tr.append('<td class=\'button\'><img src=\'' + o.plussrc + '\' class=\'plus\' /><img src=\'' + o.minussrc + '\' class=\'minus\' /></td>');
			
			return tr;
		},
		builder: function(tbl, o){
			$(tbl).append(this.html(o));
			
			this.handler(tbl, o);
		},
		handler: function(tbl, o){
			/**
			 * 时分秒得到光标时间
			**/
			$(':text', tbl).focus(function(){
				$(':text', tbl).removeClass(o.active);
				$(this).addClass(o.active);
			});
			
			/**
			 * 时钟
			**/
			$('input.hour', tbl).keydown(function(e){
				if((e.keyCode < 48 || e.keyCode > 57) && e.keyCode != 38 && e.keyCode != 40){
					return false;
				}
				if(e.keyCode == 38){
					var v = eval(this.value);
					if(v < 23){
						this.value = (v + 1) < 10 ? '0' + (v + 1) : (v + 1);
					}
					else{
						this.value = '00';
					}
				}
				if(e.keyCode == 40){
					var v = eval(this.value);
					if(v < 1){
						this.value = '23';
					}
					else{
						this.value = (v - 1) < 10 ? '0' + (v - 1) : (v - 1);
					}
				}
			}).keyup(function(){
				if(parseInt(this.value) > 23){ this.value = '23'; }
			});
			
			/**
			 * 分钟
			**/
			$('input.mins', tbl).keydown(function(e){
				if((e.keyCode < 48 || e.keyCode > 57) && e.keyCode != 38 && e.keyCode != 40){
					return false;
				}
				if(e.keyCode == 38){
					var v = eval(this.value);
					if(v < 59){
						this.value = (v + 1) < 10 ? '0' + (v + 1) : (v + 1);
					}
					else{
						this.value = '00';
					}
				}
				if(e.keyCode == 40){
					var v = eval(this.value);
					if(v < 1){
						this.value = '59';
					}
					else{
						this.value = (v - 1) < 10 ? '0' + (v - 1) : (v - 1);
					}
				}
			}).keyup(function(){
				if(parseInt(this.value) > 59){ this.value = '59'; }
			});
			
			/**
			 * 秒钟
			**/
			$('input.secs', tbl).keydown(function(e){
				if((e.keyCode < 48 || e.keyCode > 57) && e.keyCode != 38 && e.keyCode != 40){
					return false;
				}
				if(e.keyCode == 38){
					var v = eval(this.value);
					if(v < 59){
						this.value = (v + 1) < 10 ? '0' + (v + 1) : (v + 1);
					}
					else{
						this.value = '00';
					}
				}
				if(e.keyCode == 40){
					var v = eval(this.value);
					if(v < 1){
						this.value = '59';
					}
					else{
						this.value = (v - 1) < 10 ? '0' + (v - 1) : (v - 1);
					}
				}
			}).keyup(function(){
				if(parseInt(this.value) > 59){ this.value = '59'; }
			});
			
			/**
			 * 加
			**/
			$('img.plus', tbl).click(function(){
				var txt = $('input.active', tbl);
				
				if(txt.length == 0){
					return false;
				}
				else{
					var v = eval(txt.val());
					
					if(txt.hasClass('hour')){
						if(v < 23){
							txt.val((v + 1) < 10 ? '0' + (v + 1) : (v + 1));
						}
						else{
							txt.val('00');
						}
					}
					if(txt.hasClass('mins')){
						if(v < 59){
							txt.val((v + 1) < 10 ? '0' + (v + 1) : (v + 1));
						}
						else{
							txt.val('00');
						}
					}
					if(txt.hasClass('secs')){
						if(v < 59){
							txt.val((v + 1) < 10 ? '0' + (v + 1) : (v + 1));
						}
						else{
							txt.val('00');
						}
					}
				}
			});
			
			/**
			 * 减
			**/
			$('img.minus', tbl).click(function(){
				var txt = $('input.active', tbl);
				
				if(txt.length == 0){
					return false;
				}
				else{
					var v = eval(txt.val());
					
					if(txt.hasClass('hour')){
						if(v < 1){
							txt.val('23');
						}
						else{
							txt.val((v - 1) < 10 ? '0' + (v - 1) : (v - 1));
						}
					}
					if(txt.hasClass('mins')){
						if(v < 1){
							txt.val('59');
						}
						else{
							txt.val((v - 1) < 10 ? '0' + (v - 1) : (v - 1));
						}
					}
					if(txt.hasClass('secs')){
						if(v < 1){
							txt.val('59');
						}
						else{
							txt.val((v - 1) < 10 ? '0' + (v - 1) : (v - 1));
						}
					}
				}
			});
		}
	};
	
	$.fn.timer = function(o){
		o = $.extend({}, $.fn.timer.defaults, o);
		
		return this.each(function(){
			timerfun.builder(this, o);
		});
	};
	
	$.fn.timer.defaults = {
		hour: { show: true, id: 'hour', name: 'hour', value: '00' },
		mins: { show: true, id: 'mins', name: 'mins', value: '00' },
		secs: { show: true, id: 'secs', name: 'secs', value: '00' },
		plussrc: '../images/common/timer-plus.gif',
		minussrc: '../images/common/timer-minus.gif',
		active: 'active'
	};
})(jQuery);