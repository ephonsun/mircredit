/**
 * User: yangy
 * Date: 13-4-8
 * Time: 下午2:34
 * 地图模块静态分页样式
 */

var objPage, j=0,page= 0,nowPage=0;//当前页
var listNum=2;//每页显示<ul>数
var PagesLen=0;//总页数
var PageNum=4;//分页链接接数(5个)
function recordPageLoad(){  //obj为显示客户或者用户模块名
    objPage=$("#recordResult tr");
    j=objPage.length;
    PagesLen=Math.ceil(j/listNum);
    upPage(0);
}
function upPage(p){
    nowPage=p;
    //内容变换
    for (var i=0;i<j;i++){
        objPage[i].style.display="none";
    }
    for (var i=p*listNum;i<(p+1)*listNum;i++){
        if(objPage[i])objPage[i].style.display="block";
    }
    //分页链接变换
    strS='<a href="###" style="color:#0c0cf6;" onclick="upPage(0)">首页</a>  '
    var PageNum_2=PageNum%2==0?Math.ceil(PageNum/2)+1:Math.ceil(PageNum/2);
    var PageNum_3=PageNum%2==0?Math.ceil(PageNum/2):Math.ceil(PageNum/2)+1;
    var strC="",startPage,endPage;
    if (PageNum>=PagesLen) {startPage=0;endPage=PagesLen-1;}
    else if (nowPage<PageNum_2){startPage=0;endPage=PagesLen-1>PageNum?PageNum:PagesLen-1;}//首页
    else {startPage=nowPage+PageNum_3>=PagesLen?PagesLen-PageNum-1: nowPage-PageNum_2+1;var t=startPage+PageNum;endPage=t>PagesLen?PagesLen-1:t;}
    for (var i=startPage;i<=endPage;i++){
        if (i==nowPage)strC+='<a href="###" style="color:red; padding-left:5px; font-weight:700;" onclick="upPage('+i+')">'+(i+1)+'</a> ';
        else strC+='<a href="###" style="color:#0c0cf6; padding-left:5px;" onclick="upPage('+i+')">'+(i+1)+'</a> ';
    }
    strE=' <a href="###" style="color:#0c0cf6; padding-left:5px;" onclick="upPage('+(PagesLen-1)+')">尾页</a>  ';
    strE2=nowPage+1+"/"+PagesLen+"页"+"  共"+j+"条";
    document.getElementById("page-record").innerHTML=strS+strC+strE+strE2 ;
}