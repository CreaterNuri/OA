//原密码
var password;
$(function() {
	//路径取值 id
	uid = getQueryStringV(location.href, "id");
	//路径取值username
	username = getQueryStringV(decodeURI(location.href), "username");
	//点击关闭
	$(".tiptop a").click(function(){
		  $(".tip").fadeOut(200);
	});
	//显示用户名
	showname();
	//查询用户信息数量
	queryAllUserMsg();
	//获取原密码
	queryPwd();
});
/**
 * 显示登陆名
 */
function showname(){
	$("#username").text(username);
};

/**
 * 获取url中的参数信息
 */
function getQueryStringV(vhref, name) {
    // 如果链接没有参数，或者链接中不存在我们要获取的参数，直接返回空 
    if (vhref.indexOf("?") == -1 || vhref.indexOf(name + '=') == -1) {
        return '';
    }
    // 获取链接中参数部分 
    var queryString = vhref.substring(vhref.indexOf("?") + 1);
    // 分离参数对 ?key=value&key2=value2 
    var parameters = queryString.split("&");
    var pos, paraName, paraValue;
    for (var i = 0; i < parameters.length; i++) {
        // 获取等号位置 
        pos = parameters[i].indexOf('=');
        if (pos == -1) {
            continue;
        }
        // 获取name 和 value 
        paraName = parameters[i].substring(0, pos);
        paraValue = parameters[i].substring(pos + 1);
        if (paraName == name) {
        	//return unescape(paraValue.replace(/\+/g, " "));
        	return paraValue.replace(/\+/g, " ");
        }
    }
    return '';
};

/**
 * 查询用户信息数量
 */
function queryAllUserMsg(){
	$.ajax({
		type:'POST',
		url:'usermessage_query!queryUserNum.action',
		data:{
			username:username
			},
		success: function(data){
			$("#msgNum").text(data.listNum);
        }  
	});
};
/**
 * 获取原密码
 */
function queryPwd(){
	$.ajax({
		type:'POST',
		url:'user_queryPwd!queryPwd.action',
		data:{
			id:uid
			},
		success: function(data){
			password=data.password;
        }  
	});
};
/**
 * 密码修改
 */
function newPassword(){
	$("#changePwd").fadeIn(100); 
};

/**
 * 提交修改
*/
function ChangePwd(){
	//原密码
	var userOldPwd=$("#userOldPwd").val();
	var userNewPwd=$("#userNewPwd").val();
	var sureNewPwd=$("#sureNewPwd").val();
	
	if(userOldPwd==null || userNewPwd==null ||sureNewPwd==null ){
		swal("请将必输内容填写完整后进行提交！",{icon:"info"});
		$("#userNewPwd").val('');
		$("#sureNewPwd").val('');
		$("#userOldPwd").val('');
	}else if(userOldPwd!=null&&password!=null&&userOldPwd!=password){
		 $("#changePwd").fadeOut(100);
		swal("您输入原密码错误！！请重新输入！！",{icon:"warning"});
		$("#userOldPwd").val('');
	}else if(userOldPwd!=null&&password!=null&&userNewPwd != sureNewPwd){
		 $("#changePwd").fadeOut(100);
		swal("您两次输入的新密码不一致，请重新输入！！",{icon:"warning"});
		//清空输入框
		$("#userNewPwd").val('');
		$("#sureNewPwd").val('');
	}else{
		$.ajax({
			type:'POST',
			url:'user_changePwd!updatePwd.action',
			data:{
				id:uid,
				userOldPwd:userOldPwd,
				userNewPwd:userNewPwd
				},
			success: function(data){
				 if (data.success == true){ 
					 $("#changePwd").fadeOut(100);
					 alert("密码修改成功，请重新登陆！！");
					 parent.location.href="login.jsp";
				 } else {  
					 $("#changePwd").fadeOut(100);
					 swal("系统异常！请重试！！",{icon:"error"});
				 }  
	        }  
		});
	}
};

/**
 * 取消更改密码
 */
function ChangePwdclear(){
	$("#userOldPwd").val('');
	$("#userNewPwd").val('');
	$("#sureNewPwd").val();
	$("#changePwd").fadeOut(100);
};


