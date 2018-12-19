// JavaScript Document
		
//固话 传真输入控制
		function phoneOrFaxInput(query)
		{
			$(query).css({"ime-mode":"disabled"}).attr({"maxLength":20});;
			$(query).bind({					//只能按数字键
				"keydown":function(e){
					var key = e.keyCode || e.which;
					if((key==37 || key==39 || key==8 || key==56 || key==106 || key==51) || (48<=key &&key<=57 && !e.shiftKey) || (96<=key && key<=105))
					{
						return true;
					}
					return false;
				}
			});
		}
		
//验证身份证格式
		var vcity={ 11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",
            21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",
            33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",
            42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",
            51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",
            63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"
           };
		function checkIDCard(card){
			if(card === ''){false;}
		    //校验长度，类型
		    if(isCardNo(card) === false){return false;}
		    //检查省份
		    if(checkProvince(card) === false){return false;}
		    //校验生日
		    if(checkBirthday(card) === false){return false;}
		    //检验位的检测
		    if(checkParity(card) === false){return false;}
		    return true;	
		}
		//检查号码是否符合规范，包括长度，类型
		isCardNo = function(card)
		{
		    //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
		    var reg = /(^\d{15}$)|(^\d{17}(\d|X)$)/;
		    if(reg.test(card) === false){return false;}
		    return true;
		};
		
		//取身份证前两位,校验省份
		checkProvince = function(card)
		{
		    var province = card.substr(0,2);
		    if(vcity[province] == undefined){return false;}
		    return true;
		};
		
		//检查生日是否正确
		checkBirthday = function(card)
		{
		    var len = card.length;
		    //身份证15位时，次序为省（3位）市（3位）年（2位）月（2位）日（2位）校验位（3位），皆为数字
		    if(len == '15')
		    {
		        var re_fifteen = /^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/;
		        var arr_data = card.match(re_fifteen);
		        var year = arr_data[2];
		        var month = arr_data[3];
		        var day = arr_data[4];
		        var birthday = new Date('19'+year+'/'+month+'/'+day);
		        return verifyBirthday('19'+year,month,day,birthday);
		    }
		    //身份证18位时，次序为省（3位）市（3位）年（4位）月（2位）日（2位）校验位（4位），校验位末尾可能为X
		    if(len == '18')
		    {
		        var re_eighteen = /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/;
		        var arr_data = card.match(re_eighteen);
		        var year = arr_data[2];
		        var month = arr_data[3];
		        var day = arr_data[4];
		        var birthday = new Date(year+'/'+month+'/'+day);
		        return verifyBirthday(year,month,day,birthday);
		    }
		    return false;
		};
		
		//校验日期
		verifyBirthday = function(year,month,day,birthday)
		{
		    var now = new Date();
		    var now_year = now.getFullYear();
		    //年月日是否合理
		    if(birthday.getFullYear() == year && (birthday.getMonth() + 1) == month && birthday.getDate() == day)
		    {
		        //判断年份的范围（3岁到100岁之间)
		        var time = now_year - year;
		        if(time >= 3 && time <= 100)
		        {
		            return true;
		        }
		        return false;
		    }
		    return false;
		};
		
		//校验位的检测
		checkParity = function(card)
		{
		    //15位转18位
		    card = changeFivteenToEighteen(card);
		    var len = card.length;
		    if(len == '18')
		    {
		        var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
		        var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
		        var cardTemp = 0, i, valnum;
		        for(i = 0; i < 17; i ++)
		        {
		            cardTemp += card.substr(i, 1) * arrInt[i];
		        }
		        valnum = arrCh[cardTemp % 11];
		        if (valnum == card.substr(17, 1))
		        {
		            return true;
		        }
		        return false;
		    }
		    return false;
		};
		
		//15位转18位身份证号
		changeFivteenToEighteen = function(card)
		{
		    if(card.length == '15')
		    {
		        var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
		        var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
		        var cardTemp = 0, i;  
		        card = card.substr(0, 6) + '19' + card.substr(6, card.length - 6);
		        for(i = 0; i < 17; i ++)
		        {
		            cardTemp += card.substr(i, 1) * arrInt[i];
		        }
		        card += arrCh[cardTemp % 11];
		        return card;
		    }
		    return card;
		};

	//界面输入校验
		
	function checkBelongTo(){
		if($('#belongDeptId').val()=="0"){
			$("#userTree").attr('tips', '客户归属不能为空！');
			$("#deptTree").attr('tips', '客户归属不能为空！');
			$('#userList').addClass('v-fails');
			$('#deptList').addClass('v-fails');
		}else{
			$('#userList').removeClass('v-fails');
			$('#deptList').removeClass('v-fails');
		}		
		var type=$('#BelongToType').val();	
		switch(type){
			case "brUser":
				$("#userTree").focus();
				break;
			case "brDept":
				$("#deptTree").focus();
				break;
			default: break;
		}
	}
	
	function checkBelongToEmpty(obj){
		var type=$('#BelongToType').val();	
		var bool = true;
		var value = $(obj).val();
		switch(type){
			case "brUser":
				var value = $("#userTree").val();
				if(!value){
					bool = false;
				}
				break;
			case "brDept":
				var value = $("#deptTree").val();
				if(!value){
					bool = false;
				}
				break;
		}
		return bool;
	}
	
	function checkPhoneType(){
		var bool = false;
		var objs = $('[phoneType=\'group\']');
		for (i = 0; i < objs.length; i++)
		{
			if($(objs[i]).val()){
				bool = true;
				break;
			}
		}
		return bool;
	}
	
	function checkMobilePhone(obj){
		if($(obj).val().length==11||$(obj).val().length==0){
			var bool = true;
			if(checkPhoneType()){
				$('[phoneType=\'divGroup\']').removeClass('v-fails');
				if($("#mobilePhone1").val().length>0&&$("#mobilePhone1").val().length<11){
					$('#divMobilePhone1').addClass('v-fails');
					$('#mobilePhone1').attr('tips', '手机格式必须是1开头，11位');
				}
				if($("#mobilePhone2").val().length>0&&$("#mobilePhone2").val().length<11){
					$('#divMobilePhone2').addClass('v-fails');
					$('#mobilePhone2').attr('tips', '手机格式必须是1开头，11位');
				}
			}
			else{
				$('[phoneType=\'divGroup\']').addClass('v-fails');
				$('[phoneType=\'divGroup\']').attr('tips', '请至少填写一种联系方式');
				bool = false;
			}
			return bool;
		}else{
			$(obj).attr('tips', '手机格式必须是1开头，11位');
			return false;
		}
	}
	
	function checkPhone(obj){
		var bool = true;
		if(checkPhoneType()){
			$('[phoneType=\'divGroup\']').removeClass('v-fails');
		}else{
			$('[phoneType=\'divGroup\']').addClass('v-fails');
			$('[phoneType=\'divGroup\']').attr('tips', '请至少填写一种联系方式');
			bool = false;
		}
		return bool;
	}
	
	function checkEmail(obj){
		if(!$(obj).val()){return true;}
		var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if (filter.test($(obj).val())) 
			return true;
		else {
			return false;
		}
	}

