package com.sdut.oa.dao.impl;
/**
 * 考勤 管理 Dao
 */
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Component;

import com.sdut.oa.common.Dateutil;
import com.sdut.oa.common.UniversalDao;
import com.sdut.oa.dao.IAttendanceDao;
import com.sdut.oa.entity.Attendance;
@Component
public class AttendanceDaoImpl extends UniversalDao implements IAttendanceDao {
	
	private Logger logger = Logger.getLogger(AttendanceDaoImpl.class);
	
	/**
	 * 查询考勤表（根据用户id,年份，月份）
	 */
	@Override
	public List<Attendance> queryAttendance(int uid,int year,int month) {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		
		String hql = " from Attendance a where a.uid=? and a.year=? and a.month=? ";
		
		logger.debug("Dao层查询考勤，uid："+uid+"年份："+year+"月份："+month);
		List<Attendance> list = this.getHibernateTemplate().find(hql,uid,year,month);
		logger.debug("Dao层查询考勤数据"+list.size());
		return list;
	}
	
	/**
	 * 更新考勤表的加班数据(需要更新加班天数，实际出勤天数)
	 */
	@Override
	public boolean updateOvertime(int id,double overtimedays,double dutydays,double requestime) {
		boolean flag = false;
		//实际出勤天数
		double actualdays=dutydays+overtimedays-requestime;
		logger.debug("Dao层实际出勤天数："+actualdays);
		//跟新内容
		logger.debug("更新表的id"+id);
		try {
			Session session = this.getHibernateTemplate().getSessionFactory().openSession();
			String hql = "update Attendance a set a.overtimedays=?,a.actualdays=? where id=?";
			Query query = session.createQuery(hql);
			
			query.setDouble(0, overtimedays);
			logger.debug("出勤表更新Dao层获取加班天数"+overtimedays);
			query.setDouble(1, actualdays);
			logger.debug("出勤表更新Dao层实际出勤天数："+actualdays);
			query.setInteger(2, id);
			logger.debug("出勤表更新Dao层更新id"+id);
			int i = query.executeUpdate();
			logger.debug("执行更新："+i);
			if (i==1) {
				flag=true;
			}
		} catch (Exception e) {
			logger.warn("Dao层更新出勤表异常"+e);
		}
		return flag;
	}
	
	/**
	 * 创建出勤表
	 */
	@Override
	public boolean crateAttendence(int uid, int year, int month,String power) {
		boolean flag=false;
		try {
			Dateutil dateutil = new Dateutil();
			//获取当前月应出勤的天数
			int days = dateutil.listUnWeekend(year, month);
			//应出勤天数
			double dutydays=days;
			//实际出勤天数
			double actualdays = days;
			Attendance attendance = new Attendance(uid, dutydays, actualdays, year, month, power);
			flag = this.create(attendance);
			logger.debug("dao层添加考勤表标识："+flag);
		} catch (Exception e) {
			logger.warn("Dao层添加考勤表异常："+e);
		}
		return flag;
	}
	
	/**
	 * 更新请假天数
	 */
	@Override
	public boolean updateRequest(int id,double requesttime,double dutydays,double overtime) {
		boolean flag = false;
		//实际出勤天数
		double actualdays=dutydays-requesttime+overtime;
		logger.debug("Dao层实际出勤天数："+actualdays);
		//跟新内容
		logger.debug("更新表的id"+id);
		try {
			Session session = this.getHibernateTemplate().getSessionFactory().openSession();
			String hql = "update Attendance a set a.requestdays=?,a.actualdays=? where id=?";
			Query query = session.createQuery(hql);
			
			query.setDouble(0, requesttime);
			logger.debug("出勤表更新Dao层获取请假天数"+requesttime);
			query.setDouble(1, actualdays);
			logger.debug("出勤表更新Dao层实际出勤天数："+actualdays);
			query.setInteger(2, id);
			logger.debug("出勤表更新Dao层更新id"+id);
			int i = query.executeUpdate();
			logger.debug("执行更新："+i);
			if (i==1) {
				flag=true;
			}
		} catch (Exception e) {
			logger.warn("Dao层更新出勤表异常"+e);
		}
		return flag;
	}
	
}
