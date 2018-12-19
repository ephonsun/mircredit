<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
		<title></title>
		
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
		
		   var label="{$schoolName}";
		  // alert(label);
		   
		   dialogArguments.insertHTML(label);
		   window.returnValue = label;
		   window.close();
		
				 
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
    <td height="21" colspan="2" align="right">&nbsp;        <div align="center"><strong>二级网站名称</strong></div>
      <div align="center"></div></td>
    </tr>
  <tr class='tdbg'>
    <td height="36" colspan="2" align="center"><strong>{$schoolName}&nbsp;</strong></td>
    </tr>
  <tr class='tdbg'>
    <td width="40%" height="21" align="right">&nbsp;</td>
    <td width="60%" align="left">&nbsp;</td>
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
