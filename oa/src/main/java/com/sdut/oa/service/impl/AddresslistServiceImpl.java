package com.sdut.oa.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 通讯录管理 Service
 */
import org.springframework.stereotype.Service;

import com.sdut.oa.dao.IAddresslistDao;
import com.sdut.oa.entity.Addresslist;
import com.sdut.oa.service.IAddresslistService;
@Service
public class AddresslistServiceImpl implements IAddresslistService {
	private Logger logger = Logger.getLogger(Addresslist.class);
	
	@Autowired
	private IAddresslistDao addresslistDao;//注入Dao层
	
	/**
	 * 根据姓名查询
	 */
	@Override
	public Addresslist findByName(String name) {
		logger.debug("service根据姓名查询："+name);
		Addresslist findUserByName = addresslistDao.findUserByName(name);
		logger.debug("Service根据姓名查询数据："+findUserByName);
		return findUserByName;
	}

	/**
	 * 根据id查询
	 */
	@Override
	public Addresslist findById(int id) {
		logger.debug("service根据id查询："+id);
		Addresslist findUserById = addresslistDao.findUserById(id);
		logger.debug("Service根据id查询数据"+findUserById);
		return findUserById;
	}
	
	/**
	 * 查看所有通讯录信息
	 */
	@Override
	public List<Addresslist> queryAll(int startRow, int pageSize) {
		List<Addresslist> list = addresslistDao.queryAll(startRow, pageSize);
		logger.debug("service层查询通讯录数量："+list);
		return list;
	}
	
	/**
	 * 查询所有通讯记录数量
	 * @return
	 */
	@Override
	public int getTotal() {
		int total = addresslistDao.getTotal();
		return total;
	}
	
	/**
	 * 添加用户联系方式
	 */
	@Override
	public boolean addAddress(Addresslist addresslist) {
		boolean addAddressflag = addresslistDao.addAddress(addresslist);
		logger.debug("Service层添加用户联系方式："+addAddressflag);
		return addAddressflag;
	}
	
	/**
	 * 更新用户联系方式
	 */
	@Override
	public boolean updateAddress(Addresslist addresslist) {
		boolean updateAddressflag = addresslistDao.updateAddress(addresslist);
		logger.debug("Service层更新用户联系方式："+updateAddressflag);
		return updateAddressflag;
	}
	
	/**
	 * 删除用户联系方式
	 */
	@Override
	public boolean deleteAddress(int id) {
		boolean deleteAddressflag = addresslistDao.deleteAddress(id);
		logger.debug("Service层删除用户联系方式："+deleteAddressflag);
		return deleteAddressflag;
	}

}
