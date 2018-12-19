//查看贷款信息
function viewLoan(loanId,loanStatusId)
{
    var options = {id: "viewLoan-"+loanId, pid: GetId(), title: "查看贷款", url: "../loan/lnLoanShow.html?RandNum="+ Math.random()+"&loanId="+loanId+"&loanStatusId="+loanStatusId, lock: false};
    tab.add(options);
}

//查看征信调查信息
function viewZhengXin(requestId)
{
    var options = {id: "viewZhengXin-"+requestId, pid: GetId(), title: "征信调查", url: "../loan/zhengXinShow.html?RandNum="+ Math.random()+"&requestId="+requestId, lock: false};
    tab.add(options);
}
function showPhotoDi(requestId)
{
    var options = {id: "showPhotoDi-"+customerId+requestId+zp, pid: GetId(), title: "征信调查", url: "../loan/vZhengXinShow.html?RandNum="+ Math.random()+"&requestId="+requestId+"&customerId="+requestId+"&zp="+zp, lock: false};
    tab.add(options);
}

//所有贷款下的编辑
function editViewLoan(loanId){
    var options = {id: "viewLoan-"+loanId, pid: GetId(), title: "编辑贷款", url: "../loan/vLoan.html?isLoan=true&RandNum="+ Math.random()+"&loanId="+loanId, lock: false};
    tab.add(options);
}

//所有贷款下的编辑
/**
 * @param loanId
 * @param loanPhase  贷款阶段(0代表所有贷款，其他的贷款阶段值与贷款流程值一样)
 */
function editViewLoanByPhase(loanId,loanPhase){
    var options = {id: "viewLoan-"+loanId, pid: GetId(), title: "编辑贷款", url: "../loan/vLoan.html?loanPhase="+loanPhase+"&isLoan=true&RandNum="+ Math.random()+"&loanId="+loanId, lock: false};
    tab.add(options);
}

function editLoan(loanId)
{
    var options = {id: "editLoan-"+loanId, pid: GetId(), title: "编辑贷款", url: "../loan/toEditLoanPage.html?isEdit=true&RandNum="+ Math.random()+"&loanId="+loanId, lock: false,confirm: true};
    tab.add(options);
}

function delLoan(loanId)
{
    if(confirm("确定删除您选择的贷款申请吗？")){
        var postJson = {};
        postJson['loanId']=loanId;
        var urlStr = "../loan/deleteLoan.html?random="+Math.random()*1000;
        jQuery.ajax({
            type: 'POST',
            url: urlStr,
            data: postJson,
            success:function(data){
                banger.page.showMessageBox({"type":"success","content":"删除贷款成功！"});
                $("#refresh").click();
            }
        });
    }
}

//定位
function locateLoan(loanId,customerId){
    var locationUrl = "../mapManager/loanToLocationMapPage.html?customerId="+customerId;
    var options = {id:"locateLoan-"+loanId,pid:GetId(),title:'定位贷款',url:locationUrl,lock:false};
    tab.add(options);
}

//同步贷款状态
function syncLoanStatus(loanId) {
    var postJson = {};
    postJson['loanId']=loanId;
    var urlStr = "../loan/syncLoanStatus.html?random="+Math.random()*1000;
    jQuery.ajax({
        type: 'POST',
        url: urlStr,
        data: postJson,
        success:function(data){
            banger.page.showMessageBox({"type":"success","content":"同步成功！"});
            $("#refresh").click();
        }
    });
}

//同步还款状态
//同步客户账户余额
function syncLoanDunInfo(loanId) {
    var postJson = {};
    postJson['loanId']=loanId;
    var urlStr = "../loan/syncLoanDunInfo.html?random="+Math.random()*1000;
    jQuery.ajax({
        type: 'POST',
        url: urlStr,
        data: postJson,
        success:function(data){
            banger.page.showMessageBox("同步成功！");
            $("#refresh").click();
        }
    });
}

//撤销待分配贷款
function cancelLoan(loanId,loanStatusId){
    var url = "../loan/cancelLoanPage.html?loanId="+loanId+"&loanStatusId="+loanStatusId+"&RandNum="+Math.random();
    var result = window.showModalDialog(url,{window: window},'dialogWidth=550px;dialogHeight=500px;toolbar=no;menubar=no;scrollbars=no;resizable=no;location=no;status=no');
    return result;
}

