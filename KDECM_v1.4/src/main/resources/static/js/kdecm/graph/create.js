$(function () {
	initialPage();
	GetRequest();
	getGrid();
	$('#dataGrid2').on('click-row.bs.table', function (e, row, element){
		relationId = row.relationId;
		$.ajax({
			url: '../../kdecm/posPobLit/posPobLitById?postilId=' + row.postilId1,
			type: 'POST',
			success: function(data) {
				vm.posPobLit1 = data;
			},
		})
		$.ajax({
			url: '../../kdecm/posPobLit/posPobLitById?postilId=' + row.postilId2,
			type: 'POST',
			success: function(data) {
				vm.posPobLit2 = data;
			},
		})
	});
	
});

function initialPage() {
	$(window).resize(function() {
		$('#dataGrid1').bootstrapTable('resetView', {height: 500});
		$('#dataGrid2').bootstrapTable('resetView', {height: 500});
		$('#dataGrid3').bootstrapTable('resetView', {height: 500});
	});
}

function getGrid() {
	$('#dataGrid1').bootstrapTableEx({
		url: '../../kdecm/literatureUser/list?_' + $.now(),
		height: ($(window).height()-56)/2,
		height:500,
		queryParams: function(params){
			params.literatureName = vm.searchLit;
			return params;
		},
		columns: [
			{checkbox: true, width : 50},
			{field : "literatureId", title : "文献ID",align:"center", width : 100}, 
			{field : "literatureName", title : "文献名称",align:"center", width : 250}, 
			{field : "username", title : "创建者",align:"center", width : 250}, 
		]
	});
	$('#dataGrid2').bootstrapTableEx({
		url: '../../kdecm/postil_relation/list?_' + $.now(),
		height:500,
		queryParams: function(params){
			params.name = vm.searchRel;
			params.groupId = groupId;
			return params;
		},
		columns: [
			{checkbox: true, width : 50},
			{field : "relationName", title : "批注关系名称", align:"center",width : 150},
			{field : "relationTypeName", title : "批注关系类型",align:"center", width : 100}, 
			{field : "postilName1", title : "批注词1", align:"center",width : 100},
			{field : "postilName2", title : "批注词2", align:"center",width : 100},
			{field : "userName", title : "创建者", align:"center",width : 100},
		]
	});
	$('#dataGrid3').bootstrapTableEx({
		url: '../../kdecm/graph/listOfNode?_' + $.now(),
		height:500,
		queryParams: function(params){
			params.graphId = vm.graph.graphId;
			return params;
		},
		columns: [
			{checkbox: true, width : 50},
			{field : "postilId",title : "批注ID",align:"center",width:100,}, 
			{field : "postilContent",title : "批注内容",align:"center",width:150}, 
			{field : "literatureName",title : "文献名称",align:"center",width:150}, 
			{field : "postilObjectText",title : "批注实体内容",align:"center",width:150}, 
		]
	});
	$('#dataGrid4').bootstrapTableEx({
		url: '../../kdecm/graph/listOfEdge?_' + $.now(),
		height:500,
		queryParams: function(params){
			params.graphId = vm.graph.graphId;
			return params;
		},
		columns: [
			{checkbox: true, width : 50},
			{field : "relationName", title : "批注关系名称", align:"center",width : 150},
			{field : "relationTypeName", title : "批注关系类型",align:"center", width : 100}, 
			{field : "postilName1", title : "批注词1", align:"center",width : 100},
			{field : "postilName2", title : "批注词2", align:"center",width : 100},
			{field : "userName", title : "创建者", align:"center",width : 100},
		]
	});
}

function GetRequest() {   
   var url = location.search; //获取url中"?"符后的字串   
   var theRequest = new Object();   
   if (url.indexOf("?") != -1) {   
      var str = url.substr(1);   
      strs = str.split("&");
      vm.graph.graphId = unescape(strs[0].split("=")[1]);
      vm.graph.graphName = decodeURI(strs[1].split("=")[1]);
      vm.graph.graphCreatorId = unescape(strs[2].split("=")[1]);
      vm.graph.graphCreatorName = decodeURI(strs[3].split("=")[1]);
//      console.log(vm.graph.graphId);
//      console.log(vm.graph.graphName);
//      console.log(vm.graph.graphCreatorId);
//      console.log(vm.graph.graphCreatorName);
   }   
}   

var groupId = localStorage.getItem("currentGroupId");
var userId = top.vm.user.userId;
var userName = localStorage.getItem("username");
var literatureId;
var relationId;

