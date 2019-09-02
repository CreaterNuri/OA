$(function() {
	//路径取值 id
	uid = getQueryStringV(location.href, "id");
	//路径取值username
	username = getQueryStringV(decodeURI(location.href), "username");
	//点击关闭
	$(".tiptop a").click(function(){
		  $(".tip").fadeOut(200);
	});
	//显示用户名
	showname();
});
/**
 * 显示登陆名
 */
function showname(){
	$("#username").text(username);
};

/**
 * 获取url中的参数信息
 */
function getQueryStringV(vhref, name) {
    // 如果链接没有参数，或者链接中不存在我们要获取的参数，直接返回空 
    if (vhref.indexOf("?") == -1 || vhref.indexOf(name + '=') == -1) {
        return '';
    }
    // 获取链接中参数部分 
    var queryString = vhref.substring(vhref.indexOf("?") + 1);
    // 分离参数对 ?key=value&key2=value2 
    var parameters = queryString.split("&");
    var pos, paraName, paraValue;
    for (var i = 0; i < parameters.length; i++) {
        // 获取等号位置 
        pos = parameters[i].indexOf('=');
        if (pos == -1) {
            continue;
        }
        // 获取name 和 value 
        paraName = parameters[i].substring(0, pos);
        paraValue = parameters[i].substring(pos + 1);
        if (paraName == name) {
        	//return unescape(paraValue.replace(/\+/g, " "));
        	return paraValue.replace(/\+/g, " ");
        }
    }
    return '';
};
/**
 *退出按钮
 */ 
function logout(){
	var msg = "确认退出登陆？"; 
	 if (confirm(msg)==true){ 
	  return true; 
	 }else{ 
	  return false; 
	 } 
};