/* 所有客户-查看客户内容部分js滚动 */
;(function($){
    $.fn.itemScroll = function(options){
        var setting = {
            'oLeft':null,
            'oRight':null,
            'oTime':500
        };

        var _this = this;

        var arts = $.extend(true, {}, setting, options),
        //len = _this.find('li:visible').length,
            len = _this.find('li').not(_this.find('li.hide')).length,
            liW = _this.find('li').eq(0).outerWidth(true),
            clientW = _this.parent()[0].clientWidth,
            diffW = (len*liW) - clientW;

        _this.css({'position':'absolute', 'left':'0', 'top':'0', 'width':len*liW + 'px'});

        if(diffW < 0){
            return false;
        }else{
            if(!arts.oRight) return false;
            arts.oRight.off('.scroll').on('click.scroll', function(){
                clientW = _this.parent()[0].clientWidth + 17;
                diffW = (len*liW) - clientW;

                if(_this.position().left + diffW <= 0)
                    return false;
                else{
                    if(_this.position().left + diffW < 2*liW){
                        if(!_this.is(':animated')){
                            _this.stop(true, true).animate({'left':'-=' + (diffW + _this.position().left + 20 ) + 'px'}, arts.oTime);
                        }
                    }else{
                        if(!_this.is(':animated')){
                            _this.stop(true, true).animate({'left':'-=' + liW + 'px'}, arts.oTime);
                        }
                    }
                };
            });

            if(!arts.oLeft) return false;
            arts.oLeft.off('.scroll').on('click.scroll', function(){
                if(_this.position().left >= 0){
                    return false;
                }else{
                    if(_this.position().left + liW >= 0){
                        if(!_this.is(':animated')){
                            _this.stop(true, true).animate({'left':'+=' + Math.abs(_this.position().left) + 'px'}, arts.oTime);
                        }
                    }else{
                        if(!_this.is(':animated')){
                            _this.stop(true, true).animate({'left':'+=' + liW + 'px'}, arts.oTime);
                        }
                    }
                };
            });
        };
    };
})(jQuery);

