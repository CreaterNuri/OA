package com.sdut.oa.service.impl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 请假管理 service
 */
import org.springframework.stereotype.Service;

import com.sdut.oa.common.Dateutil;
import com.sdut.oa.dao.IAttendanceDao;
import com.sdut.oa.dao.ILeavetimeDao;
import com.sdut.oa.dao.IUsermessageDao;
import com.sdut.oa.entity.Attendance;
import com.sdut.oa.entity.Leavetime;
import com.sdut.oa.entity.Usermessage;
import com.sdut.oa.service.ILeavetimeService;
@Service
public class LeavetimeServiceImpl implements ILeavetimeService {
	
	private Logger logger= Logger.getLogger(LeavetimeServiceImpl.class);
	@Autowired
	private ILeavetimeDao leavetimeDao;//注入dao
	@Autowired
	private IAttendanceDao attendanceDao;
	@Autowired
	private IUsermessageDao usermessageDao;//信息管理
	/**
	 * 添加请假单，出勤表的修改两种情况（请假时长为两个月）
	 * 1.请假在同一个月内
	 * 2.请假跨月
	 * 	2.1计算本月的出勤情况（根据开始时间的月份）
	 * 	2.2计算跨了几个月（结束月份-开始月份）
	 * 	2.3计算跨月情况下开始月分到当前月的最后一天的请假天数，最后一个月的请假情况，
	 */
	@Override
	public boolean addLeavetime(Leavetime leavetime) {
		boolean flag=false;
		boolean upattendeceFlag=false;
		boolean nextupattendeceFlag=false;
		//将请假按添加到请假表中
		boolean b = leavetimeDao.addLeaveTime(leavetime);
		//将请假添加到信息表中
		Usermessage usermessage = new Usermessage();
		int lid = leavetime.getId();
		usermessage.setState(0);
		usermessage.setMessage("请假类型："+leavetime.getType()+",请假原因："+leavetime.getLeavemsg());
		usermessage.setApplicant(leavetime.getUsername());
		usermessage.setApprover(leavetime.getApprover());
		usermessage.setTime(leavetime.getStarttime());
		usermessage.setLid(lid);
		boolean addMsg = usermessageDao.addMsg(usermessage);
		logger.debug("添加信息标识："+addMsg);
		//添加成功
		if(b && addMsg){
			//得到开始时间
			Date starttime = leavetime.getStarttime();
			//结束时间
			Date endtime = leavetime.getEndtime();
			// String 开始日期
			String startStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(starttime);
			//String 结束日期
			String endStr= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endtime);
			//获取开始年份
			String startyear = startStr.substring(0, 4);
			int istartyear = Integer.parseInt(startyear);
			//结束年份
			String endyear = endStr.substring(0,4);
			int iendyear = Integer.parseInt(endyear);
			//截取月份
			String startmonth = startStr.substring(5, 7);
			String endmonty = endStr.substring(5, 7);
			//月份
			int startMonth = Integer.parseInt(startmonth);
			int endMonth=Integer.parseInt(endmonty);
			
			//同一个月份内请假，只需要更新本月出勤表
			if (istartyear==iendyear && startMonth==endMonth) {
				//根据uid,month,year查询出勤表数据
				List<Attendance> attendances = attendanceDao.queryAttendance(leavetime.getUid(), istartyear, startMonth);
				//没有考勤表创建考勤表
				if(attendances.size()==0){
					boolean c = attendanceDao.crateAttendence(leavetime.getUid(), istartyear, startMonth,leavetime.getPower());
					//创建完成后重新查询数据
					List<Attendance> queryAttendance = attendanceDao.queryAttendance(leavetime.getUid(), istartyear, startMonth);
					
					//创建出勤表后进行更新
					//获取出勤表对应id
					int id = queryAttendance.get(0).getId();
					//加班天数
					double overtimedays = queryAttendance.get(0).getOvertimedays();
					//获取应出勤天数
					double dutydays = queryAttendance.get(0).getDutydays();
					//根据起止日期计算时间间隔
					int days = Dateutil.getDutyDays(startStr, endStr);
					//转为double
					double requesttime=days;
					//获取原来的加班天数
					double oldrequestDays = queryAttendance.get(0).getRequestdays();
					//总的请假天数
					double newrequestdays=oldrequestDays+requesttime;
					//更新出勤表
					boolean upnowattendeceFlag = attendanceDao.updateRequest(id, newrequestdays, dutydays,overtimedays);
					//判断是否更新成功
					if (upnowattendeceFlag && c) {
						flag=true;
					}else {
						flag= false;
					}
				}else {
					//获取出勤表对应id
					int id = attendances.get(0).getId();
					//加班天数
					double overtimedays = attendances.get(0).getOvertimedays();
					//获取应出勤天数
					double dutydays = attendances.get(0).getDutydays();
					//根据起止日期计算时间间隔
					int days = Dateutil.getDutyDays(startStr, endStr);
					//转为double
					double requesttime=days;
					//获取原来的加班天数
					double oldrequestDays = attendances.get(0).getRequestdays();
					//总的请假天数
					double newrequestdays=oldrequestDays+requesttime;
					//更新出勤表
					boolean upnowattendeceFlag = attendanceDao.updateRequest(id, newrequestdays, dutydays,overtimedays);
					//判断是否更新成功
					if (upnowattendeceFlag) {
						flag=true;
					}else {
						flag= false;
					}
				}
				//请假时间跨月
				//非本月时间
				//获取月份最后时间
			}else{
				try {
					//得到开始时间
					Date starttime2 = leavetime.getStarttime();
					//结束时间
					Date endtime2 = leavetime.getEndtime();
					// String 开始日期
					String startStr2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(starttime2);
					String startyear2 = startStr2.substring(0, 4);
					int istartyear2 = Integer.parseInt(startyear2);
					//String 结束日期
					String endStr2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endtime2);
					
					//获取月份
					String startmonth2 = startStr2.substring(5, 7);
					//开始月份
					int startMonth2 = Integer.parseInt(startmonth2);
					String endmonty2 = endStr2.substring(5, 7);
					//结束月份
					int endMonth2 =Integer.parseInt(endmonty2);
					//本月最后一天
					String maxMonthDate = Dateutil.getMaxMonthDate(startStr2);
					//计算时间间隔
					//根据起止日期计算时间间隔
					int days = Dateutil.getDutyDays(startStr2, maxMonthDate);
					//根据uid,month,year查询出勤表数据
					List<Attendance> attendances = attendanceDao.queryAttendance(leavetime.getUid(), istartyear2, startMonth2);
					
					List<Attendance> nextqueryAttendance = attendanceDao.queryAttendance(leavetime.getUid(), istartyear2, endMonth2);
					//开始 和结束的月份考勤都为空
					if (attendances.size()==0 && nextqueryAttendance.size()==0) {
						boolean c = attendanceDao.crateAttendence(leavetime.getUid(), istartyear2, startMonth2,leavetime.getPower());
						List<Attendance> queryAttendence = attendanceDao.queryAttendance(leavetime.getUid(), istartyear2, startMonth2);
						//获取出勤表对应id
						int id = queryAttendence.get(0).getId();
						//加班时间
						double overtimedays = queryAttendence.get(0).getOvertimedays();
						//获取应出勤天数
						double dutydays = queryAttendence.get(0).getDutydays();
						//根据起止日期计算时间间隔
						//转为double
						double requesttime=days;
						//新的请假天数
						double newrequestDays=requesttime+queryAttendence.get(0).getRequestdays();
						//更新出勤表
						upattendeceFlag = attendanceDao.updateRequest(id, newrequestDays, dutydays,overtimedays);
						
						//更新另一个月份
						//另一个月份的更新 从月初到结束时间
						//获取月初
						String minMonthDate = Dateutil.getMinMonthDate(endStr2);
						//计算时间间隔
						//根据起止日期计算时间间隔
						int nextdays = Dateutil.getDutyDays(minMonthDate, endStr2);
						boolean creatFlag = attendanceDao.crateAttendence(leavetime.getUid(), istartyear2, endMonth2,leavetime.getPower());
						List<Attendance> nextattendancesList = attendanceDao.queryAttendance(leavetime.getUid(), istartyear2, endMonth2);
						//获取出勤表对应id
						int nextid = nextattendancesList.get(0).getId();
						//加班时间
						double nextovertimedays = nextattendancesList.get(0).getOvertimedays();
						//获取应出勤天数
						double nextdutydays = nextattendancesList.get(0).getDutydays();
						//转为double
						double nextrequesttime=nextdays;
						//新的请假天数
						double newRequestdays=nextrequesttime+nextattendancesList.get(0).getRequestdays();
						//更新出勤表
						nextupattendeceFlag = attendanceDao.updateRequest(nextid, newRequestdays, nextdutydays,nextovertimedays);
						if(c && upattendeceFlag && creatFlag && nextupattendeceFlag){
							flag =true;
						}else {
							flag =false;
						}
					}else if(attendances.size()!=0 && nextqueryAttendance.size()==0){
						//获取出勤表对应id
						int id = attendances.get(0).getId();
						//加班时间
						double overtimedays = attendances.get(0).getOvertimedays();
						//获取应出勤天数
						double dutydays = attendances.get(0).getDutydays();
						//根据起止日期计算时间间隔
						//转为double
						double requesttime=days;
						//新的请假天数
						double newrequestDays=requesttime+attendances.get(0).getRequestdays();
						//更新出勤表
						boolean oldupattendeceFlag = attendanceDao.updateRequest(id, newrequestDays, dutydays,overtimedays);
						//另一个月份考勤月份为空
						//创建考勤表
						boolean creatFlag = attendanceDao.crateAttendence(leavetime.getUid(), istartyear2, endMonth2,leavetime.getPower());
						//查询创建的考勤表
						List<Attendance> nextattendancesList = attendanceDao.queryAttendance(leavetime.getUid(), istartyear2, endMonth2);
						//获取出勤表对应id
						int nextid = nextattendancesList.get(0).getId();
						//加班时间
						double nextovertimedays = nextattendancesList.get(0).getOvertimedays();
						//获取应出勤天数
						double nextdutydays = nextattendancesList.get(0).getDutydays();
						//获取月初
						String minMonthDate = Dateutil.getMinMonthDate(endStr2);
						//计算时间间隔
						//根据起止日期计算时间间隔
						int nextdays = Dateutil.getDutyDays(minMonthDate, endStr2);
						double nextrequesttime=nextdays;
						//新的请假天数
						double newRequestdays=nextrequesttime+nextattendancesList.get(0).getRequestdays();
						//更新出勤表
						boolean nextupattendeceFlag2 = attendanceDao.updateRequest(nextid, newRequestdays, nextdutydays,nextovertimedays);
						if(oldupattendeceFlag && creatFlag && nextupattendeceFlag2 ){
							flag = true;
						}else {
							flag =false;
						}
					}else if (attendances.size()==0 && nextqueryAttendance.size()!=0) {
						boolean c = attendanceDao.crateAttendence(leavetime.getUid(), istartyear2, startMonth2,leavetime.getPower());
						List<Attendance> queryAttendence = attendanceDao.queryAttendance(leavetime.getUid(), istartyear2, startMonth2);
						//获取出勤表对应id
						int id = queryAttendence.get(0).getId();
						//加班时间
						double overtimedays = queryAttendence.get(0).getOvertimedays();
						//获取应出勤天数
						double dutydays = queryAttendence.get(0).getDutydays();
						//根据起止日期计算时间间隔
						//转为double
						double requesttime=days;
						//新的请假天数
						double newrequestDays=requesttime+queryAttendence.get(0).getRequestdays();
						//更新出勤表
						boolean newupattendeceFlag = attendanceDao.updateRequest(id, newrequestDays, dutydays,overtimedays);
						
						//灵一个月份
						//获取出勤表对应id
						int nid = nextqueryAttendance.get(0).getId();
						//加班时间
						double novertimedays = nextqueryAttendance.get(0).getOvertimedays();
						//获取应出勤天数
						double ndutydays = nextqueryAttendance.get(0).getDutydays();
						//根据起止日期计算时间间隔
						//转为double
						double nrequesttime=days;
						//新的请假天数
						double nnewrequestDays=nrequesttime+nextqueryAttendance.get(0).getRequestdays();
						//更新出勤表
						boolean oldupattendeceFlag = attendanceDao.updateRequest(nid, nnewrequestDays, ndutydays,novertimedays);
						if(c && newupattendeceFlag && oldupattendeceFlag){
							flag =true;
						}else {
							flag =false;
						}
					}else{
						//获取出勤表对应id
						int id = attendances.get(0).getId();
						//加班时间
						double overtimedays = attendances.get(0).getOvertimedays();
						//获取应出勤天数
						double dutydays = attendances.get(0).getDutydays();
						//根据起止日期计算时间间隔
						//转为double
						double requesttime=days;
						//新的请假天数
						double newrequestDays=requesttime+attendances.get(0).getRequestdays();
						//更新出勤表
						boolean oldupattendeceFlag = attendanceDao.updateRequest(id, newrequestDays, dutydays,overtimedays);
						
						//灵一个月份
						//获取出勤表对应id
						int nid = nextqueryAttendance.get(0).getId();
						//加班时间
						double novertimedays = nextqueryAttendance.get(0).getOvertimedays();
						//获取应出勤天数
						double ndutydays = nextqueryAttendance.get(0).getDutydays();
						//根据起止日期计算时间间隔
						//转为double
						double nrequesttime=days;
						//新的请假天数
						double nnewrequestDays=nrequesttime+nextqueryAttendance.get(0).getRequestdays();
						//更新出勤表
						boolean otherupattendeceFlag = attendanceDao.updateRequest(nid, nnewrequestDays, ndutydays,novertimedays);
						if(oldupattendeceFlag && otherupattendeceFlag){
							flag =true;
						}else {
							flag = false;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.warn("不同月份更新异常"+e);
				}
			}
		}else{
			//信息添加出错	
			logger.debug("service层天机信息到用户信息列表和请假表出错");
			flag=false;
		}
		return flag;
	}
	
	/**
	 * 查询请假单
	 */
	@Override
	public List<Leavetime> queryAll(int startRow, int pageSize, int uid) {
		logger.debug("Service层查询请假单开始");
		List<Leavetime> list = leavetimeDao.queryAll(startRow, pageSize, uid);
		return list;
	}

}
