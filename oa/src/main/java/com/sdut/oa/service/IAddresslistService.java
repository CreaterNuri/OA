package com.sdut.oa.service;

import java.util.List;

import com.sdut.oa.entity.Addresslist;

/**
 * 通讯录管理 service
 * @author Nuri
 *
 */
public interface IAddresslistService {
	/**
	 * 根据姓名查询
	 */
	public Addresslist findByName(String name);
	/**
	 * 根据id查询
	 */
	public Addresslist findById(int id);	
	/**
	 * 查询所有通讯信息
	 */
	public List<Addresslist> queryAll(int startRow, int pageSize);
	/**
	 * 查询所有通讯记录数量
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