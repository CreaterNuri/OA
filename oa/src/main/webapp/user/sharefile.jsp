<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>OA管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/sharefile.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="easyui/themes/demo.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<script  src="js/jquery.js" type="text/javascript"></script>
<script src="js/sweetalert.min.js"></script>
<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="js/jquery-form.js"></script>
<script type="text/javascript" src="operation/sharrfile.js"></script>
<link rel="stylesheet" type="text/css" href="easyui/themes/gray/easyui.css">
<link href="easyui/themes/default/easyui.css"rel="stylesheet" type="text/css" />
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">文件共享列表</a></li>
    </ul>
    </div>
    <div class="rightinfo">
    <div class="tools">
    	<form id="uploadflag" action="upFileAction_up!upfile.action" method="post" enctype="multipart/form-data">
       	 请选择需要分享的文件：<input id="uploadImage" name="uploadImage" type="file"/>
         <input id="upfile" class="upfile" type="submit" value="上传" />
    	</form>
    	<!-- 下载文件 -->
		<form id="downloadfileflag" method="get" action="downloadFileAction_down!downloadfile.action">
         <input id="downloadfile" class="downloadfile" type="submit" value="下载" />
    	<!-- 隐藏下载文件id -->
    	<input id="fileName" name="fileName" type="text" style="display:none" readonly="readonly"> 
    	</form>
    </div>
    </div>
    
   <table id="proj" class="easyui-datagrid" title="文件共享列表"
			data-options="singleSelect:true,
			toolbar: '#org-toolbar',fit:false,
			fitColumns:true,
			url:'upFileAction_query!queryAll.action',
			showPageList:true,
			idField:'id',
			pagination:true,pageList: [5,10,15,20]">
    <thead>  
 
    	 <tr>  
    	 	
        	<th data-options="field:'id',hidden: true" id="id">ID</th>
            <th data-options="field:'name',width:150">文件名称</th>  
            <th data-options="field:'date',width:120">上传日期</th>  
            <th data-options="field:'type',width:80">类型</th>  
            <th data-options="field:'size',width:100">大小</th>
        </tr>  
   </thead>
    </table>
</body>
</html>