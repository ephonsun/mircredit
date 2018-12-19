// JavaScript Document

var tips = {};
tips.create = function(options){
	this.win = options.win || window;
	this.b = $(this.win.document.body);
	this.zone = $(this.b).find(options.zone);
	this.maxwidth = options.maxwidth || 200;
	this.txt = options.txt || "text";
	this.src = "../images/template/";
	this.div = $("<div class=\"ui-emer\">" + this.txt + "</div>");
	this.b.append(this.div);
	
	this.div.width()>this.maxwidth ? this.div.width(this.maxwidth) : this.div.width("auto");
	this.div.css({
		top: this.zone.offset().top,
		left: this.zone.outerWidth(true)/2 - this.div.width()/2
	});
	
	switch(options.indicator){
		case "success":
			this.div.css("background-image", "url(" + this.src + "success.gif)");
			break;
		case "error":
			this.div.css("background-image", "url(" + this.src + "error.gif)");
			break;
		case "warning":
			this.div.css("background-image", "url(" + this.src + "warning.gif)");
			break;
		default: break;
	}
	
	this.div.animate({
		top: this.zone.offset().top + this.zone.outerHeight(true)/2 - this.div.height()/2
	}).fadeOut(4000, function(){$(this).remove()});
}

window.tips = tips;