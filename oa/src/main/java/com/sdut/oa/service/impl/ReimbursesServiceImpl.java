package com.sdut.oa.service.impl;
/**
 * 报销类型 Service
 */
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdut.oa.dao.IReimbursesDao;
import com.sdut.oa.entity.Reimburses;
import com.sdut.oa.service.IReimbursesService;
@Service
public class ReimbursesServiceImpl implements IReimbursesService {
	private Logger logger =Logger.getLogger(ReimbursesServiceImpl.class);
	@Autowired
	private IReimbursesDao reimbursesDao;//注入dao层
	
	/**
	 * 查询所有的报销类型
	 */
	@Override
	public List<Reimburses> findAll() {
		List<Reimburses> list = reimbursesDao.findAll();
		logger.debug("Service层查询报销类型："+list.get(0));
		return list;
	}

}
