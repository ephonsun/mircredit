// JavaScript Document

//To processing modal dialog transverse & vertical scroll bar.
$(function(){
	if(window.dialogArguments){
		var wH = $(window).height(), bH = $('body').outerHeight(true), w = $(window).width() - 20;
		
		if(wH < bH){
			$('body').css({ 'width': w + 'px', 'overflow-x': 'hidden', 'overflow-y': 'auto' });
		}
	}
});