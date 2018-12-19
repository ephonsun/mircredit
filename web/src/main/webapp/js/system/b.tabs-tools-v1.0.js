// JavaScript Document

window.tabs = window.top.tabs;

if(tabs && tabs.head){
	tabs.self = $('#' + $(document.parentWindow.frameElement).attr('name'), tabs.head);
	
	if(tabs.self && tabs.self.attr('pid') != ''){
		tabs.parent = $('#' + tabs.self.attr('pid'), tabs.head);
	}
}