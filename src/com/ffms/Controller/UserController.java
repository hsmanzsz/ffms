package com.ffms.Controller;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ffms.Entity.User;
import com.ffms.Service.UserSerivce;
/**
 * 	用户管理
 * @author zsz
 *
 */
@Controller
public class UserController {
	@Resource
	private UserSerivce userservice;
	
	/**
	 * 	用户的登录判断 
	 * @param user
	 * @param request
	 * @return
	 * @throws JsonProcessingException
	 */
	
	@RequestMapping(value= "/login.do",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String login(User user,HttpServletRequest request) throws JsonProcessingException{
		
		System.out.println("这是控制层");
		//	创建jackson的核心配置对象
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getRoleid());
		ObjectMapper mapper = new ObjectMapper(); 
		Map<String,String> map = new HashMap<String, String>(); 
		String json = null; 
		User reUsername = userservice.loginUsername(user); 
		if(reUsername == null) { 
			map.put("erroes","101"); 
			map.put("errmsg", "用户名不存在！");
		  }else{ 
			  User reUserPassword = userservice.loginPassword(user);
			  if(reUserPassword == null) { 
				  map.put("erroes", "102"); 
				  map.put("errmsg","密码不正确！"); 
				  }else { 
					  User reUserrole = userservice.loginRole(user); 
					  if(reUserrole == null) { 
						  map.put("erroes", "103");
						  map.put("errmsg", "用户角色不匹配！"); 
					  }else{ 
						  HttpSession session = request.getSession();
						  session.setAttribute("currentUser", reUserrole);
						  System.out.println(reUserrole.getUsername()); 
						  map.put("erroes", "200");  
						  } 
					  } 
			  }
		 json = mapper.writeValueAsString(map);
		 System.out.println(json);
		 return json;
	}
	
	/**
	 * 
	 * @param regUser
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value= "/regist.do",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String regist(User regUser) throws JsonProcessingException {
		System.out.println(regUser.getUsername());
		System.out.println(regUser.getPassword());
		System.out.println(regUser.getRoleid());
		System.out.println("注册方法");
		//	判断用户名是否存在
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		Map<String,String> map = new HashMap<String, String>();
		if(isUserExists(regUser)) {
			map.put("erroes", "100");
			map.put("errmsg","用户名已存在!");
		}else {
			//	不存在就注册用户并且 给用户默认的权限
			int resultCount = userservice.addregist(regUser);
			//	给用户权限
			int resultColumn = userservice.addRole(regUser);
			if((resultCount>0) && (resultColumn>0)) {
				map.put("erroes", "200");
				map.put("errmsg", "注册成功！");
			}else {
				map.put("erroes","201");
				map.put("errmsg","注册失败！");
			}
		}
		json = mapper.writeValueAsString(map);
		return json;
	}
	
	/**
	 * 	判断用户是否存在
	 * @param user
	 * @return
	 */
	public Boolean isUserExists(User user) {
		int resultColumns = 0;
		Boolean flag = false;
		resultColumns = userservice.getUserIsExists(user);
		if(resultColumns>0) {
			flag = true;
		}
		return flag;
		
	}
	
	
	@RequestMapping(value="/getSessionUser.do",produces="application/json;charset=UTF-8")
	@ResponseBody
	public User getSessionUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentUser");
		System.out.println(user);
		return user;
	}
	
	
}
