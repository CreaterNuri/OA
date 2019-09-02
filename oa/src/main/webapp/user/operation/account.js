//登陆用户名
var username;
//选中报销单id
var id;
//报销单状态
var state;
//用户id
var uid;
$(function() {
	//路径取值 id
	id = getQueryStringV(location.href, "id");
	//路径取值username
	username = getQueryStringV(decodeURI(location.href), "username");
	//初始化datagrid
	initdatagrid();
	//点击关闭
	$(".tiptop a").click(function(){
		  $(".tip").fadeOut(200);
	});
	//添加报销单
	addAccount();
	//初始化添加下拉框
	initCombox();
});
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
 * 初始化datagrid
 */
function initdatagrid(){
	$('#account').datagrid({      
	    title:username +"   报销单列表",
	    singleSelect:'true',
	    url:'account_query!queryAll.action?username='+username,
	    fit:'false',
	    fitColumns:'true',
	    showPageList:'true',
	    idField:'id',
	    pagination:'true',
	    pageSize:'10',
	    rownumbers:'true',
	    showPageList:'false',
	    pageList: [5,10,15,20]
	});    
};
/**
 * 添加报销单
 */
function addAccount(){
	//弹出添加框
	$(".addclick").click(function(){
		$("#addAccount").fadeIn(100);
	});	
};
/**
 * 初始化添加下拉框
 */
function initCombox(){
	//取得报销类型放入
    $.ajax({
           type: "post",
           dataType:"json",
           url: "reimburses_querytype!findAllType.action",
           success: function (data) {
               for (var i=0;i<data.reimburseslist.length;i++){
                       var id = data.reimburseslist[i].id;
                       var type = data.reimburseslist[i].type; 
                       var opt = '<option value="'+id+'"selected>'+type+'</option>';
                       //添加 下拉列表
                       $('#accounttype').append(opt);
                       //更新 下拉框
                       $('#updaccounttype').append(opt);
               }
           },
           error:function(error){
               swal("初始化下拉控件失败",{icon:"error"});
           }
    });
};
/**
 * 确认提交报销单
 */
function SureAddAccount(){
	var money=$("#money").val();
	var approver=$("#approver").val();
	var date=$("#date").val();
	if(money == '' || approver == '' || date == ''){
		//关闭弹出框
    	$("#addAccount").fadeOut(100);
		swal("请将报销单信息填写完整后进行提交！谢谢！",{icon:"info"});
	}else{
		$.ajax({
			type:'POST',
			url:'account_add!addAccount.action',
			data:{
				accounttype:$("#accounttype option:selected").text(),
				money:$("#money").val(),
				username:username,
				approver:$("#approver").val(),
				date:$("#date").val()
				},
			success: function(data){
	            if (data.success == true){ 
	            	//关闭弹出框
	            	$("#addAccount").fadeOut(100);
	            	swal("申请报销成功！",{icon:"success"});
	            	//重新加载公告列表
	            	$('#account').datagrid('reload');
	            } else {  
	            	swal("系统异常，申请报销失败！！！",{icon:"error"});
	            }  
	        }  
		});
	}
};
/**
 * 取消添加
 */
function addclearAccount(){
	//清空输入框
	$("#money").val('');
	$("#approver").val('');
	//关闭输入框
	$("#addAccount").fadeOut(100);
};
/**
 * 报销单管理
 */
function managerAccount(){
	//获取选中报销单
	var objs = $('#account').datagrid("getSelected");
	if(objs){
		//报销单id
		id=objs.id;
		//得到报销单状态
		state=objs.state;
		if(state == '正在审批'){
			//弹出管理框
			$("#managerAccount").fadeIn(50);
			//注入选中值
			$("#updmoney").val(objs.money);
			$("#updapprover").val(objs.approver);
		}else{
			swal("当前报销单已经完成审批，不可进行修改！",{icon:"warning"});
		}
	}else{
		swal("请选中报销单后进行操作！！！",{icon:"info"});
	}
};
/**
 * 报销单更新提交
 */
function updAccount(){
	var updmoney=$("#updmoney").val();
	var updapprover=$("#updapprover").val();
	var upddate=$("#upddate").val();
	if(updmoney == '' || updapprover == '' || upddate == ''){
		$("#managerAccount").fadeOut(100);
		swal("请将报销单信息填写完整后进行提交！谢谢！",{icon:"info"});
	}else{
		$.ajax({
			type:'POST',
			url:'account_manager!managerAccount.action',
			data:{
				id:id,
				updaccounttype:$("#updaccounttype option:selected").text(),
				updmoney:$("#updmoney").val(),
				username:username,
				updapprover:$("#updapprover").val(),
				state:state,
				upddate:$("#upddate").val()
				},
			success: function(data){
	            if (data.success == true){ 
	            	//关闭弹出框
	            	$("#managerAccount").fadeOut(100);
	            	swal("报销单修改成功！",{icon:"success"});
	            	//重新加载公告列表
	            	$('#account').datagrid('reload');
	            } else {  
	            	swal("系统异常，报销单更新失败！！！",{icon:"error"});
	            }  
	        }  
		});
	}
	
};
/**
 * 取消更新
 */
function updclearAccount(){
	//清空输入框
	$("#updmoney").val('');
	$("#updapprover").val('');
	//关闭输入框
	$("#managerAccount").fadeOut(100);
};


