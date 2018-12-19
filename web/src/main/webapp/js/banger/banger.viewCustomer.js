//查看客户详情
function newTab(actionType,cusId){
    if(actionType == "insert"){
        var options = {id: "cusInsert"+cusId, pid: GetId(), title: "新建客户", url: "../customer/addCustomer.html?actionType=insert", lock: false};
        tab.add(options);
    }
    else if(actionType == "modify"){
        var options = {id: "cusModify"+cusId, pid: GetId(), title: "编辑客户", url: "../customer/editCustomer.html?actionType=modify&&crmCustomer.customerId="+cusId, lock: false};
        tab.add(options);
    }
    else if(actionType == "browse"){
        isDelete(cusId);
    }
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