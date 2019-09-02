package com.sdut.oa.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Component;

import com.sdut.oa.common.UniversalDao;
import com.sdut.oa.dao.IUserDao;
import com.sdut.oa.entity.Notice;
import com.sdut.oa.entity.User;
@Component
public class UserDaoImpl extends UniversalDao implements IUserDao {
	
	private Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	/**
	 * 用户登陆
	 */
	@Override
	public User login(User user) {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		
		String hql = " from User a where a.uname=? and a.upwd=? and a.state=0 ";
		
		logger.debug("Dao层登陆，用户名："+user.getUname()+"密码："+user.getUpwd());
		
		List<User> list = this.getHibernateTemplate().find(hql,user.getUname(),user.getUpwd());
		logger.debug("Dao层登陆查询到用户信息："+list);
		
		logger.debug("得到查询的用户数量："+list.size());
		
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return  null;
	}
	
	/**
	 * 根据uid获取信息
	 */
	public User findbyId(int id) {
		logger.debug("Dao层根据id查询用户信息");
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		
		User user = (User) session.get(User.class, id);
		
		return user;
		
	}
	
	/**
	 * 更改用户密码
	 */
	@Override
	public boolean updatePwd(int id, String newPwd) {
		boolean flag=false;
		try {
			Session session = this.getHibernateTemplate().getSessionFactory().openSession();
			String hql = "update User a set a.upwd=? where a.uid=?";
			Query query = session.createQuery(hql);
			
			query.setString(0, newPwd);
			logger.debug("用户表更新Dao层新密码："+newPwd);
			query.setInteger(1, id);
			logger.debug("用户表更新Dao层id："+id);
			int i = query.executeUpdate();
			logger.debug("执行更新："+i);
			if (i==1) {
				flag=true;
			}
		} catch (Exception e) {
			logger.warn("Dao层更新密码表异常"+e);
		}
		return flag;
	}
	
	/**
	 * 查询所有用户信息
	 */
	@Override
	public List<User> queryAll(int startRow, int pageSize) {
		logger.debug("查询所有公告，开始条数："+startRow+"页面显示条数："+pageSize);
		String hql="from User";
		List<User> list = new ArrayList<User>();
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		
		list= query.list();
		logger.debug("公告查询Dao层获查询到的数据"+list);
		return list;
	}
	
	
	/**
	 * 添加用户
	 */
	@Override
	public boolean addUser(User user) {
		boolean flag=false;
		try {
			flag=this.create(user);
			logger.debug("Dao层添加用户标识："+flag);
		} catch (Exception e) {
			logger.warn("Dao层添加用户异常："+e);
		}
		return flag;
	}
	
	/**
	 * 查询所有用户数量
	 */
	@Override
	public int getTotal() {
		logger.debug("用户查询数量Dao层查询开始");
		String hql = "select count(*) from User";
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		int size = ((Long) query.iterate().next()).intValue();
		logger.debug("得到查询的总条数"+size);
		return size;
	}
	
	/**
	 * 更新用户信息
	 */
	@Override
	public boolean updateUser(User user) {
		logger.debug("用户更新Dao层开始");
		boolean flag=false;
		try {
			logger.debug("执行更新操作开始");
			this.update(user);
			flag=true;
			logger.debug("执行更新操作标志："+flag);
		} catch (Exception e) {
			logger.warn("Dao层更新操作异常:"+e);
		}
		return flag;
	}
	
	/**
	 * 用户信息删除
	 */
	@Override
	public boolean delete(User user) {
			logger.debug("用户管理删除Dao层开始");
			String hql="delete from User as u where u.uid=?";
			Session session = this.getHibernateTemplate().getSessionFactory().openSession();
			Query query=session.createQuery(hql);
			query.setInteger(0,user.getUid());
			int i = query.executeUpdate();
			logger.debug("执行删除行数："+i);
			if(i==1){
				return true;
			}
			return false;
	}

}
