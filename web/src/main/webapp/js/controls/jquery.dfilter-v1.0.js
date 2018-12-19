// JavaScript Document

/**
 * 仿百度搜索检索 （Ajax异步	table）
**/
(function(){
	$.fn.dfilter = function(args){
		return this.each(function(){
			args = $.extend({
				type: 'POST',
				async: false,
				url: '',
				data: null,
				format: 'json',
				cache: false,
				thead: null,
				click: null,
				change: null,
				nodes: [
					{ id: 1, no: '12345', name: 'abc', card: '342523198801052811' },
					{ id: 2, no: '12345', name: '123', card: '342523198801052811' },
					{ id: 3, no: '12345', name: 'bcd', card: '342523198801052811' },
					{ id: 4, no: '12345', name: '234', card: '342523198801052811' },
					{ id: 5, no: '12345', name: 'cde', card: '342523198801052811' }
				]
			}, args);
			
			var set = {
				init: function(){
					var param = (typeof(args.data) == 'function') ? args.data() : args.data;
					//was对中文值进行转码
					var spl=param.split("&");
					param="";
					for(var i=0;i<spl.length;i++){
						var str=spl[i].split("=");  
						param=str[0]+"="+escape(encodeURIComponent(str[1]))+"&"+param;
					}
					$.ajax({
						type: args.type,
						async: args.async,
						url: args.url,
						data: param,
						dataType: args.format,
						cache: args.cache,
						success: function(data){
							data = (typeof(data)=='string') ? $.parseJSON(data) : data;
							if(data && data.length > 0){
								set.mdiv.empty().html('<table style=\'border-collapse: collapse;table-layout: fixed;width: 100%;\'><tbody></tbody></table>');
								if(jQuery.browser.msie && jQuery.browser.version<7.0){
									set.mdiv.empty().html('<table style=\'border-collapse: collapse;table-layout: fixed;\'><tbody></tbody></table>');
								}
								
								$.each(data, function(i, node){
									var tr = $('<tr></tr>');
									
									//绑定当前json对象到html标记上
									tr[0].node = node;
									
									$.each($('thead tr th', set.hdiv), function(){
										var td = $('<td style=\'border-right: 1px dashed #e1e1e1; border-bottom: 1px solid #e1e1e1; padding: 2px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;\'></td>');
										
										if(this.width){
											td[0].width = this.width;
										}
										
										if(this.height){
											td[0].height = this.height;
										}
										
										if(this.align){
											td[0].align = this.align;
										}
										
										if(this.field){
											td[0].field = this.field;
										}
										
										if(node[this.field]){
											td.html('<label title=\''+ node[this.field] +'\'>' + node[this.field] + '</label>');
										}
										else{
											td.html('<label></label>');
										}
										
										tr.append(td);
									});
									
									//行单击
									tr.click(function(){
										if(args.click && typeof(args.click)=='function'){
											args.click(node);
										}
										
										//让文本框重新获得焦点
										$(':text:first', set.objtxt).focus();
										
										//隐藏
										set.hide();
									});
									
									//鼠标划过效果
									tr.hover(
										function(){ $(this).css({ backgroundColor: '#e3eaf4' }); },
										function(){ $(this).css({ backgroundColor: 'white' }); }
									);
									
									$('tbody', set.mdiv).append(tr);
								});
							}
							else{
								set.mdiv.empty().html('<div style=\'line-height: 50px; text-align: center; color: red;\'>无符合结果！</div>');
							}
							
							set.bdiv.append(set.mdiv);
						},
						error: function(){
							
						}
					});
				},
				show: function(){
					this.bdiv.show();
				},
				hide: function(){
					this.bdiv.hide();
				},
				top: function(){
					return this.objtxt.position().top + this.objtxt.outerHeight(true) - 1 + 'px';
				},
				left: function(){
					return this.objtxt.position().left + 'px';
				},
				position: function(){
					this.bdiv.css({
						top: set.top(),
						left: set.left()
					});
				},
				scroll: function(){
					
				}
			};
			set.objtxt = $(this);
			var w = 0;
			if(args.thead){
				var ths = args.thead;
				for(var i = 0; i < args.thead.length; i++){
					var th = ths[i];
					w += th.width + 5;
				}
				w += 2;
			}else{
				w = 'auto';
			}
			set.bdiv = $('<div name="divName" style="position: absolute; display: none; width:' + w + 'px; background-color: white;"></div>');
			if(jQuery.browser.msie && jQuery.browser.version<7.0){
				set.hdiv = $('<div style=\'float: left; height: 24px; border: 1px solid #ccc; background: url(../images/template/b-bomb-box-colheader.gif); overflow: hidden;\'></div>');
			}
			else{
				set.hdiv = $('<div style=\'height: 24px; border: 1px solid #ccc; background: url(../images/template/b-bomb-box-colheader.gif); overflow: hidden;\'></div>');
			}
			set.mdiv = $('<div style=\'border: 1px solid #ccc; margin-top: -1px; overflow: auto;\'></div>');
			
			//创建列头
			if(args.thead){
				var thead = $('<table style=\'border-collapse: collapse;\'><thead><tr></tr></thead></table>');
				$.each(args.thead, function(i, node){
					var th = $('<th height=\'20\' axis=\'col' + i + '\' style=\'border-right: 1px solid #ccc; padding: 2px;\'></th>');
					if(node){
						if(node.text){
							th.html('<label>' + node.text + '</label>');
						}
						else{
							th.html('<label>Undefined</label>');
							th[0].width = 100;
							th[0].align = 'center';
						}
						
						if(node.field){
							th[0].field = node.field;
						}
						
						if(node.width){
							th[0].width = node.width;
						}
						else{
							th[0].width = 100;
						}
						
						if(node.align){
							th[0].align = node.align;
						}
					}
					else{
						th.html('<label>Undefined</label>');
						th[0].width = 100;
						th[0].align = 'center';
					}
					$('tr', thead).append(th);
				});
				set.hdiv.prepend(thead);
			}
			set.bdiv.append(set.hdiv).append('<div style=\'clear:both;\'></div>');
			
			//装载数据，绘制元素标记
			set.init();
			
			$('body').append(set.bdiv);
			
			
			//按键弹起时过滤
			$(':text:first', set.objtxt).focus(function(){
				$('div[name="divName"]').hide();
			}).keydown(function(){
				var keycode = event.keyCode;
				if(keycode == 9){
					set.hide();
				}
			}).keyup(function(){							
				var keycode = event.keyCode;
				if(keycode == 9){
					return false;	
				}else{
					//重新装载数据
					set.init();
					
					//显示
					set.show();
						
					//重置大小及位置
					set.position();
					
					if(args.change && typeof(args.change)=='function'){
						var arr = [];
						$.each($('table tbody tr:visible', set.mdiv), function(i, obj){
							arr[i] = obj.node;
						});
						args.change(arr);
					}
				}
			});
			
			//点击页面其他部分隐藏列表框
			$(document).click(function(){
				set.hide();
			});
			
			//窗体大小改变时重置大小及位置
			$(window).resize(function(){
				//重置大小及位置
				set.position();
			});
		});
	};
})(jQuery);
