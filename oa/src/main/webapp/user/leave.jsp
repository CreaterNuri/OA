<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>OA管理系统</title>
<link href="css/style2.css" rel="stylesheet" type="text/css" />
<link href="js/skin/WdatePicker.css"rel="stylesheet" type="text/css" />
<link href="js/skin/default/datepicker.css"rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/WdatePicker.js"></script>
<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
<link rel="stylesheet" type="text/css" href="easyui/themes/gray/easyui.css">
<link href="easyui/themes/default/easyui.css"rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/demo.css">
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/lang/zh-cn.js"></script>
<script src="js/sweetalert.min.js"></script>
<script type="text/javascript" src="operation/leave.js"></script>
</head>
<body>
 <div class="place" >
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">请假管理</a></li>
    </ul>
    </div>
    <div class="rightinfo">
     <div class="tools">
    	<ul class="toolbar">
        <li class="addclick" onclick="addLeave()"><span><img src="images/t01.png" /></span>请假申请</li>
        <li class="updclick" onclick="queryLeave()"><span><img src="images/t02.png" /></span>请假单查询</li>
        </ul>
    </div>
    <div style="width:100%;height:81%;">
    <!-- 请假单查询 -->
    <table id="leavetime" class="easyui-datagrid">
    <thead>  
        <tr>  
        	<th data-options="field:'id',hidden: true" id="id">ID</th>
            <th data-options="field:'uid',hidden: true">用户ID</th>  
            <th data-options="field:'username',width:60">用户名</th>  
            <th data-options="field:'type',width:60">请假类型</th>  
            <th data-options="field:'leavemsg',width:100">请假类型</th>
            <th data-options="field:'starttime',width:100">开始时间</th>
            <th data-options="field:'endtime',width:100">结束时间</th>
            <th data-options="field:'leavedays',width:60">请假时长</th>
            <th data-options="field:'approver',width:60">审批人</th>
             <th data-options="field:'state',width:80">审批进度</th>
        </tr>  
    </thead>  
</table> 
</div>
</div>
<!-- 请假申请 -->
 	<div class="tip" id="addleavetime">
    	<div class="tiptop"><span>请假申请</span><a></a></div>
      	<div class="tipinfo">
      	<form id="leaveform" method="post">
		<ul>
		<li><label id="alertMsg" style="color: red;"> 提示：带有*的字段为必填项，请注意填写！</label></li><br/>
		<li><label>请假类型</label>*
			<select class="dfinput" id="leavettype" name="leavetype"></select>
		</li>
    	<li><label>请假原因</label>*&nbsp;<input name="" id="leavemsg" type="text" class="dfinput" onkeyup="this.value=this.value.replace(/[^\u4e00-\u9fa5]/g,'')" placeholder="该项为必填项，只能输入长度不大于50个字符的中文" maxlength="50"/></li>
    	<li><label>开始日期</label>*&nbsp;<input name="" id="startdate" type="text" class="dfinput" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',position:{left:380,top:-10}})"/></li>
    	<li><label>结束日期</label>*&nbsp;<input name="" id="enddate" type="text" class="dfinput" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',position:{left:380,top:-10}})"/></li>
   	 	<li><label>请假时长&nbsp;</label>*<input name="" id="leavedays" type="text" class="dfinput" readonly="readonly"/></li>
   	 	<li><label>审&nbsp;批人&nbsp;&nbsp;&nbsp;&nbsp;</label>*<input name="" id="approver" type="text" class="dfinput" onkeyup="this.value=this.value.replace(/[^\u4e00-\u9fa5]/g,'')" placeholder="该项为必填项，只能输入长度不大于6个字符的中文" maxlength="6"/></li>
    	</ul>
    	</form>
        </div>
        <div class="tipbtn">
        <input id="addLeave" type="button"  class="sure" value="确定"  onclick="SureAddLeave()" />&nbsp;
        <input id="clearLeave" type="button"  class="cancel" value="取消" onclick="addclearLeave()" />
        </div>
    </div>
</body>
</html>