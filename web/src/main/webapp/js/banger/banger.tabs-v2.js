// JavaScript Document

window.AbstractTab = {};
AbstractTab.Add = function(options, pid){
	if(this.IsExist(options.id)){
		this.Click(options.id, true);
		return false;
	}
	
	this.BuildTab(options);
	this.BuildFrame(options);
};
AbstractTab.IsExist = function(id){
	return jQuery("#" + id).size() > 0;
};
AbstractTab.Click = function(id, flag){
	var $e = jQuery("#" + id);
	
	$e.siblings("." + this.Active).removeAttr("class").end().addClass(this.Active);
	
	//alert(jQuery("#" + id).position().left);
	//alert(this.tabs.position().left);
	//Determine whether the page card cover, and move the position
	if(($e.position().left + this.Tabs.position().left) < 0){
		this.Move(3, 10, 20, id);
	}
	else if((this.Tabs.parent().width() - ($e.position().left + this.Tabs.position().left)) < $e.width()){
		this.Move(4, 10, 20, id);
	}
	
	//Loading frame page
	this.ShowFrame(id, flag);
};
AbstractTab.ShowFrame = function(id, flag){
	//If the if the frame page is exist
	if(jQuery("#frame-" + id).size() > 0){
		var frame = jQuery("#frame-" + id);
		frame.siblings().hide().end().show();
		
		if(flag){
			frame.attr("src", frame.attr("arc"));
		}
	}
};
AbstractTab.Close = function(id){
	var tab = jQuery("#" + id);
	var frame = jQuery("#frame-" + id), win = frame[0].contentWindow;
	if(tab.attr("class") == this.Active){
		if(tab.prev().length != 0){
			this.Click(tab.prev().attr("id"), false);
		}
		else if(tab.next().length != 0){
			this.Click(tab.next().attr("id"), false);
		}
		else{
			if(this.closeFormHandler){
				this.closeFormHandler();
			}
		}
	}
	tab.remove();
	
	if(win)win.close();
	
	this.ResetWidth();
	this.Move(2, 10, 10);
	
	$('#frame-' + id).remove();
};
AbstractTab.CloseAll = function(){
	this.Tabs.find("li[lock!='true']").each(function(){
		jQuery(this).find("span.c").click();
	});
	
	if(this.Tabs.children().size() == 0){
		if(this.closeFormHandler){
			this.closeFormHandler();
		}
	}
	
	if(this.Tabs.children().size() != 0 && this.Tabs.find("." + this.Active).size() == 0){
		this.Click(this.Tabs.find("li:last").attr("id"));
	}
	
	this.ResetWidth();
	this.Move(2, 1, 50);
};
AbstractTab.BuildTab = function(options){
	var $this = this;
	var id = options.id;
	var title = options.title;
	var active = this.Active;
	var lock = options.lock ? true : false;
	var display = options.lock ? "display: none;" : "display: block;";
	
	var args = { id: id, title: title, active: active, lock: lock, display: display };
	var html = this.ReturnTabHTML(args);
	
	var $e = jQuery(html);
	$e.appendTo(this.Tabs).click(function(){ $this.Click(id); }).click();
	$e.find("span.c").click(function(){
		if(options.closeHandler){
			var flag = options.closeHandler($('#frame-' + options.id).find('iframe')[0]);
			if(flag){
				$this.Close(id);
			}
		}
		else{
			$this.Close(id);
		}
	});
	
	this.ResetWidth();
	this.Move(1, 10, 10);
};
AbstractTab.ReturnTabHTML = function(args){
	var tab = "<li id=\"" + args.id + "\" lock=\"" + args.lock + "\">"
			+ "<div>" + args.title + "</div>"
			+ "<span class=\'c\' style=\"" + args.display + "\">&nbsp;</span>"
			+ "</li>";
	return tab;
};
AbstractTab.BuildFrame = function(options){
	var id = options.id;
	var url = options.url;
	
	var args = { id: id, url: url };
	var html = this.ReturnFrameHTML(args);
	
	var $e = jQuery(html);
	$e.appendTo(this.Frames);
	
	this.ShowFrame(id, false);
};
AbstractTab.ReturnFrameHTML = function(args){
	var frame = "<div id=\"frame-" + args.id + "\" class=\"call-tabs-page\" style=\"display: none;\">"
			  + "<iframe name=\"frame-" + args.id  + "\" frameborder=\"0\" width=\"100%\" height=\"1200\" scrolling=\"no\" src=\"" + args.url + "\"></iframe>"
			  + "</div>";
	return frame;
};
AbstractTab.ResetWidth = function(){
	var arr = this.Tabs.children(), val = 0;
	if(arr.size() > 0){
		arr.each(function(){
			val = val + $(this).outerWidth(true) + 21;
		});
		val += 1;
		this.Tabs.width(val);
	}
};
AbstractTab.Move = function(n, interval, speed, id){
	var $this = this;
	
	switch(n){
		case 0:
			clearInterval($this.Timer);
			$this.Timer = setInterval(
			function(){
				var sp = $this.Tabs.position().left;
				if($this.Tabs.position().left >= 0){
					clearInterval($this.Timer);
				}
				else{
					if(Math.abs(sp) < speed){
						$this.Tabs.css({ left: $this.Tabs.position().left + Math.abs(sp) });
					}
					else{
						$this.Tabs.css({ left: $this.Tabs.position().left + speed });
					}
				}
			},
			interval);
			break;
		case 1:
			clearInterval($this.Timer);
			$this.Timer = setInterval(
			function(){
				var sp = ($this.Tabs.width() + $this.Tabs.position().left) - $this.Tabs.parent().width();
				if(($this.Tabs.width() + $this.Tabs.position().left) <= $this.Tabs.parent().width()){
					clearInterval($this.Timer);
				}
				else{
					if(sp < speed){
						$this.Tabs.css({ left: $this.Tabs.position().left - Math.abs(sp) });
					}
					else{
						$this.Tabs.css({ left: $this.Tabs.position().left - speed });
					}
				}
			},
			interval);
			break;
		case 2:
			clearInterval($this.Timer);
			$this.Timer = setInterval(
			function(){
				var sp = $this.Tabs.position().left;
				if(($this.Tabs.position().left >= 0) || (($this.Tabs.width() + $this.Tabs.position().left) >= $this.Tabs.parent().width())){
					clearInterval($this.Timer);
				}
				else{
					if(Math.abs(sp) < speed){
						$this.Tabs.css({ left: $this.Tabs.position().left + Math.abs(sp) });
					}
					else{
						$this.Tabs.css({ left: $this.Tabs.position().left + speed });
					}
				}
			},
			interval);
			break;
		case 3:
			clearInterval($this.Timer);
			$this.Timer = setInterval(
			function(){
				var sp = jQuery("#" + id).position().left + $this.Tabs.position().left;
				if(sp >= 0){
					clearInterval($this.Timer);
				}
				else{
					if(Math.abs(sp) < speed){
						$this.Tabs.css({ left: $this.Tabs.position().left + Math.abs(sp) });
					}
					else{
						$this.Tabs.css({ left: $this.Tabs.position().left + speed });
					}
				}
			},
			interval);
			break;
		case 4:
			clearInterval($this.Timer);
			$this.Timer = setInterval(
			function(){
				var sp = jQuery("#" + id).width() - ($this.Tabs.parent().width() - (jQuery("#" + id).position().left + $this.Tabs.position().left));
				if(($this.Tabs.parent().width() - (jQuery("#" + id).position().left + $this.Tabs.position().left)) >= jQuery("#" + id).width()){
					clearInterval($this.Timer);
				}
				else{
					if(sp < speed){
						$this.Tabs.css({ left: $this.Tabs.position().left - Math.abs(sp) });
					}
					else{
						$this.Tabs.css({ left: $this.Tabs.position().left - speed });
					}
				}
			},
			interval);
			break;
	}
};
AbstractTab.InitEvent = function(){
	var $this = this;
	
	jQuery("#" + this.LeftId).mousedown(function(){
		$this.Move(0, 10, 20);
	});
	jQuery("#" + this.LeftId).mouseup(function(){
		clearInterval($this.Timer);
	});
	jQuery("#" + this.RightId).mousedown( function(){
		$this.Move(1, 10, 20);
	});
	jQuery("#" + this.RightId).mouseup(function(){
		clearInterval($this.Timer);
	});
	jQuery("#" + this.DropdownId).click( function(){
		
	});
	jQuery("#" + this.CloseAllId).click( function(){
		$this.CloseAll();
	});
	
	return this;
};


window.InstanceTab = function(options){
	this.TabsId = options.tabs || "call-tabs-items";
	this.FramesId = options.frames || "call-tabs-items";
	this.LeftId = options.left || "btnl";
	this.RightId = options.right || "btnr";
	this.DropdownId = options.dropdown || "btnd";
	this.CloseAllId = options.closeall || "btnc";
	this.Tabs = jQuery("#" + this.TabsId);
	this.Frames = jQuery("#" + this.FramesId);
	this.Timer = null;
	this.Active = options.active || "call-tabs-item-selected";
	this.closeFormHandler = options.closeFormHandler;
};

jQuery.extend(InstanceTab.prototype, AbstractTab);

window.InstanceTab.Create = function(options){
	var Tab = new InstanceTab(options);
	return Tab.InitEvent();
};




