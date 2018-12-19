
$(function() {

	$("#appLoanTypeId").change(function() {

		var selectId = $("#appLoanTypeId").find("option:selected").val();
		if (selectId == "1") { // 经营贷
			$(".bizType").css("display", "");
			$(".consumType").css("display", "none");
			$(".cusMateCompany").css("display","none");
		} else if (selectId == "2") {// 消费贷
			$(".consumType").css("display", "");
			$(".bizType").css("display", "none");
			var cusMarriage = $("#cusMarriage").find("option:selected").val();
			if(cusMarriage == "02"){
				$(".cusMateCompany").css("display","");
			}
		} else {
			alert("请选择贷款类型");
			$(".consumType").css("display", "none");
			$(".bizType").css("display", "none");
		}

	});

	$("#cusMarriage").change(function() {

		var selectId = $("#cusMarriage").find("option:selected").val();
		var appLoanTypeId = $("#appLoanTypeId").find("option:selected").val();
		if (selectId == "02") {
			$(".isMarriage").css("display", "");
			if(appLoanTypeId == "2"){
				$(".cusMateCompany").css("display","");
			}
		} else {// 未婚或者没有选择
			$(".isMarriage").css("display", "none");
			$(".cusMateCompany").css("display","none");
		}

	});

	$("#bizIsBelongMybank").change(function() {

		var selectId = $("#bizIsBelongMybank").find("option:selected").val();
		if (selectId == "是") {
			$(".myBank").css("display", "");
		} else {
			$(".myBank").css("display", "none");
		}

	});

	$("#isLocal").change(function() {

		var selectId = $("#isLocal").find("option:selected").val();
		if (selectId == "否") {
			$(".cusLocalYear").css("display", "");
		} else {
			$(".cusLocalYear").css("display", "none");
		}

	});





	$("#isSeason").change(function() {

		var selectId = $("#isSeason").find("option:selected").val();
		if (selectId == "是") {
			$(".seasonRemark").css("display", "");
		} else {
			$(".seasonRemark").css("display", "none");
		}

	});
	$("#adviceRepayWayId").change(function() {
		var selectId = $("#adviceRepayWayId").find("option:selected").val();
		if (selectId == "5" || selectId == "") {
			$(".ui-input-btn").css("display", "none");
		} else {
			$(".ui-input-btn").css("display", "");
		}
	});

	$("#resultRepayWayId").change(function() {
		var selectId = $("#resultRepayWayId").find("option:selected").val();
		if (selectId == "5" || selectId == "") {
			$("#jyhkfs").css("display", "none");
		} else {
			$("#jyhkfs").css("display", "");
		}
	});

	$("#bizOrgId").change(function(){

		var selectId = $("#bizOrgId").find("option:selected").val();
		if (selectId == "02") {
			$(".bizOrgCode").css("display", "");
			$("#bizLawFormId").change();
		} else {
			$(".bizOrgCode").css("display", "none");
			$(".togetherBiz").css("display", "none");
		}
	});

	$("#bizLawFormId").change(function(){

		var selectId = $("#bizLawFormId").find("option:selected").val();
		if (selectId == "02"||selectId == "04"||selectId == "05"||selectId == "06") {
			$(".togetherBiz").css("display", "");
		} else {
			$(".togetherBiz").css("display", "none");
		}
	});



	$("#bizLawFormId").change(function(){

		var selectId = $("#bizLawFormId").find("option:selected").val();
		if (selectId == "02" | selectId == "04"| selectId == "05" ) {
			$(".bbizType").css("display", "");
		} else {
			$(".bbizType").css("display", "none");
		}
	});

	$("#gtgsh").change(function(){

		var selectId = $("#gtgsh").find("option:selected").val();
		if (selectId == "01") {
			$(".bizOrgCode").css("display", "");
			$(".bizSocialCreditNum").css("display", "none");
		} else if(selectId == "02") {
			$(".bizSocialCreditNum").css("display", "");
			$(".bizOrgCode").css("display", "none");
		}else{
			$(".bizSocialCreditNum").css("display", "none");
			$(".bizOrgCode").css("display", "none");
		}
	});



	$("input[name='registerInfoSourceId']")
		.change(
		function() {
			var selectId = $("input:checkbox[name='registerInfoSourceId']:checked");
			var registerInfoSourceId = "";
			var directMarket = 0;
			var introduct = 0;
			var sourceOther = 0;
			var i = 0;
			selectId.each(function() {
				if ($(this).val() == "1") {
					$("#registerDirectMarket").show();
					directMarket = 1;
				}

				if ($(this).val() == "4") {
					$("#registerExistCusIntroduct").show();
					introduct = 1
				}

				if ($(this).val() == "12") {
					$("#registerInfoSourceOther").show();
					sourceOther = 1
				}

				if (i == 0) {
					registerInfoSourceId = $(this).val();
					i = 1;
				} else {
					registerInfoSourceId += "," + $(this).val();
				}

			});

			if (directMarket == 0) {
				$("#registerDirectMarket").val("");
				$("#registerDirectMarket").hide();
			}

			if (introduct == 0) {
				$("#registerExistCusIntroduct").val("");
				$("#registerExistCusIntroduct").hide();
			}

			if (sourceOther == 0) {
				$("#registerInfoSourceOther").val("");
				$("#registerInfoSourceOther").hide();
			}

			$("#registerInfoSourceIds").val(registerInfoSourceId);
		});

	$("#appGuarantorWayId").change(function() {

		var selectId = $("#appGuarantorWayId").find("option:selected").val();
		if (selectId == "02") {// 质押
			$(".zy").css("display", "");
		} else {
			$(".zy").css("display", "none");
			$(".zyOther").css("display", "none");
		}

		if (selectId == "01") {// 抵押
			$(".dy").css("display", "");
		} else {
			$(".dy").css("display", "none");
			$(".dyOther").css("display", "none");
		}

	});


	$("#appLoanTypeId").change(function() {
		loanTypeSelectOnChange();
	});



	function loanTypeSelectOnChange(){
		var appLoanSubTypeId = $("#appLoanSubTypeIdHid").val();

		var vUrl="../typeSub/typeSelSubSelect.html?random="+Math.random();
		var postJson={};
		postJson['lnLoanSubType.loanTypeId']=$('#appLoanTypeId').val();
		postJson['loanSubTypeId']=appLoanSubTypeId;
		jQuery.ajax({
			type:'POST',
			url:vUrl,
			data:postJson,
			success:function(html){
				var loanSub=$("#appLoanSubTypeIdDiv");
				loanSub.html(html);
				$("select").each(function () {
					$(this).select({scroll: 10});
				});
			}
		});
	}

	$("#appMortgageId").change(function() {
		var selectId = $("#appMortgageId").find("option:selected").val();
		if (selectId == "06") {// 质押其他
			$(".zyOther").css("display", "");
		} else {
			$(".zyOther").css("display", "none");
		}
	});

	$("#appPledgeId").change(function() {
		var selectId = $("#appPledgeId").find("option:selected").val();
		if (selectId == "07") {// 抵押其他
			$(".dyOther").css("display", "");
		} else {
			$(".dyOther").css("display", "none");
		}
	});

	// 添加共同借贷人
	$('#addCo').click(
		function() {

			var loanId = $("#loanId").val();
			if (loanId == "") {
				alert("请先将资料保存！");
				return;
			}

			var applyCustomerIdCard = $("#cusIdcard").val();
			var customerIds = "";
			var guCustomerIdCards = "";
			var url = $("#toAddCoUrl").val() + "?loanId=" + loanId
				+ "&customerIds=" + customerIds + "&random="
				+ Math.random() * 1000 + "&applyCusIdCard="
				+ applyCustomerIdCard + "&guCusIdCards="
				+ guCustomerIdCards;
			var result = banger.page.openDialog(url, "添加借贷人", 600, 480);
			if (result != undefined) {
				var urlStr = $("#queryLoanCoUrl").val() + "?loanId="
					+ loanId + "&random=" + Math.random() * 1000;
				jQuery.ajax({
					type : 'POST',
					url : urlStr,
					data : {},
					success : function(data) {
						$("#coList").html(data);
					}
				});
			}
		});

	// 添加担保人
	jQuery('#addGu')
		.click(
		function() {

			var loanId = $("#loanId").val();
			if (loanId == "") {
				alert("请先将资料保存！");
				return;
			}

			var applyCustomerIdCard = $("#cusIdcard").val();
			var coCustomerIdCards = "";
			var customerIds = "";
			var url = $("#toAddGuUrl").val() + "?loanId=" + loanId
				+ "&customerIds=" + customerIds
				+ "&applyCusIdCard=" + applyCustomerIdCard
				+ "&coCusIdCards=" + coCustomerIdCards
				+ "&random=" + Math.random() * 1000;
			var result = banger.page.openDialog(url, "添加担保人", 600,480);
			if (result != undefined) {
				var urlStr = $("#queryLoanGuUrl").val()
					+ "?loanId=" + loanId + "&random="
					+ Math.random() * 1000;
				jQuery.ajax({
					type : 'POST',
					url : urlStr,
					data : {},
					success : function(data) {
						$("#guList").html(data);
					}
				});
			}
		});

	// 添加上游
	jQuery('#addUp')
		.click(
		function() {

			var loanId = $("#loanId").val();
			var businessCustomer = $("#businessCustomer").val();
			var businessRate = $("#businessRate").val();
			var businessYear = $("#businessYear").val();
			var businessRemark = $("#businessRemark").val();
			var reachesType =1;
			var url = $("#addUpUrl").val() + "?loanId=" + loanId
				+ "&businessCustomer=" + businessCustomer
				+ "&businessRate="+businessRate
				+ "&businessYear="	+ businessYear
				+ "&businessRemark="	+ businessRemark
				+ "&reachesType="+ reachesType;
			var result = banger.page.openDialog(url, "添加上游", 600, 480);
			if (result != undefined) {
				var urlStr = $("#queryUpUrl").val() + "?loanId="
					+ loanId +"&random=" + Math.random() * 1000;
				jQuery.ajax({
					type : 'POST',
					url : urlStr,
					data : {},
					success : function(data) {
						$("#UpList").html(data);
					}
				});
			}
		}
	);
// 添加下游人
	jQuery('#addDown')
		.click(
		function() {	var loanId = $("#loanId").val();
			var businessCustomer = $("#businessCustomer").val();
			var businessRate = $("#businessRate").val();
			var businessYear = $("#businessYear").val();
			var businessRemark = $("#businessRemark").val();
			var reachesType =0;
			var url = $("#addDownUrl").val() + "?loanId=" + loanId
				+ "&businessCustomer=" + businessCustomer
				+ "&businessRate="+businessRate
				+ "&businessYear="	+ businessYear
				+ "&businessRemark="	+ businessRemark
				+ "&reachesType="+ reachesType;
			var result = banger.page.openDialog(url, "添加下游", 600, 480);
			if (result != undefined) {
				var urlStr = $("#queryDownUrl").val() + "?loanId="
					+ loanId + "&random=" + Math.random() * 1000;
				jQuery.ajax({
					type : 'POST',
					url : urlStr,
					data : {},
					success : function(data) {
						$("#DownList").html(data);
					}
				});
			}
		});

	function dropInBusinessReaches(id) {
		var reachesType=1;
		var postJson = {};
		postJson['id'] = id;

		var urlStr = $("#removeInBusinessReachesUrl").val() + "?random=" + Math.random() * 1000;
		jQuery.ajax({
			type : 'POST',
			url : urlStr,
			data : postJson,
			success : function(data) {
				banger.page.showMessageBox({
					"type" : "success",
					"content" : "移除上游成功！"
				});
				/* urlStr = $("#queryUpUrl").val() + "?reachesType="
				 + reachesType + "&random=" + Math.random() * 1000;
				 jQuery.ajax({
				 type : 'POST',
				 url : urlStr,
				 data : {},
				 success : function(data) {
				 $("#UpList").html(data);
				 //$('table.datatbl').table();
				 }
				 });*/
			}
		});
	}







	$("#addCH").click(
		function() {
			var loanId = $("#loanId").val();
			if (loanId == "") {
				alert("请先将资料保存！");
				return;
			}
			var customerId = $("#customerId").val();
			var url = $("#addChUrl").val() + "?loanId=" + loanId
				+ "&customerId=" + customerId + "&random="
				+ Math.random() * 1000;
			var result = banger.page.openDialog(url, "添加信贷历史", 600, 480);
			if (result != undefined) {
				var urlStr = $("#queryLoanChUrl").val() + "?loanId="
					+ loanId + "&random=" + Math.random() * 1000;
				jQuery.ajax({
					type : 'POST',
					url : urlStr,
					data : {},
					success : function(data) {
						$("#chList").html(data);
					}
				});
			}
		});
	$("#addPledge").click(
		function() {
			var loanId = $("#loanId").val();
			if (loanId == "") {
				alert("请先将资料保存！");
				return;
			}
			var url = $("#addPledgeUrl").val() + "?loanId=" + loanId + "&random="
				+ Math.random() * 1000;
			var result = banger.page.openDialog(url, "添加抵质押物", 600, 480);
			if (result != undefined) {//当弹出框关闭时候
				var urlStr = $("#queryLoanPledgeUrl").val() + "?loanId="
					+ loanId + "&random=" + Math.random() * 1000;
				jQuery.ajax({
					type : 'POST',
					url : urlStr,
					data : {},
					success : function(data) {
						$("#pledgeList").html(data);
					}
				});
			}
		});


	// jQuery('#myForm').validator();
	// 保存
	$('#applySave').click(function() {
		if (!checkFormDateValid()) {
			return false;
		}
		var targetValue = $(this).attr("target-value");
		if(targetValue == "isSurvey"){
			if(!checkIsSurvey()){
				return false;
			}
		}

		var postJson = {};
		postJson['customerName'] = $("#cusName").val();
		postJson['phone'] = $("#cusMobilePhone").val();
		postJson['idCard'] = $("#cusIdcard").val();
		postJson['credentialTypeId'] = $("#cusIdtypeId").val();
		postJson['loanId'] = $("#loanId").val();
		// 开始对用户进行更新或新增
		var customerId = $("#customerId").val();
		var urlStr = "../loan/addCustomer.html";
		jQuery.ajax({
			type : 'POST',
			url : urlStr,
			data : postJson,
			success : function(data) {
				$("#customerId").val(data);
				save();
			}
		});

	});

	$('#approveSave').click(function() {
		jQuery('#attachmentDiv').validator();
		var bool = jQuery.validator({form:'attachmentDiv',extend:true,extendhandler:extendvalidator});
		if(!bool){
			return false;
		}

		var flag=true ;
		if (!checkFormDateValid()) {
			return false;
		}
		var targetValue = $(this).attr("target-value");
		if(targetValue == "isSurvey"){
			if(!checkIsSurvey()){
				return false;
			}
		}
		var postJson1={};
		postJson1['loanId'] = $("#loanId").val();
		postJson1['adviceMoney'] = $("#adviceMoney").val();
		postJson1['adviceRepayDate']=$("#adviceRepayDate").val();
		postJson1['adviceRepayWayId']=$("#adviceRepayWayId").val();
		postJson1['repaRate']=$("#adviceRate").val();
		postJson1['limitYear']=$("#adviceLimitYear").val();


		var urlStr = "../loan/jyToHkfs.html";
		jQuery.ajax({
			type: 'POST',
			url: urlStr,
			data: postJson1,
			async : false,
			success: function (data) {
				if (data == "false") {
					flag=false;
					alert("数据已更新，请重新保存还款计划！");
				}
			}
		});


		if(flag){
			var postJson = {};
			postJson['customerName'] = $("#cusName").val();
			postJson['phone'] = $("#cusMobilePhone").val();
			postJson['idCard'] = $("#cusIdcard").val();
			postJson['credentialTypeId'] = $("#cusIdtypeId").val();
			// 开始对用户进行更新或新增
			var customerId = $("#customerId").val();
			var urlStr = "../loan/addCustomer.html";
			jQuery.ajax({
				type : 'POST',
				url : urlStr,
				data : postJson,
				success : function(data) {
					$("#customerId").val(data);
					save();
				}
			});
		}


	});



	//经营模式保存
	$('#businessModeSave').click(function() {
		var postJson = {},sorftInfo="",flag=true;
		postJson['businessId'] = $("#businessId").val();
		postJson['loanId'] = $("#loanId").val();
		postJson['upperReaches'] = $("#upperReaches").val();
		postJson['lowerReaches'] = $("#lowerReaches").val();
		postJson['workFlow'] = $("#workFlow").val();
		postJson['change'] = $("#change").val();
		postJson['officeLeaseContract'] = $("#officeLeaseContract").val();
		postJson['other'] = $("#other").val();
		postJson['businessHistory'] = $("#businessHistory").val();
		/*postJson['businessOrg'] = $("#businessOrg").val();
		 postJson['businessFinance'] = $("#businessFinance").val();*/

		$("input[name=sorftInfo]:checked").each(function(){
			var sorftInfoValue=$(this).val();
			if(sorftInfoValue==132&&postJson['other']==""){
				alert("其他不能为空");
				flag=false;
				return false;
			}

			if(sorftInfoValue==131&&postJson['officeLeaseContract']==""){
				alert("办公室合同不能为空");
				flag=false;
				return false;
			}
			sorftInfo+=$(this).val()+",";
		});


		postJson['sorftInfo'] = sorftInfo.substring(0,sorftInfo.length-1);
		postJson['businessOrg'] = $("#businessOrg").val();
		postJson['businessFinance'] = $("#businessFinance").val();




		if(!flag){
			return;
		}
		var urlStr = "../loan/insertLnBusinessModel.html";
		jQuery.ajax({
			type : 'POST',
			url : urlStr,
			data : postJson,
			success : function(data) {

				var json = eval( "(" + data + ")" );
				if(json.success){
					$("#businessId").val(json.id);
					alert("保存成功")
				}else{
					alert("保存失败")

				}
			}
		});

	});
	//发展历史保存
	$('#loanHistorySave').click(function() {
		var postJson = {},familyInfo="",guaranteeInfo="";
		postJson['historyId'] = $("#historyId").val();
		postJson['loanId'] = $("#loanId").val();
		postJson['workHistory'] = $("#workHistory").val();
		postJson['specialExplanation'] = $("#specialExplanation").val();
		postJson['otherConditions'] = $("#otherConditions").val();
		$("input[name=familyInfo]:checked").each(function(){

			familyInfo+=$(this).val()+",";
		});
		postJson['familyInfo'] = familyInfo.substring(0,familyInfo.length-1);
		$("input[name=guaranteeInfo]:checked").each(function(){

			guaranteeInfo+=$(this).val()+",";
		});
		postJson['guaranteeInfo'] = guaranteeInfo.substring(0,guaranteeInfo.length-1);
		postJson['explanationAmount'] = $("#explanationAmount").val();
		postJson['flowPrinciple'] = $("#flowPrinciple").val();
		var urlStr = "../loan/insertLnLoanHistory.html";
		jQuery.ajax({
			type : 'POST',
			url : urlStr,
			data : postJson,
			success : function(data) {
				var json = eval( "(" + data + ")" );
				if(json.success){
					$("#historyId").val(json.id);
					alert("保存成功")
				}else{
					alert("保存失败")

				}
			}
		});
	});

	//访谈记录
	$('#lnRecordInterviewSave').click(function() {
		//日期
		var regDate=/^((19|20)\d{2})\-(((0[1-9])|[1-9]|10|11|12))\-(((1|2)[0-9])|[1-9]|30|31|0[1-9])$/;
		var postJson = {};
		postJson['interviewId'] = $("#interviewId").val();
		postJson['loanId'] = $("#loanId").val();
		postJson['interviewOjectName'] = $("#interviewOjectName").val();
		postJson['interviewObjectCompany'] = $("#interviewObjectCompany").val();
		postJson['interviewObjectPosition'] = $("#interviewObjectPosition").val();
		postJson['interviewObjectPhone'] = $("#interviewObjectPhone").val();
		postJson['interviewPeopleName'] = $("#interviewPeopleName").val();

		var interviewDate=jQuery.trim($("#interviewDate").val());
		if(interviewDate==""){
			alert("访谈日期不能为空");
			return;
		}

		if(!regDate.test(interviewDate)){
			alert("调查日期格式错误(格式2014-01-01))");
			return;
		}
		postJson['interviewDate'] = $("#interviewDate").val();

		postJson['checkInformation'] = $("#checkInformation").val();
		postJson['checkCompany'] = $("#checkCompany").val();
		postJson['workTime'] = $("#workTime").val();
		postJson['income'] = $("#income").val();
		postJson['majorBusiness'] = $("#majorBusiness").val();
		postJson['companyAge'] = $("#companyAge").val();
		postJson['companyNum'] = $("#companyNum").val();
		postJson['applicantEvaluation'] = $("#applicantEvaluation").val();
		postJson['applicantFamliyInfo'] = $("#applicantFamliyInfo").val();
		var urlStr = "../loan/insertLnRecordInterview.html";
		jQuery.ajax({
			type : 'POST',
			url : urlStr,
			data : postJson,
			success : function(data) {
				var json = eval( "(" + data + ")" );
				if(json.success){
					$("#interviewId").val(json.id);
					alert("保存成功")
				}else{
					alert("保存失败")

				}
			}
		});
	});

	//资产负债表保存
	$("#lnLoanBalanceSave").click(function(){
		//日期
		var regDate=/^((19|20)\d{2})\-(((0[1-9])|[1-9]|10|11|12))\-(((1|2)[0-9])|[1-9]|30|31|0[1-9])$/;
		//整数
		var regInteger=/^[0-9]*$/;
		//小数
		var regBigDecimal=/^[0-9]+([.][0-9]{1,2}){0,1}$/;
		var postJson = {};
		postJson['loanBalanceId'] = $("#loanBalanceId").val();
		postJson['loanId'] = $("#loanId").val();
		var dcrq=jQuery.trim($("#dcrq").val());
		if(dcrq==""){
			alert("调查日期不能为空");
			return;
		}

		if(!regDate.test(dcrq)){
			alert("调查日期格式错误(格式2014-01-01))");
			return;
		}
		postJson['dcrq'] = dcrq;
		postJson['bw'] = $("#bw").val();
		postJson['bwzc'] = $("#bwzc").val();
		postJson['bwfz'] = $("#bwfz").val();
		postJson['fcsl'] = $("#fcsl").val();

		if(postJson['jdcsl']!=undefined&&postJson['fcsl']!=""&&!regInteger.test(postJson['fcsl'])){
			alert("房产数量必须为整数");
			return;
		}
		postJson['fcajsl'] = $("#fcajsl").val();
		if(postJson['fcajsl']!=undefined&&postJson['fcajsl']!=""&&!regInteger.test(postJson['fcajsl'])){
			alert("房产按揭数量必须为整数");
			return;
		}
		postJson['jdcsl'] = $("#jdcsl").val();
		if(postJson['jdcsl']!=undefined&&postJson['jdcsl']!=""&&!regInteger.test(postJson['jdcsl'])){
			alert("机动车数量必须为整数");
			return;
		}
		postJson['jdcajsl'] = $("#jdcajsl").val();
		if(postJson['jdcajsl']!=undefined&&postJson['jdcajsl']!=""&&!regInteger.test(postJson['jdcajsl'])){
			alert("机动车按揭数量必须为整数");
			return;
		}
		postJson['hsfs'] = $("#hsfs").val();
		var csqyrq=jQuery.trim($("#csqyrq").val());

		if(!regDate.test(csqyrq)&&csqyrq!=""){
			alert("权益日期格式错误(格式2014-01-01))");
			return;
		}
		postJson['csqyrq'] = csqyrq;
		postJson['csqy'] = $("#csqy").val();
		if(postJson['csqy']!=undefined&&postJson['csqy']!=""&&!regBigDecimal.test(postJson['csqy'])){
			alert("初始权益为小数或整数");
			return;
		}
		postJson['csqymx'] = $("#csqymx").val();
		postJson['qjlr'] = $("#qjlr").val();
		if(postJson['qjlr']!=undefined&&postJson['qjlr']!=""&&!regBigDecimal.test(postJson['qjlr'])){
			alert("期间利润为小数或整数");
			return;
		}
		postJson['hkbj'] = $("#hkbj").val();
		if(postJson['hkbj']!=undefined&&postJson['hkbj']!=""&&!regBigDecimal.test(postJson['hkbj'])){
			alert("还款本金为小数或整数");
			return;
		}
		postJson['qjzz'] = $("#qjzz").val();
		if(postJson['qjzz']!=undefined&&postJson['qjzz']!=""&&!regBigDecimal.test(postJson['qjzz'])){
			alert("期间注资为小数或整数");
			return;
		}
		postJson['sz'] = $("#sz").val();
		if(postJson['sz']!=undefined&&postJson['sz']!=""&&!regBigDecimal.test(postJson['sz'])){
			alert("升值为小数或整数");
			return;
		}
		postJson['qntq'] = $("#qntq").val();
		if(postJson['qntq']!=undefined&&postJson['qntq']!=""&&!regBigDecimal.test(postJson['qntq'])){
			alert("期内提取为小数或整数");
			return;
		}
		postJson['zj'] = $("#zj").val();
		if(postJson['zj']!=undefined&&postJson['zj']!=""&&!regBigDecimal.test(postJson['zj'])){
			alert("折旧为小数或整数");
			return;
		}
		postJson['bz'] = $("#bz").val();
		if(postJson['bz']!=undefined&&postJson['bz']!=""&&!regBigDecimal.test(postJson['bz'])){
			alert("贬值为小数或整数");
			return;
		}
		postJson['note'] = $("#note").val();
		postJson['fx'] = $("#fx").val();
		postJson['sm'] = $("#sm").val();
		var urlStr = "../loan/insertBalance.html";
		jQuery.ajax({
			type : 'POST',
			url : urlStr,
			data : postJson,
			success : function(data) {
				var json = eval( "(" + data + ")" );
				if(json.success){
					$("#loanBalanceId").val(json.id);
					alert("保存成功")
				}else{
					alert("保存失败")

				}
			}
		});
	});
	//添加资产
	$('#addBalanceAsset').click(
		function() {

			var loanBalanceId = $("#loanBalanceId").val();
			if (loanBalanceId == "") {
				alert("请先将资产负债表保存！");
				return;
			}
			var dictionaryName="";
			if($("#appLoanTypeId").val()==1){//经营贷

				dictionaryName="LNJYZC";
			}else{

				dictionaryName="LnASSET";
			}
			var url = $("#toAddBalanceAsset").val() + "?loanBalanceId=" + loanBalanceId	+"&dictionaryName="+dictionaryName+"&random="+ Math.random() * 1000;
			var result = banger.page.openDialog(url, "添加资产", 400, 200);

			if (result != undefined) {
				var urlStr = $("#balanceAssetListUrl").val() + "?lnLoanBalanceAsset.loanBalanceId="
					+ loanBalanceId + "&random=" + Math.random() * 1000;
				jQuery.ajax({
					type : 'POST',
					url : urlStr,
					data : {},
					success : function(data) {
						$("#balanceAssetList").html(data);
					}
				});
			}
		});

	//添加负债
	$('#addBalanceDedt').click(
		function() {

			var loanBalanceId = $("#loanBalanceId").val();
			if (loanBalanceId == "") {
				alert("请先将资产负债表保存！");
				return;
			}
			var dictionaryName="";
			if($("#appLoanTypeId").val()==1){//经营贷

				dictionaryName="LNJYFZ";
			}else{

				dictionaryName="LnDEBT";
			}
			var url = $("#toAddBalanceDedt").val() + "?loanBalanceId=" + loanBalanceId	+"&dictionaryName="+dictionaryName+"&random="+ Math.random() * 1000;
			var result = banger.page.openDialog(url, "添加资产", 400, 200);

			if (result != undefined) {
				var urlStr = $("#balanceDedtListUrl").val() + "?lnLoanBalanceDebt.loanBalanceId="
					+ loanBalanceId + "&random=" + Math.random() * 1000;
				jQuery.ajax({
					type : 'POST',
					url : urlStr,
					data : {},
					success : function(data) {
						$("#balanceDedtList").html(data);
					}
				});
			}
		});

	//添加房产
	$('#addBalanceHousing').click(
		function() {

			var loanBalanceId = $("#loanBalanceId").val();
			if (loanBalanceId == "") {
				alert("请先将资产负债表保存！");
				return;
			}

			var url = $("#toAddBalanceHousing").val() + "?loanBalanceId=" + loanBalanceId+"&random="+ Math.random() * 1000;
			var result = banger.page.openDialog(url, "添加资产", 400, 300);

			if (result != undefined) {
				var urlStr = $("#balanceHousingListUrl").val() + "?lnLoanBalanceHousing.loanBalanceId="
					+ loanBalanceId + "&random=" + Math.random() * 1000;
				jQuery.ajax({
					type : 'POST',
					url : urlStr,
					data : {},
					success : function(data) {
						$("#balanceHousingList").html(data);
					}
				});
			}
		});

	//添加机动车
	$('#addBalanceVehicle').click(
		function() {

			var loanBalanceId = $("#loanBalanceId").val();
			if (loanBalanceId == "") {
				alert("请先将资产负债表保存！");
				return;
			}

			var url = $("#toAddBalanceVehicle").val() + "?loanBalanceId=" + loanBalanceId+"&random="+ Math.random() * 1000;
			var result = banger.page.openDialog(url, "添加资产", 400, 300);

			if (result != undefined) {
				var urlStr = $("#balanceVehicleListUrl").val() + "?lnLoanBalanceVehicle.loanBalanceId="	+ loanBalanceId + "&random=" + Math.random() * 1000;
				jQuery.ajax({
					type : 'POST',
					url : urlStr,
					data : {},
					success : function(data) {
						$("#balanceVehicleList").html(data);
					}
				});
			}
		});
	//添加应收账明细
	$('#addBalanceReceivable').click(
		function() {

			var loanBalanceId = $("#loanBalanceId").val();
			if (loanBalanceId == "") {
				alert("请先将资产负债表保存！");
				return;
			}

			var url = $("#toAddBalanceReceivablea").val() + "?loanBalanceId=" + loanBalanceId+"&random="+ Math.random() * 1000;
			var result = banger.page.openDialog(url, "添加资产", 400, 450);

			if (result != undefined) {
				var urlStr = $("#balanceReceivableaListUrl").val() + "?lnLoanBalanceReceivable.loanBalanceId="
					+ loanBalanceId + "&random=" + Math.random() * 1000;
				jQuery.ajax({
					type : 'POST',
					url : urlStr,
					data : {},
					success : function(data) {
						$("#balanceReceivableList").html(data);
					}
				});
			}
		});
	//可变成本及其他交叉校验
	$('#addBalanceOther').click(
		function() {

			var loanBalanceId = $("#loanBalanceId").val();
			if (loanBalanceId == "") {
				alert("请先将资产负债表保存！");
				return;
			}

			var url = $("#toAddBalanceOther").val() + "?loanBalanceId=" + loanBalanceId	+"&random="+ Math.random() * 1000;
			var result = banger.page.openDialog(url, "添加资产", 400, 200);

			if (result != undefined) {
				var urlStr = $("#balanceOtherListUrl").val() + "?lnLBalanceOther.loanBalanceId="
					+ loanBalanceId + "&random=" + Math.random() * 1000;
				jQuery.ajax({
					type : 'POST',
					url : urlStr,
					data : {},
					success : function(data) {
						$("#balanceOtherList").html(data);
					}
				});
			}
		});
	//添加损益表基础信息_经营贷
	$('#profitandlossBaseSave').click(
		function() {

			var loanProfitandlossId = $("#loanProfitandlossId").val();
			if (loanProfitandlossId == "") {
				alert("请先将损益表保存！");
				return;
			}

			var url = $("#toEditProfitandloss").val() + "?lnLoanProfitandloss.loanProfitandlossId=" + loanProfitandlossId	+"&random="+ Math.random() * 1000;
			var result = banger.page.openDialog(url, "设置损益表", 400, 200);

			if (result != undefined) {

				var resultArr = result.split(",");
				$("#yfqj").html(resultArr[0]);
				$("#zhbfb").html(resultArr[1]+"%");

				var urlStr = $("#jyDetaiList").val() + "?lnLoanProfitandlossJyDetail.loanProfitandlossId="
					+ loanProfitandlossId + "&random=" + Math.random() * 1000;
				jQuery.ajax({
					type : 'POST',
					url : urlStr,
					data : {},
					success : function(data) {
						$("#profitandlossList").html(data);
					}
				});
			}
		});
	//添加损益表信息_经营贷
	$('#profitandlossSave').click(
		function() {
			var postJson={};
			postJson['loanProfitandlossId'] = $("#loanProfitandlossId").val();
			postJson['turnover'] = $("#turnover").val();
			postJson['crosscheck'] = $("#crosscheck").val();
			var urlStr = $("#updateProfitandloss").val()+"?random="+ Math.random() * 1000;
			jQuery.ajax({
				type : 'POST',
				url : urlStr,
				data : postJson,
				success : function(data) {
					var json = eval( "(" + data + ")" );
					if(json.success){
						alert("保存成功")
					}else{
						alert("保存失败")

					}
				}
			});
		});
	//添加损益表详情信息_经营贷
	$('#profitandlossDetailSave').click(
		function() {
			var loanProfitandlossId = $("#loanProfitandlossId").val();
			if (loanProfitandlossId == "") {
				alert("请先将损益表保存！");
				return;
			}

			var url = $("#toAddjyDetail").val() + "?loanProfitandlossId=" + loanProfitandlossId	+"&random="+ Math.random() * 1000;
			var result = banger.page.openDialog(url,"",400,400);

			if (result != undefined) {

				var urlStr = $("#jyDetaiList").val() + "?lnLoanProfitandlossJyDetail.loanProfitandlossId="
					+ loanProfitandlossId + "&random=" + Math.random() * 1000;
				jQuery.ajax({
					type : 'POST',
					url : urlStr,
					data : {},
					success : function(data) {
						$("#profitandlossList").html(data);
					}
				});
			}
		});
	//添加损益表详情_消费贷
	$('#profitandlossXfDetailSave').click(
		function() {
			var loanProfitandlossId = $("#loanProfitandlossId").val();
			if (loanProfitandlossId == "") {
				alert("请先将损益表保存！");
				return;
			}

			var url = $("#toAddXfDetail").val() + "?loanProfitandlossId=" + loanProfitandlossId	+"&random="+ Math.random() * 1000;
			var result = banger.page.openDialog(url,"",400,400);

			if (result != undefined) {
				var urlStr = $("#xfDetaiList").val() + "?lnLoanProfitandlossXfDetail.loanProfitandlossId="+ loanProfitandlossId + "&random=" + Math.random() * 1000;
				jQuery.ajax({
					type : 'POST',
					url : urlStr,
					data : {},
					success : function(data) {
						$("#profitandlossXfList").html(data);
					}
				});
			}
		});
	// 保存并提交
	jQuery('#applySubmit').click(function() {

		// 判断当前资料是否已经保存
		var loanId = $("#loanId").val();
		if (loanId == "") {
			alert("资料尚未保存，请先点击保存按钮！");
			return;
		}

		if (!checkFormDateValid()) {
			return;
		}


		//var urlStrs = "../loan/jytj.html";
		//var postJsons = {};
		//postJsons['loanId'] = $("#loanId").val();
		//postJsons['assignUserId'] = $("#assignUserId").val();
		//jQuery.ajax({
		//	type : 'POST',
		//	url : urlStrs,
		//	data : postJsons,
		//	success : function(data) {
		//		if (data != "success") {
		//			alert("请点击“我的客户”，添加完整信息");
		//			return;
		//		}else{
					save();
					var isEdit = $("#isEdit").val();
					var urlStr = "../loan/submitLoan.html";
					if (isEdit == "yes") {
						urlStr = "../loan/submitLoanEdit.html";
					}
					var postJson = {};
					postJson['loanId'] = $("#loanId").val();
					postJson['assignUserId'] = $("#assignUserId").val();
					// 开始对用户进行更新或新增

					jQuery.ajax({
						type : 'POST',
						url : urlStr,
						data : postJson,
						success : function(data) {
							if (data != "success") {
							if(data!=""){
								alert(data);
							}
								return;
							}
							tab.close(GetId(), false);
						}
					});
				//}
				tab.close(GetId(), false);
		//	}
		//});
	});

	// 取消
	jQuery('#cancel').click(function() {
		if (confirm('放弃新建贷款并关闭选项卡？')) {
			tab.close(GetId(), false);
		}
	});


	//提交审核
	$("#submitApprove").click(function(){
		var isSave = $("#isSave").val();
		if(isSave != "1"){
			alert("提交审核前，请保存内容！");
			return;
		}

		var urlStrs = "../loan/jytj.html";
		var postJsons = {};
		postJsons['loanId'] = $("#loanId").val();
		postJsons['assignUserId'] = $("#assignUserId").val();
		var flag=false;
		jQuery.ajax({
			type : 'POST',
			url : urlStrs,
			data : postJsons,
			async: false,
			success : function(data) {
				if (data != "success") {
					alert("请点击“我的客户”，添加完整信息");
					flag = true;
				}}
		});
		if(flag){
			return false;
		}
		var urlStr = "../loan/submitApproval.html?&random="  + Math.random() * 1000 ;
		var postJson = {};
		postJson['loanId'] = $("#loanId").val();
		postJson['approveProcessId'] = $("#approveProcessId").val();
		postJson['isReject'] = $("#isReject").val();
		jQuery.ajax({
			url : urlStr,
			data : postJson,
			success : function(data) {
				if (data != "success") {
					alert(data);
					return;
				}
				tab.close(GetId(), false);
			}
		});
	});


	$("#accessApprove").click(function(){

		jQuery('#isApprove').validator();
		var bool = jQuery.validator({form:'isApprove',extend:true,extendhandler:extendvalidator});
		if(!bool){
			return false;
		}

		var flag = true;
		var isApproveRole = $("#isApproveRole").val();
		if(isApproveRole != "manager"){
			var resultMoney = $("#resultMoney").val();
			if(resultMoney == ""){
				alert("请输入决议金额");
				return false;
			}else if (isNaN(resultMoney)) {
				alert("决议金额必须为数字");
				return false;
			} else if (resultMoney <= 0) {
				alert("决议金额不能小于0");
				return false;
			}

			var resultLimitYear = $("#resultLimitYear").val();
			if (resultLimitYear == "") {
				alert("决议期限不能为空！");
				return false;
			}

			var resultRate = $("#resultRate").val();
			if(resultRate == ""){
				alert("决议利率不能为空！");
				return false;
			}
			var resultRepayDate = $("#resultRepayDate").val();
			if(resultRepayDate == ""){
				alert("请选择决议还款日！");
				return false;
			}

			//var resultInstalRepayMoney = $("#resultInstalRepayMoney").val();
			//if(resultInstalRepayMoney == ""){
			//	alert("请输入分期还款额");
			//	return false;
			//}

			var resultVerdict = $("#resultVerdict").val();
			if(resultVerdict == ""){
				alert("请选择审贷会决议！");
				return false;
			}

			var resultRepayWayId = $("#resultRepayWayId").val();
			if(resultRepayWayId == ""){
				alert("请选择还款方式！");
				return false;
			}

			var resultPurpose = $("#resultPurpose").val();
			if(resultPurpose == ""){
				alert("请选择贷款用途！");
				return false;
			}

			var isCrmTask = $("input[name='lnLoanInfo.isCrmTask']:checked").val();
			if(undefined==isCrmTask||null==isCrmTask||""==isCrmTask||''==isCrmTask){
				alert("请选择是否算任务！");
				return false;
			}

			var advanceRepay = $("input[name='lnLoanInfo.advanceRepay']:checked").val();
			if(undefined==advanceRepay||null==advanceRepay||""==advanceRepay||''==advanceRepay){
				alert("请选择是否同意提前还款！");
				return false;
			}

			var lnMode = $("input[name='lnLoanInfo.lnMode']:checked").val();
			if(undefined==lnMode||null==lnMode||""==lnMode||''==lnMode){
				alert("请选择'新增'或'转发'贷款！");
				return false;
			}

			var lnBenefit = $("input[name='lnLoanInfo.lnBenefit']:checked").val();
			if(undefined==lnBenefit||null==lnBenefit||""==lnBenefit||''==lnBenefit){
				alert("请选择是否利率优惠！");
				return false;
			}

			if("1"==lnBenefit){
				var originalRate = $("#originalRate").val();
				if(originalRate == ""){
					alert("请输入原利率！");
					return false;
				}
			}else{
				var originalRate = $("#originalRate").val(resultRate);
			}


		}
		if(isApproveRole == "center"){
			var maxAppLoanMoney = $("#maxAppLoanMoney").val();
			var resultMoney = $("#resultMoney").val();
			var isDoubleApproval = $("#isDoubleApproval").val();
			//if(parseFloat(resultMoney) >= parseFloat(maxAppLoanMoney)){
			if(isDoubleApproval == "1"){
				//	if(confirm("当前决议金额已经超出单人审批金额，是否进行多人审批！")){
				var loanId = $("#loanId").val();
				var url = "../loan/centerUserTogetherApprove.html?loanId=" + loanId + "&random="  + Math.random() * 1000 ;
				var result = banger.page.openDialog(url, "贷款审批", 600, 480);
				if (result != undefined) {
					$("#isApproveRole").val("");
				}else{
					return;
				}
				//	}
			}

			var postJson2={};
			postJson2['loanId'] = $("#loanId").val();
			postJson2['adviceMoney'] = $("#resultMoney").val();
			postJson2['adviceLimitYear']=$("#resultLimitYear").val();
			postJson2['adviceRepayDate']=$("#resultRepayDate").val();
			var urlStr = "../loan/jyToHkfs.html";
			jQuery.ajax({
				type : 'POST',
				url : urlStr,
				data : postJson2,
				async : false,
				success : function(data) {
					if (data == "false") {
						flag = false;
						alert("数据已更新，请重新保存还款计划！");
					}
				}})
		}

		if(flag){
			var myFormDate = $("#myForm").serialize();
			myFormDate+="&lnLoanInfo.resultVerdict="+resultVerdict;
			myFormDate+="&lnLoanInfo.resultRepayWayId="+resultRepayWayId;
			myFormDate+="&lnLoanInfo.resultPurpose="+resultPurpose;
			var url = $("#myForm").attr("action") + "?random=" + Math.random() * 1000;
			$.ajax({
				type : 'POST',
				url : url,
				data : myFormDate,
				success : function(data) {
					if (data != "success") {
						alert(data);
						return;
					}
					tab.close(GetId(), false);
				}
			});
		}
	});


	/*function previewPhoto(fileId){
	 var fileId=$("#fileId").val();
	 var url = "../loan/showImage.html?fileId=" + fileId+ "&random="  + Math.random() * 1000 ;
	 $.ajax({
	 type :'POST',
	 url:url,
	 date :fileId,
	 success:function(date){
	 if (data!="success"){
	 alert("图片格式不对");
	 return ;
	 }
	 tab.close(GetId(),false);
	 }
	 })
	 }*/






	$("#lendNoteSave").click(function(){

		var lendContractNum = $("#lendContractNum").val();
		var lendConfrimContractNum = $("#lendConfrimContractNum").val();

		if(lendContractNum == ""){
			alert("请填写合同号！");
			return;
		}

		if(lendConfrimContractNum == ""){
			alert("请填写确认合同号！");
			return;
		}

		if(lendContractNum != lendConfrimContractNum){
			alert("两个合同号不相同！");
			return;
		}

		//var isExcitForm4 = parseInt($("#isExcitForm4").val());
		//if(isExcitForm4==null || isExcitForm4<=0){
		//	alert("放贷必须提交附件");
		//	return ;
		//}

		var postJson = {};
		postJson['loanId'] = $("#loanId").val();
		postJson['lendContractNum'] = $("#lendContractNum").val();
		postJson['lendConfrimContractNum'] = $("#lendConfrimContractNum").val();
		postJson['lendNote'] = $("#lendNote").val();
		var urlStr = "../loan/submitLend.html";
		jQuery.ajax({
			type : 'POST',
			url : urlStr,
			data : postJson,
			success : function(data) {
				if(data != "success"){
					alert(data);
				}else{
					alert("保存成功！");
					tab.close(GetId(), false);
				}
			}
		});

	});


	$("#downLoan").click(function(){
		/*var postJson = {};
		 postJson['loanId'] = $("#loanId").val();
		 postJson['lendContractNum'] = $("#lendContractNum").val();;
		 postJson['lendConfrimContractNum'] = $("#lendConfrimContractNum").val();;
		 postJson['lendNote'] = $("#lendNote").val();
		 var urlStr = "../loan/submitLend.html";
		 jQuery.ajax({
		 type : 'POST',
		 url : urlStr,
		 data : postJson,
		 success : function(data) {
		 if(data != "success"){
		 alert(data);
		 }else{
		 alert("保存成功！");
		 tab.close(GetId(), false);
		 }
		 }
		 });*/
		var lendContractNum = $("#lendContractNum").val();
		var lendConfrimContractNum = $("#lendConfrimContractNum").val();

		if(lendContractNum == ""){
			alert("请填写合同号！");
			return;
		}

		if(lendConfrimContractNum == ""){
			alert("请填写确认合同号！");
			return;
		}

		if(lendContractNum != lendConfrimContractNum){
			alert("两个合同号不相同！");
			return;
		}
		var loanId =$("#loanId").val();
		var url = "../loan/downLoanExcel.html?loanId="+loanId;
		window.location = url;

	});





	//保存分配备注
	$("#saveAllotLoanNote").click(function(){
		var assignRemark = $("#assignRemark").val();
		var loanId = $("#loanId").val();
		var urlStr = "../loan/saveAllotLoanNote.html?random="  + Math.random() * 1000 + "&assignRemark=" + assignRemark + "&loanId=" + loanId;
		jQuery.ajax({
			type : 'POST',
			url : urlStr,
			data : {},
			success : function(data) {
				alert(data)
			}
		});

	});

	//退回审批
	$('#rollApprove').click(function(){
		var loanId = $("#loanId").val();
		var urlStr = "../loan/rollBackApprove.html?" + "loanId=" + loanId +"&random=" + Math.random() * 1000;
		if(!confirm('确定将该笔贷款退回审批阶段吗？')){
			return false;
		};
		//alert('退回审批成功');

		jQuery.ajax({
			type : 'POST',
			url : urlStr,
			data : {},
			success : function(data) {
				if(data=="success") {
					banger.page.showMessageBox({
						"type" : "success",
						"content" : "操作成功！"
					});
					tab.close(GetId(), false);
				}
			}
		});
	});
	//银行拒绝 、 客户拒绝
	$(".refuse").click(function() {
		if(confirm("是否确认拒绝")) {
			var loanId = $("#loanId").val();
			if (loanId == "") {
				alert("当前贷款信息有误，请刷新后重试！");
				return;
			}

			var refuseReasonTypeId = $(this).attr("data-type");
			var title = $(this).html();
			var url = "../loan/refuseLoan.html?loanId=" + loanId + "&refuseReasonTypeId=" + refuseReasonTypeId + "&random=" + Math.random() * 1000;
			var result = banger.page.openDialog(url, title, 600, 480);
			if (result != undefined) {
				alert("拒绝成功！");
				tab.close(GetId(), false);
			}
		}
	});


	//审批驳回
	$("#approveReject").click(function(){
		var loanId = $("#loanId").val();
		var url = "../loan/approveReject.html?loanId=" + loanId + "&random="  + Math.random() * 1000 ;
		var result = banger.page.openDialog(url, "审批驳回", 600, 480);
		if (result != undefined) {
			alert("审批驳回成功！");
			tab.close(GetId(), false);
		}
	});


	//陪调
	$("#togetherSurvey").click(function(){

		var loanId = $("#loanId").val();
		var url = "../loan/togetherSurvey.html?loanId=" + loanId + "&random="  + Math.random() * 1000 ;
		var result = banger.page.openDialog(url, "调查陪调", 600, 480);
		if (result != undefined) {
			$("#togetherSurveyName").html("陪调人员：" + result);
		}
	});
	//还款方式


	$("#hkfs").click(function(){
		var loanId = $("#loanId").val();
		var money = $("#adviceMoney").val();
		var limitYear = $("#adviceLimitYear").val();
		var repayDate = $("#adviceRepayDate").val();
		var registerLoanDate = $("#registerLoanDate").val();
		var repaRate = $("#adviceRate").val();
		var adviceRepayWayId = $("#adviceRepayWayId").val();
		//alert('money='+money+' ' +
		//'limitYear='+limitYear+
		//'repayDate='+repayDate+
		//'adviceRepayWayId='+adviceRepayWayId);
		if(money.replace(/(^\s*)|(\s*$)/g, "") == ""){
			alert("请填写建议金额！");
			return;
		}
		if(repaRate.replace(/(^\s*)|(\s*$)/g, "") == ""){
			alert("请填写建议利率！");
			return;
		}


		if(limitYear.replace(/(^\s*)|(\s*$)/g, "") == ""){
			alert("请填写建议期限！");
			return;
		}


		if(repayDate.replace(/(^\s*)|(\s*$)/g, "") == ""){
			alert("请填写建议还款日！");
			return;
		}

		var url = "../loan/toHkfs.html?loanId=" + loanId
			+ "&money=" + money
			+ "&limitYear="+limitYear
			+"&repayDate="+repayDate
			+"&registerLoanDate="+registerLoanDate
			+"&repaRate="+repaRate
			+"&adviceRepayWayId="+adviceRepayWayId
			+ "&random="  + Math.random() * 1000 ;
		var result = banger.page.openDialog(url, "还款计划", 600, 480);
	});





	$("#jyhkfs").click(function(){
		var loanId = $("#loanId").val();
		var money = $("#resultMoney").val();
		var limitYear = $("#resultLimitYear").val();
		var registerLoanDate = $("#registerLoanDate").val();
		var repaRate = $("#resultRate").val();
		var repayDate = $("#resultRepayDate").val();
		var resultRepayWayId = $("#resultRepayWayId").val();
		var isLend = $('#isLend').val();

		if(money.replace(/(^\s*)|(\s*$)/g, "") == ""){
			alert("请填写决议金额！");
			return;
		}

		if(limitYear.replace(/(^\s*)|(\s*$)/g, "") == ""){
			alert("请填写决议期限！");
			return;
		}

		if(repaRate.replace(/(^\s*)|(\s*$)/g, "") == ""){
			alert("请填写决议利率！");
			return;
		}




		if(repayDate.replace(/(^\s*)|(\s*$)/g, "") == ""){
			alert("请填写决议还款日！");
			return;
		}

		var url = "../loan/toHkfs.html?loanId=" + loanId
			+ "&money=" + money
			+ "&limitYear="+limitYear
			+"&repayDate="+repayDate
			+"&registerLoanDate="+registerLoanDate
			+"&repaRate="+repaRate
			+"&resultRepayWayId="+resultRepayWayId
			+"&isLend="+isLend
			+ "&random="  + Math.random() * 1000 ;
		var result = banger.page.openDialog(url, "还款计划", 600, 480);
	});

	/*
	 var url = "../loan/toHkfs.html?loanId=" + loanId
	 + "&customerId=" + customerId
	 + "&adviceMoney="+adviceMoney
	 +"&adviceLimitYear="+adviceLimitYear
	 + "&random="  + Math.random() * 1000 ;
	 var result = banger.page.openDialog(url, "还款方式", 600, 480);



	 var customerId = $("#customerId").val();
	 var adviceMoney =$().val("#adviceMoney");
	 var adviceLimitYear =$().val("#adviceLimitYear");
	 var url = $("#addChUrl").val() + "?loanId=" + loanId
	 + "&customerId=" + customerId
	 + "&adviceMoney="+adviceMoney
	 +"&adviceLimitYear="+adviceLimitYear+
	 "&random="+ Math.random() * 1000;
	 var result = banger.page.openDialog(url, "还款方式", 600, 480);
	 if (result != undefined) {
	 var urlStr = $("#queryLoanPledge").val() + "?loanId="

	 + loanId + "&random=" + Math.random() * 1000;
	 jQuery.ajax({
	 type : 'POST',
	 url : urlStr,
	 data : {},
	 success : function(data) {
	 $("#chList").html(data);
	 }
	 });
	 }*/












	//身份验证
	$("#cusCheck").click(function(){

		var customerId = $("#customerId").val();
		var loanId = $("#loanId").val();
		if(customerId == "" || loanId == ""){
			alert("页面信息错误，请刷新后重试！");
			return;
		}
		var url = "../loan/cusCheck.html?loanId=" + loanId + "&customerId=" + customerId + "&random=" + Math.random() * 1000;
		banger.page.openDialog(url, "身份验证", 600, 480);

	});

})

