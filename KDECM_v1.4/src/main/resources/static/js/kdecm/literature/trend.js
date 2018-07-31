$(function() {
	getData();
	createGraphTop();
	createGraphBottom();
});

var graphId;
var type;
var graphCreatorId;
var graphCreatorName;
var egdes = [];
var postilNodes = [];
		
function getData() {
	var url = location.search; //获取url中"?"符后的字串   
	var theRequest = new Object();   
	if (url.indexOf("?") != -1) {   
		var str = url.substr(1);   
		strs = str.split("&");
		graphId = unescape(strs[0].split("=")[1]);
		type = unescape(strs[1].split("=")[1]);
	}   
	
//    console.log(graphId);
//    console.log(type);
    
	$.ajax({
		url: "../../kdecm/graph/info?graphId=" + graphId + "&type=" + type,
		type: "post",
		//contentType:"json",
		//dataType:"json",
		async: false,
		success: function(graph) {
			//			var graph = JSON.parse(data);
			var simpleEntity = graph.graphSimpleEntity;
			graphId = simpleEntity.graphId;
			graphCreatorId = simpleEntity.graphCreatorId;
			graphCreatorName = simpleEntity.graphCreatorName;
			egdes = graph.gedRelEntity;
			Nodes = graph.gnoPosEntity;
		}
	})
}


function createGraphTop() {
//	console.log(graphId);
//	console.log(graphCreatorId);
//	console.log(graphCreatorName);
//	console.log(egdes);
//	console.log(Nodes);
	var yourChart = echarts.init(document.getElementById("linkFrequency"));
	option = null;
	option = {
	    xAxis: {
	        type: 'category',
	        data: articles
	    },
	    yAxis: {
	        type: 'value'
	    },
		toolbox: {
			x:'85%',
	        // y: 'bottom',
	        feature: {
	            magicType: {
	                type: ['line','bar']
	            },
	            dataView: {},
	            saveAsImage: {
	                pixelRatio: 2
	            }
	        }
	    },
	    dataZoom: [
	        {
	            type: 'slider',
	            show: true,
	            xAxisIndex: [0],
	        },
	        {
	            type: 'slider',
	            show: true,
	            yAxisIndex: [0],
	            left: '95%',
	        },
	        {
	            type: 'inside',
	            xAxisIndex: [0],
	        },
	        {
	            type: 'inside',
	            yAxisIndex: [0],
	        }
	    ],

	    series: [{
	        data: fequency,
	        type: 'bar'
	    }]
	};
	if (option && typeof option === "object") {
	    myChart.setOption(option, true);
	}
}

function createGraphBottom() {
//	console.log(graphId);
//	console.log(graphCreatorId);
//	console.log(graphCreatorName);
//	console.log(egdes);
//	console.log(Nodes);

	var myChart = echarts.init(document.getElementById("linkFrequency"));
	option = null;
	option = {
		title:{
			text:"文献发表总体趋势",
			x:'center',
		},
	    xAxis: {
	    	name:'年份',
	        type: 'category',
	        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
	        //data: year
	    },
	    yAxis: {
	    	name:'被引频次',
	        type: 'value'
	    },
		
		toolbox: {
			x:'85%',
	        // y: 'bottom',
	        feature: {
	            magicType: {
	                type: ['line','bar']
	            },
	            dataView: {},
	            saveAsImage: {
	                pixelRatio: 2
	            }
	        }
	    },
	    dataZoom: [
	        {
	            type: 'slider',
	            show: true,
	            xAxisIndex: [0],
	        },
	        {
	            type: 'slider',
	            show: true,
	            yAxisIndex: [0],
	            left: '95%',
	        },
	        {
	            type: 'inside',
	            xAxisIndex: [0],
	        },
	        {
	            type: 'inside',
	            yAxisIndex: [0],
	        }
	    ],

	    series: [{
	        data:[820, 932, 901, 934, 1290, 1330, 1320],
	        //data: fequency,
	        type: 'line'
	    }]
	};
	if (option && typeof option === "object") {
	    myChart.setOption(option, true);
	}
}

//		data:graphEntity,
//		contentType: "json",
var vm = new Vue({
	el:'#dpLTE',
	data: {
		keyword: null,
	},
	methods : {
		acceptClick: function() {

		}
	}
})
