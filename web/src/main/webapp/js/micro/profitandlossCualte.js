////////////////。。。。。。。。。。。损益表。。。。。。。。。。。。/////////////////////////////////////////////////////
//生成动态表格

//经营贷损益表

if($("#appLoanTypeId_yx").val()=="1"){

	var count,len;
    // return [mon,year1,month1[0]];
	 var arry=dynamicTable();
     //动态生成行
	if($("#write").val()=="1"){
		count="<th><label title='预期代表月份'>预期代表月份</label></th><th><label title='平均值'>平均值</label></th><th><label title='总计'>总计</label></th><th><label title='操作'>操作</label></th>";
		len=4;
	}else{
		count="<th><label title='预期代表月份'>预期代表月份</label></th><th><label title='平均值'>平均值</label></th><th><label title='总计'>总计</label></th>";
		len=3;
	}	
		$(count).insertAfter("#thTable");

	for(var x=0;x<len;x++){
		 $("<th></th>").insertAfter("#srze");
		 $("<th></th>").insertAfter("#kbcbze");
		  $("<th></th>").insertAfter("#mlr");
		 $("<th></th>").insertAfter("#gdcbze");
		  $("<th></th>").insertAfter("#sqlr");
		 $("<th></th>").insertAfter("#jlr");
		 $("<th></th>").insertAfter("#kzpzj");
	
	}



	for(var i=(Number(arry[2])+Number(arry[0]));i>=Number(arry[2]);i--){
	 //for(var i=0;i<5;i++){
	if(i>12){
	
		 var monTd=i-12;
		
		//++monTd
	if(monTd>0&&monTd<10){
		 $("<th><label title='"+(Number(arry[1])+1)+"年0"+(monTd)+"月'>"+(Number(arry[1])+1)+"年0"+(monTd)+"月</label></th>").insertAfter("#thTable");
	}else{
	
		$("<th><label title='"+(Number(arry[1])+1)+"年"+(monTd)+"月'>"+(Number(arry[1])+1)+"年"+(monTd)+"月</label></th>").insertAfter("#thTable");
	}
	}else if(i>0&&i<10){
		 $("<th><label title='"+arry[1]+"年0"+(i)+"月'>"+arry[1]+"年0"+(i)+"月</label></th>").insertAfter("#thTable");
	}else{
	
		 $("<th><label title='"+arry[1]+"年"+(i)+"月'>"+arry[1]+"年"+(i)+"月</label></th>").insertAfter("#thTable");
	}
	     
		 $("<th></th>").insertAfter("#srze");
		 $("<th></th>").insertAfter("#kbcbze");
		  $("<th></th>").insertAfter("#mlr");
		 $("<th></th>").insertAfter("#gdcbze");
		  $("<th></th>").insertAfter("#sqlr");
		 $("<th></th>").insertAfter("#jlr");
		 $("<th></th>").insertAfter("#kzpzj");
		 
	}
	

	//计算
	//经营贷损益表
	function tableCalculation(calculationId,assignmentId,k){
		var total=0;
		$("#"+calculationId).find("tr").each(function(){
			
			total+=Number($(this).find("td").eq(k).text());
			
		})

		 //收入总额
			$(this).find("th").eq(k).text(total);
	    if(assignmentId==""){
			return total;
		}
		   $("#"+assignmentId).parent("tr").find("th").eq(k).text(total);
		
		
	}
	for(var k=2;k<=Number(arry[0])+3;k++){
		//收入总额
		tableCalculation("srxm","srze",k);
		//可变成本总额
		tableCalculation("kbcbxm","kbcbze",k);
		
		//毛利润
		 var mlrTotal= Number($("#srze").parent("tr").find("th").eq(k).text())- Number($("#kbcbze").parent("tr").find("th").eq(k).text())
		 $("#mlr").parent("tr").find("th").eq(k).text(mlrTotal);
		 //固定成本总额
		tableCalculation("gdcbxm","gdcbze",k);	
		 //税前利润
		 var sqlrTotal=Number($("#srze").parent("tr").find("th").eq(k).text())- Number($("#kbcbze").parent("tr").find("th").eq(k).text())-Number($("#gdcbze").parent("tr").find("th").eq(k).text())
		 $("#sqlr").parent("tr").find("th").eq(k).text(sqlrTotal)
		 //所得税总额	
		 var sdsze=tableCalculation("sdsxm","",k);
		  //净利润
		 $("#jlr").parent("tr").find("th").eq(k).text(Number($("#sqlr").parent("tr").find("th").eq(k).html())-sdsze);
		 
		  //其他支出
		 var qtzcze = tableCalculation("qtzcxm","",k);
		  //其他收入
		 var qtsrze = tableCalculation("qtsrxm","",k);
		
		//可支配资金
			$("#kzpzj").parent("tr").find("th").eq(k).text(Number($("#jlr").parent("tr").find("th").eq(k).text())+qtsrze-qtzcze);
		
		}
		
	//求总计和平均值
	var zhbfb=jQuery.trim($("#zhbfb").text().split("%")[0]);

	
	$("#profitandlossJxTable").find("tr").each(function(i){
		
		if(i==0){
			return true;
		}
		var total=0;
		for(var k=2;k<=Number(arry[0])+2;k++){
		 
			if($(this).find("td").length!=0){
				total+=Number($(this).find("td").eq(k).text())
			}
			if($(this).find("th").length!=0){
				total+=Number($(this).find("th").eq(k).text())
			}
			
		}
		
		if($(this).find("td").length!=0){
		
			$(this).find("td").eq(arry[0]+5).text(total)
		
			$(this).find("td").eq(arry[0]+4).text(total/((Number(arry[0]))+Number(zhbfb)/100))
		
		}
		
		if($(this).find("th").length!=0){
			$(this).find("th").eq(arry[0]+5).text(total)
		
			$(this).find("th").eq(arry[0]+4).text(total/((Number(arry[0]))+Number(zhbfb)/100))
		
		
		}
		
		
			
	})
	
	$("#mlr").parent("tr").find("th").eq(1).text(fmoney($("#mlr").parent("tr").find("th").eq(arry[0]+5).text()/$("#srze").parent("tr").find("th").eq(arry[0]+5).text()*100,3)+"%");
	//格式化数字
	$("#profitandlossJxTable tr").each(function(i){
	
		if(i==0){
			return true;
		}
		for(var k=2;k<=Number(arry[0])+5;k++){
		 
			if($(this).find("td").length!=0){
				$(this).find("td").eq(k).html("<label title='"+fmoney($(this).find("td").eq(k).text(),3)+"'>"+fmoney($(this).find("td").eq(k).text(),3)+"</label>")
			}
			if($(this).find("th").length!=0){
				
				$(this).find("th").eq(k).html("<label title='"+fmoney($(this).find("th").eq(k).text(),3)+"'>"+fmoney($(this).find("th").eq(k).text(),3)+"</label>")
			}
			
		}
	})
	
}

	//消费贷损益表
