package com.sdut.oa.dao;

import java.util.List;

import com.sdut.oa.entity.Addresslist;

/**
 * 通讯录管理 Dao层
 * @author Nuri
 *
 */
public interface IAddresslistDao {
	/**
	 * 根据姓名查询
	 */
	public Addresslist findUserByName(String name);
	/**
	 * 查看当前用户详情（id）
	 */
	public Addresslist findUserById(int id);
	/**
	 * 查询所有通讯录情况
	 */
	public List<Addresslist> queryAll(int startRow, int pageSize);
	/**
	 * 查询所有通讯数量
	 */
	public int getTotal();
	/**
	 * 添加用户通讯信息
	 */
	public boolean addAddress(Addresslist addresslist);
	/**
	 * 更新用户联系方式
	 */
	public boolean updateAddress(Addresslist addresslist);
	/**
	 * 删除用户联系方式
	 */
	public boolean deleteAddress(int id);
}
