package com.sdut.oa.action;
/**
 * 用户action层
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sdut.oa.entity.User;
import com.sdut.oa.service.IUserService;
@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	
	private Logger logger = Logger.getLogger(UserAction.class);
	@Resource
	private IUserService userService;//注入service
	
	private HttpServletRequest request;
	
	/*
	 *当前登录人 时间 
	 */
	private String userName;
	private String date;
	private int id;
	
	private boolean success = false;
	private String errorMsg = "";
	
	private String password;
	
	/**
	 * 用户登陆
	 */
	public String login() {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		logger.debug("获取当前登陆人："+username+"密码："+password);
		Date day=new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		date = df.format(day);
		logger.debug("获取当前时间"+date);
	
		User user = new User(username,password);
		User loginUser = userService.login(user);
		logger.debug("判断输入用户名和密码"+loginUser);
		
		if(loginUser == null){
			success = false;
			return "login";
		}else {
			userName = loginUser.getUname();//当前登陆人
			id=loginUser.getUid();//当前登陆用户id
			logger.debug("当前登陆人："+userName);
			logger.debug("获取当前登陆用户id:"+id);
			success = true;
			return "login";
		}
	}
	
	/**
	 * 用户密码修改
	 * @return String
	 */
	public String updatePwd() {
		try {
			String sid = request.getParameter("id");
			int id = Integer.parseInt(sid);
			String userOldPwd = request.getParameter("userOldPwd");
			String userNewPwd = request.getParameter("userNewPwd");
			boolean flag = userService.updatePwd(id, userOldPwd, userNewPwd);
			success = flag;
			logger.debug("action层执行更新密码标识："+flag);
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			errorMsg = e.getMessage();
			logger.debug("action层修改密码异常："+e);
		}
		return "change";
	}
	
	/**
	 * 根据用户id查询
	 * @return String 
	 */
	public String queryPwd() {
		try {
			String sid = request.getParameter("id");
			int id = Integer.parseInt(sid);
			User queryUser = userService.findById(id);
			password = queryUser.getUpwd();
			logger.debug("获取密码："+password);
		} catch (Exception e) {
			logger.warn("Action根据id查询异常", e);
		}
		return "pwd";
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
