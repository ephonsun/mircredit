// JavaScript Document

$(function(){
	//IE6:Set the height of the textarea
	if($.browser.msie && $.browser.version<7.0){
		$('div.ui-div-textarea').each(function(){
			$('textarea', this).height($(this).height() - 2);
		});
	}
	
	//button: hover / active
	$('.ui-span-btn').hover(
		function(){ $(this).addClass('ui-button-hover'); },
		function(){ $(this).removeClass('ui-button-hover ui-button-active'); }
	).mousedown(function(){
		$(this).addClass('ui-button-active');
	}).mouseup(function(){
		$(this).removeClass('ui-button-active');
	});
	
	if($.browser.msie && $.browser.version<7.0){
		//IE6 下设置 x-input-btn 按钮鼠标移上移出样式
		$('.ui-input-btn').hover(
			function(){ $(this).addClass('ui-button-hover'); },
			function(){ $(this).removeClass('ui-button-hover'); }
		);
		//IE6 下设置 x-span-rbtn 按钮鼠标移上移出样式
		$('.ui-span-rbtn').hover(
			function(){ $(this).addClass('ui-span-rbtn-hover'); },
			function(){ $(this).removeClass('ui-span-rbtn-hover'); }
		);
		//IE6 下设置 x-span-srbtn 按钮鼠标移上移出样式
		$('.ui-span-srbtn').hover(
			function(){ $(this).addClass('ui-span-srbtn-hover'); },
			function(){ $(this).removeClass('ui-span-srbtn-hover'); }
		);
		//IE6 下设置 x-link-btn 按钮鼠标移上移出样式
		$('.ui-link-btn').hover(
			function(){ $(this).addClass('ui-link-btn-hover'); },
			function(){ $(this).removeClass('ui-link-btn-hover'); }
		);		
				
		//IE6 下设置 x-input-btn 按钮鼠标移上移出样式
		$('.prev').hover(
			function(){ $(this).addClass('prev-hover'); },
			function(){ $(this).removeClass('prev-hover'); }
		);
	
		//IE6 下设置 x-input-btn 按钮鼠标移上移出样式
		$('.next').hover(
			function(){ $(this).addClass('next-hover'); },
			function(){ $(this).removeClass('next-hover'); }
		);		
	}
});

var ctrol = {};
ctrol.hide = function(){
	//隐藏下拉复选框选项列表
	$('div.combobox-box').hide();
};
ctrol.clear = function(){
	
};











