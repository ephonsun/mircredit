// JavaScript Document
		$(function(){
			$('#customerType').select().change(function(){
				($(this).val());
				switch($(this).val()){	
					case '0':
						$('#pie_0').show();
						$('#pie_1').hide();
						$('#pie_2').hide();
						$('#pie_3').hide();
						$('#pie_4').hide();
						$('#pie_5').hide();
						$('#pie_6').hide();
						break;		
					case '1':
						$('#pie_0').hide();
						$('#pie_1').show();
						$('#pie_2').hide();
						$('#pie_3').hide();
						$('#pie_4').hide();
						$('#pie_5').hide();
						$('#pie_6').hide();
						break;
					case '2':
						$('#pie_0').hide();
						$('#pie_1').hide();
						$('#pie_2').show();
						$('#pie_3').hide();
						$('#pie_4').hide();
						$('#pie_5').hide();
						$('#pie_6').hide();
						break;		
					case '3':
						$('#pie_0').hide();
						$('#pie_1').hide();
						$('#pie_2').hide();
						$('#pie_3').show();
						$('#pie_4').hide();
						$('#pie_5').hide();
						$('#pie_6').hide();
						break;
					case '4':
						$('#pie_0').hide();
						$('#pie_1').hide();
						$('#pie_2').hide();
						$('#pie_3').hide();
						$('#pie_4').show();
						$('#pie_5').hide();
						$('#pie_6').hide();
						break;
					default: break;
				}
			});
		});
		
    	$(function(){
			$('#customerType_1').select().change(function(){
				($(this).val());
				switch($(this).val()){	
					case '5':
						$('#pie_0').hide();
						$('#pie_1').hide();
						$('#pie_2').hide();
						$('#pie_3').hide();
						$('#pie_4').hide();
						$('#pie_5').show();
						$('#pie_6').hide();
						break;		
					case '6':
						$('#pie_0').hide();
						$('#pie_1').hide();
						$('#pie_2').hide();
						$('#pie_3').hide();
						$('#pie_4').hide();
						$('#pie_5').hide();
						$('#pie_6').show();
						break;
					default: break;
				}
			});
		});	