// JavaScript Document

(function($){
	var ttfun = {
		children: function(n, t){
			return $('tbody tr[pid=\'' + n.id + '\']', t);
		}
	};
	
	$.fn.tabletree = function(o){
		o = $.extend({}, $.fn.tabletree.defaults, o);
		
		return this.each(function(){
			var tbl = this;
			
			$('tbody tr', tbl).each(function(){
				if($('#' + this.pid).length == 0){
					this.level = 1;
					
					var child = ttfun.children(this, tbl);
					
					this.child = child;
					
					if(child.length > 0){
						$('td:eq(' + o.cell + ')', this).prepend('<img src=\'../images/common/fold.gif\' style=\'cursor: pointer;\' class=\'expander open\' />');
						$('img', this).data('child', child);
						
						child.insertAfter(this);
					}
					else{
						$('td:eq(' + o.cell + ')', this).prepend('<img src=\'../images/common/blank.gif\' />');
					}
				}
				else{
					this.parent = $('#' + this.pid);
					
					var l = parseInt($('#' + this.pid)[0].level)
					
					var html = '';
					for(var i=0; i<l; i++){
						html += '<img src=\'../images/common/blank.gif\' />';
					}
					
					this.level = l + 1;
					
					var child = ttfun.children(this, tbl);
					
					this.child = child;
					
					if(child.length > 0){
						$('td:eq(' + o.cell + ')', this).prepend(html + '<img src=\'../images/common/expan.gif\' style=\'cursor: pointer;\' class=\'expander\' />');
						$('img', this).data('child', child);
						
						child.insertAfter(this).hide();
					}
					else{
						$('td:eq(' + o.cell + ')', this).prepend(html + '<img src=\'../images/common/blank.gif\' />');
					}
				}
			});
			
			$('img.expander').click(function(){
				if($(this).hasClass('open')){
					$('img.open', $(this).data('child')).click();
					
					$(this).data('child').hide();
					this.src = '../images/common/expan.gif';
					$(this).removeClass('open');
				}
				else{
					$(this).data('child').show();
					this.src = '../images/common/fold.gif';
					$(this).addClass('open');
				}
			});
		});
	};
	
	$.fn.tabletree.defaults = {
		cell: 0
	};
})(jQuery);
