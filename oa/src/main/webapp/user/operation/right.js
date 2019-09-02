//公告id
var id;
$(function() {
	alertMsg();
	add();
	//点击关闭
	$(".tiptop a").click(function(){
		  $(".tip").fadeOut(200);
	});
});

//取出连接路径值
function alertMsg() {
	var Ohref = window.location.href;
	var arrhref = Ohref.split("?username=");
	username = arrhref[1];
};

//公告添加
function add(){
	$(".addclick").click(function(){
		//弹出添加框
		 $(".addclick").click(function(){
			 $("#addNotice").fadeIn(100);
		});
		 //设置发布人为当前登陆人
		$("#publisher").val(username);
	});
};

//公告提交
function addNotice(){
	var noticeNum=$("#noticeNum").val();
	var noticeMsg=$("#noticeMsg").val();
	var releasetime=$("#releasetime").val();
	if(noticeNum == '' || noticeMsg=='' || releasetime==''){
		$(".tip").fadeOut(100);
		swal("请将公告的相关内容填写完整后进行提交！",{icon:"info"});
	}else{
		$.ajax({
			type:'POST',
			url:'notice_add!addNotice.action',
			data:{
				noticeNum:$("#noticeNum").val(),
				noticeMsg:$("#noticeMsg").val(),
				publisher:username,
				releasetime:$("#releasetime").val()
				},
			success: function(data){
	            if (data.success == true){ 
	            	swal("公告添加成功！",{icon:"success"});
	            	//关闭弹出框
	            	$(".tip").fadeOut(100);
	            	//重新加载公告列表
	            	$('#proj').datagrid('reload');
	            } else {  
	            	swal("系统异常，添加失败！！！",{icon:"error"});
	            }  
	        }  
		});
	}
};

//删除公告
function delteNotice(){
	var objs = $('#proj').datagrid("getSelected");
	if(objs){
		if(objs.publisher!=username){
			swal("您没有权限删除该公告！！！",{icon:"warning"});
		}else{
			var msg = "确认删除公告？"; 
			if (confirm(msg)==true){ 
				$.ajax({
					  type:'post',
					  url: "notice_del!delNotice.action",
					  data:{
						  	id : objs.id,
					  },
					  success: function(result){ 
						  	if(result.success){
						  		swal("删除成功！！！",{icon:"success"});
						  		//重新加载公告列表
				            	$('#proj').datagrid('reload');
							} else {   
								swal("系统异常，删除失败！！！",{icon:"error"});
							}  
					  }
				});
			  return true; 
			 }else{ 
			  return false; 
			 } 
		}
	}else{
		swal("请选中公告后进行操作！！！",{icon:"info"});
	}
};

//更新公告
function updateNotice(){
	//获取选择公告
	var objs = $('#proj').datagrid("getSelected");
	//设置全局项目id
	if(objs){
		id=objs.id;
		if(objs.publisher!=username){
			$(".tip").fadeOut(100);
			swal("您没有权限更新该公告！！！",{icon:"info"});
			//不弹出更新框
		}else{
			//弹出更新框
			 $(".updclick").click(function(){
				 $(".tip").fadeIn(50);
			});
			 //将各个信息添加到更新框中
			 //公告编号
			 $("#updnoticeNum").val(objs.number);
			 //公告内容
			 $("#updnoticeMsg").val(objs.noticemsg);
			 //发布人
			 $("#updpublisher").val(username);
			 //发布时间
			 $("#updreleasetime").val(objs.releasetime);
		}
	}else{
		swal("请选中公告后进行操作！！！",{icon:"info"});
	}
};

//提交更新
function updNotice(){
	var updnoticeNum=$("#updnoticeNum").val();
	var updnoticeMsg=$("#updnoticeMsg").val();
	var updreleasetime=$("#updreleasetime").val();
	if(updnoticeNum == '' || updnoticeMsg=='' || updreleasetime==''){
		$(".tip").fadeOut(100);
		swal("请将更新内容填写完整后进行提交！",{icon:"success"});
	}else{
		$.ajax({
			  type:'post',
			  url: "notice_upd!updateNotice.action",
			  data:{
				  	id :id,
				  	username:username,
				  	updnoticeNum:$("#updnoticeNum").val(),
				  	updnoticeMsg:$("#updnoticeMsg").val(),
				  	updreleasetime:$("#updreleasetime").val()
			  },
			  success: function(result){ 
				  	if(result.success){
				  		swal("更新成功！！！",{icon:"success"});
				  		$(".tip").fadeOut(100);
				  		//重新加载公告列表
		            	$('#proj').datagrid('reload');
					} else {   
						swal("系统异常，更新失败！！！",{icon:"error"});
					}  
			  }
		});	
	}
};
$("input[name='form.organizeUUID']").val('');
//取消添加，关闭弹出框
function addclearNotice(){
	//清空输入框
	$("#noticeNum").val('');
	$("#noticeMsg").val('');
	$("#releasetime").val('');
	//关闭输入框
	$(".tip").fadeOut(100);
};
//取消更新，关闭弹出框
function updclearNotice(){
	//清空输入框
	$("#updnoticeNum").val('');
	$("#updnoticeMsg").val('');
	$("#updpublisher").val('');
	$("#updreleasetime").val('');
	$(".tip").fadeOut(100);
};
