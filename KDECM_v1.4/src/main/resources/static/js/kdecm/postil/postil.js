/**
 * @作者：WilliamSha
 * @时间：2011-10-18 下午07:52:01
 * @项目名：XXX
 * @描述：操作批注工具
 */
//公共的不断更新的批注对象ID
var postilObjectId=0;
var func = "insert";
//初始化页面元素
$(function() {
	var selectedText;
	var start=0;
	var end=0;
	//var postilObjectRowsStart = 0;
	//var postilObjectRowEnd = 0;
	//var literatureId = 1;
	$(".content").mouseup(function(e) {
	
		var regu = "^[ ]+$";
		var re = new RegExp(regu);
		
		if(window.getSelection) {
			selectedText = window.getSelection().toString();
			if(selectedText.length>0&&re.test(selectedText)==false) {
				//这是干啥的我也不知道
            	/*var selectAnchorNode = selection.anchorNode;
            	var selectFocusNode = selection.focusNode;
            	var contentObj = document.getElementById("content");
            	var contentChiles = contentObj.getElementsByTagName("span");
            	var starNode = 0;
            	var endNode = 0;*/
            	var selectObj = window.getSelection();
				start = selectObj.anchorOffset;
				end = selectObj.focusOffset;
				
				/*2018-6-2 13:14 动态添加批注对象 */  
				/*注意这里的literatureId 是直接写定的*/
				/*2018-6-3 16:04 这里还是有问题的，即使滑动了也不能马上存*/
				/*var posObjDef = {'postilObjectText':selectedText,'postilObjectRowsStart':1,'postilObjectStart':start,'postilObjectRowEnd':1,'postilObjectEnd':end,'literatureId':1};
				posObjDef = JSON.stringify(posObjDef);
				$.ajax({
					type: 'POST',
					url: '../../kdecm/postil_object/insert',   //?userId=1
					contentType: 'application/json',
					//dataType: 'text',
					data: posObjDef,
					success:function(e){
						if(e.code==0){
							postilObjectId = e.postilObjectId
						}
						alert("success");
					}
				});*/
          }
		} else if(document.selection && document.selection.createRange) {
			selectedText = document.selection.createRange().text;
			if(selectedText.length>0&&re.test(selectedText)==false) {
            	var selectObj = window.getSelection();
				start = selectObj.anchorOffset;
				end = selectObj.focusOffset;
          }
		}
		if(selectedText&&selectedText.length > 0&&re.test(selectedText)==false) {
			$("#icon").css({
				"left": e.clientX + 1,
				"top": e.offsetY

			}).fadeIn(300);
			$("#icon1").css({
				"left" : e.clientX+31,
				"top" : e.offsetY
				

			}).fadeIn(300);
			$("#icon2").css({
				"left" : e.clientX+61,
				"top" : e.offsetY

			}).fadeIn(300);
			$("#icon3").css({
				"left" : e.clientX+91,
				"top" : e.offsetY

			}).fadeIn(300);
		} else {
			$("#icon").hide();
			$("#icon1").hide();
			$("#icon2").hide();
			$("#icon3").hide();
		}
	});
	$("#icon").hover(function() {
			$(this).children().removeClass("tipsIcon");
		},
		function() {
			$(this).children().addClass("tipsIcon");
		}).click(function() {
			$("#icon").hide();
			$("#icon1").hide();
			$("#icon2").hide();
			$("#icon3").hide();
			addPostil(selectedText,1,start,1,end,1);
		});

	$("#icon1").hover(function(){
			$(this).children().removeClass("tipsIcon1");
		},
		function(){
			$(this).children().addClass("tipsIcon1");
		}).click(function() {
			$("#icon").hide();
			$("#icon1").hide();
			$("#icon2").hide();
			$("#icon3").hide();
			Changestyle(1);
		});

	$("#icon2").hover(function(){
		$(this).children().removeClass("tipsIcon2");
		},
		function(){
			$(this).children().addClass("tipsIcon2");
		}).click(function() {
			$("#icon").hide();
			$("#icon1").hide();
			$("#icon2").hide();
			$("#icon3").hide();
			Changestyle(2);
	});

	$("#icon3").hover(function(){
			$(this).children().removeClass("tipsIcon3");
		},
		function(){
			$(this).children().addClass("tipsIcon3");
		}).click(function() {
			$("#icon").hide();
			$("#icon1").hide();
			$("#icon2").hide();
			$("#icon3").hide();
			Changestyle(3);
		});
});
//添加批注
//function addPostil() {
function addPostil(selectedText,postilObjectRowsStart,start,postilObjectRowEnd,end,literatureId) {
	
	//IE支持的range对象
	var ie_range;
	//其他浏览器的range对象
	var other_range;
	if(window.getSelection) {
		other_range = window.getSelection().getRangeAt(0);
	} else if(document.selection && document.selection.createRange) {
		ie_range = document.selection.createRange();
	}

	/*-------------------------------------------分割线--------------------------------------------------*/
	/**
	 * 添加批注推荐
	 */
	var recommendresult;
	$.ajax({
		type : 'POST',
		url : '../../kdecm/postil/recommend?selectedText=' + selectedText, // ?userId=1
		contentType : 'application/json',
		async: false,
		success : function(e) {
			if (e) {
				recommendresult = e;
			}
		}
	});
	/**
	 * 对话框修改
	 */
	var table = "<table id=\"postilrec\" class=\"postilrecommend\"><tr><th>批注原文</th><th>批注推荐</th></tr>";
	var length = recommendresult.length;
	if(length >= 5){
		length = 5;
	}
	for(var i=0; i < length ;i++){
		table += "<tr><td>"+recommendresult[i].postilObjectText+"</td><td>"+recommendresult[i].postilContent+"</td></tr>";
		//"<hr style=\"height:1px;border:none;border-top:1px solid #555555;\"/>";
	}
	table+="</table>";
	var str = '<textarea id="postil" rows="10" cols="55"></textarea>'+table;
	
	/*-------------------------------------------分割线--------------------------------------------------*/

	art.dialog({
		id: 'inputDialog',
		title: '添加批注',
		content: str,
		lock: true

	}, function() {
		var value = document.getElementById("postil").value;
		if(!value) {
			art.dialog({
				content: '批注内容不能为空！',
				time: 1
			});
			return false;
		}
		if(other_range) {
			/*
			//IE之外的浏览器，如果在选择内容包含其他标签的一部分的时候会报异常
			var mark = document.createElement("ins");
			mark.setAttribute("comment", value);
			mark.className = "postil";
			mark.id=new Date().getTime();
			other_range.surroundContents(mark);
			*/
			var selected = other_range.extractContents().textContent;
			var text = "[ins id='" + (new Date().getTime()) + "' comment='" + value + "']" + selected + "[/ins]";
			var textNode = document.createTextNode(text);
			other_range.insertNode(textNode);
			var content = $(".content").html();
			var reg = /\[ins id='(\d*)' comment='([\w\W]*)']([\w\W]*)\[\/ins]/gi;
			reg.test(content);
			var id = RegExp.$1,
				comment = RegExp.$2,
				c = RegExp.$3;
			//var reHtml = "<ins id='" + id + "' comment='" + comment + "' class='postil' >" + c + "</ins>";
			var reHtml = "<ins value='' id='"+id+"' comment='"+comment+"' class='postil' >"+c+"</ins>";
			content = content.replace(reg, reHtml);
			$(".content").html(content);
		} else if(ie_range) {
			ie_range.pasteHTML("<ins comment='" + value + "' class='postil' id='" + new Date().getTime() + "'>" + ie_range.htmlText + "</ins>");
			ie_range = null;
		}
		
		//2018-6-6改，不是所有的批注对象都需要存储，只有在选中并填写批注点击OK后，才会去生成
		var literatureId = getQueryString("literatureId");
		//alert("literatureId="+literatureId);
		var posObjDef = {'postilObjectText':selectedText,'postilObjectRowsStart':1,'postilObjectStart':start,'postilObjectRowEnd':1,'postilObjectEnd':end,'literatureId':literatureId};
		posObjDef = JSON.stringify(posObjDef);
		$.ajax({
			type: 'POST',
			url: '../../kdecm/postil_object/insert',   //?userId=1
			contentType: 'application/json',
			async: false,
			//dataType: 'text',
			data: posObjDef,
			success:function(e){
				if(e.code==0){
					postilObjectId = e.postilObjectId
				}
				//alert("改进的批注对象插入 success");
			}
		});
		loader();
	});
}