function save() {

	var guarantorType = $('#appGuarantorWayId').val();
	var flag=false;
	if(guarantorType=="01"){
		var loanId = $("#loanId").val();
		var urlStr = $("#queryHavingPledge").val()+"?loanId="+loanId+"&random=" + Math.random() * 1000;
		jQuery.ajax({
			type : 'POST',
			url : urlStr,
			async: false,
			data : {},
			success : function(data) {
				if(data=="false"){
					alert("请先添加抵质押物再保存!");
					flag = true;
				}
			}
		});

	}
	if(flag){
		return false;
	}
	var myFormDate = $("#myForm").serialize();


	var url = $("#myForm").attr("save-url") + "?random=" + Math.random() * 1000;
	$.ajax({
		type : 'POST',
		url : url,
		data : myFormDate,
		success : function(data) {
			$("#loanId").val(data);
			$("#myForm").attr("save-url", "../loan/editLoan.html");
			if(data=="error"){
				alert("保存失败");
				return;
			}
			$("#isSave").val(1);
			alert("保存成功");
		}
	});
}


// 添加加权损益
$('#addPro').click(
	function() {
		var loanId = $("#loanId").val();
		if (loanId == "") {
			alert("请先将资料保存！");
			return;
		}
		var url = $("#toAddProUrl").val() + "?loanId=" + loanId+"&random="
			+ Math.random() * 1000 ;
		var result = banger.page.openDialog(url, "添加加权损益", 600, 480);
		if (result != undefined) {
			var urlStr = $("#queryProUrl").val() + "?loanId="
				+ loanId + "&random=" + Math.random() * 1000;
			//alert(urlStr);
			jQuery.ajax({
				type : 'POST',
				url : urlStr,
				data : {},
				success : function(data) {
					$("#proList").html(data);
				}
			});
		}
	});

