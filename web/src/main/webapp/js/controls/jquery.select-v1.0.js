/**
 * jQuery.select-v1.0.js
 * Date: 2012-09-20 18:15 (c) 2012 KIWI
**/
(function($){
	var f = {
		html: function(){
			var d = '<div class=\'ui-div-text ui-select\'>'
					+ '<input type=\'text\' readonly=\'readonly\' class=\'text\' />'
					+ '<i class=\'ddl\'></i>'
					+ '<div style=\'position: absolute; top: 22px; left: -1px; right: -1px; display: none; width: 100%; border: 1px solid #b1b1b1; background-color: white; overflow-x: hidden; overflow-y: auto;\'></div>'
					+ '</div>';
			
			return $(d);
		},
		create: function(ele, o){
			var $f = this;
			
			if(!ele.object){
				var zindex = $(ele).css('z-index'), d = this.html();
				
				$(ele).before(d);
				
				/*copy from zhongxin by wuxj 2013-1-16*/
				if(ele.clearFlag){
					d.attr('clearFlag', ele.clearFlag);
				}
				/*copy from zhongxin by wuxj 2013-1-16*/

                if(ele.phoneType){
                    $(':text', d).attr('phoneType', ele.phoneType);
                }

				//id
				if(ele.id || ele.name){
					d.attr('id', 'ui-select-' + (ele.id || ele.name));
				}
				
				//disabled
				if(ele.disabled){
					d.attr('disabled', ele.disabled);
				}
				
				//display
				if(ele.style.display){
					d.css('display', ele.style.display);
				}
				
				//width
				if(ele.width || ele.style.width){
					d.css({
						'width': ele.width || ele.style.width,
						'float': 'left'
					});
				}
				/*copy from zhongxin by wuxj 2013-1-16*/
				//validator
				if($(ele).attr('rule')){
					$('input[type=\'text\']', d).attr('rule', $(ele).attr('rule'));
					$(ele).removeAttr('rule');
				}
				if($(ele).attr('fun')){
					$('input[type=\'text\']', d).attr('fun', $(ele).attr('fun'));
					$(ele).removeAttr('fun');
				}
				if($(ele).attr('url')){
					$('input[type=\'text\']', d).attr('url', $(ele).attr('url'));
					$(ele).removeAttr('url');
				}
				if($(ele).attr('tips')){
					$('input[type=\'text\']', d).attr('tips', $(ele).attr('tips'));
					$(ele).removeAttr('tips');
				}
				/*copy from zhongxin by wuxj 2013-1-16*/		
				//初始化赋值
				$(':text', d).val($(':selected', ele).text());
				
				//初始化点击事件
				d.click(function(e){
					$('.combobox, .combobox-box, .ui-subtree, .ui-agetree, .atree').css('display', 'none');
					
					if(!$('div', this).is(':hidden')){
						$f.clear($(this), zindex);
					}
					else{
						//清除页面其他的select下拉框
						$('div.ui-select').each(function(){
							$f.clear($(this), zindex);
						});
						
						if(o.beforeClick && typeof(o.beforeClick)=='function'){
							o.beforeClick(ele);
						}
						
						$f.init($(this), ele, o);
					}
					
					e.stopPropagation();
				});
				
				$(document).click(function(e){
					$f.clear(d, zindex);
				});
				
				/*add by wuxj 20130422*/
				//点击目标不在下拉框中则隐藏下拉选项框
				$(document).mouseup(function(event){
					if($(event.target).parents(".ui-select").length==0){ 
                   		$f.clear(d, zindex);
                	}
				});
				/*add by wuxj 20130422*/
				
				ele.object = d;
			}
			else{
				//初始化赋值
				$(':text', ele.object).val($(':selected', ele).text());
			}
		},
		init: function(d, ele, o){
			var $f = this, option = [];
			
			if($('option', ele).length > o.scroll){
				$('div', d).height(22 * o.scroll);
			}
			else{
				$('div', d).height('auto');
			}
			
			$('option', ele).each(function(i){
				option.push('<li' + (this.disabled ? ' disabled=\'disabled\'' : '') + '><label style=\'display: block; width: 100%; height: 22px; padding: 0px 1px; overflow: hidden; text-align: left; line-height: 22px; white-space: nowrap;\'>' + $(this).text() + '</label></li>');
			});
			
			$('div', d).html('<ul>' + option.join('') + '</ul>').bgiframe();
			
			$('div ul li', d).click(function(e){
				if(this.disabled) return false;
				
				if(o.optionClick && typeof(o.optionClick)=='function'){
					if(!o.optionClick(ele, this)){
						$f.clear(d, 0);
						return false;
					}
				}
				
				//根据当前点击项的索引更新原始select标记选择项
				ele.selectedIndex = $('div ul li', d).index(this);
				
				//触发原始select标记的change事件
				$(ele).change();
				
				//文本框赋值
				$('input[type=\'text\']', d).val($('option:eq(' + ele.selectedIndex + ')', ele).text()).focus().blur();
				
				$f.clear(d, 0);
				
				//阻止事件冒泡
				e.stopPropagation();
			}).mouseover(function(){
				if(this.disabled) return false;
				
				$(this).css({
					'color': 'white',
					'backgroundColor': '#4c64a6'
				}).siblings().removeAttr('style');
			});
			
			$('div ul li:eq(' + ele.selectedIndex + ')', d).css({
				'color': 'white',
				'backgroundColor': '#4c64a6'
			});
			
			d.css('z-index', 100).find('div').show();
		},
		clear: function(d, zindex){
			if(typeof(d) != 'undefined'){
				zindex = zindex || '0';
				
				d.css('z-index', zindex).find('div').empty().hide();
			}
		}
	};
	
	$.fn.select = function(o){
		o = $.extend({}, $.fn.select.defaults, o);
		
		return this.each(function(){
			f.create(this, o);
			
			//hide this
			$(this).hide();
		});
	};
	
	$.fn.select.defaults = {
		scroll: 12,
		beforeClick: null,
		optionClick: null
	};
})(jQuery);