package com.sdut.oa.dao;

import java.util.List;

import com.sdut.oa.entity.Usermessage;

public interface IUsermessageDao {
	/**
	 * 查询用户信息数量
	 */
	public int getTotal(String approver);
	/**
	 * 根据用户查询所有信息
	 */
	public List<Usermessage> queryAll(int startRow, int pageSize,String approver);
	/**
	 * 处理信息
	 */
	public boolean managerMsg(Usermessage usermessage);
	/**
	 * 消息添加
	 */
	public boolean addMsg(Usermessage usermessage);
}
