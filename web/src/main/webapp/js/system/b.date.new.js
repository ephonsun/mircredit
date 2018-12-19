// JavaScript Document

//document.write('<link type="text/css" rel="stylesheet" href="../css/jquery.ui/base/jquery.ui.base.css" />');
//document.write('<scr' + 'ipt type="text/javascript" src="../js/jquery.ui/jquery.ui.core.js"></scr' + 'ipt>');
//document.write('<scr' + 'ipt type="text/javascript" src="../js/jquery.ui/jquery.ui.widget.js"></scr' + 'ipt>');
//document.write('<scr' + 'ipt type="text/javascript" src="../js/jquery.ui/jquery.ui.datepicker.js"></scr' + 'ipt>');

$(function(){
	$('input.datepicker').datepicker({
		showAnim: '',
		showButtonPanel: true,
		prevText: '上一月',
		nextText: '下一月',
		monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
		monthNamesShort: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
		dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
		closeText: '关闭',
		clearText: '清除',
		currentText: '今天',
		showOtherMonths: true,
		selectOtherMonths: false,
		changeMonth: true,
		changeYear: true,
		yearRange: '-100:+10',
		dateFormat: 'yy-mm-dd',
		onSelect: function(text, inst){
			$(inst.input).focus().blur();
		}
	}).next('i').click(function(){
		$(this).prev('input[type=\'text\']').click();
	});
});