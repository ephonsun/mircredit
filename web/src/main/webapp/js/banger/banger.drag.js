// JavaScript Document

function drag(id){
	var oDrag = document.getElementById(id);
	
	var mouseStart = {};
	var divStart = {};
	var rightStart = {};
	var bottomStart = {};
	
	oDrag.onmousedown=function(ev){
		var oEvent = ev || event;
		mouseStart.x = oEvent.clientX;
		mouseStart.y = oEvent.clientY;
		divStart.x = oDrag.parentNode.offsetLeft;
		divStart.y = oDrag.parentNode.offsetTop;
		if(oDrag.setCapture){
			oDrag.onmousemove = doDrag3;
			oDrag.onmouseup = stopDrag3;
			oDrag.setCapture();
		}
		else{
			document.addEventListener("mousemove", doDrag3, true);
			document.addEventListener("mouseup", stopDrag3, true);
		}
	};
	function doDrag3(ev){
		var oEvent = ev || event;
		var l = oEvent.clientX - mouseStart.x + divStart.x;
		var t = oEvent.clientY - mouseStart.y + divStart.y;
		if(l < 0){
			l = 0;
		}
		else if(l > document.documentElement.clientWidth - oDrag.parentNode.offsetWidth){
			l = document.documentElement.clientWidth - oDrag.parentNode.offsetWidth;
		}
		if(t < 0){
			t = 0;
		}
		else if(t > document.documentElement.clientHeight - oDrag.parentNode.offsetHeight)
		{
			t = document.documentElement.clientHeight - oDrag.parentNode.offsetHeight;
		}
		oDrag.parentNode.style.left = l + "px";
		oDrag.parentNode.style.top = t + "px";
	};
	function stopDrag3(){
		if(oDrag.releaseCapture){
			oDrag.onmousemove = null;
			oDrag.onmouseup = null;
			oDrag.releaseCapture();
		}
		else
		{
			document.removeEventListener("mousemove",doDrag3,true);
			document.removeEventListener("mouseup",stopDrag3,true);
		}
	};
}

 function beginDrag(elem,event){
	 var deltaX = event.clientX - parseInt(elem.style.left);
	 var deltaY = event.clientY - parseInt(elem.style.top);
	 document.attachEvent("onmousemove",moveHandler);
	 document.attachEvent("onmouseup",upHandler);
	 event.cancelBubble = true;
	 event.returnValue = false;
	 function moveHandler(e){
	   if(!e)
	       e = window.event;
	   elem.style.cursor="move";//鼠标指针成可移动形状
       elem.style.left = (e.clientX - deltaX) + "px";
	   elem.style.top = (e.clientY - deltaY) + "px";
	   e.cancelBubble = true;
	 }
	 function upHandler(e){
	  	 if(!e)
	        e = window.event;
         elem.style.cursor="default";//鼠标指针成默认形状
         document.detachEvent("onmousemove",moveHandler);
	     document.detachEvent("onmouseup",upHandler);
	     e.cancelBubble = true;
	 }
 }
 