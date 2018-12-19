var count = 0;
var sccCount = 0;
function setUpload() {
	var filenames = "";
	$("input[name='fileInput']").uploadify({

		/*注意前面需要书写path的代码*/
		'uploader' : '../uploadify/uploadify.swf',
		'script' : '../tskContact/uploadFile.html',
		'cancelImg' : '../uploadify/cancel.png',
		'queueID' : 'fileQueue', //和存放队列的DIV的id一致
		'fileDataName' : 'fileInput', //和以下input的name属性一致
		'auto' : false, //是否自动开始
		'multi' : true, //是否支持多文件上传
		//'hideButton'     : true, //隐藏浏览按钮的图片
		'buttonText' : escape('添加附件'),//浏览按钮上的文字
		'buttonImg' : '../uploadify/attach.png',//浏览按钮的图片的路径
		'width' : 70,//设置浏览按钮的宽度 ，默认值：110
		'height' : 16,//设置浏览按钮的高度 ，默认值：30
		'wmode' : 'transparent',//设置该项为transparent 可以使浏览按钮的flash背景文件透明，并且flash文件会被置为页面的最高层。 默认值：opaque
		'simUploadLimit' : 10, //一次同步上传的文件数目
		'sizeLimit' : 204800000, //设置单个文件大小限制 byte(目前最大不超过100M)
		'queueSizeLimit' : 5, //队列中同时存在的文件个数限制
		'filesReplaced' : false,//如果文件队列中已经存在A和B两个文件，再次选择文件时又选择了A和B，该属性值为2
		'onSelect' : function(event, queueID, fileObj) {
			if (fileObj.size > 204800000) {
				return;
			}
			var tag=$(".attrFile");
			for(var i in tag){
				if(tag[i].innerHTML ==fileObj.name){
					banger.page.showMessageBox('文件"' + fileObj.name + '"已存在，不允许重复上传!');
					setTimeout("$('#fileInput').uploadifyCancel('"+queueID+"')",1000);
				}
			}
		},
		'onComplete' : function(event, queueID, fileObj,
								response, data) {
			var fm = document.forms[0];
			/**生成隐藏字段**/
			//文件名称
			var fileInput = document.createElement("input");
			fileInput.setAttribute("name", "fileNameTask");
			fileInput.setAttribute("value", fileObj.name);
			fileInput.setAttribute("type", "hidden");
			fm.appendChild(fileInput);
			//文件大小
			var fileInputSize = document.createElement("input");
			fileInputSize.setAttribute("name", "fileSize");
			fileInputSize.setAttribute("value", fileObj.size);
			fileInputSize.setAttribute("type", "hidden");
			fm.appendChild(fileInputSize);

			var oldName=response.split("||");
			//源文件名
			var fileInputOldName = document.createElement("input");
			fileInputOldName.setAttribute("name", "fileNameOld");
			fileInputOldName.setAttribute("value", oldName[1]);
			fileInputOldName.setAttribute("type", "hidden");
			fm.appendChild(fileInputOldName);

			sccCount = sccCount + 1;
			if (sccCount == count) {
				submitMessage();
			}
			var json = eval("(" + response + ")"); //解析json格式数据
			filenames += json.filename + ",";
		},
		'onError' : function(event, queueID, fileObj) {
			if (fileObj.size > 204800000) {
				banger.page.showMessageBox("文件:" + fileObj.name + "大于200M,请重新选择!");
				setTimeout("$('#fileInput').uploadifyCancel('"
				+ queueID + "')", 3000);
			} else {
				banger.page.showMessageBox("文件:" + fileObj.name + "上传失败");
			}
		},
		'onCancel' : function(event, queueID, fileObj, data) {
			count = data.fileCount;
		},
		'onSelectOnce' : function(event, data) {
			count = data.fileCount;
		}
	});
}

