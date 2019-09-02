package com.sdut.oa.service.impl;
import java.util.List;

/**
 * 用户 service
 */
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdut.oa.dao.IUserDao;
import com.sdut.oa.entity.User;
import com.sdut.oa.service.IUserService;
@Service
public class UserServiceImpl implements IUserService {
	
	private Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private IUserDao userDao;//注入userDao
	
	/**
	 * 用户登陆
	 */
	@Override
	public User login(User user) {
		logger.debug("用户登陆service层开始");
		User loginUser = userDao.login(user);
		if(loginUser==null){
			logger.debug("service层查询用户数据为空");
			return null;
		}
		logger.debug("service层查询用户数据："+loginUser.getUname());
		return loginUser;
	}
	
	/**
	 * 根据用户id查询
	 */
	@Override
	public User findById(int id) {
		User user = userDao.findbyId(id);
		return user;
	}
	
	/**
	 * 修改密码
	 */
	@Override
	public boolean updatePwd(int id, String oldpwd, String newPwd) {
		boolean flag=false;
		User user = userDao.findbyId(id);
		if(oldpwd.equals(user.getUpwd())){
			flag = userDao.updatePwd(id, newPwd);
		}
		return flag;
	}
	
	/**
	 * 查询所有用户信息
	 */
	@Override
	public List<User> queryAll(int startRow, int pageSize) {
		logger.debug("Service查询所有用户信息");
		List<User> list = userDao.queryAll(startRow,pageSize);
		return list;
	}
	
	/**
	 * 添加用户
	 */
	@Override
	public boolean addUser(User user) {
		logger.debug("Service层添加用户信息");
		boolean flag = userDao.addUser(user);
		return flag;
	}
	
	/**
	 * 查询所有用户信息数量
	 */
	@Override
	public int getTotal() {
		int total = userDao.getTotal();
		return total;
	}
	
	/**
	 * 更新用户
	 */
	@Override
	public boolean update(User user) {
		boolean flag = userDao.updateUser(user);
		logger.debug("Service层更新用户标识："+flag);
		return flag;
	}
	
	/**
	 * 删除用户
	 */
	public boolean deleteUser(User user){
		boolean deleteflag = userDao.delete(user);
		logger.debug("Service层删除用户标识："+deleteflag);
		return deleteflag;
	}
}
