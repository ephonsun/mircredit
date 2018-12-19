// JavaScript Document
		function pic(ttl, val){
			art.dialog({
				padding: 0,
				title: ttl,
				width: '500px',
				height: '350px',
				content: "<img src='" + val + "' />",
				lock: true
			});
		}	
		
		function player(ttl, url){
			
			var v = jwplayer('container').setup({ 								
				flashplayer: '../../js/player/player.swf',  
				file: url,  
				width: 500,  
				height: 350,  
				dock: false
			});
			
			
			art.dialog({
				padding: 0,
				title: ttl,
				width: '500px',
				height: '350px',
				content: document.getElementById('container'),
				lock: true,
				close: function(){
					v.stop();
					$('#container_wrapper').hide();
				}
			});			
	
			
		}