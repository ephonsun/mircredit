// JavaScript Document

var tab = window.top.tabs;

if(tab && tab.head){
	tab.self = jQuery('#' + jQuery(document.parentWindow.frameElement).attr('name'), tab.head);
	
	if(tab.self && tab.self.attr('pid') != ''){
		tab.parent = jQuery('#' + tab.self.attr('pid'), tab.head);
	}
}

function GetId(){
	return jQuery('li.' + tab.active, tab.head).attr('id');
}

function GetPid(){
	return jQuery('li.' + tab.active, tab.head).attr('pid');
}