if($("#appLoanTypeId_yx").val()=="2"){

 function theCalculation(idcl){
	
    var total=0;
	$("."+idcl).each(function(){
		
		total+=Number($(this).val());

	});

	if($("#"+idcl).size()>0){
	//total=par.fmoney(total)
	   $("#"+idcl).text(total);
	}


		
		return total;
}


//收入总额计算


var mainIncomePriorYear=theCalculation("mainIncomePriorYear")
var mainIncomelCurrentYear=theCalculation("mainIncomelCurrentYear")

//固定支出计算
var fixedCostsPriorYear=theCalculation("fixedCostsPriorYear")
var fixedCostsCurrentYear=theCalculation("fixedCostsCurrentYear")


//税前收入计算
var pretaxPriorYear=mainIncomePriorYear-fixedCostsPriorYear
var pretaxCurrentYear=mainIncomelCurrentYear-fixedCostsCurrentYear
$("#pretaxPriorYear").text(fmoney(pretaxPriorYear,3));
$("#pretaxCurrentYear").text(fmoney(pretaxCurrentYear,3));


//净收入计算
 //个人所得税之和
	 var taxesPriorYear=theCalculation("taxesPriorYear")
	 var taxesCurrentYear =theCalculation("taxesCurrentYear")
	var netPriorYear =pretaxPriorYear-taxesPriorYear
	var netCurrentYear=pretaxCurrentYear-taxesCurrentYear;
	$("#netPriorYear").text(netPriorYear);
	$("#netCurrentYear").text(netCurrentYear);
//可支配资金计算
 //其他支出
  var otherCostsPriorYear=theCalculation("otherCostsPriorYear")
  var otherCostsCurrentYear=theCalculation("otherCostsCurrentYear")
  //其他收入
  var otherIncomePriorYear=theCalculation("otherIncomePriorYear")
  var otherIncomeCurrentYear=theCalculation("otherIncomeCurrentYear")
  var availablePriorYear=netPriorYear+otherIncomePriorYear-otherCostsPriorYear
  var availableCurrentYear=netCurrentYear+otherIncomeCurrentYear-otherCostsCurrentYear
  $("#availablePriorYear").text(availablePriorYear);
  $("#availableCurrentYear").text(availableCurrentYear);
  
  //格式化数字
	$("#profitandlossXfTable tr").each(function(i){
	
		if(i==0){
			return true;
		}
		for(var k=2;k<=3;k++){
		
			if($(this).find("td").length!=0){
				$(this).find("td").eq(k).html("<label title='"+fmoney($(this).find("td").eq(k).text(),3)+"'>"+fmoney($(this).find("td").eq(k).text(),3)+"</label>")
			}
			if($(this).find("th").length!=0){
				
				$(this).find("th").eq(k).html("<label title='"+fmoney($(this).find("th").eq(k).text(),3)+"'>"+fmoney($(this).find("th").eq(k).text(),3)+"</label>")
			}
			
		}
	})
}

//动态生成表格
function dynamicTable(yfqjStr){
    if(yfqjStr==undefined){

    yfqjStr=document.getElementById("yfqj").innerHTML


    }
    var yfqj=yfqjStr.split("--")
    var year1=jQuery.trim(yfqj[0]).substr(0,4);//开始年

    var year1=jQuery.trim(yfqj[0]).substr(0,4);//开始年
    var month1=jQuery.trim(yfqj[0]).substr(5).split("月");//开始月
    var year2=jQuery.trim(yfqj[1]).substr(0,4);//结束年
    var month2=jQuery.trim(yfqj[1]).substr(5).split("月");//结束月
    var yea=Number(year2)-Number(year1);//年份差
    var mon
    if(yea<0){
    alert("开始月份不能大于结束月份")
    return false;
    }
    if(yea==0){
    mon=Number(month2[0])-Number(month1[0])
    if(mon<0){
    alert("开始月份不能大于结束月份")
    return false;
    }
    }
    if(yea>0){
    mon=12-Number(month1[0])+Number(month2[0])
    if(mon>=12){
    alert("月份区间不能相差12个月")
    return false;
    }
    }
    return [mon,year1,month1[0]];
    }