// 移除加权损益
function removePro(profitLossId , loanId) {
	var postJson = {};
	postJson['loanId'] = loanId;
	postJson['profitLossId'] = profitLossId;
	var urlStr = $("#removeProUrl").val() + "?random=" + Math.random() * 1000;
	jQuery.ajax({
		type : 'POST',
		url : urlStr,
		data : postJson,
		success : function(data) {
			banger.page.showMessageBox({
				"type" : "success",
				"content" : "移除成功！"
			});
			var postJson = {};
			postJson['write']=$('#write').val();
			var urlStr = $("#queryProUrl").val() + "?loanId="
				+ loanId + "&random=" + Math.random() * 1000;
			//alert(urlStr);
			jQuery.ajax({
				type : 'POST',
				url : urlStr,
				data : postJson,
				success : function(data) {
					$("#proList").html(data);
					//$('table.datatbl').table();
				}
			});
		}
	});
}

function editPro(profitLossId,loanId){
	//var postJson = {};
	//postJson['profitLossId'] = profitLossId;
	//postJson['loanId'] = loanId;
	var url = $("#toEditProUrl").val() + "?loanId=" + loanId + "&profitLossId=" + profitLossId + "&random="
		+ Math.random() * 1000 ;
	var result = banger.page.openDialog(url, "修改加权损益", 600, 480);
	if (result != undefined) {
		var postJson={};
		postJson['write']=$('#write').val();
		var urlStr = $("#queryProUrl").val() + "?loanId="
			+ loanId + "&random=" + Math.random() * 1000;
		//alert(urlStr);
		jQuery.ajax({
			type : 'POST',
			url : urlStr,
			data : postJson,
			success : function(data) {
				$("#proList").html(data);
			}
		});
	}
}
//添加加权
function addPro(){
	var loanId = $("#loanId").val();
	if (loanId == "") {
		alert("请先将资料保存！");
		return;
	}
	var url = $("#toAddProUrl").val() + "?loanId=" + loanId+"&random="
		+ Math.random() * 1000 ;

	var result = banger.page.openDialog(url, "添加加权损益", 600, 480);
	if (result != undefined) {
		var urlStr = $("#queryProUrl").val() + "?loanId="
			+ loanId + "&random=" + Math.random() * 1000;
		var postJson={};
		postJson['write']=$('#write').val();
		jQuery.ajax({
			type : 'POST',
			url : urlStr,
			data :postJson,
			success : function(data) {
				$("#proList").html(data);
			}
		});
	}
}

