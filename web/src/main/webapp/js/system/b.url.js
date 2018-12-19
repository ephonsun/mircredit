// JavaScript Document

window.url = function(u, n, v){
	var reg = new RegExp('(\\\?|&)' + n + '=([^&]+)(&|$)', 'i');
	var m = u.match(reg);
	
	if(v){
		if(m){
			return (u.replace(reg, function($0, $1, $2){
				return ($0.replace($2, v));
			}));
		}
		else{
			if(u.indexOf('?') == -1){
				return (u + '?' + n + '=' + v);
			}
			else{
				return (u + '&' + n + '=' + v);
			}
		}
	}
	else{
		if(m){
			return m[2];
		}
		else{
			return '';
		}
	}
};