// JavaScript Document

//拨号
function dial(number,cusId,taskId)			
{
	if(window.dialogArguments){
		var win = window.dialogArguments.window;
		if(win.top.postDial){
			win.top.postDial(number,cusId,null,taskId);
		}
	}
	else{
		if(window.top.postDial)
		{
			window.top.postDial(number,cusId,null,taskId);
		}
	}
}

//现场录音
function tape(cusId)
{
	if(window.dialogArguments){
		var win = window.dialogArguments.window;
		if(win.top.tape){
			win.top.tape(cusId);
		}
	}
	else {
		if(window.top.tape)
		{
			window.top.tape(cusId);
		}
	}
}

//编辑短信
function toEditSms(basicInfoId,isTiming){
	//判断增值账号是否ok
	jQuery.ajax({
		type: "post",
		async: false,
		url: "../sms/getSmsChannelLeastLength.html?random="+Math.random()*10000,
		data: {},
		success: function(data){
			var json = jQuery.parseJSON(data); 	
			if (json.callResult != "Success"){
				banger.page.showMessageBox(json.callResult);
			} else {
				if(isTiming == 1){
					var urls="../sms/toEditSms.html?basicInfoId=" + basicInfoId + "&smsLeastLength=" + json.smsLeastLength;
					var options = {id: "toEditSms" + basicInfoId, pid: GetId(), title: "编辑定时发送短信", url: urls, lock: false};
					tab.add(options);
				}else{
					var urls="../sms/toEditSms.html?basicInfoId=" + basicInfoId + "&smsLeastLength=" + json.smsLeastLength;
					var options = {id: "toEditSms" + basicInfoId, pid: GetId(), title: "编辑立即发送短信", url: urls, lock: false};
					tab.add(options);
				}
			}
		}
	});
}

//新建短信 
function sendSms(phoneNumber, cusId){
	if((jQuery.trim(phoneNumber)=='') && ((jQuery.trim(cusId)=='') || (jQuery.trim(cusId)=='-1') || (jQuery.trim(cusId)=='0'))){
		banger.page.showMessageBox("无效的号码，不能发送短信");
	}else{
		//特殊处理新建短信按钮
		if(jQuery.trim(cusId) == '-2'){
			cusId = '';
		}
		//判断增值账号是否ok
		jQuery.ajax({
			type: "post",
			async: false,
			url: "../sms/getSmsChannelLeastLength.html?random="+Math.random()*10000,
			data: {},
			success: function(data){
				var json = jQuery.parseJSON(data); 	
				if (json.callResult != "Success"){
					banger.page.showMessageBox(json.callResult);
				} else {
					var pNumber = jQuery.trim(phoneNumber);
					var urls="../sms/toAddSms.html?customerId=" + cusId + "&phoneNumber=" + pNumber + "&smsLeastLength=" + json.smsLeastLength;
					var options = {id: "toSendSms" + cusId + pNumber, pid: GetId(), title: "新建短信", url: urls, lock: false};
					tab.add(options);
				}
			}
		});
	}
}

//彩信
function sendMms(phoneNumber, cusId){
	if((jQuery.trim(phoneNumber)=='') && ((jQuery.trim(cusId)=='') || (jQuery.trim(cusId)=='-1') || (jQuery.trim(cusId)=='0'))){
		banger.page.showMessageBox("无效的号码，不能发送彩信");
	}else{
		//判断增值账号是否ok
		jQuery.ajax({
			type: "post",
			async: false,
			url: "../mms/hasMmsAccount.html?random="+Math.random()*10000,
			data: {},
			success: function(data){
				if (data != "Success"){
					banger.page.showMessageBox(data);
				} else {
					var urls="../mms/toAddMms.html?type=new_from&phoneNumber=" + phoneNumber + "&customerId=" + cusId + "&random="+Math.random()*10000;
					var idid = "mms_add_from" + jQuery.trim(phoneNumber) + jQuery.trim(cusId);
					var options = {id: idid, pid: GetId(), title: "新建彩信", url: urls, lock: false};
					tab.add(options);
				}
			}
		});
	}
}