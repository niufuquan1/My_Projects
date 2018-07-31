/**
 * 编辑-js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		literatureObject: {
			literatureObjectId: 0
		}
	},
	methods : {
		setForm: function() {
			$.SetForm({
				url: '../../kdecm/literature_object/info?_' + $.now(),
		    	param: vm.literatureObject.literatureObjectId,
		    	success: function(data) {
		    		vm.literatureObject = data;
		    	}
			});
		},
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.ConfirmForm({
		    	url: '../../kdecm/literature_object/update?_' + $.now(),
		    	param: vm.literatureObject,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})