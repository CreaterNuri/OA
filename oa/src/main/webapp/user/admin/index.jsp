<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>OA管理系统-后台管理</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
<script src="js/sweetalert.min.js"></script>
<script src="operation/index.js"></script>
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
    <b id="username"></b>&nbsp;&nbsp;&nbsp;管理员您好，欢迎使用OA管理系统-后台管理
    </div>
    
     <div class="xline"></div>
    
    <div class="info"><b>欢迎使用OA管理系统-后台管理</b></div>
    
    
    <div class="xline"></div>
    <div class="welinfo">
    <span><img src="images/dp.png" alt="提醒" /></span>
    <b>常用功能</b>
    </div>
    <ul class="iconlist">
    
    <li><img src="images/ico01.png" /><p><a href="usermanager.jsp?id=${param.id}&username=${param.username}">用户管理</a></p></li>
    <li><img src="images/ico06.png" /><p><a href="addressmanager.jsp?id=${param.id}&username=${param.username}">通讯录管理</a></p></li>
            
    </ul>
    
    <div class="xline"></div>
    <div class="box"></div>
    
    <div class="welinfo">
    <span><img src="images/dp.png" alt="提醒" /></span>
    <b>OA管理系统使用指南</b>
    </div>
    
    <ul class="infolist">
    <li><span>您可以快速进行用户操作</span><a class="ibtn" href="usermanager.jsp?id=${param.id}&username=${param.username}">管理用户</a></li>
    <li><span>您可以进行通讯录管理</span><a class="ibtn" href="addressmanager.jsp?id=${param.id}&username=${param.username}">通讯录管理 </a></li>
    </ul>
    </div>
</body>
</html>