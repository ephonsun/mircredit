/*资产负债表计算*/
//计算
function calculate(){

      var qy=rmoney($("#qy").text());
	  //$("#qy_1").text(fmoney(qy,3));
	  //应有权益:
	var yyqy=Number($("#csqy").val())+Number($("#qjlr").val())+Number($("#hkbj").val())+Number($("#qjzz").val())+Number($("#sz").val())-Number($("#qntq").val())-Number($("#zj").val())-Number($("#bz").val())	 
	$("#yyqy").html("<span style='font-weight:bold'>"+fmoney(yyqy,3)+"</span>");
	//差别
	$("#cb").html("<span style='font-weight:bold'>"+fmoney(qy-yyqy,3)+"</span>");
	//差别率：
	var cb4=(qy-yyqy)/Number($("#qjlr").val())*100;
	$("#cbl").html("<span style='font-weight:bold'>"+fmoney(cb4,3)+"%"+"</span>");	

 }
//格式化数字
function fmoney(num,n)
{

    var reg=/\,/;
    if(reg.test(num)){
        return num;
    }
    if(isNaN(num)||num==0||!isFinite(num)){
        return 0;
    }
    //参数说明：num 要格式化的数字 n 保留小数位
    num=Number(num)
    num = num.toFixed(n).toString();
    var re = /(-?\d+)(\d{3})/;
    while(re.test(num)) {
        num = num.replace(re,"$1,$2");
    }
    return num.substring(0,num.length-1);
}
//将数字还原
function rmoney(num)
{
    return Number(num.replace(/,/g,""));

}
//初始权益相关计算
 $(".csqy").blur(function(){
		calculate()
 })
 
 //资产相关计算
  //消费贷
 if($("#appLoanTypeId").val()=="2"){
	//消费贷资产
	var totalAsset=0;
	var totalAssetFlow=0;
	for(var i=161;i<167;i++){
		var tatol=0;
		$("."+i).each(function(){
				
			tatol+=Number($(this).val());			
		})
		totalAsset+=Number(tatol)                                           
		$("#"+i).html("<span style='font-weight:bold'>"+fmoney(tatol,3)+"</span>");
		if(i<165){
			totalAssetFlow+=Number(tatol);		
		}
	}
	//流动资产合计
	$("#totalAssetFlow").html("<span style='font-weight:bold'>"+fmoney(totalAssetFlow,3)+"</span>");
	//总资产
	$("#totalAsset").html("("+fmoney(totalAsset,3)+")");
	//负债及权益合计
	$("#fzjqyhj").html("<span style='font-weight:bold'>"+fmoney(totalAsset,3)+"</span>");
	//负债计算
	   var zfz=0;
	
	for(var i=167;i<171;i++){
	var tatol=0;
		$("."+i).each(function(){
			tatol+=Number($(this).val());
		})
		zfz+=Number(tatol);
		$("#"+i).html("<span style='font-weight:bold'>"+fmoney(tatol,3)+"</span>");
	}
	$("#zfz").html("("+fmoney(zfz,3)+")");
	$("#qy").html("<span style='font-weight:bold'>"+fmoney(totalAsset-zfz,3)+"</span>");
	//实际权益	
	  $("#sjqy").html("<span style='font-weight:bold'>"+fmoney(totalAsset-zfz,3)+"</span>");
	
}


 if($("#appLoanTypeId").val()=="1"){//经营贷
	//资产
    var totalAsset=0;
	var totalAssetFlow=0;
	for(var i=201;i<=207;i++){
	var tatol=0;
		$("."+i).each(function(){
			tatol+=Number($(this).val());

		})
		totalAsset+=Number(tatol)                                           
		$("#"+i).html("<span style='font-weight:bold'>"+fmoney(tatol,3)+"</span>");
		if(i<205){
			totalAssetFlow+=Number(tatol);
		}
	}
    	//流动资产合计
		$("#totalAssetFlow").html("<span style='font-weight:bold'>"+fmoney(totalAssetFlow,3)+"</span>");
		//总资产
		$("#totalAsset").html("("+fmoney(totalAsset,3)+")")
		//负债及权益合计
		$("#fzjqyhj").html("<span style='font-weight:bold'>"+fmoney(totalAsset,3)+"</span>");

		//负债
		 var dqfzhj=0;
		 var cqfzhj=0;
	     var zfz=0;
		for(var i=208;i<=213;i++){
		var tatol=0;
			$("."+i).each(function(){
				tatol+=Number($(this).val());
			})
			zfz+=tatol;
			$("#"+i).html("<span style='font-weight:bold'>"+fmoney(tatol,3)+"</span>");
			if(i<=211){
				dqfzhj+=Number(tatol);
			}
		  if(i>211){
			cqfzhj+=Number(tatol);
		  }
	}
	 //总负债
	 $("#zfz").html("("+fmoney(zfz,3)+")");
	//长期负债合计
	// $("#cqfzhj").html("<span style='font-weight:bold'>"+fmoney(cqfzhj,3)+"</span>");
	 //短期负债合计
    $("#dqfzhj").html("<span style='font-weight:bold'>"+fmoney(dqfzhj,3)+"</span>");
    $("#qy").html("<span style='font-weight:bold'>"+fmoney(totalAsset-zfz,3)+"</span>");
	//实际权益	
	  $("#sjqy").html("<span style='font-weight:bold'>"+fmoney(totalAsset-zfz,3)+"</span>");
 }
	calculate()
//可变成本及其他交叉检验
$("#balanceOtherList table tr").each(function(trindex,tritem){
	  var td2=Number($(this).find("td").eq(1).text());
			   var td3=Number($(this).find("td").eq(2).text());
			   var td4=td3-td2
			   $(this).find("td").eq(3).text(fmoney(td4,3))
			   var td5=(td4/td3)*100
			   $(this).find("td").eq(4).text(fmoney(td5,3))
});

//可变成本及其他交叉检验数字格式化
$("#balanceOtherList table tr").each(function(){
			$(this).find("td").eq(1).text(fmoney($(this).find("td").eq(1).text(),3))
	 		$(this).find("td").eq(2).text(fmoney($(this).find("td").eq(2).text(),3))
})

//应收账明细数字格式化
$("#balanceReceivableList table tr").each(function(){
			$(this).find("td").eq(1).text(fmoney($(this).find("td").eq(1).text(),3))
	 		$(this).find("td").eq(7).text(fmoney($(this).find("td").eq(7).text(),3))
			$(this).find("td").eq(9).text(fmoney($(this).find("td").eq(9).text(),3))
})
//总资产数字格式化
$("#balanceDedtList table tr,#balanceAssetList table tr").each(function(){
	 		$(this).find("td").eq(2).html(fmoney($(this).find("td").eq(2).text(),3))
})