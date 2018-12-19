// JavaScript Document

jQuery.validator = function(o){
	var bool = true, form = jQuery('#' + o.form), v = form.data('v');
	
	jQuery('[rule], [fun], [url], [tips]', form).each(function(){
		if($(this).attr('rule')){
			if(!v.rule(this)){
				bool = false;
			}
		}
		else if($(this).attr('fun')){
			if(!v.fun(this)){
				bool = false;
			}
		}
		else if($(this).attr('url')){
			if(!v.url(this)){
				bool = false;
			}
		}
	});
	
	if(o.extend){
		if(!o.extendhandler()){
			bool = false;
		}
	}
	
	return bool;
};

(function($){
	var v = {
		rule: function(obj){
			var r = new RegExp($(obj).attr('rule')), v = $(obj).val();

			//success
			if(r.test(v)){
				if($(obj).attr('fun')){
					return this.fun(obj);
				}
				else{
					this.css(obj, 'remove');
					return true;
				}
			}
			//failure
			else{
				this.css(obj, 'add');
				return false;
			}
		},
		fun: function(obj){
			var f, bool = false;
			
			try{
				f = eval($(obj).attr('fun'));
			}
			catch(e){
				alert('找不到' + $(obj).attr('fun') + '函数!');
			}
			
			if(typeof(f) == 'function'){
				bool = f(obj);
			}
			
			//success
			if(bool){
				if($(obj).attr('url')){
					return this.url(obj);
				}
				else{
					this.css(obj, 'remove');
					return true;
				}
			}
			//failure
			else{
				this.css(obj, 'add');
				return false;
			}
		},
		url: function(obj){
			var u = $(obj).attr('url');
			
			if(u.indexOf('?') != -1){
				u = u + '&' + obj.name + '=' + obj.value;
			}
			else{
				u = u + '?' + obj.name + '=' + obj.value;
			}
			
			var txt = $.ajax({ url: u, cache: false, async: false }).responseText.replace(/(^\s*)|(\s*$)/g, '');
			
			//success
			if(txt.toLowerCase() == 'success'){
				this.css(obj, 'remove');
				this.bubble(obj, null, 'remove');
				return true;
			}
			//failure
			else{
				this.css(obj, 'add');
				this.bubble(obj, txt, 'add');
				return false;
			}
		},
		css: function(obj, action){
			//add class
			if(action == 'add'){
				$(obj).parent().addClass('v-fails');
			}
			//remove class
			else{
				$(obj).parent().removeClass('v-fails');
			}
		},
		bubble: function(obj, txt, action){
			//判断该表单元素是否定义了提示文本
			if($(obj).attr('tips')){
				$(obj).attr('notips', 'true');
			}
			
			if(action == 'add'){
				if($(obj).attr('notips') == 'true'){
					if(txt != null){
						$(obj).attr('tips', txt);
					}
				}
				else{
					if(txt != null){
						if($(obj).attr('former')){
							$(obj).attr('former', $(obj).attr('tips'));
						}
						$(obj).attr('tips', txt);
					}
				}
			}
			else{
				if($(obj).attr('notips') == 'true'){
					$(obj).removeAttr('tips');
					$(obj).removeAttr('former');
				}
				else{
					$(obj).attr('tips', $(obj).attr('former'));
					$(obj).removeAttr('former');
				}
			}
		}
	};
	
	$.fn.validator = function(o){
		o = $.extend({}, $.fn.validator.defaults, o);
		
		return this.each(function(){
			$('[rule], [fun], [url], [tips]', this).hover(
				function(e){
					if($(this).attr('tips') && ($(this).hasClass('v-fails') || $(this).parent().hasClass('v-fails'))){
						$('body').append('<div class=\'v-tips\'><img src=\'../images/common/vtips.gif\' /><label></label></div>');
						
						var tips = $(this).attr('tips');
						$('div.v-tips').bgIframe().css({ top: e.pageY + o.y + 'px', left: e.pageX + o.x + 'px' }).find('label').text(tips);
					}
				},
				function(){
					if($(this).attr('tips')){
						$('div.v-tips').remove();
					}
				}
			).mousemove(function(e){
				if($(this).attr('tips')){
					$('div.v-tips').css({ top: e.pageY + o.y + 'px', left: e.pageX + o.x + 'px' });
				}
			}).blur(function(){
				if($(this).attr('rule')){
					v.rule(this);
				}
				else if($(this).attr('fun')){
					v.fun(this);
				}
				else if($(this).attr('url')){
					v.url(this);
				}
			});
			
			$(this).data('v', v);
		});
	};
	
	$.fn.validator.defaults = {
		x: -17,
		y: 20,
		isextend: false,
		extend: null
	};
})(jQuery);










