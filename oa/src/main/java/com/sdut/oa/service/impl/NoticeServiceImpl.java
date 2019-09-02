package com.sdut.oa.service.impl;
/**
 * 公告管理 Service Impl
 */
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdut.oa.dao.INoticeDao;
import com.sdut.oa.entity.Notice;
import com.sdut.oa.service.INoticeService;
@Service
public class NoticeServiceImpl implements INoticeService {
	
	private Logger logger = Logger.getLogger(NoticeServiceImpl.class);
	
	@Autowired
	private INoticeDao noticeDao;//注入dao
	/**
	 * 公告查询
	 */
	@Override
	public List<Notice> findAll(int startRow, int pageSize) {
		logger.debug("公告查询Service开始,stareRow"+startRow+"pageSize:"+pageSize);
		List<Notice> list = noticeDao.findAll(startRow, pageSize);
		logger.debug("查询所有公告结果："+list.get(0));
		return list;
	}
	
	/**
	 * 公告查询所有总条数
	 */
	@Override
	public int getTotal() {
		logger.debug("公告总条数查询Service开始");
		int total = noticeDao.getTotal();
		logger.debug("公告查询总条数结果："+total);
		return total;
	}
	
	/**
	 * 公告添加
	 */
	@Override
	public boolean add(Notice notice) {
		logger.debug("公告添加Service开始");
		boolean flag = noticeDao.add(notice);
		logger.debug("公告添加Service返回标识："+flag);
		return flag;
	}
	
	/**
	 * 公告删除
	 */
	@Override
	public boolean delNotice(Notice notice) {
		logger.debug("公告删除Service层开始");
		boolean flag = noticeDao.delNotice(notice);
		logger.debug("公告删除Service层标识："+flag);
		return flag;
	}
	
	/**
	 * 公告更新
	 */
	@Override
	public boolean updNotice(Notice notice) {
		logger.debug("公告更新Service层开始");
		boolean flag = noticeDao.updNotice(notice);
		logger.debug("公告更新Service标识："+flag);
		return flag;
	}

}
