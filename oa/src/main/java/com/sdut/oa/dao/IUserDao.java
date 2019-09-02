package com.sdut.oa.dao;

import java.util.List;

import com.sdut.oa.entity.User;

/**
 * 用户 Dao层
 * @author Nuri
 *
 */
public interface IUserDao {
	/**
	 * 用户登陆
	 */
	public User login(User user);
	/**
	 * 根据用户id查询
	 */
	public User findbyId(int id);
	/**
	 * 修改用户密码
	 */
	public boolean updatePwd(int id,String newPwd);
	/**
	 * 查询所有用户信息
	 */
	public List<User> queryAll(int startRow, int pageSize);
	/**
	 * 查询所有用户数量
	 */
	public int getTotal();
	/**
	 * 添加用户
	 */
	public boolean addUser(User user);
	/**
	 * 更新用户信息
	 */
	public boolean updateUser(User user);
	/**
	 * 用户删除
	 */
	public boolean delete(User user);
}

