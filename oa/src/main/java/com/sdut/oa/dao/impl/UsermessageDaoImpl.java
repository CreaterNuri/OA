package com.sdut.oa.dao.impl;
/**
 * 用户信息处理
 */
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Component;

import com.sdut.oa.common.UniversalDao;
import com.sdut.oa.dao.IUsermessageDao;
import com.sdut.oa.entity.Usermessage;
@Component
public class UsermessageDaoImpl extends UniversalDao implements IUsermessageDao {
	private Logger logger = Logger.getLogger(UsermessageDaoImpl.class);
	
	/**
	 * 查询用户信息数量
	 */
	@Override
	public int getTotal(String approver) {
		logger.debug("用户信息管理Dao层查询数量开始");
		String hql = "select count(*) from Usermessage usg where usg.approver=? and usg.state=?";
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(hql);

		query.setString(0, approver);
		query.setInteger(1, 0);
		logger.debug("设置查询条件："+approver+"状态："+"0");;
		
		int size = ((Long) query.iterate().next()).intValue();
		logger.debug("得到查询的总条数"+size);
		return size;
	}
	
	/**
	 * 查询所有的信息
	 */
	@Override
	public List<Usermessage> queryAll(int startRow, int pageSize,String approver) {
		String hql = "from Usermessage usg where usg.approver = ? and usg.state=?";
		List<Usermessage> list = new ArrayList<Usermessage>();
		logger.debug("Dao层分页查询数据，开始条数："+startRow+",页面条数："+pageSize+"用户名："+approver);
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		
		query.setString(0, approver);
		query.setInteger(1, 0);
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		
		list= query.list();
		logger.debug("Dao层获查询到的数据"+list);
		return list;
	}
	
	/**
	 * 处理信息 （添加处理意见，更改处理信息状态）
	 */
	@Override
	public boolean managerMsg(Usermessage usermessage) {
		boolean flag=false;
		try {
			Session session = this.getHibernateTemplate().getSessionFactory().openSession();
			String hql = "update Usermessage a set a.note=?,a.state=? where id=?";
			Query query = session.createQuery(hql);
			
			query.setString(0, usermessage.getNote());
			logger.debug("信息表更新Dao层获取处理意见"+usermessage.getNote());
			query.setInteger(1, usermessage.getState());
			logger.debug("信息表更新Dao层状态："+usermessage.getState());
			query.setInteger(2, usermessage.getId());
			logger.debug("信息表更新Dao层更新id"+usermessage.getId());
			int i = query.executeUpdate();
			logger.debug("执行更新："+i);
			if (i==1) {
				flag=true;
			}
		} catch (Exception e) {
			logger.warn("Dao层更新信息表异常"+e);
		}
		return flag;
	}
	
	/**
	 * 添加消息
	 */
	@Override
	public boolean addMsg(Usermessage usermessage) {
		logger.debug("信息添加Dao层开始");
		boolean flag=false;
		try {
			flag = this.create(usermessage);
			logger.debug("信息添加标识："+flag);
		} catch (Exception e) {
			logger.error("信息添加异常："+e);
		}
		return flag;
	}

}
