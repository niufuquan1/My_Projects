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

function getGrid() {
	$('#dataGrid').bootstrapTableEx({
		url: '../../kdecm/postil/listPostilForGroup',
		height: $(window).height()-56,
		queryParams: function(params){
			params.postilContent= vm.keyword;
			params.groupId=localStorage.getItem("currentGroupId");
			console.log(params);
			return params;
		},
		columns: [
			{checkbox: true},
			{field : "userId", title : "编写用户", width : "100px"}, 
			{field : "postilTime", title : "创建时间", width : "100px"}, 
			{field : "postilContent", title : "批注内容", width : "100px"}
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
		}	
	}
})