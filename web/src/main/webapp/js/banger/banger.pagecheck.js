var PageCheck=
{
    Check:function()
    {
        this.Index=-1;
        this.IsDirty=false;      //是否改变
        this.SourceValue=null;
        this.Value=null;
        this.Key=null;
        this.Entity=null;
        
        this.SetValue=function(op)
        {
            if(this.SourceValue!=null && this.Value!=null)
            {
                if(this.SourceValue==op)
                {
                    this.IsDirty=false;
                }
                else
                {
                    this.IsDirty=true;
                }
                this.Value=op;
            }
        };
    }
    ,Page:function()
    {
	    this.PageNum=-1;
	    this.Parent=null;
	    this.Checks=[];
	    this.CheckAll=false;
	    this.SetCheckValue=function(index,op,ele)
	    {
	       var c=this.GetCheck(index);
	       if(c)
	       {
	           c.SetValue(op);
	       }
	       else
	       {
	           var nc=new PageCheck.Check();
	           nc.SourceValue=!op;
	           nc.Value=op;
	           nc.IsDirty=true;
	           nc.Index=index;
	           nc.Key=ele.value;
	           nc.Entity=ele.Entity;
	           this.Checks.push(nc);
	       }
	    };
	    this.GetCheck=function(index)
	    {
	       for(var i=0;i<this.Checks.length;i++)
	       {
	           var c=this.Checks[i];
	           if(c.Index==index)return c;
	       }
	    };
    }
    ,Control:function()
    {
	    this.Pages=[];
	    this.Column=1;
	    this.Id=null;
	    this.AddPage=function(pageNum)
	    {
	         var page=new PageCheck.Page();
	         page.PageNum=pageNum;
	         page.Parent=this;
	         this.Pages.push(page);
	         return page;
	    };
	    this.GetTable=function()
	    {
	    	return $("#"+this.Id)[0];
	    };
	    this.GetPage=function(pageNum)
	    {
            for(var i=0;i<this.Pages.length;i++)
            {
                var p=this.Pages[i];
                if(p.PageNum==pageNum)return p;
	        }
	    };
	    this.GetCheckedValues=function()
	    {
	        var list=[];
	        for(var j=0;j<this.Pages.length;j++)
            {
                var page=this.Pages[j];
                for(var i=0;i<page.Checks.length;i++)
                {
                    var ck=page.Checks[i];
                    if(ck.Value)
                    {
                        list.push(ck.Key);
                    }
                }
            }
            return list;
	    };
	    this.GetCheckedEntitys=function()
	    {
	        var list=[];
	        for(var j=0;j<this.Pages.length;j++)
            {
                var page=this.Pages[j];
                for(var i=0;i<page.Checks.length;i++)
                {
                    var ck=page.Checks[i];
                    if(ck.Value)
                    {
                        list.push(ck.Entity);
                    }
                }
            }
            return list;
	    }
	    this.GetPageCheckValue=function() 
	    {
	        //获得选中和取消值

	        var insValue="";
	        var delValue="";
            for(var j=0;j<this.Pages.length;j++)
            {
                var page=this.Pages[j];
                
	            for(var i=0;i<page.Checks.length;i++)
                {  
                    if(page.Checks[i].IsDirty)
                    {
                        if(page.Checks[i].Value)
                        {
                            if(insValue=="")
                            {
                                insValue+=page.Checks[i].Key;
                            }
                            else
                            {
                                insValue+=","+page.Checks[i].Key;
                            }
                        }
                        else
                        {
                            if(delValue=="")
                            {
                                delValue+=page.Checks[i].Key;
                            }
                            else
                            {
                                delValue+=","+page.Checks[i].Key;
                            }
                        }
                    }
                }
              }
            return insValue+"|"+delValue;
	    };
	    this.LoadPage=function(pageNum)
	    {
	         var page=this.GetPage(pageNum);
	         if(!page)page=this.AddPage(pageNum);
             this.SetPageChecks(pageNum);
	    };
	    this.ClearAllChecks=function()
	    {
	        for(var i=0;i<this.Pages.length;i++)
	        {
	            var page=this.Pages[i];
	            delete page;
	        }
	        this.Pages=[];
	    };
	    this.SetPageChecks=function(pageNum)
	    {
	        var table=this.GetTable();
            var cellIndex=this.Column;
            var page=this.GetPage(pageNum);
            if(table)
            {
                for(var i=0;i<page.Checks.length;i++)
                {
                    var opCheck=page.Checks[i];
                    if(opCheck && opCheck.IsDirty)
                    {
                        var row=table.rows[opCheck.Index];
                        if(row)
                        {
                            var cell=row.cells[cellIndex];
                            if(cell)
                            {
                                var check=PageCheck.Helper.GetChildCheckElement(cell);
                                if(check)
                                {
                                    check.checked=opCheck.Value;
                                }
                            }
                        }
                    }
                }
                
                var topRow=table.rows[0];
                if(topRow)
                {
                    var topCell=topRow.cells[cellIndex];
                    if(topCell)
                    {
                        var topCheck=PageCheck.Helper.GetChildCheckElement(topCell);
                        if(topCheck)
                        {
                            topCheck.checked=page.CheckAll;
                        }
                    }
                }
                
                for(var i=1;i<table.rows.length;i++)
                {
                    var row=table.rows[i];
                    if(row)
                    {
                        var cell=row.cells[cellIndex];
                        if(cell)
                        {
                            var check=PageCheck.Helper.GetChildCheckElement(cell);
                            if(check)
                            {
                                if(check.checked)
                                {
                                    page.SetCheckValue(i,true,check);
                                }
                            }
                        }
                    }
                }
            }
	    };
	    this.CheckPageAll=function(pageNum,op)
	    {
           var page=this.GetPage(pageNum);
           var table=this.GetTable();
           var cellIndex=this.Column;
           if(table)
           {
                for(var i=0;i<table.rows.length;i++)
                {
                      var row=table.rows[i];
                      if(row)
                      {
                            var cell=row.cells[cellIndex];
                            if(cell)
                            {
                                var check=PageCheck.Helper.GetChildCheckElement(cell);
                                if(check)
                                {
                                     if(op)
                                     {
                                        if(!check.checked)page.SetCheckValue(row.rowIndex,true,check); 
                                        check.checked=true;
                                     }
                                     else
                                     {
                                        var opCheck=page.GetCheck(row.rowIndex);
                                        if(opCheck)
                                        {
                                            if(opCheck.Value)page.SetCheckValue(row.rowIndex,false,check);
                                        } 
                                        else
                                        {
                                            if(check.checked)page.SetCheckValue(row.rowIndex,false,check);
                                        }
                                        check.checked=false;    
                                     }
                                }
                            }
                      }
                }
           }
           page.CheckAll=op;
           this.SetPageChecks(pageNum);
	    };
	    this.PageCheck=function(pageNum)
	    {
	        var page=this.GetPage(pageNum);
	        var ele=event.srcElement || event.target;
    	    
            if(ele.tagName=="INPUT" && ele.type=="checkbox")
            {
                var op=ele.checked;
                var row=PageCheck.Helper.GetParentElementByTagName(ele,"TR");
                if(row)
                {
                    if(row.rowIndex==0)this.CheckPageAll(pageNum,op);
                    else page.SetCheckValue(row.rowIndex,op,ele);
                }
            }
	    };
    }
    ,Construct:function(id,column)
    {
        var cps=this.Helper.CreatePageCheckControl(id,column);
        return cps;
    }
    ,Helper:
    {
        CreatePageCheckControl:function(id,column)
        {
            var cps=new PageCheck.Control();
            cps.Id=id;
            if(column!=null)cps.Column=column;
            return cps;
        }
        ,GetChildCheckElement:function(ele)
        {
            for(var i=0;i<ele.children.length;i++)
            {
                var c=ele.children[i];
                if(c && c.tagName=="INPUT" && c.type=="checkbox")return c;
            }
        }
        ,GetParentElementByTagName:function(elem,tagName)
	    {
	        while (elem && elem.tagName && elem.tagName.toUpperCase() != tagName.toUpperCase()) elem =elem.parentElement || elem.parentNode;
	        return elem;
	    }
    }
};
