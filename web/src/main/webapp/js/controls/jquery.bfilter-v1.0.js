/**
 * jQuery.bfilter-v1.0.js
 * Date: 2012-09-20 18:15 (c) 2012 KIWI
**/
(function($){
	var f = {
		html: function(){
			var ui = '<div class=\'bfilter-box\' style=\'\'>'
				   + '<div class=\'bfilter-box-h\'></div>'
				   + '<div class=\'bfilter-box-b\'></div>'
				   + '</div>';
			
			return ui;
		},
		init: function(b, o){
			var param = (typeof(o.data) == 'function') ? o.data() : o.data;
			
			$.ajax({
				'type': o.type,
				'async': o.async,
				'cache': o.cache,
				'url': o.url,
				'data': param,
				'dataType': o.dataType,
				'success': function(data){
					
				},
				'error': function(){
					if(o.nodes && o.nodes.length > 0){
						$('div.bfilter-box-b', b).empty().html('<table><tbody></tbody></table>');
						
						$.each(o.nodes, function(i, node){
							var tr = $('<tr></tr>');
							
							tr.data('node', node);
							
							$.each($('div.bfilter-box-h table thead tr th', b), function(){
								var td = $('<td></td>');
								
								if(this.field){
									td.attr('field', this.field);
								}
								
								if(this.width){
									td.attr('width', this.width);
								}
								
								if(this.height){
									td.attr('height', this.height);
								}
								
								if(this.align){
									td.attr('align', this.align);
								}
								
								if(node[this.field]){
									td.html('<label>' + node[this.field] + '</label>');
								}
								else{
									td.html('<label>--</label>');
								}
								
								tr.append(td);
							});
							
							//event
							tr.click(function(){
								if(o.click && typeof(o.click)=='function'){
									o.click(node);
								}
							}).hover(
								function(){ $(this).css({ 'background-color': '#ffece0' }); },
								function(){ $(this).css({ 'background-color': 'white' }); }
							);
							
							$('div.bfilter-box-b table tbody', b).append(tr);
						});
					}
					else{
						$('div.bfilter-box-b', b).empty().html('<div class=\'bfilter-box-error\'>无符合结果！<div>');
					}
				}
			});
		}
	};
	
	$.fn.bfilter = function(o){
		o = $.extend({}, $.fn.bfilter.defaults, o);
		
		return this.each(function(){
			var it = this, b = $(f.html());
			
			if(o.thead){
				var h = $('<table><thead><tr></tr></thead></table>');
				
				$.each(o.thead, function(i, node){
					var th = $('<th height=\'20\' axis=\'col' + i + '\'></th>');
					if(node){
						if(node.text){
							th.html('<label>' + node.text + '</label>')
						}
						else{
							th.html('<label>Undefined</label>').width(100).attr('align', 'center');
						}
						
						if(node.field){
							th.attr('field', node.field);
						}
						
						if(node.width){
							th.attr('width', node.width);
						}
						else{
							th.width(100);
						}
						
						if(node.align){
							th.attr('align', node.align);
						}
					}
					else{
						th.html('<label>Undefined</label>').width(100).attr('align', 'center');
					}
					$('tr', h).append(th);
				});
				
				$('div.bfilter-box-h', b).prepend(h);
			}
			
			f.init(b, o);
			
			$('body').append(b);
			
			//按键弹起时过滤
			$('input[type=\'text\']', this).keyup(function(){
				f.init(b, o);
				
				//显示
				b.show().css({
					'top': $(it).position().top + $(it).outerHeight(true) - 1 + 'px',
					'left': $(it).position().left + 'px'
				});
			});
			
			$(document).click(function(){
				b.hide();
			});
			
			$(window).resize(function(){
				b.css({
					'top': $(it).position().top + $(it).outerHeight(true) - 1 + 'px',
					'left': $(it).position().left + 'px'
				});
			});
		});
	};
	
	$.fn.bfilter.defaults = {
		'type': 'POST',
		'async': false,
		'url': '',
		'data': '',
		'dataType': 'json',
		'cache': false,
		'thead': null,
		'click': null,
		'change': null,
		'nodes': [
			{ 'id': 1, 'no': '12345', 'name': 'abc', 'card': '342523198801052811' },
			{ 'id': 2, 'no': '12345', 'name': '123', 'card': '342523198801052811' },
			{ 'id': 3, 'no': '12345', 'name': 'bcd', 'card': '342523198801052811' },
			{ 'id': 4, 'no': '12345', 'name': '234', 'card': '342523198801052811' },
			{ 'id': 5, 'no': '12345', 'name': 'cde', 'card': '342523198801052811' }
		]
	};
})(jQuery);