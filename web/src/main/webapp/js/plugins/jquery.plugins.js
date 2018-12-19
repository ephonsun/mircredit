// JavaScript Document

// 控件的相关操作
var Controls = {};
// 隐藏
Controls.hide = function(){
	//隐藏下拉复选框的选项列表
	$('div.combobox').css('display', 'none');
	
	//隐藏下拉单选框的选项列表
	$('div.ui-select').each(function(){
		var zIdx = zIdx || 0;
		$(this).css('z-index', zIdx).find('div').html('').css('display', 'none');
	});
};


function reinit(obj){
	var it = $(obj);
	
	if(it.is(':hidden')){
		if($("#tabs .tabs-list").find("li[to='" + it.index() + "']").length > 0) return false;
		
		var html = $("<li to=\"" + it.index() + "\">" + it.find("label").text() + "</li>");
		
		html.appendTo($(".tabs-list")).hover(
			function(){ $(this).css("background-color", "#ebebeb"); },
			function(){ $(this).removeAttr("style"); }
		).click(function(){
			$("#tabs .tabs-items").find("li").eq($(this).attr("to")).show().click();
			if($(this).siblings().size()==0){  }
			$(this).parent().hide().end().remove();
		});
		
		it.hide();
		$("#tabs .tabs-pages").find("div.tabs-page").eq(it.index()).hide();
	}else{
		$("#tabs .tabs-list").find("li[to='" + it.index() + "']").remove();
	}
}

(function($){
	$.fn.bgiframe = ($.browser.msie && /msie 6\.0/i.test(navigator.userAgent) ? function(s){
		s = $.extend({
			top     : 'auto', // auto == .currentStyle.borderTopWidth
			left    : 'auto', // auto == .currentStyle.borderLeftWidth
			width   : 'auto', // auto == offsetWidth
			height  : 'auto', // auto == offsetHeight
			opacity : true,
			src     : 'javascript:false;'
		}, s);
		var html = '<iframe class="bgiframe" frameborder="0" tabindex="-1" src="' + s.src + '"' +
				   'style="display: block; position: absolute; z-index: -1;' +
				   (s.opacity !== false?'filter:Alpha(Opacity=\'0\');':'') +
				   'top: ' + (s.top=='auto'?'expression(((parseInt(this.parentNode.currentStyle.borderTopWidth)||0)*-1)+\'px\')':prop(s.top)) + ';' +
				   'left: ' + (s.left=='auto'?'expression(((parseInt(this.parentNode.currentStyle.borderLeftWidth)||0)*-1)+\'px\')':prop(s.left)) + ';' +
				   'width: ' + (s.width=='auto'?'expression(this.parentNode.offsetWidth+\'px\')':prop(s.width)) + ';' +
				   'height: ' + (s.height=='auto'?'expression(this.parentNode.offsetHeight-5+\'px\')':prop(s.height)) + ';' +
				   '" />';
		return this.each(function(){
			if ($(this).children('iframe.bgiframe').length === 0){
				this.insertBefore(document.createElement(html), this.firstChild);
			}
		});
	} : function(){ return this; });
	
	// old alias
	$.fn.bgIframe = $.fn.bgiframe;
	
	function prop(n) {
		return n && n.constructor === Number ? n + 'px' : n;
	}
})(jQuery);


