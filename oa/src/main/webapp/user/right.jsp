<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>OA管理系统</title>
<link href="css/style5.css" rel="stylesheet" type="text/css" />
<link href="js/skin/WdatePicker.css"rel="stylesheet" type="text/css" />
<link href="js/skin/default/datepicker.css"rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/WdatePicker.js"></script>
<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="operation/right.js"></script>
<link rel="stylesheet" type="text/css" href="easyui/themes/gray/easyui.css">
<link href="easyui/themes/default/easyui.css"rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/demo.css">
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/lang/zh-cn.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
<script src="js/sweetalert.min.js"></script>
</head>
<body oncontextmenu="return false">
 <div class="place" >
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">公告管理</a></li>
    </ul>
    </div>
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        <li class="addclick"><span><img src="images/t01.png" /></span>添加</li>
        <li class="updclick" onclick="updateNotice()"><span><img src="images/t02.png" /></span>修改</li>
        <li class="delclick" onclick="javascript:return delteNotice()"><span><img src="images/t03.png" /></span>删除</li>
        </ul>
        
        <!-- <ul class="toolbar1">
        <li><span><img src="images/t05.png" /></span>设置</li>
        </ul> -->
    </div>
    
    
    <table id="proj" class="easyui-datagrid" title="公告管理"
			data-options="singleSelect:true,
			toolbar: '#org-toolbar',
			fit:false,
			fitColumns:true,
			url:'notice_query!queryAll.action',
			showPageList:true,
			idField:'id',
			pagination:true,
			pageList: [5,10,15,20]">
    <thead>  
        <tr>  
        	<th data-options="field:'id',hidden: true" id="id">ID</th>
            <th data-options="field:'number',width:80">公告编号</th>  
            <th data-options="field:'noticemsg',width:200">公告内容</th>  
            <th data-options="field:'publisher',width:80">发布人</th>  
            <th data-options="field:'releasetime',width:100">发布时间</th>
        </tr>  
    </thead>  
</table> 
</div>
 	<div class="tip" id="addNotice">
    	<div class="tiptop"><span>添加公告</span><a></a></div>
      	<div class="tipinfo">
      	<form id="noticeform" method="post">
		<ul>
		<li><label id="alertMsg" style="color: red;"> 提示：带有*的字段为必填项，请注意填写！</label></li><br/>
    	<li><label>公告编号</label>&nbsp;*<input name="" id="noticeNum" type="text" class="dfinput" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" placeholder="该项为必填项，只能输入长度不大于12个字符的字母和数字" maxlength="12"/></li>
    	<li><label>公告内容</label>&nbsp;*<input name="" id="noticeMsg" type="text" class="dfinput" onkeyup="this.value=this.value.replace(/[^\u4e00-\u9fa5]/g,'')" placeholder="该项为必填项，只能输入长度不大于40个字符的中文" maxlength="40"/></li>
   	 	<li><label>发&nbsp;布人&nbsp;&nbsp;&nbsp;&nbsp;</label>*<input name="" id="publisher" type="text" class="dfinput" readonly="readonly"/></li>
    	<li><label>发布时间</label>&nbsp;*<input name="" id="releasetime" type="text" class="dfinput" onclick="WdatePicker({dateFmt:'yyyy:MM:dd HH:mm:ss',position:{left:380,top:-10}})"/></li>
    	</ul>
    	</form>
        </div>
        <div class="tipbtn">
        <input id="addNotice" type="button"  class="sure" value="确定"  onclick="addNotice()" />&nbsp;
        <input id="clearNotice" type="button"  class="cancel" value="取消" onclick="addclearNotice()" />
        </div>
    </div>
    <!-- 更新公告 -->
    <div class="tip">
    	<div class="tiptop"><span>更新公告</span><a></a></div>
      	<div class="tipinfo">
      	<form id="noticeform" method="post">
		<ul>
		<li><label id="alertMsg" style="color: red;"> 提示：带有*的字段为必填项，请注意填写！</label></li><br/>
    	<li><label>公告编号</label>&nbsp;*<input name="" id="updnoticeNum" type="text" class="dfinput" readonly="readonly"/></li>
    	<li><label>公告内容</label>&nbsp;*<input name="" id="updnoticeMsg" type="text" class="dfinput" onkeyup="this.value=this.value.replace(/[^\u4e00-\u9fa5]/g,'')" placeholder="该项为必填项，只能输入长度不大于40个字符的中文" maxlength="40"/></li>
   	 	<li><label>发&nbsp;布人&nbsp;&nbsp;&nbsp;&nbsp;</label>*<input name="" id="updpublisher" type="text" class="dfinput" readonly="readonly" /></li>
    	<li><label>发布时间</label>&nbsp;*<input name="" id="updreleasetime" type="text" class="dfinput" onclick="WdatePicker({dateFmt:'yyyy:MM:dd HH:mm:ss',position:{left:380,top:-10}})"/></li>
    	</ul>
    	</form>
        </div>
        <div class="tipbtn">
        <input id="addNotice" type="button"  class="sure" value="确定"  onclick="updNotice()" />&nbsp;
        <input id="clearNotice" type="button"  class="cancel" value="取消" onclick="updclearNotice()" />
        </div>
    </div>
</body>
</html>