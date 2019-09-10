package com.ffms.Dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ffms.Entity.User;

@Repository
public interface UserDao {
	
	User login(User user);

	User loginUsername(User user);

	User loginRole(User user);

	User loginPassword(User user);

	int getUserIsExists(User user);

	int addregist(User user);

	int addRole(User user);

	List<User> getAllUser(Map<String, Object> userMap);
}
