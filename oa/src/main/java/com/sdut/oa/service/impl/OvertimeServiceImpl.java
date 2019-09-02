package com.sdut.oa.service.impl;
/**
 * 加班管理 service
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdut.oa.dao.IAttendanceDao;
import com.sdut.oa.dao.IOvertimeDao;
import com.sdut.oa.dao.IUserDao;
import com.sdut.oa.dao.IUsermessageDao;
import com.sdut.oa.entity.Attendance;
import com.sdut.oa.entity.Overtime;
import com.sdut.oa.entity.User;
import com.sdut.oa.entity.Usermessage;
import com.sdut.oa.service.IOvertimeService;
@Service
public class OvertimeServiceImpl implements IOvertimeService {
	
	private Logger logger=Logger.getLogger(OvertimeServiceImpl.class);
	
	@Autowired
	private IOvertimeDao overtimeDao;//注入Dao
	@Autowired
	private IAttendanceDao attendanceDao;//考勤管理
	@Autowired
	private IUserDao userDao;//用户管理
	@Autowired
	private IUsermessageDao usermessageDao;//信息申请
	/**
	 * 添加加班数据
	 */
	@Override
	public boolean addOvertime(Overtime overtime) {
		boolean flag=false;
		//添加数据到加班表
		boolean b = overtimeDao.addOvertime(overtime);
		//将加班信息添加到信息处理表
		Date day=new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		//时间
		String snowdate = df.format(day);
		Date nowdate = null;
		try {
			nowdate = df.parse(snowdate);
		} catch (ParseException e) {
			e.printStackTrace();
			logger.warn("日期转换异常", e);
		}
		
		User user = userDao.findbyId(overtime.getUid());
		//获取用户名
		String username = user.getUname();
		String message="加班申请,时长:"+overtime.getOvertimedays()+"天";
		String approver=overtime.getApprover();
		Usermessage usermessage = new Usermessage();
		usermessage.setApplicant(username);
		usermessage.setApprover(approver);
		usermessage.setMessage(message);
		usermessage.setTime(nowdate);
		//加班表id
		int oid = overtime.getId();
		usermessage.setOid(oid);
		//信息添加
		boolean msg = usermessageDao.addMsg(usermessage);
		
		if(b&&msg){
			//根据uid,month,year查询出勤表数据
			List<Attendance> attendances = attendanceDao.queryAttendance(overtime.getUid(), overtime.getYear(), overtime.getMonth());
			if(attendances.size()==0){
				boolean c = attendanceDao.crateAttendence(overtime.getUid(), overtime.getYear(), overtime.getMonth(),overtime.getPower());
				if(c){
					logger.debug("service层添加考勤表成功："+c);
					flag=false;
				}
				flag=true;
			}else{
				//获取出勤表对应id
				int id = attendances.get(0).getId();
				//请假天数
				double requestdays = attendances.get(0).getRequestdays();
				//获取应出勤天数
				double dutydays = attendances.get(0).getDutydays();
				//加班天数=原加班天数+现在的加班天数
				double Oldovertime=attendances.get(0).getOvertimedays();
				double newOvertime=overtime.getOvertimedays();
				double actualovertime=Oldovertime+newOvertime;
				//更新出勤表
				boolean upattendeceFlag = attendanceDao.updateOvertime(id, actualovertime, dutydays,requestdays);
				if(upattendeceFlag){
					flag=true;
				}else {
					flag=false;
				}
			}
		}
		return  flag;
	}
	
	/**
	 * 查询所有加班信息
	 */
	@Override
	public List<Overtime> queryAll(int startRow, int pageSize, int uid) {
		logger.debug("Service层查询所有加班信息");
		List<Overtime> list = overtimeDao.queryAll(startRow, pageSize, uid);
		return list;
	}
	
	/**
	 * 加班信息总条数
	 */
	@Override
	public int total(int id) {
		logger.debug("service层查询加班信息总条数");
		int total = overtimeDao.total(id);
		return total;
	}

}