//提交审批
function submitExam(loanId,customerId,submitRemark,tabId){
    //提交审批的url
    var url = "../loan/submitApproval.html?loanId="+loanId+"&RandNum="+Math.random();
    jQuery.ajax({
        type:'POST',
        url:url,
        data:{
            'customerId' : customerId,
            'submitRemark' : submitRemark
        },
        success:function(jsonVal){
            if(jsonVal == null || jsonVal == ""){
                tab.close(tabId,true);
            }else{
                var valArray = jsonVal.split(",");
                var promptType = valArray[0];
                var promptCustomerName;
                if(valArray.length == 2){
                    promptCustomerName = valArray[1];
                }
                if(promptType == "1"){
                    banger.page.showMessageBox("贷款提交到审批环节至少需要一段调查录音！");
                }else if(promptType == "2"){
                    banger.page.showMessageBox("贷款调查至少需要一张户籍照片、三张家庭照片、三张经营地照片！");
                }else if(promptType == "3"){
                    banger.page.showMessageBox("贷款至少需有一段共同借贷人"+promptCustomerName+"的录音！");
                }else if(promptType == "4"){
                    banger.page.showMessageBox("贷款至少需要共同借贷人"+promptCustomerName+"三张身份证照片、一张户籍照片、三张家庭照片！");
                }else if(promptType == "5"){
                    banger.page.showMessageBox("贷款至少需有一段担保人\""+promptCustomerName+"\"的录音！");
                }else if(promptType == "6"){
                    banger.page.showMessageBox("贷款至少需要担保人"+promptCustomerName+"三张身份证照片、一张户籍照片、三张家庭照片或经营地照片！");
//                }else if(promptType == "7"){
//                    banger.page.showMessageBox("贷款提交到审批环节至少需要上传一个附件！");
                }
            }
        }
    });
}

function saveBaseInfo(chooseType) {
   // if (jQuery.trim($("#txtCustomerId").val()) == "") {
        /*if (jQuery.trim($("#txtCustomerName").val()) == "") {
            banger.page.showMessageBox("请填写客户信息！");
            return false;
        }*/

        //如果是新增客户，则校验数据

        var postJson1 = {};
        postJson1['customerName']=jQuery.trim($("#txtCustomerName").val());
        postJson1['phone']=jQuery.trim($("#txtCphNumber").val());
        postJson1['idCard']=jQuery.trim($("#txtIDCard").val());
        var company = $("#txtCompany").val();
        if(company != "" && company != "undefined"){
        	postJson1["company"] = $("#txtCompany").val();
        }
        var urlStr = "../loan/addCustomer.html?random="+Math.random()*1000;
        jQuery.ajax({
            type: 'POST',
            url: urlStr,
            data: postJson1,
            async:false,
            success:function(data){
                jQuery("#txtCustomerId").val(data);
            }
        });
   /* } else {
        //如果是选取客户，又有更改
        if (jQuery.trim($("#txtCustomerName").val()) != jQuery.trim($("#txtOldCustomerName").val())
            || (jQuery.trim($("#txtCphNumber").val()) != jQuery.trim($("#txtReplaceCphNumber").val())
            && jQuery.trim($("#txtCphNumber").val()) != jQuery.trim($("#txtOldCphNumber").val()))
            || (jQuery.trim($("#txtIDCard").val()) != jQuery.trim($("#txtReplaceIDCard").val())
            && jQuery.trim($('#txtIDCard').val()) != jQuery.trim($('#txtOldIDCard').val())) || 
            (jQuery.trim($("#txtCompany").val()) != jQuery.trim($("#txtReplaceCompany").val()))) {
            //如果是选取客户，则校验数据需要判断是否有变更，如果没有变更,*表示可以输入
            var cphNumber = jQuery.trim($("#txtCphNumber").val());
            var replaceCphNumber =  jQuery.trim($("#txtReplaceCphNumber").val());
            if (cphNumber != replaceCphNumber) {
                if(cphNumber.lastIndexOf("*") > 0){
                    banger.page.showMessageBox("联系电话填写不正确！");
                    $("#txtCphNumber").focus();
                    return false;
                }
            }

            //$('#txtOldCustomerName').val(jQuery.trim($("#txtCustomerName").val()));
//            $('#txtOldCphNumber').val(jQuery.trim($("#txtCphNumber").val()));
//            $('#txtOldIDCard').val(jQuery.trim($('#txtIDCard').val()));

            var url="../loan/toChooseAddUpdate.html?chooseType="+chooseType+"&random="+Math.random()*1000;
            var result=banger.page.openDialog(url,"选择添加客户信息方式",600,200);
            if(result==undefined){
                return false;
            }

            var postJson2 = {};
            postJson2['customerName']=jQuery.trim($("#txtCustomerName").val());
            //如果联系号码和身份证没有变更，则还是用之前的值
            var phone2 = jQuery.trim($("#txtCphNumber").val());
            var phone2Hide =  jQuery.trim($("#txtReplaceCphNumber").val());
            if (phone2 == phone2Hide) {
                postJson2['phone']=jQuery.trim($("#txtOldCphNumber").val());
            }  else {
                postJson2['phone']=jQuery.trim($("#txtCphNumber").val());
                //这个值是新增的，用于在联系电话改变的时候，替换掉之前的号码
//                postJson2['oldPhone']=jQuery.trim($("#txtOldCphNumber").val());
            }
            var idCard2 = jQuery.trim($("#txtIDCard").val());
            var idCard2Hide =  jQuery.trim($("#txtReplaceIDCard").val());
            if (idCard2 == idCard2Hide) {
                postJson2['idCard']=jQuery.trim($("#txtOldIDCard").val());
            }  else {
                postJson2['idCard']=jQuery.trim($("#txtIDCard").val());
            }
            var company = $("#txtCompany").val();
            if(company != "" && company != "undefined"){
            	postJson2["company"] = $("#txtCompany").val();
            }
            
        //    alert(result);
            if (result == "add") {
                var urlStr = "../loan/addCustomer.html?random="+Math.random()*1000;
                jQuery.ajax({
                    type: 'POST',
                    url: urlStr,
                    data: postJson2,
                    async:false,
                    success:function(data){
                        jQuery("#txtCustomerId").val(data);
                    }
                });
            }
            if (result == "update") {
                postJson2['customerId']=jQuery.trim($("#txtCustomerId").val());
                var urlStr = "../loan/updateCustomer.html?random="+Math.random()*1000;
                jQuery.ajax({
                    type: 'POST',
                    url: urlStr,
                    data: postJson2,
                    async:false,
                    success:function(data){
                    }
                });
            }
        }
    }*/

    return true;
}