// 移除共同借贷人
function removeCo(coId, loanId, customerId) {
	var postJson = {};
	postJson['coId'] = coId;
	postJson['loanId'] = loanId;
	postJson['customerId'] = customerId;

	var urlStr = $("#removeCoUrl").val() + "?random=" + Math.random() * 1000;
	jQuery.ajax({
		type : 'POST',
		url : urlStr,
		data : postJson,
		success : function(data) {
			banger.page.showMessageBox({
				"type" : "success",
				"content" : "移除借贷人成功！"
			});
			urlStr = $("#queryLoanCoUrl").val() + "?loanId=" + loanId
			+ "&random=" + Math.random() * 1000;
			jQuery.ajax({
				type : 'POST',
				url : urlStr,
				data : {},
				success : function(data) {
					$("#coList").html(data);
					//$('table.datatbl').table();
				}
			});
		}
	});
}

function removeCo1(id) {
	var loanId=$("#loanId").val();
	var reachesType =1;
	var postJson = {};

	postJson['id'] = id;
	var urlStr = $("#removeCo1Url").val() + "?random=" + Math.random() * 1000;
	jQuery.ajax({
		type : 'POST',
		url : urlStr,
		data : postJson,
		success : function(data) {
			banger.page.showMessageBox({
				"type" : "success",
				"content" : "移除供应商成功！"
			});
			var urlStr = $("#queryUpUrl").val() + "?loanId="
				+ loanId +"&random=" + Math.random() * 1000;
			jQuery.ajax({
				type : 'POST',
				url : urlStr,
				data : {},
				success : function(data) {
					$("#UpList").html(data);
				}
			});

		}
	});
}



