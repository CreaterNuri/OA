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
<script src="js/sweetalert.min.js"></script>
<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="operation/account.js"></script>
<link rel="stylesheet" type="text/css" href="easyui/themes/gray/easyui.css">
<link href="easyui/themes/default/easyui.css"rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/demo.css">
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/lang/zh-cn.js"></script>
</head>
<body>
 <div class="place" >
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">费用报销管理</a></li>
    </ul>
    </div>
    <div class="rightinfo">
    
    <div class="tools">
    	<ul class="toolbar">
        <li class="addclick"><span><img src="images/t01.png" /></span>费用登记</li>
        <li class="updclick" onclick="managerAccount()"><span><img src="images/t02.png" /></span>报销单管理</li>
        </ul>
    </div>
    <div style="width:100%;height:81%;">
    <table id="account" class="easyui-datagrid">
    <thead>  
        <tr>  
        	<th data-options="field:'id',hidden: true" id="id">ID</th>
            <th data-options="field:'date',width:80">报销日期</th>  
            <th data-options="field:'accounttype',width:100">报销类别</th>  
            <th data-options="field:'money',width:80">报销金额</th>  
            <th data-options="field:'state',width:100">报销状态</th>
            <th data-options="field:'approver',width:100">审批人</th>
        </tr>  
    </thead>  
</table> 
</div>
</div>
	<!-- 添加报销 -->
 	<div class="tip" id="addAccount">
    	<div class="tiptop"><span>添加报销</span><a></a></div>
      	<div class="tipinfo">
      	<form id="accountform" method="post">
		<ul>
		<li><label id="alertMsg" style="color: red;"> 提示：带有*的字段为必填项，请注意填写！</label></li><br/>
		<li><label>报销类别</label>*
			<select class="dfinput" id="accounttype" name="accounttype"></select>
		</li>
    	<li><label>报销金额</label>*&nbsp;<input name="" id="money" type="text" class="dfinput" onkeyup="this.value=this.value.replace(/[^\d.]/g,'')" onafterpaste="this.value=this.value.replace(/[^\d.]/g,'')"  maxlength="8"/></li>
   	 	<li><label>审&nbsp;批人&nbsp;&nbsp;&nbsp;&nbsp;</label>*<input name="" id="approver" type="text" class="dfinput" onkeyup="this.value=this.value.replace(/[^\u4e00-\u9fa5]/g,'')" placeholder="该项为必填项，只能输入长度不大于6个字符的中文" maxlength="6"/></li>
    	<li><label>报销日期</label>&nbsp;*<input name="" id="date" type="text" class="dfinput" onclick="WdatePicker({dateFmt:'yyyy:MM:dd HH:mm:ss',position:{left:380,top:-10}})"/></li>
    	</ul>
    	</form>
        </div>
        <div class="tipbtn">
        <input id="addAccount" type="button"  class="sure" value="确定"  onclick="SureAddAccount()" />&nbsp;
        <input id="clearAccount" type="button"  class="cancel" value="取消" onclick="addclearAccount()" />
        </div>
    </div>
    <!-- 报销单管理 -->
    <div class="tip" id="managerAccount">
    	<div class="tiptop"><span>报销单管理</span><a></a></div>
      	<div class="tipinfo">
      	<form id="managerform" method="post">
		<ul>
		<li><label id="alertMsg" style="color: red;"> 提示：带有*的字段为必填项，请注意填写！</label></li><br/>
    	<li><label>报销类别</label>*
			<select class="dfinput" id="updaccounttype" name="updaccounttype"></select>
		</li>
    	<li><label>报销金额</label>*&nbsp;<input name="" id="updmoney" type="text" class="dfinput" onchange="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}"  placeholder="该项为必填项，请输入正确的报销金额！" maxlength="8"/></li>
   	 	<li><label>审&nbsp;批人&nbsp;&nbsp;&nbsp;&nbsp;</label>*<input name="" id="updapprover" type="text" class="dfinput" onkeyup="this.value=this.value.replace(/[^\u4e00-\u9fa5]/g,'')" placeholder="该项为必填项，只能输入长度不大于6个字符的中文" maxlength="6"/></li>
    	<li><label>报销日期</label>*&nbsp;<input name="" id="upddate" type="text" class="dfinput" onclick="WdatePicker({dateFmt:'yyyy:MM:dd HH:mm:ss',position:{left:380,top:-10}})"/></li>
    	</ul>
    	</form>
        </div>
        <div class="tipbtn">
        <input id="updAccount" type="button"  class="sure" value="确定"  onclick="updAccount()" />&nbsp;
        <input id="clearAccount" type="button"  class="cancel" value="取消" onclick="updclearAccount()" />
        </div>
    </div>
</body>
</html>