//打开共同审批人页面
function openCommApprovePersonPage(loanId){
    var url = "../loan/openCommApprovePersonPage.html?loanId="+loanId+"&RandNum="+Math.random();
    var result = window.showModalDialog(url,{window: window},'dialogWidth=400px;dialogHeight=250px;toolbar=no;menubar=no;scrollbars=no;resizable=no;location=no;status=no')
    return result;
}

//拒绝贷款
function refuseLoan(loanId,loanStatusId,remark){
    var rem = "";
    if(remark != ""){
        rem = encodeURI(encodeURI(remark));
    }
    var url = "../loan/cancelLoanPage.html?remark="+rem+"&loanId="+loanId+"&loanStatusId="+loanStatusId+"&RandNum="+Math.random();
    var result = window.showModalDialog(url,{window: window},'dialogWidth=550px;dialogHeight=500px;toolbar=no;menubar=no;scrollbars=no;resizable=no;location=no;status=no');
    return result;
}

//调查拒绝和审批拒绝专用方法
function refuse(loanId,loanStatusId,customerId,remark){
    var rem = "";
    if(remark && remark != ""){
        rem = encodeURI(encodeURI(remark));
    }
    var url = "../loan/cancelLoanPage.html?remark="+rem+"&loanId="+loanId+"&loanStatusId="+loanStatusId+"&customerId="+customerId+"&RandNum="+Math.random();
    if(loanStatusId == 4){
        url = "../loan/cancelLoanPage.html?approveRefuse=true&loanId="+loanId+"&loanStatusId="+loanStatusId+"&customerId="+customerId+"&RandNum="+Math.random();
    }
    var result = window.showModalDialog(url,{window: window},'dialogWidth=550px;dialogHeight=500px;toolbar=no;menubar=no;scrollbars=no;resizable=no;location=no;status=no');
    if(result){
        jQuery.ajax({
            type:'post',
            url:'saveFileAttachments.html',
            data:{
                'loanId':loanId,
                'customerId':customerId
            }
        })
    }
    return result;
}

/**
 * 删除表单附件
 */
