// JavaScript Document

(function($){
	var f = {
		child: function(obj, t){
			return $('tbody tr[pid=\'' + obj.id + '\']', t);
		},
		parent:function(obj,t){
			return $('tbody tr[id=\'' + obj.pid + '\']', t);
		}
	};
	var pt = {
		update:function(obj,t,oval,o){
			var sumTotal = $('tbody tr[id=\'-1\']', t).children("td").eq(1).find("label").text();
			var pid = -1;//合计节点
			var total = sumTotal;
			if(obj.parent().attr("pid") != 0){
				pid = obj.parent().attr("pid");
			}
			var pAssignTotal = 0;//父级节点已分配之和
			$('tbody tr[pid=\''+obj.parent().attr("pid")+'\']', t).each(function(){
				pAssignTotal = $(this).children("td").eq(1).find("label").text() - 0 + pAssignTotal;
			});

            //机构节点
			if(obj.parent().attr("mode") < 3){
				var myAssignTotal = $('tbody tr[id=\''+obj.parent().attr("id")+'\']', t).children("td").eq(2).find("label").text();
                var myTaskGoal=$('tbody tr[id=\''+obj.parent().attr("id")+'\']', t).children("td").eq(1).find("label").text()-0;
                var completeTotal= $('tbody tr[id=\''+obj.parent().attr("id")+'\']', t).children('td').eq(5).find('label').text()-0;

                var assignRs = myTaskGoal - 0 - myAssignTotal;
				if(assignRs < 0){
                    banger.page.showMessageBox("上级目标任务小于已分配的下级任务目标总和，请重新分配！");
                    obj.find("label").text(oval);
				}else{
					if(obj.parent().attr("pid") != 0){
						total = $('tbody tr[id=\''+pid+'\']', t).children("td").eq(1).find("label").text();
					}
					var pUnAssignTotal = total - 0 - pAssignTotal;//父级节点未分配
					if(pUnAssignTotal < 0){
                        banger.page.showMessageBox("下级目标任务总和不能超过上级任务目标，请重新分配！");
                        obj.find("label").text(oval);
					}else if(myTaskGoal<completeTotal){
                        banger.page.showMessageBox("任务目标不能够低于 "+ completeTotal +" 笔，请重新分配！");
                        obj.find("label").text(oval);
                    }else{
						$('tbody tr[id=\''+pid+'\']', t).children("td").eq(2).find("label").text(pAssignTotal);
                        $('tbody tr[id=\''+pid+'\']', t).children("td").eq(2).find("label").attr("title", pAssignTotal);

                        //如果未分配非零，则高亮显示
                        if(pUnAssignTotal){
                            $('tbody tr[id=\''+pid+'\']', t).children("td").eq(3).find("label").addClass('red').text(pUnAssignTotal);
                            $('tbody tr[id=\''+pid+'\']', t).children("td").eq(3).find("label").addClass('red').attr("title",pUnAssignTotal);
                        }else{
                            $('tbody tr[id=\''+pid+'\']', t).children("td").eq(3).find("label").removeClass('red').text(pUnAssignTotal);
                            $('tbody tr[id=\''+pid+'\']', t).children("td").eq(3).find("label").removeClass('red').attr("title",pUnAssignTotal);
                        }

						var myTaskGoal=$('tbody tr[id=\''+obj.parent().attr("id")+'\']', t).children("td").eq(1).find("label").text()-0;
						var myAssign=$('tbody tr[id=\''+obj.parent().attr("id")+'\']', t).children("td").eq(2).find("label").text()-0;

						var pTaskGoal=$('tbody tr[id=\''+pid+'\']', t).children("td").eq(1).find("label").text()-0;
						var pAssign=$('tbody tr[id=\''+pid+'\']', t).children("td").eq(2).find("label").text()-0;

						var sTskPc=0;
						var tskPc=0;
						if(pTaskGoal==0){
							sTskPc=parseFloat(0.00).toFixed(2) +'%';
						}else{
							sTskPc = (parseFloat(pAssign)/parseFloat(pTaskGoal))*100;
							sTskPc = parseFloat(sTskPc).toFixed(2) + "%";
						}

						if(myTaskGoal==0){
							tskPc=parseFloat(0.00).toFixed(2) +'%';
						}else{
							tskPc = (parseFloat(myAssign)/parseFloat(myTaskGoal))*100;
							tskPc = parseFloat(tskPc).toFixed(2) + "%";
						}

                        $('tbody tr[id=\''+pid+'\']', t).children("td").eq(4).find("label").text(sTskPc);
                        $('tbody tr[id=\''+pid+'\']', t).children("td").eq(4).find("label").attr("title",sTskPc);
						$('tbody tr[id=\''+obj.parent().attr("id")+'\']', t).children("td").eq(4).find("label").text(tskPc);
                        $('tbody tr[id=\''+obj.parent().attr("id")+'\']', t).children("td").eq(4).find("label").attr("title",tskPc);

                        var myUnAssign= myTaskGoal - 0 - myAssignTotal;

                        //如果未分配非零，则高亮显示
                        if(myUnAssign){
                            $('tbody tr[id=\''+obj.parent().attr("id")+'\']', t).children("td").eq(3).find("label").addClass('red').text(myUnAssign);
                            $('tbody tr[id=\''+obj.parent().attr("id")+'\']', t).children("td").eq(3).find("label").addClass('red').attr("title",myUnAssign);
                        }else{
                            $('tbody tr[id=\''+obj.parent().attr("id")+'\']', t).children("td").eq(3).find("label").removeClass('red').text(myUnAssign);
                            $('tbody tr[id=\''+obj.parent().attr("id")+'\']', t).children("td").eq(3).find("label").removeClass('red').attr("title",myUnAssign);
                        }
						//调用前端函数
						if(o.afterUpdate && typeof(o.afterUpdate)=='function'){
							o.afterUpdate($('tbody tr[id=\''+obj.parent().attr("id")+'\']', t));//修改完成后的td
							if(obj.parent().attr("pid") != 0){
								o.afterUpdate($('tbody tr[id=\''+pid+'\']', t));//修改完成后的父节点td
							}
						}
					}
				}
			}else{
                var myTaskGoal=$('tbody tr[id=\''+obj.parent().attr("id")+'\']', t).children("td").eq(1).find("label").text()-0;
                var completeTotal= $('tbody tr[id=\''+obj.parent().attr("id")+'\']', t).children('td').eq(5).find('label').text()-0;

                if(obj.parent().attr("pid") != 0){
                    total = $('tbody tr[id=\''+pid+'\']', t).children("td").eq(1).find("label").text();
                }
                var pUnAssignTotal = total - 0 - pAssignTotal;//父级节点未分配

                if(pUnAssignTotal < 0){
                    banger.page.showMessageBox("下级目标任务总和不能超过上级任务目标，请重新分配！");
                    obj.find("label").text(oval);
                }else if(myTaskGoal<completeTotal){
                    banger.page.showMessageBox("任务目标不能够低于"+ completeTotal +"笔，请重新分配！");
                    obj.find("label").text(oval);
                }else{
                    $('tbody tr[id=\''+pid+'\']', t).children("td").eq(2).find("label").text(pAssignTotal);
                    $('tbody tr[id=\''+pid+'\']', t).children("td").eq(2).find("label").attr("title",pAssignTotal);

                    //如果未分配非零，则高亮显示
                    if(pUnAssignTotal){
                        $('tbody tr[id=\''+pid+'\']', t).children("td").eq(3).find("label").addClass('red').text(pUnAssignTotal);
                        $('tbody tr[id=\''+pid+'\']', t).children("td").eq(3).find("label").addClass('red').attr("title",pUnAssignTotal);
                    }else{
                        $('tbody tr[id=\''+pid+'\']', t).children("td").eq(3).find("label").removeClass('red').text(pUnAssignTotal);
                        $('tbody tr[id=\''+pid+'\']', t).children("td").eq(3).find("label").removeClass('red').attr("title",pUnAssignTotal);
                    }

                    var myTaskGoal=$('tbody tr[id=\''+obj.parent().attr("id")+'\']', t).children("td").eq(1).find("label").text()-0;
                    var myAssign=$('tbody tr[id=\''+obj.parent().attr("id")+'\']', t).children("td").eq(2).find("label").text()-0;

                    var pTaskGoal=$('tbody tr[id=\''+pid+'\']', t).children("td").eq(1).find("label").text()-0;
                    var pAssign=$('tbody tr[id=\''+pid+'\']', t).children("td").eq(2).find("label").text()-0;

                    var sTskPc=0;
                    var tskPc=0;
                    if(pTaskGoal==0){
                        sTskPc=parseFloat(0.00).toFixed(2) +'%';
                    }else{
                        sTskPc = (parseFloat(pAssign)/parseFloat(pTaskGoal))*100;
                        sTskPc = parseFloat(sTskPc).toFixed(2) + "%";
                    }
                    if(myTaskGoal==0){
                        tskPc=parseFloat(0.00).toFixed(2) +'%';
                    }else{
                        tskPc = (parseFloat(myAssign)/parseFloat(myTaskGoal))*100;
                        tskPc = parseFloat(tskPc).toFixed(2) + "%";
                    }
                    $('tbody tr[id=\''+pid+'\']', t).children("td").eq(4).find("label").text(sTskPc);
                    $('tbody tr[id=\''+pid+'\']', t).children("td").eq(4).find("label").attr("title",sTskPc);
                    $('tbody tr[id=\''+obj.parent().attr("id")+'\']', t).children("td").eq(4).find("label").text(tskPc);
                    $('tbody tr[id=\''+obj.parent().attr("id")+'\']', t).children("td").eq(4).find("label").attr("title",tskPc);

                    //调用前端函数
                    if(o.afterUpdate && typeof(o.afterUpdate)=='function'){
                        o.afterUpdate($('tbody tr[id=\''+obj.parent().attr("id")+'\']', t));//修改完成后的td
                        if(obj.parent().attr("pid") != 0){
                            o.afterUpdate($('tbody tr[id=\''+pid+'\']', t));//修改完成后的父节点td
                        }
                    }
                }

			}
		}
	};
	var see={
		count:function(t){
			$('tbody tr',t).each(function(){				
				var trid=$(this).attr('id');
				var trpid=$(this).parent().attr('id');
				var taskGoal=$('td',this).eq(1).find('label').text()-0;
				var assignedTasks=0;
				var notAssignedTasks=0;
				var assignedTasksRatio=0.00;
				var completedNum=$('td',this).eq(5).find('label').text()-0;
				var completedProgress=0.00;
				if(trid!=-1){
					if($(this).nextAll('tr[pid='+ trid +']').length>0){
						$('tbody tr[pid='+ trid +']',t).each(function(){
							var tdval=$('td',this).eq(1).find('label').text()-0;
							assignedTasks+=tdval;
						});
						notAssignedTasks=taskGoal-assignedTasks;
					}else{
						assignedTasks=0;
						notAssignedTasks=0;
					}	
					if(taskGoal==0){
						assignedTasksRatio=0.00;
						completedProgress=0.00;
					}else{
						assignedTasksRatio=assignedTasks/taskGoal*100;
						completedProgress=completedNum/taskGoal*100;
					}
                    $('td',this).eq(2).find('label').text(assignedTasks);

                    //如果未分配非零，则高亮显示
                    if(notAssignedTasks){
                        $('td',this).eq(3).find('label').addClass('red').text(notAssignedTasks);
                        $('td',this).eq(3).find('label').addClass('red').attr("title",notAssignedTasks);
                    }else{
                        $('td',this).eq(3).find('label').removeClass('red').text(notAssignedTasks);
                        $('td',this).eq(3).find('label').removeClass('red').attr("title",notAssignedTasks);
                    }

                    $('td',this).eq(4).find('label').text(parseFloat(assignedTasksRatio).toFixed(2) +'%');
                    $('td',this).eq(4).find('label').attr("title",parseFloat(assignedTasksRatio).toFixed(2) +'%');
					$('td',this).eq(6).find('label').text(parseFloat(completedProgress).toFixed(2) +'%');
                    $('td',this).eq(6).find('label').attr("title",parseFloat(completedProgress).toFixed(2) +'%');
                }else{
					$('tbody tr[level=1]',t).not('tr[id=-1]').each(function(){
						var tdval=$('td',this).eq(1).find('label').text()-0;
						assignedTasks+=tdval;
					});
					taskGoal=$('td',this).eq(1).find('label').text()-0;
					notAssignedTasks=taskGoal-assignedTasks;
					
					if(taskGoal==0){
						assignedTasksRatio=0.00;
						completedProgress=0.00;
					}else{
						assignedTasksRatio=assignedTasks/taskGoal*100;
						completedProgress=completedNum/taskGoal*100;
					}
                    $('td',this).eq(2).find('label').text(assignedTasks);

                    //如果未分配非零，则高亮显示
                    if(notAssignedTasks){
                        $('td',this).eq(3).find('label').addClass('red').text(notAssignedTasks);
                        $('td',this).eq(3).find('label').addClass('red').attr("title",notAssignedTasks);
                    }else{
                        $('td',this).eq(3).find('label').removeClass('red').text(notAssignedTasks);
                        $('td',this).eq(3).find('label').removeClass('red').attr("title",notAssignedTasks);
                    }

                    $('td',this).eq(4).find('label').text(parseFloat(assignedTasksRatio).toFixed(2) +'%');
                    $('td',this).eq(4).find('label').attr("title",parseFloat(assignedTasksRatio).toFixed(2) +'%');
					$('td',this).eq(6).find('label').text(parseFloat(completedProgress).toFixed(2) +'%');
                    $('td',this).eq(6).find('label').attr("title",parseFloat(completedProgress).toFixed(2) +'%');
				}
			});
		}	
	};
	var e = {
		assign : function(obj,t,o){
			if(obj.mode > 1){
                $(obj).children("td").eq(1).click(function(){
                    var tdObj = $(this);
                    var lbl=tdObj.find('label');
                    var lblText = lbl.text();  //原来任务目标值
                    lbl.replaceWith(   //将单元格改变为可编辑文本框
                        '<div class="ui-div-text edit-target" style="border:0 !important;">'
                            +'<input type="text" id="targetNum" maxlength="9" class="text" value=\'' + lblText + '\'>'
                            +'</div>'
                    );
                    numInput('input[id=\'targetNum\']'); //输入限制
                    var inputObj = $("#targetNum",this);
                    inputObj.focus(function(e){
                        this.select();
                        inputObj.parent('div').css({background:'#F4E4E4'});
                        e.stopPropagation();
                    }).click(function(){
                        return false;
                    }).blur(function(e){
						var inputText = $(this).val();//获取当前文本框的内容
                        if(inputText == ""){
                            inputText = lblText;
						}
                        if(inputText == 0 ){  //add by wuxj 输入多个0，将值置为0
                            inputText = 0;
                        }
                        //将td的内容修改成文本框中的内容
                        $('div.edit-target').replaceWith("<label class='col1' title='"+inputText+"'>"+parseInt(inputText,10)+"</label>");
                        if(!lblText){ //新建和编辑时，如果原来任务目标值为空，则取隐藏域中任务目标值
                            lblText=tdObj.find('#assignTotalTarget').val();
                        }
                        pt.update(tdObj,t,lblText,o);
                        e.stopPropagation();
					}).focus();
				});
			}
		}
	};
	$.fn.tabletree = function(o){
		o = $.extend({}, $.fn.tabletree.defaults, o);
		
		return this.each(function(){
			var t = this;
			$('tbody tr', t).hover(
					function(){$(this).addClass("over");},
					function(){$(this).removeClass("over");}
			);
			
			$('tbody tr', t).each(function(){
				e.assign(this,t,o);//
				
				if($('#' + this.pid).length == 0){
					this.level = 1;
					
					var child = f.child(this, t);
					
					this.child = child;
					
					if(child.length > 0){
						$('td:eq(' + o.cell + ')', this).prepend('<a class=\'node-icon node-fold\'>&nbsp;</a>');
						
						$('a.node-icon', this).data('child', child);
						
						child.insertAfter(this);
					}
					else{
						$('td:eq(' + o.cell + ')', this).prepend('<a class=\'node-icon node-blank\'>&nbsp;</a>');
					}
				}
				else{
					this.parent = $('#' + this.pid);
					
					var level = parseInt($('#' + this.pid).attr('level'));
					
					var html = '';
					for(var i=0; i<level; i++){
						html += '<a class=\'node-icon node-blank\'>&nbsp;</a>';
					}
					
					this.level = level + 1;
					
					var child = f.child(this, t);
					
					this.child = child;
					
					if(child.length > 0){
						$('td:eq(' + o.cell + ')', this).prepend(html + '<a class=\'node-icon node-fold\'>&nbsp;</a>');
						
						$('a.node-icon', this).data('child', child);
						
						child.insertAfter(this);
					}
					else{
						$('td:eq(' + o.cell + ')', this).prepend(html + '<a class=\'node-icon node-blank\'>&nbsp;</a>');
					}
				}
			});			
			see.count(t);			
			$('a.node-icon').click(function(){
				if($(this).hasClass('node-fold')){
					$('a.node-fold', $(this).data('child')).click();
					$(this).data('child').hide();
					$(this).removeClass('node-fold');
					$(this).addClass('node-expan');
				}
				else{
					if($(this).data('child') && typeof $(this).data('child') !='undifined'){
						$(this).data('child').show();
						$(this).removeClass('node-expan');
						$(this).addClass('node-fold');
					}
				}
			});
		});
	};
	
	$.fn.tabletree.defaults = {
		cell: 0,
		shape: 'shrink'
	};
})(jQuery);