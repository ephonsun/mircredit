function setLabelFormElement(parament,bolColumnID) {
	//parament为要设置的值，以","分隔
	//bolColumnID 为类型，值为0代表parament第一个值并不是栏目的ＩＤ，如果为１说明parament的第一个值为栏目的ＩＤ
	if(bolColumnID != "" && parament != "") {
		//parament = parament.replace(/＃/g,"#");
		var k;
		if(bolColumnID == "0") {
			k = 0;
		} else if(bolColumnID == "1") {
			k = 1;
		}
		var arrParament = parament.split(",");
		var objForm = document.LabelForm;
		var objE;
		for(var i=0;i<objForm.length;i++) {
			objE = objForm.elements[i];
			if((String(objE.type) == "text") || (String(objE.type) == "select-one")) {
				objE.value = arrParament[k];
				k = k + 1;
			} 
		}
	}
	
}