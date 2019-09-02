package com.sdut.oa.service;

import java.util.List;

import com.sdut.oa.entity.Leavetype;

public interface ILeavetypeService {
	/**
	 * 查询所有请假类型
	 */
	public List<Leavetype> queryAll();
}
