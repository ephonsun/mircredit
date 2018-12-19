////添加开始月份
//$('#baseStartMonth').change(function(){
//	var bool = jQuery.validator({form:'saveBaseTable',extend:true,extendhandler:extendvalidator});
//	if(!bool){
//		return false;
//	}
//	alert("hello");
//
//});
////添加结束月份
//$('#baseEndMonth').change(function(){
//	var bool = jQuery.validator({form:'saveBaseTable',extend:true,extendhandler:extendvalidator});
//	if(!bool){
//		return false;
//	}
//});
//$('#btn-Profit-save').click(function(){
//	$('#monthNum').attr('disabled','false');
//	$('#btn-Profit-save').val('修改');
//
//});
//修改按钮变换状态
function changeBaseInfo(applyId){
	if(applyId==1) {
		$('#sbStatus').html('保存');
		$('#baseStartMonth').removeAttr('disabled');
		$('#baseEndMonth').removeAttr('disabled');
		$('#lastMonthRate').removeAttr('disabled');
		$('#grossRateRemark').removeAttr('disabled');
		$('#saveBase').removeAttr('onclick');
		$('#saveBase').attr('onclick', 'updateBaseInfo('+applyId+');');
		$('#oldBegin').val($('#baseStartMonth').val());
		$('#oldEnd').val($('#baseEndMonth').val());
	}else{
		$('#xfd_btn_save').val('保存');
		$('#xfd_monthNum').removeAttr('disabled');
		$('#xfd_btn_save').removeAttr('onclick');
		$('#xfd_btn_save').attr('onclick', 'updateBaseInfo('+applyId+');');
		$('#oldNum').val($('#xfd_monthNum').val());
	}
}
//保存月份区间信息
function updateBaseInfo(applyId){

	var postJson = {};
	if(applyId=="1") {
		jQuery('#saveBaseTable').validator();
		var bool = jQuery.validator({form:'saveBaseTable',extend:true,extendhandler:extendvalidator});
		if(!bool){
			return false;
		}
		var grossRateRemark = $("#grossRateRemark").val();
		var baseStartMonth = $("#baseStartMonth").val();
		var baseEndMonth = $("#baseEndMonth").val();
		var lastMonthRate = $("#lastMonthRate").val();
		var loanId = $('#baseLoanId').val();
		var m = getMonths(baseStartMonth, baseEndMonth);
		if (m == false) {
			return false;
		}
		$('#monthNumber').html(m);

		postJson['loanId'] = loanId;
		postJson['baseStartMonth'] = baseStartMonth;
		postJson['baseEndMonth'] = baseEndMonth;
		postJson['lastMonthRate'] = lastMonthRate;
		postJson['grossRateRemark'] = grossRateRemark;
		postJson['monthNumber'] = m;
		postJson['applyId']=applyId;
		if($('#oldBegin').val()==baseStartMonth&&$('#oldEnd').val()==baseEndMonth) {
			$('#sbStatus').html('修改');
			$('#baseStartMonth').attr('disabled', 'false');
			$('#baseEndMonth').attr('disabled', 'false');
			$('#lastMonthRate').attr('disabled', 'false');
			$('#grossRateRemark').attr('disabled', 'false');
			$('#saveBase').removeAttr('onclick');
			$('#saveBase').attr('onclick', 'changeBaseInfo('+applyId+');');
			return false;
		}

	}else{
		jQuery('#xfdPro').validator();
		var bool = jQuery.validator({form:'xfdPro',extend:true,extendhandler:extendvalidator});
		if(!bool){
			return false;
		}
		var xfd_monthNum = $('#xfd_monthNum').val();
		postJson['loanId']=$('#baseLoanId').val();
		postJson['xfd_monthNum']=xfd_monthNum;
		postJson['applyId']=applyId;
		if($('#oldNum').val()==xfd_monthNum){
			$('#xfd_btn_save').val('修改');
			$('#xfd_monthNum').attr('disabled', 'false');
			$('#xfd_btn_save').removeAttr('onclick');
			$('#xfd_btn_save').attr('onclick', 'changeBaseInfo('+applyId+');');
			return false;
		}

	}
	var urlStr = $('#saveProLostBaseUrl').val()+ "?random=" + Math.random() * 1000;
	var msg = "修改会删除之前的全部数据，确认吗？";
	if (confirm(msg)!=true){
		return false;
	}
	jQuery.ajax({
		type: 'POST',
		url: urlStr,
		data: postJson,
		success:function(data){
			if(data=='success') {
				if(applyId=="1"){
				$('#sbStatus').html('修改');
				$('#baseStartMonth').attr('disabled', 'false');
				$('#baseEndMonth').attr('disabled', 'false');
				$('#lastMonthRate').attr('disabled', 'false');
				$('#grossRateRemark').attr('disabled', 'false');
				$('#saveBase').removeAttr('onclick');
				$('#saveBase').attr('onclick', 'changeBaseInfo('+applyId+');');
				alert("保存成功!");
				getLnProfitLossProdTab();
				//getLnProfitLossProdTab();
				//getLProfitLossProdOff();
			}else{
					$('#xfd_btn_save').val('修改');
					$('#xfdNum').val(xfd_monthNum);
					$('#xfd_monthNum').attr('disabled', 'false');
					$('#xfd_btn_save').removeAttr('onclick');
					$('#xfd_btn_save').attr('onclick', 'changeBaseInfo('+applyId+');');
					alert("保存成功!");
					getLnProfitLossProdTab();
				}

			}
		}
	});
}
//
function saveBaseInfo(applyId){
	var postJson = {};
	var loanId = $('#baseLoanId').val();
	if(applyId=="1") {
		jQuery('#saveBaseTable').validator();
		var bool = jQuery.validator({form: 'saveBaseTable', extend: true, extendhandler: extendvalidator});
		if (!bool) {
			return false;
		}
		var grossRateRemark = $("#grossRateRemark").val();
		var baseStartMonth = $("#baseStartMonth").val();
		var baseEndMonth = $("#baseEndMonth").val();
		var lastMonthRate = $("#lastMonthRate").val();
		var m = getMonths(baseStartMonth, baseEndMonth);
		if (m == false) {
			return false;
		}
		$('#monthNumber').html(m);

		postJson['loanId'] = loanId;
		postJson['baseStartMonth'] = baseStartMonth;
		postJson['baseEndMonth'] = baseEndMonth;
		postJson['lastMonthRate'] = lastMonthRate;
		postJson['grossRateRemark'] = grossRateRemark;
		postJson['monthNumber'] = m;
		postJson['applyId']=applyId;
	}else{
		jQuery('#xfdPro').validator();
		var bool = jQuery.validator({form: 'xfdPro', extend: true, extendhandler: extendvalidator});
		if (!bool) {
			return false;
		}
		postJson['loanId'] = loanId;
		postJson['xfd_monthNum']=$('#xfd_monthNum').val();
		postJson['applyId']=applyId;
		//alert($('#xfd_monthNum').val());
	}
	var urlStr = $('#addProLostBaseUrl').val()+ "?random=" + Math.random() * 1000;
	jQuery.ajax({
		type: 'POST',
		url: urlStr,
		data: postJson,
		success:function(data) {
			if (applyId=="1") {
				$('#sbStatus').html('修改');
				$('#bsm').val(baseStartMonth);
				$('#bem').val(baseEndMonth);
				$('#baseStartMonth').attr('disabled', 'false');
				$('#baseEndMonth').attr('disabled', 'false');
				$('#lastMonthRate').attr('disabled', 'false');
				$('#grossRateRemark').attr('disabled', 'false');
				//$('#saveBase').removeAttr('onclick');
				$('#saveBase').attr('onclick', 'changeBaseInfo(' + applyId + ');');
				alert("保存成功!");
				getLnProfitLossProdTab();
			}else{
				$('#xfd_base').html('修改');
				$('#xfdNum').val($('#xfd_monthNum').val());
				$('#xfd_monthNum').attr('disabled', 'false');
				$('#xfd_btn_save').removeAttr('onclick');
				$('#xfd_btn_save').attr('onclick', 'changeBaseInfo('+applyId+');');
				alert("保存成功!");
				getLnProfitLossProdTab();
			}
		}
	});

}

