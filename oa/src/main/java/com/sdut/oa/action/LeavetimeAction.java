package com.sdut.oa.action;
/**
 * 请假管理 action
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sdut.oa.entity.Leavetime;
import com.sdut.oa.entity.User;
import com.sdut.oa.service.ILeavetimeService;
import com.sdut.oa.service.IUserService;
@Controller("leavetimeAction")
@Scope("prototype")
public class LeavetimeAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(LeavetimeAction.class);
	
	private  HttpServletRequest request;
	@Resource
	private ILeavetimeService leavetimeService;//注入service
	@Resource
	private IUserService userService;
	
	private List<Leavetime> Leavetimelist;
	private int total;
	
	private boolean success = false;
	private String errorMsg = "";
	
	/**
	 * 查询所有请假单
	 * @return
	 */
	public String queryAll() {
		//得到当前登陆用户id
		String suid = request.getParameter("uid");
		int uid = Integer.parseInt(suid);
		logger.debug("获取登陆用户id："+uid);
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
		
		Leavetimelist = leavetimeService.queryAll(startRow, pageSize, uid);
		logger.debug("查询到的日期："+Leavetimelist.get(0).getStarttime());
		logger.debug("查询到的数据列表"+Leavetimelist.size());
		
		total = Leavetimelist.size();
		logger.debug("查询到的数据的总条数"+total);
		return "list";
	}
	
	/**
	 * 添加请假单
	 * @return String
	 */
	public String addleavetime() {
		logger.debug("Action层请假单添加开始");
		try {
			String suid = request.getParameter("uid");
			//用户id
			int uid = Integer.parseInt(suid);
			String username = request.getParameter("username");
			String leavettype = request.getParameter("leavettype");
			String leavemsg = request.getParameter("leavemsg");
			logger.debug("获取用户名："+username+"请假类型："+leavettype+"请假原因："+leavemsg);
			
			String starttime = request.getParameter("starttime");
			String endtime = request.getParameter("endtime");
			SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startDate = simpledateformat.parse(starttime);
			Date endDate = simpledateformat.parse(endtime);
			logger.debug("得到开始日期："+startDate+"结束日期："+endDate);
			logger.debug("开始时间："+startDate+"结束时间："+endDate);
			String sleavedays = request.getParameter("leavedays");
			double leavedys = Double.parseDouble(sleavedays);
			
			String approver = request.getParameter("approver");
			logger.debug("请假时长："+leavedys+"审核人："+approver);
			
			User findById = userService.findById(uid);
			String power = findById.getPower();
			String state="正在审批";
			
			Leavetime leavetime = new Leavetime(uid, username, leavettype, leavemsg, leavedys, startDate, endDate, approver,power,state);
			boolean flag = leavetimeService.addLeavetime(leavetime);
			success = flag;
			logger.debug("添加请假单标识："+flag);
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			errorMsg = e.getMessage();
			logger.debug("action层添加请假单异常："+e);
		}
		return "add";
	}
	
	@JSON(name = "rows")
	public List<Leavetime> getLeavetimelist() {
		return Leavetimelist;
	}

	public void setLeavetimelist(List<Leavetime> leavetimelist) {
		Leavetimelist = leavetimelist;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
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
	
	
}
