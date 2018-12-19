// JavaScript Document

var d = {
	"total": 100,
	"page": 1,
	"rows": [
		{
			'css': 'red',
			'id': 1,
			'cell': {
				'name': 'Zhangsan',
				'sex': 'Boy',
				'birthday': '1988-01-15',
				'degree': 'College'
			}
		},
		{
			'css': 'bold',
			'id': 2,
			'cell': {
				'name': 'Lisi',
				'sex': 'Boy',
				'birthday': '1987-10-21',
				'degree': ''
			}
		},
		{
			'css': '',
			'id': 3,
			'cell': {
				'name': 'Monica',
				'sex': 'Girl',
				'birthday': '1987-11-25',
				'degree': 'Undergraduate'
			}
		},
		{
			'css': 'yellow',
			'id': 4,
			'cell': {
				'name': 'Sprite',
				'sex': 'Girl',
				'birthday': '1988-12-01',
				'degree': 'Undergraduate'
			}
		}
	]
};

(function($){
	$.builder = function(t, o){
		if(t.exist) return false; //return if already exist
		
		o = $.extend({ //apply default properties
			width: 'auto',   //default width
			height: '',     //default height
			title: false, //set the table title
			stretch: false,
			striped: true,   //apply odd even stripes
			novstripe: false,
			minwidth: 30,    //min width of columns
			minheight: 80,   //min height of columns
			resizable: true, //allow table resizing
			url: false,      //url if using data from ajax
			method: 'POST',  //data sending method
			datatype: 'xml', //type of data for ajax ( xml or json )
			errormsg: 'Connection Error', //error message
			usepager: false,
			nowrap: true,
			page: 1, //current page
			total: 1, //total pages
			userp: true, //use the results per page select box
			rp: 10, //results per page
			rpOptions: [10, 20, 30, 40, 50], //allowed per-page values
			rowid: 'id',
			pagestate: 'Displaying {from} - {to} of {total} results',
			pagetext: '跳转到',
			outofstar: '共',
			outofend: '页',
			findtext: 'Find',
			params: [], //allow optional parameters to be passed around
			procmsg: 'Being queried, please wait ...',
			query: '',
			qtype: '',
			nomsg: 'No results',
			minColToggle: 1, //minimum allowed column to be hidden
			showToggleBtn: true, //show or hide column toggle popup
			hideOnSubmit: true,
			autoload: true,
			opacity: 0.5,
			preProcess: false,
			dblClickResize: false, //auto resize column by double clicking
			onDragCol: false,
			onToggleCol: false,
			onChangeSort: false,
			onSuccess: false,
			onError: false,
			onSubmit: false //using a custom populate function
		}, o);
		
		var f = {
			hset: {},
			rePosDrag: function(){
				var top = this.header.position().top, left = 0 - this.header[0].scrollLeft;
				
				this.colsize.css({
					top: top + 'px'
				});
				
				$('table thead tr:first th', this.header).each(function(){
					var prev = $(this).prevAll(), allw = 0;
					prev.each(function(){
						if(!$(this).is(':hidden')){
							allw += $(this).outerWidth(true);
						}
					});
					
					var index = $('table thead tr:first th', f.header).index(this),
						pos = $(this).outerWidth(true) + allw + left - (Math.floor(5/2));
					
					if(isNaN(pos)){
						pos = 0;
					}
					
					if($(this).is(':hidden')){
						$('div:eq(' + index + ')', f.colsize).css({
							left : 0
						}).hide();
					}else{
						$('div:eq(' + index + ')', f.colsize).css({
							left : pos + 'px'
						}).show();
					}
				});
			},
			fixHeight: function(h){
				h = false;
				if(!h){
					h = this.body.height();
				}
				var hh = this.header.height();
				$('div', this.colsize).each(function(){
					$(this).height(h + hh + 2);
				});
				
				this.block.css({
					height: h + 2,
					marginBottom: -h - 2
				});
				
				var hrh = this.gdiv.height();
				this.hdiv.css({
					height: hrh
				});
			},
			dragStart: function(point, e, s){ //default drag function start
				if(point == 'colresize'){ //column resize
					
					
					var idx = $('div', this.colsize).index(s);
					var ow = $('th:visible div:eq(' + idx + ')', this.header).width();
					
					//
					$(s).addClass('hover').siblings().hide();
					$(s).prev().addClass('hover').show();
					
					this.colresize = {
						startX: e.pageX,
						ol: parseInt(s.style.left),
						ow: ow,
						n: idx
					};
					$('body').css('cursor', 'col-resize');
				}else if(point == 'tblresize'){ //table resize
					var hgo = false;
					$('body').css('cursor', 'row-resize');
					if(s){
						hgo = true;
						$('body').css('cursor', 'col-resize');
					}
					this.tblresize = {
						h: o.height,
						sy: e.pageY,
						w: o.width,
						sx: e.pageX,
						hgo: hgo
					};
				}else if(point == 'colmove'){ //column header drag
					
					this.hset = this.header.offset();
					this.hset.right = this.hset.left + $('table', this.header).width();
					this.hset.bottom = this.hset.top + $('table', this.header).height();
					this.Activatedcell = s;
					this.dcoln = $('th', this.header).index(s);
					
					this.simcell = $('<div class=\'simcell\'></div>');
					this.simcell.html($(s).html());
					if($.browser.msie){
						this.simcell.attr('class', 'simcell ie');
					}
					this.simcell.css({
						textAlign: s.align
					});
					
					$('body').append(this.simcell);
					
					$(this.colsize).hide();
				}
				$('body').noselect();
			},
			dragMove: function(e){
				if(this.colresize){ //column resize
					var n = this.colresize.n;
					var diff = e.pageX - this.colresize.startX;
					var nleft = this.colresize.ol + diff;
					var nw = this.colresize.ow + diff;
					if(nw > o.minwidth){
						$('div:eq(' + n + ')', this.colsize).css('left', nleft);
						this.colresize.nw = nw;
					}
				}else if(this.tblresize){ //table resize
					var v = this.tblresize;
					var y = e.pageY;
					var diff = y - v.sy;
					if(!o.defwidth) o.defwidth = o.width;
					if(o.width != 'auto' && !o.nohresize && v.hgo){
						var x = e.pageX;
						var xdiff = x - v.sx;
						var neww = v.w + xdiff;
						if(neww > o.defwidth){
							this.gdiv.width(neww);
							o.width = neww;
						}
					}
					var newh = v.h + diff;
					if((newh>o.minheight || o.height<o.minheight) && !v.hgo){
						this.body.height(newh);
						o.height = newh;
						this.fixHeight(newh);
					}
					v = null;
				}else if(this.simcell){
					$(this.Activatedcell).addClass('thmove').removeClass('thover');
					if(e.pageX>this.hset.right || e.pageX<this.hset.left || e.pageY>this.hset.bottom || e.pageY<this.hset.top){
						//this.dragEnd();
						$('body').css('cursor', 'move');
					}else{
						$('body').css('cursor', 'pointer');
					}
					$(this.simcell).css({
						top: e.pageY + 10,
						left: e.pageX + 20,
						display: 'block'
					});
				}
			},
			dragEnd: function(){
				if(this.colresize){
					var n = this.colresize.n;
					var nw = this.colresize.nw;
					$('th:visible div:eq(' + n + ')', this.header).css('width', nw);
					$('tr', this.body).each(function(){
						var $tddiv = $('td:visible div:eq(' + n + ')', this);
						$tddiv.css('width', nw);
					});
					this.header.scrollLeft = this.body.scrollLeft;
					$('div:eq(' + n + ')', this.colsize).siblings().show();
					$('.hover', this.colsize).removeClass('hover');
					this.rePosDrag();
					this.fixHeight();
					this.colresize = false;
					var name = o.thead[n].name; //store the widths in the cookies
					$.cookie('flexiwidths/' + name, nw);					
				}else if(this.tblresize){
					this.tblresize = false;
				}else if(this.simcell){
					$(this.simcell).remove();
					
					/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					if(this.dcolt != null){
						if(this.dcoln > this.dcolt) $('th:eq(' + this.dcolt + ')', this.header).before(this.Activatedcell);
						else $('th:eq(' + this.dcolt + ')', this.header).after(this.Activatedcell);
						this.switchCol(this.dcoln, this.dcolt);
						
						this.tol.remove();
						this.tor.remove();
						
						//重置表格列的拖动条位置
						this.rePosDrag();
						
						if(o.onDragCol){
							o.onDragCol(this.dcoln, this.dcolt);
						}
					}
					/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					
					this.Activatedcell = null;
					this.hset = null;
					this.dcoln = null;
					this.dcolt = null;
					this.simcell = null;
					
					$('.thmove', this.header).removeClass('thmove');
					
					$(this.colsize).show();
				}
				$('body').css('cursor', 'default');
				$('body').noselect(false);
			},
			toggleCol: function(cid, visible){
				var ncol = $("th[axis='col" + cid + "']", this.header)[0];
				var n = $('thead th', f.header).index(ncol);
				
				if(visible == null){
					visible = ncol.hidden;
				}
				
				if(visible){
					ncol.hidden = false;
					$(ncol).show();
					cb.checked = true;
				}else{
					ncol.hidden = true;
					$(ncol).hide();
					cb.checked = false;
				}
				$('tbody tr', t).each(function(){
					if(visible){
						$('td:eq(' + n + ')', this).show();
					}else{
						$('td:eq(' + n + ')', this).hide();
					}
				});
				this.rePosDrag();
				if(o.onToggleCol){
					o.onToggleCol(cid, visible);
				}
				return visible;
			},
			switchCol: function (cdrag, cdrop) { //switch columns
				$('tbody tr', t).each(
					function () {
						if (cdrag > cdrop) $('td:eq(' + cdrop + ')', this).before($('td:eq(' + cdrag + ')', this));
						else $('td:eq(' + cdrop + ')', this).after($('td:eq(' + cdrag + ')', this));
					}
				);
				//switch order in nDiv
				if (cdrag > cdrop) {
					$('tr:eq(' + cdrop + ')', this.nDiv).before($('tr:eq(' + cdrag + ')', this.nDiv));
				} else {
					$('tr:eq(' + cdrop + ')', this.nDiv).after($('tr:eq(' + cdrag + ')', this.nDiv));
				}
				if ($.browser.msie && $.browser.version < 7.0) {
					$('tr:eq(' + cdrop + ') input', this.nDiv)[0].checked = true;
				}
				this.header[0].scrollLeft = this.body[0].scrollLeft;
			},
			//////////////////////////////////////////////////////////////////////////////////
			scroll: function(){
				this.header[0].scrollLeft = this.body[0].scrollLeft;
				this.rePosDrag();
			},
			initdata: function(d){ //parse data
				if(!d){
					$('div.ppagestat', this.pdiv).html(o.errormsg);
					return false;
				}
				
				this.loading = false;
				$('div.preload', this.pdiv).removeClass('loading');
				
				//json
				if(o.dataType == 'json'){
					d = $.extend({total: 0, page: 0, rows: []}, d);
				}
				//if(o.preProcess){
				//	d = o.preProcess(d);
				//}
				
				o.total = d.total, o.page = d.page;
				if(o.total == 0){
					$('tr, td, a, div', t).unbind();
					$(t).empty();
					o.page = o.pages = 1;
					$('div.ppagestat', this.pdiv).html(o.nomsg);
					this.buildpager();
					
					return false;
				}
				
				o.pages = Math.ceil(o.total / o.rp);
				
				this.buildpager();
				
				//build html
				var tbody = $('<tbody></tbody>');
				if(o.dataType == 'json'){
					$.each(d.rows, function(i, row){
						tr = $('<tr></tr>');
						
						//Interlaced to change color
						if(o.striped && i%2){
							tr.addClass('even');
						}
						else{
							tr.addClass('odd');
						}
						//Add some custom style
						if(row.css){
							tr.addClass(row.css);
						}
						//Set row id
						if(row[o.rowid]){
							tr.attr('id', 'row' + row[o.rowid]);
						}
						
						//Traverse the cell data according to the header
						$('thead tr:first th', f.header).each(function(){
							var td = $('<td></td>'), k = this.field;
							
							if(k){
								td[0].field = k;
							}
							//Inherit the header cell text alignment
							if(this.align){
								td[0].align = this.align;
							}
							
							if(typeof row.cell != 'Undefined'){
								if(typeof row.cell[k] != 'Undefined' && row.cell[k] != null && row.cell[k] != ''){
									td.html(row.cell[k]);
								}
								else{
									td.html('Undefined');
								}
							}
							else{
								td.html('Undefined');
							}
							
							tr.append(td);
							td = k = null;
						});
						
						//handle if grid has no headers
						if($('thead', this.gdiv).length < 1 && row.cell != 'undefined'){
							$.each(row.cell, function(idx){
								var td = $('<td></td>');
								
								if(typeof row.cell[idx] != 'undefined' && row.cell[idx] != null && row.cell[k] != ''){
									td.html(row.cell[idx]);
								}
								else{
									td.html('Undefined');
								}
								
								tr.append(td);
								td = null;
							});
						}
						
						$(tbody).append(tr);
						tr = null;
					});
				}
				
				$('tr', t).unbind();
				$(t).empty();
				$(t).append(tbody);
				
				this.addCellProp();
				this.addRowProp();return false;
				
				
				this.rePosDrag();
				tbody = null;
				data = null;
				i = null;
				if (o.onSuccess) {
					o.onSuccess(this);
				}
				if (o.hideOnSubmit) {
					$(f.block).remove();
				}
				this.header.scrollLeft = this.body.scrollLeft;
				if ($.browser.opera) {
					$(t).css('visibility', 'visible');
				}
			},
			changesort: function (th) { //change sortorder
				if (this.loading) {
					return true;
				}
				
				if (o.sortname == $(th).attr('rel')) {
					if (o.sortorder == 'asc') {
						o.sortorder = 'desc';
					} else {
						o.sortorder = 'asc';
					}
				}
				$(th).addClass('sorted').siblings().removeClass('sorted');
				$('.sdesc', this.hDiv).removeClass('sdesc');
				$('.sasc', this.hDiv).removeClass('sasc');
				$('div', th).addClass('s' + o.sortorder);
				o.sortname = $(th).attr('rel');
				if (o.onChangeSort) {
					o.onChangeSort(o.sortname, o.sortorder);
				} else {
					this.populate();
				}
			},
			buildpager: function(){ //rebuild pager based on new properties
				$('.pcontrol :text', this.pdiv).val(o.page);
				$('.pcontrol span', this.pdiv).html(o.pages);
				
				var r1 = (o.page - 1) * o.rp + 1, r2 = r1 + o.rp - 1;
				if(r2 > o.total){
					r2 = o.total;
				}
				var state = o.pagestate;
				state = state.replace(/{from}/, r1);
				state = state.replace(/{to}/, r2);
				state = state.replace(/{total}/, o.total);
				$('.ppagestate', this.pdiv).html(state);
			},
			populate: function(){ //Get latest data
				if(this.loading){
					return true;
				}
				if(o.onSubmit){
					var gh = o.onSubmit();
					if(!gh){
						return false;
					}
				}
				this.loading = true;
				if(!o.url){
					return false;
				}
				
				$('div.preload', this.pdiv).addClass('loading');
				$('div.ppagestate', this.pdiv).html(o.procmsg);
				
				//this.block.css({
					//top: table.body.offset().top
				//});
				
				if(o.hideOnSubmit){
					this.body.before(this.block);
				}
				
				if(!o.newp){
					o.newp = 1;
				}
				if(o.page > o.pages){
					o.page = o.pages;
				}
				var param = [
					{ name: 'page', value: o.newp },
					{ name: 'rp', value: o.rp },
					{ name: 'sortname', value: o.sortname },
					{ name: 'sortorder', value: o.sortorder },
					{ name: 'query', value: o.query },
					{ name: 'qtype', value: o.qtype }
				];
				if(o.params.length){
					$.each(o.params, function(i){
						param[param.length] = o.params[i];
					});
				}
				$.ajax({
					type: o.method,
					url: o.url,
					data: param,
					dataType: o.dataType,
					success: function(data){
						f.initdata(data);
					},
					error: function(XMLHttpRequest, textStatus, errorThrown){
						f.initdata(d);
					}
				});
			},
			dosearch: function(){
				o.query = $('input[name=q]', f.sdiv).val();
				o.qtype = $('select[name=qtype]', f.sdiv).val();
				o.newp = 1;
				this.populate();
			},
			changePage: function (ctype) { //change page
				if (this.loading) {
					return true;
				}
				switch (ctype) {
					case 'first':
						o.newp = 1;
						break;
					case 'prev':
						if (o.page > 1) {
							o.newp = parseInt(o.page) - 1;
						}
						break;
					case 'next':
						if (o.page < o.pages) {
							o.newp = parseInt(o.page) + 1;
						}
						break;
					case 'last':
						o.newp = o.pages;
						break;
					case 'input':
						var nv = parseInt($('.pcontrol input', this.pdiv).val());
						if (isNaN(nv)) {
							nv = 1;
						}
						if (nv < 1) {
							nv = 1;
						} else if (nv > o.pages) {
							nv = o.pages;
						}
						$('.pcontrol input', this.pdiv).val(nv);
						o.newp = nv;
						break;
				}
				if (o.newp == o.page) {
					return false;
				}
				if (o.onChangePage) {
					o.onChangePage(o.newp);
				} else {
					this.populate();
				}
			},
			addCellProp: function(){
				$('tbody tr td', this.body).each(function(){
					var inner = $('<div></div>'), index = $('td', $(this).parent()).index(this);
					
					var prnt = $(this).parent()[0], id = false;
					if(prnt.id){
						id = prnt.id.substr(3);
					}
					
					var th = $('th:eq(' + index + ')', f.header);
					if(th != null){
						if(o.sortname && o.sortname == th.attr('field')){
							$(this).addClass('sorted');
						}
						inner.css({
							width: $('div:first', th).width(),
							textAlign: th.attr('align')
						});
						
						if(th[0].process){
							th.process(inner, id);
						}
					}
					inner.html($(this).html());
					$(this).empty().removeAttr('width').append(inner);
				});
			},
			getCellDim: function (obj) {// get cell prop for editable event
				var ht = parseInt($(obj).height());
				var pht = parseInt($(obj).parent().height());
				var wt = parseInt(obj.style.width);
				var pwt = parseInt($(obj).parent().width());
				var top = obj.offsetParent.offsetTop;
				var left = obj.offsetParent.offsetLeft;
				var pdl = parseInt($(obj).css('paddingLeft'));
				var pdt = parseInt($(obj).css('paddingTop'));
				return {
					ht: ht,
					wt: wt,
					top: top,
					left: left,
					pdl: pdl,
					pdt: pdt,
					pht: pht,
					pwt: pwt
				};
			},
			addRowProp: function () {
				$('tbody tr', this.body).each(function(){
					$(this).click(function(e){
						var obj = (e.target || e.srcElement);
						if(obj.href || obj.type){
							return true;
						}
						$(this).toggleClass('selected');
						if(o.singleSelect && !f.multisel){
							$(this).siblings().removeClass('selected');
							$(this).toggleClass('selected');
						}
					}).mousedown(function(e){
						if(e.shiftKey){
							$(this).toggleClass('selected');
							f.multisel = false;
							this.focus();
							f.gdiv.noselect();
						}
						if(e.ctrlKey){
							$(this).toggleClass('selected');
							f.multisel = true;
							this.focus();
						}
					}).mouseup(function(){
						if(f.multisel && !e.ctrlKey){
							f.multisel = false;
							f.gdiv.noselect(false);
						}
					}).hover(
						function(e){
							if(f.multisel && e.shiftKey){
								$(this).toggleClass('selected');
							}
						},
						function(){}
					);
					//
					if($.browser.msie && $.browser.version<7.0){
						$(this).hover(
							function(){
								$(this).addClass('hover');
							},
							function(){
								$(this).removeClass('hover');
							}
						);
					}
				});
			},
			
			combo_flag: true,
			combo_resetIndex: function(selObj)
			{
				if(this.combo_flag) {
					selObj.selectedIndex = 0;
				}
				this.combo_flag = true;
			},
			combo_doSelectAction: function(selObj)
			{
				eval( selObj.options[selObj.selectedIndex].value );
				selObj.selectedIndex = 0;
				this.combo_flag = false;
			},
			autoResizeColumn: function (obj) {
				if(!o.dblClickResize) {
					return;
				}
				var n = $('div', this.colsize).index(obj),
					$th = $('th:visible div:eq(' + n + ')', this.hDiv),
					ol = parseInt(obj.style.left),
					ow = $th.width(),
					nw = 0,
					nl = 0,
					$span = $('<span />');
				$('body').children(':first').before($span);
				$span.html($th.html());
				$span.css('font-size', '' + $th.css('font-size'));
				$span.css('padding-left', '' + $th.css('padding-left'));
				$span.css('padding-right', '' + $th.css('padding-right'));
				nw = $span.width();
				$('tr', this.bDiv).each(function () {
					var $tdDiv = $('td:visible div:eq(' + n + ')', this),
						spanW = 0;
					$span.html($tdDiv.html());
					$span.css('font-size', '' + $tdDiv.css('font-size'));
					$span.css('padding-left', '' + $tdDiv.css('padding-left'));
					$span.css('padding-right', '' + $tdDiv.css('padding-right'));
					spanW = $span.width();
					nw = (spanW > nw) ? spanW : nw;
				});
				$span.remove();
				nw = (o.minWidth > nw) ? o.minWidth : nw;
				nl = ol + (nw - ow);
				$('div:eq(' + n + ')', this.colsize).css('left', nl);
				this.colresize = {
					nw: nw,
					n: n
				};
				f.dragEnd();
			},
			pager: 0
		};
		
		
		//Init divs
		f.box = $('<div class=\'fixtbl-box\'></div>');
		
		
		
		f.ttl = $('<div class=\'fixtbl-title\'></div>');
		f.menu = $('<div class=\'fixtbl-menu\'></div>');
		f.header = $('<div class=\'fixtbl-header\'></div>');
		f.colsize = $('<div class=\'fixtbl-colsize\'></div>');
		f.body = $('<div class=\'fixtbl-body\'></div>');
		f.sdiv = $('<div rel=\'fixtbl-search\'></div>');
		f.pdiv = $('<div rel=\'fixtbl-paging\'></div>');
		f.vdiv = $('<div class=\'fixtbl-ver-resize\'></div>');
		f.hdiv = $('<div class=\'fixtbl-hor-resize\'></div>');
		f.block = $('<div class=\'fixtbl-block\'></div>');
		f.htable = $('<table cellpadding=\'0\' cellspacing=\'0\'></table>');
		f.pdiv = $('<div></div>');
		
		f.tol = $('<i class=\'tol\'></i>');
		f.tor = $('<i class=\'tor\'></i>');
		
		
		//Remove some default attributes of  the table
		$(t).show().attr({ cellpadding: 0, cellspacing: 0 }).removeAttr('class style width border');
		
		//create header if any
		if(o.thead){
			var thead = $('<thead><tr></tr></thead>');
			$.each(o.thead, function(i, col){
				var th = $('<th axis=\'col' + i + '\'></th>');
				if(col){
					if(col.text){
						th.html(col.text);
					}
					else{
						th.html('undefined').attr({ width: 100, align: 'center' });
					}
					
					if(col.width){
						th.attr('width', col.width);
					}
					
					if(col.align){
						th.attr('align', col.align);
					}
					
					if(col.field && col.sort){
						th.attr('field', col.field);
					}
				}else{
					th.html('undefined').attr({ width: 100, align: 'center' });
				}
				$('tr', thead).append(th);
			});
			$(t).prepend(thead);
		}
		
		$(t).before(f.box);
		f.box.append(t);
		
		
		return false;
		
		
		
		//set the width
		if(o.width != 'auto'){
			f.gdiv.width(o.width);
		}
		//add conditional classes
		if($.browser.msie){
			f.gdiv.addClass('ie');
		}
		else if($.browser.mozilla){
			f.gdiv.addClass('mozilla');
		}
		else if($.browser.opera){
			f.gdiv.addClass('opera');
		}
		else if($.browser.safari){
			f.gdiv.safari ('safari');
		}
		else if($.browser.webkit){
			f.gdiv.addClass('webkit');
		}
		if(o.novstripe){
			//此处添加样式为了控制表单每一列是否显示border
			f.gdiv.addClass('novstripe');
		}
		
		//Add toolbar
		if(o.buttons){
			f.menu.html('<div class=\'fixtbl-menu-inner\'></div>');
			$.each(o.buttons, function(i){
				var it = o.buttons[i];
				if(it.separator){
					$('div.fixtbl-menu-inner', f.menu).append('<div class=\'menu-separator\'></div>');
				}
				else if(it.point){
					var p = $('<div class=\'menu-point\'></div>');
					if(it.text){
						p.html(it.text);
					}
					if(it.css){
						p.addClass(it.css);
					}
					$('div.fixtbl-menu-inner', f.menu).append(p);
				}
				else{
					var b = $('<div class=\'menu-button\'><span></span></div>');
					
					//Register button text
					if(it.text){
						$('span', b).text(it.text);
					}
					else{
						$('span', b).text('Undefined');
					}
					
					//Register button id
					if(it.id){
						b.attr('id', it.id);
					}
					
					//Register button name
					if(it.name){
						b.attr('name', it.name);
					}
					
					//Register button class
					if(it.css){
						b.addClass(it.css);
					}
					
					//Register button alt
					if(it.alt){
						b.attr('title', it.alt);
					}
					
					//Register button click event
					if(it.onpress && typeof(it.onpress)=='function'){
						b[0].onpress = it.onpress;
						b.click(function(){
							this.onpress(this.id || this.name, f.gdiv);
						});
					}
					
					b.hover(
						function(){ $(this).addClass("bh"); },
						function(){ $(this).removeClass("bh ba"); }
					).mousedown(function(){
						$(this).addClass("ba");
					}).mouseup(function(){
						$(this).removeClass("ba");
					});
					
					$('div.fixtbl-menu-inner', f.menu).append(b);
				}
			});
			f.gdiv.prepend(f.menu);
		}
		
		//Add title
		if(o.title){
			f.ttl.html('<div class=\'fixtbl-ttl-inner\'></div>');
			
			if(o.title.text){
				$('div:first', f.ttl).html(o.title.text);
			}
			else{
				$('div:first', f.ttl).html('Undefined');
			}
			if(o.title.align){
				$('div:first', f.ttl).css({
					textAlign: o.title.align
				});
			}
			if(o.title.css){
				$('div:first', f.ttl).addClass(o.title.css);
			}
			
			//Whether or not to activate the stretch function
			if(o.stretch){
				$('div:first', f.ttl).click(function(){
					if(!f.gdiv.hasClass('hidefixtbl')){
						f.gdiv.addClass('hidefixtbl');
					}
					else{
						f.gdiv.removeClass('hidefixtbl');
					}
				});
			}
			
			f.gdiv.prepend(table.ttl);
		}
		
		//define a combo button set with custom action'ed calls when clicked.
		if(o.combobuttons && f.adiv2){
			var btnDiv = $('<div class="action-bar-button"></div>');
			var slt = $('<select class="select"></select>');
			slt.bind('change', function(){
				f.doselect(slt);
			});
			slt.bind('click', function(){
				f.resetindex(slt);
			});
			btnDiv.append(slt);
			$.each(o.combobuttons, function(i){
				var btn = o.combobuttons[i];
				if(!btn.separator){
					var option = $('<option></option>');
					option.text(btn.name);
					if(btn.bclass){
						//
					}
					if(btn.bimage){
						//
					}
					if(btn.title){
						option.attr('title', btn.title);
					}
					if(btn.onpress){
						option.val(btn.onpress);
					}
					slt.append(option);
				}
			});
			$('.adiv2').append(btnDiv);
		}
		
		//添加列头//////////////////////////////////////////////////////////////////////////////////////////////////////////
		f.header.html('<div class=\'fixtbl-header-inner\'><table cellpadding=\'0\' cellspacing=\'0\'></table></div>');
		var thead = $('thead:first', $(t));
		if(thead){
			$('div:first table', f.header).append(thead);
		}
		thead = null;
		$(t).before(f.header);
		$('table thead tr:first th', f.header).each(function(i){
			var div = $('<div></div>');
			div.html($(this).html()).css({
				width: this.width + 'px',
				textAlign: this.align
			});
			
			//给默认排序列的列头加载样式
			if(this.field == o.sortname){
				$(this).addClass('sorted');
				div.attr('class', o.sortorder);
			}
			
			if(this.field){
				$(this).click(function(e){
					if(!$(this).hasClass('thover')){
						return false;
					}
					var obj = (e.target || e.srcElement);
					if(obj.href || obj.type){
						return true;
					}
					f.changesort(this);
				});
			}
			
			$(this).empty().removeAttr('width').append(div);
			
			//////////////////添加列头的mousedown事件
			$(this).mousedown(function(e){
				f.dragStart('colmove', e, this);
			}).hover(
				function(){
					if(!$(this).hasClass('thmove') && !f.colresize && !f.simcell){
						$(this).addClass('thover');
					}
					
					if(this.field && this.field!=o.sortname && !f.colresize && !f.simcell){
						$('div', this).addClass(o.sortorder);
					}
					else if(this.field && this.field==o.sortname && !f.colresize && !f.simcell){
						var no = (o.sortorder == 'asc') ? 'desc' : 'asc';
						$('div', this).removeClass(o.sortorder).addClass(no);
					}
					
					if(f.simcell){
						var n = $('th', f.header).index(this);
						if(n == f.dcoln) {
							return false;
						}
						if(n < f.dcoln) {
							$(this).append(f.tol);
						}else{
							$(this).append(f.tor);
						}
						f.dcolt = n;
					}else if(!f.colresize) {
						var nv = $('th:visible', f.header).index(this);
						var onl = parseInt($('div:eq(' + nv + ')', f.colsize).css('left'));
						
						
						
						
						
						
					}
				},
				function(){
					$(this).removeClass('thover');
					
					if(this.field != o.sortname){
						$('div', this).removeClass(o.sortorder);
					}
					else if(this.field == o.sortname){
						var no = (o.sortorder == 'asc') ? 'desc' : 'asc';
						$('div', this).addClass(o.sortorder).removeClass(no);
					}
					
					if(f.simcell){
						f.tol.remove();
						f.tor.remove();
						f.dcolt = null;
					}
				}
			);
		});
		
		//set body
		f.body.css({
			height: (o.height == 'auto') ? 'auto' : o.height + "px"
		});
		f.body.scroll(function(e){
			f.scroll();
		});
		$(t).before(f.body);
		f.body.append(t);
		if(o.height == 'auto'){
			$('table', f.body).addClass('autoht');
		}
		
		//add td & row properties
		f.addCellProp();
		f.addRowProp();
		
		//set col drag line
		var cdcol = $('thead tr:first th:first', f.header).get(0);
		if(cdcol != null){
			f.body.before(f.colsize);
			
			$('thead tr:first th', f.header).each(function(){
				var l = $('<div></div>');
				$(f.colsize).append(l);
				
				if(!o.cgwidth){
					o.cgwidth = l.width();
				}
				
				l.mousedown(function(e){
					f.dragStart('colresize', e, this);
				}).dblclick(function(e){
					f.autoResizeColumn(this);
				});
				
				if($.browser.msie && $.browser.version<7.0){
					//table.fixHeight(table.gdiv.height());
					l.hover(
						function(){
							f.fixHeight();
							$(this).addClass('hover');
						},
						function(){
							if(!f.colresize){
								$(this).removeClass('hover');
							}
						}
					);
				}
			});
		}
		
		
		//Add block
		f.block.css({
			height: f.body.height() + 'px'
		});
		//table.block.fadeTo(0, o.opacity);
		
		
		//创建分页部分
		if(o.usepager){
			f.pdiv.attr('class', 'pdiv').html('<div class="pdiv-inner"></div>');
			f.body.after(f.pdiv);
			$('div', f.pdiv).html(
				'<div class=\'pgroup\'>' + 
					'<div class=\'pfirst pbutton\'>first</div>' +
					'<div class=\'pprev pbutton\'>prev</div>' +
				'</div>' +
				'<div class=\'separator\'></div>' +
				'<div class=\'pgroup\'>' +
					'<span class=\'pcontrol\'>' + o.pagetext + ' <input type=\'text\' class=\'num\' value=\'1\' /> ' + o.outofstar + ' <span>1</span> ' + o.outofend + '</span>' +
				'</div>' +
				'<div class=\'separator\'></div>' +
				'<div class=\'pgroup\'>' +
					'<div class=\'pnext pbutton\'>next</div>' +
					'<div class=\'plast pbutton\'>last</div>' +
				'</div>' +
				'<div class=\'separator\'></div>' +
				'<div class=\'pgroup\'>' +
					'<div class=\'preload pbutton\'>reload</div>' +
				'</div>' +
				'<div class=\'separator\'></div>' +
				'<div class=\'pgroup\'>' +
					'<span class=\'ppagestate\'></span>' +
				'</div>'
			);
			//首页
			$('.pfirst', f.pdiv).click(function(){
				f.changePage('first');
			});
			//上一页
			$('.pprev', f.pdiv).click(function(){
				f.changePage('prev');
			});
			//下一页
			$('.pnext', f.pdiv).click(function(){
				f.changePage('next');
			});
			//尾页
			$('.plast', f.pdiv).click(function(){
				f.changePage('last');
			});
			//刷新
			$('.preload', f.pdiv).click(function(){
				f.populate();
			});
			//跳到第几页
			$('.pcontrol input', f.pdiv).keydown(function(e){
				if(e.keyCode == 13){ f.changePage('input'); }
			});
			//兼容IE7以下版本浏览的hover效果
			if($.browser.msie && $.browser.version<7.0){
				$('.pbutton', f.pdiv).hover(
					function(){
						$(this).addClass('pbuttonhover');
					},
					function(){
						$(this).removeClass('pbuttonhover');
					}
				);
			}
			
			//Add rp control
			if(o.userp){
				var opt1 = '', sel1 = '';
				$.each(o.rpOptions, function(i){
					if(o.rp == o.rpOptions[i]){
						sel1 = 'selected=\'selected\'';
					}else{
						sel1 = '';
					}
					opt1 += '<option value=\'' + o.rpOptions[i] + '\' ' + sel1 + '>' + o.rpOptions[i] + '</option>';
				});
				$('.pdiv-inner', f.pdiv).prepend('<div class=\'pgroup\'><select name=\'rp\'>' + opt1 + '</select></div><div class=\'separator\'></div>');
				$('.pdiv-inner select[name=\'rp\']', f.pdiv).change(function(){
					if(f.onrpchange){
						f.onrpchange(+$(this).val());
					}else{
						f.newp = 1;
						f.rp = $(this).val();
						f.populate();
					}
				});
			}
			
			//Add search control
			if(o.searchitems){
				$('.pdiv-inner', f.pdiv).prepend('<div class=\'pgroup\'><div class=\'psearch pbutton\'></div></div><div class=\'separator\'></div>');
				$('.pdiv-inner .psearch', f.pdiv).click(function(){
					if(f.sdiv.is(':hidden')){
						f.sdiv.show().find(':text:first').focus();
					}else{ f.sdiv.hide(); }
				});
				
				//Add search box
				f.sdiv.attr('class', 'sdiv');
				
				var opt2 = '', sel2 = '';
				$.each(o.searchitems, function(i){
					var it = o.searchitems[i];
					if(o.qtype == '' && it.isdefault == true){
						o.qtype = it.value;
						sel2 = 'selected="selected"';
					}else{
						sel2 = '';
					}
					opt2 += '<option value="' + it.value + '" ' + sel2 + '>' + it.text + '</option>';
				});
				if(o.qtype == ''){
					o.qtype = o.searchitems[0].value;
				}
				f.sdiv.html('<div class=\'sdiv-inner\'>' + o.findtext + ' <input type=\'text\' name=\'q\' value="' + o.query + '" size="30" class="querybox" /> <select name="qtype">' + opt2 + '</select></div>');
				//Split into separate selectors because of bug in jQuery 1.3.2
				$('input[name="q"]', f.sdiv).keydown(function(e){
					if(e.keyCode == 13){
						f.dosearch();
					}
				});
				$('select[name="qtype"]', f.sdiv).keydown(function(e){
					if(e.keyCode == 13){
						f.dosearch();
					}
				});
				$('input[value="clear"]', f.sdiv).click(function(){
					$('input[name="q"]', f.sdiv).val('');
					o.query = '';
					f.dosearch();
				});
				f.body.after(f.sdiv);
			}
		
			$('.pdiv-inner', f.pdiv).append('<div class="clear"></div>');
		}
		
		//Add the bottom of the drag mark
		if(o.resizable && o.height != 'auto'){
			f.gdiv.append(f.vdiv);
			
			f.vdiv.mousedown(function(e){
				f.dragStart('tblresize', e);
			});
		}
		//Add the right of the drag mark
		if(o.resizable && o.width != 'auto' && !o.nohresize){
			f.gdiv.append(f.hdiv);
			f.hdiv.height(f.gdiv.height());
			
			f.hdiv.mousedown(function(e){
				f.dragStart('tblresize', e, true);
			});
		}
		
		
		//Add table events
		f.body.hover(
			function(){},
			function(){
				if(f.multisel){
					f.multisel = false;
				}
			}
		);
		
		//Add document events
		$(document).mousemove(function(e){
			f.dragMove(e);
		}).mouseup(function(e){
			f.dragEnd();
		}).hover(
			function(){},
			function(){
				f.dragEnd();
			}
		);
		
		//Browser adjustments
		if($.browser.msie && $.browser.version<7.0){
			$('.fixtbl-ttl, .fixtbl-menu, .fixtbl-header, .fixtbl-body, .sdiv, .pdiv, .vdiv', f.gdiv).css({
				width: '100%'
			});
			f.gdiv.addClass('ie6');
			if(o.width != 'auto'){
				f.gdiv.addClass('ie6fullwidthbug');
			}
		}
		
		f.fixHeight();
		f.rePosDrag();
		
		//Load data
		if(o.url && o.autoload){
			f.populate();
		}
		
		//Make grid functions accessible
		t.o = o;
		t.exist = table;
		
		return t;
	};
	
	
	
	
	
	
	//此变量用于监控页面是否加载完成，默认值为false，表示未加载完成
	var isloaded = false;
	
	//页面加载完成后改变变量isloaded值为true
	$(function(){
		isloaded = true;
	});
	
	//执行插件
	$.fn.table = function(o){
		return this.each(function(){
			//页面未加载完成
			if(!isloaded){
				//隐藏表格
				$(this).hide();
				
				//待页面加载完成后执行插件
				var t = this;
				$(function(){
					$.builder(t, o);
				});
			}
			//页面加载完成
			else{
				//执行插件绘制
				$.builder(this, o);
			}
		});
	};
	
	
	
	
	
	
	$.fn.tableReload = function(o){
		return this.each(function(){
			if(this.grid && this.o.url) this.grid.populate();
		});
	};
	$.fn.tableOptoins = function(o){
		return this.each(function(){
			if(this.grid) $.extend(this.o, o);
		});
	};
	$.fn.tableToggleCol = function(cid, visible){
		return this.each(function(){
			if(this.grid) this.grid.toggleCol(cid, visible);
		});
	};
	$.fn.adddata = function(data){ //function to add data to table
		return this.each(function(){
			if(this.grid) this.grid.adddata(data);
		});
	};
	$.fn.noselect = function(o) { //no select plugin by me :-)
		var prevent = (o==null) ? true : o;
		if(prevent){
			return this.each(function(){
				if($.browser.msie || $.browser.safari){
					$(this).bind('selectstart', function(){
						return false;
					});
				}else if($.browser.mozilla){
					$(this).css('MozUserSelect', 'none');
					$('body').trigger('focus');
				}else if($.browser.opera){
					$(this).bind('mousedown', function(){
						return false;
					});
				}else{ $(this).attr('unselectable', 'on'); }
			});
		}else{
			return this.each(function () {
				if ($.browser.msie || $.browser.safari) $(this).unbind('selectstart');
				else if ($.browser.mozilla) $(this).css('MozUserSelect', 'inherit');
				else if ($.browser.opera) $(this).unbind('mousedown');
				else $(this).removeAttr('unselectable', 'on');
			});
		}
	}; //end noSelect
	$.fn.flexSearch = function(o) { // function to search grid
		return this.each(function(){ if(this.grid && this.o.searchitems) this.grid.doSearch(); });
	}; //end flexSearch
})(jQuery);