function Changestyle(type) {
	var style;
	switch(type)
{
    case 1:
        style = 'span class="delete_line"';
        break;
    case 2:
        style = 'span class="under_line"';
        break;
    default:
        style = 'span class="highlight"'
}

	var ie_range;
	//其他浏览器的range对象
	var other_range;
	if(window.getSelection) {
		other_range = window.getSelection().getRangeAt(0);
	} else if(document.selection && document.selection.createRange) {
		ie_range = document.selection.createRange();
	}

	if(other_range) {
			/*
		//IE之外的浏览器，如果在选择内容包含其他标签的一部分的时候会报异常
		var mark = document.createElement("ins");
		mark.setAttribute("comment", value);
		mark.className = "postil";
		mark.id=new Date().getTime();
		other_range.surroundContents(mark);
			*/
		var docFragment = other_range.cloneContents();
		other_range.extractContents();
		
		var tempDiv = document.createElement("div");
		tempDiv.appendChild(docFragment);
		selected = tempDiv.innerHTML;
		//alert(selected);

		var text = '<'+style+'>'+ selected + '</'+style+'>'
		var textNode = document.createTextNode(text);
		other_range.insertNode(textNode);
		var divText = $(".content").html();
		divText=divText.replace(/&lt;/gi,"<").replace(/&gt;/gi,">")
		//alert(divText)
		//divText.replace(reg,'<').replace(reg,'>')
		$(".content").html(divText);

	} else if(ie_range) {
		oHtml = "<"+style+">"+ ie_range.htmlText + "</"+style+">"
		ie_range.pasteHTML(oHtml);
		ie_range=null;
	}

 }

