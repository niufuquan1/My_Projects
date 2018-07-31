$(function() {
	vm.groupId = localStorage.getItem("currentGroupId");
	if(vm.groupId == null || vm.groupId==""){
		alert("请先进入批注组");
	}
	initialPage();
	getGrid();
});

function initialPage() {
	$(window).resize(function() {
		$('#dataGrid1').bootstrapTable('resetView', {
			height : $(window).height() - 256
		});
		$('#dataGrid2').bootstrapTable('resetView', {
			height : $(window).height() - 256
		});
	});
}

function getGrid() {
	$('#dataGrid1').bootstrapTableEx({
		url : '../../kdecm/posPobLit/list?_' + $.now(),
		height : $(window).height() - 256,
		singleSelect : true,
		queryParams : function(params) {
			params.searchtext = vm.searchtext1;
			params.groupId = vm.groupId;
			return params;
		},
		/*
		 * columns : [ {checkbox : true,width:50,}, {field : "postilId",title :
		 * "批注ID",width:100,}, {field : "postilContent",title :
		 * "批注内容",width:150}, {field : "literatureName",title :
		 * "文献名称",width:150}, {field : "postilObjectText",title :
		 * "批注实体内容",width:250}, {field : "groupId",title : "批注组ID",width:100} ]
		 */
		/*columns : [ {
			checkbox : true,
			width : 50,
		}, {
			field : "postilId",
			title : "批注ID"
		}, {
			field : "postilContent",
			title : "批注内容"
		}, {
			field : "literatureName",
			title : "文献名称"
		}, {
			field : "postilObjectText",
			title : "批注实体内容"
		}, {
			field : "groupId",
			title : "批注组ID"
		} ]*/
		columns : [ {checkbox : true,width:55}, 
			  {field : "postilId",title :"批注ID",width:70}, 
			  {field : "postilContent",title :"批注内容",width:85 }, 
			  {field : "literatureName",title :"文献名称",width:85 },
			  {field : "postilObjectText",title :"批注实体内容",width:160 },
			  {field : "groupId",title : "批注组ID",width:70 } ]
	})
	$('#dataGrid2').bootstrapTableEx({
		url : '../../kdecm/posPobLit/list?_' + $.now(),
		height : $(window).height() - 256,
		singleSelect : true,
		queryParams : function(params) {
			params.searchtext = vm.searchtext2;
			params.groupId = vm.groupId;
			return params;
		},
		/*
		 * columns : [ {checkbox : true,width:50,}, {field : "postilId",title :
		 * "批注ID",align:"center",width:100,}, {field : "postilContent",title :
		 * "批注内容",align:"center",width:150}, {field : "literatureName",title :
		 * "文献名称",align:"center",width:150}, {field : "postilObjectText",title :
		 * "批注实体内容",align:"center",width:250}, {field : "groupId",title :
		 * "批注组ID",align:"center",width:100} ]
		 */
		/*columns : [ {
			checkbox : true,
			width : 50,
		}, {
			field : "postilId",
			title : "批注ID"
		}, {
			field : "postilContent",
			title : "批注内容"
		}, {
			field : "literatureName",
			title : "文献名称"
		}, {
			field : "postilObjectText",
			title : "批注实体内容"
		}, {
			field : "groupId",
			title : "批注组ID"
		} ]*/
		columns : [ {checkbox : true,width:55}, 
			  {field : "postilId",title :"批注ID",width:70}, 
			  {field : "postilContent",title :"批注内容",width:100 }, 
			  {field : "literatureName",title :"文献名称",width:100 },
			  {field : "postilObjectText",title :"批注实体内容",width:160 },
			  {field : "groupId",title : "批注组ID",width:70 } ]
	})
}

var vm = new Vue(
		{
			el : '#dpLTE',
			data : {
				relationName : null,
				relationType : null,
				searchtext1 : null,
				searchtext2 : null,
				userId : top.vm.user.userId,
				userName : localStorage.getItem("username"),
				groupId : null

			},
			methods : {
				search1 : function() {
					$('#dataGrid1').bootstrapTable('refresh');
				},
				search2 : function() {
					$('#dataGrid2').bootstrapTable('refresh');
				},
				add : function() {
					var postil1 = $("#dataGrid1").bootstrapTable(
							'getSelections')[0];
					var postil2 = $("#dataGrid2").bootstrapTable(
							'getSelections')[0];
					if (vm.relationName == null){
						dialogMsg("请输入关系名称");
					} else if (vm.relationType == null) {
						dialogMsg("请选择批注关系");
					} else if (postil1 == null || postil2 == null) {
						dialogMsg("当前未选中批注词");
					} else if (postil1.postilId == postil2.postilId) {
						dialogMsg("请选择不同的批注词");
					} else {
						$.SaveForm({
									url : '../../kdecm/postil_relation/add?relationName='
											+ vm.relationName
											+ '&relationType='
											+ vm.relationType
											+ '&userId='
											+ vm.userId
											+ '&userName='
											+ vm.userName
											+ '&postilId1='
											+ postil1.postilId
											+ '&postilName1='
											+ postil1.postilContent
											+ '&literatureId1='
											+ postil1.literatureId
											+ '&literatureName1='
											+ postil1.literatureName
											+ '&postilId2='
											+ postil2.postilId
											+ '&postilName2='
											+ postil2.postilContent
											+ '&literatureId2='
											+ postil2.literatureId
											+ '&literatureName2='
											+ postil2.literatureName
											+ '&groupId=' + vm.groupId,
									type : 'POST',
									success : function(data) {
										dialogMsg(data.msg, 'success');
									}
								});
					}
				},
			}
		})