/**
 * jQuery.table-v1.0.js
 * Date: 2012-09-20 18:15 (c) 2012 KIWI
**/
(function(){
	var f = {
		striped: function(t, opts){
			$('tbody tr:visible:odd', t).addClass(opts.odd);
			$('tbody tr:visible:even', t).addClass(opts.even);
		},
		all: function(t){
			return $('thead :checkbox:enabled[rel=\'all\']', t);
		},
		row: function(t){
			return $('tbody :checkbox:enabled[rel=\'row\']', t);
		},
		checked: function(t){
			return $('tbody :checkbox:enabled:checked[rel=\'row\']', t);
		}
	};
	
	$.fn.table = function(o){
		o = $.extend({}, $.fn.table.defaults, o);
		
		return this.each(function(){
			var t = this;
			
			if(o.striped){
				f.striped(t, o);
			}
			
			f.all().click(function(){
				if(this.checked){
					f.row().each(function(){
						this.checked = true;
						$(this).parents('tr').addClass(o.active);
					});
				}
				else{
					f.row().each(function(){
						this.checked = false;
						$(this).parents('tr').removeClass(o.active);
					});
				}
			});
			
			$('tbody tr', t).each(function(){
				var $s = $(this);
				
			if($.browser.msie && $.browser.version<7.0){}
				$s.hover(
					function(){
						if(!$s.hasClass(o.active)) $s.addClass(o.hover);
					},
					function(){
						$s.removeClass(o.hover);
					}
				);
				
				$(':checkbox[rel=\'row\']', $s).click(function(){
					if(this.checked){
						$s.addClass(o.active);
					}
					else{
						$s.removeClass(o.active);
					}
					
					if(f.row().length == f.checked().length){
						f.all().attr('checked', 'checked');
					}
					else{
						f.all().removeAttr('checked');
					}
				});
				
				$('span.delete', $s).click(function(){
					if(o.del && typeof(o.del)=='function'){
						var bool = o.del($s);
						if(bool){
							
						}
					}
				});
				$('span.up', $s).click(function(){
					if(o.up && typeof(o.up)=='function'){
						var bool = o.up($s);
						if(bool){
							
						}
					}
				});
				$('span.down', $s).click(function(){
					if(o.down && typeof(o.down)=='function'){
						var bool = o.down($s);
						if(bool){
							
						}
					}
				});
				$('span.editor', $s).click(function(){
					if(o.editor && typeof(o.editor)=='function'){
						o.editor($s);
					}
				});
				$('span.view', $s).click(function(){
					if(o.view && typeof(o.view)=='function'){
						o.view($s);
					}
				});
			});
		});
	};
	
	$.fn.table.defaults = {
		'odd': 'odd',
		'even': 'even',
		'hover': 'hover',
		'active': 'active',
		'striped': true,
		'del': null,
		'up': null,
		'down': null,
		'editor': null,
		'view': null
	};
})(jQuery);