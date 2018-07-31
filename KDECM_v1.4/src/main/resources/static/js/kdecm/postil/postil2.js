/**
 * @作者：WilliamSha
 * @时间：2011-10-18 下午07:52:01
 * @项目名：XXX
 * @描述：操作批注工具
 */
// 公共的不断更新的批注对象ID
var postilObjectId = 0;
var func = "insert";
// 初始化页面元素
$(window).resize(function() {
	$.each($(".strikeline"), function(a, b) {
		var forids = $(b).attr("class");
		var forid = forids.split(" ")[1];
		
		var postion = $("." + forid + ":eq(0) .postion").val();
		var value = $("." + forid + ":eq(0) input:eq(0)").val();
		var selectedText = $("." + forid + ":eq(0) input:eq(0)").attr("name");
		//alert("value="+value+"  "+"selectedText="+selectedText);
		
		if(postion == "" || postion == null) {
			
			return false;
		} else {
			
			var start = postion.split(" ")[0];
			var end = postion.split(" ")[1];
			
			$("." + forid).remove();
			createSelectStyleObjectList(forid,start,end,value,selectedText,1);
		}

	});
	
	$.each($(".underline"), function(a, b) {
		var forids = $(b).attr("class");
		var forid = forids.split(" ")[1];
		
		var postion = $("." + forid + ":eq(0) .postion").val();
		var value = $("." + forid + ":eq(0) input:eq(0)").val();
		var selectedText = $("." + forid + ":eq(0) input:eq(0)").attr("name");
		//alert("value="+value+"  "+"selectedText="+selectedText);
		
		if(postion == "" || postion == null) {
			
			return false;
		} else {
			var start = postion.split(" ")[0];
			var end = postion.split(" ")[1];
			

			$("." + forid).remove();
			createSelectStyleObjectList(forid,start,end,value,selectedText,2);
		}

	});
	
	$.each($(".highlight"), function(a, b) {
		var forids = $(b).attr("class");
		var forid = forids.split(" ")[1];
		
		var postion = $("." + forid + ":eq(0) .postion").val();
		var value = $("." + forid + ":eq(0) input:eq(0)").val();
		var selectedText = $("." + forid + ":eq(0) input:eq(0)").attr("name");
		//alert("value="+value+"  "+"selectedText="+selectedText);
		
		if(postion == "" || postion == null) {
			
			return false;
		} else {
			var start = postion.split(" ")[0];
			var end = postion.split(" ")[1];
			$("." + forid).remove();
			createSelectStyleObjectList(forid,start,end,value,selectedText,3);
		}

	});
	
	$.each($(".posList"), function(a, b) {
		var forid = $(b).attr("forid");
		/*$.each($("." + forid),function(a, b){
			var postion = $("." + forid + ":eq(" + a + ") .postion").val();
			var value = $("." + forid + ":eq(" + a + ") .info").val();
			var selectedText = $("." + forid + ":eq(" + a + ") .info").attr("name");
			if(postion == "" || postion == null) {
				return false;
			} else {
				var start = postion.split(" ")[0];
				var end = postion.split(" ")[1];
				$(b).remove();
				alert("即将进入重载过程");
				createSelectPostilObjectList(forid,start,end,value,selectedText,a);
			}
		});*/
		
		var postion = $("." + forid + ":eq(0) .postion").val();
		var value = $("." + forid + ":eq(0) .info").val();
		var selectedText = $("." + forid + ":eq(0) .info").attr("name");
		if(postion == "" || postion == null) {
			return false;
		} else {
			var start = postion.split(" ")[0];
			var end = postion.split(" ")[1];
			$("." + forid).remove();
			createSelectPostilObjectList(forid,start,end,value,selectedText);
		}
	});
	

	
});

