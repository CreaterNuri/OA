package com.sdut.oa.dao.impl;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
/**
 * 管理员 Dao层
 */
import org.springframework.stereotype.Component;

import com.sdut.oa.dao.IAdminDao;
import com.sdut.oa.entity.Admin;
import com.sdut.oa.entity.User;
@Component
public class AdminDaoImpl  extends HibernateDaoSupport implements IAdminDao {
	private Logger logger=Logger.getLogger(AdminDaoImpl.class);
	
	/**
	 * 管理员登陆
	 */
	@Override
	public Admin login(Admin admin) {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		
		String hql = " from Admin a where a.name=? and a.password=?";
		
		logger.debug("Dao层登陆，用户名："+admin.getName()+"密码："+admin.getPassword());
		
		List<Admin> list = this.getHibernateTemplate().find(hql,admin.getName(),admin.getPassword());
		logger.debug("Dao层登陆查询到管理员信息："+list);
		
		logger.debug("得到查询的管理员数量："+list.size());
		
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return  null;
	}

}
