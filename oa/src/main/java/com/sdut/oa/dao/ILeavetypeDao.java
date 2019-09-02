package com.sdut.oa.dao;

import java.util.List;

import com.sdut.oa.entity.Leavetype;

public interface ILeavetypeDao {
	/**
	 * 查询请假类型
	 */
	public List<Leavetype> queryAll();
}
