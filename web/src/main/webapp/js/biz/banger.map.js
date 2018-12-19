/**
 * 地图定位模块JS提取
 * User: yangy
 * Date: 13-3-23
 * Time: 下午5:03
 */
function qingkong() {            //清空查询条件
    $("#loanStatus").val("");
    $("#custId").val("");
    $("#customerName").tips({ msg: "姓名或联系电话" });
    $("#userName").tips({ msg: "姓名或编号" });
}
function TipContents(address) {//内容格式化
    if (address == "" || address == "undefined" || address == null || address == " undefined" || typeof address == "undefined") {
        address = "暂无";
    }
    var str = "地址：" + address;
    return str;
}

function closeContent() {//闭关客户标记窗口
    $('.div_content').hide();
    $('.olay').hide();
}

function closeWin() {  //关闭标记信息窗口

}

function resetTab() { //清空分页内容
    $('.easy-tabs-page').each(function (i) {
        $(this).html("");
    });
}

function formatDate(date, format) {//日期格式转换
    if (!date) return;
    if (!format) format = "yyyy-MM-dd HH:mm:ss";
    switch (typeof date) {
        case "string":
            date = new Date(date.replace(/-/, "/"));
            break;
        case "number":
            date = new Date(date);
            break;
    }
    if (!date instanceof Date) return;
    var dict = {
        "yyyy": date.getFullYear(),
        "M": date.getMonth() + 1,
        "d": date.getDate(),
        "H": date.getHours(),
        "m": date.getMinutes(),
        "s": date.getSeconds(),
        "MM": ("" + (date.getMonth() + 101)).substr(1),
        "dd": ("" + (date.getDate() + 100)).substr(1),
        "HH": ("" + (date.getHours() + 100)).substr(1),
        "mm": ("" + (date.getMinutes() + 100)).substr(1),
        "ss": ("" + (date.getSeconds() + 100)).substr(1)
    };
    return format.replace(/(yyyy|MM?|dd?|HH?|ss?|mm?)/g, function () {
        return dict[arguments[0]];
    });
}

function play(obj, name, path, fileSize) {      //播放录音
    if (fileSize == 0 || fileSize == "") {
        banger.page.showMessageBox("对不起,录音在上传时损坏,无法播放！");
        return;
    }
    var randomId = Math.random() * 10000;
    jQuery.post("../record/haveRec.html", {"path": path + "/" + name}, function (result) {
        if (result == 0) {
            banger.page.showMessageBox({"type": "error", "content": "文件不存在！"});
            return;
        } else {
            jQuery.post("../record/playVideo.html", {"recordInfo.fileName": name, "recordInfo.filePath": path}, function (result) {
                new window.video.create(randomId, 310, 60, result, obj);
            });
        }
    });

}

function ajaxQuery(urlStr) {//公用ajax请求
    var array =null;
    jQuery.ajax({
        type: "post", //请求方式
        async: false,
        url: urlStr, //发送请求地址
        data: postJson,
        success: function (html) {
            array = eval(html);
        }
    });
    return  array;
}

function analyzeResult(array,type) { //array查询出来的结果， type查询的类型 query为查询
    var customerTotal = 0, userTotal = 0, iconPath = "", k = 0, markers = [], gpsIdCount = 0;
    if (array||array==undefined) {
        //banger.page.showMessageBox(" 未查询到相关信息！");
        return false;
    }
    for (var i = 0; i < array.length; i++) {
        if (array[i]["userGpsId"] || array[i]["customerGpsId"] != "")
            gpsIdCount++;
    }
    if (array.length > 0) {
        if (gpsIdCount > 0) {
            for (var i = 0; i < array.length; i++) {
                if (!array[i]["gpsLng"])
                    continue;
                if (array[i]["type"] == 1) {//返回类型为用户设置图标为黑色
                    iconPath = "../images/icon/map_black.png";
                    userTotal = array[i]["size"];
                } else {
                    customerTotal = array[i]["size"];
                    if (array[i]["loanFlat"] == 2) //有贷款状态的用户为蓝色，无贷款为绿色
                        iconPath = "../images/icon/map_blue.png";
                    else
                        iconPath = "../images/icon/map_green.png";
                }
                markers.push(new MMap.Marker({
                    icon: iconPath,
                    position: new MMap.LngLat(array[i]["gpsLng"], array[i]["gpsLat"]),
                    id: array[i]["gpsId"],
                    offset: new MMap.Pixel(-0, -34)
                }));
                mapObj.addOverlays(markers[k]);
                mapObj.bind(markers[k], 'click', function (event) {
                    $("input").blur();
                    markersInfoClick(this.obj);
                });

                mapObj.bind(markers[k], 'dblclick', function (event) {
                    $("input").blur();
                    mapObj.setCenter(this.obj.getPosition());
                    markersInfoClick(this.obj);
                });
                k++;
            }
            if(type=='query'){
                mapObj.setLevel();//设置地图合适视野级别
            }
            $("#customerTotal").text(customerTotal);        //设置查询到的客户数
            $("#userTotal").text(userTotal);                //设置查询到的用户数
        }
    }
}


