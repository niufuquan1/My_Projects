/**
 * 
 */
$(function () {
	graphId = sessionStorage.getItem("graphId");
	var div1 =  document.getElementById("find_CorePostil_Div");
	var div2 =  document.getElementById("find_PostilGroupTheme_Div");
	var div3 = document.getElementById("find_CorePrescription_Div");
	var div4 = document.getElementById("button1");
	var div5 = document.getElementById("button2");
	var div6 = document.getElementById("button3");
	div1.style.display = 'none';
	div2.style.display = 'none';
	div3.style.display = 'none';
	div4.style.display = 'none';
	div5.style.display = 'none';
	div6.style.display = 'none';
});

var graphId;
var type;
var graphCreatorId;
var graphCreatorName;

function CreateGraph_ForPageRank(prEntity){
	flag_time1 = 1;

	//这里处理批注对象相关材料
	var originEgdes = [];
	var originNodes = [];
	var Nodes = [];
	var Egdes = [];
	console.log(prEntity);
    $.ajax({
		url: "../../kdecm/graph/info?graphId=" + graphId + "&type=" + 0,
		type: "post",
		async: false,
		success: function(graph) {
			originEgdes = graph.gedRelEntity;
			originNodes = graph.gnoPosEntity;
			
			var core_list = prEntity[0].list;
			var important_list = prEntity[1].list;
			var oridinary_list = prEntity[2].list;
			
			
			
			//批注节点进行处理
			for(var i = 0; i < originNodes.length; i++) {
				for(var t = 0; t<core_list.length;t++){
					if(originNodes[i].postilId == core_list[t]){
						Nodes.push(
								{
									name: "No." + originNodes[i].postilId + "|" + originNodes[i].postilContent,
									category: "核心批注",
									draggable: true
								}
							);
						break;
					}	
				}
				for(t = 0;t<important_list.length;t++){
					if(originNodes[i].postilId == important_list[t]){
						Nodes.push(
								{
									name: "No." + originNodes[i].postilId + "|" + originNodes[i].postilContent,
									category: "重要批注",
									draggable: true
								}
							);
						break;
					}	
				}
				for(t = 0;t<oridinary_list.length;t++){
					if(originNodes[i].postilId == oridinary_list[t]){
						Nodes.push(
								{
									name: "No." + originNodes[i].postilId + "|" + originNodes[i].postilContent,
									category: "边缘批注",
									draggable: true
								}
							);
						break;
					}	
				}
			}
			//console.log(originNodes);
			//console.log(Nodes);
		//批注关系处理
			for(var k = 0; k < originEgdes.length; k++) {
				if(originEgdes[k].relationType == 1){
					Egdes.push({
						source: "No." + originEgdes[k].postilId1 + "|" + originEgdes[k].postilName1,
						target: "No." + originEgdes[k].postilId2 + "|" + originEgdes[k].postilName2,
						category: "强化关系"
					});
			}else if(originEgdes[k].relationType == 2){
				Egdes.push({
						source: "No." + originEgdes[k].postilId1 + "|" + originEgdes[k].postilName1,
						target: "No." + originEgdes[k].postilId2 + "|" + originEgdes[k].postilName2,
						category: "单向关系"
					});
			}else if(originEgdes[k].relationType == 3){
				Egdes.push({
						source: "No." + originEgdes[k].postilId1 + "|" + originEgdes[k].postilName1,
						target: "No." + originEgdes[k].postilId2 + "|" + originEgdes[k].postilName2,
						category: "双向关系"
					});
			}
		}
	}
})
	
	var mainContainer = document.getElementById('find_CorePostil_Div');
    //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
    var resizeMainContainer = function () {
        mainContainer.style.width = window.innerWidth+'px';
        mainContainer.style.height = window.innerHeight*0.95+'px';
    };
    //设置div容器高宽
    resizeMainContainer();
    // 初始化图表
    var myChart = echarts.init(mainContainer);
    $(window).on('resize',function(){//
        //屏幕大小自适应，重置容器高宽
        resizeMainContainer();
        myChart.resize();
    });

	var option = {
//		    "tooltip": {
//		        "formatter": function(param) {
//		            if (param.dataType === 'edge') {
//		                return param.data.category + ': ' + param.data.target;
//		            }
//		            return param.data.category + ': ' + param.data.name;
//		        }
//		    },
		    "title": {
		        "text": ""
		    },
		    "legend": [{
		        "data": ["核心批注", "重要批注", "边缘批注"]
		    }],
		    "series": [{
		        "top": 150,
		        "label": {
		            "normal": {
		                "show": true,
		                "position": "inside",
		                "textStyle": {
		                    "fontSize": 18
		                }
		            }
		        },
		        "roam": true,
		        "edgeLabel": {
		            "normal": {
		                "show": true,
		                "formatter": function(param) {
		                    return param.data.category;
		                },
		                "textStyle": {
		                    "fontSize": 16
		                }
		            }
		        },
		        "bottom": 80,
		        "data": Nodes,
		        "categories": [{
		            "name": "核心批注",
		            itemStyle:{
		            	normal:{
		            		color:"#FF0000"
		            	}
		            }
		        }, {
		            "name": "重要批注",
		            itemStyle:{
		            	normal:{
		            		color:"#9AFF9A"
		            	}
		            }
		        }, {
		            "name": "边缘批注",
		            itemStyle:{
		            	normal:{
		            		color:"#8DB6CD"
		            	}
		            }
		        },{
		        	"name":"强化关系"
		        },{
		        	"name":"单向关系"
		        },{
		        	"name":"双向关系"
		        }],
		        "type": "graph",
		        "focusNodeAdjacency": true,
		        "force": {
		            "repulsion": 1000,
		            "edgeLength": [150, 300]
		        },
		        "layout": "force",
		        "symbolSize": [240, 30],
		        "links":Egdes,
		        "symbol": "path://M19.300,3.300 L253.300,3.300 C262.136,3.300 269.300,10.463 269.300,19.300 L269.300,21.300 C269.300,30.137 262.136,37.300 253.300,37.300 L19.300,37.300 C10.463,37.300 3.300,30.137 3.300,21.300 L3.300,19.300 C3.300,10.463 10.463,3.300 19.300,3.300 Z",
		        "lineStyle": {
		            "normal": {
		                "opacity": 0.9,
		                "width": 1,
		                "curveness": 0.1
		            }
		        }
		    }],
		    "animationEasingUpdate": "quinticInOut",
		    "animationDurationUpdate": 1500
		};
	myChart.hideLoading();
	// 使用刚指定的配置项和数据显示图表。

	myChart.setOption(option);
}

