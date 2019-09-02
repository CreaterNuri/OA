<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>OA管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})	
</script>
</head>
<body style="background:#f0f9fd;">
	<div class="lefttop"><span></span>功能</div>
    
    <dl class="leftmenu">
        
    <dd>
    <div class="title">
    <span><img src="images/leftico01.png" /></span>管理功能
    </div>
    	<ul class="menuson">
        <li><cite></cite><a href="right.jsp?id=${param.id}&username=${param.username}" target="rightFrame">公告管理</a><i></i></li>
        <li><cite></cite><a href="account.jsp?id=${param.id}&username=${param.username}" target="rightFrame">费用报销申请</a><i></i></li>
        <li><cite></cite><a href="attendence.jsp?id=${param.id}&username=${param.username}" target="rightFrame">考勤管理</a><i></i></li>
        <li><cite></cite><a href="leave.jsp?id=${param.id}&username=${param.username}" target="rightFrame">请假管理</a><i></i></li>
        <li><cite></cite><a href="overtime.jsp?id=${param.id}&username=${param.username}" target="rightFrame">加班管理</a><i></i></li>
        
        </ul>    
    </dd>
      <dd>
    <div class="title">
    <span><img src="images/leftico02.png" /></span>常用功能
    </div>
    <ul class="menuson">
       <li><cite></cite><a href="addresslist.jsp?id=${param.id}&username=${param.username}" target="rightFrame">通讯录查看</a><i></i></li>
        <li><cite></cite><a href="sharefile.jsp?id=${param.id}&username=${param.username}" target="rightFrame">知识库分享</a><i></i></li>
        </ul>     
    </dd> 
    </dl>
</body>
</html>