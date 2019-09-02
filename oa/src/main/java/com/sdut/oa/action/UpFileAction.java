package com.sdut.oa.action;
/**
 * 文件上传和下载action
 */
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sdut.oa.entity.Sharedfile;
import com.sdut.oa.service.ISharedfileService;

@Controller("upFileAction")
@Scope("prototype")
public class UpFileAction extends ActionSupport implements ServletRequestAware{
	
	private static final long serialVersionUID = 1L;
	
	private Logger logger =Logger.getLogger(UpFileAction.class);
	@Resource
	private ISharedfileService sharedfileService;
	//成功标识
	private boolean success = false;
	private List<Sharedfile> list;
	private int  total;
	
	private HttpServletRequest request;
	
	private File uploadImage; //得到上传的文件
    private String uploadImageContentType; //得到文件的类型
    private String uploadImageFileName; //得到文件的名称
    
    /**
     * 查询文件列表
     */
    public String queryAll() {
    	//一页显示的条数
    	String Srows = request.getParameter("rows");
		int rows = Integer.parseInt(Srows);
		//当前页为第几页
		String Spage = request.getParameter("page");
		int page = Integer.parseInt(Spage);
		//开始查询的条数
		int startRow = (page-1)*rows;
		logger.debug("开始条数："+startRow);
		//页面显示的条数
		int pageSize=rows;
		logger.debug("页面显示条数："+pageSize);
		
		list = sharedfileService.findAllFiles(startRow, pageSize);
		logger.debug("查询到的数据列表"+list);
		
		total = sharedfileService.getTotal();
		logger.debug("查询到的数据的总条数"+total);
		return "list";		
	}
    
    /**
     *上传文件 
     * @return
     * @throws Exception
     */
    public String upfile() throws Exception{  
	    	logger.debug("文件上传开始");
	    	//判断是否选择上传文件
    		logger.debug("fileName:"+this.getUploadImageFileName());
        	logger.debug("contentType:"+this.getUploadImageContentType());
        	logger.debug("File:"+this.getUploadImage());
        	
        	//获取当前时间
        	Date day=new Date();    
    		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
    		String date = df.format(day);
    		logger.debug("获取当前时间"+date);
            
    		//获取要保存文件夹的物理路径(绝对路径)
            String realPath=ServletActionContext.getServletContext().getRealPath("/upload");
            File file = new File(realPath);
            //测试此抽象路径名表示的文件或目录是否存在。若不存在，创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
            if(!file.exists())file.mkdirs();
            try{
            		 //保存文件 uploadImage：上传文件  uploadImageFileName：得到文件的名称
                    FileUtils.copyFile(uploadImage, new File(file,uploadImageFileName));
                    //保存数据到数据库
                    logger.debug("保存到数据库开始");
                    Sharedfile sharedfile = new Sharedfile();
                    //存放日期
                    sharedfile.setDate(date);
                    //存放文件名
                    sharedfile.setName(this.getUploadImageFileName());
                    //路径
                    sharedfile.setPath(realPath);
                    //大小
                    sharedfile.setSize(uploadImage.length()+"B");
                    //类型
                    sharedfile.setType(this.getUploadImageContentType());
                    
                    long size=10485760;
                    long length = uploadImage.length();
                    //判断上传文件大小 <10M 
                    if(length<size){
                    	Boolean flag = sharedfileService.addFile(sharedfile);
                    	logger.debug("Action层向添加数据库标识："+flag);
                    	success=flag;
                    }else{
                    	success = false;
                    }
            } catch (Exception e) {
                e.printStackTrace();
                logger.warn("上传文件异常："+e);
            }
        return "file";  
    }
    
    
	public File getUploadImage() {
		return uploadImage;
	}

	public void setUploadImage(File uploadImage) {
		this.uploadImage = uploadImage;
	}

	public String getUploadImageContentType() {
		return uploadImageContentType;
	}

	public void setUploadImageContentType(String uploadImageContentType) {
		this.uploadImageContentType = uploadImageContentType;
	}

	public String getUploadImageFileName() {
		return uploadImageFileName;
	}

	public void setUploadImageFileName(String uploadImageFileName) {
		this.uploadImageFileName = uploadImageFileName;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	@JSON(name = "rows")
	public List<Sharedfile> getList() {
		return list;
	}

	public void setList(List<Sharedfile> list) {
		this.list = list;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

}
