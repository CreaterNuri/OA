package com.sdut.oa.service.impl;
/**
 * 管理员 service
 */
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdut.oa.dao.IAdminDao;
import com.sdut.oa.entity.Admin;
import com.sdut.oa.service.IAdminService;
@Service
public class AdminServiceImpl implements IAdminService {

	private Logger logger=Logger.getLogger(AdminServiceImpl.class);
	
	@Autowired
	private IAdminDao adminDao;//注入Dao
	/**
	 * 管理员登陆
	 */
	@Override
	public Admin login(Admin admin) {
		logger.debug("service层管理员登陆");
		Admin login = adminDao.login(admin);
		if(login==null){
			logger.debug("service层查询管理员数据为空");
			return null;
		}
		logger.debug("service层查询管理员数据："+login.getName());
		return login;
	}

}
