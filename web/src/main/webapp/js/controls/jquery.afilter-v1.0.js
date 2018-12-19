/**
 * jQuery.afilter-v1.0.js
 * Date: 2012-09-20 18:15 (c) 2012 KIWI
**/
(function($){
	var f = {
		builder: function(s, o){
			$.ajax({
				type: o.type,
				async: o.async,
				cache: o.cache,
				url: o.url,
				data: o.data,
				dataType: o.dataType,
				success: function(d){
					d = [
						{ id: 1, text: 'afdsafdsafdsafdsafdsafdsafdsafdsafdsaf', limit: '100', unit: 'u' },
						{ id: 2, text: 'b', limit: '200', unit: 'u' },
						{ id: 3, text: '测试测试测试测试测试测试测试测试测试测试测试', limit: '300', unit: 'u' },
						{ id: 4, text: 'd', limit: '400', unit: 'u' },
						{ id: 5, text: 'e', limit: '500', unit: 'u' },
						{ id: 6, text: 'f', limit: '600', unit: 'u' },
						{ id: 7, text: 'g', limit: '700', unit: 'u' },
						{ id: 8, text: 'h', limit: '800', unit: 'u' },
						{ id: 9, text: 'i', limit: '900', unit: 'u' },
						{ id: 10, text: 'j', limit: '1000', unit: 'u' }
					];
					
					if(d && d.length>0){
						var z = $('<div class=\'afilter-box\'><ul></ul></div>'), a = [];
						
						for(var i=0, l=d.length; i<l; i++){
							if(d[i]){
								a.push('<li k=\'' + d[i] + '\'><label>' + d[i].text + '</label></li>');
							}
						}
						
						$('ul', z).append(a.join(''));
						$('body').append(z);
					}
				},
				error: function(){
					
				}
			});
		},
		show: function(){
			
		},
		scroll: function(){
			
		}
	};
	
	$.fn.afilter = function(o){
		o = $.extend({}, $.fn.afilter.defaults, o);
		
		return this.each(function(){
			f.builder(this, o);
			
			$('input[type=\'text\']', this).keyup(function(){
				
			});
			
			$('i', this).click(function(){
				if(z.is(':hidden')){
					f.show();
				}
			});
			
		});
	};
	
	$.fn.afilter.defaults = {
		type: 'POST',
		async: false,
		url: '',
		data: '',
		dataType: '',
		cache: false,
		click: null,
		change: null,
		nodes: [
			{ id: 1, text: 'abcdefg' },
			{ id: 2, text: '1234567' },
			{ id: 3, text: 'bcdefht' },
			{ id: 4, text: '23467809' },
			{ id: 5, text: 'cdeee' },
			{ id: 6, text: '3455433' },
			{ id: 7, text: 'def1dg3gfgf' },
			{ id: 8, text: '456bbddef' },
			{ id: 9, text: 'efg' },
			{ id: 10, text: '5674' },
			{ id: 11, text: 'rghklrt' },
			{ id: 12, text: '78910' },
			{ id: 13, text: '111222' },
			{ id: 14, text: 'aaabbb' },
			{ id: 15, text: '22223333' },
			{ id: 16, text: 'abababab' },
			{ id: 17, text: '76767' },
			{ id: 18, text: 'dingwei' },
			{ id: 19, text: 'zhangsan' },
			{ id: 20, text: 'ceshi' },
			{ id: 21, text: 'baidu' },
			{ id: 22, text: '121516' },
			{ id: 23, text: 'ab12effd' },
			{ id: 24, text: '2090d8' },
			{ id: 25, text: 'ff6600' }
		]
	};
})(jQuery);