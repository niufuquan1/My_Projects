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

var storage = window.localStorage; 

function getGrid() {	
	$('#dataGrid').bootstrapTableEx({
		url: '../../kdecm/posPobLit/postil_search?_' + $.now(),
		height: $(window).height()-56,
		queryParams: function(params){
			params.postilContent = vm.keyword;
			return params;
		},
		responseHandler:function(res){ // res为从服务器请求到的数据
	        return res;  
	    },
		columns: [
			{checkbox: true},
			{field : "userId", title : "用户ID", width : "20px"}, 
			{field : "postilObjectText", title : "批注对象", width: "150px"},
			{field : "postilContent", title : "批注内容", width : "100px"}, 
			{field : "literatureName", title : "文献名称", width : "100px"},
		]
	})
}

var vm = new Vue({
	el:'#dpLTE',
	data: {
		keyword: null
	},
	methods : {
		load: function() {
			$('#dataGrid').bootstrapTable('refresh');
		},
		search: function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections');
			if(checkedRow(ck)){
				console.log(ck[0].postilId);
				var queryString = "../../kdecm/literature/literatureDetail2.html?literatureId="+ck[0].literatureId+"&userId=1&chapter=0";
				window.location.href = queryString;
			}
		}
	}
});