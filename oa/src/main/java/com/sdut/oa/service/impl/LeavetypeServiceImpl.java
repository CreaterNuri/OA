package com.sdut.oa.service.impl;
/**
 * 查询所有请假类型
 */
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdut.oa.dao.ILeavetypeDao;
import com.sdut.oa.entity.Leavetype;
import com.sdut.oa.service.ILeavetypeService;
@Service
public class LeavetypeServiceImpl implements ILeavetypeService {

	private Logger logger=Logger.getLogger(LeavetypeServiceImpl.class);
	
	@Autowired
	private ILeavetypeDao leavetypeDao;//注入dao
	
	/**
	 * 查询所有请假类型
	 */
	@Override
	public List<Leavetype> queryAll() {
		List<Leavetype> list = leavetypeDao.queryAll();
		logger.debug("Service层查询请假类型："+list.get(0));
		return list;
	}

}
