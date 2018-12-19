<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/ipproot/common/include/rootPath.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>栏目图片列表标签设置</title>
		<SCRIPT LANGUAGE="javascript" type="text/javascript" src="<%=root%>/Editor/js/LabelJs.js"></SCRIPT>
		<link href='admin_style.css' rel='stylesheet' type='text/css'>
		<script language="javascript">
		var showLabelTreeType = String("<%=request.getParameter("showLabelTreeType")%>");
		function SelectColor(what){
		    var dEL = document.all(what);
		    var sEL = document.all("bg_" + what);
		    var url = "<%=root%>/Editor/editor_selcolor.jsp?color=" + encodeURIComponent(dEL.value);
		    var arr = showModalDialog(url,window,"dialogWidth:280px;dialogHeight:250px;help:no;scroll:no;status:no");
		    if (arr) {
		        dEL.value=arr;
		        sEL.style.backgroundColor=arr;
		    }
	    }
	    
	    function insertLabel() {
	    	var strReturnValue = "{$ImageList(";
	    	var t = "";
	    	var objForm = document.LabelForm;
	    	//P1
	    	t = ColumnTree.getSectionValue();
	    	if(t == "") {
				if(showLabelTreeType == "jsManage") {
	    			//说明是JS管理中使用，这时一定要选择一个栏目，否则不能提交
	    			alert("您没有选择任何栏目");
	    			return;
	    		} else {
					if(!confirm("您没有选择任何栏目,是否设置为当前栏目?")){
						return;
					} else {
						t = "$id";
					}
				}
	    		
	    	}
	    	strReturnValue = strReturnValue + t + ",";
	    	
	    	//用于存放每个得到的表单对象

			var objE;
			//用于存放每个表单的值

			var s;
			//循环整个表单
			for(var i=0;i<objForm.length;i++) {
				objE = objForm.elements[i];
				//分为两种情况，一种为输入表单，一种为选择表单，分别处理如下

				if(String(objE.type) == "text") {
					s = objE.value;
					strReturnValue = strReturnValue + s + ",";
				} else if(String(objE.type) == "select-one") {   //处理选择表单
					//循环当前表单，得到选择中的值

					for(var j=0;j<objE.length;j++) {
						if(objE[j].selected) {
							s = objE[j].value;
						}
					}
					strReturnValue = strReturnValue + s + ",";
				}
			}
		
		//循环处理完整个表单后，会多出一个“,”号，将这个符号处理掉

		strReturnValue = strReturnValue.substring(0,strReturnValue.length - 1) + ")}";
	    	window.returnValue = strReturnValue;
	    	window.close();
	    	
	    }
	    
		</script>
	</head>

	<body>
			<%
		String parament = new String((request.getParameter("parament")==null?"":request.getParameter("parament")).getBytes("ISO-8859-1"),"UTF-8");
		String bolColumnID = new String((request.getParameter("bolColumnID")==null?"":request.getParameter("bolColumnID")).getBytes("ISO-8859-1"),"UTF-8");
		String selectid = "";
		if(!parament.equals("")) {
			String arrparament[] = parament.split(",");
			selectid = arrparament[0];
		} 
		%>
		<center>
			<form name="LabelForm">
			<table cellSpacing=0 cellPadding=0 width="100%" height="100%" border=0>
				
				<tr class='title' align="center">
					<td height='22' colspan="2"><b>栏目图片列表</b></td>
				</tr>
				<tr>
					<td width="35%" height="425">
						<iframe ID='ColumnTree' src="<%=root%>/site/itemmanage/ColumnTreeAction.do?treetype=10&selectedid=<%=selectid%>" frameborder='1' scrolling='yes' width='100%' height='100%'></iframe>
					</td>
					<td valign="top" width="65%">
						<table width='100%' border='0' align='center' align='center' cellpadding='2' cellspacing='1' class='border' height="100%">
						  
						  <tr class='tdbg'>
						    <td align="right" width="35%">&nbsp;取值范围：</td>
						    <td align="left" width="65%">&nbsp;<select name="P2">
						    <option value="0" selected>本级栏目文章</option>
						    <option value="1">本栏目及子栏目文章</option>
						    </select></td>
						  </tr>
						  
						  <tr class='tdbg'>
						    <td align="right">&nbsp;一行显示图片数：</td>
						    <td align="left">&nbsp;<input type="text" name="P3" value="5" maxlength="5"></td>
						  </tr>
						  
						   <tr class='tdbg'>
						    <td align="right">&nbsp;一列显示图片数：</td>
						    <td align="left">&nbsp;<input type="text" name="P4" value="6" maxlength="5"></td>
						  </tr>
						  
						  <tr class='tdbg'>
						    <td align="right">&nbsp;图片显示宽度：</td>
						    <td align="left">&nbsp;<input type="text" name="P5" value="100" maxlength="5"></td>
						  </tr>
						  
						  <tr class='tdbg'>
						    <td align="right">&nbsp;图片显示高度：</td>
						    <td align="left">&nbsp;<input type="text" name="P6" value="150" maxlength="5"></td>
						  </tr>
						  
						  <tr class='tdbg'>
   						 <td align="right">
    					   &nbsp;图片占用单元格百分比：

    					</td>
    					<td align="left">
    					&nbsp;<input type="text" name="P7" value="70%" size="7" >
   						 </td>
  						</tr>
  						
  						<tr class='tdbg'>
   						 <td align="right">
    					&nbsp;文字占用单元格百分比：

    					</td>
    					<td align="left">
    					&nbsp;<input type="text" name="P8" value="30%" size="7">
    					</td>
  						</tr>
						  
						  <tr class='tdbg'>
						    <td align="right">&nbsp;是否显示文字：</td>
						    <td align="left">&nbsp;<select name="P9">
						    	<option value="0" selected>不显示</option>
						    	<option value="1">显示</option>
						    </select>
						    </td>
						  </tr>

						  <tr class='tdbg'>
						    <td align="right">&nbsp;显示文字类型：</td>
						    <td align="left">&nbsp;<select name="P10">
						    	<option value="1" selected>标题</option>
						    	<option value="2">文章摘要</option>
						    </select></td>
						  </tr>
						   <tr class='tdbg'>
						    <td align="right">&nbsp;标题显示格式：</td>
						    <td align="left">&nbsp;<select name="P11">
						    <option value="0" selected>图片在文字上面</option>
						    <option value="1">图片在文字下面</option>
						    <option value="2">图片在文字左边</option>
						    <option value="3">图片在文字右边</option>
						    </select>
						    </td>
						  </tr>
		  
						  <tr class='tdbg'>
						    <td align="right">&nbsp;显示文字个数：</td>
						    <td align="left">&nbsp;<input type="text" name="P12" value="15" maxlength="5"></td>
						  </tr>
						  
						  <tr class='tdbg'>
						    <td align="right">&nbsp;分页样式：</td>
						    <td align="left">&nbsp;<input type="text" name="P13" value="" maxlength="5"></td>
						  </tr>
						  <tr class='tdbg'>
						    <td align="right">&nbsp;分页类型：</td>
						    <td align="left">&nbsp;<input type="text" name="P14" value="1" maxlength="5"></td>
						  </tr>
						   <tr class='tdbg'>
						    <td align="right">&nbsp;取值文章状态：</td>
						    <td align="left">&nbsp;<select name="P15">
						    	<option value="0" selected>导读文章</option>
						    	<option value="1">所有文章</option>
						    </select>
						    
						    </td>
						  </tr>
						 
						</table>
					</td>
					
				</tr>
				<tr class='tdbg'>
			    <td align='center' colspan=2>
			    <input TYPE='button' value=' 确定 ' onClick='javascript:insertLabel()' id="btsubmit" disabled>&nbsp;
			    <input TYPE='button' value=' 取消 ' onClick='javascript:window.close();'>
			    </td>
				</tr>
			</table>
			</form>
			
			<script language="javascript">
	var parament = String("<%=parament%>");
	var bolColumnID = String("<%=bolColumnID%>");
	setLabelFormElement(parament,bolColumnID);
	</script>
			
		</center>
	</body>
</html>
