package com.sdut.oa.service.impl;
/**
 * 报销管理 Service
 */
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdut.oa.dao.IAccountDao;
import com.sdut.oa.dao.IUsermessageDao;
import com.sdut.oa.entity.Account;
import com.sdut.oa.entity.Usermessage;
import com.sdut.oa.service.IAccountService;
@Service
public class AccountServiceImpl implements IAccountService {
	
	private Logger logger = Logger.getLogger(AccountServiceImpl.class);
	@Autowired
	private IAccountDao accountDao;//报销管理
	@Autowired
	private IUsermessageDao usermessageDao;//信息管理
	
	/**
	 * 报销单查询
	 */
	@Override
	public List<Account> findAll(int startRow, int pageSize,String username) {
		logger.debug("Service层报销单查询开始");
		List<Account> list = accountDao.findAll(startRow, pageSize,username);
		logger.debug("Service层查询报销单数量："+list.size());
		return list;
	}
	
	/**
	 * 添加报销单
	 */
	@Override
	public boolean addAccount(Account account) {
		boolean flag=false;
		Usermessage usermessage = new Usermessage();
		logger.debug("service层添加报销单开始");
		Boolean addAccount = accountDao.addAccount(account);
		//得到添加数据的id,方便更新
		int aid = account.getId();
		//信息内容
		usermessage.setMessage("申请报销类型："+account.getAccounttype()+",金额："+account.getMoney()+"元");
		//状态未处理
		usermessage.setState(0);
		//申请人
		usermessage.setApplicant(account.getReimbursement());
		//审核人
		usermessage.setApprover(account.getApprover());
		//时间
		usermessage.setTime(account.getDate());
		//报销表的id
		usermessage.setAid(aid);
		boolean addMsg = usermessageDao.addMsg(usermessage);
		if(addAccount && addMsg){
			flag=true;
		}
		logger.debug("service层添加报销单标识："+flag);
		return flag;
	}
	
	/**
	 * 查询报销单总数量
	 */
	@Override
	public int getTotal(String username) {
		logger.debug("报销单总条数查询Service开始");
		int total = accountDao.getTotal(username);
		logger.debug("报销单查询总条数结果："+total);
		return total;
	}
	
	/**
	 * 更新报销单
	 */
	@Override
	public boolean updAccount(Account account) {
		logger.debug("Service层更新报销单");
		Boolean flag = accountDao.updAccount(account);
		return flag;
	}

}