function removeCo2(id) {
	var loanId=$("#loanId").val();
	var reachesType =0;
	var postJson = {};

	postJson['id'] = id;
	var urlStr = $("#removeCo1Url").val() + "?random=" + Math.random() * 1000;
	jQuery.ajax({
		type : 'POST',
		url : urlStr,
		data : postJson,
		success : function(data) {
			banger.page.showMessageBox({
				"type" : "success",
				"content" : "移除客户成功！"
			});
			var urlStr = $("#queryDownUrl").val() + "?loanId="
				+ loanId + "&random=" + Math.random() * 1000;
			jQuery.ajax({
				type : 'POST',
				url : urlStr,
				data : {},
				success : function(data) {
					$("#DownList").html(data);
				}
			});
		}
	});
}




function editCo(coId,loanId, customerId){
	var postJson = {};
	postJson['coId'] = coId;
	postJson['loanId'] = loanId;
	postJson['customerId'] = customerId;
	postJson['company'] = document.getElementById(coId).value;
	postJson['companyAddressNew'] = document.getElementById("cus_"+coId).value;
	postJson['petitionerRelate'] = document.getElementById(customerId).value;
	var urlStr = $("#editCoUrl").val() + "?random=" + Math.random() * 1000;
	jQuery.ajax({
		type : 'POST',
		url : urlStr,
		data : postJson,
		success : function(data) {
			banger.page.showMessageBox({
				"type" : "success",
				"content" : "编辑借贷人成功！"
			});
			urlStr = $("#queryLoanCoUrl").val() + "?loanId=" + loanId
			+ "&random=" + Math.random() * 1000;
			jQuery.ajax({
				type : 'POST',
				url : urlStr,
				data : {},
				success : function(data) {
					$("#coList").html(data);
					//$('table.datatbl').table();
				}
			});
		}
	});

}

