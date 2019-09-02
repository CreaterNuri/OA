package com.sdut.oa.service.impl;
/**
 * 文件共享管理 service层
 */
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdut.oa.dao.ISharedfileDao;
import com.sdut.oa.entity.Sharedfile;
import com.sdut.oa.service.ISharedfileService;
@Service
public class SharedfileServiceImpl implements ISharedfileService {
	
	private Logger logger =Logger.getLogger(SharedfileServiceImpl.class);
	//注入Dao层
	@Autowired
	private ISharedfileDao sharedfileDao;
	
	/**
	 * 文件列表
	 */
	@Override
	public List<Sharedfile> findAllFiles(int startRow, int pageSize) {
		logger.debug("文件列表查询Service层开始："+startRow+"页面数量："+pageSize);
		List<Sharedfile> list = sharedfileDao.findAllFiles(startRow, pageSize);
		logger.debug("service层查询数据："+list);
		return list;
	}
	
	/**
	 * 添加文件信息
	 */
	@Override
	public Boolean addFile(Sharedfile sharedfile) {
		logger.debug("Service层添加文件信息开始");
		boolean flag = sharedfileDao.saveFile(sharedfile);
		logger.debug("service层添加文件信息标识："+flag);
		return flag;
	}
	
	/**
	 * 查询文件列表数量
	 */
	@Override
	public int getTotal() {
		logger.debug("Service层查询文件列表数量开始");
		int total = sharedfileDao.getTotal();
		logger.debug("service层查询总数量："+total);
		return total;
	}
	
	/**
	 * 根据文件id查询文件所有信息
	 */
	@Override
	public Sharedfile findById(int id) {
		logger.debug("Service层根据文件id查询文件信息："+id);
		Sharedfile sharedfile = sharedfileDao.findById(id);
		return sharedfile;
	}

}
