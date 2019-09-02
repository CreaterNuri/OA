//用户id
var uid;
//用户名
var username;
//请假开始时间
var starttime;
//请假结束时间
var endtime;
$(function() {
	//路径取值 id
	uid = getQueryStringV(location.href, "id");
	//路径取值username
	username = getQueryStringV(decodeURI(location.href), "username");
	//初始化datagrid
	initdatagrid();
	//初始化下拉框
	initCombox();
	//点击关闭
	$(".tiptop a").click(function(){
		  $(".tip").fadeOut(200);
	});
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
	$('#leavetime').datagrid({      
	    title:"请假列表",
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
function queryLeave() {
    var options = $('#leavetime').datagrid('options');
    options.url = 'leavetime_query!queryAll.action';
    options.queryParams = {uid: uid};
    $('#leavetime').datagrid(options);
};

/**
 * 初始化添加下拉框
 */
function initCombox(){
	//取得报销类型放入
    $.ajax({
           type: "post",
           dataType:"json",
           url: "leavetype_query!queryAllType.action",
           success: function (data) {
               for (var i=0;i<data.list.length;i++){
                       var id = data.list[i].id;
                       var type = data.list[i].type; 
                       var opt = '<option value="'+id+'"selected>'+type+'</option>';
                       //添加 下拉列表
                       $('#leavettype').append(opt);
               }
           },
           error:function(error){
               swal("初始化下拉控件失败!!",{icon:"error"});
           }
    });
};

/**
 * 申请请假
 */
function addLeave(){
	//弹出添加框
	$("#addleavetime").fadeIn(100);
};
/**
 * 计算时间间隔
 */
function Calculation(startDate,endDate){
	if(startDate!='' && endDate!=''){
		var startTime = new Date(Date.parse(startDate.replace(/-/g,   "/"))).getTime();     
	    var endTime = new Date(Date.parse(endDate.replace(/-/g,   "/"))).getTime();     
	    //判断开始和结束时间
	    if(startTime > endTime){
	    	$(".tip").fadeOut(100);
	    	console.log("开始时间："+startTime+"结束时间："+endTime);
	    	/*swal("请正确选择请假的起止时间！",{icon:"info"});*/
	    	return null;
	    }else{
	    	var dates = Math.abs((startTime - endTime))/(1000*60*60*24);     
	    	var cdate= Math.round(dates)
	    	$("#leavedays").val(cdate);
	    	return  cdate;
	    }
	}else{
		swal("请选择请假开始和结束时间后进行提交申请！",{icon:"info"});
	}
};


/**
 * 提交请假单
 */
function SureAddLeave(){
	//请假类型
	var leavettype=$("#leavettype option:selected").text();
	//请假原因
	var leavemsg=$("#leavemsg").val();
	//开始时间
	starttime=$("#startdate").val();
	//结束时间
	endtime=$("#enddate").val();
	//请假时长
	var leavedays=Calculation(starttime,endtime);
	//审批人
	var approver=$("#approver").val();
	//截取年份
	var startyear=starttime.substring(0,4);
	var endyear =endtime.substring(0,4);
	/**
	 * 判断请假起止日期
	 */
	if(leavedays==null){
		//关闭弹出框
   		$(".tip").fadeOut(100);
   		swal("请正确选择请假的起止时间！请假开始日期应小于结束日期！！",{icon:"info"});
	}else if(leavettype=='' || leavemsg=='' || starttime=='' || endtime=='' || approver==''){
		 //关闭弹出框
     	  $(".tip").fadeOut(100);
		swal("请将请假条件填写完整后进行提交申请！",{icon:"info"});
	}else if(startyear!=endyear){
		$(".tip").fadeOut(100);
		swal("请假时间请仅限当年时间！谢谢！",{icon:"info"});
	}else if(leavedays>60){
		$(".tip").fadeOut(100);
		swal("请假时长大于两个月，请书面申请！谢谢！",{icon:"info"});
	}else{
		 $.ajax({
	           type: "post",
	           url: "leavetime_add!addleavetime.action",
	           data:{
	        	   uid:uid,
	        	   username:username,
	        	   leavettype:leavettype,
	        	   leavemsg:leavemsg,
	        	   starttime:starttime,
	        	   endtime:endtime,
	        	   leavedays:leavedays,
	        	   approver:approver
	           },
	           success: function (data) {
	        	   if(data.success == true){
	        		   swal("申请请假成功",{icon:"success"});
	        		   //关闭弹出框
	               	  $(".tip").fadeOut(100);
	        		   //重新加载公告列表
	        		   $('#leavetime').datagrid('reload');
	        	   } else {  
	               	swal("系统异常，申请失败！！！",{icon:"error"});
	               	$(".tip").fadeOut(100);
	               }  
	           },
	           error:function(error){
	           	swal("申请请假失败！！！",{icon:"error"});
	           	$(".tip").fadeOut(100);
	           }
	    });
	}
};
/**
 * 取消添加按钮
 */
function addclearLeave(){
	$("#approver").val('');
	$("#leavemsg").val('');
	 $(".tip").fadeOut(100);
};
