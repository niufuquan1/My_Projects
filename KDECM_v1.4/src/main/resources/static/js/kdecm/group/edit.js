/**
 * 编辑-js
 */

$(function () {
	var div1 = document.getElementById("modifyGroupName");
	var div2 = document.getElementById("SearchUser");
	var div3 = document.getElementById("SearchResult"); 
	var div4 = document.getElementById("AddOrDel");
	var div5 = document.getElementById("changeGroupPropery");
	div1.style.display= 'none';
	div2.style.display = 'none';
	div3.style.display = 'none';
	div4.style.display = 'none';
	div5.style.display = 'none';
	getGroupUserList();
	getUserList();
});
var userlist1;
var groupUserList;
window.flag = 0;
function getGroupUserList() {
	var groupId = sessionStorage.getItem("groupId");
	var t = document.getElementById("groupUser");
	$.ajax({  
		url:'../../kdecm/group/getGroupUserList',  
        type: 'POST',  
        dataType: 'json',
        data:{groupId:groupId},
        success: function (res) {
		     groupUserList = eval(res);
		     console.log(groupUserList);
		     for(var i in groupUserList)
		    {
		    	 var tr = document.createElement("tr");
		    	 var td1 = document.createElement("td");
		    	 var td2 = document.createElement("td");
		    	 td1.innerHTML = groupUserList[i].username;
		    	 td2.innerHTML = groupUserList[i].identity;
		    	 tr.appendChild(td1); 
		    	 tr.appendChild(td2);
		    	 t.appendChild(tr);
		    }
        },  
        error: function (msg) {  
          alert(msg);  
        }  
    });
}

function goback(){
	location.replace(location.href);
}

function showGroupProperty(){
	var div = document.getElementById("changeGroupPropery");
	var div1 = document.getElementById("dpLTE");
 	div1.style.display='none';
	div.style.display='inline';
}
function ChangeGroupProperty(){
	var groupId = sessionStorage.getItem("groupId");
	var options=$("#IfOpenNess	 option:selected"); 
	var options=$("#IfOpenNess	 option:selected"); 
	$.ajax({
		url:'../../kdecm/group/ChangeGroupProperty',  
        type: 'POST',  
        dataType: 'json',
		data:{
			  groupId:groupId,
			  openNess:options.val()
			  },
		success:function(res){
			if(res == 1){
				alert("修改成功!");
				$.currentIframe().vm.load();
				location.replace(location.href);
			}else{
				alert("修改失败，请联系管理员!");
				location.reload();
			}
		}
	})
}

function SearchUser(){
	var div = document.getElementById("dpLTE");
	var div1 = document.getElementById("SearchUser");
	
	div.style.display='none';
	div1.style.display='block';
}
function getUserList(){
	$.ajax({  
		url:'../../kdecm/group/GetUserList',  
        type: 'POST',  
        dataType: 'json',
        success: function (res){
        	userlist1 = eval(res);
        	}
    })
}

function addGroupMoveOn(){
	var div = document.getElementById("AddOrDel");
	var div1 = document.getElementById("dpLTE");
 	div1.style.display='none';
	div.style.display='block';
	var userList = [];
	//继续添加组成员
	window.flag = 1;
	$.ajax({  
		url:'../../kdecm/group/GetUserList',  
        type: 'POST',  
        dataType: 'json',
        success: function (res){
        	userlist1_ = eval(res);
        	for(var i=0;i<userlist1_.length;i++)
    	    {
    	    	 var flag = 0;
    	    	 for(var j = 0;j<groupUserList.length;j++){
    	    		 if(groupUserList[j].userId == userlist1_[i].userId){
    	    			 flag = 1;
    	    			 break;
    	    		 }	 
    	    	 }
    	    	 if(flag == 0){
    	    		 var obj = {
    		    			 "userId":userlist1_[i].userId,
    		    	 		 "userName":userlist1_[i].usertrname
    		    	 };
    		    	 userList.push(obj);
    	    	 }
    	    } 
    	var userChooses = $('.userList').doublebox({
            nonSelectedListLabel: '待选批注组成员',
            selectedListLabel: '已选批注组成员',
            moveOnSelect: false,
            nonSelectedList:userList,
            selectedList:null,
            optionValue:"userId",
            optionText:"userName",
            doubleMove:true
          });
        	}
    })
}

