/**
 * 新增-js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		postilObject: {
			postilObjectId: 0
		}
	},
	methods : {
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.SaveForm({
		    	url: '../../kdecm/postil_object/save?_' + $.now(),
		    	param: vm.postilObject,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})
