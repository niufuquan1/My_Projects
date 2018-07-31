$(function() {
//	GetRequest();
	getData();
	createGraph();
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
		//url:"/kdecm/graph/info?graphId="+graphEntity.graphId,/kdecm/graph
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

//function GetRequest() {   
//	   var url = location.search; //获取url中"?"符后的字串   
//	   var theRequest = new Object();   
//	   if (url.indexOf("?") != -1) {   
//	      var str = url.substr(1);   
//	      graphId = unescape(str.split("=")[1]);
//	   }   
//	} 

function createGraph() {
//	console.log(graphId);
//	console.log(graphCreatorId);
//	console.log(graphCreatorName);
//	console.log(egdes);
//	console.log(Nodes);

	var myChart = echarts.init(document.getElementById('main'));
	myChart.showLoading();
	var data1 = [];
	var data2 = [];
	var tempType;
	var tempCategory;
	var relation;
	var cate = new Array(3);
	cate[0] = "强化关系";
	cate[1] = "单向关系";
	cate[2] = "双向关系";

	var data3 = [];
	////////////////////////////////////////////////////////////////////////////////
	//种类循环
	for(var j = 0; j < cate.length; j++) {
		data2.push({
			name: cate[j],
			// itemStyle: {
			//           normal: {
			//               color: "#4592FF",
			//           }
			//       }
		});
	}
	//节点循环
	for(var i = 0; i < Nodes.length; i++) {
		data1.push({
//			id: Nodes[i].graphNodeId,
			name: "No." + Nodes[i].postilId + "|" + Nodes[i].postilContent,
			value:Nodes[i].postilObjectText,
			draggable: true,
			// category:1,
		});
	}
	console.log(data1);
	//连线循环
	for(var k = 0; k < egdes.length; k++) {
		if(egdes[k].relationType == 1) {
			tempType = 'solid';
			relation = '强化关系';
		} else if(egdes[k].relationType == 2) {
			tempType = 'dashed';
			relation = '单向关系';
		} else if(egdes[k].relationType == 3) {
			tempType = 'dotted';
			relation = '双向关系';
		}
		data3.push({
//			source: egdes[k].postilId1,
//			target: egdes[k].postilId2,
			source: "No." + egdes[k].postilId1 + "|" + egdes[k].postilName1,
			target: "No." + egdes[k].postilId2 + "|" + egdes[k].postilName2,
			value: relation,
			lineStyle: {
				normal: {
					type: tempType,
					color: {
						x: 0,
						y: 0,
					}
				}
			},
			category: tempCategory,
		});
	}
		console.log(data3);

	// 指定图表的配置项和数据
	var option = {
		title: {
			text: '批注关系可视化'
		},
		tooltip: {
			trigger : 'item',
			triggerOn : 'mousemove',
			formatter: function (params, ticket, callback) {
//				console.log(params.data.value);
				return params.data.value;
            }
		},
		legend: {
			show: true,
			data: data2
		},
		//category:[],
		animationDurationUpdate: 1500,
		animationEasingUpdate: 'quinticInOut',
		//颜色方法
		backgroudColor: {
			color: {
				type: 'linear',
				x: 0,
				y: 0,
				x2: 0,
				y2: 1,
				colorStops: [{
					offset: 0,
					color: 'red' // 0% 处的颜色
				}, {
					offset: 1,
					color: 'blue' // 100% 处的颜色
				}],
				globalCoord: false // 缺省为 false
			}
		},
		series: [{
			name: '销量',
			type: 'graph',
			layout: 'force',
			// linkSymbol:'arrow',
			force: {
				repulsion: 1000,
				edgeLength: 250,
				layoutAnimation: true,
			},
			roam: true,
			symbol: 'circle',
			symbolSize: 50,
			symbolKeepAspect: false,
			edgeSymbol: ['', 'arrow'],
			edgeSymbolSize: [4, 10],
			edgeLabel: {
				normal: {
					show: true,
					textStyle: {
						fontSize: 13
					},
					formatter: "{c}"
				}
			},
			//focusnodesAdjacency: true,
			nodes: data1,
			links: data3,
			itemStyle: {
				normal: {
					borderColor: '#FC0000',
					borderWidth: 10,
					shadowBlur: 20,
					shadowColor: '#F3B2D5',
					color: '#FC0505',
					symbolSize: 50,
					label: {
						show: true,
					}
				}
			},
			lineStyle: {
				normal: {
					opacity: 0.9,
					width: 1,
					curveness: 0
				}
			},
		}]
	};
	myChart.hideLoading();
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
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