//新建客户添加业务和页卡滚动
function addTabScroll(){
    var tLen = $('#tabs-items').find('li[init="false"]').length,
        tLiW = $('#tabs-items').find('li:first').outerWidth(true),
        totalW = tLen*tLiW,
        clientW =$('#tabs-items-zone')[0].clientWidth,
        diffWout = totalW - clientW;

    $('#tabs-items').css({'position':'absolute', 'left':'0', 'top':'0', 'width':totalW + 'px'});

    $("#tabs").tabs({
        extPlguin:function(){
            tLen = $('#tabs-items').find('li:visible').length;
            totalW = tLiW*tLen;
            if(clientW==0){
                clientW =$('#tabs-items-zone')[0].clientWidth + 17;
            }

            $('#tabs-items').css({'width':totalW + 'px'});

            var	offsetW = $('#tabs-items').find('li.tabs-active').offset().left,
                diffW = offsetW - clientW + tLiW;

            diffWout = totalW - clientW;

            if(diffW <= 0){
                return false;
            }else{
                if(!$('#tabs-items').is(':animated')){
                    $('#tabs-items').animate({'left':'-=' + diffW + 'px'}, 1000);
                }
            };
        },
        btnClick:function(){
            $('#rightBtn').off('.scroll').on('click.scroll', function(){
                clientW =$('#tabs-items-zone')[0].clientWidth + 17;
                diffWout = totalW - clientW;

                if($('#tabs-items-zone')[0].scrollWidth - clientW <= 0){
                    return false;
                }else{
                    if($('#tabs-items').position().left + diffWout < 2*tLiW){
                        if(!$('#tabs-items').is(':animated')){
                            $('#tabs-items').animate({'left':'-=' + ($('#tabs-items').position().left + diffWout + 20) + 'px'}, 500);
                        }
                    }else{
                        if(!$('#tabs-items').is(':animated')){
                        $('#tabs-items').animate({'left':'-=' + tLiW + 'px'}, 500);
                        }
                    }
                };
            });

            $('#leftBtn').off('.scroll').on('click.scroll', function(){
                if($('#tabs-items').position().left >= 0){
                    return false;
                }else{
                    if(Math.abs($('#tabs-items').position().left) <= tLiW){
                        if(!$('#tabs-items').is(':animated')){
                            $('#tabs-items').animate({'left':'+=' + Math.abs($('#tabs-items').position().left) + 'px'}, 500);
                        }
                    }else{
                        if(!$('#tabs-items').is(':animated')){
                            $('#tabs-items').animate({'left':'+=' + tLiW + 'px'}, 500);
                        }
                    }
                };
            });
        }
    });
};