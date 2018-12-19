//刷新Grid
function refreshGrid(gridId,html)
{
	var elem = $(html);
	var grid = $("#"+gridId);
	grid[0].innerHTML="";
	grid.append(elem);
	var total = $('#total').val();
	if(total!=null){
		$('#queryCount').text(total);
	}
	else {
		$('#queryCount').text("0");
	} 
}

//改变归属
function changeBelongTo(val){
	switch(val){
		case "my":
			$("#my").show();
			$("#userList, #deptList").hide();
			break;
		case "sub":
			$("#userList").show();
			$("#my, #deptList").hide();
			break;
		case "dept":
			$("#deptList").show();
			$("#my, #userList").hide();
			break;
		case "un":
			$("#my,#userList,#deptList").hide();
			break;
		default:
			$("#my,#userList,#deptList").hide();
			break;
	}
}

//在选项卡中打开查看客户网页
function showCustTab(id)
{
	tab.add({"id":"showCust"+id, pid: GetId(), title: "查看客户", url: "../customer/browseCustomer.html?actionType=browse&random="+Math.random()*1000000+"&&crmCustomer.customerId="+id,lock:false});
}