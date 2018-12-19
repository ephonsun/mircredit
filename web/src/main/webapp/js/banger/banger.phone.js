var smartPhone={
	phones:{},ocx:null
	,events:function()
	{
		this.onPickup="";		// 摘机事件
		this.onHandup="";		// 挂机事件
		this.onTalkBegin="";	// 通话开始
		this.onTalkEnd="";		// 通话结束
	}
	,object:function()			// ocx对像
	{
		this.ocx = null; 
		this.setting ={events:{}};
		this.build=function()
		{
			document.write("<OBJECT ID=\"smartPhone\" CLASSID=\"clsid:FCFBD786-D97B-4BE4-917F-625FA607EAFB\" ></Object>");
			this.ocx = document.getElementById("smartPhone");
			// document.body.appendChild(this.element);
		};
		this.load=function(setting)		// 读取配置
		{
			this.setting=setting;
		};
		this.test=function()			// 测试有没有安装ocx控件
		{
			try
			{
				return this.ocx.testOcx();
			}
			catch(e)
			{
				return false;
			}
		};
		this.login=function(account)
		{
			try
			{
				this.ocx.login(account);
			}
			catch(e)
			{
			}
		};
		this.logout=function(account)
		{
			try
			{
				this.ocx.logout(account);
			}
			catch(e)
			{
			}
		};
		this.writeLog=function(logInfo)
		{
			try
			{
				this.ocx.debugLog(logInfo);
			}
			catch(e)
			{
			}
		};
		this.addEvent=function(name,callback)		// 注册ocx事件
		{
			if(this.ocx.attachEvent)
			{
				this.ocx.attachEvent(name,callback);
			}
			else if(this.ocx.addEventListener)
			{
				this.ocx.addEventListener(name,callback,true);
			}
		};
		this.getCurrent=function()				// 得到当前话机
		{
			this.reloadPhones();
			for(var nm in smartPhone.phones)
			{
				var phone = smartPhone.phones[nm];
				if(phone && phone.actived)
				{
					return phone;
				}
			}
		};
		this.setGetWord=function(flag)				// 设置屏幕取词
		{
			try{
				this.ocx.setIsGetWord(flag);
			}
			catch(e){
			}
		}
		this.resizeWordHeight=function(size)			// 重置
		{
			try{
				this.ocx.setGetWordFormHeight(size);
			}
			catch(e){
			}
		}
		this.setConfig=function(option,key,val)		// 设置配置信息
		{
			try{
				this.ocx.setConfig(option,key,val);
			}
			catch(e){
			}
		};
		this.initOcx=function(){// 初始化OCX控件，供主界面调用
			try{
				this.ocx.exceuteCmd("initOcx","");
			}
			catch(e){
			}
		};
		this.refreshMessageTip=function()				// 刷新未读消息提醒
		{
			try{
				this.ocx.exceuteCmd("refreshMsgTip","");
			}
			catch(e){
			}
		};
		this.setDialCustomerId=function(cusId)
		{
			try{
				this.ocx.exceuteCmd("setTalkCustId",cusId);
			}
			catch(e){
			}
		};
		this.setDialTalkTaskId=function(tskId)
		{
			try{
				this.ocx.exceuteCmd("setTalkTaskId",tskId);
			}
			catch(e){
			}
		};
		this.setRecordCustomerId=function(cusId)
		{
			try{
				this.ocx.exceuteCmd("setRecordCustId",cusId);
			}
			catch(e){
			}
		};
		this.raiseCommand=function(type,context)
		{
			try{
				this.ocx.fireCallBackEvent(type,context);
			}
			catch(e){
			}
		};
		this.getAllSmartPhone=function()
		{
			try{
				return this.ocx.getAllSmartPhone();
			}
			catch(e){
				return "";
			}
		};
		this.reloadPhones=function()				// 重置所有话机
		{
			var deviceIds = this.getAllSmartPhone();
			
			var ids =(deviceIds)?deviceIds.split(","):[];
			var phones={};
			
			for(var i=0;i<ids.length;i++)
			{
				var id=ids[i];
				var phone = smartPhone.phones[id];
				if(phone)
				{
					phone.actived=true;
				}
				else
				{
					phone = this.createPhone(id);
				}
				phones[id] = phone;
			}
			
			smartPhone.phones = phones;
		};
		this.createPhone=function(deviceId)			// 创建话机对像
		{
			var phone = new smartPhone.phone(deviceId);
			phone.ocx = this.ocx;
			if(this.setting)
			{
				phone.setting = this.setting;
				if(this.setting.events)
				{
					for(var nm in this.setting.events)
					{
						phone.events[nm]=this.setting.events[nm];
					}
				}
			}
			return phone;
		};
		this.heartBeat=function(account)			// 时时通知电话客户端在线状态
		{
			try{
				this.ocx.heartBeat(account);
			}
			catch(e){
			}
		};
		this.raiseEvent=function(name,args)
		{
			var eventName = this.setting.events[name];
			if(eventName)
			{
				var callback = null;
				try
				{
					callback = eval(eventName);
				}
				catch(e)
				{
					alert("找不到回调函数"+eventName);
				}
				if(callback)
				{
					callback(args,this);
				}
			}
		};
		this.destory=function()						// 注销ocx对像
		{
			this.ocx.destory();
			smartPhone.phones={};
		};
	}
	,phone:function(id)
	{
		this.deviceId=id;			// 设备Id
		this.actived=true;			// 话机是否可用
		this.status="";				// 设备状态
		this.events=new smartPhone.events();
		this.ocx=null;			// ocx的object对像
		
		this.pickup=function()		// 摘机
		{
			if(this.ocx)
			{
				try{
					this.ocx.pickup(this.deviceId);
				}
				catch(e){
				}
			}
		};
		this.getPersonalConfig=function()			// 得到个人话机配置
		{
			try{
				return this.ocx.getPersonalConf(this.deviceId);
			}
			catch(e){
			}
		};
		this.setPersonalConfig=function(config)			// 写入个人话机配置
		{
			try{
				this.ocx.setPersonalConf(this.deviceId,config);
			}
			catch(e){
			}
		};
		this.handup=function()		// 挂机
		{
			if(this.ocx){
				try{
					this.ocx.hangup(this.deviceId);
				}
				catch(e){
				}
			}
		};
		this.dial=function(num)		// 拨号
		{
			if(this.ocx){
				try{
					this.ocx.dial(this.deviceId,num);
				}
				catch(e){
				}
			}
		};
		this.continuedDial=function(num)		// 继拨分机
		{
			if(this.ocx){
				try{
					this.ocx.continuedDial(this.deviceId,num);
				}
				catch(e){
				}
			}
		};
		this.dialExt=function(num)	// 来电转接
		{
			if(this.ocx){
				try{
					this.ocx.redirectExt(this.deviceId,num);
				}
				catch(e){
				}
			}
		};
		this.stopScene=function()		// 停止现场录音
		{
			if(this.ocx){
				try{
					this.ocx.stopPhoneRecord(this.deviceId);
				}
				catch(e){
				}
			}
		};
		this.setAnsweringMode=function(flag)		// 设置答录
		{
			if(this.ocx){
				try{
					this.ocx.setAnsweringMode(this.deviceId,flag);
				}
				catch(e){
				}
			}
		};
		this.startRecord=function()	   // 开始录音
		{
			if(this.ocx){
				try{
					this.ocx.startPhoneRecord(this.deviceId);
				}
				catch(e){
				}
			}
		};
		this.setAutoRecord=function(flag)			// 自动录音
		{
			if(this.ocx){
				try{
					this.ocx.setAutoRecord(this.deviceId,flag);
				}
				catch(e){
				}
			}
		};
		this.checkAnswerWavValid=function(filename)		// 校验录音文件
		{
			if(this.ocx){
				try{ 
					return this.ocx.checkAnswerWavValid(filename);
				}
				catch(e)
				{
					return false;
				}
			}
		};
		this.setAnsweringVoice=function(filename,answerMute)		// 设置答录音
		{
			if(this.ocx){
				try{
					this.ocx.setAnsweringVoice(this.deviceId,filename,answerMute);
				}
				catch(e)
				{
					var error = e+"";
					if(error.indexOf("Error:") > -1){
						return error.replace("Error:","");
					}
					return error;
				}
			}
		};
		this.setAnsweringRingTimes=function(ringTimes)				// 设置答录振铃次数
		{
			if(this.ocx){
				try{
					this.ocx.setAnsweringRingTimes(this.deviceId,ringTimes);
				}
				catch(e){
				}
			}
		};
		this.setRingFile=function(filename)				// 写入话机铃声
		{
			if(this.ocx){
				try{
					this.ocx.setRingFile(this.deviceId,filename);
				}
				catch(e){
					var error = e+"";
					if(error.indexOf("Error:") > -1){
						return error.replace("Error:","");
					}
					return error;
				}
			}
		};
		this.setRecordTipFile=function(filename)		// 写入录音提示音文件
		{
			if(this.ocx){
				try{
					this.ocx.setRecordTipFile(this.deviceId,filename);
				}
				catch(e){
					var error = e+"";
					if(error.indexOf("Error:") > -1){
						return error.replace("Error:","");
					}
					return error;
				}
			}
		};
		this.setRecordTipSwitch=function(flag)		// 开启录音提示
		{
			if(this.ocx){
				try{
					this.ocx.setRecordTipSwitch(this.deviceId,flag);
				}
				catch(e){
				}
			}
		};
		this.uploadRecord=function(recordId)	// 上传录音
		{
			if(this.ocx){
				try{
					this.ocx.uploadRecord(this.deviceId,recordId);
				}
				catch(e){
				}
			}
		};
		this.setRKeyDefTimes=function(time)		// 闪断时间
		{
			if(this.ocx){
				try{
					this.ocx.setRKeyDefTimes(this.deviceId,time);
				}
				catch(e){
				}
			}
		};
		this.setOutDelayedTime=function(delay)		// 出局延时
		{
			if(this.ocx){
				try{
					this.ocx.setOutDelayedTime(this.deviceId,delay);
				}
				catch(e){
				}
			}
		};
		this.setOutlineNumber=function(num)			// 外线加拨号
		{
			if(this.ocx){
				try{
					this.ocx.setOutlineNumber(this.deviceId,num);
				}
				catch(e){
				}
			}
		};
		this.setConfig=function(config)				// 设置话机配置信息
		{
			if(this.ocx){
				try{
					this.ocx.setPersonalConf(this.deviceId,config);
				}
				catch(e){
				}
			}
		};
		this.getConfig=function()					// 得到个人配置
		{
			if(this.ocx){
				try{
					return this.ocx.getPersonalConf(this.deviceId,config);
				}
				catch(e){
				}
			}
		};
		this.getStatus=function()					// 得到话机状态
		{
			if(this.ocx){
				try{
					return this.ocx.getPhoneState(this.deviceId);
				}
				catch(e){
				}
			}
		};
		this.downWavFile=function(params)
		{
			if(this.ocx){
				try{
					return this.ocx.syncAudioFile(this.deviceId,params);
				}
				catch(e){
				}
			}
		};
		this.raiseEvent=function(name,args)
		{
			var eventName = this.events[name];
			if(eventName)
			{
				var callback = null;
				try
				{
					callback = eval(eventName);
				}
				catch(e)
				{
					alert("找不到回调函数"+eventName);
				}
				if(callback)
				{
					callback(args,this);
				}
			}
		};
	}
	,create:function()
	{
		if(!this.ocx)
		{
			this.ocx = smartPhone.fn.create();
		}
		return this.ocx;
	}
	,destory:function()
	{
		if(this.ocx)
		{
			try{
				this.ocx.destory();
				this.ocx=null;
			}
			catch(e){}
		}
	}
	,fn:{
		create:function()
		{
			var ocx=new smartPhone.object();
			ocx.build();
			this.addEvents(ocx);
			return ocx;
		}
		,addEvents:function(ocx)
		{
			ocx.addEvent("OnPickup",function(deviceid){
				var phone= smartPhone.fn.getPhoneById(deviceid);
				if(phone)smartPhone.fn.pickupCallback(phone,{});
			});
			ocx.addEvent("OnHandup",function(deviceid){
				var phone= smartPhone.fn.getPhoneById(deviceid);
				if(phone)smartPhone.fn.handupCallback(phone,{});
			});
			ocx.addEvent("OnIncomeTalkBegin",function(deviceid,number,talkBeginTime,ringBeginTime){
				var phone= smartPhone.fn.getPhoneById(deviceid);
				if(phone)smartPhone.fn.talkBenginCallback(phone,{"deviceid":deviceid,"type":"incoming","number":number,"beginTime":talkBeginTime,"ringBeginTime":ringBeginTime});
			});
			ocx.addEvent("OnIncomeTalkEnd",function(deviceid,number,talkBeginTime,talkEndTime,ringBeginTime){
				var phone= smartPhone.fn.getPhoneById(deviceid);
				if(phone)smartPhone.fn.talkEndCallback(phone,{"deviceid":deviceid,"type":"incoming","number":number,"beginTime":talkBeginTime,"endTime":talkEndTime,"ringBeginTime":ringBeginTime});
			});
			ocx.addEvent("OnDialTalkBegin",function(deviceid,number,talkBeginTime){
				var phone= smartPhone.fn.getPhoneById(deviceid);
				if(phone)smartPhone.fn.talkBenginCallback(phone,{"deviceid":deviceid,"type":"dial","number":number,"beginTime":talkBeginTime});
			});
			ocx.addEvent("OnDialTalkEnd",function(deviceid,number,talkBeginTime,talkEndTime){
				var phone= smartPhone.fn.getPhoneById(deviceid);
				if(phone)smartPhone.fn.talkEndCallback(phone,{"deviceid":deviceid,"type":"dial","number":number,"beginTime":talkBeginTime,"endTime":talkEndTime});
			});
			ocx.addEvent("OnCancelIncome",function(deviceid,number,talkBeginTime,extension,ringBeginTime,ringEndTime){
				var phone= smartPhone.fn.getPhoneById(deviceid);
				if(phone)smartPhone.fn.talkMessageAnswer(phone,{"deviceid":deviceid,"number":number,"beginTime":talkBeginTime,"extension":extension,"ringBeginTime":ringBeginTime,"ringEndTime":ringEndTime});
			});
			ocx.addEvent("OnAnsweringEnd",function(deviceid,number,answerBeginTime,answerEndTime){
				var phone= smartPhone.fn.getPhoneById(deviceid);
				if(phone)smartPhone.fn.talkMessageAnswer(phone,{"deviceid":deviceid,"number":number,"answerBeginTime":answerBeginTime,"answerEndTime":answerEndTime});
			});
			ocx.addEvent("OnSmartPhoneChange",function(deviceid,op){
				smartPhone.fn.phoneDeviceChange(ocx,{"deviceid":deviceid,"op":op});
			});
			ocx.addEvent("OnStartSpotRecord",function(deviceid,recordId,ARecBeginTime){
				var phone= smartPhone.fn.getPhoneById(deviceid);
				if(phone)smartPhone.fn.talkBenginCallback(phone,{"deviceid":deviceid,"type":"spotRec","code":recordId,"beginTime":ARecBeginTime});
			});
			ocx.addEvent("OnStopSpotRecord",function(deviceid,recordId,beginTime,endTime){
				var phone= smartPhone.fn.getPhoneById(deviceid);
				if(phone)smartPhone.fn.talkEndCallback(phone,{"deviceid":deviceid,"type":"spotRec","code":recordId,"beginTime":beginTime,"endTime":endTime});
			});
			ocx.addEvent("OnHookUp",function(deviceid,hookUp){
				var phone= smartPhone.fn.getPhoneById(deviceid);
				if(phone)smartPhone.fn.hookUpCallback(phone,{"deviceid":deviceid,"hookUp":hookUp});
			});
			ocx.addEvent("OnAnsweringChanged",function(deviceid,state){
				var phone= smartPhone.fn.getPhoneById(deviceid);
				if(phone)smartPhone.fn.changePhoneAnswering(phone,{"deviceid":deviceid,"state":state});
			});
			ocx.addEvent("OnRecordUploadEnd",function(recordId,fileName){
				smartPhone.fn.fileUploadBefore(ocx,{"recordId":recordId,"fileName":fileName});
			});
			ocx.addEvent("OnHeartBeatStop",function(){
				smartPhone.fn.heartBeatStop(ocx,{});
			});
			ocx.addEvent("OnLogout",function(account){
				smartPhone.fn.logoutBack(ocx,{"account":account});
			});
			ocx.addEvent("OnLogin",function(account){
				smartPhone.fn.loginBack(ocx,{"account":account});
			});			
			ocx.addEvent("OnCallBack",function(commondType,commondContext){
				smartPhone.fn.commondCallBack(ocx,{type:commondType,context:commondContext});
			});
			ocx.addEvent("OnHeartBeat",function(){
				smartPhone.fn.heartBeat(ocx,{});
			});
			ocx.addEvent("OnUpgrade",function(){
				smartPhone.fn.upgradeCallBack(ocx,{});
			});
			ocx.addEvent("OnTrayAppRun",function(){
				smartPhone.fn.trayAppRun(ocx,{});
			});
		}
		,getPhoneById:function(deviceId)			// 根据设备Id得到话机对像
		{
			smartPhone.ocx.reloadPhones();
			return smartPhone.phones[deviceId];
		}
		,handupCallback:function(phone,args)			// 挂机回调
		{
			phone.raiseEvent("onHandup",args);
		}
		,pickupCallback:function(phone,args)			//
		{
			phone.raiseEvent("onPickup",args);
		}
		,hookUpCallback:function(phone,args)			// 话柄事件
		{
			phone.raiseEvent("onHookUp",args);
		}
		,talkBenginCallback:function(phone,args)			
		{
			phone.raiseEvent("onTalkBegin",args);
		}
		,talkEndCallback:function(phone,args)
		{
			phone.raiseEvent("onTalkEnd",args);
		}
		,talkMessageAnswer:function(phone,args)
		{
			phone.raiseEvent("onMessageAnswer",args);
		}
		,phoneDeviceChange:function(ocx,args)			// 插拔话机
		{
			ocx.raiseEvent("onPhoneDeviceChange",args);
		}
		,changePhoneAnswering:function(phone,args)
		{
			phone.raiseEvent("onAnsweringChange",args);
		}
		,fileUploadBefore:function(ocx,args)			// 文件开始上传
		{
			ocx.raiseEvent("onFileUploadBefore",args);
		}
		,heartBeatStop:function(ocx,args)			// 停止在线心跳
		{
			ocx.raiseEvent("onHeartBeatStop",args);
		}
		,logoutBack:function(ocx,args)
		{
			ocx.raiseEvent("onLogout",args);
		}
		,loginBack:function(ocx,args)
		{
			ocx.raiseEvent("onLogin",args);
		}
		,commondCallBack:function(ocx,args)			// 执行回调
		{
			ocx.raiseEvent("onCommand",args);
		}
		,heartBeat:function(ocx,args)				// 心跳事件
		{
			ocx.raiseEvent("onHeartBeat",args);
		}
		,upgradeCallBack:function(ocx,args)
		{
			ocx.raiseEvent("onUpgrade",args);
		}
		,trayAppRun:function(ocx,args)				// 启动托盘事件
		{
			ocx.raiseEvent("onTrayAppRun",args);
		}
	}
}