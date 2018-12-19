/**
 * jQuery.combobox-v1.0.js
 * Date: 2012-09-20 18:15 (c) 2012 KIWI
**/
(function($){
	var f = {
		
	};
	
	$.fn.etabs = function(o){
		o = $.extend({}, $.fn.etabs.defaults, o);
		
		return this.each(function(){
			var h = $('#' + o.head), b = $('#' + o.body), bar = $('#' + o.bar), w = 0;
			
			/*note by wuxj 2013-5-24
			//set width of the tabs items zone
			$('li', h).each(function(){
				w += $(this).outerWidth(true);
			});
			h.width(w + 1);
			*/
			//activated by default items
			h.children('li:eq(' + o.index + ')').addClass(o.active).siblings().removeAttr('class');
			b.children('div:eq(' + o.index + ')').show().siblings().hide();
			
			//click event
			$('li', h).click(function(){
				if(!$(this).hasClass(o.active)){
					if(o.beforeOnclick && typeof(o.beforeOnclick)=='function'){
						var t = $('li.' + o.active, h),
							d = $('div.etabs-body-child:eq(' + $('li', h).index(t) + ')', b);
						
						if(!o.beforeOnclick(t, d)){
							return false;
						}
					}
					
					$(this).addClass(o.active).siblings().removeAttr('class');
					$('div.etabs-body-child:eq(' + $('li', h).index(this) + ')', b).show().siblings().hide();
				}
				
				if(o.onclick && typeof(o.onclick)=='function'){
					o.onclick($(this), $('div.etabs-body-child:eq(' + $('li', h).index(this) + ')', b));
				}
			});
			
			//left
			$('li.l', bar).click(function(){
				
			});
			
			//right
			$('li.r', bar).mousedown(function(){
				
			});
		});
	};
	
	$.fn.etabs.defaults = {
		'index': 0,
		'head': 'etabs-head',
		'body': 'etabs-body',
		'bar': 'etabs-bar',
		'active': 'etabs-active',
		'beforeOnclick': null,
		'onclick': null
	};
})(jQuery);