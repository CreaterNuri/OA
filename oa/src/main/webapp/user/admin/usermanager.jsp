<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>OA管理系统</title>
<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
<link href="css/style6.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="operation/usermanager.js"></script>
<script type="text/javascript" src="js/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="easyui/themes/gray/easyui.css">
<link href="easyui/themes/default/easyui.css"rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/demo.css">
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/lang/zh-cn.js"></script>
</head>
<body oncontextmenu="return false">
 <div class="place" >
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="index.jsp">首页</a></li>
    <li><a href="#">用户管理</a></li>
    </ul>
    </div>
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        <li class="addclick" onclick="addUserMsg()"><span><img src="images/t01.png" /></span>添加</li>
        <li class="updclick" onclick="updateUserMsg()"><span><img src="images/t02.png" /></span>修改</li>
        <li class="delclick" onclick="deleteMsg()"><span><img src="images/t03.png" /></span>删除</li>
        </ul>
        
    </div>
    
    
    <table id="proj" class="easyui-datagrid" title="用户信息管理"
			data-options="singleSelect:true,
			toolbar: '#org-toolbar',
			fit:false,
			fitColumns:true,
			rownumbers:true,
			url:'admin_query!query.action',
			showPageList:true,
			idField:'id',
			pagination:true,
			pageList: [5,10,15,20]">
    <thead>  
        <tr>  
        	<th data-options="field:'uid',hidden: true" id="id">ID</th>
            <th data-options="field:'uname',width:80">用户名</th>  
            <th data-options="field:'upwd',width:80">密码</th>  
            <th data-options="field:'state',width:80">状态</th>  
            <th data-options="field:'power',width:100">职位</th>
        </tr>  
    </thead>  
</table> 
</div>
 	<div class="tip" id="adduser">
    	<div class="tiptop"><span>添加用户</span><a></a></div>
      	<div class="tipinfo">
      	<form id="userform" method="post">
		<ul>
		<li><label id="alertMsg" style="color: red;"> 提示：带有*的字段为必填项，请注意填写！</label></li><br/>
    	<li><label>用户&nbsp;名&nbsp;</label>*&nbsp;&nbsp;&nbsp;&nbsp;<input name="" required="true" id="uname" type="text" class="dfinput" onkeyup="this.value=this.value.replace(/[^\u4e00-\u9fa5]/g,'')" placeholder="该项为必填项，只能输入长度不大于6个字符的中文" maxlength="6"/></li>
    	<li><label>用户密码&nbsp;</label>*&nbsp;<input name="" id="upwd" type="password" class="dfinput" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" placeholder="该项为必填项，只能填长度小于12位的英文字母和数字" maxlength="12"/></li>
		<li><label>状&nbsp;&nbsp;态</label>*
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select class="dfinput" id="state" name="state">
    				<option value="0">正常</option>
    				<option value="1">禁用</option>
			</select>
		</li>
		<li><label>职&nbsp;&nbsp;位</label>*
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select class="dfinput" id="power" name="power"></select>
		</li>
    	</ul>
    	</form>
        </div>
        <div class="tipbtn">
        <input id="updUserMsg" type="button"  class="sure" value="确定"  onclick="addNewUserfn()" />&nbsp;
        <input id="clearUserMsg" type="button"  class="cancel" value="取消" onclick="ClearaddUserMsg()" />
        </div>
    </div>
    <!-- 更新公告 -->
    <div class="tip" id="updateUser">
    	<div class="tiptop"><span>更新用户</span><a></a></div>
      	<div class="tipinfo">
      	<form id="UserUpdform" method="post">
		<ul>
		<li><label id="alertMsg" style="color: red;"> 提示：带有*的字段为必填项，请注意填写！</label></li><br/>
    	<li><label>用户&nbsp;名&nbsp;</label>*&nbsp;&nbsp;&nbsp;&nbsp;<input name="" id="updname" type="text" class="dfinput" onkeyup="this.value=this.value.replace(/[^\u4e00-\u9fa5]/g,'')" placeholder="该项为必填项，只能输入长度不大于6个字符的中文" maxlength="6" /></li>
    	<li><label>用户密码&nbsp;</label>*&nbsp;<input name="" id="updpwd" type="password" class="dfinput" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" placeholder="该项为必填项，只能填长度小于12位的英文字母和数字" maxlength="12"/></li>
		<li><label>状&nbsp;&nbsp;态</label>*
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select class="dfinput" id="updstate" name="state">
    				<option value="0">正常</option>
    				<option value="1">禁用</option>
			</select>
		</li>
		<li><label>职&nbsp;&nbsp;位</label>*
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select class="dfinput" id="updpower" name="power"></select>
		</li>
    	</ul>
    	</form>
        </div>
        <div class="tipbtn">
        <input id="updUserMsg" type="button"  class="sure" value="确定"  onclick="updUserMsg()" />&nbsp;
        <input id="clearUserMsg" type="button"  class="cancel" value="取消" onclick="updclearUserMsg()" />
        </div>
    </div>
</body>
</html>