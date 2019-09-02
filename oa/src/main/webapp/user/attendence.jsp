<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>OA管理系统</title>
<link href="css/style1.css" rel="stylesheet" type="text/css" />
<link href="js/skin/WdatePicker.css"rel="stylesheet" type="text/css" />
<link href="js/skin/default/datepicker.css"rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/WdatePicker.js"></script>
<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="js/lang/zh-cn.js"></script>
<script type="text/javascript" src="operation/attendence.js"></script>
<script src="js/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="easyui/themes/gray/easyui.css">
<link href="easyui/themes/default/easyui.css"rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/demo.css">
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body oncontextmenu="return false">
 <div class="place" >
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">考勤管理</a></li>
    </ul>
    </div>
    <div class="rightinfo">
    <div class="listtitle">
   		请选择需要查询的月份时间： <input id="dateChoose" type="text" class="Wdate"  onclick="WdatePicker({dateFmt:'yyyy-MM',minDate:'2000-1',maxDate:'2099-12'})" readonly="readonly" />
   		<ul class="toolbar1">
        <li class="queryUser" onclick="queryAttendenceInfo()"><span><img src="images/t04.png" /></span>查询</li>
    	</ul>
    </div>
    <div style="width:100%;height:60%;">
<table id="attendence" class="easyui-datagrid">
    <thead>  
        <tr>  
        	<th data-options="field:'id',hidden: true" id="id">ID</th>
            <th data-options="field:'power',width:80">职位</th>  
            <th data-options="field:'dutydays',width:200">应出勤天数</th>  
            <th data-options="field:'requestdays',width:80">请假</th>  
            <th data-options="field:'overtimedays',width:100">加班</th>
            <th data-options="field:'actualdays',width:100">实际出勤天数</th>
            <th data-options="field:'year',width:100">年份</th>
            <th data-options="field:'month',width:100">月份</th>
        </tr>  
    </thead>  
</table> 
  </div>
  </div>
</html>
