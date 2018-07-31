/**
 * 编辑-js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		literature: {
			literatureId: 0
		}
	},
	methods : {
		setForm: function() {
			$.SetForm({
				url: '../../kdecm/literature/info?_' + $.now(),
		    	param: vm.literature.literatureId,
		    	success: function(data) {
		    		vm.literature = data;
		    	}
			});
		},
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.ConfirmForm({
		    	url: '../../kdecm/literature/update?_' + $.now(),
		    	param: vm.literature,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})