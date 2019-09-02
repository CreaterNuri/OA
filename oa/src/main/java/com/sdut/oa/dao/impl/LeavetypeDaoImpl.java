package com.sdut.oa.dao.impl;
import java.util.ArrayList;
/**
 * 请假类型管理
 */
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Component;

import com.sdut.oa.common.UniversalDao;
import com.sdut.oa.dao.ILeavetypeDao;
import com.sdut.oa.entity.Leavetype;
@Component
public class LeavetypeDaoImpl extends UniversalDao implements ILeavetypeDao {

	private Logger logger = Logger.getLogger(LeavetypeDaoImpl.class);
	
	/**
	 * 查询所有请假类型
	 */
	@Override
	public List<Leavetype> queryAll() {
		logger.debug("查询请假类型开始");
		List<Leavetype> list = new ArrayList<Leavetype>();
		String hql = "from Leavetype";
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		try {
			Query query = session.createQuery(hql);
			list = query.list();
		} catch (Exception e) {
			logger.error("Dao层查询所有请假类型阶段异常"+e);
		}
		return list;
	}

}
