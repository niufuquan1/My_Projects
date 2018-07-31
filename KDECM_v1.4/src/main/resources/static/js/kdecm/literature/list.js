/**
 * js
 */

$(function () {
	initialPage();
	getGrid();
	$('#dataGrid').on('click-row.bs.table', function (e, row, element){
		vm.literature.literatureId = row.literatureId;
		vm.literature.literatureName = row.literatureName;
	});
	
});


function initialPage() {
	$(window).resize(function() {
		$('#dataGrid').bootstrapTable('resetView', {height: $(window).height()-56});
	});
}

function getGrid() {
	$('#dataGrid').bootstrapTableEx({
		url: '../../kdecm/literature/list?_' + $.now(),
		height: $(window).height()-56,
		queryParams: function(params){
			params.name = vm.keyword;
			return params;
		},
		columns: [
			{checkbox: true},
			{field : "literatureName", title : "文献名称", width : "100px"}, 
			{field : "literatureTime", title : "文献上传时间", width : "100px"}
		]
	})
}



var vm = new Vue({
	el:'#dpLTE',
	data: {
		keyword: null,
		literature:{
			literatureId:null,
			literatureName:null
		}
	},
	methods : {
		load: function() {
			$('#dataGrid').bootstrapTable('refresh');
		},
		save: function() {
			dialogOpen_({
				title: '新增',
				url: 'kdecm/literature/add1.html?_' + $.now(),
				width: '350px',
				height: '300px',
				yes : function(iframeId) {
					top.frames[iframeId].vm.acceptClick();
				},
			});
		},
		litDet: function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections');
			if(checkedRow(ck)){
				var queryString = "../../kdecm/literature/literatureDetail2.html?literatureId="+ck[0].literatureId+"&userId=1&chapter=0";
				window.location.href = queryString;
			}
		},
		edit: function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections');
			if(checkedRow(ck)){
				dialogOpen({
					title: '编辑',
					url: 'kdecm/literature/edit.html?_' + $.now(),
					width: '420px',
					height: '350px',
					success: function(iframeId){
						top.frames[iframeId].vm.literature.literatureId = ck[0].literatureId;
						top.frames[iframeId].vm.setForm();
					},
					yes: function(iframeId){
						top.frames[iframeId].vm.acceptClick();
					}
				});
			}
		},
		remove: function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections'), ids = [];	
			if(checkedArray(ck)){
				$.each(ck, function(idx, item){
					ids[idx] = item.literatureId;
				});
				$.RemoveForm({
					url: '../../kdecm/literature/remove?_' + $.now(),
			    	param: ids,
			    	success: function(data) {
			    		vm.load();
			    	}
				});
			}
		},
		litLevel: function() {
			if(vm.literature.literatureId != null)
			{
				var queryString = "../../kdecm/postil/level.html?literatureId="+vm.literature.literatureId;
				window.location.href = queryString;
			}
		},
		trend: function() {
			$.ajax({
				type: 'POST',
				url: '../../kdecm/literature/groupliterature',
				contentType: 'application/json',
				async: false,
				success:function(e){
					var outputyear = [];
					var outputrefer = [];
					var start = e[0].literatureAge;
					var end = e[e.length-1].literatureAge;
					var tip = 0;
					if(start == end){
						object.year = start;
						object.times = e.length;
						outputyear.push(object);
					}
					if(start>end){
						for(var i = 0; i< e.length; i++){
							var object = {};
							if(e[i].literatureAge == start){
								if(e[i].literatureAge > end){
									tip++;
								}
								if(e[i].literatureAge == end){
									tip = e.length - i+1;
									object.year = end;
									object.times = tip;
									outputyear.push(object);
									break;
								}
							}
							if(e[i].literatureAge < start){
								object.year = start;
								object.times = tip;
								outputyear.push(object);
								tip = 0;
								start = e[i].literatureAge;
								tip++;
							}
						}
					}
					for(var j=0; j<e.length; j++){
						var reference = {};
						reference.title = e[j].literatureName;
						if(e[j].literatureReference == null){
							reference.chance = 0;
						}
						else{
							var refer = e[j].literatureReference.split("》");
							var len = refer.length;
							reference.chance = len;
						}
						outputrefer.push(reference);
					}
					
					createTop(outputyear);
					createBottom(outputrefer);
					$('#window').modal('show');
					console.log(outputyear);
					console.log(outputrefer);
				}
			});
		},
		mainword: function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections');
			var rst = [];
			if(checkedRow(ck)){
				var literatureId = ck[0].literatureId;
				$.ajax({ 
					type: 'POST',
					url: '../../kdecm/literature/mainWordShow?literatureId='+literatureId,
					contentType: 'application/json',
					async: false,
					success:function(e){
						for(var i = 0; i < e.length-1; i++){
							for(var j = i+1; j < e.length; j++){
								var str1 = e[i];
								var str2 = e[j];
								$.ajax({ 
									type: 'POST',
									url: '../../kdecm/literature/mainWordCompare?str1='+str1+'&str2='+str2+'&literatureId='+literatureId,
									contentType: 'application/json',
									async: false,
									success:function(f){
										var len = f.length-1;
										var idc = [];
										 for(var k = 0; k<len; k++){
											 if(f[k] == f[k+1]){
												 idc.push(f[k]);
											 }
											 else{
												 continue;
											 }
										 }
										 var check = {};
										 check.obj1 = str1;
										 check.obj2 = str2;
										 check.ids = idc;
										 rst.push(check);
										 console.log(rst);
									}
								});
							}
						}
					}
				});
			}
		},
		author: function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections');
			var rst = [];
			if(checkedRow(ck)){
				var literatureId = ck[0].literatureId;
				$.ajax({ 
					type: 'POST',
					url: '../../kdecm/literature/authorShow?literatureId='+literatureId,
					contentType: 'application/json',
					async: false,
					success:function(e){
						for(var i = 0; i < e.length-1; i++){
							for(var j = i+1; j < e.length; j++){
								var str1 = e[i];
								var str2 = e[j];
								$.ajax({ 
									type: 'POST',
									url: '../../kdecm/literature/authorCompare?str1='+str1+'&str2='+str2+'&literatureId='+literatureId,
									contentType: 'application/json',
									async: false,
									success:function(f){
										var len = f.length-1;
										var idc = [];
										 for(var k = 0; k<len; k++){
											 if(f[k] == f[k+1]){
												 idc.push(f[k]);
											 }
											 else{
												 continue;
											 }
										 }
										 var check = {};
										 check.obj1 = str1;
										 check.obj2 = str2;
										 check.ids = idc;
										 rst.push(check);
										 console.log(rst);
									}
								});
							}
						}
					}
				});
			}
		},
		literature: function() {
			if(vm.literature.literatureId != null)
			{
				var ck = $('#dataGrid').bootstrapTable('getSelections'), ids = [];	
				console.log(ck);
				var groupId = localStorage.getItem("currentGroupId");
				console.log(localStorage);
				console.log(groupId);
				var userId = localStorage.getItem("userId");
				console.log(userId);
				if(checkedArray(ck)){
					$.each(ck, function(idx, item){
						ids[idx] = item.literatureId;
					});
				}
				console.log(ids);
				//alert(ids);
				$.ajax({
					type: 'POST',
					url:'../../kdecm/literatureSimilarity/getLiteratureSimilarity',
					async:false,
					data:{ids:JSON.stringify(ids),groupId:groupId,userId:userId},
					success:function(e){
						console.log(e);
					}
				});
			}else{
				dialogMsg("请选择至少一篇文献。");
			}
		},
		postil: function() {
			if(vm.literature.literatureId != null)
			{
			
			}
		},
	}
})