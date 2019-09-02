package com.sdut.oa.action;

/**
 * 管理员 控制
 */
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sdut.oa.entity.Admin;
import com.sdut.oa.entity.Power;
import com.sdut.oa.entity.User;
import com.sdut.oa.service.IAdminService;
import com.sdut.oa.service.IPowerService;
import com.sdut.oa.service.IUserService;
@Controller("adminAction")
@Scope("prototype")
public class AdminAction extends ActionSupport implements ServletRequestAware {
	
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(AdminAction.class);
	@Resource
	private IAdminService adminService;//注入service
	@Resource
	private IUserService userService;//注入用户
	@Resource
	private IPowerService powerService;//用户职位查询
	
	private HttpServletRequest request;
	
	private String userName;
	private int id;
	
	private boolean success = false;
	private String errorMsg = "";
	
	private List<User> list;//用户列表
	private int total;
	private List<Power> powerlist;//用户角色管理
	
	
	/**
	 * 管理员登陆
	 * @return
	 */
	public String login() {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		logger.debug("获取当前登陆人："+username+"密码："+password);
		
		Admin admin = new Admin(username,password);
		Admin loginUser = adminService.login(admin);
		logger.debug("判断输入用户名和密码"+loginUser);
		
		if(loginUser == null){
			success = false;
			return "login";
		}else {
			userName = loginUser.getName();//当前登陆人
			id=loginUser.getId();//当前登陆用户id
			logger.debug("当前登陆人："+userName);
			logger.debug("获取当前登陆用户id:"+id);
			success = true;
			return "login";
		}
	}
	
	/**
	 * 所有用户查询
	 */
	public String query() {
		try {
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
			list = userService.queryAll(startRow, pageSize);
			total = userService.getTotal();
			logger.debug("查询所有用户信息："+total);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			errorMsg = e.getMessage();
			logger.warn("Action层查询所有用户异常："+e);
		}
		return "list";
	}
	
	
	/**
	 * 查询用户职位
	 */
	public String queryPower() {
			logger.debug("Action层查询所有用户职位阶段开始");
			powerlist = powerService.queryAll();
			return "power";
	}
	
	/**
	 * 添加用户
	 */
	public String addUser(){
		try {
			String username = request.getParameter("uname");
			String pwd = request.getParameter("upwd");
			String sstate = request.getParameter("state");
			int state = Integer.parseInt(sstate);
			String power = request.getParameter("power");
			logger.debug("用户名："+username+"职位："+power);
			User user = new User(username, pwd, state, power);
			boolean addUserflag = userService.addUser(user);
			success = addUserflag;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			errorMsg = e.getMessage();
			logger.warn("Action层添加用户异常："+e);
		}
		return "adduser";
	}
	
	/**
	 * 更新用户
	 */
	public String updUser(){
		try {
			String sid = request.getParameter("id");
			int id = Integer.parseInt(sid);
			String uname = request.getParameter("updname");
			String upwd = request.getParameter("updpwd");
			String sstate = request.getParameter("updstate");
			int state = Integer.parseInt(sstate);
			String power = request.getParameter("updpower");
			logger.debug("Action层接收参数，id："+id+"用户名："+uname+"密码："+upwd+"状态："+state+"职位："+power);
			User user = new User(id, uname, upwd, state, power);
			boolean updateflag = userService.update(user);
			success = updateflag;
			logger.debug("Action层更新用户标识："+updateflag);
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			errorMsg = e.getMessage();
			logger.warn("Action层更新用户异常："+e);
		}
		return "upduser";
	}
	
	/**
	 * 删除用户
	 */
	public String delUser(){
		try {
			String sid = request.getParameter("id");
			int id = Integer.parseInt(sid);
			logger.debug("删除用户id:"+id);
			User user = new User(id);
			boolean deleteUserflag = userService.deleteUser(user);
			logger.debug("删除用户标识："+deleteUserflag);
			success=deleteUserflag;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			errorMsg = e.getMessage();
			logger.warn("Action层删除用户异常："+e);
		}
		return "deluser";
	}
	
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	@JSON(name = "rows")
	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}

	public List<Power> getPowerlist() {
		return powerlist;
	}

	public void setPowerlist(List<Power> powerlist) {
		this.powerlist = powerlist;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