function createSelectPostilObjectList(forid,start,end,value,selectedText){
	
	var content = document.getElementById("content");
	var selection = window.getSelection();
	selection.removeAllRanges();
	var range = document.createRange();
	
	range.setStart(content.firstChild, start);
	range.setEnd(content.lastChild, end);
	selection.addRange(range);
	
	var tempRange = range.cloneContents();
	var span = document.createElement('div');
	span.appendChild(tempRange);
	hs = $(span).html().split("\n");
	span.remove();
	
	var startTemp = 0;
	var endTemp = start;
	if(hs.length == 1){
		var startPos = getStartPos();
		var endPos = getEndPos();
		createPostilObjectList(forid, startPos.x, startPos.y, endPos.x, endPos.y);
		$.each($("."+ forid),function(a,b){
			$("." + forid + ":eq(" + a + ")" + " .postion").val(start + " " + end);
			$("." + forid + ":eq(" + a + ")" + " .postion").html(endPos.x + " " + endPos.y);
			$("." + forid + ":eq(" + a + ")" + " .info").val(value);
			$("." + forid + ":eq(" + a + ")" + " .info").attr("name", selectedText);
		});
	}else{
		//alert("有换行");
		for(var i = 0 ; i < hs.length ; i++){
			startTemp = endTemp;
			endTemp = parseInt(endTemp) + parseInt(hs[i].length);
			
			var content = document.getElementById("content");
			var selection = window.getSelection();
			selection.removeAllRanges();
			var range = document.createRange();
			//alert("startTemp="+startTemp);
			//alert("endTemp="+endTemp);
			range.setStart(content.firstChild, startTemp);
			range.setEnd(content.lastChild, endTemp);
			selection.addRange(range);
			
			var startPos = getStartPos();
			var endPos = getEndPos();
			
			if(i == 0){
				createPostilObjectList(forid, startPos.x, startPos.y, endPos.x, endPos.y);
				endTemp++;
			}else if(i == (hs.length-1)){
				createPostilObjectList(forid, startPos.x, startPos.y, endPos.x, endPos.y);
			}else{
				createPostilObjectList(forid, startPos.x, startPos.y, endPos.x, endPos.y);
				endTemp++;
			}
			$.each($("."+ forid),function(a,b){
				$("." + forid + ":eq(" + a + ")" + " .postion").val(start + " " + end);
				/*$("." + forid + ":eq(" + a + ")" + " .postion").html(endPos.x + " " + endPos.y);*/
				$("." + forid + ":eq(" + a + ")" + " .info").val(value);
				$("." + forid + ":eq(" + a + ")" + " .info").attr("name", selectedText);
			});
		}
	}

	/*var startPos = getStartPos();
	var endPos = getEndPos();
	createPostilObjectList(forid, startPos.x, startPos.y, endPos.x, endPos.y);
	$("." + forid + ":eq(" + rank + ")" + " .postion").val(start + " " + end);
	$("." + forid + ":eq(" + rank + ")" + " .postion").html(endPos.x + " " + endPos.y);
	
	alert("l="+$("." + forid + " input:eq(0)").length);
	$("." + forid + ":eq(" + rank + ")" + " .info").val(value);
	$("." + forid + ":eq(" + rank + ")" + " .info").attr("name", selectedText);*/
	/*$.each($(".$("." + forid + " input:eq(0)")." + forid + " input:eq(0)"), function(a, b) {
		$(b).val(value);
		$(b).attr("name", selectedText);
	});*/
}

function createSelectStyleObjectList(forid,start,end,value,selectedText,type){
	var content = document.getElementById("content");
	var selection = window.getSelection();
	selection.removeAllRanges();
	var range = document.createRange();
	
	range.setStart(content.firstChild, start);
	range.setEnd(content.lastChild, end);
	selection.addRange(range);

	var startPos = getStartPos();
	var endPos = getEndPos();
	createStyleList(forid, startPos.x, startPos.y, endPos.x, endPos.y,type);
	$("." + forid + ":eq(0)" + " .postion").val(start + " " + end);
	$("." + forid + ":eq(0)" + " .postion").html(endPos.x + " " + endPos.y);
	
	//alert("l="+$("." + forid + " input:eq(0)").length);
	$("." + forid + " input:eq(0)").val(value);
	$("." + forid + " input:eq(0)").attr("name", selectedText);

}

