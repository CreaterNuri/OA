//信息id
var id;
//加班id
var oid;
//报销id
var aid;
//请假
var lid;
//信息处理状态
var state;
$(function() {
	//路径取值 id
	uid = getQueryStringV(location.href, "id");
	//路径取值username
	username = getQueryStringV(decodeURI(location.href), "username");
	//点击关闭
	$(".tiptop a").click(function(){
		  $(".tip").fadeOut(200);
	});
	//初始化表格
	initdatagrid();
	//加载数据
	loadData();
	
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
	$('#message').datagrid({      
	    title:"消息列表",
	    singleSelect:'true',
	    url:'',
	    fit:'true',
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
 * 加载数据
 */
function loadData() {
    var options = $('#message').datagrid('options');
    options.url = 'usermessage_queryAll!queryAll.action';
    options.queryParams = { username:username};
    $('#message').datagrid(options);
};

/**
 * 信息处理
 */
function updateMsg(){
	//获取选择信息
	var objs = $('#message').datagrid("getSelected");
	if(objs){
		//id
		id=objs.id;
		//报销aid
		aid = objs.aid;
		//请假
		lid=objs.lid;
		//加班
		oid = objs.oid;
		$("#managerMsg").fadeIn(100); 
		//输入框赋值
		$("#userMsg").val(objs.message);
		$("#appicant").val(objs.applicant);
		$("#time").val(objs.time);
	}else{
		 swal("请选择信息后进行操作！！！",{icon:"success"});
	}
	 
};
/**
 * 取消处理
 */
function Manageclear(){
	$(".tip").fadeOut(100);
};

/**
 * 提交处理
 */
function Manager(){
	//处理信息状态
	var advicestate=$("#advicestate option:selected").text();
	$.ajax({
		  type:'post',
		  url: "usermessage_manager!managerMsg.action",
		  data:{
			  	id :id,
			  	oid:oid,
			  	aid:aid,
			  	lid:lid,
			  	advicestate:advicestate,
			  	approveradvice:$("#approveradvice").val()
		  },
		  success: function(result){ 
			  	if(result.success){
			  		swal("处理信息成功！！！",{icon:"success"});
			  		$(".tip").fadeOut(100);
			  		//重新加载公告列表
	            	$('#message').datagrid('reload');
				} else {   
					swal("系统异常，更新失败！！！",{icon:"error"});
					$(".tip").fadeOut(100);
				}  
		  }
	});
	
};
