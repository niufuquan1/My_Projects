var groupId = localStorage.getItem("currentGroupId");

var vm = new Vue({
	el:'#dpLTE',
	data: {
		postilRelation: {
			relationId:null,
			relationName:null,
			relationType:null,
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
		}
	},
	methods : {
		setForm: function() {
			$.ajax({
				url: '../../kdecm/postil_relation/editRel?relationName='+vm.postilRelation.relationName
				+ '&postilId1='+ vm.posPobLit1.postilId + '&postilId2='+ vm.posPobLit2.postilId  + '&groupId='+ groupId,
				type: 'POST',
		    	success: function(data) {
		    		vm.postilRelation = data;
		    	}
			});
		},
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.ConfirmForm({
		    	url: '../../kdecm/postil_relation/update?_' + $.now(),
		    	param: vm.postilRelation,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})