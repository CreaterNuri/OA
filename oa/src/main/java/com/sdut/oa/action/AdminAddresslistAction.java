package com.sdut.oa.action;
/**
 * 管理员管理用户联系方式
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
import com.sdut.oa.entity.Addresslist;
import com.sdut.oa.service.IAddresslistService;
@Controller("adminAddresslistAction")
@Scope("prototype")
public class AdminAddresslistAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(AdminAddresslistAction.class);
	@Resource
	private IAddresslistService addresslistService;//注入service
	
	private HttpServletRequest request;
	
	private boolean success = false;
	private String errorMsg = "";
	
	private List<Addresslist> addresslist;
	private int total;
	
	/**
	 * 通讯录查询
	 */
	public String queryAddress() {
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
			addresslist = addresslistService.queryAll(startRow, pageSize);
			total = addresslistService.getTotal();
			logger.debug("查询所有用户信息："+total);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			errorMsg = e.getMessage();
			logger.warn("Action层查询所有通讯录异常："+e);
		}
		return "address";
	}

	/**
	 * 添加用户联系方式
	 */
	public String addAddress(){
		try {
			String name = request.getParameter("addname");
			String phone = request.getParameter("addphone");
			String address = request.getParameter("addaddress");
			String email = request.getParameter("addemail");
			String power = request.getParameter("power");
			Addresslist addresslist =new Addresslist(name, phone, address, email, power);
			boolean flag = addresslistService.addAddress(addresslist);
			logger.debug("Action层添加用户联系方式异常："+flag);
			success=flag;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			errorMsg = e.getMessage();
			logger.warn("Action层添加用户联系方式异常："+e);
		}
		return "addaddress";
	}
	
	/**
	 * 更新用户联系方式
	 */
	public String updAddress() {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("updname");
			String phone = request.getParameter("updphone");
			String address = request.getParameter("updaddress");
			String email = request.getParameter("updemail");
			String power = request.getParameter("power");
			Addresslist addresslist = new Addresslist(id, name, phone, address, email, power);
			boolean flag = addresslistService.updateAddress(addresslist);
			logger.debug("Action层更新标识："+flag);
			success=flag;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			errorMsg = e.getMessage();
			logger.warn("Action层更新用户联系方式异常："+e);
		}
		return "updaddress";
	}
	
	/**
	 * 删除用户联系方式
	 */
	public String delAddress(){
		try {
			String sid = request.getParameter("id");
			int id = Integer.parseInt(sid);
			logger.debug("action层得到删除用户id："+id);
			boolean flag = addresslistService.deleteAddress(id);
			success = flag;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			errorMsg = e.getMessage();
			logger.warn("Action层删除用户联系方式异常："+e);
		}
		return "deladdress";
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
	public List<Addresslist> getAddresslist() {
		return addresslist;
	}

	public void setAddresslist(List<Addresslist> addresslist) {
		this.addresslist = addresslist;
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
