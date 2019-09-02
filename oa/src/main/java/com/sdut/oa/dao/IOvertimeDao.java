package com.sdut.oa.dao;

import java.util.List;

import com.sdut.oa.entity.Overtime;

public interface IOvertimeDao {
	/**
	 * 加班添加
	 * @return boolean
	 */
	public boolean addOvertime(Overtime overtime);
	/**
	 * 加班查询
	 */
	public List<Overtime> queryAll(int startRow, int pageSize,int uid);
	/**
	 * 加班信息总条数查询
	 */
	public int total(int uid);
	/**
	 * 根据uid,年份，月份查询加班数据
	 */
	public List<Overtime> queryById(int uid,int year,int month);
	/**
	 * 根据id,更新请假状态
	 */
	public boolean updateState(int id,String state); 
	
}
