<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
		<title>栏目新闻列表标签设置</title>
		
		<link href='admin_style.css' rel='stylesheet' type='text/css'>
		      <style rel=stylesheet type=text/css>
        td {
        FONT-SIZE: 9pt; COLOR: #000000; FONT-FAMILY: 宋体,Dotum,DotumChe,Arial;line-height: 150%; 
        }
        INPUT {
        BACKGROUND-COLOR: #ffffff; 
        BORDER-BOTTOM: #666666 1px solid;
        BORDER-LEFT: #666666 1px solid;
        BORDER-RIGHT: #666666 1px solid;
        BORDER-TOP: #666666 1px solid;
        COLOR: #666666;
        HEIGHT: 18px;
        border-color: #666666 #666666 #666666 #666666; font-size: 9pt
        }
        .favMenu {
            BACKGROUND: #ffffff; COLOR: windowtext; CURSOR: hand;line-height: 150%; 
        }
        .favMenu DIV {
            WIDTH: 100%;height: 5px;
        }
        .favMenu A {
            COLOR: windowtext; TEXT-DECORATION: none
        }
        .favMenu A:hover {
            COLOR: windowtext; TEXT-DECORATION: underline
        }
        .topFolder {
            
        }
        .topItem {

        }
        .subFolder {
            PADDING: 0px;BACKGROUND: #ffffff;
        }
        .subItem {
            PADDING: 0px;BACKGROUND: #ffffff;
        }
        .sub {
            BACKGROUND: #ffffff;DISPLAY: none; PADDING: 0px;
        }
        .sub .sub {
            BORDER: 0px;BACKGROUND: #ffffff;
        }
        .icon {
            HEIGHT: 18px; MARGIN-RIGHT: 5px; VERTICAL-ALIGN: absmiddle; WIDTH: 18px
        }
        .outer {
            BACKGROUND: #ffffff;PADDING: 0px;
        }
        .inner {
            BACKGROUND: #ffffff;PADDING: 0px;
        }
        .scrollButton {
            BACKGROUND: #ffffff; BORDER: #f6f6f6 1px solid; PADDING: 0px;
        }
        .flat {
            BACKGROUND: #ffffff; BORDER: #f6f6f6 1px solid; PADDING: 0px;
        }
              </style>
	<script>
	
	    function InsertLabel()
		{
		//alert("新闻栏目id:"+document.form1.newscolumnkindid.value);
		//alert("取值范围:"+document.form1.P1.value);
		//alert("列表记录条数:"+document.form1.P2.value);
		//alert("标题字数:"+document.form1.P3.value);
		//alert("日期显示格式:"+document.form1.P4.value);
		//alert("时间格式:"+document.form1.P5.value);
		//alert("标题宽度:"+document.form1.P6.value);
		//alert("是否显示栏目名称:"+document.form1.P7.value);
		//alert("是否分页显示列表:"+document.form1.P8.value);
		var p1=document.form1.P1.value;
		var p2=document.form1.P2.value;
		
		if(p1=="")
		{
		  alert('请选择取值范围');
		  return false;
		}
		else
		{
		   var label="{$Top_"+document.form1.P1.options[document.form1.P1.selectedIndex].value+"_"+p2+"}";
		  // alert(label);
		   
		   dialogArguments.insertHTML(label);
		   window.returnValue = label;
		   window.close();
		}
				 
       }
	   
	   
	   
	   function showMessage(label)
	   {
	      alert(label);
	   }
	</script>

</head>

	<body onLoad="">
	<form name="form1" method="post" action="" >
	  <table width="607" border="0" align="center">
	  
        <tr>
         
          <td width="383"><table width="79%" height="47%">
		  
		    <tr class='tdbg'>
    <td height="21" colspan="2" align="right">&nbsp;        <div align="center"><strong>职业教育网信息上传排行榜</strong></div>
      <div align="center"></div></td>
    </tr>
  <tr class='tdbg'>
    <td width="40%" height="36" align="right">&nbsp;取值范围：</td>
    <td align="left" width="60%">&nbsp;
        <select name="P1" id="P1">
		  <option value="" selected>请选择</option>
		  <option value="0">国家级重点学校</option>
		  <option value="1">师范性学校</option>
		  <option value="2">省级重点学校</option>
		  <option value="3">各地市教育局</option>
        </select>
    </td>
  </tr>
  <tr class='tdbg'>
    <td height="21" align="right">&nbsp;列表记录条数：</td>
    <td align="left">&nbsp;
        <input name="P2" type="text" id="P2" value="5" maxlength="5"></td>
  </tr>

    


</table></td>
        </tr>
        <tr>
          <td><div align="center">
            <input type="button" name="Submit" value="提交" onClick="InsertLabel()">
            <input type="reset" name="Submit2" value="重置">
          </div></td>
          <td>            </td></tr>
      </table>
    </form>
<script>
  function exitSys(){
     if(confirm("真的要退出系统吗？"))
     {
       self.close();
       self.parent.parent.location.href = "lookSafeExitVm.action";
     }
  }
</script>

	</body>
</html>
