package com.ffms.Service;



import java.util.List;
import java.util.Map;

import com.ffms.Entity.User;

public interface UserSerivce {
	/**
	 * 	登录
	 * @param user
	 * @return
	 */
	User login(User user);
	/**
	 * 	判断登录的用户名是否存在
	 * @param user
	 * @return
	 */
	User loginUsername(User user);
	/**
	 * 	判断用户的权限是否正确
	 * @param user
	 * @return
	 */
	User loginRole(User user);
	/**
	 *	判断用户的密码是否正确
	 * @param user
	 * @return
	 */
	User loginPassword(User user);
	
	/**
	 * 	判断用户是否存在
	 * @param user
	 * @return
	 */
	int getUserIsExists(User user);
	
	/**
	 * 	注册用户
	 * @param regUser
	 * @return
	 */
	int addregist(User regUser);
	
	/**
	 * 
	 * @param regUser
	 * @return
	 */
	int addRole(User regUser);
	
	/**
	 * 	查询用户
	 * @param userMap
	 * @return
	 */
	List<User> getAllUser(Map<String, Object> userMap);
}