function markersInfoClick(obj){//减少重载
    inforWindow = {};
    var objGps = obj;
    postJson['gpsId']=objGps.id;
    geo.regeocode(objGps.getPosition(), function (dataCode) {   //根据坐标返回地址
        if (dataCode.status == "E0") {
            tempAddress = getAddress(dataCode);
            jQuery.ajax({
                type: "post", //请求方式
                async: false,
                url: "getMapCustomerGpsList.html", //发送请求地址
                data: postJson,
                success: function (html) {
                    var bean = eval(html);
                    if (bean) {
                        if (objGps.id.split(",")[0] == "user") {
                            if (bean.length == 1) {
                                inforWindow = newUserPlan(bean, tempAddress);
                                inforWindow.open(mapObj, objGps.getPosition());
                            } else {
                                showDiffTab(2, objGps.id);
                            }
                        } else {
                            if (bean.length == 1) {
                                inforWindow = newCustomerPlan(bean, tempAddress);
                                inforWindow.open(mapObj, objGps.getPosition());
                            } else {
                                showDiffTab(1, objGps.id);
                            }
                        }
                    }
                }
            });
        }
    });
}


function markersInfoClickScan(obj){ //减少重载
    inforWindow = {};
    var objGps = obj;
    postJson['gpsId']=objGps.id;
    geo.regeocode(objGps.getPosition(), function (dataCode) {   //根据坐标返回地址
        if (dataCode.status == "E0") {
            tempAddress = getAddress(dataCode);
            jQuery.ajax({
                type: "post", //请求方式
                async: false,
                url: "getMapCustomerGpsList.html", //发送请求地址
                data: postJson,
                success: function (html) {
                    var bean = eval(html);
                    if (bean) {
                        if (objGps.id.split(",")[0] == "record") {
                            if (bean.length == 1) {
                                inforWindow = newRecordPlan(bean, tempAddress);
                                inforWindow.open(mapObj, objGps.getPosition());
                            } else {
                                showDiffTab(2, objGps.id);
                            }
                        } else {
                            if (bean.length == 1) {
                                inforWindow = newCustomerPlan(bean, tempAddress);
                                inforWindow.open(mapObj, objGps.getPosition());
                            } else {
                                showDiffTab(1, objGps.id);
                            }
                        }
                    }
                }
            });
        }
    });
}


function getAddress(dataCode){  //处理根据坐标
    var ttAddress=null,address="";
    if(dataCode.list[0].poilist[0]){
        if(dataCode.list[0].poilist[0].address)
            ttAddress=dataCode.list[0].poilist[0].address;
    }
    if(dataCode.list[0].roadlist[0]){
        if(ttAddress)
            address = dataCode.list[0].city.name + ttAddress;
        else
            address = dataCode.list[0].city.name + dataCode.list[0].district.name + dataCode.list[0].roadlist[0].name;
    }else{
        if(ttAddress)
            address = dataCode.list[0].city.name + ttAddress;
        else
            address = dataCode.list[0].city.name + dataCode.list[0].district.name;
    }
    if(!/[^\d]/.test(address.substr(address.length-1,address.length))) {
        address=address+"号";
    }
    return  address;
}

