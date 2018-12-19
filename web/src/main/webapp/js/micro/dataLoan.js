
//贷款资料开始
 //显示录音、视频、表单列表
function showDataList(customerName, dataType, loanId, customerId, eventId, dataCount) {
    var url = "../loan/openCusDataListTableView.html?random=" + Math.random() * 1000;
    jQuery.ajax({
        type: 'POST',
        url: url,
        data: {
            'dataType': dataType,
            'loanId': loanId,
            'customerId': customerId,
            'eventId': eventId
        },
        success: function (data) {
            $('#tapeVideoDiv').html(data);
            $('#tapeVideoDiv').show();
        }
    });
}
function changeAudioRemark(audioId, remark) {
    $('#' + audioId).text(remark);
    $('#' + audioId).attr("title", remark);
}

function changeVideoRemark(videoId, remark) {
    $('#' + videoId).text(remark);
    $('#' + videoId).attr("title", remark);
}

function closeDiv() {
    $('#tapeVideoDiv').hide();
    if (null != document.getElementById("player")) {
        document.getElementById("player").Stop();
    }
}

function showPhoto(eventId, loanId, customerId) {
    window.top.aui.builder({
        url: '../loan/queryPhotoDetail.html?photoLoanId=' + loanId + '&photoEventId=' + eventId + '&customerId=' + customerId + '&pid=' + GetId()
    });
}

//录音
function audioObj(customerName, audioName, fileId, audioId) {
    var remark = $('#' + audioId).text();
    audioPlay(customerName, audioName, fileId, audioId);
}

function videoObj(customerName, videoName, fileId, videoId) {
    videoPlay(customerName, videoName, fileId, videoId);
}

//视频
function videoPlay(customerName, videoDataName, fileId, id) {
    jQuery.post("../data/readFile.html", {"fileId": fileId}, function (result) {
        if (result == "0") {
            banger.page.showMessageBox({"type": "error", "content": "文件不存在！"});
            return;
        } else {
            window.parent.showPopVideoDiv(customerName, videoDataName, result, id, GetId(), "loan");
        }
    });
}
function downLoanDatas(loanId){
    //$('#exportLoanDataButton').attr("disabled","disabled");
    exportLoanData(loanId);
}
$(function(){
    var loanId = document.getElementById("loanId");
        	 flashDataManagerView(loanId,'event1');
			 $(".event").hide();
			 $("#event1").show();
         });
 //刷新贷款人的共同借贷人和担保人信息页面
        function flashDataManagerView(loanId,eventId) {
			eventId = eventId.substring(5);
			var url = "../loan/flashDataManagerView"+eventId+".html?loanId=" + loanId;
            jQuery.ajax({
                type: 'POST',
                url: url,
                data: {},
                success: function (data) {
                    $('#event'+eventId).html(data);
                }
            })
        }
		function showDiv(div,eventId){
        var loanId = document.getElementById("loanId");
		flashDataManagerView(loanId,eventId);
        $(".event").hide();
        $(".line-round-orange.orange.fl").attr("class","line-round-black gray fl");
        $(div).attr("class","line-round-orange orange fl");
        $("#"+eventId).show();
	}
	
//贷款资料结束