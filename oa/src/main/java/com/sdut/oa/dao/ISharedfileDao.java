package com.sdut.oa.dao;

import java.util.List;

import com.sdut.oa.entity.Sharedfile;

public interface ISharedfileDao {
	/**
	 * 文件列表查看
	 */
	public List<Sharedfile> findAllFiles(int startRow,int pageSize);
	/**
	 * 查询列表数量
	 */
	public int getTotal();
	/**
	 * 保存文件信息
	 */
	public boolean saveFile(Sharedfile sharedfile);
	/**
	 * 根据文件id查询文件的所有信息
	 */
	public Sharedfile findById(int id);
}
