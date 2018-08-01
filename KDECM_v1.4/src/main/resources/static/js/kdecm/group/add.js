/**
 * 新增-js
 */
$(function () {
	getListUserToShow();
});

var userList = [];
var userChooses;
function getListUserToShow() {
	//alert("in!!");
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
		    	 var obj = {
		    			 "userId":userlist[i].userId,
		    	 		 "userName":userlist[i].usertrname
		    	 };
		    	 userList.push(obj);
		    }
		     userChooses = $('.userList').doublebox({
		         nonSelectedListLabel: '待选批注组成员',
		         selectedListLabel: '已选批注组成员',
		         moveOnSelect: false,
		         nonSelectedList:userList,
		         selectedList:null,
		         optionValue:"userId",
		         optionText:"userName",
		         doubleMove:true
		       });
        },  
        error: function (msg) {  
          alert(msg);  
        }  
    });
}
function sendChoosesToBack(){
	var userIdList = [];
	var userChooses = document.getElementsByName("doublebox");
	var groupName = document.getElementById("groupName").value;
	groupName = groupName.replace(/\s+/g,"");
	//判断组名是否输入
	if(groupName == ""||groupName == null){
    	alert("请输入批注组组名！");
    	return false;
    }
	
	for(var i = 0;i<userChooses[0].length;i++){
		if(userChooses[0].options[i].selected){  
    		obj=new Object(); 
    		obj.userid=userChooses[0].options[i].value;
            userIdList.push(obj);
    	}
	}
	$.ajax({
		url:'../../kdecm/group/GetUserSelect',
		type: 'POST',  
		data:
			{	
				groupName:groupName,
				createUserId:top.vm.user.userId,
				userIdList:JSON.stringify(userIdList)
			},
		dataType:'text',
		success:function(res){
			console.log(res);
			if(res == 1){
				alert('批注组添加成功!');
				var index = parent.layer.getFrameIndex(window.name);
		        window.parent.location.reload();
		        parent.layer.close(index);
			}else{
				alert('批注组添加失败');
				location.reload();
			}
		}
	})
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
