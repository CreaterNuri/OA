package com.sdut.oa.service;

import java.util.List;

import com.sdut.oa.entity.Leavetime;

public interface ILeavetimeService {
	/**
	 * 请假单添加
	 */
	public boolean addLeavetime(Leavetime leavetime);
	/**
	 * 请假单查询
	 */
	public List<Leavetime> queryAll(int startRow, int pageSize,int uid);
}
