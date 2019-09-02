//存放日期数组
var a = new Array();
//用户id
var id;
//用户名
var username;
//年份
var year;
//月份
var month;
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
	//初始化日历表
	new Kalendae(document.getElementById("datepk"), {
	            months:1,
	            mode:'multiple',
	            subscribe: {
	                   'date-clicked': function (date) {
	                       //数据存放到数组中
	                       putArray(date);
	                   }
	               }
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
 * 加班添加
 */
function addOvertime(){
	$("#table").hide();
	$("#addOvertime").show();
};


/**
 * 初始化datagrid
 */
function initdatagrid(){
	$('#overtime').datagrid({      
	    title:username+""+"加班记录",
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
 * 查询加班记录
 */ 
function queryOvertime(){
		$("#table").show();
		$("#addOvertime").hide();
		var options = $('#overtime').datagrid('options');
	    options.url = 'overtimet_query!queryAll.action';
	    options.queryParams = {uid:id};
	    $('#overtime').datagrid(options);
};

/**
 *取到选择的日期 
 */
function putArray(data){
	var time=data._i;
	//获取年份
	year=time.substring(0,4);
	//获取月份
	month=time.substring(5,7);
	//将数据存放入数组中
	if(a.length>0){
		for(i=0;i<a.length;i++){
			var flag = true;
			if(time==a[i]){
				flag=false;
				//删除重复元素
				a.splice(i,1);
				break;
			}
		}
		if(flag){
			a.push(time);
		}
	}else{
		a.push(time);
	}
	
}; 

/**
 *提交加班表
 */
function upovertime(){
	//调用去重方法，计算array的长度
	var size=a.length;
	//获取审核人
	var approver=$("#approver").val();
	if(approver==''|| approver==null){
		swal("请填写审核人后进行提交！！！",{icon:"warning"});
	}else{
		$.ajax({
			type:'POST',
			url:'overtimet_add!addOvertime.action',
			data:{
				id:id,
				year:year,
				month:month,
				length:size,
				approver:approver
				},
			success: function(data){
	            if (data.success == true){ 
	            	alert("加班申请成功！！");
	            	window.location.reload();
	            } else {  
	            	swal("系统异常，申请失败！！！",{icon:"error"});
	            }  
	        }  
		});
	}
};

/**
 * 取消加班填写
 */
function clearovertime(){
	$("#addOvertime").hide();
	queryOvertime();
};
