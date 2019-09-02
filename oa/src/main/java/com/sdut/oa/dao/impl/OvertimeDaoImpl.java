package com.sdut.oa.dao.impl;
/**
 * 加班管理 dao层
 */
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Component;

import com.sdut.oa.common.UniversalDao;
import com.sdut.oa.dao.IOvertimeDao;
import com.sdut.oa.entity.Overtime;
@Component
public class OvertimeDaoImpl extends UniversalDao implements IOvertimeDao {
	
	private Logger logger=Logger.getLogger(OvertimeDaoImpl.class);
	
	/**
	 * 添加加班数据
	 */
	@Override
	public boolean addOvertime(Overtime overtime) {
		logger.debug("加班添加Dao层开始");
		boolean flag=false;
		try {
			flag = this.create(overtime);
			logger.debug("加班添加标识："+flag);
		} catch (Exception e) {
			logger.error("加班添加异常："+e);
		}
		return flag;
	}
	
	/**
	 * 查询所有加班信息
	 */
	@Override
	public List<Overtime> queryAll(int startRow, int pageSize,int uid) {
		String hql = "from Overtime ovtime where ovtime.uid = ?";
		List<Overtime> list = new ArrayList<Overtime>();
		logger.debug("Dao层分页查询数据，开始条数："+startRow+",页面条数："+pageSize+"用户id："+uid);
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		
		query.setInteger(0, uid);
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		
		list= query.list();
		logger.debug("Dao层获查询到请假的数据"+list);
		return list;
	}
	
	/**
	 * 加班信息总条数
	 */
	@Override
	public int total(int uid) {
		logger.debug("加班管理Dao层查询公告条数开始");
		String hql = "select count(*) from Overtime ovtime where ovtime.uid = ?";
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		query.setInteger(0, uid);
		logger.debug("用户id:"+uid);
		int size = ((Long) query.iterate().next()).intValue();
		logger.debug("得到查询的加班信息总条数"+size);
		return size;
	}
	
	/**
	 * 根据uid,year,month查询
	 */
	@Override
	public List<Overtime> queryById(int uid, int year, int month) {
		List<Overtime> list=new ArrayList<Overtime>();
		String hql = "from Overtime ovtime where ovtime.uid = ?,ovtime.year=? and ovtime.month=?";
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		logger.debug("用户id:"+uid+"年份："+year+"月份："+month);
		query.setInteger(0, uid);
		query.setInteger(1, year);
		query.setInteger(2, month);
		list = query.list();
		return list;
	}
	
	/**
	 * 根据id，更新加班表状态
	 */
	@Override
	public boolean updateState(int id, String state) {
		logger.debug("报销单管理Dao层更新加班表状态开始");
		String hql = "update Overtime ovtime set ovtime.state=? where ovtime.id=? ";
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		query.setString(0, state);
		query.setInteger(1, id);
		logger.debug("更新状态："+state+"id:"+id);
		int i = query.executeUpdate();
		logger.debug("更新加班表的状态，更新条数："+i);
		if(i==1){
			return true;
		}
		return false;
	}
	
}
