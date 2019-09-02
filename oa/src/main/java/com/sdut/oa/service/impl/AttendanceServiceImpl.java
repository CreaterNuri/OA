package com.sdut.oa.service.impl;
/**
 * 考勤管理
 */
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdut.oa.dao.IAttendanceDao;
import com.sdut.oa.dao.ILeavetimeDao;
import com.sdut.oa.dao.IOvertimeDao;
import com.sdut.oa.dao.IUserDao;
import com.sdut.oa.entity.Attendance;
import com.sdut.oa.entity.User;
import com.sdut.oa.service.IAttendanceService;
@Service
public class AttendanceServiceImpl implements IAttendanceService {
	private Logger logger = Logger.getLogger(AttendanceServiceImpl.class);
	@Autowired
	private IAttendanceDao attendanceDao;//注入dao层
	@Autowired
	private IUserDao userDao;//注入userDao
	@Autowired
	private IOvertimeDao overtimeDao;//注入加班
	@Autowired
	private ILeavetimeDao leavetimeDao;//注入请假
	/**
	 * 根据uid,year,month查询
	 * 查询考勤表开始，需要提前查询请假表和加班表
	 */
	@Override
	public List<Attendance> queryAll(int uid, int year, int month) {
		logger.debug("servie层查询考勤表开始");
		List<Attendance> list = null;
		List<Attendance> attendance = attendanceDao.queryAttendance(uid, year, month);
		if(attendance.size()==0){
			//判断是否存在用户考勤表
			User user = userDao.findbyId(uid);
			String power = user.getPower();
			boolean b = attendanceDao.crateAttendence(uid, year, month, power);
			logger.debug("考勤表添加标识："+b);
			list = attendanceDao.queryAttendance(uid, year, month);
			
		}else {
			list  = attendance;
			logger.debug("查询数据："+list.size());
		}
		return list;
	}

}
