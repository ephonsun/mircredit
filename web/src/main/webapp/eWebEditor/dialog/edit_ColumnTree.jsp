<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/ipproot/common/include/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>栏目树</title>
	<base target="_self">
	<LINK href="<%=root%>/common/css/treeview.css" type="text/css" rel="stylesheet">
	<LINK href="<%=root%>/common/css/windows.css" type="text/css" rel="stylesheet">
	<link href="<%=root%>/common/css/dtree.css" type="text/css" rel="stylesheet">
	<script language="javascript" src="<%=root%>/common/scripts/mztreeview_check.js"></script>
	<script type="text/javascript">
		//全局变量 ，用于记录当前被选中的值

		var GLOBAL_VALUE = "";
		
		//单击事件
		function checkNodeClick(checkFlag,nodeValue,idv) {
		  	if(checkFlag) {
		  		if(Number(nodeValue) > 0){
		  			GLOBAL_VALUE = nodeValue;
		  		}
		  		for(var i=0;i<document.all.length;i++) {
					if(document.all[i].type == "checkbox" && document.all[i].checked == true) {
						if(Number(document.all[i].value) != nodeValue) {
							document.all[i].checked = false;
						}
					}
				}
		  	} else {
		  		GLOBAL_VALUE = "";
		  	}
		
		}
		
		//返回当前被中的值

		function getSectionValue() {
			return GLOBAL_VALUE;
		}
		
		function setDefault() {
			//设定默认值

			parent.document.all.btsubmit.disabled = false;
			var nodeValue = String('<%=request.getParameter("selectedid")%>');
			if(nodeValue != null && nodeValue != "" && Number(nodeValue) > 0) {
				var checkFlag = true;
				var treenodestr = tree.nodes["c"+clumnprent[nodeValue]+"_c"+nodeValue];
				if(treenodestr!=null)
				if(checkFlag){
				if(treenodestr.indexOf("isChecked")==-1)
				tree.nodes["c"+clumnprent[nodeValue]+"_c"+nodeValue] = treenodestr+"isChecked:true;";
				}else{
				if(treenodestr.indexOf("isChecked")!=-1)
				tree.nodes["c"+clumnprent[nodeValue]+"_c"+nodeValue] = treenodestr.substring(0,treenodestr.length-15);
				}
			}
		}

	</script>
	
	</head>

	<body topmargin="0" leftmargin="0" class="contentbodymargin" onload="javascript:setDefault();">
	<table border="0" cellpadding="2" cellspacing="0" width="95%">
		<tr>
			<td id="dtree">
			</td>
		</tr>
	</table>
	<c:set var="form" value="${columnForm}" />
	<script type="text/javascript">
		var treenode = new Array();
		var clumnprent = new Array();
		window.tree = new MzTreeView("tree");
		tree.setIconPath("<%=root%>/common/images/");
		tree.nodes["0_c0"] = 'text:栏目信息;valueId:0;isChecked:no;';
		<c:forEach items="${form.listTree}" var="tree">
			tree.nodes["c<c:out value="${tree.clumnParent}"/>_c<c:out value="${tree.clumnId}"/>"] = 'text:<c:out value="${tree.clumnName}"/>;valueId:<c:out value="${tree.clumnId}"/>;'
			treenode[treenode.length]= <c:out value="${tree.clumnId}"/>;
			clumnprent[<c:out value="${tree.clumnId}"/>] = <c:out value="${tree.clumnParent}"/>;
		</c:forEach>
  		document.getElementById('dtree').innerHTML = tree.toString();
  		
  		
	</script>
	</body>
</html>
