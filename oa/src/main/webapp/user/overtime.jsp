<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>OA管理系统</title>
<link rel="stylesheet" href="Kalendae/build/kalendae.css" type="text/css">
<link href="css/style1.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='Kalendae/build/kalendae.standalone.js'></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="operation/overtime.js"></script>
<link rel="stylesheet" type="text/css" href="easyui/themes/gray/easyui.css">
<link href="easyui/themes/default/easyui.css"rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/demo.css">
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/lang/zh-cn.js"></script>
<script src="js/sweetalert.min.js"></script>
</head>
<body>
<div class="place" >
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">加班管理</a></li>
    </ul>
    </div>
    <div class="rightinfo">
     <div class="tools">
    	<ul class="toolbar">
        <li class="addclick" onclick="addOvertime()"><span><img src="images/t01.png" /></span>加班申请</li>
        <li class="updclick" onclick="queryOvertime()"><span><img src="images/t02.png" /></span>加班记录查询</li>
        </ul>
    </div>
     <div id="table" style="width:100%;height:81%;">
     <table id="overtime" class="easyui-datagrid">
    <thead>  
        <tr>  
        	<th data-options="field:'id',hidden: true" id="id">ID</th>
        	<th data-options="field:'power',width:80">职位</th>  
            <th data-options="field:'overtimedays',width:80">加班时长</th>  
            <th data-options="field:'year',width:80">年份</th>  
            <th data-options="field:'month',width:80">月份</th>  
            <th data-options="field:'approver',width:100">审批人</th>
            <th data-options="field:'state',width:100">审批人</th>
        </tr>  
    </thead>  
</table> 
</div>
	<div id="addOvertime" style="display:none">
    <div  class="listtitle" >
   	请选择本月加班时间：
    </div>
    <div  id="datepk" style="width: 100%;">
    </div>
          请填写本月加班审核人：*<input  id="approver" type="text" class="dfinput" onkeyup="this.value=this.value.replace(/[^\u4e00-\u9fa5]/g,'')" placeholder="该项为必填项，只能输入长度不大于6个字符的中文" maxlength="6"/>
    <div class="tipbtn">
        <input id="addNotice" type="button"  class="sure" value="提交"  onclick="upovertime()" />&nbsp;
        <input id="clearNotice" type="button"  class="cancel" value="取消" onclick="clearovertime()" />
    </div>
     </div>
    </div>
</body>
<script type="text/javascript">
    
</script>
</html>