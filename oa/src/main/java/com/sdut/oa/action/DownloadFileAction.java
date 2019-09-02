package com.sdut.oa.action;
/**
 * 文件下载 Action
 */
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
@Controller("downloadFileAction")
@Scope("prototype")
public class DownloadFileAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private Logger logger = Logger.getLogger(DownloadFileAction.class);
	
	private String fileName;//文件名
	
	/**
	 * 下载文件
	 * @return String
	 */
	public String downloadfile() {
		logger.debug("获取文件名："+fileName);
		return "success";
	}
	
	public InputStream getAttrInputStream() throws Exception{
		logger.debug("获取下载文件的名称："+fileName);
		return ServletActionContext.getServletContext().getResourceAsStream("/upload/"+fileName);
	}
	
	
	public String getDownFileName(){
		try{
            fileName = URLEncoder.encode(fileName,"UTF-8");
        }catch(Exception e){
            throw new RuntimeException();
        }
        return fileName;
    }	         
	
	public void setFileName(String fileName) {
		//处理传入的参数种问题（get提交）
        try {
            fileName=new String(fileName.getBytes(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException();
        }
        //把处理好的文件名，赋值
        this.fileName = fileName;
    }	         
	
}
