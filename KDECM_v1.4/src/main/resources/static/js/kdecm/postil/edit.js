/**
 * 编辑-js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		postil: {
			postilId: 0
		}
	},
	methods : {
		setForm: function() {
			$.SetForm({
				url: '../../kdecm/postil/info?_' + $.now(),
		    	param: vm.postil.postilId,
		    	success: function(data) {
		    		vm.postil = data;
		    	}
			});
		},
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.ConfirmForm({
		    	url: '../../kdecm/postil/update?_' + $.now(),
		    	param: vm.postil,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})