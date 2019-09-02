package com.sdut.oa.service;

import java.util.List;

import com.sdut.oa.entity.Overtime;

public interface IOvertimeService {
	
	/**
	 * 添加加班
	 */
	public boolean addOvertime(Overtime overtime);
	/**
	 * 加班查询
	 */
	public List<Overtime> queryAll(int startRow, int pageSize,int uid);
	/**
	 * 加班信息总条数
	 */
	public int total(int id);
}	
