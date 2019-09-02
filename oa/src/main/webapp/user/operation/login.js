var username;//当前登陆人
//用户登陆
function login(){
	//判断输入
	var userName = $("#username").val();
	var password = $("#password").val();
	//获取登陆身份
	var options=$("#type option:selected").text();
	if(username=='' || password ==''){
		swal("用户名或密码不能为空",{icon:"warning"});
	}else if(options=='管理员'){
		$.ajax({
			type:'POST',
			url:'admin_login!login.action',
			data:{
				username:userName,
				password:password
				},
			success: function(data){
	            if (data.success == true){  
	            	window.location.href='/oa/user/admin/main.jsp?username='+data.userName+'&id='+data.id;
	            } else {  
	            	$("#username").val('');
	            	$("#password").val('');
	            	swal("用户名或密码错误，请核对后重新输入！！！",{icon:"error"});
	            }  
	        }  
		});
	}else if(options=='用户'){
		$.ajax({
			type:'POST',
			url:'user_login!login.action',
			data:{
				username:userName,
				password:password
				},
			success: function(data){
	            if (data.success == true){  
	            	window.location.href='/oa/user/main.jsp?username='+data.userName+'&id='+data.id;
	            } else {  
	            	$("#username").val('');
	            	$("#password").val('');
	            	swal("用户名或密码错误，请核对后重新输入！！！",{icon:"error"});
	            }  
	        }  
		});
	}
};