/**
 * 下拉选择框
**/
(function($){
	var selectfun = {
		html: function(){
			var d = '<div class=\'dtxt2 ui-select\'>'
					+ '<input type=\'text\' readonly=\'readonly\' class=\'dtxt2-txt\' />'
					+ '<i class=\'dtxt2-ddl\'></i>'
					+ '<div style=\'position: absolute; top: 22px; left: -1px; right: -1px; display: none; width: 100%; border: 1px solid #b1b1b1; background-color: white; overflow-x: hidden; overflow-y: auto;\'></div>'
					+ '</div>';
			
			return $(d);
		},
		create: function(ele, o){
			var $this = this;
			
			if(!ele.object){
				var zindex = $(ele).css('z-index'), d = this.html();
				
				$(ele).before(d);
				
				//
				if(ele.clearFlag){
					d.attr('clearFlag', ele.clearFlag);
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
				
				//validator
				if(ele.rule){
					$('input[type=\'text\']', d).attr('rule', ele.rule);
					$(ele).removeAttr('rule');
				}
				if(ele.fun){
					$('input[type=\'text\']', d).attr('fun', ele.fun);
					$(ele).removeAttr('fun');
				}
				if(ele.url){
					$('input[type=\'text\']', d).attr('url', ele.url);
					$(ele).removeAttr('url');
				}
				if(ele.tips){
					$('input[type=\'text\']', d).attr('tips', ele.tips);
					$(ele).removeAttr('tips');
				}
				
				//value
				$('input[type=\'text\']', d).val($(':selected', ele).text());
				
				//event click
				d.click(function(e){
					$('.atree, .btree, .ui-subtree, .ui-agetree, .b-bomb-box1').hide();
					
					if(!$('div', this).is(':hidden')){
						$this.clear($(this), zindex);
					}
					else{
						//隐藏其他UI控件
						Controls.hide();
						
						if(o.beforeClick && typeof(o.beforeClick)=='function'){
							o.beforeClick(ele);
						}
						
						$this.init($(this), ele, o);
					}
					
					e.stopPropagation();
				});
				
				$(document).click(function(){
					$this.clear(d, zindex);
				});
				
				$(ele).wrap('<div style=\'display: none;\'></div>');
				
				ele.object = d;
			}
			else{
				//value
				$('input[type=\'text\']', ele.object).val($(':selected', ele).text());
			}
		},
		init: function(d, ele, o){
			var $this = this;
			
			if($('option', ele).length > o.scroll){
				$('div', d).height(20 * o.scroll);
			}
			else{
				$('div', d).height('auto');
			}
			
			
			var ul = $('<ul></ul>');
			
			$('option', ele).each(function(i){
				var opt = $('<li' + (this.disabled ? ' disabled=\'disabled\'' : '') + '><label style=\'display: block; height: 22px; padding: 0px 1px; overflow: hidden; line-height: 22px; white-space: nowrap; text-align: left;\'></label></li>');
				opt.find('label').text($(this).text());
				opt.find('label').attr('title',$(this).text());/*add by wuxj 2013-3-5*/

				ul.append(opt);
			});
			
			$('div', d).append(ul);
			
			//$('div', d).html('<ul>' + option.join('') + '</ul>');
			
			$('div ul li', d).click(function(e){
				if(this.disabled) return false;
				
				if(o.optionClick && typeof(o.optionClick)=='function'){
					if(!o.optionClick(ele, this)){
						$this.clear(d, 0);
						return false;
					}
				}
				
				//根据当前点击项的索引更新原始select标记选择项
				ele.selectedIndex = $('div ul li', d).index(this);
				
				//触发原始select标记的change事件
				$(ele).change();
				
				//文本框赋值
				$('input[type=\'text\']', d).val($('option:eq(' + ele.selectedIndex + ')', ele).text()).focus().blur();
				
				$this.clear(d, 0);
				
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
			
			$('div', d).bgiframe();
			
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
			selectfun.create(this, o);
		});
	};
	
	$.fn.select.defaults = {
		scroll: 12,
		beforeClick: null,
		optionClick: null
	};
})(jQuery);


/**
 * 下拉复选框
**/
(function($){
	var f = {
		html: function(o){
			var d = '<div class=\'combobox\' style=\'position: absolute; display: none; border: 1px solid #b1b1b1; background-color: white;\'></div>';
			
			return $(d);
		},
		button: function(){
			var b = '<div class=\'buttons\' style=\'border-top: 1px solid #ccc; padding: 4px; overflow: hidden;\'>'
				  + '<input type=\'button\' class=\'all\' style=\'cursor: pointer; height: 24px; border: 1px solid #ccc; background-color: white; margin-right: 4px; padding: 0px 5px; -padding: 0; line-height: 24px; -line-height: 20px; font-size: 12px; font-family: Microsoft Yahei;\' value=\'全选\' />'
				  + '<input type=\'button\' class=\'none\' style=\'cursor: pointer; height: 24px; border: 1px solid #ccc; background-color: white; margin-right: 4px; padding: 0px 5px; -padding: 0; line-height: 24px; -line-height: 20px; font-size: 12px; font-family: Microsoft Yahei;\' value=\'全不选\' />'
				  + '<input type=\'button\' class=\'counter\' style=\'cursor: pointer; height: 24px; border: 1px solid #ccc; background-color: white; line-height: 24px; -line-height: 20px; padding: 0px 5px; -padding: 0; font-size: 12px; font-family: Microsoft Yahei;\' value=\'反选\' />'
				  + '</div>';
			
			return b;
		},
		builder: function(s, o){
			var $f = this, d = $f.html();
			
			if(o.button){
				d.html($f.button());
			}
			
			if(o.array.constructor==Array && o.array.length>0){
				d.prepend('<div class=\'options\' style=\'position: relative; overflow: auto;\'><ul style=\'position: relative; list-style: none; margin: 0; padding: 0;\'></ul></div>');
				
				var items = [];
				for(var i in o.array){
					if(!o.array[i])continue;
					
					items.push('<li style=\'position: relative; height: 24px; background-color: white; line-height: 24px; color: black;\'><input type=\'checkbox\' value=\'' + o.array[i] + '\' style=\'position: absolute; top: 4px; left: 4px; width: 15px; height: 15px; margin: 0; padding: 0;\' /><label style=\'display: block; margin-left: 22px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;\'>' + o.array[i] + '</label></li>');
					
					if(i >= o.scroll){
						$('div.options', d).height(24 * o.scroll);
					}
				}
				$('div.options ul', d).html(items.join(''));
			}
			
			$('body').append(d);
			
			$f.handler(s, d, o);
		},
		handler: function(s, d, o){
			var $f = this;
			
			if($('div.options ul li', d).length > 0){
				$('div.options ul li', d).hover(
					function(){ $(this).css({ 'background-color': '#4c64a6', 'color': 'white' }); },
					function(){ $(this).css({ 'background-color': 'white', 'color': 'black' }); }
				).click(function(e){
					e.stopPropagation();
				});
				
				$('div.options ul li :checkbox', d).click(function(){
					if(o.optionClick != null && typeof(o.optionClick)=='function'){
						o.optionClick($('div.options ul li :checkbox', d), this);
					}
					
					$f.setval(s, d);
				});
			}
			
			if(o.button){
				//全选
				$('.all', d).click(function(e){
					$('div.options :checkbox', d).attr('checked', 'checked');
					
					$f.setval(s, d);
					
					e.stopPropagation();
				});
				//全不选
				$('.none', d).click(function(e){
					$('div.options :checkbox', d).removeAttr('checked');
					
					$f.setval(s, d);
					
					e.stopPropagation();
				});
				//反选
				$('.counter', d).click(function(e){
					$('div.options :checkbox', d).each(function(){
						if(this.checked){
							$(this).removeAttr('checked');
						}
						else{
							$(this).attr('checked', 'checked');
						}
					});
					
					$f.setval(s, d);
					
					e.stopPropagation();
				});
			}
			
			$(s).click(function(e){
				if(d.is(':hidden')){
					$('.atree, .btree, .ui-subtree, .ui-agetree, .combobox').hide();
					
					//隐藏其他UI控件
					Controls.hide();
					
					if(($(s).offset().top + $(s).outerHeight() - 1 + d.outerHeight()) > $(document).height()){
						d.css({
							'top': $(s).offset().top - d.outerHeight() + 1 + 'px',
							'left': $(s).offset().left + 'px',
							'width': $(s).width() + 'px'
						}).show();
					}
					else{
						d.css({
							'top': $(s).offset().top + $(s).outerHeight() - 1 + 'px',
							'left': $(s).offset().left + 'px',
							'width': $(s).width() + 'px'
						}).show();
					}
				}
				else{
					d.hide();
				}
				
				$f.init(s, d);
				
				e.stopPropagation();
			});
			
			$(document).click(function(){
				d.hide();
			});
			
			$(window).resize(function(){
				d.css({
					'top': $(s).offset().top + $(s).outerHeight() - 1 + 'px',
					'left': $(s).offset().left + 'px',
					'width': $(s).width() + 'px'
				});
			});
		},
		init: function(s, d){
			if($(':text', s).val() != ''){
				var a = $(':text', s).val().split(',');
				for(var i in a){
					$('div.options :checkbox[value=\'' + a[i] + '\']', d).attr('checked', 'checked');
				}
			}else{
				$('li', d).find(':checkbox').removeAttr('checked');
			}
		},
		setval: function(s, d){
			var v = [];
			$('div.options ul li :checked', d).each(function(i){
				v[i] = $(this).val();
			});
			$(':text', s).val(v.join(','));
		}
	};
	
	$.fn.combobox = function(o){
		o = $.extend({}, $.fn.combobox.defaults, o);
		
		return this.each(function(){
			$(':text', this).attr('readonly', 'readonly');
			
			if(!this.object){
				f.builder(this, o);
			}
			else{
				
			}
		});
	};
	
	$.fn.combobox.defaults = {
		button: true,
		array: [],
		optionClick: null,
		scroll: 10
	};
})(jQuery);


/**
 * 内部页卡一
**/
(function($){
	var etabsfun = {
		head: function(css, obj){
			return $('.' + css, obj);
		},
		body: function(css, obj){
			return $('.' + css, obj);
		},
		show: function(json){
			json.h.children('li:eq(' + json.n + ')').addClass(json.active).siblings().removeAttr('class');
			json.b.children('div:eq(' + json.n + ')').show().siblings().hide();
		}
	};
	
	$.fn.easytabs = function(opts){
		opts = $.extend({}, $.fn.easytabs.defaults, opts);
		
		return this.each(function(){
			var h = etabsfun.head(opts.head, this), b = etabsfun.body(opts.body, this);
			
			$('li', h).bind(opts.event, function(){
				if(!$(this).hasClass(opts.active)){
					if(opts.beforeOnclick && typeof(opts.beforeOnclick) == 'function' ){
						var t = $('li.' + opts.active, h), d = b.children('div:eq(' + $('li', h).index(t) + ')');
						
						if(!opts.beforeOnclick(t, d)){
							return false;
						}
					}
					
					var i = $('li', h).index(this);
					etabsfun.show({ h: h, b: b, n: i, active: opts.active });
				}
				
				if(opts.onclick && typeof(opts.onclick) == 'function'){
					opts.onclick($(this), b.children('div:eq(' + $('li', h).index(this) + ')'));
				}
			});

            if(opts.btnClick && typeof(opts.btnClick) == 'function'){
                opts.btnClick();
            }

            if(opts.extPlguin && typeof(opts.extPlguin) == 'function'){
                opts.extPlguin();
            };

			etabsfun.show({ h: h, b: b, n: opts.index, active: opts.active });
		});
	};
	
	$.fn.easytabs.defaults = {
		'index': 0,
		'head': 'easy-tabs-header',
		'active': 'easy-tabs-active',
		'body': 'easy-tabs-pages',
		'event': 'click',
		'beforeOnclick': null,
		'onclick': null,
        extPlguin:null,
        btnClick:null
	};
})(jQuery);


(function($){
	$.fn.extend({
		"table": function(options){
			options = $.extend({
				odd: "odd",
				even: "even",
				hover: "hover",
				selected: "selected"
			}, options);
			
			var $head = this.find("thead");
			var $body = this.find("tbody");
			var $all = function(){
				return $head.find("th").find("input[type='checkbox'][nm='chkall']");
			};
			var $one = function(){
				return $body.find("td").find("input[type='checkbox'][nm='chkone'][disabled!='disabled']");
			};
			var $checked = function(){
				return $body.find("td").find("input[type='checkbox'][nm='chkone']:checked");
			};
			
			$all().click(function(){
				var flag = $(this).attr("checked");
				if(flag){
					$one().each(function(){
						$(this).attr("checked", true);
						$(this).parent().parent().addClass(options.selected);
					});
				}
				else{
					$one().each(function(){
						$(this).attr("checked", false);
						$(this).parent().parent().removeClass(options.selected);
					});
				}
			});
			
			var discolor = function(){
				$body.find("tr:odd").addClass(options.odd).end().find("tr:even").addClass(options.even);
			};
			discolor();
			
			$body.find("tr").each(function(){
				var tr = $(this);
				
				tr.hover(
					function(){$(this).addClass(options.hover);},
					function(){$(this).removeClass(options.hover);}
				);
				
				tr.find("input[type='checkbox'][nm='chkone']").click(function(){
					var flag = $(this).attr("checked");
					if(flag){
						tr.addClass(options.selected);
						if($one().size()==$checked().size()){
							$all().attr("checked", true);
						}
					}
					else{
						$all().attr("checked", false);
						tr.removeClass(options.selected);
					}
				});
				
				tr.find("a[nm='tr-remove']").click(function(){
					tr.remove();
				});
				tr.find("a[nm='tr-up']").click(function(){
					if(tr.prev().size()!=0){
						tr.prev().before(tr);
						discolor();
					}
				});
				tr.find("a[nm='tr-down']").click(function(){
					if(tr.next().size()!=0){
						tr.next().after(tr);
						discolor();
					}
				});
			});
			
			return this;
		},
        "tabs": function(options){
            options = $.extend({
                index: 0,
                items: "tabs-items",
                pages: "tabs-pages",
                page: "tabs-page",
                opentab: "tabs-open",
                list: "tabs-list",
                active: "tabs-active",
                event: "click",
                onClick: null,
                parentForm: null,
                validate: false,
                extPlguin:null,
                btnClick:null
            }, options);

            var tab = this.find("." + options.items).find("li");
            var hidetab = this.find("." + options.items).find("li[init!='false']:hidden");
            var page = this.find("." + options.pages).find("div." + options.page);
            var btn = this.find("." + options.opentab);
            var list = this.find("." + options.list);
			
            if(hidetab.length == 0){
            	btn.attr("disabled", true);
            }else{
            	hidetab.each(function(){
	                var i = $(this).index();
	                var txt = $(this).find("label").text();
	                var $e = $("<li to=" + i + " title=" + txt + ">" + txt + "</li>");
	                $e.appendTo(list).hover(
	                    function(){ $(this).css("background-color", "#ebebeb"); },
	                    function(){ $(this).removeAttr("style"); }
	                ).click(function(){
	                        tab.eq($(this).attr("to")).show().click();
	                        if($(this).siblings().size()==0){ btn.attr("disabled", true); }
	                        $(this).parents('.wrap-div').hide().end().remove();
	
	                        if(options.extPlguin && typeof(options.extPlguin) == 'function'){
	                            options.extPlguin();
	                        };
	                    });
	                $(this).hide();
	                page.eq(i).hide();
	            });
            }
            
            tab.eq(options.index).addClass(options.active);
            page.eq(options.index).show();

            list.bgIframe();

            //在ul外面添加一个div使起出现滚动条
            var clientDiv = '<div class="wrap-div"></div>';
            list.wrapAll(clientDiv);

            tab.bind(options.event, function(){
                if(options.validate != false && options.parentForm != null){
                    var form = $("#" + options.parentForm);
                    var flag = form.validationEngine("validate");
                    var i = $("." + options.active).index();
                    page.eq(i).attr("validate", flag);
                }

                if($(this).attr("class") != options.active){
                    $(this).addClass(options.active).siblings("." + options.active).removeAttr("class");
                    page.hide().eq($(this).index()).show();
                }

                if(options.onClick && typeof(options.onClick)=='function'){
                    options.onClick();
                }
            });
            /*
             btn.bind("click", function(){
             if(list.children().size()!=0){
             if(list.is(':hidden')){
             list.show();
             }
             else{
             list.hide();
             }
             }else btn.attr("disabled", true);
             });
             */
            btn.bind("click", function(){
                if(list.children().size()!=0){
                    if(list.parent().is(':hidden')){
                        list.parent().show();
                        list.show();
                    }
                    else{
                        list.parent().hide();
                        list.hide();
                    }
                }else btn.attr("disabled", true);
            });

            if($.browser.msie && $.browser.version<7.0){
                $(window).resize(function(){
                    //list.hide();
                    list.parent().hide();
                })
            }

            if(options.btnClick && typeof(options.btnClick) == 'function'){
                options.btnClick();
            };

            return this;
        },
		"tips": function(options){
			options = $.extend({
				msg: "please enter some content"
			}, options);
			
			var $this = $(this);
			
			if($this.val() == ""){
				$this.css({ color: "#bdbdbd" }).val(options.msg);
			}
			
			$this.focus(function(){
				if($this.val() == options.msg){
					$this.css({ color: "#000000" }).val("");
				}
				else{
					//$this.select();
				}
			});
			$this.blur(function(){
				if($this.val() == ""){
					$this.css({ color: "#bdbdbd" }).val(options.msg);
				}
				//add by wuxj 2013-6-19 解决ie6中,点击窗口滚动条时,文本框触发blur事件,但光标仍在文本框中问题
				if(jQuery.browser.msie && jQuery.browser.version<7.0){
					$('body').trigger('focus');
				}
			});
			//add by wuxj 2013-6-19文本框点击时,获取焦点
			if(jQuery.browser.msie && jQuery.browser.version<7.0){
				$('input,textarea').click(function(){
					$(this).trigger('focus');
				});
			}
			return this;
		}
	});
})(jQuery);

$(function(){
	$("select[render='select']").each(function(){
		$(this).select();
	});
});

(function($){
	$.fn.selecttbl = function(o){
		return this.each(function(){
			if(this.exist) return false; //return if already exist
			
			o = $.extend({
				method: "POST",
				format: 'json',
				url: false,
				param: [],
				data: [{id: 1, name: "a"}, {id: 2, name: "b"}, {id: 3, name: "c"}, {id: 4, name: "d"}, {id: 4, name: "d"}, {id: 4, name: "d"}, {id: 4, name: "d"}, {id: 4, name: "d"}, {id: 4, name: "d"}, {id: 4, name: "d"}, {id: 4, name: "d"}, {id: 4, name: "d"}, {id: 4, name: "d"}, {id: 4, name: "d"}, {id: 4, name: "d"}, {id: 4, name: "d"}, {id: 4, name: "d"}],
				showchk: true,
				filter: true,
				ok: true,
				okHandler: function(){},
				oktxt: "确 定",
				close: true,
				closeHandler: function(){},
				closetxt: "取 消",
				rowHandler: function(){},
				text: "name",
				value: "id"
			}, o);
			
			var c = {
				clearhtml: function(){
					c.box.hide().parent().css({
						zIndex: 1
					});
				},
				loaddata: function(){
					var url;
					if(typeof o.url == 'string'){
						url = o.url;
					}
					else if(typeof o.url == 'function'){
						url = o.url();
					}
					
					$.ajax({
						type: o.method,
						url: url,
						data: o.param,
						dataType: o.format,
						success: function(d){
							c.populate(d);
						},
						error: function(){
							c.populate(o.data);
							//c.mdiv.html('<div class=\'error\'>出错啦！</div>');
						}
					});
				},
				populate: function(d){
					if(o.format == 'json'){
						if(d){
							//var data = $.parseJSON(d);
							var data = d;
							c.mdiv.html('<table><tbody></tbody></table>');
							if(data.length > 10){
								c.mdiv.height(250);
							}
							$.each(data, function(i){
								var row = $('<tr val=\'' + data[i][o.value] + '\'></tr>');
								if(o.showchk){
									row.append('<td width=\'50\'><input type=\'checkbox\' /></td>');
									$('td:first :checkbox', row).click(function(e){
										if(this.checked){
											row.addClass('checked');
										}
										else{
											row.removeClass('checked');
										}
										e.stopPropagation();
									});
								}
								$.each(data[i], function(j){
									row.append('<td><div title=\'' + data[i][j] + '\' rel=\'' + j + '\'>' + data[i][j] + '</div></td>');
								});
								row.click(function(){
									if(o.showchk){
										if($(this).hasClass('checked')){
											$(this).removeClass('checked');
											$('td:first :checkbox', this).removeAttr('checked');
										}
										else{
											$(this).addClass('checked');
											$('td:first :checkbox', this).attr('checked', 'checked');
										}
									}
									else{
										$(this).addClass('checked').siblings().removeClass('checked');
									}
									
								});
								$('tbody:first', c.mdiv).append(row);
							});
						}
						else{
							c.mdiv.html('<div class=\'error\'>暂无数据！</div>');
						}
					}
				}
			};
			c.box = $('<div class=\'x-ui-tblslt\'></div>');
			c.mdiv = $('<div class=\'x-ui-tblslt-main\'></div>');
			c.bdiv = $('<div class=\'x-ui-tblslt-button\'></div>');
			
			var s = this, h = $(s).height(), l = -1, r = -1;
			c.box.css({
				top: h + 'px',
				left: l + 'px',
				right: r + 'px',
				zIndex: 11111
			});
			$(s).append(c.box.append(c.mdiv));
			
			if(o.ok || o.close){
				c.bdiv.html('<div class=\'x-ui-tblslt-button-inner\'></div>');
				c.mdiv.after(c.bdiv);
				if(o.ok){
					$('div:first', c.bdiv).append('<div class=\'button ok\'>' + o.oktxt + '</div>');
					$('div.ok', c.bdiv).click(function(){
						if($('table', c.mdiv).length != 0){
							var atxt = [], aval = [];
							$('tr.checked').each(function(i){
								atxt[i] = $('div[rel=\'' + o.text + '\']', this).text();
								aval[i] = $(this).attr('val');
							});
							var txt = atxt.join(','), val = aval.join(',');
							$('input[type=\'text\']:first', s).val(txt);
							$('input[type=\'hidden\']:first', s).val(val);
						}
						
						if(o.okHandler){
							o.okHandler();
						}
						c.clearhtml();
					});
				}
				if(o.close){
					$('div:first', c.bdiv).append('<div class=\'button close\'>' + o.closetxt + '</div>');
					$('div.close', c.bdiv).click(function(){
						if(o.closeHandler){
							o.closeHandler();
						}
						c.clearhtml();
					});
				}
			}
			
			
			
			$(':text:first, span:first', s).click(function(e){
				$('.x-ui-tblslt, .selectbox').hide().parent().css('z-index', '1');
				
				if(typeof s.exist == 'undefined'){
					c.loaddata();
					s.exist = true;
				}
				
				if(c.box.is(':hidden')){
					c.box.show().parent().css("z-index", c.box.css("z-index")-1);
					
					//if b.top + b.height > window.height
					if(c.box.offset().top + c.box.outerHeight(true) > $(window).height()){
						c.box.css({top: -c.box.outerHeight(true)});
					}
				}
				else{
					c.box.hide();
				}
				
				e.stopPropagation();
			});
			
			c.box.click(function(e){ e.stopPropagation(); });
			$(document).click(function(){
				if(typeof c.box != 'undefined'){
					c.clearhtml();
				}
			});
		});
	};
})(jQuery);



(function($){
	$.fn.extend({
		"treebox": function(o){
			o = $.extend({
				method: "POST",
				format: 'json',
				url: false,
				param: [],
				showchk: true,
				setting: {
					check: {
						enable: true,
						chkStyle: "checkbox",
						chkboxType: {"Y": "", "N": ""}
					},
					view: {
						dblClickExpand: false,
						showIcon: false
					},
					data: {
						simpleData: {
							enable: true
						}
					},
					callback: {
						beforeCheck: beforeCheck,
						onCheck: check
					}
				},
				nodes: null,
				ok: true,
				okHandler: function(){},
				oktxt: "确 定",
				close: true,
				closeHandler: function(){},
				closetxt: "取 消"
			}, o);
			
			var s = this, b, mz, tree;
			
			var id = "tree-" + s.attr("id"), t = s.height(), l = -1, r = -1;
			b = $("<div class=\"ui-tree-box\" style=\"top: " + t + "px; left: " + l + "px; right: " + r + "px; z-index: 44444;\"></div>");
			mz = $("<div class=\"ui-tree-box-main\" style=\"height: 250px;\"><ul id=\"" + id + "\" class=\"ztree\"></ul></div>");
			s.append(b.append(mz));
			if(typeof bz == "undefined" && (o.ok || o.close)){
				if(o.showchk){
					var bz = $("<div class=\"ui-tree-box-button\"></div>");
					mz.after(bz);
					
					var chk = $("<div style=\"float: left; height: 24px; line-height: 24px;\"><input type=\"checkbox\" class=\"chk\" checked=\"checked\" />&nbsp;<label>选取时包含下属机构</label></div>");
					bz.append(chk);
				
					if(o.ok){
						var ok = $("<input type=\"button\" class=\"btn1 button\" value=\"" + o.oktxt + "\" />");
						ok.appendTo(bz).bind("click", function(){
							clearhtml();
						});
					}
					if(o.close){
						var cl = $("<input type=\"button\" class=\"btn1 button\" value=\"" + o.closetxt + "\" />");
						cl.appendTo(bz).bind("click", function(){
							clearhtml();
						});
					}
				}
			}
			
			function beforeCheck(treeId, treeNode){
				if(chk.children(':checkbox').attr('checked')){
					if($('#' + treeNode.tId + '_check').hasClass('checkbox_false_full_focus') || $('#' + treeNode.tId + '_check').hasClass('checkbox_false_part_focus')){
						$('#' + treeNode.tId + '_check').parent().find('span.checkbox_false_full, span.checkbox_false_part').click();
					}
					else if($('#' + treeNode.tId + '_check').hasClass('checkbox_true_full_focus') || $('#' + treeNode.tId + '_check').hasClass('checkbox_true_part_focus')){
						$('#' + treeNode.tId + '_check').parent().find('span.checkbox_true_full, span.checkbox_true_part').click();
					}
				}
			}
			function beforeClick(treeId, treeNode){
				var zTree = $.fn.zTree.getZTreeObj(id);
				zTree.checkNode(treeNode, !treeNode.checked, null, true);
				return false;
			}
			function check(e, treeId, treeNode){
				var t = $.fn.zTree.getZTreeObj(id);
				var nodes = t.getCheckedNodes(true), v1 = "", v2 = "";
				
				for(var i=0, l=nodes.length; i<l; i++){
					v1 += nodes[i].name + ",";
					v2 += nodes[i].id + ",";
				}
				if(v1.length>0 && v2.length>0){
					v1 = v1.substring(0, v1.length - 1);
					v2 = v2.substring(0, v2.length - 1);
				}
				s.children(":text").val(v1);
				s.children(":hidden").val(v2);
			}
			function click(){
				var zTree = $.fn.zTree.getZTreeObj(id),
				nodes = zTree.getSelectedNodes(),
				v1 = "", v2 = "";
				
				nodes.sort(function compare(a,b){return a.id-b.id;});
				for (var i=0, l=nodes.length; i<l; i++) {
					v1 += nodes[i].name + ",";
					v2 += nodes[i].id + ",";
				}
				if(v1.length>0 && v2.length>0){
					v1 = v1.substring(0, v1.length - 1);
					v2 = v2.substring(0, v2.length - 1);
				}
				s.children(":text").val(v1);
				s.children(":hidden").val(v2);
				
				clearhtml();
			}
			
			
			function clearhtml(){
				b.hide().parent().css("z-index", "1");
			}
			
			b.bind("click", function(e){ e.stopPropagation(); });
			$(document).bind("click", function(){
				if(typeof b != "undefined"){ clearhtml(); }		
			});
			
			s.children(":text, span").bind("click", function(e){
				if(typeof s.exist == 'undefined'){
					if(!o.showchk){
						o.setting.check.enable = false;
						o.setting.callback.onClick = click;
					}
					
					var url;
					if(typeof o.url == 'string'){
						url = o.url;
					}
					else if(typeof o.url == 'function'){
						url = o.url();
					}
					
					$.ajax({
						type: o.method,
						url: url,
						data: o.param,
						dataType: o.format,
						success: function(data){
							$('div.error', mz).remove();
							
							if(data.length!=0){
								//'[{key:value},{key:value},{key:value}]'
								var nodes = data;
								
								$.fn.zTree.init(mz.children("ul"), o.setting, nodes);
							}else{
								mz.append("<div class=\"error\">暂无数据！</div>");
							}
						},
						error: function(){
							if(o.nodes.length!=0){
								//'[{key:value},{key:value},{key:value}]'
								var nodes = o.nodes;
								
								$.fn.zTree.init(mz.children("ul"), o.setting, nodes);
							}else{
								mz.append("<div class=\"error\">暂无数据！</div>");
							}
						}
					});
					
					s.exist = true;
				}
				
				if(b.is(":hidden")){
					$('.selectbox').hide().parent().css("z-index", "1");
					b.show().parent().css("z-index", b.css("z-index")-1);
				}else{
					b.hide();
				}
				e.stopPropagation();
			});
		}
	});
})(jQuery);


/**
 * 树 一
**/
(function(){
	$.fn.atree = function(args){
		return this.each(function(){
			args = $.extend({
				method: "POST",
				format: 'json',
				url: null,
				setting: {
					view: {
						showIcon: true,
						dblClickExpand: false
					},
					data: {
						simpleData: {
							enable: true
						}
					},
					callback: {
						beforeClick: null,
						onClick: null,
						beforeCheck: null,
						onCheck: null
					}
				},
				nodes: null,
				ok: true,
				okHandler: null,
				oktxt: "确定",
				close: true,
				closeHandler: null,
				closetxt: "取消"
			}, args);
			
			var s = $(this);
			
			var fn = {
				hide: function(){
					this.bdiv.hide();
				},
				builder: function(obj){
					if(args.nodes){
						$('ul', fn.mdiv)[0].id = fn.id;
						$('body').append(fn.bdiv.prepend(fn.mdiv.css(fn.dcss)));
						
						$.fn.zTree.init($('#' + fn.id), args.setting, args.nodes);
						
						var zTree = $.fn.zTree.getZTreeObj(fn.id), node = zTree.getNodeByParam("selected", true);
						
						if(node != null){
							zTree.selectNode(node);
							$('input[type=\'text\']', obj).val(node.name);
						}
					}
					else if(args.url){
						$.ajax({
							type: args.method,
							url: fn.url,
							dataType: args.format,
							success: function(data){
								//'[{key:value},{key:value},{key:value}]'
								if(data && data.length != 0){
									args.nodes = (typeof(data)=='string') ? $.parseJSON(data) : data;
									
									$('ul', fn.mdiv)[0].id = fn.id;
									$('body').append(fn.bdiv.prepend(fn.mdiv.css(fn.dcss)));
									
									$.fn.zTree.init($('#' + fn.id), args.setting, args.nodes);
									
									var zTree = $.fn.zTree.getZTreeObj(fn.id), node = zTree.getNodeByParam("selected", true);
									
									if(node != null){
										zTree.selectNode(node);
										$('input[type=\'text\']', obj).val(node.name);
									}
	
								}
								else{
									fn.ediv.css(fn.ecss).text('暂无数据!');
									fn.bdiv.prepend(fn.ediv);
									$('body').append(fn.bdiv);
								}
							},
							error: function(){
								fn.ediv.css(fn.ecss).text('出错啦!');
								fn.bdiv.prepend(fn.ediv);
								$('body').append(fn.bdiv);
							}
						});
					}	
				},
				top: function(obj){
					return obj.offset().top + obj.outerHeight(true) - 1;
				},
				left: function(obj){
					return obj.offset().left;
				},
				width: function(obj){
					return ($.browser.msie && $.browser.version<7.0) ? (obj.outerWidth(true) - 2) : (obj.outerWidth(true) - 3);
				}
			};
			fn.id = 'at' + this.id;
			fn.url = null;
			fn.bdiv = $('<div class=\'atree\'></div>');
			fn.mdiv = $('<div><ul class=\'ztree\'></ul></div>'),
			fn.ediv = $('<div></div>');
			fn.btndiv = $('<div></div>');
			fn.btnok = $('<input type=\'button\' class=\'btn1\' />');
			fn.btnclose = $('<input type=\'button\' class=\'btn1\' />');
			fn.dcss = { height: '186px', border: '1px solid #f1f1f1', backgroundColor: 'white', margin: '5px', overflow: 'auto' };
			fn.ecss = { height: '100px', border: '1px solid #f1f1f1', backgroundColor: 'white', margin: '5px', lineHeight: '100px', textAlign: 'center', color: 'red', fontSize: '13px' };
			
			fn.bdiv.css({
				position: 'absolute',
				zIndex: 11111,
				display: 'none',
				border: '1px solid #ccc',
				backgroundColor: '#f7f7f7'
			});
			
			if(args.beforeClick && typeof(args.beforeClick)=='function'){
				args.setting.callback.beforeClick = args.beforeClick;
			}
			if(args.onClick && typeof(args.onClick)=='function'){
				args.setting.callback.onClick = args.onClick;
			}
			if(args.beforeCheck && typeof(args.beforeCheck)=='function'){
				args.setting.callback.beforeCheck = args.beforeCheck;
			}
			if(args.onCheck && typeof(args.onCheck)=='function'){
				args.setting.callback.onCheck = args.onCheck;
			}
			
			if(args.url && typeof(args.url)=='string'){
				fn.url = args.url;
			}
			else if(args.url && typeof(args.url)=='function'){
				fn.url = args.url();
			}
			
			//请求数据，创建HTML结构
			fn.builder(s);
			
			if(args.ok || args.close){
				fn.btndiv.css({
					borderTop: '1px solid #ccc',
					marginTop: '5px',
					padding: '5px',
					textAlign: 'right'
				});
				
				if(args.ok){
					fn.btnok.css('margin-left', '5px').val(args.oktxt);
					
					fn.btnok.click(function(){
						if(typeof(args.okHandler) == 'function'){
							args.okHandler(fn.id);
						}
						
						fn.hide();
					});
					
					fn.btndiv.append(fn.btnok);
				}
				if(args.close){
					fn.btnclose.css('margin-left', '5px').val(args.closetxt);
					
					fn.btnclose.click(function(){
						if(typeof(args.closeHandler) == 'function'){
							args.closeHandler();
						}
						
						fn.hide();
					});
					
					fn.btndiv.append(fn.btnclose);
				}
				
				fn.bdiv.append(fn.btndiv);
			}
			
			//点击文本框区域显示或隐藏树形浮层
			s.click(function(e){
				$('.atree').css('display', 'none');
				if(fn.bdiv.is(':hidden')){
					fn.bdiv.css({
						top: fn.top(s) + 'px',
						left: fn.left(s) + 'px',
						width: fn.width(s) + 'px'
					}).show();
					
					var zTree = $.fn.zTree.getZTreeObj(fn.id);
					if(zTree != null && zTree.getSelectedNodes() != null){
						var nodes = zTree.getSelectedNodes();
						if($('input[type=\'text\']', s).val() == '' || $('input[type=\'text\']', s).val() == ''){
							if(nodes && nodes.length>0){
								for(var i=0; i<nodes.length; i++){
									zTree.cancelSelectedNode(nodes[i], false);
								}
							}
						}
					}
				}
				else{
					fn.bdiv.hide();
				}
				//阻止事件冒泡到document
				e.stopPropagation();
			});
			
			//阻止事件冒泡到document
			fn.bdiv.click(function(e){
				e.stopPropagation();
			});
			
			//点击页面其他区域隐藏树形浮层
			$(document).click(function(){
				fn.bdiv.hide();
			});
			
			//窗体改变大小时更新树形浮层的位置
			$(window).resize(function(){
				fn.bdiv.css({
					left: fn.left(s) + 'px'
				});
			});
		});
	}
})(jQuery);


/**
 * 树 二
**/
(function(){
	$.fn.btree = function(args){
		return this.each(function(){
			args = $.extend({
				method: "POST",
				format: 'json',
				url: null,
				setting: {
					check: {
						enable: true,
						chkboxType: { "Y": "s", "N": "s" }
					},
					view: {
						showIcon: false,
						dblClickExpand: false
					},
					data: {
						simpleData: {
							enable: true
						}
					},
					callback: {
						beforeClick: null,
						onClick: null,
						beforeCheck: null,
						onCheck: null
					}
				},
				ok: true,
				okHandler: null,
				oktxt: "确定",
				close: true,
				closeHandler: null,
				closetxt: "取消"
			}, args);
			
			var s = $(this);
			
			var fn = {
				hide: function(){
					this.bdiv.hide();
				},
				builder: function(){
					$.ajax({
						type: args.method,
						url: fn.url,
						dataType: args.format,
						success: function(data){
							//'[{key:value},{key:value},{key:value}]'
							if(data && data.length != 0){
								args.nodes = (typeof(data)=='string') ? $.parseJSON(data) : data;
								
								$('ul', fn.mdiv)[0].id = fn.id;
								$('body').append(fn.bdiv.prepend(fn.mdiv.css(fn.dcss)));
								
								$.fn.zTree.init($('#' + fn.id), args.setting, args.nodes);
							}
							else{
								fn.ediv.css(fn.ecss).text('暂无数据!');
								fn.bdiv.prepend(fn.ediv);
								$('body').append(fn.bdiv);
							}
						},
						error: function(){
							fn.ediv.css(fn.ecss).text('出错啦!');
							fn.bdiv.prepend(fn.ediv);
							$('body').append(fn.bdiv);
						}
					});
				},
				top: function(obj){
					return obj.offset().top + obj.outerHeight(true) - 1;
				},
				left: function(obj){
					return obj.offset().left;
				},
				width: function(obj){
					return ($.browser.msie && $.browser.version<7.0) ? (obj.outerWidth(true) - 2) : (obj.outerWidth(true) - 3);
				}
			};
			fn.id = 'bt' + this.id;
			fn.url = null;
			fn.bdiv = $('<div class=\'btree\'></div>');
			fn.mdiv = $('<div><ul class=\'ztree\'></ul></div>'),
			fn.ediv = $('<div></div>');
			fn.btndiv = $('<div></div>');
			fn.btnok = $('<input type=\'button\' class=\'btn1\' />');
			fn.btnclose = $('<input type=\'button\' class=\'btn1\' />');
			fn.dcss = { height: '186px', border: '1px solid #f1f1f1', backgroundColor: 'white', margin: '5px', overflow: 'auto' };
			fn.ecss = { height: '100px', border: '1px solid #f1f1f1', backgroundColor: 'white', margin: '5px', lineHeight: '100px', textAlign: 'center', color: 'red', fontSize: '13px' };
			
			fn.bdiv.css({
				position: 'absolute',
				zIndex: 11111,
				display: 'none',
				border: '1px solid #ccc',
				backgroundColor: '#f7f7f7'
			});
			
			if(args.beforeClick && typeof(args.beforeClick)=='function'){
				args.setting.callback.beforeClick = args.beforeClick;
			}
			if(args.onClick && typeof(args.onClick)=='function'){
				args.setting.callback.onClick = args.onClick;
			}
			if(args.beforeCheck && typeof(args.beforeCheck)=='function'){
				args.setting.callback.beforeCheck = args.beforeCheck;
			}
			if(args.onCheck && typeof(args.onCheck)=='function'){
				args.setting.callback.onCheck = args.onCheck;
			}
			
			if(args.url && typeof(args.url)=='string'){
				fn.url = args.url;
			}
			else if(args.url && typeof(args.url)=='function'){
				fn.url = args.url();
			}
			
			//请求数据，创建HTML结构
			fn.builder();
			
			if(args.ok || args.close){
				fn.btndiv.css({
					borderTop: '1px solid #ccc',
					marginTop: '5px',
					padding: '5px',
					textAlign: 'right'
				});
				
				if(args.ok){
					fn.btnok.css('margin-left', '5px').val(args.oktxt);
					
					fn.btnok.click(function(){
						if(typeof(args.okHandler) == 'function'){
							args.okHandler(fn.id);
						}
						
						fn.hide();
					});
					
					fn.btndiv.append(fn.btnok);
				}
				if(args.close){
					fn.btnclose.css('margin-left', '5px').val(args.closetxt);
					
					fn.btnclose.click(function(){
						if(typeof(args.closeHandler) == 'function'){
							args.closeHandler();
						}
						
						fn.hide();
					});
					
					fn.btndiv.append(fn.btnclose);
				}
				
				fn.bdiv.append(fn.btndiv);
			}
			
			//点击文本框区域显示或隐藏树形浮层
			s.click(function(e){
				if(fn.bdiv.is(':hidden')){
					fn.bdiv.css({
						top: fn.top(s) + 'px',
						left: fn.left(s) + 'px',
						width: fn.width(s) + 'px'
					}).show();
				}
				else{
					fn.bdiv.hide();
				}
				//阻止事件冒泡到document
				e.stopPropagation();
			});
			
			//阻止事件冒泡到document
			fn.bdiv.click(function(e){
				e.stopPropagation();
			});
			
			//点击页面其他区域隐藏树形浮层
			$(document).click(function(){
				fn.bdiv.hide();
			});
			
			//窗体改变大小时更新树形浮层的位置
			$(window).resize(function(){
				fn.bdiv.css({
					left: fn.left(s) + 'px'
				});
			});
		});
	}
})(jQuery);


/**
 * 仿百度搜索检索 （对已加载数据进行检索	list）
**/
(function(){
	$.fn.afilter = function(args){
		return this.each(function(){
			args = $.extend({
				type: 'POST',
				async: false,
				url: '',
				data: '',
				format: 'json',
				cache: false,
				click: null,
				change: null
			}, args);
			
			var set = {
				init: function(){
					$.ajax({
						type: args.type,
						async: args.async,
						url: args.url,
						data: args.data,
						dataType: args.format,
						cache: args.cache,
						success: function(data){
							data = (typeof(data)=='string') ? $.parseJSON(data) : data;
							
							$.each(data, function(i, node){
								if(node){
									var li = $('<li style=\'padding: 0px 2px;\'><label>' + node.text + '</label></li>');
									
									li[0].node = node;
									
									//单击、鼠标划过
									li.click(function(){
										if(args.click && typeof(args.click)=='function'){
											args.click(node);
										}
										else{
											$(':text:first', set.objtxt).val(node.text);
										}
										
										//让文本框重新获得焦点
										$(':text:first', set.objtxt).focus();
										
										//隐藏列表层
										set.hide();
									}).mouseover(function(){
										$('li', set.bdiv).css({
											backgroundColor: 'white',
											color: 'black'
										}).removeAttr('class');
										
										$(this).css({
											backgroundColor: '#4c64a6',
											color: 'white'
										}).attr('class', 'active');
									});
									
									$('ul', set.bdiv).append(li);
								}
							});
							
							var val = $(':text:first', set.objtxt).val();
							if(val != '' && $('li:contains(' + val + ')', set.bdiv).length > 0){
								$('li:contains(' + val + ')', set.bdiv).eq(0).mouseover();
							}
							else{
								$('li:first', set.bdiv).mouseover();
							}
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
				width: function(){
					return this.objtxt.outerWidth(true) - 4 + 'px';
				},
				height: function(){
					return ($('ul', this.bdiv).height() > 240) ? '240px' : 'auto';
				},
				position: function(){
					this.bdiv.css({
						top: set.top(),
						left: set.left(),
						width: set.width(),
						height: set.height()
					});
				},
				scroll: function(){
					var t = $('li.active', this.bdiv).position().top + $('li.active', this.bdiv).outerHeight() - this.bdiv.height() - 1;
					if(t > 0){
						this.bdiv.scrollTop(t);
					}
				}
			};
			set.objtxt = $(this);
			set.bdiv = $('<div style=\'position: absolute; display: none; border: 1px solid #b1b1b1; background-color: white; padding: 1px; overflow: auto;\'><div class=\'none\' style=\'display: none; line-height: 24px; text-align: center; color: red;\'>无符合结果！</div><ul style=\'line-height: 24px;\'></ul></div>');
			
			//装载数据，绘制元素标记
			set.init();
			
			$('body').append(set.bdiv);
			
			//键盘按键弹起
			$(':text:first', set.objtxt).keyup(function(){
				//显示
				set.show();
				
				if($(this).val() != ''){
					//输入框的值不为空时，只显示匹配到的数据，其他的则隐藏掉；如果无符合结果则给出提示
					$('li', set.bdiv).css('display', 'none');
					$('li:contains(' + $(this).val() + ')').css('display', 'block').eq(0).mouseover();
					
					if($('li:visible').length == 0){
						$('div.none', set.bdiv).css('display', 'block');
					}
					else{
						$('div.none', set.bdiv).css('display', 'none');
					}
				}
				else{
					//文本值为空时隐藏'无符合结果'提示层，显示所有数据并触发第一条为激活状态
					$('div.none', set.bdiv).css('display', 'none');
					$('li', set.bdiv).css('display', 'block').eq(0).mouseover();
				}
				
				if(args.change && typeof(args.change)=='function'){
					var arr = [];
					$.each($('li:visible', set.bdiv), function(i, obj){
						arr[i] = obj.node;
					});
					args.change(arr);
				}
				
				//重置大小及位置
				set.position();
			});
			
			//点击三角按钮显示
			$('i:first', set.objtxt).click(function(e){
				if(set.bdiv.is(':hidden')){
					//显示
					set.show();
					//设置大小及位置
					set.position();
					//滚动到激活数据条
					set.scroll();
				}
				
				e.stopPropagation();
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
	}
})(jQuery);


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
				change: null
			}, args);
			
			var set = {
				init: function(){
					var param = (typeof(args.data) == 'function') ? args.data() : args.data;
					
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
								set.mdiv.empty().html('<table style=\'border-collapse: collapse;\'><tbody></tbody></table>');
								
								$.each(data, function(i, node){
									var tr = $('<tr></tr>');
									
									//绑定当前json对象到html标记上
									tr[0].node = node;
									
									$.each($('thead tr th', set.hdiv), function(){
										var td = $('<td style=\'border-right: 1px dashed #e1e1e1; border-bottom: 1px solid #e1e1e1; padding: 2px;\'></td>');
										
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
											td.html('<label>' + node[this.field] + '</label>');
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
			set.bdiv = $('<div style=\'position: absolute; display: none; background-color: white; width:auto;\'></div>');
			if(jQuery.browser.msie && jQuery.browser.version<7.0){
				set.hdiv = $('<div style=\'float: left; height: 24px; border: 1px solid #ccc; background: url(../images/template/b-bomb-box-colheader.gif); padding-right: 17px; overflow: hidden;\'></div>');
			}
			else{
				set.hdiv = $('<div style=\'height: 24px; border: 1px solid #ccc; background: url(../images/template/b-bomb-box-colheader.gif); padding-right: 17px; overflow: hidden;\'></div>');
			}
			set.mdiv = $('<div style=\'border: 1px solid #ccc; margin-top: -1px; overflow: auto;\'></div>');
			
			//创建列头
			if(args.thead){
				var thead = $('<table style=\'border-collapse: collapse;\'><thead><tr></tr></thead></table>');
				$.each(args.thead, function(i, node){
					var th = $('<th height=\'20\' axis=\'col' + i + '\' style=\'border-right: 1px solid #ccc; padding: 2px;\'></th>');
					if(node){
						if(node.text){
							th.html('<label>' + node.text + '</label>')
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
			$(':text:first', set.objtxt).keyup(function(){
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
	}
})(jQuery);


/**
 * 客户分类（树，下属的）
**/
(function(){
	$.fn.subtree = function(args){
		return this.each(function(){
			args = $.extend({
				tid: null,
				showIcon: false,
				type: 'POST',
				async: false,
				url: '',
				data: '',
				format: 'json',
				cache: false,
				setting: {
					view: {
						showIcon: false,
						dblClickExpand: false
					},
					check: {
						enable: true,
						chkboxType: { 'Y': 's', 'N': 's' }
					},
					data: {
						simpleData: {
							enable: true
						}
					},
					callback: {
						beforeClick: beforeClick,
						onClick: null,
						beforeCheck: null,
						onCheck: onCheck
					}
				},
				nodes: [
					{ id: 1, pId: 0, name: 'node 1', open: true },
					{ id: 11, pId: 1, name: 'node 1-1', open: true },
					{ id: 111, pId: 11, name: 'node 1-1-1' },
					{ id: 112, pId: 11, name: 'node 1-1-2' },
					{ id: 12, pId: 1, name: 'node 1-2', open: true },
					{ id: 121, pId: 12, name: 'node 1-2-1' },
					{ id: 122, pId: 12, name: 'node 1-2-2' },
					{ id: 2, pId: 0, name: 'node 2', open: true },
					{ id: 21, pId: 2, name: 'node 2-1' },
					{ id: 22, pId: 2, name: 'node 2-2', open: true },
					{ id: 221, pId: 22, name: 'node 2-2-1' },
					{ id: 222, pId: 22, name: 'node 2-2-2' },
					{ id: 23, pId: 2, name: 'node 2-3' }
				],
				buttons: [
					{ text: '确定', callback: null },
					{ text: '取消', callback: null }
				]
			}, args);
			
			if(args.showIcon){
				args.setting.view.showIcon = args.showIcon;
			}
			
			if(args.url && typeof(args.url)=='string'){
				args.url = args.url;
			}
			else if(args.url && typeof(args.url)=='function'){
				args.url = args.url();
			}
			
			//点击树节点之前
			function beforeClick(tid, tnode){
				var zTree = $.fn.zTree.getZTreeObj(tid);
				zTree.checkNode(tnode, !tnode.checked, true, true);
				return false;
			}
			//点击树节点
			function onClick(e, tid, tnode){
				
			}
			//勾选树节点之前
			function beforeCheck(tid, tnode){
				
			}
			//勾选树节点
			function onCheck(e, tid, tnode){
				var zTree = jQuery.fn.zTree.getZTreeObj(tid), p = tnode.getParentNode();
				
				if(p != null){
					var child = p.children, n = 0;
					for(var i=0; i<child.length; i++){
						if(child[i].checked) n++;
					}
					if(n == child.length){
						zTree.checkNode(p, true, null, true);
					}
					else{
						zTree.checkNode(p, false, null, true);
					}
				}
			}
			
			var set = {
				init: function(){
					$.ajax({
						type: args.type,
						async: args.async,
						url: args.url,
						data: args.data,
						dataType: args.format,
						cache: args.cache,
						success: function(data){
							//'[{key:value},{key:value},{key:value}]'
							if(data && data.length != 0){
								args.nodes = (typeof(data)=='string') ? $.parseJSON(data) : data;
								
								$.fn.zTree.init($('#' + args.tid), args.setting, args.nodes);
								
								var zTree = $.fn.zTree.getZTreeObj(args.tid), node = null;
								var arr = $('input[type=\'hidden\']', set.objtxt).val().split(',');
								var val = [];
								for(var i=0; i<arr.length; i++){
									node = zTree.getNodeByParam("id", arr[i]);
									if(node != null){
										zTree.checkNode(node, true);
										val.push(node.name);
									}
								}
								$('input[type=\'text\']', set.objtxt).val(val.join(','));
							}
							else{
								//$.fn.zTree.init($('#' + args.tid), args.setting, args.nodes);
							}
						},
						error: function(){
							//$.fn.zTree.init($('#' + args.tid), args.setting, args.nodes);
						}
					});
				},
				show: function(){
					this.bdiv.show();
				},
				hide: function(){
					this.bdiv.hide();
				},
				width: function(){
					return (this.objtxt.outerWidth(true) < 200) ? '200px' : (this.objtxt.outerWidth(true) - 2 + 'px');
				},
				top: function(){
					return this.objtxt.position().top + this.objtxt.outerHeight(true) - 1;
				},
				left: function(){
					return this.objtxt.position().left;
				},
				position: function(){
					if((parseInt(set.width()) + set.left()) > $(window).width()){
						this.bdiv.css({
							width: set.width(),
							top: set.top(),
							right: '0px !important'
						});
					}
					else{
						this.bdiv.css({
							width: set.width(),
							top: set.top(),
							left: set.left()
						});
					}
                    //add by wuxj
                    this.mdiv.css({
                       width:set.width().split('px')[0]-11+'px'
                    });
				}
			};
			set.objtxt = $(this);
			set.bdiv = $('<div class=\'ui-subtree\' style=\'position: absolute; display: none; border: 1px solid #b1b1b1; background-color: #f7f7f7; overflow:hidden;\'></div>');
			set.mdiv = $('<div style=\'height: 186px; border: 1px solid #f1f1f1; background-color: white; margin: 5px; overflow: auto;\'><ul class=\'ztree\'></ul></div>');
			set.fdiv = $('<div style=\'border-top: 1px solid #ccc; padding: 5px; text-align: right;\'></div>');
			
			$('body').append(set.bdiv);
			
			//添加按钮
			if(args.buttons && args.buttons.length>0){
				$.each(args.buttons, function(i, button){
					var b = $('<input type=\'button\' class=\'ok\' style=\'cursor: pointer; height: 24px; border: 1px solid #bcbcbc; background: url(../images/template/btn1-bg.gif) repeat-x; margin-left: 5px; overflow: hidden; line-height: 22px; font-size: 12px;\' value=\'' + button.text + '\' />');
					
					b.click(function(){
						if(button.callback && typeof(button.callback)=='function'){
							button.callback(args.tid);
						}
						set.hide();
					});
					
					set.fdiv.append(b);
				});
				set.bdiv.prepend(set.fdiv);
			}
			
			//
			$('ul', set.mdiv)[0].id = args.tid;
			set.bdiv.prepend(set.mdiv)
			
			//装载数据，绘制标记
			set.init();
			
			//点击文本框显示树形控件
			set.objtxt.click(function(e){
				$('.atree, .btree, .ui-subtree, .ui-agetree, .combobox').hide();
				
				if(set.bdiv.is(':hidden')){
					//显示
					set.show();
					//重置位置
					set.position();
					
					//如果文本框值为空，则去除树中所有被勾选项的勾选状态
					if($('input[type=\'text\']', set.objtxt).val() == ''){
						var zTree = jQuery.fn.zTree.getZTreeObj(args.tid);
						if(zTree != null){
							zTree.checkAllNodes(false);
						}
					}
				}
				
				//阻止事件冒泡到document
				e.stopPropagation();
			});
			
			//点击树形控件区域阻止事件冒泡到document
			set.bdiv.click(function(e){
				e.stopPropagation();
			});
			
			//点击页面其他部分隐藏列表框
			$(document).click(function(){
				set.hide();
			});
			
			//窗体大小改变时重置大小及位置
			$(window).resize(function(){
				//重置位置
				//set.position();
			});
		});
	}
})(jQuery);


/**
 * 客户分类（树，机构的）
**/
(function(){
	$.fn.agetree = function(args){
		return this.each(function(){
			args = $.extend({
				tid: null,
				showIcon: false,
				type: 'POST',
				async: false,
				url: '',
				data: '',
				format: 'json',
				cache: false,
				subChecked: false,
				setting: {
					view: {
						showIcon: false
					},
					check: {
						enable: true,
						chkboxType:  { 'Y': '', 'N': '' }
					},
					data: {
						simpleData: {
							enable: true
						}
					},
					callback: {
						beforeClick: beforeClick,
						onClick: null,
						beforeCheck: null,
						onCheck: null
					}
				},
				nodes: [
					{ id: 1, pId: 0, name: 'node 1', open: true },
					{ id: 11, pId: 1, name: 'node 1-1', open: true },
					{ id: 111, pId: 11, name: 'node 1-1-1' },
					{ id: 12, pId: 1, name: 'node 1-2', open: true },
					{ id: 121, pId: 12, name: 'node 1-2-1' },
					{ id: 122, pId: 12, name: 'node 1-2-2' },
					{ id: 2, pId: 0, name: 'node 2', open: true },
					{ id: 21, pId: 2, name: 'node 2-1' },
					{ id: 22, pId: 2, name: 'node 2-2', open: true },
					{ id: 221, pId: 22, name: 'node 2-2-1' },
					{ id: 222, pId: 22, name: 'node 2-2-2' },
					{ id: 23, pId: 2, name: 'node 2-3' }
				],
				buttons: [
					{ text: '确定', callback: null },
					{ text: '取消', callback: null }
				]
			}, args);
			
			if(args.showIcon){
				args.setting.view.showIcon = args.showIcon;
			}
			
			if(args.url && typeof(args.url)=='string'){
				args.url = args.url;
			}
			else if(args.url && typeof(args.url)=='function'){
				args.url = args.url();
			}
			
			//点击树节点之前
			function beforeClick(tid, tnode){
				var zTree = $.fn.zTree.getZTreeObj(tid);
				zTree.checkNode(tnode, !tnode.checked, true, true);
				return false;
			}
			//点击树节点
			function onClick(e, tid, tnode){
				
			}
			//勾选树节点之前
			function beforeCheck(tid, tnode){
				var zTree = $.fn.zTree.getZTreeObj(tid);
				
				var parent = tnode.getParentNode();
				while(parent != null){
					zTree.checkNode(parent, false, null, true);
					parent = parent.getParentNode();
				}
				
				var child =  zTree.getNodesByParam('checked', true, tnode);
				if(child != null){
					for(var i=0; i<child.length; i++){
						zTree.checkNode(child[i], false, null, null);
					}
				}
			}
			//勾选树节点
			function onCheck(e, tid, tnode){
				
			}
			
			var set = {
				init: function(){
					$.ajax({
						type: args.type,
						async: args.async,
						url: args.url,
						data: args.data,
						dataType: args.format,
						cache: args.cache,
						success: function(data){
							//'[{key:value},{key:value},{key:value}]'
							if(data && data.length != 0){
								args.nodes = (typeof(data)=='string') ? $.parseJSON(data) : data;
								
								$.fn.zTree.init($('#' + args.tid), args.setting, args.nodes);
								
								var zTree = $.fn.zTree.getZTreeObj(args.tid), node = null;
								
								if($('.subChecked', set.bdiv).attr('checked')){
									zTree.setting.callback.beforeCheck = beforeCheck;
								}
								else{
									zTree.setting.callback.beforeCheck = null;
								}
								
								var arr = $('input[type=\'hidden\']', set.objtxt).val().split(',');
								var val = [];
								for(var i=0; i<arr.length; i++){
									node = zTree.getNodeByParam("id", arr[i]);
									if(node != null){
										zTree.checkNode(node, true, false, false);
										val.push(node.name);
									}
								}
								$('input[type=\'text\']', set.objtxt).val(val.join(','));
							}
							else{
								//$.fn.zTree.init($('#' + args.tid), args.setting, args.nodes);
							}
						},
						error: function(){
							//$.fn.zTree.init($('#' + args.tid), args.setting, args.nodes);
						}
					});
				},
				show: function(){
					this.bdiv.show();
				},
				hide: function(){
					this.bdiv.hide();
				},
				width: function(){
					return (this.objtxt.outerWidth(true) < 200) ? '200px' : (this.objtxt.outerWidth(true) - 2 + 'px');
				},
				top: function(){
					return this.objtxt.position().top + this.objtxt.outerHeight(true) - 1;
				},
				left: function(){
					return this.objtxt.position().left;
				},
				position: function(){
					this.bdiv.css({
						width: set.width(),
						top: set.top(),
						left: set.left()
					});
                    //add by wuxj
                    this.mdiv.css({
                        width:set.width().split('px')[0]-11+'px'
                    });
				}
			};
			set.objtxt = $(this);
			set.bdiv = $('<div class=\'ui-agetree\' style=\'position: absolute; display: none; border: 1px solid #b1b1b1; background-color: #f7f7f7;overflow:hidden;\'></div>');
			set.mdiv = $('<div style=\'height: 186px; border: 1px solid #f1f1f1; background-color: white; margin: 5px; overflow: auto;\'><ul class=\'ztree\'></ul></div>');
			set.fdiv = $('<div style=\'height: auto; border-top: 1px solid #ccc; padding: 5px; overflow: auto; zoom: 1;\'><div style=\'line-height: 24px;\'><input class=\'subChecked\' type=\'checkbox\' ' + (args.subChecked ? 'checked=true' : '') + ' style=\'width: 15px; height: 15px; margin: 0; padding: 0; overflow: hidden; vertical-align: middle;\' />&nbsp;<label>查询子机构</label></div><div class=\'button\' style=\'margin-top: -24px; text-align: right; font-size: 0;\'></div></div>');
			
			$('body').append(set.bdiv);
			
			//添加按钮
			if(args.buttons && args.buttons.length>0){
				$.each(args.buttons, function(i, button){
					var b = $('<input type=\'button\' class=\'ok\' style=\'cursor: pointer; height: 24px; border: 1px solid #bcbcbc; background: url(../images/template/btn1-bg.gif) repeat-x; margin-left: 5px; overflow: hidden; line-height: 22px; vertical-align: middle; font-size: 12px;\' value=\'' + button.text + '\' />');
					
					b.click(function(){
						if(button.callback && typeof(button.callback)=='function'){
							button.callback(args.tid, $(':checkbox', set.fdiv)[0]);
						}
						set.hide();
					});
					
					$('div.button', set.fdiv).append(b);
				});
				set.bdiv.prepend(set.fdiv);
			}
			
			//树ID
			$('ul', set.mdiv)[0].id = args.tid;
			set.bdiv.prepend(set.mdiv)
			
			//装载数据，绘制标记
			set.init();
			
			//查询子机构
			$(':checkbox', set.fdiv).click(function(){
				var zTree = $.fn.zTree.getZTreeObj(args.tid);
				
				if(this.checked){
					zTree.setting.callback.beforeCheck = beforeCheck;
					
					var checked = zTree.getCheckedNodes(true);
					for(var i=0; i<checked.length; i++){
						zTree.checkNode(checked[i], false, null, true);
					}
				}
				else{
					zTree.setting.callback.beforeCheck = null;
				}
			});
			
			//点击文本框显示树形控件
			set.objtxt.click(function(e){
				$('.atree, .btree, .ui-subtree, .ui-agetree, .combobox').hide();
				
				if(set.bdiv.is(':hidden')){
					//显示
					set.show();
					//重置位置
					set.position();
					
					//如果文本框值为空，则去除树中所有被勾选项的勾选状态
					if($('input[type=\'text\']', set.objtxt).val() == ''){
						var zTree = jQuery.fn.zTree.getZTreeObj(args.tid);
						if(zTree != null){
							zTree.checkAllNodes(false);
						}
					}
				}
				
				//阻止事件冒泡到document
				e.stopPropagation();
			});
			
			//点击树形控件区域阻止事件冒泡到document
			set.bdiv.click(function(e){
				e.stopPropagation();
			});
			
			//点击页面其他部分隐藏列表框
			$(document).click(function(){
				set.hide();
			});
			
			//窗体大小改变时重置大小及位置
			$(window).resize(function(){
				//重置位置
				set.position();
			});
		});
	}
})(jQuery);