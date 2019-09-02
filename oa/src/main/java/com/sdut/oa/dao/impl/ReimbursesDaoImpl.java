package com.sdut.oa.dao.impl;
import java.util.ArrayList;
/**
 * 查询所有的报销类型 Dao
 */
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Component;

import com.sdut.oa.common.UniversalDao;
import com.sdut.oa.dao.IReimbursesDao;
import com.sdut.oa.entity.Reimburses;
@Component
public class ReimbursesDaoImpl extends UniversalDao implements IReimbursesDao {
	private Logger logger =Logger.getLogger(ReimbursesDaoImpl.class);
	
	/**
	 * 查询所有的报销类型
	 */
	@Override
	public List<Reimburses> findAll() {
		logger.debug("查询项目的所有阶段开始");
		List<Reimburses> list = new ArrayList<Reimburses>();
		String hql = "from Reimburses";
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		try {
			Query query = session.createQuery(hql);
			list = query.list();
		} catch (Exception e) {
			logger.error("Dao层查询所有报销类型阶段异常"+e);
		}
		return list;
	}
}
