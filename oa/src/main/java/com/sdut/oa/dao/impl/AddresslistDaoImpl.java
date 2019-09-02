package com.sdut.oa.dao.impl;
/**
 * 通讯录管理 Dao
 */
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Component;

import com.sdut.oa.common.UniversalDao;
import com.sdut.oa.dao.IAddresslistDao;
import com.sdut.oa.entity.Addresslist;
@Component
public class AddresslistDaoImpl extends UniversalDao implements IAddresslistDao {
	private  Logger logger =Logger.getLogger(AddresslistDaoImpl.class);
	
	/**
	 * 根据姓名查询
	 */
	@Override
	public Addresslist findUserByName(String name) {
		logger.debug("Dao层姓名查询开始");
		List< Addresslist> list = new ArrayList<Addresslist>();
		
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		String hql = "from Addresslist a where a.name = ?";
		
		logger.debug("Dao层执行名称查询方法");
		Query query = session.createQuery(hql);
		//设置查询值
		query.setString(0, name);
		
		logger.debug("设置查询值："+name);
		list = query.list();
		logger.debug("Dao层根据姓名查询的数据"+list);
		
		return (Addresslist) list.get(0);
	}

	/**
	 * 根据id 查询
	 */
	@Override
	public Addresslist findUserById(int id) {
		logger.debug("Dao层根据id查询通讯录："+id);
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Addresslist addresslist = (Addresslist) session.get(Addresslist.class, id);
		logger.debug("根据id查询通讯对象："+addresslist);
		return addresslist;
	}
	
	/**
	 * 查询所有通讯录信息
	 */
	@Override
	public List<Addresslist> queryAll(int startRow, int pageSize) {
		logger.debug("查询所有公告，开始条数："+startRow+"页面显示条数："+pageSize);
		String hql="from Addresslist";
		List<Addresslist> list = new ArrayList<Addresslist>();
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		
		list= query.list();
		logger.debug("公告查询Dao层获查询到的数据"+list);
		return list;
	}
	
	/**
	 * 所有通讯记录数量
	 */
	@Override
	public int getTotal() {
		logger.debug("用户通讯记录查询数量Dao层查询开始");
		String hql = "select count(*) from Addresslist";
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		int size = ((Long) query.iterate().next()).intValue();
		logger.debug("得到查询的总条数"+size);
		return size;
	}
	
	
	/**
	 * 添加用户联系方式
	 */
	@Override
	public boolean addAddress(Addresslist addresslist) {
		boolean flag=false;
		try {
			flag=this.create(addresslist);
			logger.debug("Dao层添加用户联系方式标识："+flag);
		} catch (Exception e) {
			logger.warn("Dao层添加用户联系方式异常："+e);
		}
		return flag;
	}
	
	/**
	 * 更新用户联系方式
	 */
	@Override
	public boolean updateAddress(Addresslist addresslist) {
		logger.debug("用户更新联系方式Dao层开始");
		boolean flag=false;
		try {
			logger.debug("执行更新操作开始");
			this.update(addresslist);
			flag=true;
			logger.debug("执行更新操作标志："+flag);
		} catch (Exception e) {
			logger.warn("Dao层更新用户联系方式操作异常:"+e);
		}
		return flag;
	}

	/**
	 * 删除用户联系方式
	 */
	@Override
	public boolean deleteAddress(int id) {
		logger.debug("用户联系方式管理删除Dao层开始");
		String hql="delete from Addresslist as address where address.id=?";
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query=session.createQuery(hql);
		query.setInteger(0,id);
		int i = query.executeUpdate();
		logger.debug("执行删除行数："+i);
		if(i==1){
			return true;
		}
		return false;
	}

}
