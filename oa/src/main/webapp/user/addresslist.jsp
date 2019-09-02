<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>OA管理系统</title>
<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="operation/addresslist.js"></script>
<script src="js/sweetalert.min.js"></script>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
</head>
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">通讯录</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    <div class="tools">
        <input id="name" type="text" class="dfinput" placeholder="请输入查询的用户的姓名" />
        <ul class="toolbar1">
        <li class="queryUser" onclick="queryUserInfo()"><span><img src="images/t04.png" /></span>查询</li>
        </ul>
    
    </div>
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th></th>
        <th >姓名<i class="sort"><img src="images/px.gif" /></i></th>
        <th >电话</th>
        <th >通讯地址</th>
        <th >邮件</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
        
        <tr>
        <td></td>
        <td id="bname"></td>
        <td id="bphone"></td>
        <td id="baddress"></td>
        <td id="bemail"></td>
        <td id="operation"></td>
        </tr> 
        
        </tbody>
    </table>
    
   
    
     <div class="tip">
    	<div class="tiptop"><span>联系人详情</span><a></a></div>
      	<div class="tipinfo">
		<ul>
    	<li><label>姓名</label>&nbsp;<input id="dname" type="text" class="dfinput" readonly="readonly" /></li>
    	<li><label>电话</label>&nbsp;<input id="dphone" type="text" class="dfinput" readonly="readonly" /></li>
   	 	<li><label>住址</label>&nbsp;<input id="daddress" type="text" class="dfinput" readonly="readonly" /></li>
    	<li><label>邮箱</label>&nbsp;<input id="demail" type="text" class="dfinput" readonly="readonly" /></li>
    	<li><label>职位</label>&nbsp;<input id="dpower" type="text" class="dfinput" readonly="readonly" /></li>
    	</ul>
        </div>
    </div>
    </div>
</body>
</html>