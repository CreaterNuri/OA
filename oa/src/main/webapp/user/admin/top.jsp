<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>OA管理系统-后台管理</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="operation/top.js"></script>
<script language="JavaScript" src="js/jquery.js"></script>
<script src="js/sweetalert.min.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>
</head>
<body style="background:url(images/topbg.gif) repeat-x;">

    <div class="topleft">
    <a href="#"><img src="images/loginlogo.png" title="系统首页" /></a>
    </div>
        
    <ul class="nav">
    <li><a href="index.jsp?id=${param.id }&username=${param.username}" target="rightFrame" class="selected"><img src="images/icon01.png" title="工作台" /><h2>工作台</h2></a></li>
    <li><a href="usermanager.jsp?id=${param.id }&username=${param.username}" target="rightFrame"><img src="images/iconuser.png" title="用户管理" /><h2>用户管理</h2></a></li>
    <li><a href="addressmanager.jsp?id=${param.id }&username=${param.username}"  target="rightFrame"><img src="images/icon01.png" title="员工通讯录管理" /><h2>员工通讯录管理</h2></a></li>
    </ul>
            
    <div class="topright">    
    <ul>
    <li><span><img src="images/help.png" title="帮助"  class="helpimg"/></span><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    <li><a href="../login.jsp" target="_parent" onclick="javascript:return logout()">退出</a></li>
    </ul>
	<div class="user">
    <span id="username"></span>
    </div>        
    </div>
</body>

</html>