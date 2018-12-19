// JavaScript Document

function validateForm1(id){
	var vb = $("#easy-tabs").find(".easy-tabs-page:visible");
	if($("#" + id).validationEngine("validate")){
		vb.attr("validate", "true");
	}
	else{
		vb.attr("validate", "false");
	}
	
	var n = $("#easy-tabs").find(".easy-tabs-page[validate='false']").length;
	if(n > 0){
		return false;
	}
	else{
		return true;
	}
}

function validateForm2(id){
	var vb = $("#tabs").find(".tabs-page:visible");
	if($("#" + id).validationEngine("validate")){
		vb.attr("validate", "true");
	}
	else{
		vb.attr("validate", "false");
	}
	
	var vtab = $("#tabs").find(".tabs-items").find("li:visible"), num = 0;
	vtab.each(function(){
		if($("#tabs").find(".tabs-page").eq($(this).index()).attr("validate") == "false"){
			num += 1;
		}
	});
	if(num > 0){
		return false;
	}
	else{
		return true;
	}
}