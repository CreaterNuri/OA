package com.sdut.oa.dao.impl;
/**
 * 报销单管理 Dao层
 * 
 */
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Component;

import com.sdut.oa.common.UniversalDao;
import com.sdut.oa.dao.IAccountDao;
import com.sdut.oa.entity.Account;
@Component
public class AccountDaoImpl extends UniversalDao implements IAccountDao {
	
	private Logger logger = Logger.getLogger(AccountDaoImpl.class);
	
	/**
	 * 查询所有报销单
	 */
	@Override
	public List<Account> findAll(int startRow, int pageSize,String username) {
		logger.debug("查询所有报销单，开始条数："+startRow+"页面显示条数："+pageSize);
		String hql="from Account account where account.reimbursement=? order by account.date desc ";
		List<Account> list = new ArrayList<Account>();
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		
		query.setString(0, username);
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		
		list= query.list();
		logger.debug("报销单查询Dao层获查询到的数据"+list);
		return list;
	}
	
	/**
	 * 申请报销
	 */
	@Override
	public Boolean addAccount(Account account) {
		logger.debug("Dao层开始添加报销单");
		boolean flag=false;
		try {
			flag=this.create(account);
			logger.debug("报销单添加标识："+flag);
		} catch (Exception e) {
			logger.debug("Dao层添加报销单异常："+e);
		}
		return flag;
	}
	
	/**
	 *查询报销单总数量 
	 */
	@Override
	public int getTotal(String username) {
		logger.debug("报销单管理Dao层查询公告条数开始");
		String hql = "select count(*) from Account account where account.reimbursement=? ";
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		
		query.setString(0, username);
		logger.debug("更具用户名查询报销单数量："+username);
		int size = ((Long) query.iterate().next()).intValue();
		logger.debug("得到查询的总条数"+size);
		return size;
	}
	
	/**
	 * 更新报销单
	 */
	@Override
	public Boolean updAccount(Account account) {
		logger.debug("报销单更新Dao层开始");
		logger.debug("Dao层得到日期："+account.getDate());
		boolean flag=false;
		try {
			logger.debug("执行更新操作开始");
			this.update(account);
			flag=true;
			logger.debug("执行更新操作标志："+flag);
		} catch (Exception e) {
			logger.warn("Dao层更新操作异常:"+e);
		}
		return flag;
	}
	
	/**
	 * 根据报销单id修改报销单状态
	 */
	public boolean updateState(int id,String state){
		logger.debug("报销单管理Dao层更新报销单状态开始");
		String hql = "update Account account set account.state=? where account.id=? ";
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
