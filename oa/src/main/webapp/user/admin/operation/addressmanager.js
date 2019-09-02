//用户联系方式id
var id;
//删除用户联系方式id
var delid;
$(function() {
	//点击关闭
	$(".tiptop a").click(function(){
		  $(".tip").fadeOut(200);
	});
	//初始化下拉框
	initCombox();
	//验证输入邮箱
	$('#addemail').blur(function(){
          var value = $(this).val();
          var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
          var result = reg.test(value);
          if(!result){
        	  swal("邮箱格式错误，请重新输入！",{icon:"info"});
        	  $("#updateUserAddress").fadeOut(100);
              $(this).focus();
          }
	});
	//更新
	$('#updemail').blur(function(){
        var value = $(this).val();
        var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
        var result = reg.test(value);
        if(!result){
      	  swal("邮箱格式错误，请重新输入！",{icon:"info"});
      	  $("#adduserAddress").fadeOut(100);
          $(this).focus();
        }
	});
});
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
 * 添加用户联系方式
 */
function addUserAddress(){
	$("#adduserAddress").fadeIn(100);
};

/**
 * 提价添加信息
 */
function addNewUserAddress(){
	var name=$("#addname").val();
	var phone=$("#addphone").val();
	var address=$("#addaddress").val();
	var email=$("#addemail").val();
	var power=$("#power option:selected").text();
	if(name=='' || phone=='' || address=='' || email=='' || power==''){
		 swal("请将添加用户联系信息填写完整后进行提交！谢谢！",{icon:"info"});
	}else{
		$.ajax({
			type:'POST',
			url:'admin_addAddress!addAddress.action',
			data:{
				addname:$("#addname").val(),
				addphone:$("#addphone").val(),
				addaddress:$("#addaddress").val(),
				addemail:$("#addemail").val(),
				power:$("#power option:selected").text()
				},
			success: function(data){
	            if (data.success == true){ 
	            	//关闭弹出框
	            	$("#adduserAddress").fadeOut(100);
	            	swal("用户联系信息添加成功！",{icon:"success"});
	            	//重新加载公告列表
	            	$('#proj').datagrid('reload');
	            } else {  
	            	swal("系统异常，添加用户联系信息失败！！！",{icon:"error"});
	            }  
	        }  
		});
	}
};

/**
 * 更新用户联系方式
*/
function updateUserAddress(){
	//获得选中行
	var objs = $('#proj').datagrid("getSelected");
	if(objs){
		$("#updateUserAddress").fadeIn(50);
		//用户联系方式id
		id=objs.uid;
		//弹出管理框
		//注入选中值
		$("#updname").val(objs.name);
		$("#updphone").val(objs.phone);
		$("#updaddress").val(objs.address);
		$("#updemail").val(objs.email);
	}else{
		swal("请选中用户联系方式后进行操作！！！",{icon:"info"});
	}
};

/**
 * 提交用户联系方式
 */
function updUserAddress(){
	var updname=$("#updname").val();
	var updphone=$("#updphone").val();
	var updaddress=$("#updaddress").val();
	var updemail=$("#updemail").val();
	var updpower = $("#updpower option:selected").text();
	if(updname=='' || updphone=='' || updaddress=='' ||updemail=='' || updpower==''){
		swal("请将更新用户联系信息填写完整后进行提交！谢谢！",{icon:"info"});
	}else{
		$.ajax({
			type:'POST',
			url:'admin_addAddress!addAddress.action',
			data:{
				id:id,
				updname:$("#updname").val(),
				updphone:$("#updphone").val(),
				updaddress:$("#updaddress").val(),
				updemail:$("#updemail").val(),
				power:$("#updpower option:selected").text()
				},
			success: function(data){
	            if (data.success == true){ 
	            	//关闭弹出框
	            	$("#updateUserAddress").fadeOut(100);
	            	swal("用户联系信息更新成功！",{icon:"success"});
	            	//重新加载公告列表
	            	$('#proj').datagrid('reload');
	            } else {  
	            	swal("系统异常，更新用户联系信息失败！！！",{icon:"error"});
	            }  
	        }  
		});
	}
};

/**
 * 删除用户联系方式
 */
function deleteAddress(){
	//获得选中行
	var objs = $('#proj').datagrid("getSelected");
	if(objs){
		delid=objs.id;
			swal({
				  title: "温馨提示",
				  text: "你正在删除用户为"+objs.name+"的通讯信息",
				  icon: "info",
				  buttons: ["取消","确认"],
				  dangerMode: true
				}).then((willDelete) => {
				  if (willDelete) {
					  //调用删除方式
					  del(delid);
				  } else {
					  //取消删除
					  swal("已取消删除",{icon:"info"});
				  }
				});
		
	}else{
		swal("请选中用户联系方式后进行操作！！！",{icon:"info"});
	}
};

/**
 * 删除操作
 */
function del(delid){
	 //确认删除
	  $.ajax({
		  type:'post',
		  url: "admin_delAddress!delAddress.action",
		  data:{
			  	id : delid,
		  },
		  success: function(result){ 
			  	if(result.success){
			  		swal("删除用户联系方式成功！！！",{icon:"success"});
			  		//重新加载公告列表
	            	$('#proj').datagrid('reload');
				} else {   
					swal("系统异常，删除用户联系方式失败！！！",{icon:"error"});
				}  
		  }
	});
};

/**
 * 清空添加框
 */
function addclearUserAddress(){
	$("#addname").val('');
	$("#addphone").val('');
	$("#addaddress").val('');
	$("#addemail").val('');
	$("#adduserAddress").fadeOut(100);
};

/**
 * 清空更新框
 */
function updclearUserAddress(){
	$("#updname").val('');
	$("#updphone").val('');
	$("#updaddress").val('');
	$("#updemail").val('');
	$("#updateUserAddress").fadeOut(100);
};