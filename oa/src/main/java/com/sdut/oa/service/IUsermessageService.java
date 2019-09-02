package com.sdut.oa.service;

import java.util.List;

import com.sdut.oa.entity.Usermessage;

public interface IUsermessageService {
	/**
	 * 查询当前用户信息申请
	 */
	public int getTotal(String approver);
	/**
	 * 查询用户所有信息
	 */
	public List<Usermessage> queryAll(int startRow, int pageSize,String approver);
	/**
	 * 处理信息
	 */
	public boolean managerMsg(Usermessage usermessage);
}
