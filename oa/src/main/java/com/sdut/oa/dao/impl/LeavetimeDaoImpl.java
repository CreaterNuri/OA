package com.sdut.oa.dao.impl;
/**
 * 请假管理 Dao
 */
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import org.springframework.stereotype.Component;

import com.sdut.oa.common.UniversalDao;
import com.sdut.oa.dao.ILeavetimeDao;
import com.sdut.oa.entity.Leavetime;
@Component
public class LeavetimeDaoImpl extends UniversalDao implements ILeavetimeDao {
	
	private Logger logger=Logger.getLogger(LeavetypeDaoImpl.class);
	/**
	 * 请假单添加
	 */
	@Override
	public boolean addLeaveTime(Leavetime leavetime) {
		logger.debug("Dao层开始添加请假单");
		boolean flag=false;
		try {
			flag=this.create(leavetime);
			logger.debug("请假单添加标识："+flag);
		} catch (Exception e) {
			logger.debug("Dao层添加请假单异常："+e);
		}
		return flag;
	}
	
	/**
	 * 查询请假单
	 */
	@Override
	public List<Leavetime> queryAll(int startRow, int pageSize, int uid) {
		String hql = "from Leavetime leave where leave.uid = ?";
		List<Leavetime> list = new ArrayList<Leavetime>();
		logger.debug("Dao层分页查询数据，开始条数："+startRow+",页面条数："+pageSize+"用户id："+uid);
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		
		query.setInteger(0, uid);
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		
		list= query.list();
		logger.debug("Dao层获查询到的数据"+list);
		return list;
	}
	
	/**
	 * 根据uid查询请假单
	 */
	@Override
	public List<Leavetime> queryById(int uid) {
		String hql = "from Leavetime leave where leave.uid = ?";
		List<Leavetime> list = new ArrayList<Leavetime>();
		logger.debug("Dao层查询用户id："+uid);
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		
		query.setInteger(0, uid);
		
		list= query.list();
		logger.debug("Dao层获查询到的数据"+list);
		return list;
	}
	
	/**
	 * 根据id，更新状态
	 */
	@Override
	public boolean updateState(int id, String state) {
		logger.debug("请假Dao层更新请假单状态开始");
		String hql = "update Leavetime leave set leave.state=? where leave.id=? ";
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		query.setString(0, state);
		query.setInteger(1, id);
		logger.debug("更新状态："+state+"id:"+id);
		int i = query.executeUpdate();
		logger.debug("更新报销表的状态，更新条数："+i);
		if(i==1){
			return true;
		}
		return false;
	}

}
