package com.sdut.oa.service.impl;
/**
 * 用户角色
 */
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdut.oa.dao.IPowerDao;
import com.sdut.oa.entity.Power;
import com.sdut.oa.service.IPowerService;
@Service
public class IPowerServiceImpl implements IPowerService {

	private Logger logger=Logger.getLogger(IPowerServiceImpl.class);
	@Autowired
	private IPowerDao powerDao;
	
	/**
	 * 查询所有用户角色
	 */
	@Override
	public List<Power> queryAll() {
		List<Power> list = powerDao.queryAll();
		return list;
	}

}
