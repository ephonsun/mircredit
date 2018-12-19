// JavaScript Document

/**
 * jQuery.defaultVal-v1.0.js
**/
(function($){
	$.fn.defaultVal = function(o){
		o = $.extend({}, $.fn.defaultVal.defaults, o);
		
		return this.each(function(){
			var obj, it = $(this);
			
			if(it.is('div')){
				obj = $('input[type=\'text\']', it);
			}else if(it.is('input[type=\'text\']')){
				obj = it;
			}else{
				return false;
			}
			
			if($.trim(obj.val()) == ''){
				obj.css({ color: '#7d7d7d' }).val(o.value);
			}
			
			obj.focus(function(){
				if($.trim(obj.val()) == o.value){
					obj.removeAttr('style').val('');
				}else{
					
				}
			}).blur(function(){
				if($.trim(obj.val()) == ''){
					obj.css({ color: '#7d7d7d' }).val(o.value);
				}
			});
		});
	};
	
	$.fn.defaultVal.defaults = {
		value: 'Please enter a value'
	};
})(jQuery);