//解析批注
/* 2018/5/29 9:52 滑动按钮添加 */
function loader() {
	var $list = $(".list");
	$list.children().remove();
	
	$.each($(".content ins"), function(a, b) {
		
		var content = $(b).attr("comment");
		
		var currenttime = CurentTime();
		
		var $postil = $("<div class='posList' forid='" +$(b).get(0).id+ "'>" +
					"<input type='hidden' class='postilId' value=''/>" +
					"<span class='removeWin' onClick='removePostil(this)'>    |  　　x</span>" +
					"<span class='currentTime' id='currentTime' >" + currenttime + "</span>" +
					"<select class=\"colorSelect\" style=\"background-color:#996666;\" onchange=\"changeColor(this,this.options[this.options.selectedIndex].value)\"><option style=\"background-color: #0099FF\" value=\"#0099FF\"></option><option style=\"background-color: #00CC66\" value=\"#00CC66\"></option><option style=\"background-color: #CC0033\" value=\"#CC0033\"><option style=\"background-color: #996666\" value=\"#996666\"><option style=\"background-color: #FFCC33\" value=\"#FFCC33\"><option style=\"background-color: #FF6EB4\" value=\"#FF6EB4\"><option style=\"background-color: #B23AEE\" value=\"#B23AEE\"></select>"+
					"<div class='posContent' contenteditable='true' forid='"+$(b).get(0).id+"'>" + content + "</div>" +
					"</div>");
		

		$postil.hover(function() {
				$(this).css("border-color", "#CD5454");
				$("#" + $(this).attr("forid") + "").removeClass().addClass("postilFocus");
			},
			function() {
				$(this).css("border-color", "#ddd");
				$("#" + $(this).attr("forid") + "").removeClass().addClass("postil");
			});
		$(b).hover(function() {
				$(this).removeClass().addClass("postilFocus");
				$("div[forid='" + $(this).get(0).id + "']").css("border-color", "red");
			},
			function() {
				$(this).removeClass().addClass("postil");
				$("div[forid='" + $(this).get(0).id + "']").css("border-color", "#ddd");
			});
			
		/*.posContent.hover(function(){
			$(this).background = #EDEDED;
		})*/	
		
		$list.append($postil);
		
		//2018-6-3 17:23 通过索引值来判断这个批注是否要插入     对5-31日的修改
		var length = $(".content ins").length - 1;
		
		if(a == length&&func == "insert") {
			
			//2018/5/31 10:32 生成页面之后 AJAX动态添加数据库     
			//var id = $(this).attr("id");
			var id = $(b).get(0).id;
			var currentTimeDef = $("div[forid='" + id + "'] #currentTime").text();
			var posContentDef = $("div[forid='" + id + "'] .posContent").text();
			var opennessDef = 0;
			var currentUserId = $(".currentUserId").val();
			//alert("currentUserId="+currentUserId);
			/* 2018-6-20  改*/
			var groupId = localStorage.getItem("currentGroupId");
			
			var posDef = {'userId':currentUserId,'postilObjectId':postilObjectId,'postilContent':posContentDef,'postilTime':currentTimeDef,'postilOpenness':opennessDef,'groupId':groupId,'postilForid':id};
			posDef = JSON.stringify(posDef);
			
			$.ajax({
				type: 'POST',
				url: '../../kdecm/postil/insert',   //?userId=1
				contentType: 'application/json',
				async: false,
				//dataType: 'text',
				data: posDef,
				success:function(e){
					if(e.code==0){
						//2018-6-9 改
						alert("批注插入成功");
						$(b).attr("value",e.postilId);
						$("div[forid='" + id + "'] .postilId").val(e.postilId);
					}
					
				}
			});
		}
		
		//2018-6-4 23:14改  增加额外存储postilId的地方 
		 
		var pi = $("div[forid='" + $(b).get(0).id + "'] .postilId").val();
		
		if(pi == ""||pi == null||pi == undefined){
			//var postileId = $(b).next().val();
			var postileId = $(b).attr("value");
			$("div[forid='" + $(b).get(0).id + "'] .postilId").val(postileId);
		}
		
		// 2018-6-3 15:44 当批注改变，自动获取并保存
		var this_ins = $(this);
		var pos = $("div[forid='" + $(this).get(0).id + "'] .posContent");
		pos.blur(function(){
			var postil = $(this).text();
			this_ins.attr("comment",postil);
			
			var id = $(this).attr("forid");
			
			var postilId = $("div[forid='" + id + "'] .postilId").val();
			
			var posUpdate = {'postilId':postilId,'postilContent':postil};
			posUpdate = JSON.stringify(posUpdate);
			
			$.ajax({
				type: 'POST',
				url: '../../kdecm/postil/update',   //?userId=1
				contentType: 'application/json',
				//dataType: 'text',
				data: posUpdate,
				success:function(e){
					if(e.code==0){
						//alert("批注修改成功");
						//func = "update";
					}
				}
			});
		});
		
		//$('input[name="my-checkbox"]').bootstrapSwitch('state', true, true);
		/*$('input[name="my-checkbox"]').bootstrapSwitch({
			onText: "不公开",
			offText: "公开",
			onColor: "primary",
			offColor: "default",
			size: "mini",
			//inverse: true,
			onInit: function(event, state) {
				
			},
			onSwitchChange: function(event, state) {
				console.log(this); // DOM element
				console.log(event); // jQuery event
				console.log(state); // true | false
				//初始化时候state=true，这里批注默认是不公开，对应openness=0；
				var openness = 0;
				if(state==false){
					openness = 1; 
				}*/
				
				//var id = $(this).attr("id");
				//var currentTime = $("div[forid='" + id + "'] #currentTime").text();
				//var posContent = $("div[forid='" + id + "'] .posContent").text(); 
				//alert(${sessionScope.sys.userId});"userId=1.0"+
				//var pos = "userId=1"+"&postilObjectId=1"+"&postilTime="+currentTime+"&postilContent="+posContent+"&postilOpenness="+openness;
				//var pos = {'userId':1,'postilObjectId':1,'postilContent':posContent,'postilTime':currentTime,'postilOpenness':openness};
				//var pos1 = JSON.stringify(pos);
				
				//2018/5/31 肖凌云  当公开按钮点击，自由切换公开和不公开，更新postil表
				/*$.ajax({
					type: 'POST',
					url: '../../kdecm/postil/save',   //?userId=1
					contentType: 'application/json',
					//dataType: 'text',
					data: {'postilOpenness':openness},
					success:function(e){
						//alert(e);
						alert("success");
					}
				});*/
			/*}		
		});	*/
		
	});
	func = "insert";
}


