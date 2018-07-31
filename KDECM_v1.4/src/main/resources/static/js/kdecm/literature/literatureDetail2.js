/**
 * js
 */

$(function () {
	ajaxTest1();
	//http://localhost:8089/kdecm/literature/literatureDetail2.html?literatureId=5&userId=1&chapter=0
});

function addSpace(strs) {
	//var str = $(".content").text();
	var cssfontSize = $(".content").css('font-size');
	cssfontSize = parseFloat(cssfontSize);

	//var lengths = $(".content").html().length;
	var lengths = strs.length;
	var divWidth = $(".content").width();
	var everyRowSize = Math.floor(divWidth / cssfontSize);
	//alert("everyRowSize="+everyRowSize);
	var rows = Math.floor(lengths / everyRowSize);
	//alert("row="+rows);
	var lastRowSize = lengths - everyRowSize * rows;
	//alert("lastRowSize=" + lastRowSize);
	var remain = everyRowSize - lastRowSize;
	//alert("remain=" + remain);

	var space = cssfontSize / 4;
	//remain*cssfontSize/space
	//alert(remain*cssfontSize/space);
	var spaceSize = Math.ceil(remain);
	for(var i = 0; i < spaceSize; i++) {
		strs += "\t";
	}
	return strs;
	/*str += "肖凌云肖凌云";
	$(".content").text(str);*/
	/*var text = "test"; 	
	var len = text.visualLength(); 
	alert("length="+len);*/
}


var my_storage;
var my_literature= "";
var my_postil="";

function parseQueryString(url) {
    var str=url.split("?")[1],
    items=str.split("&");
    var arr = Array();
    var lit = items[0].split("=");
    arr[0]= lit[1];
    var user = items[1].split("=");
    arr[1]= user[1];
    var cpt = items[2].split("=");
    arr[2]= cpt[1];
    return arr;
}

function exportbtn(){
	var strs = "";
	var strp = "";
	var literatureName= "";
	var url = window.location.href;
	var arrUrl = parseQueryString(url);
	console.log(arrUrl);
	var literatureId = arrUrl[0];
	var userId = arrUrl[1];
	var chapter = arrUrl[2];
	var groupId = localStorage.getItem("currentGroupId");
	//alert("chapter="+chapter);
	if(groupId==null||groupId==""){
		alert("请先选组");
		return false;
	}
	console.log(literatureId);	
	$.ajax({
		type: 'POST',
		url: '../../kdecm/literatureObject/literatureShow?literatureId='+literatureId+'&userId=1&chapter=0',
		contentType: 'application/json',
		async: false,
		success:function(e){
			if(e.length>0){
				for(var j = 0; j < e.length; j++) {
					var num = e[j].literatureObjectType;
					var text_content = e[j].literatureObjectText;
	        		if(num == '1') {
	        			var title = e[j].literatureObjectText;
	        			literatureName = title;
	        			strs = strs + "<h2>" + title + "</h2>";
	        		}
	        		else if(num == '2') {
	        			strs = strs + "<br><br><span style='font-weight: 700; font-size: 22px;'>" + e[j].literatureObjectText + "</span><br><br>";
	        		}
	        		else if(num == '3'){
	        			strs = strs + text_content;
	        			strs = addSpace(strs);
	        		}
				}
				my_literature = strs;
				$.ajax({
					type: 'POST',
					url: '../../kdecm/postil/loadPostil?groupId='+groupId+'&literatureId='+literatureId,
					contentType: 'application/json',
					async: false,
					success:function(f){
						console.log(f);
						for(var k = 0; k < f.postilList.length; k++){
							strp = strp + f.postilObjectList[k].postilObjectText + ":" + f.postilList[k].postilContent + "&nbsp;&nbsp;" + title + "<br>";
						}
						my_postil = strp;
					}
				});
				$("#literatures").html(my_literature);
				$("#postils").html(my_postil);
				$("#literatures").wordExport(literatureName);
				$("#postils").wordExport(literatureName + "的批注");
			}
			else{
				alert("文献为空，不能导出！");
			}
		}
	});
}

