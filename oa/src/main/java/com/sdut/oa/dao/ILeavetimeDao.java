package com.sdut.oa.dao;

import java.util.List;

import com.sdut.oa.entity.Leavetime;

public interface ILeavetimeDao {
	/**
	 * 请假单添加
	 */
	public boolean addLeaveTime(Leavetime leavetime);
	/**
	 * 请假单查询
	 */
	public List<Leavetime> queryAll(int startRow, int pageSize,int uid);
	/**
	 * 根据uid查询请假单
	 */
	public List<Leavetime> queryById(int uid);
	/**
	 * 根据id
	 * 更新状态
	 */
	public boolean updateState(int id,String state);
}
