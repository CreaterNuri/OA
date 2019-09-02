$(function() {
	alertMsg()
	showname();
});
//取出连接路径值
function alertMsg() {
	var Ohref = window.location.href;
	var arrhref = Ohref.split("?username=");
	username = arrhref[1];
};
//显示登录名
function showname(){
	$("#username").text(username);
};