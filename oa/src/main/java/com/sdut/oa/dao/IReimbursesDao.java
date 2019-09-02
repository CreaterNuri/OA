package com.sdut.oa.dao;

import java.util.List;

import com.sdut.oa.entity.Reimburses;

public interface IReimbursesDao {
	/**
	 * 查询所有的报销类型
	 */
	public List<Reimburses> findAll();
}
