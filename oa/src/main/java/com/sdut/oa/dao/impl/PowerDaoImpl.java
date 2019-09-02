package com.sdut.oa.dao.impl;
import java.util.ArrayList;
/**
 * 用户角色查询
 */
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Component;

import com.sdut.oa.common.UniversalDao;
import com.sdut.oa.dao.IPowerDao;
import com.sdut.oa.entity.Leavetype;
import com.sdut.oa.entity.Power;
@Component
public class PowerDaoImpl extends UniversalDao implements IPowerDao {
	private Logger logger=Logger.getLogger(PowerDaoImpl.class);
	
	/**
	 * 查询所有用户角色
	 */
	@Override
	public List<Power> queryAll() {
		logger.debug("查询用户角色开始");
		List<Power> list = new ArrayList<Power>();
		String hql = "from Power";
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		try {
			Query query = session.createQuery(hql);
			list = query.list();
		} catch (Exception e) {
			logger.error("Dao层查询所有角色类型阶段异常"+e);
		}
		return list;
	}

}