$(function() {
	var selectedText;
	var start = 0;
	var end = 0;

	$(".content")
			.mouseup(
					function(e) {

						var regu = "^[ ]+$";
						var re = new RegExp(regu);

						if (window.getSelection) {
							selectedText = window.getSelection().toString();
						
							if (selectedText.length > 0
									&& re.test(selectedText) == false) {
								
								var selectObj = window.getSelection();
								
								start = selectObj.anchorOffset;
								end = selectObj.focusOffset;
							}
						} else if (document.selection
								&& document.selection.createRange) {
							selectedText = document.selection.createRange().text;
							if (selectedText.length > 0
									&& re.test(selectedText) == false) {
								var selectObj = window.getSelection();
								start = selectObj.anchorOffset;
								end = selectObj.focusOffset;
							}
						}
						if (selectedText && selectedText.length > 0 && re.test(selectedText) == false) {
							var w = $("#content");
							$("#icon").css({
								"left" : e.clientX + 1,
								"top" : e.offsetY + 118

							}).fadeIn(300);
							$("#icon1").css({
								"left" : e.clientX + 31,
								"top" : e.offsetY + 118

							}).fadeIn(300);
							$("#icon2").css({
								"left" : e.clientX + 61,
								"top" : e.offsetY + 118

							}).fadeIn(300);
							$("#icon3").css({
								"left" : e.clientX + 91,
								"top" : e.offsetY + 118

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
	}, function() {
		$(this).children().addClass("tipsIcon");
	}).click(function() {
		$("#icon").hide();
		$("#icon1").hide();
		$("#icon2").hide();
		$("#icon3").hide();
		addPostil(selectedText, 1, start, 1, end, 1);
	});

	$("#icon1").hover(function() {
		$(this).children().removeClass("tipsIcon1");
	}, function() {
		$(this).children().addClass("tipsIcon1");
	}).click(function() {
		$("#icon").hide();
		$("#icon1").hide();
		$("#icon2").hide();
		$("#icon3").hide();
		addStyle(selectedText, 1, start, 1, end, 1, 1);
	});

	$("#icon2").hover(function() {
		$(this).children().removeClass("tipsIcon2");
	}, function() {
		$(this).children().addClass("tipsIcon2");
	}).click(function() {
		$("#icon").hide();
		$("#icon1").hide();
		$("#icon2").hide();
		$("#icon3").hide();
		addStyle(selectedText, 1, start, 1, end, 1, 2);
	});

	$("#icon3").hover(function() {
		$(this).children().removeClass("tipsIcon3");
	}, function() {
		$(this).children().addClass("tipsIcon3");
	}).click(function() {
		$("#icon").hide();
		$("#icon1").hide();
		$("#icon2").hide();
		$("#icon3").hide();
		addStyle(selectedText, 1, start, 1, end, 1, 3);
	});
});
// 添加批注
// function addPostil() {
function addPostil(selectedText, postilObjectRowsStart, start,
		postilObjectRowEnd, end, literatureId) {

	// IE支持的range对象
	var ie_range;
	// 其他浏览器的range对象
	var other_range;

	var forid;
	var startPos;
	var endPos;
	var hs;
	if (window.getSelection) {
		other_range = window.getSelection().getRangeAt(0);
		if (other_range) {
			/*var tempRange = other_range.cloneContents();
			var span = document.createElement('div');
			span.appendChild(tempRange);
			hs = $(span).html().split("\n");
			span.remove();*/
			
			forid = new Date().getTime();
			startPos = getStartPos();
			endPos = getEndPos();
		}
	} else if (document.selection && document.selection.createRange) {
		ie_range = document.selection.createRange();
		if (ie_range) {
			/*var tempRange = other_range.cloneContents();
			var span = document.createElement('div');
			span.appendChild(tempRange);
			hs = $(span).html().split("\n");
			span.remove();*/
			
			forid = new Date().getTime();
			// endPos = showSelectionPositionXiao(forid);
			startPos = getStartPos();
			endPos = getEndPos();
		}
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
		async : false,
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
	if (length >= 3) {
		length = 3;
	}
	for (var i = 0; i < length; i++) {
		table += "<tr><td>" + recommendresult[i].postilObjectText + "</td><td>"
				+ recommendresult[i].postilContent + "</td></tr>";
		// "<hr style=\"height:1px;border:none;border-top:1px solid
		// #555555;\"/>";
	}
	table += "</table>";
	var str = '<textarea id="postil" rows="10" cols="55"></textarea>' + table;

	/*-------------------------------------------分割线--------------------------------------------------*/

	art.dialog({
		id : 'inputDialog',
		title : '添加批注',
		content : str,
		lock : true

	}, function() {
		var value = document.getElementById("postil").value;
		if (!value) {
			art.dialog({
				content : '批注内容不能为空！',
				time : 1
			});
			return false;
		}
		if (other_range) {
			/*
			 * //IE之外的浏览器，如果在选择内容包含其他标签的一部分的时候会报异常 var mark =
			 * document.createElement("ins"); mark.setAttribute("comment",
			 * value); mark.className = "postil"; mark.id=new Date().getTime();
			 * other_range.surroundContents(mark);
			 */
			createSelectPostilObjectList(forid,start,end,value,selectedText);
			/*createPostilObjectList(forid, startPos.x, startPos.y, endPos.x, endPos.y);
			$.each($("."+ forid),function(a,b){
				$("." + forid + ":eq(" + a + ")" + " .postion").val(start + " " + end);
				$("." + forid + ":eq(" + a + ")" + " .postion").html(endPos.x + " " + endPos.y);
				$("." + forid + ":eq(" + a + ")" + " .info").val(value);
				$("." + forid + ":eq(" + a + ")" + " .info").attr("name", selectedText);
			});*/
			/*var startTemp;
			var endTemp = start;
			if(hs.length == 1){
				createSelectPostilObjectList(forid,start,end,value,selectedText);
				createPostilObjectList(forid, startPos.x, startPos.y, endPos.x, endPos.y);	
				$("." + forid + ":eq(0)" + " .postion").val(start + " " + end);
				$("." + forid + ":eq(0)" + " .postion").html(endPos.x + " " + endPos.y);
				$("." + forid + " .info").val(value);
				$("." + forid + " .info").attr("name", selectedText);
			}else if(hs.length > 1){
				createSelectPostilObjectList(forid,start,end,value,selectedText);
				for(var i = 0 ; i < hs.length ; i++){
					startTemp = endTemp;
					endTemp += hs[i].length;
					if(i == 0){
						createSelectPostilObjectList(forid,startTemp,endTemp,value,selectedText,i);
						endTemp++;
					}else if(i == (hs.length-1)){
						createSelectPostilObjectList(forid,startTemp,endTemp,value,selectedText,i);
					}else{
						createSelectPostilObjectList(forid,startTemp,endTemp,value,selectedText,i);
						endTemp++;
					}
				}
			}*/
			
			//$("." + forid + ":eq(0)" + " .postion").val(start + " " + end);
			//$("." + forid + ":eq(0)" + " .postion").html(endPos.x + " " + endPos.y);
			
			//$("." + forid + " input:eq(0)").val(value);
			//$("." + forid + " input:eq(0)").attr("name", selectedText);
			
		} else if (ie_range) {
			createPostilObjectList(forid, startPos.x, startPos.y, endPos.x, endPos.y);
			$("." + forid + ":eq(0)" + " .postion").val(start + " " + end);
			$("." + forid + ":eq(0)" + " .postion").html(endPos.x + " " + endPos.y);
			
			$("." + forid + " input:eq(0)").val(value);
			$("." + forid + " input:eq(0)").attr("name", selectedText);
			/*$.each($("." + forid + " input"), function(a, b) {
				$(b).val(value);
				$(b).attr("name", selectedText);
			});*/
		}

		// 2018-6-6改，不是所有的批注对象都需要存储，只有在选中并填写批注点击OK后，才会去生成
		// 2018-6-27 改
		var literatureId = getQueryString("literatureId");
		var last = $("." + forid).length - 1;
		var $dh1 = $("." + forid + ":eq(0)").position().left;
		var $dh2 = $("." + forid + ":eq(0)").position().top;
		var $dh3 = endPos.x;
		var $dh4 = endPos.y;
		// var $dh3 = $("." + forid + ":eq("+ last +")
		// #wholeSelectionInner").width();
		// var $dh4 = $("." + forid + ":eq("+ last +")
		// #wholeSelectionInner").height();
		var chapter = getQueryString("chapter");
		// alert("chapter="+chapter);
		var postilObjectPos = $dh1 + "," + $dh2 + "," + $dh3 + "," + $dh4 + ","
				+ chapter;
		// alert(postilObjectPos);

		var posObjDef = {
			'postilObjectText' : selectedText,
			'postilObjectRowsStart' : 1,
			'postilObjectStart' : start,
			'postilObjectRowEnd' : 1,
			'postilObjectEnd' : end,
			'literatureId' : literatureId,
			'postilObjectPos' : postilObjectPos
		};
		posObjDef = JSON.stringify(posObjDef);
		$.ajax({
			type : 'POST',
			url : '../../kdecm/postil_object/insert', // ?userId=1
			contentType : 'application/json',
			async : false,
			// dataType: 'text',
			data : posObjDef,
			success : function(e) {
				if (e.code == 0) {
					postilObjectId = e.postilObjectId
				}
				//alert("改进的批注对象插入 success");
			}
		});
		loader();
	});
}

function addStyle(selectedText, postilObjectRowsStart, start,
		postilObjectRowEnd, end, literatureId, type) {

	var style;
	switch (type) {
	case 1:
		style = 1101;
		break;
	case 2:
		style = 1102;
		break;
	case 3:
		style = 1103;
		break;
	default:
		style = 1101;
	}
	// IE支持的range对象
	var ie_range;
	// 其他浏览器的range对象
	var other_range;

	var forid;
	var startPos;
	var endPos;
	if (window.getSelection) {
		other_range = window.getSelection().getRangeAt(0);
		if (other_range) {
			forid = new Date().getTime();
			startPos = getStartPos();
			endPos = getEndPos();

			
	
		}
	} else if (document.selection && document.selection.createRange) {
		ie_range = document.selection.createRange();
		if (ie_range) {
			forid = new Date().getTime();
			startPos = getStartPos();
			endPos = getEndPos();
			
			
		}
	}
	createStyleList(forid, startPos.x, startPos.y, endPos.x, endPos.y,type);
	
	$("." + forid + ":eq(0)" + " .postion").val(start + " " + end);
	
	$("." + forid + ":eq(0)" + " .postion").html(endPos.x + " " + endPos.y);
	
	$("." + forid + " input:eq(0)").val("");
	$("." + forid + " input:eq(0)").attr("name", selectedText);
	
	var literatureId = getQueryString("literatureId");
	var last = $("." + forid).length - 1;
	var $dh1 = $("." + forid + ":eq(0)").position().left;
	var $dh2 = $("." + forid + ":eq(0)").position().top;
	var $dh3 = endPos.x;
	var $dh4 = endPos.y;
	var chapter = getQueryString("chapter");
	var postilObjectPos = forid + ","
	+ chapter;
	// alert(postilObjectPos);
	

	var posObjDef = {
		'postilObjectText' : style,
		'postilObjectRowsStart' : 1,
		'postilObjectStart' : start,
		'postilObjectRowEnd' : 1,
		'postilObjectEnd' : end,
		'literatureId' : literatureId,
		'postilObjectPos' : postilObjectPos
	};
	posObjDef = JSON.stringify(posObjDef);
	$.ajax({
		type : 'POST',
		url : '../../kdecm/postil_object/insert', // ?userId=1
		contentType : 'application/json',
		async : false,
		// dataType: 'text',
		data : posObjDef,
		success : function(e) {
			if (e.code == 0) {
				postilObjectId = e.postilObjectId;
				//postilObjectText= e.postilObjectText;
			}
		
		}
	});
}

// 解析批注
/* 2018/5/29 9:52 滑动按钮添加 */
function loader() {
	var $list = $(".list");
	$list.children().remove();

	var beforeId = 0;

	// $.each($(".content ins"), function(a, b) {
	$.each(
					$(".postilObjectList"),
					function(a, b) {
						var forids = $(b).attr("class");
						var forid;
						if (forids.split(" ")[0] == "postilObjectList") {
							forid = forids.split(" ")[1];
						} else {
							forid = forids.split(" ")[0];
						}
						if (forid != beforeId) {
							beforeId = forid;

							var content = $("." + forid + ":eq(0) .info").attr(
									"value");
							var currenttime = CurentTime();

							var $postil = $("<div class='posList' forid='"
									+ forid
									+ "'>"
									+
									/*
									 * "<input type='hidden' class='postilId'
									 * value=''/>" +
									 */
									"<span class='removeWin' onClick='removePostil(this)'>    |  　　x</span>"
									+ "<span class='currentTime' id='currentTime' >"
									+ currenttime
									+ "</span>"
									+ "<select class=\"colorSelect\" style=\"background-color:#996666;\" onchange=\"changeColor(this,this.options[this.options.selectedIndex].value)\"><option style=\"background-color: #0099FF\" value=\"#0099FF\"></option><option style=\"background-color: #00CC66\" value=\"#00CC66\"></option><option style=\"background-color: #CC0033\" value=\"#CC0033\"><option style=\"background-color: #996666\" value=\"#996666\"><option style=\"background-color: #FFCC33\" value=\"#FFCC33\"><option style=\"background-color: #FF6EB4\" value=\"#FF6EB4\"><option style=\"background-color: #B23AEE\" value=\"#B23AEE\"></select>"
									+ "<div class='posContent' contenteditable='true' forid='"
									+ forid + "'>" + content + "</div>"
									+ "</div>");

							$postil.hover(function() {
								$(this).css("border-color", "#CD5454");
								$.each($("." + forid), function(a, b) {
									$(b).removeClass("postilObjectList")
											.addClass("postilFocus");
								});
								// $("."+forid).removeClass().addClass("postilFocus");
							}, function() {
								$(this).css("border-color", "#ddd");
								$.each($("." + forid), function(a, b) {
									$(b).removeClass("postilFocus").addClass(
											"postilObjectList");
								});
								// $("."+forid).removeClass().addClass("postilObjectList");
							});
							/*
							 * $(b).hover(function() {
							 * $(this).removeClass().addClass("postilFocus");
							 * $("div[forid='" + $(this).get(0).id +
							 * "']").css("border-color", "red"); }, function() {
							 * $(this).removeClass().addClass("postil");
							 * $("div[forid='" + $(this).get(0).id +
							 * "']").css("border-color", "#ddd"); });
							 */

							$list.append($postil);
							
							var color = $("." + forid + ":eq(0) input:eq(0)").attr("id");
							if(color==null&&color==""){
								color = "#000000";
							}
							$("div[forid='" + forid + "']").css("color",color);
							//$("div[forid='" + forid + "'] + .colorSelect").css("background-color",color);
							
							// 2018-6-23
							var length = $(".postilObjectList").length - 1;
							if (a == length && func == "insert") {

								// 2018/5/31 10:32 生成页面之后 AJAX动态添加数据库
								var currentTimeDef = $(
										"div[forid='" + forid
												+ "'] #currentTime").text();
								var posContentDef = $(
										"div[forid='" + forid
												+ "'] .posContent").text();
								var opennessDef = 0;
								var currentUserId = $(".currentUserId").val();

								/* 2018-6-20 改 */
								var groupId = localStorage
										.getItem("currentGroupId");

								var posDef = {
									'userId' : currentUserId,
									'postilObjectId' : postilObjectId,
									'postilContent' : posContentDef,
									'postilTime' : currentTimeDef,
									'postilOpenness' : opennessDef,
									'groupId' : groupId,
									'postilForid' : forid
								};
								posDef = JSON.stringify(posDef);

								$.ajax({
									type : 'POST',
									url : '../../kdecm/postil/insert', // ?userId=1
									contentType : 'application/json',
									async : false,
									data : posDef,
									success : function(e) {
										if (e.code == 0) {
											// 2018-6-23 改 不需要返回的ID，只需要forid即可
											alert("批注插入成功");
										}

									}
								});
							}
							// 2018-6-3 15:44 当批注改变，自动获取并保存
							// var this_div = $(this);
							var pos = $("div[forid='" + forid
									+ "'] .posContent");

							pos.blur(function() {
								var postil = $(this).text();
								$("." + forid + " input").val(postil);

								var id = $(this).attr("forid");

								// var postilId = $("div[forid='" + id + "']
								// .postilId").val();

								var posUpdate = {
									'postilForid' : id,
									'postilContent' : postil
								};
								posUpdate = JSON.stringify(posUpdate);

								$.ajax({
									type : 'POST',
									url : '../../kdecm/postil/update2', // ?userId=1
									contentType : 'application/json',
									data : posUpdate,
									success : function(e) {
										if (e.code == 0) {
											//alert("批注修改成功");
										}
									}
								});
							});
						} else {
							// 2018-6-23
							var length = $(".postilObjectList").length - 1;
							var color = $("." + forid + ":eq(0) input:eq(0)").attr("id");
							if(color==null&&color==""){
								color = "#000000";
							}
							$("div[forid='" + forid + "']").css("color",color);
							//$("div[forid='" + forid + "'] + .colorSelect").css("background-color",color);
							if (a == length && func == "insert") {
								// 2018/5/31 10:32 生成页面之后 AJAX动态添加数据库
								var currentTimeDef = $(
										"div[forid='" + forid
												+ "'] #currentTime").text();
								var posContentDef = $(
										"div[forid='" + forid
												+ "'] .posContent").text();
								var opennessDef = 0;
								var currentUserId = $(".currentUserId").val();

								/* 2018-6-20 改 */
								var groupId = localStorage
										.getItem("currentGroupId");

								var posDef = {
									'userId' : currentUserId,
									'postilObjectId' : postilObjectId,
									'postilContent' : posContentDef,
									'postilTime' : currentTimeDef,
									'postilOpenness' : opennessDef,
									'groupId' : groupId,
									'postilForid' : forid
								};
								posDef = JSON.stringify(posDef);

								$.ajax({
									type : 'POST',
									url : '../../kdecm/postil/insert', // ?userId=1
									contentType : 'application/json',
									async : false,
									// dataType: 'text',
									data : posDef,
									success : function(e) {
										if (e.code == 0) {
											// 2018-6-23 改 不需要返回的ID，只需要forid即可
											//alert("批注修改成功");
										}
									}
								});
							}
						}
					});
	func = "insert";
}

// 删除批注
function removePostil(arg) {
	var $div = $(arg).parent();
	var id = $div.attr("forid");
	var $source = $("." + id);
	
	if(confirm("确定删除吗?")){
		// var text = $source.after($source.html());
		func = "remove";
		// var id = $(arg).prev().val();
		// var id = $(arg).attr("value");
		$.ajax({
			type : 'POST',
			url : '../../kdecm/postil/delete?postilForid=' + id, // ?userId=1
			// contentType: 'application/json',
			dataType : 'json',
			success : function(e) {
				if (e.code == 0) {
					alert("批注删除成功");
				}
			}
		});
		$source.remove();
		loader();
		}else{
			return false;
		}

}

// 2018/5/29 8:59 生成当前日期 肖凌云
function CurentTime() {
	var now = new Date();
	var year = now.getFullYear(); // 年
	var month = now.getMonth() + 1; // 月
	var day = now.getDate(); // 日
	var hh = now.getHours(); // 时
	var mm = now.getMinutes(); // 分
	var ss = now.getSeconds(); // 秒

	var clock = year + "-";

	if (month < 10)
		clock += "0";

	clock += month + "-";

	if (day < 10)
		clock += "0";

	clock += day + " ";

	if (hh < 10)
		clock += "0";

	clock += hh + ":";
	if (mm < 10)
		clock += '0';
	clock += mm + ":";
	clock += ss;

	return (clock);
}

//
function getQueryString(name) {
	var result = window.location.search.match(new RegExp("[\?\&]" + name
			+ "=([^\&]+)", "i"));
	if (result == null || result.length < 1) {
		return "";
	}
	return result[1];
}