//删除批注
function removePostil(arg) {
	var $div = $(arg).parent();
	var id = $div.attr("forid");
	var $source = $("#" + id);
	var text = $source.after($source.html());

	func = "remove";
	var id = $(arg).prev().val();
	//var id = $(arg).attr("value");
	$.ajax({
		type: 'POST',
		url: '../../kdecm/postil/delete?postilId='+id,   //?userId=1
		//contentType: 'application/json',
		dataType: 'json',
		success:function(e){
			if(e.code==0){
				alert("批注删除成功");
			}
		}
	});
	$source.remove();
	loader();
}

//2018/5/29 8:59 生成当前日期 肖凌云
function CurentTime() {
	var now = new Date();
	var year = now.getFullYear(); //年
	var month = now.getMonth() + 1; //月
	var day = now.getDate(); //日
	var hh = now.getHours(); //时
	var mm = now.getMinutes(); //分
	var ss = now.getSeconds(); //秒

	var clock = year + "-";

	if(month < 10)
		clock += "0";

	clock += month + "-";

	if(day < 10)
		clock += "0";

	clock += day + " ";

	if(hh < 10)
		clock += "0";

	clock += hh + ":";
	if(mm < 10) clock += '0';
	clock += mm + ":";
	clock += ss;
	
	return(clock);
}

//
function getQueryString(name) {
	var result = window.location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
	if (result == null || result.length < 1) {
    	return "";
	}
	return result[1];
}

