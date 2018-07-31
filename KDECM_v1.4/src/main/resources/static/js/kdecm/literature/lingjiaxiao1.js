/**
 * 新增-js
 */
function getFileType(filePath){

　　//获取文件的后缀名
　　var startIndex = filePath.lastIndexOf("."); 
　　if(startIndex != -1) 
　　　　return filePath.substring(startIndex+1, filePath.length); 
　　　　else return ""; 
　　　　}
function sendOtherMsg(){
	$.ajax({
		type: 'post',  
        url: "../../kdecm/literature/sendOtherMsg",
        data: 
        	{
        		userid:top.vm.user.userId,
        		name:$("#literatureName").val()        		
        	},
        dataType:'text',
        success:function(res){
        	console.log(res);
        	alert("其他信息传递成功");
        }
	})
}

function AnalysisFile(literaturePath){
	alert("进入这个方法函数中");
	$.ajax({
		type: 'post',
		url:"../../kdecm/literature/AnalysisFile",
		data:{literaturePath:literaturePath}
	})
}

$(function () {  
    $("#upload").click(function () {  
    	var filePath = $("#Literaturefile").val();
    	var literatureName = $("#literatureName").val();
    	　　if("" != filePath){ 
    	　　　　var fileType = getFileType(filePath); 
    	　　　　//判断上传的附件是否为word文件pdf文件或者TXT文件 
    			//alert(fileType);
    	　　　　//if("doc"!=fileType && "pdf"!=fileType && "txt"!=fileType ){
    	　　	   if("doc"!=fileType){　　　
    		　		$("#Literaturefile").val(""); 
    	　　　　　　alert("请上传后缀名为doc类型的文件"); 
    				return false;
    	　　　　　　} 
    	　　　　　　else{ 
		    		if(literatureName == null||literatureName == ''){
		        		alert("文献名不可为空！");
		        		return false;
		        	}
    	　　　　　　//获取附件大小（单位：KB） 
    	　　　　　　var fileSize = document.getElementById("Literaturefile").files[0].size / 1024; 
    	　　　　　　if(fileSize > 10240){ 
    	　　　　　　　　alert("文献大小不能超过10M！");
    	　　　　　　　　$("#Literaturefile").val(""); 
    	　　　　　　} else{
    				//开始传送文件信息
    				sendOtherMsg();
    				//开始传送文件
    				var formData = new FormData($("#uploadForm")[0]);
    				var literature = [];
    	　　　　　　　　$.ajax({ 
    	　　　　　　　　　　type: 'POST',
    	          	   async: false,
    	          	   cache:false,
    	　　　　　　　　　　processData : false, // 不处理发送的数据，因为data值是Formdata对象，不需要对数据做处理
    	　　　　　　　　　　contentType : false, // 不设置Content-type请求头
    	　　　　　　　　　　url : "../../kdecm/literature/uploadLiterature",  
    	　　　　　　　　　　data:formData,
    	　　　　　　　　　　dataType : 'json',// 返回值类型 一般设置为json 
    	　　　　　　　　　　success : function(data) {// 服务器成功响应处理函数 
    						if(data.StatusCode==1){
    							alert("上传成功");  
    							AnalysisFile(data.filePath);
    		    	　　　　　　 window.location.reload();//上传成功后刷新页面		 	
    						}else{
    							alert("服务器异常！");
    							window.location.reload();
    							return false;
    						}
    	　　　　　　　　　　　　}
    	　　　　　　　　　　}); 
    	　　　　　　　　}
    	　　　　　　} 
    	　　　　　}
    	return false;
    });  
});  

var vm = new Vue({
	el:'#dpLTE',
	data: {
		literature: {
			literatureId: 0
		}
	},
	methods : {
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.SaveForm({
		    	url: '../../kdecm/literature/save?_' + $.now(),
		    	param: vm.literature,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})

