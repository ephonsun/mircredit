// JavaScript Document

document.write('<link type="text/css" rel="stylesheet" href="../css/b.ui/model-layer.css" />');

var aui;

if(!aui){
	aui = {};
}
else if(typeof aui != 'object'){
	throw new Error('aui already exists and is not an object!');
}

aui.html = function(){
	var ui = [];
	
	ui.push('<div class=\'aui-outer\'>');
	ui.push('<table class=\'aui-border\'>');
	ui.push('<tbody>');
	ui.push('<tr>');
	ui.push('<td class=\'aui-nw\'><div>&nbsp;</div></td>');
	ui.push('<td class=\'aui-n\'><div>&nbsp;</div></td>');
	ui.push('<td class=\'aui-ne\'><div>&nbsp;</div></td>');
	ui.push('</tr>');
	ui.push('<tr>');
	ui.push('<td class=\'aui-w\'><div>&nbsp;</div></td>');
	ui.push('<td class=\'aui-c\'>');
	ui.push('<div class=\'aui-inner\'>');
	ui.push('<div class=\'aui-header\'>');
	ui.push('<label class=\'aui-title\'></label>');
	ui.push('<a href=\'javascript:void(0);\' class=\'aui-close\'>&nbsp;</a>');
	ui.push('</div>');
	ui.push('<div class=\'aui-main\'>');
	ui.push('<iframe name=\'aui-iframe\' frameborder=\'0\' src=\'\'></iframe>');
	ui.push('</div>');
	ui.push('<div class=\'aui-footer\'>');
	ui.push('<button class=\'aui-button-cancel\'>关闭</button>');
	ui.push('</div>');
	ui.push('</div>');
	ui.push('</td>');
	ui.push('<td class=\'aui-e\'><div>&nbsp;</div></td>');
	ui.push('</tr>');
	ui.push('<tr>');
	ui.push('<td class=\'aui-sw\'><div>&nbsp;</div></td>');
	ui.push('<td class=\'aui-s\'><div>&nbsp;</div></td>');
	ui.push('<td class=\'aui-se\'><div>&nbsp;</div></td>');
	ui.push('</tr>');
	ui.push('</tbody>');
	ui.push('</table>');
	ui.push('</div>');
	
	return ui.join('');
};
aui.builder = function(o){
	if($('div.aui-outer').length > 0){
		$('div.aui-outer').remove();
	}
	else{
		var model = $(aui.html());
		
		//size & position
		var dw = $(window).width() - 20,
			dh = $(window).height() - 88;
		
		$('iframe', model).css({ width: dw + 'px', height: dh + 'px' });
		
		//title
		$('label.aui-title', model).html(o.title || '暂无标题');
		
		//url
		$('iframe', model).attr('src', o.url || '');
		
		//add the layer to the body
		$('body').append(model);
		
		//close-icon's event
		$('a.aui-close', model).bind('click', function(){
			model.remove();
			
			return false;
		});
		//close-button's event
		$('button.aui-button-cancel', model).bind('click', function(){
			model.remove();
		}).focus();
	}
};