function delGroupMoveOn(){
	//删除组成员
	var div = document.getElementById("AddOrDel");
	var div1 = document.getElementById("dpLTE");
 	div1.style.display='none';
	div.style.display='block';
	window.flag = 2;
	var userList = [];
	var groupId = sessionStorage.getItem("groupId");
	var t = document.getElementById("groupUser");
	$.ajax({  
		url:'../../kdecm/group/getGroupUserList',  
        type: 'POST',  
        dataType: 'json',
        data:{groupId:groupId},
        success: function (res) {
		     groupUserList = eval(res);
		     console.log(groupUserList);
		     for(var i = 0;i<groupUserList.length;i++){
		 		var obj = {
		    			 "userId":groupUserList[i].userId,
		    	 		 "userName":groupUserList[i].usertrname
		    	 };
		    	 userList.push(obj);
		 	}
		 	var userChooses = $('.userList').doublebox({
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

function modifyGroupName(){
	//修改组名称
	var div = document.getElementById("modifyGroupName");
	var div1 = document.getElementById("dpLTE");
	div.style.display='block';
	div1.style.display = 'none';
	
}

function ChangeGroupName(){
	var groupName = document.getElementById("groupName");
	var groupId = localStorage.getItem("groupId");
	if(groupName.value.trim() == ""||groupName.value == null){
		alert("组名不可为空！");
		return false;
	}
	$.ajax({
		url:'../../kdecm/group/ChangeGroupName',  
        type: 'POST', 
        data:{groupName:groupName.value,
        	  groupId:groupId
        	},
        dataType:'text',
        success:function(res){
        	if(res == 1){
        		alert("批注组组名修改成功！");
        		location.replace(location.href);
        	}else{
        		alert("批注组组名修改失败，请联系管理员！");
        		location.reload();
        	}
        }
	})
}
function searchUserlist(){
	//搜索用户
	var div = document.getElementById("AddOrDel");
	var div1 = document.getElementById("dpLTE");
 	div1.style.display='none';
	div.style.display='inline';
}

function SearchUserBtn(){
	var div = document.getElementById("SearchResult"); 
	div.style.display='block';
	var input = document.getElementById("Search").value;
	var userlist2;
	var flag = 0;
	input = input.replace(/\s+/g,"");
	if(input==null ||input==""){
		alert("请输入搜索内容后再试");
		var div = document.getElementById("SearchResult"); 
		div.style.display = 'none';
		return false;
	}
	$.ajax({  
		url:'../../kdecm/group/GetUserList',  
        type: 'POST',  
        dataType: 'json',
        success: function (res){
        	userlist2 = eval(res);
        	$('#UserResult').remove();
    		var table = document.getElementById("SearchResultTable");
    		var t = document.createElement("tbody");
    		t.setAttribute('id','UserResult');
    		table.appendChild(t);
        		var t1 = document.getElementById("UserResult");
        		for(var i = 0;i<userlist2.length;i++){
            		if((input == userlist2[i].username)||(input == userlist2[i].usertrname)){
            			 var tr = document.createElement("tr");
            	    	 var td1 = document.createElement("td");
            	    	 var td2 = document.createElement("td");
            	    	 td1.innerHTML = userlist2[i].username;
            	    	 td2.innerHTML = userlist2[i].usertrname;
            	    	 tr.appendChild(td1); 
            	    	 tr.appendChild(td2);
            	    	 t1.appendChild(tr);
            	    	 flag = 1;
            		}
            	}
        		if(flag == 0){
        			var tr = document.createElement("tr");
        			var td1 = document.createElement("td");
        			td1.innerHTML = "系统未搜索到该用户！";
        			tr.appendChild(td1);
        			t.appendChild(tr);
        		}
        }
	});
}

function sendChoosesToBack(){
	if(window.flag == 1){
		//继续添加
		var userIdList = [];
		var userChooses = document.getElementsByName("doublebox");
		
		for(var i = 0;i<userChooses[0].length;i++){
			if(userChooses[0].options[i].selected){  
	    		obj=new Object(); 
	    		obj.userid=userChooses[0].options[i].value;
	            userIdList.push(obj);
	    	}
		}
		var groupId = sessionStorage.getItem("groupId");
		$.ajax({
			url:'../../kdecm/group/addGroupUserMoveOn',
			type: 'POST',  
			data:
				{	
				groupId:groupId,
				userIdList:JSON.stringify(userIdList)
				},
			dataType:'text',
			success:function(res){
				console.log(res);
    			if(res == 1){
    				alert('批注组成员添加成功!');
    				location.replace(location.href);
    			}else{
    				alert('批注组成员添加失败');
    				location.reload();
    			}
			}
		})
	}else if(window.flag == 2){
		//删除组成员
		var btn = document.getElementById("addOrDelBtn");
		 btn.onclick = function () {  //该方法为向后台传入信息，并进行数据持久化操作方法 
		    	//对数据进行封装
		    	if(!confirm("您确定删除上述批注组成员吗?")){
		    		return false;
		    	}
		var userIdList = [];
		var userChooses = document.getElementsByName("doublebox");
		
		for(var i = 0;i<userChooses[0].length;i++){
			if(userChooses[0].options[i].selected){  
	    		obj=new Object(); 
	    		obj.userid=userChooses[0].options[i].value;
	            userIdList.push(obj);
	    	}
		}
		var groupId = sessionStorage.getItem("groupId");
		$.ajax({
			url:'../../kdecm/group/delGroupUserMoveOn',
			type: 'POST',  
			data:
				{	
				groupId:groupId,
				userIdList:JSON.stringify(userIdList)
				},
			dataType:'text',
			success:function(res){
				console.log(res);
    			if(res == 1){
    				alert('批注组成员删除成功!');
    				location.replace(location.href);
    			}else{
    				alert('批注组成员删除失败');
    				location.reload();
    			}
			}
		})
	}
}
}

var vm = new Vue({
	el:'#dpLTE',
	data: {
		proGroup: {
			groupId: 0
		}
	}
})