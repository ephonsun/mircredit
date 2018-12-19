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
              body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
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
		var p2=document.form1.P2.value;
		var p3=document.form1.P3.value;
		var p4=document.form1.P4.value;
		var p5=document.form1.P5.value;
		var p6=document.form1.P6.value;
		var p7=document.form1.P7.value;
		var p8=document.form1.P8.value;
		var p9=document.form1.P9.value;
        var p10=document.form1.P10.value;
		var p11=document.form1.P11.value;
		var p12=document.form1.P12.value;
		var p13=document.form1.P13.value;
		var p14=document.form1.P14.value;
		var p15=document.form1.P15.value;
        var label="{$NewsList_"+p0+"_"+p1+"_"+p2+"_"+p3+"_"+p4+"_"+p5+"_"+p6+"_"+p7+"_"+p8+"_"+p9+"_"+p10+"_"+p11+"_"+p12+"_"+p13+"_"+p14+"_"+p15+"}";
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
          <td width="284" height="100%"><iframe ID='ColumnTree'  src="lookleftNewsColumn.action" frameborder='1' scrolling='yes' width='100%' height='100%'>
          </iframe>		  </td>
          <td width="383" valign="top">
		  <fieldset>
		  <legend><strong>栏目新闻列表</strong></legend>
		  <table width="100%" height="47%">

            <tr class='tdbg'>
              <td width="49%" height="36" align="right"><span class="STYLE1">&nbsp;取值范围：</span></td>
              <td align="left" width="51%">&nbsp;
                  <select name="P1" id="P1">
                    <option value="0" selected>本级栏目文章</option>
                    <option value="1">本栏目及子栏目文章</option>
                    <option value="2">热点排行</option>
                  </select>
                  <script>document.form1.P1.value="$p1";</script>              </td>
            </tr>
            <tr class='tdbg'>
              <td height="26" align="right"><span class="STYLE1">&nbsp;列表记录条数：</span></td>
              <td align="left">&nbsp;
                  <input name="P2" type="text" id="P2" value="$p2" maxlength="5"></td>
            </tr>
            <tr class='tdbg'>
              <td height="26" align="right"><span class="STYLE1">&nbsp;标题字数：</span></td>
              <td align="left">&nbsp;
                  <input name="P3" type="text" id="P3" value="$p3" maxlength="5"></td>
            </tr>
            <tr class='tdbg'>
              <td height="26" align="right"><span class="STYLE1">&nbsp;日期显示格式：</span></td>
              <td align="left">&nbsp;
                  <select name="P4" id="P4">
                    <option value="0" selected>分两列显示</option>
                    <option value="1">日期显示在标题后面</option>
                    <option value="2">不显示日期</option>
                  </select>
                  <script>document.form1.P4.value="$p4";</script>              </td>
            </tr>
            <tr class='tdbg'>
              <td height="26" align="right"><span class="STYLE1">&nbsp;时间格式：</span></td>
              <td align="left">&nbsp;
                  <select name="P5" id="P5">
                    <option value="0" selected>yyyyMMdd</option>
                    <option value="1">MMdd</option>
                  </select>
                  <script>document.form1.P5.value="$p5";</script>              </td>
            </tr>
            <tr class='tdbg'>
              <td height="26" align="right"><span class="STYLE1">&nbsp;标题显示格式：</span></td>
              <td align="left">&nbsp;
                  <select name="P6" id="P6">
                    <option value="0"  selected="selected">标题</option>
                    <option value="1">&lt;li&gt;+标题</option>
                  </select>
                  <script>document.form1.P6.value="$p6";</script>              </td>
            </tr>
            <tr class='tdbg'>
              <td height="26" align="right"><span class="STYLE1">&nbsp;标题宽度：</span></td>
              <td align="left">&nbsp;
                  <input name="P7" type="text" id="P7" value="$p7" size="5" maxlength="5">
                %</td>
            </tr>
            <tr class='tdbg'>
              <td height="26" align="right"><span class="STYLE1">&nbsp;时间宽度：</span></td>
              <td align="left">&nbsp;
                  <input name="P8" type="text" id="P8" value="$p8" size="5" maxlength="5">
                %</td>
            </tr>
            <tr class='tdbg'>
              <td height="26" align="right"><span class="STYLE1">&nbsp;表格宽度：</span></td>
              <td align="left">&nbsp;
                  <input name="P9" type="text" id="P9" value="$p9" size="5" maxlength="5">
                %</td>
            </tr>
            <tr class='tdbg'>
              <td height="26" align="right"><span class="STYLE1">&nbsp;表格填充：</span></td>
              <td align="left">&nbsp;
                  <input name="P10" type="text" id="P10" value="$p10" size="5" maxlength="5"></td>
            </tr>
            <tr class='tdbg'>
              <td height="26" align="right"><span class="STYLE1">&nbsp;表格间距：</span></td>
              <td align="left">&nbsp;
                  <input name="P11" type="text" id="P11" value="$p11" size="5" maxlength="5"></td>
            </tr>
            <tr class='tdbg'>
              <td height="26" align="right"><span class="STYLE1">&nbsp;表格边框：</span></td>
              <td align="left">&nbsp;
                  <input name="P12" type="text" id="P12" value="$p12" size="5" maxlength="5"></td>
            </tr>
            <tr class='tdbg'>
              <td height="26" align="right"><span class="STYLE1">&nbsp;表格对齐方式：</span></td>
              <td align="left">&nbsp;
                  <select name="P13" id="P13">
                    <option value="0">默认</option>
                    <option value="1"  selected="selected">左对齐</option>
                    <option value="2">居中对齐</option>
                    <option value="3">右对齐</option>
                  </select>
                  <script>document.form1.P13.value="$p13";</script>              </td>
            </tr>
            <tr class='tdbg'>
              <td height="26" align="right"><span class="STYLE1">&nbsp;是否显示栏目名称：</span></td>
              <td align="left">&nbsp;
                  <select name="P14" id="P14">
                    <option value="0" selected>不显示</option>
                    <option value="1">显示</option>
                  </select>
                  <script>document.form1.P14.value="$p14";</script>              </td>
            </tr>
            <tr class='tdbg'>
              <td height="26" align="right"><span class="STYLE1">&nbsp;是否分页显示列表： </span></td>
              <td align="left">&nbsp;
                  <select name="P15" id="P15">
                    <option value="0" selected >不显示</option>
                    <option value="1">显示</option>
                  </select>
                  <script>document.form1.P15.value="$p15";</script>              </td>
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
        <tr>
          <td>&nbsp;</td>
          <td align="center"><input type="button" name="Submit" value="提交" onClick="InsertLabel()">
          <input type="reset" name="Submit2" value="重置">
          <input name="newscolumnkindid" type="hidden" id="newscolumnkindid" value="$p0">
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