/*copy from zhongxin by wuxj 2013-1-16*/
function setFileUpload(script) {
	var filenames = "";
	$("input[name='fileInput']").uploadify({
		/*注意前面需要书写path的代码*/
		'uploader' : '../uploadify/uploadify.swf',
		'script' : script,//上传调用的action名称
		'cancelImg' : '../uploadify/cancel.png',
		'queueID' : 'fileQueue', //和存放队列的DIV的id一致
		'fileDataName' : 'fileInput', //和以下input的name属性一致
		'auto' : false, //是否自动开始
		'multi' : true, //是否支持多文件上传
//			'hideButton'     : false, //隐藏浏览按钮的图片
		'buttonText' : escape('添加附件'),//浏览按钮上的文字
		'buttonImg' : '../uploadify/attach.png',//浏览按钮的图片的路径
		'width' : 70,//设置浏览按钮的宽度 ，默认值：110
		'height' : 16,//设置浏览按钮的高度 ，默认值：30
		'wmode' : 'transparent',//设置该项为transparent 可以使浏览按钮的flash背景文件透明，并且flash文件会被置为页面的最高层。 默认值：opaque
		'simUploadLimit' : 10, //一次同步上传的文件数目
		'sizeLimit' : 204800000, //设置单个文件大小限制 byte(目前最大不超过100M)
		'queueSizeLimit' : 999, //队列中同时存在的文件个数限制
		'filesReplaced' : false,//如果文件队列中已经存在A和B两个文件，再次选择文件时又选择了A和B，该属性值为2
		'removeCompleted' : false,
		'onSelect' : function(event, queueID, fileObj) {
			if (fileObj.size > 204800000) {
				return;
			}
			if(fileObj.size <=0){
				banger.page.showMessageBox("文件:" + fileObj.name + "等于0KB,请重新选择!");
				setTimeout("$('#fileInput').uploadifyCancel('"
				+ queueID + "')", 1000);
				return;
			}
			var tag=$(".attrFile");
			for(var i in tag){
				if(tag[i].innerHTML ==fileObj.name){
					banger.page.showMessageBox('文件"' + fileObj.name + '"已存在，不允许重复上传!');
					setTimeout("$('#fileInput').uploadifyCancel('"+queueID+"')",1000);
				}
			}
		},
		'onComplete' : function(event, queueID, fileObj,
								response, data) {
			//alert("event:"+event+" queueID:"+queueID+" fileObj:"+fileObj+" response:"+response+" data.fileCount:"+data.fileCount);
			var fm = document.forms[0];
			/**生成隐藏字段**/
			var fileID=response;
			//文件名称
			var fileInput = document.createElement("input");
			fileInput.setAttribute("name", "fileAttachment");
			fileInput.setAttribute("value", fileObj.name + "|" + fileObj.size + "|" + fileID + "|" + fileObj.type);
			fileInput.setAttribute("type", "hidden");
			fm.appendChild(fileInput);

			sccCount = sccCount + 1;//成功数量+1
			if (sccCount == count) {
				//执行保存任务附件到数据库操作
				saveNext();
			}
		},

		'onError' : function(event, queueID, fileObj) {
			if (fileObj.size > 204800000) {
				banger.page.showMessageBox("文件:" + fileObj.name + "大于200M,请重新选择!");
				setTimeout("$('#fileInput').uploadifyCancel('"
				+ queueID + "')", 3000);
			} else {
				banger.page.showMessageBox("文件:" + fileObj.name + "上传失败");
			}
		},
		'onCancel' : function(event, queueID, fileObj, data) {
			count = data.fileCount;
		},
		'onSelectOnce' : function(event, data) {
			count = data.fileCount;
		}
	});
}
/*copy from zhongxin by wuxj 2013-1-16*/

/*
 * @author zhangfp 2013-6-9
 */
