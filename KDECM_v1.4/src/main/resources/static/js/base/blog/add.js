/**
 * 新增-博客js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		tBlog: {
			id: 0
		}
	},
	methods : {
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.SaveForm({
		    	url: '../../sys/blog/save?_' + $.now(),
		    	param: vm.tBlog,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})