function analyzeResultScan(array) {//扫街信息中地图标记方法
    var array = array, customerTotal = 0, recordTotal = 0, iconPath = "",markers = [], k = 0;
    if (!array) {
        banger.page.showMessageBox(" 未查询到相关信息！");
        return false;
    }
    if (array.length > 0) {
        for (var i = 0; i < array.length; i++) {
            if (!array[i]["gpsLng"])
                continue;
            if (array[i]["type"] == 1) {
                iconPath = "../images/icon/map_red.png";
                recordTotal = array[i]["size"];
            } else {
                customerTotal = array.length;
                $("#customerTotalnew").html(customerTotal);
                if (array[i]["loanFlat"] == 2) //有贷款状态的用户为蓝色，无贷款为绿色
                    iconPath = "../images/icon/map_blue.png";
                else
                    iconPath = "../images/icon/map_green.png";
            }
            var resultStr;
            var html = newMoreCustomerPlan(array,resultStr,i);
            var wnd = {width:100,height:100,detaX:-100,detaY:-175,html:html};
            var pointMarker = new RSelfMarker( array[i]["gpsLng"], array[i]["gpsLat"],wnd ,iconPath, -10, -18, 20,20);
			//将标注添加入 rmap对象
			mapObj.addMarker(pointMarker);
			
			
			//pointMarker.refresh();
			//pointMarker.runClickEvent=function(event){
				//markersInfoClickScan(this);
			//};
			
			/*
			 * 
			 pointMarker.addEventListener(RMarkerEvent.MouseDoubleClickEvent,function(evt){
				markersInfoClickScan(this.obj);
			});
			
			
			
            markers.push(new MMap.Marker({
                icon: iconPath,
                position: new MMap.LngLat(array[i]["gpsLng"], array[i]["gpsLat"]),
                id: array[i]["gpsId"],
                offset: new MMap.Pixel(-0, -34)
            }));
            mapObj.addOverlays(markers[k]);
            mapObj.bind(markers[k], 'click', function () {
                markersInfoClickScan(this.obj);
            });

            mapObj.bind(markers[k], 'dblclick', function (event) {
                mapObj.setCenter(this.obj.getPosition());
                markersInfoClickScan(this.obj);
            });
            */
            k++;
        }
        //mapObj.setFitView();//设置地图合适视野级别
       // $("#customerTotal").text(customerTotal);        //设置查询到的客户数
        $("#recordTotal").text(recordTotal);                //设置查询到的用户数
    }
}

function initLocationPage() { //页面初始化
    $("#customerTotal").text(0);        //设置查询到的客户数
    $("#userTotal").text(0);                //设置查询到的用户数
    var custId=$("#custId").val(),gpsFlat=$("#gpsFlat").val(),custName=$("#custName").val(),custAddress=$("#custAddress").val();
    if (gpsFlat && gpsFlat == 0) {
        $("input").blur();
        showLocationMap(custId, custAddress, custName);
    } else {
        var url = "initCustomerOrUserGps.html";
        if (gpsFlat && gpsFlat != 0) {
            url = "getCustomerOrUserGps.html";
            getQueryJson(3);
        } else {
            getQueryJson(1);
        }
        var array = ajaxQuery(url);
        showDiffTab(1, "");
        analyzeResult(array);
        if(gpsFlat && gpsFlat != 0){
            getMarker(gpsFlat,2);
        }
    }

}

function initScaningPage() { //页面初始化


    getQueryJson(1);
    var array = ajaxQuery("getCustomerOrRecordGps.html");
    showDiffTab(1, "");
    analyzeResultScan(array);
}


function newUserPlan(bean, resultStr) {//用户信息的窗口
    var account = bean[0]["account"];
    if (account == "") {
        account = "";
    } else {
        account = "(" + account + ")";
    }
    var html =  '<div class="map-tip-box">'
        + '<div class="map-tip-box-top">'
        + '<div class="map-tip-box-top-l"></div>'
        + '<div class="map-tip-box-top-c"></div>'
        + '<div class="map-tip-box-top-r"></div>'
        + '</div>'
        + '<div class="map-tip-box-center">'
        + '<div class="cus-content">'
        + '<table class="cus-contnet-info">'
        + '<tbody>'
        + '<tr>'
        + '<td colspan="2">'
        + '<label class="cus-name">' + bean[0]["userName"] + account + '</label>'
        + '</td>'
        + '</tr>'
        + '<tr>'
        + '<td width="60"><label>当前位置：</label></td>'
        + '<td><label class="ellipsis" title="' + resultStr + '">' + resultStr + '</label></td>'
        + '</tr>'
        + '<tr>'
        + '<td  width="60"><label>主管姓名：</label></td>'
        + '<td><label class="ellipsis" title="' + bean[0]["leadName"] + '">' + bean[0]["leadName"] + '</label></td>'
        + '</tr>'
        + '<tr>'
        + '<td width="60" style="vertical-align:top;"><label>工作进度：</label></td>'
        + '<td style="white-space: normal;word-break:break-all;"><label>' + bean[0]["workPlan"] + '</label></td>'
        + '</tr>'
        + '</tbody>'
        + '</table>'
        + '</div></div>'
        + '<div class="map-tip-box-bottom">'
        + '<div class="map-tip-box-bottom-l"></div>'
        + '<div class="map-tip-box-bottom-c"></div>'
        + '<div  class="map-tip-box-bottom-r"></div>'
        + '</div>'
        + '<div class="map-tip-box-hand"></div>'
        + '<a class="map-tip-box-close" href="#2" onclick="closeWin();return false;">&nbsp;</a>'
        + '</div>';
    return html;
}