function delAttr(formId,index,formName){
	 var loanId =$("#loanId").val();
     var customerId = $("#customerId").val();
    if(confirm("您确定要删除附件"+formName+"吗?")){
        //执行后台数据库数据交互操作,执行成功则界面执行交换操作
        jQuery.ajax({
            type: "post",
            url: "../loan/delFormAttachment.html",
            data: {'formId': formId,'loanId':loanId,'customerId':customerId
            },
            success: function(data){
                delResult(data,formId,index);
            }
        });
    }
}
function delResult(msg,formId,index){
	
    if(msg == 'success'){
        jQuery("#attr_"+index).remove();
    }else if(msg == 'success2'){
        jQuery("#attr_"+index).remove();
        //附件判断为否
      //  $("#isExcitForm4").val("false");
    }
    else{
        banger.page.showMessageBox(msg);
    }
    $("#isExcitForm4").val(Number($("#isExcitForm4").val()) - 1);
}

/**
 * 图片预览
 */
function downFileOrShowAttr(fileId,uploadFileName){

    var index1=uploadFileName.lastIndexOf(".");
    var index2=uploadFileName.length;
    var suffix=uploadFileName.substring(index1+1,index2);//后缀名
    var tp ="jpg,gif,bmp,JPG,GIF,BMP";
    var rs=-1;
    rs=tp.indexOf(suffix);//判断是否包含这些类型
     if (rs>=0){
         showPhotoDiv(fileId);
         return;
     }
    else
     {
         jQuery.post("../data/queryFileByFileId.html", {"fileId": fileId}, function(result){
             if(result == "0"){
                 banger.page.showMessageBox({"type":"error","content":"文件不存在！"});
                 return;
             }else{
//			file = result;
//			jQuery.post("../record/haveRec.html", {"path": result}, function (result) {
//				if (result == 0) {
//					banger.page.showMessageBox({"type": "error", "content": "文件不存在！"});
//					return;
//				} else {
//					location.href = "../record/download.html?path=" + file;
//				}
//			});
                 location.href = '../loan/downloadFormAttachment.html?fileId=' + fileId;
             }
         });
     }
}
/**
 * 下载附件
 */
function downFileAttr(fileId){

        jQuery.post("../data/queryFileByFileId.html", {"fileId": fileId}, function(result){
            if(result == "0"){
                banger.page.showMessageBox({"type":"error","content":"文件不存在！"});
                return;
            }else{
//			file = result;
//			jQuery.post("../record/haveRec.html", {"path": result}, function (result) {
//				if (result == 0) {
//					banger.page.showMessageBox({"type": "error", "content": "文件不存在！"});
//					return;
//				} else {
//					location.href = "../record/download.html?path=" + file;
//				}
//			});
                location.href = '../loan/downloadFormAttachment.html?fileId=' + fileId;
            }
        });

}


/**
 * 贷后管理预览
 */
function downFileImage(fileId,fileName){


    var index1=fileName.lastIndexOf(".");
    var index2=fileName.length;
    var suffix=fileName.substring(index1+1,index2);//后缀名
    var tp ="jpg,gif,bmp,JPG,GIF,BMP";
    var rs=-1;
    rs=tp.indexOf(suffix);//判断是否包含这些类型
    if (rs>=0){
        showPhotoDiv(fileId);
        return;
    }
    else
    {
        jQuery.post("../data/queryFileByFileId.html", {"fileId": fileId}, function(result){
            if(result == "0"){
                banger.page.showMessageBox({"type":"error","content":"文件不存在！"});
                return;
            }else{
//			file = result;
//			jQuery.post("../record/haveRec.html", {"path": result}, function (result) {
//				if (result == 0) {
//					banger.page.showMessageBox({"type": "error", "content": "文件不存在！"});
//					return;
//				} else {
//					location.href = "../record/download.html?path=" + file;
//				}
//			});
                location.href = '../loan/downloadFormAttachment.html?fileId=' + fileId;
            }
        });
    }
}

function showPhotoDiv(fileId){


    window.top.aui.builder1({
        url: '../loan/queryAfterImageDetail.html?fileId=' + fileId
        //url: '../loan/changeLoanPhoto.html?photoLoanId=' + loanId + '&photoEventId=' + eventId + '&photoTypeId=' + $("#photoTypeId").val() + '&customerId=' + customerId + '&photoRowNum=' + photoRowNum+ '&pid=' + GetId()
    });
}

function exportLoanData(loanId){

    jQuery.post("../loan/hasLoanDatas.html", {"loanId": loanId}, function(result){
        if(result == "0"){
            banger.page.showMessageBox("没有可导出的资料！");
            return;
        }else{
            //document.location.href = '../loan/exportLoanData.html?loanId=' + loanId;
            //document.location.href="../customerExport/exportCustomerExute.html";
            window.location.href='../loan/downLoanData.html?loanId=' + loanId;
        }
    });
}