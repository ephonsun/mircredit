// JavaScript Document

document.write('<link type="text/css" rel="stylesheet" href="../css/b.ui/photo-layer.css" />');

var aui;

if(!aui){
	aui = {};
}
else if(typeof aui != 'object'){
	throw new Error('aui already exists and is not an object!');
}

aui.html = function(){
	var ui = [];
	
	ui.push('<div class=\'alert\'>');
	ui.push('</div>');
	ui.push('<div class=\'aui-outer\'>');
	ui.push('<table class=\'aui-border\'>');
	ui.push('<tbody>');
	ui.push('<tr>');
	ui.push('</tr>');
	ui.push('<tr>');
	ui.push('<td class=\'aui-c\'>');
	ui.push('<div class=\'aui-main\'>');
	ui.push('<iframe name=\'aui-iframe\' id=\'auiIframe\' frameborder=\'0\' src=\'\'></iframe>');
	ui.push('</div>');
	ui.push('</td>');
	ui.push('</tr>');
	ui.push('<tr>');
	ui.push('</tr>');
	ui.push('</tbody>');
	ui.push('</table>');
	ui.push('</div>');

	
	return ui.join('');
};
aui.builder1 = function(o){
	if($('div.aui-outer').length > 0){
		$('div.aui-outer').remove();
	}
	else{
		var model = $(aui.html());
		
		//size & position
		var dw = 1286,
		//var dw = 966,
			dh = 623;
		
		if($.browser.msie && $.browser.version<7.0){
			dw = 966;
			dh = 577;
		}

		$('iframe', model).css({ width: dw + 'px', height: dh + 'px' });
		
		//title
		$('label.aui-title', model).html(o.title || 'Ã¦ÂÂÃ¦ÂÂ Ã¦Â ÂÃ©Â¢Â');
		
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
aui.builder = function(o){
	if($('div.aui-outer').length > 0){
		$('div.aui-outer').remove();
	}
	else{
		var model = $(aui.html());
		
		//size & position
		var dw = 966,
			dh = 573;
		
		if($.browser.msie && $.browser.version<7.0){
			dw = 966;
			dh = 577;
		}

		$('iframe', model).css({ width: dw + 'px', height: dh + 'px' });
		
		//title
		$('label.aui-title', model).html(o.title || 'Ã¦ÂÂÃ¦ÂÂ Ã¦Â ÂÃ©Â¢Â');
		
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


aui.close = function(){
		$('div.aui-outer').remove();
		$('.alert').remove();
};

