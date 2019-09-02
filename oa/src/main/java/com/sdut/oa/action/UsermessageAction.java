package com.sdut.oa.action;
/**
 * 用户信息管理
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
import com.sdut.oa.entity.Usermessage;
import com.sdut.oa.service.IUsermessageService;
@Controller("usermessageAction")
@Scope("prototype")
public class UsermessageAction extends ActionSupport implements ServletRequestAware {
	
	private Logger logger = Logger.getLogger(UsermessageAction.class);
	private static final long serialVersionUID = 1L;
	@Resource
	private IUsermessageService usermessageService;//注入service
	
	private HttpServletRequest request;
	private int listNum;//信息条数
	
	private List<Usermessage> list;
	private int total;
	
	private boolean success = false;
	private String errorMsg = "";
	
	/**
	 * 查询用户信息数量
	 */
	public String queryUserNum() {
		String approver = request.getParameter("username");
		listNum = usermessageService.getTotal(approver);
		return "number";
	}
	
	/**
	 * 查询所有信息
	 * @return String
	 */
	public  String queryAll() {
		//得到当前登陆用户
		String approver = request.getParameter("username");
		logger.debug("获取登陆用户名："+approver);
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
		
		list = usermessageService.queryAll(startRow, pageSize, approver);
		
		logger.debug("查询到的数据列表"+list);
		
		total = list.size();
		logger.debug("查询到的数据的总条数"+total);
		return "list";
	}
	
	/**
	 * 信息处理
	 * @return String
	 */
	public String managerMsg() {
		try {
			Usermessage usermessage = new Usermessage();
			String said = request.getParameter("aid");
			int aid = Integer.parseInt(said);
			usermessage.setAid(aid);
			String soid = request.getParameter("oid");
			int oid = Integer.parseInt(soid);
			usermessage.setOid(oid);
			String slid = request.getParameter("lid");
			int lid = Integer.parseInt(slid);
			usermessage.setLid(lid);
			String sid = request.getParameter("id");
			int id = Integer.parseInt(sid);
			usermessage.setId(id);
			String advicestate = request.getParameter("advicestate");
			logger.debug("信息处理状态"+advicestate);
			if(advicestate.equals("搁置处理")){
				usermessage.setState(0);
			}else {
				usermessage.setState(1);
			}
			String approveradvice = request.getParameter("approveradvice");
			usermessage.setNote(approveradvice);
			boolean flag = usermessageService.managerMsg(usermessage);
			success = flag;
			logger.debug("消息处理标识："+flag);
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			errorMsg = e.getMessage();
			logger.warn("Action层处理信息异常", e);
		}
		return "manager";
	}
	
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@JSON(name = "rows")
	public List<Usermessage> getList() {
		return list;
	}

	public void setList(List<Usermessage> list) {
		this.list = list;
	}

	public int getListNum() {
		return listNum;
	}

	public void setListNum(int listNum) {
		this.listNum = listNum;
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
