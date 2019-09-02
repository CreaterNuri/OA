package com.sdut.oa.action;
/**
 * 通讯录管理 Action
 */
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionSupport;
import com.sdut.oa.entity.Addresslist;
import com.sdut.oa.service.IAddresslistService;
@Controller("addresslistAction")
@Scope("prototype")
public class AddresslistAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(AddresslistAction.class);
	
	@Resource
	private IAddresslistService addresslistService;//注入service
	
	private HttpServletRequest request;
	private boolean success = false;
	private String errorMsg = "";
	
	private Addresslist addresslist;
	/**
	 * 根据姓名查询
	 */
	public String  findByName() {
		logger.debug("Action层根据用户名查询开始");
		try {
			String name = request.getParameter("name");
			logger.debug("Action获取用户名："+name);
			addresslist = addresslistService.findByName(name);
			logger.debug("根据用户名查询数据id："+addresslist.getId());
			success=true;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			errorMsg = e.getMessage();
		}
		return "name";
	}
	
	/**
	 * 根据id查询
	 */
	public String findById() {
		logger.debug("Action层根据id查询详情开始");
		try {
			String sid = request.getParameter("id");
			int id = Integer.parseInt(sid);
			logger.debug("Action获取id："+id);
			addresslist = addresslistService.findById(id);
			logger.debug("根据用户名查询数据id："+addresslist.getId());
			success=true;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			errorMsg = e.getMessage();
		}
		return "id";
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

	public Addresslist getAddresslist() {
		return addresslist;
	}

	public void setAddresslist(Addresslist addresslist) {
		this.addresslist = addresslist;
	}
	
}