//编辑_消费贷损益表
function editProfitandlossXf(profitandlossJyDetailId,loanProfitandlossId){
	var url = $("#toEditXfDetail").val() + "?lnLoanProfitandlossXfDetail.profitandlossJyDetailId=" + profitandlossJyDetailId+"&random="+ Math.random() * 1000;
	var result = banger.page.openDialog(url, "编辑资产", 400, 400);

	if (result != undefined) {
		var urlStr = $("#xfDetaiList").val() + "?lnLoanProfitandlossXfDetail.loanProfitandlossId="
			+ loanProfitandlossId + "&random=" + Math.random() * 1000;
		jQuery.ajax({
			type : 'POST',
			url : urlStr,
			data : {},
			success : function(data) {
				$("#profitandlossXfList").html(data);
			}
		});
	}
}
//删除_消费贷损益表
function delProfitandlossXf(profitandlossJyDetailId,loanProfitandlossId){
	var urlStr = $("#deleteXfDetail").val() + "?lnLoanProfitandlossXfDetail.profitandlossJyDetailId="+profitandlossJyDetailId+"&random=" + Math.random() * 1000;
	jQuery.ajax({
		type : 'POST',
		url : urlStr,
		//data : postJson,
		success : function(data) {
			banger.page.showMessageBox({
				"type" : "success",
				"content" : "移除成功！"
			});
			urlStr = $("#xfDetaiList").val() + "?lnLoanProfitandlossXfDetail.loanProfitandlossId=" + loanProfitandlossId
			+ "&random=" + Math.random() * 1000;
			jQuery.ajax({
				type : 'POST',
				url : urlStr,
				data : {},
				success : function(data) {
					$("#profitandlossXfList").html(data);

				}
			});
		}
	});
}


//编辑_经营贷损益表
function editProfitandlossJy(profitandlossJyDetailId,loanProfitandlossId){



	var url = $("#toEditJyDetail").val() + "?lnLoanProfitandlossJyDetail.profitandlossJyDetailId=" + profitandlossJyDetailId+"&random="+ Math.random() * 1000;
	var result = banger.page.openDialog(url, "编辑资产", 400, 400);

	if (result != undefined) {
		var urlStr = $("#jyDetaiList").val() + "?lnLoanProfitandlossJyDetail.loanProfitandlossId="
			+ loanProfitandlossId + "&random=" + Math.random() * 1000;
		jQuery.ajax({
			type : 'POST',
			url : urlStr,
			data : {},
			success : function(data) {
				$("#profitandlossList").html(data);
			}
		});
	}
}
//删除_经营贷损益表
function delProfitandlossJy(profitandlossJyDetailId,loanProfitandlossId){
	var urlStr = $("#deleteJyDetail").val() + "?lnLoanProfitandlossJyDetail.profitandlossJyDetailId="+profitandlossJyDetailId+"&random=" + Math.random() * 1000;
	jQuery.ajax({
		type : 'POST',
		url : urlStr,
		//data : postJson,
		success : function(data) {
			banger.page.showMessageBox({
				"type" : "success",
				"content" : "移除成功！"
			});
			urlStr = $("#jyDetaiList").val() + "?lnLoanProfitandlossJyDetail.loanProfitandlossId=" + loanProfitandlossId
			+ "&random=" + Math.random() * 1000;
			jQuery.ajax({
				type : 'POST',
				url : urlStr,
				data : {},
				success : function(data) {
					$("#profitandlossList").html(data);

				}
			});
		}
	});
}


//编辑资产负债表_资产
function editBalanceAsset(loanBalanceAssetId,loanBalanceId){


	var dictionaryName="";
	if($("#appLoanTypeId").val()==1){//经营贷

		dictionaryName="LNJYZC";
	}else{

		dictionaryName="LnASSET";
	}
	var url = $("#toEditBalanceAsset").val() + "?lnLoanBalanceAsset.loanBalanceAssetId=" + loanBalanceAssetId	+"&dictionaryName="+dictionaryName+"&random="+ Math.random() * 1000;
	var result = banger.page.openDialog(url, "编辑资产", 400, 200);

	if (result != undefined) {
		var urlStr = $("#balanceAssetListUrl").val() + "?lnLoanBalanceAsset.loanBalanceId="
			+ loanBalanceId + "&random=" + Math.random() * 1000;
		jQuery.ajax({
			type : 'POST',
			url : urlStr,
			data : {},
			success : function(data) {
				$("#balanceAssetList").html(data);
			}
		});
	}
}
//删除_资产负债表_资产
function removeBalanceAsset(loanBalanceAssetId,loanBalanceId){
	var urlStr = $("#balanceAssetDeleteUrl").val() + "?lnLoanBalanceAsset.loanBalanceAssetId="+loanBalanceAssetId+"&random=" + Math.random() * 1000;
	jQuery.ajax({
		type : 'POST',
		url : urlStr,
		//data : postJson,
		success : function(data) {
			banger.page.showMessageBox({
				"type" : "success",
				"content" : "移除资产成功！"
			});
			urlStr = $("#balanceAssetListUrl").val() + "?lnLoanBalanceAsset.loanBalanceId=" + loanBalanceId
			+ "&random=" + Math.random() * 1000;
			jQuery.ajax({
				type : 'POST',
				url : urlStr,
				data : {},
				success : function(data) {
					$("#balanceAssetList").html(data);

				}
			});
		}
	});
}

//编辑资产负债表_负债
function editBalanceDedt(loanBalanceDebtId,loanBalanceId){


	var dictionaryName="";
	if($("#appLoanTypeId").val()==1){//经营贷

		dictionaryName="LNJYFZ";
	}else{

		dictionaryName="LnDEBT";
	}
	var url = $("#toEditBalanceDedt").val() + "?lnLoanBalanceDebt.loanBalanceDebtId=" + loanBalanceDebtId	+"&dictionaryName="+dictionaryName+"&random="+ Math.random() * 1000;
	var result = banger.page.openDialog(url, "编辑负债", 400, 200);

	if (result != undefined) {
		var urlStr = $("#balanceDedtListUrl").val() + "?lnLoanBalanceDebt.loanBalanceId="
			+ loanBalanceId + "&random=" + Math.random() * 1000;
		jQuery.ajax({
			type : 'POST',
			url : urlStr,
			data : {},
			success : function(data) {
				$("#balanceDedtList").html(data);
			}
		});
	}
}
//删除_资产负债表_负债
function removeBalanceDedt(loanBalanceDebtId,loanBalanceId){
	var urlStr = $("#balanceDedtDeleteUrl").val() + "?lnLoanBalanceDebt.loanBalanceDebtId="+loanBalanceDebtId+"&random=" + Math.random() * 1000;
	jQuery.ajax({
		type : 'POST',
		url : urlStr,
		//data : postJson,
		success : function(data) {
			banger.page.showMessageBox({
				"type" : "success",
				"content" : "移除负债成功！"
			});
			urlStr = $("#balanceDedtListUrl").val() + "?lnLoanBalanceDebt.loanBalanceId=" + loanBalanceId
			+ "&random=" + Math.random() * 1000;
			jQuery.ajax({
				type : 'POST',
				url : urlStr,
				data : {},
				success : function(data) {
					$("#balanceDedtList").html(data);

				}
			});
		}
	});
}

//编辑资产负债表_房产
function editLoanBalanceHousing(loanBalanceHousingId,loanBalanceId){

	var url = $("#toEditBalanceHousing").val() + "?lnLoanBalanceHousing.loanBalanceHousingId=" + loanBalanceHousingId	+"&random="+ Math.random() * 1000;
	var result = banger.page.openDialog(url, "编辑房产", 400, 200);

	if (result != undefined) {
		var urlStr = $("#balanceHousingListUrl").val() + "?lnLoanBalanceHousing.loanBalanceId="
			+ loanBalanceId + "&random=" + Math.random() * 1000;
		jQuery.ajax({
			type : 'POST',
			url : urlStr,
			data : {},
			success : function(data) {
				$("#balanceHousingList").html(data);
			}
		});
	}
}
//删除_资产负债表_房产
function removeLoanBalanceHousing(loanBalanceHousingId,loanBalanceId){
	var urlStr = $("#balanceHousingDeleteUrl").val() + "?lnLoanBalanceHousing.loanBalanceHousingId="+loanBalanceHousingId+"&random=" + Math.random() * 1000;
	jQuery.ajax({
		type : 'POST',
		url : urlStr,
		//data : postJson,
		success : function(data) {
			banger.page.showMessageBox({
				"type" : "success",
				"content" : "移除房产成功！"
			});
			urlStr = $("#balanceHousingListUrl").val() + "?lnLoanBalanceHousing.loanBalanceId=" + loanBalanceId
			+ "&random=" + Math.random() * 1000;
			jQuery.ajax({
				type : 'POST',
				url : urlStr,
				data : {},
				success : function(data) {
					$("#balanceHousingList").html(data);

				}
			});
		}
	});
}
//编辑资产负债表_机动车
function editLoanBalanceVehicle(loanBalanceVehicleId,loanBalanceId){

	var url = $("#toEditBalanceVehicle").val() + "?lnLoanBalanceVehicle.loanBalanceVehicleId=" + loanBalanceVehicleId+"&random="+ Math.random() * 1000;
	var result = banger.page.openDialog(url, "编辑机动车", 400, 300);

	if (result != undefined) {
		var urlStr = $("#balanceVehicleListUrl").val() + "?lnLoanBalanceVehicle.loanBalanceId="
			+ loanBalanceId + "&random=" + Math.random() * 1000;
		jQuery.ajax({
			type : 'POST',
			url : urlStr,
			data : {},
			success : function(data) {
				$("#balanceVehicleList").html(data);
			}
		});
	}
}
//删除_资产负债表_机动车
function removeLoanBalanceVehicle(loanBalanceVehicleId,loanBalanceId){
	var urlStr = $("#balanceVehicleDeleteUrl").val() + "?lnLoanBalanceVehicle.loanBalanceVehicleId="+loanBalanceVehicleId+"&random=" + Math.random() * 1000;
	jQuery.ajax({
		type : 'POST',
		url : urlStr,
		//data : postJson,
		success : function(data) {
			banger.page.showMessageBox({
				"type" : "success",
				"content" : "移除机动车成功！"
			});
			urlStr = $("#balanceVehicleListUrl").val() + "?lnLoanBalanceVehicle.loanBalanceId=" + loanBalanceId
			+ "&random=" + Math.random() * 1000;
			jQuery.ajax({
				type : 'POST',
				url : urlStr,
				data : {},
				success : function(data) {
					$("#balanceVehicleList").html(data);

				}
			});
		}
	});
}
//编辑资产负债表_应收账明细
function editLoanBalanceReceivable(loanBalanceReceivableId,loanBalanceId){


	var url = $("#toEditBalanceReceivablea").val() + "?lnLoanBalanceReceivable.loanBalanceReceivableId=" + loanBalanceReceivableId	+"&random="+ Math.random() * 1000;
	var result = banger.page.openDialog(url, "编辑应收账明细", 400, 450);

	if (result != undefined) {
		var urlStr = $("#balanceReceivableaListUrl").val() + "?lnLoanBalanceReceivable.loanBalanceId="
			+ loanBalanceId + "&random=" + Math.random() * 1000;
		jQuery.ajax({
			type : 'POST',
			url : urlStr,
			data : {},
			success : function(data) {
				$("#balanceReceivableList").html(data);
			}
		});
	}
}
//删除_资产负债表_应收账明细
function removeLoanBalanceReceivable(loanBalanceReceivableId,loanBalanceId){
	var urlStr = $("#balanceReceivableaDeleteUrl").val() + "?lnLoanBalanceReceivable.loanBalanceReceivableId="+loanBalanceReceivableId+"&random=" + Math.random() * 1000;
	jQuery.ajax({
		type : 'POST',
		url : urlStr,
		//data : postJson,
		success : function(data) {
			banger.page.showMessageBox({
				"type" : "success",
				"content" : "移除应收账明细成功！"
			});
			urlStr = $("#balanceReceivableaListUrl").val() + "?lnLoanBalanceReceivable.loanBalanceId=" + loanBalanceId
			+ "&random=" + Math.random() * 1000;
			jQuery.ajax({
				type : 'POST',
				url : urlStr,
				data : {},
				success : function(data) {
					$("#balanceReceivableList").html(data);

				}
			});
		}
	});
}
//编辑资产负债表_可变本及其他交叉检验
function editLoanBalanceOther(loanBalanceOtherId,loanBalanceId){

	var url = $("#toEditBalanceOther").val() + "?lnLBalanceOther.loanBalanceOtherId=" + loanBalanceOtherId	+"&random="+ Math.random() * 1000;
	var result = banger.page.openDialog(url, "编辑可变本及其他交叉检验", 400, 200);

	if (result != undefined) {
		var urlStr = $("#balanceOtherListUrl").val() + "?lnLBalanceOther.loanBalanceId="
			+ loanBalanceId + "&random=" + Math.random() * 1000;
		jQuery.ajax({
			type : 'POST',
			url : urlStr,
			data : {},
			success : function(data) {
				$("#balanceOtherList").html(data);
			}
		});
	}
}
//删除_资产负债表_可变成本及其他交叉检验
function removeLoanBalanceOther(loanBalanceOtherId,loanBalanceId){

	var urlStr = $("#balanceOtherDeleteUrl").val() + "?lnLBalanceOther.loanBalanceOtherId="+loanBalanceOtherId+"&random=" + Math.random() * 1000;
	jQuery.ajax({
		type : 'POST',
		url : urlStr,
		//data : postJson,
		success : function(data) {
			banger.page.showMessageBox({
				"type" : "success",
				"content" : "移除可变成本及其他交叉检验成功！"
			});
			urlStr = $("#balanceOtherListUrl").val() + "?lnLBalanceOther.loanBalanceId=" + loanBalanceId
			+ "&random=" + Math.random() * 1000;
			jQuery.ajax({
				type : 'POST',
				url : urlStr,
				data : {},
				success : function(data) {
					$("#balanceOtherList").html(data);

				}
			});
		}
	});
}
// 移除担保人
function removeGu(guId, loanId, customerId) {
	var postJson = {};
	postJson['guId'] = guId;
	postJson['loanId'] = loanId;
	postJson['customerId'] = customerId;
	var urlStr = $("#removeGuUrl").val() + "?random=" + Math.random() * 1000;
	jQuery.ajax({
		type : 'POST',
		url : urlStr,
		data : postJson,
		success : function(data) {
			banger.page.showMessageBox({
				"type" : "success",
				"content" : "移除担保人成功！"
			});
			//var loanId = $("#txtLoanIdId").val();
			urlStr = $("#queryLoanGuUrl").val() + "?loanId=" + loanId
			+ "&random=" + Math.random() * 1000;
			jQuery.ajax({
				type : 'POST',
				url : urlStr,
				data : {},
				success : function(data) {
					$("#guList").html(data);
				}
			});
		}
	});
}

