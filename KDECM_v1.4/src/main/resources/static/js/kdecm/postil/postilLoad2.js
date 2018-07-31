/**
 * js
 */

$(function () {
	initialPage();
	//getGrid();
});

function initialPage() {
	
	var groupId = localStorage.getItem("currentGroupId");
	//alert("chapter="+chapter);
	if(groupId==null||groupId==""){
		alert("请先选组，然后进入文献管理查看文献");
		return false;
	}
	var literatureId = getQueryString("literatureId");
	
	$.ajax({
		type: 'POST',
		url: '../../kdecm/postil/loadPostil?groupId='+groupId+'&literatureId='+literatureId,
		contentType: 'application/json',
		//async: false,
		//dataType: 'text',
		success:function(e){
			//loaderStyleList(e.postilObjectList);

			if(e.result==true){
				loaderList(e.postilList,e.postilObjectList);
			}else{
				return false;
			}
			//loadPostilObject(e.postilObjectList);
			
			
		}
	});
	
	
}


function loaderList(postilList,postilObjectList) {
	var $list = $(".list");
	$list.children().remove();

	var postilObjectStart = 0;
	var postilObjectEnd = 0;
	$.each(postilList, function(i, value) {
		
		//alert("load");
		//2018-6-25 获得object 位置坐标
		var postion = postilObjectList[i].postilObjectPos;
		var pos = postion.split(",");
		var chapter = getQueryString("chapter");
		if(chapter==pos[4]){
			var start = postilObjectList[i].postilObjectStart;
			var end = postilObjectList[i].postilObjectEnd;
			//createPostilObjectList(value.postilForid,pos[0],pos[1],pos[2],pos[3]);
			createSelectPostilObjectList(value.postilForid,start,end,value.postilContent,postilObjectList[i].postilObjectText);
			
			/*$("." + forid + ":eq(0)" + " .postion").val(start + " " + end);
			$("." + forid + ":eq(0)" + " .postion").html(endPos.x + " " + endPos.y);
			$("."+value.postilForid+" input:eq(0)").val(value.postilContent);
			$("."+value.postilForid+" input:eq(0)").attr("name",postilObjectList[i].postilObjectText);*/
			
			var $postil = $("<div class='posList' forid='" +value.postilForid+ "'>" +

						"<input type='hidden' class='postilId' value='" +value.postilId+ "'/>" +
						"<span class='removeWin' onClick='removePostil(this)'>    |  　　x</span>" +
						"<span class='currentTime' id='currentTime' >" + value.postilTime + "</span>" +
						"<select class=\"colorSelect\" style=\"background-color:#996666;\" onchange=\"changeColor(this,this.options[this.options.selectedIndex].value)\"><option style=\"background-color: #0099FF\" value=\"#0099FF\"></option><option style=\"background-color: #00CC66\" value=\"#00CC66\"></option><option style=\"background-color: #CC0033\" value=\"#CC0033\"><option style=\"background-color: #996666\" value=\"#996666\"><option style=\"background-color: #FFCC33\" value=\"#FFCC33\"><option style=\"background-color: #FF6EB4\" value=\"#FF6EB4\"><option style=\"background-color: #B23AEE\" value=\"#B23AEE\"></select>"+
						"<div class='posContent' contenteditable='true' forid='"+value.postilForid+"'>" + value.postilContent + "</div>" +
						"</div>");
			
			$postil.hover(function() {
					$(this).css("border-color", "#CD5454");
					$.each($("."+value.postilForid),function(a, b){
						$(b).removeClass("postilObjectList").addClass("postilFocus");
					});
				},
				function() {
					$(this).css("border-color", "#ddd");
					$.each($("."+value.postilForid),function(a, b){
						$(b).removeClass("postilFocus").addClass("postilObjectList");
					});			
				});
			
			$list.append($postil);
			
			var pos = $("div[forid='" + value.postilForid + "'] .posContent");
			pos.blur(function(){
				var postil = $(this).text();
				
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
							//alert("批注修改成功");
							//func = "update";
						}
					}
				});	
			});
		}
	
	//  this; this指向当前元素
	//  i; i表示Array当前下标
	//  value; value表示Array当前元素

	});
}

function loaderStyleList(postilObjectList) {

	var postilObjectStart = 0;
	var postilObjectEnd = 0;
	$.each(postilObjectList, function(i, value) {
		
		//2018-6-25 获得object 位置坐标
		var postion = postilObjectList[i].postilObjectPos;
		var style = postilObjectList[i].postilObjectText;
		//alert(style);
		var type;
		var pos = postion.split(",");
		var chapter = getQueryString("chapter");
		switch(style)
		{
		case 1101:
 		 type = 1;
 		 	break;		
		case 1102:
		type = 2;
			break;
		case 1103:
		type = 3;
			break;  
		case 1104:
		type = "";
			break; 
		default:
			break;
		}
		if(chapter==pos[1]){
			
			var start = postilObjectList[i].postilObjectStart;
			var end = postilObjectList[i].postilObjectEnd;
			if(type == "" || type == null){
				return;
			}else{
				createSelectStyleObjectList(pos[0],start,end,value.postilContent,postilObjectList[i].postilObjectText,type);
			}
			
						
		}
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