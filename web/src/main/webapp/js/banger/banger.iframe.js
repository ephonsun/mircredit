// JavaScript Document

$(function(){
	if($.browser.msie && $.browser.version=="6.0" && $("html")[0].scrollHeight>$("html").height()){
		$("html").css("overflow-y", "scroll");
	}
});

var tabs = window.top.tabs;
function tabid(){
	return tabs.head.children("li." + tabs.active).attr("id");
}
function tabpid(){
	return tabs.head.children("li." + tabs.active).attr("pid");
}




