//下载文件id
var id;
$(function(){  
	//判断返回值是否提交成功
    $("#uploadflag").ajaxForm(function(data){ 
        if(data.success == true){
            swal("上传成功！！！",{icon:"success"});
            //重新加载列表
            $('#proj').datagrid('reload');
            //清空文件选择框
            $("#uploadImage").val('');
        }  
    });  
    
    //提交时判断是否有文件
    $("#uploadflag").bind("submit", function(){  
        var file=$("#uploadImage").val();  
        var filesize = document.getElementById('uploadImage').files[0];
        if(file == "" ){    
            swal("请选择文件！！！",{icon:"info"});
            return false;    
        }else if(filesize.size > 10485760){
        	 swal("您选择文件较大！请上传不超过10M的文件！！",{icon:"error"});
        	 return false;   
        }
    }); 
    
    //下载文件
    $("#downloadfileflag").bind("submit", function(){  
    	//选择行
    	var objs = $('#proj').datagrid("getSelected"); 
        if(objs){    
        	//赋值下载文件名
        	$("#fileName").val(objs.name);
        	var name =$("#fileName").val();
            return true;
        }else{
        	 swal("请选择需要下载文件后进行操作！！！",{icon:"info"});
            return false;    
        }    
    });  
});  