// 编辑担保人
function editGu(guId, loanId, customerId) {
	var postJson = {};
	postJson['guId'] = guId;
	postJson['loanId'] = loanId;
	postJson['customerId'] = customerId;
	postJson['company'] = document.getElementById(guId).value;
	postJson['companyAddressNew'] = document.getElementById("GU_"+guId).value;
	postJson['petitionerRelate'] = document.getElementById(customerId).value;
	var urlStr = $("#editGuUrl").val() + "?random=" + Math.random() * 1000;
	jQuery.ajax({
		type : 'POST',
		url : urlStr,
		data : postJson,
		success : function(data) {
			banger.page.showMessageBox({
				"type" : "success",
				"content" : "编辑担保人成功！"
			});
			var loanId = $("#txtLoanIdId").val();
			urlStr = $("#queryLoanGuUrl").val() + "?loanId=" + loanId
			+ "&random=" + Math.random() * 1000;
			jQuery.ajax({
				type : 'POST',
				url : urlStr,
				data : {},
				success : function(data) {
					$("#guList").html(data);
				}
			});
		}
	});
}

// 移除信贷历史
function removeCh(chId, loanId) {
	var postJson = {};
	postJson['chId'] = chId;
	var urlStr = $("#removeChUrl").val() + "?random=" + Math.random() * 1000;
	jQuery.ajax({
		type : 'POST',
		url : urlStr,
		data : postJson,
		success : function(data) {
			banger.page.showMessageBox({
				"type" : "success",
				"content" : "移除信贷历史成功！"
			});
			urlStr = $("#queryLoanChUrl").val() + "?loanId=" + loanId
			+ "&random=" + Math.random() * 1000;
			jQuery.ajax({
				type : 'POST',
				url : urlStr,
				data : {},
				success : function(data) {
					$("#chList").html(data);
				}
			});
		}
	});
}

// 编辑信贷历史
function editCh(chId, loanId) {
	var postJson = {};
	postJson['chId'] = chId;
	postJson['finaceSource'] = document.getElementById("CH1_"+chId).value;
	postJson['loanPurpose'] = document.getElementById("CH2_"+chId).value;
	postJson['loanMoney'] = document.getElementById("CH3_"+chId).value;
	postJson['loanDate'] = document.getElementById("CH4_"+chId).value;
	postJson['loanLimitYear'] = document.getElementById("CH5_"+chId).value;
	postJson['repayMonth'] = document.getElementById("CH6_"+chId).value;
	postJson['overdueInformation'] = document.getElementById("CH7_"+chId).value;
	postJson['balanceMoney'] = document.getElementById("CH8_"+chId).value;

	var urlStr = $("#editChUrl").val() + "?random=" + Math.random() * 1000;

	jQuery.ajax({
		type : 'POST',
		url : urlStr,
		data : postJson,
		success : function(data) {
			banger.page.showMessageBox({
				"type" : "success",
				"content" : "编辑信贷历史成功！"
			});
			urlStr = $("#queryLoanChUrl").val() + "?loanId=" + loanId
			+ "&random=" + Math.random() * 1000;
			jQuery.ajax({
				type : 'POST',
				url : urlStr,
				data : {},
				success : function(data) {
					$("#chList").html(data);
				}
			});
		}
	});
}


//移除抵质押物
function removePledge(pledgeId) {
	var postJson = {};
	postJson['pledgeId'] = pledgeId;
	var urlStr = $("#removePledgeUrl").val() + "?random=" + Math.random() * 1000;
	jQuery.ajax({
		type : 'POST',
		url : urlStr,
		data : postJson,
		success : function(data) {
			banger.page.showMessageBox({
				"type" : "success",
				"content" : "移除抵质押物成功！"
			});
			urlStr = $("#queryLoanPledgeUrl").val() + "?loanId=" + $("#loanId").val();
			+ "&random=" + Math.random() * 1000;
			jQuery.ajax({
				type : 'POST',
				url : urlStr,
				data : {},
				success : function(data) {
					$("#pledgeList").html(data);
				}
			});
		}
	});
}


// 验证贷款人、共同借贷人、担保人的身份证是否有相同的
function checkIdCard() {
	// 贷款人身份证
	var txtIDCard = jQuery.trim($('#txtIDCard').val());
	var applyCusIdCard = "";
	if (txtIDCard != "" && txtIDCard.indexOf("*") > 0) {
		applyCusIdCard = $('#txtOldIDCard').val();
	} else {
		applyCusIdCard = txtIDCard;
	}
	// 共同借贷人身份证
	var coCusIdCard = "";
	$("input[name='customerCoIdCard']").each(function() {
		coCusIdCard += $(this).val();
	});
	// 担保人身份证
	var guCusIdCard = "";
	$("input[name='customerGuIdCard']").each(function() {
		guCusIdCard += $(this).val();
	});
	var warningMsg = "身份证号码重复，贷款人、共同借贷人、担保人不能为同一客户，请重新填写！";

	if (applyCusIdCard != "" && guCusIdCard == applyCusIdCard) {
		var coWarnMsg = "贷款人与担保人" + warningMsg;
		banger.page.showMessageBox(coWarnMsg);
		return false;
	}
	if (applyCusIdCard != "" && coCusIdCard == applyCusIdCard) {
		var guWarnMsg = "贷款人与共同借贷人" + warningMsg;
		banger.page.showMessageBox(guWarnMsg);
		return false;
	}
	return true;
}

// 验证当前选择的客户是否在三个月内有贷款审批失败的记录，有，则不能重新申请，没有，则可以继续申请
function isLoanFail(customerId) {
	var bool = false;
	var url = $("#isLoanFailUrl").val();
	jQuery.ajax({
		type : 'POST',
		async : false,
		url : url + '?customerId=' + customerId + '&RandNum=' + Math.random()
		* 1000,
		data : {},
		success : function(data) {
			if (data == 0) {
				// 可以申请
				bool = false;
			} else if (data == 1) {
				// 不能申请
				bool = true;
			}
		}
	});
	return bool;
}

// 验证当前客户是否为不良客户
function isNogood(customerId) {
	var bool = false;
	var url = $("#isNogoodUrl").val();
	jQuery.ajax({
		type : 'POST',
		async : false,
		url : url + '?customerId=' + customerId + '&RandNum=' + Math.random()
		* 1000,
		data : {},
		success : function(data) {
			if (data == 0) {
				// 非不良客户，可以申请贷款
				bool = false;
			} else if (data == 1) {
				// 不良客户，不能申请贷款
				bool = true;
			}
		}
	});
	return bool;
}

// 标记是否是申请拒绝贷款
var isRefuseLoan = false;
/**
 * 关闭页卡确认框
 */
function cfirm() {
	var isEdit = '$!isEdit';
	if (isEdit) {
		if (confirm('放弃修改贷款并关闭选项卡？')) {
			return true;
		}
	} else {
		if (confirm('放弃新建贷款并关闭选项卡？')) {
			return true;
		}
	}
}

// 验证必须输入项是否有输入数据
function extendvalidator() {
	var bool = true;

	return bool;
}

function paramsName() {
	var customerIds = "";
	$("input[name='customerCoGu']").each(function() {
		customerIds += $(this).val() + ",";
	});
	if (customerIds.length > 0) {
		customerIds = customerIds.substring(0, customerIds.length - 1);
	}
	return 'customerName=' + jQuery('#txtCustomerName').val() + "&customerIds="
		+ customerIds;
}

function paramsNum() {
	var customerIds = "";
	$("input[name='customerCoGu']").each(function() {
		customerIds += $(this).val() + ",";
	});
	if (customerIds.length > 0) {
		customerIds = customerIds.substring(0, customerIds.length - 1);
	}
	return 'cphNumber=' + jQuery('#txtCphNumber').val() + "&customerIds="
		+ customerIds;
}

function paramsAddr() {
	var customerIds = "";
	$("input[name='customerCoGu']").each(function() {
		customerIds += $(this).val() + ",";
	});
	if (customerIds.length > 0) {
		customerIds = customerIds.substring(0, customerIds.length - 1);
	}
	return 'idCard=' + jQuery('#txtIDCard').val() + "&customerIds="
		+ customerIds;
}

function selectNumber(cphNumber) {
	var curNumber = jQuery.trim($('#txtCphNumber').val());
	if (curNumber.indexOf("*") != -1) {
		dial('$!lnLoan.cphNumber', '$!lnLoan.customerId')
	} else {
		dial(curNumber, '$!lnLoan.customerId');
	}
}

function checkDate(date) {
	if (date.length <= 0 || date == "") {
		$(this).removeAttr("style");
		canSubmit = true;
		return;
	}
	var pattern = /^((19|20)\d{2})\-(((0[1-9])|[1-9]|10|11|12))\-(((1|2)[0-9])|[1-9]|30|31|0[1-9])$/;
	if (pattern.test(date)) {
		$(this).removeAttr("style");
		return true;
	} else {
		alert("日期格式错误(格式2014-01-01))");
		$(this).css({
			backgroundColor : "red"
		});
		return false;
	}
}

function checkDbrIdCard() {
	var code = $("#cusIdcard").val();
	var cusIdtypeId = $("#cusIdtypeId").val()+"";
	if((!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2]\d|3[0-1])\d{3}(\d|X)$/i.test(code))&&cusIdtypeId=="1" ){
		alert("身份证号格式错误");
		return;
	}

	if(cusIdtypeId=="1") {
		showBirthday(code);
	}else{
		$('#cusBirthday').removeAttr('readonly');
	}

}


function ExtractionBirthday(cusIdcard,cusBirthday){
	var txtparm=document.getElementById(cusIdcard).value;
	var year =txtparm.substring(6,10);
	var month=txtparm.substring(10,12);
	var date =txtparm.substring (12,14);
	document.getElementById(cusBirthday).value=year+'-'+month+'-'+date;
}


