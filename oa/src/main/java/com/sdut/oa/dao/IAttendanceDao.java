package com.sdut.oa.dao;

import java.util.List;

import com.sdut.oa.entity.Attendance;

public interface IAttendanceDao {
	/**
	 * 考勤查询
	 */
	public List<Attendance> queryAttendance(int uid,int year,int month);
	/**
	 * 创建考勤表
	 */
	public boolean crateAttendence(int uid,int year,int month,String power);
	/**
	 * 加班天数更新
	 */
	public boolean updateOvertime(int id,double overtimedays,double dutydays,double requesttime);
	/**
	 * 更新考勤表的请假天数
	 */
	public boolean updateRequest(int id,double requestdays,double dutydays,double overtime);
}
