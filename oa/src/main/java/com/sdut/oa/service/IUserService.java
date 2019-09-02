package com.sdut.oa.service;

import java.util.List;

import com.sdut.oa.entity.User;

/**
 * 用户 service
 * @author Nuri
 *
 */
public interface IUserService {
	
	/**
	 * 用户登陆
	 */
	public User login(User user);
	/**
	 * 根据用户id查询
	 */
	public User findById(int id);
	/**
	 * 密码修改
	 */
	public boolean updatePwd(int id,String oldpwd,String newPwd);
	/**
	 * 查询所有用户信息
	 */
	public List<User> queryAll(int startRow, int pageSize);
	/**
	 * 查询所有用户信息数量
	 */
	public int getTotal();
	/**
	 * 添加用户
	 */
	public boolean addUser(User user);
	/**
	 * 用户更新
	 */
	public boolean update(User user);
	/**
	 * 用户删除
	 */
	public boolean deleteUser(User user);
}