function newMoreUserPlan(bean, resultStr,i) {//用户信息的窗口
    var account = bean[i]["account"];
    if (account == "") {
        account = "";
    } else {
        account = "(" + account + ")";
    }
    var html =  '<div class="map-tip-box">'
        + '<div class="map-tip-box-top">'
        + '<div class="map-tip-box-top-l"></div>'
        + '<div class="map-tip-box-top-c"></div>'
        + '<div class="map-tip-box-top-r"></div>'
        + '</div>'
        + '<div class="map-tip-box-center">'
        + '<div class="cus-content">'
        + '<table class="cus-contnet-info">'
        + '<tbody>'
        + '<tr>'
        + '<td colspan="2">'
        + '<label class="cus-name">' + bean[i]["userName"] + account + '</label>'
        + '</td>'
        + '</tr>'
        + '<tr>'
        + '<td width="60"><label>当前位置：</label></td>'
        + '<td><label class="ellipsis" title="' + resultStr + '">' + resultStr + '</label></td>'
        + '</tr>'
        + '<tr>'
        + '<td  width="60"><label>主管姓名：</label></td>'
        + '<td><label class="ellipsis" title="' + bean[i]["leadName"] + '">' + bean[i]["leadName"] + '</label></td>'
        + '</tr>'
        + '<tr>'
        + '<td width="60" style="vertical-align:top;"><label>工作进度：</label></td>'
        + '<td style="white-space: normal;word-break:break-all;"><label>' + bean[i]["workPlan"] + '</label></td>'
        + '</tr>'
        + '</tbody>'
        + '</table>'
        + '</div></div>'
        + '<div class="map-tip-box-bottom">'
        + '<div class="map-tip-box-bottom-l"></div>'
        + '<div class="map-tip-box-bottom-c"></div>'
        + '<div  class="map-tip-box-bottom-r"></div>'
        + '</div>'
        + '<div class="map-tip-box-hand"></div>'
        + '<a class="map-tip-box-close" href="#2" onclick="closeWin();return false;">&nbsp;</a>'
        + '</div>';
    return html;
}

