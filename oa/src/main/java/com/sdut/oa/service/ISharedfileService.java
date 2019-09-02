package com.sdut.oa.service;

import java.util.List;

import com.sdut.oa.entity.Sharedfile;

public interface ISharedfileService {
	/**
	 * 文件列表查询
	 */
	public List<Sharedfile> findAllFiles(int startRow,int pageSize);
	/**
	 * 查询文件列表总条数
	 */
	public int getTotal();
	/**
	 * 文件信息添加 
	 */
	public Boolean addFile(Sharedfile sharedfile);
	/**
	 * 根据文件id查询所有文件信息
	 */
	public Sharedfile findById(int id);
}
