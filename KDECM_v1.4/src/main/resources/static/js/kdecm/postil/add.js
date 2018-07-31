/**
 * 新增-js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		postil: {
			postilId: 0
		}
	},
	methods : {
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.SaveForm({
		    	url: '../../kdecm/postil/save?_' + $.now(),
		    	param: vm.postil,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})
