package com.sdut.oa.service;

import java.util.List;

import com.sdut.oa.entity.Attendance;

public interface IAttendanceService {
	/**
	 * 根据用户id,月份，年份查询出勤表
	 */
	public List<Attendance> queryAll(int uid,int year,int month);
}
