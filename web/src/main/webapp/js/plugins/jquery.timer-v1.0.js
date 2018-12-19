// JavaScript Document

/**
 * æ¶é´éæ©æ§ä»¶
**/
(function($){
	var tfun = {
		html: function(tbl, o){
			$(tbl).html('<tr></tr>');
			
			if(o.hour.show){
				$('tr', tbl).append('<td><input type=\'text\' tabindex=\'1\' maxlength=\'2\' class=\'txt hour active\' /></td>');
				
				//å¦æä¸è¾åºåç§ï¼åå»é¤å¼å·èæ¯
				if(!o.mins.show && !o.secs.show){
					$('input.hour', tbl).addClass('none');
				}
				
				if(o.hour.id){
					$('input.hour', tbl).attr('id', o.hour.id);
				}
				
				if(o.hour.name){
					$('input.hour', tbl).attr('name', o.hour.name);
				}
				else{
					$('input.hour', tbl).attr('name', 'hour');
				}
				
				if(o.hour.value && eval(o.hour.value) <= 23 && eval(o.hour.value) >= 0){
					$('input.hour', tbl).val(o.hour.value);
				}
				else{
					$('input.hour', tbl).val('00');
				}
			}
			
			if(o.mins.show){
				$('tr', tbl).append('<td><input type=\'text\' tabindex=\'1\' maxlength=\'2\' class=\'txt mins\' /></td>');
				
				//å¦æä¸è¾åºç§ï¼åå»é¤å¼å·èæ¯
				if(!o.secs.show){
					$('input.mins', tbl).addClass('none');
				}
				
				if(o.mins.id){
					$('input.mins', tbl).attr('id', o.mins.id);
				}
				
				if(o.mins.name){
					$('input.mins', tbl).attr('name', o.mins.name);
				}
				else{
					$('input.mins', tbl).attr('name', 'mins');
				}
				
				if(o.mins.value && eval(o.hour.value) <= 59 && eval(o.hour.value) >= 0){
					$('input.mins', tbl).val(o.mins.value);
				}
				else{
					$('input.mins', tbl).val('00');
				}
			}
			
			if(o.secs.show){
				$('tr', tbl).append('<td><input type=\'text\' tabindex=\'1\' maxlength=\'2\' class=\'txt none secs\' /></td>');
				
				if(o.secs.id){
					$('input.secs', tbl).attr('id', o.secs.id);
				}
				
				if(o.secs.name){
					$('input.secs', tbl).attr('name', o.secs.name);
				}
				else{
					$('input.secs', tbl).attr('name', 'secs');
				}
				
				if(o.secs.value && eval(o.hour.value) <= 23 && eval(o.hour.value) >= 0){
					$('input.secs', tbl).val(o.secs.value);
				}
				else{
					$('input.secs', tbl).val('00');
				}
			}
			
			$('tr', tbl).append('<td class=\'button\'><img src=\'' + o.plus + '\' tabindex=\'1\' class=\'plus\' /><img src=\'' + o.minus + '\' tabindex=\'1\' class=\'minus\' /></td>');
		},
		handler: function(tbl, o){
			var $s = this;
			
			/**
			 * æ¶åç§å¾å°åæ æ¶é´
			**/
			$(':text', tbl).focus(function(){
				$(':text', tbl).removeClass(o.active);
				$(this).addClass(o.active);
				
				this.select();
			});
			
			/**
			 * æ¶é
			**/
			$('input.hour', tbl).keydown(function(e){
				if((e.keyCode < 48 || e.keyCode > 57 && e.keyCode < 96 || e.keyCode > 105) && e.keyCode != 38 && e.keyCode != 40 && e.keyCode != 9){
					return false;
				}
				$s.keydown(e, $(this));
			}).keyup(function(e){
				if(eval(this.value) > 23){
					this.value = '23';
				}
				if(this.value.length == 2 && $('input.mins', tbl).length != 0 && e.keyCode != 38 && e.keyCode != 40 && e.keyCode != 9){
					$('input.mins', tbl).focus();
				}
			}).blur(function(){
				$s.blur(this);
			});
			
			/**
			 * åé
			**/
			$('input.mins', tbl).keydown(function(e){
				if((e.keyCode < 48 || e.keyCode > 57 && e.keyCode < 96 || e.keyCode > 105) && e.keyCode != 38 && e.keyCode != 40 && e.keyCode != 9){
					return false;
				}
				$s.keydown(e, $(this));
			}).keyup(function(e){
				if(eval(this.value) > 59){
					this.value = '59';
				}
				if(this.value.length == 2 && $('input.secs', tbl).length != 0 && e.keyCode != 38 && e.keyCode != 40 && e.keyCode != 9){
					$('input.secs', tbl).focus();
				}
			}).blur(function(){
				$s.blur(this);
			});
			
			/**
			 * ç§é
			**/
			$('input.secs', tbl).keydown(function(e){
				if((e.keyCode < 48 || e.keyCode > 57 && e.keyCode < 96 || e.keyCode > 105) && e.keyCode != 38 && e.keyCode != 40 && e.keyCode != 9){
					return false;
				}
				$s.keydown(e, $(this));
			}).keyup(function(e){
				if(eval(this.value) > 59){
					this.value = '59';
				}
			}).blur(function(){
				$s.blur(this);
			});
			
			/**
			 * å 
			**/
			$('img.plus', tbl).hover(
				function(){
					this.src = o.plushover;	
				},
				function(){
					this.src = o.plus;
				}
			).click(function(){
				if($('.active', tbl).length == 0){
					return false;
				}
				else{
					$s.plus($('.active', tbl));
				}
			});
			
			/**
			 * å
			**/
			$('img.minus', tbl).hover(
				function(){
					this.src = o.minushover;	
				},
				function(){
					this.src = o.minus;
				}
			).click(function(){
				if($('.active', tbl).length == 0){
					return false;
				}
				else{
					$s.minus($('.active', tbl));
				}
			});
		},
		keydown: function(e, txt){
			if(e.keyCode == 38){
				this.plus(txt);
			}
			if(e.keyCode == 40){
				this.minus(txt);
			}
		},
		blur: function(txt){
			if(eval(txt.value) < 10){
				txt.value = '0' + eval(txt.value);
			}
		},
		plus: function(txt){
			var v = eval(txt.val());
			
			if(txt.hasClass('hour')){
				if(v < 23){
					txt.val((v + 1) < 10 ? ('0' + (v + 1)) : (v + 1));
				}
				else{
					txt.val('00');
				}
			}
			if(txt.hasClass('mins') || txt.hasClass('secs')){
				if(v < 59){
					txt.val((v + 1) < 10 ? ('0' + (v + 1)) : (v + 1));
				}
				else{
					txt.val('00');
				}
			}
			
			txt[0].select();
		},
		minus: function(txt){
			var v = eval(txt.val());
			
			if(txt.hasClass('hour')){
				if(v < 1){
					txt.val('23');
				}
				else{
					txt.val((v - 1) < 10 ? ('0' + (v - 1)) : (v - 1));
				}
			}
			if(txt.hasClass('mins') || txt.hasClass('secs')){
				if(v < 1){
					txt.val('59');
				}
				else{
					txt.val((v - 1) < 10 ? ('0' + (v - 1)) : (v - 1));
				}
			}
			
			txt[0].select();
		},
		builder: function(tbl, o){
			this.html(tbl, o);
			this.handler(tbl, o);
		}
	};
	
	$.fn.timer = function(o){
		o = $.extend({}, $.fn.timer.defaults, o);
		
		return this.each(function(){
			tfun.builder(this, o);
		});
	};
	
	$.fn.timer.defaults = {
		plus: '../images/common/tplus.gif',
		plushover: '../images/common/tplus-hover.gif',
		minus: '../images/common/tminus.gif',
		minushover: '../images/common/tminus-hover.gif',
		active: 'active',
		hour: { show: true, id: 'hour', name: 'hour', value: '00' },
		mins: { show: true, id: 'mins', name: 'mins', value: '00' },
		secs: { show: false, id: 'secs', name: 'secs', value: '00' }
	};
})(jQuery);