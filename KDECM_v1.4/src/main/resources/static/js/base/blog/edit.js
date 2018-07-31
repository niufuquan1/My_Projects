/**
 * 编辑-博客js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		tBlog: {
			id: 0
		}
	},
	methods : {
		setForm: function() {
			$.SetForm({
				url: '../../sys/blog/info?_' + $.now(),
		    	param: vm.tBlog.id,
		    	success: function(data) {
		    		vm.tBlog = data;
		    	}
			});
		},
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.ConfirmForm({
		    	url: '../../sys/blog/update?_' + $.now(),
		    	param: vm.tBlog,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})