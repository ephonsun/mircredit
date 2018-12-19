// JavaScript Document

window.video = {};

video.html = function(o){
	var html = '<div id=\'vbox\' style=\'position: absolute; background-color: #505050;z-index:1000; width: ' + o.width + 'px;\'>'
			   + '<div id=\'vdrag\' onmousedown=\'beginDrag(this.parentNode,event);\' style=\'cursor: move; height: 24px; border-bottom: 1px solid #ccc; line-height: 24px;\'><div class=\'vclose\' style=\'cursor: pointer; float: right; width: 24px; text-align: center; color: white; font-size: 16px;\'>×</div></div>'
			   + '<object id=\'player\' width=\'310\' height=\'64\' align=\'middle\' classid=\'clsid:22D6F312-B0F6-11D0-94AB-0080C74C7E95\'>'
			   + '<param name=\'AutoStart\' value=\'-1\' />'
			   + '<param name=\'Filename\' value=\''+ o.src +'\' />'
			   + '<param name=\'PlayCount\' value=\'1\' />'
			   + '<param name=\'Volume\' value=\'0\' />'
			   + '</object>'
			   + '</div>';
	return html;
};

video.create = function(id, w, h, src, obj){
	if($('#vbox').length > 0){
		$('#vbox #player')[0].Stop();
		$('#vbox').remove();
	}
	
	this.id = id;
	this.w = w || 320;
	this.h = h || 100;
	this.src = src || '';
	
	var ele = $(video.html({ id: this.id, width: this.w, height: this.h, src: this.src }));
	
	if(obj != null){
		var t = $(obj).offset().top, l = $(obj).offset().left - this.w - 20;
		
		ele.css({
			'top': t + 'px',
			'left': l + 'px'
		}).appendTo(document.body);
	}
	else{
		var s = this;
		ele.css({
			'top': ($(window.top).height() - s.h)/2 + 'px',
			'left': ($(window.top).width() - s.w)/2 + 'px',
			'z-index': 111111
		}).appendTo(window.top.document.body);
	}
	
	$('.vclose', ele).hover(
		function(){ $(this).css({ 'font-size': '18px', 'font-weight': 'bold' }); },
		function(){ $(this).css({ 'font-size': '16px', 'font-weight': 'normal' }); }
	).click(function(){
		$('#vbox #player')[0].Stop();
		$('#vbox').remove();
	});
};