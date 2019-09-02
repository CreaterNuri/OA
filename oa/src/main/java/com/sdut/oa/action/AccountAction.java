package com.sdut.oa.action;
/**
 * 报销单管理 控制层
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
import com.sdut.oa.entity.Account;
import com.sdut.oa.entity.Reimburses;
import com.sdut.oa.service.IAccountService;
import com.sdut.oa.service.IReimbursesService;
@Controller("accountAction")
@Scope("prototype")
public class AccountAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(AccountAction.class);
	@Resource
	private IAccountService accountService;//注入service
	@Resource
	private IReimbursesService reimbursesService;//查询报销类型

	private HttpServletRequest request;
	
	private boolean success = false;
	private String errorMsg = "";
	
	private List<Account> list;
	private List<Reimburses> reimburseslist;
	private int  total;
	
	/**
	 * 查询所有报销单
	 */
	public String queryAll() {
		//得到当前登陆用户
		String username = request.getParameter("username");
		logger.debug("获取登陆用户名："+username);
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
		
		list = accountService.findAll(startRow, pageSize,username);
		logger.debug("查询到的数据列表"+list);
		
		total = accountService.getTotal(username);
		logger.debug("查询到的数据的总条数"+total);
		return "list";
	}
	
	/**
	 * 查询所有报销类型
	 * @return string
	 */
	public String findAllType() {
		logger.debug("Action层查询所有报销类型阶段开始");
		reimburseslist = reimbursesService.findAll();
		return "type";
	}
	
	/**
	 * 添加报销单
	 * @return String
	 */
	public String addAccount() {
		logger.debug("报销单申请开始");
		try {
			String accounttype = request.getParameter("accounttype");
			String smoney = request.getParameter("money");
			double money=Double.parseDouble(smoney);
			String username = request.getParameter("username");
			String approver = request.getParameter("approver");
			
			String sdate = request.getParameter("date");
			SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
			Date date = simpledateformat.parse(sdate);
			logger.debug("类型："+accounttype+"金额："+money+"用户："+username+"审批人："+approver+"日期"+date);
			String state="正在审批";
			Account account = new Account(date, accounttype, money, username, state, approver);
			boolean flag = accountService.addAccount(account);
			success = flag;
			logger.debug("Action层报销单添加标识："+flag);
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			errorMsg = e.getMessage();
			logger.debug("action层添加报销单异常："+e);
		}
		return "add";
	}
	
	/**
	 * 报销单管理
	 */
	public String managerAccount(){
		logger.debug("Action层更新报销单开始");
		try {
			String sid = request.getParameter("id");
			int id = Integer.parseInt(sid);
			logger.debug("获取更新id:"+id);
			
			String updaccounttype = request.getParameter("updaccounttype");
			String updmoney = request.getParameter("updmoney");
			
			double money = Double.parseDouble(updmoney);
			logger.debug("得到更新的金额："+money);
			
			String username = request.getParameter("username");
			String updapprover = request.getParameter("updapprover");
			
			String sdate = request.getParameter("upddate");
			SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
			Date date = simpledateformat.parse(sdate);
			
			logger.debug("得到修改时间："+date);
			String state = request.getParameter("state");
			logger.debug("获取报销单状态"+state);
			
			Account account = new Account(id,date, updaccounttype, money, username, state, updapprover);
			boolean flag = accountService.updAccount(account);
			logger.debug("action层更新标识："+flag);
			success = flag;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			errorMsg = e.getMessage();
			logger.debug("action层添加报销单异常："+e);
		}
		return "manager";
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
	public List<Account> getList() {
		return list;
	}

	public void setList(List<Account> list) {
		this.list = list;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	public List<Reimburses> getReimburseslist() {
		return reimburseslist;
	}
	
	public void setReimburseslist(List<Reimburses> reimburseslist) {
		this.reimburseslist = reimburseslist;
	}

}
