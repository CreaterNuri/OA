<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>OA管理系统</title>
<link href="css/style6.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="operation/main.js"></script>
<script type="text/javascript" src="operation/index.js"></script>
<script src="js/sweetalert.min.js"></script>
</head>
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    </ul>
    </div>
    
    
    <div class="mainindex">
    
   
    <div class="welinfo">
    <span><img src="images/sun.png" alt="天气" /></span>
    <b id="username"></b>&nbsp;&nbsp;&nbsp;您好，欢迎使用OA管理系统,您有待处理信息<b id="msgNum" style="color:#F00">5</b>条，请及时处理
    <a  onclick="newPassword()">帐号密码修改</a>
    <a href="usermessage.jsp?id=${param.id }&username=${param.username}">信息处理</a>
    </div>
    
     <div class="xline"></div>
    
    <div class="info"><b>欢迎使用OA管理系统</b></div>
    
    
    <div class="xline"></div>
    <div class="welinfo">
    <span><img src="images/dp.png" alt="提醒" /></span>
    <b>常用功能</b>
    </div>
    <ul class="iconlist">
    
    <li><img src="images/ico01.png" /><p><a href="leave.jsp?id=${param.id }&username=${param.username}">加班管理</a></p></li>
    <li><img src="images/ico02.png" /><p><a href="leave.jsp?id=${param.id }&username=${param.username}">请假管理</a></p></li>
    <li><img src="images/ico03.png" /><p><a href="attendence.jsp?id=${param.id }&username=${param.username}">考勤管理</a></p></li>
    <li><img src="images/ico04.png" /><p><a href="sharefile.jsp?id=${param.id }&username=${param.username}">文件上传</a></p></li>
    <li><img src="images/ico06.png" /><p><a href="addresslist.jsp?id=${param.id }&username=${param.username}">通讯录查询</a></p></li> 
            
    </ul>
    
    <div class="xline"></div>
    <div class="box"></div>
    
    <div class="welinfo">
    <span><img src="images/dp.png" alt="提醒" /></span>
    <b>OA管理系统使用指南</b>
    </div>
    
    <ul class="infolist">
    <li><span>您可以快速进行文章发布公告操作</span><a class="ibtn" href="right.jsp?id=${param.id }&username=${param.username}">发布或管理公告</a></li>
    <li><span>您可以记性通讯录查看</span><a class="ibtn" href="addresslist.jsp?id=${param.id }&username=${param.username}">查询用户通讯</a></li>
    <li><span>您可以进行密码修改、账户设置等操作</span><a class="ibtn"  onclick="newPassword()">账户密码管理</a></li>
    </ul>
    </div>
   <!-- 密码修改 -->
    <div class="tip" id="changePwd">
    	<div class="tiptop"><span>修改用户密码</span><a></a></div>
      	<div class="tipinfo">
      	<form id="noticeform" method="post">
		<ul>
		<li><label id="alertMsg" style="color: red;"> 提示：带有*的字段为必填项，请注意填写！</label></li><br/>
    	<li><label>原&nbsp;密码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>&nbsp;*<input name="" id="userOldPwd" type="password" class="dfinput" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" placeholder="该项为必填项，只能填长度小于12位的英文字母和数字" maxlength="12"/></li>
   	 	<li><label>新&nbsp;密码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>*<input name="" id="userNewPwd" type="password" class="dfinput" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" placeholder="该项为必填项，只能填长度小于12位的英文字母和数字" maxlength="12"/></li>
    	<li><label>确认新密码</label>&nbsp;&nbsp;*<input name="" id="sureNewPwd" type="password" class="dfinput" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" placeholder="该项为必填项，只能填长度小于12位的英文字母和数字" maxlength="12"/></li>
    	</ul>
    	</form>
        </div>
        <div class="tipbtn">
        <input id="addpwd" type="button"  class="sure" value="确定"  onclick="ChangePwd()" />&nbsp;
        <input id="clearpwd" type="button"  class="cancel" value="取消" onclick="ChangePwdclear()" />
        </div>
    </div>
</body>
</html>