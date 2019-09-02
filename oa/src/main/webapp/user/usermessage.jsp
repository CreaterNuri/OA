<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>OA管理系统</title>
<link href="css/style3.css" rel="stylesheet" type="text/css" />
<link href="js/skin/WdatePicker.css"rel="stylesheet" type="text/css" />
<link href="js/skin/default/datepicker.css"rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/WdatePicker.js"></script>
<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="operation/usermessage.js"></script>
<link rel="stylesheet" type="text/css" href="easyui/themes/gray/easyui.css">
<link href="easyui/themes/default/easyui.css"rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/demo.css">
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/lang/zh-cn.js"></script>
<script src="js/sweetalert.min.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
</head>
<body oncontextmenu="return false">
 <div class="place" >
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">信息管理</a></li>
    </ul>
    </div>
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        <li class="addclick" onclick="updateMsg()"><span><img src="images/t02.png" /></span>信息处理</li>
        </ul>
        
    </div>
    <div style="width:100%;height:81%;">
    <table id="message" class="easyui-datagrid">
    <thead>  
        <tr>  
        	<th data-options="field:'id',hidden: true" id="id">ID</th>
        	<th data-options="field:'aid',hidden: true" id="mid">aID</th>
        	<th data-options="field:'lid',hidden: true" id="mid">lID</th>
        	<th data-options="field:'oid',hidden: true" id="oid">oID</th>
            <th data-options="field:'message',width:200">信息内容</th>  
            <th data-options="field:'applicant',width:80">申请人</th>  
            <th data-options="field:'time',width:200">申请时间</th> 
        </tr>  
    </thead>  
</table> 
</div>
</div>
 	<div class="tip" id="managerMsg">
    	<div class="tiptop"><span>处理申请信息</span><a></a></div>
      	<div class="tipinfo">
      	<form id="noticeform" method="post">
		<ul>
		<li><label id="alertMsg" style="color: red;"> 提示：带有*的字段为必填项，请注意填写！</label></li><br/>
    	<li><label>信息内容</label>&nbsp;&nbsp;&nbsp;<input name="" id="userMsg" type="text" class="dfinput" readonly="readonly"/></li>
   	 	<li><label>申&nbsp;请人&nbsp;&nbsp;&nbsp;&nbsp;</label>&nbsp;&nbsp;<input name="" id="appicant" type="text" class="dfinput" readonly="readonly"/></li>
    	<li><label>申请时间</label>&nbsp;&nbsp;&nbsp;<input name="" id="time" type="text" class="dfinput" readonly="readonly"/></li>
    	<li><label>处理意见</label>&nbsp;&nbsp;&nbsp;<input name="" id="approveradvice" type="text" class="dfinput"/></li>
    	<li><label>处理状态</label>*&nbsp;
    	<select id="advicestate" class="dfinput" name="advicestate">
    		<option value="0">搁置处理</option>
    		<option value="1">已处理</option>
		</select>
		</li>
    	</ul>
    	</form>
        </div>
        <div class="tipbtn">
        <input id="addNotice" type="button"  class="sure" value="确定"  onclick="Manager()" />&nbsp;
        <input id="clearNotice" type="button"  class="cancel" value="取消" onclick="Manageclear()" />
        </div>
    </div>
</body>
</html>