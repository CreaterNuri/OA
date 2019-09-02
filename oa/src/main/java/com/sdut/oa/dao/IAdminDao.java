package com.sdut.oa.dao;

import com.sdut.oa.entity.Admin;

public interface IAdminDao {
	/**
	 * 管理员登陆
	 */
	public Admin login(Admin admin);
}