//$('#saveBase').click(function(){
//	jQuery('#saveBaseTable').validator();
//	var bool = jQuery.validator({form:'saveBaseTable',extend:true,extendhandler:extendvalidator});
//	if(!bool){
//		return false;
//	}
//	var grossRateRemark = $("#grossRateRemark").val();
//	var baseStartMonth = $("#baseStartMonth").val();
//	var baseEndMonth = $("#baseEndMonth").val();
//	var lastMonthRate = $("#lastMonthRate").val();
//	var urlStr="";
//	//alert("hello");
//	var mons = $("#monthNumber").html();
//	if(mons==""){
//		urlStr =$('#addProLostBaseUrl').val()+"?random="+ Math.random() * 1000;
//	}
//	var m = getMonths(baseStartMonth,baseEndMonth);
//	if(m==false){
//		return false;
//	}
//	$('#monthNumber').html(m);
//	var loanId = $('#baseLoanId').val();
//	//alert(loanId);
//	var postJson = {};
//	postJson['loanId']=loanId;
//	postJson['baseStartMonth']=$("#baseStartMonth").val();
//	postJson['baseEndMonth'] = $("#baseEndMonth").val();
//	postJson['lastMonthRate'] = $("#lastMonthRate").val();
//	postJson['grossRateRemark']=$("#grossRateRemark").html();
//	postJson['monthNumber']=$("#monthNumber").html();
//	//添加跳转的url
//	if(urlStr==""){
//		var urlStr = $('#saveProLostBaseUrl').val()+ "?random=" + Math.random() * 1000;
//	}
//	jQuery.ajax({
//		type: 'POST',
//		url: urlStr,
//		data: postJson,
//		success:function(data){
//			alert("保存成功!");
//			getLnProfitLossProdTab();
//			//getLProfitLossProdOff();
//		}
//	});
//});
//验证必须输入项是否有输入数据
function extendvalidator(){
	var bool = true;
	return true;
}
function getMonths(baseStartMonth,baseEndMonth){
	var d1 = baseStartMonth.split("年");
	var d2 = d1[1].split("月");
	var date1 = parseInt(d1[0]) *12+parseInt(d2[0]);
	d1 = baseEndMonth.split("年");
	d2 = d1[1].split("月");
	var date2 = parseInt(d1[0]) *12+parseInt(d2[0]);
	if(date1>date2){
		alert("开始月份不能大于结束月份！");
		return false;
	}
	else {
		var m = date2 - date1+1;
		if(m>12){
			alert("月数不能超过12！");
			return false;
		}
		return m;
	}
}

function getLnProfitLossProdTab(){
	var postJson = {};
	postJson['loanId'] = $("#loanId").val();
	postJson['appLoanTypeId'] = $("#appLoanTypeId_yx").val();
	postJson['write']=$('#write').val();
	if($("#appLoanTypeId_yx").val()=="1") {
		postJson['beginTime'] = $('#bsm').val();
		postJson['endTime'] = $('#bem').val();
		postJson['monthNumber'] = $('#monthNumber').html();
	}
	else{
		postJson['xfdNum']=$('#xfdNum').val();
	}
	jQuery.ajax({
		type: 'post',
		url: '../loan/getLnProfitLossProdTab.html',
		data: postJson,
		success: function (data) {
			$("#loanProfitLoss").html(data);
		}
	});
}
function getLProfitLossProdOff(){

}
