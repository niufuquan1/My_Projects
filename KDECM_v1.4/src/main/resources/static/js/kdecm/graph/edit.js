/**
 * 编辑-js
 */

var vm = new Vue({
	el:'#dpLTE',
	data: {
		graph: {
			graphId:null,
			graphName:null,
			graphCreatorId:null,
			graphCreatorName:null,
			groupId:null
		},
	},
	methods : {
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.ConfirmForm({
		    	url: '../../kdecm/graph/updateGraph?_' + $.now(),
		    	param: vm.graph,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})