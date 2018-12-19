/*
*☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆
*★                                                                  ★
*☆                eWebEditor - eWebSoft在线文本编辑器飞鱼修改版     ☆
*★                                                                  ★
*☆  版权所有: eWebSoft.com                                          ☆
*★                                                                  ★
*☆  程序制作: eWeb开发团队                                          ☆
*★            email:webmaster@webasp.net                            ★
*☆            QQ:589808                                             ☆
*★                                                                  ★
*☆  相关网址: [原版地址]http://www.eWebSoft.com/Product/eWebEditor/ ☆
*★            [支持论坛]http://bbs.eWebSoft.com/                    ★
*☆                                                                  ☆
*★  主页地址: http://www.fiyu.net/   飞鱼论坛                       ★
*☆           有什么问题欢迎到飞鱼论坛提出！                         ☆
*★                                                                  ★
*★                                                                  ★
*☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆
*/


// 菜单常量
var sMenuHr="<tr><td align=center valign=middle height=2><TABLE border=0 cellpadding=0 cellspacing=0 width=128 height=2><tr><td height=1 class=HrShadow><\/td><\/tr><tr><td height=1 class=HrHighLight><\/td><\/tr><\/TABLE><\/td><\/tr>";
var sMenu1="<TABLE border=0 cellpadding=0 cellspacing=0 class=Menu width=150><tr><td width=18 valign=bottom align=center style='background:url(sysimage/contextmenu.gif);background-position:bottom;'><\/td><td width=132 class=RightBg><TABLE border=0 cellpadding=0 cellspacing=0>";
var sMenu2="<\/TABLE><\/td><\/tr><\/TABLE>";

 
//如果右键事件是删除事件的话
function editLablePopup(){
	   
	     
	     //alert("正在进行编辑标签操作--->"+eWebEditor.document.body.innerText);
	
	}



// 取菜单行
function getMenuRow(s_Disabled, s_Event, s_Image, s_Html) {
	var s_MenuRow = "";
	s_MenuRow = "<tr><td align=center valign=middle><TABLE border=0 cellpadding=0 cellspacing=0 width=132><tr "+s_Disabled+"><td valign=middle height=20 class=MouseOut onMouseOver=this.className='MouseOver'; onMouseOut=this.className='MouseOut';";
	if (s_Disabled==""){	
	    if(s_Event == "format('delete')")		    
		    s_MenuRow += "  onclick=\"parent.editLablePopup();parent."+s_Event+";parent.oPopupMenu.hide();\"";
		else
		    s_MenuRow += " onclick=\"parent."+s_Event+";parent.oPopupMenu.hide();\"";
	}
	s_MenuRow += ">"
	if (s_Image !=""){
		s_MenuRow += "&nbsp;<img border=0 src='buttonimage/"+config.StyleDir+"/"+s_Image+"' width=20 height=20 align=absmiddle "+s_Disabled+">&nbsp;";
	}else{
		s_MenuRow += "&nbsp;";
	}
	s_MenuRow += s_Html+"<\/td><\/tr><\/TABLE><\/td><\/tr>";
	return s_MenuRow;

}

// 取标准的format菜单行
function getFormatMenuRow(menu, html, image){
	var s_Disabled = "";
	if (!eWebEditor.document.queryCommandEnabled(menu)){
		s_Disabled = "disabled";
	}
	var s_Event = "format('"+menu+"')";
	var s_Image = menu+".gif";
	if (image){
		s_Image = image;
	}
	return getMenuRow(s_Disabled, s_Event, s_Image, html)
}

// 取表格菜单行
function getTableMenuRow(what){
	var s_Menu = "";
	var s_Disabled = "disabled";
	switch(what){
	case "TableInsert":
		if (!isTableSelected()) s_Disabled="";
		s_Menu += getMenuRow(s_Disabled, "TableInsert()", "tableinsert.gif", "插入表格...")
		break;
	case "TableProp":
		if (isTableSelected()||isCursorInTableCell()) s_Disabled="";
		s_Menu += getMenuRow(s_Disabled, "TableProp()", "tableprop.gif", "表格属性...")
		break;
	case "TableCell":
		if (isCursorInTableCell()) s_Disabled="";
		s_Menu += getMenuRow(s_Disabled, "TableCellProp()", "tablecellprop.gif", "单元格属性...")
		s_Menu += getMenuRow(s_Disabled, "TableCellSplit()", "tablecellsplit.gif", "拆分单元格...")
		s_Menu += sMenuHr;
		s_Menu += getMenuRow(s_Disabled, "TableRowProp()", "tablerowprop.gif", "表格行属性...")
		s_Menu += getMenuRow(s_Disabled, "TableRowInsertAbove()", "tablerowinsertabove.gif", "插入行（在上方）");
		s_Menu += getMenuRow(s_Disabled, "TableRowInsertBelow()", "tablerowinsertbelow.gif", "插入行（在下方）");
		s_Menu += getMenuRow(s_Disabled, "TableRowMerge()", "tablerowmerge.gif", "合并行（向下方）");
		s_Menu += getMenuRow(s_Disabled, "TableRowSplit(2)", "tablerowsplit.gif", "拆分行");
		s_Menu += getMenuRow(s_Disabled, "TableRowDelete()", "tablerowdelete.gif", "删除行");
		s_Menu += sMenuHr;
		s_Menu += getMenuRow(s_Disabled, "TableColInsertLeft()", "tablecolinsertleft.gif", "插入列（在左侧）");
		s_Menu += getMenuRow(s_Disabled, "TableColInsertRight()", "tablecolinsertright.gif", "插入列（在右侧）");
		s_Menu += getMenuRow(s_Disabled, "TableColMerge()", "tablecolmerge.gif", "合并列（向右侧）");
		s_Menu += getMenuRow(s_Disabled, "TableColSplit(2)", "tablecolsplit.gif", "拆分列");
		s_Menu += getMenuRow(s_Disabled, "TableColDelete()", "tablecoldelete.gif", "删除列");
		break;
	}
	return s_Menu;
}