function CreateGraph_ForNO2(Entity){
	flag_time2 = 1;
	var Nodes = [];
	var Edges = [];
	var Categories = [];
	var size = 80;
	var size1 = 50;
	var yy = 200;
	var yy1 = 250;
	var x = 0;
	var belong_postilList = [];
	var j ;
	var i ;
	var k ;
	//针对最大的节点的相关封装
	Nodes.push(
			{
				x: 0,
	            y: yy,
	            symbolSize: 100,
	            category: "批注群",
	            draggable: "true",
				name: "批注群",
				draggable: true
			}
		);
	//由于分出的批注群没有进行唯一性处理，因此必须首先进行唯一性处理,会得到不重复的批注群代表Id
	for(i=0;i<Entity.length;i++){
		if(belong_postilList.length == 0){
			var obj = [];
			obj.push(Entity[i].belongs_postilId);
			obj.push(Entity[i].belongs_postilIdContent);
			belong_postilList.push(obj);
		}else if(belong_postilList.length > 0){
			var flag = 0;
			//alert("before: "+flag);
			for(j=0;j<belong_postilList.length;j++){
				if(belong_postilList[j][0] == Entity[i].belongs_postilId){
					flag = 1;
					break;
				}
			}
			if(flag == 0){
				var obj = [];
				obj.push(Entity[i].belongs_postilId);
				obj.push(Entity[i].belongs_postilIdContent);
				belong_postilList.push(obj);
			}
		}
	}
	console.log(belong_postilList);
	//开始对节点数据进行封装
	  //首先是大的批注
	var temp = 10;
	for(i=0;i<belong_postilList.length;i++){
		x = x+10;
		if(temp%2==0){
			Nodes.push(
					{
			            x: x,
			            y: yy1,
			            symbolSize: size,
			            category: "批注群" +"|"+ "ID:" + belong_postilList[i][0],
			            draggable: "true",
						name: "No." + belong_postilList[i][0] + "|" + belong_postilList[i][1],
						draggable: true
					}
				);
			temp++;
		}else{
			Nodes.push(
					{
			            x: x,
			            y: yy,
			            symbolSize: size,
			            category: "批注群" +"|"+ "ID:" + belong_postilList[i][0],
			            draggable: "true",
						name: "No." + belong_postilList[i][0] + "|" + belong_postilList[i][1],
						draggable: true
					}
				);
			temp++;
		}
		
	}
	  //然后是小批注
	for(i=0;i<Entity.length;i++){
		flag = 0;
		for(j=0;j<belong_postilList.length;j++){
			if(Entity[i].postilId == belong_postilList[j][0]){
				flag = 1;
				break;
			}
		}
		if(flag == 0){
			x = x + 10;
			if(temp%2==0){
				Nodes.push(
						{
				            x: x,
				            y: yy1,
				            symbolSize: size1,
				            category: "批注群" +"|"+ "ID:" + Entity[i].belongs_postilId,
				            draggable: "true",
							name: "No." + Entity[i].postilId + "|" + Entity[i].postilIdContent,
							draggable: true
						}
					);
				temp++;
			}else{
				Nodes.push(
						{
				            x: x,
				            y: yy,
				            symbolSize: size1,
				            category: "批注群" +"|"+ "ID:" + Entity[i].belongs_postilId,
				            draggable: "true",
							name: "No." + Entity[i].postilId + "|" + Entity[i].postilIdContent,
							draggable: true
						}
					);
				temp++;
			}
		}
	}
	

	//针对边的封装
	var source;
	var target;
		//首先，将大类与批注组这个大node相连接
	target = "批注群";
	for(i=0;i<belong_postilList.length;i++){
		source = "No." + belong_postilList[i][0] + "|" + belong_postilList[i][1];
		Edges.push({
			source:source,
			target:target
		})
	}
		//然后放小类与批注群核心相连
	for(i=0;i<Entity.length;i++){
		flag = 0;
		for(j=0;j<belong_postilList.length;j++){
			if(Entity[i].postilId == belong_postilList[j][0]){
				flag = 1;
				break;
			}
		}
		//alert(flag);
		if(flag == 0){
			//alert("No." + Entity[i].belongs_postilId + "|" + Entity[i].belongs_postilIdContent+"  "+"No." + Entity[i].postilId + "|" + Entity[i].postilIdContent)
			target = "No." + Entity[i].belongs_postilId + "|" + Entity[i].belongs_postilIdContent;
			source = "No." + Entity[i].postilId + "|" + Entity[i].postilIdContent;
			Edges.push({
				source:source,
				target:target
			})
		}
	}	
	
	//封装一下种类
	var Category;
	var color = [];
	color.push("#00FFFF");
	color.push("#1E90FF");
	color.push("#00EE00");
	color.push("#A020F0");
	var k = 0;
	Categories.push("批注群");
	for(i=0;i<belong_postilList.length;i++){
		Category = "批注群" +"|"+ "ID:" + belong_postilList[i][0];
		if(k<4){
			Categories.push({
				name:Category,
				itemStyle: {
				           normal: {
				               		color: color[k],
				           		}
				       	   }
			});
			k++;
		}else{
			k = 0;
			Categories.push({
				name:Category,
				itemStyle: {
				           normal: {
				               		color: color[k],
				           		}
				       	   }
			});
		}
		
	}
	
	console.log(Nodes);
	console.log(Edges);
	console.log(Categories);
	
	//初始化echarts相关对象
	var mainContainer = document.getElementById('find_PostilGroupTheme_Div');
    //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
    var resizeMainContainer = function () {
        mainContainer.style.width = window.innerWidth+'px';
        mainContainer.style.height = window.innerHeight*0.95+'px';
    };
    //设置div容器高宽
    resizeMainContainer();
    // 初始化图表
    var myChart = echarts.init(mainContainer);
    $(window).on('resize',function(){//
        //屏幕大小自适应，重置容器高宽
        resizeMainContainer();
        myChart.resize();
    });
	
	
	option = {
	    title: {
	        text: "批注群发现",
	        top: "top",
	        left: "left",
	        textStyle: {
	            color: '#f7f7f7'
	        }
	    },
	    tooltip: {
	        formatter: '{b}'
	    },
	    toolbox: {
	        show: true,
	        feature: {
	            restore: {
	                show: false
	            },
	            saveAsImage: {
	                show: false
	            }
	        }
	    },
	    backgroundColor: '#337ab7',
	    animationDuration: 1000,
	    animationEasingUpdate: 'quinticInOut',
	    series: [{
	        name: '批注群体系',
	        type: 'graph',
	        layout: 'force',
	        force: {
	            repulsion: 60,
	            gravity: 0.1,
	            edgeLength: 30,
	            layoutAnimation: true,
	        },
	        data: Nodes,
	        links: Edges,
	        categories: Categories,
	        roam: true,
	        label: {
	            normal: {
	                show: true,
	                position: 'inside',
	                formatter: '{b}',
	                fontSize: 16,
	                fontStyle: '600',
	            }
	        },
	        lineStyle: {
	            normal: {
	                width: 4,
	                color: 'source',
	                curveness: 0,
	                type: "solid"
	            }
	        }
	    }]
	};
	myChart.hideLoading();
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
}

