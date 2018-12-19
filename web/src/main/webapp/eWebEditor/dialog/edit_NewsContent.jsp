<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
		<title>取某个栏目下的第一条新闻的内容</title>
		
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
		if(p0=="")
		  {
		    alert('请选择左侧新闻栏目!');
			return false
		  }
		else
		{  
		var p1=document.form1.P1.value;
        var label="{$NewsContent_"+p0+"_"+p1+"}";
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
          <td width="284" height="480"><iframe ID='ColumnTree'  src="lookleftNewsColumn.action" frameborder='1' scrolling='yes' width='100%' height='100%'> </iframe></td>
          <td width="383" valign="top">
		  <fieldset style="height:480">
		      <legend><strong>取某个栏目下的第一条新闻的内容</strong></legend>
			  
		      <table width="100%" height="47%">

                <tr class='tdbg'>
                  <td width="40%" height="80" align="right"><span class="STYLE1">&nbsp;取值范围：</span></td>
                  <td align="left" width="60%">&nbsp;
                      <select name="P1" id="P1">
                        <option value="0" selected>本级栏目文章</option>
                        <option value="1">本栏目及子栏目文章</option>
                        <option value="2">热点排行</option>
                      </select>
                      <script language="javascript">document.form1.P1.value = "$p1";</script>                  </td>
                </tr>
                <tr class='tdbg'>
                  <td height="64" colspan="2" align="center"><input type="button" name="Submit" value="提交" onClick="InsertLabel()">&nbsp;
                      <input type="reset" name="Submit2" value="重置">
                      <input name="newscolumnkindid" type="hidden" id="newscolumnkindid" value="$p0">
                      <input name="newscolumnkindName" type="hidden" id="newscolumnkindName"></td>
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
