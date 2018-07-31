/**
 * 新增-js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		literatureObject: {
			literatureObjectId: 0
		}
	},
	methods : {
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.SaveForm({
		    	url: '../../kdecm/literature_object/save?_' + $.now(),
		    	param: vm.literatureObject,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})