function CreateTable_ForNO3(Entity){
	flag_time3 = 1;
	//首先数据处理
	var divide_list = [];//分出的核心药药名+附属的药名List，默认第一位是核心药名
	var pathema_list = [];
	//这个for循环处理核心药名（没有重复）
	for(var i = 0;i<Entity.thirdFuncEntityList.length;i++){
		for(var j = 0;j<Entity.thirdFuncEntityList[i].divideCoreList.length;j++){
			if(divide_list.length == 0){
				var obj = [];
				obj.push(Entity.thirdFuncEntityList[i].divideCoreList[j]);
				divide_list.push(obj);
			}else{
				var flag = 0;
				for(var k = 0;k<divide_list.length;k++){
					if(divide_list[k] == Entity.thirdFuncEntityList[i].divideCoreList[j]){
						flag = 1;
						break;
					}
				}
				if(flag == 0){
					var obj = [];
					obj.push(Entity.thirdFuncEntityList[i].divideCoreList[j]);
					divide_list.push(obj);
				}
			}
		}
	}
	//这个for循环处理附属药名
	for(var i = 0;i<Entity.thirdFuncEntityList.length;i++){
		var affiliatedHerbal = Entity.thirdFuncEntityList[i].affiliated;
		var flag = 0;
		var j = 0;
		for(;j<divide_list.length;j++){
			if(affiliatedHerbal == divide_list[j][0]){
				flag = 1;
				break;
			}
		}
		if(flag == 0){
			for(j = 0;j<Entity.thirdFuncEntityList[i].divideCoreList.length;j++){
				for(var t = 0;t<divide_list.length;t++){
					if(Entity.thirdFuncEntityList[i].divideCoreList[j] == divide_list[t][0]){
						divide_list[t].push(affiliatedHerbal);
						break;
					}
				}
			}
		}
	}
	
	//这里处理证候名
	for(var i = 0;i<Entity.pathemaList.length;i++){
		pathema_list.push(Entity.pathemaList[i]);
	}
	
	//首先显示证候
	var t1 = document.getElementById("pathema");
	for(var i in pathema_list){
		 var tr = document.createElement("tr");
	   	 var td = document.createElement("td");
	   	 td.innerHTML = pathema_list[i];
	   	 tr.appendChild(td);
	   	 t1.appendChild(tr);
	}
	
	//然后显示药组
	var t2 = document.getElementById("herbal");
	for(var i in divide_list){
		var tr = document.createElement("tr");
		var td1 = document.createElement("td");
		td1.innerHTML = divide_list[i][0];
		
		var td2 = document.createElement("td");
		var temp = "";
		if(divide_list[i].length == 1){
			temp = "无";
		}else{
			for(var j = 1;j<divide_list[i].length;j++){
				temp = temp + " " + divide_list[i][j];
			}
		}
		td2.innerHTML = temp;
		
		tr.appendChild(td1);
		tr.appendChild(td2);
	   	t2.appendChild(tr);
	}
}
//该函数是发送前台选择的信息(先发送graphid)给后台，让后台从数据库中查出并进行数据的处理
var flag_time1 = 0;
var flag_time2 = 0;
var flag_time3 = 0;
function SendMsg1(){
	var div1 =  document.getElementById("main");
	var div2 = document.getElementById("buttons");
	var div3 = document.getElementById("find_CorePostil_Div");
	var div4 = document.getElementById("button1");
	div1.style.display = 'none';
	div2.style.display = 'none';
	div3.style.display = 'block';
	div4.style.display = 'block';
	$.ajax({
		url:'../../kdecm/graph/getMsgtoDoOther1',  
        type: 'POST',  
        dataType: 'json',
        data:{graphId:graphId},
        success:function(res){
        	console.log(res);
        	console.log(Nodes);
        	if(flag_time1 == 0){
        		CreateGraph_ForPageRank(res);
        	}	
        }
	})
}

