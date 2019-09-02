package com.sdut.oa.common;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


@SuppressWarnings("unchecked")
public class UniversalDao<T extends Object> extends HibernateDaoSupport {

	/**
	 * 批量创建，只要其中有一个创建失败，则批量全部失败
	 * 
	 * @param entityList
	 *            实例
	 * @throws DbException
	 *             异常
	 */
	public void batchCreate(final List<T> entityList) throws DbException {
		try {
			getHibernateTemplate().execute(new HibernateCallback<Object>() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					Transaction transaction = session.beginTransaction();
					try {
						for (int i = 0; i < entityList.size(); i++) {
							session.save(entityList.get(i));
							session.flush();
							session.clear();
						}
						transaction.commit();
						return null;
					} catch (HibernateException e) {
						transaction.rollback();
						throw e;
					}
				}
			});
		} catch (DataAccessException e) {
			throw new DbException("批量创建到数据库失败", e);
		}
	}
	
	/**
	 * 批量创建数据 add by kelly on 2017-02-21
	 * @param list
	 */
	public void batchInsertData(List<T> list) {
		Transaction tx=null;
		try {
			long begin = System.currentTimeMillis();
			Session session = getHibernateTemplate().getSessionFactory().openSession();
			tx = session.beginTransaction();   
			int total=list.size();
			for ( int i=0; i<total; i++ ) {
			    session.save(list.get(i));
			    if(i%30==0){ //每30条刷新一下，防止缓存，内存溢出
			    	session.flush();
			    	session.clear();
			    }
			}   
			tx.commit(); 
			session.close();
			long end = System.currentTimeMillis();
			logger.info("批量创建数据用时："+(end-begin)/1000.0+"s,共创建"+total+"条");
		} catch (HibernateException e) {
			tx.rollback();
			logger.error("批量创建数据异常", e);
		}
	}

	/**
	 * 向数据库添加一条对应于一个业务对象实例的记录
	 * 
	 * @param entity
	 *            业务对象实例
	 * @throws DbException
	 *             当添加记录失败时抛出异常
	 */
	public boolean create(T entity) throws DbException {
		try {
			getHibernateTemplate().save(entity);
			return true;
		} catch (DataAccessException e) {
			throw new DbException("保存 " + entity.getClass().getName()
					+ " 实例到数据库失败", e);
		}
	}
	
	/**
	 * 向数据库添加一条对应于一个业务对象实例的记录,成功返回ID，失败返回null.
	 * 
	 * @param entity
	 *            业务对象实例
	 * @throws DbException
	 *             当添加记录失败时抛出异常
	 */
	public long createResId(T entity) throws DbException {
		try {
			long id= (Long) getHibernateTemplate().save(entity);
			return id;
		} catch (DataAccessException e) {
			throw new DbException("保存 " + entity.getClass().getName()
					+ " 实例到数据库失败", e);
		}
	}
	

	/**
	 * 向数据库更新一条对应于一个业务对象实例的记录
	 * 
	 * @param entity
	 *            业务对象实例
	 * @throws DbException
	 *             当更新记录失败时抛出异常
	 */
	public void update(T entity) throws DbException {
		try {
			getHibernateTemplate().update(entity);
		} catch (DataAccessException e) {
			throw new DbException("更新 " + entity.getClass().getName()
					+ " 实例到数据库失败", e);
		}
	}
	/**
	 * 
	 * TODO 通过hql更新数据库记录
	 * @param hql
	 * @return 更新成功返回 true 更新无记录或者失败返回 false 
	 * @throws DbException
	 */
	public boolean updateByHql(final String hql) throws DbException
	{
		boolean reflag=false;
		try
		{
			reflag=(Boolean) getHibernateTemplate().execute(new HibernateCallback<Object>() {
				public Object doInHibernate(Session session)
						throws HibernateException {
//					session.beginTransaction(); alter by kelly on 20170512防止事务提交，统一由spring管理事务
					Query query=session.createQuery(hql);
					int ret=query.executeUpdate();
//					session.getTransaction().commit();
					if(ret>0){
						return true;
					}else
					{
						return false;
					}
				}
			});
		}catch (DataAccessException e) {
			throw new DbException("执行更新 " + hql
					+ " 语句失败", e);
		}
		return reflag;
	}
	

	/**
	 * 向数据库更新或添加一条对应于一个业务对象实例的记录
	 * 
	 * @param entity
	 *            业务对象实例
	 * @throws DbException
	 *             当更新记录失败时抛出异常
	 */
	public T saveOrUpdate(T entity) throws DbException {
		try {
			getHibernateTemplate().saveOrUpdate(entity);
		} catch (DataAccessException e) {
			throw new DbException("更新 " + entity.getClass().getName()
					+ " 实例到数据库失败", e);
		}
		return entity;
	}

	/**
	 * 从数据库删除一条对应于一个业务对象的记录
	 * 
	 * @param entity
	 *            业务对象实例
	 * @throws DbException
	 *             当删除记录失败时抛出异常
	 */
	public void delete(T entity) throws DbException {
		try {
			getHibernateTemplate().delete(entity);
		} catch (DataAccessException e) {
			throw new DbException("从数据库删除 " + entity.getClass().getName()
					+ " 实例失败", e);
		}
	}

	/**
	 * 从数据库删除所有对应于一个业务对象的记录
	 * 
	 * @param clazz
	 *            指定类型的业务对象
	 * @throws DbException
	 *             当删除记录失败时抛出异常
	 */
	public void deleteAll(Class<T> clazz) throws DbException {
		try {
			List<T> result = getHibernateTemplate().loadAll(clazz);
			getHibernateTemplate().deleteAll(result);
		} catch (DataAccessException e) {
			throw new DbException("从数据库删除 " + clazz.getName() + " 的所有记录失败", e);
		}
	}

	/**
	 * 从数据库删除集合
	 * 
	 * @param entities
	 *            数据库实体集合
	 * @throws DbException
	 *             当删除记录失败时抛出异常
	 */
	public void deleteAll(Collection<T> entities) throws DbException {
		try {
			getHibernateTemplate().deleteAll(entities);
		} catch (DataAccessException e) {
			throw new DbException("从数据库删除集合错误", e);
		}
	}

	/**
	 * 根据查询语句查询数据库并返回查询结果所包含的业务对象集合。
	 * 
	 * @param hql
	 *            指定查询语句
	 * @return 返回查询结果包含的业务对象集合
	 * @throws DbException
	 *             当查询失败时抛出异常
	 */
	public List<?> findByHql(String hql) throws DbException {
		try {
			return getHibernateTemplate().find(hql);
		} catch (DataAccessException e) {
			throw new DbException("执行查询 " + hql + " 失败", e);
		}
	}

	/**
	 * 根据查询语句查询数据库并返回查询结果所包含的业务对象集合(按分页形式)。
	 * 
	 * @param hql
	 *            指定的HQL查询语句
	 * @param startRow
	 *            开始行数(不包括开始行,从开始行数的下一行开始)
	 * @param pageSize
	 *            页长度
	 * @return 返回查询结果包含的DocSet对象集合
	 * @throws DbException
	 *             当查询失败时抛出异常
	 */
	public PaginationSupport findbyPage(final String hql, final int startRow,
			final int pageSize) throws DbException {
		try {
			PaginationSupport paginationSupport = (PaginationSupport)getHibernateTemplate().execute(
					new HibernateCallback<Object>() {
						public Object doInHibernate(Session session)
								throws HibernateException {
							Query query = session.createQuery(hql);
							int totalCount=query.list().size();
							query.setFirstResult(startRow);
							query.setMaxResults(pageSize);
							List items=query.list();
							PaginationSupport ps=new PaginationSupport(items, totalCount, pageSize, startRow);
							return ps;
						}
					});
			return paginationSupport;
		} catch (DataAccessException e) {
			throw new DbException("执行Sql失败 Sql=" + hql, e);
		}
	}
	
	/**
	 * 通过sql语句分页 add by kelly on 2017-03-24
	 * @param sql
	 * @param startRow
	 * @param pageSize
	 * @return
	 * @throws DbException
	 */
	public PaginationSupport executeSqlbyPage(final String sql, final int startRow, final int pageSize) throws DbException {
		try {
			PaginationSupport paginationSupport = (PaginationSupport) getHibernateTemplate()
					.execute(new HibernateCallback<Object>() {
						public Object doInHibernate(Session session) throws HibernateException {
							Query query = session.createSQLQuery(sql);
							int totalCount=query.list().size();
							query.setFirstResult(startRow);
							query.setMaxResults(pageSize);
							List items=query.list();
							PaginationSupport ps=new PaginationSupport(items, totalCount, pageSize, startRow);
							return ps;
						}
					});
			return paginationSupport;
		} catch (DataAccessException e) {
			throw new DbException("执行Sql失败 Sql=" + sql, e);
		}
	}

	
	/**
	 * 根据已定义的查询语句查询数据库并返回查询结果所包含的业务对象集合。
	 * 
	 * @param queryName
	 *            已定义查询语句的名称
	 * @return 返回查询结果包含的业务对象集合
	 * @throws DbException
	 *             当查询失败时抛出异常
	 */
	public List<?> findByNamedQuery(String queryName) throws DbException {
		try {
			return getHibernateTemplate().findByNamedQuery(queryName);
		} catch (DataAccessException e) {
			throw new DbException("执行命名为 " + queryName + " 的查询失败", e);
		}
	}

	/**
	 * 根据已定义的带一个参数的查询语句查询数据库并返回查询结果所包含的业务对象集合。
	 * 
	 * @param queryName
	 *            已定义查询语句的名称
	 * @param param
	 *            指定的参数
	 * @return 返回查询结果包含的业务对象集合
	 * @throws DbException
	 *             当查询失败时抛出异常
	 */
	public List<?> findByNamedQuery(String queryName, Object param)
			throws DbException {
		try {
			return getHibernateTemplate().findByNamedQuery(queryName, param);
		} catch (DataAccessException e) {
			throw new DbException("执行参数为 " + param + " 命名为 " + queryName
					+ " 的查询失败", e);
		}
	}

	/**
	 * 根据已定义的带多个参数的查询语句查询数据库并返回查询结果所包含的业务对象集合。
	 * 
	 * @param queryName
	 *            已定义查询语句的名称
	 * @param params
	 *            指定的参数数组
	 * @return 返回查询结果包含的业务对象集合
	 * @throws DbException
	 *             当查询失败时抛出异常
	 */
	public List<?> findByNamedQuery(String queryName, Object[] params)
			throws DbException {
		try {
			return getHibernateTemplate().findByNamedQuery(queryName, params);
		} catch (DataAccessException e) {
			StringBuffer paramString = new StringBuffer("");
			for (int i = 0; i < params.length; i++) {
				paramString.append(params[i]);
				paramString.append(" ");
			}
			throw new DbException("执行参数为 " + paramString + "命名为 " + queryName
					+ "的查询失败", e);
		}
	}

	/**
	 * 执行本地查询Sql语句
	 * 
	 * @param sql
	 *            本地Sql语句
	 * @return 运行结果
	 * @throws DbException
	 *             当执行本地Sql语句失败时抛出异常
	 */
	public List<?> findBySql(final String sql) throws DbException {
		try {
			return (List<?>) getHibernateTemplate().execute(
					new HibernateCallback<Object>() {
						public Object doInHibernate(Session session)
								throws HibernateException {
							Query query = session.createSQLQuery(sql);
							return query.list();
						}
					});
		} catch (DataAccessException e) {
			throw new DbException("执行本地Sql失败 Sql=" + sql, e);
		}
	}

	/**
	 * 根据条件查询
	 * 
	 * @param detachedCriteria
	 *            离线查询条件,可使用DetachedCriteria.forEntityName()等方法初始化，
	 *            与Criteria拼装条件方式一样
	 * @return 查询得到的结果List
	 * @throws DbException
	 *             异常
	 */
	public List<?> findByDetachedCriteria (
			final DetachedCriteria detachedCriteria) throws DbException {
		try {
			return (List<?>) getHibernateTemplate().execute(
					new HibernateCallback<Object>() {
						@Override
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							Criteria criteria = detachedCriteria
									.getExecutableCriteria(session);
							return criteria.list();
						}
					});
		} catch (Exception e) {
			throw new DbException("根据criteria进行查询异常" + e.getMessage());
		}
	}

	/****
	 * 根据动态传入的查询条件获取记录的总数
	 * 
	 * @param detachedCriteria
	 * @return
	 */

	public int getCountByCriteria(final DetachedCriteria detachedCriteria) throws DbException  {
		try{
		Integer count = (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = detachedCriteria
								.getExecutableCriteria(session);
						return criteria.setProjection(Projections.count("id"))
								.uniqueResult();
					}
				});
		return count.intValue();
		}catch(Exception e){
			throw new DbException(e);
		}
	}

	/***
	 * 根据动态条件分页查询,默认每页30条
	 * 
	 * @param detachedCriteria
	 * @return
	 */
	public PaginationSupport findPageByCriteria(
			final DetachedCriteria detachedCriteria) throws DbException {
		try{
		return findPageByCriteria(detachedCriteria, PaginationSupport.PAGESIZE,
				0);
		}catch(Exception e){
			throw new DbException(e);
		}
	}

	/***
	 * 根据动态传入的条件分页查询
	 * 
	 * @param detachedCriteria
	 * @param startIndex
	 * @return
	 */
	public PaginationSupport findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int startIndex) throws DbException {
		try{
		return findPageByCriteria(detachedCriteria, PaginationSupport.PAGESIZE,
				startIndex);
		}
		catch(Exception e){
			throw new DbException(e);
		}
	}

	/***
	 * 根据动态传入的条件 分页查询
	 * 
	 * @param detachedCriteria
	 * @param pageSize
	 * @param startIndex
	 * @return
	 */
	public PaginationSupport findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int pageSize,
			final int startIndex)throws DbException  {
		try{
		return (PaginationSupport) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = detachedCriteria
								.getExecutableCriteria(session);
						int totalCount = ((Integer) criteria.setProjection(
								Projections.rowCount()).uniqueResult())
								.intValue();
						criteria.setProjection(null);
						List items = criteria.setFirstResult(startIndex)
								.setMaxResults(pageSize).list();
						PaginationSupport ps = new PaginationSupport(items,
								totalCount, pageSize, startIndex);
						return ps;
					}
				});
	
	}catch(Exception e){
		throw new DbException(e);
	}
	}
     /*
      * 根据属性排序的分页
      */
	public PaginationSupport findPageOrderByCriteria(final DetachedCriteria detachedCriteria,final Order order,final int pageSize,
			final int startIndex)throws DbException  {
		try{
			return (PaginationSupport) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException {
							Criteria criteria = detachedCriteria
									.getExecutableCriteria(session);
							int totalCount = ((Integer) criteria.setProjection(
									Projections.count("id")).uniqueResult())
									.intValue();
							criteria.setProjection(null);
							criteria.addOrder(order);
							List items = criteria.setFirstResult(startIndex)
									.setMaxResults(pageSize).list();
							PaginationSupport ps = new PaginationSupport(items,
									totalCount, pageSize, startIndex);
							return ps;
						}
					});
		
		}catch(Exception e){
			throw new DbException(e);
		}
		}
    /*
     * 分页统计记录数通过count(id)的方式
     */
	public PaginationSupport findPageByCriteriaUnionId(
			final DetachedCriteria detachedCriteria, final int pageSize,
			final int startIndex) throws DbException {
		try {
			return (PaginationSupport) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException {
							Criteria criteria = detachedCriteria
									.getExecutableCriteria(session);
							int totalCount = ((Integer) criteria.setProjection(
									Projections.count("id")).uniqueResult())
									.intValue();
							criteria.setProjection(null);
							List items = criteria.setFirstResult(startIndex)
									.setMaxResults(pageSize).list();
							PaginationSupport ps = new PaginationSupport(items,
									totalCount, pageSize, startIndex);
							return ps;
						}
					});
		} catch (Exception e) {
			throw new DbException(e);
		}
	}
	
	/**
	 * 执行update sql语句
	 * @param sql
	 * @return
	 * @throws DbException
	 */
	public Boolean updateBySql(final String sql) throws DbException {
		try {
			return (Boolean) getHibernateTemplate().execute(
					new HibernateCallback<Object>() {
						public Object doInHibernate(Session session)
								throws HibernateException {
							Query query = session.createSQLQuery(sql);
							return query.executeUpdate() > 0;
						}
					});
		} catch (DataAccessException e) {
			throw new DbException("执行本地Sql失败 Sql=" + sql, e);
		}
	}
}