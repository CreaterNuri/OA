package com.sdut.oa.service.impl;
/**
 * 用户信息管理
 */
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.sdut.oa.dao.IAccountDao;
import com.sdut.oa.dao.ILeavetimeDao;
import com.sdut.oa.dao.IOvertimeDao;
import com.sdut.oa.dao.IUsermessageDao;
import com.sdut.oa.entity.Usermessage;
import com.sdut.oa.service.IUsermessageService;
@Service
public class UsermessageServiceImpl implements IUsermessageService {
	private Logger logger = Logger.getLogger(UsermessageServiceImpl.class);
	
	@Autowired
	private IUsermessageDao usermessageDao;
	@Autowired	
	private IAccountDao accountDao;//报销
	@Autowired
	private IOvertimeDao overtimeDao;//加班
	@Autowired
	private ILeavetimeDao leavetimeDao;//请假
	/**
	 * 查询用户申请信息
	 */
	@Override
	public int getTotal(String approver) {
		int total = usermessageDao.getTotal(approver);
		logger.debug("service层查询用户信息数量："+total);
		return total;
	}
	
	/**
	 * 查询所有用户信息
	 */
	@Override
	public List<Usermessage> queryAll(int startRow, int pageSize, String approver) {
		List<Usermessage> list = usermessageDao.queryAll(startRow, pageSize, approver);
		logger.debug("Service层查询所有信息数量："+list);
		return list;
	}
	
	/**
	 * 处理信息
	 */
	@Override
	public boolean managerMsg(Usermessage usermessage) {
		boolean updateAll=false;
		boolean updateAccount=false;
		boolean updateOvertime=false;
		boolean updateleavetime=false;
		int aid = usermessage.getAid();//报销表id
		int oid = usermessage.getOid();//加班表id
		int lid = usermessage.getLid();//请假表id
		String state="已审批";
		//执行报销更新
		if(aid >0 && oid==0 && lid==0){
			updateAccount = accountDao.updateState(aid, state);
		}else if(aid ==0 && oid>0 && lid==0) {
			updateOvertime = overtimeDao.updateState(oid, state);
		}else if(aid ==0 && oid==0 && lid>0){
			updateleavetime = leavetimeDao.updateState(lid, state);
		}
		boolean flag = usermessageDao.managerMsg(usermessage);
		logger.debug("信息表更新标识："+flag);
		if((flag&&updateAccount) || (flag&&updateOvertime) || (flag&&updateleavetime)){
			updateAll=true;
		}
		logger.debug("Service层处理信息标识："+updateAll);
		return updateAll;
	}
	

}
