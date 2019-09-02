package com.sdut.oa.dao.impl;
/**
 * 文件管理 Dao
 */
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Component;

import com.sdut.oa.common.UniversalDao;
import com.sdut.oa.dao.ISharedfileDao;
import com.sdut.oa.entity.Sharedfile;
@Component
public class SharedfileDaoImpl extends UniversalDao implements ISharedfileDao {
	
	private Logger logger = Logger.getLogger(SharedfileDaoImpl.class);
	
	/**
	 * 查询共享文件列表
	 */
	@Override
	public List<Sharedfile> findAllFiles(int startRow,int pageSize) {
		logger.debug("查询所有文件列表，开始条数："+startRow+"页面显示条数："+pageSize);
		String hql="from Sharedfile";
		List<Sharedfile> list = new ArrayList<Sharedfile>();
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		
		list= query.list();
		logger.debug("文件共享查询Dao层获查询到的数据"+list.size());
		return list;
	}
	
	/**
	 * 保存文件信息
	 */
	@Override
	public boolean saveFile(Sharedfile sharedfile) {
		logger.debug("文件信息添加Dao层开始");
		boolean flag=false;
		try {
			flag = this.create(sharedfile);
			logger.debug("公告添加标识："+flag);
		} catch (Exception e) {
			logger.error("公告添加异常："+e);
		}
		return flag;
	}
	
	/**
	 * 查询列表数量
	 */
	@Override
	public int getTotal() {
		logger.debug("文件管理Dao层查询文件条数开始");
		String hql = "select count(*) from Sharedfile";
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		int size = ((Long) query.iterate().next()).intValue();
		logger.debug("得到查询的总条数"+size);
		return size;
	}
	
	/**
	 * 根据id查询项目信息
	 */
	@Override
	public Sharedfile findById(int id) {
		logger.debug("Dao层根据id查询文件信息");
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		
		Sharedfile sharedfile = (Sharedfile) session.get(Sharedfile.class, id);
		logger.debug("Dao层根据id查询项目"+sharedfile.toString());
		return sharedfile;
	}

}