function newCustomerPlan(bean, resultStr) { //客户信息的窗口
    var phoneNo = bean[0]["phoneNo"], customerId = bean[0]["customerId"];
    var clickPhone = 'class="cus-call" onclick="dial(\'' + phoneNo + '\',' + customerId + ');return false;"';
    if (phoneNo == "")
        clickPhone = "";
    var customerTitle = bean[0]["customerTitle"];
    if (customerTitle == "") {
        customerTitle = "";
    } else {
        customerTitle = "(" + customerTitle + ")";
    }
    var isNogood="";
    if(bean[0]["isNogood"]=="1"){
        isNogood="不良客户";
    }
    if(bean[0]["address"]&&bean[0]["address"]!=""){
        resultStr=bean[0]["address"];
    }else{
        resultStr = " ";
    }
    var loanStr="";
    if(bean[0]["loanStatus"]!=""){
        loanStr='<tr>'
                + '<td width="60"><label>贷款状态：</label></td>'
                + '<td><label>' + bean[0]["loanStatus"] + '</label></td>'
                + '</tr>';
    }
    var customerName = '<span class="slink" onclick="isDelete('+customerId+')" title="'+ bean[0]["customerName"]+'">'+ bean[0]["customerName"]+'</span>';
    var html = '<div class="map-tip-box">'
        + '<div class="map-tip-box-top">'
        + '<div class="map-tip-box-top-l"></div>'
        + '<div class="map-tip-box-top-c"></div>'
        + '<div class="map-tip-box-top-r"></div>'
        + '</div>'
        + '<div class="map-tip-box-center">'
        + '<div class="cus-content">'
        + '<table class="cus-contnet-info">'
        + '<tbody>'
        + '<tr>'
        + '<td colspan="2">'
        + '<label class="cus-name" title="' + bean[0]["customerName"] + customerTitle + '">' + customerName + customerTitle + '<span style="color:#f00;margin-left:5px;">'+isNogood+'</span></label>'
        + '<label ' + clickPhone + '>&nbsp;</label>'
        + '</td>'
        + '</tr>'
        + '<tr>'
        + '<td width="60"><label>联系电话：</label></td>'
        + '<td><label>' + bean[0]["phoneNo"] + '</label></td>'
        + '</tr>'
        + '<tr>'
        + '<td  width="60"><label>客户地址：</label></td>'
        + '<td><label class="ellipsis" title="' + resultStr + '">' + resultStr + '</label></td>'
        + '</tr>'
        + '<tr>'
        + '<td width="60"><label>归属人员：</label></td>'
        + '<td><label class="ellipsis" title="' + bean[0]["userName"] + '">' + bean[0]["userName"] + '</label></td>'
        + '</tr>'
        +loanStr
        + '</tbody>'
        + '</table>'
        + '</div></div>'
        + '<div class="map-tip-box-bottom">'
        + '<div class="map-tip-box-bottom-l"></div>'
        + '<div class="map-tip-box-bottom-c"></div>'
        + '<div  class="map-tip-box-bottom-r"></div>'
        + '</div>'
        + '<div class="map-tip-box-hand"></div>'
        + '<a class="map-tip-box-close" href="#2" onclick="closeWin();return false;">&nbsp;</a>'
        + '</div>';
    return html;
}

function newMoreCustomerPlan(bean, resultStr,i) { //客户信息的窗口
    var phoneNo = bean[i]["phoneNo"], customerId = bean[i]["customerId"];
    var clickPhone = 'class="cus-call" onclick=dial(\'' + phoneNo + '\',' + customerId + ');return false;"';
    if (phoneNo == "")
        clickPhone = "";
    var customerTitle = bean[i]["customerTitle"];
    if (customerTitle == "") {
        customerTitle = "";
    } else {
        customerTitle = "(" + customerTitle + ")";
    }
    var isNogood="";
    if(bean[i]["isNogood"]=="1"){
        isNogood="不良客户";
    }
    if(bean[i]["address"]&&bean[i]["address"]!=""){
        resultStr=bean[i]["address"];
    }else{
        resultStr = " ";
    }
    var loanStr="";
    if(bean[i]["loanStatus"]!=""){
        loanStr='<tr>'
            + '<td width="60"><label>贷款状态：</label></td>'
            + '<td><label>' + bean[i]["loanStatus"] + '</label></td>'
            + '</tr>';
    }
    //跳转至客户基本信息
    var customerName = '<span class="slink" onclick="isDelete('+customerId+')" title="'+ bean[i]["customerName"]+'">'+ bean[i]["customerName"]+'</span>';
    var html = '<div class="map-tip-box">'
        + '<div class="map-tip-box-top">'
        + '<div class="map-tip-box-top-l"></div>'
        + '<div class="map-tip-box-top-c"></div>'
        + '<div class="map-tip-box-top-r"></div>'
        + '</div>'
        + '<div class="map-tip-box-center">'
        + '<div class="cus-content">'
        + '<table class="cus-contnet-info">'
        + '<tbody>'
        + '<tr>'
        + '<td colspan="2">'
        + '<label class="cus-name" title="' + bean[i]["customerName"] + customerTitle + '">' + customerName + customerTitle + '<span style="color:#f00;margin-left:5px;">'+isNogood+'</span></label>'
        + '<label ' + clickPhone + '>&nbsp;</label>'
        + '</td>'
        + '</tr>'
        + '<tr>'
        + '<td width="60"><label>联系电话：</label></td>'
        + '<td><label>' + bean[i]["phoneNo"] + '</label></td>'
        + '</tr>'
        + '<tr>'
        + '<td  width="60"><label>客户地址：</label></td>'
        + '<td><label class="ellipsis" title="' + resultStr + '">' + resultStr + '</label></td>'
        + '</tr>'
        + '<tr>'
        + '<td width="60"><label>归属人员：</label></td>'
        + '<td><label class="ellipsis" title="' + bean[i]["userName"] + '">' + bean[i]["userName"] + '</label></td>'
        + '</tr>'
        +loanStr
        + '</tbody>'
        + '</table>'
        + '</div></div>'
        + '<div class="map-tip-box-bottom">'
        + '<div class="map-tip-box-bottom-l"></div>'
        + '<div class="map-tip-box-bottom-c"></div>'
        + '<div  class="map-tip-box-bottom-r"></div>'
        + '</div>'
        + '<div class="map-tip-box-hand"></div>'
        + '<a class="map-tip-box-close" href="#2" onclick="closeWin();return false;">&nbsp;</a>'
        + '</div>';
    return html;
}

