/**
 * js
 */

$(function () {
	//alert("init");
	initialPage();
	//getGrid();
});

function initialPage() {
	
	var groupId = localStorage.getItem("currentGroupId");
	var literatureId = getQueryString("literatureId");
	//alert("页面初试化加载POSTIL和POSTILOBJECT");
	$.ajax({
		type: 'POST',
		url: '../../kdecm/postil/loadPostil?groupId='+groupId+'&literatureId='+literatureId,
		contentType: 'application/json',
		//async: false,
		//dataType: 'text',
		success:function(e){
			//alert(e.postilList.length);
			//alert(e.postilObjectList);
			loaderList(e.postilList,e.postilObjectList);
			//loadPostilObject(e.postilObjectList);
		}
	});
}


function loaderList(postilList,postilObjectList) {
	var $list = $(".list");
	$list.children().remove();
	//alert(postilList.toString());
	var postilObjectStart = 0;
	var postilObjectEnd = 0;
	$.each(postilList, function(i, value) {
		
		//先判断它是第几段的
		var text = document.getElementById(1);
		
		/*var range;
		var selection;
		if(document.body.createTextRange) {
			range = document.body.createTextRange();
			range.moveToElementText(text);
			range.select();
		} else if(window.getSelection) {
			selection = window.getSelection();
			range = document.createRange();
			selection.removeAllRanges();
			
			range.selectNodeContents(text);
			
			alert("startOffset="+postilObjectList[i].postilObjectStart);
			alert("endOffset="+postilObjectList[i].postilObjectEnd);
			//childNodes[i]
			range.setStart(text.childNodes[i], postilObjectList[i].postilObjectStart); // 设置range的“起点”
			range.setEnd(text.lastChild, postilObjectList[i].postilObjectEnd);
			//selection.addRange(range);
			
		}
		
		if(range){
			alert("index="+i);
			//alert("startOffset="+range.startOffset);
			//alert("endOffset="+range.endOffset);
			
			var selected = range.extractContents().textContent;
			//var selected = postilObjectList[i].postilObjectText;
			alert("selected="+selected);
			
			var text = "[ins id='" + value.postilForid + "' comment='" + value.postilContent + "']" + selected + "[/ins]";
			var textNode = document.createTextNode(text);
			range.insertNode(textNode);
			
			var content = $(".content").html();
			//alert("content="+content);
			var reg = /\[ins id='(\d*)' comment='([\w\W]*)']([\w\W]*)\[\/ins]/gi;
			reg.test(content);
			var id = RegExp.$1,
			comment = RegExp.$2,
			c = RegExp.$3;
			var reHtml = "<ins value='' id='"+id+"' comment='"+comment+"' class='postil' >"+c+"</ins>";
			content = content.replace(reg, reHtml);
			$(".content").html(content);
			range.detach();
		}*/
		
		//alert("value="+value.postilObjectId);
		
		//var content = value.postilContent;
		
		//var currenttime = value.postilTime;
		
		//在这里所有的$(b).get(0).id 都应该改成  value.postilForid
		
		var $postil = $("<div class='posList' forid='" +value.postilForid+ "'>" +
					//"<input type='hidden' class='currentUser' value=''/>" +
					"<input type='hidden' class='postilId' value='" +value.postilId+ "'/>" +
					"<span class='removeWin' onClick='removePostil(this)'>    |  　　x</span>" +
					"<span class='currentTime' id='currentTime' >" + value.postilTime + "</span>" +
					"<select class=\"colorSelect\" style=\"background-color:#996666;\" onchange=\"changeColor(this,this.options[this.options.selectedIndex].value)\"><option style=\"background-color: #0099FF\" value=\"#0099FF\"></option><option style=\"background-color: #00CC66\" value=\"#00CC66\"></option><option style=\"background-color: #CC0033\" value=\"#CC0033\"><option style=\"background-color: #996666\" value=\"#996666\"><option style=\"background-color: #FFCC33\" value=\"#FFCC33\"><option style=\"background-color: #FF6EB4\" value=\"#FF6EB4\"><option style=\"background-color: #B23AEE\" value=\"#B23AEE\"></select>"+
					"<div class='posContent' contenteditable='true' forid='"+value.postilForid+"'>" + value.postilContent + "</div>" +
					/*"<textarea class='posContent' forid='"+$(b).get(0).id+"'>" + content + "</textarea>" +*/
					"</div>");
		
		$postil.hover(function() {
				$(this).css("border-color", "#CD5454");
				$("#" + $(this).attr("forid") + "").removeClass().addClass("postilFocus");
			},
		function() {
				$(this).css("border-color", "#ddd");
				$("#" + $(this).attr("forid") + "").removeClass().addClass("postil");
			});
		/*$(".content #"+value.postilForid).hover(function() {
				$(this).removeClass().addClass("postilFocus");
				$("div[forid='" + value.postilForid + "']").css("border-color", "red");
			},
		function() {
				$(this).removeClass().addClass("postil");
				$("div[forid='" + value.postilForid + "']").css("border-color", "#ddd");
			});*/
		
		$list.append($postil);
		
		//绑定修改事件  
		//暂时没有 但是这里需要改变呢ins中的值  能不能改看我心情
		//var this_ins = $(this);
		/*var pos = $("div[forid='" + value.postilForid + "'] .posContent");
		pos.blur(function(){
			var postil = $(this).text();
			
			//this_ins.attr("comment",postil);
			
			var postilId = $("div[forid='" + value.postilForid + "'] .postilId").val();
			
			var posUpdate = {'postilId':postilId,'postilContent':postil};
			posUpdate = JSON.stringify(posUpdate);
			
			$.ajax({
				type: 'POST',
				url: '../../kdecm/postil/update',   //?userId=1
				contentType: 'application/json',
				data: posUpdate,
				success:function(e){
					if(e.code==0){
						alert("这个要更新+success");
						//func = "update";
					}
				}
			});
		});*/		
	//  this; this指向当前元素
	//  i; i表示Array当前下标
	//  value; value表示Array当前元素

	});
}

