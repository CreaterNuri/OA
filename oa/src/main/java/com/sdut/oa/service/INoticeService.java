package com.sdut.oa.service;

import java.util.List;

import com.sdut.oa.entity.Notice;

/**
 * 公告管理 service interface
 * @author Nuri
 *
 */
public interface INoticeService {
	/**
	 * 公告查看(分页查询)
	 */
	public List<Notice> findAll(int startRow,int pageSize);
	/**
	 * 查询公告总条数
	 */
	public int getTotal();
	/**
	 * 增加公告
	 */
	public boolean add(Notice notice);
	/**
	 * 删除公告
	 */
	public boolean delNotice(Notice notice);
	/**
	 * 修改公告
	 */
	public boolean updNotice(Notice notice);
}
