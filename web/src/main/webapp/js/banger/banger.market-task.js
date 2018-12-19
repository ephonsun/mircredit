// JavaScript Document

$(function(){
	$('#table').tabletree();
	
	$('#table tbody tr td:nth-child(2)').click(function(){
		if($('label', this).length != 0){
			var td = this;
			
			//改变单元格内边距
			$(td).css({ 'padding': '0px' });
			
			//将label标记变更为input标记
			$('label', td).replaceWith('<input type=\'text\' class=\'label\' value=\'' + $('label', td).text() + '\' />');
			
			try{
				floatInput('input[class=\'label\']');
			}catch(e){  }
			
			//给input标记添加blur和change事件
			$('input', td).blur(function(){
				//失去焦点后替换回label标记
				$(this).replaceWith('<label class=\'col1\' title=\'' + $(this).val() + '\'>' + $(this).val() + '</label>');
				$(td).css({ 'padding': '0px 2px' });
			}).change(function(){
				if(!update(this)){
					return false;
				}
				
				if(this.value == '') this.value = 0;
				
				var r = $(this).parents('tr'), f = r[0].parent, s = f[0].child, v = 0;
				
				s.each(function(){
					if($('label.col1', this).length > 0){
						v = calculator.plus(v, $('label.col1', this).text());
					}
					else if($('input.label', this).length > 0){
						v = calculator.plus(v, $('input.label', this).val());
					}
				});
				
				
				if(r[0].child.length > 0){
					if($('label.col1', r).length > 0){
						$('label.col3', r).text(calculator.minus($('label.col1', r).text(), $('label.col2', r).text()));
					}
					else if($('input.label', r).length > 0){
						$('label.col3', r).text(calculator.minus($('input.label', r).val(), $('label.col2', r).text()));
					}
				}
				$('label.col4', r).text(Math.round(parseFloat(this.value)/600*10000)/100.00 + '%');
				
				$('label.col2', f).text(v);
				$('label.col3', f).text(calculator.minus($('label.col1', f).text(), v));
			})[0].select();
		}
	});
});