// 右键菜单
var oPopupMenu = window.createPopup();
function showContextMenu(event){
	if (!bEditMode) return false;

	var width = 150;
	var height = 0;
	var lefter = event.clientX;
	var topper = event.clientY;

	var oPopDocument = oPopupMenu.document;
	var oPopBody = oPopupMenu.document.body;

	var sMenu="";

	sMenu += getFormatMenuRow("cut", "剪切");
	sMenu += getFormatMenuRow("copy", "复制");
	sMenu += getFormatMenuRow("paste", "常规粘贴");
	sMenu += getFormatMenuRow("delete", "删除");
	sMenu += getFormatMenuRow("selectall", "全选");
	
	sMenu += sMenuHr;
	height += 102;

	if (isCursorInTableCell()){
		sMenu += getTableMenuRow("TableProp");
		sMenu += getTableMenuRow("TableCell");
		sMenu += sMenuHr;
		height += 286;
	}

	if (isControlSelected("TABLE")){
		sMenu += getTableMenuRow("TableProp");
		sMenu += sMenuHr;
		height += 22;
	}

	if (isControlSelected("IMG")){		
	
	    sMenu += getMenuRow("", "editLable('detailEdit')", "label.gif", "详细编辑标签");
		sMenu += getMenuRow("", "editLable('simpleEdit')", "label2.gif", "简易编辑标签");
		sMenu += getMenuRow("", "ShowDialog('dialog/img.htm', 350, 315, true)", "img.gif", "图片属性...");
		sMenu += sMenuHr;
		sMenu += getMenuRow("", "zIndex('forward')", "forward.gif", "上移一层");
		sMenu += getMenuRow("", "zIndex('backward')", "backward.gif", "下移一层");
		sMenu += sMenuHr;
		height += 64;
	}

	sMenu += getMenuRow("", "findReplace()", "findreplace.gif", "查找替换...");
	height += 20;

	sMenu = sMenu1 + sMenu + sMenu2;

	oPopDocument.open();
	oPopDocument.write(config.StyleMenuHeader+sMenu);
	oPopDocument.close();

	height+=2;
	if(lefter+width > document.body.clientWidth) lefter=lefter-width;
	//if(topper+height > document.body.clientHeight) topper=topper-height;

	oPopupMenu.show(lefter, topper, width, height, eWebEditor.document.body);
	return false;

}

// 工具栏菜单
function showToolMenu(menu){
	if (!bEditMode) return false;
	var sMenu = ""
	var width = 150;
	var height = 0;

	var lefter = event.clientX;
	var leftoff = event.offsetX
	var topper = event.clientY;
	var topoff = event.offsetY;

	var oPopDocument = oPopupMenu.document;
	var oPopBody = oPopupMenu.document.body;

	switch(menu){
	case "paste":

		break;
	case "table":		// 表格菜单
		sMenu += getTableMenuRow("TableInsert");
		sMenu += getTableMenuRow("TableProp");
		sMenu += sMenuHr;
		sMenu += getTableMenuRow("TableCell");
		height = 306;
		break;
	case "form":		// 表单菜单
		sMenu += getFormatMenuRow("InsertInputText", "插入输入框", "formtext.gif");
		sMenu += getFormatMenuRow("InsertTextArea", "插入输入区", "formtextarea.gif");
		sMenu += getFormatMenuRow("InsertInputRadio", "插入单选钮", "formradio.gif");
		sMenu += getFormatMenuRow("InsertInputCheckbox", "插入复选钮", "formcheckbox.gif");
		sMenu += getFormatMenuRow("InsertSelectDropdown", "插入下拉框", "formdropdown.gif");
		sMenu += getFormatMenuRow("InsertButton", "插入按钮", "formbutton.gif");
		height = 120;
		break;
	case "zoom":		// 缩放菜单
		for (var i=0; i<aZoomSize.length; i++){
			if (aZoomSize[i]==nCurrZoomSize){
				sMenu += getMenuRow("", "doZoom("+aZoomSize[i]+")", "checked.gif", aZoomSize[i]+"%")
			}else{
				sMenu += getMenuRow("", "doZoom("+aZoomSize[i]+")", "space.gif", aZoomSize[i]+"%")
			}
			height += 20;
		}
		break;
	}

	sMenu = sMenu1 + sMenu + sMenu2;

	oPopDocument.open();
	oPopDocument.write(config.StyleMenuHeader+sMenu);
	oPopDocument.close();

	height+=2;
	if(lefter+width > document.body.clientWidth) lefter=lefter-width;
	//if(topper+height > document.body.clientHeight) topper=topper-height;

	oPopupMenu.show(lefter - leftoff - 2, topper - topoff + 22, width, height, document.body);

	return false;
}

