/**
 * jQuery.combobox-v1.0.js
 * Date: 2012-09-20 18:15 (c) 2012 KIWI
**/
(function($){
	var f = {
		html: function(o){
			var d = '<div class=\'combobox-box\'></div>';
			
			return $(d);
		},
		buttons: function(){
			var b = '<div class=\'buttons\'>'
				  + '<div>'
				  + '<input type=\'button\' class=\'button all\' value=\'全选\' />'
				  + '<input type=\'button\' class=\'button none\' value=\'全不选\' />'
				  + '<input type=\'button\' class=\'button counter\' value=\'反选\' />'
				  + '</div>'
				  + '</div>';
			
			return b;
		},
		builder: function(s, o){
			var $f = this, box = $f.html();
			
			if(o.button){
				box.html($f.buttons());
			}
			
			if(o.options.constructor==Array && o.options.length>0){
				box.prepend('<div class=\'options\' style=\'' + (o.options.length > 10 ? 'height: 240px;' : 'height: auto;') + '\'><ul></ul></div>');
				
				var l = [];
				for(var i in o.options){
					var option = o.options[i];
					
					if(!option){
						continue;
					}
					
					l.push('<li title=\'' + option.text + '\'><input type=\'checkbox\' class=\'check\' value=\'' + (option.value=='undefined'?option.text:option.value) + '\' /><label>' + option.text + '</label></li>');
				}
				
				$('div.options ul', box).html(l.join(''));
			}
			
			$('body').append(box);
			
			$f.handler(s, box, o);
		},
		handler: function(s, box, o){
			var $f = this;
			
			if($('div.options ul li', box).length > 0){
				if($.browser.msie && $.browser.version < 7.0){
					$('div.options ul li', box).hover(
						function(){ $(this).addClass('hover') },
						function(){ $(this).removeAttr('class') }
					);
				}
				
				$('div.options :checkbox', box).click(function(){
					$f.val(s, box, o);
				});
			}
			
			if(o.button){
				//全选
				$('input.all', box).click(function(){
					$('div.options :checkbox', box).attr('checked', 'checked');
					
					$f.val(s, box, o);
				});
				//全不选
				$('input.none', box).click(function(){
					$('div.options :checkbox', box).removeAttr('checked');
					
					$f.val(s, box, o);
				});
				//反选
				$('input.counter', box).click(function(){
					$('div.options :checkbox', box).each(function(){
						if(this.checked){
							$(this).removeAttr('checked');
						}
						else{
							$(this).attr('checked', 'checked');
						}
					});
					
					$f.val(s, box, o);
				});
			}
			
			box.click(function(e){
				e.stopPropagation();
			});
			
			$(s).click(function(e){
				ctrol.hide();
				
				if(box.is(':hidden')){
					box.css({
						'top': $(s).offset().top + $(s).outerHeight() - 1 + 'px',
						'left': $(s).offset().left + 'px',
						'width': $(s).width() + 'px'
					}).show();
				}
				else{
					box.hide();
				}
				
				$f.init(s, box);
				
				e.stopPropagation();
			});
			
			$(document).click(function(){
				box.hide();
			});
			
			$(window).resize(function(){
				box.css({
					'top': $(s).offset().top + $(s).outerHeight() - 1 + 'px',
					'left': $(s).offset().left + 'px',
					'width': $(s).width() + 'px'
				});
			});
		},
		init: function(s, box){
			if($('input[type=\'text\']', s).val() != ''){
				var a = $('input[type=\'text\']', s).val().split(',');
				for(var i in a){
					$('li[title=\'' + a[i] + '\']', box).find(':checkbox').attr('checked', 'checked');
				}
			}else{
				$('li', box).find(':checkbox').removeAttr('checked');
			}
		},
		val: function(s, box, o){
			var t = [], v = [];
			$('div.options :checked', box).each(function(i){
				t[i] = $(this).next('label').text();
				v[i] = $(this).val();
			});
			$('input[type=\'text\']', s).val(t.join(','));
			
			if(o.onCheck && typeof(o.onCheck)=='function'){
				o.onCheck(t, v);
			}
		}
	};
	
	$.fn.combobox = function(o){
		o = $.extend({}, $.fn.combobox.defaults, o);
		
		return this.each(function(){
			$('input[type=\'text\']', this).attr('readonly', 'readonly');
			
			if(!this.object){
				f.builder(this, o);
			}
			else{
				
			}
		});
	};
	
	$.fn.combobox.defaults = {
		button: true,
		options: [],
		onCheck: null
	};
})(jQuery);