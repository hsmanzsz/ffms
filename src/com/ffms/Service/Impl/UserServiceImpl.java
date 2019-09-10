package com.ffms.Service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ffms.Dao.UserDao;
import com.ffms.Entity.User;
import com.ffms.Service.UserSerivce;
import com.ffms.Utils.DateUtils;

@Service
public class UserServiceImpl implements UserSerivce{
	
	@Resource
	private UserDao userdao;
	
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return userdao.login(user);
	}

	@Override
	public User loginUsername(User user) {
		// TODO Auto-generated method stub
		return userdao.loginUsername(user);
	}

	@Override
	public User loginRole(User user) {
		// TODO Auto-generated method stub
		return userdao.loginRole(user);
	}

	@Override
	public User loginPassword(User user) {
		// TODO Auto-generated method stub
		return userdao.loginPassword(user);
	}

	@Override
	public int getUserIsExists(User user) {
		// TODO Auto-generated method stub
		return userdao.getUserIsExists(user);
	}

	@Override
	public int addregist(User user) {
		// TODO Auto-generated method stub
		//	 设置用户的注册时间
		user.setCreatetime(DateUtils.getCurrentTime());
		return userdao.addregist(user);
	}

	@Override
	public int addRole(User user) {
		// TODO Auto-generated method stub
		return userdao.addRole(user);
	}

	@Override
	public List<User> getAllUser(Map<String, Object> userMap) {
		// TODO Auto-generated method stub
		return userdao.getAllUser(userMap);
	}

}
