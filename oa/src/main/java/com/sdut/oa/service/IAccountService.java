package com.sdut.oa.service;

import java.util.List;

import com.sdut.oa.entity.Account;

public interface IAccountService {
	/**
	 * 报销单查询（分页）
	 */
	 public List<Account> findAll(int startRow, int pageSize,String username);
	 /**
	  * 报销单添加
	  */
	 public boolean addAccount(Account account);
	 /**
	   * 根据当前登录用户查询报销单总条数
	 */
	 public int getTotal(String username);
	 /**
	  * 更新报销单
	  */
	 public boolean updAccount(Account account);
}
