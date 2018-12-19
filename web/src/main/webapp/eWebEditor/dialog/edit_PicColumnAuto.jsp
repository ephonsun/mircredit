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
              .STYLE1 {color: #999999}
              </style>
	<script>
	
	    function InsertLabel()
		{
		var p0=document.form1.newscolumnkindid.value;
        var p1=document.form1.P1.value;
		var p2=document.form1.P2.value;
		var p3=document.form1.P3.value;
		var p4=document.form1.P4.value;
		var p5=document.form1.P5.value;
		var p6=document.form1.P6.value;
		var p7=document.form1.P7.value;


	   if(p0=="")
		{
		    alert('请选择左侧新闻栏目!');
			return false
	    }		
		if(p1=="")
		{
		  alert('图片显示张数不能为空');
		  document.form1.P1.focus();
		  return false;
		}
		else if(p2=="")
		{
		  alert('图片宽度不能为空');
		  document.form1.P2.focus();
		  return false;
		}
	    else if(p3=="")
		{
		  alert('图片高度不能为空');
		  document.form1.P3.focus();
		  return false;
		}
		else if(p4=="")
		{
		  alert('请选择图片打开方式');
		  document.form1.P4.focus();
		  return false;
		}
		else if(p5=="")
		{
		  alert('图片背景名称不能为空');
		  document.form1.P5.focus();
		  return false;
		}
		else if(p6=="")
		{
		  alert('请选择背景图片类型');
		  document.form1.P6.focus();
		  return false;
		}
		else
		{
		
		   var label="{$PicColumnAuto_"+p0+"_"+p1+"_"+p2+"_"+p3+"_"+p4+"_"+p5+"_"+p6+"_"+p7+"}";  
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
	  <table width="667" border="0" align="center">
	  
        <tr>
		<td width="284" height="480">
<iframe ID='ColumnTree'  src="lookleftNewsColumn.action" frameborder='1' scrolling='yes' width='100%' height='100%'>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
          </iframe>




</td>
         
          <td width="383" valign="top" height="480">
		      <fieldset style="height:480">
			      <legend><strong>图片列表滚动显示</strong></legend>
				  <table width="383" height="47%">

                    <tr class='tdbg'>
                      <td width="40%" height="36" align="right"><span class="STYLE1">&nbsp;图片显示张数：</span></td>
                      <td align="left" width="60%">&nbsp;
                          <input name="P1" type="text" id="P1" value="$p1" maxlength="5"></td>
                    </tr>
                    <tr class='tdbg'>
                      <td height="36" align="right"><span class="STYLE1">&nbsp;图片宽度：</span></td>
                      <td align="left">&nbsp;
                          <input name="P2" type="text" id="P2" value="$p2" maxlength="5"></td>
                    </tr>
                    <tr class='tdbg'>
                      <td height="36" align="right"><span class="STYLE1">&nbsp;图片高度：</span></td>
                      <td align="left">&nbsp;
                          <input name="P3" type="text" id="P3" value="$p3" maxlength="5"></td>
                    </tr>
                    <tr class='tdbg'>
                      <td height="36" align="right"><span class="STYLE1">&nbsp;图片打开方式：</span></td>
                      <td align="left">&nbsp;
                          <select name="P4" id="P4">
                            <option>请选择</option>
                            <option value="0">新窗口</option>
                            <option value="1">父窗口</option>
                          </select>
                          <script language="javascript">document.form1.P4.value = "$p4";</script>                      </td>
                    </tr>
                    <tr class='tdbg'>
                      <td height="36" align="right"><span class="STYLE1">&nbsp;背景图片名称：</span></td>
                      <td align="left">&nbsp;
                          <input name="P5" type="text" id="P5" value="$p5"></td>
                    </tr>
                    <tr class='tdbg'>
                      <td height="36" align="right"><span class="STYLE1">&nbsp;背景图片类型：</span></td>
                      <td align="left">&nbsp;
                          <select name="P6" id="P6">
                            <option>请选择</option>
                            <option value="0">jpg</option>
                            <option value="1">gif</option>
                            <option value="2">png</option>
                          </select>
                          <script language="javascript">document.form1.P6.value = "$p6";</script>                      </td>
                    </tr>
                    <tr class='tdbg'>
                      <td height="36" align="right"><span class="STYLE1">&nbsp;取值范围：</span></td>
                      <td align="left">&nbsp;
                          <select name="P7" id="P7">
                            <option>请选择</option>
                            <option value="0"  selected="selected">本级栏目图片</option>
                            <option value="1">本级栏目及子栏目图片</option>
                          </select>
                          <script language="javascript">document.form1.P7.value = "$p7";</script>                      </td>
                    </tr>
                    <tr >
                      <td height="60" colspan="2"><div align="center">
                          <input type="button" name="Submit" value="提交" onClick="InsertLabel()">&nbsp;
                          <input type="reset" name="Submit2" value="重置">
                          <input name="newscolumnkindid" type="hidden" id="newscolumnkindid" value="$p0">
                          <input type="hidden" name="newscolumnkindName">
                      </div></td>
                    </tr>
                  </table>
		      </fieldset>
		  
		  </td>
        </tr>
        <tr>
          <td colspan="2" align="left" style="padding-left:10px;">
		  <fieldset>
		  <legend><span style="color: #FF3300;"><strong>当前栏目</strong></span></legend>
		  
          <label div id="cName" >$columnLevel</label>
		  </fieldset>
		  </td>
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