function newRecordPlan(bean, resultStr) {//录音信息的窗口
    var recordName = bean[0]["recordName"], fileSize = bean[0]["fileSize"], path = bean[0]["recordAddress"];
    var clickPlay = 'class="ui-link-btn record-paly" onclick="play(this,\'' + recordName + '\',\'' + path + '\',' + fileSize + ');return false;"';
    var recordTime = formatDate(bean[0]["recordTime"]);
    var html =  '<div class="map-tip-box">'
        + '<div class="map-tip-box-top">'
        + '<div class="map-tip-box-top-l"></div>'
        + '<div class="map-tip-box-top-c"></div>'
        + '<div class="map-tip-box-top-r"></div>'
        + '</div>'
        + '<div class="map-tip-box-center">'
        + '<div class="cus-content">'
        + '<table class="cus-contnet-info">'
        + '<tbody>'
        + '<tr>'
        + '<td  width="60"><label>录音名称：</label></td>'
        + '<td>'
        + '<label class="ellipsis" style="width:140px;float:left;" title="' + recordName + '">' + recordName + '</label>'
        + '<a href="#2" ' + clickPlay + '>播放</a>'
        + '</td>'
        + '</tr>'
        + '<tr>'
        + '<td  width="60"><label>录音地址：</label></td>'
        + '<td><label class="ellipsis" title="' + resultStr + '">' + resultStr + '</label></td>'
        + '</tr>'
        + '<tr>'
        + '<td width="60"><label>上传用户：</label></td>'
        + '<td><label>' + bean[0]["recordUploadUser"] + '</label></td>'
        + '</tr>'
        + '<tr>'
        + '<td width="60"><label>录音时间：</label></td>'
        + '<td><label>' + recordTime + '</label></td>'
        + '</tr>'
        + '</tbody>'
        + '</table>'
        + '</div></div>'
        + '<div class="map-tip-box-bottom">'
        + '<div class="map-tip-box-bottom-l"></div>'
        + '<div class="map-tip-box-bottom-c"></div>'
        + '<div  class="map-tip-box-bottom-r"></div>'
        + '</div>'
        + '<div class="map-tip-box-hand"></div>'
        + '<a class="map-tip-box-close" href="#2" onclick="closeWin();return false;">&nbsp;</a>'
        + '</div>';

    return html;
}

function isDelete(cusId){
    jQuery.ajax({
        type: 'POST',
        url: "../customer/isDeleteCus.html?"+"random="+Math.random()*1000,
        data: {"cusId":cusId},
        success:function(data){
            var obj = jQuery.parseJSON(data);
            if(obj[0].isDel=='1'){
                banger.page.showMessageBox('客户"'+obj[0].customerName+'"被删除，无法查看具体资料！');
            }else{
                if('$isSelectCus'=='True'){
                    window.top.aui.builder({
                        title: '查看客户',
                        url: '../customer/browseCustomer.html?' + 'actionType=browse&&needLine=0&&isBrowseLayer=1&&crmCustomer.customerId=' + cusId
                    });
                }else{
                    var options = {id: "cusBrowse"+cusId, pid: GetId(), title: "查看客户", url: "../customer/browseCustomer.html?actionType=browse&&crmCustomer.customerId="+cusId, lock: false};
                    tab.add(options);
                }
            }
        }
    });
}



