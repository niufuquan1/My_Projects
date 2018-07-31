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
		url: '../../kdecm/posPobLit/listForPageuserown?_' + $.now(),
		height: $(window).height()-56,
		queryParams: function(params){
			params.userId = vm.userId;
			return params;
		},
		columns: [
			{checkbox: true},
			{field : "postilTime", title : "批注时间", width : "40px"}, 
			{field : "postilContent", title : "批注内容", width : "100px"}, 
			{field : "literatureName", title : "文献名称", width : "100px"},
		]
	})
}

var vm = new Vue({	   
		el:'#dpLTE',
	    data: {	 
	    userId : top.vm.user.userId   	   
	    },
	methods : {
		load: function() {
			$('#dataGrid').bootstrapTable('refresh');
		},
		search: function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections');
			if(checkedRow(ck)){
				console.log(ck[0].postilId);
				var queryString = "../../kdecm/literature/literatureDetail2.html?literatureID="+ck[0].literatureId+"&userId=1&chapter=0";
				window.location.href = queryString;
			}
		}
	}
});