function editLable(what){
	
		    var oControl;
            var oSeletion;
            var sRangeType;
            var Zlabel="";          
            var b = false;
            oSelection = eWebEditor.document.selection.createRange();
            sRangeType = eWebEditor.document.selection.type;                                
            if (sRangeType == "Control") {
				b=true;
                if (oSelection.item(0).tagName == "IMG"){
                    oControl = oSelection.item(0);
                    Zlabel = oControl.Zlabel;					
					if(!Zlabel){
					    b = false; 						
					}else{
						Zlabel = Zlabel.replace("$0","$");					    
						switch(what){
						    case "detailEdit" :
						         showEditLabelDialog(Zlabel);
						        break;
						    case "simpleEdit" :
						         ShowDialog('dialog/editLabelStr.html?labelStr='+Zlabel, 480,130, true);
							    break;	
						}
						
						}
					
                }
            } 
			
			if(!b){
            	alert("得不到标签对象");
            	HtmlEdit.focus();
            	
            }
		    
		
       
	}
	
	

	
	
	
	function showEditLabelDialog(label){
		
		var splitArr = null;
		
		if (label.indexOf("$NewsList")>=0){		     
			 //alert("splitArr[0] = "+splitArr[0]+" ,splitArr[1] = "+splitArr[1]);
			
		     ShowDialog('LookEditorNewsListLabel.action?label='+label, 667,550, true);
		}else
			if (label.indexOf("$Url")>=0){ // 如果是栏目链接标签的话
				ShowDialog('LookEditorNewsUrlLabel.action?label='+label, 667,550, true);
			}else
				if (label.indexOf("$More")>=0){ // 如果是栏目更多标签的话
					ShowDialog('LookEditorNewsMoreLabel.action?label='+label, 667,550, true);
				}else
					if (label.indexOf("$CurPos")>=0){ // 如果是栏目当前位置标签的话
						ShowDialog('LookEditorNewsCurPosLabel.action?label='+label, 667,550, true);
					}else
						if (label.indexOf("$NewsContent")>=0){ // 如果是取某个栏目下的第一条新闻的内容标签的话
							ShowDialog('LookEditorNewsContentLabel.action?label='+label, 667,550, true);
						}else
							if (label.indexOf("$FlashAutoPic")>=0){ // 如果是flash滚动图片标签的话
								ShowDialog('LookEditorFlashAutoPicLabel.action?label='+label, 667,550, true);
							}else
								if (label.indexOf("$PicColumnAuto")>=0){ // 如果是图片列表标签的话
									ShowDialog('LookEditorPicColumnAutoLabel.action?label='+label, 667,550, true);
								}else
									if (label.indexOf("$LeftNewsKind")>=0){ // 如果是新闻栏目左标签的话									    
										ShowDialog('LookEditorLeftNewsKindLabel.action?label='+label, 667,560, true)
									}
									
									
									
									
									else
										if (label.indexOf("$schoolMore")>=0){ // 如果是学校二级网站学校新闻更多标签的话
											alert("得不到标签对象");
										}else
											if (label.indexOf("$schoolCurPos")>=0){ // 如果是学校二级网站学校新闻当前位置标签的话
												alert("得不到标签对象");
											}else
												if (label.indexOf("$Top")>=0){ // 如果是二级网站新闻排行榜标签的话
													alert("得不到标签对象");
												}else
													if (label.indexOf("$schoolName")>=0){ // 如果是学校二级网站名称标签的话
														alert("得不到标签对象");
													}else
														if (label.indexOf("$SencondLeftNewsKind")>=0){ // 如果是二级网站新闻栏目左标签的话
															alert("得不到标签对象");
														}else
															if (label.indexOf("$Search")>=0){ // 如果是搜索标签的话
																alert("得不到标签对象");
															}else
																alert("得不到标签对象");
			    
		
	}