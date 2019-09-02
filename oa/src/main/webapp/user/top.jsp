<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>OA管理系统</title>
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
    <li><a href="right.jsp?username=${param.username}" target="rightFrame"><img src="images/icon08.png" title="公告管理" /><h2>公告管理</h2></a></li>
    <li><a href="account.jsp?id=${param.id }&username=${param.username}"  target="rightFrame"><img src="images/icon03.png" title="费用报销报销" /><h2>费用报销申请</h2></a></li>
    <li><a href="attendence.jsp?id=${param.id }"  target="rightFrame"><img src="images/icon04.png" title="考勤管理" /><h2>考勤管理</h2></a></li>
    <li><a href="leave.jsp?id=${param.id }&username=${param.username}" target="rightFrame"><img src="images/icon05.png" title="请假管理" /><h2>请假管理</h2></a></li>
    <li><a href="overtime.jsp?id=${param.id }&username=${param.username}"  target="rightFrame"><img src="images/icon06.png" title="加班管理" /><h2>加班管理</h2></a></li>
    <li><a href="addresslist.jsp"  target="rightFrame"><img src="images/icon02.png" title="员工通讯录" /><h2>员工通讯录</h2></a></li>
    <li><a href="sharefile.jsp"  target="rightFrame"><img src="images/icon09.png" title="知识库分享" /><h2>知识库分享</h2></a></li>
    </ul>
            
    <div class="topright">    
    <ul>
    <li><span><img src="images/help.png" title="帮助"  class="helpimg"/></span><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    <li><a href="login.jsp" target="_parent" onclick="javascript:return logout()">退出</a></li>
    </ul>
	<div class="user">
    <span id="username">admin</span>
    </div>        
    </div>
</body>

</html>