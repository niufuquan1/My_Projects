$(function () {
	vm.graph.groupId = localStorage.getItem("currentGroupId");
	initialPage();
	getGrid();
	$('#dataGrid').on('click-row.bs.table', function (e, row, element){
		vm.graph = row;
//		console.log(row);
	});
});

function initialPage() {
	$(window).resize(function() {
		$('#dataGrid').bootstrapTable('resetView', {height: $(window).height()-56});
	});
}

function getGrid() {
	$('#dataGrid').bootstrapTableEx({
		url: '../../kdecm/graph/listOfGraph?_' + $.now(),
		height: $(window).height()-56,
		queryParams: function(params){
			params.graphName = vm.keyword;
			params.groupId = vm.graph.groupId;
			return params;
		},
		columns: [
			{checkbox: true},
			{field : "graphId", title : "批注网络ID", width : 200}, 
			{field : "graphName", title : "网络名称", width : 200},
			{field : "graphCreatorName", title : "创建者名称", width : 200},
			{field : "groupId", title : "批注组", width : 200}
		]
	})
}

var vm = new Vue({
	el:'#dpLTE',
	data: {
		keyword: null,
		graph: {
			graphId:null,
			graphName:null,
			graphCreatorId:null,
			graphCreatorName:null,
			groupId:null
		},
	},
	methods : {
		load: function() {
			$('#dataGrid').bootstrapTable('refresh');
		},
		add: function() {
			if(top.vm.user.userId != null){
				dialogOpen({
					title: '新建批注网络',
					url: 'kdecm/graph/add.html?_' + $.now(),
					width: '420px',
					height: '350px',
					success: function(iframeId){
						top.frames[iframeId].vm.graph.graphCreatorId = top.vm.user.userId;
						top.frames[iframeId].vm.graph.graphCreatorName = localStorage.getItem("username");
						top.frames[iframeId].vm.graph.groupId = localStorage.getItem("currentGroupId");
					},
					yes : function(iframeId) {
						top.frames[iframeId].vm.acceptClick();
					},
				});
			}
		},
		edit: function() {
			if(vm.graph.graphName != null){
				dialogOpen({
					title: '编辑',
					url: 'kdecm/graph/edit.html?_' + $.now(),
					width: '420px',
					height: '350px',
					success: function(iframeId){
						top.frames[iframeId].vm.graph = vm.graph;
						top.frames[iframeId].vm.setForm();
					},
					yes: function(iframeId){
						top.frames[iframeId].vm.acceptClick();
					}
				});
			}
		},
		enter: function() {
			if(vm.graph.graphId != null)
			{
				var queryString = "../../kdecm/graph/create.html?graphId=" + vm.graph.graphId + "&graphName=" + vm.graph.graphName + 
						"&graphCreatorId=" + vm.graph.graphCreatorId + "&graphCreatorName=" + vm.graph.graphCreatorName;
				window.location.href = queryString;
				console.log(queryString);
			}
		},
		remove: function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections'), ids = [];	
			if(checkedArray(ck)){
				$.each(ck, function(idx, item){
					ids[idx] = item.graphId;
				});
				$.RemoveForm({
					url: '../../kdecm/graph/removeGraph?_' + $.now(),
			    	param: ids,
			    	success: function(data) {
			    		vm.load();
			    	}
				});
			}
		}
	}
})