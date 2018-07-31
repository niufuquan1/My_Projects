/**
 * 编辑-js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		postilObject: {
			postilObjectId: 0
		}
	},
	methods : {
		setForm: function() {
			$.SetForm({
				url: '../../kdecm/postil_object/info?_' + $.now(),
		    	param: vm.postilObject.postilObjectId,
		    	success: function(data) {
		    		vm.postilObject = data;
		    	}
			});
		},
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.ConfirmForm({
		    	url: '../../kdecm/postil_object/update?_' + $.now(),
		    	param: vm.postilObject,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})