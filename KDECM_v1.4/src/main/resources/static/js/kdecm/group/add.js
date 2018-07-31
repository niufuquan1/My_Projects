/**
 * 新增-js
 */
$(function () {
	getListUser();
});

function getListUser() {
	$.ajax({  
		url:'../../kdecm/group/GetUserList',  
        type: 'POST',  
        dataType: 'json',
        success: function (res) {
		     var userlist = eval(res);
		     for(var i in userlist)
		    {
		    	 if(userlist[i].userId == top.vm.user.userId){
		    		 //一个逻辑修复，添加的组成员里不应该有创建者，创建者一定是在组里面的
		    		 continue;
		    	 }
		    
		    	 var objSelect=document.getElementById("userlist");
		    	 objSelect.setAttribute('singleSelect','false');
		    	 objSelect.options.add( new Option(userlist[i].usertrname, userlist[i].userId));
		    }
        },  
        error: function (msg) {  
          alert(msg);  
        }  
    });
}


function selectUserToRightDiv(){
		var select = document.getElementById("userlist");
		var flag = 0;
		for(var i=0;i<select.length;i++){
        	if(select.options[i].selected){  
        		//如果用户选择了用户，则退出循环，如果用户未选择用户，则提示然后刷新界面
        		flag = 1;
        		break;
        	}
        }
		if(flag == 0){
			alert("您尚未选择任何用户，请选择后重试！");
    		location.reload();
    		return false;
		}
		document.getElementById("btn").disabled=true; //将确定按钮设置为不可用，否则会出现重复添加元素的问题
	 	var div = document.getElementById("right");  
	    var ul = document.getElementById("choiceUser");
	    var li = document.createElement("li");
	    var text = document.getElementById("remindWord");
	    var btn1 = document.createElement("button");//向后台传送传递数据
	    var btn2 = document.createElement("button");//取消向后台传送的数据
	    var input = document.createElement("input");//引导用户输入组名称 
	    var UserId = [];
	    var UserName = [];
	    text.innerHTML="您选择的批注组员依次为:";
	    for(i=0;i<select.length;i++){
	        if(select.options[i].selected){
	        	UserId.push(select[i].value);
	        	UserName.push(select.options[i].text);
	        }
	    }  
	    for(i=0;i<UserName.length;i++){
	    	var li = document.createElement("li");
	    	li.innerHTML = UserName[i];
	    	ul.appendChild(li);
	    }　
	    
	    //接下来生成对组名称的设置
	    input.setAttribute('type', 'text');     
	    input.setAttribute('id','groupName');
	    input.setAttribute('placeholder','请输入组名称');
	    div.appendChild(input);  
	    //接下来对按钮btn1相关属性进行设置
	    btn1.innerHTML = '确定创建'; 
	    btn1.className = "btn btn-success";
	    btn1.onclick = function () {  //该方法为向后台传入信息，并进行数据持久化操作方法    
	    	var tempGroupName = document.getElementById("groupName").value;
	    	tempGroupName = tempGroupName.replace(/\s+/g,"");
	    	//判断组名是否输入
	    	if(tempGroupName == ""||tempGroupName == null){
	        	alert("请输入批注组组名！");
	        	return false;
	        }
	    	//对数据进行封装
	        var userIdList = [];
	        var groupName = document.getElementById("groupName").value;
	        for(var i=0;i<select.length;i++){
	        	if(select.options[i].selected){  
	        		obj=new Object(); 
	        		obj.userid=select[i].value;
		            userIdList.push(obj);
	        	}
	        }
	        obj = new Object();
	        obj.userid = top.vm.user.userId;
	        userIdList.push(obj);
	    	$.ajax({
	    		url:'../../kdecm/group/GetUserSelect',
	    		type: 'POST',  
	    		data:
	    			{	createUserId:top.vm.user.userId,
	    				groupName:groupName,
	    				userIdList:JSON.stringify(userIdList)
	    			},
	    		dataType:'text',
	    		success:function(res){
	    			if(res == 1){
	    				alert('批注组创建成功!');
	    		        var index = parent.layer.getFrameIndex(window.name);
	    		        window.parent.location.reload();
	    		        parent.layer.close(index);
	    			}else{
	    				alert('批注组创建失败');
	    				location.reload();
	    			}
	    		}
	    	})
	    }
	    div.appendChild(btn1); 
	    
	    //接下来对按钮btn2相关属性进行设置
	    btn2.innerHTML = '取消创建';
	    btn2.className = "btn btn-danger";
	    btn2.onclick = function(){
	    	location.reload();//自动刷新当前界面        
	    }
	    div.appendChild(btn2);
}

var vm = new Vue({
	el:'#dpLTE',
	data: {
		proGroup: {
			groupId: 0
		}
	},
	methods : {
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		   ({
		    	url: '../../kdecm/group/save?_' + $.now(),
		    	param: vm.proGroup,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
		
		
	}
})
