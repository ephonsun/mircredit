jQuery(function(){
	var t1 = jQuery("#exatbl"), t2 = jQuery("#addtbl"), t3 = jQuery("#optbl"), index = 1, asc = 65 + t3.find("tbody").find("tr").length;
	
	//加载DOM事件
	t3.find("tbody").find("tr").each(function(){
		var tr = $(this);
		tr.find("a[nm='del']").bind("click", function(){
			tr.remove();
			
			reorder();
			
			asc = 65 + t3.find("tbody").find("tr").length;
			return false;
		});
	});
	
	var items = t1.find("tbody").find("tr.exam-t");
	items.each(function(){
		var s = $(this), n = s.next();
		s.find("a[nm='str']").click(function(){
			if(n.is(":hidden")){
				$(this).text("收缩");
				n.show();
			}else{
				$(this).text("展开");
				n.hide();
			}
		});
		s.find("a[nm='up']").click(function(){
			if(s.prevAll().length != 0){
				//alert("当前行ID"+s.attr("colname")+"前一行的ID:"+s.prev().prev().attr("colname"));
				jQuery.ajax({
			    type:"post", //请求方式
			    url:"moveQuestion.html", //发送请求地址
			    data:{ //发送给数据库的数据
			    	questionId:s.attr("colname"),
					questionIds:s.prev().prev().attr("colname")
			    },//请求成功后的回调函数有两个参数
			    success:function(data,textStatus){
					dataRefresh();
			    	}
				});
			}
		});
		s.find("a[nm='down']").click(function(){
			if(s.nextAll().length > 1){
				//alert(s.next().next().attr("colname"));
				jQuery.ajax({
			    type:"post", //请求方式
			    url:"moveQuestion.html", //发送请求地址
			    data:{ //发送给数据库的数据
			    	questionId:s.attr("colname"),
					questionIds:s.next().next().attr("colname")
			    },//请求成功后的回调函数有两个参数
			    success:function(data,textStatus){
					dataRefresh();
			    	}
				});
			}
		});
		s.find("a[nm='del']").click(function(){
			s.remove(); n.remove();
		});
	});
	items.last().find("a[nm='str']").click();
	
	//添加题目
	jQuery("#btnAddQue").click(function(){
		if(t2.is(":hidden")){
			t1.find("tbody").find("tr.exam-t").find("a[nm='str']").each(function(){
				if($(this).text() == "收缩"){ $(this).click(); }
			});
			t2.show();
		}
		else{ return false; }
	});
	//关闭新建题目层
	jQuery("#btnClose, .addtbl-close").click(function(){
		clearHtml();
	});
	
	//添加选项
	jQuery("#btnAddOpt").click(function(){
		var html = "<tr>"
				 + "<td width=\"20\" align=\"left\"><label rel=\"oid\">" + String.fromCharCode(asc++) + "</label></td>"
				 + "<td>"
				 + "<div class=\"dtxt1\">"
				 + "<input type=\"text\" class=\"dtxt1-txt\" rel=\"content\" maxlength=\"300\" />"
				 + "</div>"
				 + "</td>"
				 + "<td width=\"50\" align=\"center\"><label>分值</label></td>"
				 + "<td width=\"50\">"
				 + "<div class=\"dtxt1\">"
				 + "<input type=\"text\" class=\"dtxt1-txt\" rel=\"score\" maxlength=\"2\"  />"
				 + "</div>"
				 + "</td>"
				 + "<td width=\"40\" align=\"right\">"
				 + "<a class=\"slink\" href=\"javascript:void(0);\" nm=\"del\">删除</a>"
				 + "</td>"	  
				 + "</tr>";
				 
		var e = jQuery(html);
		
		e.appendTo(t3.find("tbody")).find(":text[rel='score']").bind("keypress", function(e){      
            var code = (e.keyCode ? e.keyCode : e.which);
            if(!$.browser.msie&&(e.keyCode==0x8)){      
            	return;
            }      
            return code >= 48 && code<= 57;      
        }).bind("blur", function(){      
			if(this.value.lastIndexOf(".") == (this.value.length - 1)){      
				this.value = this.value.substr(0, this.value.length - 1);      
			}
			else if(isNaN(this.value)){      
				this.value = "";      
			}      
        }).bind("paste", function(){      
			var s = clipboardData.getData('text');      
			if (!/\D/.test(s));      
			value = s.replace(/^0*/, '');      
			return false;      
		}).bind("dragenter", function(){      
			return false;      
		}).bind("keyup", function() {      
            if(/(^0+)/.test(this.value)){      
				this.value = this.value.replace(/^0*/, '');      
			}      
		}).css("ime-mode", "disabled").end().find("a[nm='del']").bind("click", function(){
			e.remove();
			reorder();
			
			asc = 65 + t3.find("tbody").find("tr").length;
			return false;
		});
	});
	
	jQuery("#btnSave1").click(function(){
		if(t3.find("tbody").find("tr").length < 2){
			art.dialog({
						 ok: function () {
                            return true;
                         },
					 	 icon: 'warning',
                        content: '请至少添加两个选项'
                    });
			return false;
		}
		else{
			var num1 = 0, flag = false;
			//Is not complete or can not be null character.
			t2.find("tbody").find(":text").each(function(){
				if(jQuery.trim(jQuery(this).val()) == ""){
					jQuery(this).bind("focus", function(){
						jQuery(this).parent().removeClass("txterr");
					}).parent().addClass("txterr");
					num1++;
				}
			});
			if(num1 > 0){
				art.dialog({
						 ok: function () {
                            return true;
                         },
					 	 icon: 'warning',
                        content: '未填写完整或不能为空字符'
                    });
				return false;
			}
			//Do not repeat the add.
			t1.find("tbody").find("tr.exam-t").find("label.topic").each(function(){
				if(jQuery(this).text() == t2.find(":text[rel='topic']").val()){
					t2.find(":text[rel='topic']").bind("focus", function(){
						jQuery(this).parent().removeClass("txterr");
					}).parent().addClass("txterr");
					flag = true;
				}
			});
			if(flag){
					art.dialog({
						 ok: function () {
                            return true;
                         },
					 	 icon: 'warning',
                        content: '请勿重复添加'
                    });
				return false;
			}
			
			var json = {};
			var arr ="";
			json["title"] = t2.find("tbody").find(":text[rel='topic']").val();
			t3.find("tbody").find("tr").each(function(i){
				var jsonop = "";
				jsonop = jQuery(this).find("label[rel='oid']").text();
				jsonop = jsonop+","+jQuery(this).find(":text[rel='score']").val();
				jsonop = jsonop+","+jQuery(this).find(":text[rel='content']").val();
				arr=arr+jsonop+">";
			});
			json["rskOptions"] = arr;
			json["templateId"]=$("#templateId").val();
			var url="addRskTemplateQuestionAndOption.html";
			jQuery.post("checkQuestionName.html",json,function(data){//验证标题是否重复
				if(data==""){
					jQuery.post(url,json,function(data){window.returnValue=1;window.close();});
				}else{
					alert(data);
				}
			});
			
		}
	});
	//////////
	function clearHtml(){
		t3.find("tbody").empty();
		t2.find("input[type='text']").val("").end().find(":radio:first").attr("checked", true).end().hide();
		
		asc = 65;
	};
	//////////
	function reorder(){
		var $i = jQuery("#optbl tbody").find("tr").find("label[rel='oid']");
		$i.each(function(i){
			$(this).text(String.fromCharCode(i + 65));
		});
	};
	
});



