$(function() {
	getData();
	createGraph();
});

var data;
var literatureId;

function getData() {
	var url = location.search; // 获取url中"?"符后的字串
	var theRequest = new Object();   
	if (url.indexOf("?") != -1) {   
		var str = url.substr(1);   
		strs = str.split("&");
		literatureId = unescape(strs[0].split("=")[1]);
	}   
    console.log(literatureId);
    
	$.ajax({
		url: "../../kdecm/posPobLit/postilLevels?literatureId="+literatureId,
		type: "post",
		async: false,
		success: function(e) {
			data = e;
		}
	})
}

function createGraph() {
	var myChart = echarts.init(document.getElementById('main'));
	myChart.showLoading();
	myChart.hideLoading();
	myChart.setOption(option = {
		tooltip : {
			trigger : 'item',
			triggerOn : 'mousemove',
			formatter: function (params, ticket, callback) {
// console.log(params.data.value);
				var content ='';
		        var contentLength = params.data.value.length;
		        var rowCount = 20;
		        var rowNumber = Math.ceil(contentLength / rowCount);
		        if(contentLength > rowCount){
		        	for (var i = 0; i < rowNumber; i++) {
	        			var tempStr = "";
	        			var start = i * rowCount;
	        			var end = start + rowCount;
	        			if (i == rowNumber - 1) {
	        				tempStr = params.data.value.substring(start, contentLength);
	        			} else {
	        				tempStr = params.data.value.substring(start, end) + "</br>";
	        			}
	        			content += tempStr;
	        		}
　　　　　　		}else {
　　　　　　　　		content = params.data.value;
　　　　　　		}　　　　　
				return content;
				
            }
		},
// legend : {
// top : '2%',
// left : '3%',
// orient : 'vertical',
// data : [ {
// name : 'tree1',
// icon : 'rectangle'
// }, {
// name : 'tree2',
// icon : 'rectangle'
// } ],
// borderColor : '#c23531'
// },
		series : [ {
			type : 'tree',
			name : 'tree',
			data : [data],
			top : '5%',
			left : '10%',
			bottom : '5%',
			right : '10%',
			roam: true,
			symbolSize : 7,
			label : {
				normal : {
					position : 'center',
					verticalAlign : 'middle',
					align : 'right'
				}
			},
			leaves : {
				label : {
					normal : {
						position : 'right',
						verticalAlign : 'middle',
						align : 'left'
					}
				}
			},
			expandAndCollapse : true,
			animationDuration : 550,
			animationDurationUpdate : 750
		} ]
	});
}