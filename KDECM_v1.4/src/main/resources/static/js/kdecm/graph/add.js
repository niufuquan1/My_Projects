var vm = new Vue({
	el: '#dpLTE',
	data: {
		graph:{
			graphName: null,
			graphCreatorId: null,
			graphCreatorName: null,
			groupId: null,
		},

	},
	methods: {
		acceptClick: function() {
			if(!$('#form').Validform()) {
				return false;
			}
			if(vm.graph.groupId == null){
				$.SaveForm({
					url: '../../kdecm/graph/addOpenGraph?graphId='+ $.now() +
						'&graphName=' + vm.graph.graphName +
						'&graphCreatorId=' + vm.graph.graphCreatorId +
						'&graphCreatorName=' + vm.graph.graphCreatorName,
					type: 'POST',
					success: function(data) {
						$.currentIframe().vm.load();
					},
				});
			}
			else{
				$.SaveForm({
					url: '../../kdecm/graph/addGraph?graphId='+ $.now() +
						'&graphName=' + vm.graph.graphName +
						'&graphCreatorId=' + vm.graph.graphCreatorId +
						'&graphCreatorName=' + vm.graph.graphCreatorName +
						'&groupId=' + vm.graph.groupId,
					type: 'POST',
					success: function(data) {
						$.currentIframe().vm.load();
					},
				});
			}
		}
	}
})