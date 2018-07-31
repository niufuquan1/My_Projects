/**
 * js
 */

$(function () {
	initialPage();
	getGrid();
});

function initialPage() {
	$(window).resize(function() {
		$('#dataGrid').bootstrapTable('resetView', {height: $(window).height()-56});
	});
}

var my_storage;

function getGrid() {
	$('#dataGridUnShow').bootstrapTableEx({
		url: '../../kdecm/posPobLit/css_search?_' + $.now(),
		height: $(window).height()-56,
		queryParams: function(params){
			console.log("123456789");
			var url = window.location.href;
			var litID = parseQueryString(url);
			params.literatureId = litID;
			params.userId = 3;
			console.log(params);
			return params;
		},
        responseHandler:function(res){ // res为从服务器请求到的数据
        	console.log(res);
        	my_storage = res;
        	return res;  
        }
	});
	
	$('#dataGrid').bootstrapTableEx({
		url: '../../kdecm/literatureObject/literatureShow?_' + $.now(),
		height: $(window).height()-56,
		queryParams: function(params){
			var url = window.location.href;
			var litID = parseQueryString(url);
			params.literatureId = litID;
			return params;
		},
        responseHandler:function(res){ // res为从服务器请求到的数据
        	console.log(res);
        	var arr = new Array();
			var tmp = 0;
			for( var j = 0; j < my_storage.rows.length; j++) {
				if(my_storage.rows[j].postilType == 101 || my_storage.rows[j].postilType == 102 || my_storage.rows[j].postilType == 103) {
					arr[tmp] = my_storage.rows[j];
					tmp++;
				}
			}
        	for(var i = 0; i < res.rows.length; i++) {
        		var num = res.rows[i].literatureObjectType;
				var text_content = res.rows[i].literatureObjectText;
        		if(num == '1') {
        			//一行共能容纳23个汉字，根据字数在左边加空格
        			var title = res.rows[i].literatureObjectText;
        			var title_length = title.length;
        			console.log(title_length);
        			var plus = (24-title_length)/2;
        			for(var j = 0; j < plus; j++) {
        				title = "&nbsp" + title;
        			}
        			console.log(title);
        			res.rows[i].literatureObjectText = title;
        		}
        		else if(num == '2') {
        			//读取样式
        			var highlight = "background: yellow;";
        			var underline = "text-decoration: underline;";
        			var deleteline = "text-decoration: line-through;";
        			
        			for(var j = 0; j < arr.length; j++) {
        				var start_row = arr[j].postilObjectRowsStart;
        				var end_row = arr[j].postilObjectRowEnd;
        				var current_row = res.rows[i].literatureObjectRow;
        				if(current_row >= start_row && current_row <= end_row) {
        					var text = res.rows[i].literatureObjectText;
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
        					/*console.log(text);
        					console.log(text.indexOf("span"));*/
        					if(text.indexOf("span") == -1) {
        						var select_css = text.replace(select, "<span style='"+style+"'>"+select+"</span>");
        						//console.log(select_css);
        						res.rows[i].literatureObjectText = select_css;
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
        						/*console.log(list);
        						console.log(list2);
        						console.log(list3);*/
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
        						
        						/*console.log(list22);
								console.log(list33);*/
								var inner_css = "";
								for(var k = 0; k < list22.length; k++) {
									var start = list22[k].tmp_len;
									var end = list22[k].len;
									var css = list33[k];
									if(css == "") {
										inner_css+=text_content.substr(start, end-start);
										/*console.log("11111");
										console.log(inner_css);*/
									} else {
										inner_css = inner_css + "<span style='"+css+"'>"+text_content.substr(start, end-start)+"</span>";
										/*console.log("22222");
										console.log(inner_css);*/
									}
								}
        						console.log(inner_css);
        						res.rows[i].literatureObjectText = inner_css;
        					}
        				}
        			}
        		}
        	}
        	return res;  
        }, 
		columns: [
			{field : "literatureObjectText", title : "", width : "50%"}, 
		]
	});
}

function parseQueryString(url) {
    var str=url.split("?")[1],
    items=str.split("&");
    var arr,name,value;
    arr=items[0].split("=");
    value= arr[1];
    return value;
}

var vm = new Vue({
	el:'#literature',
	data: {
		
	},
	methods : {
		load: function() {
			$('#dataGrid').bootstrapTable('refresh');
		}
	}
});