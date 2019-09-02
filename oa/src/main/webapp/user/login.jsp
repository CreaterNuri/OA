<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录OA管理系统</title>
<link href="css/style4.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="js/cloud.js"></script>
<script src="js/sweetalert.min.js"></script>
<script type="text/javascript" src="operation/login.js" ></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});  
</script> </head>
<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">
<div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录OA管理平台</span>    
    <ul>
    </ul>    
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
       
    <div class="loginbox">
    
    <ul>
    <li><input  id="username" type="text" class="loginuser" value="admin" onclick="JavaScript:this.value=''"/></li>
    <li><input  id="password" type="password" class="loginpwd" value="" onclick="JavaScript:this.value=''"/></li>
   	<li>
			<select class="loginuser" id="type" name="type">
    			 <option value="管理员">管理员</option>
    			 <option value="用户">用户</option>
			</select>
	</li>
    <li><input type="button" class="loginbtn" value="登录"  onclick="login()"/>
    	<!--<label>
     <input name="" type="checkbox" value="" checked="checked" />记住密码</label> -->
    <!-- <label><a href="#"></a></label> --></li>
    </ul>
    </div>
    </div>
    <div class="loginbm">仅供学习交流，勿用于任何商业用途</div>
</body>
</html>
