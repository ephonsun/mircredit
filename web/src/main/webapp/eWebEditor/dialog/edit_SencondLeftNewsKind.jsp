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

		var p0=document.form1.newscolumnkindid.value;
		if(p0=="")
		  {
		    alert('请选择左侧新闻栏目!');
			return false
		  }
		else
		{  
		var p1=document.form1.P1.value;
		var p2=document.form1.P2.value;
		var p3=document.form1.P3.value;
		var p4=document.form1.P4.value;
		var p5=document.form1.P5.value;
		var p6=document.form1.P6.value;
		var p7=document.form1.P7.value;
		var p8=document.form1.P8.value;
		var p9=document.form1.P9.value;
		
		
		
		if(p1=="")  p1="0";
		if(p2=="")  p2="0";
		if(p3=="")  p3="0";
		
	
		
		
        var label="{$SencondLeftNewsKind_"+p0+"_"+p1+"_"+p2+"_"+p3+"_"+p4+"_"+p5+"_"+p6+"_"+p7+"_"+p8+"_"+p9+"}";
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
          <td width="214" height="400"><iframe ID='ColumnTree'  src="lookleftNewsColumn.action" frameborder='1' scrolling='yes' width='100%' height='100%'>
          </iframe>		  </td>
          <td width="383" valign="top"><table width="79%" height="47%">
		    <tr class='tdbg'>
  
    <td height="36" colspan="2" align="right"><div align="center"><strong>二级网站新闻栏目左标签&nbsp;</strong></div></td>
    </tr>
	
  <tr class='tdbg' >
  
    <td width="50%" height="36" align="right">栏目背景图片名称：</td>
	
    <td align="left" width="50%">&nbsp;
      <input name="P1" type="text" id="P1"></td>
  </tr>
    <tr class='tdbg'  >
  
    <td width="50%" height="36" align="right">栏目背景图片高度：</td>
	
    <td align="left" width="50%">&nbsp;
      <input name="P2" type="text" id="P2"></td>
  </tr>
    <tr class='tdbg'  >
  
    <td width="50%" height="36" align="right">栏目背景图片宽度：</td>
	
    <td align="left" width="50%">&nbsp;
      <input name="P3" type="text" id="P3"></td>
  </tr>
    <tr class='tdbg'  >
  
    <td width="50%" height="36" align="right">栏目背景图片类型：</td>
	
    <td align="left" width="50%">&nbsp;
      <select name="P4" id="P4">
        <option>请选择</option>
        <option value="0" selected>jpg</option>
        <option value="1">gif</option>
        <option value="2">png</option>
              </select></td>
  </tr>
   
   
  

     <tr class='tdbg'>
    <td height="21" align="right">&nbsp;表格宽度：</td>
    <td align="left">&nbsp;
        <input name="P5" type="text" id="P5" value="100" size="5" maxlength="5">
        (像素px)</td>
  </tr>
    <tr class='tdbg'>
    <td height="21" align="right">&nbsp;表格填充：</td>
    <td align="left">&nbsp;
        <input name="P6" type="text" id="P6" value="0" size="5" maxlength="5"></td>
  </tr>
      <tr class='tdbg'>
    <td height="21" align="right">&nbsp;表格间距：</td>
    <td align="left">&nbsp;
        <input name="P7" type="text" id="P7" value="0" size="5" maxlength="5"></td>
  </tr>
       <tr class='tdbg'>
    <td height="21" align="right">&nbsp;表格边框：</td>
    <td align="left">&nbsp;
      <input name="P8" type="text" id="P8" value="0" size="5" maxlength="5"></td>
  </tr>
     <tr class='tdbg'>
    <td height="21" align="right">&nbsp;表格对齐方式：</td>
    <td align="left">&nbsp;
      <select name="P9" id="P9">
        <option value="0">默认</option>
        <option value="1"  selected="selected">左对齐</option>
        <option value="2">居中对齐</option>
        <option value="3">右对齐</option>
              </select></td>
  </tr>



</table></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><input type="button" name="Submit" value="提交" onClick="InsertLabel()">
          <input type="reset" name="Submit2" value="重置">
          <input name="newscolumnkindid" type="hidden" id="newscolumnkindid">
          <input name="newscolumnkindName" type="hidden" id="newscolumnkindName"></td>
        </tr>
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
