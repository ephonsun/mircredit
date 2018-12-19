//实现地图定位弹出遮罩
function showLocationMap(id, address, name){
	var content = $('#content');
    var json = {"customerId": id, "address": address, "customerName": name};
    addressSearch(json);
    var ob = document.body;
    var scrollW = document.documentElement.clientWidth;
    var scrollH = document.documentElement.clientHeight;
    content.css({
    	'top':(scrollH / 2 - 380)>0?(scrollH / 2 - 380):'30px',
    	'left':scrollW / 2 - 510,
    	'display':'block',
    	'z-index':'151'
    }); 
    var cdiv="<div class='olay'></div>";
    var odiv=$(cdiv);
    odiv.insertBefore(content);
    odiv.css({
    	'width':Math.max(document.documentElement.scrollWidth, scrollW) + "px",
    	'height':Math.max(document.documentElement.scrollHeight, scrollH) + "px"
    });
    window.onresize = function () {
       if(!content.is('hidden')){
    	   var scrollW = document.documentElement.clientWidth;
           var scrollH = document.documentElement.clientHeight;   
           odiv.css({
	           	'width':Math.max(document.documentElement.scrollWidth, scrollW) + "px",
	           	'height':Math.max(document.documentElement.scrollHeight, scrollH) + "px"
           });      
           content.css({
        	   'top':(scrollH / 2 - 380)>0?(scrollH / 2 - 380):'30px',
        	   'left':scrollW / 2 - 510
           });
       }
    }
}