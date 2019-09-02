package com.sdut.oa.dao;

import java.util.List;

import com.sdut.oa.entity.Account;

public interface IAccountDao {
	/**
	 * 报销单查询（分页）
	 */
	public List<Account> findAll(int startRow,int pageSize,String username);
	/**
	 *查询总数量 
	 */
	public int getTotal(String username);
	
	/**
	 * 申请报销
	 */
	public Boolean addAccount(Account account);
	/**
	 * 更新报销单
	 */
	public Boolean updAccount(Account account);
	/**
	 * 
	 * 根据报销单id修改报销单状态
	 */
	 public boolean updateState(int id,String state);
	
	
}
