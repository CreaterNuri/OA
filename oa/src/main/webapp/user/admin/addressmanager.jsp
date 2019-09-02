<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>OA管理系统</title>
<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
<link href="css/style5.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="operation/addressmanager.js"></script>
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
    <li><a href="#">用户联系方式管理</a></li>
    </ul>
    </div>
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        <li class="addclick" onclick="addUserAddress()"><span><img src="images/t01.png" /></span>添加</li>
        <li class="updclick" onclick="updateUserAddress()"><span><img src="images/t02.png" /></span>修改</li>
        <li class="delclick" onclick="deleteAddress()"><span><img src="images/t03.png" /></span>删除</li>
        </ul>
        
    </div>
    
    
    <table id="proj" class="easyui-datagrid" title="用户联系方式管理"
			data-options="singleSelect:true,
			toolbar: '#org-toolbar',
			fit:false,
			fitColumns:true,
			rownumbers:true,
			url:'admin_queryAddress!queryAddress.action',
			showPageList:true,
			idField:'id',
			pagination:true,
			pageList: [5,10,15,20]">
    <thead>  
        <tr>  
        	<th data-options="field:'uid',hidden: true" id="id">ID</th>
            <th data-options="field:'name',width:80">用户名</th>  
            <th data-options="field:'phone',width:100">联系电话</th>  
            <th data-options="field:'address',width:120">通讯地址</th>  
            <th data-options="field:'email',width:100">邮箱</th>
            <th data-options="field:'power',width:80">职位</th>
        </tr>  
    </thead>  
</table> 
</div>
<!-- 添加用户联系方式 -->
 	<div class="tip" id="adduserAddress">
    	<div class="tiptop"><span>添加用户联系方式</span><a></a></div>
      	<div class="tipinfo">
      	<form id="Addressform" method="post">
		<ul>
		<li><label id="alertMsg" style="color: red;"> 提示：带有*的字段为必填项，请注意填写！</label></li><br/>
    	<li><label>用户&nbsp;名&nbsp;</label>*&nbsp;&nbsp;&nbsp;&nbsp;<input name="" id="addname" type="text" class="dfinput" onkeyup="this.value=this.value.replace(/[^\u4e00-\u9fa5]/g,'')" placeholder="该项为必填项，只能输入长度不大于6个字符的中文" maxlength="6"/></li>
    	<li><label>电话号码&nbsp;</label>*&nbsp;<input name="" id="addphone" type="text" class="dfinput" onkeyup="this.value=this.value.replace(/^[1][3,4,5,7,8][0-9]{9}$/,'')" placeholder="该项为必填项，只能输入长度为11位字符的电话号码" maxlength="11"/></li>
		<li><label>通讯地址&nbsp;</label>*&nbsp;<input name="" id="addaddress" type="text" class="dfinput" onkeyup="value=value.replace(/[^\u4e00-\u9fa5]/g,'')" placeholder="该项为必填项，只能输入长度小于20个字符的中文" maxlength="20"/></li>
		<li><label>电子邮箱&nbsp;</label>*&nbsp;<input name="" id="addemail" type="text" class="dfinput" placeholder="该项为必填项，请输入正确的邮箱地址！"/></li>
		<li><label>职&nbsp;&nbsp;位</label>*
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select class="dfinput" id="power" name="power"></select>
		</li>
    	</ul>
    	</form>
        </div>
        <div class="tipbtn">
        <input id="addnewUserAddresss" type="button"  class="sure" value="确定"  onclick="addNewUserAddress()" />&nbsp;
        <input id="clearUserAddress" type="button"  class="cancel" value="取消" onclick="addclearUserAddress()" />
        </div>
    </div>
    <!-- 更新用户联系方式 -->
    <div class="tip" id="updateUserAddress">
    	<div class="tiptop"><span>更新用户联系方式</span><a></a></div>
      	<div class="tipinfo">
      	<form id="UserUpdAddressform" method="post">
		<ul>
		<li><label id="alertMsg" style="color: red;"> 提示：带有*的字段为必填项，请注意填写！</label></li><br/>
    	<li><label>用户&nbsp;名&nbsp;</label>*&nbsp;&nbsp;&nbsp;&nbsp;<input name="" id="updname" type="text" class="dfinput" onkeyup="this.value=this.value.replace(/[^\u4e00-\u9fa5]/g,'')" placeholder="该项为必填项，只能输入长度不大于6个字符的中文" maxlength="6"/></li>
    	<li><label>电话号码&nbsp;</label>*&nbsp;<input name="" id="updphone" type="text" class="dfinput" onkeyup="this.value=this.value.replace(/^[1][3,4,5,7,8][0-9]{9}$/,'')" placeholder="该项为必填项，只能输入长度为11位字符的电话号码" maxlength="11"/></li>
		<li><label>通讯地址&nbsp;</label>*&nbsp;<input name="" id="updaddress" type="text" class="dfinput" onkeyup="value=value.replace(/[^\u4e00-\u9fa5]/g,'')" placeholder="该项为必填项，只能输入长度小于20个字符的中文" maxlength="20"/></li>
		<li><label>电子邮箱&nbsp;</label>*&nbsp;<input name="" id="updemail" type="text" class="dfinput"/></li>
		<li><label>职&nbsp;&nbsp;位</label>*
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select class="dfinput" id="updpower" name="power"></select>
		</li>
    	</ul>
    	</form>
        </div>
        <div class="tipbtn">
        <input id="updUserAddress" type="button"  class="sure" value="确定"  onclick="updUserAddress()" />&nbsp;
        <input id="clearUserAddress" type="button"  class="cancel" value="取消" onclick="updclearUserAddress()" />
        </div>
    </div>
</body>
</html>