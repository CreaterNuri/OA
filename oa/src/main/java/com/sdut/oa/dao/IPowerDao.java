package com.sdut.oa.dao;

import java.util.List;

import com.sdut.oa.entity.Power;

public interface IPowerDao {
	/**
	 * 查询所有用户权限
	 */
	public List<Power> queryAll();
}
