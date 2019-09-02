package com.sdut.oa.action;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;
/**
 * 考勤管理 Action
 */
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionSupport;
import com.sdut.oa.entity.Attendance;
import com.sdut.oa.service.IAttendanceService;

@Controller("attendanceAction")
@Scope("prototype")
public class AttendanceAction extends ActionSupport implements ServletRequestAware{
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(AttendanceAction.class);
	@Resource
	private IAttendanceService attendanceService;//注入service
	
	private HttpServletRequest request;
	private boolean success = false;
	private String errorMsg = "";
	
	private List<Attendance> attendanceList;
	private int total;
	
	/**
	 * 查询考勤表
	 */
	public String queryAttendence(){
		try {
			String suid = request.getParameter("uid");
			int uid = Integer.parseInt(suid);
			//获取日期
			String date = request.getParameter("date");
			//截取年份
			String syear = date.substring(0, 4);
			//截取月份
			String smonth = date.substring(5, 7);
			logger.debug("获取年份"+syear+"月份"+smonth);
			int year = Integer.parseInt(syear);
			int month = Integer.parseInt(smonth);
			attendanceList = attendanceService.queryAll(uid, year, month);
			total=attendanceList.size();
			logger.debug("查询数据长度："+total);
		} catch (Exception e) {
			logger.warn("Action层查询异常："+e);
		}
		return "query";
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
	public List<Attendance> getAttendanceList() {
		return attendanceList;
	}


	public void setAttendanceList(List<Attendance> attendanceList) {
		this.attendanceList = attendanceList;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}
	
	

}