function SendMsg2(){
	graphId = sessionStorage.getItem("graphId");
	var div1 = document.getElementById("main");
	var div2 = document.getElementById("buttons");
	var div3 = document.getElementById("find_PostilGroupTheme_Div");
	var div4 = document.getElementById("button2");
	div1.style.display = 'none';
	div2.style.display = 'none';
	div3.style.display = 'block';
	div4.style.display = 'block';
	
	$.ajax({
		url:'../../kdecm/graph/getMsgtoDoOther2',
		type: 'POST',  
        dataType: 'json',
        data:{graphId:graphId},
        success:function(res){
        	var postilList = eval(res);
        	console.log(postilList);
        	if(flag_time2 == 0){
        		CreateGraph_ForNO2(postilList);
        	}
        }
	})
}

function SendMsg3(){
	graphId = sessionStorage.getItem("graphId");
	var div1 = document.getElementById("main");
	var div2 = document.getElementById("buttons");
	var div3 = document.getElementById("find_CorePrescription_Div");
	var div4 = document.getElementById("button3");
	div1.style.display = 'none';
	div2.style.display = 'none';
	div3.style.display = 'block';
	div4.style.display = 'block';
	
	$.ajax({
		url:'../../kdecm/graph/getMsgtoDoOther3',
		type: 'POST',  
        dataType: 'json',
        data:{graphId:graphId},
        success:function(res){
        	//仅仅是发送到后台，前台暂时先不做任何操作
        	console.log(res);
        	console.log(res.pathemaList[0]);
        	console.log(res.thirdFuncEntityList[0].divideCoreList);
        	if(flag_time3 == 0){
        		CreateTable_ForNO3(res);
        	}
        }
	})
}

function back1(){
	var div1 = document.getElementById("find_CorePostil_Div");
	var div2 = document.getElementById("buttons");
	var div3 = document.getElementById("main");
	var div4 = document.getElementById("button1");
	div1.style.display = 'none';
	div2.style.display = 'block';
	div3.style.display = 'block';
	div4.style.display = 'none';
}

function back2(){
	var div1 = document.getElementById("find_PostilGroupTheme_Div");
	var div2 = document.getElementById("buttons");
	var div3 = document.getElementById("main");
	var div4 = document.getElementById("button2");
	div1.style.display = 'none';
	div2.style.display = 'block';
	div3.style.display = 'block';
	div4.style.display = 'none';
}

function back3(){
	var div1 = document.getElementById("find_CorePrescription_Div");
	var div2 = document.getElementById("buttons");
	var div3 = document.getElementById("main");
	var div4 = document.getElementById("button3");
	div1.style.display = 'none';
	div2.style.display = 'block';
	div3.style.display = 'block';
	div4.style.display = 'none';
}