function checkFormDateValid() {

	/*
	 * var registerMicroBizCenter = $("#registerMicroBizCenter").val();
	 * if(registerMicroBizCenter == ""){ alert("微贷事业中心为必选项"); return false; }
	 */

	var registerApplyDate = $("#registerApplyDate").val();
	if (registerApplyDate == "") {
		alert("申请日期为必填项");
		return false;
	}
	var pattern = /^((19|20)\d{2})\-(((0[1-9])|[1-9]|10|11|12))\-(((1|2)[0-9])|[1-9]|30|31|0[1-9])$/;
	if (!pattern.test(registerApplyDate)) {
		alert("申请日期格式错误(格式2014-01-01))");
		return false;
	}

	var registerLoanDate = $("#registerLoanDate").val();

	if (registerLoanDate == "") {
		alert("需要贷款日期为必填项");
		return false;
	}
	if (!pattern.test(registerLoanDate)) {
		alert("需要贷款日期格式错误(格式2014-01-01))");
		return false;
	}

	var registerInfoSourceIds = $("#registerInfoSourceIds").val();
	if (registerInfoSourceIds == "") {
		alert("信息来源必须选择！");
		return false;
	}

	var appLoanTypeId = $("#appLoanTypeId").val();
	if (appLoanTypeId == "") {
		alert("贷款类型必填！");
		return false;
	}

	var appMoney = $("#appMoney").val();
	if (appMoney == "") {
		alert("贷款金额不能为空");
		return false;
	} else if (isNaN(appMoney)) {
		alert("贷款金额必须为数字");
		return false;
	} else if (appMoney <= 0) {
		alert("贷款金额不能小于0");
		return false;
	}

	var appLimitYear = $("#appLimitYear").val();
	if (appLimitYear.replace(/(^\s*)|(\s*$)/g, "") == "") {
		alert("贷款期限不能为空！");
		return false;
	}

	var appLoanPurpose = $("#appLoanPurpose").val();
	if (appLoanPurpose.replace(/(^\s*)|(\s*$)/g, "") == "") {
		alert("贷款用途不能为空！");
		return false;
	}

	var cusName = $("#cusName").val();
	if (cusName.replace(/(^\s*)|(\s*$)/g, "") == "") {
		alert("请输入申请人姓名！");
		return false;
	}


	var cusEducationId = $("#cusEducationId").val();
	if (cusEducationId.replace(/(^\s*)|(\s*$)/g, "") == "") {
		alert("请输入教育程度！");
		return false;
	}

	var cusMarriage = $("#cusMarriage").val();
	if (cusMarriage.replace(/(^\s*)|(\s*$)/g, "") == "") {
		alert("请输入婚姻状况！");
		return false;
	}
	var cusLivingStatusId = $("#cusLivingStatusId").val();
	if (cusLivingStatusId.replace(/(^\s*)|(\s*$)/g, "") == "") {
		alert("请输入居住状况！");
		return false;
	}





	var cusIdtypeId = $("#cusIdtypeId").val();
	if (cusIdtypeId == "") {
		alert("请选择证件类型");
		return false;
	}

	var cusIdcard = $("#cusIdcard").val();
	var cusIdtypeId = $("#cusIdtypeId").val()+"";
	if (cusIdcard == "") {
		alert("请证件号码！");
		return false;
	} else if (!/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2]\d|3[0-1])\d{3}(\d|X)$/i.test(cusIdcard) && cusIdtypeId=="1") {
		alert("身份证格式错误！");
		return false;
	}else{
		if(cusIdtypeId=="1") {
			showBirthday(cusIdcard);
		}
	}
	var cusMobilePhone = $("#cusMobilePhone").val();
	var reg = /^[1][34587]\d{9}$/;
	if (cusMobilePhone == "") {
		alert("请输入手机号！");
		return false;
	} else if (!reg.test(cusMobilePhone)) {
		alert("手机号码格式有误！");
		return false;
	}

	var cusFirstImpress = $("#cusFirstImpress").val();
	if(cusFirstImpress.replace(/(^\s*)|(\s*$)/g, "") == ""){
		alert("请输入第一印象！");
		return false;
	}

	/*
	 var cafGrossMargin = $("#cafGrossMargin").val();
	 if(cafGrossMargin.replace(/(^\s*)|(\s*$)/g, "") == ""){
	 alert("请输入毛利率！");
	 return false;
	 }
	 */

	/*var cafNetMargin = $("#cafNetMargin").val();
	 if(cafNetMargin.replace(/(^\s*)|(\s*$)/g, "") == ""){
	 alert("请输入净利率！");
	 return false;
	 }
	 */



	if (appLoanTypeId == "1") { // 经营贷
		var bizCompany = $("#bizCompany").val();
		if (bizCompany.replace(/(^\s*)|(\s*$)/g, "") == "") {
			alert("请输入企业名称");
			return false;
		}

		var cafMonthlySales = $("#cafMonthlySales").val();
		if (cafMonthlySales.replace(/(^\s*)|(\s*$)/g, "") == "") {
			alert("请输入月销售额");
			return false;
		}

		var cafReceivableMoney = $("#cafReceivableMoney").val();
		if (cafReceivableMoney.replace(/(^\s*)|(\s*$)/g, "") == "") {
			alert("请输入应收账款");
			return false;
		}

		/*	var cafGrossMargin = $("#cafGrossMargin").val();
		 if (cafGrossMargin == "") {
		 alert("请输入毛利润");
		 return false;
		 }

		 var cafNetMargin = $("#cafNetMargin").val();
		 if (cafNetMargin == "") {
		 alert("请输入净利润");
		 return false;
		 }*/

		var cafInventory = $("#cafInventory").val();
		if (cafInventory.replace(/(^\s*)|(\s*$)/g, "") == "") {
			alert("请输入存货");
			return false;
		}

		var cafDebt = $("#cafDebt").val();
		if (cafDebt.replace(/(^\s*)|(\s*$)/g, "") == "") {
			alert("请输入负债");
			return false;
		}

		var cafTotalMoney = $("#cafTotalMoney").val();
		if (cafTotalMoney.replace(/(^\s*)|(\s*$)/g, "") == "") {
			alert("请输入总 资 产");
			return false;
		}

		var cafOther = $("#cafOther").val();
		if (cafOther.replace(/(^\s*)|(\s*$)/g, "") == "") {
			alert("请输入其他收入");
			return false;
		}


		var bizOrgId = $("#bizOrgId").val();
		if(bizOrgId == "02"){

			var gtgsh = $("#gtgsh").val();
			if(gtgsh == ""){
				alert("请选择法律实体选项！");
				return false;
			}else if(gtgsh == "01"){
				var bizOrgCode = $("#bizOrgCode").val();
				if(bizOrgCode == ""){
					alert("请填写组织机构代码！");
					return false;
				}
			}else {
				var bizSocialCreditNum = $("#bizSocialCreditNum").val();
				if(bizSocialCreditNum == ""){
					alert("请填写社会信用号码！");
					return false;
				}
			}


		}

	} else if (appLoanTypeId == "2") {// 消费贷
		var companyName = $("#companyName").val();
		if (companyName.replace(/(^\s*)|(\s*$)/g, "") == "") {
			alert("请输入工作单位");
			return false;
		}

		/*		var companyNature = $("#companyNature").val();
		 if (companyNature == "") {
		 alert("请输入单位性质");
		 return false;
		 }*/

		var companyJob = $("#companyJob").val();
		if (companyJob.replace(/(^\s*)|(\s*$)/g, "") == "") {
			alert("请输入工作岗位");
			return false;
		}

		var companyWorkYear = $("#companyWorkYear").val();
		if (companyWorkYear.replace(/(^\s*)|(\s*$)/g, "") == "") {
			alert("请输入工作年限");
			return false;
		}

		var companyMonthlyIncoming = $("#companyMonthlyIncoming").val();
		if (companyMonthlyIncoming.replace(/(^\s*)|(\s*$)/g, "") == "") {
			alert("请选择月收入水平");
			return false;
		}

		/*var companyNature = $("#companyNature").val();
		 if (companyNature == "") {
		 alert("请选择月收入水平");
		 return false;
		 }*/

		var companyAddress = $("#companyAddress").val();
		if (companyAddress.replace(/(^\s*)|(\s*$)/g, "") == "") {
			alert("请输入单位地址");
			return false;
		}
	}

	return true;
}





function showBirthday(val) {
	var birthdayValue;
	var sex;
	if (15 == val.length) { //15位身份证号码
		birthdayValue = val.charAt(6) + val.charAt(7);
		if (parseInt(birthdayValue) < 10) {
			birthdayValue = '20' + birthdayValue;
		}
		else {
			birthdayValue = '19' + birthdayValue;
		}
		birthdayValue = birthdayValue + '-' + val.charAt(8) + val.charAt(9) + '-' + val.charAt(10) + val.charAt(11);
		if (parseInt(val.charAt(14) / 2) * 2 != val.charAt(14))
			sex = '男';
		else
			sex = '女';
		sr = birthdayValue;
	}
	if (18 == val.length) { //18位身份证号码
		birthdayValue = val.charAt(6) + val.charAt(7) + val.charAt(8) + val.charAt(9) + '-' + val.charAt(10) + val.charAt(11)
		+ '-' + val.charAt(12) + val.charAt(13);
		if (parseInt(val.charAt(16) / 2) * 2 != val.charAt(16))
			sex = '男';
		else
			sex = '女';

		sr = birthdayValue;
	}


	$("#cusBirthday").val(birthdayValue);

	$("#cusSex option[value='"+ sex +"']").attr("selected", "selected");
	$("#cusSex").select();


}


function checkIsSurvey(){
	var adviceMoney = $("#adviceMoney").val();
	if(adviceMoney == ""){
		alert("请输入建议金额");
		return false;
	}
	else if (isNaN(adviceMoney)) {
		alert("建议金额必须为数字");
		return false;
	}

	var adviceLimitYear = $("#adviceLimitYear").val();
	if (adviceLimitYear.replace(/(^\s*)|(\s*$)/g, "") == "") {
		alert("建议期限不能为空！");
		return false;
	}

	var adviceRate = $("#adviceRate").val();
	if (adviceRate.replace(/(^\s*)|(\s*$)/g, "") == "") {
		alert("建议利率不能为空！");
		return false;
	}

	var adviceRepayDate = $("#adviceRepayDate").val();
	if(adviceRepayDate.replace(/(^\s*)|(\s*$)/g, "") == ""){
		alert("建议还款日不能为空！");
		return false;
	}

	var adviceLendWayId = $("#adviceLendWayId").val();
	if(adviceLendWayId.replace(/(^\s*)|(\s*$)/g, "") == ""){
		alert("请选择放款方式！");
		return false;
	}



	var adviceRepayWayId = $("#adviceRepayWayId").val();
	if(adviceRepayWayId.replace(/(^\s*)|(\s*$)/g, "") == ""){
		alert("请选择还款方式！");
		return false;
	}

	var adviceVerdict = $("input[name='lnLoanInfo.adviceVerdict']:checked").val();
	if(undefined==adviceVerdict||null==adviceVerdict||""==adviceVerdict||''==adviceVerdict){
		alert("请选择调查建议！");
		return false;
	}
	//var isExcitForm4 = parseInt($("#isExcitForm4").val());
	//if(isExcitForm4==null || isExcitForm4<=0){
	//	alert("调查阶段通过必须提交附件");
	//	return ;
	//}

	return true;
};

function lnDeviation(){
	return true;
};


$('#lnDeviationSave').click(function(){

	var postJson = {};
	postJson['remark']=$("#deviationRemark").val();
	postJson['loanId'] = $("#loanId").val();
	postJson['loanMaritalStatus']= tdmap.get("loanMaritalStatus");
	postJson['loanAge']= tdmap.get("loanAge");
	postJson['loanWorkStatus']= tdmap.get("loanWorkStatus");
	postJson['loanBizYear']= tdmap.get("loanBizYear");
	postJson['loanLocalYear']= tdmap.get("loanLocalYear");
	postJson['loanFinanceStatus']= tdmap.get("loanFinanceStatus");
	postJson['loanCreditStatus']= tdmap.get("loanCreditStatus");
	postJson['loanChildStatus']= tdmap.get("loanChildStatus");
	postJson['loanSpouseStatus']= tdmap.get("loanSpouseStatus");
	postJson['guMaritalStatus']= tdmap.get("guMaritalStatus");
	postJson['guAge']= tdmap.get("guAge");
	postJson['guWorkStatus']= tdmap.get("guWorkStatus");
	postJson['guBizYear']= tdmap.get("guBizYear");
	postJson['guFinanceStatus']= tdmap.get("guWorkStatus");
	postJson['guCreditStatus']= tdmap.get("guCreditStatus");
	var urlStr = "../loan/insertLnDeviationAnalsysis.html";
	jQuery.ajax({
		type: 'POST',
		url: urlStr,
		data: postJson,
		success:function(data){
			if(data == "success"){
				window.returnValue="success";
				alert("保存成功");

			}
			window.close();
		}
	});
});


