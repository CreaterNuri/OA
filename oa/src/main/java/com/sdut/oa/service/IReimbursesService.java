package com.sdut.oa.service;

import java.util.List;

import com.sdut.oa.entity.Reimburses;

public interface IReimbursesService {
	/**
	 * 查询所有的报销类型
	 */
	public List<Reimburses> findAll();
}
