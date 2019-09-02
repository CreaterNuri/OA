package com.sdut.oa.action;
/**
 * 公告管理Action
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
import com.sdut.oa.entity.Notice;
import com.sdut.oa.service.INoticeService;
@Controller("noticeAction")
@Scope("prototype")
public class NoticeAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	
	private Logger logger = Logger.getLogger(NoticeAction.class);
	@Resource
	private INoticeService noticeService;//注入service
	
	private HttpServletRequest request;
	
	private boolean success = false;
	private String errorMsg = "";
	
	private int  total;
	private List<Notice> list;
	
	/**
	 * 查询所有公告信息
	 * @return String
	 */
	public String queryAll() {
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
		
		list = noticeService.findAll(startRow, pageSize);
		logger.debug("查询到的数据列表"+list);
		
		total = noticeService.getTotal();
		logger.debug("查询到的数据的总条数"+total);
		return "list";
	}
	
	/**
	 * 公告添加
	 * @return String
	 */
	public String addNotice() {
		logger.debug("Action层公告添加开始");
		try {
			//公告编号
			String noticeNum = request.getParameter("noticeNum");
			int number = Integer.parseInt(noticeNum);
			//公告内容
			String noticeMsg = request.getParameter("noticeMsg");
			//发布人
			String publisher = request.getParameter("publisher");
			//发布时间
			String releasetime = request.getParameter("releasetime");
			logger.debug("公告编号："+number+"公告内容："+noticeMsg+"分布人："+publisher+"发布时间："+releasetime);
			Notice notice = new Notice(noticeMsg, publisher, releasetime, number);
			boolean addflag = noticeService.add(notice);//添加结果
			logger.debug("添加公告结果："+addflag);
			success=addflag;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			errorMsg = e.getMessage();
		}
		return "add";
	}
	
	/**
	 * 公告删除
	 * @return String
	 */
	public String delNotice() {
		logger.debug("Action层删除公告开始！");
		try {
			String sid = request.getParameter("id");
			int id = Integer.parseInt(sid);
			logger.debug("获取删除公告id:"+id);
			Notice notice = new Notice(id);
			boolean delflag = noticeService.delNotice(notice);
			logger.debug("公告删除结果："+delflag);
			success=delflag;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			errorMsg = e.getMessage();
		}
		return "del";
	}
	
	/**
	 * 公告更新
	 * @return String 
	 */
	public String updateNotice() {
		logger.debug("Action公告更新开始");
		try {
			String sid = request.getParameter("id");
			int id = Integer.parseInt(sid);
			logger.debug("项目更新ID:"+id);
			String username=request.getParameter("username");
			String snumber = request.getParameter("updnoticeNum");
			int number = Integer.parseInt(snumber);
			String noticeMsg = request.getParameter("updnoticeMsg");
			String releasetime = request.getParameter("updreleasetime");
			logger.debug("公告更新信息，公告编号："+number+"公告内容："+noticeMsg+"公告时间："+releasetime+"公告发布人："+username);
			Notice notice = new Notice(id, noticeMsg, username, releasetime,number);
			boolean updflag = noticeService.updNotice(notice);
			success = updflag;
			logger.debug("公告更新标识："+updflag);
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			errorMsg = e.getMessage();
			logger.warn("公告更新异常："+e);
		}
		return "update";
	}
	
	@JSON(name = "rows")
	public List<Notice> getList() {
		return list;
	}

	public void setList(List<Notice> list) {
		this.list = list;
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

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
