//$(function(){
//	alert("xixi");
//});
function createTop(params){
	var yourChart = echarts.init(document.getElementById("linkFrequency"));
	console.log(params[0].year);
	yourChart.showLoading();
	var year = [];
	var frequency = [];
	for(var i = 0;i<params.length;i++){
		year.push(params[i].year);
		frequency.push(params[i].times);
	}
	yourChart.hideLoading();
	option = null;
	option = {
		title:{
			text:"文章发表趋势",
			x:'center',
		},
	    xAxis: {
	    	name:'年份',
	        type: 'category',
	        data:year,
	        //data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
	        //data: year
	    },
	    yAxis: {
	    	name:'发表文章数',
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
	        //data:[820, 932, 901, 934, 1290, 1330, 1320],
	        data: frequency,
	        type: 'line'
	    }]
	};
	if (option && typeof option === "object") {
	    yourChart.setOption(option, true);
	}
}

function createBottom(params){
	var myChart = echarts.init(document.getElementById("publishYear"));
	myChart.showLoading();
	var name = [];
	var frequency = [];
	for(var i = 0; i < params.length;i++){
		name.push(params[i].title);
		frequency.push(params[i].chance);
	}
	myChart.hideLoading();
	option = null;
	option={	
		title:{
			text:"文献被引次数",
			x:'center',
		},
	    xAxis: {
	    	name:'年份',
	        type: 'category',
	        data: name
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
	        //data:[820, 932, 901, 934, 1290, 1330, 1320],
	        data: frequency,
	        type: 'bar'
	    }]
	};
	if (option && typeof option === "object") {
	    myChart.setOption(option, true);
	}
}


//var vm = new Vue({
//	el:'#dpLTE',
//	data: {
//		keyword: null,
//	},
//	methods : {
//		acceptClick: function() {
//
//		}
//	}
//})
