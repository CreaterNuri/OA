//用户id
var id;
//删除时id
var delid;
$(function() {
	//点击关闭
	$(".tiptop a").click(function(){
		  $(".tip").fadeOut(200);
	});
	//初始化下拉框
	initCombox();
});

/**
 * 初始化datagrid
 * @returns
 */
function initCombox(){
	//取得报销类型放入
    $.ajax({
           type: "post",
           dataType:"json",
           url: "admin_queryPower!queryPower.action",
           success: function (data) {
               for (var i=0;i<data.powerlist.length;i++){
                       var id = data.powerlist[i].id;
                       var type = data.powerlist[i].name; 
                       var opt = '<option value="'+id+'"selected>'+type+'</option>';
                       //添加 下拉列表
                       $('#power').append(opt);
                       //更新 下拉框
                       $('#updpower').append(opt);
               }
           },
           error:function(error){
               swal("初始化下拉控件失败",{icon:"error"});
           }
    });
};

/**
 * 删除用户
 */
function deleteMsg(){
	//获得选中行
	var objs = $('#proj').datagrid("getSelected");
	if(objs){
		delid=objs.uid;
		if(objs.state=='0'){
			swal("该用户状态正常！请将该用户状态修改后进行删除操作！谢谢！",{icon:"warning"});
		}else{
			swal({
				  title: "温馨提示",
				  text: "你正在删除用户为"+objs.uname+"的信息",
				  icon: "info",
				  buttons: ["取消","确认"],
				  dangerMode: true
				}).then((willDelete) => {
				  if (willDelete) {
					  del(delid);
				  } else {
					  //取消删除
					  swal("已取消删除",{icon:"info"});
				  }
				});
		}
	}else{
		swal("请选中用户后进行操作！！！",{icon:"info"});
	}
};

/**
 * 删除操作
 */
function del(delid){
	 //确认删除
	  $.ajax({
		  type:'post',
		  url: "admin_delUser!delUser.action",
		  data:{
			  	id : delid,
		  },
		  success: function(result){ 
			  	if(result.success){
			  		swal("删除用户成功！！！",{icon:"success"});
			  		//重新加载公告列表
	            	$('#proj').datagrid('reload');
				} else {   
					swal("系统异常，删除失败！！！",{icon:"error"});
				}  
		  }
	});
};

/**
 * 添加用户
 */
function addUserMsg(){
	$("#adduser").fadeIn(100);
};
/**
 * 提交添加用户信息
 */
function addNewUserfn(){
	var username=$("#uname").val();
	var pwd=$("#upwd").val();
	var state=$("#state option:selected").val();
	var power=$("#power option:selected").text();
	if(username=='' || pwd==''|| state=='' || power==''){
		$("#adduser").fadeOut(100);
		swal("请将添加用户信息填写完整后进行提交！谢谢！",{icon:"info"});
	}else{
		$.ajax({
			type:'POST',
			url:'admin_addUser!addUser.action',
			data:{
				uname:$("#uname").val(),
				upwd:$("#upwd").val(),
				state:$("#state option:selected").val(),
				power:$("#power option:selected").text()
				},
			success: function(data){
	            if (data.success == true){ 
	            	//关闭弹出框
	            	$("#adduser").fadeOut(100);
	            	swal("用户添加成功！",{icon:"success"});
	            	//重新加载公告列表
	            	$('#proj').datagrid('reload');
	            } else {  
	            	swal("系统异常，添加用户失败！！！",{icon:"error"});
	            	//关闭弹出框
	            	$("#adduser").fadeOut(100);
	            }  
	        }  
		});
	}
	
};

/**
 * 更新用户
 */
function updateUserMsg(){
	//获得选中行
	var objs = $('#proj').datagrid("getSelected");
	if(objs){
		$("#updateUser").fadeIn(50);
		//用户id
		id=objs.uid;
		//弹出管理框
		//注入选中值
		$("#updname").val(objs.uname);
		$("#updpwd").val(objs.upwd);
	}else{
		swal("请选中用户后进行操作！！！",{icon:"info"});
	}
};

/**
 * 提交更新内容
 */
function updUserMsg(){
	var updusername=$("#updname").val();
	var updpwd=$("#updpwd").val();
	var updstate=$("#updstate option:selected").val();
	var updpower=$("#updpower option:selected").text();
	if(updusername=='' ||updpwd=='' || updstate=='' || updpower=='' ){
		$("#updateUser").fadeOut(100);
		swal("请将更新用户信息填写完整后进行提交！谢谢！",{icon:"info"});
	}else{
		$.ajax({
			type:'POST',
			url:'admin_updUser!updUser.action',
			data:{
				id:id,
				updname:$("#updname").val(),
				updpwd:$("#updpwd").val(),
				updstate:$("#updstate option:selected").val(),
				updpower:$("#updpower option:selected").text()
				},
			success: function(data){
	            if (data.success == true){ 
	            	//关闭弹出框
	            	$("#updateUser").fadeOut(100);
	            	swal("用户信息修改成功！",{icon:"success"});
	            	//重新加载公告列表
	            	$('#proj').datagrid('reload');
	            } else {  
	            	swal("系统异常，用户信息更新失败！！！",{icon:"error"});
	            	//关闭弹出框
	            	$("#updateUser").fadeOut(100);
	            }  
	        }  
		});
	}
};
/**
 * 取消添加
 * @returns
 */
function ClearaddUserMsg(){
	$("#uname").val('');
	$("#upwd").val('');
	$("#adduser").fadeOut(100);
};

/**
 * 取消更新
 */
function updclearUserMsg(){
	$("#updname").val('');
	$("#updpwd").val('');
	$("#updateUser").fadeOut(100);
};
