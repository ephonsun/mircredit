// JavaScript Document
document.write('<link type="text/css" rel="stylesheet" href="../css/jquery.ui/base/jquery-ui.css" />');
document.write('<link type="text/css" rel="stylesheet" href="../css/jquery.ui/base/jquery-ui-timepicker-addon.css" />');
document.write('<scr' + 'ipt type="text/javascript" src="../js/jquery.ui/jquery-ui.js"></scr' + 'ipt>');
document.write('<scr' + 'ipt type="text/javascript" src="../js/jquery.ui/jquery-ui-sliderAccess.js"></scr' + 'ipt>');
document.write('<scr' + 'ipt type="text/javascript" src="../js/jquery.ui/jquery-ui-timepicker-addon.js"></scr' + 'ipt>');

//默认页面自动渲染日期时间控件，显示日期，时，分
$(function(){
	$('input.datepicker').datetimepicker({
		currentText: '今天',
		closeText: '关闭',
		clearText: "清除",
		amNames: ['AM', 'A'],
		pmNames: ['PM', 'P'],
		dateFormat: "yy-mm-dd",
		timeFormat: 'HH:mm',
		timeSuffix: '',
		timeOnlyTitle: '选择时间',
		timeText: '时间',
		hourText: '小时',
		minuteText: '分钟',
		secondText: '秒',
		millisecText: '毫秒',
		microsecText: '微秒',
		timezoneText: '时区',
		isRTL: false,
		onSelect: function(){
	    }
	}).next('i').click(function(){
		$(this).prev('input[type=\'text\']').focus();
	});
});
/*add by wuxj 2013-6-17*/
//手动配置页面渲染日期时间控件，显示日期，时，分，可自行配置参数
(function(){
	var f={
		init: function(t,o){
			$(t).datetimepicker({
				currentText: o.currentText,
				closeText: o.closeText,
				amNames: o.amNames,
				pmNames: o.pmNames,
				dateFormat: o.dateFormat,
				timeFormat: o.timeFormat,
				timeSuffix: o.timeSuffix,
				timeOnlyTitle: o.timeOnlyTitle,
				timeText:o.timeText,
				hourText: o.hourText,
				minuteText: o.minuteText,
				secondText: o.secondText,
				millisecText: o.millisecText,
				microsecText: o.microsecText,
				timezoneText:o.timezoneText,
				isRTL: o.isRTL,
				timeOnly:o.timeOnly,
				showTimepicker: o.showTimepicker,
				showButtonPanel: o.showButtonPanel,
                defaultValue: o.defaultValue,
				onSelect: o.onSelect
			}).next('i').click(function(){
				$(this).prev('input[type=\'text\']').focus();
			});
		}
	};
	$.fn.datetimeReset = function(o) {
		o = $.extend({}, $.fn.datetimeReset.defaults, o);
		return this.each(function(){
			var t=this;
			f.init(t,o);
		});
	};
	$.fn.datetimeReset.defaults = {
		currentText: '今天',
		closeText: '关闭',
		amNames: ['AM', 'A'],
		pmNames: ['PM', 'P'],
		dateFormat: "yy-mm-dd",
		timeFormat: 'HH:mm',
		timeSuffix: '',
		timeOnlyTitle: '选择时间',
		timeText: '时间',
		hourText: '小时',
		minuteText: '分钟',
		secondText: '秒',
		millisecText: '毫秒',
		microsecText: '微秒',
		timezoneText: '时区',
		isRTL: false,
		timeOnly:false,
		showTimepicker: true,
		showButtonPanel: true,
        defaultValue:null,
		onSelect: function(){
	    }
	};
})(jQuery);
/*add by wuxj 2013-6-17*/