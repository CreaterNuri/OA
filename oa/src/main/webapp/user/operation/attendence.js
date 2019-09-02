//获取用户id
var uid;
//获取选择时间
var date;
$(function() {
	//路径取值
	alertMsg();
	//初始化datagrid
	initdatagrid();
});
//取出连接路径值
function alertMsg() {
	var Ohref = window.location.href;
	var arrhref = Ohref.split("?id=");
	uid = arrhref[1];
};
/**
 * 初始化datagrid
 */
function initdatagrid(){
	$('#attendence').datagrid({      
	    title:"考勤列表",
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
 * 根据月份查询
 */
function queryAttendenceInfo(){
	//获取选择日期
	date=$("#dateChoose").val();
	if(date!=''){
		//加载数据
		loadData();
	}else{
		swal("请选择查询时期后进行操作!!",{icon:"info"});
	}
};
/**
 * 加载数据
 */
function loadData() {
    var options = $('#attendence').datagrid('options');
    options.url = 'attendence_query!queryAttendence.action';
    options.queryParams = { date: date, uid: uid };
    $('#attendence').datagrid(options);
};