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
function getQueryString(name) {
	var result = window.location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
	if (result == null || result.length < 1) {
    	return "";
	}
	return result[1];
}
function getGrid() {
	$('#dataGrid').bootstrapTableEx({
		url: '../../kdecm/group/listgroupbyuserid',
		height: $(window).height()-56,
		queryParams: function(params){
			params.name = vm.keyword;
			params.username=localStorage.getItem("username");
			return params;
		},
		columns: [
			{checkbox: true},
			{field : "groupId", title : "批注组ID", width : "100px"}, 
			{field : "groupName", title : "批注组名称", width : "100px"}, 
			{field : "userId", title : "创建者ID", width : "100px"}
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
	}
})