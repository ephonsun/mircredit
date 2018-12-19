// JavaScript Document

(function($){
	var f = {
		child: function(obj, t){
			return $('tbody tr[pid=\'' + obj.id + '\']', t);
		}
	};
	
	$.fn.tabletree = function(o){
		o = $.extend({}, $.fn.tabletree.defaults, o);
		
		return this.each(function(){
			var t = this;
			
			$('tbody tr', t).each(function(){
				if($('#' + this.pid).length == 0){
					this.level = 1;
					
					var child = f.child(this, t);
					
					this.child = child;
					
					if(child.length > 0){
						$('td:eq(' + o.cell + ')', this).prepend('<a class=\'node-icon node-fold\'>&nbsp;</a>');
						
						$('a.node-icon', this).data('child', child);
						
						child.insertAfter(this);
					}
					else{
						$('td:eq(' + o.cell + ')', this).prepend('<a class=\'node-icon node-blank\'>&nbsp;</a>');
					}
				}
				else{
					this.parent = $('#' + this.pid);
					
					var level = parseInt($('#' + this.pid).attr('level'));
					
					var html = '';
					for(var i=0; i<level; i++){
						html += '<a class=\'node-icon node-blank\'>&nbsp;</a>';
					}
					
					this.level = level + 1;
					
					var child = f.child(this, t);
					
					this.child = child;
					
					if(child.length > 0){
						$('td:eq(' + o.cell + ')', this).prepend(html + '<a class=\'node-icon node-fold\'>&nbsp;</a>');
						
						$('a.node-icon', this).data('child', child);
						
						child.insertAfter(this);
					}
					else{
						$('td:eq(' + o.cell + ')', this).prepend(html + '<a class=\'node-icon node-blank\'>&nbsp;</a>');
					}
				}
			});
			
			$('a.node-icon').click(function(){
				/*copy from zhongxin by wuxj 2013-1-16*/
				if($(this).hasClass('node-fold')){
					$('a.node-fold', $(this).data('child')).click();
					$(this).data('child').hide();
					$(this).removeClass('node-fold');
					$(this).addClass('node-expan');
				}
				else{
					if($(this).data('child') && typeof $(this).data('child') !='undifined'){
						$(this).data('child').show();
						$(this).removeClass('node-expan');
						$(this).addClass('node-fold');
					}
				}
				/*copy from zhongxin by wuxj 2013-1-16*/
			});
		});
	};
	
	$.fn.tabletree.defaults = {
		cell: 0,
		shape: 'shrink'
	};
})(jQuery);
