function setFileUploadByParams(obj) {
	var filenames = "";
	$("input[name='fileInput']").uploadify({
		/*注意前面需要书写path的代码*/
		'uploader' : '../uploadify/uploadify.swf',
		'script' : obj['action'],//上传调用的action名称
		'cancelImg' : '../uploadify/cancel.png',
		'queueID' : obj['queueId'], //和存放队列的DIV的id一致
		'fileDataName' : 'fileInput', //和以下input的name属性一致
		'auto' : obj['auto'], //是否自动开始
		'multi' : true, //是否支持多文件上传
		//'hideButton'     : true, //隐藏浏览按钮的图片
		'buttonText' : escape('添加附件'),//浏览按钮上的文字
		'buttonImg' : '../uploadify/attach.png',//浏览按钮的图片的路径
		'width' : 70,//设置浏览按钮的宽度 ，默认值：110
		'height' : 16,//设置浏览按钮的高度 ，默认值：30
		'wmode' : 'transparent',//设置该项为transparent 可以使浏览按钮的flash背景文件透明，并且flash文件会被置为页面的最高层。 默认值：opaque
		'simUploadLimit' : 10, //一次同步上传的文件数目
		'sizeLimit' : 204800000, //设置单个文件大小限制 byte(目前最大不超过100M)
		'queueSizeLimit' : 999, //队列中同时存在的文件个数限制
		'filesReplaced' : false,//如果文件队列中已经存在A和B两个文件，再次选择文件时又选择了A和B，该属性值为2
		'removeCompleted' : false,
		'onSelect' : function(event, queueID, fileObj) {
			if (fileObj.size > 204800000) {
				return;
			}
			if(fileObj.size <=0){
				banger.page.showMessageBox("文件:" + fileObj.name + "等于0KB,请重新选择!");
				setTimeout("$('#fileInput').uploadifyCancel('"
				+ queueID + "')", 1000);
				return;
			}
//            var tag=$(".attrFile");
//            for(var i in tag){
//                if(tag[i].innerHTML ==fileObj.name){
//                    banger.page.showMessageBox('文件"' + fileObj.name + '"已存在，不允许重复上传!');
//                    setTimeout("$('#fileInput').uploadifyCancel('"+queueID+"')",1000);
//                }
//            }
		},
		'onComplete' : function(event, queueID, fileObj,
								response, data) {
			$('#fileInput'+queueID).hide();
			var loanId=obj['loanId'];
			var customerId = obj['customerId'];
			var loanStatusId = obj['loanStatusId'];
			jQuery.ajax({
				type:'POST',
				url:'../loan/getAttachmentList.html?loanStatusId='+loanStatusId+'&loanId='+loanId+'&customerId='+customerId+'&RandNum='+Math.random(),
				data:{},
				success:function(html){
					$('#attachmentList').html(html);
					var num = parseInt($("#isExcitForm4").val()) + 1;
					$("#isExcitForm4").val(num);
				}
			});

//            //alert("event:"+event+" queueID:"+queueID+" fileObj:"+fileObj+" response:"+response+" data.fileCount:"+data.fileCount);
////            var fm = document.forms[0];
//            var attachmentDiv = document.getElementById("attachmentDiv");
//            /**生成隐藏字段**/
//            var oldName=response.split("||");
//            //文件名称
//            var fileInput = document.createElement("input");
//            fileInput.setAttribute("name", "fileAttachment");
//            fileInput.setAttribute("value", fileObj.name + "|" + fileObj.size + "|" + oldName + "|" + fileObj.type);
//            fileInput.setAttribute("type", "hidden");
//            fileInput.setAttribute("class","del");
//
////            fm.appendChild(fileInput);
//            attachmentDiv.appendChild(fileInput);
//
//            sccCount = sccCount + 1;//成功数量+1
//            if (sccCount == count) {
//                //执行保存任务附件到数据库操作
//                saveNext();
//            }
		},
		'onError' : function(event, queueID, fileObj) {
			if (fileObj.size > 204800000) {
				banger.page.showMessageBox("文件:" + fileObj.name + "大于200M,请重新选择!");
				setTimeout("$('#fileInput').uploadifyCancel('"
				+ queueID + "')", 3000);
			} else {
				banger.page.showMessageBox("文件:" + fileObj.name + "上传失败");
			}
		},
		'onCancel' : function(event, queueID, fileObj, data) {
			count = data.fileCount;
		},
		'onSelectOnce' : function(event, data) {
			count = data.fileCount;
		}
	});
}
function setFileUpByParams(obj) {
	var filenames = "";
	$("input[name='fileInput']").uploadify({
		/*注意前面需要书写path的代码*/
		'uploader' : '../uploadify/uploadify.swf',
		'script' : obj['action'],//上传调用的action名称
		'cancelImg' : '../uploadify/cancel.png',
		'queueID' : obj['queueId'], //和存放队列的DIV的id一致
		'fileDataName' : 'fileInput', //和以下input的name属性一致
		'auto' : obj['auto'], //是否自动开始
		'multi' : true, //是否支持多文件上传
		//'hideButton'     : true, //隐藏浏览按钮的图片
		'buttonText' : escape('添加附件'),//浏览按钮上的文字
		'buttonImg' : '../uploadify/attach.png',//浏览按钮的图片的路径
		'width' : 70,//设置浏览按钮的宽度 ，默认值：110
		'height' : 16,//设置浏览按钮的高度 ，默认值：30
		'wmode' : 'transparent',//设置该项为transparent 可以使浏览按钮的flash背景文件透明，并且flash文件会被置为页面的最高层。 默认值：opaque
		'simUploadLimit' : 10, //一次同步上传的文件数目
		'sizeLimit' : 204800000, //设置单个文件大小限制 byte(目前最大不超过100M)
		'queueSizeLimit' : 999, //队列中同时存在的文件个数限制
		'filesReplaced' : false,//如果文件队列中已经存在A和B两个文件，再次选择文件时又选择了A和B，该属性值为2
		'removeCompleted' : false,
		'onSelect' : function(event, queueID, fileObj) {
			if (fileObj.size > 204800000) {
				return;
			}
			if(fileObj.size <=0){
				banger.page.showMessageBox("文件:" + fileObj.name + "等于0KB,请重新选择!");
				setTimeout("$('#fileInput').uploadifyCancel('"
				+ queueID + "')", 1000);
				return;
			}

		},
		'onComplete' : function(event, queueID, fileObj,
								response, data) {
			$('#fileInput'+queueID).hide();
			var requestId=obj['requestId'];
			jQuery.ajax({
				type:'POST',
				url:'../loan/getAttachList.html?requestId='+requestId+'&RandNum='+Math.random(),
				data:{},
				success:function(html){
					$('#attachmentList').html(html);
					//   var num = parseInt($("#isExcitForm4").val()) + 1;
					// $("#isExcitForm4").val(num);
				}
			});


		},
		'onError' : function(event, queueID, fileObj) {
			if (fileObj.size > 204800000) {
				banger.page.showMessageBox("文件:" + fileObj.name + "大于200M,请重新选择!");
				setTimeout("$('#fileInput').uploadifyCancel('"
				+ queueID + "')", 3000);
			} else {
				banger.page.showMessageBox("文件:" + fileObj.name + "上传失败");
			}
		},
		'onCancel' : function(event, queueID, fileObj, data) {
			count = data.fileCount;
		},
		'onSelectOnce' : function(event, data) {
			count = data.fileCount;
		}
	});
}

