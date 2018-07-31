$(function () {
		ajaxTest1();
		hasLogin();
	});
	
	function hasLogin(){
		/*var userName = document.cookie.split("=")[1];
		alert(document.cookie);
		alert("++"+userName+"++");*/
		var userName = document.cookie.replace(/(?:(?:^|.*;\s*)username\s*\=\s*([^;]*).*$)|^.*$/, "$1");
		if(userName != null && userName != ""){
			$(".login").html(userName);
			$(".login").attr("href","index.html");
			$(".register").remove();
		}
	}
	
	function parseQueryString(url) {
	    var str=url.split("?")[1],
	    items=str.split("&");
	    var arr,name,value;
	    arr=items[0].split("=");
	    return arr;
	}
	
	function Dictionary(){
		this.data = new Array();
		
		this.put = function(key,value){
			this.data[key] = value;
		};

		this.get = function(key){
			return this.data[key];
		};

		this.remove = function(key){
			this.data[key] = null;
		};
		 
		this.isEmpty = function(){
			return this.data.length == 0;
		};

		this.size = function(){
			return this.data.length;
		};
	}

	function ajaxTest1(){
		var url = window.location.href;
		url = decodeURI(url);
		var arrUrl = parseQueryString(url);
		var type = arrUrl[0];
		var literatureObjectText = arrUrl[1];
		var postilContent = arrUrl[1];
		var resMap = new Dictionary();
		$("#txtKeyword")[0].value = postilContent;
		$.ajax({ 
			type: 'POST',
			url: 'kdecm/literature/getName',
			contentType: 'application/json',
			async: false,
			success:function(res2){
				for(var i = 0; i < res2.length; i++) {
					var id = res2[i].literatureId;
					var name = res2[i].literatureName;
					resMap.put(id, name);
				}
			}
		});
		
		$.ajax({ 
			type: 'POST',
			url: 'kdecm/literatureObject/literature_search_pub?literatureObjectText='+literatureObjectText,
			contentType: 'application/json',
			async: false,
			success:function(res){
				var html = "";
				var url = "login.html?";
				var userName = document.cookie.replace(/(?:(?:^|.*;\s*)username\s*\=\s*([^;]*).*$)|^.*$/, "$1");
				if(userName != null && userName != ""){
					url = "index.html?username=" + userName + "&";
				}
				
				if(res.length != 0) {
					for(var i = 0; i < res.length; i++) {
						var title = resMap.get(res[i].literatureId);
						html += "<a href='" + url + "literatureId="+res[i].literatureId+"' class='list-group-item'  style='font-size: 20px;'>" + title;
						html += "<table style='font-size: 10px;'><tr><td>";
						var content = res[i].literatureObjectText;
						var lastIndex;
						var htmlTmp = "";
						while(true) {
							if(content != null) {
								var index = content.lastIndexOf(literatureObjectText);
								if(index != -1) {
									htmlTmp = "<a style='color: red;'>"+literatureObjectText+"</a>"+content.substr(index+literatureObjectText.length, content.length-index-literatureObjectText.length);
									content = content.substr(0, index);
									lastIndex = index;
								} else {
									htmlTmp = content + htmlTmp;
									break;
								}
							}
						}
						html = html + htmlTmp + "</td></tr></table></a>";
						document.getElementById("listLit").innerHTML = html;
					}
				} else {
					html = "<a class='list-group-item' style='font-size: 20px; text-align: center;'><br>没有找到匹配的记录<br></a>"
					document.getElementById("listLit").innerHTML = html;
				}
			}
		});
		
		$.ajax({ 
			type: 'POST',
			url: 'kdecm/posPobLit/postil_search_pub?postilContent='+postilContent,
			contentType: 'application/json',
			async: false,
			success:function(res){
				var html = "";
				
				var url = "login.html?";
				var userName = document.cookie.replace(/(?:(?:^|.*;\s*)username\s*\=\s*([^;]*).*$)|^.*$/, "$1");
				if(userName != null && userName != ""){
					url = "index.html?username=" + userName + "&";
				}
				
				if(res.length != 0) {
					for(var i = 0; i < res.length; i++) {
						html += "<a href='"+ url + "literatureId="+res[i].literatureId+"' class='list-group-item'  style='font-size: 20px;'>" + res[i].literatureName;
						html += "<table style='font-size: 10px;'><tr><td>";
						var content = res[i].postilContent;
						var lastIndex;
						var htmlTmp = "";
						while(true) {
							if(content != null) {
								var index = content.lastIndexOf(postilContent);
								if(index != -1) {
									htmlTmp = "<a style='color: red;'>"+postilContent+"</a>"+content.substr(index+postilContent.length, content.length-index-postilContent.length);
									content = content.substr(0, index);
									lastIndex = index;
								} else {
									htmlTmp = content + htmlTmp;
									break;
								}
							}
						}
						html = html + htmlTmp + "</td></tr></table></a>";
						document.getElementById("listPos").innerHTML = html;
					}
				} else {
					html = "<a class='list-group-item' style='font-size: 20px; text-align: center;'><br>没有找到匹配的记录<br></a>"
					document.getElementById("listPos").innerHTML = html;
				}
			}
		});
	}
	
	var vm = new Vue({
		el:'#searchText',
		data: {
			keyword: '',
		},
		methods : {
			search : function(event) {
				var keyword = vm.keyword;
				if(keyword == "") {
					var queryString = window.location.href;
					window.location.href = queryString;
				} else {
					var queryString = "searchResult.html?keyword="+keyword;
					queryString = encodeURI(queryString);
					window.location.href = queryString;
				}
			}
		}
	})