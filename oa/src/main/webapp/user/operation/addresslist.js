var id;
$(function() {
	//点击关闭
	$(".tiptop a").click(function(){
		  $(".tip").fadeOut(200);
	});
});
//根据用户名查询
function queryUserInfo(){
	var username = $("#name").val();
	if(username == ''){
		swal("请输入正确的用户名进行查询！！",{icon:"info"});
	}else{
		//输入名
		$.ajax({
			type:'POST',
			url:'addresslist_findbyname!findByName.action',
			data:{
				name:$("#name").val()
				},
			success: function(data){
	            if (data.success == true){ 
	            	$("#bname").text(data.addresslist.name);
	            	$("#bphone").text(data.addresslist.phone);
	            	$("#baddress").text(data.addresslist.address);
	            	$("#bemail").text(data.addresslist.email);
	            	$("#operation").append("<a href='#' onclick='Viewdetails("+data.addresslist.id+")'>查看详情</a>");
	            } else {  
	            	swal("请输入正确的用户名进行查询！！",{icon:"info"});
	            }  
	        }  
		});
	}
};
//查看详情
function Viewdetails(id){
	//清空表格
	  $("#bname").text('');
	  $("#bphone").text('');
	  $("#baddress").text('');
	  $("#bemail").text('');
	  $("#operation").text('');
	$.ajax({
		type:'POST',
		url:'addresslist_findbyid!findById.action',
		data:{
			id:id
			},
		success: function(data){
            if (data.success == true){ 
            	$(".tip").fadeIn(100);
            	$("#dname").val(data.addresslist.name);
            	$("#dphone").val(data.addresslist.phone);
            	$("#daddress").val(data.addresslist.address);
            	$("#demail").val(data.addresslist.email);
            	$("#dpower").val(data.addresslist.power);
            } else {  
            	swal("系统异常，请稍后！！！",{icon:"error"});
            }  
        }  
	});
};