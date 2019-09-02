package com.sdut.oa.dao.impl;
/**
 * 公告管理 DaoImpl
 */
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Component;

import com.sdut.oa.common.UniversalDao;
import com.sdut.oa.dao.INoticeDao;
import com.sdut.oa.entity.Notice;
@Component
public class NoticeDaoImpl extends UniversalDao implements INoticeDao {
	
	private Logger logger = Logger.getLogger(NoticeDaoImpl.class); 
	
	/**
	 * 查询所有公告
	 */
	@Override
	public List<Notice> findAll(int startRow, int pageSize) {
		logger.debug("所有用户查询，开始条数："+startRow+"页面显示条数："+pageSize);
		String hql="from Notice";
		List<Notice> list = new ArrayList<Notice>();
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		
		list= query.list();
		logger.debug("所有用户查询Dao层获查询到的数据"+list);
		return list;
	}
	
	/**
	 * 查询公告所有条数
	 */
	@Override
	public int getTotal() {
		logger.debug("公告管理Dao层查询公告条数开始");
		String hql = "select count(*) from Notice";
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		int size = ((Long) query.iterate().next()).intValue();
		logger.debug("得到查询的总条数"+size);
		return size;
	}
	
	/**
	 * 添加公告
	 */
	@Override
	public boolean add(Notice notice) {
		logger.debug("公告添加Dao层开始");
		boolean flag=false;
		try {
			flag = this.create(notice);
			logger.debug("公告添加标识："+flag);
		} catch (Exception e) {
			logger.error("公告添加异常："+e);
		}
		return flag;
	}

	/**
	 * 删除公告（根据发布人，公告id）
	 */
	@Override
	public boolean delNotice(Notice notice) {
		logger.debug("公告管理删除Dao层开始");
		boolean flag=false;
		try {
			logger.debug("执行删除操作");
			this.delete(notice);
			flag=true;
			logger.debug("删除结果标识："+flag);
		} catch (Exception e) {
			logger.warn("公告删除异常："+e);
		}
		return flag;
	}
	
	/**
	 * 更新公告
	 */
	@Override
	public boolean updNotice(Notice notice) {
		logger.debug("公告更新Dao层开始");
		boolean flag=false;
		try {
			logger.debug("执行更新操作开始");
			this.update(notice);
			flag=true;
			logger.debug("执行更新操作标志："+flag);
		} catch (Exception e) {
			logger.warn("Dao层更新操作异常:"+e);
		}
		return flag;
	}

}