function ajaxTest1(){
	var url = window.location.href;
	var arrUrl = parseQueryString(url);
	var literatureId = arrUrl[0];
	var userId = arrUrl[1];
	var chapter = arrUrl[2];
	$.ajax({ 
		type: 'POST',
		url: '../../kdecm/posPobLit/css_search?literatureId='+literatureId,
		contentType: 'application/json',
		async: false,
		success:function(e){
			my_storage = e;
		}
	});
	
	$.ajax({
		type: 'POST',
		url: '../../kdecm/literatureObject/literatureShow?literatureId='+literatureId+'&userId=1&chapter='+chapter,
		contentType: 'application/json',
		async: false,
		success:function(e){
        	var arr = new Array();
			var tmp = 0;
			var count = 0;
			for( var j = 0; j < my_storage.length; j++) {
				if(my_storage[j].postilType == 101 || my_storage[j].postilType == 102 || my_storage[j].postilType == 103) {
					arr[tmp] = my_storage[j];
					tmp++;
				}
			}
			var str = "";
			var flag = false;
			var flagType = false;
			for(var i = 0; i < e.length; i++) {
				if(e[i].literatureObjectType == '2') {
					flagType = true;
				}
			}
			if(flagType == false) {
				flag = true;
				$("#linkPreCpt").text("没有了");
			}
			for(var i = 0; i < e.length; i++) {
				var num = e[i].literatureObjectType;
				var text_content = e[i].literatureObjectText;
        		if(num == '1') {
        			var title = e[i].literatureObjectText;
        			title = "<h2>" + title + "</h2><br>";
        			$(".firstTitle").html(title);
        			//str = str + "<h2>" + title + "</h2><br>";
        		}
        		else if(num == '3') {
        			if(flag == true) {
        				//读取样式
            			var highlight = "background: yellow;";
            			var underline = "text-decoration: underline;";
            			var deleteline = "text-decoration: line-through;";
            			var flagChange = false;
            			var textTmp = "";
            			
            			if(arr.length != 0) {
            				for(var j = 0; j < arr.length; j++) {
                				var start_row = arr[j].postilObjectRowsStart;
                				var end_row = arr[j].postilObjectRowEnd;
                				var current_row = e[i].literatureObjectRow;
                				
                				if(current_row >= start_row && current_row <= end_row) {
                					var text = e[i].literatureObjectText;
                					var select;
                					if(current_row == start_row && current_row == end_row) {
                						select = text.slice(arr[j].postilObjectStart, arr[j].postilObjectEnd);
                					}
                					else if(current_row == start_row && current_row < end_row) {
                						var select = text.slice(arr[j].postilObjectStart, text.length);
                					}
                					else if(current_row > start_row && current_row == end_row) {
                						var select = text.slice(0, arr[j].postilObjectEnd);
                					}
                					else if(current_row > start_row && current_row < end_row) {
                						var select = text;
                					}
                					//判断样式
                					var style = "";
                					if(arr[j].postilType == 101) {
                						style += highlight;
                					}
                					if(arr[j].postilType == 102) {
                						style += underline;
                					}
                					if(arr[j].postilType == 103) {
                						style += deleteline;
                					}
                					console.log(text);
                					console.log(text.indexOf("span"));
                					if(text.indexOf("span") == -1) {
                						var select_css = text.replace(select, "<span style='"+style+"'>"+select+"</span>");
                						//console.log(select_css);
                						/*e[i].literatureObjectText = select_css;*/
                						textTmp = select_css + "<br>";
                						flagChange = true;
                					} else {
                						var list = text.split(/<span|'>|span>/);
                						var letter_start = arr[j].postilObjectStart;
                						var letter_end = arr[j].postilObjectEnd;
                						var list2 = [,];
                						var list3 = [];
                						var len = 0;
                						var tmp2 = 0;
                						var tmp3 = 0;
                						for(var k = 0; k < list.length; k++) {
                							if(k%3 == 0) {
                								var tmp_len = len;
                								len = len + list[k].length;
                								list2[tmp2] = {tmp_len, len};
                								tmp2++;
                								list3[tmp3] = "";
                								tmp3++;
                							}
                							else if(k%3 == 2) {
                								var tmp_len = len;
                								len = len + list[k].length - 2;
                								list2[tmp2] = {tmp_len, len};
                								tmp2++;
                								
                								list3[tmp3] = "";
                								
                								if(list[k-1].indexOf(highlight) != -1) {
                									list3[tmp3] += highlight;
                								}
                								if(list[k-1].indexOf(underline) != -1) {
                									list3[tmp3] += underline;
                								}
                								if(list[k-1].indexOf(deleteline) != -1) {
                									list3[tmp3] += deleteline;
                								}
                								tmp3++;
                							}
                						}
                						console.log(list);
                						console.log(list2);
                						console.log(list3);
                						var list22 = [,];
                						var list33 = [];
                						tmp2 = 0, tmp3 = 0;
                						console.log(letter_start);
                						console.log(letter_end);
                						for( var k = 0; k < list2.length; k++) {
                							  //[1,3][4,8],[9,23] => [6,10]
                							if(letter_end<list2[k].tmp_len) {
                								var tmp_len = list2[k].tmp_len;
                								var len = list2[k].len;
                								if(tmp_len!=len) {
        	        								list22[tmp2] = {tmp_len, len};
        	        								list33[tmp2] = list3[k];
        	        								tmp2++;
        	        								//console.log("1");
                								}
                							}
                							else if(letter_start<list2[k].tmp_len && letter_end>=list2[k].tmp_len && letter_end<list2[k].len) {
                								var tmp_len = list2[k].tmp_len;
                								var len = letter_end;
                								if(tmp_len!=len) {
        	        								list22[tmp2] = {tmp_len, len};
        	        								list33[tmp2] = list3[k]+style;
        	        								tmp2++;
                								}
                								
                								tmp_len = letter_end;
                								len = list2[k].len;
                								if(tmp_len!=len) {
        	        								list22[tmp2] = {tmp_len, len};
        	        								list33[tmp2] = list3[k];
        	        								tmp2++;
                								}
                								
                								//console.log("2");
                							}
                							else if(letter_start<list2[k].tmp_len && letter_end>=list2[k].len) {
                								var tmp_len = list2[k].tmp_len;
                								var len = list2[k].len;
                								if(tmp_len!=len) {
        	        								list22[tmp2] = {tmp_len, len};
        	        								list33[tmp2] = list3[k]+style;
        	        								tmp2++;
        	        								//console.log("3");
                								}
                							}
                							else if(letter_start>=list2[k].tmp_len && letter_end<=list2[k].len) {  //若另外样式在该字段中间，直接将其划分
                								var tmp_len = list2[k].tmp_len;
                								var len = letter_start;
                								if(tmp_len!=len) {
        	        								list22[tmp2] = {tmp_len, len};
        	        								list33[tmp2] = list3[k];
        	        								tmp2++;
                								}
                								
                								tmp_len = letter_start;
                								len = letter_end;
                								if(tmp_len!=len) {
        	        								list22[tmp2] = {tmp_len, len};
        	        								list33[tmp2] = list3[k]+style;
        	        								tmp2++;
                								}
                								
                								tmp_len = letter_end;
                								len = list2[k].len;
                								if(tmp_len!=len) {
        	        								list22[tmp2] = {tmp_len, len};
        	        								list33[tmp2] = list3[k];
        	        								tmp2++;
        	        								//console.log("4");
                								}
                							}
                							else if(letter_start>=list2[k].tmp_len && letter_start<list2[k].len && letter_end>list2[k].len) {
                								var tmp_len = list2[k].tmp_len;
                								var len = letter_start;
                								if(tmp_len!=len) {
        	        								list22[tmp2] = {tmp_len, len};
        	        								list33[tmp2] = list3[k];
        	        								tmp2++;
                								}
                								
                								var tmp_len = letter_start;
                								var len = list2[k].len;
                								if(tmp_len!=len) {
        	        								list22[tmp2] = {tmp_len, len};
        	        								list33[tmp2] = list3[k]+style;
        	        								tmp2++;
        	        								//console.log("5");
                								}
                							} else {
                								var tmp_len = list2[k].tmp_len;
                								var len = list2[k].len;
                								if(tmp_len!=len) {
        	        								list22[tmp2] = {tmp_len, len};
        	        								list33[tmp2] = list3[k];
        	        								tmp2++;
        	        								//console.log("6");
                								}
                							}
                						}
                						
                						console.log(list22);
        								console.log(list33);
        								var inner_css = "";
        								for(var k = 0; k < list22.length; k++) {
        									var start = list22[k].tmp_len;
        									var end = list22[k].len;
        									var css = list33[k];
        									if(css == "") {
        										inner_css+=text_content.substr(start, end-start);
        										console.log("11111");
        										console.log(inner_css);
        									} else {
        										inner_css = inner_css + "<span style='"+css+"'>"+text_content.substr(start, end-start)+"</span>";
        										console.log("22222");
        										console.log(inner_css);
        									}
        								}
                						console.log(inner_css);
                						str = str + inner_css + "<br>";
                						flagChange = true;
                					}
                				}
                			}
            				
            			} 
            			if(flagChange == false) {
            				/*gaile*/
            				str += e[i].literatureObjectText+"\n";
            				//alert("str="+str);
            				//str = addSpace(str);
            			} else {
            				//alert("123");
            				str = str + textTmp;
            			}
        			}
        			
        			if(i == e.length-1) {
        				$("#linkNextCpt").text("没有了");
        			}
        		}
        		else if(num == '2') {
        			if(chapter == 0) {
        				$("#linkPreCpt").text("没有了");
        			}
        			if(count == Number(chapter)) {
        				flag = true;
        				var title = "<span style='font-weight: 700; font-size: 22px;'>" + e[i].literatureObjectText + "</span><br><br>";
        				$(".secondTitle").html(title);
        				//str = str + "<span style='font-weight: 700; font-size: 22px;'>" + e[i].literatureObjectText + "</span><br><br>";
        			} else {
        				if(count == Number(chapter) + 1) {
        					var queryUrl = "literatureDetail2.html?literatureId="+literatureId+"&userId=1&chapter="+count;
        					console.log(queryUrl);
        					$("#linkNextCpt").attr("href",queryUrl);
        					$("#linkNextCpt").text(e[i].literatureObjectText);
        					break;
        				}
        				else if(count == Number(chapter) - 1) {
        					var queryUrl = "literatureDetail2.html?literatureId="+literatureId+"&userId=1&chapter="+count;
        					console.log(queryUrl);
        					$("#linkPreCpt").attr("href",queryUrl);
        					$("#linkPreCpt").text(e[i].literatureObjectText);
        				}
        			}
        			count++;
        		}
			}
			/*console.log(str);*/
			
			$("#content").html(str);
		}
	});


} 

var vm = new Vue({
	el:'#content',
	data: {
		/*url: '../../kdecm/literatureObject/literatureShow?_' + $.now(),
		height: $(window).height()-56,
		queryParams: function(params){
			var url = window.location.href;
			var litID = parseQueryString(url);
			params.literatureId = litID;
			return params;
		},
		litText : "111";*/
	},
	methods : {
		load: function() {
			$('#dataGrid').bootstrapTable('refresh');
		}
	}
});