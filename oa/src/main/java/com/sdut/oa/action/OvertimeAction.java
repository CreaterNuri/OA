package com.sdut.oa.action;
import java.util.List;

/**
 * 加班管理 Action层
 */
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sdut.oa.entity.Overtime;
import com.sdut.oa.entity.User;
import com.sdut.oa.service.IOvertimeService;
import com.sdut.oa.service.IUserService;
@Controller("overtimeAction")
@Scope("prototype")
public class OvertimeAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(OvertimeAction.class);
	@Resource
	private IOvertimeService overtimeService;//注入service
	@Resource
	private IUserService userService;//注入用户
	private HttpServletRequest request;
	private boolean success = false;
	private String errorMsg = "";
	
	private List<Overtime> list;
	private int total;
	/**
	 * 查询加班记录
	 */
	public String queryAll() {
		//获取用户id
		String sid = request.getParameter("uid");
		int id = Integer.parseInt(sid);
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
		
		list = overtimeService.queryAll(startRow, pageSize, id);
		logger.debug("查询到的数据列表"+list);
		
		total = overtimeService.total(id);
		logger.debug("查询到的数据的总条数"+total);
		return "list";		
	}
	
	
	/**
	 * 添加加班记录
	 * @return
	 */
	public String addOvertime() {
		try {
			String sid = request.getParameter("id");
			int uid = Integer.parseInt(sid);
			logger.debug("用户id"+uid);
			String syear = request.getParameter("year");
			int year = Integer.parseInt(syear);
			String smonth = request.getParameter("month");
			int month = Integer.parseInt(smonth);
			String length = request.getParameter("length");
			double overtimedays = Double.parseDouble(length);
			logger.debug("加班时间："+overtimedays);
			//获取用户信息
			User user = userService.findById(uid);
			String power = user.getPower();
			String approver = request.getParameter("approver");
			String state="正在审批";
			Overtime overtime = new Overtime(uid, year, month, overtimedays,power,approver,state);
			boolean flag = overtimeService.addOvertime(overtime);
			logger.debug("Action层添加标识："+flag);
			success=flag;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			errorMsg = e.getMessage();
			logger.warn("Action层添加异常："+e);
		}
		return "add";
	}
	
	
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
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
	public List<Overtime> getList() {
		return list;
	}


	public void setList(List<Overtime> list) {
		this.list = list;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}
	

}