function getQueryString(name) {
	var result = window.location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
	if (result == null || result.length < 1) {
    	return "";
	}
	return result[1];
}

function loadPostilObject(postilObjectList){
	//IE支持的range对象
	//var ie_range;
	//其他浏览器的range对象
	//var other_range;
	
	/*$.each(postilObjectList, function(i, value) {
		alert(i);
		var range = value.postilObjectText;
		if(range){
			var selected = range.extractContents().textContent;
			alert("selected="+selected);
			var text = "[ins id='" + (new Date().getTime()) + "' comment='" + value + "']" + selected + "[/ins]";
		}
	});*/
	
	/*if(other_range) {
		var selected = other_range.extractContents().textContent;
		
		var text = "[ins id='" + (new Date().getTime()) + "' comment='" + value + "']" + selected + "[/ins]";
		//alert("text="+text);
		var textNode = document.createTextNode(text);
		other_range.insertNode(textNode);
		var content = $(".content").html();
		alert("content="+content);
		var reg = /\[ins id='(\d*)' comment='([\w\W]*)']([\w\W]*)\[\/ins]/gi;
		//alert("result="+reg.test(content));
		reg.test(content);
		var id = RegExp.$1,
			comment = RegExp.$2,
			c = RegExp.$3;
		alert("id="+id+"  comment"+comment+"   c="+c);	
		var reHtml = "<ins value='222' id='" + id + "' comment='" + comment + "' class='postil' >" + c + "</ins><input type='hidden' value='1'>";
		content = content.replace(reg, reHtml);
		alert("content="+content);
		$(".content").html(content);
	} else if(ie_range) {
		ie_range.pasteHTML("<ins comment='" + value + "' class='postil' id='" + new Date().getTime() + "'>" + ie_range.htmlText + "</ins>");
		ie_range = null;
	}*/
}
/*function getGrid() { 
	$('#dataGrid').bootstrapTableEx({
		url: '../../kdecm/postil/list?_' + $.now(),
		height: $(window).height()-56,
		queryParams: function(params){
			params.name = vm.keyword;
			return params;
		},
		columns: [
			{checkbox: true},
			{field : "postilId", title : "", width : "100px"}, 
			{field : "userId", title : "", width : "100px"}, 
			{field : "postilObjectId", title : "", width : "100px"}, 
			{field : "postilTime", title : "", width : "100px"}, 
			{field : "postilContent", title : "", width : "100px"}, 
			{field : "postilType", title : "", width : "100px"}, 
			{field : "postilOpenness", title : "", width : "100px"}
		]
	})
}*/

/*var vm = new Vue({
	el:'#dpLTE',
	data: {
		keyword: null
	},
	methods : {
		load: function() {
			$('#dataGrid').bootstrapTable('refresh');
		},
		save: function() {
			dialogOpen({
				title: '新增',
				url: 'kdecm/postil/add.html?_' + $.now(),
				width: '420px',
				height: '350px',
				yes : function(iframeId) {
					top.frames[iframeId].vm.acceptClick();
				},
			});
		},
		edit: function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections');
			if(checkedRow(ck)){
				dialogOpen({
					title: '编辑',
					url: 'kdecm/postil/edit.html?_' + $.now(),
					width: '420px',
					height: '350px',
					success: function(iframeId){
						top.frames[iframeId].vm.postil.postilId = ck[0].postilId;
						top.frames[iframeId].vm.setForm();
					},
					yes: function(iframeId){
						top.frames[iframeId].vm.acceptClick();
					}
				});
			}
		},
		remove: function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections'), ids = [];	
			if(checkedArray(ck)){
				$.each(ck, function(idx, item){
					ids[idx] = item.postilId;
				});
				$.RemoveForm({
					url: '../../kdecm/postil/remove?_' + $.now(),
			    	param: ids,
			    	success: function(data) {
			    		vm.load();
			    	}
				});
			}
		}
	}
})*/