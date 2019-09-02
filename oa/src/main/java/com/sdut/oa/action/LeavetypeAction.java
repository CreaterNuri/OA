package com.sdut.oa.action;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
/**
 * 请假类型 
 */
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionSupport;
import com.sdut.oa.entity.Leavetype;
import com.sdut.oa.service.ILeavetypeService;

@Controller("leavetypeAction")
@Scope("prototype")
public class LeavetypeAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private Logger logger =Logger.getLogger(LeavetypeAction.class);
	@Resource
	private ILeavetypeService leavetypeService;//注入service
	
	private List<Leavetype> list;

	/**
	 * 查询所有请假类型
	 * @return String
	 */
	public String queryAllType() {
		logger.debug("Action层查询所有请假类型阶段开始");
		list = leavetypeService.queryAll();
		return "list";
	}
	
	public List<Leavetype> getList() {
		return list;
	}
	public void setList(List<Leavetype> list) {
		this.list = list;
	}
	
	
}