var vm = new Vue({
	el:'#dpLTE',
	data: {
		type:0,
		searchLit:null,
		searchRel:null,
		graph:{
			graphId:null,
			graphName:null,
			graphCreatorId:null,
			graphCreatorName:null,
		},
		posPobLit1:{
			postilId:null,
			postilContent:null,
			literatureName:null,
			postilObjectText:null,
		},
		posPobLit2:{
			postilId:null,
			postilContent:null,
			literatureName:null,
			postilObjectText:null,
		},
	},
	methods : {
		loadLit: function() {
			$('#dataGrid1').bootstrapTable('refresh');
		},
		loadRel: function() {
			$('#dataGrid2').bootstrapTable('refresh');
		},
		addLit: function() {
			var ck = $("#dataGrid1").bootstrapTable('getSelections'), ids = [];
			if(checkedArray(ck)){
				$.each(ck, function(idx, item){
					ids[idx] = item.literatureId;
				});
				$.ajax({
					url: '../../kdecm/graph/addNodeEdgeByLit?graphId=' + vm.graph.graphId + "&groupId=" + vm.graph.groupId +'&literatureId=' + ids.join(","),
					type: 'POST',
					success: function(data) {
						$('#dataGrid3').bootstrapTable('refresh');
						$('#dataGrid4').bootstrapTable('refresh');
						//翻滚
						$("html, body").animate(
								{scrollTop:$("#graphNodeList").offset().top}, {duration: 500,easing: "swing"}
						);
						dialogMsg("向节点列表中添加"+""+"成功!");
					},
					error : function() {  
						$('#dataGrid3').bootstrapTable('refresh');
						$('#dataGrid4').bootstrapTable('refresh');
						dialogMsg("向节点列表中添加"+""+"失败!");
					}
				});
			}
		},
		addRel: function() {
			var ck = $('#dataGrid2').bootstrapTable('getSelections'), ids = [];
			if(checkedArray(ck)){
				$.each(ck, function(idx, item){
					ids[idx] = item.relationId;
				});
				$.ajax({
					url: '../../kdecm/graph/addGraphEdge?graphId=' + vm.graph.graphId + 
						'&relationId=' + ids.join(","),
					type: 'POST',
					success: function(data) {
						$('#dataGrid3').bootstrapTable('refresh');
						$('#dataGrid4').bootstrapTable('refresh');
					},
					error : function() {  
						$('#dataGrid3').bootstrapTable('refresh');
						$('#dataGrid4').bootstrapTable('refresh');
			        }
				});
			}
		},
		removeNode: function() {
			var ck = $('#dataGrid3').bootstrapTable('getSelections'), ids = [];	
			if(checkedArray(ck)){
				$.each(ck, function(idx, item){
					ids[idx] = item.graphNodeId;
				});
				$.RemoveForm({
					url: '../../kdecm/graph/removeGraphNode?_' + $.now(),
					param: ids,
			    	success: function(data) {
			    		$('#dataGrid3').bootstrapTable('refresh');
			    	}
				});
			}
		},
		removeRel: function() {
			var ck = $('#dataGrid4').bootstrapTable('getSelections'), ids = [];	
			if(checkedArray(ck)){
				$.each(ck, function(idx, item){
					ids[idx] = item.graphEdgeId;
				});
				$.RemoveForm({
					url: '../../kdecm/graph/removeGraphEdge?_' + $.now(),
					param: ids,
			    	success: function(data) {
			    		$('#dataGrid4').bootstrapTable('refresh');
			    	}
				});
			}
		},
		show: function() {
			sessionStorage.setItem("graphId", vm.graph.graphId); 
			dialogOpen_({
				title: '批注网络图',
				url: 'kdecm/graph/show.html?graphId='+vm.graph.graphId + '&type='+vm.type,
				width: '800px',
				height: '800px',
				yes : function(iframeId) {
					top.frames[iframeId].vm.acceptClick();
				},
			});
		},
		selectTarget:function(){
			//alert(vm.type);
			$.ajax({
				url: '../../kdecm/graph/listOfEdgeByType?type=' + vm.type,
				type: 'POST',
				success: function(data) {
					$('#dataGrid3').bootstrapTable('refresh');
					$('#dataGrid4').bootstrapTable('refresh');
				},
				error : function() {  
					$('#dataGrid3').bootstrapTable('refresh');
					$('#dataGrid4').bootstrapTable('refresh');
		        },
			});
		}
//		level: function() {
//			var queryString = "../../kdecm/postil/level.html?literatureId=1";
//			window.location.href = queryString;
//			console.log(queryString);
//			
//			dialogOpen({
//				title: '批注网络图',
//				url: 'kdecm/postil/level.html?graphId='+vm.graph.graphId + '&type='+vm.type,
//				url: 'kdecm/postil/level.html',
//				width: '800px',
//				height: '800px',
//				yes : function(iframeId) {
//					top.frames[iframeId].vm.acceptClick();
//				},
//			});
//		},
	}
})
