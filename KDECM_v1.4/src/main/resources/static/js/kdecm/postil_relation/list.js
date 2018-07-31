$(function () {
	initialPage();
	getGrid();
	$('#dataGrid').on('click-row.bs.table', function (e, row, element){  
		relationName = row.relationName;
		console.log(row.relationName);
		console.log(relationName);
	    $('#dataGrid1').bootstrapTableEx({
			url : '../../kdecm/posPobLit/list?_' + $.now(),
			height : ($(window).height()-56)/2,
			singleSelect : true,
			queryParams : function(params) {
				params.relationName = relationName;
				params.flag = 'flag';
				params.groupId = groupId;
				return params;
			},
			columns : [ 
				{checkbox : true ,width:50}, 
				{field : "postilId",title : "批注1 ID",align:"center",width:100,}, 
				{field : "postilContent",title : "批注1内容",align:"center",width:200}, 
				{field : "literatureName",title : "文献名称",align:"center",width:200}, 
				{field : "postilObjectText",title : "批注1实体内容",align:"center",width:250}, 
			]
		});
	    $('#dataGrid1').bootstrapTable('refresh');
	});
	$('#dataGrid1').on('click-row.bs.table', function (e, row, element){ 
		postilId1 = row.postilId;
		console.log(row.postilId);
		$('#dataGrid2').bootstrapTableEx({
			url : '../../kdecm/posPobLit/list?_' + $.now(),
			height : ($(window).height()-56)/2,
			singleSelect : true,
			queryParams : function(params) {
				params.relationName = relationName;
				params.postilId1 = postilId1;
				params.groupId = groupId;
				return params;
			},
			columns : [ 
				{checkbox : true ,width:50}, 
				{field : "postilId",title : "批注2 ID",align:"center",width:100,}, 
				{field : "postilContent",title : "批注2内容",align:"center",width:200}, 
				{field : "literatureName",title : "文献名称",align:"center",width:200}, 
				{field : "postilObjectText",title : "批注2实体内容",align:"center",width:250}, 
			]
		});
		$('#dataGrid2').bootstrapTable('refresh');
	});
	
});

function initialPage() {
	$(window).resize(function() {
		$('#dataGrid').bootstrapTable('resetView', {height: $(window).height()-56});
	});
}

function getGrid() {
	$('#dataGrid').bootstrapTableEx({
		url: '../../kdecm/postil_relation/listDistinct?_' + $.now(),
		height: $(window).height()-56,
		singleSelect : true,
		queryParams: function(params){
			params.name = vm.keyword;
			params.groupId = groupId;
			return params;
		},
		columns: [
			{checkbox: true, width:50},
			{field : "relationName", title : "批注关系名称",align:"center",width:200},
		]
	})
}

var groupId = localStorage.getItem("currentGroupId");
var relationName;
var postilId1;
var postilId2;

var vm = new Vue({
	el:'#dpLTE',
	data: {
		keyword: null,
	},
	methods : {
		load: function() {
			$('#dataGrid').bootstrapTable('refresh');
		},
		edit: function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections');
			var ck1 = $('#dataGrid1').bootstrapTable('getSelections');
			var ck2 = $('#dataGrid2').bootstrapTable('getSelections');
			if(checkedRow(ck)&&checkedRow(ck1)&&checkedRow(ck2)){
				dialogOpen({
					title: '编辑',
					url: 'kdecm/postil_relation/edit.html?_' + $.now(),
					width: '600px',
					height: '500px',
					success: function(iframeId){
						top.frames[iframeId].vm.postilRelation.relationName = ck[0].relationName;
						top.frames[iframeId].vm.posPobLit1 = ck1[0];
						top.frames[iframeId].vm.posPobLit2 = ck2[0];
						top.frames[iframeId].groupId = groupId;
						top.frames[iframeId].vm.setForm();
					},
					yes: function(iframeId){
						top.frames[iframeId].vm.acceptClick();
					}
				});
			}
		},
		remove: function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections');
			var ck1 = $('#dataGrid1').bootstrapTable('getSelections');
			var ck2 = $('#dataGrid2').bootstrapTable('getSelections');
			if(checkedRow(ck)&&checkedRow(ck1)&&checkedRow(ck2)){
				$.RemoveForm({
					url: '../../kdecm/postil_relation/removeRel?relationName='+ck[0].relationName
					+'&postilId1=' + ck1[0].postilId + '&postilId2=' + ck2[0].postilId + '&groupId='+ groupId,
					type: 'POST',
			    	success: function(data) {
			    		vm.load();
			    	}
				});
			}
		}
	}
})