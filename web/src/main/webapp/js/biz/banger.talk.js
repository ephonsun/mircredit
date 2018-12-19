function dialInput(query,change)		//拨号
{
	inputRule(query,function(text){
		if(text!="")
		{
			var patrn=/^[a-zA-Z0-9_*#\u4e00-\u9fa5]+$/;
			if(!patrn.exec(text))return false;
			return true;
		}
		return true;
	},false,function(param){if(change && param["oldText"]!=param["newText"])change();});
}

function dial(num,cusId,tskId){			//拨号
	var phone = sp.getCurrent();
	var id = cusId+"";
	var taskId = tskId+"";
	if(phone)
	{
		if(num)
		{
			if(num.length>30){
				banger.page.showMessageBox("你拨打号码 "+num+" 太长，不得超过30位!");
				return;
			}
			try{
				if(getDialEnable()){
					phone.dial(num);
					if(id!="" && parseInt(id)>0)sp.setDialCustomerId(cusId);
					if(taskId!="" && parseInt(taskId)>0)sp.setDialTalkTaskId(taskId);//存放任务执行Id
				}
			}
			catch(e)
			{
			}
		}
		else banger.page.showMessageBox("拨打的电话号码不能为空!");
	}
	else
	{
		if(sp.test())
		{
			banger.page.showMessageBox("无可用的智能话机，无法使用来电弹屏、软件拨号、电话录音等智能语音功能!");
		}
		else
		{
			banger.page.showMessageBox("没有安装话机组件!");
		}
	}
}

function tape(cusId){				//座谈
	var phone = sp.getCurrent();
	var id = cusId+"";
	if(phone)
	{
		try{
			if(getAnswerEnable()){
				phone.startRecord();
				if(id!="" && parseInt(id)>0)sp.setRecordCustomerId(cusId);
			}
		}
		catch(e)
		{
		}
	}
	else
	{
		if(sp.test())
		{
			banger.page.showMessageBox("无可用的智能话机，无法使用来电弹屏、软件拨号、电话录音等智能语音功能!");
		}
		else
		{
			banger.page.showMessageBox("没有安装话机组件!");
		}
	}
}

function hangup(){			//挂机
	var phone = sp.getCurrent();
	if(phone)
	{
		try{
			phone.handup();
		}
		catch(e)
		{
		}
	}
}

function setAutoPlay(flag)
{
	try{
		var phone = sp.getCurrent();
		if(phone)
		{
			phone.setRecordTipSwitch(flag);
		}
	}
	catch(e)
	{
	}
}

function setAutoRecord(flag)			//设置自动录音
{
	try{
		var phone = sp.getCurrent();
		if(phone)
		{
			phone.setAutoRecord(flag);
		}
	}
	catch(e)
	{
	}
}

function getAnswerEnable(){				//是否答录状态
	var phone = getPhone();
	if(phone){
		var status = phone.getStatus();
		if(status){
			var json = jQuery.parseJSON(status);
			return !(json["dialing"] || json["onLine"] || json["hookUp"] || json["answering"] || json["spkOn"] || json["ringing"] || json["recording"]);
		}
	}
	return false;
}

function getAnswerSwitchEnable(){		//是否可以设置答录状态
	var phone = getPhone();
	if(phone){
		var status = phone.getStatus();
		if(status){
			var json = jQuery.parseJSON(status);
			return !(json["dialing"] || json["onLine"] || json["hookUp"] || json["answering"] || json["spkOn"] || json["ringing"]);
		}
	}
	return false;
}

function openPhoneAnswer()			//开启答录
{
	try{
		var phone = sp.getCurrent();
		if(phone)
		{
			phone.setAnsweringMode(true);
		}
	}
	catch(e)
	{
	}
}

function closePhoneAnswer()			//开启答录
{
	try{
		var phone = sp.getCurrent();
		if(phone)
		{
			phone.setAnsweringMode(false);
		}
	}
	catch(e)
	{
	}
}

function setPhoneParam(config){				//设置话机参数
	try{
		var phone = sp.getCurrent();
		if(phone)
		{
			if(config.flashBreakTime!="" && parseInt(config.flashBreakTime)>0)phone.setRKeyDefTimes(config.flashBreakTime);			//闪断时间
			if(config.outDelay!="" && parseInt(config.outDelay)>0)phone.setOutDelayedTime(config.outDelay);
			if(config.ringCount!="" && parseInt(config.ringCount)>0)phone.setAnsweringRingTimes(config.ringCount);
			if(config.outsideCallCode!="")phone.setOutlineNumber(config.outsideCallCode);
		}
	}
	catch(e)
	{
	}
}

function getPersonalConfig(){			//得到写入话机的个人配置
	try{
		return sp.getPersonalConfig();
	}
	catch(e)
	{
	}
}

function setGetWord(flag){				//设置屏幕取词
	try{
		return sp.setGetWord(flag);
	}
	catch(e)
	{
	}
}

function setPersonalConfig(config)
{
	try{
		var phone = sp.getCurrent();
		if(phone)
		{
			var configString = $.toJsonString(config);
			phone.setPersonalConfig(configString);
		}
	}
	catch(e)
	{
	}
}

function getPersonalConfig()
{
	try{
		var phone = sp.getCurrent();
		if(phone)
		{
			return phone.getPersonalConfig();
		}
	}
	catch(e)
	{
	}
}

function changePhoneConfig(config){			//
	try{
		setPhoneConfig(config);
	}
	catch(e)
	{
	}
}

function setPhoneWanFile(config){		//设置话机文件
	try{
		var pc = getPersonalConfig();
		var files = {};
		var addr = getUrlServer();
		var pcc= (pc)?$.parseJSON(pc):null;
		if(config.answerFileVersion!=null && config.answerFileVersion!="")
		{
			if(!pcc || pcc.answerFileVersion!=config.answerFileVersion)
			{
				files["AnswerFileUrl"] = "http://"+addr+"/getConfig?type=answer&id="+config.answerConfigId;
				files["IsAnsweringMute"] = config.isMute?"true":"false";
			}
		}
		if(config.tipFileVersion!=null && config.tipFileVersion!="")
		{
			if(!pcc || pcc.tipFileVersion!=config.tipFileVersion)
			{
				files["RecTipFileUrl"] = "http://"+addr+"/getConfig?type=remind&id="+config.remindConfigId;
			}
		}
		if(config.ringFileVersion!=null && config.ringFileVersion!="")
		{
			if(!pcc || pcc.ringFileVersion!=config.ringFileVersion)
			{
				files["RingFileUrl"] = "http://"+addr+"/getConfig?type=ring&id="+config.ringConfigId;
			}
		}

		var fileStr = "";
		if(files.AnswerFileUrl)fileStr="AnswerFileUrl:"+files.AnswerFileUrl+",IsAnsweringMute:"+files.IsAnsweringMute;
		if(files.RecTipFileUrl)fileStr+=(fileStr!="")?",RecTipFileUrl:"+files.RecTipFileUrl:"RecTipFileUrl:"+files.RecTipFileUrl;
		if(files.RingFileUrl)fileStr+=(fileStr!="")?",RingFileUrl:"+files.RingFileUrl:"RingFileUrl:"+files.RingFileUrl;
		if(fileStr!="")downPhoneFile(fileStr);
	}
	catch(e)
	{
	}
}

function downPhoneFile(fileStr){			//得到写入话机的个人配置
	try{
		var phone = sp.getCurrent();
		if(phone)
		{
			phone.downWavFile(fileStr);
		}
	}
	catch(e)
	{
	}
}

function setPhoneConfig(config){		//设置话机配置
	setAutoRecord(config.autoRecord);
	setAutoPlay(config.autoPlay);
	setPhoneParam(config);
	setPhoneWanFile(config);
	setPersonalConfig(config);				//保存配置到话机
	
	setGetWord(config.openWord);			//设置屏幕取词
	fn.switcher('words',config.openWord?"关闭屏幕取词":"打开屏幕取词");
	setConfigParams("talkForm","autoOpen",config.openTalkWin);
	fn.switcher('call',config.openTalkWin?"关闭通话窗口":"打开通话窗口");
}

function setPhoneConfigFile(config){
	var server = config.serverAddress;
	var port = config.serverPort;
	var addr = (port)?server+":"+port:server;
	var transport = config.transport;
		
	setConfigParams("socketService","address",server);
	setConfigParams("socketService","port",transport);
	setConfigParams("socketService","timeout","4000");
	
	setConfigParams("httpService","address",server);
	setConfigParams("httpService","port",port);

	setConfigParams("zeroMQ","enable","false");

	setConfigParams("webService","url","http://"+addr+config["usrRef"]+"/services/BangerCrmService");
	setConfigParams("talkForm","url",config["usrRef"]+"/talk/getCallInPage.html?autoLogin=true&talktype=%s&account=%s&number=%s&cusId=%s&code=%s&taskId=%s");
	
	setConfigParams("incomeForm","autoOpen","true");
	setConfigParams("incomeForm","url",config["usrRef"]+"/talk/getTalkIncomingPopup.html?autoLogin=true&account=%s&number=%s");
	setConfigParams("unreadMessage","autoOpen","true");
	setConfigParams("unreadMessage","url",config["usrRef"]+"/login/initMessage.html?autoLogin=true&account=%s");
	
	setConfigParams("getWordForm","autoOpen","true");
	setConfigParams("getWordForm","url",config["usrRef"]+"/talk/getWordsPage.html?autoLogin=true&account=%s&number=%s");
	
	setConfigParams("userConfig","timeout","300000");
	setConfigParams("userConfig","refreshMsgTime","300000");
	
	setConfigParams("serviceUrl","smartPhoneUpgradeUrl",config["usrRef"]+"/upgrade/isPhoneNeedUpgrade.html?version=%s");
	setConfigParams("serviceUrl","trayAppUpgradeUrl",config["usrRef"]+"/upgrade/isTrayAppNeedUpgrade.html?version=%s");
}

function setPhoneInfo(config)				//设置话机登入帐号和配置
{
	setPhoneConfig(config);
	setPhoneConfigFile(config);
	
	var account = $("#loginAccount").val();
	sp.login(account);
}

function setConfigParams(op,key,val)
{
	if(sp)
	{
		sp.setConfig(op,key,val);
	}
}

function getPhone()					//得到话机对像
{
	try
	{
		if(sp.test())
		{
			var phone = sp.getCurrent();
			return phone;
		}
		else
		{
			banger.page.showMessageBox("没有安装话机组件!");
			return null
		}
	}
	catch(e)
	{
		banger.page.showMessageBox("无可用的智能话机，无法使用来电弹屏、软件拨号、电话录音等智能语音功能!");
	}
}

function getDialEnable(){				//判断当前话机是否可以拨号
	var phone = sp.getCurrent();
	if(phone){
		var status = phone.getStatus();
		if(status){
			var json = jQuery.parseJSON(status);
			return !(json["dialing"] || json["onLine"]);
		}
	}
	return true;
}

var tempCusId = null;
		
function postDial(number,cusId,account,taskId)			//带号码规则的拨号
{
	if(!getDialEnable())return;
	tempCusId = (cusId!=null)?cusId:"";
	var loginAccount = (account!=null)?account+"":"";
	var autoLogin = (loginAccount!="")?"&autoLogin=true&account="+encodeURI(encodeURI(loginAccount)):"";
	var id = tempCusId;
	if(number)
	{
		if(!inChars(number,"0123456789*#") || !hasChars(number,"0123456789"))
		{
			setTimeout(function(){
				banger.page.showMessageBox("无效号码，不能拨号!");
			},100);
			return;
		}
		var url = "../dailNumber/getDialNumber.html?number="+number.replace(/[#]/g,"%23").replace(/[*]/g,"%2a")+"&cusId="+id+autoLogin+"&RandNum="+Math.random();
		jQuery.post(url,{},
			function(data){
				var obj = jQuery.parseJSON(data);
				var tel = obj.phoneNumber;
				if(obj.needShowLastContact){
					//弹出上次联系框
                    if (taskId > 0 && cusId <= 0) {
                        //如果是任务执行页面，非在行不需要弹出上次联系
                        if(tel)
                        {
                            dial(tel,obj.cusId,taskId);
                        }
                        else banger.page.showMessageBox("无效号码，不能拨号!");
                    } else {
                        showLastContact(obj,number,autoLogin,taskId);
                    }

				}else{
					if(tel)
					{
						dial(tel,obj.cusId,taskId);
					}
					else banger.page.showMessageBox("无效号码，不能拨号!");
				}
			});
	}
	else banger.page.showMessageBox("空号码无效，不能拨号!");
}
//弹出上次联系情况
function showLastContact(obj,number,autoLogin,taskId){
	var postJson = {};
	var tel = obj.phoneNumber;
	//var cusId = (obj.cusId!=null)?obj.cusId:"";
	var cusId = obj.cusId;
	var url = "../dailNumber/showLastContact.html?cusId=" + cusId + "&number=" + number + autoLogin + "&RandNum="+ Math.random();
	var result=banger.page.openDialog(url,{"msg":"上次联系信息"},
		'dialogWidth=1000px;dialogHeight=550px;toolbar=no;menubar=no;scrollbars=no;resizable=no;location=no;status=no');
	if(result!=undefined){
		if(result=='ok')
		{
			dial(tel,tempCusId,taskId);
		}
	}
}
//初始化话机配置
function initPhoneConfig(phone,config)
{
	var pcStr = phone.getConfig();
	if(pcStr)
	{
		var pc = $.parseJSON(pcStr);
		if(pc)
		{
			var uid = pc["userId"];
			if(uid!=config["userId"])		//用户ID不同，换话机
			{
				for(var nm in config)
				{
					pc[nm] = config[nm];
				}
			}
		}
	}
	else
	{
		var did=newGuid();
		config["driveId"]=did;
		config["isDefault"]=true;
		config["lineNumber"]="";
	}
}

function formatTime(d,formatter)
{
	if(!formatter || formatter == "")
    {
        formatter = "hh:mm:ss";
    }
	var hour = d.getHours()+"";
    var minute = d.getMinutes()+"";
    var second = d.getSeconds()+"";
    
    var hourMarker = formatter.replace(/[^h|H]/g,'');
    if(hourMarker.length > 1)
    {
        if(hour.length == 1) 
        {
        	hour = "0" + hour;
        }
    }    
    var minuteMarker = formatter.replace(/[^m|M]/g,'');
    if(minuteMarker.length > 1)
    {
        if(minute.length == 1) 
        {
            minute = "0" + minute;
        }
    }    
    var secondMarker = formatter.replace(/[^s|S]/g,'');
    if(secondMarker.length > 1)
    {
        if(second.length == 1) 
        {
            second = "0" + second;
        }
    }
    return formatter.replace(hourMarker,hour).replace(minuteMarker,minute).replace(secondMarker,second);
}

//客户端和服务端的时间差
var syscTimeTick = -1;

//任务管理
var taskManager={
	initTasks:function(taskIds)		//初始计时器任务
	{
		for(var i=0;i<taskIds.length;i++)
		{
			var taskId = taskIds[i];
			var task = taskManager[taskId];
			if(task)
			{
				talkTimer.add(task.config,task.handler);
			}
		}
	}
	,timerTask:{
		config:{id:"timerTask",rate:30000,delay:10000}
		,handler:function()			//向服务端请求最新时间
		{
			jQuery.post("../login/getServiceTime.html?random="+Math.random()*1000000,null,
				function(timeVal){
				 	var d = new Date(1970,0,1);
        			d.setMilliseconds(parseInt(timeVal));
        			var timeStr = formatTime(d);
        			$("#serverTime").text(timeStr);
				});
		}
	}
	,statusTask:{
		config:{id:"statusTask",rate:5000,delay:10000}
		,handler:function()			//时时通知话机客户端登入状态
		{
			setTimeout(function(){
    			try{
		    		var account= $("#loginAccount").val();
					if(sp)
					{
						sp.heartBeat(account);
					}
				}
				catch(e){
				}
			},200);
		}
	}
}

//通话计时器
var talkTimer={
	status:{}
	,add:function(config,fn)
	{
		if(config)
		{
			if(config.dealy)
			{
				window.setTimeout(function(){
					var timeHandler = window.setInterval(fn,config.rate);
					talkTimer.status[config.id]=timeHandler;
					},config.dealy);
			}
			else
			{
				var timeHandler = window.setInterval(fn,config.rate);
				talkTimer.status[config.id]=timeHandler;
			}
		}
	}
	,remove:function(taskId)
	{
		var timeHandler = talkTimer.status[taskId];
		if(timeHandler)
		{
			window.clearInterval(timeHandler);
		}
		talkTimer.status[taskId]=null;
	}
}