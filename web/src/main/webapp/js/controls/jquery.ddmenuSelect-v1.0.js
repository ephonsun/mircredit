// JavaScript Document

/**
 * 下拉树形菜单（单选）
**/
(function($){
	var f = {
		builder: function(d, o){ //Build the drop-down menu
			if(!o.id){
				alert('Is not specified id!');
			}else{
				if(o.url){
					$.ajax({
						type: o.method,
						url: o.url,
						cache: o.cache,
						dataType: o.format,
						success: function(n){
							f.init(d, o, n);
						},
						error: function(){
							//Create a box
							var b = $('<div class=\'ui-ddmenuSelect\'><div class=\'failed\'>请求失败，请刷新重试！</div></div>');
							$('body').append(b);
							
							//Set the d's object
							d.object = b;
							
							//Set the size and position of the box
							f.size(d);
						}
					});
				}else if(o.nodes){
					f.init(d, o, o.nodes);
				}else{
					alert('Not specify a data source!');
				}
			}
		},
		init: function(d, o, n){
			//Create a box
			var b = $('<div class=\'ui-ddmenuSelect\' style=\'display: none;\'></div>');
			$('body').append(
				b.click(function(e){ 
					e.stopPropagation();
				})
			);
			
			if(n && n.constructor==Array && n.length>0){
				b.html('<ul id=\'' + o.id + '\' class=\'ztree\'></ul>');
				
				//Initialize the tree
				$.fn.zTree.init($('#' + o.id), o.config, n);
				
				//Set the default option
				var t = $('input[type=\'text\']', d), zTree = $.fn.zTree.getZTreeObj(o.id), node = zTree.getNodeByParam('name', t.val());
				if(node){
					zTree.selectNode(node);
					t.val(node.name);
				}
			}else{
				b.html('<div class=\'failed\'>无数据，请刷新重试！</div>');
			}
			
			//Set the d's object
			d.object = b;
			
			//Set the size and position of the box
			f.size(d);
			
			b.css({ 'display': 'block' });
		},
		size: function(d){ //Set the size and position of the box
			if(d.object){
				var offset = $(d).offset(), t = offset.top + $(d).outerHeight(true) - 1, l = offset.left, w = $(d).width();
				
				d.object.css({
					top: t + 'px',
					left: l + 'px',
					width: w + 'px',
                    "z-index" : 99999
				});
			}
		}
	};
	
	$.fn.ddMenuSelect = function(o){
		o = $.extend({}, $.fn.ddMenuSelect.defaults, o);
		
		return this.each(function(){
			var d = this;
			
			//Set the text box is read-only
			$('input[type=\'text\']', d).attr('readonly', 'readonly');
			
			if(o.beforeClick && typeof(o.beforeClick)=='function'){
				o.config.callback.beforeClick = o.beforeClick;
			}
			if(o.onClick && typeof(o.onClick)=='function'){
				o.config.callback.onClick = o.onClick;
			}
			
			//When clicked, pull out the menu.
			$(d).click(function(e){
				if(!d.object){
					f.builder(d, o);
				}
				else{
					var b = d.object;
					if(!b.is(':hidden')){
						b.css({ 'display': 'none' });
					}
					else{
						b.css({ 'display': 'block' });
						f.size(d);
					}
				}
				
				e.stopPropagation();
			});
			
			//Click on the document to hide the box
			$(document).click(function(){
				if(d.object){ d.object.css({ 'display': 'none' }); }
			});
			
			//When the window size is changed, to change the size & position.
			$(window).resize(function(){
				f.size(d);
			});
		});
	};
	
	$.fn.ddMenuSelect.defaults = {
		id: null,
		method: 'POST',
		url: null,
		cache: false,
		format: 'json',
		config: {
			view: {
				showIcon: false,
				expandSpeed: '',
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: null,
				onClick: null
			}
		},
		nodes: [
			{ id: 1, pId: 0, name: '测试总行', icon: '../images/icon/agency.gif', open: true },
			{ id: 2, pId: 1, name: '测试分行', icon: '../images/icon/agency.gif' },
			{ id: 3, pId: 2, name: '测试支行', icon: '../images/icon/agency.gif' },
			{ id: 4, pId: 2, name: '测试支行', icon: '../images/icon/agency.gif' },
			{ id: 5, pId: 1, name: '测试分行', icon: '../images/icon/agency.gif' },
			{ id: 6, pId: 5, name: '测试支行', icon: '../images/icon/agency.gif' },
			{ id: 7, pId: 5, name: '测试支行', icon: '../images/icon/agency.gif' },
			{ id: 8, pId: 1, name: '测试分行', icon: '../images/icon/agency.gif' },
			{ id: 9, pId: 1, name: '测试分行', icon: '../images/icon/agency.gif' },
			{ id: 10, pId: 1, name: '测试分行', icon: '../images/icon/agency.gif' },
			{ id: 11, pId: 1, name: '测试分行', icon: '../images/icon/agency.gif' },
			{ id: 12, pId: 1, name: '测试分行', icon: '../images/icon/agency.gif' },
			{ id: 13, pId: 1, name: '测试分行', icon: '../images/icon/agency.gif' },
			{ id: 14, pId: 1, name: '测试分行', icon: '../images/icon/agency.gif' },
			{ id: 15, pId: 1, name: '测试分行', icon: '../images/icon/agency.gif' }
		],
		beforeClick: null,
		onClick: null
	};
})(jQuery);